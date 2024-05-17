package com.she.safety.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "사고종류")
@Getter
@Setter
public class AccidentOcctype {

    @ApiModelProperty(value = "사내사고종류코드")
    private String accidentTypeCd;
    @ApiModelProperty(value = "사내사고종류명")
    private String accidentTypeNm;
    @ApiModelProperty(value = "사내사고번호")
    private int safAccidentNo;

}
