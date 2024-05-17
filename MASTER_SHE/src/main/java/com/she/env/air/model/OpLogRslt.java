package com.she.env.air.model;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * OpLogRslt(대기 운영일지-기본정보)
 *
 */
@Data
public class OpLogRslt {
    @ApiModelProperty(value = "작성일")
    private String measureYmd;

    @ApiModelProperty(value = "요일")
    private String day;

    @ApiModelProperty(value = "날씨코드")
    private String weather;

    @ApiModelProperty(value = "날씨")
    private String weatherNm;

    @ApiModelProperty(value = "온도")
    private Float temp;

    @ApiModelProperty(value = "사업장코드")
    private String plantCd;

    @ApiModelProperty(value = "사업장")
    private String plantNm;

    @ApiModelProperty(value = "작성부서코드")
    private String deptCd;

    @ApiModelProperty(value = "작성부서")
    private String deptNm;

    @ApiModelProperty(value = "관리부서코드")
    private String mgDeptCd;

    @ApiModelProperty(value = "관리부서")
    private String mgDeptNm;

    @ApiModelProperty(value = "습도")
    private Float hum;

    @ApiModelProperty(value = "비고")
    private String remark;

    @ApiModelProperty(value = "진행상태코드")
    private String envOpLogStCd;

    @ApiModelProperty(value = "진행상태")
    private String envOpLogStNm;

    @ApiModelProperty(value = "")
    private int apprRqstNo;

    @ApiModelProperty(value = "")
    private String bizApprStepCd;

    @ApiModelProperty(value = "")
    private String bizApprStepNm;

    @ApiModelProperty(value = "등록부서코드")
    private String createDeptCd;

    @ApiModelProperty(value = "등록부서명")
    private String createDeptNm;

    @ApiModelProperty(value = "등록자")
    private String createUserId;

    @ApiModelProperty(value = "등록자")
    private String createUserNm;

    @ApiModelProperty(value = "등록일")
    private String createDt;

    @ApiModelProperty(value = "수정부서코드")
    private String updateDeptCd;

    @ApiModelProperty(value = "수정부서")
    private String updateDeptNm;

    @ApiModelProperty(value = "수정자")
    private String updateUserId;

    @ApiModelProperty(value = "수정자")
    private String updateUserNm;

    @ApiModelProperty(value = "수정일")
    private String updateDt;

    @ApiModelProperty(value = "가동시간")
    private String editDisch;

    @ApiModelProperty(value = "운전사항")
    private String editPrevent;

    @ApiModelProperty(value = "연료, 원료")
    private String editFuelIngr;

    @ApiModelProperty(value = "가동시간")
    private List<OutletDischChkResult> dischChkResult;

    @ApiModelProperty(value = "운전사항")
    private List<OutletPreventChkResult> preventChkResult;

    @ApiModelProperty(value = "운전사항-약품")
    private List<PreventChemResult> preventChemResults;

    @ApiModelProperty(value = "연료사용량")
    private List<FuelCheckResult> fuelCheckResult;

    @ApiModelProperty(value = "원료사용량")
    private List<IngrCheckResult> ingrCheckResult;

    @ApiModelProperty(value = "작성자")
    private String writerUserNm;

    @ApiModelProperty(value = "작성일")
    private String writerDt;

}
