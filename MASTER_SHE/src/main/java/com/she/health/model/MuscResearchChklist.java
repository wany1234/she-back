package com.she.health.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

// SK E&S
@ApiModel(description = "근골격계 질환조사 정보")
@Data
public class MuscResearchChklist {

    @ApiModelProperty(value = "부담작업번호")
    private int muscResearchChklistNo;

    @ApiModelProperty(value = "단위작업번호")
    private int muscResearchUnitNo;

    @ApiModelProperty(value = "구분명")
    private String chklistNm;

    @ApiModelProperty(value = "구분설명(유해요인)")
    private String chklistDesc;

    @ApiModelProperty(value = "노출시간")
    private int exposureTime;

    @ApiModelProperty(value = "노출시간설명")
    private String exposureTimeDesc;

    @ApiModelProperty(value = "노출빈도")
    private String exopsureCnt;

    @ApiModelProperty(value = "노출빈도설명")
    private String exposureCntDesc;

    @ApiModelProperty(value = "총노출시간")
    private int totExposureTime;

    @ApiModelProperty(value = "신체부위")
    private String bodyDesc;

    @ApiModelProperty(value = "작업자세및내용")
    private String positionDesc;

    @ApiModelProperty(value = "무게")
    private String weightDesc;

    @ApiModelProperty(value = "등록일")
    private String createUserId;

    @ApiModelProperty(value = "등록자")
    private String createDt;

    @ApiModelProperty(value = "수정자")
    private String updateUserId;

    @ApiModelProperty(value = "수정일")
    private String updateDt;

}
