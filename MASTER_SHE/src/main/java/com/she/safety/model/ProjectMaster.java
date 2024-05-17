package com.she.safety.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "프로젝트 마스터")
@Getter
@Setter
public class ProjectMaster {

    @ApiModelProperty(value = "프로젝트코드")
    private String plantCd;

    @ApiModelProperty(value = "프로젝트명")
    private String projectName;

    @ApiModelProperty(value = "사업장소재지")
    private String projectAddress;

    @ApiModelProperty(value = "사업개시번호")
    private String projectNumber;

    @ApiModelProperty(value = "사업장관리번호")
    private String projectCode;

    @ApiModelProperty(value = "사업시작일")
    private String fromDate;

    @ApiModelProperty(value = "사업종료일")
    private String toDate;

    @ApiModelProperty(value = "사업금액")
    private long cost;

    @ApiModelProperty(value = "공사개요")
    private String description;

    @ApiModelProperty(value = "사용여부")
    private String useYn;

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
}
