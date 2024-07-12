package com.she.vendor.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "협력업체 작업인원")
@Getter
@Setter
public class VendorWorker {
    @ApiModelProperty(value = "업체코드")
    private String vendorCd;
    @ApiModelProperty(value = "업체명")
    private String vendorNm;
    @ApiModelProperty(value = "업체근무자번호")
    private int vendorWorkerNo;
    @ApiModelProperty(value = "성명")
    private String workerNm;
    @ApiModelProperty(value = "생년월일(YYYYMMDD)")
    private String birthYmd;
    @ApiModelProperty(value = "성별")
    private String genderCd;
    @ApiModelProperty(value = "성별명")
    private String genderNm;
    @ApiModelProperty(value = "직책/직무")
    private String workerJob;
    @ApiModelProperty(value = "특이사항")
    private String speCommts;
    @ApiModelProperty(value = "업체분류명")
    private String vendorTypeNm;
    @ApiModelProperty(value = "업체유형명")
    private String vendorAttNm;
    @ApiModelProperty(value = "사업장명")
    private String plantNms;
}
