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

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SelfMeasurementResult {

    private int eairOpMeasNo;

    private int eairOutletNo;

    private String eairTestItemCd;

    private String eairTestItemNm;

    private String envUnitNm;

    private String envUnitCd;

    private Float numResult;

    private Float dischAmtPerDay;

    private int dischAmtCalcFactor;

    private int legalLimit;

    private int selfLimit;

    private int limitPerDay;

    private String eairInstCd;

    private String eairTestMtdCd;

}
