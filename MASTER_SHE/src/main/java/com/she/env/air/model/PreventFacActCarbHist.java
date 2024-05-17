/**
 * Copyright (C) 2019, 2019 All Right Reserved, http://www.yullin.com/
 *
 * SHE Business can not be copied and/or distributed without the express
 * permission of Yullin Technologies
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 */
package com.she.env.air.model;

/**
 * PreventionFacility(대기 방지시설)
 *
 */
public class PreventFacActCarbHist {
    private int eairPreventFacActCarbHistNo;
    private int eairPreventFacNo;
    private String chngYmd;
    private int chngCost;
    private String chngUnit;

    private int chngAmount;
    private String unitCd;
    private String unitNm;
    private String remark;
    private String chngDate;
    private String createUserId;
    private String createDt;

    public int getEairPreventFacActCarbHistNo() {
        return eairPreventFacActCarbHistNo;
    }

    public void setEairPreventFacActCarbHistNo(int eairPreventFacActCarbHistNo) {
        this.eairPreventFacActCarbHistNo = eairPreventFacActCarbHistNo;
    }

    public int getEairPreventFacNo() {
        return eairPreventFacNo;
    }

    public void setEairPreventFacNo(int eairPreventFacNo) {
        this.eairPreventFacNo = eairPreventFacNo;
    }

    public String getChngYmd() {
        return chngYmd;
    }

    public void setChngYmd(String chngYmd) {
        this.chngYmd = chngYmd;
    }

    public int getChngCost() {
        return chngCost;
    }

    public void setChngCost(int chngCost) {
        this.chngCost = chngCost;
    }

    public String getChngUnit() {
        return chngUnit;
    }

    public void setChngUnit(String chngUnit) {
        this.chngUnit = chngUnit;
    }

    public int getChngAmount() {
        return chngAmount;
    }

    public void setChngAmount(int chngAmount) {
        this.chngAmount = chngAmount;
    }

    public String getUnitCd() {
        return unitCd;
    }

    public void setUnitCd(String unitCd) {
        this.unitCd = unitCd;
    }

    public String getUnitNm() {
        return unitNm;
    }

    public void setUnitNm(String unitNm) {
        this.unitNm = unitNm;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public String getChngDate() {
        return chngDate;
    }

    public void setChngDate(String chngDate) {
        this.chngDate = chngDate;
    }
}
