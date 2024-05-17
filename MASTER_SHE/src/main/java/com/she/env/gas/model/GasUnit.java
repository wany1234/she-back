package com.she.env.gas.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "산정기준단위")
public class GasUnit {

    @ApiModelProperty(value = "산정기준단위코드")
    private String unitCd;

    @ApiModelProperty(value = "산정기준단위")
    private String unitNm;

    @ApiModelProperty(value = "산정기준단위구분")
    private String unitClsCd;

    @ApiModelProperty(value = "산정기준단위구분2")
    private String unitClsSecCd;

    @ApiModelProperty(value = "사용여부")
    private String useYn;

    @ApiModelProperty(value = "정렬순서")
    private String sortOrder;

    @ApiModelProperty(value = "등록한 유저")
    private String createUserId;

    @ApiModelProperty(value = "수정한 유저")
    private String updateUserId;

    @ApiModelProperty(value = "작성자")
    private String writerUserNm;

    @ApiModelProperty(value = "작성일")
    private String writerDt;

    public String getUnitCd() {
        return unitCd;
    }

    public void setUnitCd(String unitCd) {
        this.unitCd = unitCd;
    }

    public String getUnitNm() {
        return unitNm;
    }

    public void setUnitNm(String unitNm) {
        this.unitNm = unitNm;
    }

    public String getUnitClsCd() {
        return unitClsCd;
    }

    public void setUnitClsCd(String unitClsCd) {
        this.unitClsCd = unitClsCd;
    }

    public String getUnitClsSecCd() {
        return unitClsSecCd;
    }

    public void setUnitClsSecCd(String unitClsSecCd) {
        this.unitClsSecCd = unitClsSecCd;
    }

    public String getUseYn() {
        return useYn;
    }

    public void setUseYn(String useYn) {
        this.useYn = useYn;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
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
