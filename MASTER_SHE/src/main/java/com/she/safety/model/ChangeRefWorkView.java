package com.she.safety.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "변경관리관련업무뷰")
@Getter
@Setter
public class ChangeRefWorkView {
    @ApiModelProperty(value = "관련업무분류코드")
    private String typeCd;
    @ApiModelProperty(value = "사업장코드")
    private String plantCd;
    @ApiModelProperty(value = "사업장명")
    private String plantNm;
    @ApiModelProperty(value = "분류명")
    private String clsNm;
    @ApiModelProperty(value = "분류코드")
    private String clsCd;
    @ApiModelProperty(value = "관련테이블키")
    private String refTableId;
    @ApiModelProperty(value = "관련제목명")
    private String refWorkNm;
    @ApiModelProperty(value = "관련테이블명")
    private String refTableNm;
    @ApiModelProperty(value = "관련등록수정일")
    private String refWorkDt;
    @ApiModelProperty(value = "진행코드")
    private String stepCd;
    @ApiModelProperty(value = "진행상태")
    private String stepNm;
    @ApiModelProperty(value = "파이프 코드")
    private String pipeCd;
    @ApiModelProperty(value = "공정코드")
    private String processCd;
    @ApiModelProperty(value = "도면번호")
    private String diagramNo;
    @ApiModelProperty(value = "도면그룹번호")
    private String diagGrpNo;
    @ApiModelProperty(value = "도면명")
    private String diagTitle;
    @ApiModelProperty(value = "도명종류")
    private String diagKindNo;
    @ApiModelProperty(value = "도면종류코드")
    private String diagClassCd;
    @ApiModelProperty(value = "도면첨부파일코드")
    private String taskClassNm;
}
