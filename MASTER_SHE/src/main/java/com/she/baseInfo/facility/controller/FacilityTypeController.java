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
package com.she.baseInfo.facility.controller;

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

import com.she.baseInfo.model.FacilityType;
import com.she.baseInfo.facility.service.FacilityTypeService;
import com.she.utils.RequestMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 설비유형 관리
 *
 */
@RestController
@RequestMapping("/api/baseinfo/")
@Api(value = "/api/baseinfo/", description = "기초정보 서비스")
public class FacilityTypeController {
    // TODO : queryString 변환을 위한 mapper 선언
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private FacilityTypeService facilityTypeService;

    /**
     * 설비유형 조회
     * 
     * @param parameter
     *            (설비 유형명,사용여부)
     * @return 설비유형 목록
     * @throws Exception
     */
    @ApiOperation(value = "설비유형 조회[BAS01001]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "safFacilityTypeNm", value = "설비유형명", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "psafFacilityTypeCd", value = "상위설비유형코드", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "useYn", value = "사용여부", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
    @GetMapping("facilitytypes")
    public ResponseEntity<List<FacilityType>> getFacilityTypes(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> convertedParameter = requestMapper.convertAsParameter(parameter);
        // 설비유형명
        String safFacilityTypeNm = convertedParameter.containsKey("safFacilityTypeNm") ? convertedParameter.get("safFacilityTypeNm").toString() : "";
        // 상위설비유형코드
        String psafFacilityTypeCd = convertedParameter.containsKey("psafFacilityTypeCd") ? convertedParameter.get("psafFacilityTypeCd").toString() : "";
        // 사용여부
        String useYn = convertedParameter.containsKey("useYn") ? convertedParameter.get("useYn").toString() : "";

        return ResponseEntity.ok().body(facilityTypeService.getFacilityTypes(safFacilityTypeNm, psafFacilityTypeCd, useYn));

    }

    /**
     * 설비유형 상세 조회
     * 
     * @param safFacilityTypeCd
     *            설비유형코드
     * @return 설비유형
     * @throws Exception
     */
    @ApiOperation(value = "설비유형 상세조회[BAS01002]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "safFacilityTypeCd", value = "설비유형코드", required = false, dataType = "String", paramType = "path") })
    @GetMapping("facilitytype/{safFacilityTypeCd}")
    public ResponseEntity<FacilityType> getFacilityType(@PathVariable("safFacilityTypeCd") String safFacilityTypeCd) throws Exception {
        return ResponseEntity.ok().body(facilityTypeService.getFacilityType(safFacilityTypeCd));
    }

    /**
     * 설비유형 등록
     * 
     * @param FacilityInsType
     *            설비 유형
     * @return 설비유형코드
     * @throws Exception
     */
    @ApiOperation(value = "설비유형 등록[BAS01003]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "facilityType", value = "설비유형", required = false, dataType = "FacilityType", paramType = "body") })
    @PostMapping("facilitytype")
    public ResponseEntity<String> createFacilityType(@RequestBody FacilityType facilityType) throws Exception {
        return ResponseEntity.ok().body(facilityTypeService.createFacilityType(facilityType));
    }

    /**
     * 설비 유형 수정
     * 
     * @param FacilityInsType
     *            설비 유형
     * @return 설비유형코드
     * @throws Exception
     */
    @ApiOperation(value = "설비유형 수정[BAS01004]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "facilityType", value = "설비유형", required = false, dataType = "FacilityType", paramType = "body") })
    @PutMapping("facilitytype")
    public ResponseEntity<Integer> updateFacilityType(@RequestBody FacilityType facilityType) throws Exception {

        return ResponseEntity.ok().body(facilityTypeService.updateFacilityType(facilityType));
    }

    /**
     * 설비유형 중복검사
     * 
     * @param parameter
     * @return 설비유형 중복검사
     * @throws Exception
     */
    @ApiOperation(value = "설비유형 중복검사[BAS01005]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "safFacilityTypeNm", value = "설비유형명", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "safFacilityTypeCd", value = "설비유형코드", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
    @GetMapping("checkfacilitytype")
    public ResponseEntity<List<HashMap<String, Object>>> getCheckFacilityType(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> convertedParameter = requestMapper.convertAsParameter(parameter);
        // 설비유형명
        String safFacilityTypeNm = convertedParameter.containsKey("safFacilityTypeNm") ? convertedParameter.get("safFacilityTypeNm").toString() : "";
        // 설비유형코드
        String safFacilityTypeCd = convertedParameter.containsKey("safFacilityTypeCd") ? convertedParameter.get("safFacilityTypeCd").toString() : "";

        return ResponseEntity.ok().body(facilityTypeService.getCheckFacilityType(safFacilityTypeNm, safFacilityTypeCd));
    }

}
