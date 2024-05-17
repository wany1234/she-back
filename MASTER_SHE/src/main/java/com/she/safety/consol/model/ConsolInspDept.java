package com.she.safety.consol.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "합동점검계획의 참여부서/업체")
public class ConsolInspDept {

    @ApiModelProperty(value = "참여부서및협력업체번호")
    public int safChkInspDeptNo;

    @ApiModelProperty(value = "합동점검결과번호")
    public int safCongChkRsltNo;

    @ApiModelProperty(value = "점검예정일")
    public String congChkSchYmd;

    @ApiModelProperty(value = "점검자구분코드")
    public String inspClsCd;

    @ApiModelProperty(value = "합동점검계획명")
    public String chkTitle;

    @ApiModelProperty(value = "참여부서코드")
    public String deptCd;

    @ApiModelProperty(value = "합동점검결과번호")
    public String deptNm;

    @ApiModelProperty(value = "참여업체코드")
    public String vendorCd;

    @ApiModelProperty(value = "참여업체명")
    public String vendorNm;

    @ApiModelProperty(value = "참석자1 이름")
    public String userIdF;

    @ApiModelProperty(value = "참석자1 id")
    public String userNmF;

    @ApiModelProperty(value = "참석자2 id")
    public String userIdS;

    @ApiModelProperty(value = "참석자2 이름")
    public String userNmS;

    @ApiModelProperty(value = "참석자2 서명여부")
    public String signCfmYnS;

    @ApiModelProperty(value = "참석자1 서명여부")
    public String signCfmYnF;

    @ApiModelProperty(value = "참석자1 서명이미지")
    public String signImgF;

    @ApiModelProperty(value = "참석자2 서명이미지")
    public String signImgS;

    @ApiModelProperty(value = "비고")
    public String remark;

    @ApiModelProperty(value = "업체참석여부")
    public String vendorActYn;

    @ApiModelProperty(value = "업체참석처리여부")
    public String vendorActCfmYn;

    @ApiModelProperty(value = "불참사유")
    public String abseRsn;

    @ApiModelProperty(value = "업체참석처리일시")
    public String vendorActCfmDt;

    @ApiModelProperty(value = "참석자's")
    public String userNms;

    @ApiModelProperty(value = "참석자's id")
    public String userIds;

    @ApiModelProperty(value = "점검자 리스트")
    public List<ConsolPerson> personList;

    public int getSafChkInspDeptNo() {
        return safChkInspDeptNo;
    }

    public void setSafChkInspDeptNo(int safChkInspDeptNo) {
        this.safChkInspDeptNo = safChkInspDeptNo;
    }

    public int getSafCongChkRsltNo() {
        return safCongChkRsltNo;
    }

    public void setSafCongChkRsltNo(int safCongChkRsltNo) {
        this.safCongChkRsltNo = safCongChkRsltNo;
    }

    public String getCongChkSchYmd() {
        return congChkSchYmd;
    }

    public void setCongChkSchYmd(String congChkSchYmd) {
        this.congChkSchYmd = congChkSchYmd;
    }

    public String getInspClsCd() {
        return inspClsCd;
    }

    public void setInspClsCd(String inspClsCd) {
        this.inspClsCd = inspClsCd;
    }

    public String getChkTitle() {
        return chkTitle;
    }

    public void setChkTitle(String chkTitle) {
        this.chkTitle = chkTitle;
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

    public String getVendorCd() {
        return vendorCd;
    }

    public void setVendorCd(String vendorCd) {
        this.vendorCd = vendorCd;
    }

    public String getVendorNm() {
        return vendorNm;
    }

    public void setVendorNm(String vendorNm) {
        this.vendorNm = vendorNm;
    }

    public String getUserIdF() {
        return userIdF;
    }

    public void setUserIdF(String userIdF) {
        this.userIdF = userIdF;
    }

    public String getUserNmF() {
        return userNmF;
    }

    public void setUserNmF(String userNmF) {
        this.userNmF = userNmF;
    }

    public String getUserIdS() {
        return userIdS;
    }

    public void setUserIdS(String userIdS) {
        this.userIdS = userIdS;
    }

    public String getUserNmS() {
        return userNmS;
    }

    public void setUserNmS(String userNmS) {
        this.userNmS = userNmS;
    }

    public String getSignCfmYnS() {
        return signCfmYnS;
    }

    public void setSignCfmYnS(String signCfmYnS) {
        this.signCfmYnS = signCfmYnS;
    }

    public String getSignCfmYnF() {
        return signCfmYnF;
    }

    public void setSignCfmYnF(String signCfmYnF) {
        this.signCfmYnF = signCfmYnF;
    }

    public String getSignImgF() {
        return signImgF;
    }

    public void setSignImgF(String signImgF) {
        this.signImgF = signImgF;
    }

    public String getSignImgS() {
        return signImgS;
    }

    public void setSignImgS(String signImgS) {
        this.signImgS = signImgS;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getVendorActYn() {
        return vendorActYn;
    }

    public void setVendorActYn(String vendorActYn) {
        this.vendorActYn = vendorActYn;
    }

    public String getVendorActCfmYn() {
        return vendorActCfmYn;
    }

    public void setVendorActCfmYn(String vendorActCfmYn) {
        this.vendorActCfmYn = vendorActCfmYn;
    }

    public String getAbseRsn() {
        return abseRsn;
    }

    public void setAbseRsn(String abseRsn) {
        this.abseRsn = abseRsn;
    }

    public String getVendorActCfmDt() {
        return vendorActCfmDt;
    }

    public void setVendorActCfmDt(String vendorActCfmDt) {
        this.vendorActCfmDt = vendorActCfmDt;
    }

    public String getUserNms() {
        return userNms;
    }

    public void setUserNms(String userNms) {
        this.userNms = userNms;
    }

    public String getUserIds() {
        return userIds;
    }

    public void setUserIds(String userIds) {
        this.userIds = userIds;
    }

    public List<ConsolPerson> getPersonList() {
        return personList;
    }

    public void setPersonList(List<ConsolPerson> personList) {
        this.personList = personList;
    }
}
