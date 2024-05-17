package com.she.safety.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "변경관리_관련업무내역")
@Getter
@Setter
public class ChangeCommiPsn {
    @ApiModelProperty(value = "결과번호")
    private int safChngCommiNo;
    @ApiModelProperty(value = "사용자ID")
    private String userId;
    @ApiModelProperty(value = "사용자명")
    private String userNm;
    @ApiModelProperty(value = "부서코드")
    private String deptCd;
    @ApiModelProperty(value = "부서명")
    private String deptNm;
    @ApiModelProperty(value = "직급")
    private String positionNm;
    @ApiModelProperty(value = "담당")
    private String mgrDesc;
}
