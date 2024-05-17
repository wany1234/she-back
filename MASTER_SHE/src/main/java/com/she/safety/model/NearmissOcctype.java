package com.she.safety.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "아차사고 발생유형")
@Data
public class NearmissOcctype {

    @ApiModelProperty(value = "사고종류코드")
    private String occTypeCd;
    
    @ApiModelProperty(value = "사고종류코드명")
    private String occTypeNm;
    
    @ApiModelProperty(value = "아차사고번호")
    private int safNearmissNo;
}
