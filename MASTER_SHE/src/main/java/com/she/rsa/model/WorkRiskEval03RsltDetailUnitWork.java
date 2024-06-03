package com.she.rsa.model;

import lombok.Data;

@Data
public class WorkRiskEval03RsltDetailUnitWork {

    private String plantCd; // 사업장코드
    private String evalYear; // 평가년도
    private String evalNo; // 평가번호
    private String deptCd; // 평가대상부서코드(부서코드)
    private String deptNm; // 평가대상부서명
    private String processCd; // 프로세스코드(공정코드)
    private String processNm; // 프로세스명
    private String unitWorkCd; // 작업코드(단위작업코드)
    private String unitWorkNm; // 작업명
    private String processAllNm; // 전체공정명
    private String dtlWkSeq;
    private String dtlWkNm;
    private String remark;
    private String sortOrder;
    private String useYn;
    private String useYnTxt;
    private String workStep; // 작업순서
    private String createUserId;
    private String createDt;
    private String updateUserId;
    private String updateDt;

}
