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
import lombok.Getter;
import lombok.Setter;

/**
 * PreventionFacilityChangeHistory(대기 방지시설 변경이력)
 *
 */
@ApiModel(description = "대기 방지시설 변경이력")
@Getter
@Setter
public class PreventFacChgHist {
    private int eairPreventFacChgHistNo;

    private int eairPreventFacNo;

    private String plantCd;

    private String plantNm;

    private String eairPreventFacNm;

    private String eairPreventFacClassCd;

    private String eairPreventFacClassNm;

    private String eairOutletNo;

    private String eairOutletNm;

    private String installPos;

    private String startYmd;

    private String endYmd;

    private Float dispoCap;

    private String eairPolluCd;

    private String eairPolluNm;

    private Float dispoConc;

    private Float dispoEff;

    private String eairChemCd;

    private String eairChemNm;

    private List<PreventFacItem> eairChemCds;

    private List<PreventFacItem> eairPolluCds;

    private Float chemConsum;

    private String endCause;

    private String createUserId;

    private String createUserNm;

    private String createDt;

    private String updateUserId;

    private String updateUserNm;

    private String updateDt;

    private String deptCd;

    private String deptNm;

    private String remark;

}
