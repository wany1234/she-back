package com.she.vendor.model;

import java.util.List;
import java.util.Map;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "협력업체 게시판")
@Data
public class SubconNotice {

    @ApiModelProperty(value = "게시판No")
    private int boardNo;

    @ApiModelProperty(value = "사업장코드")
    private String plantCd;

    @ApiModelProperty(value = "사업장명")
    private String plantNm;

    @ApiModelProperty(value = "분류코드")
    private String kindCd;

    @ApiModelProperty(value = "분류명")
    private String kindNm;

    @ApiModelProperty(value = "업체코드")
    private String vendorCd;

    @ApiModelProperty(value = "업체명")
    private String vendorNm;

    @ApiModelProperty(value = "제목")
    private String title;

    @ApiModelProperty(value = "내용")
    private String contents;

    @ApiModelProperty(value = "사용여부")
    private String useYn;

    @ApiModelProperty(value = "등록자")
    private String createUserNm;

    @ApiModelProperty(value = "등록자ID")
    private String createUserId;

    @ApiModelProperty(value = "등록일")
    private String createDt;

    @ApiModelProperty(value = "수정자")
    private String updateUserNm;

    @ApiModelProperty(value = "수정자ID")
    private String updateUserId;

    @ApiModelProperty(value = "수정일")
    private String updateDt;

    @ApiModelProperty(value = "조회사업장범위명")
    private String plantNms;

    @ApiModelProperty(value = "조회업체명")
    private String vendorNms;

    @ApiModelProperty(value = "조회업체유형명")
    private String vendorAttNms;

    @ApiModelProperty(value = "조회사업장범위")
    private List<String> plantCds;

    @ApiModelProperty(value = "조회업체유형")
    private List<String> vendorAttCds;

    @ApiModelProperty(value = "조회업체")
    private List<ChemicalVendorMaster> vendorCds;
}
