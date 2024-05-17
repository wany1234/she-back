package com.she.security.service;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.ValidationException;

import org.apache.commons.codec.binary.Base32;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.nimbusds.jwt.JWTClaimsSet;
import com.she.config.JwtSettings;
import com.she.manage.model.User;
import com.she.manage.model.VendorUser;
import com.she.manage.service.UserService;
import com.she.security.auth.UserContext;
import com.she.security.auth.jwt.extractor.TokenExtractor;
import com.she.security.exception.ExceptionCode;
import com.she.security.exception.RestAuthException;
import com.she.security.model.AuthRequestInfoParam;
import com.she.security.model.LoginLog;
import com.she.security.model.LoginLogType;
import com.she.security.model.token.JwtJoseToken;
import com.she.security.model.token.JwtToken;
import com.she.security.model.token.JwtTokenFactory;
import com.she.security.model.token.RawAccessJoseJwtToken;
import com.she.security.model.token.RawRefreshJoseJwtToken;
import com.she.security.model.token.TokenResponse;
import com.she.security.model.token.UserTokenStore;
import com.she.security.util.DateFormat;
import com.she.security.util.DateFormatConverter;
import com.she.security.util.DateUtil;
import com.she.security.util.StringUtil;
import com.she.security.util.UserContextName;

/**
 *
 * @클래스명 : JoseTokenService.java
 * @설명 : 토큰처리서비스
 * @작성일 : 2018
 * @작성자 : 열린기술 (김유경)
 * @변경이력 :
 */
@Service
public class JoseTokenService {

	private static final Logger logger = LoggerFactory.getLogger(JoseTokenService.class);

	@Autowired
	private JwtSettings jwtSettings;

	@Autowired
	private JwtTokenFactory tokenFactory;

	@Autowired
	@Qualifier("jwtHeaderTokenExtractor")
	private TokenExtractor tokenExtractor;

	@Autowired
	private UserService userService;

	@Autowired
	private UserTokenStoreService userTokenStoreService;

	@Autowired
	private LoginLogService loginLogService;

	@Autowired
	private Environment environment;

	/**
	 * 토큰생성
	 *
	 * @param userId
	 * @param userPwd
	 * @return
	 * @throws RestAuthException
	 */
	public JwtJoseToken makeSSOToken(String userId, AuthRequestInfoParam reqInfo) throws RestAuthException {

		User user = null;
		try {
			user = userService.getUser(userId);
		} catch (IOException ex) {
			logger.error(ex.getMessage());
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			throw new RestAuthException(HttpStatus.UNAUTHORIZED, "사용자 인증에 실패했습니다. 사용자를 찾을 수 없습니다.",
					ExceptionCode.UNAUTHORIZED.name());
		}

		if (user == null) {
			throw new RestAuthException(HttpStatus.UNAUTHORIZED, "사용자 인증에 실패했습니다. 사용자를 찾을 수 없습니다.",
					ExceptionCode.UNAUTHORIZED.name());
		}

		// String sha256hex = DigestUtils.sha256Hex(userPwd);
		// if (!sha256hex.equalsIgnoreCase(user.getUserPwd())) {
		// throw new RestAuthException(HttpStatus.UNAUTHORIZED, "사용자 인증에 실패했습니다.
		// 비밀번호를 다시 확인하세요.", "WRONG_PASSWORD");
		// }

		// if (user.getUserAuthGroups() == null) throw new
		// InsufficientAuthenticationException("사용자의 권한이 없습니다.");

		DateFormatConverter conv = new DateFormatConverter();
		LocalDateTime currentTime = LocalDateTime.now();
		Date sysDate = Date.from(currentTime.plusMinutes(jwtSettings.getTokenExpirationTime())
				.atZone(ZoneId.systemDefault()).toInstant());
		Date dateRefresh = Date.from(currentTime.plusMinutes(jwtSettings.getRefreshTokenExpTime())
				.atZone(ZoneId.systemDefault()).toInstant());

		// 이중로그인방지처리
		// userTokenStore에 저장된 정보가 있고, 만료일이 아직 안지난것중에서
		// - 아이피가 다른 경우
		UserTokenStore oldUserTokenStore = userTokenStoreService.getUserTokenStore(user.getUserId());

		if (!reqInfo.getForce() && !reqInfo.getMobile() && oldUserTokenStore != null
				&& !StringUtil.isEmpty(oldUserTokenStore.getExpiredTime())
				&& !StringUtil.isEmpty(oldUserTokenStore.getIpAddr())
				&& !StringUtil.isEmpty(oldUserTokenStore.getAccessToken())
				&& (!reqInfo.getPassIp().equalsIgnoreCase(reqInfo.getClientIP())
						&& !reqInfo.getPassIp2().equalsIgnoreCase(reqInfo.getClientIP()))
				&& ("test".equalsIgnoreCase(environment.getRequiredProperty("spring.profiles.active")))) {
			Date currDate = Date.from(currentTime.atZone(ZoneId.systemDefault()).toInstant());
			if (currDate.compareTo(
					DateUtil.stringToDate(oldUserTokenStore.getExpiredTime(), DateFormat.yyyyMMddhhmmss)) < 0) {
				// 만료일이 아직 지나지 않은것.
				if (userId.equalsIgnoreCase(oldUserTokenStore.getUserId())
						&& (!reqInfo.getClientIP().equalsIgnoreCase(oldUserTokenStore.getIpAddr()))) {
					throw new RestAuthException(HttpStatus.UNAUTHORIZED, "다른 기기에서 접속된 기록이 있습니다.",
							ExceptionCode.CONCURRENT_EXPIRED.name());
				} else {
					logger.debug("### CONCURRENT_EXPIRED DEBUG ");
				}
			}
		}

		List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
		roles.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

		UserContext userContext = UserContext.create(user.getUserId(), user.getUserNm(), reqInfo.getClientIP(),
				new SimpleDateFormat(conv.convertToDatabaseColumn(DateFormat.yyyyMMddhhmmss)).format(sysDate), "", "",
				roles);

		JwtToken accessToken = tokenFactory.createAccessNimbusJwtToken(userContext);

		UserContext userContextRefresh = UserContext.create(user.getUserId(), user.getUserNm(), reqInfo.getClientIP(),
				new SimpleDateFormat(conv.convertToDatabaseColumn(DateFormat.yyyyMMddhhmmss)).format(dateRefresh), "",
				"", roles);

		JwtToken refreshToken = tokenFactory.createRefreshNimbusToken(userContextRefresh);

		UserTokenStore userTokenStore = userTokenStoreService.insert(user.getUserId(), refreshToken.getToken(),
				accessToken.getToken(), reqInfo.getClientIP(),
				new SimpleDateFormat(conv.convertToDatabaseColumn(DateFormat.yyyyMMddhhmmss)).format(sysDate));

		if (userTokenStore == null) {
			throw new RestAuthException(HttpStatus.UNAUTHORIZED, "사용자 인증에 실패했습니다. 토큰 저장에 실패 했습니다.",
					ExceptionCode.UNAUTHORIZED.name());
		}

		// 로그인로그
		this.writeLoginLog(new LoginLog(LoginLogType.IN.name(), userContext.getUsername(), userContext.getDispname(),
				accessToken.getToken(), false, "", reqInfo.getClientIP()));

		return new JwtJoseToken(userId, accessToken.getToken(), refreshToken.getToken());
	}

	/**
	 * 토큰생성
	 *
	 * @param userId
	 * @param userPwd
	 * @return
	 * @throws RestAuthException
	 */
	public JwtJoseToken makeToken(String userId, String userPwd, AuthRequestInfoParam reqInfo)
			throws RestAuthException {
		return makeToken(userId, userPwd, "", reqInfo);
	}

	public JwtJoseToken makeToken(String userId, String userPwd, String userOtpPwd, AuthRequestInfoParam reqInfo)
			throws RestAuthException {

		User user = null;
		try {
			user = userService.getUser(userId);
		} catch (IOException ex) {
			logger.error(ex.getMessage());
		} catch (Exception ex) {
			// 사용자 인증에 실패했습니다. 사용자를 찾을 수 없습니다.
			logger.error(ex.getMessage());
			throw new RestAuthException(HttpStatus.UNAUTHORIZED, "M0000000002", ExceptionCode.UNAUTHORIZED.name());
		}

		if (user == null || !user.getUseYn().equals("Y")) {
			// 사용자 인증에 실패했습니다. 사용자를 찾을 수 없습니다.
			throw new RestAuthException(HttpStatus.UNAUTHORIZED, "M0000000002", ExceptionCode.UNAUTHORIZED.name());
		}

		String sha256hex = DigestUtils.sha256Hex(userPwd);
		if (!sha256hex.equalsIgnoreCase(user.getUserPwdSHA())) {
			// 사용자 인증에 실패했습니다.
			throw new RestAuthException(HttpStatus.UNAUTHORIZED, "M0000000018", "WRONG_PASSWORD");
		}

		// if (user.getUserAuthGroups() == null) throw new
		// InsufficientAuthenticationException("사용자의 권한이 없습니다.");

		DateFormatConverter conv = new DateFormatConverter();
		LocalDateTime currentTime = LocalDateTime.now();
		Date sysDate = Date.from(currentTime.plusMinutes(jwtSettings.getTokenExpirationTime())
				.atZone(ZoneId.systemDefault()).toInstant());
		Date dateRefresh = Date.from(currentTime.plusMinutes(jwtSettings.getRefreshTokenExpTime())
				.atZone(ZoneId.systemDefault()).toInstant());

		// 이중로그인방지처리
		// userTokenStore에 저장된 정보가 있고, 만료일이 아직 안지난것중에서
		// - 아이피가 다른 경우
		UserTokenStore oldUserTokenStore = userTokenStoreService.getUserTokenStore(user.getUserId());

		if (!reqInfo.getForce() && !reqInfo.getMobile() && oldUserTokenStore != null
				&& !StringUtil.isEmpty(oldUserTokenStore.getExpiredTime())
				&& !StringUtil.isEmpty(oldUserTokenStore.getIpAddr())
				&& !StringUtil.isEmpty(oldUserTokenStore.getAccessToken())
				&& (!reqInfo.getPassIp().equalsIgnoreCase(reqInfo.getClientIP())
						&& !reqInfo.getPassIp2().equalsIgnoreCase(reqInfo.getClientIP()))
				&& ("test".equalsIgnoreCase(environment.getRequiredProperty("spring.profiles.active")))) {
			Date currDate = Date.from(currentTime.atZone(ZoneId.systemDefault()).toInstant());
			if (currDate.compareTo(
					DateUtil.stringToDate(oldUserTokenStore.getExpiredTime(), DateFormat.yyyyMMddhhmmss)) < 0) {
				// 만료일이 아직 지나지 않은것.
				if (userId.equalsIgnoreCase(oldUserTokenStore.getUserId())
						&& (!reqInfo.getClientIP().equalsIgnoreCase(oldUserTokenStore.getIpAddr()))) {
					// 다른 기기에서 접속된 기록이 있습니다.
					throw new RestAuthException(HttpStatus.UNAUTHORIZED, "M0000000020",
							ExceptionCode.CONCURRENT_EXPIRED.name());
				} else {
					logger.debug("### CONCURRENT_EXPIRED DEBUG ");
				}
			}
		}

		List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
		roles.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

		UserContext userContext = UserContext.create(user.getUserId(), user.getUserNm(), reqInfo.getClientIP(),
				new SimpleDateFormat(conv.convertToDatabaseColumn(DateFormat.yyyyMMddhhmmss)).format(sysDate), "", "",
				roles);

		JwtToken accessToken = tokenFactory.createAccessNimbusJwtToken(userContext);

		UserContext userContextRefresh = UserContext.create(user.getUserId(), user.getUserNm(), reqInfo.getClientIP(),
				new SimpleDateFormat(conv.convertToDatabaseColumn(DateFormat.yyyyMMddhhmmss)).format(dateRefresh), "",
				"", roles);

		JwtToken refreshToken = tokenFactory.createRefreshNimbusToken(userContextRefresh);

		UserTokenStore userTokenStore = userTokenStoreService.insert(user.getUserId(), refreshToken.getToken(),
				accessToken.getToken(), reqInfo.getClientIP(),
				new SimpleDateFormat(conv.convertToDatabaseColumn(DateFormat.yyyyMMddhhmmss)).format(sysDate));

		if (userTokenStore == null) {
			// 사용자 인증에 실패했습니다. 토큰 저장에 실패 했습니다.
			throw new RestAuthException(HttpStatus.UNAUTHORIZED, "M0000000025", ExceptionCode.UNAUTHORIZED.name());
		}

		// 로그인로그
		this.writeLoginLog(new LoginLog(LoginLogType.IN.name(), userContext.getUsername(), userContext.getDispname(),
				accessToken.getToken(), false, "", reqInfo.getClientIP()));

		// Google OTP 이용 2차인증용 소스-최초로그인 시 관리자일 경우 otpYn="Y",OTP QR코드생성 후
		// OTP비밀번호입력시 일치할 경우 로그인 완료 처리
		// String otpYn = "N";
		// String otpUrl = "";
		// String otpSecKey = userId.toUpperCase() + "YULLINSHE";
		// if (StringUtil.isEmpty(userOtpPwd)) {
		// if (userId.equals("dev")) {
		// otpYn = "Y";
		// otpUrl = getQRBarcodeURL(userId, "she2016.yullin.com", otpSecKey);
		// }
		// } else {
		// String user_codeStr = userOtpPwd;
		// long user_code = Integer.parseInt(user_codeStr);
		// String encodedKey = otpSecKey;
		// long l = new Date().getTime();
		// long ll = l / 30000;
		//
		// boolean check_code = false;
		// try {
		// check_code = check_code(encodedKey, user_code, ll);
		// } catch (InvalidKeyException e) {
		// e.printStackTrace();
		// } catch (NoSuchAlgorithmException e) {
		// e.printStackTrace();
		// }
		// if (check_code) {
		// otpYn = "N";
		// } else {
		// otpYn = "X";
		// }
		// }
		//
		// if (otpYn.equals("N")) {
		// return new JwtJoseToken(userId, accessToken.getToken(),
		// refreshToken.getToken());
		// } else {
		// return new JwtJoseToken("", "", "", otpYn, otpSecKey, otpUrl);
		// }
		return new JwtJoseToken(userId, accessToken.getToken(), refreshToken.getToken());
	}

	/**
	 * Google OTP SecretKey 생성
	 *
	 * @return
	 */
	private String generateSecretKey() {
		byte[] buffer = new byte[5 + 5 * 5];
		new SecureRandom().nextBytes(buffer);
		Base32 codec = new Base32();
		byte[] secretKey = Arrays.copyOf(buffer, 5);
		byte[] bEncodedKey = codec.encode(secretKey);

		// 생성된 Key!
		String encodedKey = new String(bEncodedKey);

		return encodedKey;
	}

	/**
	 * Google OTP QR코드생성
	 *
	 * @param user
	 * @param host
	 * @param secret
	 * @return
	 */
	private String getQRBarcodeURL(String user, String host, String secret) {
		String format = "http://chart.apis.google.com/chart?cht=qr&chs=200x200&chl=otpauth://totp/%s@%s?secret=%s&chld=H|0";
		return String.format(format, user, host, secret);
	}

	/**
	 * Google OTP 비밀번호 일치 여부
	 *
	 * @param secret
	 * @param code
	 * @param t
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 */
	private boolean check_code(String secret, long code, long t) throws NoSuchAlgorithmException, InvalidKeyException {
		Base32 codec = new Base32();
		byte[] decodedKey = codec.decode(secret);
		int window = 3;
		for (int i = -window; i <= window; ++i) {
			long hash = verifyGoogleOtp(decodedKey, t + i);

			if (hash == code) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Google OTP 비밀번호 일치 여부
	 *
	 * @param key
	 * @param t
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 */
	private int verifyGoogleOtp(byte[] key, long t) throws NoSuchAlgorithmException, InvalidKeyException {
		byte[] data = new byte[8];
		long value = t;
		for (int i = 8; i-- > 0; value >>>= 8) {
			data[i] = (byte) value;
		}

		SecretKeySpec signKey = new SecretKeySpec(key, "HmacSHA1");
		Mac mac = Mac.getInstance("HmacSHA1");
		mac.init(signKey);
		byte[] hash = mac.doFinal(data);

		int offset = hash[20 - 1] & 0xF;

		long truncatedHash = 0;
		for (int i = 0; i < 4; ++i) {
			truncatedHash <<= 8;
			truncatedHash |= (hash[offset + i] & 0xFF);
		}

		truncatedHash &= 0x7FFFFFFF;
		truncatedHash %= 1000000;

		return (int) truncatedHash;
	}

	/**
	 * 토큰생성
	 *
	 * @param userId
	 * @param userPwd
	 * @return
	 * @throws RestAuthException
	 */
	public JwtJoseToken makeTokenForVendor(String userId, String userPwd, AuthRequestInfoParam reqInfo)
			throws RestAuthException {

		VendorUser user = null;
		try {
			user = userService.getVendorUser(userId); // 업체정보 테이블 조회
		} catch (IOException ex) {
			logger.error(ex.getMessage());
		} catch (Exception ex) {
			// 사용자 인증에 실패했습니다. 사용자를 찾을 수 없습니다.
			logger.error(ex.getMessage());
			throw new RestAuthException(HttpStatus.UNAUTHORIZED, "M0000000002", ExceptionCode.UNAUTHORIZED.name());
		}

		if (user == null) {
			// 사용자 인증에 실패했습니다. 사용자를 찾을 수 없습니다.
			throw new RestAuthException(HttpStatus.UNAUTHORIZED, "M0000000002", ExceptionCode.UNAUTHORIZED.name());
		}

		String sha256hex = DigestUtils.sha256Hex(userPwd);
		if (!sha256hex.equalsIgnoreCase(user.getPortalPwd())) {
			// 사용자 인증에 실패했습니다.
			throw new RestAuthException(HttpStatus.UNAUTHORIZED, "M0000000018", "WRONG_PASSWORD");
		}

		// if (user.getUserAuthGroups() == null) throw new
		// InsufficientAuthenticationException("사용자의 권한이 없습니다.");

		DateFormatConverter conv = new DateFormatConverter();
		LocalDateTime currentTime = LocalDateTime.now();
		Date sysDate = Date.from(currentTime.plusMinutes(jwtSettings.getTokenExpirationTime())
				.atZone(ZoneId.systemDefault()).toInstant());
		Date dateRefresh = Date.from(currentTime.plusMinutes(jwtSettings.getRefreshTokenExpTime())
				.atZone(ZoneId.systemDefault()).toInstant());

		// 이중로그인방지처리
		// userTokenStore에 저장된 정보가 있고, 만료일이 아직 안지난것중에서
		// - 아이피가 다른 경우
		UserTokenStore oldUserTokenStore = userTokenStoreService.getUserTokenStore(user.getPortalId());

		if (!reqInfo.getForce() && !reqInfo.getMobile() && oldUserTokenStore != null
				&& !StringUtil.isEmpty(oldUserTokenStore.getExpiredTime())
				&& !StringUtil.isEmpty(oldUserTokenStore.getIpAddr())
				&& !StringUtil.isEmpty(oldUserTokenStore.getAccessToken())
				&& (!reqInfo.getPassIp().equalsIgnoreCase(reqInfo.getClientIP())
						&& !reqInfo.getPassIp2().equalsIgnoreCase(reqInfo.getClientIP()))
				&& ("test".equalsIgnoreCase(environment.getRequiredProperty("spring.profiles.active")))) {
			Date currDate = Date.from(currentTime.atZone(ZoneId.systemDefault()).toInstant());
			if (currDate.compareTo(
					DateUtil.stringToDate(oldUserTokenStore.getExpiredTime(), DateFormat.yyyyMMddhhmmss)) < 0) {
				// 만료일이 아직 지나지 않은것.
				if (userId.equalsIgnoreCase(oldUserTokenStore.getUserId())
						&& (!reqInfo.getClientIP().equalsIgnoreCase(oldUserTokenStore.getIpAddr()))) {
					// 다른 기기에서 접속된 기록이 있습니다.
					throw new RestAuthException(HttpStatus.UNAUTHORIZED, "M0000000020",
							ExceptionCode.CONCURRENT_EXPIRED.name());
				} else {
					logger.debug("### CONCURRENT_EXPIRED DEBUG ");
				}
			}
		}

		List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
		roles.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

		UserContext userContext = UserContext.create(user.getPortalId(), user.getVendorNm(), reqInfo.getClientIP(),
				new SimpleDateFormat(conv.convertToDatabaseColumn(DateFormat.yyyyMMddhhmmss)).format(sysDate), "", "",
				roles);

		JwtToken accessToken = tokenFactory.createAccessNimbusJwtToken(userContext);

		UserContext userContextRefresh = UserContext.create(user.getPortalId(), user.getVendorNm(),
				reqInfo.getClientIP(),
				new SimpleDateFormat(conv.convertToDatabaseColumn(DateFormat.yyyyMMddhhmmss)).format(dateRefresh), "",
				"", roles);

		JwtToken refreshToken = tokenFactory.createRefreshNimbusToken(userContextRefresh);

		UserTokenStore userTokenStore = userTokenStoreService.insert(user.getPortalId(), refreshToken.getToken(),
				accessToken.getToken(), reqInfo.getClientIP(),
				new SimpleDateFormat(conv.convertToDatabaseColumn(DateFormat.yyyyMMddhhmmss)).format(sysDate));

		if (userTokenStore == null) {
			// 사용자 인증에 실패했습니다. 토큰 저장에 실패 했습니다.
			throw new RestAuthException(HttpStatus.UNAUTHORIZED, "M0000000025", ExceptionCode.UNAUTHORIZED.name());
		}

		// 로그인로그
		this.writeLoginLog(new LoginLog(LoginLogType.IN.name(), userContext.getUsername(), userContext.getDispname(),
				accessToken.getToken(), false, "", reqInfo.getClientIP()));

		return new JwtJoseToken(userId, accessToken.getToken(), refreshToken.getToken());
	}

	/**
	 *
	 * @param tokenPayload
	 * @return
	 * @throws RestAuthException
	 * @throws ValidationException
	 */
	public boolean clearUserLoginInfo(String tokenPayload, AuthRequestInfoParam reqInfo)
			throws RestAuthException, ValidationException {

		logger.debug("### SWING AUTH > clearUserLoginInfo Logout");

		TokenResponse res = this.clearToken(tokenPayload, reqInfo);
		if (res == null) {
			// 사용자 인증에 실패했습니다. 로그아웃시 토큰정보를 알수 없습니다.
			throw new RestAuthException(HttpStatus.UNAUTHORIZED, "M0000000030", ExceptionCode.INVALID.name());
		}
		// 로그인 토큰 정보를 Clear한다.
		userTokenStoreService.clearLoginInfo(res.getUserid());

		this.writeLoginLog(new LoginLog(LoginLogType.OU.name(), res.getUserid(), res.getDispname(), tokenPayload, false,
				"", reqInfo.getClientIP()));

		return true;
	}

	/**
	 * 로그인로그처리 비동기 서비스
	 *
	 * @param loginLog
	 */
	@Async
	private void writeLoginLog(LoginLog loginLog) {
		try {
			loginLogService.createLoginLog(loginLog);
		} catch (ValidationException e) {
			logger.error("### SHE ### Fail to write Login/Logout Log");
		}
	}

	/**
	 * 토큰 유효성 검증
	 *
	 * @param tokenPayload
	 * @return
	 * @throws RestException
	 * @throws ValidationException
	 */
	public TokenResponse validateToken(String tokenPayload, AuthRequestInfoParam reqInfo)
			throws RestAuthException, ValidationException {
		RawAccessJoseJwtToken rawToken = new RawAccessJoseJwtToken(tokenPayload);

		JWTClaimsSet jwsClaims;
		try {
			jwsClaims = rawToken.parseClaims(jwtSettings.getTokenSigningKey());
		} catch (ValidationException e) {
			if (ExceptionCode.INVALID.errorCode().equalsIgnoreCase(e.getErrorCode())) {
				// 사용자 인증에 실패했습니다. 잘못된 토큰입니다.
				throw new RestAuthException(HttpStatus.UNAUTHORIZED, "M0000000031", ExceptionCode.UNAUTHORIZED.name());
			} else if (ExceptionCode.ACCESS_EXPIRED.errorCode().equalsIgnoreCase(e.getErrorCode())) {
				// 사용자 인증에 실패했습니다. 토큰이 만료되었습니다.
				throw new RestAuthException(HttpStatus.UNAUTHORIZED, "M0000000032",
						ExceptionCode.ACCESS_EXPIRED.name());
			} else {
				// 사용자 인증에 실패했습니다. 사용자를 찾을 수 없습니다.
				throw new RestAuthException(HttpStatus.UNAUTHORIZED, "M0000000002", ExceptionCode.UNAUTHORIZED.name());
			}
		}

		TokenResponse res = this.getInfo(jwsClaims);
		// this.checkAuthValidation(tokenPayload, res, reqInfo);
		return res;
	}

	public TokenResponse clearToken(String tokenPayload, AuthRequestInfoParam reqInfo) throws ValidationException {
		RawAccessJoseJwtToken rawToken = new RawAccessJoseJwtToken(tokenPayload);
		JWTClaimsSet jwsClaims;
		try {
			jwsClaims = rawToken.parseClaims(jwtSettings.getTokenSigningKey());
		} catch (ValidationException e) {
			return null;
		}

		TokenResponse res = this.getInfo(jwsClaims);
		return res;
	}

	/**
	 * 현재 로그인된 토큰이 유효하지만, 다른곳에서 로그인되어 사용되는 경우 Exception 처리
	 *
	 * @param tokenPayload
	 * @param res
	 * @param reqInfo
	 * @throws RestAuthException
	 */
	private void checkAuthValidation(String tokenPayload, TokenResponse res, AuthRequestInfoParam reqInfo)
			throws RestAuthException {

		// 모바일요청이 아니고, 강제로 처리하는 요청도 아니고, 10년 장기토큰(관리자가 처리하는 정보, 아이피가 0.0.0.0)이
		// 아닌 경우
		if (!reqInfo.getMobile() && !reqInfo.getForce() && !res.getClientip().equalsIgnoreCase(reqInfo.getPassIp())
				&& !res.getClientip().equalsIgnoreCase(reqInfo.getPassIp2())) {
			// 디비정보조회
			UserTokenStore userTokenStore = userTokenStoreService.getUserTokenStore(res.getUserid());

			// 1. 요청된 클라이언트의 아이피와 DB의 아이피가 다른경우 Exception 발생
			if (!userTokenStore.getIpAddr().contains(reqInfo.getClientIP())) {
				// 사용자 인증에 실패했습니다. 다른 사용자로부터 사용되고 있어 로그아웃됩니다. IP WRONG
				throw new RestAuthException(HttpStatus.UNAUTHORIZED, "M0000000035", ExceptionCode.UNAUTHORIZED.name());
			}

			if ("2".equalsIgnoreCase(jwtSettings.getValidmode())) {
				if (!tokenPayload.equalsIgnoreCase(userTokenStore.getAccessToken())) {
					// 사용자 인증에 실패했습니다. 다른 사용자로부터 사용되고 있어 로그아웃됩니다. TOKENWRONG
					throw new RestAuthException(HttpStatus.UNAUTHORIZED, "M0000000036",
							ExceptionCode.UNAUTHORIZED.name());
				}
			}
		}
	}

	/**
	 * 토큰의 정보를 TokenResponse로 반환
	 *
	 * @param jwsClaims
	 * @return
	 */
	private TokenResponse getInfo(JWTClaimsSet jwsClaims) {
		@SuppressWarnings("unchecked")
		List<String> scopes = (List<String>) jwsClaims.getClaim(UserContextName.scopes.name());
		DateFormatConverter conv = new DateFormatConverter();

		logger.debug("#########만료일#########" + jwsClaims.getExpirationTime());
		logger.debug("### 만료일 ### " + jwsClaims.getClaim(UserContextName.expiredtime.name()));

		SimpleDateFormat df = new SimpleDateFormat(conv.convertToDatabaseColumn(DateFormat.yyyyMMddhhmmss));
		Date jwsexpiredTime = jwsClaims.getExpirationTime();
		@SuppressWarnings("unused")
		String expiredTime = df.format(jwsexpiredTime);

		return new TokenResponse("valid is ok!", jwsClaims.getSubject(),
				jwsClaims.getClaim(UserContextName.userid.name()).toString(),
				(String) jwsClaims.getClaim(UserContextName.dispname.name()),
				scopes.stream().collect(Collectors.joining(",")),
				(String) jwsClaims.getClaim(UserContextName.clientip.name()),
				(String) jwsClaims.getClaim(UserContextName.expiredtime.name()));
	}

	public JwtJoseToken issueRefreshToken(String tokenPayload, AuthRequestInfoParam reqInfo)
			throws RestAuthException, ValidationException {
		RawRefreshJoseJwtToken rawToken = new RawRefreshJoseJwtToken(tokenPayload);
		JWTClaimsSet jwsClaims;
		try {
			jwsClaims = rawToken.parseClaims(jwtSettings.getTokenSigningKey());
		} catch (ValidationException e) {
			if (ExceptionCode.INVALID.errorCode().equalsIgnoreCase(e.getErrorCode())) {
				// 사용자 인증에 실패했습니다. 잘못된 토큰입니다.
				throw new RestAuthException(HttpStatus.UNAUTHORIZED, "M0000000031", ExceptionCode.UNAUTHORIZED.name());
			} else if (ExceptionCode.REFRESH_EXPIRED.errorCode().equalsIgnoreCase(e.getErrorCode())) {
				// 사용자 인증에 실패했습니다. 토큰이 만료되었습니다.
				throw new RestAuthException(HttpStatus.UNAUTHORIZED, "M0000000032",
						ExceptionCode.REFRESH_EXPIRED.name());
			} else {
				// 사용자 인증에 실패했습니다. 사용자를 찾을 수 없습니다.
				throw new RestAuthException(HttpStatus.UNAUTHORIZED, "M0000000002", ExceptionCode.UNAUTHORIZED.name());
			}
		}

		String userId = jwsClaims.getClaim(UserContextName.userid.name()).toString();

		User user = null;
		try {
			user = userService.getUser(userId);
		} catch (IOException e) {
			logger.error(e.getMessage());
		} catch (Exception e) {
			// 사용자 인증에 실패했습니다. 사용자를 찾을 수 없습니다.
			logger.error(e.getMessage());
			throw new RestAuthException(HttpStatus.UNAUTHORIZED, "M0000000002", ExceptionCode.UNAUTHORIZED.name());
		}

		/*
		 * Date _date = new Date(new Date().getTime() +
		 * jwtSettings.getTokenExpirationTime()*1000*60);
		 */
		LocalDateTime currentTime = LocalDateTime.now();
		Date nowDate = Date.from(currentTime.plusMinutes(jwtSettings.getTokenExpirationTime())
				.atZone(ZoneId.systemDefault()).toInstant());
		DateFormatConverter conv = new DateFormatConverter();

		// 임시로 권한을 주어야 함.
		List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
		roles.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

		// AccessToken 생성
		UserContext userContext = UserContext.create(user.getUserId(), user.getUserNm(), reqInfo.getClientIP(),
				new SimpleDateFormat(conv.convertToDatabaseColumn(DateFormat.yyyyMMddhhmmss)).format(nowDate), "", "",
				roles);

		JwtToken accessToken = tokenFactory.createAccessNimbusJwtToken(userContext);
		// 190108 : refresh_token로 재발급시킴.
		JwtToken refreshToken = tokenFactory.createRefreshNimbusToken(userContext);

		// 토큰발급후 발급된 정보를 업데이트
		UserTokenStore userTokenStore = userTokenStoreService.insert(user.getUserId(), refreshToken.getToken(),
				accessToken.getToken(), reqInfo.getClientIP(),
				new SimpleDateFormat(conv.convertToDatabaseColumn(DateFormat.yyyyMMddhhmmss)).format(nowDate));

		if (userTokenStore == null) {
			// 사용자 인증에 실패했습니다. 토큰 저장에 실패 했습니다.
			throw new RestAuthException(HttpStatus.UNAUTHORIZED, "M0000000025", ExceptionCode.UNAUTHORIZED.name());
		}

		return new JwtJoseToken(user.getUserId(), accessToken.getToken(), refreshToken.getToken() /* tokenPayload */);
	}
}