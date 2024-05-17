package com.she.chm.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "규제DB업로드 화학물질 기준 유효성결과")
@Getter
@Setter
public class ChemicalRegulDbValid {

    @ApiModelProperty(value = "규제DB화학물질고유번호")
    private String chemId;

    @ApiModelProperty(value = "화학물질명국문")
    private String chemNmKr;

    @ApiModelProperty(value = "화학물질명영문")
    private String chemNmEn;

    @ApiModelProperty(value = "법규명")
    private String lawNm;

    @ApiModelProperty(value = "규제항목명")
    private String regulItmNm;

    @ApiModelProperty(value = "유효성검사결과유형")
    private String actType;

    @ApiModelProperty(value = "유효성검사결과")
    private String actTypeDesc;

    @ApiModelProperty(value = "화학물질번호")
    private String chemNo;

    @ApiModelProperty(value = "규제항목번호")
    private String regulItmNo;

    @ApiModelProperty(value = "법규코드")
    private String regulLawCd;


    // 제품부분
    @ApiModelProperty(value = "취급자재번호")
    private String chemProdNo;

    @ApiModelProperty(value = "취급자재명(국문)")
    private String chemProdNmKr;

    @ApiModelProperty(value = "취급자재명(영문)")
    private String chemProdNmEn;

    @ApiModelProperty(value = "SAP자재코드 ")
    private String sapMatCd;

}
