package com.she.vendor.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "협력업체 게시판")
@Data
public class VendorBoard {

    @ApiModelProperty(value = "게시판번호")
    private int boardNo;

    @ApiModelProperty(value = "등록사업장코드")
    private String plantCd;

    @ApiModelProperty(value = "등록사업장")
    private String plantNm;

    @ApiModelProperty(value = "분류코드")
    private String kindCd;

    @ApiModelProperty(value = "분류코드명")
    private String kindNm;

    @ApiModelProperty(value = "업체조회범위")
    private String vendorRgeCd;

    @ApiModelProperty(value = "업체조회범위명")
    private String vendorRgeNm;

    @ApiModelProperty(value = "제목")
    private String title;

    @ApiModelProperty(value = "내용")
    private String contents;

    @ApiModelProperty(value = "사용여부")
    private String useYn;

    @ApiModelProperty(value = "등록자ID")
    private String createUserId;

    @ApiModelProperty(value = "등록자")
    private String createUserNm;

    @ApiModelProperty(value = "등록일")
    private String createDt;

    @ApiModelProperty(value = "수정자ID")
    private String updateUserId;

    @ApiModelProperty(value = "수정자")
    private String updateUserNm;

    @ApiModelProperty(value = "수정일")
    private String updateDt;

    @ApiModelProperty(value = "협력업체 게시판 조회업체 목록")
    private List<VendorBoardVendor> vendorBoardVendorList;

    @ApiModelProperty(value = "협력업체 게시판 조회업체유형 목록")
    private List<VendorBoardVendorAtt> vendorBoardVendorAttList;

    @ApiModelProperty(value = "작성자")
    private String writerUserNm;

    @ApiModelProperty(value = "작성일")
    private String writerDt;
}
