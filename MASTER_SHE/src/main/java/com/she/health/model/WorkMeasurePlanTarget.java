package com.she.health.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "작업환경측정 범위 및 대상")
@Data
public class WorkMeasurePlanTarget {

    @ApiModelProperty(value = "작업환경측정계획범위번호")
    private int workMeasPlanTargetNo;

    @ApiModelProperty(value = "작업환경측정계획번호")
    private int workMeasPlanNo;

    @ApiModelProperty(value = "부서코드")
    private String deptCd;

    @ApiModelProperty(value = "부서명")
    private String deptNm;

    @ApiModelProperty(value = "공정코드")
    private String processCd;

    @ApiModelProperty(value = "공정명")
    private String processNm;

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

    @ApiModelProperty(value = "작업환경측정 유해인자s")
    private List<WorkMeasurePlanHazard> workMeasurePlanHazards;

}
