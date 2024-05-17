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

@ApiModel(description = "환경>대기>검사항목")
@Data
public class TestItem {
    @ApiModelProperty(value = "배출구번호")
    private int eairOutletNo;

    @ApiModelProperty(value = "검사항목코드")
    private String eairTestItemCd;

    @ApiModelProperty(value = "검사항목명")
    private String eairTestItemNm;

    @ApiModelProperty(value = "사업장코드")
    private String plantCd;

    @ApiModelProperty(value = "사업장명")
    private String plantNm;

    @ApiModelProperty(value = "단위코드")
    private String envUnitCd;

    @ApiModelProperty(value = "단위명")
    private String envUnitNm;

    @ApiModelProperty(value = "SEMS코드")
    private String semsCd;

    @ApiModelProperty(value = "비고")
    private String remark;

    @ApiModelProperty(value = "정렬순서")
    private int sortOrder;

    @ApiModelProperty(value = "사용여부")
    private String useYn;

    @ApiModelProperty(value = "사용여부명")
    private String useYnNm;

    @ApiModelProperty(value = "배출량 계산 팩터")
    private Float dischAmtCalcFactor;

    @ApiModelProperty(value = "법적기준(상한값)")
    private Float legalLimit;

    @ApiModelProperty(value = "자체기준(상한값)")
    private Float selfLimit;

    @ApiModelProperty(value = "검사기기코드")
    private String eairInstCd;

    @ApiModelProperty(value = "검사기기명")
    private String eairInstNm;

    @ApiModelProperty(value = "검사방법코드")
    private String eairTestMtdCd;

    @ApiModelProperty(value = "검사방법명")
    private String eairTestMtdNm;

    @ApiModelProperty(value = "측정 농도 수치@eair_op_meas_result")
    private String numResult;

    @ApiModelProperty(value = "일일 배출량(kg/일)@eair_op_meas_result")
    private String dischAmtPerDay;

    @ApiModelProperty(value = "일일 배출량기준(kg/일)")
    private String limitPerDay;

    @ApiModelProperty(value = "등록자ID")
    private String createUserId;

    @ApiModelProperty(value = "등록자명")
    private String createUserNm;

    @ApiModelProperty(value = "등록일")
    private String createDt;

    @ApiModelProperty(value = "수정자ID")
    private String updateUserId;

    @ApiModelProperty(value = "수정자명")
    private String updateUserNm;

    @ApiModelProperty(value = "수정일")
    private String updateDt;

    @ApiModelProperty(value = "작성자")
    private String writerUserNm;

    @ApiModelProperty(value = "작성일")
    private String writerDt;
    
    @ApiModelProperty(value = "대기총량제 사용여부")
    private String airtotTargetYn;
}
