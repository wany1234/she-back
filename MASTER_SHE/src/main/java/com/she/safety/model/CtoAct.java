package com.she.safety.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "핵심행동")
@Getter
@Setter
public class CtoAct {
    @ApiModelProperty(value = "핵심행동번호")
    private int safCtoActNo;
    @ApiModelProperty(value = "cto번호")
    private int safCtoNo;
    @ApiModelProperty(value = "핵심행동")
    private String mainAct;
    @ApiModelProperty(value = "양호불량여부")
    private String selCd;
    @ApiModelProperty(value = "양호불량여부명")
    private String selNm;
    @ApiModelProperty(value = "비고")
    private String remarks;
}
