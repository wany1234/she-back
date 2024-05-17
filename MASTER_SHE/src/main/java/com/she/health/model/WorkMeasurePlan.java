package com.she.health.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "작업환경측정계획")
@Data
public class WorkMeasurePlan {

    @ApiModelProperty(value = "작업환경측정계획번호")
    private int workMeasPlanNo;

    @ApiModelProperty(value = "사업장코드")
    private String plantCd;

    @ApiModelProperty(value = "사업장명")
    private String plantNm;

    @ApiModelProperty(value = "측정년도")
    private String year;

    @ApiModelProperty(value = "측정분기")
    private String halfYearCd;

    @ApiModelProperty(value = "측정분기명")
    private String halfYearNm;

    @ApiModelProperty(value = "측정기관")
    private String measAgency;

    @ApiModelProperty(value = "측정일자 시작일")
    private String measDtSta;

    @ApiModelProperty(value = "측정일자 종료일")
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

    @ApiModelProperty(value = "작업환경측정결과s")
    private List<WorkMeasureResult> workMeasureResults;

    @ApiModelProperty(value = "범위 및 대상")
    private List<WorkMeasurePlanTarget> workMeasurePlanTargets;

    @ApiModelProperty(value = "[삭제] 범위 및 대상")
    private List<WorkMeasurePlanTarget> deleteWorkMeasurePlanTargets;

    @ApiModelProperty(value = "작성자")
    private String writerUserNm;

    @ApiModelProperty(value = "작성일")
    private String writerDt;
    
    @ApiModelProperty(value = "화학적인자수")
    private int cHarmCount;
    
    @ApiModelProperty(value = "물리적인자수")
    private int pHarmCount;
    
}
