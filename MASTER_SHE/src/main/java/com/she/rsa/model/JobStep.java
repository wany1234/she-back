package com.she.rsa.model;

public class JobStep {

    private int jobStepId;

    private int jobStepNo;

    private int selectedJobStepNo;

    private int jobId;

    private String jobStepNm;

    private String jobStepDesc;

    private int jobStepGroupNo;

    private double revNo;

    private String revContents;

    private String useYn;

    private String useYnNm;

    private String createUserId;

    private String createDt;

    private String updateUserId;

    private String updateDt;

    public int getJobStepId() {
        return jobStepId;
    }

    public void setJobStepId(int jobStepId) {
        this.jobStepId = jobStepId;
    }

    public int getJobStepGroupNo() {
        return jobStepGroupNo;
    }

    public void setJobStepGroupNo(int jobStepGroupNo) {
        this.jobStepGroupNo = jobStepGroupNo;
    }

    public double getRevNo() {
        return revNo;
    }

    public void setRevNo(double revNo) {
        this.revNo = revNo;
    }

    public String getRevContents() {
        return revContents;
    }

    public void setRevContents(String revContents) {
        this.revContents = revContents;
    }

    public int getJobStepNo() {
        return jobStepNo;
    }

    public void setJobStepNo(int jobStepNo) {
        this.jobStepNo = jobStepNo;
    }

    public int getSelectedJobStepNo() {
        return selectedJobStepNo;
    }

    public void setSelectedJobStepNo(int selectedJobStepNo) {
        this.selectedJobStepNo = selectedJobStepNo;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public String getJobStepNm() {
        return jobStepNm;
    }

    public void setJobStepNm(String jobStepNm) {
        this.jobStepNm = jobStepNm;
    }

    public String getJobStepDesc() {
        return jobStepDesc;
    }

    public void setJobStepDesc(String jobStepDesc) {
        this.jobStepDesc = jobStepDesc;
    }

    public String getUseYn() {
        return useYn;
    }

    public void setUseYn(String useYn) {
        this.useYn = useYn;
    }

    public String getUseYnNm() {
        return useYnNm;
    }

    public void setUseYnNm(String useYnNm) {
        this.useYnNm = useYnNm;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getCreateDt() {
        return createDt;
    }

    public void setCreateDt(String createDt) {
        this.createDt = createDt;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    public String getUpdateDt() {
        return updateDt;
    }

    public void setUpdateDt(String updateDt) {
        this.updateDt = updateDt;
    }
}
