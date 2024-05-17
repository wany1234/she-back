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

import com.she.env.waste.baseInfo.service.WasteService;
import com.she.env.waste.model.Waste;
import com.she.utils.RequestMapper;

/**
 * 폐기물
 *
 */
@RestController("EwstWasteController")
@RequestMapping("api/env/waste/baseinfo")
public class WasteController {
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private WasteService wasteService;

    /**
     * 폐기물 전체 조회
     *
     * @param parameter
     *            검색조건
     * @return 폐기물 목록
     * @throws Exception
     */
    @GetMapping("/wastes")
    public ResponseEntity<List<Waste>> getWasteClasses(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = requestMapper.convertAsParameter(parameter);
        String useYn = map.containsKey("useYn") ? map.get("useYn").toString() : "";
        String ewstClassCd = map.containsKey("ewstClassCd") ? map.get("ewstClassCd").toString() : "";
        String ewstDispoMtdCd = map.containsKey("ewstDispoMtdCd") ? map.get("ewstDispoMtdCd").toString() : "";
        String ewstWasteNm = map.containsKey("ewstWasteNm") ? map.get("ewstWasteNm").toString() : "";
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";

        List<Waste> wastes = this.wasteService.getWastes(ewstDispoMtdCd, ewstWasteNm, useYn, ewstClassCd, plantCd, defaultParam);

        return ResponseEntity.ok().body(wastes);
    }

    /**
     * 선택된 폐기물 상세 조회
     *
     * @param ewstWasteNo
     *            폐기물번호
     * @return 폐기물
     * @throws Exception
     */
    @GetMapping("/waste/{ewstWasteNo}")
    public ResponseEntity<Waste> getWasteClass(@PathVariable(name = "ewstWasteNo") int ewstWasteNo) throws Exception {
        Waste waste = this.wasteService.getWaste(ewstWasteNo);
        return ResponseEntity.ok().body(waste);
    }

    /**
     * 폐기물 신규등록
     *
     * @param waste
     *            폐기물
     * @return 폐기물번호
     * @throws Exception
     */
    @PostMapping("/waste")
    public ResponseEntity<Integer> createWasteClass(@RequestBody Waste waste) throws Exception {
        return ResponseEntity.ok().body(this.wasteService.createWaste(waste));
    }

    /**
     * 선택된 폐기물 수정
     *
     * @param waste
     *            폐기물
     * @return 수정행수
     * @throws Exception
     */
    @PutMapping("/waste")
    public ResponseEntity<Integer> updateWasteClass(@RequestBody Waste waste) throws Exception {
        Integer count = this.wasteService.updateWaste(waste);

        return ResponseEntity.ok().body(count);
    }

    /**
     * 중복체크
     *
     * @param ewstWasteNm
     * @param plantCd
     * @return
     * @throws Exception
     */
    @GetMapping("/waste/duplecheck/{ewstWasteNm}/{plantCd}")
    public ResponseEntity<Integer> dupleCheck(@PathVariable String ewstWasteNm, @PathVariable String plantCd) throws Exception {
        return ResponseEntity.ok().body(this.wasteService.dupleCheck(ewstWasteNm, plantCd));
    }
}
