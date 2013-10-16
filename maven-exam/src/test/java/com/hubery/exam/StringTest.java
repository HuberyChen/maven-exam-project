package com.hubery.exam;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.hubery.exam.utils.StringUtils;

public class StringTest {

    @Test
    public void stringUtilsTest() {
        System.out.println(StringUtils.removeAll("true is true", "true"));
        assertTrue(StringUtils.removeAll("true is true", "true").equals(" is "));
        System.out.println(StringUtils.remove("$is$", "$"));
    }

}
