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

import com.she.safety.baseinfo.service.FacilityInsTypeService;
import com.she.safety.model.FacilityInsType;
import com.she.utils.RequestMapper;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 시설유형
 *
 */
@RestController
@RequestMapping("api/saf/baseinfo")
public class FacilityInsTypeController {
    // TODO : queryString 변환을 위한 mapper 선언
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private FacilityInsTypeService facilityInsTypeService;

    /**
     * 시설유형 리스트 조회
     * 
     * @param param
     *            (시설유형명)
     * @return 시설유형 목록
     * @throws Exception
     */
    @ApiOperation(value = "시설유형 리스트 조회", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "facilityTypeNm", value = "시설유형명", required = false, dataType = "string", paramType = "query") })
    @GetMapping("/facilityinstypelist")
    public ResponseEntity<List<FacilityInsType>> getFacilityTypeList(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        // 시설유형명
        String facilityTypeNm = map.containsKey("facilityTypeNm") ? map.get("facilityTypeNm").toString() : "";

        return ResponseEntity.ok().body(facilityInsTypeService.getFacilityTypeList(facilityTypeNm));
    }

    /**
     * 시설유형 상세 조회
     *
     * @param facilityTypeCd
     *            시설유형코드
     * @return 시설유형
     * @throws Exception
     */
    @ApiOperation(value = "시설유형 상세 조회", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "facilityTypeCd", value = "시설유형코드", required = false, dataType = "string", paramType = "query") })
    @GetMapping("/facilityinstype/{facilityTypeCd}")
    public ResponseEntity<FacilityInsType> getFacilityType(@PathVariable("facilityTypeCd") String facilityTypeCd) throws Exception {
        return ResponseEntity.ok().body(facilityInsTypeService.getFacilityType(facilityTypeCd));
    }

    /**
     * 시설유형 신규등록
     * 
     * @param FacilityInsType
     *            시설유형
     * @return 시설유형 코드
     * @throws Exception
     */
    @ApiOperation(value = "시설유형 신규등록", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "FacilityInsType", value = "시설유형", required = false, dataType = "FacilityInsType", paramType = "body") })
    @PostMapping("/facilityinstype")
    public ResponseEntity<String> createChemicalVendorMaster(@RequestBody FacilityInsType facilityInsType) throws Exception {
        return ResponseEntity.ok().body(this.facilityInsTypeService.createFacilityInsType(facilityInsType));
    }

    /**
     * 시설유형 수정
     * 
     * @param FacilityInsType
     *            시설유형
     * @return 수정 행 수
     * @throws Exception
     */
    @ApiOperation(value = "시설유형 수정", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "FacilityInsType", value = "시설유형", required = false, dataType = "FacilityInsType", paramType = "body") })
    @PutMapping("/facilityinstype")
    public ResponseEntity<Integer> updateChemicalVendorMaster(@RequestBody FacilityInsType facilityInsType) throws Exception {
        return ResponseEntity.ok().body(this.facilityInsTypeService.updateFacilityInsType(facilityInsType));
    }

    /**
     * 시설유형코드 체크
     * 
     * @param parameter
     *            (시설유형코드)
     * @return 체크값
     * @throws Exception
     */
    @ApiOperation(value = "시설유형코드 중복 체크", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "facilityTypeCd", value = "시설유형코드", required = false, dataType = "string", paramType = "query") })
    @GetMapping("/checkfacilitytype")
    public ResponseEntity<List<HashMap<String, Object>>> getCheckFacilityType(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 업체명
        String facilityTypeCd = map.containsKey("facilityTypeCd") ? map.get("facilityTypeCd").toString() : "";

        return ResponseEntity.ok().body(facilityInsTypeService.getCheckChemicalVendorMaster(facilityTypeCd));
    }
}
