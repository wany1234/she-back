package com.she.safety.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "정부지자체 시정조치 이행점검 내부 점검자")
public class GovImplChkInRater {
    @ApiModelProperty(value = "정부지자체 시정조치 이행점검 내부점검자번호")
    private int implChkInRaterNo;

    @ApiModelProperty(value = "정부지자체 시정조치 이행점검번호")
    private int implChkPlanNo;

    @ApiModelProperty(value = "부서코드")
    private String deptCd;

    @ApiModelProperty(value = "부서명")
    private String deptNm;

    @ApiModelProperty(value = "직위코드")
    private String positionCd;

    @ApiModelProperty(value = "직위명")
    private String positionNm;

    @ApiModelProperty(value = "점검자ID")
    private String userId;

    @ApiModelProperty(value = "점검자명")
    private String userNm;

    @ApiModelProperty(value = "비고")
    private String raterDesc;
}
