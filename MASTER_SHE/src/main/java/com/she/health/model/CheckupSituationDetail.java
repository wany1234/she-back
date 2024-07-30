package com.she.health.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "검진현황대상")
@Data
public class CheckupSituationDetail {
    @ApiModelProperty(value = "건강검진계획번호")
    private int heaCheckupPlanNo;
    @ApiModelProperty(value = "검진년도")
    private String year;
    @ApiModelProperty(value = "사업장코드")
    private String plantCd;
    @ApiModelProperty(value = "사업장이름")
    private String plantNm;
    @ApiModelProperty(value = "사용자ID")
    private String userId;
    @ApiModelProperty(value = "사용자이름")
    private String userNm;
    @ApiModelProperty(value = "부서코드")
    private String deptCd;
    @ApiModelProperty(value = "부서이름")
    private String deptNm;
    @ApiModelProperty(value = "판정")
    private String heaDiagnoseCd;
    @ApiModelProperty(value = "검진종류")
    private String heaCheckupClassNm;
    @ApiModelProperty(value = "검진일")
    private String heaCheckedYmd;
    @ApiModelProperty(value = "검진이름")
    private String heaCheckupPlanNm;
}
