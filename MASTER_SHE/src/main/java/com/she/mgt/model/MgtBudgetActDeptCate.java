package com.she.mgt.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel(description = "예산편성")
@Data
public class MgtBudgetActDeptCate {
    
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
    
}
