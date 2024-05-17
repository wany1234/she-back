package com.she.env.tms.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "TMS 측정항목")
@Getter
@Setter
public class TmsItem {

    @ApiModelProperty(value = "항목코드저장된값")
    private String itemCodeOrign;

    @ApiModelProperty(value = "항목코드")
    private String itemCode;

    @ApiModelProperty(value = "항목명")
    private String itemName;

    @ApiModelProperty(value = "구분")
    private String tmsType;

    @ApiModelProperty(value = "구분명")
    private String tmsTypeNm;

    @ApiModelProperty(value = "단위")
    private String unit;

    @ApiModelProperty(value = "변환팩터")
    private String convFactor;

    @ApiModelProperty(value = "정렬순서")
    private String dispOrder;

    @ApiModelProperty(value = "생성자아이디")
    private String createUserId;

    @ApiModelProperty(value = "생성자명")
    private String createUserNm;

    @ApiModelProperty(value = "생성일")
    private String createDt;

    @ApiModelProperty(value = "수정자아이디")
    private String updateUserId;

    @ApiModelProperty(value = "수정자명")
    private String updateUserNm;

    @ApiModelProperty(value = "수정일")
    private String updateDt;

    @ApiModelProperty(value = "작성자")
    private String writerUserNm;

    @ApiModelProperty(value = "작성일")
    private String writerDt;

}
