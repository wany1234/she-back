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

import com.she.env.waste.baseInfo.service.WasteDispoMtdService;
import com.she.env.waste.model.WasteDispoMtd;
import com.she.utils.RequestMapper;

/**
 * 폐기물 처리방법
 *
 */
@RestController("EwstWasteDispoMtdController")
@RequestMapping("api/env/waste/baseinfo")
public class WasteDispoMtdController {
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private WasteDispoMtdService wasteDispoMtdService;

    /**
     * 폐기물 처리방법 전체 조회
     *
     * @param parameter
     *            검색조건
     * @return 폐기물 처리방법 목록
     * @throws Exception
     */
    @GetMapping("/wastedisposalmethods")
    public ResponseEntity<List<WasteDispoMtd>> getWasteDisposalMethods(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = requestMapper.convertAsParameter(parameter);
        String ewstDispoMtdNm = map.containsKey("ewstDispoMtdNm") ? map.get("ewstDispoMtdNm").toString() : "";
        String ewstDispoClassCd = map.containsKey("ewstDispoClassCd") ? map.get("ewstDispoClassCd").toString() : "";
        String useYn = map.containsKey("useYn") ? map.get("useYn").toString() : "";

        List<WasteDispoMtd> wasteDispoMtds = this.wasteDispoMtdService.getWasteDisposalMethods(ewstDispoMtdNm, ewstDispoClassCd, useYn, defaultParam);

        return ResponseEntity.ok().body(wasteDispoMtds);
    }

    /**
     * 선택된 폐기물 처리방법 상세 조회
     *
     * @param ewstDispoMtdCd
     *            폐기물 처리방법 코드
     * @return 폐기물 처리방법
     * @throws Exception
     */
    @GetMapping("/wastedisposalmethod/{ewstDispoMtdCd}")
    public ResponseEntity<WasteDispoMtd> getWasteDisposalMethod(@PathVariable(name = "ewstDispoMtdCd") String ewstDispoMtdCd) throws Exception {
        WasteDispoMtd wasteDispoMtd = this.wasteDispoMtdService.getWasteDisposalMethod(ewstDispoMtdCd);
        return ResponseEntity.ok().body(wasteDispoMtd);
    }

    /**
     * 폐기물 처리방법 신규등록
     *
     * @param wasteDispoMtd
     *            폐기물 처리방법
     * @return 등록행수
     * @throws Exception
     */
    @PostMapping("/wastedisposalmethod")
    public ResponseEntity<Integer> createWasteDisposalMethod(@RequestBody WasteDispoMtd wasteDispoMtd) throws Exception {
        return ResponseEntity.ok().body(this.wasteDispoMtdService.createWasteDisposalMethod(wasteDispoMtd));
    }

    /**
     * 선택된 폐기물 처리방법 수정
     *
     * @param wasteDispoMtd
     *            폐기물 처리방법
     * @return 수정행수
     * @throws Exception
     */
    @PutMapping("/wastedisposalmethod")
    public ResponseEntity<Integer> updateWasteDisposalMethod(@RequestBody WasteDispoMtd wasteDispoMtd) throws Exception {
        Integer count = this.wasteDispoMtdService.updateWasteDisposalMethod(wasteDispoMtd);

        return ResponseEntity.ok().body(count);
    }

    /**
     * 폐기물 처리방법 중복체크
     *
     * @param ewstDispoMtdCd
     * @return
     * @throws Exception
     */
    @GetMapping("/duplecheck/{ewstDispoMtdCd}")
    public ResponseEntity<Integer> dupleCheck(@PathVariable String ewstDispoMtdCd) throws Exception {
        return ResponseEntity.ok().body(this.wasteDispoMtdService.dupleCheck(ewstDispoMtdCd));
    }
}
