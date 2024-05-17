package com.she.board.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "게시글")
@Data
public class Bbs {
    @ApiModelProperty(value = "게시판NO")
    private int bbsNo;
    @ApiModelProperty(value = "게시물번호")
    private int notiNo;
    @ApiModelProperty(value = "게시물제목")
    private String notiTtl;
    @ApiModelProperty(value = "게시물내용")
    private String notiCont;
    @ApiModelProperty(value = "게시자명")
    private String notierName;
    @ApiModelProperty(value = "댓글여부")
    private String replyYn;
    @ApiModelProperty(value = "조회수")
    private String searchCnt;
    @ApiModelProperty(value = "삭제여부")
    private String useYn;
    @ApiModelProperty(value = "공지여부")
    private String notiYn;
    @ApiModelProperty(value = "공지시작일")
    private String notiBeginDt;
    @ApiModelProperty(value = "공지종료일")
    private String notiEndDt;
    @ApiModelProperty(value = "등록자")
    private String createUserId;
    @ApiModelProperty(value = "등록일")
    private String createDt;
    @ApiModelProperty(value = "수정자")
    private String updateUserId;
    @ApiModelProperty(value = "수정일")
    private String updateDt;
    @ApiModelProperty(value = "상위게시물NO")
    private int parentNotiNo;
    @ApiModelProperty(value = "게시물 순서(상위게시물+1)")
    private String notiLevel;
    @ApiModelProperty(value = "사업장번호")
    private String plantCd;
    private String lookYn;
    private String lookFromDate;
    private String lookToDate;

}
