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
package com.she.chm.chem.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.she.chm.chem.service.ChemService;
import com.she.chm.model.Chem;
import com.she.chm.model.ChemicalRegulItmChemVal;
import com.she.common.model.DefaultParam;
import com.she.utils.RequestMapper;

@RestController
@RequestMapping("api/chm/chem")
public class ChemController {
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private ChemService chemService;

    /**
     * 화학물질 조회
     *
     * @param parameter
     *            검색조건
     * @return 화학물질 목록
     * @throws Exception
     */
    @GetMapping("/chems")
    public ResponseEntity<List<HashMap<String, Object>>> getChems(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 사용여부
        String useYn = map.containsKey("useYn") ? map.get("useYn").toString() : "";
        // 검색어 (화학물질명 및 CAS NO.)
        String search = map.containsKey("search") ? map.get("search").toString() : "";
        // 규제항목
        int[] regulItmNos = this.requestMapper.convertObjectListAsIntArray(map.get("regulItmNos"));
        // 취급물질no
        int chemprodNo = map.containsKey("chemprodNo") ? Integer.parseInt("".equals(map.get("chemprodNo").toString()) ? "0" : map.get("chemprodNo").toString()) : 0;

        return ResponseEntity.ok().body(chemService.getChems(search, regulItmNos, useYn, chemprodNo));
    }

    /**
     * 화학물질 조회 페이징 포함
     *
     * @param parameter
     *            검색조건
     * @return 화학물질 목록
     * @throws Exception
     */
    @GetMapping("/chemspage")
    public ResponseEntity<Map<String, Object>> getChemsPage(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 사용여부
        String useYn = map.containsKey("useYn") ? map.get("useYn").toString() : "";
        // 검색어 (화학물질명 및 CAS NO.)
        String search = map.containsKey("search") ? map.get("search").toString() : "";
        // 규제항목
        int[] regulItmNos = this.requestMapper.convertObjectListAsIntArray(map.get("regulItmNos"));
        // 취급물질no
        int chemprodNo = map.containsKey("chemprodNo") ? Integer.parseInt("".equals(map.get("chemprodNo").toString()) ? "0" : map.get("chemprodNo").toString()) : 0;

        Integer pageNumber = map.containsKey("pageNumber") ? Integer.parseInt(map.get("pageNumber").toString()) : 1;
        Integer pageSize = map.containsKey("pageSize") ? Integer.parseInt(map.get("pageSize").toString()) : 10000;

        String orderByExpression = map.containsKey("orderByExpression") ? map.get("orderByExpression").toString() : "";

        Map<String, Object> returnMap = new HashMap<String, Object>();
        List<HashMap<String, Object>> body = chemService.getChemsPage(search, regulItmNos, useYn, chemprodNo, pageNumber, pageSize, orderByExpression);
        Integer totalCount = CollectionUtils.isNotEmpty(body) ? Integer.parseInt(String.valueOf(body.get(0).get("total_cnt"))) : 0;
        returnMap.put("items", body);
        returnMap.put("count", totalCount);

        return ResponseEntity.ok().body(returnMap);
    }

    /**
     * 화학물질 조회 (법규 제외)
     *
     * @param parameter
     *            검색조건
     * @return 화학물질 목록 (법규 제외)
     * @throws Exception
     */
    @GetMapping("/chemnoreguls")
    public ResponseEntity<List<Chem>> getChemNoReguls(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // CAS NO.
        String casNo = map.containsKey("casNo") ? map.get("casNo").toString() : "";
        // 화학물질명
        String chemNmKr = map.containsKey("chemNmKr") ? map.get("chemNmKr").toString() : "";
        // 화학물질명
        String chemNmEn = map.containsKey("chemNmEn") ? map.get("chemNmEn").toString() : "";

        return ResponseEntity.ok().body(chemService.getChemNoReguls(casNo, chemNmKr, chemNmEn));
    }

    /**
     * 화학물질 조회 (법규 제외) 페이징처리
     *
     * @param parameter
     *            검색조건
     * @return 화학물질 목록 (법규 제외)
     * @throws Exception
     */
    @GetMapping("/chemnoregulspage")
    public ResponseEntity<Map<String, Object>> getChemNoRegulsPage(@RequestParam HashMap<String, Object> parameter) throws Exception {

        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // CAS NO.
        String casNo = map.containsKey("casNo") ? map.get("casNo").toString() : "";
        // 화학물질명
        String chemNmKr = map.containsKey("chemNmKr") ? map.get("chemNmKr").toString() : "";
        // 화학물질명
        String chemNmEn = map.containsKey("chemNmEn") ? map.get("chemNmEn").toString() : "";
        // 페이징정보

        Integer pageNumber = map.containsKey("pageNumber") ? Integer.parseInt(map.get("pageNumber").toString()) : 1;
        Integer pageSize = map.containsKey("pageSize") ? Integer.parseInt(map.get("pageSize").toString()) : 10000;

        String orderByExpression = map.containsKey("orderByExpression") ? map.get("orderByExpression").toString() : "";

        Map<String, Object> returnMap = new HashMap<String, Object>();
        List<Chem> body = chemService.getChemNoRegulsPage(casNo, chemNmKr, chemNmEn, pageNumber, pageSize, orderByExpression);
        Integer totalCount = chemService.getChemNoRegulsTotalSize(casNo, chemNmKr, chemNmEn);
        returnMap.put("items", body);
        returnMap.put("count", totalCount);

        return ResponseEntity.ok().body(returnMap);
    }

    /**
     * 화학물질 상세 조회
     *
     * @param chemNo
     *            화학물질 번호
     * @return 화학물질
     * @throws Exception
     */
    @GetMapping("/chem/{chemNo}")
    public ResponseEntity<Chem> getChem(@PathVariable(name = "chemNo") int chemNo, @ModelAttribute DefaultParam defaultParam) throws Exception {
        return ResponseEntity.ok().body(this.chemService.getChem(chemNo, defaultParam));
    }

    /**
     * 화학물질 상세 조회 모든 규제정보 함
     *
     * @param chemNo
     *            화학물질 번호
     * @return 화학물질
     * @throws Exception
     */
    @GetMapping("/chemunion/{chemNo}")
    public ResponseEntity<Chem> getChemUnion(@PathVariable(name = "chemNo") int chemNo, @ModelAttribute DefaultParam defaultParam) throws Exception {
        return ResponseEntity.ok().body(this.chemService.getChemUnion(chemNo, defaultParam));
    }

    /**
     * casNo 중복검사
     *
     * @param casNo
     * @return 중복 행 수
     * @throws Exception
     */
    @GetMapping("/checkchem")
    public ResponseEntity<List<HashMap<String, Object>>> checkChem(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // CAS No
        String casNo = map.containsKey("casNo") ? map.get("casNo").toString() : "";
        // 화학물질명(KOR)
        String chemNmKr = map.containsKey("chemNmKr") ? map.get("chemNmKr").toString() : "";
        // 화학물질명(ENG)
        String chemNmEn = map.containsKey("chemNmEn") ? map.get("chemNmEn").toString() : "";
        // 화학물질 번호
        int chemNo = map.containsKey("chemNo") ? Integer.parseInt("".equals(map.get("chemNo").toString()) ? "0" : map.get("chemNo").toString()) : 0;

        return ResponseEntity.ok().body(this.chemService.checkChem(casNo, chemNmKr, chemNmEn, chemNo));
    }

    /**
     * 화학물질 신규등록
     *
     * @param chem
     *            화학물질
     * @return 화학물질 번호
     * @throws Exception
     */
    @PostMapping("/chem")
    public ResponseEntity<Integer> createChem(@RequestBody Chem chem) throws Exception {
        return ResponseEntity.ok().body(this.chemService.createChem(chem));
    }

    /**
     * 화학물질 수정
     *
     * @param chem
     *            화학물질
     * @return 수정 행 수
     * @throws Exception
     */
    @PutMapping("/chem")
    public ResponseEntity<Integer> updateChem(@RequestBody Chem chem) throws Exception {
        return ResponseEntity.ok().body(this.chemService.updateChem(chem));
    }

    /**
     * 화학물질 규제법규별 항목값 상세 조회
     *
     * @param regulItmChemValNo
     *            항목값 번호
     * @return 화학물질 규제법규별 항목값
     * @throws Exception
     */
    @GetMapping("/chemicalregulitmchemval/{regulItmChemValNo}")
    public ResponseEntity<ChemicalRegulItmChemVal> getChemicalRegulItmChemVal(@PathVariable(name = "regulItmChemValNo") int regulItmChemValNo, @ModelAttribute DefaultParam defaultParam) throws Exception {
        return ResponseEntity.ok().body(this.chemService.getChemicalRegulItmChemVal(regulItmChemValNo, defaultParam));
    }

}
