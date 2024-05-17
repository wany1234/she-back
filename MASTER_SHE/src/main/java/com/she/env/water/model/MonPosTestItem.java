package com.she.env.water.model;

import java.math.BigDecimal;

public class MonPosTestItem {
    private int ewtrMonPosNo;

    private String ewtrMonPosNm;

    private String ewtrTestItemNm;

    private String ewtrTestItemCd;

    private String envUnitNm;

    private BigDecimal legalLimit;

    private BigDecimal selfLimit;

    private String useYn;

    private int sortOrder;

    private String createUserId;

    private String createUserNm;

    private String createDt;

    public int getEwtrMonPosNo() {
        return ewtrMonPosNo;
    }

    public void setEwtrMonPosNo(int ewtrMonPosNo) {
        this.ewtrMonPosNo = ewtrMonPosNo;
    }

    public String getEwtrMonPosNm() {
        return ewtrMonPosNm;
    }

    public String getEwtrTestItemNm() {
        return ewtrTestItemNm;
    }

    public void setEwtrTestItemNm(String ewtrTestItemNm) {
        this.ewtrTestItemNm = ewtrTestItemNm;
    }

    public void setEwtrMonPosNm(String ewtrMonPosNm) {
        this.ewtrMonPosNm = ewtrMonPosNm;
    }

    public String getEwtrTestItemCd() {
        return ewtrTestItemCd;
    }

    public void setEwtrTestItemCd(String ewtrTestItemCd) {
        this.ewtrTestItemCd = ewtrTestItemCd;
    }

    public String getEnvUnitNm() {
        return envUnitNm;
    }

    public void setEnvUnitNm(String envUnitNm) {
        this.envUnitNm = envUnitNm;
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

    public BigDecimal getLegalLimit() {
        return legalLimit;
    }

    public void setLegalLimit(BigDecimal legalLimit) {
        this.legalLimit = legalLimit;
    }

    public BigDecimal getSelfLimit() {
        return selfLimit;
    }

    public void setSelfLimit(BigDecimal selfLimit) {
        this.selfLimit = selfLimit;
    }

    public String getUseYn() {
        return useYn;
    }

    public void setUseYn(String useYn) {
        this.useYn = useYn;
    }

    public int getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(int sortOrder) {
        this.sortOrder = sortOrder;
    }

}
