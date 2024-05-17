package com.she.manage.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "다국어>라벨타입")
@Getter
@Setter
public class LblMst {

    @ApiModelProperty(value = "라벨타입")
    private String mstCd;

    @ApiModelProperty(value = "라벨타입 명")
    private String mstNm;

    @ApiModelProperty(value = "삭제 여부")
    private String delYn;

    @ApiModelProperty(value = "등록자ID")
    private String createUserId;

    @ApiModelProperty(value = "등록일")
    private String createDt;

    @ApiModelProperty(value = "수정자ID")
    private String updateUserId;

    @ApiModelProperty(value = "수정일")
    private String updateDt;
}
