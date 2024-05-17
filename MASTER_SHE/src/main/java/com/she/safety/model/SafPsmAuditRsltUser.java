package com.she.safety.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "PSM 감사결과_감사원")
@Getter
@Setter
public class SafPsmAuditRsltUser {

    @ApiModelProperty(value = "감사결과번호")
    private int auditRsltNo;

    @ApiModelProperty(value = "감사원번호")
    private int auditUserNo;

    @ApiModelProperty(value = "감사원 비고")
    private String remark;

    @ApiModelProperty(value = "감사원 구분")
    private String auditTypeCd;

    @ApiModelProperty(value = "감사원 구분명")
    private String auditTypeNm;

    @ApiModelProperty(value = "감사원 소속")
    private String deptCd;

    @ApiModelProperty(value = "감사원 소속명")
    private String deptNm;

    @ApiModelProperty(value = "감사원 직위명")
    private String dutyNm;

    @ApiModelProperty(value = "감사원 성명")
    private String userNm;

    @ApiModelProperty(value = "감사원 전공")
    private String majorDesc;

    @ApiModelProperty(value = "감사원 경력")
    private String careerDesc;

    @ApiModelProperty(value = "감사원 교수이수현황")
    private String eduDesc;

    @ApiModelProperty(value = "감사원 자격증 보유현황")
    private String certiDesc;

    @ApiModelProperty(value = "등록자")
    private String createUserId;

    @ApiModelProperty(value = "등록일")
    private String createDt;

    @ApiModelProperty(value = "수정자")
    private String updateUserId;

    @ApiModelProperty(value = "수정일")
    private String updateDt;

    @ApiModelProperty(value = "")
    private int rowNum;

    @ApiModelProperty(value = "선임감사원")
    private List<SafPsmAuditRsltUser> seniorAuditorUsers;

    @ApiModelProperty(value = "감사원")
    private List<SafPsmAuditRsltUser> auditorUsers;

    @ApiModelProperty(value = "감사원 성명")
    private String userNm1;

    @ApiModelProperty(value = "감사원 성명")
    private String userNm2;

}
