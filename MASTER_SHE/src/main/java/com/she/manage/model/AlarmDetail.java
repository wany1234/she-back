package com.she.manage.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "알람상세")
@Getter
@Setter
public class AlarmDetail {

    @ApiModelProperty(value = "알람코드")
    private int alarmCd;
    @ApiModelProperty(value = "사전/사후구분")
    private String dtlKind;
    @ApiModelProperty(value = "알람순서")
    private String dtlSort;
    @ApiModelProperty(value = "알람주기구분")
    private String dtlType;
    @ApiModelProperty(value = "알람주기일")
    private String dtlDate;
}
