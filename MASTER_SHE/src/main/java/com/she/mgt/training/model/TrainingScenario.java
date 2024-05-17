package com.she.mgt.training.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "훈련시나리오")
@Data
public class TrainingScenario {
    @ApiModelProperty(value = "훈련시나리오번호")
    private int trainSceNo;

    @ApiModelProperty(value = "훈련시나리오그룹번호")
    private int trainSceGrpNo;

    @ApiModelProperty(value = "사업장코드")
    private String plantCd;

    @ApiModelProperty(value = "사업장")
    private String plantNm;

    @ApiModelProperty(value = "부서코드")
    private String deptCd;

    @ApiModelProperty(value = "부서")
    private String deptNm;

    @ApiModelProperty(value = "C분류코드")
    private String trainSceTypeCd;

    @ApiModelProperty(value = "분류")
    private String trainSceTypeNm;

    @ApiModelProperty(value = "문서번호")
    private String sceNum;

    @ApiModelProperty(value = "제목")
    private String title;

    @ApiModelProperty(value = "재개정번호")
    private String revNum;

    @ApiModelProperty(value = "내용")
    private String contents;

    @ApiModelProperty(value = "개정내용")
    private String revContents;

    @ApiModelProperty(value = "사용여부")
    private String useYn;

    @ApiModelProperty(value = "삭제여부")
    private String delYn;

    @ApiModelProperty(value = "등록자ID")
    private String createUserId;

    @ApiModelProperty(value = "등록자")
    private String createUserNm;

    @ApiModelProperty(value = "등록일_제개정일")
    private String createDt;

    @ApiModelProperty(value = "최종수정자ID")
    private String updateUserId;

    @ApiModelProperty(value = "최종수정자")
    private String updateUserNm;

    @ApiModelProperty(value = "최종수정일")
    private String updateDt;

    @ApiModelProperty(value = "훈련마스터 번호")
    private int safTrainMstNo;

    @ApiModelProperty(value = "작성자")
    private String writerUserNm;

    @ApiModelProperty(value = "작성일")
    private String writerDt;

}