package com.she.vendor.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "협력업제자체항목별점검결과")
@Data
public class SubconCheckItemResult {

    @ApiModelProperty(value = "협력회사자체점검결과번호")
    private int vendorCheckRsltNo;

    @ApiModelProperty(value = "안전점검항목번호")
    private int safCheckItemNo;

    @ApiModelProperty(value = "안전점검항목명")
    private String safCheckTypeNm;

    @ApiModelProperty(value = "점검결과")
    private String checkResult;

    @ApiModelProperty(value = "비고")
    private String remark;

    @ApiModelProperty(value = "사용여부")
    private String useYn;
}
