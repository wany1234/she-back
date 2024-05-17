package com.she.mgt.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MgtLawRevision {
    @ApiModelProperty(value = "법령일련번호")
    private int lmst;

    @ApiModelProperty(value = "현행연혁코드")
    private String lstepNm;

    @ApiModelProperty(value = "법령한글명")
    private String lnameKor;

    @ApiModelProperty(value = "법령약칭명")
    private String lnameAbb;

    @ApiModelProperty(value = "법령ID")
    private int lkey;

    @ApiModelProperty(value = "법령키")
    private String legiKey;

    @ApiModelProperty(value = "공포일자")
    private String promDate;

    @ApiModelProperty(value = "공포번호")
    private int promNum;

    @ApiModelProperty(value = "제개정구분명")
    private String revDiv;

    @ApiModelProperty(value = "소관부처코드")
    private int mgrGovCd;

    @ApiModelProperty(value = "소관부처명")
    private String mgrGov;

    @ApiModelProperty(value = "법령구분명")
    private String ltypeNm;

    @ApiModelProperty(value = "시행일자")
    private String enfDate;

    @ApiModelProperty(value = "자법타법여부")
    private String lflagNm;

    @ApiModelProperty(value = "법령상세링크")
    private String ldtlLink;

    @ApiModelProperty(value = "등록일")
    private String regDt;

    @ApiModelProperty(value = "등록자ID")
    private String regerId;

    @ApiModelProperty(value = "수정일")
    private String udtDt;

    @ApiModelProperty(value = "수정자ID")
    private String udterId;

    @ApiModelProperty(value = "법령상세생성여부")
    private String dtlInsFlag;

    @ApiModelProperty(value = "법령분류(LAWMST-법, LAWENF-시행령,LAWRGL-시행규칙)")
    private String lawTypeCd;
    //
}
