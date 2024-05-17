package com.she.safety.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "핵심직무관찰CTO")
@Getter
@Setter
public class Cto {
    @ApiModelProperty(value = "cto번호")
    private int safCtoNo;
    @ApiModelProperty(value = "사업장코드")
    private String plantCd;
    @ApiModelProperty(value = "사업장명")
    private String plantNm;
    @ApiModelProperty(value = "관찰일자")
    private String ctoDt;
    @ApiModelProperty(value = "소요시간")
    private String ctoTm;
    @ApiModelProperty(value = "관찰자ID")
    private String userId;
    @ApiModelProperty(value = "관찰자명")
    private String userNm;
    @ApiModelProperty(value = "관찰인원")
    private String manCnt;
    @ApiModelProperty(value = "작업ID")
    private String jobId;
    @ApiModelProperty(value = "작업명")
    private String jobNm;
    @ApiModelProperty(value = "관찰부서코드")
    private String deptCd;
    @ApiModelProperty(value = "관찰부서명")
    private String deptNm;
    @ApiModelProperty(value = "관찰공정코드")
    private String processCd;
    @ApiModelProperty(value = "관찰공정명")
    private String processNm;
    @ApiModelProperty(value = "작업구역")
    private String workArea;
    @ApiModelProperty(value = "핵심행동(우선사항)")
    private String mainAct;
    @ApiModelProperty(value = "내용설명(개선사항)")
    private String content;
    @ApiModelProperty(value = "등록자ID")
    private String createDeptNm;
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
    @ApiModelProperty(value = "진행상태코드")
    private String stepCd;
    @ApiModelProperty(value = "진행상태명")
    private String stepNm;

    @ApiModelProperty(value = "핵심행동 리스트")
    private List<CtoAct> ctoActs;
    @ApiModelProperty(value = "CTO체크리스트 리스트")
    private List<CtoCheckResult> ctoCheckResults;
    @ApiModelProperty(value = "근본원인 리스트")
    private List<CtoActCause> ctoActCauses;

    @ApiModelProperty(value = "작성자")
    private String writerUserNm;

    @ApiModelProperty(value = "작성일")
    private String writerDt;
}
