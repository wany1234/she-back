package com.she.safety.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "사고자 상해종류")
@Getter
@Setter
public class AccidentVictimHumanInjuryCls {
    @ApiModelProperty(value = "사내사고피해자번호")
    private int safAccidentVictimNo;
    @ApiModelProperty(value = "상해종류코드")
    private String humanInjuryClsCd;
    @ApiModelProperty(value = "상해종류명")
    private String humanInjuryClsNm;

}
