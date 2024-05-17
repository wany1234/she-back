package com.she.chm.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@ApiModel(description = "규제DB업로드이력별규제물질정보")
@Data
@AllArgsConstructor
@Builder
public class ChemicalRegulDb {

    @ApiModelProperty(value = "규제DB업로드코드(YYYY-MM-DD-HH24-MI-SS")
    private String chmRegulDbCd;

    @ApiModelProperty(value = "규제법규명")
    private String lawNm;

    @ApiModelProperty(value = "규제항목명")
    private String regulItmNm;

    @ApiModelProperty(value = "구분자(1:추가,2:삭제,3:변경[기준치],4:변경없음")
    private Short dbType;

    @ApiModelProperty(value = "구분자명")
    private String dbTypeNm;

    @ApiModelProperty(value = "쉬즈케미컬chemID")
    private String chemId;

    @ApiModelProperty(value = "관리자번호(규제관리번호)")
    private String adminNum;

    @ApiModelProperty(value = "화학물질명영문")
    private String chmNmEn;

    @ApiModelProperty(value = "화학물질명국문")
    private String chmNmKr;

    @ApiModelProperty(value = "화학물질명일반명")
    private String chmNmSyn;

    @ApiModelProperty(value = "CAS NO.")
    private String casNo;

    @ApiModelProperty(value = "UN NO.")
    private String unNo;

    @ApiModelProperty(value = "EC NO.")
    private String ecNo;

    @ApiModelProperty(value = "규제번호,고시번호(기존화학물질인경우)")
    private String etcNo;

    @ApiModelProperty(value = "상세분류")
    private String detailClass;

    @ApiModelProperty(value = "고시일")
    private String noticeDate;

    @ApiModelProperty(value = "함량기준설명")
    private String contStdDesc;

    @ApiModelProperty(value = "함량기준수치")
    private String contStdNum;

    @ApiModelProperty(value = "유해성정보")
    private String hazardInfo;

    @ApiModelProperty(value = "취급정보")
    private String handleInfo;

    @ApiModelProperty(value = "분자식/화학식/구조식")
    private String formula;

/*    산업안전보건법    금지물질        금지내용
    산업안전보건법    노동부_CMR    발암성
    산업안전보건법    노출기준        TWA_ppm
    산업안전보건법    유해위험물질    규정량(kg)
    산업안전보건법    특수검진        배치 후 첫번째 특수건강진단 시기
    산업안전보건법    허용기준        TWA_ppm
    위험물안전관리법    위험물안전관리법    지정수량
    화학물질관리법    금지물질        금지내용
    화학물질관리법    등록대상기존    등록일자
    화학물질관리법    사고대비         저장량_kg
    화학물질관리법    유독물질        신호어
    화학물질관리법    제한물질        제한내용*/
    @ApiModelProperty(value = "기타참조항목1:금지내용,발암성,규정량(kg),배치 후 첫번째 특수건강진단 시기,TWA_ppm,지정수량,금지내용,등록일자,저장량_kg,신호어,제한내용")
    private String etcRefCont1;

/*    산업안전보건법    노동부_CMR    생식세포변이원성
    산업안전보건법    노출기준        TWA_mg/m3
    산업안전보건법    유해위험물질    함량기준
    산업안전보건법    특수검진        주기
    산업안전보건법    허용기준        TWA_mg/m3
    화학물질관리법    사고대비         사용량_kg/yr
    화학물질관리법    유독물질        그림문자*/
    @ApiModelProperty(value = "기타참조항목2:생식세포변이원성,TWA_mg/m3,함량기준,주기,사용량_kg/yr,그림문자")
    private String etcRefCont2;

/*    산업안전보건법    노동부_CMR    생식독성
    산업안전보건법    노출기준        TWA_개/cm3
    산업안전보건법    허용기준        STEL_ppm
    화학물질관리법    사고대비        예외 사항*/
    @ApiModelProperty(value = "기타참조항목3:생식독성,TWA_개/cm3,STEL_ppm,예외 사항")
    private String etcRefCont3;

/*    산업안전보건법    노동부_CMR    참조사항
    산업안전보건법    노출기준        STEL_ppm
    산업안전보건법    허용기준        STEL_mg/m3
    화학물질관리법    사고대비        호흡보호구*/
    @ApiModelProperty(value = "기타참조항목4:참조사항,STEL_ppm,STEL_mg/m3,호흡보호구")
    private String etcRefCont4;

/*    산업안전보건법    노출기준    STEL_mg/m3
    화학물질관리법    사고대비    보호복*/
    @ApiModelProperty(value = "기타참조항목5:STEL_mg/m3,보호복")
    private String etcRefCont5;

/*    산업안전보건법    노출기준    Ceiling_ppm
    화학물질관리법    사고대비    보호장갑*/
    @ApiModelProperty(value = "기타참조항목6:Ceiling_ppm,보호장갑")
    private String etcRefCont6;

/*    산업안전보건법    노출기준    Ceiling_mg/m3*/
    @ApiModelProperty(value = "기타참조항목7:Ceiling_mg/m3")
    private String etcRefCont7;

/*    산업안전보건법    노출기준    Skin**/
    @ApiModelProperty(value = "기타참조항목8:Skin*")
    private String etcRefCont8;

    @ApiModelProperty(value = "기타참조항목9:기타참조항목9")
    private String etcRefCont9;

    @ApiModelProperty(value = "기타참조항목10")
    private String etcRefCont10;

    @ApiModelProperty(value = "사용여부")
    private String useYn;

    @ApiModelProperty(value = "유효성검사오류메시지")
    private String errMsg;

    @ApiModelProperty(value = "적용처리메시지")
    private String applyMsg;

    @ApiModelProperty(value = "[server paging] 총 갯수")
    private int totalCnt;

}
