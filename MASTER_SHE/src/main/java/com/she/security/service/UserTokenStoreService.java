package com.she.security.service;

import java.io.IOException;

import javax.xml.bind.ValidationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.she.security.exception.RestAuthException;
import com.she.security.mapper.UserTokenStoreMapper;
import com.she.security.model.token.UserTokenStore;
import com.she.security.util.StringUtil;

/**
 *
 * @클래스명 : UserTokenStoreService.java
 * @설명 : 로그인시 사용자 토큰 및 접속 정보 처리 서비스
 * @작성일 : 2018
 * @작성자 : 열린기술 (김유경)
 * @변경이력 :
 */
@Service
public class UserTokenStoreService {

	private static final Logger logger = LoggerFactory.getLogger(UserTokenStoreService.class);

	@Autowired
	private UserTokenStoreMapper userTokenStoreMapper;

	/**
	 * 사용자토큰정보 저장
	 * 
	 * @param userId
	 * @param refreshToken
	 * @param accessToken
	 * @param ipAddr
	 * @param expiredTime
	 * @return
	 * @throws RestAuthException
	 * @throws Exception
	 */
	@Transactional
	public UserTokenStore insert(String userId, String refreshToken, String accessToken, String ipAddr,
			String expiredTime) throws RestAuthException {

		logger.debug("::: UserTokenStore > insert");

		try {
			UserTokenStore userTokenStore = userTokenStoreMapper.getUserTokenStore(userId);
			if (userTokenStore == null) {
				return this.createUserToken(new UserTokenStore(userId, refreshToken, accessToken, ipAddr, expiredTime));
			} else {
				userTokenStore.updateInfo(new UserTokenStore(userId, refreshToken, accessToken, ipAddr, expiredTime));
				userTokenStoreMapper.updateUserTokenStore(userTokenStore);
				return userTokenStore;
			}
		} catch (IOException e) {
			logger.error(e.getMessage());
		} catch (Exception e) {
			// 사용자 인증에 실패했습니다. 토큰 정보를 저장할 수 없습니다.
			logger.error(e.getMessage());
			throw new RestAuthException(HttpStatus.UNAUTHORIZED, "M0000000024", "ERROR_INSERT_USER_TOKEN_STORE");
		}
		return null;
	}

	@Transactional(rollbackFor = ValidationException.class)
	private UserTokenStore createUserToken(UserTokenStore userTokenStore) throws RestAuthException {
		try {
			Integer returnValue = userTokenStoreMapper.createUserTokenStore(userTokenStore);
			if (StringUtil.isEmpty(returnValue) || returnValue.intValue() == 0) {
				// 사용자 인증에 실패했습니다. 토큰 정보를 저장할 수 없습니다.
				throw new RestAuthException(HttpStatus.UNAUTHORIZED, "M0000000024", "ERROR_INSERT_USER_TOKEN_STORE");
			} else {
				userTokenStore = userTokenStoreMapper.getUserTokenStore(userTokenStore.getUserId());
			}
			return userTokenStore;
		} catch (IOException e) {
			logger.error(e.getMessage());
		} catch (Exception e) {
			// 사용자 인증에 실패했습니다. 토큰 정보를 저장할 수 없습니다.
			logger.error(e.getMessage());
			throw new RestAuthException(HttpStatus.UNAUTHORIZED, "M0000000024", "ERROR_INSERT_USER_TOKEN_STORE");
		}
		return null;
	}

	/**
	 * 사용자ID로 토큰정보 찾기
	 * 
	 * @param userId
	 * @return
	 * @throws RestAuthException
	 * @throws Exception
	 */
	@Transactional
	public UserTokenStore getUserTokenStore(String userId) throws RestAuthException {
		try {
			return userTokenStoreMapper.getUserTokenStore(userId);
		} catch (IOException e) {
			logger.error(e.getMessage());
		} catch (Exception e) {
			// 사용자 인증에 실패했습니다. 토큰 정보를 가져올 수 없습니다.
			logger.error(e.getMessage());
			throw new RestAuthException(HttpStatus.UNAUTHORIZED, "M0000000019", "ERROR_GET_USER_TOKEN_STORE");
		}
		return null;
	}

	/**
	 * 사용자ID로 토큰정보 삭제
	 * 
	 * @param userId
	 * @throws RestAuthException
	 * @throws Exception
	 */
	@Transactional
	public void removeUserTokenStore(String userId) throws RestAuthException {
		try {
			UserTokenStore userTokenStore = userTokenStoreMapper.getUserTokenStore(userId);

			if (userTokenStore != null) {
				userTokenStoreMapper.removeUserTokenStore(userId);
			}
		} catch (IOException e) {
			logger.error(e.getMessage());
		} catch (Exception e) {
			// 사용자 인증에 실패했습니다. 토큰 정보를 가져올 수 없습니다.
			logger.error(e.getMessage());
			throw new RestAuthException(HttpStatus.UNAUTHORIZED, "M0000000019", "ERROR_GET_USER_TOKEN_STORE");
		}
	}

	/**
	 * 사용자ID의 토큰 정보 초기화
	 * 
	 * @param userId
	 * @throws RestAuthException
	 * @throws Exception
	 */
	@Transactional
	public void clearLoginInfo(String userId) throws RestAuthException {
		try {
			userTokenStoreMapper.clearUserTokenStore(userId);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// 사용자 인증에 실패했습니다. 토큰 정보를 초기화할 수 없습니다.
			e.printStackTrace();
			throw new RestAuthException(HttpStatus.UNAUTHORIZED, "M0000000023", "ERROR_CLEAR_USER_TOKEN_STORE");
		}
	}

}
