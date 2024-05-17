package com.she.env.gas.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "사용시설 변경관리 이력")
public class DischargeFacilityHistory {

    @ApiModelProperty(value = "이력no")
    private int historyNo;

    @ApiModelProperty(value = "배출시설순번")
    private int ghgFcltNo;

    @ApiModelProperty(value = "개정사유")
    private String chgReason;

    @ApiModelProperty(value = "등록자")
    private String createUserId;

    @ApiModelProperty(value = "등록자")
    private String createUserNm;

    @ApiModelProperty(value = "등록일시")
    private String createDt;

    @ApiModelProperty(value = "개정번호")
    private int revisionNo;

    public int getRevisionNo() {
        return revisionNo;
    }

    public void setRevisionNo(int revisionNo) {
        this.revisionNo = revisionNo;
    }

    public DischargeFacilityHistory(int historyNo, int ghgFcltNo, String chgReason, String createUserNm, String createDt, int revisionNo) {
        super();
        this.historyNo = historyNo;
        this.ghgFcltNo = ghgFcltNo;
        this.chgReason = chgReason;
        this.createUserNm = createUserNm;
        this.createDt = createDt;
        this.revisionNo = revisionNo;
    }

    public DischargeFacilityHistory() {
        super();
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public int getHistoryNo() {
        return historyNo;
    }

    public void setHistoryNo(int historyNo) {
        this.historyNo = historyNo;
    }

    public int getGhgFcltNo() {
        return ghgFcltNo;
    }

    public void setGhgFcltNo(int ghgFcltNo) {
        this.ghgFcltNo = ghgFcltNo;
    }

    public String getChgReason() {
        return chgReason;
    }

    public void setChgReason(String chgReason) {
        this.chgReason = chgReason;
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

}
