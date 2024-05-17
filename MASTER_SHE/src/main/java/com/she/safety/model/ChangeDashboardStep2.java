package com.she.safety.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "변경관리 dashboard 변경실시")
@Getter
@Setter
public class ChangeDashboardStep2 {
    @ApiModelProperty(value = "변경발의")
    private int safChngNo;
    @ApiModelProperty(value = "관련업무 목록")
    private List<ChangeCheckItemResult> changeCheckItemResults;
}
