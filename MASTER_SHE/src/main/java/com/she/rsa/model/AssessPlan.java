package com.she.rsa.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "평가계획")
@Getter
@Setter
public class AssessPlan {
    @ApiModelProperty(value = "평가계획no")
    private int assessPlanNo;
    @ApiModelProperty(value = "평가명")
    private String assessNm;
    @ApiModelProperty(value = "평가설명")
    private String assessDesc;
    @ApiModelProperty(value = "평가시작일")
    private String assessStartDate;
    @ApiModelProperty(value = "평가종료일")
    private String assessEndDate;
    @ApiModelProperty(value = "평가년도")
    private String assessYear;
    @ApiModelProperty(value = "담당자 ID")
    private String userId;
    @ApiModelProperty(value = "담당자명")
    private String userNm;
    @ApiModelProperty(value = "담당부서")
    private String deptCd;
    @ApiModelProperty(value = "담당부서명")
    private String deptNm;
    @ApiModelProperty(value = "정기/수시 여부")
    private String regRegdem;
    @ApiModelProperty(value = "정기/수시 여부명")
    private String regRegdemNm;
    @ApiModelProperty(value = "a")
    private String chkKras;
    @ApiModelProperty(value = "b")
    private String krasAssessTypeNo;
    @ApiModelProperty(value = "c")
    private String chkJsa;
    @ApiModelProperty(value = "d")
    private String jsaAssessTypeNo;
    @ApiModelProperty(value = "e")
    private String chkCharm;
    @ApiModelProperty(value = "f")
    private String charmAssessTypeNo;
    @ApiModelProperty(value = "위험Matrix명")
    private String assessTypeChecks;
    @ApiModelProperty(value = "등록자부서코드")
    private String createDeptCd;
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
    @ApiModelProperty(value = "진행상태")
    private String assessStatus;
    @ApiModelProperty(value = "진행상태코드")
    private String assessStepCd;
    @ApiModelProperty(value = "진행상태명")
    private String assessStatusNm;
    @ApiModelProperty(value = "")
    private int[] assessTypeNos;
    @ApiModelProperty(value = "평가기법no")
    private int assessTypeNo;
    @ApiModelProperty(value = "평가기법명")
    private String assessTypeNm;
    @ApiModelProperty(value = "평가기법분류명")
    private String assessGroupNm;
    @ApiModelProperty(value = "평가기법코드")
    private String assessTypeCd;
    @ApiModelProperty(value = "평가기법분류코드")
    private String assessGroupCd;
    @ApiModelProperty(value = "평가기간")
    private String assessPeriod;
    @ApiModelProperty(value = "사업장")
    private String plantCd;
    @ApiModelProperty(value = "사업장명")
    private String plantNm;
    @ApiModelProperty(value = "결재진행상태코드")
    private String bizApprStepCd;
    @ApiModelProperty(value = "결재진행상태명")
    private String bizApprStepNm;
    @ApiModelProperty(value = "위험등록부-조회용")
    private String riskBook;
    @ApiModelProperty(value = "계획결재진행번호")
    private int papprRqstNo;
    @ApiModelProperty(value = "결과결재진행번호")
    private int rapprRqstNo;
    @ApiModelProperty(value = "결재 유효성 체크")
    private int apprValidation;
    @ApiModelProperty(value = "g")
    private List<ProcessAssessChemical> processAssessChemicals;
    @ApiModelProperty(value = "작성자")
    private String writerUserNm;

    @ApiModelProperty(value = "작성일")
    private String writerDt;

}
