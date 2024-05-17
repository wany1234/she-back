package com.she.env.waste.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "폐기물 분배율")
@Getter
@Setter
public class DisporateDeptItem {

    @ApiModelProperty(value = "분배율번호")
    private String ewstWasteRateNo;

    @ApiModelProperty(value = "부서명")
    private String deptNm;

    @ApiModelProperty(value = "부서코드")
    private String deptCd;

    @ApiModelProperty(value = "지정월")
    private String month;

    /* rate */
    @ApiModelProperty(value = "1월")
    private Float m01;

    @ApiModelProperty(value = "2월")
    private Float m02;

    @ApiModelProperty(value = "3월")
    private Float m03;

    @ApiModelProperty(value = "4월")
    private Float m04;

    @ApiModelProperty(value = "5월")
    private Float m05;

    @ApiModelProperty(value = "6월")
    private Float m06;

    @ApiModelProperty(value = "7월")
    private Float m07;

    @ApiModelProperty(value = "8월")
    private Float m08;

    @ApiModelProperty(value = "9월")
    private Float m09;

    @ApiModelProperty(value = "10월")
    private Float m10;

    @ApiModelProperty(value = "11월")
    private Float m11;

    @ApiModelProperty(value = "12월")
    private Float m12;

    @ApiModelProperty(value = "합계")
    private Float sum;

}
