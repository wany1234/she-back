package com.she.safety.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "사고구분")
@Getter
@Setter
public class AccidentKind {
    @ApiModelProperty(value = "사내사고구분코드")
    private String accKindCd;
    @ApiModelProperty(value = "사내사고구분명")
    private String accKindNm;
    @ApiModelProperty(value = "사내사고번호")
    private int safAccidentNo;

}
