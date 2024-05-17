package com.she.mgt.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@ApiModel(description = "예산편성및집행 현황")
@Data
public class MgtBudgetStat {
    @ApiModelProperty(value = "년도")
    private String year;

    @ApiModelProperty(value = "년도(편성관리 조회시 사용)")
    private String searchYear;

    @ApiModelProperty(value = "사업장코드")
    private String plantCd;

    @ApiModelProperty(value = "사업장")
    private String plantNm;

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

    @ApiModelProperty(value = "예산계정명")
    private String budgetActNm;

    @ApiModelProperty(value = "편성금액")
    private long orgAmt;

    @ApiModelProperty(value = "편성금액")
    private String orgAmtT;

    @ApiModelProperty(value = "집행금액")
    private long execAmt;

    @ApiModelProperty(value = "집행금액")
    private String execAmtT;

    @ApiModelProperty(value = "집행가능 금액")
    private String execRate;

    @ApiModelProperty(value = "월별 집행 금액(1월)")
    private String month1;

    @ApiModelProperty(value = "월별 집행 금액(2월)")
    private String month2;

    @ApiModelProperty(value = "월별 집행 금액(3월)")
    private String month3;

    @ApiModelProperty(value = "월별 집행 금액(4월)")
    private String month4;

    @ApiModelProperty(value = "월별 집행 금액(5월)")
    private String month5;

    @ApiModelProperty(value = "월별 집행 금액(6월)")
    private String month6;

    @ApiModelProperty(value = "월별 집행 금액(7월)")
    private String month7;

    @ApiModelProperty(value = "월별 집행 금액(8월)")
    private String month8;

    @ApiModelProperty(value = "월별 집행 금액(9월)")
    private String month9;

    @ApiModelProperty(value = "월별 집행 금액(10월)")
    private String month10;

    @ApiModelProperty(value = "월별 집행 금액(11월)")
    private String month11;

    @ApiModelProperty(value = "월별 집행 금액(12월)")
    private String month12;


}