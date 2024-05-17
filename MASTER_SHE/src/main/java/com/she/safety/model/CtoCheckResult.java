package com.she.safety.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "CTO체크리스트")
@Getter
@Setter
public class CtoCheckResult {
    @ApiModelProperty(value = "cto번호")
    private int safCtoNo;
    @ApiModelProperty(value = "항목코드")
    private String ctoActCd;
    @ApiModelProperty(value = "항목명")
    private String ctoActNm;
    @ApiModelProperty(value = "선택값")
    private String selCd;
    @ApiModelProperty(value = "선택값명")
    private String selNm;
    @ApiModelProperty(value = "내용설명")
    private String remarks;
}
