package com.she.rsa.model;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class WorkRiskEval03Rslt {

    private String plantCd; // 사업장코드
    private String plantNm; // 사업장명
    private String evalYear; // 평가년도
    private String evalNo; // 평가번호
    private String evalTypeCd;// 평가구분코드
    private String evalTypeNm; // 평가구분명
    private String deptCd; // 평가대상부서코드(부서코드)
    private String deptNm; // 평가대상부서명
    private String linePrcsNm;// 라인명
    private String grandPrcsNm;// 대공정명
    private String processCd; // 프로세스코드(공정코드)
    private String processNm; // 프로세스명
    private String unitWorkCd; // 작업코드(단위작업코드)
    private String unitWorkNm; // 작업명
    private String rsltCfmYn; // 확정여부
    private String rsltCfmNm; // 확정여부명
    private String evalYmd; // 평가일
    private String evalNm; // 평가명
    private String planPeriod; // 평가계획기간
    private String mainDeptCd; // 평가주관부서코드
    private String mainDeptNm; // 평가주관부서명
    private String mgrId; // 평가계획담당자아이디
    private String mgrNm; // 평가계획담당자명
    private String processAllNm; // 전체공정명
    private String safAcdntCnt; // 안전사고이력
    private String remark; // 비고
    private String createUserId;
    private String createDt;
    private String updateUserId;
    private String updateDt;
    private String refTableId;
    private String rsltStepCd; // 평가결과진행상태코드

    private String chngElemTxt; // 변경관리 요소명
    private String safChngNo; // 변경번호
    private int refBizNo; // 변경관리업무순번
    private int oldRefBizNo; // 기존변경관리업무순번
    private String workChkRsltCd;
    private String workChkRsltDesc;
    private String chkYmd;
    private String chkRsltCd;
    private String chkRsltDesc;

    @ApiModelProperty(value = "세부작업 목록")
    private List<WorkRiskEval03RsltDetailWork> workRiskEval03RsltDetailWork;

    @ApiModelProperty(value = "평가참여자 목록")
    private List<WorkRiskEval03RsltEvalParty> workRiskEval03RsltEvalParty;

    @ApiModelProperty(value = "세부작업 수정 목록")
    private List<WorkRiskEval03RsltDetailUnitWork> workRiskEval03RsltDetailUnitWork;

}
