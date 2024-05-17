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

import com.she.baseInfo.model.FacilityTypeItem;
import com.she.baseInfo.facility.service.FacilityTypeItemService;
import com.she.utils.RequestMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 설비유형별 상세항목
 *
 */
@RestController
@RequestMapping("/api/baseinfo/")
@Api(value = "/api/baseinfo/", description = "기초정보 서비스")
public class FacilityTypeItemController {

    // TODO : queryString 변환을 위한 mapper 선언
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private FacilityTypeItemService facilityTypeItemService;

    /**
     * 설비유형별 상세항목 조회
     *
     * @param parameter
     * @return 설비유형별 관리항목 목록
     * @throws Exception
     */
    @ApiOperation(value = "설비유형별 상세항목 조회[BAS02001]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "safFacilityTypeNm", value = "설비유형명", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "psafFacilityTypeCd", value = "상위설비유형코드", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "useYn", value = "사용여부", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
    @GetMapping("facilitytypeitems")
    public ResponseEntity<List<FacilityTypeItem>> getFacilityTypeItems(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> convertedParameter = requestMapper.convertAsParameter(parameter);
        // 설비 유형 코드
        String safFacilityTypeCd = convertedParameter.containsKey("safFacilityTypeCd") ? convertedParameter.get("safFacilityTypeCd").toString() : "";
        // 관리항목 명
        String safFacilityTypeItemNm = convertedParameter.containsKey("safFacilityTypeItemNm") ? convertedParameter.get("safFacilityTypeItemNm").toString() : "";
        // 사용여부
        String useYn = convertedParameter.containsKey("useYn") ? convertedParameter.get("useYn").toString() : "";

        return ResponseEntity.ok().body(facilityTypeItemService.getFacilityTypeItems(safFacilityTypeCd, safFacilityTypeItemNm, useYn));
    }

    /**
     * 설비유형별 상세항목 상세조회
     *
     * @param parameter
     *            (안전설비유형관리항목번호)
     * @return 설비유형별 관리항목
     * @throws Exception
     */
    @ApiOperation(value = "설비유형별 상세항목 상세조회[BAS02002]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "safFacilityTypeItemNo", value = "설비유형관리항목번호", required = false, dataType = "String", paramType = "path") })
    @GetMapping("facilitytypeitem/{safFacilityTypeItemNo}")
    public ResponseEntity<FacilityTypeItem> getFacilityTypeItem(@PathVariable("safFacilityTypeItemNo") int safFacilityTypeItemNo) throws Exception {
        return ResponseEntity.ok().body(facilityTypeItemService.getFacilityTypeItem(safFacilityTypeItemNo));
    }

    /**
     * 설비유형별 상세항목 신규
     *
     * @param FacilityTypeItem
     *            설비 유형별 관리 항목
     * @return 설비유형별 관리항목 key 값
     * @throws Exception
     */
    @ApiOperation(value = "설비유형별 상세항목 등록[BAS02003]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "facilityTypeItem", value = "설비유형별 상세항목", required = false, dataType = "FacilityTypeItem", paramType = "body") })
    @PostMapping("facilitytypeitem")
    public ResponseEntity<Integer> createFacilityTypeItem(@RequestBody FacilityTypeItem facilityTypeItem) throws Exception {

        return ResponseEntity.ok().body(facilityTypeItemService.createFacilityTypeItem(facilityTypeItem) > 0 ? facilityTypeItem.getSafFacilityTypeItemNo() : 0);
    }

    /**
     * 설비 유형별 상세항목 수정
     *
     * @param FacilityTypeItem
     *            설비 유형별 관리 항목
     * @return 설비 유형별 관리 항목 key 값
     * @throws Exception
     */
    @ApiOperation(value = "설비유형별 상세항목 수정[BAS02004]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "facilityTypeItem", value = "설비유형별 상세항목", required = false, dataType = "FacilityTypeItem", paramType = "body") })
    @PutMapping("facilitytypeitem")
    public ResponseEntity<Integer> updateFacilityTypeItem(@RequestBody FacilityTypeItem facilityTypeItem) throws Exception {
        return ResponseEntity.ok().body(facilityTypeItemService.updateFacilityTypeItem(facilityTypeItem));
    }

    /**
     * 설비유형별 상세항목 중복검사
     *
     * @param parameter
     * @return 설비유형별 상세항목 중복검사
     * @throws Exception
     */
    @ApiOperation(value = "설비유형별 상세항목 중복검사[BAS02005]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "safFacilityTypeNm", value = "설비유형명", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "safFacilityTypeItemNo", value = "설비유형관리항목번호", required = false, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
    @GetMapping("checkfacilitytypeitem")
    public ResponseEntity<List<HashMap<String, Object>>> getCheckFacilityTypeItem(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> convertedParameter = requestMapper.convertAsParameter(parameter);
        // 사내사고번호
        int safFacilityTypeItemNo = convertedParameter.containsKey("safFacilityTypeItemNo") ? Integer.parseInt("".equals(convertedParameter.get("safFacilityTypeItemNo").toString()) ? "0" : convertedParameter.get("safFacilityTypeItemNo").toString()) : 0;
        // 관리항목 명
        String safFacilityTypeItemNm = convertedParameter.containsKey("safFacilityTypeItemNm") ? convertedParameter.get("safFacilityTypeItemNm").toString() : "";
        // 설비유형
        String safFacilityTypeCd = convertedParameter.containsKey("safFacilityTypeCd") ? convertedParameter.get("safFacilityTypeCd").toString() : "";

        return ResponseEntity.ok().body(facilityTypeItemService.getCheckFacilityTypeItem(safFacilityTypeItemNo, safFacilityTypeItemNm, safFacilityTypeCd));
    }

}
