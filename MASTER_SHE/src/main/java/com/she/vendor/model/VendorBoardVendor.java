package com.she.vendor.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "협력업체 게시판 조회업체")
@Data
public class VendorBoardVendor {

    @ApiModelProperty(value = "게시판번호")
    private int boardNo;

    @ApiModelProperty(value = "업체코드")
    private String vendorCd;

    @ApiModelProperty(value = "업체명")
    private String vendorNm;

    @ApiModelProperty(value = "업체분류코드")
    private String vendorTypeCd;

    @ApiModelProperty(value = "업체분류")
    private String vendorTypeNm;

    @ApiModelProperty(value = "업체유형코드")
    private String vendorAttCd;

    @ApiModelProperty(value = "업체유형")
    private String vendorAttNm;

    @ApiModelProperty(value = "사업장범위")
    private String plantNms;
}
