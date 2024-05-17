package com.she.chm.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "공정관리요령 그림문자")
@Data
public class ProcessManagePicGraph {
    @ApiModelProperty(value = "공정관리요령번호")
    private int chmProcessManageNo;

    @ApiModelProperty(value = "그림문자코드")
    private String picGraphCd;

}
