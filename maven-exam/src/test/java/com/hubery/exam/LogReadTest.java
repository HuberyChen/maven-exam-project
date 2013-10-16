package com.hubery.exam;

import java.io.File;
import java.util.Stack;

import org.junit.Test;

import com.hubery.exam.utils.LogReadUtils;

public class LogReadTest {

    @Test
    public void test() {
        File root = new File("D:\\log\\vtxlogs\\Prod-vtxsvc2\\2013\\09\\30");
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
                    System.out.println(LogReadUtils.LogReadWarn(file));
                }
            }
        }
    }
}
