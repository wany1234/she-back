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
package com.she.mgt.sapaDashboard.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.she.common.model.DefaultParam;
import com.she.mgt.sapaDashboard.service.SapaDashboardService;
import com.she.utils.RequestMapper;

/**
 * 평가계획
 */
@RestController
@RequestMapping("api/mgt/sapaDashboard")
public class SapaDashboardController {
    @Autowired
    private SapaDashboardService sapaDashboardService;

    // queryString 변환을 위한 mapper 선언
    @Autowired
    private RequestMapper requestMapper;

    private static final Logger logger = LoggerFactory.getLogger(SapaDashboardController.class);

    /**
     * 대시보드 개선사항 목록 cnt
     *
     * @param parameter
     *            검색조건
     * @return 대시보드 개선사항 목록 cnt
     * @throws Exception
     */
    @GetMapping("/imprLists")
    public ResponseEntity<Map<String, Object>> getImprCnt(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 평가계획 ID
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        String startDt = map.containsKey("startDt") ? map.get("startDt").toString() : "";
        String endDt = map.containsKey("endDt") ? map.get("endDt").toString() : "";
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";
        String deptSubYn = map.containsKey("deptSubYn") ? map.get("deptSubYn").toString() : "";

        return ResponseEntity.ok().body(sapaDashboardService.getImprCnt(plantCd, startDt, endDt, deptCd, deptSubYn, defaultParam));
    }
}