package com.she.safety.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "공사현황 작업구분별 업체 출입자")
@Getter
@Setter
public class ConstKindSubconnWorker {
    @ApiModelProperty(value = "공사작업자번호")
    private int subconnWorkerNo;
    @ApiModelProperty(value = "공사작업구분별_번호")
    private int constKindSubconnNo;
    @ApiModelProperty(value = "자체작업여부")
    private String selfYn;
    @ApiModelProperty(value = "자체-사용자ID")
    private String userId;
    @ApiModelProperty(value = "공통-성명")
    private String workerNm;
    @ApiModelProperty(value = "자체-부서코드")
    private String deptCd;
    @ApiModelProperty(value = "공통-소속처명")
    private String deptNm;
    @ApiModelProperty(value = "업체-생년월일(YYYYMMDD)")
    private String birthYmd;
    @ApiModelProperty(value = "업체-성별")
    private String genderCd;
    @ApiModelProperty(value = "업체-성별명")
    private String genderNm;
    @ApiModelProperty(value = "공통-직책/직무")
    private String workerJob;
    @ApiModelProperty(value = "업체-차량번호")
    private String carNum;
    @ApiModelProperty(value = "업체-반입장비")
    private String inEquip;
    @ApiModelProperty(value = "공통-특이사항")
    private String speCommts;
}
