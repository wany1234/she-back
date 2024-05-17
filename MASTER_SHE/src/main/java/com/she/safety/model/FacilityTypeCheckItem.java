package com.she.safety.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "시설유형별점검항목")
@Getter
@Setter
public class FacilityTypeCheckItem {
    @ApiModelProperty(value = "시설점검항목번호")
    private int facilityCheckItemNo;
    @ApiModelProperty(value = "시설점검항목명")
    private String facilityCheckItemNm;
    @ApiModelProperty(value = "시설점검종류코드")
    private String facilityCheckCd;
    @ApiModelProperty(value = "시설점검종류명")
    private String facilityCheckNm;
    @ApiModelProperty(value = "사업장코드")
    private String plantCd;
    @ApiModelProperty(value = "사업장명")
    private String plantNm;
    @ApiModelProperty(value = "시설유형코드")
    private String facilityTypeCd;
    @ApiModelProperty(value = "시설유형명")
    private String facilityTypeNm;
    @ApiModelProperty(value = "사용여부")
    private String useYn;
    @ApiModelProperty(value = "사용여부명")
    private String useYnNm;
    @ApiModelProperty(value = "정렬순서")
    private String sortOrder;
    @ApiModelProperty(value = "생성자아이디")
    private String createUserId;
    @ApiModelProperty(value = "수정자아이디")
    private String updateUserId;
    @ApiModelProperty(value = "작성자")
    private String writerUserNm;
    @ApiModelProperty(value = "작성일")
    private String writerDt;
}
