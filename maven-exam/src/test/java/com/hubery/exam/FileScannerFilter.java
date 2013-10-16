package com.hubery.exam;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Pattern;

import org.junit.Test;

public class FileScannerFilter {

    public void scan(String path, List<String> filters) {
        Long lineNum = 0l;
        Long fileNum = 0l;
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
                    fileNum++;
                }

            }

        }

        System.out.println("parse " + fileNum + " file  and all  line  is " + lineNum);

    }

    @Test
    public void test() {
        final String SCAN_FOLDER = "F:\\Emm\\trunk";
        FileScanner scanner = new FileScanner();
        // scanner.scan(SCAN_FOLDER, ".*\.ftl");
    }
}
