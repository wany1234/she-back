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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.she.common.model.DefaultParam;
import com.she.rsa.model.WorkRiskEval04Appr;
import com.she.rsa.workRiskEval.service.WorkRiskEval04Service;
import com.she.utils.RequestMapper;

/**
 * 작업위험성평가 결재
 */
@RestController
@RequestMapping("api/rsa/workRiskEval04")
public class WorkRiskEval04Controller {

    // queryString 변환을 위한 mapper 선언
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private WorkRiskEval04Service workRiskEval04Service;

    /**
     * 작업위험성평가 결과 조회
     * 
     * @return 작업위험성평가 결과 목록
     * @throws Exception
     */
    @GetMapping("/workRiskEval04List")
    public ResponseEntity<List<WorkRiskEval04Appr>> getworkRiskEval04List(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
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

        return ResponseEntity.ok().body(workRiskEval04Service.getworkRiskEval04List(plantCd, deptCd, evalTypeCd, startYear, endYear, defaultParam));
    }

    /**
     * 작업위험성평가 결과 결재요청 내용 조회 plantCd(사업장코드) evalYear(평가년도) evalNo(평가번호)
     * deptCd(부서코드) apprBizCd(결재문서코드)
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

        // 부서코드
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";

        // Html Title
        String apprRqstNm = map.containsKey("apprRqstNm") ? map.get("apprRqstNm").toString() : "";

        // 결재양식 url
        String apprBizCd = map.containsKey("apprBizCd") ? map.get("apprBizCd").toString() : "";

        // 결재양식 line url
        String linkUrl = map.containsKey("linkUrl") ? map.get("linkUrl").toString() : "";

        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(workRiskEval04Service.createApprContents(plantCd, evalYear, evalNo, deptCd, apprRqstNm, apprBizCd, linkUrl, defaultParam));
    }

}
