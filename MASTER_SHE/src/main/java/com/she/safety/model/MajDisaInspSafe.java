package com.she.safety.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MajDisaInspSafe {

    @ApiModelProperty(value = "중대시민재해점검 안전계획번호")
    private int majDisaInspSafeNo;
    @ApiModelProperty(value = "중대시민재해점검 우려사항조치번호")
    private int majDisaInspActNo;
    @ApiModelProperty(value = "중대시민재해점검 법정점검계획번호")
    private int majDisaInspRawNo;
    @ApiModelProperty(value = "중대시민재해점검번호")
    private int majDisaInspNo;
    @ApiModelProperty(value = "계획수립대상(개소)")
    private double planTargetVal;
    @ApiModelProperty(value = "계획수립(개소)")
    private double planVal;
    @ApiModelProperty(value = "계획수립율(%)")
    private double planRate;
    @ApiModelProperty(value = "안전계획 비고")
    private String safeDesc;
    @ApiModelProperty(value = "점검실시(개소)")
    private double chkVal;
    @ApiModelProperty(value = "점검실시율(%)")
    private double chkRate;
    @ApiModelProperty(value = "법정계획 비고")
    private String rawDesc;
    @ApiModelProperty(value = "우려상황 조치(건수)")
    private double conActVal;
    @ApiModelProperty(value = "시민재해 조치(건수)")
    private double civilDisActVal;
    @ApiModelProperty(value = "시민재해 개선(건수)")
    private double civilDisImprVal;
    @ApiModelProperty(value = "우려사항 조치 비고")
    private String actDesc;

}
