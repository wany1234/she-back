package com.she.common.model;

import java.util.List;

import com.she.manage.model.LblDtlInfo;
import com.she.manage.model.MsgDtlInfo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "언어정보(메세지, 라벨)")
@Data
public class Lang {

    @ApiModelProperty(value = "라벨 정보")
    private List<LblDtlInfo> label;

    @ApiModelProperty(value = "메시지 정보")
    private List<MsgDtlInfo> message;

}