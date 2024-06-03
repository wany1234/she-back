package com.she.utils.model;

import java.util.List;

import com.she.manage.model.MailLog;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "메일")
@Data
public class MailVo {

	@ApiModelProperty(value = "메일 제목")
    private String title;

    @ApiModelProperty(value = "메일 본문 제목")
    private String mailTitle;

    @ApiModelProperty(value = "메일 내용")
    private String contents;

    @ApiModelProperty(value = "수신자 사람 이름")
    private String receiver;

    @ApiModelProperty(value = "수신자 이메일 주소")
    private String[] recipientsEmailAddress;

    @ApiModelProperty(value = "보내는 사람 이름")
    private String sender;

    @ApiModelProperty(value = "보내는 사람 이메일")
    private String senderEmail;

    @ApiModelProperty(value = "메일로그")
    private List<MailLog> mailLogs;

    @ApiModelProperty(value = "SHE 링크")
    private String link;
}
