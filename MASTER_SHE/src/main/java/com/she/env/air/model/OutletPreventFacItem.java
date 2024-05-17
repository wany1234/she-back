package com.she.env.air.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class OutletPreventFacItem {

    @ApiModelProperty(value = "배출구번호")
    private int eairOutletNo;
    @ApiModelProperty(value = "방지시설번호")
    private int eairPreventFacNo;
    @ApiModelProperty(value = "방지시설번호")
    private int code;
    @ApiModelProperty(value = "방지시설명")
    private String name;

}
