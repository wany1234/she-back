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

package com.she.health.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "상담별 약품 지급")
@Data
public class DrugSuply {

    @ApiModelProperty(value = "건강상담번호")
    private int heaConsultNo;

    @ApiModelProperty(value = "상담구분명")
    private int heaDrugNo;

    @ApiModelProperty(value = "사용량")
    private int amount;

    @ApiModelProperty(value = "약품명")
    private String heaDrugNm;

    @ApiModelProperty(value = "단위")
    private String unit;

    @ApiModelProperty(value = "등록자ID")
    private String createUserId;

    @ApiModelProperty(value = "수정자ID")
    private String updateUserId;

}
