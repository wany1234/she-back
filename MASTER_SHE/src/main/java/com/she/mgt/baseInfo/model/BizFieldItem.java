package com.she.mgt.baseInfo.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "SHE목표 분야별 항목")
@Getter
@Setter
public class BizFieldItem {
    @ApiModelProperty(value = "SHE목표 분야별 항목 번호")
    private int bizFieldItemNo;
    @ApiModelProperty(value = "SHE목표 분야 코드")
    private String bizFieldCd;
    @ApiModelProperty(value = "SHE목표 분야명")
    private String bizFieldNm;
    @ApiModelProperty(value = "SHE목표 분야별 항목명")
    private String bizFieldItemNm;
    @ApiModelProperty(value = "소수점 자리수")
    private int decPlace;
    @ApiModelProperty(value = "정렬순서")
    private int sortOrder;
    @ApiModelProperty(value = "대시보드사용여부")
    private String dashboardYn;
    @ApiModelProperty(value = "대시보드사용여부명")
    private String dashboardYnNm;
    @ApiModelProperty(value = "사용여부")
    private String useYn;
    @ApiModelProperty(value = "사용여부명")
    private String useYnNm;
    @ApiModelProperty(value = "등록자")
    private String createUserId;
    @ApiModelProperty(value = "등록자명")
    private String createUserNm;
    @ApiModelProperty(value = "등록일")
    private String createDt;
    @ApiModelProperty(value = "수정자")
    private String updateUserId;
    @ApiModelProperty(value = "수정자명")
    private String updateUserNm;
    @ApiModelProperty(value = "수정일")
    private String updateDt;
    @ApiModelProperty(value = "등록자 부서명")
    private String createDeptNm;
    @ApiModelProperty(value = "등록자 부서코드")
    private String createDeptCd;
    @ApiModelProperty(value = "수정자 부서명")
    private String updateDeptNm;
    @ApiModelProperty(value = "수정자 부서코드")
    private String updateDeptCd;
    @ApiModelProperty(value = "등록자 직위명")
    private String createPositionNm;
    @ApiModelProperty(value = "등록자 직위코드")
    private String createPositionCd;
    @ApiModelProperty(value = "수정자 직위명")
    private String updatePositionNm;
    @ApiModelProperty(value = "수정자 직위코드")
    private String updatePositionCd;

}
