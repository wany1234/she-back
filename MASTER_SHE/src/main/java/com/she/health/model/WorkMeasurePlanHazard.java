package com.she.health.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "작업환경측정계획유해인자")
public class WorkMeasurePlanHazard {

    @ApiModelProperty(value = "작업환경측정계획범위번호")
    private int workMeasPlanTargetNo;

    @ApiModelProperty(value = "유해인자코드")
    private String hazardCd;

    @ApiModelProperty(value = "유해인자명")
    private String hazardNmKo;

    public WorkMeasurePlanHazard() {
    }

    public int getWorkMeasPlanTargetNo() {
        return workMeasPlanTargetNo;
    }

    public void setWorkMeasPlanTargetNo(int workMeasPlanTargetNo) {
        this.workMeasPlanTargetNo = workMeasPlanTargetNo;
    }

    public String getHazardCd() {
        return hazardCd;
    }

    public void setHazardCd(String hazardCd) {
        this.hazardCd = hazardCd;
    }

    public String getHazardNmKo() {
        return hazardNmKo;
    }

    public void setHazardNmKo(String hazardNmKo) {
        this.hazardNmKo = hazardNmKo;
    }
}
