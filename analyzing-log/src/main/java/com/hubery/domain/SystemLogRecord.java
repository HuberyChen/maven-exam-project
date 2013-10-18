package com.hubery.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "System_Log_Record")
public class SystemLogRecord {

    @Id
    @GeneratedValue
    @Column(name = "Id")
    private int id;

    @Column(name = "LogTime")
    private Date logTime;

    @Column(name = "LogName")
    private String logName;

    @Enumerated(EnumType.STRING)
    @Column(name = "System")
    private SystemType system;

    @Column(name = "Host")
    private String host;

    @Column(name = "Status")
    private String status;

    @Column(name = "Interface")
    private String interfaceName;

    @Column(name = "ElapsedTime")
    private int elapsedTime;

    @Column(name = "RequestMethod")
    private String requestMethod;

    @Column(name = "ErrorCode")
    private String errorCode;

    @Column(name = "ExceptionMsg")
    private String exceptionMsg;

    @Column(name = "LogAddress")
    private String logAddress;

    @Enumerated(EnumType.STRING)
    @Column(name = "IsAnalyzed")
    private IsAnalyzed isAnalyzed;

    public enum IsAnalyzed {
        Y, N
    }

    public enum SystemType {
        TaxSERVICE, GiftcoSERVICE, GiftmessageService, VertexLOG
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getLogTime() {
        return logTime;
    }

    public void setLogTime(Date logTime) {
        this.logTime = logTime;
    }

    public String getLogName() {
        return logName;
    }

    public void setLogName(String logName) {
        this.logName = logName;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public int getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(int elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getExceptionMsg() {
        return exceptionMsg;
    }

    public void setExceptionMsg(String exceptionMsg) {
        this.exceptionMsg = exceptionMsg;
    }

    public String getLogAddress() {
        return logAddress;
    }

    public void setLogAddress(String logAddress) {
        this.logAddress = logAddress;
    }

    public IsAnalyzed getIsAnalyzed() {
        return isAnalyzed;
    }

    public void setIsAnalyzed(IsAnalyzed isAnalyzed) {
        this.isAnalyzed = isAnalyzed;
    }

    public SystemType getSystem() {
        return system;
    }

    public void setSystem(SystemType system) {
        this.system = system;
    }

}
