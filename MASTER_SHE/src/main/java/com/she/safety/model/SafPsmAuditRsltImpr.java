package com.she.safety.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "PSM 감사결과_개선사항")
@Getter
@Setter
public class SafPsmAuditRsltImpr {

    @ApiModelProperty(value = "개선조치번호")
    private int safImprActNo;

    @ApiModelProperty(value = "개선요청내용")
    private String imprRqstContents;

    @ApiModelProperty(value = "조치결과내용")
    private String actResultContents;

    @ApiModelProperty(value = "요청일")
    private String actLimitYmd;

    @ApiModelProperty(value = "조치완료일")
    private String actConfirmYmd;

    @ApiModelProperty(value = "관련테이블PKID")
    private int refTableId;

    @ApiModelProperty(value = "사업장코드")
    private String plantCd;

    @ApiModelProperty(value = "개선분류코드")
    private String imprClassCd;

    @ApiModelProperty(value = "감사범위")
    private String auditStdNm;

    @ApiModelProperty(value = "항목명")
    private String auditItemNm;

    @ApiModelProperty(value = "감사결과번호")
    private int auditRsltNo;

    @ApiModelProperty(value = "조치처리구분코드")
    private String actClassCd;

    @ApiModelProperty(value = "개선진행단계코드")
    private String imprStepCd;

    @ApiModelProperty(value = "수정자")
    private String updateUserId;

    @ApiModelProperty(value = "수정일")
    private String updateDt;

    @ApiModelProperty(value = "psm개선요청부서명")
    private String actDeptNm;

    private String actSchYmd;

}
