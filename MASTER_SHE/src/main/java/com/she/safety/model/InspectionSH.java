package com.she.safety.model;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class InspectionSH {
    private int implChkPlanNo;
    private String plantCd;
    private String plantNm;
    private String year;
    private String regRegdemCd;
    private String regRegdemNm;
    private String chkStartDt;
    private String chkEndDt;
    private String mainDeptCd;
    private String mainDeptNm;
    private String chkNm;
    private String chkDesc;
    private String procStepCd;
    private String procStepNm;
    private String stateCd;
    private String stateNm;
    private String stepCd;
    private String stepNm;
    private String papprRqstNo;
    private String papprRqstNm;
    private String createUserId;
    private String createUserNm;
    private String createDt;
    private String updateUserId;
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

    @ApiModelProperty(value = "대상부서 목록")
    private List<InspectionSHDept> inspectionSHDetp;

}
