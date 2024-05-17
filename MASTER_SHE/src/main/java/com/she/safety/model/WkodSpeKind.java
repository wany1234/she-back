package com.she.safety.model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "안전작업허가서_특별작업구분")
@Getter
@Setter
public class WkodSpeKind {
    @ApiModelProperty(value = "작업허가서번호")
    private int wkodNo;
    @ApiModelProperty(value = "특별작업구분코드")
    private String wkodSpeKindCd;
    @ApiModelProperty(value = "특별작업구분명")
    private String wkodSpeKindNm;
    @ApiModelProperty(value = "특별작업구분선택유무")
    private String wkodSpeKindCk;
}
