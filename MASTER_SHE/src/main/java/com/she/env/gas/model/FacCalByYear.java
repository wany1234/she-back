package com.she.env.gas.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class FacCalByYear {
    @ApiModelProperty(value = "배출시설순번")
    private int ghgFcltNo;

    @ApiModelProperty(value = "년도")
    private String year;

    @ApiModelProperty(value = "배출시설그룹순번")
    private int ghgFcltGrpNo;

    @ApiModelProperty(value = "배출시설그룹순번")
    private int fcltCalcMtdNo;

    @ApiModelProperty(value = "배출시설코드")
    private String ghgFcltCd;

    @ApiModelProperty(value = "저장됨")
    private String isSave;

    @ApiModelProperty(value = "배출시설명")
    private String ghgFcltNm;

    @ApiModelProperty(value = "배출시설명")
    private String ghgOutActCd;

    @ApiModelProperty(value = "배출시설명")
    private String ghgActDaCd;

    @ApiModelProperty(value = "자체시설명")
    private String fcltNm;

    @ApiModelProperty(value = "사업장")
    private String plantCd;

    @ApiModelProperty(value = "사업장명")
    private String plantNm;

    @ApiModelProperty(value = "공장(인)코드")
    private String lcnFactCd;

    @ApiModelProperty(value = "ngms시설일련번허")
    private String ngmsFceqNum;

    @ApiModelProperty(value = "시설코드")
    private String erpCode;

    @ApiModelProperty(value = "부서코드")
    private String deptCd;

    @ApiModelProperty(value = "부서명")
    private String deptNm;

    @ApiModelProperty(value = "배출활동수")
    private String paTier;

    @ApiModelProperty(value = "단위코드")
    private String ghgUnitCd;

    @ApiModelProperty(value = "매개변수 배출계수")
    private Float paEmiCoeCo2;

    @ApiModelProperty(value = "매개변수 배출계수")
    private Float paEmiCoeCh4;

    @ApiModelProperty(value = "매개변수 배출계수")
    private Float paEmiCoeN20;

    @ApiModelProperty(value = "매개변수 배출계수")
    private Float paEmiCoeHfcs;

    @ApiModelProperty(value = "매개변수 배출계수")
    private Float paEmiCoePfcs;

    @ApiModelProperty(value = "매개변수 배출계수")
    private Float paEmiCoeSf6;

    @ApiModelProperty(value = "매개변수 열량계수 순발열량")
    private Float paCalCoeP;

    @ApiModelProperty(value = "매개변수 열량계수 총발열량")
    private Float paCalCoeT;

    @ApiModelProperty(value = "매개변수 열량계수 산화물")
    private Float paOxiCoe;

    @ApiModelProperty(value = "매개변수gwp")
    private int paGwpCo2;

    @ApiModelProperty(value = "매개변수gwp")
    private int paGwpCh4;

    @ApiModelProperty(value = "매개변수gwp")
    private int paGwpN2o;

    @ApiModelProperty(value = "매개변수gwp")
    private int paGwpHfcs;

    @ApiModelProperty(value = "매개변수gwp")
    private int paGwpPfcs;

    @ApiModelProperty(value = "매개변수gwp")
    private int paGwpSf6;

    @ApiModelProperty(value = "공정코드")
    private String processCd;

    @ApiModelProperty(value = "공정명")
    private String processNm;

    @ApiModelProperty(value = "등록자")
    private String createUserId;

    @ApiModelProperty(value = "등록자")
    private String createUserNm;

    @ApiModelProperty(value = "등록일시")
    private String createDt;

    @ApiModelProperty(value = "수정자")
    private String updateUserId;

    @ApiModelProperty(value = "수정자")
    private String updateUserNm;

    @ApiModelProperty(value = "수정일시")
    private String updateDt;

    @ApiModelProperty(value = "저장클릭")
    private String click;

    @ApiModelProperty(value = "활동자료명")
    private String actDataNm;

    @ApiModelProperty(value = "배출활동명")
    private String actNm;

    @ApiModelProperty(value = "작성자")
    private String writerUserNm;

    @ApiModelProperty(value = "작성일")
    private String writerDt;

    @ApiModelProperty(value = "유효여부")
    private String activeYn;

    public FacCalByYear() {
        super();
    }

    public FacCalByYear(int ghgFcltNo, String year, int ghgFcltGrpNo, int fcltCalcMtdNo, String ghgFcltCd, String isSave, String ghgFcltNm, String ghgOutActCd, String ghgActDaCd, String fcltNm, String plantCd, String plantNm, String lcnFactCd, String ngmsFceqNum, String erpCode, String deptCd, String deptNm, String paTier, String ghgUnitCd,
            Float paEmiCoeCo2, Float paEmiCoeCh4, Float paEmiCoeN20, Float paCalCoeP, Float paCalCoeT, Float paOxiCoe, int paGwpCo2, int paGwpCh4, int paGwpN2o, int paGwpHfcs, int paGwpPfcs, int paGwpSf6, String processCd, String processNm, String createUserId, String createUserNm, String createDt, String updateUserId, String updateUserNm,
            String updateDt, String click, String actDataNm, String actNm, String writerUserNm, String writerDt, String activeYn) {
        super();
        this.ghgFcltNo = ghgFcltNo;
        this.year = year;
        this.ghgFcltGrpNo = ghgFcltGrpNo;
        this.fcltCalcMtdNo = fcltCalcMtdNo;
        this.ghgFcltCd = ghgFcltCd;
        this.isSave = isSave;
        this.ghgFcltNm = ghgFcltNm;
        this.ghgOutActCd = ghgOutActCd;
        this.ghgActDaCd = ghgActDaCd;
        this.fcltNm = fcltNm;
        this.plantCd = plantCd;
        this.plantNm = plantNm;
        this.lcnFactCd = lcnFactCd;
        this.ngmsFceqNum = ngmsFceqNum;
        this.erpCode = erpCode;
        this.deptCd = deptCd;
        this.deptNm = deptNm;
        this.paTier = paTier;
        this.ghgUnitCd = ghgUnitCd;
        this.paEmiCoeCo2 = paEmiCoeCo2;
        this.paEmiCoeCh4 = paEmiCoeCh4;
        this.paEmiCoeN20 = paEmiCoeN20;
        this.paCalCoeP = paCalCoeP;
        this.paCalCoeT = paCalCoeT;
        this.paOxiCoe = paOxiCoe;
        this.paGwpCo2 = paGwpCo2;
        this.paGwpCh4 = paGwpCh4;
        this.paGwpN2o = paGwpN2o;
        this.paGwpHfcs = paGwpHfcs;
        this.paGwpPfcs = paGwpPfcs;
        this.paGwpSf6 = paGwpSf6;
        this.processCd = processCd;
        this.processNm = processNm;
        this.createUserId = createUserId;
        this.createUserNm = createUserNm;
        this.createDt = createDt;
        this.updateUserId = updateUserId;
        this.updateUserNm = updateUserNm;
        this.updateDt = updateDt;
        this.click = click;
        this.actDataNm = actDataNm;
        this.actNm = actNm;
        this.writerUserNm = writerUserNm;
        this.writerDt = writerDt;
        this.activeYn = activeYn;
    }

}
