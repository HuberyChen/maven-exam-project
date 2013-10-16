package com.hubery.exam;

import java.io.File;

import org.junit.Test;

public class ScanTest {

    @Test
    public void test() {
        String filePath = "C:/logs";
        File f = new File(filePath);
        if (f.isDirectory()) { // 首先判断该路径是否是文件夹，如果不是就自己结束吧，此处省略不是文件夹的情况
            File[] fileList = f.listFiles();// 得到该文件夹下的所有文件和文件夹列表
            for (File fs : fileList) { // 循环该列表
                if (fs.isFile()) { // 如果得到的为文件，则提示
                    System.out.println("有文件");
                    System.out.println();
                    System.out.println("");
                }
            }
        }
    }

}
