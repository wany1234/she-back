package com.she.safety.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "유해위험기계기구")
@Getter
@Setter
public class HazardousRiskMechanism {
    
    @ApiModelProperty(value = "유해위험기계기구 리스트")
    private List<SafCheckMachine> safCheckMachines;
    
    @ApiModelProperty(value = "점검이력 리스트")
    private List<SafCheckLog> safCheckLogs;
    
    // 검색 조건
    @ApiModelProperty(value = "사업장코드")
    private String plantCd;
    
    @ApiModelProperty(value = "기계기구번호")
    private String machineTypeCd;
    
    @ApiModelProperty(value = "검사대상")
    private String inspYn;
    
    @ApiModelProperty(value = "차기검사일자 검색 시작일")
    private String startDate;
    
    @ApiModelProperty(value = "차기검사일자 검색 종료일")
    private String endDate;
    
    @ApiModelProperty(value = "ITEM NO")
    private String itemNo;
}
