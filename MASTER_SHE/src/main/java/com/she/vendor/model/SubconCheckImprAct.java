package com.she.vendor.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "협력업제자체점검 개선사항")
@Data
public class SubconCheckImprAct {

    @ApiModelProperty(value = "개선조치No")
    private int safImprActNo;

    @ApiModelProperty(value = "조치처리구분코드")
    private String actClassCd;

    @ApiModelProperty(value = "조치처리구분명")
    private String actClassNm;

    @ApiModelProperty(value = "개선요청내용")
    private String imprRqstContents;

    @ApiModelProperty(value = "조치결과내용")
    private String actResultContents;

    @ApiModelProperty(value = "조치업체코드")
    private String vendorCd;

    @ApiModelProperty(value = "조치업체명")
    private String vendorNm;

    @ApiModelProperty(value = "조치기한")
    private String actLimitYmd;

    @ApiModelProperty(value = "조치일")
    private String actConfirmYmd;
}
