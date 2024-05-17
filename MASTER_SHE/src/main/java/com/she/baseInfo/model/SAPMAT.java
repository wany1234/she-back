package com.she.baseInfo.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "자재")
@Getter
@Setter
public class SAPMAT {
    @ApiModelProperty(value = "자재코드")
    private String matCode;
    @ApiModelProperty(value = "자재명(국문)")
    private String matName;
    @ApiModelProperty(value = "자재명(영문)")
    private String matNameEng;
    @ApiModelProperty(value = "포장단위")
    private String packUnit;
    @ApiModelProperty(value = "자재그룹코드")
    private String matGroupCd;
    @ApiModelProperty(value = "자재그룹명")
    private String matGroupNm;
    @ApiModelProperty(value = "자재유형")
    private String matType;
    @ApiModelProperty(value = "자재유형명")
    private String matTypeNm;
    @ApiModelProperty(value = "사용여부")
    private String useYn;
    @ApiModelProperty(value = "사용여부명")
    private String useYnNm;
    @ApiModelProperty(value = "등록일")
    private String createDt;
    @ApiModelProperty(value = "수정일")
    private String updateDt;
    @ApiModelProperty(value = "취급자재")
    private int chemProdNo;
    @ApiModelProperty(value = "자재별 제조업체")
    private List<MatVendor> matVendors;

}
