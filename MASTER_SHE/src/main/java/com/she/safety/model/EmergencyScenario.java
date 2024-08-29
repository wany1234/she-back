package com.she.safety.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "훈련계획관리시나리오")
@Data
public class EmergencyScenario {

    @ApiModelProperty(value = "훈련시나리오번호")
    private int trainSceNo;
    @ApiModelProperty(value = "훈련계획번호")
    private int safTrainPlanNo;
    @ApiModelProperty(value = "제목")
    private String title;
}
