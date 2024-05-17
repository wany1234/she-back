package com.she.vendor.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "협력업체평가결과")
@Data
public class SubconEvalResult {

    @ApiModelProperty(value = "협력업체평가결과No")
    private int safSubconEvalResultNo;

    @ApiModelProperty(value = "사업장코드")
    private String plantCd;

    @ApiModelProperty(value = "사업장명")
    private String plantNm;

    @ApiModelProperty(value = "평가구분코드")
    private String subconEvalClsCd;

    @ApiModelProperty(value = "평가구분명")
    private String subconEvalClsNm;

    @ApiModelProperty(value = "평가유형코드")
    private String subconEvalAttCd;

    @ApiModelProperty(value = "평가유형명")
    private String subconEvalAttNm;

    @ApiModelProperty(value = "평가시작일")
    private String evalSDt;

    @ApiModelProperty(value = "평가종료일")
    private String evalEDt;

    @ApiModelProperty(value = "평가기간")
    private String evalPeriod;

    @ApiModelProperty(value = "공사번호")
    private String constNo;

    @ApiModelProperty(value = "평가명")
    private String evalNm;

    @ApiModelProperty(value = "업체코드")
    private String vendorCd;

    @ApiModelProperty(value = "업체명")
    private String vendorNm;

    @ApiModelProperty(value = "평점")
    private int evalSumPnt;

    @ApiModelProperty(value = "총점")
    private int evalAllPnt;

    @ApiModelProperty(value = "비고")
    private String remarks;

    @ApiModelProperty(value = "진행상태코드")
    private String status;

    @ApiModelProperty(value = "진행상태")
    private String statusNm;

    @ApiModelProperty(value = "결재요청No")
    private int apprRqstNo;

    @ApiModelProperty(value = "결재상태코드")
    private String bizApprStepCd;

    @ApiModelProperty(value = "결재상태")
    private String bizApprStepNm;

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

    @ApiModelProperty(value = "평가항목리스트")
    private List<SubconEvalItemResult> subconEvalItemResults;

    @ApiModelProperty(value = "작성자")
    private String writerUserNm;

    @ApiModelProperty(value = "작성일")
    private String writerDt;
}
