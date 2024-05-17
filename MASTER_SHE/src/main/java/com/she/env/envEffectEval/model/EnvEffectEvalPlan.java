package com.she.env.envEffectEval.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "환경영향평가계획")
@Data
public class EnvEffectEvalPlan {

    @ApiModelProperty(value = "환경영향평가 계획번호")
    public int evalPlanNo;

    @ApiModelProperty(value = "사업장코드")
    public String plantCd;

    @ApiModelProperty(value = "사업장명")
    public String plantNm;

    @ApiModelProperty(value = "부서코드")
    public String deptCd;

    @ApiModelProperty(value = "부서명")
    public String deptNm;

    @ApiModelProperty(value = "진행단계코드")
    public String envEffEvalPlanStepCd;

    @ApiModelProperty(value = "진행단계명")
    public String envEffEvalPlanStepNm;

    @ApiModelProperty(value = "평가구분코드")
    public String envEffEvalDivCd;

    @ApiModelProperty(value = "평가구분명")
    public String envEffEvalDivNm;

    @ApiModelProperty(value = "평가년도")
    public String evalYear;

    @ApiModelProperty(value = "평가기간")
    public String evalPeriod;

    @ApiModelProperty(value = "평가시작일")
    public String evalStartYmd;

    @ApiModelProperty(value = "평가종료일")
    public String evalEndYmd;

    @ApiModelProperty(value = "평가명")
    public String evalTitle;

    @ApiModelProperty(value = "대상부서코드")
    public String tgtDeptCd;

    @ApiModelProperty(value = "대상부서명")
    public String tgtDeptNm;

    @ApiModelProperty(value = "대상공정코드")
    public String tgtProcessCd;

    @ApiModelProperty(value = "대상공정명")
    public String tgtProcessNm;

    @ApiModelProperty(value = "비고")
    public String remark;

    @ApiModelProperty(value = "계획결재번호")
    public int planApprRqstNo;

    @ApiModelProperty(value = "계획결재상태코드")
    public String planApprStepCd;

    @ApiModelProperty(value = "계획결재상태명")
    public String planApprStepNm;

    @ApiModelProperty(value = "결과결재번호")
    public int rsltApprRqstNo;

    @ApiModelProperty(value = "결과결재상태코드")
    public String rsltApprStepCd;

    @ApiModelProperty(value = "결과결재상태명")
    public String rsltApprStepNm;

    @ApiModelProperty(value = "결과결재상태코드")
    public String apprStepCd;

    @ApiModelProperty(value = "결과결재상태명")
    public String apprStepNm;

    @ApiModelProperty(value = "등록자")
    private String createUserId;

    @ApiModelProperty(value = "등록일")
    private String createDt;

    @ApiModelProperty(value = "수정자")
    private String updateUserId;

    @ApiModelProperty(value = "수정일")
    private String updateDt;

    @ApiModelProperty(value = "등록자부서명")
    private String createDeptNm;

    @ApiModelProperty(value = "등록자부서코드")
    private String createDeptCd;

    @ApiModelProperty(value = "수정자부서명")
    private String updateDeptNm;

    @ApiModelProperty(value = "수정부서코드")
    private String updateDeptCd;

    @ApiModelProperty(value = "등록자직위명")
    private String createPositionNm;

    @ApiModelProperty(value = "등록자직위코드")
    private String createPositionCd;

    @ApiModelProperty(value = "수정자직위명")
    private String updatePositionNm;

    @ApiModelProperty(value = "수정자직위코드")
    private String updatePositionCd;

    @ApiModelProperty(value = "작성일")
    private String writeDt;

    @ApiModelProperty(value = "작성자아이디")
    private String writeUserId;

    @ApiModelProperty(value = "작성자명")
    private String writeUserNm;

    @ApiModelProperty(value = "환경영향평가 결과")
    public List<EnvEffectEvalRslt> envEffectEvalRslts;
}
