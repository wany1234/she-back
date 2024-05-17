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
package com.she.manage.model;

public class Notice {
    private int noticeNo;

    private String comNoticeClassCd;

    private String comNoticeClassNm;

    private String noticeTitle;

    private String noticeContents;

    private String popupYn;

    private String popupYnNm;

    private String popupStartYmd;

    private String popupEndYmd;

    private String createUserId;

    private String createUserNm;

    private String createDt;

    private String updateUserId;

    private String updateUserNm;

    private String updateDt;

    private String popupPeriod;

    public String getPopupPeriod() {
        return popupPeriod;
    }

    public void setPopupPeriod(String popupPeriod) {
        this.popupPeriod = popupPeriod;
    }

    public int getNoticeNo() {
        return noticeNo;
    }

    public void setNoticeNo(int noticeNo) {
        this.noticeNo = noticeNo;
    }

    public String getComNoticeClassCd() {
        return comNoticeClassCd;
    }

    public void setComNoticeClassCd(String comNoticeClassCd) {
        this.comNoticeClassCd = comNoticeClassCd;
    }

    public String getComNoticeClassNm() {
        return comNoticeClassNm;
    }

    public void setComNoticeClassNm(String comNoticeClassNm) {
        this.comNoticeClassNm = comNoticeClassNm;
    }

    public String getNoticeTitle() {
        return noticeTitle;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }

    public String getNoticeContents() {
        return noticeContents;
    }

    public void setNoticeContents(String noticeContents) {
        this.noticeContents = noticeContents;
    }

    public String getPopupYn() {
        return popupYn;
    }

    public void setPopupYn(String popupYn) {
        this.popupYn = popupYn;
    }

    public String getPopupYnNm() {
        return popupYnNm;
    }

    public void setPopupYnNm(String popupYnNm) {
        this.popupYnNm = popupYnNm;
    }

    public String getPopupStartYmd() {
        return popupStartYmd;
    }

    public void setPopupStartYmd(String popupStartYmd) {
        this.popupStartYmd = popupStartYmd;
    }

    public String getPopupEndYmd() {
        return popupEndYmd;
    }

    public void setPopupEndYmd(String popupEndYmd) {
        this.popupEndYmd = popupEndYmd;
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
