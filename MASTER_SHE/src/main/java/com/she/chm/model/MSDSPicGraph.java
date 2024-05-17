package com.she.chm.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "MSDS 그림문자")
@Data
public class MSDSPicGraph {

    @ApiModelProperty(value = "MSDS요청번호")
    private int msdsRqstNo;

    @ApiModelProperty(value = "그림문자코드")
    private String picGraphCd;

}
