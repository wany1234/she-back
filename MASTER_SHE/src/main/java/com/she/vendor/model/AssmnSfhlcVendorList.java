package com.she.vendor.model;

import java.util.HashMap;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "평가및안전보건계획")
@Data
public class AssmnSfhlcVendorList {

    @ApiModelProperty(value = "대상협력업체번호")
    private int evalPlanVendorNo;

    @ApiModelProperty(value = "평가및안전보건계획번호")
    private int vendorEvalPlanNo;

    @ApiModelProperty(value = "협력업체코드")
    private String vendorCd;

    @ApiModelProperty(value = "협력업체코드명")
    private String vendorNm;

    @ApiModelProperty(value = "평가완료여부")
    private String finishYn;

    @ApiModelProperty(value = "평점/총점")
    private String averageCnt;

    @ApiModelProperty(value = "평가시작일")
    private String evalStartDt;

    @ApiModelProperty(value = "평가종료일")
    private String evalEndDt;

    @ApiModelProperty(value = "평가명")
    private String evalTitle;

    @ApiModelProperty(value = "평점")
    private String evalSumPnt;

    @ApiModelProperty(value = "총점")
    private String evalAllPnt;

    @ApiModelProperty(value = "평가구분")
    private String evalClsCd;

    @ApiModelProperty(value = "평가구분명")
    private String evalClsNm;

    @ApiModelProperty(value = "대상년도")
    private String year;

    @ApiModelProperty(value = "평가기간")
    private String evalDate;

    @ApiModelProperty(value = "주관부서")
    private String mainDeptCd;

    @ApiModelProperty(value = "주관부서명")
    private String mainDeptNm;

    @ApiModelProperty(value = "평가완료여부")
    private String evalFinishYn;

    @ApiModelProperty(value = "평가완료여부2")
    private String showEvalFinishYn;

    @ApiModelProperty(value = "평가완료일")
    private String evalFinishDt;

    @ApiModelProperty(value = "사업장코드")
    private String plantCd;

    @ApiModelProperty(value = "사업장명")
    private String plantNm;
    @ApiModelProperty(value = "부서")
    private HashMap<String, Object> actDept = new HashMap<String, Object>();
    @ApiModelProperty(value = "수행부서")
    private String deptCd;

    @ApiModelProperty(value = "수행부서명")
    private String deptNm;

    @ApiModelProperty(value = "평점별등급코드")
    private String ratingCd;

    @ApiModelProperty(value = "평점별등급명")
    private String ratingNm;

    @ApiModelProperty(value = "산업안전보건비반영여부")
    private String costRefYn;

    @ApiModelProperty(value = "산업안전보건비")
    private String cost;

    @ApiModelProperty(value = "결재번호")
    private int apprRqstNo;

    @ApiModelProperty(value = "비고")
    private String vendorDesc;

    @ApiModelProperty(value = "상세내용")
    private String evalDesc;

    @ApiModelProperty(value = "단계")
    private String procStepCd;

    @ApiModelProperty(value = "단계명")
    private String procStepNm;

    @ApiModelProperty(value = "상태")
    private String stateCd;

    @ApiModelProperty(value = "상태명")
    private String stateNm;

    @ApiModelProperty(value = "결재상태코드")
    private String stepCd;

    @ApiModelProperty(value = "결재상태명")
    private String stepNm;

    @ApiModelProperty(value = "평가여부명")
    private String stepStatus;

    @ApiModelProperty(value = "수정자명")
    private String createUserNm;

    @ApiModelProperty(value = "수정자명")
    private String createDt;

    @ApiModelProperty(value = "수정자")
    private String updateUserId;

    @ApiModelProperty(value = "수정자명")
    private String updateUserNm;

    @ApiModelProperty(value = "수정일")
    private String updateDt;

    @ApiModelProperty(value = "수정자부서명")
    private String updateDeptNm;

    @ApiModelProperty(value = "수정자부서코드")
    private String updateDeptCd;

    @ApiModelProperty(value = "수정자직위명")
    private String updatePositionNm;

    @ApiModelProperty(value = "수정자직위코드")
    private String updatePositionCd;

    @ApiModelProperty(value = "평가항목")
    private List<AssmnSfhlcItemList> assmnSfhlcItemList;

}
