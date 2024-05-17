package com.she.vendor.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "협력업체 게시판 조회업체유형")
@Data
public class VendorBoardVendorAtt {

    @ApiModelProperty(value = "게시판번호")
    private int boardNo;

    @ApiModelProperty(value = "업체유형코드")
    private String vendorAttCd;

    @ApiModelProperty(value = "업체유형")
    private String vendorAttNm;
}
