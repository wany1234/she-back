package com.she.safety.model;

import java.util.List;

import com.she.rsa.model.ResultEstimator;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class InspectionSHDept {
    private int implChkPlanNo;
    private int implChkDeptNo;
    private String plantCd;
    private String plantNm;
    private String deptCd;
    private String deptNm;
    private String stepCd;
    private String rApprRqstNm;
    private String rApprRqstNo;
    private String deptReaderId;
    private String readerDeptCd;
    private String readerDeptNm;
    private String deptReaderNm;
    private String showChkFinishYn;
    private String regRegdemCd;
    private String chkStartDt;
    private String chkEndDt;
    private String chkNm;
    private String chkDesc;
    private String chkFinishYn;
    private String chkFinishDt;
    private String chkDt;
    private String regRegdemNm;
    private String year;
    private String createUserId;
    private String updateUserId;
    private String createUserNm;
    private String createDt;
    private String createDeptNm;
    private String createDeptCd;
    private String createPositionNm;
    private String createPositionCd;
    private String updateUserNm;
    private String updateDt;
    private String updateDeptNm;
    private String updateDeptCd;
    private String updatePositionNm;
    private String updatePositionCd;
    private int implChkRsltNo;
    private int imprCaseCnt;
    private String evalDt;
    private String rsltDesc;
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
