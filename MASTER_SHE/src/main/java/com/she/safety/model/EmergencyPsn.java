package com.she.safety.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "훈련_이수자")
@Data
public class EmergencyPsn {

    @ApiModelProperty(value = "훈련계획번호")
    private int safTrainPlanNo;
    @ApiModelProperty(value = "이수자ID")
    private String userId;
    @ApiModelProperty(value = "이수자명")
    private String userNm;
    @ApiModelProperty(value = "이수자소속부서코드")
    private String deptCd;
    @ApiModelProperty(value = "부서명")
    private String deptNm;
    @ApiModelProperty(value = "훈련이수여부")
    private String completYn;
    @ApiModelProperty(value = "재훈련불필요여부 ")
    private String reTrainNotYn;
    @ApiModelProperty(value = "비고(불참사유)")
    private String remark;
    @ApiModelProperty(value = "재교육이수여부")
    private String reCompletYn;
    @ApiModelProperty(value = "재교육비고(불참사유)")
    private String reRemark;
    @ApiModelProperty(value = "사업자코드")
    private String plantCd;
    @ApiModelProperty(value = "사업자명")
    private String plantNm;
    @ApiModelProperty(value = "직책명")
    private String positionNm;

}
