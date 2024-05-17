package com.she.chm.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "취급자재 규제정보")
@Data
public class ChemicalRegulItmChemprodVal {
    @ApiModelProperty(value = "취급자재별 규제번호")
    private int regulItmChemprodValNo;

    @ApiModelProperty(value = "규제항목번호")
    private int regulItmNo;

    @ApiModelProperty(value = "규제항목명")
    private String regulItmNm;

    @ApiModelProperty(value = "규제법규코드")
    private String regulLawCd;

    @ApiModelProperty(value = "규제법규명")
    private String regulLawNm;

    @ApiModelProperty(value = "취급자재번호")
    private int chemProdNo;

    @ApiModelProperty(value = "작성자아이디")
    private String createUserId;

    @ApiModelProperty(value = "작성자명")
    private String createUserNm;

    @ApiModelProperty(value = "작성일")
    private String createDt;

    @ApiModelProperty(value = "수정자아이디")
    private String updateUserId;

    @ApiModelProperty(value = "수정자명")
    private String updateUserNm;

    @ApiModelProperty(value = "수정일")
    private String updateDt;

    @ApiModelProperty(value = "")
    private String hisYn;

    @ApiModelProperty(value = "")
    private String hisYnNm;

    @ApiModelProperty(value = "시작일")
    private String startDt;

    @ApiModelProperty(value = "마침일")
    private String endDt;

    @ApiModelProperty(value = "기간")
    private String period;

}
