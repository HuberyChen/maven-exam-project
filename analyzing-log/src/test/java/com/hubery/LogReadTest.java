package com.hubery;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.junit.Test;

import com.hubery.utils.FileScan;
import com.hubery.utils.FileScanDecorator;
import com.hubery.utils.FileScanUtils;
import com.hubery.utils.LogReadUtils;
import com.hubery.utils.UnFileFactory;

public class LogReadTest {

    @Test
    public void scanTest() {
        final String SCAN_FOLDER = "D:\\test";
        // final String SCAN_FOLDER =
        // "D:\\log\\vtxlogs\\Prod-vtxsvc1\\2013\\action\\log";
        List<String> filter = new ArrayList<>();
        filter.add("\\D*.\\S*_\\d*.log");
        // filter.add("\\D*.2013-09-30_\\d*.log");
        FileScanUtils.scan(SCAN_FOLDER, filter);
    }

    @Test
    public void logReadTest() {
        final String SCAN_FOLDER = "D:\\log\\Prod-gmsvc2\\2013\\10\\16\\action";
        // final String SCAN_FOLDER =
        // "D:\\log\\vtxlogs\\Prod-vtxsvc2\\2013\\action\\decompression";

        FileScan fileScan = new FileScan(SCAN_FOLDER, new UnFileFactory());
        FileScanDecorator fsDecorator = new FileScanDecorator();
        fsDecorator.setOperation(fileScan);
        fsDecorator.getTime();

        LogReadUtils.scanNotSuccessLogFilter(SCAN_FOLDER, null);
    }

    @Test
    public void test() {
        File root = new File("D:\\log\\vtxlogs\\Prod-vtxsvc1\\2013\\action\\log");
        String message = "";
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
                    message = LogReadUtils.logReadNotSuccess(file);
                    if (null != message) {
                        System.out.println(LogReadUtils.logReadNotSuccess(file));
                    }
                }
            }
        }
    }
}
