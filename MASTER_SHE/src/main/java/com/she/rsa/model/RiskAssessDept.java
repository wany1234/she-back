package com.she.rsa.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "평가계획")
@Getter
@Setter
public class RiskAssessDept {

    @ApiModelProperty(value = "평가계획번호")
    private int assessPlanNo;

    @ApiModelProperty(value = "부서코드")
    private String deptCd;

    @ApiModelProperty(value = "부서명")
    private String deptNm;

    @ApiModelProperty(value = "평가자아이디")
    private String userId;

    @ApiModelProperty(value = "평가자")
    private String userNm;

    @ApiModelProperty(value = "공지사항")
    private String assessPlanContents;

    @ApiModelProperty(value = "등록자아이디")
    private String createUserId;

    @ApiModelProperty(value = "등록자")
    private String createUserNm;

    @ApiModelProperty(value = "등록일")
    private String createDt;

    @ApiModelProperty(value = "수정자아이디")
    private String updateUserId;

    @ApiModelProperty(value = "수정자")
    private String updateUserNm;

    @ApiModelProperty(value = "수정일")
    private String updateDt;

    @ApiModelProperty(value = "jobCount")
    private int jobCount;

    @ApiModelProperty(value = "chemprodCount")
    private int chemprodCount;

    @ApiModelProperty(value = "selectedDeptCd")
    private String selectedDeptCd;

    @ApiModelProperty(value = "selectedProcessCd")
    private String selectedProcessCd;

    @ApiModelProperty(value = "공정코드")
    private String processCd;

    @ApiModelProperty(value = "공정명")
    private String processNm;

}
