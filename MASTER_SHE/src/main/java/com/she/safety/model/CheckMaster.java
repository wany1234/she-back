package com.she.safety.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "안전점검마스터")
@Data
public class CheckMaster {

    @ApiModelProperty(value = "안전점검일련번호")
    private int safCheckNo;

    @ApiModelProperty(value = "사업장코드")
    private String plantCd;

    @ApiModelProperty(value = "사업장")
    private String plantNm;

    @ApiModelProperty(value = "안전점검종류번호")
    private int safCheckKindNo;

    @ApiModelProperty(value = "안전점검종류명")
    private String safCheckKindNm;

    @ApiModelProperty(value = "점검마스터명")
    private String checkMasterTitle;

    @ApiModelProperty(value = "점검주기")
    private String safCheckPerd;

    @ApiModelProperty(value = "점검계획시작일")
    private String safCheckPlanSymd;

    @ApiModelProperty(value = "점검계획종료일")
    private String safCheckPlanEymd;

    @ApiModelProperty(value = "주관부서코드")
    private String deptCd;

    @ApiModelProperty(value = "주관부서명")
    private String deptNm;
    
    @ApiModelProperty(value = "대상부서코드")
    private String tgtDeptCd;

    @ApiModelProperty(value = "대상부서명")
    private String tgtDeptNm;
    
    @ApiModelProperty(value = "수행부서코드")
    private String pfmDeptCd;

    @ApiModelProperty(value = "수행부서명")
    private String pfmDeptNm;

    @ApiModelProperty(value = "일정결재요청번호")
    private int apprRqstNo;

    @ApiModelProperty(value = "업무결재진행단계코드")
    private String bizApprStepCd;

    @ApiModelProperty(value = "업무결재진행단계")
    private String bizApprStepNm;

    @ApiModelProperty(value = "등록자")
    private String createUserId;

    @ApiModelProperty(value = "등록자명")
    private String createUserNm;

    @ApiModelProperty(value = "등록일")
    private String createDt;

    @ApiModelProperty(value = "수정자")
    private String updateUserId;

    @ApiModelProperty(value = "수정일")
    private String updateDt;

    @ApiModelProperty(value = "작성자")
    private String writerUserNm;

    @ApiModelProperty(value = "작성일")
    private String writerDt;

    private String[] arrayTgtDeptCd;

    private String[] arrayCheckSchYmd;

    private String[] arrayCheckTitle;

    private List<CheckSchedule> checkScheduleList;

    private String stepStatus;

    private String stepStatusNm;

    @ApiModelProperty(value = "무일정여부")
    private String noSchYn;
}
