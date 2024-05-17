package com.she.env.air.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PreventDischFacItem {

    @ApiModelProperty(value = "배출시설번호")
    public int eairDischFacNo;
    @ApiModelProperty(value = "방지시설번호")
    public int eairPreventFacNo;
    @ApiModelProperty(value = "배출시설번호")
    public int code;
    @ApiModelProperty(value = "배출시설명")
    public String name;
}
