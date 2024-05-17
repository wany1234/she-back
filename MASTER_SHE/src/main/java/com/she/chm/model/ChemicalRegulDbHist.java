package com.she.chm.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "규제DB업로드이력관리")
@Getter
@Setter
public class ChemicalRegulDbHist {

    @ApiModelProperty(value = "규제DB업로드코드(YYYY-MM-DD-HH24-MI-SS")
    private String chmRegulDbCd;

    @ApiModelProperty(value = "업로드일시")
    private String uploadDt;

    @ApiModelProperty(value = "업로드처리자아이디")
    private String uploadUserId;

    @ApiModelProperty(value = "업로드처리자명")
    private String uploadUserNm;

    @ApiModelProperty(value = "적용일시")
    private String applyDt;

    @ApiModelProperty(value = "적용처리자아이디")
    private String applyUserId;

    @ApiModelProperty(value = "적용처리자명")
    private String applyUserNm;

    @ApiModelProperty(value = "규제DB업로드처리상태")
    private String uploadStatus;

    @ApiModelProperty(value = "규제DB업로드처리상태명")
    private String uploadStatusNm;

    @ApiModelProperty(value = "삭제여부")
    private String delYn;

    @ApiModelProperty(value = "규제DB업로드최종확인일시")
    private String confirmDt;

    @ApiModelProperty(value = "업로드한 파일번호")
    private Long fileNo;

    @ApiModelProperty(value = "오류가없는업로드여부")
    private String successYn;

    @ApiModelProperty(value = "총 업로드데이터 건수")
    private Integer totCount;

    @ApiModelProperty(value = "변경이 없는 데이터건수")
    private Integer noCount;

    @ApiModelProperty(value = "추가할 데이터 건수")
    private Integer addCount;

    @ApiModelProperty(value = "수정할 데이터 건수")
    private Integer editCount;

    @ApiModelProperty(value = "삭제할 데이터 건수")
    private Integer deleteCount;

    @ApiModelProperty(value = "마지막오류메시지")
    private String lastErr;

    @ApiModelProperty(value = "마지막메시지")
    private String lastMsg;
}
