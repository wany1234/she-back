package com.she.impr.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "개선조치")
@Getter
@Setter
public class ImprActDashboard {
    @ApiModelProperty(value = "개선조치번호")
    private int safImprActNo;
    @ApiModelProperty(value = "제목")
    private String imprTitle;
    @ApiModelProperty(value = "조치부서명")
    private String actDeptNm;
    @ApiModelProperty(value = "진행상태명")
    private String imprStepNm;
    @ApiModelProperty(value = "dashboard 신호등")
    private String checkStatus;
}
