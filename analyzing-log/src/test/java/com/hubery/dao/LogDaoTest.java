package com.hubery.dao;

import java.util.Date;

import javax.inject.Inject;

import org.junit.Test;

import com.hubery.SpringTest;
import com.hubery.domain.SystemLogRecord;
import com.hubery.domain.SystemLogRecord.IsAnalyzed;
import com.hubery.domain.SystemLogRecord.SystemType;

public class LogDaoTest extends SpringTest {

    private SystemLogRecordDao dao;

    @Test
    public void logRecordDaoTest() {
        SystemLogRecord record = new SystemLogRecord();
        record.setElapsedTime(1);
        record.setErrorCode("404");
        record.setExceptionMsg("");
        record.setHost("1");
        record.setInterfaceName("");
        record.setIsAnalyzed(IsAnalyzed.N);
        record.setLogAddress("");
        record.setLogName("");
        record.setLogTime(new Date());
        record.setRequestMethod("");
        record.setStatus("");
        record.setSystem(SystemType.GiftcoSERVICE);
        dao.save(record);
    }

    @Inject
    public void setDao(SystemLogRecordDao dao) {
        this.dao = dao;
    }

}
