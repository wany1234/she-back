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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.she.common.model.DefaultParam;
import com.she.impr.model.ImprAct;
import com.she.rsa.assess.service.AssessTotalService;
import com.she.rsa.model.AssessTotal;
import com.she.utils.RequestMapper;

/**
 * 평가계획
 */
@RestController
@RequestMapping("api/rsa/assess")
public class AssessTotalController {
    @Autowired
    private AssessTotalService assessTotalService;

    // queryString 변환을 위한 mapper 선언
    @Autowired
    private RequestMapper requestMapper;

    /**
     * 평가결과 조회
     * 
     * @param parameter
     *            검색조건
     * @return 평가직무 목록
     * @throws Exception
     */
    @GetMapping("/assesstotals")
    public ResponseEntity<List<AssessTotal>> getAssessTotals(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        String userId = map.containsKey("userId") ? map.get("userId").toString() : "";
        String assessNm = map.containsKey("assessNm") ? map.get("assessNm").toString() : "";
        String totalDivision = map.containsKey("totalDivision") ? map.get("totalDivision").toString() : "";
        String flag = map.containsKey("flag") ? map.get("flag").toString() : "";
        String[] assessYear = this.requestMapper.convertObjectListAsStringArray(map.get("assessYear"));

        String startYear = "";
        String endYear = "";

        if (assessYear != null && assessYear.length == 2) {
            startYear = assessYear[0];
            endYear = assessYear[1];
        }

        return ResponseEntity.ok().body(assessTotalService.getAssessTotals(plantCd,userId, assessNm, startYear, endYear, totalDivision, flag, defaultParam));
    }

    /**
     * 개선진행현황 조회
     * 
     * @param parameter
     *            검색조건
     * @return 개선진행현황 목록
     * @throws Exception
     */
    @GetMapping("/impracts")
    public ResponseEntity<List<ImprAct>> getImprActs(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        String assessPlanNo = map.containsKey("assessPlanNo") ? map.get("assessPlanNo").toString() : "";
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";
        String processCd = map.containsKey("processCd") ? map.get("processCd").toString() : "";
        String assessTypeNm = map.containsKey("assessTypeNm") ? map.get("assessTypeNm").toString() : "";

        return ResponseEntity.ok().body(assessTotalService.getImprActs(assessPlanNo, deptCd, processCd, assessTypeNm, defaultParam));
    }

    /**
     * 평가대상 직무 조회 (JSA)
     * 
     * @param parameter
     *            검색조건
     * @return 개선진행현황 목록
     * @throws Exception
     */
    @GetMapping("/assessjob")
    public ResponseEntity<List<AssessTotal>> getAssessJob(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        String assessPlanNo = map.containsKey("assessPlanNo") ? map.get("assessPlanNo").toString() : "";
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";
        String processCd = map.containsKey("processCd") ? map.get("processCd").toString() : "";

        return ResponseEntity.ok().body(assessTotalService.getAssessJob(assessPlanNo, deptCd, processCd, defaultParam));
    }
}
