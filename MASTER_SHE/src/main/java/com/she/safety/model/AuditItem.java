package com.she.safety.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "PSM감사기준별항목")
@Getter
@Setter
public class AuditItem {

    @ApiModelProperty(value = "감사항목번호")
    private int auditItemNo;

    @ApiModelProperty(value = "감사기준번호")
    private int auditStdNo;

    @ApiModelProperty(value = "감사범위")
    private String auditStdNm;

    @ApiModelProperty(value = "항목명")
    private String auditItemNm;

    @ApiModelProperty(value = "비고")
    private String remark;

    @ApiModelProperty(value = "사용여부")
    private String useYn;

    @ApiModelProperty(value = "정렬순서")
    private int sortOrder;

    @ApiModelProperty(value = "등록자")
    private String createUserId;

    @ApiModelProperty(value = "등록일")
    private String createDt;

    @ApiModelProperty(value = "수정자")
    private String updateUserId;

    @ApiModelProperty(value = "수정일")
    private String updateDt;

    @ApiModelProperty(value = "계획및결과에서 사용여부")
    private String auditUseYn;

    private String tempId;
}
