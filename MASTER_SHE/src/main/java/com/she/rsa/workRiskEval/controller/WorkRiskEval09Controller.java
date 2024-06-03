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
import com.she.rsa.model.WorkRiskEval09Line;
import com.she.rsa.model.WorkRiskEval09Process;
import com.she.rsa.workRiskEval.service.WorkRiskEval09Service;
import com.she.utils.RequestMapper;

/**
 * 작업위험성평가 > 기준정보 > 작업위험성평가 공정
 */
@RestController
@RequestMapping("api/rsa/workRiskEval09")
public class WorkRiskEval09Controller {

    // queryString 변환을 위한 mapper 선언
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private WorkRiskEval09Service workRiskEval09Service;

    /**
     * 작업위험성평가 공정 조회
     * 
     * @return 작업위험성평가 공정 목록
     * @throws Exception
     */
    @GetMapping("/workRiskEval09List")
    public ResponseEntity<List<WorkRiskEval09Process>> getworkRiskEval09Lists(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 사업장
        String plantCd = map.containsKey("schPlantCd") ? map.get("schPlantCd").toString() : "";

        // 공정레벨
        String prcsLvlCd = map.containsKey("schPrcsLvlCd") ? map.get("schPrcsLvlCd").toString() : "";

        // 공정명
        String processNm = map.containsKey("schProcessNm") ? map.get("schProcessNm").toString() : "";

        return ResponseEntity.ok().body(workRiskEval09Service.getworkRiskEval09Lists(plantCd, prcsLvlCd, processNm, defaultParam));
    }

    /**
     * 작업위험성평가 공정 등록
     * 
     * @param WorkRiskEval09UnitWork
     * @return 작업위험성평가 단위작업 Key값
     * @throws Exception
     */
    @PostMapping("/workRiskEval09")
    public ResponseEntity<String> createWorkRiskEval09(@RequestBody WorkRiskEval09Process workRiskEval09Process) throws Exception {
        return ResponseEntity.ok().body(workRiskEval09Service.createWorkRiskEval09(workRiskEval09Process));
    }

    /**
     * 작업위험성평가 공정 수정
     * 
     * @param WorkRiskEval09UnitWork
     * @return 작업위험성평가 단위작업 Key값
     * @throws Exception
     */
    @PutMapping("/workRiskEval09")
    public ResponseEntity<String> updateWorkRiskEval09(@RequestBody WorkRiskEval09Process workRiskEval09Process) throws Exception {
        return ResponseEntity.ok().body(workRiskEval09Service.updateWorkRiskEval09(workRiskEval09Process));
    }

    /**
     * 작업위험성평가 공정 조회
     * 
     * @param parameter
     *            검색조건
     * @return 작업위험성평가 공정 상세조회
     * @throws Exception
     */
    @GetMapping("/workRiskEval09Info/{plantCd}/{processCd}")
    public ResponseEntity<WorkRiskEval09Process> getWorkRiskEval09info(@PathVariable("plantCd") String plantCd, @PathVariable("processCd") String processCd, @ModelAttribute DefaultParam defaultParam) throws Exception {
        return ResponseEntity.ok().body(workRiskEval09Service.getWorkRiskEval09Info(plantCd, processCd, defaultParam));
    }

    /**
     * 작업위험성평가 공정 라인 조회
     * 
     * @return 작업위험성평가 공정 라인 목록
     * @throws Exception
     */
    @GetMapping("/workRiskEval09LineList")
    public ResponseEntity<List<WorkRiskEval09Line>> getworkRiskEval09LineLists(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 사업장
        String plantCd = map.containsKey("schPlantCd") ? map.get("schPlantCd").toString() : "";

        // 부서코드
        String processClsCd = map.containsKey("schProcessClsCd") ? map.get("schProcessClsCd").toString() : "";

        // 공정명
        String processNm = map.containsKey("schProcessNm") ? map.get("schProcessNm").toString() : "";

        return ResponseEntity.ok().body(workRiskEval09Service.getworkRiskEval09LineLists(plantCd, processClsCd, processNm, defaultParam));
    }

    /**
     * 작업위험성평가 상위공정 코드조회
     * 
     * @param plantCd,
     *            prcsLvlCd 레벨
     * @return 작업위험성평가 상위공정 코드조회
     * @throws Exception
     */
    @GetMapping("/workRiskEval09UpProcess/{plantCd}/{prcsLvlCd}")
    public ResponseEntity<List<WorkRiskEval09Process>> getWorkRiskEval09UpProcess(@PathVariable("plantCd") String plantCd, @PathVariable("prcsLvlCd") String prcsLvlCd, @ModelAttribute DefaultParam defaultParam) throws Exception {
        return ResponseEntity.ok().body(workRiskEval09Service.getWorkRiskEval09UpProcess(plantCd, prcsLvlCd, defaultParam));
    }
}
