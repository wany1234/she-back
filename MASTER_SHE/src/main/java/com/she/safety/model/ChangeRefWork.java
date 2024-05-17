package com.she.safety.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "변경관리_관련업무진행사항")
@Getter
@Setter
public class ChangeRefWork {
    @ApiModelProperty(value = "관련업무내역번호")
    private int safChngRefWorkNo;
    @ApiModelProperty(value = "변경관리번호")
    private int safChngNo;
    @ApiModelProperty(value = "진행사항코드")
    private String chngRefWorkCd;
    @ApiModelProperty(value = "진행사항명")
    private String chngRefWorkNm;
    @ApiModelProperty(value = "관련테이블")
    private String refTableNm;
    @ApiModelProperty(value = "관련테이블ID")
    private String refTableId;
    @ApiModelProperty(value = "등록자ID")
    private String createUserId;
    @ApiModelProperty(value = "등록자명")
    private String createUserNm;
    @ApiModelProperty(value = "등록일")
    private String createDt;
    @ApiModelProperty(value = "수정자ID")
    private String updateUserId;
    @ApiModelProperty(value = "수정자명")
    private String updateUserNm;
    @ApiModelProperty(value = "수정일")
    private String updateDt;
    @ApiModelProperty(value = "관련제목명")
    private String refWorkNm;
    @ApiModelProperty(value = "진행상태")
    private String stepNm;
    @ApiModelProperty(value = "관련업무분류코드")
    private String typeCd;
    @ApiModelProperty(value = "관련등록수정일")
    private String refWorkDt;
}
