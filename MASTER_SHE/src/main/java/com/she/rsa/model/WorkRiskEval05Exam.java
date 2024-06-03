package com.she.rsa.model;

import lombok.Data;

@Data
public class WorkRiskEval05Exam {

    private String plantCd; // (C)사업장코드
    private String evalYear; // 평가년도
    private String evalNo; // 평가번호
    private String deptCd; // 평가대상부서코드
    private String processCd; // 프로세스코드(공정코드)
    private String unitWorkCd; // 작업코드(단위작업코드)
    private String createUserId;
    private String createDt;
    private String updateUserId;
    private String updateDt;
    private String rsltStepCd;
    private String deptEvalUserId;
    private String chkUserId; // 검토자아이디
    private String chkUserNm; // 검토자명
    private String chkYmd; // 검토일자
    private String chkRsltCd; // 검토결과구분코드
    private String chkRsltDesc; // 검토결과
    private String workChkRsltCd; // 작업검토결과구분코드
    private String workChkRsltDesc; // 작업검토결과
    private String callGb;
}
