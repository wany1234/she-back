package com.she.mgt.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MgtLawMaking {
    @ApiModelProperty(value = "입법예고 번호")
    private int seqNo;

    @ApiModelProperty(value = "입법예고 일련번호")
    private String ogLmPpSeq;

    @ApiModelProperty(value = "입법예고명")
    private String isNm;

    @ApiModelProperty(value = "법령종류")
    private String isClsNm;

    @ApiModelProperty(value = "소관부처코드")
    private String mgrGovCd;

    @ApiModelProperty(value = "소관부처명")
    private String asndOfiNm;

    @ApiModelProperty(value = "공고번호")
    private String pntcNo;

    @ApiModelProperty(value = "공고일자")
    private int pntcDt;

    @ApiModelProperty(value = "시작일자")
    private String stYd;

    @ApiModelProperty(value = "종료일자")
    private String edYd;

    @ApiModelProperty(value = "파일명")
    private String fileName;

    @ApiModelProperty(value = "파일다운로드 링크")
    private String fileDownLink;

    @ApiModelProperty(value = "조회수")
    private int readCnt;

    @ApiModelProperty(value = "법안매핑번호")
    private String mappingLbicId;

    @ApiModelProperty(value = "공고종류")
    private String announceType;

    @ApiModelProperty(value = "입법예고상세URL")
    private String detailUrl;

    @ApiModelProperty(value = "등록일")
    private String regDt;

    @ApiModelProperty(value = "수정일")
    private String udtDt;

    @ApiModelProperty(value = "예고여부")
    private String notiYn;

    @ApiModelProperty(value = "임시변수")
    private String temp;

}
