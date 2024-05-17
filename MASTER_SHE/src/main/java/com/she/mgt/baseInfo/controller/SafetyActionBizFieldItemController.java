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

import com.she.mgt.baseInfo.model.BizFieldItem;
import com.she.mgt.baseInfo.service.SafetyActionBizFieldItemService;
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
@Api(value = "/api/mgt/baseinfo/", description = "KPI 분야별 항목관리")
public class SafetyActionBizFieldItemController {

    // TODO : queryString 변환을 위한 mapper 선언
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private SafetyActionBizFieldItemService safetyActionBizFieldItemService;

    /**
     * 경영 기준정보 KPI 분야별 항목관리 조회
     * 
     * @param parameter
     * @return 경영 기준정보 KPI 분야별 항목 목록
     * @throws Exception
     */
    @ApiOperation(value = "경영 기준정보 KPI 분야별 항목관리 조회", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "bizFieldCd", value = "분야코드", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "bizFieldItemNm", value = "항목", required = false, dataType = "java.util.HashMap", paramType = "query"),
            @ApiImplicitParam(name = "useYn", value = "사용여부", required = false, dataType = "java.util.HashMap", paramType = "query"), @ApiImplicitParam(name = "from", value = "시작일", required = false, dataType = "java.util.HashMap", paramType = "query"),
            @ApiImplicitParam(name = "to", value = "종료일", required = false, dataType = "java.util.HashMap", paramType = "query"), @ApiImplicitParam(name = "createUserNm", value = "작성자", required = false, dataType = "java.util.HashMap", paramType = "query") })
    @GetMapping("safetyactionbizfielditems")
    public ResponseEntity<List<BizFieldItem>> getSafetyActionBizFieldItems(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> convertedParameter = requestMapper.convertAsParameter(parameter);
        // 분야코드
        String bizFieldCd = convertedParameter.containsKey("bizFieldCd") ? convertedParameter.get("bizFieldCd").toString() : "";
        // 항목명
        String bizFieldItemNm = convertedParameter.containsKey("bizFieldItemNm") ? convertedParameter.get("bizFieldItemNm").toString() : "";
        // 사용여부
        String useYn = convertedParameter.containsKey("useYn") ? convertedParameter.get("useYn").toString() : "";
        // 시작일
        String from = convertedParameter.containsKey("from") ? convertedParameter.get("from").toString() : "";
        // 종료일
        String to = convertedParameter.containsKey("to") ? convertedParameter.get("to").toString() : "";
        // 작성자
        String createUserNm = convertedParameter.containsKey("createUserNm") ? convertedParameter.get("createUserNm").toString() : "";

        return ResponseEntity.ok().body(safetyActionBizFieldItemService.getSafetyActionBizFieldItems(bizFieldCd, bizFieldItemNm, useYn, from, to, createUserNm));
    }

    /**
     * 경영 기준정보 KPI 분야별 항목관리 상세조회
     * 
     * @param bizFieldItemNo
     *            분야별 항목번호
     * @return 경영 기준정보 KPI 분야별 항목
     * @throws Exception
     */
    @ApiOperation(value = "경영 기준정보 KPI 분야별 항목관리 상세조회", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @GetMapping("safetyactionbizfielditem/{bizFieldItemNo}")
    public BizFieldItem getSafetyActionBizFieldItem(@PathVariable int bizFieldItemNo) throws Exception {
        return safetyActionBizFieldItemService.getSafetyActionBizFieldItem(bizFieldItemNo);
    }

    /**
     * 경영 기준정보 KPI 분야별 항목관리 등록
     * 
     * @param bizFieldItem
     * @return 분야별 항목
     * @throws Exception
     */
    @PostMapping("safetyactionbizfielditem")
    public ResponseEntity<Integer> createSafetyActionBizFieldItem(@RequestBody BizFieldItem bizFieldItem) throws Exception {
        return ResponseEntity.ok().body(safetyActionBizFieldItemService.createSafetyActionBizFieldItem(bizFieldItem));
    }

    /**
     * 경영 기준정보 KPI 분야별 항목관리 수정
     * 
     * @param bizFieldItem
     * @return 변경 행 수
     * @throws Exception
     */
    @PutMapping("safetyactionbizfielditem")
    public ResponseEntity<Integer> updateSafetyActionBizFieldItem(@RequestBody BizFieldItem bizFieldItem) throws Exception {
        return ResponseEntity.ok().body(safetyActionBizFieldItemService.updateSafetyActionBizFieldItem(bizFieldItem));
    }

    /**
     * 경영 기준정보 KPI 분야별 항목관리 중복 검사
     * 
     * @param parameter
     * @return 경영 기준정보 KPI 분야별 항목관리 중복 검사 List
     * @throws Exception
     */
    @ApiOperation(value = "경영 기준정보 KPI 분야별 항목관리 중복 검사", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "bizFieldCd", value = "분야코드", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "bizFieldItemNm", value = "항목", required = false, dataType = "java.util.HashMap", paramType = "query"),
            @ApiImplicitParam(name = "bizFieldItemNo", value = "항목번호", required = false, dataType = "java.util.HashMap", paramType = "query"), })
    @GetMapping("checksafetyactionbizfielditem")
    public ResponseEntity<List<HashMap<String, Object>>> checkSafetyActionBizFieldItem(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> convertedParameter = requestMapper.convertAsParameter(parameter);
        // 분야코드
        String bizFieldCd = convertedParameter.containsKey("bizFieldCd") ? convertedParameter.get("bizFieldCd").toString() : "";
        // 항목명
        String bizFieldItemNm = convertedParameter.containsKey("bizFieldItemNm") ? convertedParameter.get("bizFieldItemNm").toString() : "";
        // 항목번호
        int bizFieldItemNo = convertedParameter.containsKey("bizFieldItemNo") ? Integer.parseInt(convertedParameter.get("bizFieldItemNo").toString()) : 0;

        return ResponseEntity.ok().body(safetyActionBizFieldItemService.checkSafetyActionBizFieldItem(bizFieldCd, bizFieldItemNm, bizFieldItemNo));
    }

}
