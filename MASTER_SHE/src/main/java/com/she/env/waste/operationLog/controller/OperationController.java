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
package com.she.env.waste.operationLog.controller;

import java.util.HashMap;
import java.util.List;

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
import com.she.env.waste.operationLog.service.OperationService;
import com.she.utils.RequestMapper;

/**
 * 폐기물 관리대장
 *
 */
@RestController("EwstOperationController")
@RequestMapping("api/env/waste/operation")
public class OperationController {
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private OperationService operationService;

    private static final Logger logger = LoggerFactory.getLogger(OperationController.class);

    /**
     * 폐기물 운영현황 조회
     *
     * @param parameter
     *            검색조건
     * @return 폐기물 운영현황
     * @throws Exception
     */
    @GetMapping("/wstDispoStatus")
    public ResponseEntity<List<HashMap<String, Object>>> getOperationStatus(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = requestMapper.convertAsParameter(parameter);
        String ewstWasteNo = map.containsKey("ewstWasteNo") ? map.get("ewstWasteNo").toString() : "";
        String ewstClassCd = map.containsKey("ewstClassCd") ? map.get("ewstClassCd").toString() : "";
        String measureYear = map.containsKey("measureYear") ? map.get("measureYear").toString() : "";
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        String totalTypeCd = map.containsKey("totalTypeCd") ? map.get("totalTypeCd").toString() : "";

        return ResponseEntity.ok().body(this.operationService.getOperationStatus(ewstWasteNo, ewstClassCd, measureYear, plantCd, totalTypeCd, defaultParam));
    }

    /**
     * 폐기물 운영현황 조회
     *
     * @param parameter
     *            검색조건
     * @return 폐기물 운영현황
     * @throws Exception
     */
    @GetMapping("/wstBasicUnitStatus")
    public ResponseEntity<List<HashMap<String, Object>>> getWaterUnitStatus(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = requestMapper.convertAsParameter(parameter);

        String measureYear = map.containsKey("measureYear") ? map.get("measureYear").toString() : "";
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";

        return ResponseEntity.ok().body(this.operationService.getWaterUnitStatus(measureYear, plantCd, defaultParam));
    }

}
