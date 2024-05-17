package com.she.mgt.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel(description = "예산편성")
@Data
public class MgtBudgetAct {
    @ApiModelProperty(value = "예산편성번호")
    private int budgetActNo;

    @ApiModelProperty(value = "년도")
    private String year;

    @ApiModelProperty(value = "사업장코드")
    private String plantCd;

    @ApiModelProperty(value = "사업장")
    private String plantNm;

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

    @ApiModelProperty(value = "예산편성부서번호")
    private int budgetActDeptNo;

    @ApiModelProperty(value = "대상부서명")
    private String deptNm;

    @ApiModelProperty(value = "대상부서코드")
    private String deptCd;

    @ApiModelProperty(value = "예산분류코드")
    private String budgetTypeCd;

    @ApiModelProperty(value = "예산분류명")
    private String budgetTypeNm;

    @ApiModelProperty(value = "예산구분코드")
    private String budgetClsCd;

    @ApiModelProperty(value = "예산구분명")
    private String budgetClsNm;

    @ApiModelProperty(value = "예산계정코드")
    private String budgetActCd;

    @ApiModelProperty(value = "예산계정명")
    private String budgetActNm;

    @ApiModelProperty(value = "편성금액")
    private String orgAmt;

    @ApiModelProperty(value = "편성금액")
    private String orgAmtT;

    @ApiModelProperty(value = "단계코드(편성/집행)")
    private String procStepCd;

    @ApiModelProperty(value = "단계(편성/집행)")
    private String procStepNm;

    @ApiModelProperty(value = "상태코드(미작성/작성중/결재중/결재완료)")
    private String stateCd;

    @ApiModelProperty(value = "상태(미작성/작성중/결재중/결재완료)")
    private String stateNm;

    @ApiModelProperty(value = "결재상태코드")
    private String stepCd;

    @ApiModelProperty(value = "단계(상태)")
    private String stepNm;

    @ApiModelProperty(value = "결재요청번호")
    private int apprRqstNo;

    @ApiModelProperty(value = "작성자(최종수정자)")
    private String writerNm;

    @ApiModelProperty(value = "작성일(최종수정일)")
    private String writeDt;

    @ApiModelProperty(value = "예산편성 부서 목록")
    private List<MgtBudgetActDept> mgtBudgetActDepts;

    @ApiModelProperty(value = "예산편성 부서 삭제 목록")
    private List<MgtBudgetActDept> deleteMgtBudgetActDepts;
}
