package com.she.manage.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "결재문서결재선")
@Data
public class ApprBizLine {

    @ApiModelProperty(value = "결재문서결재선번호")
    private int apprBizLineNo;
    
    @ApiModelProperty(value = "결재문서마스터번호")
    private int apprBizNo;
    
    @ApiModelProperty(value = "사업장코드")
    private String plantCd;
    
    @ApiModelProperty(value = "사업장명")
    private String plantNm;
    
    @ApiModelProperty(value = "결재선명")
    private String apprLineNm;
    
    @ApiModelProperty(value = "설명")
    private String apprLineDesc;
    
    @ApiModelProperty(value = "결재선유형코드")
    private String apprLineTypeCd;
}
