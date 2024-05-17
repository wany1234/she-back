package com.she.manage.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "첨부파일 설정")
@Getter
@Setter
public class AttachSetting {

    @ApiModelProperty(value = "첨부파일구분명")
    private String taskClassNm;

    @ApiModelProperty(value = "첨부파일구분설명")
    private String taskClassDesc;

    @ApiModelProperty(value = "허용확장자")
    private String acceptExt;

    @ApiModelProperty(value = "업로드가능파일수")
    private int limitCnt;

    @ApiModelProperty(value = "업로드가능사이즈")
    private int limitSize;

    @ApiModelProperty(value = "미리보기여부")
    private String previewYn;

    @ApiModelProperty(value = "미리보기여부명")
    private String previewYnNm;

    @ApiModelProperty(value = "암호화여부")
    private String encryptYn;

    @ApiModelProperty(value = "암호화여부명")
    private String encryptYnNm;

    @ApiModelProperty(value = "다중선택가능여부")
    private String multipleSelYn;

    @ApiModelProperty(value = "다중선택가능여부명")
    private String multipleSelYnNm;

    @ApiModelProperty(value = "Drag&Drop 가능여부")
    private String dragDropYn;

    @ApiModelProperty(value = "Drag&Drop 가능여부명")
    private String dragDropYnNm;

    @ApiModelProperty(value = "설명작성가능여부")
    private String pictureExplainYn;

    @ApiModelProperty(value = "설명작성가능여부명")
    private String pictureExplainYnNm;

    @ApiModelProperty(value = "사용여부")
    private String useYn;

    @ApiModelProperty(value = "사용여부명")
    private String useYnNm;

    @ApiModelProperty(value = "등록자아이디")
    private String createUserId;

    @ApiModelProperty(value = "등록자명")
    private String createUserNm;

    @ApiModelProperty(value = "등록일")
    private String createDt;

    @ApiModelProperty(value = "수정자아이디")
    private String updateUserId;

    @ApiModelProperty(value = "수정자명")
    private String updateUserNm;

    @ApiModelProperty(value = "수정일")
    private String updateDt;

    @ApiModelProperty(value = "작성자")
    private String writerUserNm;

    @ApiModelProperty(value = "작성일")
    private String writerDt;
}
