package com.she.env.tms.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "TMS_측정소항목_할당량")
@Getter
@Setter
public class TmsStationItemQuota {

    @ApiModelProperty(value = "측정소항목코드")
    private String stationItemCode;

    @ApiModelProperty(value = "사업장연도")
    private String plantyear;

    @ApiModelProperty(value = "연도")
    private String year;

    @ApiModelProperty(value = "할당량_1월")
    private String quota01;

    @ApiModelProperty(value = "할당량_2월")
    private String quota02;

    @ApiModelProperty(value = "할당량_3월")
    private String quota03;

    @ApiModelProperty(value = "할당량_4월")
    private String quota04;

    @ApiModelProperty(value = "할당량_5월")
    private String quota05;

    @ApiModelProperty(value = "할당량_6월")
    private String quota06;

    @ApiModelProperty(value = "할당량_7월")
    private String quota07;

    @ApiModelProperty(value = "할당량_8월")
    private String quota08;

    @ApiModelProperty(value = "할당량_9월")
    private String quota09;

    @ApiModelProperty(value = "할당량_10월")
    private String quota10;

    @ApiModelProperty(value = "할당량_11월")
    private String quota11;

    @ApiModelProperty(value = "할당량_12월")
    private String quota12;

    @ApiModelProperty(value = "사업장코드")
    private String plantCd;

    @ApiModelProperty(value = "사업장명")
    private String plantNm;

    @ApiModelProperty(value = "배출구명")
    private String outletNm;

    @ApiModelProperty(value = "측정소명")
    private String stationName;

    @ApiModelProperty(value = "항목명")
    private String itemName;

    @ApiModelProperty(value = "생성자아이디")
    private String createUserId;

    @ApiModelProperty(value = "생성자명")
    private String createUserNm;

    @ApiModelProperty(value = "생성일")
    private String createDt;

    @ApiModelProperty(value = "수정자아이디")
    private String updateUserId;

    @ApiModelProperty(value = "수정자명")
    private String updateUserNm;

    @ApiModelProperty(value = "수정일")
    private String updateDt;

}
