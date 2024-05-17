package com.she.vendor.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "평가및안전보건계획")
@Data
public class AssmnSfhlcItemList {

    @ApiModelProperty(value = "협력업체평가결과NO")
    private int safSubconEvalItemNo;

    @ApiModelProperty(value = "협력업체평가항목번호")
    private int vendorEvalItemRsltNo;

    @ApiModelProperty(value = "대상협력업체번호")
    private int evalPlanVendorNo;

    @ApiModelProperty(value = "협력업체 평가계획번호")
    private int vendorEvalPlanNo;

    @ApiModelProperty(value = "평가점수")
    private String evalPnt;

    @ApiModelProperty(value = "비고")
    private String remarks;

    @ApiModelProperty(value = "평가항목명")
    private String subconEvalItemNm;

    @ApiModelProperty(value = "평가기준")
    private String subconEvalItemStnd;

    @ApiModelProperty(value = "배점")
    private int subconEvalItemPnt;

    @ApiModelProperty(value = "C평가구분코드")
    private String subconEvalClsCd;

    @ApiModelProperty(value = "C평가유형코드")
    private String subconEvalAttCd;

}
