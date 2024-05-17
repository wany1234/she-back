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
package com.she.chm.baseInfo.controller;

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

import com.she.chm.baseInfo.service.ChemicalUnitService;
import com.she.chm.model.ChemicalUnit;
import com.she.utils.RequestMapper;

@RestController
@RequestMapping("api/chm/baseinfo")
public class ChemicalUnitController {

    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private ChemicalUnitService chemicalUnitService;

    /**
     * 물질단위 조회
     * 
     * @param parameter
     *            검색조건
     * @return 물질단위 목록
     * @throws Exception
     */
    @GetMapping("/chemicalunits")
    public ResponseEntity<List<ChemicalUnit>> getChemicalUnits(@RequestParam HashMap<String, Object> parameter)
            throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 사용여부
        String useYn = map.containsKey("useYn") ? map.get("useYn").toString() : "";
        // 물질단위명
        String unitNm = map.containsKey("unitNm") ? map.get("unitNm").toString() : "";

        return ResponseEntity.ok().body(chemicalUnitService.getChemicalUnits(unitNm, useYn));
    }

    /**
     * 물질단위 상세 조회
     * 
     * @param branchCd
     *            물질단위코드
     * @return 물질단위
     * @throws Exception
     */
    @GetMapping("/chemicalunit/{unitNo}")
    public ResponseEntity<ChemicalUnit> getChemicalUnit(@PathVariable(name = "unitNo") int unitNo) throws Exception {
        return ResponseEntity.ok().body(this.chemicalUnitService.getChemicalUnit(unitNo));
    }

    /**
     * 물질단위 신규등록
     * 
     * @param chemicalUnit
     *            물질단위
     * @return 물질단위 코드
     * @throws Exception
     */
    @PostMapping("/chemicalunit")
    public ResponseEntity<Integer> createChemicalUnit(@RequestBody ChemicalUnit chemicalUnit) throws Exception {
        return ResponseEntity.ok().body(this.chemicalUnitService.createChemicalUnit(chemicalUnit));
    }

    /**
     * 물질단위 수정
     * 
     * @param chemicalUnit
     *            물질단위
     * @return 수정 행 수
     * @throws Exception
     */
    @PutMapping("/chemicalunit")
    public ResponseEntity<Integer> updateChemicalUnit(@RequestBody ChemicalUnit chemicalUnit) throws Exception {
        return ResponseEntity.ok().body(this.chemicalUnitService.updateChemicalUnit(chemicalUnit));
    }

    /**
     * 물질단위명 체크
     * 
     * @param parameter
     *            검색조건
     * @return 체크값
     * @throws Exception
     */
    @GetMapping("/checkchemicalunit")
    public ResponseEntity<List<HashMap<String, Object>>> getCheckChemicalUnit(
            @RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 물질단위명
        String unitNm = map.containsKey("unitNm") ? map.get("unitNm").toString() : "";
        // 물질단위번호
        int unitNo = map.containsKey("unitNo")
                ? Integer.parseInt("".equals(map.get("unitNo").toString()) ? "0" : map.get("unitNo").toString()) : 0;

        return ResponseEntity.ok().body(chemicalUnitService.getCheckChemicalUnit(unitNm, unitNo));
    }

}
