package com.she.safety.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "훈련계획관리대상부서")
@Data
public class EmergencyDept {

    @ApiModelProperty(value = "훈련계획번호")
    private int safTrainPlanNo;
    @ApiModelProperty(value = "훈련결과번호")
    private int safTrainDeptRsltNo;
    @ApiModelProperty(value = "문제점및개선사항")
    private String trainImprDesc;
    @ApiModelProperty(value = "훈련결과요약")
    private String trainEvalOpin;
    @ApiModelProperty(value = "훈련완료여부")
    private String trainCompleteYn;
    @ApiModelProperty(value = "사업장")
    private String plantCd;
    @ApiModelProperty(value = "사업장명")
    private String plantNm;
    @ApiModelProperty(value = "진행상태")
    private String stateAnm;
    @ApiModelProperty(value = "훈련명")
    private String trainNm;
    @ApiModelProperty(value = "훈련장소")
    private String trainPlace;
    @ApiModelProperty(value = "발의번호")
    private String chngNum;
    @ApiModelProperty(value = "훈련내용요약")
    private String trainContent;
    @ApiModelProperty(value = "훈련시간")
    private String trainTime;
    @ApiModelProperty(value = "훈련구분")
    private String trainTypeCd;
    @ApiModelProperty(value = "훈련구분명")
    private String trainTypeNm;
    @ApiModelProperty(value = "훈련교관부서")
    private String trainDeptCd;
    @ApiModelProperty(value = "훈련교관부서명")
    private String trainDeptNm;
    @ApiModelProperty(value = "훈련교관부서Id")
    private String trainUserId;
    @ApiModelProperty(value = "훈련교관부서명")
    private String trainUserNm;
    @ApiModelProperty(value = "대상부서")
    private String deptCd;
    @ApiModelProperty(value = "대상부서명")
    private String deptNm;
    @ApiModelProperty(value = "대상부서")
    private String subDeptCd;
    @ApiModelProperty(value = "대상부서명")
    private String subDeptNm;
    @ApiModelProperty(value = "상태코드")
    private String stateCd;
    @ApiModelProperty(value = "상태명")
    private String stateNm;
    @ApiModelProperty(value = "비고")
    private String trainDesc;
    @ApiModelProperty(value = "단계")
    private String procStepCd;
    @ApiModelProperty(value = "참여여부")
    private String trainYn;
    @ApiModelProperty(value = "참여인원수")
    private String trainUserCnt;
    @ApiModelProperty(value = "훈련일")
    private String trainYmd;
    @ApiModelProperty(value = "결과훈련일")
    private String subTrainYmd;
    @ApiModelProperty(value = "훈련시작시간")
    private String trainSTime;
    @ApiModelProperty(value = "훈련종료시간")
    private String trainETime;
    @ApiModelProperty(value = "훈련시작시간")
    private String subTrainSTime;
    @ApiModelProperty(value = "훈련시작시간")
    private String subTrainETime;
    @ApiModelProperty(value = "훈련완료일")
    private String trainCompleteDt;
    @ApiModelProperty(value = "등록자ID")
    private String createUserId;
    @ApiModelProperty(value = "등록자명")
    private String createUserNm;
    @ApiModelProperty(value = "등록일")
    private String createDt;
    @ApiModelProperty(value = "등록자부서명")
    private String createDeptNm;
    @ApiModelProperty(value = "등록자부서코드")
    private String createDeptCd;
    @ApiModelProperty(value = "등록자직위명")
    private String createPositionNm;
    @ApiModelProperty(value = "등록자직위코드")
    private String createPositionCd;
    @ApiModelProperty(value = "훈련시작시")
    private String trainSHour;
    @ApiModelProperty(value = "훈련시작분")
    private String trainSMin;
    @ApiModelProperty(value = "훈련종료시")
    private String trainEHour;
    @ApiModelProperty(value = "훈련종료분")
    private String trainEMin;
    @ApiModelProperty(value = "개선요청")
    private String requestCnt;
    @ApiModelProperty(value = "조치미완료")
    private String incompletCnt;
    @ApiModelProperty(value = "조치기한초과")
    private String overdueCnt;
    @ApiModelProperty(value = "조치완료")
    private String completCnt;

    @ApiModelProperty(value = "훈련계획관리시나리오목록")
    private List<EmergencyScenario> emergencyScenarioList;
}
