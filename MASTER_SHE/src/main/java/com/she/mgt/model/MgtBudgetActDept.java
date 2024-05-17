package com.she.mgt.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel(description = "예산편성")
@Data
public class MgtBudgetActDept {
    @ApiModelProperty(value = "예산편성부서번호")
    private int budgetActDeptNo;

    @ApiModelProperty(value = "예산편성번호")
    private int budgetActNo;

    @ApiModelProperty(value = "년도")
    private String year;

    @ApiModelProperty(value = "사업장코드")
    private String plantCd;

    @ApiModelProperty(value = "사업장")
    private String plantNm;

    @ApiModelProperty(value = "부서코드")
    private String deptCd;

    @ApiModelProperty(value = "부서명")
    private String deptNm;

    @ApiModelProperty(value = "단계 MGT_BUDGET_STEP")
    private String procStepCd;

    @ApiModelProperty(value = "상태 COM_STATE")
    private String stateCd;

    @ApiModelProperty(value = "결재상태")
    private String stepCd;

    @ApiModelProperty(value = "결재요청번호")
    private int apprRqstNo;

    @ApiModelProperty(value = "비고")
    private String remark;

    @ApiModelProperty(value = "대상부서별 예산계정 목록")
    private List<MgtBudgetActDeptItem> mgtBudgetActDeptItems;

    @ApiModelProperty(value = "대상부서별 예산계정 삭제 목록")
    private List<MgtBudgetActDeptItem> deleteMgtBudgetActDeptItems;
}
