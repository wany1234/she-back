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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.she.common.model.DefaultParam;
import com.she.rsa.assess.service.AssessResultService;
import com.she.rsa.model.AssessResult;
import com.she.utils.RequestMapper;

/**
 * 평가계획
 */
@RestController
@RequestMapping("api/rsa/assess")
public class AssessResultController {
    @Autowired
    private AssessResultService assessResultService;

    // queryString 변환을 위한 mapper 선언
    @Autowired
    private RequestMapper requestMapper;

    /**
     * 위험성 등록부 항목 생성
     * 
     * @param parameter
     *            검색조건
     * @return 위험성 등록부 키값
     * @throws Exception
     */
    @PostMapping("/assessresult")
    public ResponseEntity<Integer> createJobRiskBook(@RequestBody List<AssessResult> assessResults) throws Exception {
        return ResponseEntity.ok().body(assessResultService.createJobRiskBook(assessResults));
    }

    /**
     * 평가대상 Jsa 목록 조회
     * 
     * @param assessPlanNo
     *            평가계획 번호
     * @param deptCd
     *            부서코드
     * @param processNo
     *            공정번호
     * @return 평가직무 목록
     * @throws Exception
     */
    @GetMapping("/assessresultsjsa")
    public ResponseEntity<List<AssessResult>> getAssessResultsJsa(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 평가계획 ID
        String assessPlanNo = map.containsKey("assessPlanNo") ? map.get("assessPlanNo").toString() : "";
        // 부서코드
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";

        String processCd = map.containsKey("processCd") ? map.get("processCd").toString() : "";

        String riskBookCheck = map.containsKey("riskBookCheck") ? map.get("riskBookCheck").toString() : "";

        String searchFlag = map.containsKey("searchFlag") ? map.get("searchFlag").toString() : "";

        return ResponseEntity.ok().body(assessResultService.getAssessResultsJsa(assessPlanNo, deptCd, processCd, riskBookCheck, searchFlag, defaultParam));
    }

}
