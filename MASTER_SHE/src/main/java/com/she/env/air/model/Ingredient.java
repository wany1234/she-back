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
@ApiModel(description = "대기 원료")
public class Ingredient {

    @ApiModelProperty(value = "원료 코드")
    private String eairIngrCd;

    @ApiModelProperty(value = "원료명")
    private String eairIngrNm;

    @ApiModelProperty(value = "사업장 코드")
    private String plantCd;

    @ApiModelProperty(value = "사업장")
    private String plantNm;

    @ApiModelProperty(value = "단위코드")
    private String envUnitCd;

    @ApiModelProperty(value = "단위")
    private String envUnitNm;

    @ApiModelProperty(value = "비고")
    private String remark;

    @ApiModelProperty(value = "정렬")
    private int sortOrder;

    @ApiModelProperty(value = "사용여부Yn")
    private String useYn;

    @ApiModelProperty(value = "사용여부")
    private String useYnNm;

    @ApiModelProperty(value = "등록자 아이디")
    private String createUserId;

    @ApiModelProperty(value = "등록자")
    private String createUserNm;

    @ApiModelProperty(value = "등록일")
    private String createDt;

    @ApiModelProperty(value = "수정자 아이디")
    private String updateUserId;

    @ApiModelProperty(value = "수정자")
    private String updateUserNm;

    @ApiModelProperty(value = "수정일")
    private String updateDt;

    @ApiModelProperty(value = "작성자")
    private String writerUserNm;

    @ApiModelProperty(value = "작성일")
    private String writerDt;

}
