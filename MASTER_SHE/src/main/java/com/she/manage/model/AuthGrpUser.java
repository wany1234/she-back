package com.she.manage.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "사용자, 권한그룹")
@Getter
@Setter
public class AuthGrpUser {
    @ApiModelProperty(value = "사업장코드")
    private String plantCd;
    @ApiModelProperty(value = "사업장명")
    private String plantNm;
    @ApiModelProperty(value = "부서코드")
    private String deptCd;
    @ApiModelProperty(value = "부서명")
    private String deptNm;
    @ApiModelProperty(value = "사용자id")
    private String userId;
    @ApiModelProperty(value = "사용자명")
    private String userNm;
    @ApiModelProperty(value = "사용자별 권한그룹")
    private List<AuthGrp> authGrps;
}
