package com.she.mgt.baseInfo.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(description = "예산항목")
public class MgtMgBudgetItem {
	
	@ApiModelProperty(value = "예산항목코드")    
    private String budgetCateCd;
	@ApiModelProperty(value = "예산항목이름")
    private String budgetCateNm;
	@ApiModelProperty(value = "예산상세항목코드")
	private String budgetCateDtlCd;
	@ApiModelProperty(value = "예산상세항목이름")
	private String budgetCateDtlNm;
	@ApiModelProperty(value = "정렬순서")
    private String sortOrder;
	@ApiModelProperty(value = "사용여부")
    private String useYn;
	@ApiModelProperty(value = "비고")
	private String remark;
	@ApiModelProperty(value = "등록자ID")
    private String createUserId;
	@ApiModelProperty(value = "등록자ID")
    private String createDt;
	@ApiModelProperty(value = "수정자ID")
    private String updateUserId;

}
