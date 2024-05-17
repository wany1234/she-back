package com.she.baseInfo.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "자재 그룹")
@Getter
@Setter
public class SapMatGroup {
    @ApiModelProperty(value = "자재그룹코드")
    private String matGroupCd;
    @ApiModelProperty(value = "자재그룹명")
    private String matGroupNm;
    @ApiModelProperty(value = "자재상위그룹명 + 자재그룹명")
    private String matGroupNmUp;
    @ApiModelProperty(value = "자재상위그룹명")
    private String matUpGroupNm;
    @ApiModelProperty(value = "출력순서")
    private String outputOrder;

}
