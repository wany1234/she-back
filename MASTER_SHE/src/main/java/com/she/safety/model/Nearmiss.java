package com.she.safety.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "아차사고")
@Data
public class Nearmiss {

    @ApiModelProperty(value = "아차사고번호")
    private int safNearmissNo;

    @ApiModelProperty(value = "사업장코드")
    private String plantCd;

    @ApiModelProperty(value = "사업장명")
    private String plantNm;

    @ApiModelProperty(value = "아차사고명")
    private String nearmissTitle;

    @ApiModelProperty(value = "아차사고명")
    private String accidentTitle;

    @ApiModelProperty(value = "발행번호")
    private String nearmissNum;

    @ApiModelProperty(value = "진행단계코드")
    private String processStepCd;

    @ApiModelProperty(value = "진행단계코드명")
    private String processStepNm;

    @ApiModelProperty(value = "보고자사번")
    private String rptUserId;

    @ApiModelProperty(value = "보고자소속부서코드")
    private String rptDeptCd;

    @ApiModelProperty(value = "보고자소속부서명")
    private String rptDeptNm;

    @ApiModelProperty(value = "보고일시")
    private String rptDt;

    @ApiModelProperty(value = "발생일")
    private String nearmissYmd;

    @ApiModelProperty(value = "발생일")
    private String accidentYmd;

    @ApiModelProperty(value = "발생시")
    private String nearmissHour;

    @ApiModelProperty(value = "발생분")
    private String nearmissMinute;

    @ApiModelProperty(value = "발생부서")
    private String deptCd;

    @ApiModelProperty(value = "발생부서명")
    private String deptNm;

    @ApiModelProperty(value = "발생장소")
    private String area;

    @ApiModelProperty(value = "사고구분코드")
    private String nearKindCd;

    @ApiModelProperty(value = "사고구분코드명")
    private String nearKindNm;

    @ApiModelProperty(value = "사고구분코드명")
    private String accKindNms;

    @ApiModelProperty(value = "사고종류코드")
    private String occTypeCd;

    @ApiModelProperty(value = "사고종류코드명")
    private String occTypeNm;

    @ApiModelProperty(value = "사고종류코드명")
    private String accidentTypeNms;

    @ApiModelProperty(value = "사고종류기타")
    private String nearmissTypeEtc;

    @ApiModelProperty(value = "공정상태코드")
    private String prcsStateCd;

    @ApiModelProperty(value = "공정상태코드명")
    private String prcsStateNm;

    @ApiModelProperty(value = "날씨코드")
    private String wethCd;

    @ApiModelProperty(value = "날씨코드명")
    private String wethNm;

    @ApiModelProperty(value = "사고개요")
    private String contents;

    @ApiModelProperty(value = "조치사항")
    private String bgnMeas;

    @ApiModelProperty(value = "추정원인")
    private String provReason;

    @ApiModelProperty(value = "발생형태대분류코드")
    private String occPtnClsCd;

    @ApiModelProperty(value = "발생형태대분류코드명")
    private String occPtnClsNm;

    @ApiModelProperty(value = "발생형태대분류코드명")
    private String occKindNm;

    @ApiModelProperty(value = "발생형태중분류코드")
    private String occPtnMClsCd;

    @ApiModelProperty(value = "발생형태중분류코드명")
    private String occPtnMClsNm;

    @ApiModelProperty(value = "발생형태중분류코드명")
    private String occAttNm;

    @ApiModelProperty(value = "사고직접원인코드")
    private String immCauseCd;

    @ApiModelProperty(value = "사고직접원인코드명")
    private String immCauseNm;

    @ApiModelProperty(value = "사고간접원인코드")
    private String indCauseCd;

    @ApiModelProperty(value = "사고간접원인코드명")
    private String indCauseNm;

    @ApiModelProperty(value = "기인물코드")
    private String originMatCd;

    @ApiModelProperty(value = "기인물코드명")
    private String originMatNm;

    @ApiModelProperty(value = "가해물코드")
    private String assMatCd;

    @ApiModelProperty(value = "가해물코드명")
    private String assMatNm;

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

    @ApiModelProperty(value = "등록/수정자명")
    private String showUserNm;

    @ApiModelProperty(value = "등록/수정 부서명")
    private String showDeptNm;

    @ApiModelProperty(value = "아차사고 사고자정보 목록")
    private List<NearmissVictim> nearmissVictimList;

    @ApiModelProperty(value = "아차사고 발생유형 목록")
    private List<NearmissOcctype> nearmissOcctypeList;

    @ApiModelProperty(value = "결재요청번호")
    private int apprRqstNo;

    @ApiModelProperty(value = "결재진행단계코드")
    private String bizApprStepCd;

    @ApiModelProperty(value = "결재진행단계")
    private String bizApprStepNm;

    @ApiModelProperty(value = "작성일")
    private String writerDt;
}
