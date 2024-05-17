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
package com.she.env.waste.disporate.controller;

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

import com.she.env.waste.disporate.service.DisporateService;
import com.she.env.waste.model.Disporate;
import com.she.utils.RequestMapper;

/**
 * 폐기물 관리대장
 *
 */
@RestController("DisporateController")
@RequestMapping("api/env/waste/disporate")
public class DisporateController {
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private DisporateService disporateService;

    /**
     * 분배율 목록
     *
     * @param fromDate,
     *            시작기간
     * @param toDate,
     *            마감기간
     * @param plantCd,
     *            사업장코드
     * @param ewstClassCd,
     *            폐기물 분류 코드
     * @param ewstWasteNo,
     *            폐기물 고유번호
     * @return 부서별 분배율 목록
     * @throws Exception
     */
    @GetMapping("/disporates")
    public ResponseEntity<List<Disporate>> getDisporates(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = requestMapper.convertAsParameter(parameter);

        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        String dispoDeptCd = map.containsKey("dispoDeptCd") ? map.get("dispoDeptCd").toString() : "";
        String ewstWasteNo = map.containsKey("ewstWasteNo") ? map.get("ewstWasteNo").toString() : "";
        String ewstClassCd = map.containsKey("ewstClassCd") ? map.get("ewstClassCd").toString() : "";
        String[] searchPeriod = this.requestMapper.convertObjectListAsStringArray(map.get("period"));
        String fromYear = "";
        String toYear = "";
        if (searchPeriod != null && searchPeriod.length == 2) {
            fromYear = searchPeriod[0];
            toYear = searchPeriod[1];
        }

        return ResponseEntity.ok().body(this.disporateService.getDisporates(dispoDeptCd, fromYear, toYear, plantCd, ewstClassCd, ewstWasteNo, defaultParam));
    }

    /**
     * 분배율 조회
     *
     * @param plantCd
     *            사업장
     * @param ewstWasteRateNo
     *            분배율 번호
     * @throws Exception
     */
    @GetMapping("/disporate/{plantCd}/{ewstWasteRateNo}")
    public ResponseEntity<Disporate> getDisporate(@PathVariable("plantCd") String plantCd, @PathVariable("ewstWasteRateNo") String ewstWasteRateNo) throws Exception {
        Disporate disporate = this.disporateService.getDisporate(plantCd, ewstWasteRateNo);
        return ResponseEntity.ok().body(disporate);
    }

    @PutMapping("/disporates")
    public ResponseEntity<Integer> updateDisporate(@RequestBody Disporate disporate) throws Exception {
        return ResponseEntity.ok().body(this.disporateService.updateDisporate(disporate));
    }

    /**
     * 분배율 생성
     *
     * @param Disporate
     *            분배율
     *
     * @throws Exception
     */
    @PostMapping("/disporates")
    public ResponseEntity<Integer> createDisporate(@RequestBody Disporate disporate) throws Exception {
        return ResponseEntity.ok().body(this.disporateService.createDisporate(disporate));
    }

    /**
     * 분배율 (사업장, 연도, 폐기물, 폐기물분류) 중복 체크
     *
     * @param ewstWasteRateNo
     *            분배율 번호
     * @param ewstWasteNo
     *            폐기물 번호
     * @param plantCd
     *            사업장
     * @param year
     *            연도
     * @throws Exception
     */
    @GetMapping("/disporate/check")
    public ResponseEntity<Integer> getDisporateCheck(@RequestParam HashMap<String, Object> parameter) throws Exception {

        HashMap<String, Object> map = requestMapper.convertAsParameter(parameter);

        String ewstWasteRateNo = map.containsKey("ewstWasteRateNo") ? map.get("ewstWasteRateNo").toString() : "";
        String ewstWasteNo = map.containsKey("ewstWasteNo") ? map.get("ewstWasteNo").toString() : "";
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        String year = map.containsKey("year") ? map.get("year").toString() : "";

        int checkCount = this.disporateService.getDisporateCheck(ewstWasteRateNo, ewstWasteNo, plantCd, year);
        return ResponseEntity.ok().body(checkCount);
    }

}
