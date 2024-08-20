package com.she.mgt.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "법정선임자평가 계획")
public class ElectEvalPlan {
    @ApiModelProperty(value = "평가계획번호")
    private int evalPlanNo;

    @ApiModelProperty(value = "대상년도")
    private String year;

    @ApiModelProperty(value = "사업장코드")
    private String plantCd;

    @ApiModelProperty(value = "사업장명")
    private String plantNm;

    @ApiModelProperty(value = "구분(상반기/하반기)")
    private String halfTypeCd;

    @ApiModelProperty(value = "구분(상반기/하반기)")
    private String halfTypeNm;

    @ApiModelProperty(value = "주관부서")
    private String deptCd;

    @ApiModelProperty(value = "주관부서")
    private String deptNm;

    @ApiModelProperty(value = "법정선임자번호")
    private int safElectTitlNo;

    @ApiModelProperty(value = "법정선임자")
    private String electTitlNm;

    @ApiModelProperty(value = "평가명")
    private String evalNm;

    @ApiModelProperty(value = "상세내용")
    private String evalDesc;

    @ApiModelProperty(value = "단계 SAF_ELECT_TITL_STEP")
    private String procStepCd;

    @ApiModelProperty(value = "단계 SAF_ELECT_TITL_STEP")
    private String procStepNm;

    @ApiModelProperty(value = "상태 COM_STATE")
    private String stateCd;

    @ApiModelProperty(value = "상태 COM_STATE")
    private String stateNm;

    @ApiModelProperty(value = "단계(상태)")
    private String stepCd;

    @ApiModelProperty(value = "단계(상태)")
    private String stepNm;

    @ApiModelProperty(value = "결재요청번호")
    private int apprRqstNo;

    @ApiModelProperty(value = "등록자ID")
    private String createUserId;

    @ApiModelProperty(value = "등록일시")
    private String createDt;

    @ApiModelProperty(value = "수정자ID")
    private String updateUserId;

    @ApiModelProperty(value = "수정일시")
    private String updateDt;

    @ApiModelProperty(value = "작성자")
    private String writerNm;

    @ApiModelProperty(value = "작성일")
    private String writeDt;

    @ApiModelProperty(value = "전체대상")
    private int totalCnt;

    @ApiModelProperty(value = "본인평가")
    private int meCnt;

    @ApiModelProperty(value = "상위평가")
    private int upCnt;

    @ApiModelProperty(value = "평가 미진행")
    private int noCnt;

    @ApiModelProperty(value = "평가 대상자 목록")
    private List<ElectEvalUser> electEvalUsers;

    @ApiModelProperty(value = "평가 대상자 삭제 목록")
    private List<ElectEvalUser> deleteElectEvalUsers;

    @ApiModelProperty(value = "평가 항목 목록")
    private List<ElectEvalItem> electEvalItems;
}
