package com.she.env.tms.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "TMS 측정소")
@Getter
@Setter
public class TmsStation {

    @ApiModelProperty(value = "측정소코드(태그)저장된값")
    private String stationCodeOrign;

    @ApiModelProperty(value = "측정소코드(태그)")
    private String stationCode;

    @ApiModelProperty(value = "측정소명")
    private String stationName;

    @ApiModelProperty(value = "구분")
    private String tmsType;

    @ApiModelProperty(value = "구분명")
    private String tmsTypeNm;

    @ApiModelProperty(value = "설치년도")
    private String setupDay;

    @ApiModelProperty(value = "제작업체")
    private String comp;

    @ApiModelProperty(value = "모델번호")
    private String model;

    @ApiModelProperty(value = "정렬순서")
    private String dispOrder;

    @ApiModelProperty(value = "대기-배출시설번호")
    private int eairDischFacNo;

    @ApiModelProperty(value = "대기 배출구 번호")
    private int eairOutletNo;

    @ApiModelProperty(value = "배출구명")
    private String outletNm;

    @ApiModelProperty(value = "배출시설명")
    private String facilityNm;

    @ApiModelProperty(value = "사업장코드")
    private String plantCd;

    @ApiModelProperty(value = "사업장명")
    private String plantNm;

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

    @ApiModelProperty(value = "작성자")
    private String writerUserNm;

    @ApiModelProperty(value = "작성일")
    private String writerDt;

}
