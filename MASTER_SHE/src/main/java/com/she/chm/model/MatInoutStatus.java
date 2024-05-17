package com.she.chm.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "취급자재 실적")
@Getter
@Setter
public class MatInoutStatus {

    @ApiModelProperty(value = "년월")
    private String yearmon;

    @ApiModelProperty(value = "플랜트코드(사업장코드)")
    private String plantCd;

    @ApiModelProperty(value = "플랜트코드(사업장명)")
    private String plantNm;

    @ApiModelProperty(value = "자재코드")
    private String sapMatCd;

    @ApiModelProperty(value = "자재코드")
    private String matCode;

    @ApiModelProperty(value = "자재명")
    private String matName;

    @ApiModelProperty(value = "취급자재번호")
    private int chemProdNo;

    @ApiModelProperty(value = "취급자재명")
    private String chemProdNmKr;

    @ApiModelProperty(value = "취급자재명")
    private String chemProdNmEn;

    @ApiModelProperty(value = "화학물질명")
    private String chemNmKr;

    @ApiModelProperty(value = "화학물질명")
    private String chemNmEn;

    @ApiModelProperty(value = "CAS NO.")
    private String casNo;

    @ApiModelProperty(value = "성상")
    private String propertyNm;

    @ApiModelProperty(value = "저장위치")
    private String matStrgCd;

    @ApiModelProperty(value = "저장위치명")
    private String matStrgNm;

    @ApiModelProperty(value = "입고량")
    private String inAmt;

    @ApiModelProperty(value = "출고량")
    private String outAmt;

    @ApiModelProperty(value = "출고량[조사대상물질]")
    private String outAmtReal;

    @ApiModelProperty(value = "재고량")
    private String stocAmt;

    @ApiModelProperty(value = "입고이월량")
    private String stocAmtLastYear;

    @ApiModelProperty(value = "단위")
    private String unit;

    @ApiModelProperty(value = "등록자")
    private String createUserId;

    @ApiModelProperty(value = "등록일")
    private String createDt;

    @ApiModelProperty(value = "수정자")
    private String updateUserId;

    @ApiModelProperty(value = "수정일")
    private String updateDt;

    @ApiModelProperty(value = "하한")
    private String limitLow;

    @ApiModelProperty(value = "상한")
    private String limitHigh;

    @ApiModelProperty(value = "대표값")
    private String limitRepval;

    @ApiModelProperty(value = "제조업체코드")
    private String makerCd;

    @ApiModelProperty(value = "제조업체명")
    private String makerNm;

    @ApiModelProperty(value = "납품업체코드")
    private String vendorCd;

    @ApiModelProperty(value = "납품업체명")
    private String vendorNm;

    @ApiModelProperty(value = "비율")
    private String rate;

    @ApiModelProperty(value = "비중")
    private String specificGravity;

    @ApiModelProperty(value = "혼합물여부")
    private String mixYn;

    @ApiModelProperty(value = "유해화학물질 해당여부")
    private String hazardChem;

    @ApiModelProperty(value = "제조업체에 따른 취급자재 재고량")
    private String chemProdStocAmt;

    @ApiModelProperty(value = "제조업체에 따른 취급자재 재고량 환산값")
    private String chemProdSpGraStocAmt;

    @ApiModelProperty(value = "위험물질번호")
    private int chemprodRegulItmNo;

    @ApiModelProperty(value = "위험물질명")
    private String regulItmNm;

    @ApiModelProperty(value = "조사대상범위[무게함유율(%)]")
    private String stndVal;

    @ApiModelProperty(value = "용도")
    private String usageNm;

    @ApiModelProperty(value = "excel count index용도")
    private int indexCount;

    @ApiModelProperty(value = "merge용")
    private String plantRequl;

}
