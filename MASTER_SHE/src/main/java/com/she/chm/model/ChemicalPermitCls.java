package com.she.chm.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "인허가신고서")
@Getter
@Setter
public class ChemicalPermitCls {

    @ApiModelProperty(value = "인허가신고서번호")
    private int permitClsNo;

    @ApiModelProperty(value = "인허가신고서명")
    private String permitClsNm;

    @ApiModelProperty(value = "인허가신고서코드")
    private String permitClsCd;

    @ApiModelProperty(value = "인허가구분코드")
    private String permitKindCd;

    @ApiModelProperty(value = "인허가구분명")
    private String permitKindNm;

    @ApiModelProperty(value = "인허가확인방법-확인")
    private String confirmYn;

    @ApiModelProperty(value = "인허가확인방법-첨부파일")
    private String attachYn;

    @ApiModelProperty(value = "정렬순서")
    private int sortOrder;

    @ApiModelProperty(value = "제출기관")
    private String mainOrg;

    @ApiModelProperty(value = "관련법규")
    private String permitLaw;

    @ApiModelProperty(value = "처리기간")
    private String permitPeriod;

    @ApiModelProperty(value = "협회화면링크")
    private String permitUrl;

    @ApiModelProperty(value = "사업장코드")
    private String plantCd;

    @ApiModelProperty(value = "사업장명")
    private String plantNm;

    @ApiModelProperty(value = "사용여부")
    private String useYn;

    @ApiModelProperty(value = "사용여부명")
    private String useYnNm;

    @ApiModelProperty(value = "필수여부")
    private String needYn;

    @ApiModelProperty(value = "필수여부명")
    private String needYnNm;

    @ApiModelProperty(value = "생성자아이디")
    private String createUserId;

    @ApiModelProperty(value = "생성자명")
    private String createUserNm;

    @ApiModelProperty(value = "생성일")
    private String createDt;

    @ApiModelProperty(value = "수정자아이디")
    private String updateUserId;

    @ApiModelProperty(value = "수정자명")
    private String updateUserNm;

    @ApiModelProperty(value = "수정일")
    private String updateDt;

    @ApiModelProperty(value = "규제법규명")
    private String regulLawNm;

    @ApiModelProperty(value = "규제항목명리스트")
    private String regulItmNms;

    @ApiModelProperty(value = "규제항목번호리스트")
    private int[] regulItmNos;

    @ApiModelProperty(value = "작성자")
    private String writerUserNm;

    @ApiModelProperty(value = "작성일")
    private String writerDt;
}
