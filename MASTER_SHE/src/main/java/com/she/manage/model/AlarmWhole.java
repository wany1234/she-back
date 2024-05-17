package com.she.manage.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "알람")
@Getter
@Setter
public class AlarmWhole {

    @ApiModelProperty(value = "알람코드")
    private Alarm alarm;
    private AlarmDetail[] alarmDetail;

}
