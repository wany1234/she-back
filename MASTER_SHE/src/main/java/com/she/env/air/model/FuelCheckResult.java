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

@Data
public class FuelCheckResult {
    private String measureYmd;

    private String eairDischFacNm;

    private String eairPreventFacNm;

    private String eairPreventFacNo;

    private String eairDischFacNo;

    private String eairFuelCd;

    private String eairFuelNm;

    private Float numResult;

    private String charResult;

    private String envUnitCd;

    private String envUnitNm;

    private String createUserId;

    private String createUserNm;

    private String createDt;

    private String updateUserId;

    private String updateUserNm;

    private String updateDt;

    private String deptCd;

    private String deptNm;
}
