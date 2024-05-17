package com.she.safety.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "보호구정보")
@Data
public class Spe {
    @ApiModelProperty(value = "보호구번호")
    private int safSpeNo;

    @ApiModelProperty(value = "보호구명")
    private String speNm;

    @ApiModelProperty(value = "보호구종류")
    private String speKindCd;

    @ApiModelProperty(value = "보호구종류 명")
    private String speKindNm;

    @ApiModelProperty(value = "신청대상여부")
    private String rqstYn;

    @ApiModelProperty(value = "신청대상여부 명")
    private String rqstYnNm;

    @ApiModelProperty(value = "지급구분코드")
    private String giveKindCd;

    @ApiModelProperty(value = "지급구분 명")
    private String giveKindNm;

    @ApiModelProperty(value = "지급단위코드")
    private String giveUnitCd;

    @ApiModelProperty(value = "지급단위 명")
    private String giveUnitNm;

    @ApiModelProperty(value = "정렬순서")
    private int sortOrder;

    @ApiModelProperty(value = "현재고")
    private int nowStock;

    @ApiModelProperty(value = "사용여부")
    private String useYn;

    @ApiModelProperty(value = "사용여부 명")
    private String useYnNm;

    @ApiModelProperty(value = "사업장코드")
    private String plantCd;

    @ApiModelProperty(value = "사업장")
    private String plantNm;

    @ApiModelProperty(value = "SAP 자재코드")
    private String matCd;

    @ApiModelProperty(value = "SAP 자재 명")
    private String matName;

    @ApiModelProperty(value = "지급주기")
    private String giveCyclCd;

    @ApiModelProperty(value = "지급주기")
    private String giveCyclNm;

    @ApiModelProperty(value = "단가")
    private int cost;

    @ApiModelProperty(value = "단가")
    private int unitCost;

    @ApiModelProperty(value = "등록자ID")
    private String createUserId;

    @ApiModelProperty(value = "등록일")
    private String createDt;

    @ApiModelProperty(value = "수정자ID")
    private String updateUserId;

    @ApiModelProperty(value = "수정일")
    private String updateDt;

    @ApiModelProperty(value = "보호구 이미지 파일일련번호")
    private int fileNo;

    @ApiModelProperty(value = "주요 공급 업체코드")
    private String supplyVendorCd;

    @ApiModelProperty(value = "주요 공급 업체명")
    private String supplyVendorNm;

    @ApiModelProperty(value = "작성자")
    private String writerUserNm;

    @ApiModelProperty(value = "작성일")
    private String writerDt;
}
