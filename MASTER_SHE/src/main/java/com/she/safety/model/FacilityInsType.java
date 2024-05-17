package com.she.safety.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "시설유형")
@Getter
@Setter
public class FacilityInsType {
    @ApiModelProperty(value = "기존시설유형코드")
    private String facilityTypeOriCd;
    @ApiModelProperty(value = "시설유형코드")
    private String facilityTypeCd;
    @ApiModelProperty(value = "시설유형명")
    private String facilityTypeNm;
    @ApiModelProperty(value = "PSM해당여부")
    private String psmYn;
    @ApiModelProperty(value = "PSM해당여부명")
    private String psmYnNm;
    @ApiModelProperty(value = "사용여부")
    private String useYn;
    @ApiModelProperty(value = "사용여부명")
    private String useYnNm;
    @ApiModelProperty(value = "정렬순서")
    private String sortOrder;
}
