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
package com.she.rsa.jobRiskBook.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.she.common.model.DefaultParam;
import com.she.rsa.jobRiskBook.service.JobRiskBookService;
import com.she.rsa.model.JobRiskBook;
import com.she.utils.RequestMapper;

/**
 * 위험등록부
 */
@RestController
@RequestMapping("api/rsa/jobriskbook")
public class JobRiskBookController {
    @Autowired
    private JobRiskBookService jobRiskBookService;

    // queryString 변환을 위한 mapper 선언
    @Autowired
    private RequestMapper requestMapper;

    /**
     * 위험등록부 JSA 조회
     * 
     * @param assessTypeNo
     *            평가기법 번호
     * @param assessNm
     *            평가명
     * @param deptCd
     *            부서
     * @param processNo
     *            공정
     * @param assessYear
     *            평가년도
     * @param jobNm
     *            직무명
     * @param jobStepNm
     *            직무절차명
     * @return 위험등록부 JSA 목록
     * @throws Exception
     */
    @GetMapping("/jobriskbookjsas")
    public ResponseEntity<List<JobRiskBook>> getJobRiskBookJSAs(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 사업장
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";

        // 평가기법 번호
        int assessTypeNo = map.containsKey("assessTypeNo") ? Integer.parseInt("".equals(map.get("assessTypeNo").toString()) ? "0" : map.get("assessTypeNo").toString()) : 0;
        // 공정
        String processCd = map.containsKey("processCd") ? map.get("processCd").toString() : "";
        // int processNo = map.containsKey("processNo")
        // ? Integer.parseInt("".equals(map.get("processNo").toString()) ? "0" :
        // map.get("processNo").toString())
        // : 0;
        // 평가명
        String assessNm = map.containsKey("assessNm") ? map.get("assessNm").toString() : "";
        // 부서
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";
        // 하위부서 포함여부
        String deptSubYn = map.containsKey("deptSubYn") ? map.get("deptSubYn").toString() : "Y";

        // 담당자
        String userId = map.containsKey("userId") ? map.get("userId").toString() : "";
        // 평가년도
        String assessYear = map.containsKey("assessYear") ? map.get("assessYear").toString() : "";
        // 직무명
        String jobNm = map.containsKey("jobNm") ? map.get("jobNm").toString() : "";
        // 직무절차명
        String jobStepNm = map.containsKey("jobStepNm") ? map.get("jobStepNm").toString() : "";

        return ResponseEntity.ok().body(jobRiskBookService.getJobRiskBookJSAs(plantCd, userId, assessTypeNo, assessNm, deptCd, deptSubYn, processCd, assessYear, jobNm, jobStepNm, defaultParam));
    }

    /**
     * 위험등록부 삭제
     * 
     * @param JobRiskBooks
     *            위험등록부 리스트
     * @return 변경 행 수
     * @throws Exception
     */
    @DeleteMapping("/jobriskbook")
    public ResponseEntity<Integer> deleteJobRiskBook(@RequestBody List<JobRiskBook> jobRiskBooks) throws Exception {
        return ResponseEntity.ok().body(jobRiskBookService.deleteJobRiskBook(jobRiskBooks));
    }

}
