package com.she.manage.controller;

import com.she.common.model.DefaultParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.she.manage.model.UserMenu;
import com.she.manage.service.UserMenuService;
import com.she.utils.RequestMapper;

import java.util.List;

/**
 * 사용자 메뉴 관리 컨트롤러
 */
@RestController
public class UserMenuController {
    @Autowired
    UserMenuService userMenuService;

    @Autowired
    RequestMapper requestMapper;

    @GetMapping("/api/manage/usermenus/{userId}")
    public ResponseEntity<List<UserMenu>> getUserMenus(@PathVariable String userId, @ModelAttribute DefaultParam defaultParam) throws Exception {
        return ResponseEntity.ok().body(userMenuService.getUserMenus(userId, defaultParam));
    }
}
