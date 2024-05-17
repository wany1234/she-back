package com.she.chm.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "취급자재 규제현황 : 규제항목별 화학물질 개수")
@Data
public class ChemicalByRegulItmRes {

    @ApiModelProperty(value = "화학물질번호")
    private int chemNo;

    @ApiModelProperty(value = "규제항목번호")
    private int regulItmNo;

    @ApiModelProperty(value = "규제법규코드")
    private String regulLawCd;

    @ApiModelProperty(value = "규제법규명")
    private String regulLawNm;

    @ApiModelProperty(value = "규제항목명")
    private String regulItmNm;

    @ApiModelProperty(value = "CAS NO")
    private String casNo;

    @ApiModelProperty(value = "화학물질명(국문)")
    private String chemNmKr;

    @ApiModelProperty(value = "화학물질명(영문)")
    private String chemNmEn;

    @ApiModelProperty(value = "[server paging] 총 갯수")
    private int totalCnt;
}
