package com.she.safety.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "변경후기대효과")
@Getter
@Setter
public class ChangeEffect {
    @ApiModelProperty(value = "변경관리번호")
    private int safChngNo;
    @ApiModelProperty(value = "변경후기대효과코드")
    private String chngEffectCd;
    @ApiModelProperty(value = "변경후기대효과명")
    private String chngEffectNm;
}
