package com.she.mgt.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel(description = "예산편성")
@Data
public class MgtBudgetActItem {
    @ApiModelProperty(value = "예산편성번호")
    private int budgetActNo;
    @ApiModelProperty(value = "년도")
    private String year;
    @ApiModelProperty(value = "작성자")
    private String createUserId;
    @ApiModelProperty(value = "작성일")
    private String createDt;
    @ApiModelProperty(value = "수정자")
    private String updateUserId;
    
    @ApiModelProperty(value = "예산편성부서번호")
    private int budgetActDeptNo;
    @ApiModelProperty(value = "예산편성부서코드")
    private String deptCd;
    @ApiModelProperty(value = "비고")
    private String remark;
    
    @ApiModelProperty(value = "편성목록")
    private List<MgtBudgetActDeptCate> budgetActDeptCateList;
    
    @ApiModelProperty(value = "삭제편성목록")
    private List<MgtBudgetActDeptCate> deleteBudgetActDeptCateList;
}
