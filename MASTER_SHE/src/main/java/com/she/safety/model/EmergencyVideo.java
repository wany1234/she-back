package com.she.safety.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "훈련계획관리")
@Data
public class EmergencyVideo {

    @ApiModelProperty(value = "훈련계획번호")
    private int safTrainPlanNo;
    @ApiModelProperty(value = "사업장코드")
    private String plantCd;
    @ApiModelProperty(value = "동영상주소")
    private String videoUrl;
    @ApiModelProperty(value = "동영상전체시간")
    private String videoTotalTime;
    @ApiModelProperty(value = "이수처리시간")
    private String videoCompTime;
    @ApiModelProperty(value = "사용여부")
    private String useYn;
    @ApiModelProperty(value = "등록자ID")
    private String createUserId;
    @ApiModelProperty(value = "등록일")
    private String createDt;
    @ApiModelProperty(value = "수정자")
    private String updateUserId;
    @ApiModelProperty(value = "수정일")
    private String updateDt;

}
