package com.she.safety.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "아차사고 사고자정보")
@Data
public class NearmissVictim {

    @ApiModelProperty(value = "아차사고사고자번호")
    private int safNearmissVictimNo;
    
    @ApiModelProperty(value = "아차사고번호")
    private int safNearmissNo;
    
    @ApiModelProperty(value = "성명")
    private String victimNm;
    
    @ApiModelProperty(value = "사용자ID")
    private String victimUserId;
    
    @ApiModelProperty(value = "직책")
    private String victimDutyNm;
    
    @ApiModelProperty(value = "부서코드")
    private String victimDeptCd;
    
    @ApiModelProperty(value = "소속")
    private String victimDeptNm;
    
    @ApiModelProperty(value = "생년월일")
    private String victimBirthday;
    
    @ApiModelProperty(value = "나이")
    private String victimYears;
}
