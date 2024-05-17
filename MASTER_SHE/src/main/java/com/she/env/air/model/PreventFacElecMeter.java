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
 * PreventionFacility(대기 방지시설)
 *
 *
 */
@Data
public class PreventFacElecMeter {
    private int eairPreventFacElecMeterNo;

    private int eairPreventFacNo;

    private String eairPreventFacElecMeterNm;

    private int pwrMeterMagn;

    private String sapCd;

    private int sortOrder;

    private String useYn;

}
