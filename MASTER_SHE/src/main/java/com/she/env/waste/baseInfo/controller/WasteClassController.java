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
package com.she.env.waste.baseInfo.controller;

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

import com.she.env.waste.baseInfo.service.WasteClassService;
import com.she.env.waste.model.WasteClass;
import com.she.utils.RequestMapper;

/**
 * 폐기물 분류
 *
 */
@RestController("EwstWasteClassController")
@RequestMapping("api/env/waste/baseinfo")
public class WasteClassController {
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private WasteClassService wasteClassService;

    /**
     * 폐기물 분류 전체 조회
     *
     * @param parameter
     *            검색조건
     * @return 폐기물 분류 목록
     * @throws Exception
     */
    @GetMapping("/wasteclasses")
    public ResponseEntity<List<WasteClass>> getWasteClasses(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = requestMapper.convertAsParameter(parameter);

        String ewstClassNm = map.containsKey("ewstClassNm") ? map.get("ewstClassNm").toString() : "";
        String ewstDivCd = map.containsKey("ewstDivCd") ? map.get("ewstDivCd").toString() : "";
        String useYn = map.containsKey("useYn") ? map.get("useYn").toString() : "";

        List<WasteClass> wasteClasses = this.wasteClassService.getWasteClasses(ewstClassNm, ewstDivCd, useYn, defaultParam);

        return ResponseEntity.ok().body(wasteClasses);
    }

    /**
     * 선택된 폐기물 분류 상세 조회
     *
     * @param ewstClassCd
     *            폐기물 분류코드
     * @return 폐기물 분류
     * @throws Exception
     */
    @GetMapping("/wasteclass/{ewstClassCd}")
    public ResponseEntity<WasteClass> getWasteClass(@PathVariable(name = "ewstClassCd") String ewstClassCd) throws Exception {
        WasteClass wasteClass = this.wasteClassService.getWasteClass(ewstClassCd);
        return ResponseEntity.ok().body(wasteClass);
    }

    /**
     * 폐기물 분류 신규등록
     *
     * @param wasteClass
     *            폐기물 분류
     * @return 등록행수
     * @throws Exception
     */
    @PostMapping("/wasteclass")
    public ResponseEntity<Integer> createWasteClass(@RequestBody WasteClass wasteClass) throws Exception {
        return ResponseEntity.ok().body(this.wasteClassService.createWasteClass(wasteClass));
    }

    /**
     * 선택된 폐기물 분류 수정
     *
     * @param wasteClass
     *            폐기물 분류
     * @return 수정행수
     * @throws Exception
     */
    @PutMapping("/wasteclass")
    public ResponseEntity<Integer> updateWasteClass(@RequestBody WasteClass wasteClass) throws Exception {
        Integer count = this.wasteClassService.updateWasteClass(wasteClass);

        return ResponseEntity.ok().body(count);
    }

    /**
     * 폐기물 분류 중복 체크
     *
     * @param ewstClassCd
     * @return
     * @throws Exception
     */
    @GetMapping("/wasteclass/duplecheck/{ewstClassCd}")
    public ResponseEntity<Integer> dupleCheck(@PathVariable String ewstClassCd) throws Exception {
        return ResponseEntity.ok().body(this.wasteClassService.dupleCheck(ewstClassCd));
    }
}
