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
 * IngredientCheckResult(대기운영일지-원료사용량)
 *
 */
public class IngrCheckResult {
    private String measureYmd;

    private String eairIngrCd;

    private String eairIngrNm;

    private String envUnitCd;

    private String eairFuleNm;

    private String envUnitNm;

    private String deptCd;

    private String deptNm;

    private Float numResult;

    private String charResult;

    private String createUserId;

    private String createUserNm;

    private String createDt;

    private String updateUserId;

    private String updateUserNm;

    private String updateDt;

    public String getEnvUnitCd() {
        return envUnitCd;
    }

    public String getEairFuleNm() {
        return eairFuleNm;
    }

    public void setEairFuleNm(String eairFuleNm) {
        this.eairFuleNm = eairFuleNm;
    }

    public void setEnvUnitCd(String envUnitCd) {
        this.envUnitCd = envUnitCd;
    }

    public String getEnvUnitNm() {
        return envUnitNm;
    }

    public void setEnvUnitNm(String envUnitNm) {
        this.envUnitNm = envUnitNm;
    }

    public String getMeasureYmd() {
        return measureYmd;
    }

    public void setMeasureYmd(String measureYmd) {
        this.measureYmd = measureYmd;
    }

    public String getEairIngrCd() {
        return eairIngrCd;
    }

    public void setEairIngrCd(String eairIngrCd) {
        this.eairIngrCd = eairIngrCd;
    }

    public String getEairIngrNm() {
        return eairIngrNm;
    }

    public void setEairIngrNm(String eairIngrNm) {
        this.eairIngrNm = eairIngrNm;
    }

    public Float getNumResult() {
        return numResult;
    }

    public void setNumResult(Float numResult) {
        this.numResult = numResult;
    }

    public String getCharResult() {
        return charResult;
    }

    public void setCharResult(String charResult) {
        this.charResult = charResult;
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

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    public String getUpdateUserNm() {
        return updateUserNm;
    }

    public void setUpdateUserNm(String updateUserNm) {
        this.updateUserNm = updateUserNm;
    }

    public String getUpdateDt() {
        return updateDt;
    }

    public void setUpdateDt(String updateDt) {
        this.updateDt = updateDt;
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

}
