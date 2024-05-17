package com.she.safety.model;

import java.util.List;

import com.she.impr.model.ImprActDashboard;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "변경관리 dashboard 사후처리")
@Getter
@Setter
public class ChangeDashboardStep3 {
    @ApiModelProperty(value = "변경관리 개선사항")
    private List<ImprActDashboard> imprs;
}
