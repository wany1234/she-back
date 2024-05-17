package com.she.safety.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "정부지자체 시정조치 이행점검 내부 점검자")
public class GovImplChkExRater {
    @ApiModelProperty(value = "정부지자체 시정조치 이행점검 외부점검자번호")
    private int implChkExRaterNo;

    @ApiModelProperty(value = "정부지자체 시정조치 이행점검번호")
    private int implChkPlanNo;

    @ApiModelProperty(value = "소속")
    private String companyNm;

    @ApiModelProperty(value = "직위명")
    private String positionNm;

    @ApiModelProperty(value = "성명")
    private String userNm;

    @ApiModelProperty(value = "비고")
    private String raterDesc;
}
