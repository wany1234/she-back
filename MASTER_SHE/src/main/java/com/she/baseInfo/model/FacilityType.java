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

package com.she.baseInfo.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "설비유형")
@Getter
@Setter
public class FacilityType {

    @ApiModelProperty(value = "상위설비유형코드")
    private String psafFacilityTypeCd;
    @ApiModelProperty(value = "상위설비유형명")
    private String psafFacilityTypeNm;
    @ApiModelProperty(value = "설비유형코드-모바일")
    private String id;
    @ApiModelProperty(value = "설비유형코드")
    private String safFacilityTypeCd;
    @ApiModelProperty(value = "설비유형명-모바일")
    private String label;
    @ApiModelProperty(value = "설비유형명")
    private String safFacilityTypeNm;
    @ApiModelProperty(value = "출력순서")
    private int sortOrder;
    @ApiModelProperty(value = "사용여부")
    private String useYn;
    @ApiModelProperty(value = "사용여부명")
    private String useYnNm;
}