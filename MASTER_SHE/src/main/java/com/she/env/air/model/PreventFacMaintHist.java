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
 * PreventionFacilityMaintenanceHistory(대기 방지시설 보수이력)
 *
 */
public class PreventFacMaintHist {
    private int eairPreventFacMaintHistNo;

    private int eairPreventFacNo;

    private String eairPreventFacNm;

    private String eairPreventFacClassCd;

    private String eairPreventFacClassNm;

    private String eairOutletNo;

    private String eairOutletNm;

    private String installPos;

    private String startYmd;

    private String endYmd;

    private String worker;

    private String remark;

    private String createUserId;

    private String createUserNm;

    private String createDt;

    private String updateUserId;

    private String updateUserNm;

    private String updateDt;

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

    public int getEairPreventFacMaintHistNo() {
        return eairPreventFacMaintHistNo;
    }

    public void setEairPreventFacMaintHistNo(int eairPreventFacMaintHistNo) {
        this.eairPreventFacMaintHistNo = eairPreventFacMaintHistNo;
    }

    public int getEairPreventFacNo() {
        return eairPreventFacNo;
    }

    public void setEairPreventFacNo(int eairPreventFacNo) {
        this.eairPreventFacNo = eairPreventFacNo;
    }

    public String getEairPreventFacNm() {
        return eairPreventFacNm;
    }

    public void setEairPreventFacNm(String eairPreventFacNm) {
        this.eairPreventFacNm = eairPreventFacNm;
    }

    public String getEairPreventFacClassCd() {
        return eairPreventFacClassCd;
    }

    public void setEairPreventFacClassCd(String eairPreventFacClassCd) {
        this.eairPreventFacClassCd = eairPreventFacClassCd;
    }

    public String getEairPreventFacClassNm() {
        return eairPreventFacClassNm;
    }

    public void setEairPreventFacClassNm(String eairPreventFacClassNm) {
        this.eairPreventFacClassNm = eairPreventFacClassNm;
    }

    public String getEairOutletNo() {
        return eairOutletNo;
    }

    public void setEairOutletNo(String eairOutletNo) {
        this.eairOutletNo = eairOutletNo;
    }

    public String getEairOutletNm() {
        return eairOutletNm;
    }

    public void setEairOutletNm(String eairOutletNm) {
        this.eairOutletNm = eairOutletNm;
    }

    public String getInstallPos() {
        return installPos;
    }

    public void setInstallPos(String installPos) {
        this.installPos = installPos;
    }

    public String getStartYmd() {
        return startYmd;
    }

    public void setStartYmd(String startYmd) {
        this.startYmd = startYmd;
    }

    public String getEndYmd() {
        return endYmd;
    }

    public void setEndYmd(String endYmd) {
        this.endYmd = endYmd;
    }

    public String getWorker() {
        return worker;
    }

    public void setWorker(String worker) {
        this.worker = worker;
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
