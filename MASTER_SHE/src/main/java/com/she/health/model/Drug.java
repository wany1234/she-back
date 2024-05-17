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

public class Drug {

    private int heaDrugNo;

    private String heaDrugNm;

    private String unit;

    private int amountLimit;

    private int amountCurr;

    private int sortOrder;

    private String useYn;

    private String useYnNm;

    private String createUserId;

    private String creatDt;

    private String updateUserId;

    private String updateDt;

    private int amount;

    private String writerUserNm;

    private String writerDt;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getUseYnNm() {
        return useYnNm;
    }

    public void setUseYnNm(String useYnNm) {
        this.useYnNm = useYnNm;
    }

    public int getHeaDrugNo() {
        return heaDrugNo;
    }

    public void setHeaDrugNo(int heaDrugNo) {
        this.heaDrugNo = heaDrugNo;
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

    public int getAmountLimit() {
        return amountLimit;
    }

    public void setAmountLimit(int amountLimit) {
        this.amountLimit = amountLimit;
    }

    public int getAmountCurr() {
        return amountCurr;
    }

    public void setAmountCurr(int amountCurr) {
        this.amountCurr = amountCurr;
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

    public String getCreatDt() {
        return creatDt;
    }

    public void setCreatDt(String creatDt) {
        this.creatDt = creatDt;
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
