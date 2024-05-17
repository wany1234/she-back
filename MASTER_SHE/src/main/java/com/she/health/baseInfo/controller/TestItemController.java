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
package com.she.health.baseInfo.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.she.health.baseInfo.service.TestItemService;
import com.she.health.model.TestItem;
import com.she.utils.RequestMapper;

/**
 * 건강검진항목
 *
 */
@RestController
public class TestItemController {
    // TODO : queryString 변환을 위한 mapper 선언
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private TestItemService testItemService;

    /**
     * 건강검진항목 조회
     *
     * @param parameter
     *            (검사 분류 코드, 건강검진기관 번호, 검진년도, 종합건강검진유형, 사용 유무, 공통 선택 유무, 검사항목명)
     * @return 건강검진항목 목록
     * @throws Exception
     */
    @GetMapping("/api/hea/baseinfo/testitems")
    public ResponseEntity<List<TestItem>> getTestItems(@RequestParam HashMap<String, Object> parameter)
            throws Exception {
        HashMap<String, Object> map = requestMapper.convertAsParameter(parameter);

        // 검사 분류 코드
        String heaTestClassCd = map.containsKey("heaTestClassCd") ? map.get("heaTestClassCd").toString() : "";
        // 건강검진기관 번호
        int heaCheckupOrgNo = map.containsKey("heaCheckupOrgNo")
                ? Integer.parseInt(map.get("heaCheckupOrgNo").toString()) : 0;
        // 검진년도
        String year = map.containsKey("year") ? map.get("year").toString() : "";
        // 종합건강검진유형
        String heaCheckupTypeCd = map.containsKey("heaCheckupTypeCd") ? map.get("heaCheckupTypeCd").toString() : "";
        // 사용 유무
        String useYn = map.containsKey("useYn") ? map.get("useYn").toString() : "";
        // 공통 선택 유무
        String optionalYn = map.containsKey("optionalYn") ? map.get("optionalYn").toString() : "";
        // 검사항목명
        String heaTestItemNm = map.containsKey("heaTestItemNm") ? map.get("heaTestItemNm").toString() : "";

        List<TestItem> testItem = testItemService.getTestItems(heaTestClassCd, heaCheckupOrgNo, year, heaCheckupTypeCd,
                useYn, optionalYn, heaTestItemNm);
        return ResponseEntity.ok().body(testItem);
    }

    /**
     * 건강검진항목 상세 조회
     *
     * @param heaTestClassCd
     * @return 건강검진항목
     * @throws Exception
     */
    @GetMapping("/api/hea/baseinfo/testitem/{heaTestItemCd}")
    public ResponseEntity<TestItem> getTestItem(@PathVariable("heaTestItemCd") String heaTestItemCd) throws Exception {
        TestItem testItem = testItemService.getTestItem(heaTestItemCd);
        return ResponseEntity.ok().body(testItem);
    }

    /**
     * 건강검진항목 생성
     *
     * @param testItem
     *            건강검진항목
     * @return heaTestItemCd
     * @throws Exception
     */
    @PostMapping("/api/hea/baseinfo/testitem")
    public ResponseEntity<String> createTestItem(@RequestBody TestItem testItem) throws Exception {
        int resultNo = testItemService.createTestItem(testItem);
        return ResponseEntity.ok().body(testItem.getHeaTestItemCd());
    }

    /**
     * 건강검진항목 수정
     *
     * @param testItem
     *            건강검진항목
     * @return 변경 행 수
     * @throws Exception
     */
    @PutMapping("/api/hea/baseinfo/testitem")
    public ResponseEntity<Integer> updateTestItem(@RequestBody TestItem testItem) throws Exception {
        return ResponseEntity.ok().body(testItemService.updateTestItem(testItem));
    }

}
