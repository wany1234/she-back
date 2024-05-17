package com.she.chm.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "물질단위")
@Getter
@Setter
public class ChemicalUnit {
    @ApiModelProperty(value = "물질단위번호")
    private int unitNo;

    @ApiModelProperty(value = "물질단위명")
    private String unitNm;

    @ApiModelProperty(value = "물질단위코드")
    private String unitCd;

    @ApiModelProperty(value = "단위코드(상업)")
    private String unitBusiness;

    @ApiModelProperty(value = "KG환산값")
    private String factor;

    @ApiModelProperty(value = "사용여부")
    private String useYn;

    @ApiModelProperty(value = "사용여부명")
    private String useYnNm;

    @ApiModelProperty(value = "정렬순서")
    private int sortOrder;

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
