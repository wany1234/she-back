package com.she.vendor.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "평가및안전보건계획")
@Data
public class AssmnSfhlc {

    @ApiModelProperty(value = "평가및안전보건계획번호")
    private int vendorEvalPlanNo;

    @ApiModelProperty(value = "사업장코드")
    private String plantCd;

    @ApiModelProperty(value = "사업장명")
    private String plantNm;

    @ApiModelProperty(value = "대상년도")
    private String year;

    @ApiModelProperty(value = "평가구분코드")
    private String evalClsCd;

    @ApiModelProperty(value = "평가구분명")
    private String evalClsNm;

    @ApiModelProperty(value = "평가시작일")
    private String evalStartDt;

    @ApiModelProperty(value = "평가종료일")
    private String evalEndDt;

    @ApiModelProperty(value = "주관부서")
    private String mainDeptCd;

    @ApiModelProperty(value = "주관부서명")
    private String mainDeptNm;

    @ApiModelProperty(value = "평가명")
    private String evalTitle;

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

    @ApiModelProperty(value = "결재번호")
    private int apprRqstNo;

    @ApiModelProperty(value = "등록자")
    private String createUserId;

    @ApiModelProperty(value = "등록자명")
    private String createUserNm;

    @ApiModelProperty(value = "등록일")
    private String createDt;

    @ApiModelProperty(value = "수정자")
    private String updateUserId;

    @ApiModelProperty(value = "수정일")
    private String updateDt;

    @ApiModelProperty(value = "등록자부서명")
    private String createDeptNm;

    @ApiModelProperty(value = "등록자부서코드")
    private String createDeptCd;

    @ApiModelProperty(value = "수정자부서명")
    private String updateDeptNm;

    @ApiModelProperty(value = "수정자부서코드")
    private String updateDeptCd;

    @ApiModelProperty(value = "등록자직위명")
    private String createPositionNm;

    @ApiModelProperty(value = "등록자직위코드")
    private String createPositionCd;

    @ApiModelProperty(value = "수정자직위명")
    private String updatePositionNm;

    @ApiModelProperty(value = "수정자직위코드")
    private String updatePositionCd;

    @ApiModelProperty(value = "수행부서명")
    private String deptNm;

    @ApiModelProperty(value = "대상협력업체수")
    private String vendorCnt;

    @ApiModelProperty(value = "평가미완료")
    private String assessNCnt;

    @ApiModelProperty(value = "평가기한초과")
    private String overDateCnt;

    @ApiModelProperty(value = "평가완료")
    private String assessYCnt;

    @ApiModelProperty(value = "대상협력업체")
    private List<AssmnSfhlcVendorList> assmnSfhlcVendorList;

    @ApiModelProperty(value = "평가항목")
    private List<AssmnSfhlcItemList> assmnSfhlcItemList;

}
