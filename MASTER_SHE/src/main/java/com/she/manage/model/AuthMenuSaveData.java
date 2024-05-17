package com.she.manage.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "권한에 따른 메뉴정보 저장데이터")
@Getter
@Setter
public class AuthMenuSaveData {
    @ApiModelProperty(value = "사내사고번호")
    private List<AuthGrpMenu> saveData;
    @ApiModelProperty(value = "사내사고번호")
    private List<AuthGrpMenu> deleteData;
    @ApiModelProperty(value = "권한번호")
    private int authGrpNo;
    @ApiModelProperty(value = "등록자")
    private String createUserId;
}
