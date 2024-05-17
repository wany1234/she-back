package com.she.safety.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "법적인허가대상 항목")
@Getter
@Setter
public class ConstLegalLcnMaster {
    @ApiModelProperty(value = "분류코드")
    private String grpCd;
    @ApiModelProperty(value = "분류명")
    private String grpNm;
    @ApiModelProperty(value = "항목값")
    private List<ConstLegalLcn> comboItems;
    @ApiModelProperty(value = "항목저장값")
    private List<ConstLegalLcn> constLegalLcns;
}
