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
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import com.she.rsa.model.WorkRiskEval01Plan;
import com.she.rsa.model.WorkRiskEval01PlanDeptList;
import com.she.rsa.workRiskEval.service.WorkRiskEval01Service;
import com.she.utils.RequestMapper;

/**
 * 작업위험성평가
 */
@RestController
@RequestMapping("api/rsa/workRiskEval01")
public class WorkRiskEval01Controller {

    // queryString 변환을 위한 mapper 선언
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private WorkRiskEval01Service workRiskEval01Service;

    /**
     * 작업위험성평가 계획 조회
     * 
     * @return 작업위험성평가 계획 목록
     * @throws Exception
     */
    @GetMapping("/workRiskEval01Lists")
    public ResponseEntity<List<WorkRiskEval01Plan>> getworkRiskEval01Lists(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 사업장
        String plantCd = map.containsKey("schPlantCd") ? map.get("schPlantCd").toString() : "";

        // 평가명
        String evalNm = map.containsKey("schEvalNm") ? map.get("schEvalNm").toString() : "";

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

        return ResponseEntity.ok().body(workRiskEval01Service.getworkRiskEval01Lists(plantCd, evalNm, evalTypeCd, startYear, endYear, defaultParam));
    }

    /**
     * 작업위험성평가 관리 등록
     * 
     * @param WorkRiskEval01Plan
     *            작업위험성평가 관리 List
     * @return 작업위험성평가 관리 Key값
     * @throws Exception
     */
    @PostMapping("/workRiskEval01")
    public ResponseEntity<String> createWorkRiskEval01(@RequestBody WorkRiskEval01Plan workRiskEval01Plan) throws Exception {
        return ResponseEntity.ok().body(workRiskEval01Service.createWorkRiskEval01(workRiskEval01Plan));
    }

    /**
     * 작업위험성평가 관리 수정
     * 
     * @param WorkRiskEval01Plan
     *            작업위험성평가 관리 List
     * @return 작업위험성평가 관리 Key값
     * @throws Exception
     */
    @PutMapping("/workRiskEval01")
    public ResponseEntity<String> updateWorkRiskEval01(@RequestBody WorkRiskEval01Plan workRiskEval01Plan) throws Exception {
        return ResponseEntity.ok().body(workRiskEval01Service.updateWorkRiskEval01(workRiskEval01Plan));
    }

    /**
     * 작업위험성평가 관리 조회
     * 
     * @param parameter
     *            검색조건
     * @return 작업위험성평가 관리 상세조회
     * @throws Exception
     */
    @GetMapping("/workRiskEval01Info/{plantCd}/{evalYear}/{evalNo}")
    public ResponseEntity<WorkRiskEval01Plan> getWorkRiskEval01info(@PathVariable("plantCd") String plantCd, @PathVariable("evalYear") String evalYear, @PathVariable("evalNo") String evalNo, @ModelAttribute DefaultParam defaultParam) throws Exception {
        return ResponseEntity.ok().body(workRiskEval01Service.getWorkRiskEval01Info(plantCd, evalYear, evalNo, defaultParam));
    }

    /**
     * 작업위험성평가계획 관리 대상부서조회
     * 
     * @param parameter
     *            검색조건
     * @return 작업위험성평가 관리 평가대상부서목록
     * @throws Exception
     */
    @GetMapping("/workRiskEval01detpLists")
    public ResponseEntity<List<WorkRiskEval01PlanDeptList>> getWorkRiskEval01detpLists(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        String evalYear = map.containsKey("evalYear") ? map.get("evalYear").toString() : "";
        String evalNo = map.containsKey("evalNo") ? map.get("evalNo").toString() : "";

        return ResponseEntity.ok().body(workRiskEval01Service.getWorkRiskEval01detpLists(plantCd, evalYear, evalNo, defaultParam));
    }

    /**
     * 작업위험성평가 관리 삭제
     * 
     * @param evalNo
     * 
     * @return 작업위험성평가계획/결과 관리 삭제
     * @throws Exception
     */
    @DeleteMapping("/workRiskEval01/{plantCd}/{evalYear}/{evalNo}")
    public ResponseEntity<Integer> deleteWorkRiskEval01(@PathVariable("plantCd") String plantCd, @PathVariable("evalYear") String evalYear, @PathVariable("evalNo") String evalNo) throws Exception {
        return ResponseEntity.ok().body(workRiskEval01Service.deleteWorkRiskEval01(plantCd, evalYear, evalNo));
    }

    /**
     * 작업위험성평가 계획 결재요청 내용 조회 plantCd(사업장코드) evalYear(평가년도) evalNo(평가번호)
     * apprBizCd(결재문서코드)
     * 
     * @return 결재요청 내용 HTML
     * @throws Exception
     */
    @GetMapping("/createApprContents")
    public ResponseEntity<Map<String, String>> createApprContents(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = requestMapper.convertAsParameter(parameter);

        // 사업장코드
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";

        // 평가년도
        String evalYear = map.containsKey("evalYear") ? map.get("evalYear").toString() : "";

        // 평가번호
        String evalNo = map.containsKey("evalNo") ? map.get("evalNo").toString() : "";

        // Html Title
        String apprRqstNm = map.containsKey("apprRqstNm") ? map.get("apprRqstNm").toString() : "";

        // 결재양식 url
        String apprBizCd = map.containsKey("apprBizCd") ? map.get("apprBizCd").toString() : "";

        // 결재양식 line url
        String linkUrl = map.containsKey("linkUrl") ? map.get("linkUrl").toString() : "";

        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(workRiskEval01Service.createApprContents(plantCd, evalYear, evalNo, apprRqstNm, apprBizCd, linkUrl, defaultParam));
    }

}
