package com.she.manage.model;

import org.springframework.stereotype.Repository;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "배치로그관리")
@Data
public class BatchLog {

    @ApiModelProperty(value = "배치코드")
    private String batchCd;
    @ApiModelProperty(value = "실행일자")
    private String runDt;
    @ApiModelProperty(value = "실행결과")
    private String runResult;
    @ApiModelProperty(value = "실행내용")
    private String remark;

}
