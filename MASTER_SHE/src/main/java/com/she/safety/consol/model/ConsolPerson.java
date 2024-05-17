package com.she.safety.consol.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "참여부서/업체의 점검자")
public class ConsolPerson {

    @ApiModelProperty(value = "점검자번호")
    public int safChkInspPsnNo;

    @ApiModelProperty(value = "참여부서및협력업체번호")
    public int safChkInspDeptNo;

    @ApiModelProperty(value = "점검자구분코드")
    public String inspClsCd;

    @ApiModelProperty(value = "사용자id")
    public String userId;

    @ApiModelProperty(value = "사용자명")
    public String userNm;

    @ApiModelProperty(value = "사인이미지")
    public String signImg;

    @ApiModelProperty(value = "사인완료여부")
    public String signCfmYn;

    @ApiModelProperty(value = "비고")
    public String remark;

    @ApiModelProperty(value = "사인일시")
    public String signRegDt;

    public int getSafChkInspPsnNo() {
        return safChkInspPsnNo;
    }

    public void setSafChkInspPsnNo(int safChkInspPsnNo) {
        this.safChkInspPsnNo = safChkInspPsnNo;
    }

    public int getSafChkInspDeptNo() {
        return safChkInspDeptNo;
    }

    public void setSafChkInspDeptNo(int safChkInspDeptNo) {
        this.safChkInspDeptNo = safChkInspDeptNo;
    }

    public String getInspClsCd() {
        return inspClsCd;
    }

    public void setInspClsCd(String inspClsCd) {
        this.inspClsCd = inspClsCd;
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

    public String getSignRegDt() {
        return signRegDt;
    }

    public void setSignRegDt(String signRegDt) {
        this.signRegDt = signRegDt;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
