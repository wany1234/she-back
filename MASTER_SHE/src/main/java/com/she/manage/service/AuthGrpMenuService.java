package com.she.manage.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.common.model.DefaultParam;
import com.she.manage.mapper.AuthGrpMenuMapper;
import com.she.manage.model.AuthGrpMenu;
import com.she.manage.model.AuthGrpMenus;
import com.she.manage.model.AuthMenuSaveData;

@Service
public class AuthGrpMenuService {
    @Autowired
    AuthGrpMenuMapper authGrpMenuMapper;

    /**
     * 권한 그룹별 메뉴 목록 조회 서비스
     *
     * @param keyword
     *            권한 그룹명/코드 키워드
     * @param useYn
     *            사용여부
     * @return 권한 그룹 목록
     * @throws Exception
     */
    public List<AuthGrpMenu> getAuthGrpMenus(int authGrpNo, DefaultParam defaultParam) throws Exception {
        return authGrpMenuMapper.getAuthGrpMenus(authGrpNo, defaultParam);
    }

    /**
     * 권한 그룹별 메뉴 등록(DELETE & INSERT)
     *
     * @param authGrpMenu
     *            권한 그룹 모델
     * @return 추가된 행 수
     * @TODO : transaction 처리 필요
     */
    @Transactional
    public int saveAuthGrpMenuAccess(AuthMenuSaveData authMenuSaveData) throws Exception {
        int count = 0;
        // 전체 삭제 후 재등록
        if (authMenuSaveData.getSaveData() != null) {
            authGrpMenuMapper.deleteAuthMenu(authMenuSaveData.getAuthGrpNo(), "");
            if (authMenuSaveData.getSaveData().size() > 0) {
                for (AuthGrpMenu authGrpMenu : authMenuSaveData.getSaveData()) {
                    authGrpMenu.setAuthGrpNo(authMenuSaveData.getAuthGrpNo());
                    authGrpMenu.setCreateUserId(authMenuSaveData.getCreateUserId());
                    if (authGrpMenu.getWriteYn() == null || authGrpMenu.getWriteYn().equals("")) {
                        authGrpMenu.setWriteYn("N");
                    }
                    count += authGrpMenuMapper.createAuthGrpMenu(authGrpMenu);
                }
            }
        }
        return count;
    }

    /**
     * 권한 그룹별 메뉴 쓰기권한 부여
     *
     * @param authGrpMenu
     *            권한 그룹 모델
     * @return 추가된 행 수
     * @TODO : transaction 처리 필요
     */
    @Transactional
    public int updateAuthGrpMenuWrite(List<AuthGrpMenu> authGrpMenus) throws Exception {
        int count = 0;
        if (authGrpMenus != null && authGrpMenus.size() > 0) {
            // 쓰기권한 부여 전 해당 권한에 메뉴들의 쓰기권한을 전부 N처리 한다.
            authGrpMenuMapper.updateAuthMenuWriteReset(authGrpMenus.get(0).getAuthGrpNo());
            for (AuthGrpMenu authGrpMenu : authGrpMenus) {
                // 쓰기권한 부여
                authGrpMenu.setWriteYn("Y");
                count += authGrpMenuMapper.updateAuthMenu(authGrpMenu);
            }
        }
        return count;
    }

    /**
     * 권한 그룹별 메뉴 등록(DELETE & INSERT)
     *
     * @param authGrpMenu
     *            권한 그룹 모델
     * @return 추가된 행 수
     * @TODO : transaction 처리 필요
     */
    public int createAuthGrpMenu(AuthGrpMenus authGrpMenus) throws Exception {
        // 기존 등록된 권한 그룹별 메뉴 삭제
        authGrpMenuMapper.deleteAuthGrpMenu(authGrpMenus.getAuthGrpNo());
        int count = 0;
        if (authGrpMenus.getAuthGrpMenus() != null) {
            for (AuthGrpMenu authGrpMenu : authGrpMenus.getAuthGrpMenus()) {
                authGrpMenuMapper.createAuthGrpMenu(authGrpMenu);
                count++;
            }
        }
        return count;
    }
}
