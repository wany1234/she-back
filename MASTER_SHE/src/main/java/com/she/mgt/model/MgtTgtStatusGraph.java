package com.she.mgt.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "제조공정별 목표달성 그래프")
@Getter
@Setter
public class MgtTgtStatusGraph {
    @ApiModelProperty(value = "대상연도")
    private String year;
    @ApiModelProperty(value = "사업장코드")
    private String plantCd;
    @ApiModelProperty(value = "공정코드")
    private String processCd;
    @ApiModelProperty(value = "분야코드")
    private String bizFieldCd;
    @ApiModelProperty(value = "항목번호")
    private String bizFieldItemNo;
    @ApiModelProperty(value = "항목명")
    private String bizFieldItemNm;
    @ApiModelProperty(value = "목표값")
    private String targetVal;
    @ApiModelProperty(value = "실적값")
    private String rsltVal;
    @ApiModelProperty(value = "평가값")
    private String evalVal;
}
