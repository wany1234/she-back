package com.she.manage.model;

import java.util.List;
import java.util.Map;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "결재문서마스터")
@Data
public class ApprBiz {

    @ApiModelProperty(value = "결재문서마스터번호")
    private int apprBizNo;

    @ApiModelProperty(value = "결재문서유형코드")
    private String apprBizCd;

    @ApiModelProperty(value = "업무명")
    private String bizNm;

    @ApiModelProperty(value = "결재선범위구분코드")
    private String apprBizTypeCd;

    @ApiModelProperty(value = "결재선범위구분코드명")
    private String apprBizTypeNm;

    @ApiModelProperty(value = "결재URL")
    private String apprUrl;

    @ApiModelProperty(value = "결재결재파라메터")
    private String apprParameter;

    @ApiModelProperty(value = "결재메일내용")
    private String apprMailContents;

    @ApiModelProperty(value = "사용여부")
    private String useYn;

    @ApiModelProperty(value = "정렬순서")
    private int sortOrder;

    @ApiModelProperty(value = "등록자")
    private String createUserId;

    @ApiModelProperty(value = "등록자명")
    private String createUserNm;

    @ApiModelProperty(value = "등록일")
    private String createDt;

    @ApiModelProperty(value = "수정자")
    private String updateUserId;

    @ApiModelProperty(value = "수정자명")
    private String updateUserNm;

    @ApiModelProperty(value = "수정일")
    private String updateDt;

    @ApiModelProperty(value = "결재문서결재선")
    private ApprBizLine apprBizLine;

    @ApiModelProperty(value = "결재문서결재선 목록")
    private List<ApprBizLine> apprBizLineList;

    @ApiModelProperty(value = "결재문서결재선 세부정보 목록")
    private List<ApprBizLineDtl> apprBizLineDtlList;

    private List<Map<String, String>> apprLines;

    @ApiModelProperty(value = "작성자")
    private String writerUserNm;

    @ApiModelProperty(value = "작성일")
    private String writerDt;
}
