package com.hubery.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hubery.dao.SystemLogRecordDao;
import com.hubery.domain.SystemLogRecord;

@Service
public class SystemLogRecordService {

    private SystemLogRecordDao systemLogRecordDao;

    @Transactional
    public int save(SystemLogRecord record) {
        return systemLogRecordDao.save(record);
    }

    @Inject
    public void setSystemLogRecordDao(SystemLogRecordDao systemLogRecordDao) {
        this.systemLogRecordDao = systemLogRecordDao;
    }

}
