package com.she.manage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.she.common.model.DefaultParam;
import com.she.manage.model.AuthGrpMenu;
import com.she.manage.model.AuthGrpMenus;
import com.she.manage.model.AuthMenuSaveData;
import com.she.manage.service.AuthGrpMenuService;

/**
 * 권한 그룹별 메뉴 컨트롤러
 */
@RestController
public class AuthGrpMenuController {

    @Autowired
    private AuthGrpMenuService authGrpMenuService;

    /**
     * 권한 그룹 메뉴 목록 조회
     *
     * @param parameter
     * @return
     * @throws Exception
     */
    @GetMapping("/api/manage/authgrpmenus/{authGrpNo}")
    public ResponseEntity<List<AuthGrpMenu>> getAuthGrpMenus(@PathVariable int authGrpNo, @ModelAttribute DefaultParam defaultParam) throws Exception {
        return ResponseEntity.ok().body(this.authGrpMenuService.getAuthGrpMenus(authGrpNo, defaultParam));
    }

    /**
     * 권한 그룹 메뉴 등록
     *
     * @param authGrpMenu
     *            권한 그룹 모델
     * @return 신규 등록된 권한 그룹 코드
     * @throws Exception
     */
    @PostMapping("/api/manage/authgrpmenuaccess")
    public int saveAuthGrpMenuAccess(@RequestBody AuthMenuSaveData authMenuSaveData) throws Exception {
        return this.authGrpMenuService.saveAuthGrpMenuAccess(authMenuSaveData);
    }

    /**
     * 권한 그룹 메뉴 등록
     *
     * @param authGrpMenu
     *            권한 그룹 모델
     * @return 신규 등록된 권한 그룹 코드
     * @throws Exception
     */
    @PutMapping("/api/manage/authgrpmenuwrite")
    public int updateAuthGrpMenuWrite(@RequestBody List<AuthGrpMenu> authGrpMenus) throws Exception {
        return this.authGrpMenuService.updateAuthGrpMenuWrite(authGrpMenus);
    }

    /**
     * 권한 그룹 메뉴 등록
     *
     * @param authGrpMenu
     *            권한 그룹 모델
     * @return 신규 등록된 권한 그룹 코드
     * @throws Exception
     */
    @PostMapping("/api/manage/authgrpmenu")
    public int createAuthGrpMenu(@RequestBody AuthGrpMenus authGrpMenus) throws Exception {
        int authGrpMenuNo = this.authGrpMenuService.createAuthGrpMenu(authGrpMenus);
        return authGrpMenuNo;
    }
}
