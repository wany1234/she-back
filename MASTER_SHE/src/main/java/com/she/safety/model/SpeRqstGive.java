package com.she.safety.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "보호구신청및지급")
@Data
public class SpeRqstGive {

    @ApiModelProperty(value = "보호구신청및지급번호")
    private int safSpeRqstGiveNo;

    @ApiModelProperty(value = "사업장코드")
    private String plantCd;

    @ApiModelProperty(value = "사업장")
    private String plantNm;

    @ApiModelProperty(value = "신청여부YN")
    private String rqstYn;

    @ApiModelProperty(value = "신청여부YN")
    private String rqstYnNm;

    @ApiModelProperty(value = "신청일")
    private String rqstYmd;

    @ApiModelProperty(value = "신청/지급요청자부서코드")
    private String rqstDeptCd;

    @ApiModelProperty(value = "신청/지급요청자부서")
    private String rqstDeptNm;

    @ApiModelProperty(value = "신청/지급요청자ID")
    private String rqstUserId;

    @ApiModelProperty(value = "신청/지급요청자")
    private String rqstUserNm;

    @ApiModelProperty(value = "신청비고")
    private String rqstRemark;

    @ApiModelProperty(value = "보호구진행단계코드")
    private String speStepCd;

    @ApiModelProperty(value = "보호구진행단계")
    private String speStepNm;

    @ApiModelProperty(value = "지급일")
    private String giveYmd;

    @ApiModelProperty(value = "신청등록자ID")
    private String rqstCreateUserId;

    @ApiModelProperty(value = "신청등록자")
    private String rqstCreateUserNm;

    @ApiModelProperty(value = "신청등록일")
    private String rqstCreateDt;

    @ApiModelProperty(value = "신청수정자ID")
    private String rqstUpdateUserId;

    @ApiModelProperty(value = "신청수정자")
    private String rqstUpdateUserNm;

    @ApiModelProperty(value = "신청수정일")
    private String rqstUpdateDt;

    @ApiModelProperty(value = "지급등록자ID")
    private String giveCreateUserId;

    @ApiModelProperty(value = "지급등록자")
    private String giveCreateUserNm;

    @ApiModelProperty(value = "지급등록일")
    private String giveCreateDt;

    @ApiModelProperty(value = "지급수정자ID")
    private String giveUpdateUserId;

    @ApiModelProperty(value = "지급수정자")
    private String giveUpdateUserNm;

    @ApiModelProperty(value = "지급수정일")
    private String giveUpdateDt;

    @ApiModelProperty(value = "보호구신청및지급 신청상세 목록")
    private List<SpeRqDtl> speRqDtlList;

    @ApiModelProperty(value = "보호구신청및지급 지급상세 목록")
    private List<SpeGiDtl> speGiDtlList;
    
    @ApiModelProperty(value = "작성자")
    private String writerUserNm;

    @ApiModelProperty(value = "작성일")
    private String writerDt;

    private String userNms;
    private String userIds;
    private String speKindCd;
    private String speKindNm;
    private int safSpeNo;
    private String speNm;
    private int giveNum;
    private String giveUnitCd;
    private String giveUnitNm;
    private int unitCost;
    private int totCost;
}
