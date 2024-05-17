/**
 * Copyright (C) 2019, 2019 All Right Reserved, http://www.yullin.com/
 *
 * SHE Business can not be copied and/or distributed without the express
 * permission of Yullin Technologies
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 */

package com.she.security.controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.ValidationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nets.sso.agent.authcheck.AuthCheck;
import com.nets.sso.common.AgentExceptionCode;
import com.nets.sso.common.Utility;
import com.nets.sso.common.enums.AuthStatus;
import com.she.config.JwtSettings;
import com.she.security.auth.jwt.extractor.TokenExtractor;
import com.she.security.exception.ExceptionCode;
import com.she.security.exception.RestAuthException;
import com.she.security.model.AuthRequestInfoParam;
import com.she.security.model.RequestAuthentication;
import com.she.security.model.RestOkResponse;
import com.she.security.model.token.JwtJoseToken;
import com.she.security.model.token.TokenResponse;
import com.she.security.service.JoseTokenService;
import com.she.security.service.LoginLogServiceImpl;
import com.she.security.util.AuthUtil;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 인증처리 컨트롤러
 *
 */
@RestController
@RequestMapping("api/auth")
public class AuthenticationController {

    // @SuppressWarnings("unused")
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    @Qualifier("jwtHeaderTokenExtractor")
    private TokenExtractor tokenExtractor;

    @Autowired
    private JwtSettings jwtSettings;

    @Autowired
    private JoseTokenService joseTokenService;

    @Autowired
    private LoginLogServiceImpl loginLogServiceImpl;

    private static final String PADDING_FOUR = "&nbsp;&nbsp;&nbsp;&nbsp;";
    private static final String STR_ENTER = " \n ";

    private static final String LOGIN_TENANT_INFO = "로그인 JSON 예제 " + STR_ENTER + "{ " + STR_ENTER + PADDING_FOUR + "\"userId\": \"dev\", " + STR_ENTER + PADDING_FOUR + "\"userPwdSHA\": \"admin\", " + STR_ENTER + PADDING_FOUR + "\"force\": false, " + STR_ENTER + PADDING_FOUR + "\"mobile\": false " + STR_ENTER + "} ";

    @ApiOperation(httpMethod = "POST", value = "sso 처리", notes = LOGIN_TENANT_INFO, response = JwtJoseToken.class, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({})
    @PostMapping("/sso")
    public ResponseEntity<JwtJoseToken> sso(HttpServletRequest request, HttpServletResponse response) throws RestAuthException {
        AuthRequestInfoParam authParam = new AuthRequestInfoParam.Builder().setClientIP(AuthUtil.getClientIpAddress(request, request.getHeader(jwtSettings.getLocalIp()))).setConnService(request.getRequestURL().toString().toUpperCase()).setPassIp(jwtSettings.getPassip()).setPassIp2(jwtSettings.getPassip2()).setForce(true)
                .setMobile(AuthUtil.isMobile(request)).build();

        String logonId = ""; // 로그온 된 사용자 아이디
        String userInfo = ""; // 로그온 된 사용자 추가정보
        String scriptMsg = ""; // 에러 메시지
        AuthStatus status = null;

        // if (request.getCookies() != null && request.getCookies().length > 0)
        // {
        // for (int i = 0; i < request.getCookies().length; i++) {
        // LOGGER.info("#### cookie name" + i + " : " +
        // request.getCookies()[i].getName());
        // LOGGER.info("#### cookie value" + i + " : " +
        // request.getCookies()[i].getValue());
        // }
        // }

        // sso
        try {
            System.setProperty("sso.config.file", "E:\\server\\SHE_BACKEND\\webapps\\ROOT\\WEB-INF\\classes\\agentconfig.xml");
            // System.setProperty("sso.config.file",
            // "C:\\project\\풍산SHE\\source\\backend\\SHE_BACKEND\\src\\main\\webapp\\WEB-INF\\classes\\agentconfig.xml");
            // LOGGER.info("#### provider 전");
            // SSOProvider provider =
            // SSOConfig.getInstance().getCurrentSSOProvider(request.getServerName());
            // LOGGER.info("#### provider 후");
            // LOGGER.info("#### site 전");
            // SSOSite site =
            // SSOConfig.getInstance().getCurrentSSOSite(request.getServerName());
            // LOGGER.info("#### site 후");

            // LOGGER.info("#### 빌드번호 : " + provider.getPolicyVer());
            // LOGGER.info("#### 사용자 ID 입력 태그명 : " +
            // provider.getParamName(com.nets.sso.agent.AuthUtil.ParamInfo.USER_ID));
            // LOGGER.info("#### 비빌번호 입력 태그명 : " +
            // provider.getParamName(com.nets.sso.agent.AuthUtil.ParamInfo.USER_PW));
            // LOGGER.info("#### 자격증명 종류 태그명 : " +
            // provider.getParamName(com.nets.sso.agent.AuthUtil.ParamInfo.CRED_TYPE));
            // LOGGER.info("#### 리턴 URL 태그명 : " +
            // provider.getParamName(com.nets.sso.agent.AuthUtil.ParamInfo.RETURN_URL));
            // LOGGER.info("#### 로그온 요청 URL : " + provider.getLogonUrl(request,
            // site.getSiteDNS()));
            // LOGGER.info("#### 로그오프 요청 URL : " +
            // provider.getLogoffUrl(request, site.getSiteDNS()));
            // LOGGER.info("#### 인증 검사 요청 URL : " +
            // provider.getAuthCheckUrl(request, site.getSiteDNS()));
            // LOGGER.info("#### 중앙인증 도메인 : " + provider.getProviderDomain());
            // LOGGER.info("#### 인증요청 IP 검사 여부 : " +
            // provider.getClientIPCheck());
            // LOGGER.info("#### 인증 유효 기간(idle-timeout) : " +
            // provider.getIdleTimeout());
            // LOGGER.info("#### 체크 주기(idle-timeout) : " +
            // provider.getIdleTimeoutInterval());
            // LOGGER.info("#### 인증 만료 시간 사용 여부 : " +
            // provider.getTokenExpiredYN());
            // LOGGER.info("#### 인증 만료 시간 : " +
            // provider.getTokenExpiredTimeout());
            // LOGGER.info("#### 중복 로그온 방지 정책 사용여부 : " +
            // provider.getDuplicationYN());
            // LOGGER.info("#### 세션 재설정 시간 : " +
            // provider.getDuplicationCheckIntervalMinutes());
            //
            // LOGGER.info("#### SSO 도메인 : " + site.getSsoDomain());
            // LOGGER.info("#### SSO 참여 여부 : " + site.getSsoYN());
            // LOGGER.info("#### SSO 도메인 인증 쿠키 : " +
            // site.getTokenConfig().getName());
            // LOGGER.info("#### SSO 참여 여부 : " + site.getSsoYN());
            // LOGGER.info("#### SSO 사용자정보 쿠키 : " +
            // site.getAttributeConfig().getName());
            //
            // LOGGER.info("#### SSO 사이트명 : " + site.getSiteDomain());
            // LOGGER.info("#### HttpOnly : " +
            // site.getTokenConfig().getHttpOnly());
            // LOGGER.info("#### Secure : " +
            // site.getTokenConfig().getSecure());
            // LOGGER.info("#### SSO 도메인 쿠키목록");
            // if (site.getAttributeConfig().getAttributeNames() != null
            // && site.getAttributeConfig().getAttributeNames().length > 0) {
            // for (int i = 0; i <
            // site.getAttributeConfig().getAttributeNames().length; i++) {
            //
            // LOGGER.info("#### " + i + " 쿠키명 : " +
            // site.getAttributeConfig().getAttributeNames()[i]);
            // }
            // }

            // 인증객체생성 및 인증확인
            AuthCheck auth = new AuthCheck(request, response);
            // 인증확인
            status = auth.checkLogon();

            // 인증상태별 처리
            if (status == AuthStatus.SSOFirstAccess) {
                // 최초 접속
                auth.trySSO();
            } else if (status == AuthStatus.SSOSuccess) {
                // 인증성공
                // 로그인 아이디
                logonId = auth.getUserID();
                // 인증정보 모두 보기(화면에서 보고 싶을 때 주석을 제거 하세요)
                if (auth.getUserInfoCollection() != null && auth.getUserInfoCollection().size() > 0) {
                    // JDK 1.4
                    // for (Enumeration e = auth.getUserInfoCollection().keys();
                    // e.hasMoreElements(); ) {
                    // if (Utility.isNullOrEmpty(userInfo) == false)
                    // userInfo += "<br />";
                    // String key = (String) e.nextElement();
                    // userInfo += key + ":" +
                    // auth.getUserInfoCollection().get(key);
                    // }
                    // JDK 1.5
                    for (Enumeration<String> e = auth.getUserInfoCollection().keys(); e.hasMoreElements();) {
                        if (Utility.isNullOrEmpty(userInfo) == false) {
                            userInfo += "<br />";
                        }
                        String key = e.nextElement();
                        userInfo += key + ":" + auth.getUserInfoCollection().get(key);
                    }
                }
                // 사용자정보 조회 샘플
                String userinfo = auth.getUserInfo("AttributeName");
                // String somethingUserInfo = auth.getUserInfo("조회할이름"); //조회할
                // 이름은 프로젝트 시, 정해지면 전달해드립니다.
                // 선입자를 끊고, 내가 인증 성공했을 경우, 선입자의 정보를 보여준다.
                if (Utility.isNullOrEmpty(auth.getDuplicationIP()) == false) {
                    String dupInfo = "(끊어진 사용자정보)\\nIP:" + auth.getDuplicationIP() + "\\nTime:" + auth.getDuplicationTime();
                    scriptMsg = dupInfo;
                }

            } else if (status == AuthStatus.SSOFail) {
                // 인증실패
                if (auth.getErrCode() != AgentExceptionCode.NoException.getValue()) {
                    scriptMsg = String.valueOf(auth.getErrCode());
                }
                if (auth.getErrCode() == AgentExceptionCode.SessionDuplicationCheckedLastPriority.getValue()) {
                    scriptMsg = "중복로그인 정보 IP:" + auth.getDuplicationIP() + " TIME:" + auth.getDuplicationTime();
                }
                // 로그오프를 해야하는 상황
                if (auth.getErrCode() == AgentExceptionCode.SessionDuplicationCheckedLastPriority.getValue() || auth.getErrCode() == AgentExceptionCode.TokenIdleTimeout.getValue() || auth.getErrCode() == AgentExceptionCode.TokenExpired.getValue() || auth.getErrCode() == AgentExceptionCode.NoExistUserIDSessionValue.getValue()) {
                    scriptMsg += ""; // "OnLogoff();";
                } else {
                    scriptMsg += ""; // "goLogonPage();";
                }
                throw new RestAuthException(HttpStatus.UNAUTHORIZED, "SSO 인증에 실패하였습니다. " + scriptMsg, ExceptionCode.UNAUTHORIZED.name());
            } else if (status == AuthStatus.SSOUnAvailable) {
                // SSO장애
                scriptMsg = "현재 통합인증 서비스가 불가합니다.";
                throw new RestAuthException(HttpStatus.UNAUTHORIZED, "현재 통합인증 서비스가 불가합니다. SSO장애", ExceptionCode.UNAUTHORIZED.name());
            } else if (status == AuthStatus.SSOAccessDenied) {
                // 접근거부
                // response.sendRedirect("denied.jsp");
                throw new RestAuthException(HttpStatus.UNAUTHORIZED, "접근이 거부 되었습니다.", ExceptionCode.UNAUTHORIZED.name());
            }
        } catch (NullPointerException e) {
        	LOGGER.error(e.getMessage());
        } catch (Exception e) {
            // TODO: handle exception
        	LOGGER.error(e.getMessage());
            throw new RestAuthException(HttpStatus.UNAUTHORIZED, "관리자에게 문의바랍니다.", ExceptionCode.UNAUTHORIZED.name());
        }
        // catch (AgentException e) {
        // // TODO: handle exception
        // LOGGER.error("### SSO 처리 실패 " + e.toString());
        // throw new RestAuthException(HttpStatus.UNAUTHORIZED, "SSO 처리 실패",
        // ExceptionCode.UNAUTHORIZED.name());
        // }

        if (!"".equals(logonId) && logonId != null) {
            // 토큰 생성
            JwtJoseToken token = joseTokenService.makeSSOToken(logonId, authParam);
            return ResponseEntity.ok().body(token);
        } else {
            return ResponseEntity.ok().body(null);
        }

    }

    @ApiOperation(httpMethod = "POST", value = "로그인 인증처리 및 JWT생성", notes = LOGIN_TENANT_INFO, response = JwtJoseToken.class, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "account", value = "로그인인증요청정보", required = true, dataType = "RequestAuthentication", paramType = "body") })
    @PostMapping("/login")
    public ResponseEntity<JwtJoseToken> login(@ApiParam(name = "account", value = "", required = true) @RequestBody RequestAuthentication account, HttpServletRequest request, HttpServletResponse response) throws RestAuthException {
        
    	System.out.println("===account.getUserPwdSHA()===" + account.getUserPwdSHA());
    	
    	if (account.getUserId() == null || account.getUserPwdSHA() == null) {
            // 사용자 아이디와 암호를 입력하세요.
            throw new RestAuthException(HttpStatus.UNAUTHORIZED, "M0000000001", ExceptionCode.UNAUTHORIZED.name());
        }

        AuthRequestInfoParam authParam = new AuthRequestInfoParam.Builder().setClientIP(AuthUtil.getClientIpAddress(request, request.getHeader(jwtSettings.getLocalIp()))).setConnService(request.getRequestURL().toString().toUpperCase()).setPassIp(jwtSettings.getPassip()).setPassIp2(jwtSettings.getPassip2()).setForce(true)
                .setMobile(AuthUtil.isMobile(request)).build();

        // Google OTP
        // JwtJoseToken token = joseTokenService.makeToken(account.getUserId(),
        // account.getUserPwdSHA(), account.getUserOtpPass(), authParam);
        JwtJoseToken token = joseTokenService.makeToken(account.getUserId(), account.getUserPwdSHA(), authParam);
        return ResponseEntity.ok().body(token);
    }

    @ApiOperation(httpMethod = "POST", value = "협력업체 로그인 인증처리 및 JWT생성", notes = LOGIN_TENANT_INFO, response = JwtJoseToken.class, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "account", value = "로그인인증요청정보", required = true, dataType = "RequestAuthentication", paramType = "body") })
    @PostMapping("/vendorlogin")
    public ResponseEntity<JwtJoseToken> vendorLogin(@ApiParam(name = "account", value = "", required = true) @RequestBody RequestAuthentication account, HttpServletRequest request, HttpServletResponse response) throws RestAuthException {
        if (account.getUserId() == null || account.getUserPwdSHA() == null) {
            // 사용자 아이디와 암호를 입력하세요.
            throw new RestAuthException(HttpStatus.UNAUTHORIZED, "M0000000001", ExceptionCode.UNAUTHORIZED.name());
        }

        AuthRequestInfoParam authParam = new AuthRequestInfoParam.Builder().setClientIP(AuthUtil.getClientIpAddress(request, request.getHeader(jwtSettings.getLocalIp()))).setConnService(request.getRequestURL().toString().toUpperCase()).setPassIp(jwtSettings.getPassip()).setPassIp2(jwtSettings.getPassip2()).setForce(true)
                .setMobile(AuthUtil.isMobile(request)).build();

        JwtJoseToken token = joseTokenService.makeTokenForVendor(account.getUserId(), account.getUserPwdSHA(), authParam);
        return ResponseEntity.ok().body(token);
    }

    @ApiOperation(value = "토큰정보를 Clear하여 사용자 로그아웃처리. X-Authorization정보 사용중인 AccessToken전달.")
    @RequestMapping(value = "/logout", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, headers = "Accept=application/json;charset=UTF-8")
    public ResponseEntity<RestOkResponse> userLogOut(HttpServletRequest request) throws RestAuthException, ValidationException {

        String tokenPayload = tokenExtractor.extract(request.getHeader(jwtSettings.getTokenHeader()));

        AuthRequestInfoParam authParam = new AuthRequestInfoParam.Builder().setClientIP(AuthUtil.getClientIpAddress(request, request.getHeader(jwtSettings.getLocalIp()))).setConnService(request.getRequestURL().toString().toUpperCase()).build();

        Boolean isOk = joseTokenService.clearUserLoginInfo(tokenPayload, authParam);

        Map<String, String> returnResult = new HashMap<String, String>();
        returnResult.put("isOk", isOk ? "true" : "false");
        return new ResponseEntity<RestOkResponse>(new RestOkResponse(returnResult), HttpStatus.OK);
    }

    @ApiOperation(value = "JWT AccessToken 유효성검증")
    @ApiImplicitParams({ @ApiImplicitParam(name = "mobile", value = "모바일에서 접속한 서비스인지 여부 : 모바일인 경우 아이피가 유동적이기 때문에, " + "아이피 중복로그인체크를 하지 않기 위해서 사용. (ex.false)", required = false, dataType = "boolean", paramType = "query") })
    @RequestMapping(value = "/valid", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, headers = "Accept=application/json;charset=UTF-8")
    public ResponseEntity<TokenResponse> validateAccessToken(@RequestParam(value = "mobile", required = false) Boolean mobile, HttpServletRequest request) throws RestAuthException, ValidationException {

        String tokenPayload = tokenExtractor.extract(request.getHeader(jwtSettings.getTokenHeader()));

        AuthRequestInfoParam authParam = new AuthRequestInfoParam.Builder().setClientIP(AuthUtil.getClientIpAddress(request, request.getHeader(jwtSettings.getLocalIp()))).setConnService(request.getRequestURL().toString().toUpperCase()).setPassIp(jwtSettings.getPassip()).setPassIp2(jwtSettings.getPassip2()).setForce(false)
                .setMobile(AuthUtil.isMobile(request)).build();

        TokenResponse res = joseTokenService.validateToken(tokenPayload, authParam);
        return ResponseEntity.ok(res);
    }

    @ApiOperation(value = "JWT RefreshToken 발급요청 : 유지기간이 30분인 AccessToken이 만료되면 시스템은 자동으로 RefreshToken으로 AccessToken을 재발급 받을 수 있다. <br>" + "RefreshToken은 90분의 유지기간을 가지고 있기 때문에, 사용자가 90분 이상 사용하지 않은 경우에는 만료처리 된다. <br>" + "X-Authorization정보에 이미 발급받은 RefreshToken을 전달해주면 해당 토큰으로 AccessToken을 발급시킨다")
    @RequestMapping(value = "/refresh", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<JwtJoseToken> updateRefreshToken(HttpServletRequest request) throws RestAuthException, ValidationException {
        /**
         * Refresh Token으로 AccessToken을 발급한다. 만약, Refresh Token이 만료되었다면 409 오류를
         * 반환, 인증이 성공하면 AccessToken, RefreshToken(이전 RefreshToken)을 발급.
         */
        String tokenPayload = tokenExtractor.extract(request.getHeader(jwtSettings.getTokenHeader()));

        AuthRequestInfoParam authParam = new AuthRequestInfoParam.Builder().setClientIP(AuthUtil.getClientIpAddress(request, request.getHeader(jwtSettings.getLocalIp()))).setConnService(request.getRequestURL().toString().toUpperCase()).build();

        JwtJoseToken res = joseTokenService.issueRefreshToken(tokenPayload, authParam);
        return ResponseEntity.ok(res);
    }

    @PutMapping("/logout/{userId}")
    public void updateLoginLog(@PathVariable String userId) throws Exception {
        loginLogServiceImpl.updateLoginLog(userId);
        // return ResponseEntity.ok().body(count);
    }

}
