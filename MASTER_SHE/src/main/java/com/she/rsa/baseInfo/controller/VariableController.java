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
package com.she.rsa.baseInfo.controller;

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
import com.she.rsa.baseInfo.service.VariableService;
import com.she.rsa.model.Variable;
import com.she.utils.RequestMapper;

@RestController
@RequestMapping("api/rsa/baseinfo")
public class VariableController {

    // queryString 변환을 위한 mapper 선언
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private VariableService variableService;

    /**
     * 변수 조회
     * 
     * @param parameter
     *            검색조건
     * @return 변수 목록
     * @throws Exception
     */
    @GetMapping("/variables")
    public ResponseEntity<List<Variable>> getVariables(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 사용여부
        String useYn = map.containsKey("useYn") ? map.get("useYn").toString() : "";

        return ResponseEntity.ok().body(variableService.getVariables(useYn));
    }

    /**
     * 변수 상세 조회
     * 
     * @param varId
     *            변수ID
     * @return 변수
     * @throws Exception
     */
    @GetMapping("/variable/{varId}")
    public ResponseEntity<Variable> getVariable(@PathVariable(name = "varId") int varId, @ModelAttribute DefaultParam defaultParam) throws Exception {
        return ResponseEntity.ok().body(this.variableService.getVariable(varId, defaultParam));
    }

    /**
     * 변수 신규등록
     * 
     * @param variable
     *            변수
     * @return 변수 번호
     * @throws Exception
     */
    @PostMapping("/variable")
    public ResponseEntity<Integer> createVariable(@RequestBody Variable variable) throws Exception {
        return ResponseEntity.ok().body(this.variableService.createVariable(variable));
    }

    /**
     * 변수 수정
     * 
     * @param variable
     *            변수
     * @return 수정 행 수
     * @throws Exception
     */
    @PutMapping("/variable")
    public ResponseEntity<Integer> updateVariable(@RequestBody Variable variable) throws Exception {
        return ResponseEntity.ok().body(this.variableService.updateVariable(variable));
    }

    /**
     * 변수명 체크
     * 
     * @param parameter
     *            검색조건
     * @return 체크값
     * @throws Exception
     */
    @GetMapping("/checkvariables")
    public ResponseEntity<List<HashMap<String, Object>>> getCheckVariables(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 변수명(KOR)
        String varNmKr = map.containsKey("varNmKr") ? map.get("varNmKr").toString() : "";
        // 변수명 (ENG)
        String varNmEn = map.containsKey("varNmEn") ? map.get("varNmEn").toString() : "";
        // 변수ID
        int varId = map.containsKey("varId") ? Integer.parseInt("".equals(map.get("varId").toString()) ? "0" : map.get("varId").toString()) : 0;

        return ResponseEntity.ok().body(variableService.getCheckVariables(varNmKr, varNmEn, varId));
    }

}
