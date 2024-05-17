package com.she.manage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.common.model.DefaultParam;
import com.she.manage.model.UserMenu;

/**
 * 사용자 맵퍼
 *
 */
@Mapper
@Repository("com.she.manage.mapper.UserMenuMapper")
public interface UserMenuMapper {

    /**
     * 사용자 권한 별 메뉴 부여
     *
     * @param userIds
     * @param createUserId
     * @return
     * @throws Exception
     */
    public Integer createUserMenuByAuthGrp(@Param("userId") String userId, @Param("createUserId") String createUserId) throws Exception;

    public List<UserMenu> getUserMenus(@Param("userId") String userId, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    public List<UserMenu> getDefaultUserMenus(@Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 사용자에게 부여된 권한 그룹의 메뉴 삭제
     *
     * @param userId
     * @return
     * @throws Exception
     */
    public Integer deleteUserMenuByAuthGrp(@Param("userId") String userId) throws Exception;
}
