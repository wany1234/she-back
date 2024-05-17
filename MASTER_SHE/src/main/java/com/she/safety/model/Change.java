package com.she.safety.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "변경관리")
@Getter
@Setter
public class Change {
    @ApiModelProperty(value = "변경관리번호")
    private int safChngNo;
    @ApiModelProperty(value = "문서번호")
    private String chngNum;
    @ApiModelProperty(value = "사업장코드")
    private String plantCd;
    @ApiModelProperty(value = "사업장명")
    private String plantNm;
    @ApiModelProperty(value = "요청부서코드")
    private String rqstDeptCd;
    @ApiModelProperty(value = "요청부서명")
    private String rqstDeptNm;
    @ApiModelProperty(value = "요청자ID")
    private String rqstUserId;
    @ApiModelProperty(value = "요청자명")
    private String rqstUserNm;
    @ApiModelProperty(value = "기안일")
    private String rqstDt;
    @ApiModelProperty(value = "승인일")
    private String confirmDt;
    @ApiModelProperty(value = "위원회여부")
    private String commiYn;
    @ApiModelProperty(value = "위원회여부명")
    private String commiYnNm;
    @ApiModelProperty(value = "제목")
    private String bizNm;
    @ApiModelProperty(value = "변경구분코드")
    private String chngAttCd;
    @ApiModelProperty(value = "변경구분명")
    private String chngAttNm;
    @ApiModelProperty(value = "등급(검토)코드")
    private String mocLvlCd;
    @ApiModelProperty(value = "등급(검토)명")
    private String mocLvlNm;
    @ApiModelProperty(value = "등급(작성)코드")
    private String prevMocLvlCd;
    @ApiModelProperty(value = "등급(작성)명")
    private String prevMocLvlNm;
    @ApiModelProperty(value = "내용")
    private String chngContents;
    @ApiModelProperty(value = "관련근거법령")
    private String chngRefLaw;
    @ApiModelProperty(value = "팀장의견")
    private String chkOpinion;
    @ApiModelProperty(value = "완료여부")
    private String isComplete;
    @ApiModelProperty(value = "변경관리진행단계")
    private String chngStepCd;
    @ApiModelProperty(value = "변경관리진행단계명")
    private String chngStepNm;
    @ApiModelProperty(value = "등록자ID")
    private String createUserId;
    @ApiModelProperty(value = "등록자명")
    private String createUserNm;
    @ApiModelProperty(value = "등록일")
    private String createDt;
    @ApiModelProperty(value = "수정자ID")
    private String updateUserId;
    @ApiModelProperty(value = "수정자명")
    private String updateUserNm;
    @ApiModelProperty(value = "수정일")
    private String updateDt;
    @ApiModelProperty(value = "결재진행번호")
    private String apprRqstNo;
    @ApiModelProperty(value = "결재진행코드")
    private String bizApprStepCd;
    @ApiModelProperty(value = "결재진행명")
    private String bizApprStepNm;

    @ApiModelProperty(value = "관련업무 목록")
    private List<ChangeType> changeTypes;
    @ApiModelProperty(value = "정비운전 목록")
    private List<ChangeOperation> changeOperations;
    @ApiModelProperty(value = "관련업무 목록")
    private List<ChangeEffect> changeEffects;
    @ApiModelProperty(value = "변경관리위원회")
    private ChangeCommi changeCommi;
    @ApiModelProperty(value = "관련업무 목록")
    private List<ChangeCheckItemResult> changeCheckItemResults;
    @ApiModelProperty(value = "관련업무진행사항 목록")
    private List<ChangeRefWork> changeRefWorks;
    @ApiModelProperty(value = "인허가검토 목록")
    private List<ChangeElectLaw> changeElectLaws;
}
