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
import com.she.rsa.model.WorkRiskEval02DeptPrcs;
import com.she.rsa.model.WorkRiskEval02Prcs;
import com.she.rsa.workRiskEval.service.WorkRiskEval02Service;
import com.she.utils.RequestMapper;

/**
 * 작업위험성평가 대상 공정설정
 */
@RestController
@RequestMapping("api/rsa/workRiskEval02")
public class WorkRiskEval02Controller {

    // queryString 변환을 위한 mapper 선언
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private WorkRiskEval02Service workRiskEval02Service;

    /**
     * 작업위험성평가 대상 공정설정 조회
     * 
     * @return 작업위험성평가 대상 공정설정 목록
     * @throws Exception
     */
    @GetMapping("/workRiskEval02Lists")
    public ResponseEntity<List<WorkRiskEval02Prcs>> getworkRiskEval02Lists(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 사업장
        String plantCd = map.containsKey("schPlantCd") ? map.get("schPlantCd").toString() : "";

        // 부서코드
        String deptCd = map.containsKey("schDeptCd") ? map.get("schDeptCd").toString() : "";

        // 퍙가구분 (정시/수기)
        String evalTypeCd = map.containsKey("schEvalType") ? map.get("schEvalType").toString() : "";

        // 평가년도
        String[] evalYear = this.requestMapper.convertObjectListAsStringArray(map.get("schEvalYear"));

        String startYear = "";
        String endYear = "";

        if (evalYear != null && evalYear.length == 2) {
            startYear = evalYear[0];
            endYear = evalYear[1];
        }

        return ResponseEntity.ok().body(workRiskEval02Service.getworkRiskEval02Lists(plantCd, deptCd, evalTypeCd, startYear, endYear, defaultParam));
    }

    /**
     * 작업위험성평가 대상 공정설정 평가대상공정 조회
     * 
     * @return 작업위험성평가 대상 공정설정 평가대상공정 목록
     * @throws Exception
     */
    @GetMapping("/workRiskEval02detpPrcsLists")
    public ResponseEntity<List<WorkRiskEval02DeptPrcs>> getWorkRiskEval02detpPrcsLists(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 평가번호
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";

        // 평가번호
        String evalYear = map.containsKey("evalYear") ? map.get("evalYear").toString() : "";

        // 평가번호
        String evalNo = map.containsKey("evalNo") ? map.get("evalNo").toString() : "";

        // 부서코드
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";

        return ResponseEntity.ok().body(workRiskEval02Service.getWorkRiskEval02detpPrcsLists(plantCd, deptCd, evalYear, evalNo, defaultParam));
    }

    /**
     * 작업위험성평가 대상 공정설정 부서별 공정/단위작업 조회
     * 
     * @return 작업위험성평가 대상 공정설정 부서별 공정/단위작업 목록
     * @throws Exception
     */
    @GetMapping("/deptPrcsBaseInfoLists")
    public ResponseEntity<List<WorkRiskEval02DeptPrcs>> getdeptPrcsBaseInfoLists(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 평가번호
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";

        // 부서코드
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";

        // 공정명
        String processNm = map.containsKey("processNm") ? map.get("processNm").toString() : "";

        // 작업명
        String unitWorkNm = map.containsKey("unitWorkNm") ? map.get("unitWorkNm").toString() : "";

        return ResponseEntity.ok().body(workRiskEval02Service.getdeptPrcsBaseInfoLists(plantCd, deptCd, processNm, unitWorkNm, defaultParam));
    }

    /**
     * 작업위험성평가 대상 공정설정 부서별 공정/단위작업 조회
     * 
     * @return 작업위험성평가 대상 공정설정 부서별 공정/단위작업 목록
     * @throws Exception
     */
    @GetMapping("/deptPrcsExistBaseInfoLists")
    public ResponseEntity<List<WorkRiskEval02DeptPrcs>> getdeptPrcsExistBaseInfoLists(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 사업장
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";

        // 평가번호
        String evalNo = map.containsKey("evalNo") ? map.get("evalNo").toString() : "";

        // 부서코드
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";

        // 공정명
        String processNm = map.containsKey("processNm") ? map.get("processNm").toString() : "";

        // 작업명
        String unitWorkNm = map.containsKey("unitWorkNm") ? map.get("unitWorkNm").toString() : "";

        // 평가구분
        String evalTypeCd = map.containsKey("evalTypeCd") ? map.get("evalTypeCd").toString() : "";

        // 평가년도
        String[] evalYear = this.requestMapper.convertObjectListAsStringArray(map.get("evalYear"));

        String startYear = "";
        String endYear = "";

        if (evalYear != null && evalYear.length == 2) {
            startYear = evalYear[0];
            endYear = evalYear[1];
        }

        return ResponseEntity.ok().body(workRiskEval02Service.getdeptPrcsExistBaseInfoLists(plantCd, evalNo, deptCd, processNm, unitWorkNm, evalTypeCd, startYear, endYear, defaultParam));
    }

    /**
     * 작업위험성평가 대상 공정설정 평가대상공정 상세 조회
     * 
     * @param parameter
     *            검색조건
     * @return 작업위험성평가 대상 공정설정 평가대상공정 관리 상세조회
     * @throws Exception
     */
    @GetMapping("/workRiskEval02Info/{plantCd}/{evalYear}/{evalNo}/{deptCd}")
    public ResponseEntity<WorkRiskEval02Prcs> getWorkRiskEval02Info(@PathVariable("plantCd") String plantCd, @PathVariable("evalYear") String evalYear, @PathVariable("evalNo") String evalNo, @PathVariable("deptCd") String deptCd, @ModelAttribute DefaultParam defaultParam) throws Exception {
        return ResponseEntity.ok().body(workRiskEval02Service.getWorkRiskEval02Info(plantCd, evalYear, evalNo, deptCd, defaultParam));
    }

    /**
     * 작업위험성평가 공정설정 평가대상공정 등록
     * 
     * @param WorkRiskEval02Plan
     *            작업위험성평가 공정설정 평가대상공정 List
     * @return 작업위험성평가 공정설정 평가대상공정 관리 Key값
     * @throws Exception
     */
    @PostMapping("/workRiskEval02")
    public ResponseEntity<String> createWorkRiskEval02(@RequestBody WorkRiskEval02Prcs workRiskEval02Prcs) throws Exception {
        workRiskEval02Prcs.setRsltStepCd("RREGI");
        return ResponseEntity.ok().body(workRiskEval02Service.createWorkRiskEval02(workRiskEval02Prcs));
    }

    /**
     * 작업위험성평가 공정설정 평가대상공정 등록
     * 
     * @param WorkRiskEval02Plan
     *            작업위험성평가 공정설정 평가대상공정 List
     * @return 작업위험성평가 공정설정 평가대상공정 관리 Key값
     * @throws Exception
     */
    @PutMapping("/workRiskEval02")
    public ResponseEntity<String> updateWorkRiskEval02Confirm(@RequestBody WorkRiskEval02Prcs workRiskEval02Prcs) throws Exception {
        workRiskEval02Prcs.setRsltStepCd("RPSET");
        return ResponseEntity.ok().body(workRiskEval02Service.createWorkRiskEval02(workRiskEval02Prcs));
    }

}
