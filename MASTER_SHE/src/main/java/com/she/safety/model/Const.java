package com.she.safety.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "공사현황")
@Getter
@Setter
public class Const {
    @ApiModelProperty(value = "공사번호")
    private String constNo;
    @ApiModelProperty(value = "공사seq")
    private int seqValue;
    @ApiModelProperty(value = "PO번호")
    private String constNum;
    @ApiModelProperty(value = "사업장코드")
    private String plantCd;
    @ApiModelProperty(value = "사업장명")
    private String plantNm;
    @ApiModelProperty(value = "작업시작일")
    private String constStartYmd;
    @ApiModelProperty(value = "작업종료일")
    private String constEndYmd;
    @ApiModelProperty(value = "작업기간")
    private String constPeriod;
    @ApiModelProperty(value = "공사명")
    private String constTitle;
    @ApiModelProperty(value = "공사내용")
    private String constContent;
    @ApiModelProperty(value = "공사진행단계")
    private String constStepCd;
    @ApiModelProperty(value = "공사진행단계명")
    private String constStepNm;
    @ApiModelProperty(value = "삭제여부")
    private String delYn;
    @ApiModelProperty(value = "협력업체 노출여부")
    private String showVendorYn;
    @ApiModelProperty(value = "등록자ID")
    private String createUserId;
    @ApiModelProperty(value = "등록자명")
    private String createUserNm;
    @ApiModelProperty(value = "등록부서코드")
    private String createDeptCd;
    @ApiModelProperty(value = "등록부서명")
    private String createDeptNm;
    @ApiModelProperty(value = "등록일")
    private String createDt;
    @ApiModelProperty(value = "수정자ID")
    private String updateUserId;
    @ApiModelProperty(value = "수정자명")
    private String updateUserNm;
    @ApiModelProperty(value = "수정부서코드")
    private String updateDeptCd;
    @ApiModelProperty(value = "수정부서명")
    private String updateDeptNm;
    @ApiModelProperty(value = "수정일")
    private String updateDt;
    @ApiModelProperty(value = "결재진행번호")
    private String apprRqstNo;
    @ApiModelProperty(value = "결재진행코드")
    private String bizApprStepCd;
    @ApiModelProperty(value = "결재진행명")
    private String bizApprStepNm;
    @ApiModelProperty(value = "협력업체명s")
    private String vendorNms;
    @ApiModelProperty(value = "평가계획no")
    private int assessPlanNo;
    @ApiModelProperty(value = "평가명")
    private String assessNm;
    @ApiModelProperty(value = "공사현황 작업구분 리스트")
    private List<ConstKind> wkodKindCds;
    @ApiModelProperty(value = "공사현황 작업구분별 업체 리스트")
    private List<ConstKindSubconn> constKindSubconns;
    @ApiModelProperty(value = "LOTO 리스트")
    private List<LOTO> constLotos;
    @ApiModelProperty(value = "법적인허가 대상항목")
    private List<ConstLegalLcn> constLegalLcns;
    @ApiModelProperty(value = "작성자")
    private String writerUserNm;
    @ApiModelProperty(value = "작성일")
    private String writerDt;
}
