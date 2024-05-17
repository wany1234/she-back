package com.she.safety.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "공사현황 작업구분")
@Getter
@Setter
public class ConstKind {
    @ApiModelProperty(value = "공사번호")
    private String constNo;
    @ApiModelProperty(value = "작업구분번호")
    private String wkodKindCd;
    @ApiModelProperty(value = "작업구분명")
    private String wkodKindNm;
}
