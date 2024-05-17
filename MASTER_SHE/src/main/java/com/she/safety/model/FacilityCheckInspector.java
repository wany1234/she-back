package com.she.safety.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "설비점검대상자")
@Getter
@Setter
public class FacilityCheckInspector {

    @ApiModelProperty(value = "설비점검일정일련번호")
    private int safFacilityCheckInspectorNo;
    @ApiModelProperty(value = "설비점검일정일련번호")
    private int safFacilityCheckScheduleNo;
    @ApiModelProperty(value = "설비점검일련번호")
    private int safFacilityCheckNo;
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
