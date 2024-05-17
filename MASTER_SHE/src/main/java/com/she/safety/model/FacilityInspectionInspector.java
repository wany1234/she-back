package com.she.safety.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "시설점검대상자")
@Getter
@Setter
public class FacilityInspectionInspector {

    @ApiModelProperty(value = "시설점검일련번호")
    private int comFacilityCheckNo;
    @ApiModelProperty(value = "시설점검자일련번호")
    private int comFacilityCheckInspectorNo;
    @ApiModelProperty(value = "시설점검일정일련번호")
    private int comFacilityCheckScheduleNo;
    @ApiModelProperty(value = "점검자구분코드")
    private String inspectorClassCd;
    @ApiModelProperty(value = "점검자구분명")
    private String inspectorClassNm;
    @ApiModelProperty(value = "사용자ID")
    private String userId;
    @ApiModelProperty(value = "사용자명")
    private String userNm;
    @ApiModelProperty(value = "부서코드")
    private String deptCd;
    @ApiModelProperty(value = "부서명")
    private String deptNm;
    @ApiModelProperty(value = "직책명")
    private String dutyNm;
    @ApiModelProperty(value = "점검예정시")
    private String checkSchHour;
    @ApiModelProperty(value = "점검예정분")
    private String checkSchMinute;
    @ApiModelProperty(value = "점검예정종료시")
    private String checkSchEhour;
    @ApiModelProperty(value = "점검예정종료분")
    private String checkSchEminute;
    @ApiModelProperty(value = "비고")
    private String remark;
}
