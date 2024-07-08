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

package com.she.manage.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.common.model.AttachFile;
import com.she.common.model.DefaultParam;
import com.she.common.model.Menu;
import com.she.manage.model.EduHistory;
import com.she.manage.model.EhrHistory;
import com.she.manage.model.HeaHistory;
import com.she.manage.model.SafHistory;
import com.she.manage.model.User;
import com.she.manage.model.VendorUser;

/**
 * 사용자 맵퍼
 *
 */
@Mapper
@Repository("com.she.manage.mapper.UserMapper")
public interface UserMapper {

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
    public List<User> getUsers(@Param("processNo") int processNo, @Param("deptCd") String deptCd, @Param("userId") String userId, @Param("userNm") String userNm) throws Exception;

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
    public List<User> getUsers(@Param("plantCd") String plantCd, @Param("processCd") String processCd, @Param("deptCd") String deptCd, @Param("userId") String userId, @Param("userNm") String userNm, @Param("useYn") String useYn, @Param("deptSubYn") String deptSubYn, @Param("dutyCd") String dutyCd, @Param("defaultParam") DefaultParam defaultParam)
            throws Exception;

    /**
     * 사용자 상세 조회
     *
     * @param userId
     *            사용자아이디
     * @return 사용자
     * @throws Exception
     */
    public User getUser(@Param("userId") String userId) throws Exception;

    public User getUser(@Param("userId") String userId, @Param("userPwdSHA") String userPwdSHA) throws Exception;

    /**
     * 협력업체 사용자 정보 조회
     *
     * @param userId
     * @return
     * @throws Exception
     */
    public VendorUser getVendorUser(@Param("userId") String userId) throws Exception;

    /**
     * user 중복검사
     *
     * @param userId
     * @return 중복 행 수
     * @throws Exception
     */
    public int countUser(@Param("userId") String userId) throws Exception;

    /**
     * 사용자 신규 등록
     *
     * @param user
     * @return 등록 개수
     * @throws Exception
     */
    public Integer createUser(User user) throws Exception;

    /**
     * 사용자 수정
     *
     * @param user
     * @return 수정 개수
     * @throws Exception
     */
    public Integer updateUser(User user) throws Exception;

    public User login(User account) throws Exception;

    public Integer passwordChange(User user) throws Exception;

    public List<User> getDutyUserInfo(@Param("plantCd") String plantCd, @Param("deptNms") String[] deptNm, @Param("dutyCds") String[] dutyCds, @Param("userNms") String[] userNms) throws Exception;

    public List<User> getChmMaliReceiveUserInfo(@Param("plantCd") String plantCd) throws Exception;

    /**
     * user 즐겨찾기 등록
     *
     * @param userId
     * @param menuId
     * @return
     * @throws Exception
     */
    public Integer createUserFavorites(@Param("userId") String userId, @Param("menuId") String menuId);

    /**
     * user 즐겨찾기 삭제
     *
     * @param userId
     * @param menuId
     * @return
     * @throws Exception
     */
    public Integer deleteUserFavorites(@Param("userId") String userId, @Param("menuId") String menuId);

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
    public List<Menu> getUserFavorites(@Param("userId") String userId, @Param("taskGrpCd") String taskGrpCd, @Param("menuNm") String menuNm, @Param("upMenuId") String upMenuId, @Param("menuId") String menuId, @Param("defaultParam") DefaultParam defaultParam);

    /**
     * 사용자 즐겨찾기 메뉴 검색
     *
     * @param userId
     * @return
     */
    List<Menu> getUserFavoriteMenus(@Param("userId") String userId, @Param("defaultParam") DefaultParam defaultParam);

    /**
     * 사용자 서명 등록
     *
     * @param userId
     *            사용자 아이디
     * @param signature
     *            사용자 서명
     * @return
     * @throws Exception
     */
    public Integer updateUserSignature(@Param("userId") String userId, @Param("signature") String signature) throws Exception;

    /**
     * 메뉴얼 다운로드를 위한 파일서치
     *
     * @param parameter
     *            사용자 아이디, 사용자 서명
     * @return
     * @throws Exception
     */
    public AttachFile fileSearch(HashMap<String, Object> param) throws Exception;
    
    /**
     * 팀장 조회 해당부서 팀장없을 경우 상위부서 팀장 조회 하지 않음
     * 
     * @param deptCd
     *            부서
     * @return
     * @throws Exception
     */
    public List<User> getTeamLeader(@Param("deptCd") String deptCd) throws Exception;
    
    /**
     * EHR 근무이력 조회
     * @param userId
     * @return
     * @throws Exception
     */
    public List<EhrHistory> getEhrHistory(@Param("userId") String userId, @Param("defaultParam") DefaultParam defaultParam) throws Exception;
    
    /**
     * 교육이력 조회
     * @param userId
     * @return
     * @throws Exception
     */
    public List<EduHistory> getEduHistory(@Param("userId") String userId, @Param("defaultParam") DefaultParam defaultParam) throws Exception;
    
    /**
     * 검진이력 조회
     * @param userId
     * @return
     * @throws Exception
     */
    public List<HeaHistory> getHeaHistory(@Param("userId") String userId, @Param("defaultParam") DefaultParam defaultParam) throws Exception;
    
    /**
     * 사고이력 조회
     * @param userId
     * @return
     * @throws Exception
     */
    public List<SafHistory> getSafHistory(@Param("userId") String userId, @Param("defaultParam") DefaultParam defaultParam) throws Exception;
}
