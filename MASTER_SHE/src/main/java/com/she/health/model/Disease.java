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

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "질환")
@Data
public class Disease {
    @ApiModelProperty(value = "질환코드")
    private String heaDiseaseCd;

    @ApiModelProperty(value = "질환명칭")
    private String heaDiseaseNm;

    @ApiModelProperty(value = "질환분류코드")
    private String heaDiseaseClassCd;

    @ApiModelProperty(value = "질환분류명칭")
    private String heaDiseaseClassNm;

    @ApiModelProperty(value = "비고")
    private String remark;

    @ApiModelProperty(value = "정렬순서")
    private int sortOrder;

    @ApiModelProperty(value = "사용여부")
    private String useYn;

    @ApiModelProperty(value = "사용여부명칭")
    private String useYnNm;

    @ApiModelProperty(value = "등록자 아이디")
    private String createUserId;

    @ApiModelProperty(value = "등록자 명")
    private String createUserNm;

    @ApiModelProperty(value = "생성일자")
    private String createDt;

    @ApiModelProperty(value = "수정자 아이디")
    private String updateUserId;

    @ApiModelProperty(value = "수정자 명")
    private String updateUserNm;

    @ApiModelProperty(value = "수정일자")
    private String updateDt;

    @ApiModelProperty(value = "작성자")
    private String writerUserNm;

    @ApiModelProperty(value = "작성일")
    private String writerDt;
}
