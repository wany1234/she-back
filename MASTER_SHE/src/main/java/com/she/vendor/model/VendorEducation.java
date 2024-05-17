package com.she.vendor.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "협력업체교육")
@Data
public class VendorEducation {

    @ApiModelProperty(value = "사업장코드")
    private String plantCd;

    @ApiModelProperty(value = "사업장명")
    private String plantNm;

    @ApiModelProperty(value = "교육시작일")
    private String eduSYmd;

    @ApiModelProperty(value = "교육종료일")
    private String eduEYmd;

    @ApiModelProperty(value = "교육분류코드")
    private String eduAttCd;

    @ApiModelProperty(value = "교육분류명")
    private String eduAttNm;

    @ApiModelProperty(value = "교육과정번호")
    private int safEduCourseNo;

    @ApiModelProperty(value = "교육과정명")
    private String eduCourseNm;

    @ApiModelProperty(value = "교육구분코드")
    private String eduTypeCd;

    @ApiModelProperty(value = "교육구분명")
    private String eduTypeNm;

    @ApiModelProperty(value = "교육명")
    private String eduNm;

    @ApiModelProperty(value = "담당부서코드")
    private String deptCd;

    @ApiModelProperty(value = "담당부서명")
    private String deptNm;

    @ApiModelProperty(value = "협력업체명")
    private String vendorNm;

    @ApiModelProperty(value = "성명")
    private String workerNm;

    @ApiModelProperty(value = "시작일")
    private String startDate;

    @ApiModelProperty(value = "종료일")
    private String endDate;

    @ApiModelProperty(value = "작성자")
    private String writerUserNm;

    @ApiModelProperty(value = "작성일")
    private String writerDt;
}
