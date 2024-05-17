package com.she.manage.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.she.manage.model.AuthGrp;
import com.she.manage.model.AuthGrpDept;
import com.she.manage.model.AuthGrpUser;
import com.she.manage.model.DeptAuthGrp;
import com.she.manage.model.UserAuthGrp;
import com.she.manage.service.AuthGrpService;
import com.she.utils.RequestMapper;

/**
 * 권한 그룹 컨트롤러
 */
@RestController
public class AuthGrpController {
    // TODO : queryString 변환을 위한 mapper 선언
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private AuthGrpService authGrpService;

    /**
     * 권한 그룹 목록 조회
     *
     * @param parameter
     * @return
     * @throws Exception
     */
    @GetMapping("/api/manage/authgrps")
    public ResponseEntity<List<AuthGrp>> getAuthGrps(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        String keyword = map.containsKey("keyword") ? map.get("keyword").toString() : "";
        String useYn = map.containsKey("useYn") ? map.get("useYn").toString() : "";
        String searchFlag = map.containsKey("searchFlag") ? map.get("searchFlag").toString() : "";
        return ResponseEntity.ok().body(this.authGrpService.getAuthGrps(keyword, useYn, searchFlag));
    }

    /**
     * 권한그룹 중복검사
     *
     * @param authGrpNm
     * @return 중복 행 수
     * @throws Exception
     */
    @GetMapping("/api/manage/countauthgrp")
    public ResponseEntity<Integer> countAuthGrp(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 권한그룹명
        String authGrpNm = map.containsKey("authGrpNm") ? map.get("authGrpNm").toString() : "";

        return ResponseEntity.ok().body(this.authGrpService.countAuthGrp(authGrpNm));
    }

    /**
     * 권한 그룹 등록
     *
     * @param authGrp
     *            권한 그룹 모델
     * @return 신규 등록된 권한 그룹 코드
     * @throws Exception
     */
    @PostMapping("/api/manage/authgrp")
    public int createAuthGrp(@RequestBody AuthGrp authGrp) throws Exception {
        return this.authGrpService.createAuthGrp(authGrp);
    }

    /**
     * 권한 그룹 수정
     *
     * @param authGrp
     *            권한 그룹 모델
     * @return 수정된 행 수
     * @throws Exception
     */
    @PutMapping("/api/manage/authgrp/{authGrpNo}")
    public ResponseEntity<Integer> updateAuthGrp(@PathVariable int authGrpNo, @RequestBody AuthGrp authGrp) throws Exception {
        authGrp.setAuthGrpNo(authGrpNo);
        return ResponseEntity.ok().body(this.authGrpService.updateAuthGrp(authGrp));
    }

    @GetMapping("/api/manage/authgrpusers")
    public ResponseEntity<List<AuthGrpUser>> getAuthGrpUsers(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        String keyword = map.containsKey("keyword") ? map.get("keyword").toString() : "";
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";
        return ResponseEntity.ok().body(this.authGrpService.getAuthGrpUsers(keyword, plantCd, deptCd));
    }

    @PostMapping("/api/manage/authgrpuser")
    public int createAuthGrpUser(@RequestBody AuthGrpUser authGrpUser) throws Exception {
        return this.authGrpService.createAuthGrpUser(authGrpUser);
    }

    @DeleteMapping("/api/manage/authgrpuser")
    public int deleteAuthGrpUser(@RequestBody UserAuthGrp userAuthGrp) throws Exception {
        return this.authGrpService.deleteAuthGrpUser(userAuthGrp);
    }

    @GetMapping("/api/manage/authgrpdepts")
    public ResponseEntity<List<AuthGrpDept>> getAuthGrpDepts(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        String keyword = map.containsKey("keyword") ? map.get("keyword").toString() : "";
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        return ResponseEntity.ok().body(this.authGrpService.getAuthGrpDepts(keyword, plantCd));
    }

    @PostMapping("/api/manage/authgrpdept")
    public int createAuthGrpDept(@RequestBody AuthGrpDept authGrpDept) throws Exception {
        return this.authGrpService.createAuthGrpDept(authGrpDept);
    }

    @DeleteMapping("/api/manage/authgrpdept")
    public int deleteAuthGrpDept(@RequestBody DeptAuthGrp deptAuthGrp) throws Exception {
        return this.authGrpService.deleteAuthGrpDept(deptAuthGrp);
    }

}
