package com.she.common.model;

/**
 * 코드 도메인 모델 클래스
 */
public class CodeDomain {
    private String codeDomainCd;

    private String codeDomainNm;

    private String remarks;

    private String useYn;

    private String createUserId;

    private String createDt;

    private String updateUserId;

    private String updateDt;

    public CodeDomain() {
    }

    public String getCodeDomainCd() {
        return codeDomainCd;
    }

    public void setCodeDomainCd(String codeDomainCd) {
        this.codeDomainCd = codeDomainCd;
    }

    public String getCodeDomainNm() {
        return codeDomainNm;
    }

    public void setCodeDomainNm(String codeDomainNm) {
        this.codeDomainNm = codeDomainNm;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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
}
