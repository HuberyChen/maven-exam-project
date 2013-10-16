package com.hubery.exam.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

    public static String removeAll(String source, String sub) {
        return source.replaceAll(sub, "");
    }

    public static String remove(String source, String sub) {
        return source.replace(sub, "");
    }

    public static int wordCount(String str) {
        Pattern pattern = Pattern.compile("\\b\\w+\\b");
        Matcher matcher = pattern.matcher(str);
        int wordsCount = 0;
        while (matcher.find()) {
            wordsCount++;
        }
        return wordsCount;
    }
}
