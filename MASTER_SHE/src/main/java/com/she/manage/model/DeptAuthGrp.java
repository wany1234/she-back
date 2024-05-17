package com.she.manage.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 부서별 권한 그룹
 */
@ApiModel(description = "부서별 권한 그룹")
@Getter
@Setter
public class DeptAuthGrp {
    @ApiModelProperty(value = "사업장코드")
    private String plantCd;
    @ApiModelProperty(value = "사업장명")
    private String plantNm;
    @ApiModelProperty(value = "부서코드")
    private String deptCd;
    @ApiModelProperty(value = "부서명")
    private String deptNm;
    @ApiModelProperty(value = "부서레벨")
    private String deptLvl;
    @ApiModelProperty(value = "부서레벨명")
    private String deptLvlNm;
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
    @ApiModelProperty(value = "사업장접근")
    private String plantAccessYn;
    @ApiModelProperty(value = "부서접근")
    private String deptAccessYn;
    @ApiModelProperty(value = "부서/사업장")
    private String flag;
    @ApiModelProperty(value = "코멘트(부서단위로 적용되는지, 사용자별로 적용되는지 알려주는 문구)")
    private String comment;
    @ApiModelProperty(value = "사용자 ID")
    private String userId;
}
