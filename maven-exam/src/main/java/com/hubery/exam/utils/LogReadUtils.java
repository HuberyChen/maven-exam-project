package com.hubery.exam.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class LogReadUtils {

    public static String ERROR_MESSAGE = "ERROR";
    public static String WARN_MESSAGE = "WARN";
    public static String ENTER_STRING = "\n";

    public static void scanErrorOrWarnLogFilter(String path, String filter) {
        List<String> filters = new ArrayList<>();
        if (null == filter) {
            filter = "\\S*";
        }
        filters.add("\\D*." + filter + "_\\d*.log");
        FileScanUtils.scan(path, filters);
    }

    @SuppressWarnings("resource")
    public static String LogReadErrorOrWarn(File file) {
        boolean isError = false;
        String warnMessage = "";
        String str = "";
        StringBuilder stringBuilder = new StringBuilder();

        InputStreamReader inputReader = null;
        BufferedReader bufferReader = null;

        InputStream inputStream;
        try {
            inputStream = new FileInputStream(file);
            inputReader = new InputStreamReader(inputStream);
            bufferReader = new BufferedReader(inputReader);

            // ∂¡»°“ª––
            while ((str = bufferReader.readLine()) != null) {
                if (str.contains(WARN_MESSAGE) || str.contains(ERROR_MESSAGE)) {
                    stringBuilder.append(str).append(ENTER_STRING);
                    if (!isError) {
                        isError = true;
                    }
                }
            }
            if (isError) {
                stringBuilder.append(file.getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (null == stringBuilder.toString().trim() || stringBuilder.toString().equals("")) {
            return null;
        }
        warnMessage = stringBuilder.toString();
        return warnMessage;
    }
}
