package com.she.security.model;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 *
 * @클래스명 : RequestAuthentication.java
 * @설명 : 로그인인증요청정보
 * @작성일 : 2019
 * @작성자 : 열린기술 (김유경)
 * @변경이력 :
 */
@ApiModel(description = "로그인인증요청정보")
public class RequestAuthentication implements Serializable {

    private static final long serialVersionUID = 3484701944527809610L;

    @ApiModelProperty(value = "로그인아이디")
    private String userId;

    @ApiModelProperty(value = "로그인암호")
    private String userPwdSHA;

    @ApiModelProperty(value = "OTP암호")
    private String userOtpPass;

    @ApiModelProperty(value = "인증을강제로처리하는지여부 (default. false)")
    private Boolean force;

    @ApiModelProperty(value = "모바일에서접속했는지여부 (default. false)")
    private Boolean mobile;

    public RequestAuthentication() {

    }

    public RequestAuthentication(String userId, String userPwdSHA, Boolean force, Boolean mobile) {
        super();
        this.userId = userId;
        this.userPwdSHA = userPwdSHA;
        this.force = (force == null ? false : force);
        this.mobile = (mobile == null ? false : mobile);
    }

    /**
     * Google OTP
     * 
     * @param userId
     * @param userPwdSHA
     * @param userOtpPass
     * @param force
     * @param mobile
     */
    public RequestAuthentication(String userId, String userPwdSHA, String userOtpPass, Boolean force, Boolean mobile) {
        super();
        this.userId = userId;
        this.userPwdSHA = userPwdSHA;
        this.userOtpPass = userOtpPass;
        this.force = (force == null ? false : force);
        this.mobile = (mobile == null ? false : mobile);
    }

    public String getUserId() {
        return userId;
    }

    public String getUserPwdSHA() {
        return userPwdSHA;
    }

    public String getUserOtpPass() {
        return userOtpPass;
    }

    public Boolean getForce() {
        return force;
    }

    public Boolean getMobile() {
        return mobile;
    }

    @Override
    public String toString() {
        return "RequestAuthentication [userId= " + userId + ", userPwdSHA= " + userPwdSHA + ", force= " + force + ", mobile= " + mobile + "]";
    }

}
