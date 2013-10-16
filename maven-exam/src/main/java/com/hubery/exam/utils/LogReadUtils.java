package com.hubery.exam.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

public class LogReadUtils {

    public static String ERROR_MESSAGE = "ERROR";
    public static String WARN_MESSAGE = "WARN";
    public static String ENTER_STRING = "\n";

    @SuppressWarnings("resource")
    public static String LogReadWarn(File file) {
        String warnMessage = "";
        String str = "";
        StringBuilder stringBuilder = new StringBuilder();

        InputStreamReader inputReader = null;
        BufferedReader bufferReader = null;

        try {
            InputStream inputStream = new FileInputStream(file);
            inputReader = new InputStreamReader(inputStream);
            bufferReader = new BufferedReader(inputReader);

            // ∂¡»°“ª––
            while ((str = bufferReader.readLine()) != null) {
                if (str.contains(WARN_MESSAGE) || str.contains(ERROR_MESSAGE)) {
                    stringBuilder.append(str).append(ENTER_STRING);
                }
            }
            stringBuilder.append(file.getName());
        } catch (Exception e) {
        }

        warnMessage = stringBuilder.toString();
        return warnMessage;
    }
}
