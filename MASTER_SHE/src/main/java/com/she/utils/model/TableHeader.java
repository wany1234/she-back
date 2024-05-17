package com.she.utils.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "테이블 헤더")
@Data
public class TableHeader {

    @ApiModelProperty(value = "컬럼 명칭")
    private String name;

    @ApiModelProperty(value = "컬럼 표기값")
    private String text;

    @ApiModelProperty(value = "컬럼 타입, 1:사진")
    private String type;

    @ApiModelProperty(value = "하위 헤더")
    private List<TableHeader> child;
}
