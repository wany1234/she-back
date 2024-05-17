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

import java.util.List;

import com.she.mgt.model.InvestItem;

public class CodeMaster {
    private String code;

    private String codeNm;

    private String codeNmEn;

    private String codeNmJa;

    private String codeNmZhCn;

    private String codeNmZhTw;

    private String codeGroupCd;

    private String codeGroupNm;

    private String codeDomainCd;

    private String codeDomainNm;

    private String codeAbbr;

    private String attr1;

    private String attr2;

    private String attr3;

    private String remarks;

    private int sortOrder;

    private String useYn;

    private String useYnNm;

    private String createUserId;

    private String createUserNm;

    private String createDt;

    private String updateUserId;

    private String updateUserNm;

    private String updateDt;

    private String codeLength;

    private String budgetClsNm;

    private String budgetActNm;

    private boolean printVal;

    private List<InvestItem> investItems;

    private List<InvestItem> deleteInvestItems;

    private String writerUserNm;

    private String writerDt;

    private List<CodeMaster> middleItems;

    private List<CodeMaster> deleteMiddleItems;

    public List<CodeMaster> getMiddleItems() {
        return middleItems;
    }

    public void setMiddleItems(List<CodeMaster> middleItems) {
        this.middleItems = middleItems;
    }

    public List<CodeMaster> getDeleteMiddleItems() {
        return deleteMiddleItems;
    }

    public void setDeleteMiddleItems(List<CodeMaster> deleteMiddleItems) {
        this.deleteMiddleItems = deleteMiddleItems;
    }

    public boolean isPrintVal() {
        return printVal;
    }

    public List<InvestItem> getInvestItems() {
        return investItems;
    }

    public void setInvestItems(List<InvestItem> investItems) {
        this.investItems = investItems;
    }

    public List<InvestItem> getDeleteInvestItems() {
        return deleteInvestItems;
    }

    public void setDeleteInvestItems(List<InvestItem> deleteInvestItems) {
        this.deleteInvestItems = deleteInvestItems;
    }

    public void setPrintVal(boolean printVal) {
        this.printVal = printVal;
    }

    public String getCodeLength() {
        return codeLength;
    }

    public void setCodeLength(String codeLength) {
        this.codeLength = codeLength;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCodeNm() {
        return codeNm;
    }

    public String getCodeNmEn() {
        return codeNmEn;
    }

    public void setCodeNmEn(String codeNmEn) {
        this.codeNmEn = codeNmEn;
    }

    public String getCodeNmJa() {
        return codeNmJa;
    }

    public void setCodeNmJa(String codeNmJa) {
        this.codeNmJa = codeNmJa;
    }

    public String getCodeNmZhCn() {
        return codeNmZhCn;
    }

    public void setCodeNmZhCn(String codeNmZhCn) {
        this.codeNmZhCn = codeNmZhCn;
    }

    public String getCodeNmZhTw() {
        return codeNmZhTw;
    }

    public void setCodeNmZhTw(String codeNmZhTw) {
        this.codeNmZhTw = codeNmZhTw;
    }

    public void setCodeNm(String codeNm) {
        this.codeNm = codeNm;
    }

    public String getCodeGroupCd() {
        return codeGroupCd;
    }

    public void setCodeGroupCd(String codeGroupCd) {
        this.codeGroupCd = codeGroupCd;
    }

    public String getCodeGroupNm() {
        return codeGroupNm;
    }

    public void setCodeGroupNm(String codeGroupNm) {
        this.codeGroupNm = codeGroupNm;
    }

    public String getCodeAbbr() {
        return codeAbbr;
    }

    public void setCodeAbbr(String codeAbbr) {
        this.codeAbbr = codeAbbr;
    }

    public String getAttr1() {
        return attr1;
    }

    public void setAttr1(String attr1) {
        this.attr1 = attr1;
    }

    public String getAttr2() {
        return attr2;
    }

    public void setAttr2(String attr2) {
        this.attr2 = attr2;
    }

    public String getAttr3() {
        return attr3;
    }

    public void setAttr3(String attr3) {
        this.attr3 = attr3;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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

    public String getUseYnNm() {
        return useYnNm;
    }

    public void setUseYnNm(String useYnNm) {
        this.useYnNm = useYnNm;
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

    public String getBudgetActNm() {
        return budgetActNm;
    }

    public void setBudgetActNm(String budgetActNm) {
        this.budgetActNm = budgetActNm;
    }

    public String getBudgetClsNm() {
        return budgetClsNm;
    }

    public void setBudgetClsNm(String budgetClsNm) {
        this.budgetClsNm = budgetClsNm;
    }

}
