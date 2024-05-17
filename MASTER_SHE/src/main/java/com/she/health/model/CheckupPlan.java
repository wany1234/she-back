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

import java.util.List;

@ApiModel(description = "검진계획")
@Data
public class CheckupPlan {
    @ApiModelProperty(value = "건강검진 계획번호")
    private int heaCheckupPlanNo;

    @ApiModelProperty(value = "건강검진 계획명칭")
    private String heaCheckupPlanNm;

    @ApiModelProperty(value = "건강검진 분류코드")
    private String heaCheckupClassCd;

    @ApiModelProperty(value = "건강검진 분류명칭")
    private String heaCheckupClassNm;

    @ApiModelProperty(value = "사업장코드")
    private String plantCd;

    @ApiModelProperty(value = "사업장명칭")
    private String plantNm;

    @ApiModelProperty(value = "시작일자")
    private String startYmd;

    @ApiModelProperty(value = "종료일자")
    private String endYmd;

    @ApiModelProperty(value = "완료일자")
    private String finishYmd;

    @ApiModelProperty(value = "건강검진기간")
    private String heaCheckupPlanPeriod;

    @ApiModelProperty(value = "선택항목 필수여부")
    private String requiredOptYn;

    @ApiModelProperty(value = "선택항목 필수여부 명")
    private String requiredOptYnNm;

    @ApiModelProperty(value = "선택항목 선택가능 항목 수")
    private int selectOptCount;

    @ApiModelProperty(value = "대상자 수")
    private int checkupUserCount;

    @ApiModelProperty(value = "검진계획에 따른 검진결과 수")
    private int resultCnt;

    @ApiModelProperty(value = "검진 기관s")
    private List<CheckupPlanOrg> checkupPlanOrgs;

    @ApiModelProperty(value = "등록자아이디")
    private String createUserId;

    @ApiModelProperty(value = "등록자명")
    private String createUserNm;

    @ApiModelProperty(value = "등록일")
    private String createDt;

    @ApiModelProperty(value = "수정자아이디")
    private String updateUserId;

    @ApiModelProperty(value = "수정자명")
    private String updateUserNm;

    @ApiModelProperty(value = "수정일")
    private String updateDt;

    @ApiModelProperty(value = "작성자")
    private String writerUserNm;

    @ApiModelProperty(value = "작성일")
    private String writerDt;

}
