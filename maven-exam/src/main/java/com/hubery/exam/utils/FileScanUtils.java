package com.hubery.exam.utils;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Pattern;

import org.springframework.util.CollectionUtils;

public class FileScanUtils {

    public static void scan(String path, List<String> filters) {
        String message;
        File root = new File(path);
        if (root.exists()) {
            Stack<File> fileStack = new Stack<File>();
            fileStack.add(root);
            final List<Pattern> fileNamePatterns = new ArrayList<Pattern>();
            if (filters != null && filters.size() > 0) {
                for (String f : filters) {
                    fileNamePatterns.add(Pattern.compile(f));
                }
            }

            while (!fileStack.isEmpty()) {
                final File file = fileStack.pop();
                if (file.isDirectory()) {
                    for (File f : file.listFiles(new FileFilter() {
                        public boolean accept(File file) {
                            if (file.isDirectory())
                                return true;

                            if (CollectionUtils.isEmpty(fileNamePatterns)) {
                                return true;
                            }

                            for (Pattern p : fileNamePatterns) {
                                if (p.matcher(file.getName()).matches()) {
                                    return true;
                                }
                            }
                            return false;
                        }
                    })) {
                        fileStack.add(f);
                    }
                } else {
                    message = LogReadUtils.LogReadErrorOrWarn(file);
                    if (null != message) {
                        System.out.println(LogReadUtils.LogReadErrorOrWarn(file));
                    }
                }

            }

        }
    }
}
