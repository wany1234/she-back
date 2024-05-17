package com.she.mgt.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "법정선임자평가 본인평가/상위평가 평가결과 목록")
public class ElectEvalRsltItem {
    @ApiModelProperty(value = "평가계획번호")
    private int evalPlanNo;

    @ApiModelProperty(value = "평가대상자 번호")
    private int evalUserNo;

    @ApiModelProperty(value = "본인평가결과 번호")
    private int evalMeRsltNo;

    @ApiModelProperty(value = "상위평가결과 번호")
    private int evalUpRsltNo;

    @ApiModelProperty(value = "평가항목 번호")
    private int evalItemNo;

    @ApiModelProperty(value = "구분")
    private String itemType;

    @ApiModelProperty(value = "평가대상업무")
    private String itemWork;

    @ApiModelProperty(value = "비고")
    private String remark;

    @ApiModelProperty(value = "평가 점수")
    private int evalPoint;

    @ApiModelProperty(value = "본인평가 점수")
    private String meEvalPoint;

    @ApiModelProperty(value = "상위평가 점수")
    private String upEvalPoint;

    @ApiModelProperty(value = "등록자 ID")
    private String createUserId;

    @ApiModelProperty(value = "등록일")
    private String createDt;

    @ApiModelProperty(value = "수정자 ID")
    private String updateUserId;

    @ApiModelProperty(value = "수정일")
    private String updateDt;

    @ApiModelProperty(value = "등록자 부서")
    private String createDeptNm;

    @ApiModelProperty(value = "등록자 부서코드")
    private String createDeptCd;

    @ApiModelProperty(value = "수정자 부서")
    private String updateDeptNm;

    @ApiModelProperty(value = "수정자 부서코드")
    private String updateDeptCd;

    @ApiModelProperty(value = "등록자 직위")
    private String createPositionNm;

    @ApiModelProperty(value = "등록자 직위코드")
    private String createPositionCd;

    @ApiModelProperty(value = "수정자 직위")
    private String updatePositionNm;

    @ApiModelProperty(value = "수정자 직위코드")
    private String updatePositionCd;
}
