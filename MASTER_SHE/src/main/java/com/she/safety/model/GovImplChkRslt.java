package com.she.safety.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(description = "정부지자체 시정조치 이행점검 결과")
public class GovImplChkRslt {
    @ApiModelProperty(value = "정부지자체 시정조치 이행점검 결과번호")
    private int implChkRsltNo;

    @ApiModelProperty(value = "정부지자체 시정조치 이행점검번호")
    private int implChkPlanNo;

    @ApiModelProperty(value = "개선도출건수")
    private int imprCaseCnt;

    @ApiModelProperty(value = "점검 완료일")
    private String chkDt;

    @ApiModelProperty(value = "점검결과 요약")
    private String rsltDesc;
}
