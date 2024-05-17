package com.she.security.model;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "인증요청 파라미터 정보")
public class AuthRequestInfoParam implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "서비스요청클라이언트아이피")
    private String clientIP;

    @ApiModelProperty(value = "서비스요청서비스")
    private String connService;

    @ApiModelProperty(value = "아이피중복체크제외할 아이피1")
    private String passIp;

    @ApiModelProperty(value = "아이피중복체크제외할 아이피2")
    private String passIp2;

    @ApiModelProperty(value = "강제로 아이피 중복체크옵션")
    private Boolean force;

    @ApiModelProperty(value = "모바일에서 호출된 것인지 여부")
    private Boolean mobile;

    public static class Builder {
        private String clientIP = "";
        private String connService = "";
        private String passIp = "";
        private String passIp2 = "";
        private Boolean force = false;
        private Boolean mobile = false;

        public Builder() {
        }

        public Builder setClientIP(String clientIP) {
            this.clientIP = clientIP;
            return this;
        }

        public Builder setConnService(String connService) {
            this.connService = connService;
            return this;
        }

        public Builder setPassIp(String passIp) {
            this.passIp = passIp;
            return this;
        }

        public Builder setPassIp2(String passIp2) {
            this.passIp2 = passIp2;
            return this;
        }

        public Builder setForce(Boolean force) {
            this.force = force;
            return this;
        }

        public Builder setMobile(Boolean mobile) {
            this.mobile = mobile;
            return this;
        }

        public AuthRequestInfoParam build() {
            return new AuthRequestInfoParam(this);
        }

    }

    private AuthRequestInfoParam(Builder builder) {
        this.clientIP = builder.clientIP;
        this.connService = builder.connService;
        this.passIp = builder.passIp;
        this.passIp2 = builder.passIp2;
        this.force = builder.force;
        this.mobile = builder.mobile;
    }

    //
    //
    // public AuthRequestInfoParam(String clientIP, String connService, String
    // passIp, String passIp2, Boolean force, Boolean mobile) {
    // super();
    // this.clientIP = clientIP;
    // this.connService = connService;
    // this.passIp = passIp;
    // this.passIp2 = passIp2;
    // this.force = force;
    // this.mobile = mobile;
    // }
    //
    // public AuthRequestInfoParam(String clientIP, String connService) {
    // super();
    // this.clientIP = clientIP;
    // this.connService = connService;
    // this.force = false;
    // this.mobile = false;
    // }
    //
    // public AuthRequestInfoParam(String clientIP, String connService, String
    // passIp, String passIp2, Boolean force, Boolean mobile) {
    // super();
    // this.clientIP = clientIP;
    // this.connService = connService;
    // this.passIp = passIp;
    // this.passIp2 = passIp2;
    // this.force = (force == null ? false : force);
    // this.mobile = (mobile == null ? false : mobile);
    // }

    public String getClientIP() {
        return clientIP == null ? "" : clientIP;
    }

    public String getConnService() {
        return connService == null ? "" : connService;
    }

    public String getPassIp() {
        return passIp == null ? "" : passIp;
    }

    public Boolean getForce() {
        return force == null ? false : force;
    }

    public Boolean getMobile() {
        return mobile == null ? false : mobile;
    }

    public String getPassIp2() {
        return passIp2;
    }

    @Override
    public String toString() {
        return "AuthRequestInfoParam [clientIP= " + clientIP + ", connService= " + connService + ", passIp= " + passIp + ", passIp2= " + passIp2 + ", force= " + force + ", mobile= " + mobile + "]";
    }

}