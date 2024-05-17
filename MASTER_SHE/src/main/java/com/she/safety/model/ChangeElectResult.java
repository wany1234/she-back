package com.she.safety.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "변경관리_법규별 선해임 해당여부")
@Getter
@Setter
public class ChangeElectResult {
    @ApiModelProperty(value = "결과항목번호")
    private int safChngElectItemResultNo;
    @ApiModelProperty(value = "결과번호")
    private int safChngElectLawResultNo;
    @ApiModelProperty(value = "선해임명번호")
    private int safElectTitlNo;
    @ApiModelProperty(value = "선해임명")
    private String electTitlNm;
    @ApiModelProperty(value = "분야코드")
    private String electAttCd;
    @ApiModelProperty(value = "분야명")
    private String electAttNm;
    @ApiModelProperty(value = "법규코드")
    private String refLawCd;
    @ApiModelProperty(value = "법규명")
    private String refLawNm;
}
