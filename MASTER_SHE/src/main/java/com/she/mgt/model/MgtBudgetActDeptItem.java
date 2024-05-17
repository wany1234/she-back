package com.she.mgt.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "예산편성")
@Data
public class MgtBudgetActDeptItem {
    @ApiModelProperty(value = "예산계정편성부서항목번호")
    private int budgetActDeptItemNo;

    @ApiModelProperty(value = "예산계정편성부서번호")
    private int budgetActDeptNo;

    @ApiModelProperty(value = "예산계정번호")
    private int budgetActMstNo;

    @ApiModelProperty(value = "예산계정명")
    private String budgetActNm;

    @ApiModelProperty(value = "예산분류코드")
    private String budgetTypeCd;

    @ApiModelProperty(value = "예산분류명")
    private String budgetTypeNm;

    @ApiModelProperty(value = "예산구분코드")
    private String budgetClsCd;

    @ApiModelProperty(value = "예산구분명")
    private String budgetClsNm;

    @ApiModelProperty(value = "편성금액")
    private long orgAmt;

    @ApiModelProperty(value = "예산집행여부")
    private String execYn;

    @ApiModelProperty(value = "예산집행 번호")
    private int budgetExecNo;

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
}