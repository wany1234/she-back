package com.she.health.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "체크리스트")
@Getter
@Setter
public class CheckList {

    @ApiModelProperty(value = "문진번호")
    private int chklistNo;

    @ApiModelProperty(value = "문진명")
    private String chklistNm;

    @ApiModelProperty(value = "사업장코드")
    private String plantCd;

    @ApiModelProperty(value = "사업장명")
    private String plantNm;

    @ApiModelProperty(value = "문진유형")
    private String chklistType;

    @ApiModelProperty(value = "문진유형명")
    private String chklistName;

    @ApiModelProperty(value = "결과유형")
    private String resultType;

    @ApiModelProperty(value = "결과유형명")
    private String resultName;

    @ApiModelProperty(value = "사용여부")
    private String useYn;

    @ApiModelProperty(value = "사용여부명")
    private String useYnNm;

    @ApiModelProperty(value = "비고")
    private String remark;

    @ApiModelProperty(value = "생성자아이디")
    private String createUserId;

    @ApiModelProperty(value = "생성자명")
    private String createUserNm;

    @ApiModelProperty(value = "생성일")
    private String createDt;

    @ApiModelProperty(value = "수정자아이디")
    private String updateUserId;

    @ApiModelProperty(value = "수정자명")
    private String updateUserNm;

    @ApiModelProperty(value = "수정일")
    private String updateDt;

    @ApiModelProperty(value = "삭제할 항목들")
    private List<CheckListItem> deleteCheckListItems;

    @ApiModelProperty(value = "항목들")
    private List<CheckListItem> checkListItems;

}
