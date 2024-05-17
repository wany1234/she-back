package com.she.vendor.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "협력업제자체점검결과")
@Data
public class SubconCheckResult {

    @ApiModelProperty(value = "협력업체자체점검결과번호")
    private int vendorCheckRsltNo;

    @ApiModelProperty(value = "안전점검종류번호")
    private int safCheckKindNo;

    @ApiModelProperty(value = "안전점검종류명")
    private String safCheckKindNm;

    @ApiModelProperty(value = "프로젝트코드")
    private String plantCd;

    @ApiModelProperty(value = "프로젝트명")
    private String plantNm;

    @ApiModelProperty(value = "점검일")
    private String checkYmd;

    @ApiModelProperty(value = "점검명")
    private String checkTitle;

    @ApiModelProperty(value = "결과요약")
    private String checkResult;

    @ApiModelProperty(value = "점검진행상태")
    private String checkStepCd;

    @ApiModelProperty(value = "점검진행상태명")
    private String checkStepNm;

    @ApiModelProperty(value = "점검회사코드")
    private String vendorCd;

    @ApiModelProperty(value = "점검회사명")
    private String vendorNm;

    @ApiModelProperty(value = "등록자Id")
    private String createPortalId;

    @ApiModelProperty(value = "등록자이름")
    private String createPortalNm;

    @ApiModelProperty(value = "등록일")
    private String createDt;

    @ApiModelProperty(value = "수정자Id")
    private String updatePortalId;

    @ApiModelProperty(value = "수정자이름")
    private String updatePortalNm;

    @ApiModelProperty(value = "수정일")
    private String updateDt;

    @ApiModelProperty(value = "협력회사점검결과항목리스트")
    private List<SubconCheckItemResult> vendorCheckItemResults;

    @ApiModelProperty(value = "협력회사검점검자리스트")
    private List<SubconCheckInspector> vendorCheckInspectors;

    @ApiModelProperty(value = "협력회사개선사항리스트")
    private List<SubconCheckImprAct> vendorCheckImprActs;

    @ApiModelProperty(value = "작성자")
    private String writerUserNm;

    @ApiModelProperty(value = "작성일")
    private String writerDt;
}
