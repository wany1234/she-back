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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.she.common.model.DefaultParam;
import com.she.rsa.model.WorkRiskEval11UnitWork;
import com.she.rsa.workRiskEval.service.WorkRiskEval11Service;
import com.she.utils.RequestMapper;

/**
 * 작업위험성평가 > 기준정보 > 단위작업
 */
@RestController
@RequestMapping("api/rsa/workRiskEval11")
public class WorkRiskEval11Controller {

    // queryString 변환을 위한 mapper 선언
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private WorkRiskEval11Service workRiskEval11Service;

    /**
     * 작업위험성평가 계획 조회
     * 
     * @return 작업위험성평가 계획 목록
     * @throws Exception
     */
    @GetMapping("/workRiskEval11List")
    public ResponseEntity<List<WorkRiskEval11UnitWork>> getworkRiskEval11Lists(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 사업장
        String plantCd = map.containsKey("schPlantCd") ? map.get("schPlantCd").toString() : "";

        // 작업명
        String unitWorknm = map.containsKey("schUnitWorkNm") ? map.get("schUnitWorkNm").toString() : "";

        // 사용여부
        String useYn = map.containsKey("schUseYn") ? map.get("schUseYn").toString() : "";

        return ResponseEntity.ok().body(workRiskEval11Service.getworkRiskEval11Lists(plantCd, unitWorknm, useYn, defaultParam));
    }

    /**
     * 작업위험성평가 단위작업 등록
     * 
     * @param WorkRiskEval11UnitWork
     * @return 작업위험성평가 단위작업 Key값
     * @throws Exception
     */
    @PostMapping("/workRiskEval11")
    public ResponseEntity<String> createWorkRiskEval11(@RequestBody WorkRiskEval11UnitWork workRiskEval11UnitWork) throws Exception {
        return ResponseEntity.ok().body(workRiskEval11Service.createWorkRiskEval11(workRiskEval11UnitWork));
    }

    /**
     * 작업위험성평가 단위작업 수정
     * 
     * @param WorkRiskEval11UnitWork
     * @return 작업위험성평가 단위작업 Key값
     * @throws Exception
     */
    @PutMapping("/workRiskEval11")
    public ResponseEntity<String> updateWorkRiskEval11(@RequestBody WorkRiskEval11UnitWork workRiskEval11UnitWork) throws Exception {
        return ResponseEntity.ok().body(workRiskEval11Service.updateWorkRiskEval11(workRiskEval11UnitWork));
    }

    /**
     * 작업위험성평가 단위작업 조회
     * 
     * @param parameter
     *            검색조건
     * @return 작업위험성평가 단위작업 상세조회
     * @throws Exception
     */
    @GetMapping("/workRiskEval11Info/{plantCd}/{unitWorkCd}")
    public ResponseEntity<WorkRiskEval11UnitWork> getWorkRiskEval11info(@PathVariable("plantCd") String plantCd, @PathVariable("unitWorkCd") String unitWorkCd, @ModelAttribute DefaultParam defaultParam) throws Exception {
        return ResponseEntity.ok().body(workRiskEval11Service.getWorkRiskEval11Info(plantCd, unitWorkCd, defaultParam));
    }

}
