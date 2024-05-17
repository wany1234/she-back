package com.she.env.gas.model;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class EmstrHis {

    @ApiModelProperty(value = "거래이력 번호")
    private int emstrHisNo;
    @ApiModelProperty(value = "사업장코드")
    private String plantCd;
    @ApiModelProperty(value = "사업장명")
    private String plantNm;
    @ApiModelProperty(value = "거래일자")
    private String trYmd;
    @ApiModelProperty(value = "거래구분")
    private String trGubun;
    @ApiModelProperty(value = "거래구분명")
    private String trGubunNm;
    @ApiModelProperty(value = "거래업체코드")
    private String trVendorCd;
    @ApiModelProperty(value = "거래업체코드명")
    private String trVendorNm;
    @ApiModelProperty(value = "거래사업장코드")
    private String trPlantCd;
    @ApiModelProperty(value = "거래사업장코드명")
    private String trPlantNm;
    @ApiModelProperty(value = "구분/판매 구분")
    private String gubun;
    @ApiModelProperty(value = "구분/판매 구분명")
    private String gubunNm;
    @ApiModelProperty(value = "구매량")
    private BigDecimal purchases;
    @ApiModelProperty(value = "판매량")
    private BigDecimal sales;
    @ApiModelProperty(value = "비고")
    private String remark;
    @ApiModelProperty(value = "등록자")
    private String createUserId;
    @ApiModelProperty(value = "등록일")
    private String createDt;
    @ApiModelProperty(value = "수정자")
    private String updateUserId;
    @ApiModelProperty(value = "수정일")
    private String updateDt;
    @ApiModelProperty(value = "작성자")
    private String writerUserNm;
    @ApiModelProperty(value = "작성일")
    private String writerDt;

}
