package com.she.security.model.token;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel(description = "토큰인증반환정보")
public class TokenResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "반환메시지")
    private String message;

    @ApiModelProperty(value = "사용자아이디")
    private String subject;

    @ApiModelProperty(value = "사용자ID")
    private String userid;

    @ApiModelProperty(value = "사용자명")
    private String dispname;

    @ApiModelProperty(value = "권한")
    private String scopes;

    @ApiModelProperty(value = "접속아이피")
    private String clientip;

    @ApiModelProperty(value = "토큰만료일시")
    private String expiredtime;

    protected TokenResponse() {

    }

    public TokenResponse(String message,
            String subject,
            String userid,
            String dispname,
            String scopes,
            String clientip,
            String expiredtime) {
        super();
        this.message = message;
        this.subject = subject;
        this.userid = userid;
        this.dispname = dispname;
        this.scopes = scopes;
        this.clientip = clientip;
        this.expiredtime = expiredtime;
    }

    public String getMessage() {
        return message;
    }

    public String getSubject() {
        return subject;
    }

    public String getDispname() {
        return dispname;
    }

    public String getScopes() {
        return scopes;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setDispname(String dispname) {
        this.dispname = dispname;
    }

    public void setScopes(String scopes) {
        this.scopes = scopes;
    }

    public String getClientip() {
        return clientip;
    }

    public String getExpiredtime() {
        return expiredtime;
    }

    public void setClientip(String clientip) {
        this.clientip = clientip;
    }

    public void setExpiredtime(String expiredtime) {
        this.expiredtime = expiredtime;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }


}
