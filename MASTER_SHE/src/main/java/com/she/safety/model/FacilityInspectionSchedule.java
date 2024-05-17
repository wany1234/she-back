package com.she.safety.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "시설점검일정")
@Getter
@Setter
public class FacilityInspectionSchedule {

    @ApiModelProperty(value = "시설점검일정일련번호")
    private int comFacilityCheckScheduleNo;

    @ApiModelProperty(value = "시설점검일련번호")
    private int comFacilityCheckNo;

    @ApiModelProperty(value = "사업장")
    private String plantCd;

    @ApiModelProperty(value = "사업장명")
    private String plantNm;

    @ApiModelProperty(value = "점검주관부서코드")
    private String deptCd;

    @ApiModelProperty(value = "점검주관부서명")
    private String deptNm;
    
    @ApiModelProperty(value = "점검대상부서코드")
    private String tgtDeptCd;

    @ApiModelProperty(value = "점검대상부서명")
    private String tgtDeptNm;
    
    @ApiModelProperty(value = "점검수행부서코드")
    private String pfmDeptCd;

    @ApiModelProperty(value = "점검수행부서명")
    private String pfmDeptNm;

    @ApiModelProperty(value = "일정명")
    private String comFacilityCheckTitle;

    @ApiModelProperty(value = "시설점검종류코드")
    private String comFacilityCheckCd;

    @ApiModelProperty(value = "시설점검종류명")
    private String comFacilityCheckNm;

    @ApiModelProperty(value = "시설점검명")
    private String facilityCheckNm;

    @ApiModelProperty(value = "시설점검예정일")
    private String facilityCheckSchYmd;

    @ApiModelProperty(value = "시설점검계획시작일")
    private String comFacilityCheckPlanSymd;

    @ApiModelProperty(value = "시설점검계획종료일")
    private String comFacilityCheckPlanEymd;

    @ApiModelProperty(value = "시설코드")
    private String facilityCd;

    @ApiModelProperty(value = "시설명")
    private String facilityNm;

    @ApiModelProperty(value = "시설유형코드")
    private String comFacilityTypeCd;

    @ApiModelProperty(value = "시설유형명")
    private String comFacilityTypeNm;

    @ApiModelProperty(value = "시설점검일")
    private String facilityCheckYmd;

    @ApiModelProperty(value = "점검결과코드")
    private String checkResultCd;
    
    @ApiModelProperty(value = "점검결과명")
    private String checkResultNm;

    @ApiModelProperty(value = "시설점검결과요약")
    private String facilityCheckResult;

    @ApiModelProperty(value = "점검진행상태")
    private String checkStepCd;

    @ApiModelProperty(value = "점검진행상태명")
    private String checkStepNm;

    @ApiModelProperty(value = "시설점검계획결재진행상태No")
    private int pApprRqstNo;

    @ApiModelProperty(value = "시설점검결과결재진행상태No")
    private int rApprRqstNo;

    @ApiModelProperty(value = "시설점검결재진행상태")
    private String bizApprStepCd;

    @ApiModelProperty(value = "시설점검결재진행상태명")
    private String bizApprStepNm;

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

    @ApiModelProperty(value = "시설점검대상자 리스트")
    private List<FacilityInspectionInspector> facilityInsInspectors;

    @ApiModelProperty(value = "시설점검항목별결과 리스트")
    private List<FacilityInspectionItemResult> facilityInsItemResults;

    @ApiModelProperty(value = "작성자")
    private String writerUserNm;

    @ApiModelProperty(value = "작성일")
    private String writerDt;

    @ApiModelProperty(value = "무계획결과유무")
    private String noPlanYn;

    @ApiModelProperty(value = "무일정계획유무")
    private String noSchYn;
    
    @ApiModelProperty(value = "시설점검항목 임시파일키")
    private String checkItemTaskTempKey;

}
