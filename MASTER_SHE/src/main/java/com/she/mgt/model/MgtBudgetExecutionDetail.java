package com.she.mgt.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "예산집행")
@Data
public class MgtBudgetExecutionDetail {
	@ApiModelProperty(value = "예산집행번호")
    private int budgetExecNo;
	@ApiModelProperty(value = "예산편성부서번호")
	private int budgetActDeptCateNo;
	@ApiModelProperty(value = "집행월")
	private String execMm;
	@ApiModelProperty(value = "부서코드")
	private String deptCd;
	@ApiModelProperty(value = "집행금액")
	private String execAmt;
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