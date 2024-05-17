package com.she.safety.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "설비점검 항목별점검결과")
@Getter
@Setter
public class FacilityCheckItemResult {
    @ApiModelProperty(value = "설비점검일정일련번호")
    private String safFacilityCd;
    @ApiModelProperty(value = "설비점검일정일련번호")
    private int safFacilityCheckScheduleNo;
    @ApiModelProperty(value = "설비점검일련번호")
    private int safFacilityCheckNo;
    @ApiModelProperty(value = "설비점검항목번호")
    private int safFacilityCheckItemNo;
    @ApiModelProperty(value = "설비점검항목명")
    private String safFacilityCheckNm;
    @ApiModelProperty(value = "설비유형코드")
    private String safFacilityTypeCd;
    @ApiModelProperty(value = "설비유형명")
    private String safFacilityTypeNm;
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
