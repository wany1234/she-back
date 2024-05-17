package com.she.baseInfo.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "시설유형")
@Getter
@Setter
public class SafFacilityType {

    @ApiModelProperty(value = "시설유형코드")
    private String comFacilityTypeCd;

    @ApiModelProperty(value = "시설유형명")
    private String comFacilityTypeNm;

    @ApiModelProperty(value = "사용여부")
    private String useYn;

    @ApiModelProperty(value = "사용여부명")
    private String useYnNm;

    @ApiModelProperty(value = "정렬순서")
    private int sortOrder;

    @ApiModelProperty(value = "PSM여부")
    private String psmYn;

    @ApiModelProperty(value = "PSM여부명")
    private String psmYnNm;
}
