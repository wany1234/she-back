package com.she.manage.model;

import java.util.List;

/**
 * 권한 그룹별 메뉴 등록용 모델
 */
public class AuthGrpMenus {

    public int authGrpNo;
    public List<AuthGrpMenu> authGrpMenus;

    public AuthGrpMenus() {
    }

    public AuthGrpMenus(int authGrpNo, List<AuthGrpMenu> authGrpMenus) {
        this.authGrpNo = authGrpNo;
        this.authGrpMenus = authGrpMenus;
    }

    public int getAuthGrpNo() {
        return authGrpNo;
    }

    public void setAuthGrpNo(int authGrpNo) {
        this.authGrpNo = authGrpNo;
    }

    public List<AuthGrpMenu> getAuthGrpMenus() {
        return authGrpMenus;
    }

    public void setAuthGrpMenus(List<AuthGrpMenu> authGrpMenus) {
        this.authGrpMenus = authGrpMenus;
    }
}
