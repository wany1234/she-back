package com.she.manage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.common.model.DefaultParam;
import com.she.manage.model.AuthGrpMenu;

@Mapper
@Repository("com.she.manage.mapper.AuthGrpMenuMapper")
public interface AuthGrpMenuMapper {
    /**
     * 권한 그룹별 메뉴 목록 조회 mapper
     * 
     * @param keyword
     *            권한 그룹별 메뉴명/코드 키워드
     * @param useYn
     *            사용여부
     * @return 권한 그룹별 메뉴 목록
     * @throws Exception
     */
    public List<AuthGrpMenu> getAuthGrpMenus(@Param("authGrpNo") int authGrpNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 권한 그룹별 메뉴 삭제
     * 
     * @param authGrpNo
     *            권한 그룹별 메뉴 코드
     * @param useYn
     *            사용 여부
     * @return 삭제된 행 수
     * @throws Exception
     */
    public int deleteAuthMenu(@Param("authGrpNo") int authGrpNo, @Param("menuId") String menuId) throws Exception;

    /**
     * 권한 그룹별 메뉴 삭제 (menu id에 따른)
     * 
     * @param menuId
     * @return 삭제된 행 수
     * @throws Exception
     */
    public int deleteAuthMenuAccordingMenuId(@Param("menuId") String menuId) throws Exception;

    /**
     * 권한 그룹별 메뉴 등록
     * 
     * @param authGrp
     *            권한 그룹별 메뉴 모델
     * @return 신규 등록된 권한 코드
     */
    public int createAuthGrpMenu(AuthGrpMenu authGrpMenu) throws Exception;

    /**
     * 권한 그룹별 메뉴 쓰기권한 부여
     * 
     * @param authGrp
     *            권한 그룹별 메뉴 모델
     * @return 신규 등록된 권한 코드
     */
    public int updateAuthMenuWriteReset(@Param("authGrpNo") int authGrpNo) throws Exception;

    /**
     * 권한 그룹별 메뉴 쓰기권한 부여
     * 
     * @param authGrp
     *            권한 그룹별 메뉴 모델
     * @return 신규 등록된 권한 코드
     */
    public int updateAuthMenu(AuthGrpMenu authGrpMenu) throws Exception;

    /**
     * 권한 그룹별 메뉴 삭제
     * 
     * @param authGrpNo
     *            권한 그룹별 메뉴 코드
     * @param useYn
     *            사용 여부
     * @return 삭제된 행 수
     * @throws Exception
     */
    public int deleteAuthGrpMenu(int authGrpNo) throws Exception;
}
