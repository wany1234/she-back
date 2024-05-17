package com.she.mgt.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MgtLawMakingChecker {
    @ApiModelProperty(value = "입법예고 검토번호")
    private int lmcSeqNo;

    @ApiModelProperty(value = "검토부서")
    private String checkDeptCd;

    @ApiModelProperty(value = "검토부서")
    private String checkDeptNm;

    @ApiModelProperty(value = "검토자")
    private String checkUserId;

    @ApiModelProperty(value = "검토자")
    private String checkUserNm;

    @ApiModelProperty(value = "검토일자")
    private String checkDt;

    @ApiModelProperty(value = "검토의견")
    private String opinion;

    @ApiModelProperty(value = "의견작성 완료여부")
    private String finishYn;
}
