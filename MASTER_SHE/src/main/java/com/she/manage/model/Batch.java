package com.she.manage.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "배치서비스목록")
@Getter
@Setter
public class Batch {

    @ApiModelProperty(value = "배치코드")
    private String batchCd;
    @ApiModelProperty(value = "배치명")
    private String batchNm;
    @ApiModelProperty(value = "배치설명")
    private String batchDesc;
    @ApiModelProperty(value = "주기-시작일")
    private String cycleStartYmd;
    @ApiModelProperty(value = "주기-일 구분")
    private String cycleDayCd;
    @ApiModelProperty(value = "주기-일")
    private String cycleDay;
    @ApiModelProperty(value = "주기-일 명")
    private String cycleDayNm;
    @ApiModelProperty(value = "주기-시간/분 구분")
    private String cycleHmCd;
    @ApiModelProperty(value = "주기-시간/분")
    private String cycleHm;
    @ApiModelProperty(value = "주기-시간/분 명")
    private String cycleHmNm;
    @ApiModelProperty(value = "사용여부")
    private String useYn;
    @ApiModelProperty(value = "사용여부명")
    private String useYnNm;
    @ApiModelProperty(value = "등록자")
    private String createUserId;
    @ApiModelProperty(value = "등록일자")
    private String createDt;
    @ApiModelProperty(value = "수정자")
    private String updateUserId;
    @ApiModelProperty(value = "수정일자")
    private String updateDt;
    @ApiModelProperty(value = "알람번호")
    private String alarmNo;
    @ApiModelProperty(value = "알람명")
    private String alarmNm;
    @ApiModelProperty(value = "배치기준일수")
    private String batchDay;

}
