package com.she.mgt.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "산업안전보건위원회_회의록_참석자")
public class MgtCommitteePsn {

    @ApiModelProperty(value = "참석자번호")
    public int mgtCommitteePsnNo;

    @ApiModelProperty(value = "회의록번호")
    public int committeeConfNo;

    @ApiModelProperty(value = "회의록명")
    public String confNm;

    @ApiModelProperty(value = "회의일시")
    public String confDate;

    @ApiModelProperty(value = "회의장소")
    public String confPlace;

    @ApiModelProperty(value = "C참석자구분코드")
    public String psnClsCd;

    @ApiModelProperty(value = "사용자ID")
    public String userId;

    @ApiModelProperty(value = "성명")
    public String userNm;

    @ApiModelProperty(value = "부서코드")
    public String deptCd;

    @ApiModelProperty(value = "업체코드")
    public String vendorCd;

    @ApiModelProperty(value = "소속")
    public String deptNm;

    @ApiModelProperty(value = "직책")
    public String dutyNm;

    @ApiModelProperty(value = "비고")
    public String remark;

    @ApiModelProperty(value = "사인이미지")
    public String signImg;

    @ApiModelProperty(value = "사인완료여부")
    public String signCfmYn;

    @ApiModelProperty(value = "사인완료여부명칭")
    public String signCfmYnNm;

    @ApiModelProperty(value = "사인등록일시")
    public String signRegDt;

    @ApiModelProperty(value = "사업장코드")
    public String plantCd;

    @ApiModelProperty(value = "사업장명칭")
    public String plantNm;

    @ApiModelProperty(value = "구분코드")
    public String cmiClsCd;

    @ApiModelProperty(value = "구분명칭")
    public String cmiClsNm;

    public MgtCommitteePsn() {
    }

    public int getMgtCommitteePsnNo() {
        return mgtCommitteePsnNo;
    }

    public void setMgtCommitteePsnNo(int mgtCommitteePsnNo) {
        this.mgtCommitteePsnNo = mgtCommitteePsnNo;
    }

    public int getCommitteeConfNo() {
        return committeeConfNo;
    }

    public void setCommitteeConfNo(int committeeConfNo) {
        this.committeeConfNo = committeeConfNo;
    }

    public String getConfNm() {
        return confNm;
    }

    public void setConfNm(String confNm) {
        this.confNm = confNm;
    }

    public String getConfDate() {
        return confDate;
    }

    public void setConfDate(String confDate) {
        this.confDate = confDate;
    }

    public String getConfPlace() {
        return confPlace;
    }

    public void setConfPlace(String confPlace) {
        this.confPlace = confPlace;
    }

    public String getPsnClsCd() {
        return psnClsCd;
    }

    public void setPsnClsCd(String psnClsCd) {
        this.psnClsCd = psnClsCd;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserNm() {
        return userNm;
    }

    public void setUserNm(String userNm) {
        this.userNm = userNm;
    }

    public String getDeptCd() {
        return deptCd;
    }

    public void setDeptCd(String deptCd) {
        this.deptCd = deptCd;
    }

    public String getVendorCd() {
        return vendorCd;
    }

    public void setVendorCd(String vendorCd) {
        this.vendorCd = vendorCd;
    }

    public String getDeptNm() {
        return deptNm;
    }

    public void setDeptNm(String deptNm) {
        this.deptNm = deptNm;
    }

    public String getDutyNm() {
        return dutyNm;
    }

    public void setDutyNm(String dutyNm) {
        this.dutyNm = dutyNm;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSignImg() {
        return signImg;
    }

    public void setSignImg(String signImg) {
        this.signImg = signImg;
    }

    public String getSignCfmYn() {
        return signCfmYn;
    }

    public void setSignCfmYn(String signCfmYn) {
        this.signCfmYn = signCfmYn;
    }

    public String getSignCfmYnNm() {
        return signCfmYnNm;
    }

    public void setSignCfmYnNm(String signCfmYnNm) {
        this.signCfmYnNm = signCfmYnNm;
    }

    public String getSignRegDt() {
        return signRegDt;
    }

    public void setSignRegDt(String signRegDt) {
        this.signRegDt = signRegDt;
    }

    public String getPlantCd() {
        return plantCd;
    }

    public void setPlantCd(String plantCd) {
        this.plantCd = plantCd;
    }

    public String getPlantNm() {
        return plantNm;
    }

    public void setPlantNm(String plantNm) {
        this.plantNm = plantNm;
    }

    public String getCmiClsCd() {
        return cmiClsCd;
    }

    public void setCmiClsCd(String cmiClsCd) {
        this.cmiClsCd = cmiClsCd;
    }

    public String getCmiClsNm() {
        return cmiClsNm;
    }

    public void setCmiClsNm(String cmiClsNm) {
        this.cmiClsNm = cmiClsNm;
    }
}
