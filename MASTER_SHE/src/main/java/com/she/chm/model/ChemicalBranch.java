package com.she.chm.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "법인")
@Getter
@Setter
public class ChemicalBranch {

    @ApiModelProperty(value = "법인코드")
    private String branchCd;

    @ApiModelProperty(value = "법인명(국문)")
    private String branchNmKr;

    @ApiModelProperty(value = "법인명(영문)")
    private String branchNmEn;

    @ApiModelProperty(value = "법인명(중문)")
    private String branchNmCh;

    @ApiModelProperty(value = "사업자번호")
    private String branchBizNum;

    @ApiModelProperty(value = "법인주소(국문)")
    private String branchAddrKr;

    @ApiModelProperty(value = "법인주소(영문)")
    private String branchAddrEn;

    @ApiModelProperty(value = "법인주소(중문)")
    private String branchAddrCh;

    @ApiModelProperty(value = "법인연락처")
    private String branchTel;

    @ApiModelProperty(value = "대표자(국문)")
    private String branchCeoKr;

    @ApiModelProperty(value = "대표자(영문)")
    private String branchCeoEn;

    @ApiModelProperty(value = "대표자(중문)")
    private String branchCeoCh;

    @ApiModelProperty(value = "담당자(국문)")
    private String branchChargerKr;

    @ApiModelProperty(value = "담당자(영문)")
    private String branchChargerEn;

    @ApiModelProperty(value = "담당자(중문)")
    private String branchChargerCh;

    @ApiModelProperty(value = "담당자연락처")
    private String branchChargerTel;

    @ApiModelProperty(value = "사용여부")
    private String useYn;

    @ApiModelProperty(value = "사용여부명")
    private String useYnNm;

    @ApiModelProperty(value = "생성자아이디")
    private String createUserId;

    @ApiModelProperty(value = "생성자명")
    private String createUserNm;

    @ApiModelProperty(value = "생성일")
    private String createDt;

    @ApiModelProperty(value = "수정자아이디")
    private String updateUserId;

    @ApiModelProperty(value = "수정자명")
    private String updateUserNm;

    @ApiModelProperty(value = "수정일")
    private String updateDt;

}
