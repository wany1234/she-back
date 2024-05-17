package com.she.env.gas.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "온실가스종류")
public class GasType {

    @ApiModelProperty(value = "온실가스코드")
    private String gasTypeCd;

    @ApiModelProperty(value = "온실가스명")
    private String gasTypeNm;

    @ApiModelProperty(value = "화학식")
    private String cheForm;

    @ApiModelProperty(value = "GWP(지구온난화지수)")
    private String gwp;

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

    public String getGasTypeCd() {
        return gasTypeCd;
    }

    public void setGasTypeCd(String gasTypeCd) {
        this.gasTypeCd = gasTypeCd;
    }

    public String getGasTypeNm() {
        return gasTypeNm;
    }

    public void setGasTypeNm(String gasTypeNm) {
        this.gasTypeNm = gasTypeNm;
    }

    public String getCheForm() {
        return cheForm;
    }

    public void setCheForm(String cheForm) {
        this.cheForm = cheForm;
    }

    public String getGwp() {
        return gwp;
    }

    public void setGwp(String gwp) {
        this.gwp = gwp;
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
