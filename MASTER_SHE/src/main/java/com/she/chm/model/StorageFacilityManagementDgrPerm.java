package com.she.chm.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "저장위치 위험물허가량")
@Getter
@Setter
public class StorageFacilityManagementDgrPerm {
    @ApiModelProperty(value = "저장위치코드")
    private String matStrgCd;
    @ApiModelProperty(value = "사업장코드")
    private String plantCd;
    @ApiModelProperty(value = "위험물종류번호")
    private int chmDgrAttAmtNo;
    @ApiModelProperty(value = "법규별분류번호")
    private int regulItmNo;
    @ApiModelProperty(value = "법규별분류명")
    private String regulItmNm;
    @ApiModelProperty(value = "품명")
    private String dgrNm;
    @ApiModelProperty(value = "지정수량")
    private String appAmt;
    @ApiModelProperty(value = "허가량 대비 비율")
    private String appAmtRate;
    @ApiModelProperty(value = "단위번호")
    private int unitNo;
    @ApiModelProperty(value = "단위명")
    private String unitNm;
    @ApiModelProperty(value = "사용여부")
    private String useYn;
    @ApiModelProperty(value = "사용여부명")
    private String useYnNm;
    @ApiModelProperty(value = "정렬순서")
    private int sortOrder;
    @ApiModelProperty(value = "등록자")
    private String createUserId;
    @ApiModelProperty(value = "등록자명")
    private String createUserNm;
    @ApiModelProperty(value = "등록일")
    private String createDt;
    @ApiModelProperty(value = "수정자")
    private String updateUserId;
    @ApiModelProperty(value = "수정자명")
    private String updateUserNm;
    @ApiModelProperty(value = "수정일")
    private String updateDt;
}
