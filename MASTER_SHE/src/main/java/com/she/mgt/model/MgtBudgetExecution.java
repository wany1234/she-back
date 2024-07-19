package com.she.mgt.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "예산집행")
@Data
public class MgtBudgetExecution {
	@ApiModelProperty(value = "예산편성번호")
    private int budgetActNo;
    @ApiModelProperty(value = "예산편성부서번호")
    private int budgetActDeptNo;
    @ApiModelProperty(value = "예산편성항목번호")
    private int budgetActDeptCateNo;
    @ApiModelProperty(value = "년도")
    private String year;
    @ApiModelProperty(value = "예산편성부서코드")
    private String deptCd;
    @ApiModelProperty(value = "예산편성부서이름")
    private String deptNm;
    @ApiModelProperty(value = "항목코드")
    private String budgetCateCd;
    @ApiModelProperty(value = "항목명")
    private String budgetCateNm;
    @ApiModelProperty(value = "상세항목코드")
    private String budgetCateDtlCd;
    @ApiModelProperty(value = "상세항목명")
    private String budgetCateDtlNm;
    @ApiModelProperty(value = "편성금액")
    private String orgAmt;
    @ApiModelProperty(value = "비고")
    private String remark;
    @ApiModelProperty(value = "작성자")
    private String createUserId;
    @ApiModelProperty(value = "작성일")
    private String createDt;
    @ApiModelProperty(value = "수정자")
    private String updateUserId;
    @ApiModelProperty(value = "수정일")
    private String updateDt;
    @ApiModelProperty(value = "1월 집행번호")
    private String b1ExecNo;
    @ApiModelProperty(value = "1월 집행비용")
    private String b1ExecAmt;
    @ApiModelProperty(value = "1월")
    private String b1ExecMm;
    @ApiModelProperty(value = "2월 집행번호")
    private String b2ExecNo;
    @ApiModelProperty(value = "2월 집행비용")
    private String b2ExecAmt;
    @ApiModelProperty(value = "2월")
    private String b2ExecMm;
    @ApiModelProperty(value = "3월 집행번호")
    private String b3ExecNo;
    @ApiModelProperty(value = "3월 집행비용")
    private String b3ExecAmt;
    @ApiModelProperty(value = "3월")
    private String b3ExecMm;
    @ApiModelProperty(value = "4월 집행번호")
    private String b4ExecNo;
    @ApiModelProperty(value = "4월 집행비용")
    private String b4ExecAmt;
    @ApiModelProperty(value = "4월")
    private String b4ExecMm;
    @ApiModelProperty(value = "5월 집행번호")
    private String b5ExecNo;
    @ApiModelProperty(value = "5월 집행비용")
    private String b5ExecAmt;
    @ApiModelProperty(value = "5월")
    private String b5ExecMm;
    @ApiModelProperty(value = "6월 집행번호")
    private String b6ExecNo;
    @ApiModelProperty(value = "6월 집행비용")
    private String b6ExecAmt;
    @ApiModelProperty(value = "6월")
    private String b6ExecMm;
    @ApiModelProperty(value = "7월 집행번호")
    private String b7ExecNo;
    @ApiModelProperty(value = "7월 집행비용")
    private String b7ExecAmt;
    @ApiModelProperty(value = "7월")
    private String b7ExecMm;
    @ApiModelProperty(value = "8월 집행번호")
    private String b8ExecNo;
    @ApiModelProperty(value = "8월 집행비용")
    private String b8ExecAmt;
    @ApiModelProperty(value = "8월")
    private String b8ExecMm;
    @ApiModelProperty(value = "9월 집행번호")
    private String b9ExecNo;
    @ApiModelProperty(value = "9월 집행비용")
    private String b9ExecAmt;
    @ApiModelProperty(value = "9월")
    private String b9ExecMm;
    @ApiModelProperty(value = "10월 집행번호")
    private String b10ExecNo;
    @ApiModelProperty(value = "10월 집행비용")
    private String b10ExecAmt;
    @ApiModelProperty(value = "10월")
    private String b10ExecMm;
    @ApiModelProperty(value = "11월 집행번호")
    private String b11ExecNo;
    @ApiModelProperty(value = "11월 집행비용")
    private String b11ExecAmt;
    @ApiModelProperty(value = "11월")
    private String b11ExecMm;
    @ApiModelProperty(value = "12월 집행번호")
    private String b12ExecNo;
    @ApiModelProperty(value = "12월 집행비용")
    private String b12ExecAmt;
    @ApiModelProperty(value = "12월")
    private String b12ExecMm;
    @ApiModelProperty(value = "집행비용 합계")
    private String execAmtSum;
    @ApiModelProperty(value = "집행비용 비율")
    private String ratio;
}