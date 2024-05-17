package com.she.safety.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "변경관리구분내역")
@Getter
@Setter
public class ChangeType {
    @ApiModelProperty(value = "변경관리번호")
    private int safChngNo;
    @ApiModelProperty(value = "변경분야구분코드")
    private String chngTypeCd;
    @ApiModelProperty(value = "변경분야구분명")
    private String chngTypeNm;
    @ApiModelProperty(value = "변경분야유형코드")
    private String chngClsCd;
    @ApiModelProperty(value = "변경분야유형명")
    private String chngClsNm;
}
