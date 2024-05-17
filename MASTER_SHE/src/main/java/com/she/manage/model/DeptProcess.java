package com.she.manage.model;

public class DeptProcess {

    private String deptCd;

    private int processNo;

    private String processCd;

    private String deptNm;

    private String processNm;

    private String createUserId;

    private String createUserNm;

    private String createDt;

    public String getDeptNm() {
        return deptNm;
    }

    public void setDeptNm(String deptNm) {
        this.deptNm = deptNm;
    }

    public String getProcessNm() {
        return processNm;
    }

    public void setProcessNm(String processNm) {
        this.processNm = processNm;
    }

    public String getDeptCd() {
        return deptCd;
    }

    public void setDeptCd(String deptCd) {
        this.deptCd = deptCd;
    }

    public int getProcessNo() {
        return processNo;
    }

    public String getProcessCd() {
        return processCd;
    }

    public void setProcessCd(String processCd) {
        this.processCd = processCd;
    }

    public void setProcessNo(int processNo) {
        this.processNo = processNo;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getCreateUserNm() {
        return createUserNm;
    }

    public void setCreateUserNm(String createUserNm) {
        this.createUserNm = createUserNm;
    }

    public String getCreateDt() {
        return createDt;
    }

    public void setCreateDt(String createDt) {
        this.createDt = createDt;
    }

}
