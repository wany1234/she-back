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
package com.she.env.waste.model;

import java.util.List;

import lombok.Data;

@Data
public class DisposalResult {
    private boolean resultEditable = true;

    private boolean resultComplete = false;

    private boolean requestComplete = false;

    private int ewstDispoResultNo;

    private String ewstDispoStCd;

    private String ewstDispoStNm;

    private String plantCd;

    private String plantNm;

    private int ewstDispoReqNo;

    private String ewstDivCd;

    private String ewstDivNm;

    private String ewstClassCd;

    private String ewstClassNm;

    private String ewstDispoMtdCd;

    private String ewstDispoMtdNm;

    private int ewstWasteNo;

    private String ewstWasteNm;

    private String reqUserId;

    private String reqUserNm;

    private String reqDeptCd;

    private String reqDeptNm;

    private String reqYmd;

    private String ewstDispoDivCd;

    private String ewstDispoDivNm;

    private String ewstDispoStepCd;

    private String ewstDispoStepNm;

    private String dispoUserId;

    private String dispoUserNm;

    private String dispoDeptCd;

    private String dispoDeptNm;

    private String dispoYmd;

    private Double amtEmpty;

    private Double amtLoaded;

    private Float amtGen;

    private String amtGent;

    private String envUnitCd;

    private String envUnitNm;

    private int ewstDispoCoNo;

    private String ewstDispoCoNm;

    private int ewstFreightCoNo;

    private String ewstFreightCoNm;

    private String carrier;

    private Float dispoCost;

    private Float freightCost;
    private String freightCostt;

    private Float dispoCostSum;

    private Float freightCostSum;

    private Float totalCost;
    private String totalCostt;

    private String remark;

    private String createUserId;

    private String createUserNm;

    private String createDt;

    private String updateUserId;

    private String updateUserNm;

    private String updateDt;

    private String ewstPhaseCd;

    private String ewstPhaseNm;

    private String sellYn;

    private List<DisporateDeptItem> disporateDeptItem;

    private String vendorCd;

    private String vendorNm;

    private String freightCd;

    private String freightNm;

    private String allbaroTransNum;

    private String allbaroYn;

    private String wgtOutNo;

    private String wgtIocnt;

    private String wgtRfno;

    private String wgtIogb;

    private String wgtIogbNm;

    private String wgtBigo;

    private String status; // 중복 데이터 체크

    private String errorMessage;

    private String ewstClassItem;// 성상

    private List<DisposalRequest> disposalRequest;

    private String writerUserNm;

    private String writerDt;

}
