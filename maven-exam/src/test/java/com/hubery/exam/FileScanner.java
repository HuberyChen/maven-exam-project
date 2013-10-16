package com.hubery.exam;

import java.io.File;
import java.util.Stack;

import org.junit.Test;

public class FileScanner {

    public void scan(String path) {
        Long fileNum = 0l;
        File root = new File(path);
        if (root.exists()) {
            Stack<File> fileStack = new Stack<File>();
            fileStack.add(root);

            while (!fileStack.isEmpty()) {
                final File file = fileStack.pop();
                if (file.isDirectory()) {
                    for (File f : file.listFiles()) {
                        fileStack.add(f);
                    }
                } else {
                    fileNum++;
                }
            }
        }
        System.out.println("parse " + fileNum + " file ");
    }

    @Test
    public void tetst() {
        final String SCAN_FOLDER = "D:/rarTest/rar/";
        FileScanner scanner = new FileScanner();
        scanner.scan(SCAN_FOLDER);

    }
}
