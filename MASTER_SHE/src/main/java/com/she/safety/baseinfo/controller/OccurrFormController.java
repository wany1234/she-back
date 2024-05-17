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

import java.util.Arrays;
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
import com.she.safety.baseinfo.service.OccurrFormService;
import com.she.utils.RequestMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 발생형태(대분류/중분류) 항목
 *
 */
@RestController
@RequestMapping("/api/saf/baseinfo/")
@Api(value = "/api/saf/baseinfo/", description = "안전 기준정보 서비스")
public class OccurrFormController {
    // TODO : queryString 변환을 위한 mapper 선언
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private OccurrFormService occurrFormService;

    /**
     * 안전 기준정보 발생형태(대분류) 조회
     * 
     * @param parameter
     * @return 안전 기준정보 발생형태(대분류) 목록
     * @throws Exception
     */
    @ApiOperation(value = "안전 기준정보 발생형태(대분류) 조회[SAF14001]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "codeNm", value = "코드명(대분류명)", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
    @GetMapping("occurrforms")
    public ResponseEntity<List<CodeMaster>> getOccurrForms(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> convertedParameter = requestMapper.convertAsParameter(parameter);
        // 코드명(발생형태 대분류명)
        String codeNm = convertedParameter.containsKey("codeNm") ? convertedParameter.get("codeNm").toString() : "";
        // 사용여부
        String useYn = convertedParameter.containsKey("useYn") ? convertedParameter.get("useYn").toString() : "";

        return ResponseEntity.ok().body(occurrFormService.getOccurrForms(codeNm, useYn));
    }

    /**
     * 안전 기준정보 발생형태(대분류) 상세조회
     * 
     * @param code
     *            코드
     * @return 안전 기준정보 발생형태(대분류)
     * @throws Exception
     */
    @ApiOperation(value = "안전 기준정보 발생형태(대분류) 상세조회[SAF14002]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "code", value = "코드", required = false, dataType = "string", paramType = "path") })
    @GetMapping("occurrform/{code}")
    public ResponseEntity<CodeMaster> getOccurrForm(@PathVariable("code") String code) throws Exception {
        return ResponseEntity.ok().body(occurrFormService.getOccurrForm(code));
    }

    /**
     * 안전 기준정보 발생형태(대분류) 등록
     * 
     * @param codeMaster
     * @return 코드
     * @throws Exception
     */
    @ApiOperation(value = "안전 기준정보 발생형태(대분류) 등록[SAF14003]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "codeMaster", value = "안전 기준정보 발생형태(대분류)", required = false, dataType = "CodeMaster", paramType = "body") })
    @PostMapping("occurrform")
    public ResponseEntity<String> createOccurrForm(@RequestBody CodeMaster codeMaster) throws Exception {
        return ResponseEntity.ok().body(occurrFormService.createOccurrForm(codeMaster) > 0 ? codeMaster.getCode() : "");
    }

    /**
     * 안전 기준정보 발생형태(대분류) 수정
     * 
     * @param codeMaster
     * @return 변경 행 수
     * @throws Exception
     */
    @ApiOperation(value = "안전 기준정보 발생형태(대분류) 수정[SAF14004]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "codeMaster", value = "안전 기준정보 발생형태(대분류)", required = false, dataType = "CodeMaster", paramType = "body") })
    @PutMapping("occurrform")
    public ResponseEntity<Integer> updateOccurrForm(@RequestBody CodeMaster codeMaster) throws Exception {
        return ResponseEntity.ok().body(occurrFormService.updateOccurrForm(codeMaster));
    }

    /**
     * 안전 기준정보 발생형태(대분류) 중복 검사
     * 
     * @param parameter
     * @return 안전 기준정보 발생형태(대분류) 중복 검사 List
     * @throws Exception
     */
    @ApiOperation(value = "안전 기준정보 발생형태(대분류) 중복 검사[SAF14005]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "code", value = "코드", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "codeNm", value = "코드명(발생형태 대분류명)", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
    @GetMapping("checkoccurrform")
    public ResponseEntity<List<HashMap<String, Object>>> checkOccurrForm(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> convertedParameter = requestMapper.convertAsParameter(parameter);
        // 코드
        String code = convertedParameter.containsKey("code") ? convertedParameter.get("code").toString() : "";
        // 코드명(발생형태 대분류명)
        String codeNm = convertedParameter.containsKey("codeNm") ? convertedParameter.get("codeNm").toString() : "";

        return ResponseEntity.ok().body(occurrFormService.checkOccurrForm(code, codeNm));
    }

    /**
     * 안전 기준정보 발생형태(중분류) 조회
     * 
     * @param parameter
     * @return 안전 기준정보 발생형태(중분류) 목록
     * @throws Exception
     */
    @ApiOperation(value = "안전 기준정보 발생형태(중분류) 조회[SAF14006]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "codeGroupCd", value = "코드그룹코드", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
    @GetMapping("occurratts")
    public ResponseEntity<List<CodeMaster>> getOccurrAtts(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> convertedParameter = requestMapper.convertAsParameter(parameter);
        // 코드그룹코드
        String codeGroupCd = convertedParameter.containsKey("codeGroupCd") ? convertedParameter.get("codeGroupCd").toString() : "";

        return ResponseEntity.ok().body(occurrFormService.getOccurrAtts(codeGroupCd));
    }

    /**
     * 안전 기준정보 발생형태(중분류) 상세조회
     * 
     * @param code
     *            코드
     * @return 안전 기준정보 발생형태(중분류)
     * @throws Exception
     */
    @ApiOperation(value = "안전 기준정보 발생형태(중분류) 상세조회[SAF14007]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "codeGroupCd", value = "코드그룹코드", required = false, dataType = "string", paramType = "path"), @ApiImplicitParam(name = "code", value = "코드", required = false, dataType = "string", paramType = "path") })
    @GetMapping("occurratt/{codeGroupCd}/{code}")
    public ResponseEntity<CodeMaster> getOccurrAtt(@PathVariable("codeGroupCd") String codeGroupCd, @PathVariable("code") String code) throws Exception {
        return ResponseEntity.ok().body(occurrFormService.getOccurrAtt(codeGroupCd, code));
    }

    /**
     * 안전 기준정보 발생형태(중분류) 등록
     * 
     * @param codeMaster
     * @return 코드
     * @throws Exception
     */
    @ApiOperation(value = "안전 기준정보 발생형태(중분류) 등록[SAF14008]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "codeMaster", value = "안전 기준정보 발생형태(중분류)", required = false, dataType = "CodeMaster", paramType = "body") })
    @PostMapping("occurratt")
    public ResponseEntity<String> createOccurrAtt(@RequestBody CodeMaster codeMaster) throws Exception {
        return ResponseEntity.ok().body(occurrFormService.createOccurrAtt(codeMaster) > 0 ? codeMaster.getCode() : "");
    }

    /**
     * 안전 기준정보 발생형태(중분류) 수정
     * 
     * @param codeMaster
     * @return 변경 행 수
     * @throws Exception
     */
    @ApiOperation(value = "안전 기준정보 발생형태(중분류) 수정[SAF14009]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "codeMaster", value = "안전 기준정보 발생형태(중분류)", required = false, dataType = "CodeMaster", paramType = "body") })
    @PutMapping("occurratt")
    public ResponseEntity<Integer> updateOccurrAtt(@RequestBody CodeMaster codeMaster) throws Exception {
        return ResponseEntity.ok().body(occurrFormService.updateOccurrAtt(codeMaster));
    }

    /**
     * 안전 기준정보 발생형태(중분류) 중복 검사
     * 
     * @param parameter
     * @return 안전 기준정보 발생형태(중분류) 중복 검사 List
     * @throws Exception
     */
    @ApiOperation(value = "안전 기준정보 발생형태(중분류) 중복 검사[SAF14010]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "code", value = "코드", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "codeNm", value = "코드명(발생형태 중분류명)", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
    @GetMapping("checkoccurratt")
    public ResponseEntity<List<HashMap<String, Object>>> checkOccurrAtt(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> convertedParameter = requestMapper.convertAsParameter(parameter);
        // 코드
        String codeGroupCd = convertedParameter.containsKey("codeGroupCd") ? convertedParameter.get("codeGroupCd").toString() : "";
        // 코드
        String code = convertedParameter.containsKey("code") ? convertedParameter.get("code").toString() : "";
        // 코드명(발생형태 중분류명)
        String codeNm = convertedParameter.containsKey("codeNm") ? convertedParameter.get("codeNm").toString() : "";

        return ResponseEntity.ok().body(occurrFormService.checkOccurrAtt(codeGroupCd, code, codeNm));
    }

    /**
     * 안전 기준정보 발생형태(중분류) 중복 검사
     * 
     * @param parameter
     * @return 안전 기준정보 발생형태(중분류) 중복 검사 List
     * @throws Exception
     */
    @ApiOperation(value = "안전 기준정보 발생형태(중분류) 중복 검사[SAF14011]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "code", value = "코드", required = true, dataType = "string", paramType = "query") })
    @GetMapping("checkoccurratts")
    public ResponseEntity<List<HashMap<String, Object>>> checkoccurratts(@RequestParam(value = "code", required = true) String code) throws Exception {
        List<String> codes = Arrays.asList(code.split(","));
        return ResponseEntity.ok().body(occurrFormService.checkOccurrAtts(codes));
    }

}
