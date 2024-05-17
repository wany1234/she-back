package com.she.chm.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "자재검토 규제DB검증 결과")
@Data
public class MatCheckRequestDbVerf {
    @ApiModelProperty(value = "구분(reguldb:규제DB, rqstregul:자재검토)")
    private String gubun;

    @ApiModelProperty(value = "구분명(reguldb:규제DB, rqstregul:자재검토)")
    private String gubunNm;

    @ApiModelProperty(value = "규제항목번호")
    private int regulItemNo;

    @ApiModelProperty(value = "규제항목코드")
    private String regulItemCd;

    @ApiModelProperty(value = "규제항목명")
    private String regulItemNm;

    @ApiModelProperty(value = "법규코드")
    private String regulLawCd;

    @ApiModelProperty(value = "법규명")
    private String regulLawNm;

    @ApiModelProperty(value = "법규규제항목명")
    private String lawItemNm;

    @ApiModelProperty(value = "화학물질명국문")
    private String chemNmKr;

    @ApiModelProperty(value = "CAS NO.")
    private String casNo;

    @ApiModelProperty(value = "함유량대표값")
    private String rpval;

    @ApiModelProperty(value = "단위")
    private String unitNm;

    @ApiModelProperty(value = "기준")
    private String stndVal;

    @ApiModelProperty(value = "결과")
    private String chkVal;

    @ApiModelProperty(value = "해당여부")
    private String chkValNm;

    @ApiModelProperty(value = "규제항목번호")
    private String regulItmNo;

    @ApiModelProperty(value = "화학물질번호")
    private String chemNo;

}
