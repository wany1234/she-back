package com.she.env.water.baseInfo.controller;

import java.util.HashMap;
import java.util.List;

import com.she.common.model.DefaultParam;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.she.env.water.baseInfo.service.TestItemService;
import com.she.env.water.model.TestItem;
import com.she.utils.RequestMapper;

@RestController("ewtrTestItemController")
@RequestMapping("api/env/water/baseinfo/testitem")
public class TestItemController {
    @Autowired
    private TestItemService testItemService;

    @Autowired
    private RequestMapper requestMapper;

    /**
     * 수질 검사항목 조회
     *
     * @param useYn
     *            사용여부
     * @return 수질 검사항목 목록
     * @throws Exception
     *             예외
     */
    @GetMapping("/testitems")
    public ResponseEntity<List<TestItem>> getTestItems(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        String useYn = map.containsKey("useYn") ? map.get("useYn").toString() : "";
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        String ewtrTestItemNm = map.containsKey("ewtrTestItemNm") ? map.get("ewtrTestItemNm").toString() : "";

        List<TestItem> testItemList = testItemService.getTestItems(useYn, plantCd, ewtrTestItemNm, defaultParam);

        return ResponseEntity.ok().body(testItemList);
    }

    /**
     * 수질 검사항목 상세조회
     *
     * @param ewtrTestItemCd
     *            수질 검사항목코드
     * @return TestItem 수질 검사항목
     * @throws Exception
     *             예외
     */
    @GetMapping("/testitem/{ewtrTestItemCd}")
    public ResponseEntity<TestItem> getTestItem(@PathVariable("ewtrTestItemCd") String ewtrTestItemCd) throws Exception {
        TestItem testItem = testItemService.getTestItem(ewtrTestItemCd);
        return ResponseEntity.ok().body(testItem);
    }

    /**
     * 수질 검사항목 신규등록
     *
     * @param TestItem
     *            수질 검사항목
     * @return ewtrTestItemCd 수질 검사항목코드
     * @throws Exception
     *             예외
     */
    @PostMapping("/testitem")
    public ResponseEntity<String> createTestItem(@RequestBody TestItem testItem) throws Exception {
        return ResponseEntity.ok().body(testItemService.createTestItem(testItem));
    }

    /**
     * 수질 검사항목 체크
     *
     * @return 배출수 체크 값
     * @throws Exception
     *             예외
     */
    @GetMapping("/check")
    public ResponseEntity<Integer> getTestItemCheck(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        String ewtrTestItemNm = map.containsKey("ewtrTestItemNm") ? map.get("ewtrTestItemNm").toString() : "";
        int ewtrTestItemCd = map.containsKey("ewtrTestItemCd") ? Integer.parseInt("".equals(map.get("ewtrTestItemCd").toString()) ? "0" : map.get("ewtrTestItemCd").toString()) : 0;

        return ResponseEntity.ok().body(testItemService.getTestItemCheck(plantCd, ewtrTestItemNm, ewtrTestItemCd));
    }

    /**
     * 수질 검사항목 수정
     *
     * @param TestItem
     *            수질 검사항목
     * @return 수정행수
     * @throws Exception
     *             예외
     */
    @PutMapping("/testitem")
    public ResponseEntity<Integer> updateTestItem(@RequestBody TestItem testItem) throws Exception {
        return ResponseEntity.ok().body(testItemService.updateTestItem(testItem));
    }

}
