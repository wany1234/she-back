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
import com.she.safety.baseinfo.service.HumanDamageService;
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
@RequestMapping("/api/saf/baseinfo/")
@Api(value = "/api/saf/baseinfo/", description = "안전 기준정보 서비스")
public class HumanDamageController {

    // TODO : queryString 변환을 위한 mapper 선언
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private HumanDamageService humanDamageService;

    /**
     * 안전 기준정보 인적피해 조회
     * 
     * @param parameter
     * @return 안전 기준정보 인적피해 목록
     * @throws Exception
     */
    @ApiOperation(value = "안전 기준정보 인적피해 조회[SAF11001]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "codeNm", value = "코드명(인적피해명)", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
    @GetMapping("humandamages")
    public ResponseEntity<List<CodeMaster>> getHumanDamages(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> convertedParameter = requestMapper.convertAsParameter(parameter);
        // 코드명(인적피해명)
        String codeNm = convertedParameter.containsKey("codeNm") ? convertedParameter.get("codeNm").toString() : "";
        // 사용여부
        String useYn = convertedParameter.containsKey("useYn") ? convertedParameter.get("useYn").toString() : "";

        return ResponseEntity.ok().body(humanDamageService.getHumanDamages(codeNm, useYn));
    }

    /**
     * 안전 기준정보 인적피해 상세조회
     * 
     * @param code
     *            코드
     * @return 안전 기준정보 인적피해
     * @throws Exception
     */
    @ApiOperation(value = "안전 기준정보 인적피해 상세조회[SAF11002]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "code", value = "코드", required = false, dataType = "string", paramType = "path") })
    @GetMapping("humandamage/{code}")
    public ResponseEntity<CodeMaster> getHumanDamage(@PathVariable("code") String code) throws Exception {
        return ResponseEntity.ok().body(humanDamageService.getHumanDamage(code));
    }

    /**
     * 안전 기준정보 인적피해 등록
     * 
     * @param codeMaster
     * @return 코드
     * @throws Exception
     */
    @ApiOperation(value = "안전 기준정보 인적피해 등록[SAF11003]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "codeMaster", value = "안전 기준정보 인적피해", required = false, dataType = "CodeMaster", paramType = "body") })
    @PostMapping("humandamage")
    public ResponseEntity<String> createHumanDamage(@RequestBody CodeMaster codeMaster) throws Exception {
        return ResponseEntity.ok().body(humanDamageService.createHumanDamage(codeMaster) > 0 ? codeMaster.getCode() : "");
    }

    /**
     * 안전 기준정보 인적피해 수정
     * 
     * @param codeMaster
     * @return 변경 행 수
     * @throws Exception
     */
    @ApiOperation(value = "안전 기준정보 인적피해 수정[SAF11004]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "codeMaster", value = "안전 기준정보 인적피해", required = false, dataType = "CodeMaster", paramType = "body") })
    @PutMapping("humandamage")
    public ResponseEntity<Integer> updateHumanDamage(@RequestBody CodeMaster codeMaster) throws Exception {
        return ResponseEntity.ok().body(humanDamageService.updateHumanDamage(codeMaster));
    }

    /**
     * 안전 기준정보 인적피해 중복 검사
     * 
     * @param parameter
     * @return 안전 기준정보 인적피해 중복 검사 List
     * @throws Exception
     */
    @ApiOperation(value = "안전 기준정보 인적피해 중복 검사[SAF11005]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "code", value = "코드", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "codeNm", value = "코드명(인적피해명)", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
    @GetMapping("checkhumandamage")
    public ResponseEntity<List<HashMap<String, Object>>> checkHumanDamage(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> convertedParameter = requestMapper.convertAsParameter(parameter);
        // 코드
        String code = convertedParameter.containsKey("code") ? convertedParameter.get("code").toString() : "";
        // 코드명(인적피해명)
        String codeNm = convertedParameter.containsKey("codeNm") ? convertedParameter.get("codeNm").toString() : "";

        return ResponseEntity.ok().body(humanDamageService.checkHumanDamage(code, codeNm));
    }

}
