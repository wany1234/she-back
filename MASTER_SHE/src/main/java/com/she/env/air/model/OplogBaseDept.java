package com.she.env.air.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class OplogBaseDept {

    @ApiModelProperty(value = "운영일지 마스터 번호")
    private int eairOplogBaseMstNo;
    @ApiModelProperty(value = "작성부서")
    private String deptCd;
    @ApiModelProperty(value = "작성부서명")
    private String deptNm;

}
