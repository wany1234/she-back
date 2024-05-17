package com.she.manage.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "다국어>라벨코드")
@Getter
@Setter
public class LblDtl {

    @ApiModelProperty(value = "라벨타입")
    private String mstCd;

    @ApiModelProperty(value = "라벨코드")
    private String lblCd;

    @ApiModelProperty(value = "라벨-한글")
    private String lblVal;

    @ApiModelProperty(value = "라벨-영어")
    private String lblValEn;

    @ApiModelProperty(value = "라벨-일본어")
    private String lblValJa;

    @ApiModelProperty(value = "라벨-중국어-간체")
    private String lblValZhCn;

    @ApiModelProperty(value = "라벨-중국어-번체")
    private String lblValZhTw;

    @ApiModelProperty(value = "라벨-변환 값")
    private String lblConversionVal;

    @ApiModelProperty(value = "삭제 여부")
    private String delYn;

    @ApiModelProperty(value = "등록자ID")
    private String createUserId;

    @ApiModelProperty(value = "등록일")
    private String createDt;

    @ApiModelProperty(value = "수정자ID")
    private String updateUserId;

    @ApiModelProperty(value = "수정일")
    private String updateDt;

}
