package com.hubery.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.hubery.dao.SystemLogRecordDao;
import com.hubery.domain.SystemLogRecord;
import com.hubery.utils.FileScan;
import com.hubery.utils.FileScanDecorator;
import com.hubery.utils.FileScanUtils;
import com.hubery.utils.UnFileFactory;
import com.quidsi.core.util.DateUtils;

@Service
public class SystemLogRecordService {

    public static String ENTER_STRING = "\n";

    private SystemLogRecordDao systemLogRecordDao;

    @Transactional
    public int save(SystemLogRecord record) {
        return systemLogRecordDao.save(record);
    }

    public List<SystemLogRecord> scanLogFilter(String path, String dataFilter) {
        List<String> filters = new ArrayList<>();
        if (null == dataFilter) {
            dataFilter = "\\S*";
        }
        filters.add("\\D*." + dataFilter + "_\\d*.log");
        return logsRead(FileScanUtils.scan(path, filters));
    }

    public void decompression(String SCAN_FOLDER) {
        FileScan fileScan = new FileScan(SCAN_FOLDER, new UnFileFactory());
        FileScanDecorator fsDecorator = new FileScanDecorator();
        fsDecorator.setOperation(fileScan);
        fsDecorator.getTime();
    }

    private List<SystemLogRecord> logsRead(List<File> logs) {
        List<SystemLogRecord> records = new ArrayList<>();
        if (!CollectionUtils.isEmpty(logs)) {
            for (File file : logs) {
                records.add(logRead(file));
            }
        }
        return records;
    }

    @SuppressWarnings("resource")
    private SystemLogRecord logRead(File file) {
        String pathName = file.getParent();
        SystemLogRecord record = initializeSetSystemAndHost(pathName);
        record.setLogName(file.getName());
        String str = "";

        InputStreamReader inputReader = null;
        BufferedReader bufferReader = null;

        InputStream inputStream;
        try {
            inputStream = new FileInputStream(file);
            inputReader = new InputStreamReader(inputStream);
            bufferReader = new BufferedReader(inputReader);

            // ∂¡»°“ª––
            while ((str = bufferReader.readLine()) != null) {
                String[] messages = str.split("\\|");
                record = dataConverToRecord(messages, record);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return record;
    }

    private SystemLogRecord initializeSetSystemAndHost(String pathName) {
        SystemLogRecord record = new SystemLogRecord();
        if (pathName.contains("gcsvc")) {
            record.setSystem(SystemLogRecord.SystemType.GiftcoSERVICE);
            if (pathName.contains("gcsvc1")) {
                record.setHost("GCSVC1");
            } else {
                record.setHost("GCSVC2");
            }
        }

        if (pathName.contains("gmsvc")) {
            record.setSystem(SystemLogRecord.SystemType.GiftmessageService);
            if (pathName.contains("gmsvc1")) {
                record.setHost("GMSVC1");
            } else {
                record.setHost("GMSVC2");
            }
        }

        if (pathName.contains("vtxsvc")) {
            if (pathName.contains("vtxlogs")) {
                record.setSystem(SystemLogRecord.SystemType.VertexLOG);
                if (pathName.contains("vtxsvc1")) {
                    record.setHost("VTXLOG-VTXSVC1");
                } else {
                    record.setHost("VTXLOG-VTXSVC2");
                }
            } else {
                record.setSystem(SystemLogRecord.SystemType.TaxSERVICE);
                if (pathName.contains("vtxsvc1")) {
                    record.setHost("VTXSVC1");
                } else {
                    record.setHost("VTXSVC2");
                }
            }
        }

        return record;
    }

    private SystemLogRecord dataConverToRecord(String[] messages, SystemLogRecord record) {
        record.setLogTime(dataConverToDate(messages[0]));
        record.setStatus(messages[1]);
        record.setInterfaceName(messages[2]);
        record.setElapsedTime(Integer.parseInt(messages[3]));
        record.setRequestMethod(messages[4]);
        record.setErrorCode(messages[5]);
        record.setExceptionMsg(messages[6]);
        record.setLogAddress(messages[7]);
        record.setIsAnalyzed(SystemLogRecord.IsAnalyzed.Y);
        return record;
    }

    private Date dataConverToDate(String dateMessage) {
        String[] date = dateMessage.split("//-");
        int[] dateTime;
        dateTime = new int[5];
        for (int i = 0; i < 5; i++) {
            dateTime[i] = Integer.parseInt(date[i]);
        }
        return DateUtils.date(dateTime[0], dateTime[1], dateTime[2], dateTime[3], dateTime[4], dateTime[5]);
    }

    @Inject
    public void setSystemLogRecordDao(SystemLogRecordDao systemLogRecordDao) {
        this.systemLogRecordDao = systemLogRecordDao;
    }

}
