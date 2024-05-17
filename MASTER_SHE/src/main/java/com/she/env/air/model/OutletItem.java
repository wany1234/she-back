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

public class OutletItem {
    private int eairOutletNo;

    private String eairTestItemCd;

    private int dischAmtCalcFactor;

    private int legalLimit;

    private int selfLimit;

    private String eairInstCd;

    private String eairTestMtdCd;

    private int sortOrder;

    private String useYn;

    private String createUserId;

    private String createDt;

    public int getEairOutletNo() {
        return eairOutletNo;
    }

    public void setEairOutletNo(int eairOutletNo) {
        this.eairOutletNo = eairOutletNo;
    }

    public String getEairTestItemCd() {
        return eairTestItemCd;
    }

    public void setEairTestItemCd(String eairTestItemCd) {
        this.eairTestItemCd = eairTestItemCd;
    }

    public int getDischAmtCalcFactor() {
        return dischAmtCalcFactor;
    }

    public void setDischAmtCalcFactor(int dischAmtCalcFactor) {
        this.dischAmtCalcFactor = dischAmtCalcFactor;
    }

    public int getLegalLimit() {
        return legalLimit;
    }

    public void setLegalLimit(int legalLimit) {
        this.legalLimit = legalLimit;
    }

    public int getSelfLimit() {
        return selfLimit;
    }

    public void setSelfLimit(int selfLimit) {
        this.selfLimit = selfLimit;
    }

    public String getEairInstCd() {
        return eairInstCd;
    }

    public void setEairInstCd(String eairInstCd) {
        this.eairInstCd = eairInstCd;
    }

    public String getEairTestMtdCd() {
        return eairTestMtdCd;
    }

    public void setEairTestMtdCd(String eairTestMtdCd) {
        this.eairTestMtdCd = eairTestMtdCd;
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

    public String getCreateDt() {
        return createDt;
    }

    public void setCreateDt(String createDt) {
        this.createDt = createDt;
    }

}
