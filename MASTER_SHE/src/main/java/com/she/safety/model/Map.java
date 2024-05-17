package com.she.safety.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "지도 관리")
@Getter
@Setter
public class Map {
    @ApiModelProperty(value = "지도ID")
    private int mapId;
    @ApiModelProperty(value = "지도명")
    private String mapName;
    @ApiModelProperty(value = "사업장코드")
    private String plantCd;
    @ApiModelProperty(value = "사업장명")
    private String plantNm;
    @ApiModelProperty(value = "이미지(base64)")
    private String imgBase64;
    @ApiModelProperty(value = "지도 설명")
    private String description;
    @ApiModelProperty(value = "사용여부")
    private String useYn;
    @ApiModelProperty(value = "메인노출여부")
    private String mainViewYn;
}
