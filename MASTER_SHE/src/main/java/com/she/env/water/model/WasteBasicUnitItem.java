package com.she.env.water.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "클린센터일지상세 리스트들 model")
public class WasteBasicUnitItem {

    @ApiModelProperty(value = "클린센터번호")
    private int ewtrCleanFacNo;

    @ApiModelProperty(value = "원료 번호")
    private int ewtrIngrNo;

    @ApiModelProperty(value = "약품번호")
    private int ewtrChemNo;

    @ApiModelProperty(value = "공급수 번호")
    private int ewtrSuplNo;

    @ApiModelProperty(value = "공급수 sap코드")
    private String sapCode;

    @ApiModelProperty(value = "배출수 번호")
    private int ewtrDischNo;

    @ApiModelProperty(value = "전력계코드")
    private String pwrMeterCode;

    @ApiModelProperty(value = "자가측정")
    private int ewtrMonPosNo;

    // 2022-03-16 Model 명추가
    @ApiModelProperty(value = "원료명")
    private String ewtrIngrNm;

    @ApiModelProperty(value = "약품명")
    private String ewtrChemNm;

    @ApiModelProperty(value = "용수명")
    private String ewtrSuplNm;

    @ApiModelProperty(value = "측정위치명")
    private String ewtrMonPosNm;

    @ApiModelProperty(value = "폐수명")
    private String ewtrDischNm;

    @ApiModelProperty(value = "단위")
    private String envUnitNm;

    @ApiModelProperty(value = "용수분류")
    private String ewtrSuplClassNm;

    @ApiModelProperty(value = "관리부서")
    private String deptNm;

    @ApiModelProperty(value = "폐수분류명")
    private String ewtrDischClassNm;
    //

    public WasteBasicUnitItem() {
        super();
    }

    public WasteBasicUnitItem(int ewtrCleanFacNo, int ewtrIngrNo, int ewtrChemNo, int ewtrSuplNo, String sapCode, int ewtrDischNo, String pwrMeterCode, int ewtrMonPosNo) {
        super();
        this.ewtrCleanFacNo = ewtrCleanFacNo;
        this.ewtrIngrNo = ewtrIngrNo;
        this.ewtrChemNo = ewtrChemNo;
        this.ewtrSuplNo = ewtrSuplNo;
        this.sapCode = sapCode;
        this.ewtrDischNo = ewtrDischNo;
        this.pwrMeterCode = pwrMeterCode;
        this.ewtrMonPosNo = ewtrMonPosNo;
    }

    public int getEwtrCleanFacNo() {
        return ewtrCleanFacNo;
    }

    public void setEwtrCleanFacNo(int ewtrCleanFacNo) {
        this.ewtrCleanFacNo = ewtrCleanFacNo;
    }

    public int getEwtrIngrNo() {
        return ewtrIngrNo;
    }

    public void setEwtrIngrNo(int ewtrIngrNo) {
        this.ewtrIngrNo = ewtrIngrNo;
    }

    public int getEwtrChemNo() {
        return ewtrChemNo;
    }

    public void setEwtrChemNo(int ewtrChemNo) {
        this.ewtrChemNo = ewtrChemNo;
    }

    public int getEwtrSuplNo() {
        return ewtrSuplNo;
    }

    public void setEwtrSuplNo(int ewtrSuplNo) {
        this.ewtrSuplNo = ewtrSuplNo;
    }

    public String getSapCode() {
        return sapCode;
    }

    public void setSapCode(String sapCode) {
        this.sapCode = sapCode;
    }

    public int getEwtrDischNo() {
        return ewtrDischNo;
    }

    public void setEwtrDischNo(int ewtrDischNo) {
        this.ewtrDischNo = ewtrDischNo;
    }

    public String getPwrMeterCode() {
        return pwrMeterCode;
    }

    public void setPwrMeterCode(String pwrMeterCode) {
        this.pwrMeterCode = pwrMeterCode;
    }

    public int getEwtrMonPosNo() {
        return ewtrMonPosNo;
    }

    public void setEwtrMonPosNo(int ewtrMonPosNo) {
        this.ewtrMonPosNo = ewtrMonPosNo;
    }

    // 2022-03-16

    public String getEwtrIngrNm() {
        return ewtrIngrNm;
    }

    public void setEwtrIngrNm(String ewtrIngrNm) {
        this.ewtrIngrNm = ewtrIngrNm;
    }

    public String getEwtrChemNm() {
        return ewtrChemNm;
    }

    public void setEwtrChemNm(String ewtrChemNm) {
        this.ewtrChemNm = ewtrChemNm;
    }

    public String getEwtrSuplNm() {
        return ewtrSuplNm;
    }

    public void setEwtrSuplNm(String ewtrSuplNm) {
        this.ewtrSuplNm = ewtrSuplNm;
    }

    public String getEwtrMonPosNm() {
        return ewtrMonPosNm;
    }

    public void setEwtrMonPosNm(String ewtrMonPosNm) {
        this.ewtrMonPosNm = ewtrMonPosNm;
    }

    public String getEwtrDischNm() {
        return ewtrDischNm;
    }

    public void setEwtrDischNm(String ewtrDischNm) {
        this.ewtrDischNm = ewtrDischNm;
    }

    public String getEnvUnitNm() {
        return envUnitNm;
    }

    public void setEnvUnitNm(String envUnitNm) {
        this.envUnitNm = envUnitNm;
    }

    public String getEwtrSuplClassNm() {
        return ewtrSuplClassNm;
    }

    public void setEwtrSuplClassNm(String ewtrSuplClassNm) {
        this.ewtrSuplClassNm = ewtrSuplClassNm;
    }

    public String getDeptNm() {
        return deptNm;
    }

    public void setDeptNm(String deptNm) {
        this.deptNm = deptNm;
    }

    public String getEwtrDischClassNm() {
        return ewtrDischClassNm;
    }

    public void setEwtrDischClassNm(String ewtrDischClassNm) {
        this.ewtrDischClassNm = ewtrDischClassNm;
    }

    //

}
