package com.she.safety.model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "안전작업허가서_작업구분")
@Getter
@Setter
public class WkodKind {
    @ApiModelProperty(value = "작업허가서번호")
    private int wkodNo;
    @ApiModelProperty(value = "작업구분번호")
    private String wkodKindCd;
    @ApiModelProperty(value = "작업구분명")
    private String wkodKindNm;
    @ApiModelProperty(value = "작업구분선택유무")
    private String wkodKindCk;
}
