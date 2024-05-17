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

import lombok.Data;

/**
 * PreventionFacilityPowerCheckResult(대기 운영일지-전력사용량)
 *
 */
@Data
public class OutletPreventChkResult {
    private String measureYmd;

    private int eairPreventFacNo;

    private int eairPreventFacElecMeterNo;

    private String eairPreventFacElecMeterNm;

    private String eairPreventFacNm;

    private String deptCd;

    private String deptNm;

    private int eairOutletNo;

    private String eairOutletNm;

    private String mainDischFacNm;

    private String eairOutletPermitNo;

    private String measureTime;

    private Float pwrMeterMagn;

    private Float prevPwrMeterCnt;

    private Float pwrMeterCnt;


    private String pwrMeterCntCal;

    private Float pwrConsumAmt;

    private String pwrConsumAmtt;

    private String createUserId;

    private String createUserNm;

    private String createDt;

    private String updateUserId;

    private String updateUserNm;

    private String updateDt;

    private String chemNm1;

    private String chemCd1;

    private Float consumAmt1;

    private String unitNm1;

    private String unitCd1;

    private String chemNm2;

    private String chemCd2;

    private Float consumAmt2;

    private String unitNm2;

    private String unitCd2;

    private String chemNm3;

    private String chemCd3;

    private Float consumAmt3;

    private String unitNm3;

    private String unitCd3;

    private String installPos;

    private Float dispoCap;

    private String dispoCapt;

    private String eairPolluNm;

    private Float dispoConc;

    private Float dispoEff;


}
