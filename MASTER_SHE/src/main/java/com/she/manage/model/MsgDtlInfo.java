package com.she.manage.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "다국어>메시지코드")
@Getter
@Setter
public class MsgDtlInfo {

    @ApiModelProperty(value = "메시지타입")
    private String mstCd;

    @ApiModelProperty(value = "메시지코드")
    private String msgCd;

    @ApiModelProperty(value = "메시지-변환 값")
    private String msgConversionVal;

}
