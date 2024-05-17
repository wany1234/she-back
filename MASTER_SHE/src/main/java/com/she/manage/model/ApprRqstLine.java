package com.she.manage.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "결재요청결재선")
@Data
public class ApprRqstLine {

    @ApiModelProperty(value = "결재요청번호")
    private int apprRqstNo;

    @ApiModelProperty(value = "결재선순번")
    private int lineSeqNo;

    @ApiModelProperty(value = "결재자유형코드")
    private String apprTypeCd;

    @ApiModelProperty(value = "결재자유형")
    private String apprTypeNm;

    @ApiModelProperty(value = "상신/결재자ID")
    private String apprUserId;

    @ApiModelProperty(value = "상신/결재자")
    private String apprUserNm;

    @ApiModelProperty(value = "결재일시")
    private String apprDt;

    @ApiModelProperty(value = "결재자처리상태코드")
    private String apprStepCd;

    @ApiModelProperty(value = "결재자처리상태")
    private String apprStepNm;

    @ApiModelProperty(value = "결재의견")
    private String apprRemark;

    @ApiModelProperty(value = "상신/결재자 부서코드")
    private String apprUserDeptCd;

    @ApiModelProperty(value = "상신/결재자 부서")
    private String apprUserDeptNm;

    @ApiModelProperty(value = "상신/결재자 직급코드")
    private String apprUserDutyCd;

    @ApiModelProperty(value = "상신/결재자 직급")
    private String apprUserDutyNm;

    @ApiModelProperty(value = "대결자 아이디")
    private String delegatorId;

    @ApiModelProperty(value = "대결자명")
    private String delegatorNm;

    @ApiModelProperty(value = "대결자부서명")
    private String delegatorDeptNm;

    private String userId;
    private String userNm;
    private String deptCd;
    private String deptNm;
}
