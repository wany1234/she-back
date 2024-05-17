package com.she.health.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "근골격계 기본조사 작업분류표")
@Data
public class MuscSurveyChklist {

    @ApiModelProperty(value = "근골격계 기본조사 작업분류표번호")
    private int heaMuscSurveyChklistNo;

    @ApiModelProperty(value = "근골격계 기본조사번호")
    private int heaMuscSurveyNo;

    @ApiModelProperty(value = "단위작업명")
    private String workNm;

    @ApiModelProperty(value = "1형여부")
    private String cls01Yn;

    @ApiModelProperty(value = "2형여부")
    private String cls02Yn;

    @ApiModelProperty(value = "3형여부")
    private String cls03Yn;

    @ApiModelProperty(value = "4형여부")
    private String cls04Yn;

    @ApiModelProperty(value = "5형여부")
    private String cls05Yn;

    @ApiModelProperty(value = "6형여부")
    private String cls06Yn;

    @ApiModelProperty(value = "7형여부")
    private String cls07Yn;

    @ApiModelProperty(value = "8형여부")
    private String cls08Yn;

    @ApiModelProperty(value = "9형여부")
    private String cls09Yn;

    @ApiModelProperty(value = "10형여부")
    private String cls10Yn;

    @ApiModelProperty(value = "11형여부")
    private String cls11Yn;

    @ApiModelProperty(value = "공정코드")
    private String processCd;

    @ApiModelProperty(value = "공정명")
    private String processNm;

    @ApiModelProperty(value = "작업설비_선택값")
    private String workFacilVal;

    @ApiModelProperty(value = "작업설비_선택값_명칭")
    private String workFacilNm;

    @ApiModelProperty(value = "작업설비_시작시점")
    private String workFacilSTm;

    @ApiModelProperty(value = "작업량_선택값")
    private String workQntyVal;

    @ApiModelProperty(value = "작업량_선택값_명칭")
    private String workQntyNm;

    @ApiModelProperty(value = "작업량_시작시점")
    private String workQntySTm;

    @ApiModelProperty(value = "작업속도_선택값")
    private String workSpeedVal;

    @ApiModelProperty(value = "작업속도_시작시점")
    private String workSpeedSTm;

    @ApiModelProperty(value = "작업속도_선택값_명칭")
    private String workSpeedNm;

    @ApiModelProperty(value = "작업내용")
    private String workContents;

    @ApiModelProperty(value = "작업부하")
    private int doseLvl;

    @ApiModelProperty(value = "작업빈도")
    private int workLvl;

    @ApiModelProperty(value = "총점수(부하X빈도)")
    private int calPnt;

    @ApiModelProperty(value = "조사결과 목록")
    private List<MuscSurveyRslt> muscSurveyRslts;
}
