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
package com.she.psm.processFacility.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.she.psm.processFacility.service.SafetyValveService;
import com.she.psm.model.SafetyValve;
import com.she.utils.RequestMapper;

/**
 * 공정시설정보 - 안전밸브 및 파열판
 */
@RestController
@RequestMapping("api/psm/processFacility")
public class SafetyValveController {
    @Autowired
    private SafetyValveService safetyValveService;

    // queryString 변환을 위한 mapper 선언
    @Autowired
    private RequestMapper requestMapper;

    /**
     * 안전밸브 및 파열판 조회
     * 
     * @param parameter
     *            검색조건
     * @return 안전밸브 및 파열판 목록
     * @throws Exception
     */
    @GetMapping("/safetyvalves")
    public ResponseEntity<List<SafetyValve>> getSafetyValves(@RequestParam HashMap<String, Object> parameter)
            throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 사업장
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 공정
        // int processNo = map.containsKey("processNo")
        // ? Integer.parseInt("".equals(map.get("processNo").toString()) ? "0" :
        // map.get("processNo").toString())
        // : 0;
        // 공정
        String processCd = map.containsKey("processCd") ? map.get("processCd").toString() : "";
        // 동력기계번호
        String safFacilityCd = map.containsKey("safFacilityCd") ? map.get("safFacilityCd").toString() : "";
        // 동력기계명
        String facilityNm = map.containsKey("facilityNm") ? map.get("facilityNm").toString() : "";

        List<SafetyValve> safetyValves = safetyValveService.getSafetyValves(plantCd, processCd, safFacilityCd,
                facilityNm);
        return ResponseEntity.ok().body(safetyValves);
    }

    /**
     * 안전밸브 및 파열판 상세 조회
     * 
     * @param parameter
     *            검색조건
     * @return 안전밸브 및 파열판 목록
     * @throws Exception
     */
    @GetMapping("/safetyvalve/{safFacilityCd}")
    public ResponseEntity<SafetyValve> getSafetyValve(@PathVariable(name = "safFacilityCd") String safFacilityCd)
            throws Exception {
        return ResponseEntity.ok().body(safetyValveService.getSafetyValve(safFacilityCd));
    }

    /**
     * 안전밸브 및 파열판 등록/수정
     * 
     * @param SafetyValve
     *            안전밸브 및 파열판
     * @return 변경 행 수
     * @throws Exception
     */
    @PostMapping("/safetyvalve")
    public ResponseEntity<Integer> saveSafetyValve(@RequestBody SafetyValve safetyValve) throws Exception {
        return ResponseEntity.ok().body(safetyValveService.saveSafetyValve(safetyValve));
    }

}
