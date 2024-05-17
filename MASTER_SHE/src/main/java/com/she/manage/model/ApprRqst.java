package com.she.manage.model;

import java.util.List;
import java.util.Map;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "결재요청")
@Data
public class ApprRqst {

    @ApiModelProperty(value = "결재요청번호")
    private Integer apprRqstNo;

    @ApiModelProperty(value = "상위결재요청번호")
    private Integer pApprRqstNo;

    @ApiModelProperty(value = "결재요청명")
    private String apprRqstNm;

    @ApiModelProperty(value = "결재문서마스터번호")
    private Integer apprBizNo;

    @ApiModelProperty(value = "사업장코드")
    private String plantCd;

    @ApiModelProperty(value = "사업장명")
    private String plantNm;

    @ApiModelProperty(value = "업무결재진행단계코드")
    private String bizApprStepCd;

    @ApiModelProperty(value = "업무결재진행단계코드명")
    private String bizApprStepNm;

    @ApiModelProperty(value = "결재요청파라미터")
    private String apprReqParameter;

    @ApiModelProperty(value = "등록일")
    private String createDt;

    private String reqUserId;

    private List<Map<String, String>> apprLines;

}
