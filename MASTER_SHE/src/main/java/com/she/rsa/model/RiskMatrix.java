package com.she.rsa.model;

public class RiskMatrix {

    private int riskNo;

    private int frequencySize;

    private int strongSize;

    private int riskSize;

    private String plantCd;

    private String frequencyDesc;

    private String strongDesc;

    private String riskDesc;

    private int assessTypeNo;

    private String assessNm;

    private String createUserId;

    private String createDt;

    private String updateUserId;

    private String updateDt;

    public String getPlantCd() {
        return plantCd;
    }

    public void setPlantCd(String plantCd) {
        this.plantCd = plantCd;
    }

    public int getRiskNo() {
        return riskNo;
    }

    public void setRiskNo(int riskNo) {
        this.riskNo = riskNo;
    }

    public int getFrequencySize() {
        return frequencySize;
    }

    public void setFrequencySize(int frequencySize) {
        this.frequencySize = frequencySize;
    }

    public int getStrongSize() {
        return strongSize;
    }

    public void setStrongSize(int strongSize) {
        this.strongSize = strongSize;
    }

    public int getRiskSize() {
        return riskSize;
    }

    public void setRiskSize(int riskSize) {
        this.riskSize = riskSize;
    }

    public String getFrequencyDesc() {
        return frequencyDesc;
    }

    public void setFrequencyDesc(String frequencyDesc) {
        this.frequencyDesc = frequencyDesc;
    }

    public String getStrongDesc() {
        return strongDesc;
    }

    public void setStrongDesc(String strongDesc) {
        this.strongDesc = strongDesc;
    }

    public String getRiskDesc() {
        return riskDesc;
    }

    public void setRiskDesc(String riskDesc) {
        this.riskDesc = riskDesc;
    }

    public int getAssessTypeNo() {
        return assessTypeNo;
    }

    public void setAssessTypeNo(int assessTypeNo) {
        this.assessTypeNo = assessTypeNo;
    }

    public String getAssessNm() {
        return assessNm;
    }

    public void setAssessNm(String assessNm) {
        this.assessNm = assessNm;
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
