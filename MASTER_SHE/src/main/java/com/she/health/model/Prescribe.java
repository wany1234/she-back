package com.she.health.model;

public class Prescribe {

    private int heaInfirmaryUsageNo;

    private int heaDrugNo;

    private String heaDrugNm;

    private int amount;

    private String createUserId;

    private String createDt;

    private String visitYmd;

    private String unit;

    private String deptNm;

    private String userNm;

    private String userId;

    private int amountCurr;

    public int getAmountCurr() {
        return amountCurr;
    }

    public void setAmountCurr(int amountCurr) {
        this.amountCurr = amountCurr;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getHeaInfirmaryUsageNo() {
        return heaInfirmaryUsageNo;
    }

    public void setHeaInfirmaryUsageNo(int heaInfirmaryUsageNo) {
        this.heaInfirmaryUsageNo = heaInfirmaryUsageNo;
    }

    public int getHeaDrugNo() {
        return heaDrugNo;
    }

    public void setHeaDrugNo(int heaDrugNo) {
        this.heaDrugNo = heaDrugNo;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
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

    public String getVisitYmd() {
        return visitYmd;
    }

    public void setVisitYmd(String visitYmd) {
        this.visitYmd = visitYmd;
    }

    public String getHeaDrugNm() {
        return heaDrugNm;
    }

    public void setHeaDrugNm(String heaDrugNm) {
        this.heaDrugNm = heaDrugNm;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getDeptNm() {
        return deptNm;
    }

    public void setDeptNm(String deptNm) {
        this.deptNm = deptNm;
    }

    public String getUserNm() {
        return userNm;
    }

    public void setUserNm(String userNm) {
        this.userNm = userNm;
    }

}
