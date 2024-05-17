package com.she.env.air.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PreventChemResult {

    @ApiModelProperty(value = "방지시설번호")
    private int eairPreventFacNo;

    @ApiModelProperty(value = "방지시설명")
    private String eairPreventFacNm;

    @ApiModelProperty(value = "약품코드")
    private String eairChemCd;

    @ApiModelProperty(value = "약품명")
    private String eairChemNm;

    @ApiModelProperty(value = "작성부서")
    private String deptCd;

    @ApiModelProperty(value = "작성부서")
    private String deptNm;

    @ApiModelProperty(value = "측정일자")
    private String measureYmd;

    @ApiModelProperty(value = "사용량")
    private int consumAmt;

    @ApiModelProperty(value = "약품단위코드")
    private String envUnitCd;

    @ApiModelProperty(value = "약품단위")
    private String envUnitNm;

    @ApiModelProperty(value = "사업장코드")
    private String plantCd;

    @ApiModelProperty(value = "사업장")
    private String plantNm;
}
