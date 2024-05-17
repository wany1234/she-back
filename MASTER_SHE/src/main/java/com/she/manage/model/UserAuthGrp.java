package com.she.manage.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 사용자별 권한 그룹
 */
@ApiModel(description = "사용자 권한")
@Getter
@Setter
public class UserAuthGrp {
    @ApiModelProperty(value = "사용자id")
    private String userId;
    @ApiModelProperty(value = "사용자명")
    private String userNm;
    @ApiModelProperty(value = "사업장코드")
    private String plantCd;
    @ApiModelProperty(value = "사업장명")
    private String plantNm;
    @ApiModelProperty(value = "부서코드")
    private String deptCd;
    @ApiModelProperty(value = "부서명")
    private String deptNm;
    @ApiModelProperty(value = "권한번호")
    private String authGrpNo;
    @ApiModelProperty(value = "권한명")
    private String authGrpNm;
    @ApiModelProperty(value = "설정")
    private String settingYn;
    @ApiModelProperty(value = "등록자")
    private String createUserId;
    @ApiModelProperty(value = "등록일")
    private String createDt;
}
