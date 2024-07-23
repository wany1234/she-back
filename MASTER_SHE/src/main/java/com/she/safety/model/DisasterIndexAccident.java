package com.she.safety.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "종합재해지수 현황")
@Data
public class DisasterIndexAccident {

	@ApiModelProperty(value = "사고번호")
	private String safAccidentNo;
    @ApiModelProperty(value = "년도")
    private String year;
    @ApiModelProperty(value = "사업장코드")
    private String plantCd;
    @ApiModelProperty(value = "사업장이름")
    private String plantNm;
    @ApiModelProperty(value = "사고명")
    private String accidentTitle;
    @ApiModelProperty(value = "부서코드")
    private String deptCd;
    @ApiModelProperty(value = "부서명")
    private String deptNm;
    @ApiModelProperty(value = "발생장소")
    private String area;
    @ApiModelProperty(value = "진행코드")
    private String accidentStepCd;
    @ApiModelProperty(value = "사고종류")
    private String accidentTypeNms;
    @ApiModelProperty(value = "사고자명")
    private String accKindNms;
    @ApiModelProperty(value = "사고조사대상여부")
    private String investYn;
}
