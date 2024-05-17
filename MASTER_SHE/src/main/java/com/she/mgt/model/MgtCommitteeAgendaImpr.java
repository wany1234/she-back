package com.she.mgt.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "개선조치")
public class MgtCommitteeAgendaImpr {
    @ApiModelProperty(value = "개선조치번호")
    public int safImprActNo;
    @ApiModelProperty(value = "사업장코드")
    public String plantCd;
    @ApiModelProperty(value = "사업장번호")
    public String plantNm;
    @ApiModelProperty(value = "평가여부")
    public String completYn;
    @ApiModelProperty(value = "개선분류코드")
    public String imprClassCd;
    @ApiModelProperty(value = "개선분류명")
    public String imprClassNm;
    @ApiModelProperty(value = "조치처리구분코드")
    public String actClassCd;
    @ApiModelProperty(value = "조치처리구분명")
    public String actClassNm;
    @ApiModelProperty(value = "요청일")
    public String imprRqstYmd;
    @ApiModelProperty(value = "제목")
    public String imprTitle;
    @ApiModelProperty(value = "업체개선요청여부")
    public String subconnRqstYn;

    @ApiModelProperty(value = "개선요청업체코드")
    public String imprRqstVendorCd;
    @ApiModelProperty(value = "개선요청업체명")
    public String imprRqstVendorNm;

    @ApiModelProperty(value = "개선요청부서코드")
    public String imprRqstDeptCd;
    @ApiModelProperty(value = "개선요청부서명")
    public String imprRqstDeptNm;
    @ApiModelProperty(value = "개선요청자사용자ID")
    public String imprRqstUserId;
    @ApiModelProperty(value = "개선요청자성명")
    public String imprRqstUserNm;
    @ApiModelProperty(value = "개선요청내용")
    public String imprRqstContents;
    @ApiModelProperty(value = "개선진행단계코드")
    public String imprStepCd;
    @ApiModelProperty(value = "개선진행단계명")
    public String imprStepNm;
    @ApiModelProperty(value = "관련테이블PKID")
    public int refTableId;
    @ApiModelProperty(value = "조치예정일")
    public String actSchYmd;
    @ApiModelProperty(value = "조치완료일")
    public String actConfirmYmd;
    @ApiModelProperty(value = "조치기한")
    public String actLimitYmd;
    @ApiModelProperty(value = "공정코드")
    public String processCd;
    @ApiModelProperty(value = "공정명")
    public String processNm;
    @ApiModelProperty(value = "세부위치")
    public String dtlLocat;

    @ApiModelProperty(value = "조치업체조치완료여부")
    public String actVendorConfirmYn;
    @ApiModelProperty(value = "기존조치업체")
    public String preActVendorCd;
    @ApiModelProperty(value = "기존조치업체명")
    public String preActVendorNm;
    @ApiModelProperty(value = "조치업체변경사유")
    public String actVendorChngContents;
    @ApiModelProperty(value = "조치업체코드")
    public String actVendorCd;
    @ApiModelProperty(value = "조치업체명")
    public String actVendorNm;

    @ApiModelProperty(value = "기존조치부서")
    public String preActDeptCd;
    @ApiModelProperty(value = "기존조치부서명")
    public String preActDeptNm;
    @ApiModelProperty(value = "조치부서변경사유")
    public String actDeptChngContents;
    @ApiModelProperty(value = "조치부서코드")
    public String actDeptCd;
    @ApiModelProperty(value = "조치부서명")
    public String actDeptNm;
    @ApiModelProperty(value = "조치자ID")
    public String actUserId;
    @ApiModelProperty(value = "조치자성명")
    public String actUserNm;
    @ApiModelProperty(value = "조치결과내용")
    public String actResultContents;
    @ApiModelProperty(value = "조치결과검토")
    public String actResultReview;
    @ApiModelProperty(value = "개선전빈도")
    public String befFreq;
    @ApiModelProperty(value = "개선전강도")
    public String befInten;
    @ApiModelProperty(value = "개선전위험도")
    public String befDegRisk;
    @ApiModelProperty(value = "개선후빈도")
    public String aftFreq;
    @ApiModelProperty(value = "개선후강도")
    public String aftInten;
    @ApiModelProperty(value = "개선후위험도")
    public String aftDegRisk;
    @ApiModelProperty(value = "등록자ID")
    public String createUserId;
    @ApiModelProperty(value = "등록일")
    public String createDt;
    @ApiModelProperty(value = "최종수정자ID")
    public String updateUserId;
    @ApiModelProperty(value = "최종수정일")
    public String updateDt;
    @ApiModelProperty(value = "위험등록부번호")
    public String riskBookNo;
    @ApiModelProperty(value = "계획미수립수")
    public int notPlanCnt;
    @ApiModelProperty(value = "계획수립수")
    public int planCnt;
    @ApiModelProperty(value = "조치진행중수")
    public int actCnt;
    @ApiModelProperty(value = "조치완료수")
    public int completeCnt;
    @ApiModelProperty(value = "계획미수립수")
    public int notPlanCnt2;
    @ApiModelProperty(value = "계획수립수")
    public int planCnt2;
    @ApiModelProperty(value = "조치진행중수")
    public int actCnt2;
    @ApiModelProperty(value = "조치완료수")
    public int completeCnt2;
    @ApiModelProperty(value = "이행률(%)")
    public int transitionRate1;
    @ApiModelProperty(value = "이행률(%)")
    public int transitionRate2;
    @ApiModelProperty(value = "이행률(%)")
    public int transitionRate3;
    @ApiModelProperty(value = "결재진행번호")
    public String apprRqstNo;
    @ApiModelProperty(value = "결재진행코드")
    public String bizApprStepCd;
    @ApiModelProperty(value = "결재진행명")
    public String bizApprStepNm;
    @ApiModelProperty(value = "첫번째 개선전사진")
    public String befImg;
    @ApiModelProperty(value = "첫번째 개선후사진")
    public String aftImg;
    @ApiModelProperty(value = "발견사항")
    public String discoverMatter;
    @ApiModelProperty(value = "개선사항_기술적")
    public String imprTech;
    @ApiModelProperty(value = "개선사항_관리적")
    public String imprMgm;
    @ApiModelProperty(value = "개선사항_교육적")
    public String imprEdu;

    public String errorMessage;

    @ApiModelProperty(value = "회의안건번호")
    public int agendaNo;

    @ApiModelProperty(value = "비고")
    public String remark;

    @ApiModelProperty(value = "회의안건")
    public String agendaDesc;

    public int checkListNo;

    public int checkLogNo;

    public String checkNm;

    public String inspDt;

    public MgtCommitteeAgendaImpr() {
    }

    public int getSafImprActNo() {
        return safImprActNo;
    }

    public void setSafImprActNo(int safImprActNo) {
        this.safImprActNo = safImprActNo;
    }

    public String getPlantCd() {
        return plantCd;
    }

    public void setPlantCd(String plantCd) {
        this.plantCd = plantCd;
    }

    public String getPlantNm() {
        return plantNm;
    }

    public void setPlantNm(String plantNm) {
        this.plantNm = plantNm;
    }

    public String getImprClassCd() {
        return imprClassCd;
    }

    public void setImprClassCd(String imprClassCd) {
        this.imprClassCd = imprClassCd;
    }

    public String getImprClassNm() {
        return imprClassNm;
    }

    public void setImprClassNm(String imprClassNm) {
        this.imprClassNm = imprClassNm;
    }

    public String getActClassCd() {
        return actClassCd;
    }

    public void setActClassCd(String actClassCd) {
        this.actClassCd = actClassCd;
    }

    public String getActClassNm() {
        return actClassNm;
    }

    public void setActClassNm(String actClassNm) {
        this.actClassNm = actClassNm;
    }

    public String getImprRqstYmd() {
        return imprRqstYmd;
    }

    public void setImprRqstYmd(String imprRqstYmd) {
        this.imprRqstYmd = imprRqstYmd;
    }

    public String getImprTitle() {
        return imprTitle;
    }

    public void setImprTitle(String imprTitle) {
        this.imprTitle = imprTitle;
    }

    public String getSubconnRqstYn() {
        return subconnRqstYn;
    }

    public void setSubconnRqstYn(String subconnRqstYn) {
        this.subconnRqstYn = subconnRqstYn;
    }

    public String getImprRqstVendorCd() {
        return imprRqstVendorCd;
    }

    public void setImprRqstVendorCd(String imprRqstVendorCd) {
        this.imprRqstVendorCd = imprRqstVendorCd;
    }

    public String getImprRqstVendorNm() {
        return imprRqstVendorNm;
    }

    public void setImprRqstVendorNm(String imprRqstVendorNm) {
        this.imprRqstVendorNm = imprRqstVendorNm;
    }

    public String getImprRqstDeptCd() {
        return imprRqstDeptCd;
    }

    public void setImprRqstDeptCd(String imprRqstDeptCd) {
        this.imprRqstDeptCd = imprRqstDeptCd;
    }

    public String getImprRqstDeptNm() {
        return imprRqstDeptNm;
    }

    public void setImprRqstDeptNm(String imprRqstDeptNm) {
        this.imprRqstDeptNm = imprRqstDeptNm;
    }

    public String getImprRqstUserId() {
        return imprRqstUserId;
    }

    public void setImprRqstUserId(String imprRqstUserId) {
        this.imprRqstUserId = imprRqstUserId;
    }

    public String getImprRqstUserNm() {
        return imprRqstUserNm;
    }

    public void setImprRqstUserNm(String imprRqstUserNm) {
        this.imprRqstUserNm = imprRqstUserNm;
    }

    public String getImprRqstContents() {
        return imprRqstContents;
    }

    public void setImprRqstContents(String imprRqstContents) {
        this.imprRqstContents = imprRqstContents;
    }

    public String getImprStepCd() {
        return imprStepCd;
    }

    public void setImprStepCd(String imprStepCd) {
        this.imprStepCd = imprStepCd;
    }

    public String getImprStepNm() {
        return imprStepNm;
    }

    public void setImprStepNm(String imprStepNm) {
        this.imprStepNm = imprStepNm;
    }

    public int getRefTableId() {
        return refTableId;
    }

    public void setRefTableId(int refTableId) {
        this.refTableId = refTableId;
    }

    public String getActSchYmd() {
        return actSchYmd;
    }

    public void setActSchYmd(String actSchYmd) {
        this.actSchYmd = actSchYmd;
    }

    public String getActConfirmYmd() {
        return actConfirmYmd;
    }

    public void setActConfirmYmd(String actConfirmYmd) {
        this.actConfirmYmd = actConfirmYmd;
    }

    public String getActLimitYmd() {
        return actLimitYmd;
    }

    public void setActLimitYmd(String actLimitYmd) {
        this.actLimitYmd = actLimitYmd;
    }

    public String getProcessCd() {
        return processCd;
    }

    public void setProcessCd(String processCd) {
        this.processCd = processCd;
    }

    public String getProcessNm() {
        return processNm;
    }

    public void setProcessNm(String processNm) {
        this.processNm = processNm;
    }

    public String getDtlLocat() {
        return dtlLocat;
    }

    public void setDtlLocat(String dtlLocat) {
        this.dtlLocat = dtlLocat;
    }

    public String getActVendorConfirmYn() {
        return actVendorConfirmYn;
    }

    public void setActVendorConfirmYn(String actVendorConfirmYn) {
        this.actVendorConfirmYn = actVendorConfirmYn;
    }

    public String getPreActVendorCd() {
        return preActVendorCd;
    }

    public void setPreActVendorCd(String preActVendorCd) {
        this.preActVendorCd = preActVendorCd;
    }

    public String getPreActVendorNm() {
        return preActVendorNm;
    }

    public void setPreActVendorNm(String preActVendorNm) {
        this.preActVendorNm = preActVendorNm;
    }

    public String getActVendorChngContents() {
        return actVendorChngContents;
    }

    public void setActVendorChngContents(String actVendorChngContents) {
        this.actVendorChngContents = actVendorChngContents;
    }

    public String getActVendorCd() {
        return actVendorCd;
    }

    public void setActVendorCd(String actVendorCd) {
        this.actVendorCd = actVendorCd;
    }

    public String getActVendorNm() {
        return actVendorNm;
    }

    public void setActVendorNm(String actVendorNm) {
        this.actVendorNm = actVendorNm;
    }

    public String getPreActDeptCd() {
        return preActDeptCd;
    }

    public void setPreActDeptCd(String preActDeptCd) {
        this.preActDeptCd = preActDeptCd;
    }

    public String getPreActDeptNm() {
        return preActDeptNm;
    }

    public void setPreActDeptNm(String preActDeptNm) {
        this.preActDeptNm = preActDeptNm;
    }

    public String getActDeptChngContents() {
        return actDeptChngContents;
    }

    public void setActDeptChngContents(String actDeptChngContents) {
        this.actDeptChngContents = actDeptChngContents;
    }

    public String getActDeptCd() {
        return actDeptCd;
    }

    public void setActDeptCd(String actDeptCd) {
        this.actDeptCd = actDeptCd;
    }

    public String getActDeptNm() {
        return actDeptNm;
    }

    public void setActDeptNm(String actDeptNm) {
        this.actDeptNm = actDeptNm;
    }

    public String getActUserId() {
        return actUserId;
    }

    public void setActUserId(String actUserId) {
        this.actUserId = actUserId;
    }

    public String getActUserNm() {
        return actUserNm;
    }

    public void setActUserNm(String actUserNm) {
        this.actUserNm = actUserNm;
    }

    public String getActResultContents() {
        return actResultContents;
    }

    public void setActResultContents(String actResultContents) {
        this.actResultContents = actResultContents;
    }

    public String getActResultReview() {
        return actResultReview;
    }

    public void setActResultReview(String actResultReview) {
        this.actResultReview = actResultReview;
    }

    public String getBefFreq() {
        return befFreq;
    }

    public void setBefFreq(String befFreq) {
        this.befFreq = befFreq;
    }

    public String getBefInten() {
        return befInten;
    }

    public void setBefInten(String befInten) {
        this.befInten = befInten;
    }

    public String getBefDegRisk() {
        return befDegRisk;
    }

    public void setBefDegRisk(String befDegRisk) {
        this.befDegRisk = befDegRisk;
    }

    public String getAftFreq() {
        return aftFreq;
    }

    public void setAftFreq(String aftFreq) {
        this.aftFreq = aftFreq;
    }

    public String getAftInten() {
        return aftInten;
    }

    public void setAftInten(String aftInten) {
        this.aftInten = aftInten;
    }

    public String getAftDegRisk() {
        return aftDegRisk;
    }

    public void setAftDegRisk(String aftDegRisk) {
        this.aftDegRisk = aftDegRisk;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getCreateDt() {
        return createDt;
    }

    public void setCreateDt(String createDt) {
        this.createDt = createDt;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    public String getUpdateDt() {
        return updateDt;
    }

    public void setUpdateDt(String updateDt) {
        this.updateDt = updateDt;
    }

    public String getRiskBookNo() {
        return riskBookNo;
    }

    public void setRiskBookNo(String riskBookNo) {
        this.riskBookNo = riskBookNo;
    }

    public int getNotPlanCnt() {
        return notPlanCnt;
    }

    public void setNotPlanCnt(int notPlanCnt) {
        this.notPlanCnt = notPlanCnt;
    }

    public int getPlanCnt() {
        return planCnt;
    }

    public void setPlanCnt(int planCnt) {
        this.planCnt = planCnt;
    }

    public int getActCnt() {
        return actCnt;
    }

    public void setActCnt(int actCnt) {
        this.actCnt = actCnt;
    }

    public int getCompleteCnt() {
        return completeCnt;
    }

    public void setCompleteCnt(int completeCnt) {
        this.completeCnt = completeCnt;
    }

    public int getNotPlanCnt2() {
        return notPlanCnt2;
    }

    public void setNotPlanCnt2(int notPlanCnt2) {
        this.notPlanCnt2 = notPlanCnt2;
    }

    public int getPlanCnt2() {
        return planCnt2;
    }

    public void setPlanCnt2(int planCnt2) {
        this.planCnt2 = planCnt2;
    }

    public int getActCnt2() {
        return actCnt2;
    }

    public void setActCnt2(int actCnt2) {
        this.actCnt2 = actCnt2;
    }

    public int getCompleteCnt2() {
        return completeCnt2;
    }

    public void setCompleteCnt2(int completeCnt2) {
        this.completeCnt2 = completeCnt2;
    }

    public int getTransitionRate1() {
        return transitionRate1;
    }

    public void setTransitionRate1(int transitionRate1) {
        this.transitionRate1 = transitionRate1;
    }

    public int getTransitionRate2() {
        return transitionRate2;
    }

    public void setTransitionRate2(int transitionRate2) {
        this.transitionRate2 = transitionRate2;
    }

    public int getTransitionRate3() {
        return transitionRate3;
    }

    public void setTransitionRate3(int transitionRate3) {
        this.transitionRate3 = transitionRate3;
    }

    public String getApprRqstNo() {
        return apprRqstNo;
    }

    public void setApprRqstNo(String apprRqstNo) {
        this.apprRqstNo = apprRqstNo;
    }

    public String getBizApprStepCd() {
        return bizApprStepCd;
    }

    public void setBizApprStepCd(String bizApprStepCd) {
        this.bizApprStepCd = bizApprStepCd;
    }

    public String getBizApprStepNm() {
        return bizApprStepNm;
    }

    public void setBizApprStepNm(String bizApprStepNm) {
        this.bizApprStepNm = bizApprStepNm;
    }

    public String getBefImg() {
        return befImg;
    }

    public void setBefImg(String befImg) {
        this.befImg = befImg;
    }

    public String getAftImg() {
        return aftImg;
    }

    public void setAftImg(String aftImg) {
        this.aftImg = aftImg;
    }

    public String getDiscoverMatter() {
        return discoverMatter;
    }

    public void setDiscoverMatter(String discoverMatter) {
        this.discoverMatter = discoverMatter;
    }

    public String getImprTech() {
        return imprTech;
    }

    public void setImprTech(String imprTech) {
        this.imprTech = imprTech;
    }

    public String getImprMgm() {
        return imprMgm;
    }

    public void setImprMgm(String imprMgm) {
        this.imprMgm = imprMgm;
    }

    public String getImprEdu() {
        return imprEdu;
    }

    public void setImprEdu(String imprEdu) {
        this.imprEdu = imprEdu;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public int getAgendaNo() {
        return agendaNo;
    }

    public void setAgendaNo(int agendaNo) {
        this.agendaNo = agendaNo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAgendaDesc() {
        return agendaDesc;
    }

    public void setAgendaDesc(String agendaDesc) {
        this.agendaDesc = agendaDesc;
    }

    public int getCheckListNo() {
        return checkListNo;
    }

    public void setCheckListNo(int checkListNo) {
        this.checkListNo = checkListNo;
    }

    public int getCheckLogNo() {
        return checkLogNo;
    }

    public void setCheckLogNo(int checkLogNo) {
        this.checkLogNo = checkLogNo;
    }

    public String getCheckNm() {
        return checkNm;
    }

    public void setCheckNm(String checkNm) {
        this.checkNm = checkNm;
    }

    public String getInspDt() {
        return inspDt;
    }

    public void setInspDt(String inspDt) {
        this.inspDt = inspDt;
    }
}
