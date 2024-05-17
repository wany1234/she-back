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
package com.she.env.air.baseInfo.controller;

import java.util.HashMap;
import java.util.List;

import com.she.common.model.DefaultParam;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

import com.she.env.air.baseInfo.service.TestItemService;
import com.she.env.air.model.TestItem;
import com.she.utils.RequestMapper;

/**
 * 대기 검사항목 컨트롤러
 *
 */
@RestController("EairTestItemController")
@RequestMapping("api/env/air/baseinfo")
public class TestItemController {
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private TestItemService testItemService;

    /**
     * 검사항목 전체 조회
     *
     * @param parameter
     *            검색조건
     * @return 검사항목 목록
     * @throws Exception
     */
    @ApiOperation(value = "대기검사항목 조회", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "plantCd", value = "사업장", required = false, dataType = "string", paramType = "query"),
    	@ApiImplicitParam(name = "useYn", value = "사용여부", required = false, dataType = "string", paramType = "query"),
    	@ApiImplicitParam(name = "eairTestItemNm", value = "검사항목", required = false, dataType = "String", paramType = "query")
    	})
    @GetMapping("/testitems")
    public ResponseEntity<List<TestItem>> getTestItems(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = requestMapper.convertAsParameter(parameter);

        // 사용여부
        String useYn = map.containsKey("useYn") ? map.get("useYn").toString() : "";
        // 사업장코드
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 검사항목
        String eairTestItemNm = map.containsKey("eairTestItemNm") ? map.get("eairTestItemNm").toString() : "";

        return ResponseEntity.ok().body(this.testItemService.getTestItems(useYn, plantCd, eairTestItemNm, defaultParam));
    }

    /**
     * 검사항목 상세 조회
     *
     * @param eairTestItemCd
     *            검사항목코드
     * @return 검사항목
     * @throws Exception
     */
    @GetMapping("/testitem/{eairTestItemCd}")
    public ResponseEntity<TestItem> getTestItem(@PathVariable(name = "eairTestItemCd") String eairTestItemCd) throws Exception {
        return ResponseEntity.ok().body(this.testItemService.getTestItem(eairTestItemCd));
    }

    /**
     * 검사항목 신규등록
     *
     * @param testItem
     *            검사항목
     * @return 검사항목코드
     * @throws Exception
     */
    @PostMapping("/testitem")
    public ResponseEntity<String> createTestItem(@RequestBody TestItem testItem) throws Exception {
        return ResponseEntity.ok().body(this.testItemService.createTestItem(testItem));
    }

    /**
     * 검사항목 수정
     *
     * @param testItem
     *            검사항목
     * @return 수정 행 수
     * @throws Exception
     */
    @PutMapping("/testitem")
    public ResponseEntity<String> updateTestItem(@RequestBody TestItem testItem) throws Exception {
        return ResponseEntity.ok().body(this.testItemService.updateTestItem(testItem));
    }

    /**
     * 검사항목명 중복체크
     *
     * @param parameter
     *            중복체크조건
     * @return 중복 행 수
     * @throws Exception
     */
    @GetMapping("/check/testitem")
    public ResponseEntity<Integer> checkTestItem(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 검사항목코드
        String eairTestItemCd = map.containsKey("eairTestItemCd") ? map.get("eairTestItemCd").toString() : "";
        // 검사항목명
        String eairTestItemNm = map.containsKey("eairTestItemNm") ? map.get("eairTestItemNm").toString() : "";
        // 사업장코드
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";

        return ResponseEntity.ok().body(this.testItemService.checkTestItem(eairTestItemCd, eairTestItemNm, plantCd));
    }
}
