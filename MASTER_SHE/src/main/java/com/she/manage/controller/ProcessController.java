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

package com.she.manage.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.she.common.model.DefaultParam;
import com.she.manage.model.Process;
import com.she.manage.service.ProcessService;
import com.she.utils.RequestMapper;

/**
 * 작업공정 컨트롤러
 *
 */
@RestController
@RequestMapping("api/manage")
public class ProcessController {
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private ProcessService processService;

    /**
     * 작업공정 조회
     * 
     * @param parameter
     *            검색조건
     * @return 작업공정목록
     * @throws Exception
     */
    @GetMapping("/processes")
    public ResponseEntity<List<Process>> getProcesses(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = requestMapper.convertAsParameter(parameter);
        // 사용여부
        String useYn = map.containsKey("useYn") ? map.get("useYn").toString() : "";
        // 유해인자 코드
        String heaHazardCd = map.containsKey("heaHazardCd") ? map.get("heaHazardCd").toString() : "";
        // 부서코드
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";
        // 공정명
        String processNm = map.containsKey("processNm") ? map.get("processNm").toString() : "";
        // 공정명
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";

        return ResponseEntity.ok().body(this.processService.getProcesses(useYn, heaHazardCd, deptCd, processNm, plantCd, defaultParam));
    }

    /**
     * 작업공정 상세 조회
     * 
     * @param processCd
     *            공정코드
     * @return 작업공정
     * @throws Exception
     */
    @GetMapping("/process")
    public ResponseEntity<Process> getProcess(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = requestMapper.convertAsParameter(parameter);
        // 공정코드
        String processCd = map.containsKey("processCd") ? map.get("processCd").toString() : "";

        return ResponseEntity.ok().body(this.processService.getProcess(processCd, defaultParam));
    }

    /**
     * 작업공정 생성
     * 
     * @param process
     *            작업공정
     * @return 공정코드
     * @throws Exception
     */
    @PostMapping("/process")
    public ResponseEntity<String> createProcess(@RequestBody Process process) throws Exception {
        return ResponseEntity.ok().body(this.processService.createProcess(process));
    }

    /**
     * 작업공정 수정
     * 
     * @param process
     *            작업공정
     * @return 변경적용 행 수
     * @throws Exception
     */
    @PutMapping("/process")
    public ResponseEntity<Integer> updateProcess(@RequestBody Process process) throws Exception {
        Integer count = this.processService.updateProcess(process);

        return ResponseEntity.ok().body(count);
    }

    /**
     * 작업공정 중복검사
     * 
     * @param parameter
     *            검색조건
     * @return 중복검사
     * @throws Exception
     */
    @GetMapping("/checkprocess")
    public ResponseEntity<List<HashMap<String, Object>>> getCheckProcesses(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = requestMapper.convertAsParameter(parameter);
        // 공정코드
        String processCd = map.containsKey("processCd") ? map.get("processCd").toString() : "";
        // 공정명
        String processNm = map.containsKey("processNm") ? map.get("processNm").toString() : "";

        return ResponseEntity.ok().body(this.processService.getCheckProcesses(processCd, processNm));
    }

}
