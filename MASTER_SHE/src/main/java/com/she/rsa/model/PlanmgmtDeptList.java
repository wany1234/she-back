package com.she.rsa.model;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PlanmgmtDeptList {
    private int assessDeptNo;
    private int assessPlanNo;
    private String plantCd;
    private String plantNm;
    private String deptCd;
    private String deptNm;
    private String stepCd;
    private String rApprRqstNm;
    private String rApprRqstNo;
    private String deptReaderId;
    private String deptReaderNm;
    private String deptReaderDeptCd;
    private String assessTypeCd;
    private String showAssessFinishYn;
    private String regRegdemCd;
    private String riskType;
    private String assessStartDt;
    private String assessEndDt;
    private String assessNm;
    private String assessDesc;
    private String assessFinishYn;
    private String assessFinishDt;
    private String assessTypeNm;
    private String regRegdemNm;
    private String assessYear;
    private String createUserId;
    private String createUserNm;
    private String createDt;

    private String createDeptNm;
    private String createDeptCd;
    private String createPositionNm;
    private String createPositionCd;
    private int assessRsltNo;
    private int imprCaseCnt;
    private String evalDt;
    private String assessRsltDesc;
    private String requestCnt;
    private String incompletCnt;
    private String overdueCnt;
    private String completCnt;
    private String mainDeptCd;
    private String mainDeptNm;
    private String stateCd;
    private String stateNm;
    @ApiModelProperty(value = "평가자 목록(내부)")
    private List<ResultEstimator> estimatorInList;
    @ApiModelProperty(value = "평가자 목록(외부)")
    private List<ResultEstimator> estimatorOutList;
}
