package com.she.mgt.baseInfo.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(description = "예산항목")
public class MgtMgBudget {
	
	@ApiModelProperty(value = "예산항목")
	private MgtMgBudgetItem mgtMgBudgetItem;

    @ApiModelProperty(value = "예산항목 상세목록")
    private List<MgtMgBudgetDetailItem> mgtMgBudgetDetailItem;

    @ApiModelProperty(value = "예산항목 삭제목록")
    private List<MgtMgBudgetDetailItem> deleteMgtMgBudgetDetailItem;
}
