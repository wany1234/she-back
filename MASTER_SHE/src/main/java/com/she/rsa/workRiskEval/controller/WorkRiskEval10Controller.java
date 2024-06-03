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
package com.she.rsa.workRiskEval.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.she.common.model.DefaultParam;
import com.she.rsa.model.WorkRiskEval10DeptProcess;
import com.she.rsa.workRiskEval.service.WorkRiskEval10Service;
import com.she.utils.RequestMapper;

/**
 * 작업위험성평가 > 기준정보 > 작업위험성평가 공정별 부서
 */
@RestController
@RequestMapping("api/rsa/workRiskEval10")
public class WorkRiskEval10Controller {

    // queryString 변환을 위한 mapper 선언
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private WorkRiskEval10Service workRiskEval10Service;

    /**
     * 작업위험성평가 작업위험성평가 공정별 부서 조회
     * 
     * @return 작업위험성평가 작업위험성평가 공정별 부서 목록
     * @throws Exception
     */
    @GetMapping("/workRiskEval10List")
    public ResponseEntity<List<WorkRiskEval10DeptProcess>> getworkRiskEval10Lists(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 사업장
        String plantCd = map.containsKey("schPlantCd") ? map.get("schPlantCd").toString() : "";

        // 작업명
        String deptNm = map.containsKey("schDeptNm") ? map.get("schDeptNm").toString() : "";

        return ResponseEntity.ok().body(workRiskEval10Service.getworkRiskEval10Lists(plantCd, deptNm, defaultParam));
    }

    /**
     * 작업위험성평가 작업위험성평가 공정별 부서
     * 
     * @param parameter
     *            검색조건
     * @return 작업위험성평가 공정별 부서 상세조회
     * @throws Exception
     */
    @GetMapping("/workRiskEval10Info/{deptCd}")
    public ResponseEntity<WorkRiskEval10DeptProcess> getWorkRiskEval10info(@PathVariable("deptCd") String deptCd, @ModelAttribute DefaultParam defaultParam) throws Exception {
        return ResponseEntity.ok().body(workRiskEval10Service.getWorkRiskEval10Info(deptCd, defaultParam));
    }

}
