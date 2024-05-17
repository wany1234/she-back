package com.she.health.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "근골격계 기본조사 조사결과")
@Data
public class MuscSurveyRslt {

    @ApiModelProperty(value = "근골격계 기본조사 작업분류표번호")
    private int heaMuscSurveyChklistNo;

    @ApiModelProperty(value = "근골격계 유해인자 항번호코드")
    private String clsCd;

    @ApiModelProperty(value = "근골격계 유해인자 항번호명")
    private String clsNm;

    @ApiModelProperty(value = "유해요인에 대한 원인")
    private String cause;

    @ApiModelProperty(value = "비고")
    private String remarks;
}
