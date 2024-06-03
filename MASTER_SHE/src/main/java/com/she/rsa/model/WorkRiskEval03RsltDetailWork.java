package com.she.rsa.model;

import lombok.Data;

@Data
public class WorkRiskEval03RsltDetailWork {

    private String plantCd; // 사업장코드
    private String evalYear; // 평가년도
    private String evalNo; // 평가번호
    private String deptCd; // 평가대상부서코드(부서코드)
    private String processCd; // 프로세스코드(공정코드)
    private String unitWorkCd; // 작업코드(단위작업코드)
    private String dtlWkSeq; // 세부작업순번
    private String workStep; // 작업순서
    private String sortOrder; // 정렬순서
    private String dtlWkNm; // 세부작업명
    private String wkClsCd; // 작업구분코드(SAF_RSA_WK_TYPE)
    private int dtlPsnNum; // 작업인원
    private String jigNm; // 치공구
    private String riskHazCd; // 위험요인코드
    private String dgrActSituRslt; // 위험발생상황 및 결과
    private String disaTypeCd; // 재해유형코드(SAF_DISA_TYPE)
    private String nowSafAct; // 현재안전조치
    private String acdntVal; // 사고발생건수
    private String dwExpVal; // 위험작업노출횟수
    private String freqLvlVal; // 빈도등급
    private String dmgInteVal; // 피해강도
    private String dgrVal; // 위험성
    private String dgrLvlCd; // 위험등급
    private String manDgrRegNum; // 중요위험 등록번호
    private int imprActNo; // 개선테이블용PKID
    private String improvement;
    private int imprCnt; // 개선요청 등록건수
    private String rsltConfYn; // 평가결과확정여부
    private String createUserId;
    private String createDt;
    private String updateUserId;
    private String updateDt;

}
