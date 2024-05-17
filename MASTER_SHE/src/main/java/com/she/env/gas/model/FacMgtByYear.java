package com.she.env.gas.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "년도별배출시설관리")
public class FacMgtByYear {
    @ApiModelProperty(value = "배출시설순번")
    public int ghgFcltNo;

    @ApiModelProperty(value = "년도")
    public String year;

    @ApiModelProperty(value = "배출시설그룹순번")
    public int ghgFcltGrpNo;

    @ApiModelProperty(value = "배출시설코드")
    public String ghgFcltCd;

    @ApiModelProperty(value = "저장됨")
    public String isSave;

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

    @ApiModelProperty(value = "등록자")
    public String createUserId;

    @ApiModelProperty(value = "등록자")
    public String createUserNm;

    @ApiModelProperty(value = "등록일시")
    public String createDt;

    @ApiModelProperty(value = "수정자")
    public String updateUserId;

    @ApiModelProperty(value = "수정자")
    public String updateUserNm;

    @ApiModelProperty(value = "수정일시")
    public String updateDt;

    @ApiModelProperty(value = "배출활동자료리스트")
    public List<DischargeFacilityData> disActDataList;

    @ApiModelProperty(value = "작성자")
    private String writerUserNm;

    @ApiModelProperty(value = "작성일")
    private String writerDt;

    public FacMgtByYear(int ghgFcltNo, String year, int ghgFcltGrpNo, String ghgFcltCd, String ghgFcltNm, String fcltNm, String plantCd, String plantNm, String lcnFactCd, String ngmsFceqNum, String erpCode, String deptCd, String deptNm, int ghgCount, String processCd, String processNm, String createUserId, String createUserNm, String createDt,
            String updateUserId, String updateUserNm, String updateDt, List<DischargeFacilityData> disActDataList, String writerUserNm, String writerDt) {
        super();
        this.ghgFcltNo = ghgFcltNo;
        this.year = year;
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
        this.createUserId = createUserId;
        this.createUserNm = createUserNm;
        this.createDt = createDt;
        this.updateUserId = updateUserId;
        this.updateUserNm = updateUserNm;
        this.updateDt = updateDt;
        this.disActDataList = disActDataList;
        this.writerUserNm = writerUserNm;
        this.writerDt = writerDt;
    }

    public String getIsSave() {
        return isSave;
    }

    public void setIsSave(String isSave) {
        this.isSave = isSave;
    }

    public String getUpdateUserNm() {
        return updateUserNm;
    }

    public void setUpdateUserNm(String updateUserNm) {
        this.updateUserNm = updateUserNm;
    }

    public List<DischargeFacilityData> getDisActDataList() {
        return disActDataList;
    }

    public void setDisActDataList(List<DischargeFacilityData> disActDataList) {
        this.disActDataList = disActDataList;
    }

    public int getGhgFcltGrpNo() {
        return ghgFcltGrpNo;
    }

    public void setGhgFcltGrpNo(int ghgFcltGrpNo) {
        this.ghgFcltGrpNo = ghgFcltGrpNo;
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

    public String getFcltNm() {
        return fcltNm;
    }

    public void setFcltNm(String fcltNm) {
        this.fcltNm = fcltNm;
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

    public int getGhgCount() {
        return ghgCount;
    }

    public void setGhgCount(int ghgCount) {
        this.ghgCount = ghgCount;
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

    public FacMgtByYear() {
        super();
    }

    public int getGhgFcltNo() {
        return ghgFcltNo;
    }

    public void setGhgFcltNo(int ghgFcltNo) {
        this.ghgFcltNo = ghgFcltNo;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
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
