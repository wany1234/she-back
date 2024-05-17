/**
 * Copyright (C) 2019, 2019 All Right Reserved, http://www.yullin.com/
 *
 * SHE Business can not be copied and/or distributed without the express
 * permission of Yullin Technologies
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 */
package com.she.env.air.model;

import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(description = "대기 배출구")
public class Outlet {

    @ApiModelProperty(value = "대기 배출구 번호")
    private int eairOutletNo;

    @ApiModelProperty(value = "")
    private String outletNm;

    @ApiModelProperty(value = "배출구 일련 번호")
    private String eairOutletNm;

    @ApiModelProperty(value = "배출구 직경")
    private Float eairOutletDiam;

    @ApiModelProperty(value = "배출구 높이")
    private Float eairOutletHt;

    @ApiModelProperty(value = "배출구명")
    private String mainDischFacNm;

    @ApiModelProperty(value = "SAP(ERP)코드")
    private String sapCd;

    @ApiModelProperty(value = "출력순서")
    private int sortOrder;

    @ApiModelProperty(value = "측정횟수코드")
    private String airPerdCd;

    @ApiModelProperty(value = "측정횟수명")
    private String airPerdNm;

    @ApiModelProperty(value = "사용여부")
    private String useYn;

    @ApiModelProperty(value = "사용여부명")
    private String useYnNm;

    @ApiModelProperty(value = "검사항목 목록")
    private ArrayList<TestItem> eairTestItemCds;

    @ApiModelProperty(value = "배출구허가증상배출구번호")
    private String eairOutletPermitNo;

    @ApiModelProperty(value = "사업장명")
    private String plantNm;

    @ApiModelProperty(value = "사업장코드")
    private String plantCd;

    @ApiModelProperty(value = "일지작성부서코드")
    private String deptCd;

    @ApiModelProperty(value = "일지작성부서명")
    private String deptNm;

    @ApiModelProperty(value = "관리부서코드")
    private String mgDeptCd;

    @ApiModelProperty(value = "관리부서명")
    private String mgDeptNm;

    @ApiModelProperty(value = "등록자ID")
    private String createUserId;

    @ApiModelProperty(value = "등록자명")
    private String createUserNm;

    @ApiModelProperty(value = "등록일")
    private String createDt;

    @ApiModelProperty(value = "수정자ID")
    private String updateUserId;

    @ApiModelProperty(value = "수정자명")
    private String updateUserNm;

    @ApiModelProperty(value = "수정일")
    private String updateDt;

    @ApiModelProperty(value = "방지시설면제여부")
    private String preventFacExemYn;

    @ApiModelProperty(value = "SEMS대상여부")
    private String semsYn;

    @ApiModelProperty(value = "대기배출구별 배출시설")
    private List<OutletDischFacItem> outletDischItem;

    @ApiModelProperty(value = "대기배출구-연결방지시설")
    private List<OutletPreventSerial> outletPreventSerial;

    @ApiModelProperty(value = "작성자")
    private String writerUserNm;

    @ApiModelProperty(value = "작성일")
    private String writerDt;
    
    @ApiModelProperty(value = "대기총량제 대상여부")
    private String airtotTargetYn;
    
    @ApiModelProperty(value = "방지시설효율(%)")
    private Float efficiency;
}
