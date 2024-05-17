package com.she.safety.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "근본원인")
@Getter
@Setter
public class CtoActCause {
    @ApiModelProperty(value = "cto번호")
    private int safCtoNo;
    @ApiModelProperty(value = "항목분류코드")
    private String attCd;
    @ApiModelProperty(value = "항목분류명")
    private String attNm;
    @ApiModelProperty(value = "항목코드")
    private String itmCd;
    @ApiModelProperty(value = "항목명")
    private String itmNm;
}
