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
package com.she.env.waste.operationStatus.controller;

import java.util.HashMap;
import java.util.List;

import com.she.common.model.DefaultParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.she.env.waste.model.WesteOperationStatus;
import com.she.env.waste.operationStatus.service.WstGenStatusService;
import com.she.utils.RequestMapper;

/**
 * 폐기물 관리대장
 *
 */
@RestController("wstGenStatusController")
@RequestMapping("api/env/waste/operationStatus")
public class WstGenStatusController {
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private WstGenStatusService wstGenStatusService;

    /**
     * 폐기물 발생량 조회
     *
     * @param parameter
     *            검색조건
     * @return 폐기물 발생량 목록
     * @throws Exception
     */
    @GetMapping("/wstGenStatus")
    public ResponseEntity<List<WesteOperationStatus>> getOperationLogAmgGens(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = requestMapper.convertAsParameter(parameter);

        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        String measureYear = map.containsKey("measureYear") ? map.get("measureYear").toString() : "";
        String ewstClassCd = map.containsKey("ewstClassCd") ? map.get("ewstClassCd").toString() : "";
        String ewstWasteNo = map.containsKey("ewstWasteNo") ? map.get("ewstWasteNo").toString() : "";
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";

        return ResponseEntity.ok().body(wstGenStatusService.getOperationLogAmgGens(plantCd, measureYear, ewstClassCd, ewstWasteNo, deptCd, defaultParam));
    }

}
