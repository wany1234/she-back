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

package com.she.health.model;

public class CheckupPlanOrg {
    public int heaCheckupPlanNo;

    public String heaCheckupPlanNm;

    public int heaCheckupOrgNo;

    public String heaCheckupOrgNm;

    public String startYmd;

    public String endYmd;

    public String deadlineDt;

    public int weekdayMax;

    public int weekendMax;

    public int integratedMax;

    public String createUserId;

    public String createUserNm;

    public String createDt;

    public String updateUserId;

    public String updateUserNm;

    public String updateDt;

    public String heaCheckupPlanOrgPeriod;

    public String reserveStatusYmd;

    public String ymd;

    public String reserveUserCount;

    public int reserveCnt;

    public String checked;

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }

    public int getReserveCnt() {
        return reserveCnt;
    }

    public void setReserveCnt(int reserveCnt) {
        this.reserveCnt = reserveCnt;
    }

    public String getYmd() {
        return ymd;
    }

    public void setYmd(String ymd) {
        this.ymd = ymd;
    }


    public String getReserveUserStatus() {
        return this.reserveUserCount + "/" + this.integratedMax;
    }


    public int getIntegratedMax() {
        return integratedMax;
    }

    public void setIntegratedMax(int integratedMax) {
        this.integratedMax = integratedMax;
    }

    public String getReserveStatusYmd() {
        return reserveStatusYmd;
    }

    public void setReserveStatusYmd(String reserveStatusYmd) {
        this.reserveStatusYmd = reserveStatusYmd;
    }

    public String getReserveUserCount() {
        return reserveUserCount;
    }

    public void setReserveUserCount(String reserveUserCount) {
        this.reserveUserCount = reserveUserCount;
    }

    public String getHeaCheckupPlanOrgPeriod() {
        return heaCheckupPlanOrgPeriod;
    }

    public void setHeaCheckupPlanOrgPeriod(String heaCheckupPlanOrgPeriod) {
        this.heaCheckupPlanOrgPeriod = heaCheckupPlanOrgPeriod;
    }

    public int getHeaCheckupPlanNo() {
        return heaCheckupPlanNo;
    }

    public void setHeaCheckupPlanNo(int heaCheckupPlanNo) {
        this.heaCheckupPlanNo = heaCheckupPlanNo;
    }

    public String getHeaCheckupPlanNm() {
        return heaCheckupPlanNm;
    }

    public void setHeaCheckupPlanNm(String heaCheckupPlanNm) {
        this.heaCheckupPlanNm = heaCheckupPlanNm;
    }

    public int getHeaCheckupOrgNo() {
        return heaCheckupOrgNo;
    }

    public void setHeaCheckupOrgNo(int heaCheckupOrgNo) {
        this.heaCheckupOrgNo = heaCheckupOrgNo;
    }

    public String getHeaCheckupOrgNm() {
        return heaCheckupOrgNm;
    }

    public void setHeaCheckupOrgNm(String heaCheckupOrgNm) {
        this.heaCheckupOrgNm = heaCheckupOrgNm;
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

    public String getDeadlineDt() {
        return deadlineDt;
    }

    public void setDeadlineDt(String deadlineDt) {
        this.deadlineDt = deadlineDt;
    }

    public int getWeekdayMax() {
        return weekdayMax;
    }

    public void setWeekdayMax(int weekdayMax) {
        this.weekdayMax = weekdayMax;
    }

    public int getWeekendMax() {
        return weekendMax;
    }

    public void setWeekendMax(int weekendMax) {
        this.weekendMax = weekendMax;
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

}
