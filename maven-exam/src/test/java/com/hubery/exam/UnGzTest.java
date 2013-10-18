package com.hubery.exam;

import org.junit.Test;

import com.hubery.exam.utils.GZip;

public class UnGzTest {

    @Test
    public void test() {
        GZip.unTargzFile("D:\\test\\tax-service-action.2013-09-14_22.log.gz", "D:\\test\\log");
    }
}
