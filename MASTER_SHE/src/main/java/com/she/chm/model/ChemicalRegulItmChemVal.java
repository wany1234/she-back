package com.she.chm.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "화학물질별규제정보")
@Data
public class ChemicalRegulItmChemVal {

    @ApiModelProperty(value = "화학물질별규제정보번호")
    private int regulItmChemValNo;

    @ApiModelProperty(value = "규제항목번호")
    private int regulItmNo;

    @ApiModelProperty(value = "규제항목명")
    private String regulItmNm;

    @ApiModelProperty(value = "규제법규코드")
    private String regulLawCd;

    @ApiModelProperty(value = "규제법규명")
    private String regulLawNm;

    @ApiModelProperty(value = "화학물질번호")
    private int chemNo;

    @ApiModelProperty(value = "기준값")
    private double stndVal;

    @ApiModelProperty(value = "기준단위번호")
    private int unitNo;

    @ApiModelProperty(value = "기준단위명")
    private String unitNm;

    @ApiModelProperty(value = "부등호코드")
    private String stndSign;

    @ApiModelProperty(value = "부등호명")
    private String stndSignNm;

    @ApiModelProperty(value = "적용시작일")
    private String startDt;

    @ApiModelProperty(value = "적용마침일")
    private String endDt;

    @ApiModelProperty(value = "적용기간")
    private String period;

    @ApiModelProperty(value = "생성자아이디")
    private String createUserId;

    @ApiModelProperty(value = "생성자")
    private String createUserNm;

    @ApiModelProperty(value = "생성일")
    private String createDt;

    @ApiModelProperty(value = "수정자아이디")
    private String updateUserId;

    @ApiModelProperty(value = "수정자")
    private String updateUserNm;

    @ApiModelProperty(value = "수정일")
    private String updateDt;

    @ApiModelProperty(value = "")
    private String hisYn;

    @ApiModelProperty(value = "")
    private String hisYnNm;
}
