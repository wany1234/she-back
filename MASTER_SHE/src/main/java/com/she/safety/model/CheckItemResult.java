package com.she.safety.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "안전점검 항목별 점검결과")
@Data
public class CheckItemResult {
    
    @ApiModelProperty(value = "안전점검일정일련번호")
    private int safCheckScheduleNo;
    
    @ApiModelProperty(value = "안전점검일련번호")
    private int safCheckNo;
    
    @ApiModelProperty(value = "안전점검항목번호")
    private int safCheckItemNo;
    
    @ApiModelProperty(value = "점검결과")
    private String checkResult;
    
    @ApiModelProperty(value = "비고")
    private String remark;
    
    @ApiModelProperty(value = "등록자")
    private String createUserId;
    
    @ApiModelProperty(value = "등록일")
    private String createDt;
    
    @ApiModelProperty(value = "수정자")
    private String updateUserId;
    
    @ApiModelProperty(value = "수정일")
    private String updateDt;
    
    private String safCheckTypeNm;
    
    private int sortOrder;
}
