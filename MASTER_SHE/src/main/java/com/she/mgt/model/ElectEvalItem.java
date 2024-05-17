package com.she.mgt.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "법정선임자 평가항목")
@Data
public class ElectEvalItem {
    @ApiModelProperty(value = "평가항목번호") private int evalItemNo;

    @ApiModelProperty(value = "평가계획번호")
    private int evalPlanNo;

    @ApiModelProperty(value = "구분")
    private String itemType;

    @ApiModelProperty(value = "평가대상업무")
    private String itemWork;

    @ApiModelProperty(value = "비고")
    private String remark;
}
