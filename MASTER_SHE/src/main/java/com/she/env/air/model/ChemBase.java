package com.she.env.air.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "대기 약품")
public class ChemBase {

    @ApiModelProperty(value = "약품 코드")
    private String eairChemCd;

    @ApiModelProperty(value = "약품명")
    private String eairChemNm;

    @ApiModelProperty(value = "약품명 + 단위")
    private String chemNm;

    @ApiModelProperty(value = "단위 코드")
    private String envUnitCd;

    @ApiModelProperty(value = "SEMS 코드")
    private String semsCode;

    @ApiModelProperty(value = "단위 명")
    private String envUnitNm;

    @ApiModelProperty(value = "비고")
    private String remark;

    @ApiModelProperty(value = "사업장 코드")
    private String plantCd;

    @ApiModelProperty(value = "사업장명")
    private String plantNm;

    @ApiModelProperty(value = "사용여부")
    private String useYn;

    @ApiModelProperty(value = "사용여부 이름")
    private String useYnNm;

    @ApiModelProperty(value = "정렬")
    private String sortOrder;

    @ApiModelProperty(value = "약품 아이템")
    private String code;

    @ApiModelProperty(value = "약품 아이템")
    private String codeNm;

    @ApiModelProperty(value = "등록자")
    private String createUserId;

    @ApiModelProperty(value = "등록일")
    private String createDt;

    @ApiModelProperty(value = "수정자")
    private String updateUserId;

    @ApiModelProperty(value = "수정일")
    private String updateDt;

    @ApiModelProperty(value = "등록자 명")
    private String createUserNm;

    @ApiModelProperty(value = "수정자 명")
    private String updateUserNm;

    @ApiModelProperty(value = "작성자")
    private String writerUserNm;

    @ApiModelProperty(value = "작성일")
    private String writerDt;
}
