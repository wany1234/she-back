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
package com.she.mgt.baseInfo.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.she.manage.model.CodeMaster;
import com.she.mgt.baseInfo.service.SafetyActionBizFieldService;
import com.she.utils.RequestMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 안전 기준정보 인적피해 항목
 *
 */
@RestController
@RequestMapping("/api/mgt/baseinfo/")
@Api(value = "/api/mgt/baseinfo/", description = "KPI 분야관리")
public class SafetyActionBizFieldController {

    // TODO : queryString 변환을 위한 mapper 선언
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private SafetyActionBizFieldService safetyActionBizFieldService;

    /**
     * 경영 기준정보 KPI 분야관리 조회
     * 
     * @param parameter
     * @return 경영 기준정보 KPI 분야 목록
     * @throws Exception
     */
    @ApiOperation(value = "경영 기준정보 KPI 분야관리 조회", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "codeNm", value = "분야명", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
    @GetMapping("safetyactionbizfields")
    public ResponseEntity<List<CodeMaster>> getSafetyActionBizFields(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> convertedParameter = requestMapper.convertAsParameter(parameter);
        // 분야명
        String codeNm = convertedParameter.containsKey("codeNm") ? convertedParameter.get("codeNm").toString() : "";
        // 사용여부
        String useYn = convertedParameter.containsKey("useYn") ? convertedParameter.get("useYn").toString() : "";

        return ResponseEntity.ok().body(safetyActionBizFieldService.getSafetyActionBizFields(codeNm, useYn));
    }

    /**
     * 경영 기준정보 KPI 분야관리 상세조회
     * 
     * @param code
     *            코드
     * @return 경영 기준정보 KPI 분야
     * @throws Exception
     */
    @ApiOperation(value = "경영 기준정보 KPI 분야관리 상세조회", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "code", value = "코드", required = false, dataType = "string", paramType = "path") })
    @GetMapping("safetyactionbizfield/{code}")
    public ResponseEntity<CodeMaster> getSafetyActionBizField(@PathVariable("code") String code) throws Exception {
        return ResponseEntity.ok().body(safetyActionBizFieldService.getSafetyActionBizField(code));
    }

    /**
     * 경영 기준정보 KPI 분야관리 등록
     * 
     * @param codeMaster
     * @return 코드
     * @throws Exception
     */
    @ApiOperation(value = "경영 기준정보 KPI 분야관리 등록", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "codeMaster", value = "경영 기준정보 KPI 분야관리", required = false, dataType = "CodeMaster", paramType = "body") })
    @PostMapping("safetyactionbizfield")
    public ResponseEntity<String> createSafetyActionBizField(@RequestBody CodeMaster codeMaster) throws Exception {
        return ResponseEntity.ok().body(safetyActionBizFieldService.createSafetyActionBizField(codeMaster) > 0 ? codeMaster.getCode() : "");
    }

    /**
     * 경영 기준정보 KPI 분야관리 수정
     * 
     * @param codeMaster
     * @return 변경 행 수
     * @throws Exception
     */
    @ApiOperation(value = "경영 기준정보 KPI 분야관리 수정", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "codeMaster", value = "경영 기준정보 KPI 분야관리", required = false, dataType = "CodeMaster", paramType = "body") })
    @PutMapping("safetyactionbizfield")
    public ResponseEntity<Integer> updateSafetyActionBizField(@RequestBody CodeMaster codeMaster) throws Exception {
        return ResponseEntity.ok().body(safetyActionBizFieldService.updateSafetyActionBizField(codeMaster));
    }

    /**
     * 경영 기준정보 KPI 분야관리 중복 검사
     * 
     * @param parameter
     * @return 경영 기준정보 KPI 분야관리 중복 검사 List
     * @throws Exception
     */
    @ApiOperation(value = "경영 기준정보 KPI 분야관리 중복 검사", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "code", value = "코드", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "codeNm", value = "분야명", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
    @GetMapping("checksafetyactionbizfield")
    public ResponseEntity<List<HashMap<String, Object>>> checkSafetyActionBizField(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> convertedParameter = requestMapper.convertAsParameter(parameter);
        // 코드
        String code = convertedParameter.containsKey("code") ? convertedParameter.get("code").toString() : "";
        // 코드명(분야명)
        String codeNm = convertedParameter.containsKey("codeNm") ? convertedParameter.get("codeNm").toString() : "";

        return ResponseEntity.ok().body(safetyActionBizFieldService.checkSafetyActionBizField(code, codeNm));
    }

}
