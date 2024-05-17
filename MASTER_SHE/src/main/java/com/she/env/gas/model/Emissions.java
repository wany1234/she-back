package com.she.env.gas.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author ETG
 *
 */
@ApiModel(description = "배출량/에너지사용량")
public class Emissions {

    @ApiModelProperty(value = "사업장")
    private String plantCd;

    @ApiModelProperty(value = "사업장명")
    private String plantNm;

    @ApiModelProperty(value = "부서명")
    private String deptNm;

    @ApiModelProperty(value = "부서코드")
    private String deptCd;

    @ApiModelProperty(value = "공정코드")
    private String processCd;

    @ApiModelProperty(value = "공정명")
    private String processNm;

    @ApiModelProperty(value = "년도")
    private String year;

    @ApiModelProperty(value = "활동시설명")
    private String ghgFcltNm;

    @ApiModelProperty(value = "자체시설명")
    private String fcltNm;

    @ApiModelProperty(value = "중분류")
    private String pActDataNm;

    @ApiModelProperty(value = "소분류")
    private String actDataNm;

    @ApiModelProperty(value = "단위명")
    private String unitNm;

    @ApiModelProperty(value = "ngms번호")
    private String ngmsFceqNum;

    @ApiModelProperty(value = "활동량")
    private String actVol;

    @ApiModelProperty(value = "tier명")
    private String paTierNm;

    @ApiModelProperty(value = "tier")
    private String paTier;

    @ApiModelProperty(value = "단위코드")
    private String ghgUnitCd;

    @ApiModelProperty(value = "매개변수 배출계수")
    private Float paEmiCoeCo2;

    @ApiModelProperty(value = "tj")
    private Float TJ;

    @ApiModelProperty(value = "연간사용량")
    private Float useByYear;

    @ApiModelProperty(value = "누계")
    private Float alltCo2Eq;

    @ApiModelProperty(value = "매개변수 배출계수")
    private Float paEmiCoeCh4;

    @ApiModelProperty(value = "매개변수 배출계수")
    private Float paEmiCoeN20;

    @ApiModelProperty(value = "매개변수 열량계수 순발열량")
    private Float paCalCoeP;

    @ApiModelProperty(value = "매개변수 열량계수 총발열량")
    private Float paCalCoeT;

    @ApiModelProperty(value = "매개변수 열량계수 산화물")
    private Float paOxiCoe;

    @ApiModelProperty(value = "년도별산정방법순번")
    private int ghgFcltNo;

    @ApiModelProperty(value = "매개변수gwp")
    private int paGwpCo2;

    @ApiModelProperty(value = "매개변수gwp")
    private int paGwpCh4;

    @ApiModelProperty(value = "매개변수gwp")
    private int paGwpN2o;

    @ApiModelProperty(value = "toegwp")
    private float toe;

    @ApiModelProperty(value = "활동자료코드")
    private String ghgFcltCd;

    @ApiModelProperty(value = "배출량/에너지사용량 시뮬레이션 순번")
    private int fcltCalcNo;

    @ApiModelProperty(value = "배출량/에너지사용량 순번")
    private int fcltCalcMtdNo;

    @ApiModelProperty(value = "배출량/에너지사용량 구분자")
    private String ghgYn;

    @ApiModelProperty(value = "erp")
    private String erpCode;

    @ApiModelProperty(value = "활동자료명")
    private String actNm;

    @ApiModelProperty(value = "배출활동코드")
    private String ghgOutActCd;

    @ApiModelProperty(value = "활동자료코드")
    private String ghgActDaCd;

    @ApiModelProperty(value = "tCo2Eq")
    private Float tCo2Eq;

    @ApiModelProperty(value = "1월")
    private Float one;

    @ApiModelProperty(value = "2월")
    private Float two;

    @ApiModelProperty(value = "3월")
    private Float three;

    @ApiModelProperty(value = "4월")
    private Float four;

    @ApiModelProperty(value = "5월")
    private Float five;

    @ApiModelProperty(value = "6월")
    private Float six;

    @ApiModelProperty(value = "7월")
    private Float seven;

    @ApiModelProperty(value = "8월")
    private Float eight;

    @ApiModelProperty(value = "9월")
    private Float nine;

    @ApiModelProperty(value = "10월")
    private Float ten;

    @ApiModelProperty(value = "11월")
    private Float eleven;

    @ApiModelProperty(value = "12월")
    private Float twelve;

    @ApiModelProperty(value = "1분기")
    private Float Qone;

    @ApiModelProperty(value = "2분기")
    private Float Qtwo;

    @ApiModelProperty(value = "3분기")
    private Float Qthree;

    @ApiModelProperty(value = "4분기")
    private Float Qfour;

    @ApiModelProperty(value = "활동자료코드")
    private String actDataCd;

    @ApiModelProperty(value = "시뮬레이션 여부")
    private String simYn;

    @ApiModelProperty(value = "등록자")
    private String createUserId;

    @ApiModelProperty(value = "등록일시")
    private String createDt;

    @ApiModelProperty(value = "수정자")
    private String updateUserId;

    @ApiModelProperty(value = "수정일시")
    private String updateDt;

    public Emissions() {
        super();
    }

    public int getGhgFcltNo() {
        return ghgFcltNo;
    }

    public void setGhgFcltNo(int ghgFcltNo) {
        this.ghgFcltNo = ghgFcltNo;
    }

    public float getToe() {
        return toe;
    }

    public void setToe(float toe) {
        this.toe = toe;
    }

    public Float gettCo2Eq() {
        return tCo2Eq;
    }

    public void settCo2Eq(Float tCo2Eq) {
        this.tCo2Eq = tCo2Eq;
    }

    public int getFcltCalcNo() {
        return fcltCalcNo;
    }

    public void setFcltCalcNo(int fcltCalcNo) {
        this.fcltCalcNo = fcltCalcNo;
    }

    public int getFcltCalcMtdNo() {
        return fcltCalcMtdNo;
    }

    public void setFcltCalcMtdNo(int fcltCalcMtdNo) {
        this.fcltCalcMtdNo = fcltCalcMtdNo;
    }

    public String getGhgYn() {
        return ghgYn;
    }

    public void setGhgYn(String ghgYn) {
        this.ghgYn = ghgYn;
    }

    public String getPlantCd() {
        return plantCd;
    }

    public Float getUseByYear() {
        return useByYear;
    }

    public void setUseByYear(Float useByYear) {
        this.useByYear = useByYear;
    }

    public Float getAlltCo2Eq() {
        return alltCo2Eq;
    }

    public void setAlltCo2Eq(Float alltCo2Eq) {
        this.alltCo2Eq = alltCo2Eq;
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

    public String getDeptNm() {
        return deptNm;
    }

    public void setDeptNm(String deptNm) {
        this.deptNm = deptNm;
    }

    public String getDeptCd() {
        return deptCd;
    }

    public void setDeptCd(String deptCd) {
        this.deptCd = deptCd;
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

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
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

    public String getpActDataNm() {
        return pActDataNm;
    }

    public void setpActDataNm(String pActDataNm) {
        this.pActDataNm = pActDataNm;
    }

    public String getActDataNm() {
        return actDataNm;
    }

    public void setActDataNm(String actDataNm) {
        this.actDataNm = actDataNm;
    }

    public String getUnitNm() {
        return unitNm;
    }

    public void setUnitNm(String unitNm) {
        this.unitNm = unitNm;
    }

    public String getActVol() {
        return actVol;
    }

    public void setActVol(String actVol) {
        this.actVol = actVol;
    }

    public String getPaTierNm() {
        return paTierNm;
    }

    public void setPaTierNm(String paTierNm) {
        this.paTierNm = paTierNm;
    }

    public String getPaTier() {
        return paTier;
    }

    public void setPaTier(String paTier) {
        this.paTier = paTier;
    }

    public String getGhgUnitCd() {
        return ghgUnitCd;
    }

    public void setGhgUnitCd(String ghgUnitCd) {
        this.ghgUnitCd = ghgUnitCd;
    }

    public Float getPaEmiCoeCo2() {
        return paEmiCoeCo2;
    }

    public void setPaEmiCoeCo2(Float paEmiCoeCo2) {
        this.paEmiCoeCo2 = paEmiCoeCo2;
    }

    public Float getPaEmiCoeCh4() {
        return paEmiCoeCh4;
    }

    public void setPaEmiCoeCh4(Float paEmiCoeCh4) {
        this.paEmiCoeCh4 = paEmiCoeCh4;
    }

    public Float getPaEmiCoeN20() {
        return paEmiCoeN20;
    }

    public void setPaEmiCoeN20(Float paEmiCoeN20) {
        this.paEmiCoeN20 = paEmiCoeN20;
    }

    public Float getPaCalCoeP() {
        return paCalCoeP;
    }

    public void setPaCalCoeP(Float paCalCoeP) {
        this.paCalCoeP = paCalCoeP;
    }

    public Float getPaCalCoeT() {
        return paCalCoeT;
    }

    public void setPaCalCoeT(Float paCalCoeT) {
        this.paCalCoeT = paCalCoeT;
    }

    public Float getPaOxiCoe() {
        return paOxiCoe;
    }

    public void setPaOxiCoe(Float paOxiCoe) {
        this.paOxiCoe = paOxiCoe;
    }

    public int getPaGwpCo2() {
        return paGwpCo2;
    }

    public void setPaGwpCo2(int paGwpCo2) {
        this.paGwpCo2 = paGwpCo2;
    }

    public int getPaGwpCh4() {
        return paGwpCh4;
    }

    public void setPaGwpCh4(int paGwpCh4) {
        this.paGwpCh4 = paGwpCh4;
    }

    public int getPaGwpN2o() {
        return paGwpN2o;
    }

    public void setPaGwpN2o(int paGwpN2o) {
        this.paGwpN2o = paGwpN2o;
    }

    public String getGhgFcltCd() {
        return ghgFcltCd;
    }

    public void setGhgFcltCd(String ghgFcltCd) {
        this.ghgFcltCd = ghgFcltCd;
    }

    public String getErpCode() {
        return erpCode;
    }

    public void setErpCode(String erpCode) {
        this.erpCode = erpCode;
    }

    public String getActNm() {
        return actNm;
    }

    public void setActNm(String actNm) {
        this.actNm = actNm;
    }

    public String getGhgOutActCd() {
        return ghgOutActCd;
    }

    public void setGhgOutActCd(String ghgOutActCd) {
        this.ghgOutActCd = ghgOutActCd;
    }

    public String getGhgActDaCd() {
        return ghgActDaCd;
    }

    public void setGhgActDaCd(String ghgActDaCd) {
        this.ghgActDaCd = ghgActDaCd;
    }

    public Float getOne() {
        return one;
    }

    public void setOne(Float one) {
        this.one = one;
    }

    public Float getTwo() {
        return two;
    }

    public void setTwo(Float two) {
        this.two = two;
    }

    public Float getThree() {
        return three;
    }

    public void setThree(Float three) {
        this.three = three;
    }

    public Float getFour() {
        return four;
    }

    public void setFour(Float four) {
        this.four = four;
    }

    public Float getFive() {
        return five;
    }

    public void setFive(Float five) {
        this.five = five;
    }

    public Float getSix() {
        return six;
    }

    public void setSix(Float six) {
        this.six = six;
    }

    public Float getSeven() {
        return seven;
    }

    public void setSeven(Float seven) {
        this.seven = seven;
    }

    public Float getEight() {
        return eight;
    }

    public void setEight(Float eight) {
        this.eight = eight;
    }

    public Float getNine() {
        return nine;
    }

    public void setNine(Float nine) {
        this.nine = nine;
    }

    public Float getTen() {
        return ten;
    }

    public void setTen(Float ten) {
        this.ten = ten;
    }

    public Float getEleven() {
        return eleven;
    }

    public void setEleven(Float eleven) {
        this.eleven = eleven;
    }

    public Float getTwelve() {
        return twelve;
    }

    public void setTwelve(Float twelve) {
        this.twelve = twelve;
    }

    public String getNgmsFceqNum() {
        return ngmsFceqNum;
    }

    public void setNgmsFceqNum(String ngmsFceqNum) {
        this.ngmsFceqNum = ngmsFceqNum;
    }

    public Float getTJ() {
        return TJ;
    }

    public void setTJ(Float tJ) {
        TJ = tJ;
    }

    public Float getQone() {
        return Qone;
    }

    public void setQone(Float qone) {
        Qone = qone;
    }

    public Float getQtwo() {
        return Qtwo;
    }

    public void setQtwo(Float qtwo) {
        Qtwo = qtwo;
    }

    public Float getQthree() {
        return Qthree;
    }

    public void setQthree(Float qthree) {
        Qthree = qthree;
    }

    public Float getQfour() {
        return Qfour;
    }

    public void setQfour(Float qfour) {
        Qfour = qfour;
    }

    public String getActDataCd() {
        return actDataCd;
    }

    public void setActDataCd(String actDataCd) {
        this.actDataCd = actDataCd;
    }

    public String getSimYn() {
        return simYn;
    }

    public void setSimYn(String simYn) {
        this.simYn = simYn;
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
}
