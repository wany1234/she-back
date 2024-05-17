package com.she.safety.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "사고관리 조사인원")
@Getter
@Setter
public class AccidentInvestPsn {

    @ApiModelProperty(value = "사내사고번호")
    private int safAccidentNo;
    @ApiModelProperty(value = "조사팀원ID")
    private String userId;
    @ApiModelProperty(value = "조사팀원성명")
    private String userNm;
    @ApiModelProperty(value = "조사팀원부서코드")
    private String deptCd;
    @ApiModelProperty(value = "조사팀원부서명")
    private String deptNm;
    @ApiModelProperty(value = "조사팀원직책")
    private String dutyNm;
    @ApiModelProperty(value = "조사팀원직무")
    private String positionNm;
}
