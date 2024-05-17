package com.she.safety.model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "사내사고 관계자정보")
@Getter
@Setter
public class AccidentRefPsn {
    @ApiModelProperty(value = "사내사고관계자번호")
    private int safAccidentRefPsnNo;
    @ApiModelProperty(value = "사내사고번호")
    private int safAccidentNo;
    @ApiModelProperty(value = "구분코드")
    private String psnTypeCd;
    @ApiModelProperty(value = "구분명")
    private String psnTypeNm;
    @ApiModelProperty(value = "내외부-성명")
    private String refNm;
    @ApiModelProperty(value = "내부-사번")
    private String refUserId;
    @ApiModelProperty(value = "내외부-직책")
    private String refDutyNm;
    @ApiModelProperty(value = "내부-부서코드")
    private String refDeptCd;
    @ApiModelProperty(value = "내외부-소속")
    private String refDeptNm;
    @ApiModelProperty(value = "내외부-비고")
    private String remarks;
}
