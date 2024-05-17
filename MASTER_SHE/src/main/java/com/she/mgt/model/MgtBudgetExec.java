package com.she.mgt.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "예산집행")
@Data
public class MgtBudgetExec {
    @ApiModelProperty(value = "예산집행번호")
    private int budgetExecNo;

    @ApiModelProperty(value = "예산편성부서계정번호")
    private int budgetActDeptItemNo;

    @ApiModelProperty(value = "년도")
    private String year;

    @ApiModelProperty(value = "집행일자")
    private String execDt;

    @ApiModelProperty(value = "사업장코드")
    private String plantCd;

    @ApiModelProperty(value = "사업장")
    private String plantNm;

    @ApiModelProperty(value = "대상부서코드")
    private String deptCd;

    @ApiModelProperty(value = "대상부서")
    private String deptNm;

    @ApiModelProperty(value = "예산분류코드")
    private String budgetTypeCd;

    @ApiModelProperty(value = "예산분류명")
    private String budgetTypeNm;

    @ApiModelProperty(value = "예산구분코드")
    private String budgetClsCd;

    @ApiModelProperty(value = "예산구분명")
    private String budgetClsNm;

    @ApiModelProperty(value = "예산계정번호")
    private int budgetActMstNo;

    @ApiModelProperty(value = "예산계정코드")
    private String budgetActCd;

    @ApiModelProperty(value = "예산계정명")
    private String budgetActNm;

    @ApiModelProperty(value = "집행금액")
    private long execAmt;

    @ApiModelProperty(value = "집행금액")
    private String execAmtT;

    @ApiModelProperty(value = "집행가능 금액")
    private String execPsblAmt;

    @ApiModelProperty(value = "단계 MGT_BUDGET_STEP")
    private String procStepCd;

    @ApiModelProperty(value = "단계")
    private String procStepNm;

    @ApiModelProperty(value = "상태 COM_STATE")
    private String stateCd;

    @ApiModelProperty(value = "상태")
    private String stateNm;

    @ApiModelProperty(value = "결재상태 COM_BIZ_APPR_STEP")
    private String stepCd;

    @ApiModelProperty(value = "결재상태")
    private String bizApprStepNm;

    @ApiModelProperty(value = "단계(상태)")
    private String stepNm;

    @ApiModelProperty(value = "결재요청번호")
    private int apprRqstNo;

    @ApiModelProperty(value = "등록자ID")
    private String createUserId;

    @ApiModelProperty(value = "등록일시")
    private String createDt;

    @ApiModelProperty(value = "수정자ID")
    private String updateUserId;

    @ApiModelProperty(value = "수정일시")
    private String updateDt;

    @ApiModelProperty(value = "등록자부서명")
    private String createDeptNm;

    @ApiModelProperty(value = "등록자부서코드")
    private String createDeptCd;

    @ApiModelProperty(value = "수정자부서명")
    private String updateDeptNm;

    @ApiModelProperty(value = "수정자부서코드")
    private String updateDeptCd;

    @ApiModelProperty(value = "등록자직위명")
    private String createPositionNm;

    @ApiModelProperty(value = "등록자직위코드")
    private String createPositionCd;

    @ApiModelProperty(value = "수정자직위명")
    private String updatePositionNm;

    @ApiModelProperty(value = "수정자직위코드")
    private String updatePositionCd;

    @ApiModelProperty(value = "작성자")
    private String writerNm;

    @ApiModelProperty(value = "작성일")
    private String writeDt;
}