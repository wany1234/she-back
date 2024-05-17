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

@ApiModel(description = "검진대상")
@Data
public class CheckupUser {
    @ApiModelProperty(value = "건강검진 계획번호")
    private int heaCheckupPlanNo;

    @ApiModelProperty(value = "건강검진 분류코드")
    private String heaCheckupClassCd;

    @ApiModelProperty(value = "건강검진 분류명칭")
    private String heaCheckupClassNm;

    @ApiModelProperty(value = "건강검진 게획명칭")
    private String heaCheckupPlanNm;

    @ApiModelProperty(value = "건강검진 유형")
    private String heaCheckupTypeCd;

    @ApiModelProperty(value = "건강검진 유형명칭")
    private String heaCheckupTypeNm;

    @ApiModelProperty(value = "공정명")
    private String processNm;

    @ApiModelProperty(value = "공정명s")
    private String processNms;

    @ApiModelProperty(value = "부서코드")
    private String deptCd;

    @ApiModelProperty(value = "부서명")
    private String deptNm;

    @ApiModelProperty(value = "유해인자명")
    private String deptHazardNm;

    @ApiModelProperty(value = "사번")
    private String userId;

    @ApiModelProperty(value = "성명")
    private String userNm;

    @ApiModelProperty(value = "입사일")
    private String entryYmd;

    @ApiModelProperty(value = "연락처")
    private String phoneNo;

    @ApiModelProperty(value = "사무실번호")
    private String officeTel;

    @ApiModelProperty(value = "이메일")
    private String email;

    @ApiModelProperty(value = "기관번호")
    private int heaCheckupOrgNo;

    @ApiModelProperty(value = "기관명")
    private String heaCheckupOrgNm;

    @ApiModelProperty(value = "검진받은 기관번호")
    private int heaCheckedOrgNo;

    @ApiModelProperty(value = "검진받은 기관명")
    private String heaCheckedOrgNm;

    @ApiModelProperty(value = "건강검진일")
    private String heaCheckedYmd;

    @ApiModelProperty(value = "검진기간")
    private String heaCheckupPlanPeriod;

    @ApiModelProperty(value = "등록자")
    private String createUserId;

    @ApiModelProperty(value = "수정자")
    private String updateUserId;

    @ApiModelProperty(value = "검진년도")
    private String checkupYear;

    @ApiModelProperty(value = "")
    private String consentYn;

    @ApiModelProperty(value = "")
    private String statusYn;

    @ApiModelProperty(value = "")
    private String requiredOptYn;

    @ApiModelProperty(value = "")
    private String selectOptCount;

    @ApiModelProperty(value = "시작일자")
    private String startYmd;

    @ApiModelProperty(value = "종료일자")
    private String endYmd;

    @ApiModelProperty(value = "마감일자")
    private String finishYmd;

    @ApiModelProperty(value = "")
    private String[] selectedTestItemCd;

    @ApiModelProperty(value = "성별명칭")
    private String comSexTypeNm;

    @ApiModelProperty(value = "성별")
    private String comSexTypeCd;

    @ApiModelProperty(value = "")
    private String ReserveBatchYn;

    @ApiModelProperty(value = "공정")
    private String prcsNm;

    @ApiModelProperty(value = "취급자재")
    private String deptChemProdNm;

    @ApiModelProperty(value = "관련화학자재s")
    private String chemProdNmKrs;

    @ApiModelProperty(value = "관련화학물질")
    private String deptChemNm;

    @ApiModelProperty(value = "관련화학물질")
    private String chemNmKrs;

    @ApiModelProperty(value = "예약자비고")
    private String bookRemark;

    @ApiModelProperty(value = "예약일")
    private String reserveYmd;

    @ApiModelProperty(value = "예약자수")
    private int reserveNum;

    @ApiModelProperty(value = "총대상자수")
    private int totalNum;

    @ApiModelProperty(value = "사업장코드")
    private String plantCd;

    @ApiModelProperty(value = "사업장명")
    private String plantNm;

    @ApiModelProperty(value = "검진기간")
    private String period;

    @ApiModelProperty(value = "마감일")
    private String deadlineDt;

    @ApiModelProperty(value = "[server paging] 총 갯수")
    private int totalCnt;

    @ApiModelProperty(value = "생년월일")
    private String birthYmd;

    @ApiModelProperty(value = "검진등록")
    private String checkUpResult;
}
