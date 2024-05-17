package com.she.env.gas.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "배출활동코드")
public class DischargeAct {

    @ApiModelProperty(value = "배출활동코드")
    private String disActCd;

    @ApiModelProperty(value = "배출활동명")
    private String disActNm;

    @ApiModelProperty(value = "배출활동분류")
    private String disClsCd;

    @ApiModelProperty(value = "사용여부")
    private String useYn;

    @ApiModelProperty(value = "정렬순서")
    private int sortOrder;

    @ApiModelProperty(value = "등록한 유저")
    private String createUserId;

    @ApiModelProperty(value = "수정한 유저")
    private String updateUserId;

    @ApiModelProperty(value = "작성자")
    private String writerUserNm;

    @ApiModelProperty(value = "작성일")
    private String writerDt;

    public String getDisActCd() {
        return disActCd;
    }

    public void setDisActCd(String disActCd) {
        this.disActCd = disActCd;
    }

    public String getDisActNm() {
        return disActNm;
    }

    public void setDisActNm(String disActNm) {
        this.disActNm = disActNm;
    }

    public String getDisClsCd() {
        return disClsCd;
    }

    public void setDisClsCd(String disClsCd) {
        this.disClsCd = disClsCd;
    }

    public String getUseYn() {
        return useYn;
    }

    public void setUseYn(String useYn) {
        this.useYn = useYn;
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
