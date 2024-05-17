package com.she.env.gas.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "배출시설관리마스터")
public class DischargeFacility {

    @ApiModelProperty(value = "배출시설순번")
    public int ghgFcltNo;

    @ApiModelProperty(value = "배출시설그룹순번")
    public int ghgFcltGrpNo;

    @ApiModelProperty(value = "대기 배출시설 번호")
    public int eairDischFacNo;

    @ApiModelProperty(value = "배출시설코드")
    public String ghgFcltCd;

    @ApiModelProperty(value = "배출시설명")
    public String ghgFcltNm;

    @ApiModelProperty(value = "자체시설명")
    public String fcltNm;

    @ApiModelProperty(value = "사업장")
    public String plantCd;

    @ApiModelProperty(value = "사업장명")
    public String plantNm;

    @ApiModelProperty(value = "공장(인)코드")
    public String lcnFactCd;

    @ApiModelProperty(value = "ngms시설일련번허")
    public String ngmsFceqNum;

    @ApiModelProperty(value = "시설코드")
    public String erpCode;

    @ApiModelProperty(value = "부서코드")
    public String deptCd;

    @ApiModelProperty(value = "부서명")
    public String deptNm;

    @ApiModelProperty(value = "배출활동수")
    public int ghgCount;

    @ApiModelProperty(value = "공정코드")
    public String processCd;

    @ApiModelProperty(value = "공정명")
    public String processNm;

    @ApiModelProperty(value = "사용변경사유")
    public String chgReason;

    @ApiModelProperty(value = "사용여부")
    public String useYn;

    @ApiModelProperty(value = "사용여부명")
    public String useYnNm;

    @ApiModelProperty(value = "소규모배출시설여부")
    public String sFcltYn;

    @ApiModelProperty(value = "정렬순서")
    public int sortOrder;

    @ApiModelProperty(value = "등록자")
    public String createUserId;

    @ApiModelProperty(value = "등록자")
    public String createUserNm;

    @ApiModelProperty(value = "등록일시")
    public String createDt;

    @ApiModelProperty(value = "수정자")
    public String updateUserId;

    @ApiModelProperty(value = "수정일시")
    public String updateDt;

    @ApiModelProperty(value = "배출활동자료리스트")
    public List<DischargeFacilityData> disActDataList;

    @ApiModelProperty(value = "배출활동자료리스트")
    public List<DischargeFacilityHistory> disActDataHistoryList;

    @ApiModelProperty(value = "개정번호")
    private int revisionNo;

    @ApiModelProperty(value = "유입경로")
    public String saveOrRev;

    @ApiModelProperty(value = "작성자")
    private String writerUserNm;

    @ApiModelProperty(value = "작성일")
    private String writerDt;

    public String getSaveOrRev() {
        return saveOrRev;
    }

    public void setSaveOrRev(String saveOrRev) {
        this.saveOrRev = saveOrRev;
    }

    public int getRevisionNo() {
        return revisionNo;
    }

    public void setRevisionNo(int revisionNo) {
        this.revisionNo = revisionNo;
    }

    public DischargeFacility() {
        super();
    }

    public DischargeFacility(int ghgFcltNo, int ghgFcltGrpNo, String ghgFcltCd, String ghgFcltNm, String fcltNm, String plantCd, String plantNm, String lcnFactCd, String ngmsFceqNum, String erpCode, String deptCd, String deptNm, int ghgCount, String processCd, String processNm, String chgReason, String useYn, String useYnNm, int sortOrder,
            String createUserId, String createUserNm, String createDt, String updateUserId, String updateDt, List<DischargeFacilityData> disActDataList, int revisionNo, String saveOrRev, String sFcltYn, String writerUserNm, String writerDt) {
        super();
        this.ghgFcltNo = ghgFcltNo;
        this.ghgFcltGrpNo = ghgFcltGrpNo;
        this.ghgFcltCd = ghgFcltCd;
        this.ghgFcltNm = ghgFcltNm;
        this.fcltNm = fcltNm;
        this.plantCd = plantCd;
        this.plantNm = plantNm;
        this.lcnFactCd = lcnFactCd;
        this.ngmsFceqNum = ngmsFceqNum;
        this.erpCode = erpCode;
        this.deptCd = deptCd;
        this.deptNm = deptNm;
        this.ghgCount = ghgCount;
        this.processCd = processCd;
        this.processNm = processNm;
        this.chgReason = chgReason;
        this.useYn = useYn;
        this.useYnNm = useYnNm;
        this.sortOrder = sortOrder;
        this.createUserId = createUserId;
        this.createUserNm = createUserNm;
        this.createDt = createDt;
        this.updateUserId = updateUserId;
        this.updateDt = updateDt;
        this.disActDataList = disActDataList;
        this.revisionNo = revisionNo;
        this.saveOrRev = saveOrRev;
        this.sFcltYn = sFcltYn;
        this.writerUserNm = writerUserNm;
        this.writerDt = writerDt;
    }

    public int getGhgCount() {
        return ghgCount;
    }

    public List<DischargeFacilityHistory> getDisActDataHistoryList() {
        return disActDataHistoryList;
    }

    public void setDisActDataHistoryList(List<DischargeFacilityHistory> disActDataHistoryList) {
        this.disActDataHistoryList = disActDataHistoryList;
    }

    public void setGhgCount(int ghgCount) {
        this.ghgCount = ghgCount;
    }

    public String getCreateUserNm() {
        return createUserNm;
    }

    public void setCreateUserNm(String createUserNm) {
        this.createUserNm = createUserNm;
    }

    public int getGhgFcltGrpNo() {
        return ghgFcltGrpNo;
    }

    public void setGhgFcltGrpNo(int ghgFcltGrpNo) {
        this.ghgFcltGrpNo = ghgFcltGrpNo;
    }

    public String getFcltNm() {
        return fcltNm;
    }

    public void setFcltNm(String fcltNm) {
        this.fcltNm = fcltNm;
    }

    public String getChgReason() {
        return chgReason;
    }

    public void setChgReason(String chgReason) {
        this.chgReason = chgReason;
    }

    public List<DischargeFacilityData> getDisActDataList() {
        return disActDataList;
    }

    public void setDisActDataList(List<DischargeFacilityData> disActDataList) {
        this.disActDataList = disActDataList;
    }

    public int getGhgFcltNo() {
        return ghgFcltNo;
    }

    public void setGhgFcltNo(int ghgFcltNo) {
        this.ghgFcltNo = ghgFcltNo;
    }

    public String getGhgFcltCd() {
        return ghgFcltCd;
    }

    public void setGhgFcltCd(String ghgFcltCd) {
        this.ghgFcltCd = ghgFcltCd;
    }

    public String getGhgFcltNm() {
        return ghgFcltNm;
    }

    public void setGhgFcltNm(String ghgFcltNm) {
        this.ghgFcltNm = ghgFcltNm;
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

    public String getLcnFactCd() {
        return lcnFactCd;
    }

    public void setLcnFactCd(String lcnFactCd) {
        this.lcnFactCd = lcnFactCd;
    }

    public String getNgmsFceqNum() {
        return ngmsFceqNum;
    }

    public void setNgmsFceqNum(String ngmsFceqNum) {
        this.ngmsFceqNum = ngmsFceqNum;
    }

    public String getErpCode() {
        return erpCode;
    }

    public void setErpCode(String erpCode) {
        this.erpCode = erpCode;
    }

    public String getDeptCd() {
        return deptCd;
    }

    public void setDeptCd(String deptCd) {
        this.deptCd = deptCd;
    }

    public String getDeptNm() {
        return deptNm;
    }

    public void setDeptNm(String deptNm) {
        this.deptNm = deptNm;
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

    public String getUseYn() {
        return useYn;
    }

    public void setUseYn(String useYn) {
        this.useYn = useYn;
    }

    public String getUseYnNm() {
        return useYnNm;
    }

    public void setUseYnNm(String useYnNm) {
        this.useYnNm = useYnNm;
    }

    public int getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(int sortOrder) {
        this.sortOrder = sortOrder;
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

    public int getEairDischFacNo() {
        return eairDischFacNo;
    }

    public void setEairDischFacNo(int eairDischFacNo) {
        this.eairDischFacNo = eairDischFacNo;
    }

    public String getsFcltYn() {
        return sFcltYn;
    }

    public void setsFcltYn(String sFcltYn) {
        this.sFcltYn = sFcltYn;
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

}
