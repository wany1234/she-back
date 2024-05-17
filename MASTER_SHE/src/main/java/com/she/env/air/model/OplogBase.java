package com.she.env.air.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class OplogBase {

    @ApiModelProperty(value = "운영일지번호")
    private int eairOplogBaseMstNo;

    @ApiModelProperty(value = "배출구번호")
    private int eairOutletNo;
    @ApiModelProperty(value = "배출구명")
    private String mainDischFacNm;
    @ApiModelProperty(value = "배출구일련번호")
    private String eairOutletNm;
    @ApiModelProperty(value = "배출구허가증상번호")
    private String eairOutletPermitNo;
    @ApiModelProperty(value = "방지시설번호")
    private int eairPreventFacNo;
    @ApiModelProperty(value = "방지시설명")
    private String eairPreventFacNm;
    @ApiModelProperty(value = "방지시설 순차")
    private int Serial;
    @ApiModelProperty(value = "방지시설번호(직렬)")
    private int eairSerialPreventFacNo;
    @ApiModelProperty(value = "방지시설명(직렬)")
    private String eairSerialPreventFacNm;
    @ApiModelProperty(value = "배출시설번호")
    private int eairDischFacNo;
    @ApiModelProperty(value = "배출시설명")
    private String eairDischFacNm;
    @ApiModelProperty(value = "대표배출시설")
    private String oplogDischYn;
    @ApiModelProperty(value = "방지시설 운전여부")
    private String oplogPreventYn;
}
