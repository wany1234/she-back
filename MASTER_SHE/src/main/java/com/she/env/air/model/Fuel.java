/**
 * Copyright (C) 2019, 2019 All Right Reserved, http://www.yullin.com/
 *
 * SHE Business can not be copied and/or distributed without the express
 * permission of Yullin Technologies
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 */
package com.she.env.air.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "대기 연료")
public class Fuel {
    @ApiModelProperty(value = "연료 코드")
    private String eairFuelCd;

    @ApiModelProperty(value = "연료명")
    private String eairFuelNm;

    @ApiModelProperty(value = "사업장코드")
    private String plantCd;

    @ApiModelProperty(value = "사업장")
    private String plantNm;

    @ApiModelProperty(value = "단위코드")
    private String envUnitCd;

    @ApiModelProperty(value = "단위")
    private String envUnitNm;

    @ApiModelProperty(value = "황함량(%)")
    private String sulfurContent;

    @ApiModelProperty(value = "발열량")
    private String calVal;

    @ApiModelProperty(value = "발열량단위")
    private String calValUnitCd;

    @ApiModelProperty(value = "발열량단위명")
    private String calValUnitNm;

    @ApiModelProperty(value = "SEMS코드")
    private String semsCd;

    @ApiModelProperty(value = "기타")
    private String remark;

    @ApiModelProperty(value = "정렬")
    private int sortOrder;

    @ApiModelProperty(value = "사용여부 코드")
    private String useYn;

    @ApiModelProperty(value = "사용여부")
    private String useYnNm;

    @ApiModelProperty(value = "등록자 아이디")
    private String createUserId;

    @ApiModelProperty(value = "등록자")
    private String createUserNm;

    @ApiModelProperty(value = "등록일자")
    private String createDt;

    @ApiModelProperty(value = "수정자 아이디")
    private String updateUserId;

    @ApiModelProperty(value = "수정자")
    private String updateUserNm;

    @ApiModelProperty(value = "수정일자")
    private String updateDt;

    @ApiModelProperty(value = "연료 코드")
    private String code;

    @ApiModelProperty(value = "연료명")
    private String name;

    @ApiModelProperty(value = "작성자")
    private String writerUserNm;

    @ApiModelProperty(value = "작성일")
    private String writerDt;
}
