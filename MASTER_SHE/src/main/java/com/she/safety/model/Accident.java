package com.she.safety.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "사고관리")
@Getter
@Setter
public class Accident {

    @ApiModelProperty(value = "사내사고번호")
    private int safAccidentNo;
    @ApiModelProperty(value = "사업장코드")
    private String plantCd;
    @ApiModelProperty(value = "사업장명")
    private String plantNm;
    @ApiModelProperty(value = "사고명")
    private String accidentTitle;
    @ApiModelProperty(value = "발행번호")
    private String accidentNum;
    @ApiModelProperty(value = "사내사고진행단계")
    private String accidentStepCd;
    @ApiModelProperty(value = "사내사고진행단계명")
    private String accidentStepNm;
    @ApiModelProperty(value = "발생일")
    private String accidentYmd;
    @ApiModelProperty(value = "발생시")
    private String accidentHour;
    @ApiModelProperty(value = "발생분")
    private String accidentMinute;
    @ApiModelProperty(value = "발생부서코드")
    private String deptCd;
    @ApiModelProperty(value = "발생부서명")
    private String deptNm;
    @ApiModelProperty(value = "발생장소")
    private String area;
    @ApiModelProperty(value = "날씨코드")
    private String weatherCd;
    @ApiModelProperty(value = "날씨명")
    private String weatherNm;
    @ApiModelProperty(value = "사고종류기타")
    private String accidentTypeEtc;
    @ApiModelProperty(value = "보고자사번")
    private String rptUserId;
    @ApiModelProperty(value = "보고자성명")
    private String rptUserNm;
    @ApiModelProperty(value = "보고자소속코드")
    private String rptDeptCd;
    @ApiModelProperty(value = "보고자소속명")
    private String rptDeptNm;
    @ApiModelProperty(value = "보고자사무실전화번호")
    private String rptOfficeTel;
    @ApiModelProperty(value = "보고일시")
    private String rptDt;
    @ApiModelProperty(value = "사고개요")
    private String contents;
    @ApiModelProperty(value = "상세피해내용")
    private String detailDamageDesc;
    @ApiModelProperty(value = "인적피해_상해정도")
    private String humanInjuryCd;
    @ApiModelProperty(value = "인적피해_상해정도명")
    private String humanInjuryNm;
    @ApiModelProperty(value = "인적피해내용")
    private String humanInjuryDesc;
    @ApiModelProperty(value = "물적피해내용")
    private String matDamageDesc;

    @ApiModelProperty(value = "사업장무재해여부")
    private String noAccidentYn;
    @ApiModelProperty(value = "사업장무재해여부명")
    private String noAccidentYnNm;
    @ApiModelProperty(value = "부서무재해여부")
    private String noAccidentDeptYn;
    @ApiModelProperty(value = "부서무재해여부명")
    private String noAccidentDeptYnNm;

    @ApiModelProperty(value = "조치사항")
    private String firstAct;
    @ApiModelProperty(value = "사고조사대상여부")
    private String investYn;
    @ApiModelProperty(value = "사고조사대상여부명")
    private String investYnNm;
    @ApiModelProperty(value = "추정원인")
    private String provReason;
    @ApiModelProperty(value = "원인유형코드")
    private String cauTypeCd;
    @ApiModelProperty(value = "원인유형명")
    private String cauTypeNm;
    @ApiModelProperty(value = "원인유형기타")
    private String cauTypeEtc;
    @ApiModelProperty(value = "초기대책")
    private String bgnMeas;
    @ApiModelProperty(value = "물적피해직접손실액")
    private String dirLossAmount;
    @ApiModelProperty(value = "물적피해간접손실액")
    private String indirLossAmount;
    @ApiModelProperty(value = "환경피해내용")
    private String envDamageDesc;
    @ApiModelProperty(value = "환경피해_수계누출량")
    private String drainLeak;
    @ApiModelProperty(value = "환경피해_그외누출량")
    private String etcLeak;
    @ApiModelProperty(value = "사고분류코드")
    private String accidentAttCd;
    @ApiModelProperty(value = "사고분류명")
    private String accidentAttNm;
    @ApiModelProperty(value = "피해정도_인적피해코드")
    private String humanDamage;
    @ApiModelProperty(value = "피해정도_인적피해명")
    private String humanDamageNm;
    @ApiModelProperty(value = "피해정보_물적피해")
    private String matDamage;
    @ApiModelProperty(value = "독성여부")
    private String toxiYn;
    @ApiModelProperty(value = "독성여부명")
    private String toxiYnNm;
    @ApiModelProperty(value = "발생형태대분류코드")
    private String occKindCd;
    @ApiModelProperty(value = "발생형태대분류명")
    private String occKindNm;
    @ApiModelProperty(value = "발생형태중분류코드")
    private String occAttCd;
    @ApiModelProperty(value = "발생형태중분류명")
    private String occAttNm;
    @ApiModelProperty(value = "사고직접원인코드")
    private String immCauseCd;
    @ApiModelProperty(value = "사고직접원인명")
    private String immCauseNm;
    @ApiModelProperty(value = "사고간접원인코드")
    private String indCauseCd;
    @ApiModelProperty(value = "사고간접원인명")
    private String indCauseNm;
    @ApiModelProperty(value = "사고원인내용")
    private String causeContents;
    @ApiModelProperty(value = "기인물코드")
    private String originMatCd;
    @ApiModelProperty(value = "가해물코드")
    private String assMatCd;
    @ApiModelProperty(value = "공정상태코드")
    private String prcsStateCd;
    @ApiModelProperty(value = "처리구분코드")
    private String endTypeCd;
    @ApiModelProperty(value = "근로손실일수")
    private String workLostDay;
    @ApiModelProperty(value = "예상치료기간")
    private String expectCureDay;
    @ApiModelProperty(value = "사고등급코드")
    private String accidentLvlCd;
    @ApiModelProperty(value = "사고등급명")
    private String accidentLvlNm;
    @ApiModelProperty(value = "사고등급명")
    private String etc;
    @ApiModelProperty(value = "등록자")
    private String createUserId;
    @ApiModelProperty(value = "등록자명")
    private String createUserNm;
    @ApiModelProperty(value = "등록일")
    private String createDt;
    @ApiModelProperty(value = "수정자")
    private String updateUserId;
    @ApiModelProperty(value = "수정자명")
    private String updateUserNm;
    @ApiModelProperty(value = "수정일")
    private String updateDt;

    @ApiModelProperty(value = "결재진행번호")
    private String apprRqstNo;
    @ApiModelProperty(value = "등록결재진행번호")
    private String s1ApprRqstNo;
    @ApiModelProperty(value = "접수결재진행번호")
    private String s2ApprRqstNo;
    @ApiModelProperty(value = "조사결과결재진행번호")
    private String s3ApprRqstNo;
    @ApiModelProperty(value = "결재진행코드")
    private String bizApprStepCd;
    @ApiModelProperty(value = "결재진행명")
    private String bizApprStepNm;

    @ApiModelProperty(value = "공사번호")
    private int completeable;

    @ApiModelProperty(value = "사내사고구분명")
    private String accKindNms;
    @ApiModelProperty(value = "사내사고종류명")
    private String accidentTypeNms;
    @ApiModelProperty(value = "사고자명s")
    private String victimNms;

    @ApiModelProperty(value = "사내사고구분")
    private List<AccidentOcctype> accidentTypes;
    @ApiModelProperty(value = "사내사고종류")
    private List<AccidentKind> accKindCds;
    @ApiModelProperty(value = "사내사고조사")
    private AccidentInvest accidentInvest;
    @ApiModelProperty(value = "사새사고조사인원")
    private List<AccidentInvestPsn> accidentInvestPsns;
    @ApiModelProperty(value = "사고자정보")
    private List<AccidentVictim> accidentVictims;
    @ApiModelProperty(value = "사내사고원인및대책")
    private List<AccidentCauMeas> accidentCauMeass;
    @ApiModelProperty(value = "사내사고 관련물질")
    private List<AccidentChemprod> accidentChemprod;
    @ApiModelProperty(value = "사내사고 관련설비")
    private List<AccidentFacility> accidentFacility;
    @ApiModelProperty(value = "사내사고 관계자정보")
    private List<AccidentRefPsn> accidentRefPsns;

    @ApiModelProperty(value = "작성자")
    private String writerUserNm;

    @ApiModelProperty(value = "작성일")
    private String writerDt;

    @ApiModelProperty(value = "개선요청")
    private String requestCnt;

    @ApiModelProperty(value = "조치미완료")
    private String incompletCnt;

    @ApiModelProperty(value = "조치기한초과")
    private String overdueCnt;

    @ApiModelProperty(value = "조치완료")
    private String completCnt;

    @ApiModelProperty(value = "단계(상태)")
    private String stateNm;

}
