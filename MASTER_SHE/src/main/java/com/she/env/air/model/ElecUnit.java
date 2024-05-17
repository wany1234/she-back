package com.she.env.air.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "대기 약품")
@Getter
@Setter
public class ElecUnit {

    @ApiModelProperty(value = "단가번호")
    private int costNo;
    @ApiModelProperty(value = "사업장코드")
    private String plantCd;
    @ApiModelProperty(value = "사업장명")
    private String plantNm;
    @ApiModelProperty(value = "연도")
    private String year;
    @ApiModelProperty(value = "1월단가")
    private Float m01Cost;
    @ApiModelProperty(value = "2월단가")
    private Float m02Cost;
    @ApiModelProperty(value = "3월단가")
    private Float m03Cost;
    @ApiModelProperty(value = "4월단가")
    private Float m04Cost;
    @ApiModelProperty(value = "5월단가")
    private Float m05Cost;
    @ApiModelProperty(value = "6월단가")
    private Float m06Cost;
    @ApiModelProperty(value = "7월단가")
    private Float m07Cost;
    @ApiModelProperty(value = "8월단가")
    private Float m08Cost;
    @ApiModelProperty(value = "9월단가")
    private Float m09Cost;
    @ApiModelProperty(value = "10월단가")
    private Float m10Cost;
    @ApiModelProperty(value = "11월단가")
    private Float m11Cost;
    @ApiModelProperty(value = "12월단가")
    private Float m12Cost;
    @ApiModelProperty(value = "등록자")
    private String createUserId;
    @ApiModelProperty(value = "등록일자")
    private String createDt;
    @ApiModelProperty(value = "수정자")
    private String updateUserId;
    @ApiModelProperty(value = "수정일자")
    private String updateDt;

}
