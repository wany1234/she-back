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
package com.she.health.model;

import lombok.Data;

@Data
public class InterviewResult {

    private int heaCheckupPlanNo;

    private String heaCheckupPlanNm;

    private String heaCheckupClassCd;

    private String heaCheckupClassNm;

    private String userId;

    private String userNm;

    private String comSexTypeCd;

    private String comSexTypeNm;

    private String deptCd;

    private String deptNm;

    private String deptCdOrg;

    private String deptNmOrg;

    private int heaCheckupOrgNo;

    private String heaCheckedOrgNm;

    private String heaCheckedYmd;

    private String heaFollowUpCd;

    private String heaFollowUpNm;

    private String heaWorkableCd;

    private String heaWorkableNm;

    private String overallOpinion;

    private String createUserId;

    private String createUserNm;

    private String createDt;

    private String updateUserId;

    private String updateUserNm;

    private String updateDt;

    private String charResult;

}
