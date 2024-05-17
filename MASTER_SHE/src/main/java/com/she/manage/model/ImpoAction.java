package com.she.manage.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "알람")
@Getter
@Setter
public class ImpoAction {

    @ApiModelProperty(value = "경로")
    private String servletPath;
    @ApiModelProperty(value = "유형코드")
    private String crudCd;
    @ApiModelProperty(value = "유형명")
    private String crudNm;
    @ApiModelProperty(value = "유형설명")
    private String crudDesc;
    @ApiModelProperty(value = "사용유무")
    private String useYn;

}
