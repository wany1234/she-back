package com.she.health.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "작업환경측정결과")
public class WorkMeasureResult {

    @ApiModelProperty(value = "작업환경측정결과번호")
    private int workMeasRsltNo;

    @ApiModelProperty(value = "작업환경측정계획번호")
    private int workMeasPlanNo;

    @ApiModelProperty(value = "작업환경측정분류코드(C:화학적인자, P:물리적인자)")
    private String workAreaGradeCd;

    @ApiModelProperty(value = "작업환경측정분류코드명")
    private String workAreaGradeNm;

    @ApiModelProperty(value = "사업장코드")
    private String plantCd;

    @ApiModelProperty(value = "사업장명")
    private String plantNm;

    @ApiModelProperty(value = "부서코드")
    private String deptCd;

    @ApiModelProperty(value = "부서명")
    private String deptNm;

    @ApiModelProperty(value = "공정코드")
    private String processCd;

    @ApiModelProperty(value = "공정명")
    private String processNm;

    @ApiModelProperty(value = "단위작업장소(주요발생원인)")
    private String workPlace;

    @ApiModelProperty(value = "유해인자코드")
    private String hazardCd;

    @ApiModelProperty(value = "유해인자명칭")
    private String hazardNm;

    @ApiModelProperty(value = "작업내용")
    private String workContents;

    @ApiModelProperty(value = "근로자수")
    private String workerCnt;

    @ApiModelProperty(value = "근로형태_시간")
    private String shiftType;

    @ApiModelProperty(value = "발생형태 및 발생시간(주기)")
    private String occurType;

    @ApiModelProperty(value = "유해인자발생시간")
    private int occurTime;

    @ApiModelProperty(value = "측정위치(근로자)")
    private String measPsnNm;

    @ApiModelProperty(value = "측정시간(시작)")
    private String measStartTime;

    @ApiModelProperty(value = "측정시간(시작)")
    private String measEndTime;

    @ApiModelProperty(value = "측정일자")
    private String measDt;

    @ApiModelProperty(value = "측정횟수")
    private int measCnt;

    @ApiModelProperty(value = "측정치")
    private String measValue;

    @ApiModelProperty(value = "시간가중평균치(전회)")
    private String twaPrev;

    @ApiModelProperty(value = "시간가중평균치(금회)")
    private String twaCurr;

    @ApiModelProperty(value = "노출기준")
    private String exposureStd;

    @ApiModelProperty(value = "노출기준초과여부")
    private String exposureExcessFlag;

    @ApiModelProperty(value = "측정_분석방법")
    private String measMethod;

    @ApiModelProperty(value = "비고")
    private String remark;

    @ApiModelProperty(value = "등록자ID")
    private String createUserId;

    @ApiModelProperty(value = "등록자명")
    private String createUserNm;

    @ApiModelProperty(value = "등록일")
    private String createDt;

    @ApiModelProperty(value = "수정자ID")
    private String updateUserId;

    @ApiModelProperty(value = "수정자명")
    private String updateUserNm;

    @ApiModelProperty(value = "수정일")
    private String updateDt;

    @ApiModelProperty(value = "측정년도")
    private String year;

    @ApiModelProperty(value = "측정분기")
    private String halfYearCd;

    @ApiModelProperty(value = "측정분기명")
    private String halfYearNm;

    @ApiModelProperty(value = "측정기관")
    private String measAgency;

    @ApiModelProperty(value = "측정기간 시작일")
    private String measDtSta;

    @ApiModelProperty(value = "측정기간 종료일")
    private String measDtEnd;

    @ApiModelProperty(value = "측정계획명")
    private String measPlanNm;

    @ApiModelProperty(value = "진행상태")
    private String workMeasStateCd;

    @ApiModelProperty(value = "진행상태명")
    private String workMeasStateNm;

    @ApiModelProperty(value = "계획결재진행번호")
    private String planApprRqstNo;

    @ApiModelProperty(value = "결과결재진행번호")
    private String rsltApprRqstNo;

    @ApiModelProperty(value = "결재진행코드")
    private String bizApprStepCd;

    @ApiModelProperty(value = "결재진행명")
    private String bizApprStepNm;

    @ApiModelProperty(value = "작성자")
    private String writerUserNm;

    @ApiModelProperty(value = "작성일")
    private String writerDt;

    public WorkMeasureResult() {
    }

    public int getWorkMeasRsltNo() {
        return workMeasRsltNo;
    }

    public void setWorkMeasRsltNo(int workMeasRsltNo) {
        this.workMeasRsltNo = workMeasRsltNo;
    }

    public int getWorkMeasPlanNo() {
        return workMeasPlanNo;
    }

    public void setWorkMeasPlanNo(int workMeasPlanNo) {
        this.workMeasPlanNo = workMeasPlanNo;
    }

    public String getWorkAreaGradeCd() {
        return workAreaGradeCd;
    }

    public void setWorkAreaGradeCd(String workAreaGradeCd) {
        this.workAreaGradeCd = workAreaGradeCd;
    }

    public String getWorkAreaGradeNm() {
        return workAreaGradeNm;
    }

    public void setWorkAreaGradeNm(String workAreaGradeNm) {
        this.workAreaGradeNm = workAreaGradeNm;
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

    public String getWorkPlace() {
        return workPlace;
    }

    public void setWorkPlace(String workPlace) {
        this.workPlace = workPlace;
    }

    public String getHazardCd() {
        return hazardCd;
    }

    public void setHazardCd(String hazardCd) {
        this.hazardCd = hazardCd;
    }

    public String getHazardNm() {
        return hazardNm;
    }

    public void setHazardNm(String hazardNm) {
        this.hazardNm = hazardNm;
    }

    public String getWorkContents() {
        return workContents;
    }

    public void setWorkContents(String workContents) {
        this.workContents = workContents;
    }

    public String getWorkerCnt() {
        return workerCnt;
    }

    public void setWorkerCnt(String workerCnt) {
        this.workerCnt = workerCnt;
    }

    public String getShiftType() {
        return shiftType;
    }

    public void setShiftType(String shiftType) {
        this.shiftType = shiftType;
    }

    public int getOccurTime() {
        return occurTime;
    }

    public void setOccurTime(int occurTime) {
        this.occurTime = occurTime;
    }

    public String getMeasPsnNm() {
        return measPsnNm;
    }

    public void setMeasPsnNm(String measPsnNm) {
        this.measPsnNm = measPsnNm;
    }

    public String getMeasStartTime() {
        return measStartTime;
    }

    public void setMeasStartTime(String measStartTime) {
        this.measStartTime = measStartTime;
    }

    public String getMeasEndTime() {
        return measEndTime;
    }

    public void setMeasEndTime(String measEndTime) {
        this.measEndTime = measEndTime;
    }

    public int getMeasCnt() {
        return measCnt;
    }

    public void setMeasCnt(int measCnt) {
        this.measCnt = measCnt;
    }

    public String getMeasValue() {
        return measValue;
    }

    public void setMeasValue(String measValue) {
        this.measValue = measValue;
    }

    public String getTwaPrev() {
        return twaPrev;
    }

    public void setTwaPrev(String twaPrev) {
        this.twaPrev = twaPrev;
    }

    public String getTwaCurr() {
        return twaCurr;
    }

    public void setTwaCurr(String twaCurr) {
        this.twaCurr = twaCurr;
    }

    public String getExposureStd() {
        return exposureStd;
    }

    public void setExposureStd(String exposureStd) {
        this.exposureStd = exposureStd;
    }

    public String getExposureExcessFlag() {
        return exposureExcessFlag;
    }

    public void setExposureExcessFlag(String exposureExcessFlag) {
        this.exposureExcessFlag = exposureExcessFlag;
    }

    public String getMeasMethod() {
        return measMethod;
    }

    public void setMeasMethod(String measMethod) {
        this.measMethod = measMethod;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public String getOccurType() {
        return occurType;
    }

    public void setOccurType(String occurType) {
        this.occurType = occurType;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getHalfYearCd() {
        return halfYearCd;
    }

    public void setHalfYearCd(String halfYearCd) {
        this.halfYearCd = halfYearCd;
    }

    public String getHalfYearNm() {
        return halfYearNm;
    }

    public void setHalfYearNm(String halfYearNm) {
        this.halfYearNm = halfYearNm;
    }

    public String getMeasAgency() {
        return measAgency;
    }

    public void setMeasAgency(String measAgency) {
        this.measAgency = measAgency;
    }

    public String getMeasPlanNm() {
        return measPlanNm;
    }

    public void setMeasPlanNm(String measPlanNm) {
        this.measPlanNm = measPlanNm;
    }

    public String getWorkMeasStateCd() {
        return workMeasStateCd;
    }

    public void setWorkMeasStateCd(String workMeasStateCd) {
        this.workMeasStateCd = workMeasStateCd;
    }

    public String getWorkMeasStateNm() {
        return workMeasStateNm;
    }

    public void setWorkMeasStateNm(String workMeasStateNm) {
        this.workMeasStateNm = workMeasStateNm;
    }

    public String getPlanApprRqstNo() {
        return planApprRqstNo;
    }

    public void setPlanApprRqstNo(String planApprRqstNo) {
        this.planApprRqstNo = planApprRqstNo;
    }

    public String getRsltApprRqstNo() {
        return rsltApprRqstNo;
    }

    public void setRsltApprRqstNo(String rsltApprRqstNo) {
        this.rsltApprRqstNo = rsltApprRqstNo;
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

    public String getMeasDtSta() {
        return measDtSta;
    }

    public void setMeasDtSta(String measDtSta) {
        this.measDtSta = measDtSta;
    }

    public String getMeasDtEnd() {
        return measDtEnd;
    }

    public void setMeasDtEnd(String measDtEnd) {
        this.measDtEnd = measDtEnd;
    }

    public String getMeasDt() {
        return measDt;
    }

    public void setMeasDt(String measDt) {
        this.measDt = measDt;
    }
}
