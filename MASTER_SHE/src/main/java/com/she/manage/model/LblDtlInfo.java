package com.she.manage.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "다국어>라벨코드")
@Getter
@Setter
public class LblDtlInfo {

    @ApiModelProperty(value = "라벨타입")
    private String mstCd;

    @ApiModelProperty(value = "라벨코드")
    private String lblCd;

    @ApiModelProperty(value = "라벨-변환 값")
    private String lblConversionVal;

}
