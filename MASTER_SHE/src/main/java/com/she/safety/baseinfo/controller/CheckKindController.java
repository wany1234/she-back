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
import com.she.safety.baseinfo.service.CheckKindService;
import com.she.safety.model.CheckKind;
import com.she.utils.RequestMapper;

/**
 * 안전점검 종류
 *
 */
@RestController
public class CheckKindController {
    // TODO : queryString 변환을 위한 mapper 선언
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private CheckKindService checkKindService;

    /**
     * 안전 점검 종류 List 조회
     * 
     * @param parameter
     *            (점검종류명,설비점검해당여부, 사용여부)
     * @return 안전점검종류 List
     * @throws Exception
     */
    @GetMapping("/api/saf/baseinfo/getcheckkindlist")
    public ResponseEntity<List<CheckKind>> getCheckKindList(@RequestParam HashMap<String, Object> parameter,
            @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        // 사업장코드
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 안전점검종류명
        String safCheckKindNm = map.containsKey("safCheckKindNm") ? map.get("safCheckKindNm").toString() : "";
        // 점검계획사용여부
        String planUseYn = map.containsKey("planUseYn") ? map.get("planUseYn").toString() : "";
        // 설비점검해당여부
        String facilityUseYn = map.containsKey("facilityUseYn") ? map.get("facilityUseYn").toString() : "";
        // 사용여부
        String useYn = map.containsKey("useYn") ? map.get("useYn").toString() : "";
        // 순회여부
        String patrolYn = map.containsKey("patrolYn") ? map.get("patrolYn").toString() : "";
        // 합동여부
        String congYn = map.containsKey("congYn") ? map.get("congYn").toString() : "";
        // 순회여부
        String subconUseYn = map.containsKey("subconUseYn") ? map.get("subconUseYn").toString() : "";
        // 점검종류코드
        String chngKind = map.containsKey("chngKind") ? map.get("chngKind").toString() : "";

        return ResponseEntity.ok().body(checkKindService.getCheckKindList(plantCd, safCheckKindNm, planUseYn,
                facilityUseYn, useYn, patrolYn, subconUseYn, congYn, chngKind, defaultParam));
    }

    /**
     * 안전 점검 종류 상세 조회
     * 
     * @param parameter
     *            (점검종류번호)
     * @return 안전점검종류 상세
     * @throws Exception
     */
    @GetMapping("/api/saf/baseinfo/getcheckkinditem/{safCheckKindNo}")
    public ResponseEntity<CheckKind> getCheckKindItem(@PathVariable("safCheckKindNo") String safCheckKindNo)
            throws Exception {
        CheckKind checkKind = checkKindService.getCheckKindItem(safCheckKindNo);
        return ResponseEntity.ok().body(checkKind);
    }

    /*
     * 안전 점검 종류 생성
     * 
     * @param CheckKind 안전 점검 종류 항목
     * 
     * @return 안전 점검 종류 항목 Key값
     * 
     * @throws Exception
     */
    @PostMapping("/api/saf/baseinfo/createcheckkind")
    public ResponseEntity<Integer> createCheckKind(@RequestBody CheckKind checkKind) throws Exception {
        return ResponseEntity.ok().body(checkKindService.createCheckKind(checkKind));
    }

    /*
     * 안전 점검 종류 수정
     * 
     * @param CheckKind 안전 점검 종류 항목
     * 
     * @return 안전 점검 종류 항목 Key값
     * 
     * @throws Exception
     */
    @PutMapping("/api/saf/baseinfo/updatecheckkind")
    public ResponseEntity<Integer> updateCheckKind(@RequestBody CheckKind checkKind) throws Exception {
        return ResponseEntity.ok().body(checkKindService.updateCheckKind(checkKind));
    }

    /**
     * 안전 점검 종류 체크
     * 
     * @param parameter
     * @return 안전점검종류 체크
     * @throws Exception
     */
    @GetMapping("/api/saf/baseinfo/checkkind/check")
    public ResponseEntity<List<CheckKind>> getCheck(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        // 사업장코드
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 안전점검종류명
        String safCheckKindNm = map.containsKey("safCheckKindNm") ? map.get("safCheckKindNm").toString() : "";
        // 체크
        int safCheckKindNo = map.containsKey("safCheckKindNo")
                ? Integer.parseInt(
                        "".equals(map.get("safCheckKindNo").toString()) ? "0" : map.get("safCheckKindNo").toString())
                : 0;
        CheckKind checkKind = new CheckKind();
        checkKind.setPlantCd(plantCd);
        checkKind.setSafCheckKindNm(safCheckKindNm);
        checkKind.setSafCheckKindNo(safCheckKindNo);
        return ResponseEntity.ok().body(checkKindService.checkCheckKind(checkKind));
    }

}
