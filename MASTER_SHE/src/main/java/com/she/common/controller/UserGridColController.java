package com.she.common.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.she.common.model.UserGridCol;
import com.she.common.service.UserGridService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/common/grid")
public class UserGridColController {

    @Autowired
    private UserGridService userGridService;

    @ApiOperation(value = "사용자별 메뉴 그리드 컬럼설정 목록 [반환값 : 사용자별 즐겨찾기 목록]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @GetMapping("/usergridcol")
    public ResponseEntity<List<UserGridCol>> getUserGridCols(@RequestParam final HashMap<String, String> parameter) throws Exception {

        String userId = parameter.getOrDefault("userId", "").toString();
        String menuId = parameter.getOrDefault("menuId", "").toString();
        String gridId = parameter.getOrDefault("gridId", "").toString();

        List<UserGridCol> userGridCols = new ArrayList<UserGridCol>();

        if (userId != "" && menuId != "" && gridId != "") {
            final UserGridCol userGridCol = new UserGridCol();

            userGridCol.setUserId(userId);
            userGridCol.setMenuId(menuId);
            userGridCol.setGridId(gridId);

            userGridCols = this.userGridService.getUserGridCols(userGridCol);
        }

        return ResponseEntity.ok().body(userGridCols);
    }

    @ApiOperation(value = "사용자별 메뉴 그리드 컬럼설정 등록 [반환값 : 등록 행 수]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PostMapping("/usergridcol")
    public ResponseEntity<Integer> createUserGridCol(@RequestBody final UserGridCol userGridCol) throws Exception {
        String userId = userGridCol.getUserId();
        String menuId = userGridCol.getMenuId();
        String gridId = userGridCol.getGridId();
        if (userId == null || menuId == null || gridId == null) {
            return ResponseEntity.badRequest().body(0);
        }

        final Integer count = this.userGridService.createUserGridCol(userGridCol);
        return ResponseEntity.ok().body(count);
    }

    @ApiOperation(value = "사용자별 메뉴 그리드 컬럼설정 삭제 [반환값 : 삭제 행 수]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "userGridCol", value = "사용자별즐겨찾기", required = true, dataType = "UserGridCol", paramType = "body") })
    @DeleteMapping("/usergridcol/{userId}/{menuId}/{gridId}/{isAll}/{type}")
    public ResponseEntity<Integer> deleteUserGridCol(@PathVariable final String userId, @PathVariable final String menuId, @PathVariable final String gridId, @PathVariable final String isAll, @PathVariable final String type) throws Exception {
        UserGridCol userGridCol = new UserGridCol();
        userGridCol.setUserId(userId);
        userGridCol.setMenuId(menuId);
        userGridCol.setGridId(gridId);
        userGridCol.setIsAll(isAll);
        userGridCol.setType(type);

        this.userGridService.deleteUserGridCol(userGridCol);
        return ResponseEntity.ok().body(1);
    }
}
