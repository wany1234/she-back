package com.she.health.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

// SK E&S
@ApiModel(description = "근골격계 질환조사 정보")
@Data
public class MuscResearch {

    @ApiModelProperty(value = "근골격계질환조사번호")
    private int muscResearchNo;

    @ApiModelProperty(value = "사업부코드")
    private String plantCd;

    @ApiModelProperty(value = "사업부명")
    private String plantNm;

    @ApiModelProperty(value = "년도")
    private String year;

    @ApiModelProperty(value = "조사명")
    private String researchNm;

    @ApiModelProperty(value = "조사내용")
    private String researchDesc;

    @ApiModelProperty(value = "조사기관")
    private String researchOrgNm;

    @ApiModelProperty(value = "조사일자(From)")
    private String startDate;

    @ApiModelProperty(value = "조사일자(From)")
    private String endDate;

    @ApiModelProperty(value = "조사일자(From)")
    private String researchStartDt;

    @ApiModelProperty(value = "조사일자(To)")
    private String researchEndDt;

    @ApiModelProperty(value = "조사기간")
    private String duration;

    @ApiModelProperty(value = "비고")
    private String remark;

    @ApiModelProperty(value = "결재진행상태명")
    private String bizApprStepNm;

    @ApiModelProperty(value = "결재진행상태")
    private String bizApprStepCd;

    @ApiModelProperty(value = "(C)진행상태")
    private String muscResearchStateCd;

    @ApiModelProperty(value = "(C)진행상태명")
    private String muscResearchStateNm;

    @ApiModelProperty(value = "등록일")
    private String createUserId;

    @ApiModelProperty(value = "등록자")
    private String createDt;

    @ApiModelProperty(value = "수정자")
    private String updateUserId;

    @ApiModelProperty(value = "수정일")
    private String updateDt;

    @ApiModelProperty(value = "결재요청번호")
    private int apprRqstNo;

    @ApiModelProperty(value = "조사부서명s")
    private String deptNms;

    @ApiModelProperty(value = "조사결과 리스트")
    private List<MuscResearchRslt> muscResearchRslts;

    @ApiModelProperty(value = "조사부서 리스트")
    private List<MuscResearchDept> muscResearchDepts;

    @ApiModelProperty(value = "[결과등록 화면 상세화면에서 결과완료 처리를 위한]부서별조사번호")
    private int muscResearchDeptNo;

    @ApiModelProperty(value = "[결과등록 화면 상세화면에서 결과완료 처리를 위한]부서코드")
    private String deptCd;

    @ApiModelProperty(value = "작성자")
    private String writerUserNm;

    @ApiModelProperty(value = "작성일")
    private String writerDt;

}
