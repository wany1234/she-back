package com.she.utils.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "SMS")
@Data
public class SmsVo {
    @ApiModelProperty(value = "보내는사람이름")
    private String sndr;

    @ApiModelProperty(value = "보내는 사람 전화번호")
    private String callback;

    @ApiModelProperty(value = "받는사람이름")
    private String rcvr;

    @ApiModelProperty(value = "받는 사람 전화번호")
    private String rcvrnum;

    @ApiModelProperty(value = "내용")
    private String msg;
}
