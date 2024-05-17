package com.she.chm.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "규제항목")
@Getter
@Setter
public class ChemicalRegulItem {
    @ApiModelProperty(value = "규제항목번호")
    private int regulItmNo;

    @ApiModelProperty(value = "규제항목코드")
    private String regulItmCd;

    @ApiModelProperty(value = "규제항목명")
    private String regulItmNm;

    @ApiModelProperty(value = "비고")
    private String contents;

    @ApiModelProperty(value = "규제법규코드")
    private String regulLawCd;

    @ApiModelProperty(value = "규제법규명")
    private String regulLawNm;

    @ApiModelProperty(value = "기관코드")
    private String regulOrgCd;

    @ApiModelProperty(value = "기관명")
    private String regulOrgNm;

    @ApiModelProperty(value = "사용여부")
    private String useYn;

    @ApiModelProperty(value = "사용여부명")
    private String useYnNm;

    @ApiModelProperty(value = "정렬순서")
    private int sortOrder;

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

    @ApiModelProperty(value = "작성자")
    private String writerUserNm;

    @ApiModelProperty(value = "작성일")
    private String writerDt;
}
