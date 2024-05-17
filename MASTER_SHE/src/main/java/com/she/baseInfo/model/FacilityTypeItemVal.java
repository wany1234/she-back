package com.she.baseInfo.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "설비관리항목값")
@Getter
@Setter
public class FacilityTypeItemVal {
    @ApiModelProperty(value = "설비코드")
    private String safFacilityCd;
    @ApiModelProperty(value = "설비유형관리항목번호")
    private int safFacilityTypeItemNo;
    @ApiModelProperty(value = "관리항목결과값")
    private String itemValue;
    @ApiModelProperty(value = "비고")
    private String remark;
}
