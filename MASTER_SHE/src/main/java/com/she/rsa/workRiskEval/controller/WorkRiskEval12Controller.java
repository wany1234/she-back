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
import com.she.rsa.model.WorkRiskEval12RefInd;
import com.she.rsa.workRiskEval.service.WorkRiskEval12Service;
import com.she.utils.RequestMapper;

/**
 * 작업위험성평가 > 기준정보 > 단위작업
 */
@RestController
@RequestMapping("api/rsa/workRiskEval12")
public class WorkRiskEval12Controller {

    // queryString 변환을 위한 mapper 선언
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private WorkRiskEval12Service workRiskEval12Service;

    /**
     * 작업위험성평가 계획 조회
     * 
     * @return 작업위험성평가 계획 목록
     * @throws Exception
     */
    @GetMapping("/workRiskEval12List")
    public ResponseEntity<List<WorkRiskEval12RefInd>> getworkRiskEval12Lists(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 사업장
        String plantCd = map.containsKey("schPlantCd") ? map.get("schPlantCd").toString() : "";

        // 구분
        String indTypeCd = map.containsKey("schIndTypeCd") ? map.get("schIndTypeCd").toString() : "";

        // 설정명
        String setNm = map.containsKey("schSetNm") ? map.get("schSetNm").toString() : "";

        // 사용여부
        String useYn = map.containsKey("schUseYn") ? map.get("schUseYn").toString() : "";

        return ResponseEntity.ok().body(workRiskEval12Service.getworkRiskEval12Lists(plantCd, indTypeCd, setNm, useYn, defaultParam));
    }

    /**
     * 작업위험성평가 단위작업 등록
     * 
     * @param WorkRiskEval12RefInd
     * @return 작업위험성평가 단위작업 Key값
     * @throws Exception
     */
    @PostMapping("/workRiskEval12")
    public ResponseEntity<String> createWorkRiskEval12(@RequestBody WorkRiskEval12RefInd workRiskEval12RefInd) throws Exception {
        return ResponseEntity.ok().body(workRiskEval12Service.createWorkRiskEval12(workRiskEval12RefInd));
    }

    /**
     * 작업위험성평가 단위작업 수정
     * 
     * @param WorkRiskEval12RefInd
     * @return 작업위험성평가 단위작업 Key값
     * @throws Exception
     */
    @PutMapping("/workRiskEval12")
    public ResponseEntity<String> updateWorkRiskEval12(@RequestBody WorkRiskEval12RefInd workRiskEval12RefInd) throws Exception {
        return ResponseEntity.ok().body(workRiskEval12Service.updateWorkRiskEval12(workRiskEval12RefInd));
    }

    /**
     * 작업위험성평가 단위작업 조회
     * 
     * @param parameter
     *            검색조건
     * @return 작업위험성평가 단위작업 상세조회
     * @throws Exception
     */
    @GetMapping("/workRiskEval12Info/{plantCd}/{indTypeCd}/{setVal}")
    public ResponseEntity<WorkRiskEval12RefInd> getWorkRiskEval12info(@PathVariable("plantCd") String plantCd, @PathVariable("indTypeCd") String indTypeCd, @PathVariable("setVal") String setVal, @ModelAttribute DefaultParam defaultParam) throws Exception {
        return ResponseEntity.ok().body(workRiskEval12Service.getWorkRiskEval12Info(plantCd, indTypeCd, setVal, defaultParam));
    }

}
