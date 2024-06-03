package com.she.rsa.model;

import lombok.Data;

@Data
public class WorkRiskEval04Appr {

    private String plantCd; // 사업장코드
    private String plantNm; // 사업장명
    private String evalYear; // 평가년도
    private String evalNo; // 평가번호
    private String deptCd; // 평가대상부서코드(부서코드)
    private String deptNm; // 평가대상부서명
    private String processCd; // 프로세스코드(공정코드)
    private String unitWorkCd; // 작업코드(단위작업코드)
    private String evalTypeCd;// 평가구분코드
    private String evalTypeNm; // 평가구분명
    private String rsltApprRqstNo; // 결재번호(결과)
    private String rsltApprRqstNm; // 결재진행상태명
    private String rsltStepCd; // 평가결과진행상태코드
    private String rsltStepNm; // 평가결과진행상태명
    private String deptEvalUserId; // 부서대표평가자ID
    private String deptEvalUserNm; // 부서대표평가자명
    private String evalNm; // 평가명
    private String planPeriod; // 평가기간
    private String docId;

}
