package com.she.safety.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "훈련계획관리")
@Data
public class Emergency {

    @ApiModelProperty(value = "훈련계획번호")
    private int safTrainPlanNo;
    @ApiModelProperty(value = "사업장코드")
    private String plantCd;
    @ApiModelProperty(value = "사업장명")
    private String plantNm;
    @ApiModelProperty(value = "주관부서코드")
    private String deptCd;
    @ApiModelProperty(value = "주관부서명 ")
    private String deptNm;
    @ApiModelProperty(value = "수행부서명 ")
    private String subDeptNm;
    @ApiModelProperty(value = "대상부서Cnt")
    private String subDeptCnt;
    @ApiModelProperty(value = "참여인원수Cnt")
    private String userCnt;
    @ApiModelProperty(value = "미완료")
    private String stateNCnt;
    @ApiModelProperty(value = "기한초과")
    private String overDateCnt;
    @ApiModelProperty(value = "완료")
    private String stateYCnt;
    @ApiModelProperty(value = "훈련구분코드")
    private String trainTypeCd;
    @ApiModelProperty(value = "훈련구분명")
    private String trainTypeNm;
    @ApiModelProperty(value = "훈련교관ID")
    private String trainUserId;
    @ApiModelProperty(value = "훈련교관명")
    private String trainUserNm;
    @ApiModelProperty(value = "훈련교관소속부서코드")
    private String trainDeptCd;
    @ApiModelProperty(value = "훈련교관소속부서코드명")
    private String trainDeptNm;
    @ApiModelProperty(value = "훈련명")
    private String trainNm;
    @ApiModelProperty(value = "훈련시작일")
    private String trainStartYmd;
    @ApiModelProperty(value = "훈련종료일")
    private String trainEndYmd;
    @ApiModelProperty(value = "훈련시간")
    private String trainTime;
    @ApiModelProperty(value = "훈련시작시간")
    private String trainSTime;
    @ApiModelProperty(value = "훈련종료시간")
    private String trainETime;
    @ApiModelProperty(value = "훈련시작시")
    private String trainSHour;
    @ApiModelProperty(value = "훈련시작분")
    private String trainSMin;
    @ApiModelProperty(value = "훈련종료시")
    private String trainEHour;
    @ApiModelProperty(value = "훈련종료분")
    private String trainEMin;
    @ApiModelProperty(value = "훈련장소")
    private String trainPlace;
    @ApiModelProperty(value = "훈련내용요약")
    private String trainContent;
    @ApiModelProperty(value = "등록자ID")
    private String createUserId;
    @ApiModelProperty(value = "등록자")
    private String createUserNm;
    @ApiModelProperty(value = "등록일")
    private String createDt;
    @ApiModelProperty(value = "수정자")
    private String updateUserId;
    @ApiModelProperty(value = "수정일")
    private String updateDt;
    @ApiModelProperty(value = "변경발의번호")
    private String chngNum;
    @ApiModelProperty(value = "훈련방법코드")
    private String trainMethodCd;
    @ApiModelProperty(value = "훈련방법명")
    private String trainMethodNm;
    @ApiModelProperty(value = "훈련계획단계")
    private String trainPlanState;
    @ApiModelProperty(value = "훈련계획단계명")
    private String trainPlanStateNm;
    @ApiModelProperty(value = "원훈련계획번호")
    private String oriSafTrainPlanNo;
    @ApiModelProperty(value = "원훈련명")
    private String oriTrainNm;
    @ApiModelProperty(value = "재훈련계획번호")
    private String reSafTrainPlanNo;
    @ApiModelProperty(value = "재훈련명")
    private String reTrainNm;
    @ApiModelProperty(value = "재훈련계획단계")
    private String reTrainPlanState;

    @ApiModelProperty(value = "재훈련여부")
    private String reTrainYn;

    @ApiModelProperty(value = "훈련일")
    private String trainYmd;
    @ApiModelProperty(value = "훈련결과_훈련시작시간")
    private String subTrainSTime;
    @ApiModelProperty(value = "훈련결과_훈련종료시간")
    private String subTrainETime;
    @ApiModelProperty(value = "비고")
    private String trainDesc;
    @ApiModelProperty(value = "참여인원수")
    private String trainUserCnt;
    @ApiModelProperty(value = "참여여부")
    private String trainYn;
    @ApiModelProperty(value = "개선요청")
    private String requestCnt;
    @ApiModelProperty(value = "조치미완료")
    private String incompletCnt;
    @ApiModelProperty(value = "조치기한초과")
    private String overdueCnt;
    @ApiModelProperty(value = "조치완료")
    private String completCnt;

    @ApiModelProperty(value = "훈련계획관리t사업장목록")
    private List<EmergencyPlant> emergencyPlantList;
    @ApiModelProperty(value = "훈련계획관리사업장코드목록")
    private List<String> plantCdList;
    @ApiModelProperty(value = "훈련계획관리대상자목록")
    private List<EmergencyPsn> emergencyUserList;
    @ApiModelProperty(value = "훈련계획관리대상부서목록")
    private List<EmergencyDept> emergencyDeptList;
    @ApiModelProperty(value = "훈련계획관리시나리오목록")
    private List<EmergencyScenario> emergencyScenarioList;
    @ApiModelProperty(value = "훈련이수자(외부)목록")
    private List<EmergencyOutsidePsn> emergencyOutSideUserList;
    @ApiModelProperty(value = "훈련계획별동영상")
    private EmergencyVideo emergencyVideo;

}
