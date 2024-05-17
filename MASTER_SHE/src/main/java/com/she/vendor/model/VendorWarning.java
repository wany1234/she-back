package com.she.vendor.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "협력업체경고지적")
@Data
public class VendorWarning {

    @ApiModelProperty(value = "안전점검결과번호")
    private int safCheckRsltNo;

    @ApiModelProperty(value = "안전점검종류번호")
    private int safCheckKindNo;

    @ApiModelProperty(value = "사업장코드")
    private String plantCd;

    @ApiModelProperty(value = "사업장명")
    private String plantNm;

    @ApiModelProperty(value = "안전점검종류명")
    private String safCheckKindNm;

    @ApiModelProperty(value = "순회일")
    private String checkYmd;

    @ApiModelProperty(value = "순회명")
    private String checkTitle;

    @ApiModelProperty(value = "주관부서코드")
    private String deptCd;

    @ApiModelProperty(value = "주관부서명")
    private String deptNm;

    @ApiModelProperty(value = "대상업체코드")
    private String tgtVendorCd;

    @ApiModelProperty(value = "대상업체명")
    private String tgtVendorNm;

    @ApiModelProperty(value = "순회결과코드")
    private String checkResultCd;

    @ApiModelProperty(value = "순회결과명")
    private String checkResultNm;

    @ApiModelProperty(value = "결과요약")
    private String checkResult;

    @ApiModelProperty(value = "등록자ID")
    private String createUserId;

    @ApiModelProperty(value = "등록자명")
    private String createUserNm;

    @ApiModelProperty(value = "등록일")
    private String createDt;

    @ApiModelProperty(value = "작성자")
    private String writerUserNm;

    @ApiModelProperty(value = "작성일")
    private String writerDt;
}
