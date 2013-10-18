package com.hubery.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class LogReadUtils {

    public static String SUCCESS_MESSAGE = "SUCCESS";
    public static String ENTER_STRING = "\n";

    public static void scanNotSuccessLogFilter(String path, String filter) {
        List<String> filters = new ArrayList<>();
        if (null == filter) {
            filter = "\\S*";
        }
        filters.add("\\D*." + filter + "_\\d*.log");
        FileScanUtils.scan(path, filters);
    }

    @SuppressWarnings("resource")
    public static String logReadNotSuccess(File file) {
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
                String[] messages = str.split("\\|");
                if (!messages[1].trim().equalsIgnoreCase(SUCCESS_MESSAGE)) {
                    stringBuilder.append(messages[0]).append(messages[1]).append(messages[3]).append(messages[4]).append(messages[7]).append(messages[8]).append(messages[9]).append(messages[11])
                            .append(ENTER_STRING);
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
