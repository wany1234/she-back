package com.she.manage.service;

import com.she.common.model.DefaultParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.manage.mapper.UserMenuMapper;
import com.she.manage.model.UserAuthGrp;
import com.she.manage.model.UserMenu;

import java.util.List;

@Service
public class UserMenuService {
    @Autowired
    private UserMenuMapper userMenuMapper;

    /**
     * 사용자ID로 사용자 권한별 메뉴 생성
     *
     * @param userIds
     * @return
     * @TODO: Delete 후 Insert, Transaction 처리 필요
     */
    public Integer createUserMenuByAuthGrp(UserAuthGrp userAuthGrp) throws Exception {
        Integer count = userMenuMapper.createUserMenuByAuthGrp(userAuthGrp.getUserId(), userAuthGrp.getCreateUserId());
        return count;
    }

    public List<UserMenu> getUserMenus(String userId, DefaultParam defaultParam) throws Exception {
        List<UserMenu> resultList = userMenuMapper.getUserMenus(userId, defaultParam);
		/*
		 * if (CollectionUtils.isEmpty(resultList)) { resultList =
		 * userMenuMapper.getDefaultUserMenus(defaultParam); }
		 */
        return resultList;
    }

    public Integer deleteUserMenuByAuthGrp(String userId) throws Exception {
        return userMenuMapper.deleteUserMenuByAuthGrp(userId);
    }
}
