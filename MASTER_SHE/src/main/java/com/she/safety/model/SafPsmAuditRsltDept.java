package com.she.safety.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "PSM 감사결과 대상부서")
@Getter
@Setter
public class SafPsmAuditRsltDept {

    @ApiModelProperty(value = "감사결과번호")
    private int auditRsltNo;

    @ApiModelProperty(value = "대상부서코드")
    private String targetDeptCd;

    @ApiModelProperty(value = "대상부서코드명")
    private String targetDeptNm;

    @ApiModelProperty(value = "감사범위")
    private List<SafPsmAuditRsltStd> safPsmAuditRsltDeptStds;

    @ApiModelProperty(value = "삭제할 감사범위")
    private List<SafPsmAuditRsltStd> deleteSafPsmAuditRsltDeptStds;

    @ApiModelProperty(value = "pdf대상부서코드명")
    private String targetDeptNm1;

    @ApiModelProperty(value = "pdf대상부서코드명")
    private String targetDeptNm2;

    @ApiModelProperty(value = "pdf대상부서코드명")
    private String targetDeptNm3;

    @ApiModelProperty(value = "pdf대상부서코드명")
    private String targetDeptNm4;

    @ApiModelProperty(value = "pdf대상부서코드명")
    private String targetDeptNm5;

    @ApiModelProperty(value = "pdf대상부서코드명")
    private String targetDeptNm6;
}
