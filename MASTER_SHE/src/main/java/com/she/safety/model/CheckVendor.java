package com.she.safety.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "협력업체")
@Data
public class CheckVendor {

    @ApiModelProperty(value = "안전점검일련번호")
    private int safCheckNo;
    @ApiModelProperty(value = "시퀀스")
    private String seqNo;
    @ApiModelProperty(value = "협력업체코드")
    private String vendorCd;
    @ApiModelProperty(value = "협력업체명")
    private String vendorNm;

}
