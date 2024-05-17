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

import com.she.chm.chem.service.ChemprodService;
import com.she.chm.model.ChemicalRegulItem;
import com.she.chm.model.ChemicalRegulItmChemprodVal;
import com.she.chm.model.Chemprod;
import com.she.chm.model.ChemprodChem;
import com.she.chm.model.ChemprodChemRegul;
import com.she.common.model.DefaultParam;
import com.she.utils.RequestMapper;

@RestController
@RequestMapping("api/chm/chem")
public class ChemprodController {
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private ChemprodService chemprodService;

    /**
     * 취급물질 조회
     *
     * @param parameter
     *            검색조건
     * @return 취급물질 목록
     * @throws Exception
     */
    @GetMapping("/chemprods")
    public ResponseEntity<List<HashMap<String, Object>>> getChemprods(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 사용여부
        String useYn = map.containsKey("useYn") ? map.get("useYn").toString() : "";
        // 검색어 (취급물질명 및 CAS NO.)
        String search = map.containsKey("search") ? map.get("search").toString() : "";
        // 사업장
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 대기오염물질여부
        String airPolYn = map.containsKey("airPolYn") ? map.get("airPolYn").toString() : "";
        // 수질오염물질여부
        String wtrPolYn = map.containsKey("wtrPolYn") ? map.get("wtrPolYn").toString() : "";
        // 인허가대상물질여부
        String licensingYn = map.containsKey("licensingYn") ? map.get("licensingYn").toString() : "";
        // 위험물질
        String dgrYn = map.containsKey("dgrYn") ? map.get("dgrYn").toString() : "";
        // 위험물질 항목
        int chemprodRegulItmNo = map.containsKey("chemprodRegulItmNo") ? Integer.parseInt("".equals(map.get("chemprodRegulItmNo").toString()) ? "0" : map.get("chemprodRegulItmNo").toString()) : 0;
        // 규제항목
        int[] regulItmNos = this.requestMapper.convertObjectListAsIntArray(map.get("regulItmNos"));

        return ResponseEntity.ok().body(chemprodService.getChemprods(search, regulItmNos, useYn, plantCd, airPolYn, wtrPolYn, licensingYn, dgrYn, chemprodRegulItmNo, defaultParam));
    }

    /**
     * 취급자재 조회 페이징 처리
     *
     * @param parameter
     * @return
     * @throws Exception
     */
    @GetMapping("/chemprodspage")
    public ResponseEntity<Map<String, Object>> getChemprodsPage(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 사용여부
        String useYn = map.containsKey("useYn") ? map.get("useYn").toString() : "";
        // 검색어 (취급물질명 및 CAS NO.)
        String search = map.containsKey("search") ? map.get("search").toString() : "";
        // 사업장
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 사업장
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";
        // 사업장
        String safFacilityCd = map.containsKey("safFacilityCd") ? map.get("safFacilityCd").toString() : "";
        // 대기오염물질여부
        String airPolYn = map.containsKey("airPolYn") ? map.get("airPolYn").toString() : "";
        // 수질오염물질여부
        String wtrPolYn = map.containsKey("wtrPolYn") ? map.get("wtrPolYn").toString() : "";
        // 인허가대상물질여부
        String licensingYn = map.containsKey("licensingYn") ? map.get("licensingYn").toString() : "";
        // 위험물질
        String dgrYn = map.containsKey("dgrYn") ? map.get("dgrYn").toString() : "";
        // 위험물질 항목
        int chemprodRegulItmNo = map.containsKey("chemprodRegulItmNo") ? Integer.parseInt("".equals(map.get("chemprodRegulItmNo").toString()) ? "0" : map.get("chemprodRegulItmNo").toString()) : 0;
        // 규제항목
        int[] regulItmNos = this.requestMapper.convertObjectListAsIntArray(map.get("regulItmNos"));

        Integer pageNumber = map.containsKey("pageNumber") ? Integer.parseInt(map.get("pageNumber").toString()) : 1;
        Integer pageSize = map.containsKey("pageSize") ? Integer.parseInt(map.get("pageSize").toString()) : 10000;

        String orderByExpression = map.containsKey("orderByExpression") ? map.get("orderByExpression").toString() : "";

        Map<String, Object> returnMap = new HashMap<String, Object>();
        List<HashMap<String, Object>> body = chemprodService.getChemprodsPage(search, regulItmNos, useYn, plantCd, deptCd, safFacilityCd, airPolYn, wtrPolYn, licensingYn, dgrYn, chemprodRegulItmNo, pageNumber, pageSize, orderByExpression, defaultParam);
        Integer totalCount = CollectionUtils.isNotEmpty(body) ? Integer.parseInt(String.valueOf(body.get(0).get("total_cnt"))) : 0;
        returnMap.put("items", body);
        returnMap.put("count", totalCount);

        return ResponseEntity.ok().body(returnMap);
    }

    /**
     * 취급물질 조회 (법규 제외)
     *
     * @param parameter
     *            검색조건
     * @return 취급물질 목록 (법규 제외)
     * @throws Exception
     */
    @GetMapping("/chemprodnoreguls")
    public ResponseEntity<List<Chemprod>> getChemprodNoReguls(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 사용여부
        String useYn = map.containsKey("useYn") ? map.get("useYn").toString() : "";
        // 취급자재명 => SEARCH로 사용함.
        String search = map.containsKey("chemprodNm") ? map.get("chemprodNm").toString() : "";
        // 사업장
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 대기오염물질여부
        String airPolYn = map.containsKey("airPolYn") ? map.get("airPolYn").toString() : "";
        // 수질오염물질여부
        String wtrPolYn = map.containsKey("wtrPolYn") ? map.get("wtrPolYn").toString() : "";
        // 인허가대상물질여부
        String licensingYn = map.containsKey("licensingYn") ? map.get("licensingYn").toString() : "";
        // 위험물질
        String dgrYn = map.containsKey("dgrYn") ? map.get("dgrYn").toString() : "";
        // 위험물질 항목
        int chemprodRegulItmNo = map.containsKey("chemprodRegulItmNo") ? Integer.parseInt("".equals(map.get("chemprodRegulItmNo").toString()) ? "0" : map.get("chemprodRegulItmNo").toString()) : 0;

        return ResponseEntity.ok().body(chemprodService.getChemprodNoReguls(search, useYn, plantCd, airPolYn, wtrPolYn, licensingYn, dgrYn, chemprodRegulItmNo, defaultParam));
    }

    /**
     * 취급물질 조회 (법규 제외) 페이징
     *
     * @param parameter
     * @return
     * @throws Exception
     */
    @GetMapping("/chemprodnoregulspage")
    public ResponseEntity<Map<String, Object>> getChemprodNoRegulsPage(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 사용여부
        String useYn = map.containsKey("useYn") ? map.get("useYn").toString() : "";
        // 취급자재명 => SEARCH로 사용함.
        String search = map.containsKey("chemprodNm") ? map.get("chemprodNm").toString() : "";
        // 사업장
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 대기오염물질여부
        String airPolYn = map.containsKey("airPolYn") ? map.get("airPolYn").toString() : "";
        // 수질오염물질여부
        String wtrPolYn = map.containsKey("wtrPolYn") ? map.get("wtrPolYn").toString() : "";
        // 인허가대상물질여부
        String licensingYn = map.containsKey("licensingYn") ? map.get("licensingYn").toString() : "";
        // 위험물질
        String dgrYn = map.containsKey("dgrYn") ? map.get("dgrYn").toString() : "";
        // 위험물질 항목
        int chemprodRegulItmNo = map.containsKey("chemprodRegulItmNo") ? Integer.parseInt("".equals(map.get("chemprodRegulItmNo").toString()) ? "0" : map.get("chemprodRegulItmNo").toString()) : 0;

        Integer pageNumber = map.containsKey("pageNumber") ? Integer.parseInt(map.get("pageNumber").toString()) : 1;
        Integer pageSize = map.containsKey("pageSize") ? Integer.parseInt(map.get("pageSize").toString()) : 10000;

        String orderByExpression = map.containsKey("orderByExpression") ? map.get("orderByExpression").toString() : "";

        Map<String, Object> returnMap = new HashMap<String, Object>();
        List<Chemprod> body = chemprodService.getChemprodNoRegulsPage(search, useYn, plantCd, airPolYn, wtrPolYn, licensingYn, dgrYn, chemprodRegulItmNo, pageNumber, pageSize, orderByExpression, defaultParam);
        returnMap.put("items", body);
        returnMap.put("count", body != null && body.size() > 0 ? body.get(0).getTotalCnt() : 0);

        return ResponseEntity.ok().body(returnMap);
    }

    /**
     * 취급물질 상세 조회
     *
     * @param chemprodNo
     *            취급물질 번호
     * @return 취급물질
     * @throws Exception
     */
    @GetMapping("/chemprod/{chemprodNo}")
    public ResponseEntity<Chemprod> getChemprod(@PathVariable(name = "chemprodNo") int chemprodNo, @ModelAttribute DefaultParam defaultParam) throws Exception {
        return ResponseEntity.ok().body(this.chemprodService.getChemprod(chemprodNo, defaultParam));
    }

    /**
     * 취급자재중복검사 사업장 + 자재코드 + 납품업체 + 제조업체
     *
     * @param parameter
     * @return
     * @throws Exception
     */
    @GetMapping("/checkchemprod")
    public ResponseEntity<List<HashMap<String, Object>>> checkChemprod(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        // 사업장코드
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // SAP 자재코드
        String sapMatCd = map.containsKey("sapMatCd") ? map.get("sapMatCd").toString() : "";
        // 납품업체코드
        String vendorCd = map.containsKey("vendorCd") ? map.get("vendorCd").toString() : "";
        // 제조업체코드
        String makerCd = map.containsKey("makerCd") ? map.get("makerCd").toString() : "";

        // 취급자재번호
        int chemProdNo = map.containsKey("chemProdNo") ? Integer.parseInt("".equals(map.get("chemProdNo").toString()) ? "0" : map.get("chemProdNo").toString()) : 0;

        return ResponseEntity.ok().body(this.chemprodService.checkChemprod(sapMatCd, vendorCd, makerCd, plantCd, chemProdNo));
    }

    /**
     * 취급물질 신규등록
     *
     * @param chemprod
     *            취급물질
     * @return 취급물질 번호
     * @throws Exception
     */
    @PostMapping("/chemprod")
    public ResponseEntity<Integer> createChemprod(@RequestBody Chemprod chemprod) throws Exception {
        return ResponseEntity.ok().body(this.chemprodService.createChemprod(chemprod));
    }

    /**
     * 취급물질 수정
     *
     * @param chemprod
     *            취급물질
     * @return 수정 행 수
     * @throws Exception
     */
    @PutMapping("/chemprod")
    public ResponseEntity<Integer> updateChemprod(@RequestBody Chemprod chemprod) throws Exception {
        return ResponseEntity.ok().body(this.chemprodService.updateChemprod(chemprod));
    }

    /**
     * 취급물질 규제법규별 항목값 상세 조회
     *
     * @param regulItmChemprodValNo
     *            항목값 번호
     * @return 취급물질 규제법규별 항목값
     * @throws Exception
     */
    @GetMapping("/chemicalregulitmchemprodval/{regulItmChemprodValNo}")
    public ResponseEntity<ChemicalRegulItmChemprodVal> getChemicalRegulItmChemVal(@PathVariable(name = "regulItmChemprodValNo") int regulItmChemprodValNo, @ModelAttribute DefaultParam defaultParam) throws Exception {
        return ResponseEntity.ok().body(this.chemprodService.getChemicalRegulItmChemprodVal(regulItmChemprodValNo, defaultParam));
    }

    /**
     * 취급물질 구성성분 조회
     *
     * @param casNo
     * @return 취급물질 구성성분 목록
     * @throws Exception
     */
    @GetMapping("/chemprodchems")
    public ResponseEntity<List<ChemprodChem>> getChemprodChems(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 취급물질 번호
        int chemProdNo = map.containsKey("chemProdNo") ? Integer.parseInt("".equals(map.get("chemProdNo").toString()) ? "0" : map.get("chemProdNo").toString()) : 0;
        String searchFlag = map.containsKey("searchFlag") ? map.get("searchFlag").toString() : "false";

        return ResponseEntity.ok().body(this.chemprodService.getChemprodChems(chemProdNo, searchFlag));
    }

    /**
     * 취급물질 구성성분 신규등록
     *
     * @param chemprod
     *            취급물질
     * @return 변경 행 수
     * @throws Exception
     */
    @PostMapping("/chemprodchem")
    public ResponseEntity<Integer> createChemprodChem(@RequestBody ChemprodChemRegul chemprodChemRegul) throws Exception {
        return ResponseEntity.ok().body(this.chemprodService.createChemprodChem(chemprodChemRegul));
    }

    /**
     * 취급물질 규제항목 조회
     *
     * @param chemprodNo
     * @return 취급물질 규제항목 목록
     * @throws Exception
     */
    @GetMapping("/chemprodregul")
    public ResponseEntity<List<ChemicalRegulItem>> getChemprodReguls(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 취급물질 번호
        int chemprodNo = map.containsKey("chemprodNo") ? Integer.parseInt("".equals(map.get("chemprodNo").toString()) ? "0" : map.get("chemprodNo").toString()) : 0;

        return ResponseEntity.ok().body(this.chemprodService.getChemprodReguls(chemprodNo, defaultParam));
    }

}
