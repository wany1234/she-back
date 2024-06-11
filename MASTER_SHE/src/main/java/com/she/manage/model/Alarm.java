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
    private String mailYn;

    @ApiModelProperty(value = "EMAIL 전송여부명")
    private String mailYnNm;

    @ApiModelProperty(value = "알람설명")
    private String alarmDesk;

    @ApiModelProperty(value = "알람내용")
    private String alarmText;

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

    @ApiModelProperty(value = "알람타입코드")
    private String alarmType;

    @ApiModelProperty(value = "알람타입")
    private String alarmTypeNm;

    @ApiModelProperty(value = "알람코드")
    private String alarmCd;

    @ApiModelProperty(value = "메일링크URL")
    private String mailUrl;

    @ApiModelProperty(value = "메일양식URL")
    private String templateUrl;

}
