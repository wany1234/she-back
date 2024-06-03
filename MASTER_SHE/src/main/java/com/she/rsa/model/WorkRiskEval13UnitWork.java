package com.she.rsa.model;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class WorkRiskEval13UnitWork {

    private String plantCd;
    private String plantNm;
    private String deptCd;
    private String deptNm;
    private String linePrcsNm;
    private String grandPrcsNm;
    private String processNm;
    private String processCd;
    private String prcsLvlNm;
    private String unitWorkCd;
    private String unitWorkNm;
    private String safAcdntCnt;
    private String remark;
    private String sortOrder;
    private String useYn;
    private String createUserId;
    private String createDt;
    private String updateUserId;
    private String updateDt;
    private String processAllNm;

    @ApiModelProperty(value = "세부작업 목록")
    private List<WorkRiskEval13DtlUnitWork> workRiskEval13DtlUnitWork;
}
