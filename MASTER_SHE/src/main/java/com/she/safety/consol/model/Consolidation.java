package com.she.safety.consol.model;

import java.util.List;

import com.she.safety.model.PatrolItemResult;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "합동점검Model")
public class Consolidation {

    @ApiModelProperty(value = "합동점검결과번호")
    public int safCongChkRsltNo;

    @ApiModelProperty(value = "연간점검계획번호")
    public int safCongChkPlanNo;

    @ApiModelProperty(value = "사업장 코드")
    public String plantCd;

    @ApiModelProperty(value = "사업장 명")
    public String plantNm;

    @ApiModelProperty(value = "점검자번호")
    public int safChkInspPsnNo;

    @ApiModelProperty(value = "점검자부서번호")
    public int safChkInspDeptNo;

    @ApiModelProperty(value = "주관부서코드")
    public String deptCd;

    @ApiModelProperty(value = "결과요약")
    public String checkResult;

    @ApiModelProperty(value = "주관부서명")
    public String deptNm;

    @ApiModelProperty(value = "점검일")
    public String congChkYmd;

    @ApiModelProperty(value = "점검예정일")
    public String congChkSchYmd;

    @ApiModelProperty(value = "연간합동점검명")
    public String chkYearTitle;

    @ApiModelProperty(value = "년도")
    public String safCheckPerd;

    @ApiModelProperty(value = "점검계획시작일")
    public String planSymd;

    @ApiModelProperty(value = "점검계획종료일")
    public String planEymd;

    @ApiModelProperty(value = "점검명")
    public String chkTitle;

    @ApiModelProperty(value = "점검진행상태")
    public String checkStepCd;

    @ApiModelProperty(value = "점검진행상태명")
    public String checkStepNm;

    @ApiModelProperty(value = "대상업체/부서 명")
    public String tgtNm;

    @ApiModelProperty(value = "합동종류")
    public int safCheckKindNo;

    @ApiModelProperty(value = "합동종류명")
    public String safCheckKindNm;

    @ApiModelProperty(value = "점검결과결재진행상태")
    public String apprStepCd;

    @ApiModelProperty(value = "결재진행상태번호(계획)")
    public int pApprRqstNo;

    @ApiModelProperty(value = "결재진행상태번호(결과)")
    public int rApprRqstNo;

    @ApiModelProperty(value = "결재진행상태")
    public String bizApprStepCd;

    @ApiModelProperty(value = "결재진행상태명")
    public String bizApprStepNm;

    @ApiModelProperty(value = "등록자")
    public String createUserId;

    @ApiModelProperty(value = "등록자명")
    public String createUserNm;

    @ApiModelProperty(value = "등록일")
    public String createDt;

    @ApiModelProperty(value = "수정자")
    public String updateUserId;

    @ApiModelProperty(value = "수정자명")
    public String updateUserNm;

    @ApiModelProperty(value = "수정일")
    public String updateDt;

    @ApiModelProperty(value = "참석자명")
    public String userNm;

    @ApiModelProperty(value = "참석자Id")
    public String userId;

    @ApiModelProperty(value = "서명이미지")
    public String signImg;

    @ApiModelProperty(value = "서명여부명")
    public String signCfmYnNm;

    @ApiModelProperty(value = "서명여부")
    public String signCfmYn;

    @ApiModelProperty(value = "stuff 부서명 alias")
    public String depts;

    @ApiModelProperty(value = "stuff 업체명 alias")
    public String vendors;

    @ApiModelProperty(value = "연간점검계획 진행상태")
    public String stepNm;

    @ApiModelProperty(value = "주관부서와 참여부서 헷갈리지않게 ")
    public String mstDeptNm;

    @ApiModelProperty(value = "연간점검 계획목록 list")
    public List<ConsolInspDept> vendorDeptList;

    @ApiModelProperty(value = "참여업체 리스트")
    public List<ConsolInspDept> vendorList;

    @ApiModelProperty(value = "참여부서 리스트")
    public List<ConsolInspDept> deptList;

    @ApiModelProperty(value = "합동점검결과의 부서와업체")
    public List<ConsolInspDept> consolInspectors;

    @ApiModelProperty(value = "합동 검사항목")
    public List<PatrolItemResult> consolItems;

    public int getSafCongChkRsltNo() {
        return safCongChkRsltNo;
    }

    public void setSafCongChkRsltNo(int safCongChkRsltNo) {
        this.safCongChkRsltNo = safCongChkRsltNo;
    }

    public int getSafCongChkPlanNo() {
        return safCongChkPlanNo;
    }

    public void setSafCongChkPlanNo(int safCongChkPlanNo) {
        this.safCongChkPlanNo = safCongChkPlanNo;
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

    public String getDeptCd() {
        return deptCd;
    }

    public void setDeptCd(String deptCd) {
        this.deptCd = deptCd;
    }

    public String getCheckResult() {
        return checkResult;
    }

    public void setCheckResult(String checkResult) {
        this.checkResult = checkResult;
    }

    public String getDeptNm() {
        return deptNm;
    }

    public void setDeptNm(String deptNm) {
        this.deptNm = deptNm;
    }

    public String getCongChkYmd() {
        return congChkYmd;
    }

    public void setCongChkYmd(String congChkYmd) {
        this.congChkYmd = congChkYmd;
    }

    public String getCongChkSchYmd() {
        return congChkSchYmd;
    }

    public void setCongChkSchYmd(String congChkSchYmd) {
        this.congChkSchYmd = congChkSchYmd;
    }

    public String getChkYearTitle() {
        return chkYearTitle;
    }

    public void setChkYearTitle(String chkYearTitle) {
        this.chkYearTitle = chkYearTitle;
    }

    public String getSafCheckPerd() {
        return safCheckPerd;
    }

    public void setSafCheckPerd(String safCheckPerd) {
        this.safCheckPerd = safCheckPerd;
    }

    public String getPlanSymd() {
        return planSymd;
    }

    public void setPlanSymd(String planSymd) {
        this.planSymd = planSymd;
    }

    public String getPlanEymd() {
        return planEymd;
    }

    public void setPlanEymd(String planEymd) {
        this.planEymd = planEymd;
    }

    public String getChkTitle() {
        return chkTitle;
    }

    public void setChkTitle(String chkTitle) {
        this.chkTitle = chkTitle;
    }

    public String getCheckStepCd() {
        return checkStepCd;
    }

    public void setCheckStepCd(String checkStepCd) {
        this.checkStepCd = checkStepCd;
    }

    public String getCheckStepNm() {
        return checkStepNm;
    }

    public void setCheckStepNm(String checkStepNm) {
        this.checkStepNm = checkStepNm;
    }

    public String getTgtNm() {
        return tgtNm;
    }

    public void setTgtNm(String tgtNm) {
        this.tgtNm = tgtNm;
    }

    public int getSafCheckKindNo() {
        return safCheckKindNo;
    }

    public void setSafCheckKindNo(int safCheckKindNo) {
        this.safCheckKindNo = safCheckKindNo;
    }

    public String getSafCheckKindNm() {
        return safCheckKindNm;
    }

    public void setSafCheckKindNm(String safCheckKindNm) {
        this.safCheckKindNm = safCheckKindNm;
    }

    public String getApprStepCd() {
        return apprStepCd;
    }

    public void setApprStepCd(String apprStepCd) {
        this.apprStepCd = apprStepCd;
    }

    public int getpApprRqstNo() {
        return pApprRqstNo;
    }

    public void setpApprRqstNo(int pApprRqstNo) {
        this.pApprRqstNo = pApprRqstNo;
    }

    public int getrApprRqstNo() {
        return rApprRqstNo;
    }

    public void setrApprRqstNo(int rApprRqstNo) {
        this.rApprRqstNo = rApprRqstNo;
    }

    public String getBizApprStepCd() {
        return bizApprStepCd;
    }

    public void setBizApprStepCd(String bizApprStepCd) {
        this.bizApprStepCd = bizApprStepCd;
    }

    public String getBizApprStepNm() {
        return bizApprStepNm;
    }

    public void setBizApprStepNm(String bizApprStepNm) {
        this.bizApprStepNm = bizApprStepNm;
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

    public String getUserNm() {
        return userNm;
    }

    public void setUserNm(String userNm) {
        this.userNm = userNm;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSignImg() {
        return signImg;
    }

    public void setSignImg(String signImg) {
        this.signImg = signImg;
    }

    public String getSignCfmYnNm() {
        return signCfmYnNm;
    }

    public void setSignCfmYnNm(String signCfmYnNm) {
        this.signCfmYnNm = signCfmYnNm;
    }

    public String getSignCfmYn() {
        return signCfmYn;
    }

    public void setSignCfmYn(String signCfmYn) {
        this.signCfmYn = signCfmYn;
    }

    public String getDepts() {
        return depts;
    }

    public void setDepts(String depts) {
        this.depts = depts;
    }

    public String getVendors() {
        return vendors;
    }

    public void setVendors(String vendors) {
        this.vendors = vendors;
    }

    public String getStepNm() {
        return stepNm;
    }

    public void setStepNm(String stepNm) {
        this.stepNm = stepNm;
    }

    public String getMstDeptNm() {
        return mstDeptNm;
    }

    public void setMstDeptNm(String mstDeptNm) {
        this.mstDeptNm = mstDeptNm;
    }

    public List<ConsolInspDept> getVendorDeptList() {
        return vendorDeptList;
    }

    public void setVendorDeptList(List<ConsolInspDept> vendorDeptList) {
        this.vendorDeptList = vendorDeptList;
    }

    public List<ConsolInspDept> getVendorList() {
        return vendorList;
    }

    public void setVendorList(List<ConsolInspDept> vendorList) {
        this.vendorList = vendorList;
    }

    public List<ConsolInspDept> getDeptList() {
        return deptList;
    }

    public void setDeptList(List<ConsolInspDept> deptList) {
        this.deptList = deptList;
    }

    public List<ConsolInspDept> getConsolInspectors() {
        return consolInspectors;
    }

    public void setConsolInspectors(List<ConsolInspDept> consolInspectors) {
        this.consolInspectors = consolInspectors;
    }

    public List<PatrolItemResult> getConsolItems() {
        return consolItems;
    }

    public void setConsolItems(List<PatrolItemResult> consolItems) {
        this.consolItems = consolItems;
    }

}
