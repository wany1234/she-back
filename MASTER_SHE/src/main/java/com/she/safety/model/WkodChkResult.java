package com.she.safety.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "작업허가서 점검확인결과")
@Getter
@Setter
public class WkodChkResult {
    @ApiModelProperty(value = "작업허가서번호")
    private int wkodNo;
    @ApiModelProperty(value = "점검항목번호")
    private String chkItemNo;
    @ApiModelProperty(value = "확인단계코드")
    private String chkStepCd;
    @ApiModelProperty(value = "결과값1")
    private String chkValue1;
    @ApiModelProperty(value = "결과값2")
    private String chkValue2;
    @ApiModelProperty(value = "점검자id")
    private String chkUserId;
    @ApiModelProperty(value = "점검자명")
    private String chkUserNm;

    @ApiModelProperty(value = "작업종류코드")
    private String wkodKindCd;
    @ApiModelProperty(value = "작업종류명")
    private String wkodKindNm;
    @ApiModelProperty(value = "작업허가서 점검 확인사항 구분#SAF_WKOD_DPTY")
    private String wkodDptyCd;
    @ApiModelProperty(value = "작업허가서 점검 확인사항명")
    private String wkodDptyNm;
    @ApiModelProperty(value = "점검항목명")
    private String chkItemNm;
    @ApiModelProperty(value = "체크항목 종류#STANDARD_WO_CHECK_TYPE")
    private String chkTypeCd;
    @ApiModelProperty(value = "체크항목 종류명")
    private String chkTypeNm;

}
