package com.she.manage.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "알람")
@Getter
@Setter
public class CrudLog {

    @ApiModelProperty(value = "로그번호")
    private int logNo;
    @ApiModelProperty(value = "유형코드")
    private String crudCd;
    @ApiModelProperty(value = "유형명")
    private String crudNm;
    @ApiModelProperty(value = "유형설명")
    private String crudDesc;
    @ApiModelProperty(value = "로그일")
    private String logDt;
    @ApiModelProperty(value = "사용자ID")
    private String userId;
    @ApiModelProperty(value = "사용자명")
    private String userNm;
    @ApiModelProperty(value = "url")
    private String url;
    @ApiModelProperty(value = "사업장명")
    private String plantNm;
    @ApiModelProperty(value = "부서명")
    private String deptNm;
    @ApiModelProperty(value = "액션옵션")
    private String crudOption;

}
