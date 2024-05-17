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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.she.rsa.assess.service.RiskAssessDeptService;
import com.she.rsa.model.RiskAssessDept;
import com.she.utils.RequestMapper;

/**
 * 대상부서
 */
@RestController
@RequestMapping("api/rsa/assess")
public class RiskAssessDeptController {
    @Autowired
    private RiskAssessDeptService riskAssessDeptService;

    // queryString 변환을 위한 mapper 선언
    @Autowired
    private RequestMapper requestMapper;

    /**
     * 대상부서 조회
     * 
     * @param parameter
     *            검색조건
     * @return 대상부서 목록
     * @throws Exception
     */
    @GetMapping("/riskassessdepts")
    public ResponseEntity<List<RiskAssessDept>> getRiskAssessDepts(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        // 평가계획 번호
        int assessPlanNo = map.containsKey("assessPlanNo") ? Integer.parseInt("".equals(map.get("assessPlanNo").toString()) ? "0" : map.get("assessPlanNo").toString()) : 0;

        return ResponseEntity.ok().body(riskAssessDeptService.getRiskAssessDepts(assessPlanNo));
    }

    /**
     * 대상부서 상세 조회
     * 
     * @param assessPlanNo
     *            평가계획 번호
     * @param deptCd
     *            부서 코드
     * @param userId
     *            부서담당자ID
     * @return 대상부서
     * @throws Exception
     */
    @GetMapping("/riskassessdept")
    public ResponseEntity<RiskAssessDept> getRiskAssessDept(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = requestMapper.convertAsParameter(parameter);
        int assessPlanNo = map.containsKey("assessPlanNo") ? Integer.parseInt("".equals(map.get("assessPlanNo").toString()) ? "0" : map.get("assessPlanNo").toString()) : 0;
        String processCd = map.containsKey("processCd") ? map.get("processCd").toString() : "";
        String userId = map.containsKey("userId") ? map.get("userId").toString() : "";

        return ResponseEntity.ok().body(riskAssessDeptService.getRiskAssessDept(assessPlanNo, processCd, userId));
    }

    /**
     * 대상부서 신규등록
     * 
     * @param AssessPlan
     *            대상부서
     * @return 변경 행 수 (복합키이기 떄문에 이미 키를 가지고 있다)
     * @throws Exception
     */
    @PostMapping("/riskassessdept")
    public ResponseEntity<Integer> createRiskAssessDept(@RequestBody RiskAssessDept riskAssessDept) throws Exception {
        return ResponseEntity.ok().body(riskAssessDeptService.createRiskAssessDept(riskAssessDept));
    }

    /**
     * 대상부서 신규등록
     * 
     * @param AssessPlan
     *            대상부서
     * @return 변경 행 수 (복합키이기 떄문에 이미 키를 가지고 있다)
     * @throws Exception
     */
    @PostMapping("/multi/riskassessdept")
    public ResponseEntity<Integer> createRiskAssessDeptMulti(@RequestBody List<RiskAssessDept> riskAssessDepts) throws Exception {
        return ResponseEntity.ok().body(riskAssessDeptService.createRiskAssessDeptMulti(riskAssessDepts));
    }

    /**
     * 대상부서 수정
     * 
     * @param AssessPlan
     *            대상부서
     * @return 변경 행 수
     * @throws Exception
     */
    @PutMapping("/riskassessdept")
    public ResponseEntity<Integer> updateRiskAssessDept(@RequestBody RiskAssessDept riskAssessDept) throws Exception {
        return ResponseEntity.ok().body(riskAssessDeptService.updateRiskAssessDept(riskAssessDept));
    }

    /**
     * 대상부서 삭제
     * 
     * @param AssessPlan
     *            대상부서
     * @return 변경 행 수
     * @throws Exception
     */
    @DeleteMapping("/riskassessdept")
    public ResponseEntity<Integer> deleteRiskAssessDept(@RequestBody RiskAssessDept riskAssessDept) throws Exception {
        return ResponseEntity.ok().body(riskAssessDeptService.deleteRiskAssessDept(riskAssessDept.getAssessPlanNo(), riskAssessDept.getProcessCd()));
    }

    /**
     * 대상부서 삭제
     * 
     * @param AssessPlan
     *            대상부서
     * @return 변경 행 수
     * @throws Exception
     */
    @DeleteMapping("/multi/riskassessdept")
    public ResponseEntity<Integer> deleteRiskAssessDeptMulti(@RequestBody List<RiskAssessDept> riskAssessDepts) throws Exception {
        return ResponseEntity.ok().body(riskAssessDeptService.deleteRiskAssessDeptMulti(riskAssessDepts));
    }

}
