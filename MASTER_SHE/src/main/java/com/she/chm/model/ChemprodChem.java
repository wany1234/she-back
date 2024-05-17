package com.she.chm.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "취급자재별 구성성분(화학물질)정보")
@Data
public class ChemprodChem {
    @ApiModelProperty(value = "취급자재번호")
    private int chemProdNo;

    @ApiModelProperty(value = "화학물질번호")
    private int chemNo;

    @ApiModelProperty(value = "CAS NO")
    private String casNo;

    @ApiModelProperty(value = "화학물질명(국문)")
    private String chemNmKr;

    @ApiModelProperty(value = "화학물질명(영문)")
    private String chemNmEn;

    @ApiModelProperty(value = "사용여부")
    private String useYn;

    @ApiModelProperty(value = "사용여부명")
    private String useYnNm;

    @ApiModelProperty(value = "함유량하한(%)")
    private String limitLow;

    @ApiModelProperty(value = "함유량상한(%)")
    private String limitHigh;

    @ApiModelProperty(value = "함유량대표값(%)")
    private String limitRepval;

    @ApiModelProperty(value = "비고")
    private String chemContent;

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

    @ApiModelProperty(value = "수정일")
    private String companySecret;

    @ApiModelProperty(value = "화학물질별 규제정보")
    private List<ChemicalRegulItmChemVal> chemicalRegulItmChemVals;

}
