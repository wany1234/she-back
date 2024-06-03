package com.she.rsa.model;

import lombok.Data;

@Data
public class WorkRiskEval02DeptPrcs {

    private String plantCd; // (C)사업장코드
    private String plantNm; // (C)사업장명
    private String evalYear; // 평가년도
    private String evalNo; // 평가번호
    private String deptCd; // 부서 코드
    private String deptNm; // 부서 명
    private String processCd; // 공정 코드
    private String processNm; // 공정 코드
    private String prcsLvlCd; // 공정 레벨
    private String unitWorkCd; // 단위작업코드
    private String unitWorkNm; // 단위작업명
    private String pProcessCd; // 평가대상상위공정코드
    private String parentsProcessCd; // 평가대상상위공정코드
    private String evalYmd; // 평가일자
    private String safAcdntCnt; // 안전사고이력
    private String chkRsltCd; // (C)검토결과구분코드
    private String chkRsltDesc; // 검토결과
    private String rsltCfmYn; // 확정여부
    private String remark; // 비고
    private String createUserId; // 등록자
    private String createDt; // 등록일
    private String updateUserId; // 수정자
    private String updateDt; // 수정일
    private String evalTypeCd;
    private String evalTypeNm;
    private String linePrcsCd; // 라인공정코드
    private String linePrcsNm; // 라인공정명
    private String linePrcsLvlCd; // 라인공정레벨
    private String grandPrcsCd; // 대공정코드
    private String grandPrcsNm; // 대공정명
    private String grandPrcsLvlCd; // 대공정레벨
    private int dtlWkNo; // 세부작업건수
    private int imprCnt; // 개선요청건수
    private String psnNmList; // 평가참여자목록
    private String chkRsltNm;
    private String unitWorkAgg;

}
