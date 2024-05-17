package com.she.mgt.model;

import java.util.HashMap;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "중처법 대시보드")
@Data
public class SapaDashboard {

    @ApiModelProperty(value = "개선요청명")
    private String imprNm;
    @ApiModelProperty(value = "요청건수")
    private String total;
    @ApiModelProperty(value = "완료건수")
    private String completeCnt;
    @ApiModelProperty(value = "완료율")
    private String average;
    @ApiModelProperty(value = "편성액")
    private String orgAmt;
    @ApiModelProperty(value = "비용액")
    private String execAmt;
    @ApiModelProperty(value = "rowIndex")
    private String rowIndex;
    @ApiModelProperty(value = "예산 계정 구분")
    private String budgetTypeNm;
}
