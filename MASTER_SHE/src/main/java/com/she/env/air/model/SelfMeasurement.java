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
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(description = "환경>대기>자가측정")
public class SelfMeasurement {

    @ApiModelProperty(value = "대기자가측정번호")
    private int eairOpMeasNo;

    @ApiModelProperty(value = "사업장코드")
    private String plantCd;

    @ApiModelProperty(value = "사업장명칭")
    private String plantNm;

    @ApiModelProperty(value = "측정단계코드")
    private String stepCd;

    @ApiModelProperty(value = "측정단계명칭")
    private String stepNm;

    @ApiModelProperty(value = "자가측정방법코드")
    private String methodCd;

    @ApiModelProperty(value = "자가측정방법명칭")
    private String methodNm;

    @ApiModelProperty(value = "관리부서코드")
    private String deptCd;

    @ApiModelProperty(value = "관리부서명칭")
    private String deptNm;

    @ApiModelProperty(value = "환경기술인")
    private String envEngrNm;

    @ApiModelProperty(value = "대기 배출구 번호")
    private int eairOutletNo;

    @ApiModelProperty(value = "대기 배출구 명칭")
    private String eairOutletNm;

    @ApiModelProperty(value = "측정일자")
    private String measureYmd;

    @ApiModelProperty(value = "요일")
    private String day;

    @ApiModelProperty(value = "날씨코드")
    private String weatherCd;

    @ApiModelProperty(value = "날씨명칭")
    private String weatherNm;

    @ApiModelProperty(value = "온도(℃)")
    private Float temp;

    @ApiModelProperty(value = "습도(%)")
    private Float hum;

    @ApiModelProperty(value = "기압(mb)")
    private Float airPress;

    @ApiModelProperty(value = "풍향(풍)")
    private String windDir;

    @ApiModelProperty(value = "풍속(m/sec)")
    private Float windSpeed;

    @ApiModelProperty(value = "가스속도(m/s)")
    private Float gasSpeed;

    @ApiModelProperty(value = "가스온도(℃)")
    private Float gasTemp;

    @ApiModelProperty(value = "수분함량(%)")
    private Float wtrPer;

    @ApiModelProperty(value = "실측산소농도(%)")
    private Float realO2Val;

    @ApiModelProperty(value = "표준산소농도(%)")
    private Float stndO2Val;

    @ApiModelProperty(value = "유량(sm3/일)")
    private Float flowDay;

    @ApiModelProperty(value = "과학원배출가스유량")
    private Float scFlow;

    @ApiModelProperty(value = "환경 기술인 의견")
    private String envEngrOpn;

    @ApiModelProperty(value = "연료명 및 사용량")
    private String fuelNmResult;

    @ApiModelProperty(value = "원료명 및 사용량")
    private String ingrNmResult;

    @ApiModelProperty(value = "기타")
    private String remark;

    @ApiModelProperty(value = "결재진행상태코드")
    private String bizApprStepCd;

    @ApiModelProperty(value = "결재진행상태명칭")
    private String bizApprStepNm;

    @ApiModelProperty(value = "결재요청번호")
    private int apprRqstNo;

    @ApiModelProperty(value = "등록자ID")
    private String createUserId;

    @ApiModelProperty(value = "등록자명칭")
    private String createUserNm;

    @ApiModelProperty(value = "등록일")
    private String createDt;

    @ApiModelProperty(value = "수정자ID")
    private String updateUserId;

    @ApiModelProperty(value = "수정자명칭")
    private String updateUserNm;

    @ApiModelProperty(value = "수정일")
    private String updateDt;

    @ApiModelProperty(value = "")
    private String eairTestItemNo;

    @ApiModelProperty(value = "관리부서")
    private String mgDeptCd;

    @ApiModelProperty(value = "관리부서")
    private String mgDeptNm;

    @ApiModelProperty(value = "배출구별 검사결과")
    private List<SelfMeasurementResult> selfMeasurementResult;

    @ApiModelProperty(value = "작성자")
    private String writerUserNm;

    @ApiModelProperty(value = "작성일")
    private String writerDt;
}
