package com.she.rsa.model;

import lombok.Data;

@Data
public class WorkRiskEvalPlanDeptList {

    private String plantCd; // (C)사업장코드
    private String evalYear; // 평가년도
    private String evalNo; // 평가번호
    private String deptCd; // 부서 코드
    private String deptNm; // 평가대상부서명
    private String evalTypeCd; // (C)평가구분코드
    private String evalTypeNm; // 평가구분명
    private String deptEvalUserId; // 부서대표평가자ID
    private String chkUserId; // 검토담당자ID
    private String chkYmd; // 검토일자
    private String chkRsltCd; // (C)검토결과구분코드
    private String chkRsltDesc; // 검토결과
    private String remark; // 비고
    private String rsltStepCd; // (C)평가결과진행상태코드
    private String pGwApprBizCd; // GW결재업무코드(계획)
    private String pApprRqstNo; // 결재번호(계획)
    private String rGwApprBizCd; // GW결재업무코드(결과)
    private int rApprRqstNo; // 결재번호(결과)
    private String createUserId; // 등록자
    private String createDt; // 등록일
    private String updateUserId; // 수정자
    private String updateDt; // 수정일

}
