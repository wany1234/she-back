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
package com.she.safety.baseinfo.controller;

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
import com.she.safety.baseinfo.service.ImmCauseService;
import com.she.utils.RequestMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 안전 기준정보 원인(직접) 항목
 *
 */
@RestController
@RequestMapping("/api/saf/baseinfo/")
@Api(value = "/api/saf/baseinfo/", description = "안전 기준정보 서비스")
public class ImmCauseController {
    // TODO : queryString 변환을 위한 mapper 선언
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private ImmCauseService immCauseService;

    /**
     * 안전 기준정보 원인(직접) 조회
     * 
     * @param parameter
     * @return 안전 기준정보 원인(직접) 목록
     * @throws Exception
     */
    @ApiOperation(value = "안전 기준정보 원인(직접) 조회[SAF12001]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "codeNm", value = "코드명(인적피해명)", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
    @GetMapping("immcauses")
    public ResponseEntity<List<CodeMaster>> getImmCauses(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> convertedParameter = requestMapper.convertAsParameter(parameter);
        // 코드명(원인(직접)명)
        String codeNm = convertedParameter.containsKey("codeNm") ? convertedParameter.get("codeNm").toString() : "";
        // 사용여부
        String useYn = convertedParameter.containsKey("useYn") ? convertedParameter.get("useYn").toString() : "";

        return ResponseEntity.ok().body(immCauseService.getImmCauses(codeNm, useYn));
    }

    /**
     * 안전 기준정보 원인(직접) 상세조회
     * 
     * @param code
     *            코드
     * @return 안전 기준정보 원인(직접)
     * @throws Exception
     */
    @ApiOperation(value = "안전 기준정보 원인(직접) 상세조회[SAF12002]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "code", value = "코드", required = false, dataType = "string", paramType = "path") })
    @GetMapping("immcause/{code}")
    public ResponseEntity<CodeMaster> getImmCause(@PathVariable("code") String code) throws Exception {
        return ResponseEntity.ok().body(immCauseService.getImmCause(code));
    }

    /**
     * 안전 기준정보 원인(직접) 등록
     * 
     * @param codeMaster
     * @return 코드
     * @throws Exception
     */
    @ApiOperation(value = "안전 기준정보 원인(직접) 등록[SAF12003]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "codeMaster", value = "안전 기준정보 원인(직접)", required = false, dataType = "CodeMaster", paramType = "body") })
    @PostMapping("immcause")
    public ResponseEntity<String> createImmCause(@RequestBody CodeMaster codeMaster) throws Exception {
        return ResponseEntity.ok().body(immCauseService.createImmCause(codeMaster) > 0 ? codeMaster.getCode() : "");
    }

    /**
     * 안전 기준정보 인적피해 수정
     * 
     * @param codeMaster
     * @return 변경 행 수
     * @throws Exception
     */
    @ApiOperation(value = "안전 기준정보 원인(직접) 수정[SAF12004]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "codeMaster", value = "안전 기준정보 원인(직접)", required = false, dataType = "CodeMaster", paramType = "body") })
    @PutMapping("immcause")
    public ResponseEntity<Integer> updateImmCause(@RequestBody CodeMaster codeMaster) throws Exception {
        return ResponseEntity.ok().body(immCauseService.updateImmCause(codeMaster));
    }

    /**
     * 안전 기준정보 원인(직접) 중복 검사
     * 
     * @param parameter
     * @return 안전 기준정보 원인(직접) 중복 검사 List
     * @throws Exception
     */
    @ApiOperation(value = "안전 기준정보 원인(직접) 중복 검사[SAF12005]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "code", value = "코드", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "codeNm", value = "코드명(인적피해명)", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
    @GetMapping("checkimmcause")
    public ResponseEntity<List<HashMap<String, Object>>> checkImmCause(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> convertedParameter = requestMapper.convertAsParameter(parameter);
        // 코드
        String code = convertedParameter.containsKey("code") ? convertedParameter.get("code").toString() : "";
        // 코드명(원인(직접)명)
        String codeNm = convertedParameter.containsKey("codeNm") ? convertedParameter.get("codeNm").toString() : "";

        return ResponseEntity.ok().body(immCauseService.checkImmCause(code, codeNm));
    }

}
