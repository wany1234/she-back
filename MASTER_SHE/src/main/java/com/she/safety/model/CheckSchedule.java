package com.she.safety.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "안전점검일정")
@Data
public class CheckSchedule {

    @ApiModelProperty(value = "안전점검일정일련번호")
    private int safCheckScheduleNo;

    @ApiModelProperty(value = "안전점검일련번호")
    private int safCheckNo;

    @ApiModelProperty(value = "점검예정일")
    private String checkSchYmd;

    @ApiModelProperty(value = "점검명")
    private String checkTitle;

    @ApiModelProperty(value = "대상부서코드")
    private String tgtDeptCd;

    @ApiModelProperty(value = "대상부서명")
    private String tgtDeptNm;
    
    @ApiModelProperty(value = "수행부서코드")
    private String pfmDeptCd;

    @ApiModelProperty(value = "수행부서명")
    private String pfmDeptNm;

    @ApiModelProperty(value = "점검일")
    private String checkYmd;

    @ApiModelProperty(value = "결과요약")
    private String checkResult;

    @ApiModelProperty(value = "점검결과코드")
    private String checkResultCd;
    
    @ApiModelProperty(value = "점검결과명")
    private String checkResultNm;

    @ApiModelProperty(value = "점검진행상태")
    private String checkStepCd;

    @ApiModelProperty(value = "점검진행상태명")
    private String checkStepNm;

    @ApiModelProperty(value = "점검결과결재진행상태")
    private String apprStepCd;

    @ApiModelProperty(value = "업체작성여부")
    private String subconnRegYn;

    @ApiModelProperty(value = "대상업체코드")
    private String vendorCd;

    @ApiModelProperty(value = "계획결재요청번호")
    private int pApprRqstNo;

    @ApiModelProperty(value = "결과결재요청번호")
    private int rApprRqstNo;

    @ApiModelProperty(value = "결과결재요청번호")
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

    private int safCheckKindNo;

    private String safCheckKindNm;

    private String deptCd;

    private String deptNm;

    private String plantCd;

    private String plantNm;

    private String checkMasterTitle;

    private List<CheckInspector> checkInspectors;

    private List<CheckItemResult> checkItemResults;

    private String[] checkStepCds;

    @ApiModelProperty(value = "무일정여부")
    private String noSchYn;

    @ApiModelProperty(value = "무계획여부")
    private String noPlanYn;

    @ApiModelProperty(value = "작성자")
    private String writerUserNm;

    @ApiModelProperty(value = "작성일")
    private String writerDt;

}
