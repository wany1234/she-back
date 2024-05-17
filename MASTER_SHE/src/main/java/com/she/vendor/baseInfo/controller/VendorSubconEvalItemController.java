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
package com.she.vendor.baseInfo.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.she.vendor.baseInfo.service.SubconEvalItemService;
import com.she.vendor.baseInfo.service.VendorSubconEvalItemService;
import com.she.vendor.model.SubconEvalItem;
import com.she.utils.RequestMapper;

@RestController
@RequestMapping("api/vendor/baseinfo")
public class VendorSubconEvalItemController {
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private VendorSubconEvalItemService subconEvalItemService;

    /**
     * 협력업체평가항목 조회
     * 
     * @param parameter
     *            검색조건
     * @return 협력업체평가항목 목록
     * 
     * @throws Exception
     */
    @GetMapping("/subconevalitems")
    public ResponseEntity<List<SubconEvalItem>> getSubconEvalItems(@RequestParam HashMap<String, Object> parameter)
            throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 사용여부
        String useYn = map.containsKey("useYn") ? map.get("useYn").toString() : "";
        // 사업장 코드
        String subconEvalPlantCd = map.containsKey("subconEvalPlantCd") ? map.get("subconEvalPlantCd").toString() : "";
        // 평가구분코드
        String subconEvalClsCd = map.containsKey("subconEvalClsCd") ? map.get("subconEvalClsCd").toString() : "";
        // 평가유형코드
        String subconEvalAttCd = map.containsKey("subconEvalAttCd") ? map.get("subconEvalAttCd").toString() : "";
        // 협력업체평가항목명
        String subconEvalItemNm = map.containsKey("subconEvalItemNm") ? map.get("subconEvalItemNm").toString() : "";

        return ResponseEntity.ok().body(subconEvalItemService.getSubconEvalItems(subconEvalPlantCd, subconEvalClsCd,
                subconEvalAttCd, subconEvalItemNm, useYn));
    }

    /**
     * 협력업체평가항목 상세 조회
     * 
     * @param regulItmNo
     *            협력업체평가항목번호
     * @return 협력업체평가항목
     * @throws Exception
     */
    @GetMapping("/subconevalitem/{safSubconEvalItemNo}")
    public ResponseEntity<SubconEvalItem> getSubconEvalItem(
            @PathVariable(name = "safSubconEvalItemNo") int safSubconEvalItemNo) throws Exception {
        return ResponseEntity.ok().body(this.subconEvalItemService.getSubconEvalItem(safSubconEvalItemNo));
    }

    /**
     * 협력업체평가항목 신규등록
     * 
     * @param subconEvalItem
     *            협력업체평가항목
     * @return 협력업체평가항목 코드
     * @throws Exception
     */
    @PostMapping("/subconevalitem")
    public ResponseEntity<Integer> createSubconEvalItem(@RequestBody SubconEvalItem subconEvalItem) throws Exception {
        return ResponseEntity.ok().body(this.subconEvalItemService.createSubconEvalItem(subconEvalItem));
    }

    /**
     * 협력업체평가항목 수정
     * 
     * @param subconEvalItem
     *            협력업체평가항목
     * @return 수정 행 수
     * @throws Exception
     */
    @PutMapping("/subconevalitem")
    public ResponseEntity<Integer> updateSubconEvalItem(@RequestBody SubconEvalItem subconEvalItem) throws Exception {
        return ResponseEntity.ok().body(this.subconEvalItemService.updateSubconEvalItem(subconEvalItem));
    }

    /**
     * 협력업체평가항목명 체크
     * 
     * @param parameter
     *            검색조건
     * @return 체크값
     * @throws Exception
     */
    @GetMapping("/checksubconevalitem")
    public ResponseEntity<List<HashMap<String, Object>>> getCheckSubconEvalItem(
            @RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 협력업체평가항목명
        String subconEvalItemNm = map.containsKey("subconEvalItemNm") ? map.get("subconEvalItemNm").toString() : "";
        // 협력업체평가항목번호
        int safSubconEvalItemNo = map.containsKey("safSubconEvalItemNo") ? Integer.parseInt(
                "".equals(map.get("safSubconEvalItemNo").toString()) ? "0" : map.get("safSubconEvalItemNo").toString())
                : 0;

        return ResponseEntity.ok()
                .body(subconEvalItemService.getCheckSubconEvalItem(subconEvalItemNm, safSubconEvalItemNo));
    }

}
