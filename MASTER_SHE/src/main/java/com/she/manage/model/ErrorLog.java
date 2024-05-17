package com.she.manage.model;

public class ErrorLog {

    private int logNo;
    private String logClassCd;
    private String logInfo;
    private String statusCd;
    private String queryString;
    private String cause;
    private String causeMessage;
    private String createDt;
    // private String userId;
    // private String userNm;
    // private String deptNm;
    // private String plantNm;

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

    // public String getUserId() {
    // return userId;
    // }
    //
    // public void setUserId(String userId) {
    // this.userId = userId;
    // }
    //
    // public String getDeptNm() {
    // return deptNm;
    // }
    //
    // public void setDeptNm(String deptNm) {
    // this.deptNm = deptNm;
    // }
    //
    // public String getPlantNm() {
    // return plantNm;
    // }
    //
    // public void setPlantNm(String plantNm) {
    // this.plantNm = plantNm;
    // }
}
