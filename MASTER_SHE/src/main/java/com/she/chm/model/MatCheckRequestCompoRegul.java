package com.she.chm.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@ApiModel(description = "자재검토요청조성정보")
@Data
@ToString
public class MatCheckRequestCompoRegul {

    @ApiModelProperty(value = "검토요청번호")
    private int matChkRqstNo;

    @ApiModelProperty(value = "취급자재 규제정보")
    private List<Integer> regulItmMatVals;

    @ApiModelProperty(value = "취급자재 규제정보")
    private List<RegulItmMatVal> matCheckRequestRegulItms;

    @ApiModelProperty(value = "구성성분정보")
    private List<MatCheckRequestCompo> matCheckRequestCompos;

    @ApiModelProperty(value = "생성자아이디")
    private String createUserId;

    @ApiModelProperty(value = "수정자아이디")
    private String updateUserId;

}
