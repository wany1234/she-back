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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.she.common.model.DefaultParam;
import com.she.rsa.model.WorkRiskEvalImprove;
import com.she.rsa.model.WorkRiskEvalImpt;
import com.she.rsa.workRiskEval.service.WorkRiskEvalImptService;
import com.she.utils.RequestMapper;

/**
 * 작업위험성평가 팀 중요위험등록부
 */
@RestController
@RequestMapping("api/rsa/workRiskEvalImpt")
public class WorkRiskEvalImptController {

    // queryString 변환을 위한 mapper 선언
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private WorkRiskEvalImptService workRiskEvalImptService;

    /**
     * 작업위험성평가 팀/사업장 중요위험등록부 조회
     * 
     * @return 작업위험성평가 팀/사업장 중요위험등록부 목록
     * @throws Exception
     */
    @GetMapping("/workRiskEvalImptList")
    public ResponseEntity<List<WorkRiskEvalImpt>> getworkRiskEval06List(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 사업장
        String plantCd = map.containsKey("schPlantCd") ? map.get("schPlantCd").toString() : "";

        // 부서코드
        String deptCd = map.containsKey("schDeptCd") ? map.get("schDeptCd").toString() : "";

        // 퍙가구분 (정시/수기)
        String evalTypeCd = map.containsKey("schEvalType") ? map.get("schEvalType").toString() : "";

        // 주요설비
        String processNm = map.containsKey("schProcessNm") ? map.get("schProcessNm").toString() : "";

        // 작업명
        String unitWorkNm = map.containsKey("schUnitWorkNm") ? map.get("schUnitWorkNm").toString() : "";

        // 평가년도
        String[] evalYear = this.requestMapper.convertObjectListAsStringArray(map.get("schEvalYear"));

        String startYear = "";
        String endYear = "";

        if (evalYear != null && evalYear.length == 2) {
            startYear = evalYear[0];
            endYear = evalYear[1];
        }

        return ResponseEntity.ok().body(workRiskEvalImptService.getworkRiskEvalImptList(plantCd, deptCd, evalTypeCd, processNm, unitWorkNm, startYear, endYear, defaultParam));
    }
    
    /**
     * 작업위험성평가 중요위험 개선표 조회
     * 
     * @return 작업위험성평가 중요위험 개선표 목록
     * @throws Exception
     */
    @GetMapping("/workRiskEvalImproveList")
    public ResponseEntity<List<WorkRiskEvalImprove>> getworkRiskEvalImproveList(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 사업장
        String plantCd = map.containsKey("schPlantCd") ? map.get("schPlantCd").toString() : "";

        // 부서코드
        String deptCd = map.containsKey("schDeptCd") ? map.get("schDeptCd").toString() : "";

        // 퍙가구분 (정시/수기)
        String evalTypeCd = map.containsKey("schEvalType") ? map.get("schEvalType").toString() : "";

        // 주요설비
        String processNm = map.containsKey("schProcessNm") ? map.get("schProcessNm").toString() : "";

        // 작업명
        String unitWorkNm = map.containsKey("schUnitWorkNm") ? map.get("schUnitWorkNm").toString() : "";

        // 평가년도
        String[] evalYear = this.requestMapper.convertObjectListAsStringArray(map.get("schEvalYear"));

        String startYear = "";
        String endYear = "";

        if (evalYear != null && evalYear.length == 2) {
            startYear = evalYear[0];
            endYear = evalYear[1];
        }

        return ResponseEntity.ok().body(workRiskEvalImptService.getworkRiskEvalImproveList(plantCd, deptCd, evalTypeCd, processNm, unitWorkNm, startYear, endYear, defaultParam));
    }

}
