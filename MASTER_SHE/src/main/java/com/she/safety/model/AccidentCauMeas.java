package com.she.safety.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "원인및대책")
@Getter
@Setter
public class AccidentCauMeas {
    @ApiModelProperty(value = "사내사고번호")
    private int safAccidentCauMeasNo;
    @ApiModelProperty(value = "사내사고번호")
    private int safAccidentNo;
    @ApiModelProperty(value = "사내사고번호")
    private int safImprActNo;
    @ApiModelProperty(value = "근본원인")
    private String rootCause;
    @ApiModelProperty(value = "개선대책")
    private String imprMeasure;
    @ApiModelProperty(value = "개선요청문구")
    private String improvement;
    @ApiModelProperty(value = "즉시조치문구")
    private String request;
    @ApiModelProperty(value = "재발방지대책코드")
    private String actClsCd;
    @ApiModelProperty(value = "재발방지대책명")
    private String actClsNm;

}
