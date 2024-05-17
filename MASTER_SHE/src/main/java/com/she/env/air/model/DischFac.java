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

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * DischargeFacility(대기 배출시설)
 *
 */

@Data
@ApiModel(description = "대기 배출시설")
public class DischFac {

    @ApiModelProperty(value = "대기 배출시설 번호")
    private int eairDischFacNo;

    @ApiModelProperty(value = "대기 배출구 번호")
    private int eairOutletNo;

    @ApiModelProperty(value = "대기 배출구 일련 번호")
    private String eairOutletNm;

    @ApiModelProperty(value = "대기 배출시설 일련 번호")
    private String eairDischFacNum;

    @ApiModelProperty(value = "SAP(ERP)코드")
    private String sapCd;

    @ApiModelProperty(value = "대기 배출시설명")
    private String eairDischFacNm;

    @ApiModelProperty(value = "방지시설면제여부")
    private String preventFacExemYn;

    @ApiModelProperty(value = "비고")
    private String remark;

    @ApiModelProperty(value = "사업장코드")
    private String plantCd;

    @ApiModelProperty(value = "사업장명")
    private String plantNm;

    @ApiModelProperty(value = "출력순서")
    private int sortOrder;

    @ApiModelProperty(value = "사용여부")
    private String useYn;

    @ApiModelProperty(value = "사용여부명")
    private String useYnNm;

    @ApiModelProperty(value = "일지작성부서코드")
    private String deptCd;

    @ApiModelProperty(value = "일지작성부서명")
    private String deptNm;

    @ApiModelProperty(value = "관리부서코드")
    private String mgDeptCd;

    @ApiModelProperty(value = "관리부서명")
    private String mgDeptNm;

    @ApiModelProperty(value = "관리번호")
    private String mgrNum;

    @ApiModelProperty(value = "수량")
    private String capVol;

    @ApiModelProperty(value = "용량")
    private String vol;

    @ApiModelProperty(value = "용량단위")
    private String unitCd;

    @ApiModelProperty(value = "용량단위명")
    private String unitNm;

    @ApiModelProperty(value = "연결배출구")
    private List<OutletDischFac> outletItem;

    @ApiModelProperty(value = "연결방지시설")
    private List<PreventDischFac> preventionItem;

    @ApiModelProperty(value = "연료")
    private String[] fuelItem;

    @ApiModelProperty(value = "연료")
    private List<Fuel> fuelItems;

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

    @ApiModelProperty(value = "SEMS대상여부")
    private String semsYn;

    @ApiModelProperty(value = "작성자")
    private String writerUserNm;

    @ApiModelProperty(value = "작성일")
    private String writerDt;
}