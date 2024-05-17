package com.she.safety.model;

import java.util.List;

import com.she.common.model.AttachFile;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "PSM감사기준")
@Getter
@Setter
public class AuditStd {

    @ApiModelProperty(value = "감사기준번호")
    private int auditStdNo;

    @ApiModelProperty(value = "사업장코드")
    private String plantCd;

    @ApiModelProperty(value = "사업장명")
    private String plantNm;

    @ApiModelProperty(value = "감사범위")
    private String auditStdNm;

    @ApiModelProperty(value = "감사기준(비고)")
    private String auditStdDesc;

    @ApiModelProperty(value = "환산계수")
    private String convFactor;

    @ApiModelProperty(value = "주관부서")
    private String chargeDeptCd;

    @ApiModelProperty(value = "주관부서")
    private String chargeDeptNm;

    @ApiModelProperty(value = "비고")
    private String remark;

    @ApiModelProperty(value = "사용여부")
    private String useYn;

    @ApiModelProperty(value = "사용여부")
    private String useYnNm;

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

    @ApiModelProperty(value = "PSM감사기준별항목 리스트")
    private List<AuditItem> auditItems;

    @ApiModelProperty(value = "삭제할 PSM감사기준별항목 리스트")
    private List<AuditItem> deleteAuditItems;

    @ApiModelProperty(value = "[첨부파일]임시id")
    private String tempId;

    @ApiModelProperty(value = "[첨부파일]파일정보들")
    private List<AttachFile> files;

    @ApiModelProperty(value = "작성자")
    private String writerUserNm;

    @ApiModelProperty(value = "작성일")
    private String writerDt;
}
