package com.she.safety.model;

import java.util.List;

import com.she.common.model.AttachFile;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "PSM 감사결과_항목")
@Getter
@Setter
public class SafPsmAuditRsltItem {

    @ApiModelProperty(value = "항목결과번호")
    private int auditRsltDeptItemNo;

    @ApiModelProperty(value = "감사결과번호")
    private int auditRsltNo;

    @ApiModelProperty(value = "감사항목번호")
    private int auditItemNo;

    @ApiModelProperty(value = "감사기준번호")
    private int auditStdNo;

    @ApiModelProperty(value = "감사범위")
    private String auditStdNm;

    @ApiModelProperty(value = "항목명")
    private String auditItemNm;

    @ApiModelProperty(value = "평가결과(비고)")
    private String resultRemark;

    @ApiModelProperty(value = "평가배점")
    private String resultValue;

    @ApiModelProperty(value = "개선요청문구")
    private String improvement;

    @ApiModelProperty(value = "대상부서코드")
    private String targetDeptCd;

    @ApiModelProperty(value = "등록자")
    private String createUserId;

    @ApiModelProperty(value = "등록일")
    private String createDt;

    @ApiModelProperty(value = "수정자")
    private String updateUserId;

    @ApiModelProperty(value = "수정일")
    private String updateDt;

    @ApiModelProperty(value = "[첨부파일]임시id")
    private String tempId;

    @ApiModelProperty(value = "[첨부파일]파일정보들")
    private List<AttachFile> files;

    @ApiModelProperty(value = "[첨부파일]결과파일정보들")
    private List<AttachFile> reltFiles;
}
