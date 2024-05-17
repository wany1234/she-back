package com.she.manage.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "SMS로그")
@Data
public class SmsLog {
    @ApiModelProperty(value = "발송로그no")
    private String logNo;
    @ApiModelProperty(value = "발신자 id")
    private String sndPhnId;
    @ApiModelProperty(value = "수신자 번호")
    private String rcvPhnId;
    @ApiModelProperty(value = "발신자 번호")
    private String callback;
    @ApiModelProperty(value = "메시지 내용")
    private String sndMsg;
    @ApiModelProperty(value = "메시지 상태")
    private String smsSt;
    @ApiModelProperty(value = "전송결과")
    private int rsltVal;
    @ApiModelProperty(value = "발신자사번")
    private String rsrvdId;
    @ApiModelProperty(value = "발신부서명")
    private String rsrvdWd;
    @ApiModelProperty(value = "발신부서코드")
    private String sndDept;
    @ApiModelProperty(value = "발신자이름")
    private String snderNm;
    @ApiModelProperty(value = "수신자이름")
    private String rcvNm;
    @ApiModelProperty(value = "발송일시")
    private String sendDt;
    @ApiModelProperty(value = "발송여부")
    private String sendYn;
    @ApiModelProperty(value = "등록자")
    private String createUserId;
    @ApiModelProperty(value = "등록일시")
    private String createDt;
}
