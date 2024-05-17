package com.she.safety.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "경영_무재해_사업장무재해현황")
@Getter
@Setter
public class NoAccident {

    @ApiModelProperty(value = "사업장무재해번호")
    private int safNoAccidentNo;

    @ApiModelProperty(value = "부서무재해번호")
    private int safNoAccidentDeptNo;

    @ApiModelProperty(value = "무재해시작일")
    private String startYmd;

    @ApiModelProperty(value = "무재해달성예정일")
    private String endSchYmd;

    @ApiModelProperty(value = "목표달성일")
    private String endYmd;

    @ApiModelProperty(value = "달성여부")
    private String endYn;

    @ApiModelProperty(value = "달성여부명")
    private String endYnNm;

    @ApiModelProperty(value = "종료여부")
    private String closeYn;

    @ApiModelProperty(value = "종료여부명")
    private String closeYnNm;

    @ApiModelProperty(value = "사업장코드")
    private String plantCd;

    @ApiModelProperty(value = "사업장명")
    private String plantNm;

    @ApiModelProperty(value = "부서코드")
    private String deptCd;

    @ApiModelProperty(value = "부서명")
    private String deptNm;

    @ApiModelProperty(value = "세부부서명")
    private String dtlDeptNm;

    @ApiModelProperty(value = "인시사용여부")
    private String mhUseYn;

    @ApiModelProperty(value = "목표인시")
    private int targetMh;

    @ApiModelProperty(value = "초기인시")
    private int initMh;

    @ApiModelProperty(value = "기본인시-평일")
    private int normMh;

    @ApiModelProperty(value = "기본인시-휴일")
    private int holiMh;

    @ApiModelProperty(value = "근무인원-직원")
    private int dayMancntCpy;

    @ApiModelProperty(value = "근무인원-협력업체")
    private int dayMancntCon;

    @ApiModelProperty(value = "근무인원-합계")
    private String dayMancntSum;

    @ApiModelProperty(value = "일시사용여부")
    private String dayUseYn;

    @ApiModelProperty(value = "목표일")
    private int targetDays;

    @ApiModelProperty(value = "초기일")
    private int initDays;

    @ApiModelProperty(value = "기본일-평일")
    private int normalDays;

    @ApiModelProperty(value = "기본일-휴일")
    private int holiDays;

    @ApiModelProperty(value = "비고")
    private String remark;

    @ApiModelProperty(value = "사용여부")
    private String useYn;

    @ApiModelProperty(value = "등록자ID")
    private String createUserId;

    @ApiModelProperty(value = "등록일")
    private String createDt;

    @ApiModelProperty(value = "수정자ID")
    private String updateUserId;

    @ApiModelProperty(value = "수정일")
    private String updateDt;
    @ApiModelProperty(value = "작성자")
    private String writerUserNm;

    @ApiModelProperty(value = "작성일")
    private String writerDt;

    @ApiModelProperty(value = "총-인시")
    private int totalMh;

    @ApiModelProperty(value = "누적일")
    private int totalDays;

    @ApiModelProperty(value = "누적일")
    private int currentTotalDays;

    @ApiModelProperty(value = "배수 현재일수")
    private int mulTotalDays;

    @ApiModelProperty(value = "진행-인시")
    private int progressMh;

    @ApiModelProperty(value = "진행_일")
    private int progressDays;

    @ApiModelProperty(value = "일별 생성인시")
    private String byDateMh;

    @ApiModelProperty(value = "무재해 일")
    private int noAccidentDays;

    @ApiModelProperty(value = "기본일(일수)")
    private String byDefaultDays;

    @ApiModelProperty(value = "무재해 시작사유")
    private String noaccStypeCd;

    @ApiModelProperty(value = "무재해 시작사유명")
    private String noaccStypeNm;

    @ApiModelProperty(value = "무재해 종료사유")
    private String noaccEtypeCd;

    @ApiModelProperty(value = "무재해 종료사유명")
    private String noaccEtypeNm;

    @ApiModelProperty(value = "무재해 시작 관련일")
    private String noaccStypeYmd;

    @ApiModelProperty(value = "업데이트 여부")
    private int updateYn;

    @ApiModelProperty(value = "개수")
    private int countNoAccount;

    @ApiModelProperty(value = "목표배수")
    private int multiple;

    @ApiModelProperty(value = "배수기준일")
    private int stndDays;

    @ApiModelProperty(value = "포상금액")
    private int rewadMoney;

    @ApiModelProperty(value = "포상금액")
    private String rewadMoneyDis;

    @ApiModelProperty(value = "표창인원-사장")
    private int cmdaNumCeo;

    @ApiModelProperty(value = "표창인원-사장")
    private String cmdaNumCeoDis;

    @ApiModelProperty(value = "표창인원-공장장")
    private int cmdaNumPm;

    @ApiModelProperty(value = "표창인원-공장장")
    private String cmdaNumPmDis;

    @ApiModelProperty(value = "표창인원")
    private String cmdaNumSum;

    @ApiModelProperty(value = "d-day")
    private String dday;

    @ApiModelProperty(value = "사고건수")
    private String accidentCnt;
}
