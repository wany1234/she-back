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
package com.she.rsa.assessHazop.controller;

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
import com.she.rsa.assessHazop.service.AssessPlanProcessService;
import com.she.rsa.model.AssessPlanHazop;
import com.she.utils.RequestMapper;

/**
 * 평가계획
 */
@RestController
@RequestMapping("api/rsa/assess")
public class AssessPlanProcessController {
    @Autowired
    private AssessPlanProcessService assessPlanProcessService;

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
    @GetMapping("/assessplans/hazop")
    public ResponseEntity<List<AssessPlanHazop>> getAssessPlanHazops(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 사업장
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 쳥가년도
        String[] assessYear = this.requestMapper.convertObjectListAsStringArray(map.get("assessYear"));

        String startYear = "";
        String endYear = "";

        if (assessYear != null && assessYear.length == 2) {
            startYear = assessYear[0];
            endYear = assessYear[1];
        }

        // 진행상태
        String assessStepCd = map.containsKey("assessStepCd") ? map.get("assessStepCd").toString() : "";
        // 담당자
        String userId = map.containsKey("userId") ? map.get("userId").toString() : "";

        /* 평가계획 검색 */
        return ResponseEntity.ok().body(assessPlanProcessService.getAssessPlanHazops(plantCd, startYear, endYear, assessStepCd, userId, defaultParam));
    }

}
