package com.she.mgt.model;

import java.util.List;

import com.she.vendor.model.SubconEvalItemResult;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "경영실적계획_부서_월별실적")
@Getter
@Setter
public class MgtMrDeptCalc {

    @ApiModelProperty(value = "사업장코드")
    private String plantCd;

    @ApiModelProperty(value = "사업장명")
    private String plantNm;

    @ApiModelProperty(value = "년도")
    private String year;

    @ApiModelProperty(value = "월")
    private String month;

    @ApiModelProperty(value = "비고")
    private String remarks;

    @ApiModelProperty(value = "항목")
    private String gubun;

    @ApiModelProperty(value = "년도")
    private String bizTgtYear;

    @ApiModelProperty(value = "부서코드")
    private String deptCd;

    @ApiModelProperty(value = "부서명")
    private String deptNm;

    @ApiModelProperty(value = "결재요청번호")
    private int apprRqstNo;

    @ApiModelProperty(value = "결재요청명")
    private String apprRqstNm;

    @ApiModelProperty(value = "결재요청번호단계코드")
    private String apprStepCd;

    @ApiModelProperty(value = "C문서진행상태코드")
    private String planStepCd;

    @ApiModelProperty(value = "C문서진행상태명")
    private String planStepNm;

    @ApiModelProperty(value = "안환지수_목표")
    private Float sheIndexTgt;

    @ApiModelProperty(value = "안환지수_달성")
    private Float sheIndexSum;

    @ApiModelProperty(value = "안환지수")
    private Float sheIndexVal;

    @ApiModelProperty(value = "안환지수")
    private int sheIndexCal;

    @ApiModelProperty(value = "안환지수")
    private String sheIndexValDis;

    @ApiModelProperty(value = "가점_점수")
    private int addPnt;

    @ApiModelProperty(value = "감점_점수")
    private int subPnt;

    @ApiModelProperty(value = "산술가점_교육계획 수립수_결과")
    private int caEdPlRsltVal;

    @ApiModelProperty(value = "산술가점_교육실시 횟수_결과")
    private int caEdRsRsltVal;

    @ApiModelProperty(value = "산술가점_안전점검실시 횟수_결과")
    private int caSaScRsltVal;

    @ApiModelProperty(value = "산술가점_설비점검실시 횟수_결과")
    private int caSaFcRsltVal;

    @ApiModelProperty(value = "산술가점_시설점검실시 횟수_결과")
    private int caSaEcRsltVal;

    @ApiModelProperty(value = "산술가점_사업장무재해 달성건수_결과")
    private int caSaNaRsltVal;

    @ApiModelProperty(value = "산술가점_부서무재해 달성건수_결과")
    private int caSaDnRsltVal;

    @ApiModelProperty(value = "산술가점_포상건수_결과")
    private int caMgPzRsltVal;

    @ApiModelProperty(value = "산술가점_시정/개선 요청 건수_결과")
    private int caMgIrRsltVal;

    @ApiModelProperty(value = "루트가점_상담인원 수_결과")
    private int raHeCuRsltVal;

    @ApiModelProperty(value = "루트가점_근골격계 조사실시 횟수_결과")
    private int raHeBnRsltVal;

    @ApiModelProperty(value = "루트가점_순회등록 횟수_결과")
    private int raHeRdRsltVal;

    @ApiModelProperty(value = "루트가점_개선조치실적_결과")
    private int raMgIsRsltVal;

    @ApiModelProperty(value = "루트가점_아차사고 횟수_결과")
    private int raSaNeRsltVal;

    @ApiModelProperty(value = "루트가점_CTO건수_결과")
    private int raSaCtRsltVal;

    @ApiModelProperty(value = "루트가점_위험성평가 실시 횟수_결과")
    private int raSaDeRsltVal;

    @ApiModelProperty(value = "산술감점_교육 불참자수_결과")
    private int cmEdNrRsltVal;

    @ApiModelProperty(value = "산술감점_유소견자 인원수_결과")
    private int cmHePaRsltVal;

    @ApiModelProperty(value = "산술감점_작업환경측정 노출기준 초과공정 수_결과")
    private int cmHeWnRsltVal;

    @ApiModelProperty(value = "산술감점_변경관리 완료 지연 일수_결과")
    private int cmSaChRsltVal;

    @ApiModelProperty(value = "산술감점_개선조치 지연건수_결과")
    private int cmMgNiRsltVal;

    @ApiModelProperty(value = "산술감점_사고 등록건수_결과")
    private int cmSaAcRsltVal;

    @ApiModelProperty(value = "산술감점_징계건수_결과")
    private int cmMgDsRsltVal;

    @ApiModelProperty(value = "산술감점_과징금(벌과금)건수_결과")
    private int cmFnDsRsltVal;

    @ApiModelProperty(value = "경영실적결과번호")
    private int mgtMrRsltNo;

    @ApiModelProperty(value = "가중치")
    private float wghVal;

    @ApiModelProperty(value = "목표")
    private float tgtVal;

    @ApiModelProperty(value = "1월")
    private float sheIndexValMonth1;

    @ApiModelProperty(value = "2월")
    private float sheIndexValMonth2;

    @ApiModelProperty(value = "3월")
    private float sheIndexValMonth3;

    @ApiModelProperty(value = "4월")
    private float sheIndexValMonth4;

    @ApiModelProperty(value = "5월")
    private float sheIndexValMonth5;

    @ApiModelProperty(value = "6월")
    private float sheIndexValMonth6;

    @ApiModelProperty(value = "7월")
    private float sheIndexValMonth7;

    @ApiModelProperty(value = "8월")
    private float sheIndexValMonth8;

    @ApiModelProperty(value = "9월")
    private float sheIndexValMonth9;

    @ApiModelProperty(value = "10월")
    private float sheIndexValMonth10;

    @ApiModelProperty(value = "11월")
    private float sheIndexValMonth11;

    @ApiModelProperty(value = "12월")
    private float sheIndexValMonth12;

    @ApiModelProperty(value = "등록자ID")
    private String createUserId;

    @ApiModelProperty(value = "등록일")
    private String createDt;

    @ApiModelProperty(value = "수정자ID")
    private String updateUserId;

    @ApiModelProperty(value = "수정일")
    private String updateDt;

    @ApiModelProperty(value = "년월")
    private String rsltMonth;

    @ApiModelProperty(value = "부서월별 실적 리스트")
    private List<MgtMrDeptCalcItem> mgtMrDeptCalcItem;

}
