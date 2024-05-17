package com.she.env.waste.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "폐기물 집계 (전체)")
@Getter
@Setter
public class WesteOperationStatus {

    @ApiModelProperty(value = "사업장")
    String plantNm;

    @ApiModelProperty(value = "폐기물분류")
    String ewstClassNm;

    @ApiModelProperty(value = "폐기물")
    String ewstWasteNm;

    @ApiModelProperty(value = "단위")
    String envUnitNm;

    @ApiModelProperty(value = "년")
    String year;

    @ApiModelProperty(value = "요청부서명")
    String deptNm;

    @ApiModelProperty(value = "부서코드")
    String deptCd;

    @ApiModelProperty(value = "1월")
    private String mm1;

    @ApiModelProperty(value = "2월")
    private String mm2;

    @ApiModelProperty(value = "3월")
    private String mm3;

    @ApiModelProperty(value = "4월")
    private String mm4;

    @ApiModelProperty(value = "5월")
    private String mm5;

    @ApiModelProperty(value = "6월")
    private String mm6;

    @ApiModelProperty(value = "7월")
    private String mm7;

    @ApiModelProperty(value = "8월")
    private String mm8;

    @ApiModelProperty(value = "9월")
    private String mm9;

    @ApiModelProperty(value = "10월")
    private String mm10;

    @ApiModelProperty(value = "11월")
    private String mm11;

    @ApiModelProperty(value = "12월")
    private String mm12;

    @ApiModelProperty(value = "합계")
    private String sum;

}
