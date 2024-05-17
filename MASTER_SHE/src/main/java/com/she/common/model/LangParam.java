package com.she.common.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "언어정보 param")
@Data
public class LangParam {

    @ApiModelProperty(value = "라벨 정보")
    private String lang;

    @ApiModelProperty(value = "구분(로그인 정보인지 아닌지)")
    private String divide;

}