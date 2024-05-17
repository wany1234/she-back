package com.she.chm.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "취급자재 규제현황 : 규제항목별 화학물질 개수")
@Data
public class ChemprodByRegulItmRes {

    @ApiModelProperty(value = "취급자재번호")
    private int chemProdNo;

    @ApiModelProperty(value = "규제항목번호")
    private int regulItmNo;

    @ApiModelProperty(value = "규제법규코드")
    private String regulLawCd;

    @ApiModelProperty(value = "규제법규명")
    private String regulLawNm;

    @ApiModelProperty(value = "규제항목명")
    private String regulItmNm;

    @ApiModelProperty(value = "SAP자재코드")
    private String sapMatCd;

    @ApiModelProperty(value = "취급자재명(KOR)")
    private String chemProdNmKr;

    @ApiModelProperty(value = "취급자재명(ENG)")
    private String chemProdNmEn;

    @ApiModelProperty(value = "공급업체명")
    private String vendorNm;

    @ApiModelProperty(value = "공급업체국가명")
    private String originNm;

    @ApiModelProperty(value = "제조업체명")
    private String makerNm;

    @ApiModelProperty(value = "제조업체국가명")
    private String makerOriginNm;

    @ApiModelProperty(value = "[server paging] 총 갯수")
    private int totalCnt;

}
