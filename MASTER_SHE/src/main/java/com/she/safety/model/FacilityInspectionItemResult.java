package com.she.safety.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "시설점검 항목별점검결과")
@Getter
@Setter
public class FacilityInspectionItemResult {
    @ApiModelProperty(value = "시설점검일정일련번호")
    private int comFacilityCheckScheduleNo;
    @ApiModelProperty(value = "시설점검일련번호")
    private int comFacilityCheckNo;
    @ApiModelProperty(value = "시설점검항목번호")
    private int comFacilityCheckItemNo;
    @ApiModelProperty(value = "시설점검항목명")
    private String comFacilityCheckNm;
    @ApiModelProperty(value = "시설유형코드")
    private String comFacilityTypeCd;
    @ApiModelProperty(value = "시설유형명")
    private String comFacilityTypeNm;
    @ApiModelProperty(value = "점검결과")
    private String checkResult;
    @ApiModelProperty(value = "비고")
    private String remark;
    @ApiModelProperty(value = "등록자")
    private String createUserId;
    @ApiModelProperty(value = "등록자명")
    private String createUserNm;
    @ApiModelProperty(value = "등록일")
    private String createDt;
    @ApiModelProperty(value = "수정자")
    private String updateUserId;
    @ApiModelProperty(value = "수정자명")
    private String updateUserNm;
    @ApiModelProperty(value = "수정일")
    private String updateDt;
}
