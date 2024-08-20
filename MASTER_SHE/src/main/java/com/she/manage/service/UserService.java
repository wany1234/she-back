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

package com.she.manage.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.she.common.model.AttachFile;
import com.she.common.model.DefaultParam;
import com.she.common.model.Log;
import com.she.common.model.Menu;
import com.she.common.service.LogService;
import com.she.manage.mapper.UserMapper;
import com.she.manage.model.EduHistory;
import com.she.manage.model.EhrHistory;
import com.she.manage.model.HeaHistory;
import com.she.manage.model.LoginUserInfo;
import com.she.manage.model.SafHistory;
import com.she.manage.model.User;
import com.she.manage.model.UserAuthGrp;
import com.she.manage.model.VendorUser;

/**
 * 사용자 기능정의
 *
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private LogService logService;

    // 사용자 권한 그룹 서비스
    @Autowired
    UserAuthGrpService userAuthGrpService;

    /**
     * 사용자 조회
     *
     * @param processNo
     *            공정번호
     * @param deptCd
     *            부서코드
     * @param userId
     *            사용자아이디
     * @param userNm
     *            사용자명
     * @return 사용자 목록
     * @throws Exception
     */
    public List<User> getUsers(int processNo, String deptCd, String userId, String userNm) throws Exception {
        return this.userMapper.getUsers(processNo, deptCd, userId, userNm);
    }

    /**
     * 사용자 조회
     *
     * @param plantCd
     *            사업장코드
     * @param processCd
     *            공정번호
     * @param deptCd
     *            부서코드
     * @param userId
     *            사용자아이디
     * @param userNm
     *            사용자명
     * @param useYn
     *            사용여부
     * @return 사용자 목록
     * @throws Exception
     */
    public List<User> getUsers(String plantCd, String processCd, String deptCd, String userId, String userNm, String useYn, String deptSubYn, String dutyCd, String[] plantCds, String retireYn, DefaultParam defaultParam) throws Exception {
        return this.userMapper.getUsers(plantCd, processCd, deptCd, userId, userNm, useYn, deptSubYn, dutyCd, plantCds, retireYn, defaultParam);
    }

    /**
     * 사용자 상세 조회
     *
     * @param userId
     *            사용자아이디
     * @return 사용자
     * @throws Exception
     */
    public User getUser(String userId) throws Exception {
        return this.userMapper.getUser(userId);
    }

    public User getUser(String userId, String userPwdSHA) throws Exception {
        return this.userMapper.getUser(userId, userPwdSHA);
    }

    /**
     * 협력업체 사용자 정보 조회
     *
     * @param userId
     * @return
     * @throws Exception
     */
    public VendorUser getVendorUser(String userId) throws Exception {
        return this.userMapper.getVendorUser(userId);
    }

    /**
     * user 중복검사
     *
     * @param userId
     * @return 중복 행 수
     * @throws Exception
     */
    public int countUser(String userId) throws Exception {
        return this.userMapper.countUser(userId);
    }

    public String createUser(User user) throws Exception {
        User userValidator = this.getUser(user.getUserId()); // 유저 정보 중복 체크
        Integer count = null;
        String userId = null;
        String signature = null;
        if (userValidator != null) {
            throw new SQLException(user.getUserId() + "는 이미 등록된 ID 입니다.", new SQLException("Duplicated"));
        } else {
            count = this.userMapper.createUser(user);
            userId = user.getUserId();
            signature = user.getSignature();
            if (userId != null && signature != null) {
                userMapper.updateUserSignature(userId, signature);
            }
        }

        return count > 0 ? user.getUserId() : "";
    }

    public Integer updateUser(User user) throws Exception {
        Integer count = this.userMapper.updateUser(user);
        return count;
    }

    /**
     * 사용자 로그인
     *
     * @param account
     * @return
     * @throws Exception
     */
    public String login(User account) throws Exception {
        String token = "";
        User user = this.userMapper.login(account);
        if (user != null && user.getUserId() != null && !user.getUserId().equals("")) {
            token = user.getUserId();

            Log log = new Log("login", account.getUserId(), null, null, user.getUserId() + ':' + account.getUserNm() + " Login", null, null);
            logService.createLog(log);
        } else {
            Log log = new Log("loginFailed", account.getUserId(), null, null, account.getUserId() + ':' + " Login has been Failed", null, null);
            logService.createLog(log);
        }
        return token;
    }

    public LoginUserInfo getUserByToken(String token) throws Exception {
        String userId = token;
        User user = this.getUser(userId);
        LoginUserInfo loginUserInfo = null;
        if (user != null && user.getUserId() != null && !user.getUserId().equals("")) {
            loginUserInfo = new LoginUserInfo();
            List<UserAuthGrp> userAuthGrps = userAuthGrpService.getUserAuthGrps(user.getUserId(), user.getDeptCd());
            loginUserInfo.setUserAuthGrps(userAuthGrps);
            loginUserInfo.setUser(user);
            for (UserAuthGrp userAuthGrp : userAuthGrps) {
                loginUserInfo.getAuths().add(userAuthGrp.getAuthGrpNm());
            }
            loginUserInfo.setDeptAuthGrp(userAuthGrpService.getAuth(user.getUserId()));

            Log log = new Log("login", user.getUserId(), null, null, user.getUserId() + ':' + user.getUserNm() + " Login", null, null);
            logService.createLog(log);
        } else {
            Log log = new Log("loginFailed", userId, null, null, userId + ':' + " Login has been Failed", null, null);
            logService.createLog(log);
        }
        return loginUserInfo;
    }

    public LoginUserInfo getVendorByToken(String token) throws Exception {
        String userId = token;
        VendorUser user = this.getVendorUser(userId);
        LoginUserInfo loginUserInfo = null;
        if (user != null && StringUtils.isNotBlank(user.getPortalId())) {
            loginUserInfo = new LoginUserInfo();
            loginUserInfo.setVendor(user);

            Log log = new Log("login", user.getPortalId(), null, null, user.getPortalId() + ':' + user.getVendorNm() + " Login", null, null);
            logService.createLog(log);
        } else {
            Log log = new Log("loginFailed", userId, null, null, userId + ':' + " Login has been Failed", null, null);
            logService.createLog(log);
        }
        return loginUserInfo;
    }

    public Integer passwordChange(User user) throws Exception {
        return this.userMapper.passwordChange(user);
    }

    public List<User> getDutyUserInfo(String plantCd, String[] deptNms, String[] dutyCds, String[] userNms) throws Exception {
        return userMapper.getDutyUserInfo(plantCd, deptNms, dutyCds, userNms);
    }

    public List<User> getChmMaliReceiveUserInfo(String plantCd) throws Exception {
        return userMapper.getChmMaliReceiveUserInfo(plantCd);
    }

    /**
     * user 즐겨찾기 등록
     *
     * @param userId
     * @param menuId
     * @return
     * @throws Exception
     */
    @Transactional
    @CacheEvict(cacheNames = "PageFavoriteChkCashe", key = "'PAGEFAVORITE' + #userId + #menuId")
    public Integer createUserFavorites(String userId, String menuId) {
        // userMapper.deleteUserFavorites(userId, menuId);

        // 20200608 박정환차장 수정
        // 즐겨찾기가 추가되어 있으면 삭제, 미등록이면 추가
        // 2021.04.06 kdh
        // 있는지 확인 용도임으로 언어를 kr로 넣어 사용
        List<Menu> resultList = userMapper.getUserFavorites(userId, "", "", "", menuId, new DefaultParam("kr"));
        if (CollectionUtils.isEmpty(resultList)) {
            // 정상적으로 등록된 경우 1 리턴
            userMapper.createUserFavorites(userId, menuId);
            return 1;
        } else {
            // 정상적으로 삭제된 경우 9 리턴
            userMapper.deleteUserFavorites(userId, menuId);
            return 9;
        }

    }

    /**
     * user 즐겨찾기 삭제
     *
     * @param userId
     * @param menuId
     * @return
     * @throws Exception
     */
    @CacheEvict(cacheNames = "PageFavoriteChkCashe", allEntries = true)
    public Integer deleteUserFavorites(List<HashMap<String, Object>> parameter) {
        int count = 0;
        String userId = "";
        String menuId = "";
        for (HashMap<String, Object> hashMap : parameter) {
            userId = hashMap.containsKey("userId") ? hashMap.get("userId").toString() : "";
            menuId = hashMap.containsKey("menuId") ? hashMap.get("menuId").toString() : "";

            count += userMapper.deleteUserFavorites(userId, menuId);
        }

        return count;
    }

    /**
     * user 즐겨찾기 검색
     *
     * @param userId
     * @param upMenuId
     * @param menuNm
     * @param taskGrpCd
     * @param menuId
     * @return
     * @throws Exception
     */
    @Cacheable(cacheNames = "PageFavoriteChkCashe", key = "'PAGEFAVORITE' + #userId + #menuId")
    public List<Menu> getUserFavorites(String userId, String taskGrpCd, String menuNm, String upMenuId, String menuId, DefaultParam defaultParam) {

        return userMapper.getUserFavorites(userId, taskGrpCd, menuNm, upMenuId, menuId, defaultParam);
    }

    /**
     * 사용자 즐겨찾기 메뉴
     *
     * @param userId
     * @return
     */
    public List<Menu> getUserFavoriteMenus(String userId, DefaultParam defaultParam) {
        return userMapper.getUserFavoriteMenus(userId, defaultParam);
    }

    /**
     * 사용자 서명 등록
     *
     * @param parameter
     *            사용자 아이디, 사용자 서명
     * @return
     * @throws Exception
     */
    public Integer updateUserSignature(String userId, String signature) throws Exception {
        return userMapper.updateUserSignature(userId, signature);
    }

    /**
     * 메뉴얼 다운로드를 위한 파일서치
     *
     * @param parameter
     *            사용자 아이디, 사용자 서명
     * @return
     * @throws Exception
     */
    public AttachFile fileSearch(HashMap<String, Object> param) throws Exception {
        return userMapper.fileSearch(param);
    }
    
    /**
     * 팀장 조회 해당부서 팀장없을 경우 상위부서 팀장 조회 하지 않음
     * 
     * @param deptCd
     *            부서
     * @return
     * @throws Exception
     */
    public List<User> getTeamLeader(@Param("deptCd") String deptCd) throws Exception {
        return userMapper.getTeamLeader(deptCd);
    }
    
    /**
     * EHR 근무이력 조회
     * @param userId
     * @return
     * @throws Exception
     */
    public List<EhrHistory> getEhrHistory(@Param("userId") String userId, DefaultParam defaultParam) throws Exception{
    	return userMapper.getEhrHistory(userId, defaultParam);
    }
    
    /**
     * 교육이력 조회
     * @param userId
     * @return
     * @throws Exception
     */
    public List<EduHistory> getEduHistory(@Param("userId") String userId, DefaultParam defaultParam) throws Exception{
    	return userMapper.getEduHistory(userId, defaultParam);
    }
    
    /**
     * 검진이력 조회
     * @param userId
     * @return
     * @throws Exception
     */
    public List<HeaHistory> getHeaHistory(@Param("userId") String userId, DefaultParam defaultParam) throws Exception{
    	return userMapper.getHeaHistory(userId, defaultParam);
    }
    
    /**
     * 사고이력 조회
     * @param userId
     * @return
     * @throws Exception
     */
    public List<SafHistory> getSafHistory(@Param("userId") String userId, DefaultParam defaultParam) throws Exception{
    	return userMapper.getSafHistory(userId, defaultParam);
    }
}
