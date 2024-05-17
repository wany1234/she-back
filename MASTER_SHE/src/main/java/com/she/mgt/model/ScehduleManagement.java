package com.she.mgt.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "일정관리")
@Getter
@Setter
public class ScehduleManagement {

    @ApiModelProperty(value = "일정번호")
    private int mgtCalendarNo;

    @ApiModelProperty(value = "사업장코드")
    private String plantCd;

    @ApiModelProperty(value = "사업장명")
    private String plantNm;

    @ApiModelProperty(value = "시작일")
    private String startDt;

    @ApiModelProperty(value = "종료일")
    private String endDt;
    
    @ApiModelProperty(value = "기간")
    private String period;

    @ApiModelProperty(value = "일정유형")
    private String mgtCalKindCd;
    
    @ApiModelProperty(value = "일정유형명")
    private String mgtCalKindNm;
    
    @ApiModelProperty(value = "업무유형명")
    private String mgtCalTypeNm;

    @ApiModelProperty(value = "제목")
    private String title;

    @ApiModelProperty(value = "일정내용")
    private String planContents;

    @ApiModelProperty(value = "사용여부")
    private String useYn;

    @ApiModelProperty(value = "사용여부명")
    private String useYnNm;

    @ApiModelProperty(value = "생성자부서코드")
    private String createDeptCd;

    @ApiModelProperty(value = "생성자부서명")
    private String createDeptNm;

    @ApiModelProperty(value = "생성자아이디")
    private String createUserId;

    @ApiModelProperty(value = "생성자명")
    private String createUserNm;

    @ApiModelProperty(value = "생성일")
    private String createDt;

    @ApiModelProperty(value = "수정자부서코드")
    private String updateDeptNm;

    @ApiModelProperty(value = "수정자부서명")
    private String updateDeptCd;

    @ApiModelProperty(value = "수정자아이디")
    private String updateUserId;

    @ApiModelProperty(value = "수정자명")
    private String updateUserNm;

    @ApiModelProperty(value = "수정일")
    private String updateDt;

    @ApiModelProperty(value = "작성자")
    private String writerUserNm;

    @ApiModelProperty(value = "작성일")
    private String writerDt;
    
    @ApiModelProperty(value = "참조자들")
    private List<ScehduleManagementPsn> scehduleManagementPsns;

}
