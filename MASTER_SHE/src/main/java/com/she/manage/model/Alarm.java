package com.she.manage.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "알람")
@Getter
@Setter
public class Alarm {

    @ApiModelProperty(value = "알람번호")
    private int alarmNo;

    @ApiModelProperty(value = "알람명")
    private String alarmNm;

    @ApiModelProperty(value = "SMS 전송여부")
    private String smsYn;

    @ApiModelProperty(value = "EMAIL 전송여부")
    private String emailYn;

    @ApiModelProperty(value = "SMS, EMAIL 전송여부명")
    private String smsEmailYnNm;

    @ApiModelProperty(value = "알람설명")
    private String alarmDesk;

    @ApiModelProperty(value = "사용유무")
    private String useYn;

    @ApiModelProperty(value = "사용유무명")
    private String useYnNm;

    @ApiModelProperty(value = "등록자ID")
    private String createUserId;

    @ApiModelProperty(value = "등록자명")
    private String createUserNm;

    @ApiModelProperty(value = "등록일")
    private String createDt;

    @ApiModelProperty(value = "수정자ID")
    private String updateUserId;

    @ApiModelProperty(value = "수정자명")
    private String updateUserNm;

    @ApiModelProperty(value = "수정일")
    private String updateDt;

}
