package com.she.common.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "그리드 컬럼")
@Getter
@Setter
public class UserGridCol {

    @ApiModelProperty(value = "사용자번호")
    private String userId;

    @ApiModelProperty(value = "사용자명")
    private String userNm;

    @ApiModelProperty(value = "메뉴명")
    private String menuId;

    @ApiModelProperty(value = "메뉴명")
    private String menuNm;

    @ApiModelProperty(value = "그리드아이디")
    private String gridId;

    @ApiModelProperty(value = "컬럼명")
    private String colNm;

    @ApiModelProperty(value = "표시순서")
    private int dispOrd;

    @ApiModelProperty(value = "넓이")
    private int width;

    @ApiModelProperty(value = "보이기여부")
    private String visible;

    @ApiModelProperty(value = "사용자메뉴그리드별 컬럼 목록 ")
    private List<UserGridCol> userGridCols;

    @ApiModelProperty(value = "전역처리여부")
    private String isAll;

    private String type;

    private String style;
}
