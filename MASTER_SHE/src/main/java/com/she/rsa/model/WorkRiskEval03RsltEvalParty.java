package com.she.rsa.model;

import lombok.Data;

@Data
public class WorkRiskEval03RsltEvalParty {

    private String plantCd; // 사업장코드
    private String plantNm; // 사업장명
    private String evalYear; // 평가년도
    private String evalNo; // 평가번호
    private String deptCd; // 평가대상부서코드(부서코드)
    private String processCd; // 프로세스코드(공정코드)
    private String unitWorkCd; // 작업코드(단위작업코드)
    private String userId; // 평가참여자 아이디
    private String userNm; // 평가참여자 명
    private String remark; // 비고
    private String createUserId;
    private String createDt;
    private String updateUserId;
    private String updateDt;
    private String deptNm;
    private String deptNmHr;

}
