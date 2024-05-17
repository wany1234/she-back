package com.she.baseInfo.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "자재 제조업체")
@Getter
@Setter
public class MatVendor {
    @ApiModelProperty(value = "자재코드")
    private String matCode;
    @ApiModelProperty(value = "자재명(국문)")
    private String matName;
    @ApiModelProperty(value = "자재명(영문)")
    private String matNameEng;
    @ApiModelProperty(value = "사업장")
    private String plantNms;
    @ApiModelProperty(value = "업체유형")
    private String vendorAttNm;
    @ApiModelProperty(value = "제조업체")
    private String vendorCd;
    @ApiModelProperty(value = "제조업체명")
    private String vendorNm;
    @ApiModelProperty(value = "취급자재명(국문)")
    private String chemProdNmKr;
    @ApiModelProperty(value = "취급자재명(영문)")
    private String chemProdNmEn;
    @ApiModelProperty(value = "국가")
    private String originNmKr;
    @ApiModelProperty(value = "사업자번호")
    private String bizNum;
    @ApiModelProperty(value = "비율")
    private String rate;

}
