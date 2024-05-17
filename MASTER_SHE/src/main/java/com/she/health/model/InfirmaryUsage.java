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

@ApiModel(description = "상담2")
@Data
public class InfirmaryUsage {

    @ApiModelProperty(value = "건강상담번호")
    private int heaConsultNo;

    @ApiModelProperty(value = "사업장 코드")
    private String plantCd;

    @ApiModelProperty(value = "사업장 명칭")
    private String plantNm;

    @ApiModelProperty(value = "사용자 아이디")
    private String userId;

    @ApiModelProperty(value = "사용자 이름")
    private String userNm;

    @ApiModelProperty(value = "부서 코드")
    private String deptCd;

    @ApiModelProperty(value = "부서 명칭")
    private String deptNm;

    @ApiModelProperty(value = "방문일자")
    private String visitYmd;

    @ApiModelProperty(value = "상담구분코드")
    private String counselTypeCd;

    @ApiModelProperty(value = "상담구분명칭")
    private String counselTypeNm;

    @ApiModelProperty(value = "상담자")
    private String counselor;

    @ApiModelProperty(value = "과거력")
    private String diseasePast;

    @ApiModelProperty(value = "현병력")
    private String diseaseCurr;

    @ApiModelProperty(value = "증상")
    private String symptom;

    @ApiModelProperty(value = "상담내역")
    private String consult;

    @ApiModelProperty(value = "상담결과 및 특이사항")
    private String remark;

    @ApiModelProperty(value = "혈당측정치")
    private int bldSugVal;

    @ApiModelProperty(value = "콜레스테롤측정치")
    private int cholesterolVal;

    @ApiModelProperty(value = "혈압수축기측정치")
    private int bldPressContVal;

    @ApiModelProperty(value = "혈압이완기측정치")
    private int bldPressReleVal;

    @ApiModelProperty(value = "등록자")
    private String createUserId;

    @ApiModelProperty(value = "등록일")
    private String createDt;

    @ApiModelProperty(value = "수정자")
    private String updateUserId;

    @ApiModelProperty(value = "수정일")
    private String updateDt;

    @ApiModelProperty(value = "등록자 부서코드")
    private String createDeptCd;

    @ApiModelProperty(value = "등록자 부서명")
    private String createDeptNm;

    @ApiModelProperty(value = "등록자 직위코드")
    private String createPositionCd;

    @ApiModelProperty(value = "등록자 직위명")
    private String createPositionNm;

    @ApiModelProperty(value = "수정자 부서코드")
    private String updateDeptCd;

    @ApiModelProperty(value = "수정자 부서명")
    private String updateDeptNm;

    @ApiModelProperty(value = "수정자 직위코드")
    private String updatePositionCd;

    @ApiModelProperty(value = "수정자 직위명")
    private String updatePositionNm;

    @ApiModelProperty(value = "작성자")
    private String writerUserNm;

    @ApiModelProperty(value = "작성일")
    private String writerDt;
}
