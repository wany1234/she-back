package com.she.manage.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "알람")
@Getter
@Setter
public class ApprDelegate {

    @ApiModelProperty(value = "사용자")
    private String userId;

    @ApiModelProperty(value = "사용자명")
    private String delegatorNm;

    @ApiModelProperty(value = "위임자")
    private String delegatorId;

    @ApiModelProperty(value = "시작일")
    private String startYmd;

    @ApiModelProperty(value = "종료일")
    private String endYmd;
}
