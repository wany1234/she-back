package com.she.vendor.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "협력업제자체점검 점검자")
@Data
public class SubconCheckInspector {

    @ApiModelProperty(value = "점검자번호")
    private int vendorCheckInspectPsnNo;

    @ApiModelProperty(value = "안전점검결과번호")
    private int vendorCheckRsltNo;

    @ApiModelProperty(value = "협력업체근무자번호")
    private String vendorWorkerNo;

    @ApiModelProperty(value = "점검자이름")
    private String workerNm;

    @ApiModelProperty(value = "소속회사코드")
    private String vendorCd;

    @ApiModelProperty(value = "소속회사명")
    private String vendorNm;

    @ApiModelProperty(value = "직책명")
    private String workerJob;

    @ApiModelProperty(value = "연락처")
    private String tel;

    @ApiModelProperty(value = "비고")
    private String remark;
}
