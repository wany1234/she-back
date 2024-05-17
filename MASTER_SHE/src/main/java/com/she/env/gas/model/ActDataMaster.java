package com.she.env.gas.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "활동자료분류마스터")
public class ActDataMaster {

    @ApiModelProperty(value = "활동자료코드")
    private String actDataCd;

    @ApiModelProperty(value = "활동자료구분")
    private String actClsCd;

    @ApiModelProperty(value = "활동자료구분명")
    private String actClsNm;

    @ApiModelProperty(value = "활동자료명")
    private String actDataNm;

    @ApiModelProperty(value = "분류레벨")
    private String dataLvlCd;

    @ApiModelProperty(value = "부모활동자료")
    private String pActDataCd;

    @ApiModelProperty(value = "부모활동자료명")
    private String pActDataNm;

    @ApiModelProperty(value = "부모활동자료명")
    private String ppActDataNm;

    @ApiModelProperty(value = "사용여부")
    private String useYn;

    @ApiModelProperty(value = "사용여부명")
    private String useYnNm;

    @ApiModelProperty(value = "정렬순서")
    private int sortOrder;

    @ApiModelProperty(value = "등록자")
    private String createUserId;

    @ApiModelProperty(value = "등록일시")
    private String createDt;

    @ApiModelProperty(value = "수정자")
    private String updateUserId;

    @ApiModelProperty(value = "수정일시")
    private String updateDt;

    @ApiModelProperty(value = "작성자")
    private String writerUserNm;

    @ApiModelProperty(value = "작성일")
    private String writerDt;

    public ActDataMaster() {
        super();
    }

    public ActDataMaster(String actDataCd, String actClsCd, String actClsNm, String actDataNm, String dataLvlCd, String pActDataCd, String pActDataNm, String ppActDataNm, String useYn, String useYnNm, int sortOrder, String createUserId, String createDt, String updateUserId, String updateDt, String writerUserNm, String writerDt) {
        super();
        this.actDataCd = actDataCd;
        this.actClsCd = actClsCd;
        this.actClsNm = actClsNm;
        this.actDataNm = actDataNm;
        this.dataLvlCd = dataLvlCd;
        this.pActDataCd = pActDataCd;
        this.pActDataNm = pActDataNm;
        this.ppActDataNm = ppActDataNm;
        this.useYn = useYn;
        this.useYnNm = useYnNm;
        this.sortOrder = sortOrder;
        this.createUserId = createUserId;
        this.createDt = createDt;
        this.updateUserId = updateUserId;
        this.updateDt = updateDt;
        this.writerUserNm = writerUserNm;
        this.writerDt = writerDt;
    }

    public String getActClsNm() {
        return actClsNm;
    }

    public void setActClsNm(String actClsNm) {
        this.actClsNm = actClsNm;
    }

    public String getActClsCd() {
        return actClsCd;
    }

    public String getPpActDataNm() {
        return ppActDataNm;
    }

    public void setPpActDataNm(String ppActDataNm) {
        this.ppActDataNm = ppActDataNm;
    }

    public void setActClsCd(String actClsCd) {
        this.actClsCd = actClsCd;
    }

    public String getActDataCd() {
        return actDataCd;
    }

    public void setActDataCd(String actDataCd) {
        this.actDataCd = actDataCd;
    }

    public String getActDataNm() {
        return actDataNm;
    }

    public void setActDataNm(String actDataNm) {
        this.actDataNm = actDataNm;
    }

    public String getDataLvlCd() {
        return dataLvlCd;
    }

    public void setDataLvlCd(String dataLvlCd) {
        this.dataLvlCd = dataLvlCd;
    }

    public String getpActDataCd() {
        return pActDataCd;
    }

    public void setpActDataCd(String pActDataCd) {
        this.pActDataCd = pActDataCd;
    }

    public String getpActDataNm() {
        return pActDataNm;
    }

    public void setpActDataNm(String pActDataNm) {
        this.pActDataNm = pActDataNm;
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
