package com.she.safety.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "PSM 감사결과")
@Getter
@Setter
public class SafPsmAuditRslt {

    @ApiModelProperty(value = "감사결과번호")
    private int auditRsltNo;

    @ApiModelProperty(value = "사업장코드")
    private String plantCd;

    @ApiModelProperty(value = "사업장코드명")
    private String plantNm;

    @ApiModelProperty(value = "(C)감사종류")
    private String auditType;

    @ApiModelProperty(value = "(C)감사종류명")
    private String auditTypeNm;

    @ApiModelProperty(value = "감사년도")
    private String auditYear;

    @ApiModelProperty(value = "대상기간")
    private String targetDt;

    @ApiModelProperty(value = "대상기간From")
    private String targetStartDt;

    @ApiModelProperty(value = "대상기간To")
    private String targetEndDt;

    @ApiModelProperty(value = "감사기간")
    private String auditDt;

    @ApiModelProperty(value = "감사기간From")
    private String auditStartDt;

    @ApiModelProperty(value = "감사기간To")
    private String auditEndDt;

    @ApiModelProperty(value = "감사명")
    private String auditNm;

    @ApiModelProperty(value = "역할/책임(선임감사원)")
    private String seniorAudidorRole;

    @ApiModelProperty(value = "역할/책임(감사원)")
    private String audidorRole;

    @ApiModelProperty(value = "비고(선임감사원)")
    private String seniorAuditorRemark;

    @ApiModelProperty(value = "비고(감사원)")
    private String auditorRemark;

    @ApiModelProperty(value = "비고")
    private String remark;

    @ApiModelProperty(value = "차기감사시반영사항")
    private String nextRemark;

    @ApiModelProperty(value = "(C)진행상태")
    private String psmProgState;

    @ApiModelProperty(value = "(C)진행상태명")
    private String psmProgStateNm;

    @ApiModelProperty(value = "진행상태정렬순서")
    private int sortOrder;

    @ApiModelProperty(value = "계획결재요청상태코드")
    private String planApprRqstCd;

    @ApiModelProperty(value = "계획결재요청번호")
    private String planApprRqstNo;

    @ApiModelProperty(value = "계획결재상태명")
    private String planApprRqstNm;

    @ApiModelProperty(value = "결과결재요청상태코드")
    private String rsltApprRqstCd;

    @ApiModelProperty(value = "결과결재요청번호")
    private String rsltApprRqstNo;

    @ApiModelProperty(value = "결과결재상태명")
    private String rsltApprRqstNm;

    @ApiModelProperty(value = "등록자")
    private String createUserId;

    @ApiModelProperty(value = "등록일")
    private String createDt;

    @ApiModelProperty(value = "수정자")
    private String updateUserId;

    @ApiModelProperty(value = "수정일")
    private String updateDt;

    @ApiModelProperty(value = "감사원 소속")
    private String deptCd;

    @ApiModelProperty(value = "감사원ID")
    private String userId;

    @ApiModelProperty(value = "대상부서")
    private List<SafPsmAuditRsltDept> safPsmAuditRsltDepts;

    @ApiModelProperty(value = "감사팀")
    private List<SafPsmAuditRsltUser> safPsmAuditRsltUsers;

    @ApiModelProperty(value = "삭제할 감사팀")
    private List<SafPsmAuditRsltUser> deleteSafPsmAuditRsltUsers;

    @ApiModelProperty(value = "감사범위")
    private List<SafPsmAuditRsltStd> safPsmAuditRsltStds;

    @ApiModelProperty(value = "삭제할 감사범위")
    private List<SafPsmAuditRsltStd> deleteSafPsmAuditRsltStds;

    @ApiModelProperty(value = "개선사항")
    private List<SafPsmAuditRsltImpr> safPsmAuditRsltImprs;

    @ApiModelProperty(value = "삭제할 개선사항")
    private List<SafPsmAuditRsltImpr> deleteSafPsmAuditRsltImprs;

    @ApiModelProperty(value = "작성자")
    private String writerUserNm;

    @ApiModelProperty(value = "작성일")
    private String writerDt;
}
