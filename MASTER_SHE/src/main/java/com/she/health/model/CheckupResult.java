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

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "검진결과")
@Data
public class CheckupResult {
    @ApiModelProperty(value = "건강검진계획번호")
    private int heaCheckupPlanNo;

    @ApiModelProperty(value = "건강검진계획명")
    private String heaCheckupPlanNm;

    @ApiModelProperty(value = "건강검진분류코드")
    private String heaCheckupClassCd;

    @ApiModelProperty(value = "건강검진분류명")
    private String heaCheckupClassNm;

    @ApiModelProperty(value = "사번")
    private String userId;

    @ApiModelProperty(value = "성명")
    private String userNm;

    @ApiModelProperty(value = "성별코드")
    private String comSexTypeCd;

    @ApiModelProperty(value = "성별")
    private String comSexTypeNm;

    @ApiModelProperty(value = "부서코드")
    private String deptCd;

    @ApiModelProperty(value = "부서명")
    private String deptNm;

    @ApiModelProperty(value = "검진당시 부서코드")
    private String deptCdOrg;

    @ApiModelProperty(value = "검진당시 부서명")
    private String deptNmOrg;

    @ApiModelProperty(value = "검진기관번호")
    private int heaCheckupOrgNo;

    @ApiModelProperty(value = "검진기관명")
    private String heaCheckupOrgNm;

    @ApiModelProperty(value = "대상선정 통보여부")
    private String notifyPlanYn;

    @ApiModelProperty(value = "대상선정 통보일")
    private String notifyPlanYmd;

    @ApiModelProperty(value = "")
    private String specialCheckupYm;

    @ApiModelProperty(value = "")
    private String consentYn;

    @ApiModelProperty(value = "")
    private String consentYmd;

    @ApiModelProperty(value = "")
    private String reserveYmd;

    @ApiModelProperty(value = "")
    private String reserveBatchYn;

    private int heaCheckedOrgNo;

    private String heaCheckedOrgNm;

    private String heaCheckedYmd;

    private String diagnosedYmd;

    private String notifyResultYn;

    private String notifyResultYmd;

    private String heaFollowUpCd;

    private String heaFollowUpNm;

    private String heaFollowUpRemark;

    private String heaWorkableCd;

    private String heaWorkableNm;

    private String heaFollowUpGenCd;

    private String heaFollowUpGenNm;

    private String heaFollowUpGenRemark;

    private String heaWorkableGenCd;

    private String heaWorkableGenNm;

    private String overallOpinion;

    private String overallOpinionGen;

    private String createUserId;

    private String createUserNm;

    private String createDt;

    private String createDeptCd;

    private String createDeptNm;

    private String createPositionCd;

    private String createPositionNm;

    private String updateUserId;

    private String updateUserNm;

    private String updateDt;

    private String updateDeptCd;

    private String updateDeptNm;

    private String updatePositionCd;

    private String updatePositionNm;

    private String heaDiagnoseCd;

    private String heaDiagnoseNm;

    private String heaDiseaseClassCd;

    private String heaDiseaseClassNm;

    private String heaDiseaseCd;

    private String heaDiseaseNm;

    private String suspectYn;

    private int rowNum;

    private String heaCheckupTypeCd;

    private String heaCheckupTypeNm;

    private String plantNm;

    private String refHazardDesc;

    @ApiModelProperty(value = "작성자")
    private String writerUserNm;

    @ApiModelProperty(value = "작성일")
    private String writerDt;
    
    @ApiModelProperty(value = "검진년도")
    private String checkupYear;
}
