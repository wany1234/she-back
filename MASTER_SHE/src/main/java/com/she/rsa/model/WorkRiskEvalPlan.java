package com.she.rsa.model;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class WorkRiskEvalPlan {

    private String plantCd; // 사업장코드
    private String plantNm; // 사업장명
    private String evalYear; // 평가년도
    private String evalNo; // 평가번호
    private String evalTypeCd; // 평가구분코드
    private String evalTypeNm; // 평가구분명
    private String evalNm; // 평가명
    private String planSymd; // 평가기간시작일
    private String planEymd; // 평가기간종료일
    private String planPeriod; // 평가기간
    private String deptCd; // 평가주관부서코드
    private String deptNm; // 평가주관부서명
    private String mainDeptCd; // 평가주관부서코드(화면용)
    private String mainDeptNm; // 평가주관부서명(화면용)
    private String mgrId; // 평가계힉담당자아이디
    private String mgrNm; // 평가계획담당자명
    private String chngNo; // 변경번호
    private String stepCd; // 계획진행상태코드
    private String stepNm; // 계획진행상태명
    private String gwApprBizCd; // GW결재업무코드
    private int apprRqstNo; // 결과 결재번호
    private String apprRqstNm; // 결과 결재명
    private String bizApprStepCd; // 결재진행상태코드
    private String bizApprStepNm; // 결재진행상태명
    private String createUserId;
    private String createDt;
    private String updateUserId;
    private String updateDt;
    private String lang;
    private String docId;

    @ApiModelProperty(value = "평가대상부서 목록")
    private List<WorkRiskEvalPlanDeptList> workRiskEvalPlanDeptList;

}
