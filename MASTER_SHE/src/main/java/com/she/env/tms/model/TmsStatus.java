package com.she.env.tms.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "TMS 현황")
@Getter
@Setter
public class TmsStatus {

    @ApiModelProperty(value = "측정소항목코드")
    private String stationItemCode;

    @ApiModelProperty(value = "항목코드")
    private String itemCode;

    @ApiModelProperty(value = "항목명")
    private String itemName;

    @ApiModelProperty(value = "측정소코드(태그)")
    private String stationCode;

    @ApiModelProperty(value = "측정소명")
    private String stationName;

    @ApiModelProperty(value = "구분")
    private String tmsType;

    @ApiModelProperty(value = "구분명")
    private String tmsTypeNm;

    @ApiModelProperty(value = "대기배출시설명")
    private String facilityNm;

    @ApiModelProperty(value = "대기배출구명")
    private String outletNm;

    @ApiModelProperty(value = "사업장코드")
    private String plantCd;

    @ApiModelProperty(value = "사업장명")
    private String plantNm;

    @ApiModelProperty(value = "단위(측정항목의 단위가 아닌듯?)")
    private String sunit;

    @ApiModelProperty(value = "측정일시")
    private String dataDate;

    @ApiModelProperty(value = "측정값")
    private String dataValue;

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
