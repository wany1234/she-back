package com.she.common.model;

/**
 * com_log 테이블의 모델 클래스
 */
public class Log {

    private int logNo;
    private String logClassCd;
    private String logInfo;
    private String statusCd;
    private String queryString;
    private String cause;
    private String causeMessage;
    private String createDt;

    public Log(String logClassCd, String logInfo, String statusCd, String queryString, String cause, String createDt) {
        this.logClassCd = logClassCd;
        this.logInfo = logInfo;
        this.statusCd = statusCd;
        this.queryString = queryString;
        this.cause = cause;
        this.createDt = createDt;
    }

    public Log(String logClassCd, String logInfo, String statusCd, String queryString, String cause, String causeMessage, String createDt) {
        this.logClassCd = logClassCd;
        this.logInfo = logInfo;
        this.statusCd = statusCd;
        this.queryString = queryString;
        this.cause = cause;
        this.causeMessage = causeMessage;
        this.createDt = createDt;
    }

    public int getLogNo() {
        return logNo;
    }

    public void setLogNo(int logNo) {
        this.logNo = logNo;
    }

    public String getLogClassCd() {
        return logClassCd;
    }

    public void setLogClassCd(String logClassCd) {
        this.logClassCd = logClassCd;
    }

    public String getLogInfo() {
        return logInfo;
    }

    public void setLogInfo(String logInfo) {
        this.logInfo = logInfo;
    }

    public String getStatusCd() {
        return statusCd;
    }

    public void setStatusCd(String statusCd) {
        this.statusCd = statusCd;
    }

    public String getQueryString() {
        return queryString;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public String getCauseMessage() {
        return causeMessage;
    }

    public void setCauseMessage(String causeMessage) {
        this.causeMessage = causeMessage;
    }

    public String getCreateDt() {
        return createDt;
    }

    public void setCreateDt(String createDt) {
        this.createDt = createDt;
    }

}
