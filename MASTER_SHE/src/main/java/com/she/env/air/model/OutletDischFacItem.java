package com.she.env.air.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class OutletDischFacItem {

    @ApiModelProperty(value = "배출구번호")
    private int eairOutletNo;
    @ApiModelProperty(value = "배출시설번호")
    private int eairDischFacNo;
    @ApiModelProperty(value = "배출시설번호번호")
    private int code;
    @ApiModelProperty(value = "배출시설명")
    private String name;
}
