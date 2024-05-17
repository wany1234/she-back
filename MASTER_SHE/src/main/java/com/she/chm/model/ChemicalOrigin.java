package com.she.chm.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "원산지")
@Getter
@Setter
public class ChemicalOrigin {
    @ApiModelProperty(value = "원산지코드")
    private String originCd;

    @ApiModelProperty(value = "원산지명(영문)")
    private String originNmEn;

    @ApiModelProperty(value = "원산지명(국문)")
    private String originNmKr;

    @ApiModelProperty(value = "EU여부")
    private String euYn;

    @ApiModelProperty(value = "EU여부명")
    private String euYnNm;

    @ApiModelProperty(value = "사용여부")
    private String useYn;

    @ApiModelProperty(value = "사용여부명")
    private String useYnNm;

    @ApiModelProperty(value = "정렬순서")
    private int sortOrder;

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

}
