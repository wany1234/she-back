package com.she.safety.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "순회 항목별 점검결과(합동점검도 포함)")
@Data
public class PatrolItemResult {
    @ApiModelProperty(value = "안전점검결과번호")
    private int safCheckRsltNo;
    @ApiModelProperty(value = "합동점검결과번호")
    private int safCongChkRsltNo;
    @ApiModelProperty(value = "안전점검항목번호")
    private int safCheckItemNo;
    @ApiModelProperty(value = "안전점검항목명")
    private String safCheckTypeNm;
    @ApiModelProperty(value = "안전점검종류번호")
    private int safCheckKindNo;
    @ApiModelProperty(value = "안전점검종류명")
    private String safCheckKindNm;
    @ApiModelProperty(value = "점검결과")
    private String checkResult;
    @ApiModelProperty(value = "비고")
    private String remark;
    @ApiModelProperty(value = "점검결과코드")
    private String chkRsltCd;
}
