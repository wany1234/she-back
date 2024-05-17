package com.she.mgt.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "법정선임자 평가대상자")
@Data
public class ElectEvalUser {
    @ApiModelProperty(value = "평가대상자번호")
    private int evalUserNo;

    @ApiModelProperty(value = "평가계획번호")
    private int evalPlanNo;

    @ApiModelProperty(value = "사업장명")
    private String plantNm;

    @ApiModelProperty(value = "부서명")
    private String deptNm;

    @ApiModelProperty(value = "직위명")
    private String positionNm;

    @ApiModelProperty(value = "본인평가자ID")
    private String meUserId;

    @ApiModelProperty(value = "본인평가자")
    private String meUserNm;

    @ApiModelProperty(value = "상위평가자ID")
    private String upUserId;

    @ApiModelProperty(value = "상위평가자")
    private String upUserNm;

    @ApiModelProperty(value = "본인평가 진행상태")
    private String meProcStep;

    @ApiModelProperty(value = "본인평가 진행상태")
    private String upProcStep;

    @ApiModelProperty(value = "비고")
    private String remark;
}
