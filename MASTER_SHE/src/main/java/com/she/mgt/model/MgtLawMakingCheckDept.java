package com.she.mgt.model;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MgtLawMakingCheckDept {
    @ApiModelProperty(value = "입법예고 검토번호")
    private int lmcSeqNo;

    @ApiModelProperty(value = "사업장코드")
    private String plantCd;

    @ApiModelProperty(value = "사업장명")
    private String plantNm;

    @ApiModelProperty(value = "검토부서")
    private String checkDeptCd;

    @ApiModelProperty(value = "검토부서명")
    private String checkDeptNm;

    @ApiModelProperty(value = "의견작성 완료여부")
    private String finishYn;

    @ApiModelProperty(value = "검토자 목록")
    private List<MgtLawMakingChecker> lawMakingCheckers;
}
