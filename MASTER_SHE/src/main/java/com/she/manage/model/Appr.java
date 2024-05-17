package com.she.manage.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "결재")
@Data
public class Appr {

    @ApiModelProperty(value = "결재문서마스터번호")
    private Integer apprBizNo;

    @ApiModelProperty(value = "결재요청번호")
    private Integer apprRqstNo;

    @ApiModelProperty(value = "상위결재요청번호")
    private Integer pApprRqstNo;

    @ApiModelProperty(value = "결재요청명")
    private String apprRqstNm;

    @ApiModelProperty(value = "결재선순번")
    private Integer lineSeqNo;

    @ApiModelProperty(value = "결재문서유형코드")
    private String apprBizCd;

    @ApiModelProperty(value = "업무명")
    private String bizNm;

    @ApiModelProperty(value = "결재url")
    private String apprUrl;

    @ApiModelProperty(value = "결재메일내용")
    private String apprMailContents;

    @ApiModelProperty(value = "등록일")
    private String createDt;

    @ApiModelProperty(value = "사업장코드")
    private String plantCd;

    @ApiModelProperty(value = "결재요청파라미터")
    private String apprReqParameter;

    @ApiModelProperty(value = "결재자유형코드")
    private String apprTypeCd;

    @ApiModelProperty(value = "결재자유형명")
    private String apprTypeNm;

    @ApiModelProperty(value = "결재자부서코드")
    private String apprUserDeptCd;

    @ApiModelProperty(value = "결재자부서명")
    private String apprUserDeptNm;

    @ApiModelProperty(value = "결재자ID")
    private String apprUserId;

    @ApiModelProperty(value = "결재자")
    private String apprUserNm;

    @ApiModelProperty(value = "위임자ID")
    private String delegatorId;

    @ApiModelProperty(value = "위임자")
    private String delegatorNm;

    @ApiModelProperty(value = "위임자부서")
    private String delegatorDeptNm;

    @ApiModelProperty(value = "결재자처리상태코드")
    private String apprStepCd;

    @ApiModelProperty(value = "결재자처리상태")
    private String apprStepNm;

    @ApiModelProperty(value = "결재일시")
    private String apprDt;

    @ApiModelProperty(value = "업무결재진행단계코드")
    private String bizApprStepCd;

    @ApiModelProperty(value = "업무결재진행단계")
    private String bizApprStepNm;

    @ApiModelProperty(value = "결재의견")
    private String apprRemark;

    @ApiModelProperty(value = "결재자직급코드")
    private String apprUserDutyCd;

    @ApiModelProperty(value = "결재자직급")
    private String apprUserDutyNm;

    private List<ApprRqstLine> apprRqstLineList;

    private List<ApprRqstLine> apprRqstLineHistoryList;
}
