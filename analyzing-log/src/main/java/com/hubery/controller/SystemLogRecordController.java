package com.hubery.controller;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hubery.domain.SystemLogRecord;
import com.hubery.service.SystemLogRecordService;
import com.quidsi.core.platform.monitor.Track;
import com.quidsi.core.platform.web.site.scheme.HTTPSOnly;

@Controller
@HTTPSOnly
public class SystemLogRecordController {

    private SystemLogRecordService systemLogRecordService;

    @RequestMapping(value = "/log/path/{SCAN_FOLDER}", method = RequestMethod.GET)
    @ResponseBody
    @Track(warningThresholdInMs = 5000)
    public void LogRead(@Valid @RequestParam String SCAN_FOLDER) {
        systemLogRecordService.decompression(SCAN_FOLDER);
        List<SystemLogRecord> records = systemLogRecordService.scanLogFilter(SCAN_FOLDER, null);
        if (!CollectionUtils.isEmpty(records)) {
            for (SystemLogRecord record : records) {
                systemLogRecordService.save(record);
            }
        }
    }

    @Inject
    public void setSystemLogRecordService(SystemLogRecordService systemLogRecordService) {
        this.systemLogRecordService = systemLogRecordService;
    }

}
