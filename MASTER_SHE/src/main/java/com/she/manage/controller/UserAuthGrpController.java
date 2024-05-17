package com.she.manage.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.she.manage.model.DeptAuthGrp;
import com.she.manage.model.DeptAuthGrps;
import com.she.manage.model.UserAuthGrp;
import com.she.manage.model.UserAuthGrps;
import com.she.manage.service.UserAuthGrpService;
import com.she.utils.RequestMapper;

/**
 * 사용자별 권한 그룹 설정 컨트롤러
 */
@RestController
public class UserAuthGrpController {
    // 사용자 권한 그룹 서비스
    @Autowired
    UserAuthGrpService userAuthGrpService;

    @Autowired
    RequestMapper requestMapper;

    /**
     * 권한에 따른 사용자/부서 정보 조회
     *
     * @param parameter
     * @return 권한에 따른 사용자/부서 정보
     */
    @GetMapping("/api/manage/authgrpuserdepts")
    public ResponseEntity<HashMap<String, Object>> getAuthGrpUserDepts(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        int authGrpNo = map.containsKey("authGrpNo") ? Integer.parseInt("".equals(map.get("authGrpNo").toString()) ? "0" : map.get("authGrpNo").toString()) : 0;

        return ResponseEntity.ok().body(userAuthGrpService.getAuthGrpUserDepts(authGrpNo));
    }

    /**
     * 사업장/부서 접근 권한에 따른 부서 조회
     *
     * @param parameter
     * @return 사업장/부서 접근 권한에 따른 부서
     */
    @GetMapping("/api/manage/authgrpplantdepts")
    public ResponseEntity<HashMap<String, Object>> getAuthGrpPlantDepts(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        int authGrpNo = map.containsKey("authGrpNo") ? Integer.parseInt("".equals(map.get("authGrpNo").toString()) ? "0" : map.get("authGrpNo").toString()) : 0;

        return ResponseEntity.ok().body(userAuthGrpService.getAuthGrpPlantDepts(authGrpNo));
    }

    /**
     * 사용자 권한 그룹 생성 (부서)
     *
     * @param userAuthGrps
     * @return 등록된 권한 그룹 개수
     * @throws Exception
     * @TODO: 사용자 권한 그룹 등록 후 권한 그룹 별 메뉴를 사용자 메뉴로 등록
     */
    @PostMapping("/api/manage/deptauth")
    public ResponseEntity<Integer> createDeptAuth(@RequestBody DeptAuthGrps deptAuthGrps) throws Exception {
        return ResponseEntity.ok().body(userAuthGrpService.createDeptAuth(deptAuthGrps));
    }

    /**
     * 사용자 권한 그룹 삭제 (부서, 사용자)
     *
     * @param userAuthGrps
     * @return 삭제된 권한 그룹 개수
     * @throws Exception
     */
    @DeleteMapping("/api/manage/deptauth")
    public ResponseEntity<Integer> deleteDeptAuth(@RequestBody List<DeptAuthGrp> deptAuthGrps) throws Exception {
        return ResponseEntity.ok().body(userAuthGrpService.deleteDeptAuth(deptAuthGrps));
    }

    /**
     * 사용자 권한 그룹 생성 (사용자)
     *
     * @param userAuthGrps
     * @return 등록된 권한 그룹 개수
     * @throws Exception
     * @TODO: 사용자 권한 그룹 등록 후 권한 그룹 별 메뉴를 사용자 메뉴로 등록
     */
    @PostMapping("/api/manage/userauth")
    public ResponseEntity<Integer> createUserAuth(@RequestBody DeptAuthGrps deptAuthGrps) throws Exception {
        return ResponseEntity.ok().body(userAuthGrpService.createUserAuth(deptAuthGrps));
    }

    /**
     * 사용자 권한 그룹 생성
     *
     * @param userAuthGrps
     * @return 등록된 권한 그룹 개수
     * @throws Exception
     * @TODO: 사용자 권한 그룹 등록 후 권한 그룹 별 메뉴를 사용자 메뉴로 등록
     */
    @PostMapping("/api/manage/userauthgrp")
    public ResponseEntity<Integer> createUserAuthGrp(@RequestBody UserAuthGrps userAuthGrps) throws Exception {
        // 사용자의 권한 그룹 등록
        return ResponseEntity.ok().body(userAuthGrpService.createUserAuthGrp(userAuthGrps));
    }

    /**
     * 사용자 권한 그룹 삭제
     *
     * @param userAuthGrps
     * @return 삭제된 권한 그룹 개수
     * @throws Exception
     */
    @DeleteMapping("/api/manage/userauthgrp")
    public ResponseEntity<Integer> deleteUserAuthGrp(@RequestBody List<UserAuthGrp> userAuthGrps) throws Exception {
        // 사용자의 권한 그룹 등록
        return ResponseEntity.ok().body(userAuthGrpService.deleteUserAuthGrp(userAuthGrps));
    }

    /**
     * 부서 권한 그룹 생성
     *
     * @param deptAuthGrps
     * @return 등록된 권한 그룹 개수
     * @throws Exception
     * @TODO: 사용자 권한 그룹 등록 후 권한 그룹 별 메뉴를 사용자 메뉴로 등록
     */
    @PostMapping("/api/manage/deptauthgrp")
    public ResponseEntity<Integer> createDeptAuthGrp(@RequestBody DeptAuthGrps deptAuthGrps) throws Exception {
        // 사용자의 권한 그룹 등록
        return ResponseEntity.ok().body(userAuthGrpService.createDeptAuthGrp(deptAuthGrps));
    }

    /**
     * 부서 권한 그룹 삭제
     *
     * @param deptAuthGrps
     * @return 삭제된 권한 그룹 개수
     * @throws Exception
     */
    @DeleteMapping("/api/manage/deptauthgrp")
    public ResponseEntity<Integer> deleteDeptAuthGrp(@RequestBody List<DeptAuthGrp> deptAuthGrps) throws Exception {
        // 사용자의 권한 그룹 등록
        return ResponseEntity.ok().body(userAuthGrpService.deleteDeptAuthGrpAll(deptAuthGrps));
    }

    /**
     * 사업장/부서 접근 권한에 따른 부서 제외시 하위부서 조회
     *
     * @param parameter
     * @return 사업장/부서 접근 권한에 따른 부서
     */
    @GetMapping("/api/manage/authGrpSubDept")
    public ResponseEntity<List<DeptAuthGrp>> getAuthGrpSubDepts(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        String[] deptCds = map.get("deptCds").toString().substring(1).split(",");

        return ResponseEntity.ok().body(userAuthGrpService.getAuthGrpSubDepts(deptCds));
    }
}
