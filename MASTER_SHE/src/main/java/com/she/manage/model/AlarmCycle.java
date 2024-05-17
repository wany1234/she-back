package com.she.manage.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "알람주기")
@Getter
@Setter
public class AlarmCycle {

    @ApiModelProperty(value = "알람주기번호")
    private int alarmCycleNo;

    @ApiModelProperty(value = "주기명")
    private String alarmCycleNm;

    @ApiModelProperty(value = "주기코드")
    private String alarmCycleCd;

    @ApiModelProperty(value = "주기코드 점검알람주기")
    private String safAlarmCycleCd;

    @ApiModelProperty(value = "(C)주기유형")
    private String alarmCycleType;

    @ApiModelProperty(value = "(C)주기유형명")
    private String alarmCycleTypeName;

    @ApiModelProperty(value = "주기일")
    private int cycleCnt;

    @ApiModelProperty(value = "주기설명")
    private String cycleDesc;

    @ApiModelProperty(value = "사용여부")
    private String useYn;

    @ApiModelProperty(value = "등록자")
    private String createUserId;

    @ApiModelProperty(value = "등록자명")
    private String createUserNm;

    @ApiModelProperty(value = "등록일")
    private String createDt;

    @ApiModelProperty(value = "수정자")
    private String updateUserId;

    @ApiModelProperty(value = "수정자명")
    private String updateUserNm;

    @ApiModelProperty(value = "수정일")
    private String updateDt;

    @ApiModelProperty(value = "기계기구번호")
    private int checkMachineNo;

    @ApiModelProperty(value = "점검목록번호")
    private int checkListNo;

}
