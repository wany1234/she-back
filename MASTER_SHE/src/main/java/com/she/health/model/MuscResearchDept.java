package com.she.health.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

// SK E&S
@ApiModel(description = "근골격계 질환조사별 조사부서")
@Data
public class MuscResearchDept {

    @ApiModelProperty(value = "부서별조사번호")
    private int muscResearchDeptNo;

    @ApiModelProperty(value = "근골격계질환조사번호")
    private int muscResearchNo;

    @ApiModelProperty(value = "사업부코드")
    private String deptCd;

    @ApiModelProperty(value = "사업부명")
    private String deptNm;

    @ApiModelProperty(value = "년도")
    private String sendYn;

    @ApiModelProperty(value = "조사명")
    private String sendUserId;

    @ApiModelProperty(value = "조사내용")
    private String sendUserNm;

    @ApiModelProperty(value = "조사기관")
    private String sendDt;

    @ApiModelProperty(value = "등록일")
    private String createUserId;

    @ApiModelProperty(value = "등록자")
    private String createDt;

    @ApiModelProperty(value = "수정자")
    private String updateUserId;

    @ApiModelProperty(value = "수정일")
    private String updateDt;

    @ApiModelProperty(value = "부서에 따른 조사결과 저장 데이터 수")
    private int cntSurveyResult;

    @ApiModelProperty(value = "조사담당자 부서")
    private String researchDeptCd;

    @ApiModelProperty(value = "조사담당자")
    private String researchUserId;

}
