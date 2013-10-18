package com.hubery.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.http.MediaType;

import com.hubery.SpringServiceTest;

public class LogRecordTest extends SpringServiceTest {

    @Test
    public void logRecordTest() throws Exception {
        mockMvc.perform(get("/log/path").content(MediaType.APPLICATION_FORM_URLENCODED_VALUE).param("SCAN_FOLDER", "D:\\log\\Prod-gcsvc1\\2013\\10\\15\\action")).andExpect(status().isOk());
    }
}
