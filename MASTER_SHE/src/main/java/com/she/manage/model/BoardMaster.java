package com.she.manage.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "게시판마스터")
@Data
public class BoardMaster {
    @ApiModelProperty(value = "게시판NO")
    private int bbsNo;
    @ApiModelProperty(value = "게시판명")
    private String bbsNm;
    @ApiModelProperty(value = "게시판소개")
    private String bbsInstr;
    @ApiModelProperty(value = "댓글가능여부")
    private String replyPosbYn;
    @ApiModelProperty(value = "양식ID")
    private String tmplId;
    @ApiModelProperty(value = "사용여부")
    private String useYn;
    @ApiModelProperty(value = "등록자")
    private String createUserId;
    @ApiModelProperty(value = "등록일")
    private String createDt;
    @ApiModelProperty(value = "수정자")
    private String updateUserId;
    @ApiModelProperty(value = "수정일")
    private String updateDt;
    @ApiModelProperty(value = "답글작성여부")
    private String answerPosbYn;
    @ApiModelProperty(value = "열람권한설정여부")
    private String readAuthPosbYn;
    @ApiModelProperty(value = "첨부파일 설정 코드(com_attach_")
    private String taskClassNm;
    @ApiModelProperty(value = "작성자")
    private String writerUserNm;
    @ApiModelProperty(value = "작성일")
    private String writerDt;
}
