package com.she.rsa.model;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Planmgmt {
    private int assessPlanNo;
    private String plantCd;
    private String plantNm;
    private String riskType;
    private String assessYear;
    private String assessTypeCd;
    private String assessTypeNm;
    private String regRegdemCd;
    private String regRegdemNm;
    private String assessStartDt;
    private String assessEndDt;
    private String assessNm;
    private String assessDesc;
    private String stepCd;
    private String pApprRqstNo;
    private String pApprRqstNm;
    private String createUserId;
    private String createUserNm;
    private String createDt;
    private String updateUserId;
    private String updateUserNm;
    private String updateDt;
    private String createDeptNm;
    private String createDeptCd;
    private String updateDeptNm;
    private String updateDeptCd;
    private String createPositionNm;
    private String createPositionCd;
    private String updatePositionNm;
    private String updatePositionCd;
    private String deptCnt;
    private String deptYCnt;
    private String deptNCnt;
    private String overDateCnt;
    private String deptCd;
    private String mainDeptCd;
    private String mainDeptNm;
    private String stateNm;
    @ApiModelProperty(value = "대상부서 목록")
    private List<PlanmgmtDeptList> planmgmtDeptList;

}
