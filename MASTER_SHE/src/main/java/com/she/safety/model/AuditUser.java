package com.she.safety.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "PSM감사원")
@Getter
@Setter
public class AuditUser {

    @ApiModelProperty(value = "감사원번호")
    private int auditUserNo;

    @ApiModelProperty(value = "연도")
    private String year;

    @ApiModelProperty(value = "감사원 구분")
    private String auditTypeCd;

    @ApiModelProperty(value = "감사원 구분명")
    private String auditTypeNm;

    @ApiModelProperty(value = "감사원id")
    private String userId;

    @ApiModelProperty(value = "감사원명")
    private String userNm;

    @ApiModelProperty(value = "사업장코드")
    private String plantCd;

    @ApiModelProperty(value = "사업장명")
    private String plantNm;

    @ApiModelProperty(value = "부서명")
    private String deptNm;

    @ApiModelProperty(value = "감사원 직위명")
    private String dutyNm;

    @ApiModelProperty(value = "전공내역")
    private String majorDesc;

    @ApiModelProperty(value = "경력사항")
    private String careerDesc;

    @ApiModelProperty(value = "교육이수현황")
    private String eduDesc;

    @ApiModelProperty(value = "자격증내역")
    private String certiDesc;

    @ApiModelProperty(value = "비고")
    private String remark;

    @ApiModelProperty(value = "사용여부")
    private String useYn;

    @ApiModelProperty(value = "사용여부명")
    private String useYnNm;

    @ApiModelProperty(value = "등록자")
    private String createUserId;

    @ApiModelProperty(value = "등록일")
    private String createDt;

    @ApiModelProperty(value = "수정자")
    private String updateUserId;

    @ApiModelProperty(value = "수정일")
    private String updateDt;

    @ApiModelProperty(value = "작성자")
    private String writerUserNm;

    @ApiModelProperty(value = "작성일")
    private String writerDt;
}
