package com.she.env.air.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class OutletPreventSerial {

    @ApiModelProperty(value = "배출구번호")
    private int eairOutletNo;

    @ApiModelProperty(value = "방지시설번호")
    private int eairPreventFacNo;

    @ApiModelProperty(value = "방지시설명")
    private String eairPreventFacNm;

    @ApiModelProperty(value = "방지시설번호(직렬)")
    private int eairSerialPreventFacNo;

    @ApiModelProperty(value = "방지시설번호명(직렬)")
    private String eairSerialPreventFacNm;

    @ApiModelProperty(value = "순차")
    private int serial;

}
