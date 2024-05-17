package com.she.env.gas.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "배출시설관리마스터 활동자료")
public class DischargeFacilityData {

    @ApiModelProperty(value = "배출시설별활동및자료순번")
    private int fcltOutActDaNo;

    @ApiModelProperty(value = "년도별배출시설별활동및자료순번")
    private int fcltOutActDaYearNo;

    @ApiModelProperty(value = "년도별")
    private String year;

    @ApiModelProperty(value = "배출시설순번")
    private int ghgFcltNo;

    @ApiModelProperty(value = "배출활동코드")
    private String ghgOutActCd;

    @ApiModelProperty(value = "활동자료코드")
    private String ghgActDaCd;

    @ApiModelProperty(value = "대분류명")
    private String pActDataNm;

    @ApiModelProperty(value = "중분류명")
    private String ppActDataNm;

    @ApiModelProperty(value = "자식활동자료명")
    private String actDataNm;

    @ApiModelProperty(value = "활동자료명(자체)")
    private String actDaNm;

    @ApiModelProperty(value = "데이터연계코드")
    private String daIfCd;

    @ApiModelProperty(value = "연계SYS코드")
    private String ifTagCd;

    @ApiModelProperty(value = "등록자")
    private String createUserId;

    @ApiModelProperty(value = "등록일시")
    private String createDt;

    @ApiModelProperty(value = "수정자")
    private String updateUserId;

    @ApiModelProperty(value = "수정일시")
    private String updateDt;

    public DischargeFacilityData() {
        super();
    }

    public DischargeFacilityData(int fcltOutActDaNo, int fcltOutActDaYearNo, String year, int ghgFcltNo, String ghgOutActCd, String ghgActDaCd, String pActDataNm, String ppActDataNm, String actDataNm, String actDaNm, String daIfCd, String ifTagCd, String createUserId, String createDt, String updateUserId, String updateDt) {
        super();
        this.fcltOutActDaNo = fcltOutActDaNo;
        this.fcltOutActDaYearNo = fcltOutActDaYearNo;
        this.year = year;
        this.ghgFcltNo = ghgFcltNo;
        this.ghgOutActCd = ghgOutActCd;
        this.ghgActDaCd = ghgActDaCd;
        this.pActDataNm = pActDataNm;
        this.ppActDataNm = ppActDataNm;
        this.actDataNm = actDataNm;
        this.actDaNm = actDaNm;
        this.daIfCd = daIfCd;
        this.ifTagCd = ifTagCd;
        this.createUserId = createUserId;
        this.createDt = createDt;
        this.updateUserId = updateUserId;
        this.updateDt = updateDt;
    }

    public int getFcltOutActDaYearNo() {
        return fcltOutActDaYearNo;
    }

    public void setFcltOutActDaYearNo(int fcltOutActDaYearNo) {
        this.fcltOutActDaYearNo = fcltOutActDaYearNo;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getpActDataNm() {
        return pActDataNm;
    }

    public void setpActDataNm(String pActDataNm) {
        this.pActDataNm = pActDataNm;
    }

    public String getPpActDataNm() {
        return ppActDataNm;
    }

    public void setPpActDataNm(String ppActDataNm) {
        this.ppActDataNm = ppActDataNm;
    }

    public String getActDataNm() {
        return actDataNm;
    }

    public void setActDataNm(String actDataNm) {
        this.actDataNm = actDataNm;
    }

    public int getFcltOutActDaNo() {
        return fcltOutActDaNo;
    }

    public void setFcltOutActDaNo(int fcltOutActDaNo) {
        this.fcltOutActDaNo = fcltOutActDaNo;
    }

    public int getGhgFcltNo() {
        return ghgFcltNo;
    }

    public void setGhgFcltNo(int ghgFcltNo) {
        this.ghgFcltNo = ghgFcltNo;
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

    public String getActDaNm() {
        return actDaNm;
    }

    public void setActDaNm(String actDaNm) {
        this.actDaNm = actDaNm;
    }

    public String getDaIfCd() {
        return daIfCd;
    }

    public void setDaIfCd(String daIfCd) {
        this.daIfCd = daIfCd;
    }

    public String getIfTagCd() {
        return ifTagCd;
    }

    public void setIfTagCd(String ifTagCd) {
        this.ifTagCd = ifTagCd;
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
