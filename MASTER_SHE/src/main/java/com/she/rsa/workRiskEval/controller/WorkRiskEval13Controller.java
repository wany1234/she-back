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
import com.she.rsa.model.WorkRiskEval13DtlUnitWork;
import com.she.rsa.model.WorkRiskEval13UnitWork;
import com.she.rsa.workRiskEval.service.WorkRiskEval13Service;
import com.she.utils.RequestMapper;

/**
 * 작업위험성평가 > 기준정보 > 부서공정별 단위작업
 */
@RestController
@RequestMapping("api/rsa/workRiskEval13")
public class WorkRiskEval13Controller {

    // queryString 변환을 위한 mapper 선언
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private WorkRiskEval13Service workRiskEval13Service;

    /**
     * 작업위험성평가 계획 조회
     * 
     * @return 작업위험성평가 계획 목록
     * @throws Exception
     */
    @GetMapping("/workRiskEval13List")
    public ResponseEntity<List<WorkRiskEval13UnitWork>> getworkRiskEval13Lists(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 사업장
        String plantCd = map.containsKey("schPlantCd") ? map.get("schPlantCd").toString() : "";

        // 부서
        String deptCd = map.containsKey("schDeptCd") ? map.get("schDeptCd").toString() : "";

        // 공정코드
        String processCd = map.containsKey("schProcessCd") ? map.get("schProcessCd").toString() : "";

        // 작업명
        String unitWorkNm = map.containsKey("schUnitWorkNm") ? map.get("schUnitWorkNm").toString() : "";

        // 사용여부
        String useYn = map.containsKey("schUseYn") ? map.get("schUseYn").toString() : "";

        return ResponseEntity.ok().body(workRiskEval13Service.getworkRiskEval13Lists(plantCd, deptCd, processCd, unitWorkNm, useYn, defaultParam));
    }

    /**
     * 작업위험성평가 공정검색팝업 공정 조회
     * 
     * @return 작업위험성평가 공정검색팝업 공정 목록
     * @throws Exception
     */
    @GetMapping("/workRiskEval13ProcessList")
    public ResponseEntity<List<WorkRiskEval13UnitWork>> getworkRiskEval13ProcessLists(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 사업장
        String plantCd = map.containsKey("schPlantCd") ? map.get("schPlantCd").toString() : "";

        // 공정레벨
        String prcsLvlCd = map.containsKey("schPrcsLvlCd") ? map.get("schPrcsLvlCd").toString() : "";

        // 작업공정명
        String processNm = map.containsKey("schProcessNm") ? map.get("schProcessNm").toString() : "";

        return ResponseEntity.ok().body(workRiskEval13Service.getworkRiskEval13ProcessLists(plantCd, prcsLvlCd, processNm, defaultParam));
    }

    /**
     * 작업위험성평가 단위작업검색팝업 단위작업 조회
     * 
     * @return 작업위험성평가 단위작업검색팝업 단위작업 목록
     * @throws Exception
     */
    @GetMapping("/workRiskEval13UnitList")
    public ResponseEntity<List<WorkRiskEval13UnitWork>> getworkRiskEval13UnitLists(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 사업장
        String plantCd = map.containsKey("schPlantCd") ? map.get("schPlantCd").toString() : "";

        // 단위작업명
        String unitWorkNm = map.containsKey("schUnitWorkNm") ? map.get("schUnitWorkNm").toString() : "";

        return ResponseEntity.ok().body(workRiskEval13Service.getworkRiskEval13UnitLists(plantCd, unitWorkNm, defaultParam));
    }

    /**
     * 작업위험성평가 부서공정별 조회
     * 
     * @param parameter
     *            검색조건
     * @return 작업위험성평가 부서공정별 상세조회
     * @throws Exception
     */
    @GetMapping("/workRiskEval13Info/{plantCd}/{deptCd}/{processCd}/{unitWorkCd}")
    public ResponseEntity<WorkRiskEval13UnitWork> getWorkRiskEval13info(@PathVariable("plantCd") String plantCd, @PathVariable("deptCd") String deptCd, @PathVariable("processCd") String processCd, @PathVariable("unitWorkCd") String unitWorkCd, @ModelAttribute DefaultParam defaultParam) throws Exception {
        return ResponseEntity.ok().body(workRiskEval13Service.getWorkRiskEval13Info(plantCd, deptCd, processCd, unitWorkCd, defaultParam));
    }

    /**
     * 작업위험성평가 부서공정 등록
     * 
     * @param WorkRiskEval13UnitWork
     * @return 작업위험성평가 부서공정 Key값
     * @throws Exception
     */
    @PostMapping("/workRiskEval13")
    public ResponseEntity<String> createWorkRiskEval13(@RequestBody WorkRiskEval13UnitWork workRiskEval13UnitWork) throws Exception {
        return ResponseEntity.ok().body(workRiskEval13Service.createWorkRiskEval13(workRiskEval13UnitWork));
    }

    /**
     * 작업위험성평가 부서공정 수정
     * 
     * @param WorkRiskEval13UnitWork
     * @return 작업위험성평가 부서공정 Key값
     * @throws Exception
     */
    @PutMapping("/workRiskEval13")
    public ResponseEntity<String> updateWorkRiskEval13(@RequestBody WorkRiskEval13UnitWork workRiskEval13UnitWork) throws Exception {
        return ResponseEntity.ok().body(workRiskEval13Service.updateWorkRiskEval13(workRiskEval13UnitWork));
    }

    /**
     * 작업위험성평가 부서공정별 세부작업 조회
     * 
     * @param parameter
     *            검색조건
     * @return 작업위험성평가 부서공정별 세부작업 목록 조회
     * @throws Exception
     */
    @GetMapping("/workRiskEval13DtlUnitWorkList/{plantCd}/{deptCd}/{processCd}/{unitWorkCd}")
    public ResponseEntity<List<WorkRiskEval13DtlUnitWork>> getWorkRiskEval13DtlUnitWorkList(@PathVariable("plantCd") String plantCd, @PathVariable("deptCd") String deptCd, @PathVariable("processCd") String processCd, @PathVariable("unitWorkCd") String unitWorkCd, @ModelAttribute DefaultParam defaultParam) throws Exception {
        return ResponseEntity.ok().body(workRiskEval13Service.getWorkRiskEval13DtlUnitWorkList(plantCd, deptCd, processCd, unitWorkCd, defaultParam));
    }

}
