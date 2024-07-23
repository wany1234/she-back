package com.she.safety.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "종합재해지수 현황")
@Data
public class DisasterIndexStatus {

    @ApiModelProperty(value = "년도")
    private String year;
    @ApiModelProperty(value = "사업장코드")
    private String plantCd;
    @ApiModelProperty(value = "사업장이름")
    private String plantNm;
    @ApiModelProperty(value = "대상월")
    private String month;
    @ApiModelProperty(value = "총인원수")
    private String totalCount ;
    @ApiModelProperty(value = "사무직인원수")
    private String offiCount ;
    @ApiModelProperty(value = "제조직인원수")
    private String manuCount ;
    @ApiModelProperty(value = "총근로시간")
    private String totalWorkingHours;
    @ApiModelProperty(value = "재해건수")
    private String accidentCnt;
    @ApiModelProperty(value = "손실일수")
    private String workLostDay;
    @ApiModelProperty(value = "빈도율")
    private String freqRate;
    @ApiModelProperty(value = "강도율")
    private String intensRate;
    @ApiModelProperty(value = "빈도*강도")
    private String frxin;
    @ApiModelProperty(value = "종합재해목표")
    private String compDisaIndex ;
    @ApiModelProperty(value = "종합재해실적")
    private String compPerformRate;
    @ApiModelProperty(value = "목표비")
    private String compDisaRatio;
    @ApiModelProperty(value = "재해율목표")
    private String acciRateTarget ;
    @ApiModelProperty(value = "재해율실적")
    private String acciPerfomRate;
    @ApiModelProperty(value = "목표비")
    private String acciRatio;

}
