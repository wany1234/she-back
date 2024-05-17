package com.she.safety.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "보호구신청및지급 신청상세")
@Data
public class SpeRqDtl {

    @ApiModelProperty(value = "보호구신청및지급번호")
    private int safSpeRqstGiveNo;

    @ApiModelProperty(value = "보호구종류코드")
    private String speKindCd;

    @ApiModelProperty(value = "보호구종류")
    private String speKindNm;

    @ApiModelProperty(value = "신청수량")
    private int rqstNum;

    @ApiModelProperty(value = "신청보호구비고")
    private String rqstSpeRemark;

    @ApiModelProperty(value = "지급보호구비고")
    private String giveSpeRemark;
}
