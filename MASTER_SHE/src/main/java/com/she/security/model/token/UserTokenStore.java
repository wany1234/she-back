package com.she.security.model.token;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 *
 * @클래스명 : UserTokenStore.java
 * @설명 : 사용자 토큰정보 저장소
 * @작성일 : 2018
 * @작성자 : 열린기술 (김유경)
 * @변경이력 :
 */
@ApiModel(description = "사용자 토큰정보 저장소")
public class UserTokenStore implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private Long userTokenStoreNo;
    private String userId;
    private String refreshToken;
    private String accessToken;
    private Date updateDt;
    private String ipAddr;
    private String expiredTime;

    public UserTokenStore() {
    }

    public UserTokenStore(String userId, String refreshToken, String accessToken, String ipAddr, String expiredTime,
            Date updateDt) {
        this.userId = userId;
        this.refreshToken = refreshToken;
        this.accessToken = accessToken;
        this.ipAddr = ipAddr;
        this.expiredTime = expiredTime;
        this.updateDt = updateDt;
    }

    public UserTokenStore(String userId, String refreshToken, String accessToken, String ipAddr, String expiredTime) {
        super();
        this.userId = userId;
        this.refreshToken = refreshToken;
        this.accessToken = accessToken;
        this.ipAddr = ipAddr;
        this.expiredTime = expiredTime;
        this.updateDt = new Date();
    }

    @ApiModelProperty(value = "ID")
    public Long getUserTokenStoreNo() {
        return this.userTokenStoreNo;
    }

    public void setUserTokenStoreNo(Long userTokenStoreNo) {
        this.userTokenStoreNo = userTokenStoreNo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @ApiModelProperty(value = "refresh_token")
    public String getRefreshToken() {
        return this.refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    @ApiModelProperty(value = "access_token")
    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @ApiModelProperty(value = "마지막변경일시")
    public Date getUpdateDt() {
        return this.updateDt;
    }

    public void setUpdateDt(Date updateDt) {
        this.updateDt = updateDt;
    }

    @ApiModelProperty(value = "접속IP")
    public String getIpAddr() {
        return ipAddr;
    }

    @ApiModelProperty(value = "토큰만료일시")
    public String getExpiredTime() {
        return expiredTime;
    }

    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }

    public void setExpiredTime(String expiredTime) {
        this.expiredTime = expiredTime;
    }

    public void updateInfo(UserTokenStore entity) {
        this.refreshToken = entity.getRefreshToken();
        this.accessToken = entity.getAccessToken();
        this.ipAddr = entity.getIpAddr();
        this.expiredTime = entity.getExpiredTime();
    }

    @Override
    public String toString() {
        return "UserTokenStore [userTokenStoreNo= " + userTokenStoreNo + ", userId= " + userId + ", refreshToken= "
                + refreshToken + ", accessToken= " + accessToken + ", updateDt= " + updateDt + ", ipAddr= " + ipAddr
                + ", expiredTime= " + expiredTime + "]";
    }

}