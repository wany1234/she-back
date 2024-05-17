package com.she.env.tms.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "TMS_5분측정")
@Getter
@Setter
public class Tms5 {

    @ApiModelProperty(value = "측정소코드")
    private String stationCode;

    @ApiModelProperty(value = "항목코드")
    private String itemCode;

    @ApiModelProperty(value = "측정항목코드")
    private String stationItemCode;

    @ApiModelProperty(value = "연도")
    private String dataYear;

    @ApiModelProperty(value = "월")
    private String dataMonth;

    @ApiModelProperty(value = "일")
    private String dataDay;

    @ApiModelProperty(value = "시간")
    private String dataTime;

    @ApiModelProperty(value = "측정일시")
    private String dataDate;

    @ApiModelProperty(value = "측정값")
    private String dataValue;

    @ApiModelProperty(value = "측정상태")
    private String analyState;

    @ApiModelProperty(value = "수집비고")
    private String dataLog;

    @ApiModelProperty(value = "등록일")
    private String createDt;

    @ApiModelProperty(value = "수정일")
    private String updateDt;

}
