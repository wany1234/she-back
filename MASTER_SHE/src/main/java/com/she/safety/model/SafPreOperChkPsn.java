package com.she.safety.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "가동전점검 점검자")
@Data
public class SafPreOperChkPsn {

    @ApiModelProperty(value = "점검자번호")
    private int safPreOperationChkPsnNo;

    @ApiModelProperty(value = "가동전점검 마스터 번호")
    private int safFacilChkNo;

    @ApiModelProperty(value = "C점검자구분코드")
    private String inspectorClassCd;

    @ApiModelProperty(value = "사용자ID")
    private String userId;

    @ApiModelProperty(value = "부서코드")
    private String deptCd;

    @ApiModelProperty(value = "성명")
    private String userNm;

    @ApiModelProperty(value = "소속")
    private String deptNm;

    @ApiModelProperty(value = "직책")
    private String dutyNm;

    @ApiModelProperty(value = "점검예정시")
    private String checkSchHour;

    @ApiModelProperty(value = "점검예정분")
    private String checkSchMinute;

    @ApiModelProperty(value = "점검예정종료시")
    private String checkSchEhour;

    @ApiModelProperty(value = "점검예정종료분")
    private String checkSchEminute;

    @ApiModelProperty(value = "점검시")
    private String checkHour;

    @ApiModelProperty(value = "점검분")
    private String checkMinute;

    @ApiModelProperty(value = "점검종료시")
    private String checkEhour;

    @ApiModelProperty(value = "점검종료분")
    private String checkEminute;

    @ApiModelProperty(value = "결과비고")
    private String remarkRslt;
}
