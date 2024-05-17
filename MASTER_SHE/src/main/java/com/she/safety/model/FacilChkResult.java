package com.she.safety.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "설비점검결과")
@Data
public class FacilChkResult {

    @ApiModelProperty(value = "설비점검일련번호")
    private int safFacilChkNo;

    @ApiModelProperty(value = "설비점검일정일련번호")
    private int safFacilityCheckNo;

    @ApiModelProperty(value = "설비유형 명")
    private String safFacilityTypeNm;

    @ApiModelProperty(value = "설비유형코드")
    private String safFacilityTypeCd;

    @ApiModelProperty(value = "설비코드")
    private String safFacilityCd;

    @ApiModelProperty(value = "설비명")
    private String facilityNm;

    @ApiModelProperty(value = "관리번호")
    private String facilityMgrNum;

    @ApiModelProperty(value = "설비점검예정일")
    // private String chkSchYmd;
    private String safFacilityCheckSchYmd;

    @ApiModelProperty(value = "설비점검자ID")
    // private String userId;
    private String createUserId;
    private String createUserId2;

    @ApiModelProperty(value = "점검자id")
    private String[] chkUserId;

    @ApiModelProperty(value = "점검자명")
    private String chkUserNm;

    @ApiModelProperty(value = "설비점검자id")
    private String userId;

    @ApiModelProperty(value = "설비점검자명")
    private String userNm;

    @ApiModelProperty(value = "설비점검자id")
    private String userId2;

    @ApiModelProperty(value = "설비점검자명")
    private String userNm2;

    @ApiModelProperty(value = "설비점검완료일")
    private String chkYmd;

    @ApiModelProperty(value = "설비점검완료여부")
    private String chkEndYn;

    @ApiModelProperty(value = "설비점검완료여부명칭")
    private String chkEndYnNm;

    @ApiModelProperty(value = "설비점검결과1")
    private String chkResult;

    @ApiModelProperty(value = "설비점검결과2")
    private String chkResultTwo;

    @ApiModelProperty(value = "설비점검결과예정일")
    private String chkSchYmd;

    @ApiModelProperty(value = "비고")
    private String remark;

    @ApiModelProperty(value = "설비점검결과코드")
    private String chkResultCd;

    @ApiModelProperty(value = "공정코드")
    private String processCd;

    @ApiModelProperty(value = "공정명")
    private String processNm;

    @ApiModelProperty(value = "점검주관부서코드")
    private String pDeptCd;

    @ApiModelProperty(value = "점검주관부서명")
    private String pDeptNm;

    @ApiModelProperty(value = "점검주관부서코드")
    private String createDeptCd;

    @ApiModelProperty(value = "점검주관부서명")
    private String createDeptNm;

    @ApiModelProperty(value = "점검대상부서코드")
    private String tDeptCd;

    @ApiModelProperty(value = "점검대상부서명")
    private String tDeptNm;

    @ApiModelProperty(value = "공정's")
    private String processNms;

    @ApiModelProperty(value = "점검명")
    private String safFacilChkTitle;

    @ApiModelProperty(value = "점검일")
    private String safFacilityCheckYmd;

    @ApiModelProperty(value = "단계")
    private String chkStepNm;

    @ApiModelProperty(value = "점검유형")
    private String safCheckTypeNm;

    @ApiModelProperty(value = "점검항목별결과 목록")
    private List<FacilChkItemResult> facilChkItems;

    @ApiModelProperty(value = "점검유형별 항목 목록")
    private List<ForEachType> forEachTypeList;

    @ApiModelProperty(value = "점검유형별 설비 목록")
    private List<DailyChkForeachFacil> forEachFacilList;

    @ApiModelProperty(value = "점검결과별 확정유무")
    private String chkComYn;

    public List<ForEachType> getForEachTypeList() {
        return forEachTypeList;
    }

    public void setSafCheckTypeNm(String safCheckTypeNm) {
        this.safCheckTypeNm = safCheckTypeNm;
    }

    public String getSafCheckTypeNm() {
        return safCheckTypeNm;
    }

    public void setChkStepNm(String chkStepNm) {
        this.chkStepNm = chkStepNm;
    }

    public String getChkStepNm() {
        return chkStepNm;
    }

    public void setSafFacilityCheckYmd(String safFacilityCheckYmd) {
        this.safFacilityCheckYmd = safFacilityCheckYmd;
    }

    public String getSafFacilityCheckYmd() {
        return safFacilityCheckYmd;
    }

    public void setForEachTypeList(List<ForEachType> forEachTypeList) {
        this.forEachTypeList = forEachTypeList;
    }

    public int getSafFacilChkNo() {
        return safFacilChkNo;
    }

    public void setSafFacilChkNo(int safFacilChkNo) {
        this.safFacilChkNo = safFacilChkNo;
    }

    public int getSafFacilityCheckNo() {
        return safFacilityCheckNo;
    }

    public void setSafFacilityCheckNo(int safFacilityCheckNo) {
        this.safFacilityCheckNo = safFacilityCheckNo;
    }

    public String getSafFacilityTypeNm() {
        return safFacilityTypeNm;
    }

    public void setSafFacilityTypeNm(String safFacilityTypeNm) {
        this.safFacilityTypeNm = safFacilityTypeNm;
    }

    public String getSafFacilityTypeCd() {
        return safFacilityTypeCd;
    }

    public void setSafFacilityTypeCd(String safFacilityTypeCd) {
        this.safFacilityTypeCd = safFacilityTypeCd;
    }

    public String getSafFacilityCd() {
        return safFacilityCd;
    }

    public void setSafFacilityCd(String safFacilityCd) {
        this.safFacilityCd = safFacilityCd;
    }

    public String getFacilityNm() {
        return facilityNm;
    }

    public void setFacilityNm(String facilityNm) {
        this.facilityNm = facilityNm;
    }

    public String getFacilityMgrNum() {
        return facilityMgrNum;
    }

    public void setFacilityMgrNum(String facilityMgrNum) {
        this.facilityMgrNum = facilityMgrNum;
    }

    public String getSafFacilityCheckSchYmd() {
        return safFacilityCheckSchYmd;
    }

    public void setSafFacilityCheckSchYmd(String safFacilityCheckSchYmd) {
        this.safFacilityCheckSchYmd = safFacilityCheckSchYmd;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
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

    public String getChkYmd() {
        return chkYmd;
    }

    public void setChkYmd(String chkYmd) {
        this.chkYmd = chkYmd;
    }

    public String getChkEndYn() {
        return chkEndYn;
    }

    public void setChkEndYn(String chkEndYn) {
        this.chkEndYn = chkEndYn;
    }

    public String getChkEndYnNm() {
        return chkEndYnNm;
    }

    public void setChkEndYnNm(String chkEndYnNm) {
        this.chkEndYnNm = chkEndYnNm;
    }

    public String getChkResult() {
        return chkResult;
    }

    public void setChkResult(String chkResult) {
        this.chkResult = chkResult;
    }

    public String getChkResultTwo() {
        return chkResultTwo;
    }

    public void setChkResultTwo(String chkResultTwo) {
        this.chkResultTwo = chkResultTwo;
    }

    public String getChkSchYmd() {
        return chkSchYmd;
    }

    public void setChkSchYmd(String chkSchYmd) {
        this.chkSchYmd = chkSchYmd;
    }

    public String getChkResultCd() {
        return chkResultCd;
    }

    public void setChkResultCd(String chkResultCd) {
        this.chkResultCd = chkResultCd;
    }

    public String getProcessCd() {
        return processCd;
    }

    public void setProcessCd(String processCd) {
        this.processCd = processCd;
    }

    public String getProcessNm() {
        return processNm;
    }

    public void setProcessNm(String processNm) {
        this.processNm = processNm;
    }

    public String getProcessNms() {
        return processNms;
    }

    public void setProcessNms(String processNms) {
        this.processNms = processNms;
    }

    public String getSafFacilChkTitle() {
        return safFacilChkTitle;
    }

    public void setSafFacilChkTitle(String safFacilChkTitle) {
        this.safFacilChkTitle = safFacilChkTitle;
    }

    public List<FacilChkItemResult> getFacilChkItems() {
        return facilChkItems;
    }

    public void setFacilChkItems(List<FacilChkItemResult> facilChkItems) {
        this.facilChkItems = facilChkItems;
    }

    public String[] getChkUserId() {
        return chkUserId;
    }

    public void setChkUserId(String[] chkUserId) {
        this.chkUserId = chkUserId;
    }

    public String getChkUserNm() {
        return chkUserNm;
    }

    public void setChkUserNm(String chkUserNm) {
        this.chkUserNm = chkUserNm;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<DailyChkForeachFacil> getForEachFacilList() {
        return forEachFacilList;
    }

    public String getUserId2() {
        return userId2;
    }

    public void setUserId2(String userId2) {
        this.userId2 = userId2;
    }

    public String getUserNm2() {
        return userNm2;
    }

    public void setUserNm2(String userNm2) {
        this.userNm2 = userNm2;
    }

    public String getCreateUserId2() {
        return createUserId2;
    }

    public void setCreateUserId2(String createUserId2) {
        this.createUserId2 = createUserId2;
    }

    public void setForEachFacilList(List<DailyChkForeachFacil> forEachFacilList) {
        this.forEachFacilList = forEachFacilList;
    }

    public String getpDeptCd() {
        return pDeptCd;
    }

    public void setpDeptCd(String pDeptCd) {
        this.pDeptCd = pDeptCd;
    }

    public String getpDeptNm() {
        return pDeptNm;
    }

    public void setpDeptNm(String pDeptNm) {
        this.pDeptNm = pDeptNm;
    }

    public String gettDeptCd() {
        return tDeptCd;
    }

    public void settDeptCd(String tDeptCd) {
        this.tDeptCd = tDeptCd;
    }

    public String gettDeptNm() {
        return tDeptNm;
    }

    public void settDeptNm(String tDeptNm) {
        this.tDeptNm = tDeptNm;
    }

    public String getChkComYn() {
        return chkComYn;
    }

    public void setChkComYn(String chkComYn) {
        this.chkComYn = chkComYn;
    }

    public String getCreateDeptCd() {
        return createDeptCd;
    }

    public void setCreateDeptCd(String createDeptCd) {
        this.createDeptCd = createDeptCd;
    }

    public String getCreateDeptNm() {
        return createDeptNm;
    }

    public void setCreateDeptNm(String createDeptNm) {
        this.createDeptNm = createDeptNm;
    }
}
