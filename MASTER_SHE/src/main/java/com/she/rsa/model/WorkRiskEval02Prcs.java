package com.she.rsa.model;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class WorkRiskEval02Prcs {

    private String plantCd; // (C)사업장코드
    private String plantNm; // 사업장명
    private String evalYear; // 평가년도
    private String evalNo; // 평가번호
    private String stepCd;// 계획진행상태코드
    private String stepNm;// 계획진행상태명
    private String deptEvalUserId; // 부서대표평가자ID
    private String deptEvalUserNm; // 부서대표평가자명
    private String rsltStepCd; // 평가결과진행상태코드
    private String rsltStepNm; // 평가결과진행상태코드명
    private String deptCd; // 평가대상부서코드
    private String deptNm; // 평가대상부서명
    private String evalTypeCd; // (C)평가구분코드
    private String evalTypeNm; // (C)평가구분코드명
    private String evalNm; // 평가명
    private String planSymd; // 평가기간시작일
    private String planEymd; // 평가기간종료일
    private String planPeriod; // 평가기간
    private String mainDeptCd; // 평가주관부서코드
    private String mainDeptNm; // 평가주관부서명
    private String mgrId; // 평가계힉담당자아이디
    private String mgrNm; // 평가계획담당자명
    private String chngNo; // 변경번호
    private String gwApprBizCd; // GW결재업무코드
    private int pApprRqstNo; // 결재번호(계획)
    private int rApprRqstNo; // 결재번호(검토)
    private int planApprRqstNo; // 결재번호(계획)
    private int rsltApprRqstNo; // 결재번호(검토)
    private String evalYearNm; // 평가년도명
    private String remark; // 비고
    private String createUserId;
    private String createDt;
    private String updateUserId;
    private String updateDt;
    private String chkUserId;
    private String chkUserNm;
    private String chkYmd;
    private String chkRsltCd;
    private String chkRsltDesc;
    private String planApprRqstNm;
    private String rsltApprRqstNm;
    private String chkDeptCd;
    private String rsltGwApprBizCd;

    @ApiModelProperty(value = "대상공정설정 목록")
    private List<WorkRiskEval02DeptPrcs> workRiskEval02DeptPrcs;

}
