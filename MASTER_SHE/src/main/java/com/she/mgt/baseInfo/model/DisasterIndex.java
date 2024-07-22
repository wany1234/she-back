package com.she.mgt.baseInfo.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "종합재해지수 기준정보")
@Data
public class DisasterIndex {
    @ApiModelProperty(value = "대상년도")
    private String year;
    @ApiModelProperty(value = "사업장코드")
    private String plantCd;
    @ApiModelProperty(value = "사업장이름")
    private String plantNm;
    @ApiModelProperty(value = "종합재해지수 목표")
    private String compDisaIndex;
    @ApiModelProperty(value = "재해율 목표")
    private String acciRateTarget;
    @ApiModelProperty(value = "근로시간(사무)")
    private String workingHoursOffi;
    @ApiModelProperty(value = "근로시간(제조)")
    private String workingHoursManu;
    @ApiModelProperty(value = "등록자ID")
    private String createUserId;
    @ApiModelProperty(value = "등록일")
    private String createDt;
    @ApiModelProperty(value = "수정자ID")
    private String updateUserId;
    @ApiModelProperty(value = "수정일")
    private String updateDt;
}
