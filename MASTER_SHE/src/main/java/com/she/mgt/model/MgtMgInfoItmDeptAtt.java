package com.she.mgt.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "경영정보항목 부서유형")
@Data
public class MgtMgInfoItmDeptAtt {

    @ApiModelProperty(value = "부서구분번호")
    private int mgtMgInfoDeptAttNo;

    @ApiModelProperty(value = "경영정보항목번호")
    private int mgtMgInfoItmNo;

    @ApiModelProperty(value = "사업장코드")
    private String plantCd;

    @ApiModelProperty(value = "사업장")
    private String plantNm;

    @ApiModelProperty(value = "부서구분명")
    private String deptAttNm;

    @ApiModelProperty(value = "사용여부")
    private String useYn;

    @ApiModelProperty(value = "사용여부")
    private String useYnNm;

    @ApiModelProperty(value = "항목구분코드")
    private String infoItmTypeCd;

    @ApiModelProperty(value = "항목구분")
    private String infoItmTypeNm;

    @ApiModelProperty(value = "항목분류코드")
    private String infoItmAttCd;

    @ApiModelProperty(value = "항목분류")
    private String infoItmAttNm;

    @ApiModelProperty(value = "항목명")
    private String mgInfoItmNm;

    @ApiModelProperty(value = "정렬순서")
    private int sortOrder;

    @ApiModelProperty(value = "등록자")
    private String createUserId;

    @ApiModelProperty(value = "등록일")
    private String createDt;

    @ApiModelProperty(value = "수정자")
    private String updateUserId;

    @ApiModelProperty(value = "수정일")
    private String updateDt;
}
