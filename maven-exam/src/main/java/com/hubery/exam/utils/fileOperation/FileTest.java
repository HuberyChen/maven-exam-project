package com.hubery.exam.utils.fileOperation;

public class FileTest {

    public static void main(String[] args) {
        FileScan fileScan = new FileScan("D:/test/", new UnFileFactory());
        FileScanDecorator fsDecorator = new FileScanDecorator();
        fsDecorator.setOperation(fileScan);
        fsDecorator.getTime();
    }

}
