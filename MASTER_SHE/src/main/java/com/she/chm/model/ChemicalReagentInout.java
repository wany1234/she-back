package com.she.chm.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "시약입출고")
@Getter
@Setter
public class ChemicalReagentInout {

    @ApiModelProperty(value = "입출고번호")
    private int reagentInoutNo;

    @ApiModelProperty(value = "사업장코드")
    private String plantCd;

    @ApiModelProperty(value = "사업장명")
    private String plantNm;

    @ApiModelProperty(value = "시약번호(취급자재)")
    private Integer chemProdNo;

    @ApiModelProperty(value = "시약명")
    private String chemProdNm;

    @ApiModelProperty(value = "CAS NO")
    private String casNo;

    @ApiModelProperty(value = "입출고구분")
    private String inoutFlag;

    @ApiModelProperty(value = "입출고구분명")
    private String inoutFlagNm;

    @ApiModelProperty(value = "입출고일")
    private String inoutDt;

    @ApiModelProperty(value = "실험실번호")
    private Integer labNo;

    @ApiModelProperty(value = "실험실명")
    private String labNm;

    @ApiModelProperty(value = "입출고량")
    private String inoutAmt;

    @ApiModelProperty(value = "입고량")
    private String inAmt;

    @ApiModelProperty(value = "출고량")
    private String outAmt;

    @ApiModelProperty(value = "단위명")
    private String unitNm;

    @ApiModelProperty(value = "중량")
    private String weight;

    @ApiModelProperty(value = "비중")
    private String specificGravity;

    @ApiModelProperty(value = "비고")
    private String remarks;

    @ApiModelProperty(value = "생성자아이디")
    private String createUserId;

    @ApiModelProperty(value = "생성자명")
    private String createUserNm;

    @ApiModelProperty(value = "생성일")
    private String createDt;

    @ApiModelProperty(value = "수정자아이디")
    private String updateUserId;

    @ApiModelProperty(value = "수정자명")
    private String updateUserNm;

    @ApiModelProperty(value = "수정일")
    private String updateDt;

    @ApiModelProperty(value = "작성자")
    private String writerUserNm;

    @ApiModelProperty(value = "작성일")
    private String writerDt;
}
