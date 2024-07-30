package com.she.health.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "검진현황")
@Data
public class CheckupSituation {
    @ApiModelProperty(value = "건강검진계획번호")
    private int heaCheckupPlanNo;
    @ApiModelProperty(value = "검진년도")
    private String year;
    @ApiModelProperty(value = "사업장코드")
    private String plantCd;
    @ApiModelProperty(value = "사업장이름")
    private String plantNm;
    @ApiModelProperty(value = "A")
    private String classA;
    @ApiModelProperty(value = "B")
    private String classB;
    @ApiModelProperty(value = "C1")
    private String classC1;
    @ApiModelProperty(value = "C2")
    private String classC2;
    @ApiModelProperty(value = "CN")
    private String classCN;
    @ApiModelProperty(value = "D1")
    private String classD1;
    @ApiModelProperty(value = "D2")
    private String classD2;
    @ApiModelProperty(value = "DN")
    private String classDN;
    @ApiModelProperty(value = "R")
    private String classR;
    @ApiModelProperty(value = "U")
    private String classU;
    @ApiModelProperty(value = "합계")
    private String classSum;
}
