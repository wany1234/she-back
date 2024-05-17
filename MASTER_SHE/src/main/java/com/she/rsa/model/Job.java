package com.she.rsa.model;

import io.swagger.annotations.ApiModelProperty;

public class Job {

    private int jobId;

    private String jobCd;

    private int jobRevno;

    private String jobNm;

    private String sopNo;

    private String sopNm;

    private String processCd;

    private String processNm;

    private String deptCd;

    private String deptNm;

    private String useYn;

    private String useYnNm;

    private String jobCommts;

    private String assessDate;

    private String createUserId;

    private String createDt;

    private String updateUserId;

    private String updateDt;

    private String histUpdateDt;

    private String errorMessage;

    private int rowNum;

    private String plantCd;

    private String plantNm;

    private String writerUserNm;

    private String writerDt;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public int getRowNum() {
        return rowNum;
    }

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public String getJobCd() {
        return jobCd;
    }

    public void setJobCd(String jobCd) {
        this.jobCd = jobCd;
    }

    public int getJobRevno() {
        return jobRevno;
    }

    public void setJobRevno(int jobRevno) {
        this.jobRevno = jobRevno;
    }

    public String getJobNm() {
        return jobNm;
    }

    public void setJobNm(String jobNm) {
        this.jobNm = jobNm;
    }

    public String getSopNo() {
        return sopNo;
    }

    public void setSopNo(String sopNo) {
        this.sopNo = sopNo;
    }

    public String getSopNm() {
        return sopNm;
    }

    public void setSopNm(String sopNm) {
        this.sopNm = sopNm;
    }

    public String getProcessCd() {
        return processCd;
    }

    public void setProcessCd(String processCd) {
        this.processCd = processCd;
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

    public String getDeptNm() {
        return deptNm;
    }

    public void setDeptNm(String deptNm) {
        this.deptNm = deptNm;
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

    public String getJobCommts() {
        return jobCommts;
    }

    public void setJobCommts(String jobCommts) {
        this.jobCommts = jobCommts;
    }

    public String getAssessDate() {
        return assessDate;
    }

    public void setAssessDate(String assessDate) {
        this.assessDate = assessDate;
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

    public String getHistUpdateDt() {
        return histUpdateDt;
    }

    public void setHistUpdateDt(String histUpdateDt) {
        this.histUpdateDt = histUpdateDt;
    }

    public String getPlantCd() {
        return plantCd;
    }

    public void setPlantCd(String plantCd) {
        this.plantCd = plantCd;
    }

    public String getPlantNm() {
        return plantNm;
    }

    public void setPlantNm(String plantNm) {
        this.plantNm = plantNm;
    }

    public String getWriterUserNm() {
        return writerUserNm;
    }

    public void setWriterUserNm(String writerUserNm) {
        this.writerUserNm = writerUserNm;
    }

    public String getWriterDt() {
        return writerDt;
    }

    public void setWriterDt(String writerDt) {
        this.writerDt = writerDt;
    }

}
