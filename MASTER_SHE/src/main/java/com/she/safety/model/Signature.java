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

package com.she.safety.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@ApiModel(description = "서명")
@Getter
@Setter
public class Signature {
    @ApiModelProperty(value = "서명번호")
    private int signNo;

    @ApiModelProperty(value = "작업허가서번호")
    private int wkodNo;

    @ApiModelProperty(value = "서명 구분")
    private String signType;

    @ApiModelProperty(value = "서명여부")
    private String confirmYn;

    @ApiModelProperty(value = "등록자")
    private String createUserId;

    @ApiModelProperty(value = "등록일시")
    private String createDt;

    @ApiModelProperty(value = "수정자")
    private String updateUserId;

    @ApiModelProperty(value = "수정일시")
    private String updateDt;
}