package com.she.env.air.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel(description = "대기운영일지(관리자)")
@Data
public class OpLogAdminRslt {
    @ApiModelProperty(value = "작성일자")
    private String measureYmd;

    @ApiModelProperty(value = "사업장 코드")
    private String plantCd;

    @ApiModelProperty(value = "사업장")
    private String plantNm;

    @ApiModelProperty(value = "요일")
    private String day;

    @ApiModelProperty(value = "날씨")
    private String weather;

    @ApiModelProperty(value = "날씨")
    private String weatherNm;

    @ApiModelProperty(value = "온도")
    private Float temp;

    @ApiModelProperty(value = "결재번호")
    private String apprRqstNo;

    @ApiModelProperty(value = "결재진행상태코드")
    private String bizApprStepCd;

    @ApiModelProperty(value = "결재진행상태")
    private String bizApprStepNm;

    @ApiModelProperty(value = "진행상태코드")
    private String stepCd;

    @ApiModelProperty(value = "진행상태")
    private String stepNm;

    @ApiModelProperty(value = "등록자")
    private String createUserId;

    @ApiModelProperty(value = "등록자명")
    private String createUserNm;

    @ApiModelProperty(value = "등록일")
    private String createDt;

    @ApiModelProperty(value = "작성자")
    private String writerUserNm;

    @ApiModelProperty(value = "작성일")
    private String writerDt;

    @ApiModelProperty(value = "수정자")
    private String updateUserId;

    @ApiModelProperty(value = "수정자명")
    private String updateUserNm;

    @ApiModelProperty(value = "수정일")
    private String updateDt;

    @ApiModelProperty(value = "대상부서")
    private int tgtCnt;

    @ApiModelProperty(value = "작성부서")
    private int writeCnt;

    @ApiModelProperty(value = "미작성부서")
    private int noCnt;
    
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
}
