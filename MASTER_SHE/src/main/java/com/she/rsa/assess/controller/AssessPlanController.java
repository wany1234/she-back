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
package com.she.rsa.assess.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.she.common.model.DefaultParam;
import com.she.rsa.assess.service.AssessPlanService;
import com.she.rsa.model.AssessPlan;
import com.she.utils.RequestMapper;

/**
 * 평가계획
 */
@RestController
@RequestMapping("api/rsa/assess")
public class AssessPlanController {
    @Autowired
    private AssessPlanService assessPlanService;

    // queryString 변환을 위한 mapper 선언
    @Autowired
    private RequestMapper requestMapper;

    /**
     * 평가계획 조회
     *
     * @param parameter
     *            검색조건
     * @return 평가계획 목록
     * @throws Exception
     */
    @GetMapping("/assessplans")
    public ResponseEntity<List<AssessPlan>> getAssessPlans(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 사업장
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 평가명
        String assessNm = map.containsKey("assessNm") ? map.get("assessNm").toString() : "";
        // 정시/수기
        String regRegdem = map.containsKey("regRegdem") ? map.get("regRegdem").toString() : "";
        // 쳥가년도
        String[] assessYear = this.requestMapper.convertObjectListAsStringArray(map.get("assessYear"));

        String startYear = "";
        String endYear = "";

        if (assessYear != null && assessYear.length == 2) {
            startYear = assessYear[0];
            endYear = assessYear[1];
        }

        // 진행상태
        String assessStatus = map.containsKey("assessStatus") ? map.get("assessStatus").toString() : "";
        // 평가기간
        String[] assessPriod = this.requestMapper.convertObjectListAsStringArray(map.get("assessPriod"));

        String startDate = "";
        String endDate = "";

        if (assessPriod != null && assessPriod.length == 2) {
            startDate = assessPriod[0];
            endDate = assessPriod[1];
        }
        // 위험성평가 분류
        String assessGroupCd = map.containsKey("assessGroupCd") ? map.get("assessGroupCd").toString() : "";
        // 평가기법
        String assessTypeCd = map.containsKey("assessTypeCd") ? map.get("assessTypeCd").toString() : "";
        // 담당자
        String userId = map.containsKey("userId") ? map.get("userId").toString() : "";
        String userNm = map.containsKey("userNm") ? map.get("userNm").toString() : "";
        // 담당부서
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";
        // 하위부서 포함여부
        String deptSubYn = map.containsKey("deptSubYn") ? map.get("deptSubYn").toString() : "Y";

        /* 평가계획 검색 */
        return ResponseEntity.ok().body(assessPlanService.getAssessPlans(plantCd, deptCd, deptSubYn, userId, assessStatus, startDate, endDate, assessGroupCd, assessTypeCd, userNm, assessNm, regRegdem, startYear, endYear, defaultParam));
    }

    /**
     * 평가계획 조회
     *
     * @param parameter
     *            검색조건
     * @return 평가계획 목록
     * @throws Exception
     */
    @GetMapping("/assessplanresults")
    public ResponseEntity<List<AssessPlan>> getAssessPlanResults(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 사업장
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 평가명
        String assessNm = map.containsKey("assessNm") ? map.get("assessNm").toString() : "";
        // 정시/수기
        String regRegdem = map.containsKey("regRegdem") ? map.get("regRegdem").toString() : "";
        // 쳥가년도
        String[] assessYear = this.requestMapper.convertObjectListAsStringArray(map.get("assessYear"));

        String startYear = "";
        String endYear = "";

        if (assessYear != null && assessYear.length == 2) {
            startYear = assessYear[0];
            endYear = assessYear[1];
        }

        // 진행상태
        String assessStatus = map.containsKey("assessStatus") ? map.get("assessStatus").toString() : "";
        // 평가기간
        String[] assessPriod = this.requestMapper.convertObjectListAsStringArray(map.get("assessPriod"));

        String startDate = "";
        String endDate = "";

        if (assessPriod != null && assessPriod.length == 2) {
            startDate = assessPriod[0];
            endDate = assessPriod[1];
        }
        // 위험성평가 분류
        String assessGroupCd = map.containsKey("assessGroupCd") ? map.get("assessGroupCd").toString() : "";
        // 평가기법
        String assessTypeCd = map.containsKey("assessTypeCd") ? map.get("assessTypeCd").toString() : "";
        // 담당자
        String userNm = map.containsKey("userNm") ? map.get("userNm").toString() : "";
        // 담당자
        String userId = map.containsKey("userId") ? map.get("userId").toString() : "";
        // 담당자
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";
        // 하위부서 포함여부
        String deptSubYn = map.containsKey("deptSubYn") ? map.get("deptSubYn").toString() : "Y";

        return ResponseEntity.ok().body(assessPlanService.getAssessPlanResults(plantCd, assessStatus, userId, deptCd, deptSubYn, startDate, endDate, assessGroupCd, assessTypeCd, userNm, assessNm, regRegdem, startYear, endYear, defaultParam));
    }

    /**
     * 평가계획 상세 조회
     *
     * @param assessPlanNo
     *            평가계획 번호
     * @return 평가계획
     * @throws Exception
     */
    @GetMapping("/assessplan/{assessPlanNo}")
    public ResponseEntity<AssessPlan> getAssessPlan(@PathVariable(name = "assessPlanNo") int assessPlanNo, @ModelAttribute DefaultParam defaultParam) throws Exception {
        return ResponseEntity.ok().body(assessPlanService.getAssessPlan(assessPlanNo, defaultParam));
    }

    /**
     * 평가계획 신규등록
     *
     * @param AssessPlan
     *            평가계획
     * @return 평가계획 번호
     * @throws Exception
     */
    @PostMapping("/assessplan")
    public ResponseEntity<Integer> createAssessPlan(@RequestBody AssessPlan assessPlan) throws Exception {
        return ResponseEntity.ok().body(assessPlanService.createAssessPlan(assessPlan) > 0 ? assessPlan.getAssessPlanNo() : 0);
    }

    /**
     * 평가계획 수정
     *
     * @param AssessPlan
     *            평가계획
     * @return 변경 행 수
     * @throws Exception
     */
    @PutMapping("/assessplan")
    public ResponseEntity<Integer> updateAssessPlan(@RequestBody AssessPlan assessPlan) throws Exception {
        return ResponseEntity.ok().body(assessPlanService.updateAssessPlan(assessPlan) > 0 ? assessPlan.getAssessPlanNo() : 0);
    }

}
