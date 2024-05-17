package com.she.env.gas.model;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "활동데이터보고결과")
@Data
public class ActDataResult {

    @ApiModelProperty(value = "활동데이터보고순번")
    private int actDaRptNo;

    @ApiModelProperty(value = "황동데이터보고결과순번")
    private int actDaRsltNo;

    @ApiModelProperty(value = "배출시설순번")
    private int ghgFcltNo;

    @ApiModelProperty(value = "활동자료코드")
    private String actDataCd;

    @ApiModelProperty(value = "배출시설산정방법순번")
    private int fcltCalcMtdNo;

    @ApiModelProperty(value = "배출활동코드")
    private String disActCd;

    @ApiModelProperty(value = "활동량")
    // private float actAmt;
    private BigDecimal actAmt;

    @ApiModelProperty(value = "CO2 배출량")
    private BigDecimal emsCo2;
    @ApiModelProperty(value = "CH4 배출량")
    private BigDecimal emsCh4;
    @ApiModelProperty(value = "N2O 배출량")
    private BigDecimal emsN2o;
    @ApiModelProperty(value = "HFCs 배출량")
    private BigDecimal emsHfcs;
    @ApiModelProperty(value = "PFCs 배출량")
    private BigDecimal emsPfcs;
    @ApiModelProperty(value = "SF6 배출량")
    private BigDecimal emsSf6;
    @ApiModelProperty(value = "에너지 사용량")
    private BigDecimal energyUsage;
    @ApiModelProperty(value = "전략 사용량")
    private BigDecimal elecUsage;
    @ApiModelProperty(value = "스팀 사용량")
    private BigDecimal steamUsage;

}
