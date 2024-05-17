package com.she.mgt.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "산업안전보건위원회")
public class IndustrialSafetyHealthCommittee {

    @ApiModelProperty(value = "회의록번호")
    public int committeeConfNo;

    @ApiModelProperty(value = "회의체번호")
    public int meetingGrpNo;

    @ApiModelProperty(value = "회의체명")
    public String meetingGrpNm;

    @ApiModelProperty(value = "구분코드")
    public String cmiClsCd;

    @ApiModelProperty(value = "구분코드명칭")
    public String cmiClsNm;

    @ApiModelProperty(value = "사업장코드")
    public String plantCd;

    @ApiModelProperty(value = "사업장명")
    public String plantNm;

    @ApiModelProperty(value = "회의록명")
    public String confNm;

    @ApiModelProperty(value = "회의일")
    public String confDate;

    @ApiModelProperty(value = "회의장소")
    public String confPlace;

    @ApiModelProperty(value = "비고")
    public String remark;

    @ApiModelProperty(value = "진행상태코드")
    public String progressStepCd;

    @ApiModelProperty(value = "진행상태명")
    public String progressStepNm;

    @ApiModelProperty(value = "진행단계코드")
    public String stateCd;

    @ApiModelProperty(value = "진행단계명")
    public String stateNm;

    @ApiModelProperty(value = "등록일")
    public String createDt;

    @ApiModelProperty(value = "등록자ID")
    public String createUserId;

    @ApiModelProperty(value = "등록자명")
    public String createUserNm;

    @ApiModelProperty(value = "수정일")
    public String updateDt;

    @ApiModelProperty(value = "수정자ID")
    public String updateUserId;

    @ApiModelProperty(value = "수정자명")
    public String updateUserNm;

    @ApiModelProperty(value = "C개선분류코드")
    public String imprClassCd;

    @ApiModelProperty(value = "결재진행상태")
    public String bizApprStepNm;

    @ApiModelProperty(value = "결재진행상태코드")
    public String bizApprStepCd;

    @ApiModelProperty(value = "위원회 회의안건")
    public List<MgtCommitteeAgenda> mgtCommitteeAgendas;

    @ApiModelProperty(value = "삭제할 위원회 회의안건")
    public List<MgtCommitteeAgenda> deleteMgtCommitteeAgendas;

    @ApiModelProperty(value = "개선사항")
    public List<MgtCommitteeAgendaImpr> mgtCommitteeAgendaImprs;

    @ApiModelProperty(value = "삭제할 개선사항")
    public List<MgtCommitteeAgendaImpr> deleteMgtCommitteeAgendaImprs;

    @ApiModelProperty(value = "참석자")
    public List<MgtCommitteePsn> mgtCommitteePsns;

    @ApiModelProperty(value = "결재진행번호")
    public String apprRqstNo;

    @ApiModelProperty(value = "작성자")
    public String writerUserNm;

    @ApiModelProperty(value = "작성일")
    public String writerDt;

    @ApiModelProperty(value = "대상년도")
    public String year;

    @ApiModelProperty(value = "주간부서코드")
    public String mainDeptCd;

    @ApiModelProperty(value = "주간부서명")
    public String mainDeptNm;

    @ApiModelProperty(value = "개선요청")
    public String requestCnt;

    @ApiModelProperty(value = "조치미완료")
    public String incompletCnt;

    @ApiModelProperty(value = "조치기한초과")
    public String overdueCnt;

    @ApiModelProperty(value = "조치완료")
    public String completCnt;

    @ApiModelProperty(value = "상하반기구분코드")
    public String halfTypeCd;

    @ApiModelProperty(value = "상하반기구분명")
    public String halfTypeNm;

    @ApiModelProperty(value = "기타채널상세")
    public String etcDesc;

    public String getApprRqstNo() {
        return apprRqstNo;
    }

    public void setApprRqstNo(String apprRqstNo) {
        this.apprRqstNo = apprRqstNo;
    }

    public IndustrialSafetyHealthCommittee() {
    }

    public int getCommitteeConfNo() {
        return committeeConfNo;
    }

    public void setCommitteeConfNo(int committeeConfNo) {
        this.committeeConfNo = committeeConfNo;
    }

    public int getMeetingGrpNo() {
        return meetingGrpNo;
    }

    public void setMeetingGrpNo(int meetingGrpNo) {
        this.meetingGrpNo = meetingGrpNo;
    }

    public String getMeetingGrpNm() {
        return meetingGrpNm;
    }

    public void setMeetingGrpNm(String meetingGrpNm) {
        this.meetingGrpNm = meetingGrpNm;
    }

    public String getCmiClsCd() {
        return cmiClsCd;
    }

    public void setCmiClsCd(String cmiClsCd) {
        this.cmiClsCd = cmiClsCd;
    }

    public String getCmiClsNm() {
        return cmiClsNm;
    }

    public void setCmiClsNm(String cmiClsNm) {
        this.cmiClsNm = cmiClsNm;
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

    public String getConfNm() {
        return confNm;
    }

    public void setConfNm(String confNm) {
        this.confNm = confNm;
    }

    public String getConfDate() {
        return confDate;
    }

    public void setConfDate(String confDate) {
        this.confDate = confDate;
    }

    public String getConfPlace() {
        return confPlace;
    }

    public void setConfPlace(String confPlace) {
        this.confPlace = confPlace;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getProgressStepCd() {
        return progressStepCd;
    }

    public void setProgressStepCd(String progressStepCd) {
        this.progressStepCd = progressStepCd;
    }

    public String getProgressStepNm() {
        return progressStepNm;
    }

    public void setProgressStepNm(String progressStepNm) {
        this.progressStepNm = progressStepNm;
    }

    public String getCreateDt() {
        return createDt;
    }

    public void setCreateDt(String createDt) {
        this.createDt = createDt;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getCreateUserNm() {
        return createUserNm;
    }

    public void setCreateUserNm(String createUserNm) {
        this.createUserNm = createUserNm;
    }

    public String getUpdateDt() {
        return updateDt;
    }

    public void setUpdateDt(String updateDt) {
        this.updateDt = updateDt;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    public String getUpdateUserNm() {
        return updateUserNm;
    }

    public void setUpdateUserNm(String updateUserNm) {
        this.updateUserNm = updateUserNm;
    }

    public String getImprClassCd() {
        return imprClassCd;
    }

    public void setImprClassCd(String imprClassCd) {
        this.imprClassCd = imprClassCd;
    }

    public String getBizApprStepNm() {
        return bizApprStepNm;
    }

    public void setBizApprStepNm(String bizApprStepNm) {
        this.bizApprStepNm = bizApprStepNm;
    }

    public String getBizApprStepCd() {
        return bizApprStepCd;
    }

    public void setBizApprStepCd(String bizApprStepCd) {
        this.bizApprStepCd = bizApprStepCd;
    }

    public List<MgtCommitteeAgenda> getMgtCommitteeAgendas() {
        return mgtCommitteeAgendas;
    }

    public void setMgtCommitteeAgendas(List<MgtCommitteeAgenda> mgtCommitteeAgendas) {
        this.mgtCommitteeAgendas = mgtCommitteeAgendas;
    }

    public List<MgtCommitteeAgenda> getDeleteMgtCommitteeAgendas() {
        return deleteMgtCommitteeAgendas;
    }

    public void setDeleteMgtCommitteeAgendas(List<MgtCommitteeAgenda> deleteMgtCommitteeAgendas) {
        this.deleteMgtCommitteeAgendas = deleteMgtCommitteeAgendas;
    }

    public List<MgtCommitteeAgendaImpr> getMgtCommitteeAgendaImprs() {
        return mgtCommitteeAgendaImprs;
    }

    public void setMgtCommitteeAgendaImprs(List<MgtCommitteeAgendaImpr> mgtCommitteeAgendaImprs) {
        this.mgtCommitteeAgendaImprs = mgtCommitteeAgendaImprs;
    }

    public List<MgtCommitteeAgendaImpr> getDeleteMgtCommitteeAgendaImprs() {
        return deleteMgtCommitteeAgendaImprs;
    }

    public void setDeleteMgtCommitteeAgendaImprs(List<MgtCommitteeAgendaImpr> deleteMgtCommitteeAgendaImprs) {
        this.deleteMgtCommitteeAgendaImprs = deleteMgtCommitteeAgendaImprs;
    }

    public List<MgtCommitteePsn> getMgtCommitteePsns() {
        return mgtCommitteePsns;
    }

    public void setMgtCommitteePsns(List<MgtCommitteePsn> mgtCommitteePsns) {
        this.mgtCommitteePsns = mgtCommitteePsns;
    }

    public String getWriterUserNm() {
        return writerUserNm;
    }

    public void setWriterUserNm(String writerUserNm) {
        this.writerUserNm = writerUserNm;
    }

    public String getWriterDt() {
        return writerDt;
    }

    public void setWriterDt(String writerDt) {
        this.writerDt = writerDt;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMainDeptCd() {
        return mainDeptCd;
    }

    public void setMainDeptCd(String mainDeptCd) {
        this.mainDeptCd = mainDeptCd;
    }

    public String getMainDeptNm() {
        return mainDeptNm;
    }

    public void setMainDeptNm(String mainDeptNm) {
        this.mainDeptNm = mainDeptNm;
    }

    public String getRequestCnt() {
        return requestCnt;
    }

    public void setRequestCnt(String requestCnt) {
        this.requestCnt = requestCnt;
    }

    public String getIncompletCnt() {
        return incompletCnt;
    }

    public void setIncompletCnt(String incompletCnt) {
        this.incompletCnt = incompletCnt;
    }

    public String getOverdueCnt() {
        return overdueCnt;
    }

    public void setOverdueCnt(String overdueCnt) {
        this.overdueCnt = overdueCnt;
    }

    public String getCompletCnt() {
        return completCnt;
    }

    public void setCompletCnt(String completCnt) {
        this.completCnt = completCnt;
    }

    public String getHalfTypeCd() {
        return halfTypeCd;
    }

    public void setHalfTypeCd(String halfTypeCd) {
        this.halfTypeCd = halfTypeCd;
    }

    public String getEtcDesc() {
        return etcDesc;
    }

    public void setEtcDesc(String etcDesc) {
        this.etcDesc = etcDesc;
    }

}
