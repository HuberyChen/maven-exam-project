package com.hubery.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.hubery.domain.SystemLogRecord;
import com.quidsi.core.database.JPAAccess;

@Repository
public class SystemLogRecordDao {

    private JPAAccess jpaAccess;

    public int save(SystemLogRecord systemLogRecord) {
        jpaAccess.save(systemLogRecord);
        return systemLogRecord.getId();
    }

    public SystemLogRecord getRecordByLogTimeAndHost(Date logTime, String host) {
        StringBuilder sql = new StringBuilder();
        Map<String, Object> params = new HashMap<>();
        params.put("LogTime", logTime);
        params.put("Host", host);
        sql.append("from ").append(SystemLogRecord.class.getName()).append(" where LogTime = :LogTime and Host = :Host");
        return jpaAccess.findUniqueResult(sql.toString(), params);
    }

    @Inject
    public void setJpaAccess(JPAAccess jpaAccess) {
        this.jpaAccess = jpaAccess;
    }

}
