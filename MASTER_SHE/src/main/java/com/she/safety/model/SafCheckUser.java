package com.she.safety.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "알람담당자")
@Getter
@Setter
public class SafCheckUser {

    @ApiModelProperty(value = "알람담당자번호")
    private int checkUserNo;
    
    @ApiModelProperty(value = "기계기구번호")
    private int checkMachineNo;
    
    @ApiModelProperty(value = "점검목록번호")
    private int checkListNo;
    
    @ApiModelProperty(value = "담당자(알람)")
    private String alarmUserId;
    
    @ApiModelProperty(value = "담당자명")
    private String userNm;
    
    @ApiModelProperty(value = "직급")
    private String dutyNm;
    
    @ApiModelProperty(value = "직책")
    private String positionNm;
    
    @ApiModelProperty(value = "부서")
    private String deptNm;
    
}
