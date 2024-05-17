package com.she.common.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "기본 param")
@Data
public class DefaultParam {

    @ApiModelProperty(value = "언어 정보")
    public String lang;

    public DefaultParam(String lang) {
        super();
        this.lang = lang;
    }

}