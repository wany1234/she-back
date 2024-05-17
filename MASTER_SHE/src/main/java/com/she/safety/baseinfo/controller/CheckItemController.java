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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.she.common.model.DefaultParam;
import com.she.safety.baseinfo.service.CheckItemService;
import com.she.safety.model.CheckItem;
import com.she.utils.RequestMapper;

/**
 * 안전점검 항목
 *
 */
@RestController
public class CheckItemController {
    // TODO : queryString 변환을 위한 mapper 선언
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private CheckItemService checkItemService;

    /*
     * 안전 점검 항목 List 조회
     * 
     * @param parameter (점검종류 번호,점검 종류 명,사용여부)
     * 
     * @return 안전점검항목 List
     * 
     * @throws Exception
     */
    @GetMapping("/api/saf/baseinfo/getcheckitemlist")
    public ResponseEntity<List<CheckItem>> getCheckItemList(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> convertedParameter = requestMapper.convertAsParameter(parameter);
        // 사업장코드
        String plantCd = convertedParameter.containsKey("plantCd") ? convertedParameter.get("plantCd").toString() : "";
        String safCheckKindNoStr = convertedParameter.get("safCheckKindNo").toString();
        if ("".equals(safCheckKindNoStr)) {
            safCheckKindNoStr = "0";
        }
        // 점검종류 번호
        int safCheckKindNo = convertedParameter.containsKey("safCheckKindNo") ? Integer.parseInt(safCheckKindNoStr) : 0;
        // 점검 종류 명
        String safCheckTypeNm = convertedParameter.containsKey("safCheckTypeNm") ? convertedParameter.get("safCheckTypeNm").toString() : "";
        // 사용여부
        String useYn = convertedParameter.containsKey("useYn") ? convertedParameter.get("useYn").toString() : "";
        // 점검종류코드
        String chngKind = convertedParameter.containsKey("chngKind") ? convertedParameter.get("chngKind").toString() : "";

        return ResponseEntity.ok().body(checkItemService.getCheckItemList(plantCd, safCheckKindNo, safCheckTypeNm, useYn, chngKind, defaultParam));
    }

    /*
     * 안전 점검 항목 상세 조회
     * 
     * @param safcheckItemno (점검항목번호)
     * 
     * @return 안전점검항목
     * 
     * @throws Exception
     */
    @GetMapping("/api/saf/baseinfo/getcheckitem/{safcheckItemno}")
    public ResponseEntity<CheckItem> getCheckItem(@PathVariable("safcheckItemno") int safcheckItemno) throws Exception {
        CheckItem checkItem = checkItemService.getCheckItem(safcheckItemno);
        return ResponseEntity.ok().body(checkItem);
    }

    /*
     * 안전 점검 항목 신규
     * 
     * @param CheckItem 안전 점검 항목
     * 
     * @return 점검항목번호
     * 
     * @throws Exception
     */
    @PostMapping("/api/saf/baseinfo/createcheckitem")
    public ResponseEntity<Integer> createCheckItem(@RequestBody CheckItem checkItem) throws Exception {
        return ResponseEntity.ok().body(checkItemService.createCheckItem(checkItem));
    }

    /*
     * 안전 점검 항목 수정
     * 
     * @param CheckItem 안전 점검 항목
     * 
     * @return 점검항목번호
     * 
     * @throws Exception
     */
    @PutMapping("/api/saf/baseinfo/updatecheckitem")
    public ResponseEntity<Integer> updateCheckItem(@RequestBody CheckItem checkItem) throws Exception {
        return ResponseEntity.ok().body(checkItemService.updateCheckItem(checkItem));
    }

    /*
     * 안전 점검 항목 check
     * 
     * @param parameter (점검종류 번호,점검 종류 명,사용여부)
     * 
     * @return 안전점검항목 check
     * 
     * @throws Exception
     */
    @GetMapping("/api/saf/baseinfo/checkitem/check")
    public ResponseEntity<List<CheckItem>> getCheck(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = requestMapper.convertAsParameter(parameter);
        // 점검종류 번호
        int safCheckKindNo = map.containsKey("safCheckKindNo") ? Integer.parseInt("".equals(map.get("safCheckKindNo").toString()) ? "0" : map.get("safCheckKindNo").toString()) : 0;
        // 점검 종류 명
        String safCheckTypeNm = map.containsKey("safCheckTypeNm") ? map.get("safCheckTypeNm").toString() : "";
        // 점검종류 번호
        int safCheckItemNo = map.containsKey("safCheckItemNo") ? Integer.parseInt("".equals(map.get("safCheckItemNo").toString()) ? "0" : map.get("safCheckItemNo").toString()) : 0;

        CheckItem checkItem = new CheckItem();
        checkItem.setSafCheckKindNo(safCheckKindNo);
        checkItem.setSafCheckTypeNm(safCheckTypeNm);
        checkItem.setSafCheckItemNo(safCheckItemNo);

        return ResponseEntity.ok().body(checkItemService.checkCheckItem(checkItem));
    }

}
