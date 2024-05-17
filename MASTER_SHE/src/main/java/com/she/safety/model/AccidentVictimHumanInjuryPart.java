package com.she.safety.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "사고자 상해부위")
@Getter
@Setter
public class AccidentVictimHumanInjuryPart {
    @ApiModelProperty(value = "사내사고피해자번호")
    private int safAccidentVictimNo;
    @ApiModelProperty(value = "상해부위코드")
    private String humanInjuryPartCd;
    @ApiModelProperty(value = "상해부위명")
    private String humanInjuryPartNm;

}
