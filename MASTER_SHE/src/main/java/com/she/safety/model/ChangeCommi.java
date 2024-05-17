package com.she.safety.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "변경관리위원회")
@Getter
@Setter
public class ChangeCommi {
    @ApiModelProperty(value = "위원회번호")
    private int safChngCommiNo;
    @ApiModelProperty(value = "변경관리번호")
    private int safChngNo;
    @ApiModelProperty(value = "개최일")
    private String commiDt;
    @ApiModelProperty(value = "협의내용")
    private String commiContents;
    @ApiModelProperty(value = "등록자ID")
    private String createUserId;
    @ApiModelProperty(value = "등록자명")
    private String createUserNm;
    @ApiModelProperty(value = "등록일")
    private String createDt;
    @ApiModelProperty(value = "등록부서")
    private String deptNm;
    @ApiModelProperty(value = "수정자ID")
    private String updateUserId;
    @ApiModelProperty(value = "수정자명")
    private String updateUserNm;
    @ApiModelProperty(value = "수정일")
    private String updateDt;
    @ApiModelProperty(value = "변경관리위원회_참석자 리스트")
    private List<ChangeCommiPsn> changeCommiPsns;
}
