/**
 * Copyright (C) 2019, 2019 All Right Reserved, http://www.yullin.com/
 *
 * SHE Business can not be copied and/or distributed without the express
 * permission of Yullin Technologies
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 */

package com.she.manage.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "부서")
@Getter
@Setter
public class Dept {

    @ApiModelProperty(value = "부서코드")
    private String deptCd;

    @ApiModelProperty(value = "상위부서코드")
    private String pdeptCd;

    @ApiModelProperty(value = "상위부서명")
    private String pdeptNm;

    @ApiModelProperty(value = "부서명")
    private String deptNm;

    @ApiModelProperty(value = "사업장코드")
    private String plantCd;

    @ApiModelProperty(value = "사업장명")
    private String plantNm;

    @ApiModelProperty(value = "등록자ID")
    private String createUserId;

    @ApiModelProperty(value = "등록자명")
    private String createUserNm;

    @ApiModelProperty(value = "등록일")
    private String createDt;

    @ApiModelProperty(value = "수정자ID")
    private String updateUserId;

    @ApiModelProperty(value = "수정자명")
    private String updateUserNm;

    @ApiModelProperty(value = "수정일")
    private String updateDt;

    @ApiModelProperty(value = "공정코드")
    private int processNo;

    @ApiModelProperty(value = "공정코드")
    private String processCd;

    @ApiModelProperty(value = "공정명")
    private String processNm;

    @ApiModelProperty(value = "사용여부")
    private String useYn;

    @ApiModelProperty(value = "사용여부명")
    private String useYnNm;

    @ApiModelProperty(value = "부서레벨")
    private String deptLvl;

    @ApiModelProperty(value = "부서레벨명")
    private String deptLvlNm;

    @ApiModelProperty(value = "keyValue")
    private String keyValue;

    @ApiModelProperty(value = "공정리스트")
    private String processList;

    @ApiModelProperty(value = "공정코드리스트")
    private String processCdList;

    @ApiModelProperty(value = "공정리스트")
    private List<Process> selectProcess;

    @ApiModelProperty(value = "부서코드")
    private String id;

    @ApiModelProperty(value = "부서명")
    private String label;

}
