package com.she.safety.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "보호구신청및지급 지급상세")
@Data
public class SpeGiDtl {

    @ApiModelProperty(value = "보호구신청및지급번호")
    private int safSpeRqstGiveNo;

    @ApiModelProperty(value = "보호구번호")
    private int safSpeNo;

    @ApiModelProperty(value = "사용자ID")
    private String userId;

    @ApiModelProperty(value = "사용자")
    private String userNm;

    @ApiModelProperty(value = "지급수량")
    private int giveNum;

    @ApiModelProperty(value = "단가")
    private int unitCost;

    @ApiModelProperty(value = "재고량")
    private int stocAmt;

    @ApiModelProperty(value = "단위")
    private String unit;

    @ApiModelProperty(value = "보호구종류코드")
    private String speKindCd;

    @ApiModelProperty(value = "보호구종류")
    private String speKindNm;

    @ApiModelProperty(value = "보호구명")
    private String speNm;

    @ApiModelProperty(value = "지급단위코드")
    private String giveUnitCd;

    @ApiModelProperty(value = "지급단위 명")
    private String giveUnitNm;

    @ApiModelProperty(value = "지급주기코드")
    private String giveCyclCd;

    @ApiModelProperty(value = "지급주기")
    private String giveCyclNm;
}
