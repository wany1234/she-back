package com.she.mgt.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(description = "법정선임자평가 본인평가/상위평가")
public class ElectEvalRslt {
    @ApiModelProperty(value = "평가계획번호")
    private int evalPlanNo;

    @ApiModelProperty(value = "평가대상자 번호")
    private int evalUserNo;

    @ApiModelProperty(value = "사업장코드")
    private String plantCd;

    @ApiModelProperty(value = "사업장")
    private String plantNm;

    @ApiModelProperty(value = "대상년도")
    private String year;

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

    @ApiModelProperty(value = "소속부서")
    private String meDeptCd;

    @ApiModelProperty(value = "소속부서")
    private String meDeptNm;

    @ApiModelProperty(value = "대상자")
    private String userNm;

    @ApiModelProperty(value = "본인평가자")
    private String meUserId;

    @ApiModelProperty(value = "본인평가자")
    private String meUserNm;

    @ApiModelProperty(value = "본인평가 진행단계")
    private String meProcStep;

    @ApiModelProperty(value = "본인평가 진행단계")
    private String meProcStepNm;

    @ApiModelProperty(value = "상위평가자")
    private String upUserId;

    @ApiModelProperty(value = "상위평가자")
    private String upUserNm;

    @ApiModelProperty(value = "상위평가 진행단계")
    private String upProcStep;

    @ApiModelProperty(value = "상위평가 진행단계")
    private String upProcStepNm;

    @ApiModelProperty(value = "결재요청번호")
    private int apprRqstNo;

    @ApiModelProperty(value = "평가대상자 사업장")
    private String mePlantNm;

    @ApiModelProperty(value = "평가자 사업장")
    private String upPlantNm;

    @ApiModelProperty(value = "평가자 부서")
    private String upDeptNm;

    @ApiModelProperty(value = "본인평가결과 전체 완료 여부")
    private String isMeComplete;

    @ApiModelProperty(value = "평가결과 목록")
    private List<ElectEvalRsltItem> electEvalRsltItems;
}
