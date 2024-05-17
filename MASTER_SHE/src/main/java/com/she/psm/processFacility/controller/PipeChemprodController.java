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
package com.she.psm.processFacility.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;

import com.she.chm.model.Chemprod;
import com.she.psm.model.Pipe;
import com.she.psm.processFacility.service.PipeChemprodService;
import com.she.utils.RequestMapper;

/**
 * 배관별 취급물질
 */
@RestController
@RequestMapping("api/psm/chemprodchem")
public class PipeChemprodController {
    @Autowired
    private PipeChemprodService pipeChemprodService;

    // queryString 변환을 위한 mapper 선언
    @Autowired
    private RequestMapper requestMapper;

    /**
     * 배관별 취급물질 조회
     *
     * @param parameter
     *            검색조건
     * @return 배관별 취급물질 목록
     * @throws Exception
     */
    @GetMapping("/pipechemprods")
    public ResponseEntity<List<Chemprod>> getPipeChemprods(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 사업장
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 취급물질명
        String chemProdNmKr = map.containsKey("chemProdNmKr") ? map.get("chemProdNmKr").toString() : "";
        // 배관번호
        int pipeNo = map.containsKey("pipeNo") ? Integer.parseInt("".equals(map.get("pipeNo").toString()) ? "0" : map.get("pipeNo").toString()) : 0;

        List<Chemprod> chemprods = pipeChemprodService.getPipeChemprods(plantCd, chemProdNmKr, pipeNo);
        return ResponseEntity.ok().body(chemprods);
    }

    /**
     * 배관별 취급물질 등록
     *
     * @param pipe
     *            안전설비마스터
     * @return 등록 행 수
     * @throws Exception
     */
    @PostMapping("/pipechemprod")
    public ResponseEntity<Integer> createPipeChemprods(@RequestBody Pipe pipe) throws Exception {
        return ResponseEntity.ok().body(pipeChemprodService.createPipeChemprods(pipe));
    }

    /**
     * 배관별 취급물질 삭제
     *
     * @param chemprods
     *            배관별 취급물질s
     * @return 변경 행 수
     * @throws Exception
     */
    @DeleteMapping("/pipechemprod")
    public ResponseEntity<Integer> deletePipeChemprods(@RequestBody List<Chemprod> chemprods) throws Exception {
        return ResponseEntity.ok().body(pipeChemprodService.deletePipeChemprods(chemprods));
    }

}
