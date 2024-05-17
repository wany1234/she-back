package com.she.utils.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "메일전송결과")
@Data
public class MailResult {

    @ApiModelProperty(value = "결과메세지")
    private String resultMsg;

    @ApiModelProperty(value = "결과코드")
    private String resultCd;
}
