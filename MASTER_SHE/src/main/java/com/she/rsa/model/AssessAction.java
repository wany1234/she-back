package com.she.rsa.model;

/**
 * @author JDR
 *
 */
public class AssessAction extends RiskAssessDept {
    private String jobId;

    private String jobCd;

    private String jobNm;

    private String jobRevno;

    private String processNo;

    private String processCd;

    private String processNm;

    private String sopNo;

    private String sopNm;

    private String jobStepNo;

    private String jobStepNm;

    private int riskHazardCnt;

    private int totalCnt;

    private int assessCnt;

    private String taCnt;

    private String lastAssessDate;

    private int jobStepId;

    public int getJobStepId() {
        return jobStepId;
    }

    public void setJobStepId(int jobStepId) {
        this.jobStepId = jobStepId;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getJobCd() {
        return jobCd;
    }

    public void setJobCd(String jobCd) {
        this.jobCd = jobCd;
    }

    public String getJobNm() {
        return jobNm;
    }

    public void setJobNm(String jobNm) {
        this.jobNm = jobNm;
    }

    public String getJobRevno() {
        return jobRevno;
    }

    public void setJobRevno(String jobRevno) {
        this.jobRevno = jobRevno;
    }

    public String getProcessNo() {
        return processNo;
    }

    public void setProcessNo(String processNo) {
        this.processNo = processNo;
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

    public int getTotalCnt() {
        return totalCnt;
    }

    public void setTotalCnt(int totalCnt) {
        this.totalCnt = totalCnt;
    }

    public int getAssessCnt() {
        return assessCnt;
    }

    public void setAssessCnt(int assessCnt) {
        this.assessCnt = assessCnt;
    }

    public String getTaCnt() {
        return taCnt;
    }

    public void setTaCnt(String taCnt) {
        this.taCnt = taCnt;
    }

    public String getLastAssessDate() {
        return lastAssessDate;
    }

    public void setLastAssessDate(String lastAssessDate) {
        this.lastAssessDate = lastAssessDate;
    }

    public String getJobStepNo() {
        return jobStepNo;
    }

    public void setJobStepNo(String jobStepNo) {
        this.jobStepNo = jobStepNo;
    }

    public String getJobStepNm() {
        return jobStepNm;
    }

    public void setJobStepNm(String jobStepNm) {
        this.jobStepNm = jobStepNm;
    }

    public int getRiskHazardCnt() {
        return riskHazardCnt;
    }

    public void setRiskHazardCnt(int riskHazardCnt) {
        this.riskHazardCnt = riskHazardCnt;
    }

}
