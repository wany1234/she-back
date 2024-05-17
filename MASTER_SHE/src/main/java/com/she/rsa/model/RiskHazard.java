package com.she.rsa.model;

import io.swagger.annotations.ApiModelProperty;

public class RiskHazard {

    private int riskHazardNo;

    private String riskHazardNm;

    private int priskHazardNo;

    private String priskHazardNm;

    private String createUserId;

    private String createDt;

    private String updateUserId;

    private String updateDt;

    private String riskHazardDesc;

    private int riskHazardLevel;

    private int jobStepId;

    private int jobId;

    private int jobStepNo;

    private String jobStepNm;

    private String jobCreateUserId;

    private String jobCreateDt;

    private String menuId;

    private String menuNm;

    private int menuLvl;

    private String upMenuId;

    private String checkNm;

    private String errorMessage;

    private int rowNum;

    private String useYn;

    private String useYnNm;

    private String writerUserNm;

    private String writerDt;

    public String getUseYnNm() {
        return useYnNm;
    }

    public void setUseYnNm(String useYnNm) {
        this.useYnNm = useYnNm;
    }

    public String getUseYn() {
        return useYn;
    }

    public void setUseYn(String useYn) {
        this.useYn = useYn;
    }

    public String getJobStepNm() {
        return jobStepNm;
    }

    public void setJobStepNm(String jobStepNm) {
        this.jobStepNm = jobStepNm;
    }

    public int getJobStepId() {
        return jobStepId;
    }

    public void setJobStepId(int jobStepId) {
        this.jobStepId = jobStepId;
    }

    public int getJobStepNo() {
        return jobStepNo;
    }

    public void setJobStepNo(int jobStepNo) {
        this.jobStepNo = jobStepNo;
    }

    public int getRowNum() {
        return rowNum;
    }

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getCheckNm() {
        return checkNm;
    }

    public void setCheckNm(String checkNm) {
        this.checkNm = checkNm;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getMenuNm() {
        return menuNm;
    }

    public void setMenuNm(String menuNm) {
        this.menuNm = menuNm;
    }

    public int getMenuLvl() {
        return menuLvl;
    }

    public void setMenuLvl(int menuLvl) {
        this.menuLvl = menuLvl;
    }

    public String getUpMenuId() {
        return upMenuId;
    }

    public void setUpMenuId(String upMenuId) {
        this.upMenuId = upMenuId;
    }

    public int getRiskHazardNo() {
        return riskHazardNo;
    }

    public void setRiskHazardNo(int riskHazardNo) {
        this.riskHazardNo = riskHazardNo;
    }

    public String getRiskHazardNm() {
        return riskHazardNm;
    }

    public void setRiskHazardNm(String riskHazardNm) {
        this.riskHazardNm = riskHazardNm;
    }

    public int getPriskHazardNo() {
        return priskHazardNo;
    }

    public void setPriskHazardNo(int priskHazardNo) {
        this.priskHazardNo = priskHazardNo;
    }

    public String getPriskHazardNm() {
        return priskHazardNm;
    }

    public void setPriskHazardNm(String priskHazardNm) {
        this.priskHazardNm = priskHazardNm;
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

    public String getRiskHazardDesc() {
        return riskHazardDesc;
    }

    public void setRiskHazardDesc(String riskHazardDesc) {
        this.riskHazardDesc = riskHazardDesc;
    }

    public int getRiskHazardLevel() {
        return riskHazardLevel;
    }

    public void setRiskHazardLevel(int riskHazardLevel) {
        this.riskHazardLevel = riskHazardLevel;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public String getJobCreateUserId() {
        return jobCreateUserId;
    }

    public void setJobCreateUserId(String jobCreateUserId) {
        this.jobCreateUserId = jobCreateUserId;
    }

    public String getJobCreateDt() {
        return jobCreateDt;
    }

    public void setJobCreateDt(String jobCreateDt) {
        this.jobCreateDt = jobCreateDt;
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

    @Override
    public String toString() {
        return "RiskHazard [riskHazardNo= " + riskHazardNo + ", riskHazardNm= " + riskHazardNm + ", priskHazardNo= " + priskHazardNo + ", priskHazardNm= " + priskHazardNm + ", createUserId= " + createUserId + ", createDt= " + createDt + ", updateUserId= " + updateUserId + ", updateDt= " + updateDt + ", riskHazardDesc= " + riskHazardDesc
                + ", riskHazardLevel= " + riskHazardLevel + ", jobId= " + jobId + ", jobCreateUserId= " + jobCreateUserId + ", jobCreateDt= " + jobCreateDt + ", writerUserNm= " + writerUserNm + ", writerDt= " + writerDt + ", menuId= " + menuId + ", menuNm= " + menuNm + ", menuLvl= " + menuLvl + ", upMenuId= " + upMenuId + ", checkNm= " + checkNm
                + ", errorMessage= " + errorMessage + "]";
    }
}
