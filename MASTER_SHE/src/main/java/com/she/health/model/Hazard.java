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

package com.she.health.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "유해인자")
@Data
public class Hazard {

    @ApiModelProperty(value = "유해인자코드")
    private String hazardCd;

    @ApiModelProperty(value = "유해인자대분류코드")
    private String hazardClsCd;

    @ApiModelProperty(value = "유해인자대분류명")
    private String hazardClsNm;

    @ApiModelProperty(value = "유해인자분류코드")
    private String hazardGradCd;

    @ApiModelProperty(value = "유해인자분류명")
    private String hazardGradNm;

    @ApiModelProperty(value = "유해인자명(한글)")
    private String hazardNmKo;

    @ApiModelProperty(value = "유해인자명(영어)")
    private String hazardNmEn;

    @ApiModelProperty(value = "특수검진 관련 여부")
    private String specialYn;

    @ApiModelProperty(value = "작업환경 관련 여부")
    private String workEnvYn;

    @ApiModelProperty(value = "사용 여부")
    private String useYn;

    @ApiModelProperty(value = "등록자")
    private String createUserId;

    @ApiModelProperty(value = "등록일")
    private String createDt;

    @ApiModelProperty(value = "수정자")
    private String updateUserId;

    @ApiModelProperty(value = "수정일")
    private String updateDt;

    @ApiModelProperty(value = "공정코드")
    private String processCd;

    @ApiModelProperty(value = "유해인자취급공정목록")
    private List<HazardProcess> hazardProcessList;

    @ApiModelProperty(value = "작성자")
    private String writerUserNm;

    @ApiModelProperty(value = "작성일")
    private String writerDt;

}