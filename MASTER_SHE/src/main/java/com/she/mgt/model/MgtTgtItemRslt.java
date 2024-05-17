package com.she.mgt.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "SHE목표항목")
@Getter
@Setter
public class MgtTgtItemRslt {
    @ApiModelProperty(value = "SHE목표 항목 번호")
    private int mgtTargetItemRsltNo;
    @ApiModelProperty(value = "SHE목표 월별 번호")
    private int mgtTargetMonthNo;
    @ApiModelProperty(value = "SHE목표 번호")
    private int mgtTargetNo;
    @ApiModelProperty(value = "SHE목표 그룹 번호")
    private int mgtTargetGrpNo;
    @ApiModelProperty(value = "대상월")
    private String month;
    @ApiModelProperty(value = "부소코드")
    private String deptCd;
    @ApiModelProperty(value = "SHE목표 분야 코드")
    private String bizFieldCd;
    @ApiModelProperty(value = "SHE목표 분야명")
    private String bizFieldNm;
    @ApiModelProperty(value = "SHE목표 분야별 항목 번호")
    private int bizFieldItemNo;
    @ApiModelProperty(value = "SHE목표 분야별 항목명")
    private String bizFieldItemNm;
    @ApiModelProperty(value = "소수점 자리수")
    private int decPlace;
    @ApiModelProperty(value = "출력순서")
    private int sortOrder;
    @ApiModelProperty(value = "목표값")
    private String targetVal;
    @ApiModelProperty(value = "실적값")
    private String rsltVal;
    @ApiModelProperty(value = "평가값")
    private String evalVal;
    @ApiModelProperty(value = "등록자")
    private String createUserId;
}
