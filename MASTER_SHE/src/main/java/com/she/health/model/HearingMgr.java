package com.she.health.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "청력관리대상자")
@Data
public class HearingMgr {

    @ApiModelProperty(value = "청력관리대상자번호")
    private int heaHearingMgrListNo;

    @ApiModelProperty(value = "사업장 코드")
    private String plantCd;

    @ApiModelProperty(value = "사업장 명칭")
    private String plantNm;

    @ApiModelProperty(value = "사용자 아이디")
    private String userId;

    @ApiModelProperty(value = "사용자 이름")
    private String userNm;

    @ApiModelProperty(value = "부서 코드")
    private String deptCd;

    @ApiModelProperty(value = "부서 명칭")
    private String deptNm;

    @ApiModelProperty(value = "검사일자")
    private String chkDt;

    @ApiModelProperty(value = "좌500")
    private float left500;

    @ApiModelProperty(value = "좌1000")
    private float left1000;

    @ApiModelProperty(value = "좌2000")
    private float left2000;

    @ApiModelProperty(value = "좌4000")
    private float left4000;

    @ApiModelProperty(value = "좌3분법")
    private float left3Div;

    @ApiModelProperty(value = "좌6분법")
    private float left6Div;

    @ApiModelProperty(value = "우500")
    private float right500;

    @ApiModelProperty(value = "우1000")
    private float right1000;

    @ApiModelProperty(value = "우2000")
    private float right2000;

    @ApiModelProperty(value = "우4000")
    private float right4000;

    @ApiModelProperty(value = "우3분법")
    private float right3Div;

    @ApiModelProperty(value = "우6분법")
    private float right6Div;

    @ApiModelProperty(value = "검사결과 진단 코드")
    private String heaDiagnoseCd;

    @ApiModelProperty(value = "관리등급")
    private String mgrLvl;

    @ApiModelProperty(value = "고위험군")
    private String highDgrGrpNm;

    @ApiModelProperty(value = "등록일")
    private String createUserId;

    @ApiModelProperty(value = "등록자")
    private String createDt;

    @ApiModelProperty(value = "수정자")
    private String updateUserId;

    @ApiModelProperty(value = "수정일")
    private String updateDt;

}
