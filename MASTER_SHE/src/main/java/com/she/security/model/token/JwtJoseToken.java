package com.she.security.model.token;

import java.io.Serializable;

public class JwtJoseToken implements Serializable {

    private static final long serialVersionUID = 1L;
    private String userId;
    private String accessToken;
    private String refreshToken;
    private String otpYn;
    private String otpSecKey;
    private String otpUrl;

    protected JwtJoseToken() {

    }

    public JwtJoseToken(String userId, String accessToken, String refreshToken) {
        super();
        this.userId = userId;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.otpYn = "N";
    }

    public JwtJoseToken(String userId, String accessToken, String refreshToken, String otpYn, String otpSecKey, String otpUrl) {
        super();
        this.userId = userId;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.otpYn = otpYn;
        this.otpSecKey = otpSecKey;
        this.otpUrl = otpUrl;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getOtpYn() {
        return otpYn;
    }

    public void setOtpYn(String otpYn) {
        this.otpYn = otpYn;
    }

    public String getOtpSecKey() {
        return otpSecKey;
    }

    public void setOtpSecKey(String otpSecKey) {
        this.otpSecKey = otpSecKey;
    }

    public String getOtpUrl() {
        return otpUrl;
    }

    public void setOtpUrl(String otpUrl) {
        this.otpUrl = otpUrl;
    }

}
