package com.she.utils.model;

import java.util.List;

import com.she.manage.model.MailLog;

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

    @ApiModelProperty(value = "메일발송결과로그")
    private List<MailLog> mailLogs;

}
