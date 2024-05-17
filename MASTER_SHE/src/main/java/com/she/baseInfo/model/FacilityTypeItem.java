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

@ApiModel(description = "설비유형별 상세항목")
@Getter
@Setter
public class FacilityTypeItem {

    @ApiModelProperty(value = "설비유형관리항목번호")
    private int safFacilityTypeItemNo;
    @ApiModelProperty(value = "관리항목명")
    private String safFacilityTypeItemNm;
    @ApiModelProperty(value = "설비유형코드")
    private String safFacilityTypeCd;
    @ApiModelProperty(value = "설비유형명")
    private String safFacilityTypeNm;
    @ApiModelProperty(value = "설비코드")
    private String safFacilityCd;
    @ApiModelProperty(value = "추가설명")
    private String safFacilityTypeItemExplain;
    @ApiModelProperty(value = "비고")
    private String remark;
    @ApiModelProperty(value = "출력순서")
    private int sortOrder;
    @ApiModelProperty(value = "사용여부")
    private String useYn;
    @ApiModelProperty(value = "사용여부명")
    private String useYnNm;
    @ApiModelProperty(value = "관리항목값(임시)")
    private String itemValue;
}