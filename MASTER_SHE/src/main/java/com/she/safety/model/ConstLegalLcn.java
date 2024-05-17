package com.she.safety.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "법적인허가대상")
@Getter
@Setter
public class ConstLegalLcn {
    @ApiModelProperty(value = "공사번호")
    private String constNo;
    @ApiModelProperty(value = "작업허가서 번호")
    private int wkodNo;
    @ApiModelProperty(value = "분류코드")
    private String grpCd;
    @ApiModelProperty(value = "분류명")
    private String grpNm;
    @ApiModelProperty(value = "항목코드")
    private String itmCd;
    @ApiModelProperty(value = "항목명")
    private String itmNm;
}
