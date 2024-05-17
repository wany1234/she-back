package com.she.mgt.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "SHE목표등록")
@Getter
@Setter
public class MgtTgtItemPlanRslt {
    @ApiModelProperty(value = "SHE목표 번호")
    private int mgtTargetNo;
    @ApiModelProperty(value = "대상연도")
    private String year;
    @ApiModelProperty(value = "SHE분야 코드")
    private String bizFieldCd;
    @ApiModelProperty(value = "SHE분야명")
    private String bizFieldNm;
    @ApiModelProperty(value = "SHE분야별 항목번호")
    private int bizFieldItemNo;
    @ApiModelProperty(value = "SHE분야별 항목명")
    private String bizFieldItemNm;
    @ApiModelProperty(value = "단계(상태)코드")
    private String tstepCd;
    @ApiModelProperty(value = "단계(상태)명")
    private String tstepNm;
    @ApiModelProperty(value = "소수점자리수")
    private int decPlace;
    @ApiModelProperty(value = "출력순서")
    private int sortOrder;
    @ApiModelProperty(value = "1월")
    private String m1Val;
    @ApiModelProperty(value = "2월")
    private String m2Val;
    @ApiModelProperty(value = "3월")
    private String m3Val;
    @ApiModelProperty(value = "4월")
    private String m4Val;
    @ApiModelProperty(value = "5월")
    private String m5Val;
    @ApiModelProperty(value = "6월")
    private String m6Val;
    @ApiModelProperty(value = "7월")
    private String m7Val;
    @ApiModelProperty(value = "8월")
    private String m8Val;
    @ApiModelProperty(value = "9월")
    private String m9Val;
    @ApiModelProperty(value = "10월")
    private String m10Val;
    @ApiModelProperty(value = "11월")
    private String m11Val;
    @ApiModelProperty(value = "12월")
    private String m12Val;
}
