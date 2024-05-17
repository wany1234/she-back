package com.she.safety.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "변경관리 dashboard 검토")
@Getter
@Setter
public class ChangeDashboardStepA {
    @ApiModelProperty(value = "번호")
    private int safChngNo;
    @ApiModelProperty(value = "위원회번호")
    private int safChngCommiNo;
    @ApiModelProperty(value = "개최일")
    private String commiDt;
    @ApiModelProperty(value = "협의내용")
    private String commiContents;
    @ApiModelProperty(value = "위원회개최여부")
    private String commiYn;
    @ApiModelProperty(value = "변경관리위원회_참석자 리스트")
    private List<ChangeCommiPsn> changeCommiPsns;
}
