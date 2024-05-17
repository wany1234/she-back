package com.she.health.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

// SK E&S
@ApiModel(description = "근골격계 질환조사 정보")
@Data
public class MuscResearchUnit {

    @ApiModelProperty(value = "근골격계질환조사번호")
    private int muscResearchNo;

    @ApiModelProperty(value = "부서별조사번호")
    private int muscResearchDeptNo;

    @ApiModelProperty(value = "단위작업번호")
    private int muscResearchUnitNo;

    @ApiModelProperty(value = "부담작업번호")
    private int muscResearchChklistNo;

    @ApiModelProperty(value = "조사번호")
    private String researchCd;

    @ApiModelProperty(value = "공정 코드")
    private String processCd;

    @ApiModelProperty(value = "사업부 코드")
    private String plantCd;

    @ApiModelProperty(value = "사업부 명")
    private String plantNm;

    @ApiModelProperty(value = "공정명")
    private String processNm;

    @ApiModelProperty(value = "조사번호")
    private String unitNo;

    @ApiModelProperty(value = "공정내용")
    private String processDesc;

    @ApiModelProperty(value = "유해요인")
    private String chklistDesc;

    @ApiModelProperty(value = "단위작업명")
    private String unitWorkNm;

    @ApiModelProperty(value = "조사일")
    private String researchDt;

    @ApiModelProperty(value = "작업인원")
    private String workerCnt;

    @ApiModelProperty(value = "담당부서")
    private String deptCd;

    @ApiModelProperty(value = "담당부서명")
    private String deptNm;

    @ApiModelProperty(value = "조사자")
    private String researchUserId;

    @ApiModelProperty(value = "조사자명")
    private String researchUserNm;

    @ApiModelProperty(value = "비고")
    private String remark;

    @ApiModelProperty(value = "등록일")
    private String createUserId;

    @ApiModelProperty(value = "등록자")
    private String createDt;

    @ApiModelProperty(value = "수정자")
    private String updateUserId;

    @ApiModelProperty(value = "수정일")
    private String updateDt;

    @ApiModelProperty(value = "유해요인평가번호")
    private int muscHarmfulEvalNo;

    @ApiModelProperty(value = "작업부하(A)")
    private int workload;

    @ApiModelProperty(value = "작업빈도(B)")
    private int workCnt;

    @ApiModelProperty(value = "총점수(A*B)")
    private int totalScore;

    @ApiModelProperty(value = "작업명")
    private String workNm;

    @ApiModelProperty(value = "발생원인")
    private String causes;

    @ApiModelProperty(value = "비고")
    private String surRemark;

    @ApiModelProperty(value = "부담작업no 목록")
    private List<MuscResearchRslt> muscResearchChklists;

    @ApiModelProperty(value = "단위작업 목록")
    private List<MuscHarmfulEval> muscHarmfulSurvList;

    // @ApiModelProperty(value = "대상공정 목록")
    // private List<MuscSurveyProcess> processItems;
    //
    // @ApiModelProperty(value = "작업분류표 목록")
    // private List<MuscSurveyChklist> chklistItems;
    //
    // @ApiModelProperty(value = "단위작업 목록")
    // private List<MuscSurveyChklist> unitItems;

}
