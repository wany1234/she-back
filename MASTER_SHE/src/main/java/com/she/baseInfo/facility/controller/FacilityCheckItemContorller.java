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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.she.baseInfo.facility.service.FacilityCheckItemService;
import com.she.baseInfo.model.FacilityCheckItem;
import com.she.common.model.DefaultParam;
import com.she.utils.RequestMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 설비유형별 점검항목
 *
 */
@RestController
@RequestMapping("/api/baseinfo/")
@Api(value = "/api/baseinfo/", description = "기초정보 서비스")
public class FacilityCheckItemContorller {

    // TODO : queryString 변환을 위한 mapper 선언
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private FacilityCheckItemService facilityCheckItemService;

    /**
     * 설비 유형 List 조회
     *
     * @param parameter
     *            (설비 유형 코드, 설비점검 종류코드)
     * @return 설비유형별 점검항목 List
     * @throws Exception
     */
    @ApiOperation(value = "설비유형별 점검항목 조회[BAS03001]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "plantCd", value = "사업장", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "safCheckTypeCd", value = "설비 점검 종류", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "safFacilityTypeCd", value = "설비유형", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "useYn", value = "사용여부", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
    @GetMapping("facilitycheckitems")
    public ResponseEntity<List<FacilityCheckItem>> getFacilityCheckItems(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> convertedParameter = requestMapper.convertAsParameter(parameter);
        // 사업장
        String plantCd = convertedParameter.containsKey("plantCd") ? convertedParameter.get("plantCd").toString() : "";
        // 설비 점검 종류 코드
        String safCheckTypeCd = convertedParameter.containsKey("safCheckTypeCd") ? convertedParameter.get("safCheckTypeCd").toString() : "";
        // 설비유형 코드
        String safFacilityTypeCd = convertedParameter.containsKey("safFacilityTypeCd") ? convertedParameter.get("safFacilityTypeCd").toString() : "";
        // 사용여부
        String useYn = convertedParameter.containsKey("useYn") ? convertedParameter.get("useYn").toString() : "";
        String safFacilityCheckNm = convertedParameter.containsKey("safFacilityCheckNm") ? convertedParameter.get("safFacilityCheckNm").toString() : "";

        return ResponseEntity.ok().body(facilityCheckItemService.getFacilityCheckItems(plantCd, safCheckTypeCd, safFacilityTypeCd, useYn, safFacilityCheckNm, defaultParam));
    }

    /**
     * 설비유형별 점검항목 상세 조회
     *
     * @param safFacilityCheckItemNo(설비점검항목번호)
     * @return 설비유형별 점검항목
     * @throws Exception
     */
    @ApiOperation(value = "설비유형별 점검항목 상세조회[BAS03002]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "safFacilityCheckItemNo", value = "설비점검항목번호", required = false, dataType = "String", paramType = "path") })
    @GetMapping("facilitycheckitem/{safFacilityCheckItemNo}")
    public ResponseEntity<FacilityCheckItem> getFacilityCheckItem(@PathVariable("safFacilityCheckItemNo") int safFacilityCheckItemNo) throws Exception {
        return ResponseEntity.ok().body(facilityCheckItemService.getFacilityCheckItem(safFacilityCheckItemNo));
    }

    /**
     * 설비유형별 점검항목 생성
     *
     * @param FacilityCheckItem
     *            설비유형별 점검항목
     * @return 설비유형별 점검 항목 번호 key
     * @throws Exception
     */
    @ApiOperation(value = "설비유형별 점검항목 등록[BAS03003]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "facilityCheckItem", value = "설비유형별 점검항목", required = false, dataType = "FacilityCheckItem", paramType = "body") })
    @PostMapping("facilitycheckitem")
    public ResponseEntity<Integer> createFacilityCheckItem(@RequestBody FacilityCheckItem facilityCheckItem) throws Exception {
        return ResponseEntity.ok().body(facilityCheckItemService.createFacilityCheckItem(facilityCheckItem) > 0 ? facilityCheckItem.getSafFacilityCheckItemNo() : 0);
    }

    /**
     * 설비유형별 점검항목 수정
     *
     * @param FacilityCheckItem
     *            설비유형별 점검항목
     * @return 설비유형별 점검 항목 번호 key
     * @throws Exception
     */
    @ApiOperation(value = "설비유형별 점검항목 수정[BAS02004]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "facilityCheckItem", value = "설비유형별 점검항목", required = false, dataType = "FacilityCheckItem", paramType = "body") })
    @PutMapping("facilitycheckitem")
    public ResponseEntity<Integer> updateFacilityCheckItem(@RequestBody FacilityCheckItem facilityCheckItem) throws Exception {
        return ResponseEntity.ok().body(facilityCheckItemService.updateFacilityCheckItem(facilityCheckItem));
    }

    /**
     * 설비유형별 점검항목명 중복 체크
     *
     * @param parameter
     * @return 설비유형별 점검항목명 중복 체크 값
     * @throws Exception
     */
    @ApiOperation(value = "설비유형별 점검항목 조회[BAS03001]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "safFacilityCheckItemNo", value = "설비점검항목번호", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "safFacilityCheckNm", value = "설비점검항목명", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
    @GetMapping("checkfacilitycheckitem")
    public ResponseEntity<List<HashMap<String, Object>>> checkFacilityCheckItem(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> convertedParameter = requestMapper.convertAsParameter(parameter);
        // 설비점검항목번호
        int safFacilityCheckItemNo = convertedParameter.containsKey("safFacilityCheckItemNo") ? Integer.parseInt("".equals(convertedParameter.get("safFacilityCheckItemNo").toString()) ? "0" : convertedParameter.get("safFacilityCheckItemNo").toString()) : 0;
        // 설비점검항목명
        String safFacilityCheckNm = convertedParameter.containsKey("safFacilityCheckNm") ? convertedParameter.get("safFacilityCheckNm").toString() : "";
        // 사업장
        String plantCd = convertedParameter.containsKey("plantCd") ? convertedParameter.get("plantCd").toString() : "";
        // 설비점검종류
        String safCheckTypeCd = convertedParameter.containsKey("safCheckTypeCd") ? convertedParameter.get("safCheckTypeCd").toString() : "";
        // 설비유형
        String safFacilityTypeCd = convertedParameter.containsKey("safFacilityTypeCd") ? convertedParameter.get("safFacilityTypeCd").toString() : "";

        return ResponseEntity.ok().body(facilityCheckItemService.checkFacilityCheckItem(safFacilityCheckItemNo, safFacilityCheckNm, plantCd, safCheckTypeCd, safFacilityTypeCd));
    }

}
