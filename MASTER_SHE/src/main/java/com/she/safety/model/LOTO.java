package com.she.safety.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "LOTO")
@Getter
@Setter
public class LOTO {
    @ApiModelProperty(value = "공사번호")
    private String constNo;
    @ApiModelProperty(value = "작업허가서번호")
    private int wkodNo;
    @ApiModelProperty(value = "설비코드")
    private String safFacilityCd;
    @ApiModelProperty(value = "설비명")
    private String facilityNm;
    @ApiModelProperty(value = "잠금위치")
    private String lockLocate;
    @ApiModelProperty(value = "설비유형코드")
    private String safFacilityTypeCd;
    @ApiModelProperty(value = "설비유형명")
    private String safFacilityTypeNm;
}
