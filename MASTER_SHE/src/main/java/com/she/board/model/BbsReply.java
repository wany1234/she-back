package com.she.board.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "게시물 댓글")
@Data
public class BbsReply {
    @ApiModelProperty(value = "게시물NO")
    private int notiId;
    @ApiModelProperty(value = "댓글번호")
    private int replyNo;
    @ApiModelProperty(value = "댓글 게시자명")
    private String wrierName;
    @ApiModelProperty(value = "댓글내용")
    private String replyCont;
    @ApiModelProperty(value = "삭제여부")
    private String useYn;
    @ApiModelProperty(value = "등록자")
    private String createUserId;
    @ApiModelProperty(value = "등록일")
    private String createDt;
    @ApiModelProperty(value = "수정자")
    private String updateUserId;
    @ApiModelProperty(value = "수정일")
    private String updateDt;
}
