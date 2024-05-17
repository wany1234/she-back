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

import com.she.safety.baseinfo.service.FacilityInsCkItemService;
import com.she.safety.model.FacilityTypeCheckItem;
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
public class FacilityInsCkItemController {
    // TODO : queryString 변환을 위한 mapper 선언
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private FacilityInsCkItemService facilityInsCheckItemService;

    /**
     * 시설유형별 점검항목 조회
     * 
     * @param param
     *            (사업장코드,시설유형코드,시설점검종류코드)
     * @return 시설유형별 점검항목 목록
     * @throws Exception
     */
    @ApiOperation(value = "시설유형별 점검항목 조회", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "plantCd", value = "사업장코드", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "facilityTypeCd", value = "시설유형코드", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "facilityCheckCd", value = "시설점검종류코드", required = false, dataType = "string", paramType = "query") })
    @GetMapping("/facilitytypecheckitemlist")
    public ResponseEntity<List<FacilityTypeCheckItem>> getFacilityTypeCkItemList(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        // 시설유형명
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 시설유형명
        String facilityTypeCd = map.containsKey("facilityTypeCd") ? map.get("facilityTypeCd").toString() : "";
        // 시설유형명
        String facilityCheckCd = map.containsKey("facilityCheckCd") ? map.get("facilityCheckCd").toString() : "";
        String facilityCheckItemNm = map.containsKey("facilityCheckItemNm") ? map.get("facilityCheckItemNm").toString() : "";

        return ResponseEntity.ok().body(facilityInsCheckItemService.getFacilityTypeCkItemList(plantCd, facilityTypeCd, facilityCheckCd, facilityCheckItemNm));
    }

    /**
     * 시설유형별 점검항목 상세 조회
     *
     * @param facilityCheckItemNo
     *            시설유형별점검항목No
     * @return 점검항목
     * @throws Exception
     */
    @ApiOperation(value = "시설유형 상세 조회", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "facilityCheckItemNo", value = "시설유형별점검항목No", required = false, dataType = "string", paramType = "query") })
    @GetMapping("/facilitytypecheckitem/{facilityCheckItemNo}")
    public ResponseEntity<FacilityTypeCheckItem> getFacilityTypeCkItem(@PathVariable("facilityCheckItemNo") int facilityCheckItemNo) throws Exception {
        return ResponseEntity.ok().body(facilityInsCheckItemService.getFacilityTypeCkItem(facilityCheckItemNo));
    }

    /**
     * 시설유형별 점검항목 등록
     *
     * @param FacilityTypeCheckItem
     *            시설유형별 점검항목
     * @return 점검항목No
     * @throws Exception
     */
    @PostMapping("/facilitytypecheckitem")
    public ResponseEntity<Integer> createFacilityTypeCkItem(@RequestBody FacilityTypeCheckItem facilityTypeCheckItem) throws Exception {
        return ResponseEntity.ok().body(this.facilityInsCheckItemService.createFacilityInsType(facilityTypeCheckItem));
    }

    /**
     * 시설유형별 점검항목 수정
     *
     * @param FacilityTypeCheckItem
     *            시설유형별 점검항목
     * @return 변경 행 수
     * @throws Exception
     */
    @PutMapping("/facilitytypecheckitem")
    public ResponseEntity<Integer> updateFacilityTypeCkItem(@RequestBody FacilityTypeCheckItem facilityTypeCheckItem) throws Exception {
        return ResponseEntity.ok().body(this.facilityInsCheckItemService.updateFacilityInsType(facilityTypeCheckItem));
    }

    /**
     * 시설유형별 점검항목명 체크
     *
     * @param parameter
     *            (점검항목No,점검항목명,시설유형코드)
     * @return 체크값
     * @throws Exception
     */
    @ApiOperation(value = "시설유형코드 중복 체크", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "facilityCheckItemNo", value = "점검항목No", required = false, dataType = "int", paramType = "query"), @ApiImplicitParam(name = "facilityCheckItemNm", value = "점검항목명", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "facilityTypeCd", value = "시설유형코드", required = false, dataType = "string", paramType = "query") })
    @GetMapping("/checkfacilitytypecheckitem")
    public ResponseEntity<List<HashMap<String, Object>>> getCheckFacilityTypeCkItem(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 점검항목No
        int facilityCheckItemNo = map.containsKey("facilityCheckItemNo") ? Integer.parseInt(map.get("facilityCheckItemNo").toString()) : 0;
        // 점검항목명
        String facilityCheckItemNm = map.containsKey("facilityCheckItemNm") ? map.get("facilityCheckItemNm").toString() : "";
        // 시설유형코드
        String facilityTypeCd = map.containsKey("facilityTypeCd") ? map.get("facilityTypeCd").toString() : "";
        return ResponseEntity.ok().body(facilityInsCheckItemService.getCheckFacilityTypeCkItemNm(facilityCheckItemNo, facilityCheckItemNm, facilityTypeCd));
    }
}
