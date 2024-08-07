package com.she.safety.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "훈련_이수자")
@Data
public class EmergencyOutsidePsn {
    @ApiModelProperty(value = "훈련이수자_외부번호")
    private int subconnPsnNo;
    @ApiModelProperty(value = "훈련계획번호")
    private int safTrainPlanNo;
    @ApiModelProperty(value = "업체명")
    private String vendorNm;
    @ApiModelProperty(value = "업체번호")
    private String vendorCd;
    @ApiModelProperty(value = "성명")
    private String workerNm;
    @ApiModelProperty(value = "생년월일")
    private String birthYmd;
    @ApiModelProperty(value = "성별코드")
    private String genderCd;
    @ApiModelProperty(value = "성별명")
    private String genderNm;
    @ApiModelProperty(value = "협력업체근무자번호")
    private int vendorWorkerNo;
    @ApiModelProperty(value = "이수일")
    private String completYmd;
    @ApiModelProperty(value = "비고")
    private String remark;
}
