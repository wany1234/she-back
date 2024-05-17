package com.she.safety.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "변경관리 dashboard 변경발의")
@Getter
@Setter
public class ChangeDashboardStep1 {
    @ApiModelProperty(value = "번호")
    private int safChngNo;
    @ApiModelProperty(value = "문서번호")
    private String chngNum;
    @ApiModelProperty(value = "제목")
    private String bizNm;
    @ApiModelProperty(value = "요청부서명")
    private String rqstDeptNm;
    @ApiModelProperty(value = "등급(작성)명")
    private String prevMocLvlNm;
    @ApiModelProperty(value = "등급(검토)명")
    private String mocLvlNm;
    @ApiModelProperty(value = "변경관리진행단계")
    private String chngStepCd;
    @ApiModelProperty(value = "변경관리진행단계명")
    private String chngStepNm;
}
