package com.she.mgt.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "SHE목표목록")
@Getter
@Setter
public class MgtTgtItemEvalRslt {
    @ApiModelProperty(value = "SHE목표 번호")
    private int mgtTargetNo;
    @ApiModelProperty(value = "SHE목표 그룹번호")
    private int mgtTargetGrpNo;
    @ApiModelProperty(value = "SHE목표 월별 번호")
    private int mgtTargetMonthNo;
    @ApiModelProperty(value = "대상연도")
    private String year;
    @ApiModelProperty(value = "대상월")
    private String month;
    @ApiModelProperty(value = "사업장코드")
    private String plantCd;
    @ApiModelProperty(value = "사업장명")
    private String plantNm;
    @ApiModelProperty(value = "대상부서코드")
    private String deptCd;
    @ApiModelProperty(value = "대상부서명")
    private String deptNm;
    @ApiModelProperty(value = "SHE분야명")
    private String bizFieldNm;
    @ApiModelProperty(value = "SHE분야별 항목번호")
    private int bizFieldItemNo;
    @ApiModelProperty(value = "SHE분야별 항목명")
    private String bizFieldItemNm;
    @ApiModelProperty(value = "목표값")
    private String targetVal;
    @ApiModelProperty(value = "실적값")
    private String rsltVal;
    @ApiModelProperty(value = "평가값")
    private String evalVal;
    @ApiModelProperty(value = "단계(상태)명")
    private String stepNm;
    @ApiModelProperty(value = "목표단계(상태)코드")
    private String tstepCd;
    @ApiModelProperty(value = "목표단계(상태)명")
    private String tstepNm;
    @ApiModelProperty(value = "실적단계(상태)코드")
    private String rstepCd;
    @ApiModelProperty(value = "실적단계(상태)명")
    private String rstepNm;
    @ApiModelProperty(value = "평가단계(상태)코드")
    private String estepCd;
    @ApiModelProperty(value = "평가단계(상태)명")
    private String estepNm;
    @ApiModelProperty(value = "목표")
    private String target;
    @ApiModelProperty(value = "방침")
    private String policy;
    @ApiModelProperty(value = "소수점자리수")
    private int decPlace;
    @ApiModelProperty(value = "출력순서")
    private int sortOrder;
    @ApiModelProperty(value = "목표결재요청번호")
    private int tapprRqstNo;
    @ApiModelProperty(value = "실적결재요청번호")
    private int rapprRqstNo;
    @ApiModelProperty(value = "평가결재요청번호")
    private int eapprRqstNo;
    @ApiModelProperty(value = "등록자")
    private String createUserNm;
    @ApiModelProperty(value = "등록자")
    private String createUserId;
    @ApiModelProperty(value = "등록일")
    private String createDt;
}
