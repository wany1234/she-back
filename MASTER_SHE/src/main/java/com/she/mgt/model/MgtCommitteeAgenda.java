package com.she.mgt.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "위원회 회의안건")
public class MgtCommitteeAgenda {

    @ApiModelProperty(value = "회의록번호")
    public int committeeConfNo;

    @ApiModelProperty(value = "회의안건번호")
    public int agendaNo;

    @ApiModelProperty(value = "회의안건")
    public String agendaDesc;

    @ApiModelProperty(value = "심의내용")
    public String agendaReview;

    @ApiModelProperty(value = "협의결과")
    public String agendaResult;

    @ApiModelProperty(value = "비고")
    public String remark;

    @ApiModelProperty(value = "등록일")
    public String createDt;

    @ApiModelProperty(value = "등록자")
    public String createUserId;

    @ApiModelProperty(value = "수정일")
    public String updateDt;

    @ApiModelProperty(value = "수정자")
    public String updateUserId;

    @ApiModelProperty(value = "개선요청문구")
    public String improvement;

    @ApiModelProperty(value = "즉시조치문구")
    public String imprAct;

    @ApiModelProperty(value = "C개선분류코드")
    public String imprClassCd;

    @ApiModelProperty(value = "종사자의견여부")
    public String workerOpinionYn;

    @ApiModelProperty(value = "서브사업자")
    public String subPlantNm;

    @ApiModelProperty(value = "서브구분명")
    public String subHalfTypeNm;

    @ApiModelProperty(value = "서브제목명")
    public String subConfNm;

    @ApiModelProperty(value = "정렬순서")
    public String sortOrder;

    public MgtCommitteeAgenda() {
    }

    public int getCommitteeConfNo() {
        return committeeConfNo;
    }

    public void setCommitteeConfNo(int committeeConfNo) {
        this.committeeConfNo = committeeConfNo;
    }

    public int getAgendaNo() {
        return agendaNo;
    }

    public void setAgendaNo(int agendaNo) {
        this.agendaNo = agendaNo;
    }

    public String getAgendaDesc() {
        return agendaDesc;
    }

    public void setAgendaDesc(String agendaDesc) {
        this.agendaDesc = agendaDesc;
    }

    public String getAgendaReview() {
        return agendaReview;
    }

    public void setAgendaReview(String agendaReview) {
        this.agendaReview = agendaReview;
    }

    public String getAgendaResult() {
        return agendaResult;
    }

    public void setAgendaResult(String agendaResult) {
        this.agendaResult = agendaResult;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public String getImprovement() {
        return improvement;
    }

    public void setImprovement(String improvement) {
        this.improvement = improvement;
    }

    public String getImprAct() {
        return imprAct;
    }

    public void setImprAct(String imprAct) {
        this.imprAct = imprAct;
    }

    public String getImprClassCd() {
        return imprClassCd;
    }

    public void setImprClassCd(String imprClassCd) {
        this.imprClassCd = imprClassCd;
    }

    public String getWorkerOpinionYn() {
        return workerOpinionYn;
    }

    public void setWorkerOpinionYn(String workerOpinionYn) {
        this.workerOpinionYn = workerOpinionYn;
    }

    public String getSubPlantNm() {
        return subPlantNm;
    }

    public void setSubPlantNm(String subPlantNm) {
        this.subPlantNm = subPlantNm;
    }

    public String getSubHalfTypeNm() {
        return subHalfTypeNm;
    }

    public void setSubHalfTypeNm(String subHalfTypeNm) {
        this.subHalfTypeNm = subHalfTypeNm;
    }

    public String getSubConfNm() {
        return subConfNm;
    }

    public void setSubConfNm(String subConfNm) {
        this.subConfNm = subConfNm;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

}