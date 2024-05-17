package com.she.baseInfo.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "시설마스터")
@Getter
@Setter
public class SafFacilityMst {

    @ApiModelProperty(value = "사업장코드")
    private String plantCd;

    @ApiModelProperty(value = "사업장명")
    private String plantNm;

    @ApiModelProperty(value = "시설유형코드")
    private String comFacilityTypeCd;

    @ApiModelProperty(value = "시설유형명")
    private String comFacilityTypeNm;

    @ApiModelProperty(value = "시설코드")
    private String facilityCd;

    @ApiModelProperty(value = "시설명")
    private String facilityNm;

    @ApiModelProperty(value = "시설관리번호")
    private String facilityMgrNum;

    @ApiModelProperty(value = "설치위치")
    private String installLocate;

    @ApiModelProperty(value = "관리부서코드")
    private String deptCd;

    @ApiModelProperty(value = "관리부서명")
    private String deptNm;

    @ApiModelProperty(value = "사용여부")
    private String useYn;

    @ApiModelProperty(value = "사용여부명")
    private String useYnNm;
}
