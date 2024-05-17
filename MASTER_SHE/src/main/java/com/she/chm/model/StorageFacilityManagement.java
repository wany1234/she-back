package com.she.chm.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "저장위치")
@Getter
@Setter
public class StorageFacilityManagement {
    @ApiModelProperty(value = "저장위치코드")
    private String matStrgCd;
    @ApiModelProperty(value = "저장위치명")
    private String matStrgNm;
    @ApiModelProperty(value = "플랜트코드(사업장코드)")
    private String plantCd;
    @ApiModelProperty(value = "플랜트코드(사업장명)")
    private String plantNm;
    @ApiModelProperty(value = "시설유형")
    private String matStrgType;
    @ApiModelProperty(value = "시설유형명")
    private String matStrgTypeNm;
    @ApiModelProperty(value = "관리부서코드")
    private String deptCd;
    @ApiModelProperty(value = "관리부서명")
    private String deptNm;
    @ApiModelProperty(value = "설치위치")
    private String location;
    @ApiModelProperty(value = "비고")
    private String remarks;
    @ApiModelProperty(value = "총허가량(TON)")
    private String allPermitAmt;
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
    @ApiModelProperty(value = "수정일")
    private List<StorageFacilityManagementDgrPerm> storageFacilityManagementDgrPerms;
}
