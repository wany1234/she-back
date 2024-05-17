package com.she.env.envEffectEval.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "환경영향평가")
@Data
public class EnvEffectEval {

    @ApiModelProperty(value = "환경영향평가no")
    private int envEffectEvalNo;

    @ApiModelProperty(value = "사업장코드")
    private String plantCd;

    @ApiModelProperty(value = "사업장명")
    private String plantNm;

    @ApiModelProperty(value = "사업주관부서코드")
    private String deptCd;

    @ApiModelProperty(value = "사업주관부서명")
    private String deptNm;

    @ApiModelProperty(value = "C평가구분")
    private String evalGubun;

    @ApiModelProperty(value = "사업진행상태")
    private String evalProgState;

    @ApiModelProperty(value = "C사업진행상태")
    private String evalProgStateCd;

    @ApiModelProperty(value = "C진행단계코드")
    private String evalStepCd;

    @ApiModelProperty(value = "진행단계")
    private String evalStepNm;

    @ApiModelProperty(value = "대행업체코드")
    private String proxyVendorCd;

    @ApiModelProperty(value = "대행업체명")
    private String proxyVendorNm;

    @ApiModelProperty(value = "평가대행여부")
    private String proxyYn;

    @ApiModelProperty(value = "사업명")
    private String businessName;

    @ApiModelProperty(value = "사업기간 시작일")
    private String businessStartDt;

    @ApiModelProperty(value = "사업기간 종료일")
    private String businessEndDt;

    @ApiModelProperty(value = "사업내용")
    private String businessContents;

    @ApiModelProperty(value = "대상여부확인-요약내용")
    private String objectConfirmContent;

    @ApiModelProperty(value = "평가준비서 작성-요약내용")
    private String evalPrepCont;

    @ApiModelProperty(value = "평가항목 결정 공개-요약내용")
    private String evalDeterCont;

    @ApiModelProperty(value = "환경영향평가(초안)작성-요약내용")
    private String draftCont;

    @ApiModelProperty(value = "환경영향평가(초안)공고 공람-요약내용")
    private String draftNoticeCont;

    @ApiModelProperty(value = "주민 등의 의견 수렴-요약내용")
    private String residentOpnAccept;

    @ApiModelProperty(value = "주민설명회 개최-요약내용")
    private String residentExplCont;

    @ApiModelProperty(value = "주민공청회 개최-요약내용")
    private String residentHearCont;

    @ApiModelProperty(value = "환경영향평가(본안)작성-요약내용")
    private String evalAgendaWrite;

    @ApiModelProperty(value = "환경영향평가(본안)협의-요약내용")
    private String evalAgendaDiscuss;

    @ApiModelProperty(value = "환경영향평가(본안)협의 의견반영-요약내용")
    private String evalAgendaReflect;

    @ApiModelProperty(value = "탭번호")
    private String tabParam;

    @ApiModelProperty(value = "등록자")
    private String createUserId;

    @ApiModelProperty(value = "등록일")
    private String createDt;

    @ApiModelProperty(value = "수정자")
    private String updateUserId;

    @ApiModelProperty(value = "수정일")
    private String updateDt;

    @ApiModelProperty(value = "등록자부서명")
    private String createDeptNm;

    @ApiModelProperty(value = "등록자부서코드")
    private String createDeptCd;

    @ApiModelProperty(value = "수정자부서명")
    private String updateDeptNm;

    @ApiModelProperty(value = "수정부서코드")
    private String updateDeptCd;

    @ApiModelProperty(value = "등록자직위명")
    private String createPositionNm;

    @ApiModelProperty(value = "등록자직위코드")
    private String createPositionCd;

    @ApiModelProperty(value = "수정자직위명")
    private String updatePositionNm;

    @ApiModelProperty(value = "수정자직위코드")
    private String updatePositionCd;

    @ApiModelProperty(value = "작성자")
    private String writerUserNm;

    @ApiModelProperty(value = "작성일")
    private String writerDt;

}
