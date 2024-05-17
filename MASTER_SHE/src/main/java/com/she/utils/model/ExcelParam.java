package com.she.utils.model;

import java.util.List;
import java.util.Map;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "excel param")
@Data
public class ExcelParam {

    @ApiModelProperty(value = "테이블 헤더")
    private List<TableHeader> tableHeaders;

    @ApiModelProperty(value = "조회조건")
    private Map<String, Object> searchParam;

    @ApiModelProperty(value = "조회조건")
    private String isSso;
}
