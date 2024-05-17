package com.she.env.water.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "클린센터")
public class WasteBasicUnit {

    @ApiModelProperty(value = "클린센터번호")
    public int ewtrCleanFacNo;

    @ApiModelProperty(value = "클린센터명")
    public String ewtrCleanFacNm;

    public String deptCd;

    public String deptNm;

    public String plantCd;

    public String plantNm;

    @ApiModelProperty(value = "sap코드")
    public String sapCode;

    @ApiModelProperty(value = "클린센터분류코드")
    public String ewtrCleanFacClassCd;

    @ApiModelProperty(value = "클린센터분류명")
    public String ewtrCleanFacClassNm;

    @ApiModelProperty(value = "비고")
    public String remark;

    public String useYn;

    public String useYnNm;

    @ApiModelProperty(value = "약품등록구분자")
    public String wtrChemRegCd;

    public int sortOrder;

    public String createUserId;

    public String createUserNm;

    public String createDt;

    public String updateUserId;

    public String updateUserNm;

    public String updateDt;

    @ApiModelProperty(value = "약품리스트")
    public List<WasteBasicUnitItem> chemBase;

    @ApiModelProperty(value = "배출수")
    public List<WasteBasicUnitItem> discharge;

    @ApiModelProperty(value = "사용원료")
    public List<WasteBasicUnitItem> ingredient;

    @ApiModelProperty(value = "자가측정지점")
    public List<WasteBasicUnitItem> monPos;

    @ApiModelProperty(value = "공급수")
    public List<WasteBasicUnitItem> supply;

    @ApiModelProperty(value = "작성자")
    private String writerUserNm;

    @ApiModelProperty(value = "작성일")
    private String writerDt;

    public WasteBasicUnit() {
        super();
    }

    public WasteBasicUnit(int ewtrCleanFacNo, String ewtrCleanFacNm, String deptCd, String deptNm, String plantCd, String plantNm, String sapCode, String ewtrCleanFacClassCd, String ewtrCleanFacClassNm, String remark, String useYn, String useYnNm, String wtrChemRegCd, int sortOrder, String createUserId, String createUserNm, String createDt,
            String updateUserId, String updateUserNm, String updateDt, List<WasteBasicUnitItem> chemBase, List<WasteBasicUnitItem> discharge, List<WasteBasicUnitItem> ingredient, List<WasteBasicUnitItem> monPos, List<WasteBasicUnitItem> supply, String writerUserNm, String writerDt) {
        super();
        this.ewtrCleanFacNo = ewtrCleanFacNo;
        this.ewtrCleanFacNm = ewtrCleanFacNm;
        this.deptCd = deptCd;
        this.deptNm = deptNm;
        this.plantCd = plantCd;
        this.plantNm = plantNm;
        this.sapCode = sapCode;
        this.ewtrCleanFacClassCd = ewtrCleanFacClassCd;
        this.ewtrCleanFacClassNm = ewtrCleanFacClassNm;
        this.remark = remark;
        this.useYn = useYn;
        this.useYnNm = useYnNm;
        this.wtrChemRegCd = wtrChemRegCd;
        this.sortOrder = sortOrder;
        this.createUserId = createUserId;
        this.createUserNm = createUserNm;
        this.createDt = createDt;
        this.updateUserId = updateUserId;
        this.updateUserNm = updateUserNm;
        this.updateDt = updateDt;
        this.chemBase = chemBase;
        this.discharge = discharge;
        this.ingredient = ingredient;
        this.monPos = monPos;
        this.supply = supply;
        this.writerUserNm = writerUserNm;
        this.writerDt = writerDt;
    }

    public List<WasteBasicUnitItem> getChemBase() {
        return chemBase;
    }

    public void setChemBase(List<WasteBasicUnitItem> chemBase) {
        this.chemBase = chemBase;
    }

    public List<WasteBasicUnitItem> getDischarge() {
        return discharge;
    }

    public void setDischarge(List<WasteBasicUnitItem> discharge) {
        this.discharge = discharge;
    }

    public List<WasteBasicUnitItem> getIngredient() {
        return ingredient;
    }

    public void setIngredient(List<WasteBasicUnitItem> ingredient) {
        this.ingredient = ingredient;
    }

    public List<WasteBasicUnitItem> getMonPos() {
        return monPos;
    }

    public void setMonPos(List<WasteBasicUnitItem> monPos) {
        this.monPos = monPos;
    }

    public List<WasteBasicUnitItem> getSupply() {
        return supply;
    }

    public void setSupply(List<WasteBasicUnitItem> supply) {
        this.supply = supply;
    }

    public int getEwtrCleanFacNo() {
        return ewtrCleanFacNo;
    }

    public void setEwtrCleanFacNo(int ewtrCleanFacNo) {
        this.ewtrCleanFacNo = ewtrCleanFacNo;
    }

    public String getEwtrCleanFacNm() {
        return ewtrCleanFacNm;
    }

    public void setEwtrCleanFacNm(String ewtrCleanFacNm) {
        this.ewtrCleanFacNm = ewtrCleanFacNm;
    }

    public String getDeptCd() {
        return deptCd;
    }

    public void setDeptCd(String deptCd) {
        this.deptCd = deptCd;
    }

    public String getSapCode() {
        return sapCode;
    }

    public void setSapCode(String sapCode) {
        this.sapCode = sapCode;
    }

    public String getDeptNm() {
        return deptNm;
    }

    public void setDeptNm(String deptNm) {
        this.deptNm = deptNm;
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

    public String getEwtrCleanFacClassCd() {
        return ewtrCleanFacClassCd;
    }

    public void setEwtrCleanFacClassCd(String ewtrCleanFacClassCd) {
        this.ewtrCleanFacClassCd = ewtrCleanFacClassCd;
    }

    public String getEwtrCleanFacClassNm() {
        return ewtrCleanFacClassNm;
    }

    public void setEwtrCleanFacClassNm(String ewtrCleanFacClassNm) {
        this.ewtrCleanFacClassNm = ewtrCleanFacClassNm;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public String getWtrChemRegCd() {
        return wtrChemRegCd;
    }

    public void setWtrChemRegCd(String wtrChemRegCd) {
        this.wtrChemRegCd = wtrChemRegCd;
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

    public String getUpdateUserNm() {
        return updateUserNm;
    }

    public void setUpdateUserNm(String updateUserNm) {
        this.updateUserNm = updateUserNm;
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
