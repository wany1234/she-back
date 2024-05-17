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
package com.she.rsa.assess.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.she.rsa.assess.service.DeptAssessJobService;
import com.she.rsa.model.DeptAssessJob;
import com.she.utils.RequestMapper;

/**
 * 평가대상직무
 */
@RestController
@RequestMapping("api/rsa/assess")
public class DeptAssessJobController {
    @Autowired
    private DeptAssessJobService deptAssessJobService;

    // queryString 변환을 위한 mapper 선언
    @Autowired
    private RequestMapper requestMapper;

    /**
     * 평가대상직무 조회
     * 
     * @param parameter
     *            검색조건
     * @return 평가대상직무 목록
     * @throws Exception
     */
    @GetMapping("/deptassessjobs")
    public ResponseEntity<List<DeptAssessJob>> getDeptAssessJobs(@RequestParam HashMap<String, Object> parameter)
            throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        // 평가계획 번호
        int assessPlanNo = map.containsKey("assessPlanNo")
                ? Integer.parseInt(
                        "".equals(map.get("assessPlanNo").toString()) ? "0" : map.get("assessPlanNo").toString())
                : 0;
        // 공정 코드
        String processCd = map.containsKey("processCd") ? map.get("processCd").toString() : "";
        // 평가유형 번호
        int assessTypeNo = map.containsKey("assessTypeNo")
                ? Integer.parseInt(
                        "".equals(map.get("assessTypeNo").toString()) ? "0" : map.get("assessTypeNo").toString())
                : 0;

        List<DeptAssessJob> deptAssessJobs = deptAssessJobService.getDeptAssessJobs(assessPlanNo, processCd,
                assessTypeNo);
        return ResponseEntity.ok().body(deptAssessJobs);
    }

    /**
     * 평가대상직무 등록
     * 
     * @param AssessPlan
     *            평가대상직무
     * @return 변경 행 수
     * @throws Exception
     */
    @PostMapping("/deptassessjob")
    public ResponseEntity<Integer> createDeptAssessJob(@RequestBody DeptAssessJob deptAssessJob) throws Exception {
        return ResponseEntity.ok().body(deptAssessJobService.createDeptAssessJob(deptAssessJob));
    }

}
