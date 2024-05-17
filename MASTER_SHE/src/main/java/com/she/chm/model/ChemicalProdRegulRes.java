package com.she.chm.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "취급자재 규제현황")
@Data
public class ChemicalProdRegulRes {


    @ApiModelProperty(value = "규제기관")
    private String regulOrgNm;

    @ApiModelProperty(value = "규제항목번호")
    private int regulItmNo;

    @ApiModelProperty(value = "규제항목명")
    private String regulItmNm;

    @ApiModelProperty(value = "규제법규코드")
    private String regulLawCd;

    @ApiModelProperty(value = "규제법규명")
    private String regulLawNm;

    @ApiModelProperty(value = "화학물질 개수")
    private int chmCnt;

    @ApiModelProperty(value = "화학물질이 포함된 취급자재 개수")
    private int chemProdCnt;
}
