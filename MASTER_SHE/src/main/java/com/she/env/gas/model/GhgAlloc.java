package com.she.env.gas.model;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class GhgAlloc {

    @ApiModelProperty(value = "할당량관리 번호")
    private int ghgAllocNo;
    @ApiModelProperty(value = "사업장코드")
    private String plantCd;
    @ApiModelProperty(value = "사업장명")
    private String plantNm;
    @ApiModelProperty(value = "대상연도")
    private String year;
    @ApiModelProperty(value = "전년도 이월량")
    private BigDecimal prevAmount;
    @ApiModelProperty(value = "할당량")
    private BigDecimal amount;
    @ApiModelProperty(value = "월 예상배출량")
    private BigDecimal estEmissions;
    @ApiModelProperty(value = "차년도이월량")
    private BigDecimal nextAmount;
    @ApiModelProperty(value = "등록자")
    private String createUserId;
    @ApiModelProperty(value = "등록자")
    private String createUserNm;
    @ApiModelProperty(value = "등록일")
    private String createDt;
    @ApiModelProperty(value = "수정자")
    private String updateUserId;
    @ApiModelProperty(value = "수정자")
    private String updateUserNm;
    @ApiModelProperty(value = "수정일")
    private String updateDt;
    @ApiModelProperty(value = "작성자")
    private String writerUserNm;

    @ApiModelProperty(value = "작성일")
    private String writerDt;
}
