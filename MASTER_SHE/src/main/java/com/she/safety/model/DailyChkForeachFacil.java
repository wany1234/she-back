package com.she.safety.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "설비유형별 설비")
@Data
public class DailyChkForeachFacil {
    @ApiModelProperty(value = "설비점검번호")
    private int safFacilChkNo;

    @ApiModelProperty(value = "설비유형코드")
    private String safFacilityTypeCd;

    @ApiModelProperty(value = "설비유형")
    private String safFacilityTypeNm;

    @ApiModelProperty(value = "설비코드")
    private String safFacilityCd;

    @ApiModelProperty(value = "설비명")
    private String facilityNm;

    @ApiModelProperty(value = "점검예정일")
    private String chkSchYmd;

    @ApiModelProperty(value = "점검자")
    private String createUserId;

    @ApiModelProperty(value = "점검자")
    private String userNm;

    @ApiModelProperty(value = "점검완료여부")
    private String chkEndYn;

    @ApiModelProperty(value = "점검완료여부")
    private String chkEndYnNm;

    @ApiModelProperty(value = "점검완료일")
    private String chkYmd;

    @ApiModelProperty(value = "")
    private String safFacilityCheckNo;

    @ApiModelProperty(value = "점검결과코드")
    private String chkResultCd;

    @ApiModelProperty(value = "점검결과요약")
    private String chkResult;

    @ApiModelProperty(value = "비고")
    private String remark;

    @ApiModelProperty(value = "부서코드")
    private String deptCd;

    @ApiModelProperty(value = "부서")
    private String deptNm;

    @ApiModelProperty(value = "관리번호")
    private String facilityMgrNum;
}
