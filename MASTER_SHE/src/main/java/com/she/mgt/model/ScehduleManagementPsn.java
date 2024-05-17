package com.she.mgt.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "일정 참조자")
@Getter
@Setter
public class ScehduleManagementPsn {

    @ApiModelProperty(value = "일정번호")
    private int mgtCalendarNo;

    @ApiModelProperty(value = "참조자ID")
    private String userId;

    @ApiModelProperty(value = "참조자명")
    private String userNm;

    @ApiModelProperty(value = "참조자부서코드")
    private String deptCd;

    @ApiModelProperty(value = "참조자부서명")
    private String deptNm;

    @ApiModelProperty(value = "직책코드")
    private String dutyCd;
    
    @ApiModelProperty(value = "직책명")
    private String dutyNm;

    @ApiModelProperty(value = "직급코드")
    private String positionCd;

    @ApiModelProperty(value = "직급명")
    private String positionNm;

}
