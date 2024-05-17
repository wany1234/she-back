package com.she.rsa.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "[HAZOP]평가계획")
@Getter
@Setter
public class AssessPlanHazop {
    @ApiModelProperty(value = "평가계획no")
    private int assessPlanNo;
    @ApiModelProperty(value = "사업장")
    private String plantCd;
    @ApiModelProperty(value = "사업장명")
    private String plantNm;
    @ApiModelProperty(value = "평가명")
    private String assessNm;
    @ApiModelProperty(value = "평가설명")
    private String assessDesc;
    @ApiModelProperty(value = "평가시작일")
    private String assessStartDate;
    @ApiModelProperty(value = "평가종료일")
    private String assessEndDate;
    @ApiModelProperty(value = "평가기간")
    private String assessPeriod;
    @ApiModelProperty(value = "평가년도")
    private String assessYear;
    @ApiModelProperty(value = "담당자 ID")
    private String userId;
    @ApiModelProperty(value = "담당자명")
    private String userNm;
    @ApiModelProperty(value = "진행상태코드")
    private String assessStepCd;
    @ApiModelProperty(value = "진행상태명")
    private String assessStepNm;
    @ApiModelProperty(value = "정기/수시 여부")
    private String regRegdem;
    @ApiModelProperty(value = "정기/수시 여부명")
    private String regRegdemNm;
    @ApiModelProperty(value = "평가기법no")
    private int assessTypeNo;
    @ApiModelProperty(value = "평가기법명")
    private String assessTypeNm;
    @ApiModelProperty(value = "결재진행상태코드")
    private String bizApprStepCd;
    @ApiModelProperty(value = "결재진행상태명")
    private String bizApprStepNm;
    @ApiModelProperty(value = "계획결재진행번호")
    private int papprRqstNo;
    @ApiModelProperty(value = "결과결재진행번호")
    private int rapprRqstNo;
    @ApiModelProperty(value = "변경관리번호")
    private int safChngNo;
    @ApiModelProperty(value = "MOC 번호")
    private String chngNum;
    @ApiModelProperty(value = "등록자")
    private String createUserId;
    @ApiModelProperty(value = "등록자명")
    private String createUserNm;
    @ApiModelProperty(value = "등록일")
    private String createDt;
    @ApiModelProperty(value = "수정자")
    private String updateUserId;
    @ApiModelProperty(value = "수정자명")
    private String updateUserNm;
    @ApiModelProperty(value = "수정일")
    private String updateDt;
    @ApiModelProperty(value = "대상공정")
    private List<AssessProcess> assessProcesses;

}
