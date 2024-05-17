package com.she.health.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "체크리스트 항목")
@Getter
@Setter
public class CheckListItem {

    @ApiModelProperty(value = "문진번호")
    private int chklistNo;

    @ApiModelProperty(value = "항목번호")
    private int chklistItemNo;

    @ApiModelProperty(value = "항목명")
    private String chklistItemNm;

    @ApiModelProperty(value = "항목분류")
    private String itemCls;

    @ApiModelProperty(value = "항목분류명")
    private String itemClsNm;

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

}
