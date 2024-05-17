package com.she.env.water.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class OperationCleanFacDept {

    @ApiModelProperty(value = "클린센터번호")
    public int ewtrCleanFacNo;
    @ApiModelProperty(value = "클린센터명")
    public String ewtrCleanFacNm;
    @ApiModelProperty(value = "부서코드")
    public String deptCd;
    @ApiModelProperty(value = "부서명")
    public String deptNm;

}
