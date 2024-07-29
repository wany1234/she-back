/**
 * Copyright (C) 2019, 2019 All Right Reserved, http://www.yullin.com/
 *
 * SHE Business can not be copied and/or distributed without the express
 * permission of Yullin Technologies
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 */

package com.she.health.checkup.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.she.health.checkup.service.CheckupPlanService;
import com.she.health.model.CheckupPlan;
import com.she.health.model.CheckupPlanOrg;
import com.she.health.model.CheckupUser;
import com.she.utils.RequestMapper;

/**
 * 건강검진계획
 *
 */
@RestController
@RequestMapping("api/hea/checkup")
public class CheckupPlanController {
    // TODO : queryString 변환을 위한 mapper 선언
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private CheckupPlanService checkupPlanService;

    /**
     * 건강검진계획 조회
     *
     * @param parameter
     *            검색조건
     * @return 건강검진계획 목록
     */
    @GetMapping("/checkupplans")
    public ResponseEntity<List<CheckupPlan>> getCheckupPlans(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // from
        String startDate = map.containsKey("startDate") ? map.get("startDate").toString() : "";
        // to
        String endDate = map.containsKey("endDate") ? map.get("endDate").toString() : "";
        // 사업장 코드
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 검진종류
        String heaCheckupClassCd = map.containsKey("heaCheckupClassCd") ? map.get("heaCheckupClassCd").toString() : "";
        String heaCheckupPlanNm = map.containsKey("heaCheckupPlanNm") ? map.get("heaCheckupPlanNm").toString() : "";

        List<CheckupPlan> checkupPlans = this.checkupPlanService.getCheckupPlans(startDate, endDate, plantCd, heaCheckupClassCd, heaCheckupPlanNm);

        return ResponseEntity.ok().body(checkupPlans);
    }

    /**
     * 건강검진계획 상세 조회
     *
     * @param heaCheckupPlanNo
     * @return 건강검진계획
     * @throws Exception
     */
    @GetMapping("/checkupplan/{heaCheckupPlanNo}")
    public ResponseEntity<CheckupPlan> getCheckupPlan(@PathVariable(name = "heaCheckupPlanNo") int heaCheckupPlanNo) throws Exception {
        return ResponseEntity.ok().body(this.checkupPlanService.getCheckupPlan(heaCheckupPlanNo));
    }

    /**
     * 건강검진계획 생성
     *
     * @param checkupPlan
     *            건강검진계획
     * @return 건강검진계획번호
     * @throws Exception
     */
    @PostMapping("/checkupplan")
    public ResponseEntity<Integer> createCheckupPlan(@RequestBody CheckupPlan checkupPlan) throws Exception {
        Integer checkupPlanNo = this.checkupPlanService.createCheckupPlan(checkupPlan);

        return ResponseEntity.ok().body(checkupPlanNo);
    }

    /**
     * 건강검진계획 수정
     *
     * @param checkupPlan
     *            건강검진계획
     * @return 변경 행 수
     * @throws Exception
     */
    @PutMapping("/checkupplan")
    public ResponseEntity<Integer> updateCheckupPlan(@RequestBody CheckupPlan checkupPlan) throws Exception {
        Integer count = this.checkupPlanService.updateCheckupPlan(checkupPlan);

        return ResponseEntity.ok().body(count);
    }

    /**
     * 건강검진계획 삭제
     *
     * @param checkupPlan
     *            건강검진계획
     * @return 삭제 행 수
     * @throws Exception
     */
    @DeleteMapping("/checkupplan")
    public ResponseEntity<Integer> deleteCheckupPlan(@RequestBody CheckupPlan checkupPlan) throws Exception {
        return ResponseEntity.ok().body(this.checkupPlanService.deleteCheckupPlan(checkupPlan));
    }

    /**
     * 건강검진계획별 대상자 지정
     *
     * @param checkupUsers
     *            건강검진 대상자 목록
     * @return 생성행수
     * @throws Exception
     */
    @PostMapping("/checkupusers")
    public ResponseEntity<Integer> createCheckupUsers(@RequestBody List<CheckupUser> checkupUsers) throws Exception {
        return ResponseEntity.ok().body(this.checkupPlanService.createCheckupUsers(checkupUsers));
    }

    /**
     * 건강검진계획별 전체 대상자 지정
     * 
     * @param parameter
     * @return
     * @throws Exception
     */
    @GetMapping("/checkupallusers")
    public ResponseEntity<Integer> addCheckupAllUsers(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        int heaCheckupPlanNo = map.containsKey("heaCheckupPlanNo") ? Integer.parseInt(map.get("heaCheckupPlanNo").toString()) : 0;
        String processCd = map.containsKey("processCd") ? map.get("processCd").toString() : "";
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";
        String userId = map.containsKey("userId") ? map.get("userId").toString() : "";
        String userNm = map.containsKey("userNm") ? map.get("userNm").toString() : "";
        String prevYearCheckupUserYn = map.containsKey("prevYearCheckupUserYn") ? map.get("prevYearCheckupUserYn").toString() : "";
        int age = map.containsKey("age") ? Integer.parseInt(map.get("age").toString()) : 0;
        String gender = map.containsKey("gender") ? map.get("gender").toString() : "";
        String specialTargetChemYn = map.containsKey("specialTargetChemYn") ? map.get("specialTargetChemYn").toString() : "";
        String specialTargetPhyYn = map.containsKey("specialTargetPhyYn") ? map.get("specialTargetPhyYn").toString() : "";
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";

        int pageNumber = 1;
        int pageSize = 100000;

        String orderByExpression = map.containsKey("orderByExpression") ? map.get("orderByExpression").toString() : "";

        int result = this.checkupPlanService.addCheckupAllUsers(heaCheckupPlanNo, processCd, deptCd, userId, userNm, prevYearCheckupUserYn, age, gender, specialTargetChemYn, specialTargetPhyYn, plantCd, pageNumber, pageSize, orderByExpression);

        return ResponseEntity.ok().body(result);
    }

    /**
     * 건강검진계획별 대상자 해제
     *
     * @param checkupUsers
     *            건강검진 대상자 목록
     * @return 생성행수
     * @throws Exception
     */
    @DeleteMapping("/checkupusers")
    public ResponseEntity<Integer> deleteCheckupUsers(@RequestBody List<CheckupUser> checkupUsers) throws Exception {
        return ResponseEntity.ok().body(this.checkupPlanService.deleteCheckupUsers(checkupUsers));
    }

    /**
     * 검색조건 해당 대상자 조회
     *
     * @param parameter
     *            검색조건
     * @return 대상자목록
     * @throws Exception
     */
    @GetMapping("/checkupusers")
    public ResponseEntity<Map<String, Object>> getCheckupUsers(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        int heaCheckupPlanNo = map.containsKey("heaCheckupPlanNo") ? Integer.parseInt(map.get("heaCheckupPlanNo").toString()) : 0;
        int processNo = map.containsKey("processCd") ? Integer.parseInt(map.get("processNo").toString()) : 0;
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";
        String userId = map.containsKey("userId") ? map.get("userId").toString() : "";
        String userNm = map.containsKey("userNm") ? map.get("userNm").toString() : "";
        String age = map.containsKey("age") ? map.get("age").toString() : "";
        String gender = map.containsKey("gender") ? map.get("gender").toString() : "";
        String checkUpResult = map.containsKey("checkUpResult") ? map.get("checkUpResult").toString() : "";
        System.out.println("컨트롤러");
        System.out.println(checkUpResult);
        System.out.println(checkUpResult.getClass().getName());
        if (checkUpResult != "" && Integer.parseInt(checkUpResult) == 1) {
            System.out.println("1이다");
            checkUpResult = "등록";
        } else if (checkUpResult != "" && Integer.parseInt(checkUpResult) == 2) {
            System.out.println("2이다");
            checkUpResult = "미등록";
        }

        int[] heaCheckedOrgNos = this.requestMapper.convertObjectListAsIntArray(map.get("heaCheckedOrgNos"));
        int[] heaCheckupOrgNos = this.requestMapper.convertObjectListAsIntArray(map.get("heaCheckupOrgNos"));
        String statusYn = map.containsKey("statusYn") ? map.get("statusYn").toString() : "";

        int pageNumber = map.containsKey("pageNumber") ? Integer.parseInt(map.get("pageNumber").toString()) : 1;
        int pageSize = map.containsKey("pageSize") ? Integer.parseInt(map.get("pageSize").toString()) : 10000;

        String orderByExpression = map.containsKey("orderByExpression") ? map.get("orderByExpression").toString() : "";

        Map<String, Object> returnMap = new HashMap<String, Object>();
        List<CheckupUser> body = this.checkupPlanService.getCheckupUsers(heaCheckupPlanNo, processNo, deptCd, userId, userNm, heaCheckedOrgNos, heaCheckupOrgNos, statusYn, age, gender, checkUpResult, pageNumber, pageSize, orderByExpression);
        List<CheckupUser> totItems = this.checkupPlanService.getCheckupUsersNoPaging(heaCheckupPlanNo, processNo, deptCd, userId, userNm, heaCheckedOrgNos, heaCheckupOrgNos, statusYn, age, gender, pageNumber, pageSize, orderByExpression);

        returnMap.put("items", body);
        returnMap.put("totItems", totItems);
        returnMap.put("count", body != null && body.size() > 0 ? body.get(0).getTotalCnt() : 0);

        return ResponseEntity.ok().body(returnMap);
    }

    /**
     * 검색조건 해당 대상자 조회
     *
     * @param parameter
     *            검색조건
     * @return 대상자목록
     * @throws Exception
     */
    @GetMapping("/checkupusersnotarget")
    public ResponseEntity<Map<String, Object>> getCheckupUsersNoTarget(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        int heaCheckupPlanNo = map.containsKey("heaCheckupPlanNo") ? Integer.parseInt(map.get("heaCheckupPlanNo").toString()) : 0;
        String processCd = map.containsKey("processCd") ? map.get("processCd").toString() : "";
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";
        String userId = map.containsKey("userId") ? map.get("userId").toString() : "";
        String userNm = map.containsKey("userNm") ? map.get("userNm").toString() : "";
        String prevYearCheckupUserYn = map.containsKey("prevYearCheckupUserYn") ? map.get("prevYearCheckupUserYn").toString() : "";
        int age = map.containsKey("age") ? Integer.parseInt(map.get("age").toString()) : 0;
        String gender = map.containsKey("gender") ? map.get("gender").toString() : "";
        String specialTargetChemYn = map.containsKey("specialTargetChemYn") ? map.get("specialTargetChemYn").toString() : "";
        String specialTargetPhyYn = map.containsKey("specialTargetPhyYn") ? map.get("specialTargetPhyYn").toString() : "";
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";

        int pageNumber = map.containsKey("pageNumber") ? Integer.parseInt(map.get("pageNumber").toString()) : 1;
        int pageSize = map.containsKey("pageSize") ? Integer.parseInt(map.get("pageSize").toString()) : 10000;

        String orderByExpression = map.containsKey("orderByExpression") ? map.get("orderByExpression").toString() : "";

        Map<String, Object> returnMap = new HashMap<String, Object>();
        List<CheckupUser> body = this.checkupPlanService.getCheckupUsersNoTarget(heaCheckupPlanNo, processCd, deptCd, userId, userNm, prevYearCheckupUserYn, age, gender, specialTargetChemYn, specialTargetPhyYn, plantCd, pageNumber, pageSize, orderByExpression);

        List<CheckupUser> totData = this.checkupPlanService.getCheckupUsersNoTargetNoPage(heaCheckupPlanNo, processCd, deptCd, userId, userNm, prevYearCheckupUserYn, age, gender, specialTargetChemYn, specialTargetPhyYn, plantCd, pageNumber, pageSize, orderByExpression);

        returnMap.put("items", body);
        returnMap.put("totItems", totData);
        returnMap.put("count", body != null && body.size() > 0 ? body.get(0).getTotalCnt() : 0);

        return ResponseEntity.ok().body(returnMap);
    }

    /**
     * 건강검진계획별 기관 조회
     *
     * @param parameter
     *            (건강검진계획번호, 건강검진기관번호, 기준날짜)
     * @return 건강검진계획별 기관 목록
     */
    @GetMapping("/checkupplanorgs")
    public ResponseEntity<List<CheckupPlanOrg>> getCheckupPlanOrgs(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = requestMapper.convertAsParameter(parameter);

        // 건강검진계획번호
        int heaCheckupPlanNo = map.containsKey("heaCheckupPlanNo") ? Integer.parseInt(map.get("heaCheckupPlanNo").toString()) : 0;
        // 건강검진기관번호
        int heaCheckupOrgNo = map.containsKey("heaCheckupOrgNo") ? Integer.parseInt(map.get("heaCheckupOrgNo").toString()) : 0;
        // 기준 날짜
        String standardYmd = map.containsKey("standardYmd") ? map.get("standardYmd").toString() : "";

        List<CheckupPlanOrg> checkupPlanOrgs = this.checkupPlanService.getCheckupPlanOrgs(heaCheckupPlanNo, heaCheckupOrgNo, standardYmd);

        return ResponseEntity.ok().body(checkupPlanOrgs);
    }
}
