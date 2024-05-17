package com.she.env.air.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "운영일지 작성구분 마스터")
public class OplogBaseMst {

    @ApiModelProperty(value = "운영일지 마스터 번호")
    private int eairOplogBaseMstNo;
    @ApiModelProperty(value = "사업장")
    private String plantCd;
    @ApiModelProperty(value = "관리부서")
    private String mgDeptCd;
    @ApiModelProperty(value = "등록자")
    private String createUserId;
    @ApiModelProperty(value = "등록일")
    private String createDt;
    @ApiModelProperty(value = "수정일")
    private String updateUserId;
    @ApiModelProperty(value = "수정자")
    private String updateDt;
    @ApiModelProperty(value = "운영일지-작성부서 ")
    private List<OplogBaseDept> oplogBaseDept;
    @ApiModelProperty(value = "운영일지 기본정보")
    private List<OplogBase> oplogBase;

}
