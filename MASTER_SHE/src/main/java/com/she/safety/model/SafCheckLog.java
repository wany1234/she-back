package com.she.safety.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "점검이력")
@Getter
@Setter
public class SafCheckLog {

    @ApiModelProperty(value = "점검이력번호")
    private int checkLogNo;

    @ApiModelProperty(value = "기계기구번호")
    private int checkMachineNo;

    @ApiModelProperty(value = "점검목록번호")
    private int checkListNo;

    @ApiModelProperty(value = "ITEM NO")
    private String itemNo;

    @ApiModelProperty(value = "검사기간시작일")
    private String inspStartDt;

    @ApiModelProperty(value = "검사기간종료일")
    private String inspEndDt;

    @ApiModelProperty(value = "검사일자")
    private String inspDt;

    @ApiModelProperty(value = "이력비고")
    private String remark;

    @ApiModelProperty(value = "등록자")
    private String createUserId;

    @ApiModelProperty(value = "등록일")
    private String createDt;

    @ApiModelProperty(value = "수정자")
    private String updateUserId;

    @ApiModelProperty(value = "수정일")
    private String updateDt;

    @ApiModelProperty(value = "점검(구분)명")
    private String checkNm;
}
