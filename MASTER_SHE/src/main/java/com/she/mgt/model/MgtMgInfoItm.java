package com.she.mgt.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "경영정보항목")
@Data
public class MgtMgInfoItm {

    @ApiModelProperty(value = "경영정보항목번호")
    private int mgtMgInfoItmNo;

    @ApiModelProperty(value = "상위경영정보항목번호")
    private int pMgtMgInfoItmNo;

    @ApiModelProperty(value = "항목구분코드")
    private String infoItmTypeCd;

    @ApiModelProperty(value = "항목분류코드")
    private String infoItmAttCd;

    @ApiModelProperty(value = "항목명")
    private String mgInfoItmNm;

    @ApiModelProperty(value = "단위")
    private String unitNm;

    @ApiModelProperty(value = "사용여부")
    private String useYn;

    @ApiModelProperty(value = "정렬순서")
    private int sortOrder;
}
