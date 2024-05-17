package com.she.safety.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "변경관리_관련업무내역")
@Getter
@Setter
public class ChangeCheckItemResult {
    @ApiModelProperty(value = "결과번호")
    private int safChngChkItmRsltNo;
    @ApiModelProperty(value = "변경관리번호")
    private int safChngNo;
    @ApiModelProperty(value = "진행사항코드")
    private String chngRefWorkCd;
    @ApiModelProperty(value = "진행사항명")
    private String chngRefWorkNm;
    @ApiModelProperty(value = "완료예정일")
    private String endSchDt;
    @ApiModelProperty(value = "담당자ID")
    private String mgrId;
    @ApiModelProperty(value = "담당자명")
    private String mgrNm;
    @ApiModelProperty(value = "담당부서코드")
    private String mgrDeptCd;
    @ApiModelProperty(value = "담당부서")
    private String mgrDeptNm;
    @ApiModelProperty(value = "실행확인")
    private String contents;
    @ApiModelProperty(value = "확인자ID")
    private String chkId;
    @ApiModelProperty(value = "확인자명")
    private String chkNm;
    @ApiModelProperty(value = "완료일")
    private String endDt;
    @ApiModelProperty(value = "변경실행여부")
    private String chngRsltYn;
    @ApiModelProperty(value = "dashboard 신호등")
    private String checkStatus;
    @ApiModelProperty(value = "등록자ID")
    private String createUserId;
    @ApiModelProperty(value = "등록자명")
    private String createUserNm;
    @ApiModelProperty(value = "등록부서코드")
    private String createDeptCd;
    @ApiModelProperty(value = "등록부서명")
    private String createDeptNm;
    @ApiModelProperty(value = "등록일")
    private String createDt;
    @ApiModelProperty(value = "수정자ID")
    private String updateUserId;
    @ApiModelProperty(value = "수정자명")
    private String updateUserNm;
    @ApiModelProperty(value = "수정부서코드")
    private String updateDeptCd;
    @ApiModelProperty(value = "수정부서명")
    private String updateDeptNm;
    @ApiModelProperty(value = "수정일")
    private String updateDt;
    @ApiModelProperty(value = "코드마스터 사용여부")
    private String useYn;
}
