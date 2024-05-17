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

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "설비유형별 점검항목")
@Getter
@Setter
public class FacilityCheckItem {

    @ApiModelProperty(value = "설비점검항목번호")
    private int safFacilityCheckItemNo;
    @ApiModelProperty(value = "설비점검항목명")
    private String safFacilityCheckNm;
    @ApiModelProperty(value = "사업장코드")
    private String plantCd;
    @ApiModelProperty(value = "사업장명")
    private String plantNm;
    @ApiModelProperty(value = "설비점검종류코드")
    private String safCheckTypeCd;
    @ApiModelProperty(value = "설비점검종류명")
    private String safCheckTypeNm;
    @ApiModelProperty(value = "설비유형코드")
    private String safFacilityTypeCd;
    @ApiModelProperty(value = "설비유형명")
    private String safFacilityTypeNm;
    @ApiModelProperty(value = "출력순서")
    private int sortOrder;
    @ApiModelProperty(value = "사용여부")
    private String useYn;
    @ApiModelProperty(value = "사용여부명")
    private String useYnNm;
    @ApiModelProperty(value = "수정")
    private String updating;
    @ApiModelProperty(value = "lvl코드")
    private String dataLvlCd;
    @ApiModelProperty(value = "부모설비점검항목명")
    @JsonProperty("pSafFacilityCheckNm")
    private String pSafFacilityCheckNm;
    @ApiModelProperty(value = "부모의부모설비점검항목명")
    private String ppSafFacilityCheckNm;
    @ApiModelProperty(value = "부모설비점검항목코드")
    @JsonProperty("pSafFacilityCheckItemNo")
    private int pSafFacilityCheckItemNo;
    @ApiModelProperty(value = "부모의부모설비점검항목코드")
    private int ppSafFacilityCheckItemNo;
    @ApiModelProperty(value = "생성자아이디")
    private String createUserId;
    @ApiModelProperty(value = "수정자아이디")
    private String updateUserId;
    @ApiModelProperty(value = "작성자")
    private String writerUserNm;
    @ApiModelProperty(value = "작성일")
    private String writerDt;

}