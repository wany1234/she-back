package com.she.safety.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "변경관리 dashboard")
@Getter
@Setter
public class ChangeDashboard {
    @ApiModelProperty(value = "변경발의")
    private ChangeDashboardStep1 CGSP1;
    @ApiModelProperty(value = "검토")
    private ChangeDashboardStepA CGSPA;
    @ApiModelProperty(value = "변경실시")
    private ChangeDashboardStep2 CGSP2;
    @ApiModelProperty(value = "사후처리")
    private ChangeDashboardStep3 CGSP3;
    @ApiModelProperty(value = "변경관리진행단계")
    private String chngStepCd;
}
