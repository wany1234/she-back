package com.she.env.waste.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "부서별 폐기물 분배율")
@Getter
@Setter
public class Disporate {

    private String ewstWasteRateSeq;

    @ApiModelProperty(value = "분배율번호")
    private String ewstWasteRateNo;

    @ApiModelProperty(value = "사업장코드")
    private String plantCd;

    @ApiModelProperty(value = "사업장 명")
    private String plantNm;

    @ApiModelProperty(value = "기준년")
    private String year;

    @ApiModelProperty(value = "폐기물 분류코드")
    private String ewstClassCd;

    @ApiModelProperty(value = "폐기물 분류명")
    private String ewstClassNm;

    @ApiModelProperty(value = "폐기물 번호")
    private String ewstWasteNo;

    @ApiModelProperty(value = "폐기물 이름")
    private String ewstWasteNm;

    @ApiModelProperty(value = "기준월")
    private String month;

    @ApiModelProperty(value = "부서코드")
    private String deptCd;

    @ApiModelProperty(value = "부서명")
    private String deptNm;

    @ApiModelProperty(value = "분배율")
    private Float rate;

    @ApiModelProperty(value = "등록자")
    private String createUserId;

    @ApiModelProperty(value = "등록일자")
    private String createDt;

    @ApiModelProperty(value = "수정자")
    private String updateUserId;

    @ApiModelProperty(value = "수정일자")
    private String updateDt;

    @ApiModelProperty(value = "부서별 분배")
    private List<DisporateDeptItem> disporateDeptItem;

    private List<String> checkedDeptList;
    @ApiModelProperty(value = "작성자")
    private String writerUserNm;

    @ApiModelProperty(value = "작성일")
    private String writerDt;

}
