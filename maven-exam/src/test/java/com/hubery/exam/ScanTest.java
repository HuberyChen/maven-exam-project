package com.hubery.exam;

import java.io.File;

import org.junit.Test;

public class ScanTest {

    @Test
    public void test() {
        String filePath = "C:/logs";
        File f = new File(filePath);
        if (f.isDirectory()) { // �����жϸ�·���Ƿ����ļ��У�������Ǿ��Լ������ɣ��˴�ʡ�Բ����ļ��е����
            File[] fileList = f.listFiles();// �õ����ļ����µ������ļ����ļ����б�
            for (File fs : fileList) { // ѭ�����б�
                if (fs.isFile()) { // ����õ���Ϊ�ļ�������ʾ
                    System.out.println("���ļ�");
                    System.out.println();
                    System.out.println("");
                }
            }
        }
    }

}
