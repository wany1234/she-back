package com.she.security.model;

import com.she.security.util.DateUtil;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 *
 * @클래스명 : LoginLog.java
 * @설명 : 테넌트별 로그인 로그
 * @작성일 : 2018
 * @작성자 : 열린기술 (김유경)
 * @변경이력 :
 */
@ApiModel(description = "테넌트별 로그인 로그")
public class LoginLog implements java.io.Serializable {

    private static final long serialVersionUID = 8251970890786802045L;

    @ApiModelProperty(value = "접속로그PK")
    private Long loginLogNo;

    @ApiModelProperty(value = "로그인유형")
    private String loginType;

    @ApiModelProperty(value = "연결사용자ID")
    private String connUserId;

    @ApiModelProperty(value = "연결사용자명")
    private String connUserNm;

    @ApiModelProperty(value = "연결토큰정보")
    private String connUserToken;

    @ApiModelProperty(value = "오류발생여부")
    private boolean connErrorYn = false;

    @ApiModelProperty(value = "오류내용")
    private String connErrorMsg;

    @ApiModelProperty(value = "접속아이피주소")
    private String connIp;

    @ApiModelProperty(value = "생성일시")
    private String insertDt;

    @ApiModelProperty(value = "로그아웃일시")
    private String logoutDt;

    @ApiModelProperty(value = "사업장명")
    private String plantNm;

    @ApiModelProperty(value = " 부서명")
    private String deptNm;

    public LoginLog() {
    }

    public LoginLog(String loginType, boolean connErrorYn, String insertDt) {
        this.loginType = loginType;
        this.connErrorYn = connErrorYn;
        this.insertDt = insertDt;
    }

    public LoginLog(String loginType, String connUserId, String connUserNm, String connUserToken, boolean connErrorYn, String connErrorMsg, String connIp, String insertDt) {
        this.loginType = loginType;
        this.connUserId = connUserId;
        this.connUserNm = connUserNm;
        this.connUserToken = connUserToken;
        this.connErrorYn = connErrorYn;
        this.connErrorMsg = connErrorMsg;
        this.connIp = connIp;
        this.insertDt = insertDt;
    }

    public LoginLog(String loginType, String connUserId, String connUserNm, String connUserToken, boolean connErrorYn, String connErrorMsg, String connIp) {
        this.loginType = loginType;
        this.connUserId = connUserId;
        this.connUserNm = connUserNm;
        this.connUserToken = connUserToken;
        this.connErrorYn = connErrorYn;
        this.connErrorMsg = connErrorMsg;
        this.connIp = connIp;
        this.insertDt = DateUtil.currentTimeStamp().toString();
    }

    public Long getLoginLogNo() {
        return this.loginLogNo;
    }

    public void setLoginLogNo(Long loginLogNo) {
        this.loginLogNo = loginLogNo;
    }

    public String getLoginType() {
        return this.loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    public String getConnUserId() {
        return connUserId;
    }

    public void setConnUserId(String connUserId) {
        this.connUserId = connUserId;
    }

    public String getConnUserNm() {
        return this.connUserNm;
    }

    public void setConnUserNm(String connUserNm) {
        this.connUserNm = connUserNm;
    }

    public String getConnUserToken() {
        return this.connUserToken;
    }

    public void setConnUserToken(String connUserToken) {
        this.connUserToken = connUserToken;
    }

    public String getConnErrorMsg() {
        return this.connErrorMsg;
    }

    public void setConnErrorMsg(String connErrorMsg) {
        this.connErrorMsg = connErrorMsg;
    }

    public String getConnIp() {
        return this.connIp;
    }

    public void setConnIp(String connIp) {
        this.connIp = connIp;
    }

    public String getInsertDt() {
        return this.insertDt;
    }

    public void setInsertDt(String insertDt) {
        this.insertDt = insertDt;
    }

    public String getLogoutDt() {
        return this.insertDt;
    }

    public void setLogoutDt(String logoutDt) {
        this.logoutDt = logoutDt;
    }

    public boolean isConnErrorYn() {
        return connErrorYn;
    }

    public void setConnErrorYn(boolean connErrorYn) {
        this.connErrorYn = connErrorYn;
    }

    public String getDeptNm() {
        return deptNm;
    }

    public void setDeptNm(String deptNm) {
        this.deptNm = deptNm;
    }

    public String getPlantNm() {
        return plantNm;
    }

    public void setPlantNm(String plantNm) {
        this.plantNm = plantNm;
    }

}