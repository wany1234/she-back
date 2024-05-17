package com.she.env.envEffectEval.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "환경영향평가결과")
@Data
public class EnvEffectEvalRslt {

    @ApiModelProperty(value = "환경영향평가 계획번호")
    public int evalPlanNo;

    @ApiModelProperty(value = "사업장코드")
    public String plantCd;

    @ApiModelProperty(value = "사업장명")
    public String plantNm;

    @ApiModelProperty(value = "주관부서코드")
    public String deptCd;

    @ApiModelProperty(value = "주관부서명")
    public String deptNm;

    @ApiModelProperty(value = "평가년도")
    public String evalYear;

    @ApiModelProperty(value = "평가기간")
    public String evalPeriod;

    @ApiModelProperty(value = "평가명")
    public String evalTitle;

    @ApiModelProperty(value = "대상부서코드")
    public String tgtDeptCd;

    @ApiModelProperty(value = "대상부서명")
    public String tgtDeptNm;

    @ApiModelProperty(value = "대상공정코드")
    public String tgtProcessCd;

    @ApiModelProperty(value = "대상공정명")
    public String tgtProcessNm;

    @ApiModelProperty(value = "작성일")
    private String writeDt;

    @ApiModelProperty(value = "작성자아이디")
    private String writeUserId;

    @ApiModelProperty(value = "작성자명")
    private String writeUserNm;

    @ApiModelProperty(value = "환경영향평가 결과번호")
    public int evalRsltNo;

    @ApiModelProperty(value = "설비코드")
    public String safFacilityCd;

    @ApiModelProperty(value = "설비명")
    public String safFacilityNm;

    @ApiModelProperty(value = "세부공정")
    public String detailProcess;

    @ApiModelProperty(value = "기초정보-구동설비명")
    public String facilityNm;

    @ApiModelProperty(value = "기초정보-연료")
    public String facilityFuel;

    @ApiModelProperty(value = "기초정보-사용량")
    public String facilityAmt;

    @ApiModelProperty(value = "기초정보-입력물")
    public String inputNm;

    @ApiModelProperty(value = "기초정보-입력량")
    public String inputAmt;

    @ApiModelProperty(value = "기초정보-출력물")
    public String outputNm;

    @ApiModelProperty(value = "기초정보-출력량")
    public String outputAmt;

    @ApiModelProperty(value = "기초정보-부산물")
    public String residueNm;

    @ApiModelProperty(value = "기초정보-부산물량")
    public String residueAmt;

    @ApiModelProperty(value = "환경측면-발생조건")
    public String envGen;

    @ApiModelProperty(value = "환경측면-상세활동")
    public String envAct;

    @ApiModelProperty(value = "환경영향-환경분야")
    public String envImpDiv;

    @ApiModelProperty(value = "환경영향-통제/관리방안")
    public String envImpMng;

    @ApiModelProperty(value = "환경영향-환경이슈")
    public String envImpIssue;

    @ApiModelProperty(value = "중요성평가-발생가능성")
    public String impoGen;

    @ApiModelProperty(value = "중요성평가-부정적영향")
    public String impoEffect;

    @ApiModelProperty(value = "중요성평가-부정적영향-유해성")
    public String impoToxic;

    @ApiModelProperty(value = "중요성평가-부정적영향-유해성(점수)")
    public String impoToxicScore;

    @ApiModelProperty(value = "중요성평가-부정적영향-양")
    public String impoQnt;

    @ApiModelProperty(value = "중요성평가-부정적영향-양(점수)")
    public String impoQntScore;

    @ApiModelProperty(value = "중요성평가-부정적영향-통제방안")
    public String impoCtrl;

    @ApiModelProperty(value = "중요성평가-부정적영향-통제방안(점수)")
    public String impoCtrlScore;

    @ApiModelProperty(value = "중요성평가-가중치")
    public String impoWeight;

    @ApiModelProperty(value = "중요성평가-심각성")
    public String impoSeriousness;

    @ApiModelProperty(value = "중요성평가-등급")
    public String impoRate;

    @ApiModelProperty(value = "중요성평가-유형")
    public String impoType;

    @ApiModelProperty(value = "대응방안")
    public String confDesc;

    @ApiModelProperty(value = "중요 환경영향 여부")
    public String envImpoYn;

    @ApiModelProperty(value = "중요 환경영향 여부명")
    public String envImpoYnNm;

    @ApiModelProperty(value = "환경담당자 의견")
    public String managerComment;

    @ApiModelProperty(value = "환경담당자 의견등록일")
    public String managerDt;

    @ApiModelProperty(value = "환경담당자")
    public String managerId;

    @ApiModelProperty(value = "환경담당자명")
    public String managerNm;

    @ApiModelProperty(value = "등록일")
    public String createDt;

    @ApiModelProperty(value = "등록자")
    public String createUserId;

    @ApiModelProperty(value = "개선요청번호")
    public int safImprActNo;

    @ApiModelProperty(value = "테이블구분")
    public String imprClassCd;

    @ApiModelProperty(value = "테이블식별자")
    public String refTableId;

    @ApiModelProperty(value = "개선요청/즉시조치")
    public String actClassCd;

    @ApiModelProperty(value = "개선조치상태")
    public String imprStepCd;

    @ApiModelProperty(value = "개선조치상태명")
    public String imprStepNm;

    @ApiModelProperty(value = "개선조치")
    public String imprActLbl;

    @ApiModelProperty(value = "즉시조치")
    public String imprActImmeLbl;

    @ApiModelProperty(value = "업로드 유효성체크")
    public boolean uploadFlag;

    @ApiModelProperty(value = "업로드 메시지")
    public List<String> uploadMessages;
}
