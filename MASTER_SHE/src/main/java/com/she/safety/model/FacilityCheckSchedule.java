package com.she.safety.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "설비점검일정")
@Getter
@Setter
public class FacilityCheckSchedule {

    @ApiModelProperty(value = "설비점검일정일련번호")
    private int safFacilityCheckScheduleNo;
    @ApiModelProperty(value = "설비점검일련번호")
    private int safFacilityCheckNo;
    @ApiModelProperty(value = "사업장")
    private String plantCd;
    @ApiModelProperty(value = "사업장명")
    private String plantNm;
    @ApiModelProperty(value = "주관점검부서코드")
    private String deptCd;
    @ApiModelProperty(value = "주관점검부서명")
    private String deptNm;
    @ApiModelProperty(value = "설비점검명")
    private String safFacilityCheckTitle;
    @ApiModelProperty(value = "설비점검종류코드")
    private String safCheckTypeCd;
    @ApiModelProperty(value = "설비점검종류명")
    private String safCheckTypeNm;
    @ApiModelProperty(value = "설비점검일정점검명")
    private String safFacilityCheckNm;
    @ApiModelProperty(value = "안전설비코드")
    private String safFacilityCd;
    @ApiModelProperty(value = "안전설비명")
    private String facilityNm;
    @ApiModelProperty(value = "설비유형")
    private String safFacilityTypeCd;
    @ApiModelProperty(value = "설비유형명")
    private String safFacilityTypeNm;
    @ApiModelProperty(value = "설비점검예정일")
    private String safFacilityCheckSchYmd;
    @ApiModelProperty(value = "설비점검일")
    private String safFacilityCheckYmd;
    @ApiModelProperty(value = "설비점검결과요약")
    private String safFacilityCheckResult;
    @ApiModelProperty(value = "점검진행상태")
    private String checkStepCd;
    @ApiModelProperty(value = "점검진행상태명")
    private String checkStepNm;
    @ApiModelProperty(value = "설비점검결과결재진행상태")
    private String apprStepCd;
    @ApiModelProperty(value = "설비점검결과결재진행상태명")
    private String apprStepNm;
    @ApiModelProperty(value = "설비점검결과")
    private String checkResultCd;
    @ApiModelProperty(value = "설비점검결과명")
    private String checkResultNm;
    @ApiModelProperty(value = "등록자")
    private String createUserId;
    @ApiModelProperty(value = "등록자명")
    private String createUserNm;
    @ApiModelProperty(value = "등록일")
    private String createDt;
    @ApiModelProperty(value = "수정자")
    private String updateUserId;
    @ApiModelProperty(value = "수정자명")
    private String updateUserNm;
    @ApiModelProperty(value = "수정일")
    private String updateDt;
    @ApiModelProperty(value = "설비-사업장")
    private String facilityPlantCd;
    @ApiModelProperty(value = "설비-사업장명")
    private String facilityPlantNm;
    @ApiModelProperty(value = "설비-관리부서코드")
    private String facilityDeptCd;
    @ApiModelProperty(value = "설비-관리부서명")
    private String facilityDeptNm;
    @ApiModelProperty(value = "설비-공정코드")
    private String facilityProcessCd;
    @ApiModelProperty(value = "설비-공정명")
    private String facilityProcessNm;
    @ApiModelProperty(value = "계획결재요청번호")
    private String papprRqstNo;
    @ApiModelProperty(value = "결과결재요청번호")
    private String rapprRqstNo;
    @ApiModelProperty(value = "결재진행코드")
    private String bizApprStepCd;
    @ApiModelProperty(value = "결재진행명")
    private String bizApprStepNm;

    @ApiModelProperty(value = "설비점검대상자 리스트")
    private List<FacilityCheckInspector> facilityCheckInspectors;
    @ApiModelProperty(value = "설비점검항목별점검결과 리스트")
    private List<FacilityCheckItemResult> facilityCheckItemResults;
}
