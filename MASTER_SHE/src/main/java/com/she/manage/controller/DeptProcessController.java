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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.she.manage.model.DeptProcess;
import com.she.manage.service.DeptProcessService;
import com.she.utils.RequestMapper;

/**
 * 부서별 공정
 *
 */
@RestController
@RequestMapping("api/manage")
public class DeptProcessController {
    @Autowired
    private DeptProcessService deptProcessService;

    private RequestMapper requestMapper = new RequestMapper();

    /**
     * 부서별 공정 조회
     * 
     * @param parameter
     *            검색조건
     * @return 부서별 공정 목록
     * @throws Exception
     */
    @GetMapping("/deptprocesses")
    public ResponseEntity<List<DeptProcess>> getDeptProcesses(@RequestParam HashMap<String, Object> parameter)
            throws Exception {

        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        int processNo = map.containsKey("processNo") ? Integer.parseInt(map.get("processNo").toString()) : 0;
        String processNm = map.containsKey("processNm") ? map.get("processNm").toString() : "";
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";
        String deptNm = map.containsKey("deptNm") ? map.get("deptNm").toString() : "";

        List<DeptProcess> deptProcesses = this.deptProcessService.getDeptProcesses(processNo, processNm, deptCd,
                deptNm);

        return ResponseEntity.ok().body(deptProcesses);
    }

}
