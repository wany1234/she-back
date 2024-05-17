package com.she.mgt.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(description = "법정선임자평가 본인평가/상위평가")
public class ElectEvalRsltStatus {
    @ApiModelProperty(value = "대상년도")
    private String year;

    @ApiModelProperty(value = "사업장코드")
    private String plantCd;

    @ApiModelProperty(value = "사업장")
    private String plantNm;

    @ApiModelProperty(value = "구분(상반기/하반기)")
    private String halfTypeCd;

    @ApiModelProperty(value = "구분(상반기/하반기)")
    private String halfTypeNm;

    @ApiModelProperty(value = "법정선임자번호")
    private int safElectTitlNo;

    @ApiModelProperty(value = "법정선임자")
    private String electTitlNm;

    @ApiModelProperty(value = "대상자(명)")
    private int tgtCnt;

    @ApiModelProperty(value = "대상자(명)")
    private String tgtCntT;

    @ApiModelProperty(value = "평가완료(명)")
    private int complateCnt;

    @ApiModelProperty(value = "평가완료(명)")
    private String completeCntT;

    @ApiModelProperty(value = "평가실시율(%)")
    private String evalRate;
}
