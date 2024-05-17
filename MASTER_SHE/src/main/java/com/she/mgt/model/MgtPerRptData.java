package com.she.mgt.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "중처법 정기보고자료")
@Data
public class MgtPerRptData {

    @ApiModelProperty(value = "중처법 정기보고자료 번호")
    private int perRptDataNo;

    @ApiModelProperty(value = "주관부서 사업장 코드")
    private String plantCd;

    @ApiModelProperty(value = "주관부서 코드")
    private String deptCd;

    @ApiModelProperty(value = "주관부서명")
    private String deptNm;

    @ApiModelProperty(value = "년도")
    private String year;

    @ApiModelProperty(value = "구분(상/하반기)코드")
    private String halfTypeCd;

    @ApiModelProperty(value = "구분(상/하반기)코드명")
    private String halfTypeNm;

    @ApiModelProperty(value = "보고일자")
    private String rptDt;

    @ApiModelProperty(value = "보고명")
    private String rptNm;

    @ApiModelProperty(value = "단계")
    private String procStepCd;

    @ApiModelProperty(value = "상태")
    private String stepCd;

    @ApiModelProperty(value = "상태명")
    private String stepNm;

    @ApiModelProperty(value = "등록자ID")
    private String createUserId;

    @ApiModelProperty(value = "등록일시")
    private String createDt;

    @ApiModelProperty(value = "수정자ID")
    private String updateUserId;

    @ApiModelProperty(value = "수정일시")
    private String updateDt;

    @ApiModelProperty(value = "등록자부서명")
    private String createDeptNm;

    @ApiModelProperty(value = "등록자부서코드")
    private String createDeptCd;

    @ApiModelProperty(value = "수정자부서명")
    private String updateDeptNm;

    @ApiModelProperty(value = "수정자부서코드")
    private String updateDeptCd;

    @ApiModelProperty(value = "등록자직위명")
    private String createPositionNm;

    @ApiModelProperty(value = "등록자직위코드")
    private String createPositionCd;

    @ApiModelProperty(value = "수정자직위명")
    private String updatePositionNm;

    @ApiModelProperty(value = "수정자직위코드")
    private String updatePositionCd;

    @ApiModelProperty(value = "작성자")
    private String writerNm;

    @ApiModelProperty(value = "작성일")
    private String writeDt;

    @ApiModelProperty(value = "보고명")
    private String rptData;

    @ApiModelProperty(value = "파일순번")
    private String fileNo;

    @ApiModelProperty(value = "파일타입")
    private String fileExt;

}
