package com.she.chm.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@ApiModel(description = "취급자재 규제및성분정보")
@Data
@ToString
public class ChemprodChemRegul {

    @ApiModelProperty(value = "취급자재번호")
    private int chemProdNo;

    @ApiModelProperty(value = "취급자재 규제정보")
    private List<Integer> regulItmNos;

    @ApiModelProperty(value = "구성성분 및 규제정보")
    private List<ChemprodChem> chemprodChems;

    @ApiModelProperty(value = "생성자아이디")
    private String createUserId;

    @ApiModelProperty(value = "수정자아이디")
    private String updateUserId;

}
