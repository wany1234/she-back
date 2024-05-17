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
package com.she.env.waste.model;

/**
 * WasteDisposalMethod(폐기물처리방법)
 *
 */
public class WasteDispoMtd {
    private String ewstDispoMtdCd;

    private String ewstDispoMtdNm;

    private String ewstDispoClassCd;

    private String ewstDispoClassNm;

    private int sortOrder;

    private String useYn;

    private String useYnNm;

    private String createUserId;

    private String createUserNm;

    private String createDt;

    private String updateUserId;

    private String updateUserNm;

    private String updateDt;

    private String writerUserNm;

    private String writerDt;

    public String getUseYnNm() {
        return useYnNm;
    }

    public void setUseYnNm(String useYnNm) {
        this.useYnNm = useYnNm;
    }

    public String getEwstDispoMtdCd() {
        return ewstDispoMtdCd;
    }

    public void setEwstDispoMtdCd(String ewstDispoMtdCd) {
        this.ewstDispoMtdCd = ewstDispoMtdCd;
    }

    public String getEwstDispoMtdNm() {
        return ewstDispoMtdNm;
    }

    public void setEwstDispoMtdNm(String ewstDispoMtdNm) {
        this.ewstDispoMtdNm = ewstDispoMtdNm;
    }

    public String getEwstDispoClassCd() {
        return ewstDispoClassCd;
    }

    public void setEwstDispoClassCd(String ewstDispoClassCd) {
        this.ewstDispoClassCd = ewstDispoClassCd;
    }

    public String getEwstDispoClassNm() {
        return ewstDispoClassNm;
    }

    public void setEwstDispoClassNm(String ewstDispoClassNm) {
        this.ewstDispoClassNm = ewstDispoClassNm;
    }

    public int getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(int sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getUseYn() {
        return useYn;
    }

    public void setUseYn(String useYn) {
        this.useYn = useYn;
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
