package com.she.safety.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "사고자")
@Getter
@Setter
public class AccidentVictim {

    @ApiModelProperty(value = "사내사고피해자번호")
    private int safAccidentVictimNo;
    @ApiModelProperty(value = "사내사고번호")
    private int safAccidentNo;
    @ApiModelProperty(value = "상해구분코드")
    private String damageLvlCd;
    @ApiModelProperty(value = "상해구분명")
    private String damageLvlNm;
    @ApiModelProperty(value = "피해자구분코드")
    private String victimTypeCd;
    @ApiModelProperty(value = "피해자구분명")
    private String victimTypeNm;
    @ApiModelProperty(value = "피해자성명")
    private String victimNm;
    @ApiModelProperty(value = "내부-사번")
    private String victimUserId;
    @ApiModelProperty(value = "내부-직책")
    private String victimDutyNm;
    @ApiModelProperty(value = "내부-부서코드")
    private String victimDeptCd;
    @ApiModelProperty(value = "내외부-소속")
    private String victimDeptNm;
    @ApiModelProperty(value = "성별코드")
    private String comSexTypeCd;
    @ApiModelProperty(value = "성별명")
    private String comSexTypeNm;
    @ApiModelProperty(value = "생년월일")
    private String victimBirthday;
    @ApiModelProperty(value = "내부-입사일")
    private String entDt;
    @ApiModelProperty(value = "내외부-나이")
    private String years;
    @ApiModelProperty(value = "내외부-직급")
    private String position;
    @ApiModelProperty(value = "내외부-경력")
    private String workHis;
    @ApiModelProperty(value = "내외부-현업무직무경력")
    private String jobHis;
    @ApiModelProperty(value = "내외부-사고이력")
    private String accidentHis;
    @ApiModelProperty(value = "사고자 상해종류 리스트")
    private String[] humanInjuryClsCds;
    @ApiModelProperty(value = "사고자 상해부위 리스트")
    private String[] humanInjuryPartCds;
}
