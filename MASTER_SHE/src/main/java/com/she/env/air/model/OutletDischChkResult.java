package com.she.env.air.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 배출구별 배출시설 가동시간
 * 
 *
 */
@Data
public class OutletDischChkResult {

    @ApiModelProperty(value = "작성부서")
    private String deptCd;

    @ApiModelProperty(value = "")
    private String deptNm;

    @ApiModelProperty(value = "사업장")
    private String plantCd;

    @ApiModelProperty(value = "작성일자(측정일자)")
    private String measureYmd;

    @ApiModelProperty(value = "")
    private int eairOutletNo;

    @ApiModelProperty(value = "")
    private String eairOutletNm;

    @ApiModelProperty(value = "")
    private String eairOutletPermitNo;

    @ApiModelProperty(value = "대기 배출시설 번호")
    private int eairDischFacNo;

    @ApiModelProperty(value = "대기 배출시설 명칭")
    private String eairDischFacNm;

    @ApiModelProperty(value = "")
    private String eairOutletNum;

    @ApiModelProperty(value = "")
    private String mainDischFacNm;

    @ApiModelProperty(value = "")
    private String mainDischFacNum;

    @ApiModelProperty(value = "")
    private Float runTm;

    @ApiModelProperty(value = "")
    private String runTmt;

    @ApiModelProperty(value = "")
    private String runMin;

    @ApiModelProperty(value = "")
    private String normalYn;

    @ApiModelProperty(value = "")
    private String remark;

    @ApiModelProperty(value = "등록자ID")
    private String createUserId;

    @ApiModelProperty(value = "등록자명")
    private String createUserNm;

    @ApiModelProperty(value = "등록일")
    private String createDt;

    @ApiModelProperty(value = "수정자ID")
    private String updateUserId;

    @ApiModelProperty(value = "수정자명")
    private String updateUserNm;

    @ApiModelProperty(value = "수정일")
    private String updateDt;

    @ApiModelProperty(value = "")
    private String capVolNm;
}
