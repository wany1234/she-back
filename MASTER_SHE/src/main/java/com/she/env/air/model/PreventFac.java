/**
 *
 * Copyright (C) 2019, 2019 All Right Reserved, http://www.yullin.com/
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
 * PreventionFacility(대기 방지시설)
 *
 */
@Data
@ApiModel(description = "대기 (오염)방지시설")
public class PreventFac {

    @ApiModelProperty(value = "대기 오염 방지시설 번호")
    private int eairPreventFacNo;

    @ApiModelProperty(value = "")
    private String preventFacNm;

    @ApiModelProperty(value = "대기 오염 방지시설 명칭")
    private String eairPreventFacNm;

    @ApiModelProperty(value = "(C)대기오염 방지시설 분류 코드")
    private String eairPreventFacClassCd;

    @ApiModelProperty(value = "(C)대기오염 방지시설 분류 명칭")
    private String eairPreventFacClassNm;

    @ApiModelProperty(value = "방지시설일련번호")
    private String eairPreventFacNum;

    @ApiModelProperty(value = "고유방지시설번호")
    private String eairPreventFacInhNum;

    @ApiModelProperty(value = "대기 배출구 번호")
    private String eairOutletNo;

    @ApiModelProperty(value = "배출구일련번호")
    private String eairOutletNm;

    @ApiModelProperty(value = "SAP(ERP) 코드")
    private String sapCd;

    @ApiModelProperty(value = "설치위치")
    private String installPos;

    @ApiModelProperty(value = "사업장 코드")
    private String plantCd;

    @ApiModelProperty(value = "사업장 명칭")
    private String plantNm;

    @ApiModelProperty(value = "시설연결구조코드")
    private String facConnStruCd;

    @ApiModelProperty(value = "시설연결구조명칭")
    private String facConnStruNm;

    @ApiModelProperty(value = "출력순서")
    private int sortOrder;

    @ApiModelProperty(value = "사용여부")
    private String useYn;

    @ApiModelProperty(value = "사용여부명칭")
    private String useYnNm;

    @ApiModelProperty(value = "처리용량(㎡/min)")
    private Float dispoCap;

    @ApiModelProperty(value = "처리농도(mg/S㎥)")
    private Float dispoConc;

    @ApiModelProperty(value = "처리효율(%)")
    private Float dispoEff;

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

    @ApiModelProperty(value = "일지작성부서코드")
    private String deptCd;

    @ApiModelProperty(value = "일지작성부서명칭")
    private String deptNm;

    @ApiModelProperty(value = "관리부서코드")
    private String mgDeptCd;

    @ApiModelProperty(value = "관리부서명칭")
    private String mgDeptNm;

    @ApiModelProperty(value = "비고")
    private String remark;

    @ApiModelProperty(value = "SEMS대상여부")
    private String semsYn;

    @ApiModelProperty(value = "대기 (오염)방지 시설_전력량계")
    private List<PreventFacElecMeter> prevFacElecMeter;

    @ApiModelProperty(value = "대기배출구별 방지시설")
    private List<OutletPreventFac> outletItem;

    @ApiModelProperty(value = "대기방지시설별 연결방지시설")
    private List<PreventPreventFac> preventionItem;

    @ApiModelProperty(value = "연료")
    private String[] fuelItem;

    @ApiModelProperty(value = "연료")
    private List<Fuel> fuelItems;

    @ApiModelProperty(value = "방지시설 변경관리정보")
    PreventFacChgHist preventFacChgHist;

    @ApiModelProperty(value = "대기방지시설별 연결배출시설")
    private List<PreventDischFacItem> outletDischItem;

    @ApiModelProperty(value = "작성자")
    private String writerUserNm;

    @ApiModelProperty(value = "작성일")
    private String writerDt;
}
