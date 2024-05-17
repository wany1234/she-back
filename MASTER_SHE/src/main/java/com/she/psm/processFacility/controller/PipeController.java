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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.she.psm.processFacility.service.PipeService;
import com.she.psm.model.Pipe;
import com.she.utils.RequestMapper;

/**
 * 공정시설정보 - 배관 및 개스킷
 */
@RestController
@RequestMapping("api/psm/processFacility")
public class PipeController {

    @Autowired
    private PipeService pipeService;

    // queryString 변환을 위한 mapper 선언
    @Autowired
    private RequestMapper requestMapper;

    /**
     * 배관 및 개스킷 조회
     * 
     * @param plantCd
     *            사업장
     * @param processNo
     *            공정
     * @param pipeCd
     *            분류코드
     * @return 배관 및 개스킷 목록
     * @throws Exception
     */
    @GetMapping("/pipes")
    public ResponseEntity<List<Pipe>> getPipes(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 사업장
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 공정
        // int processNo = map.containsKey("processNo")
        // ? Integer.parseInt("".equals(map.get("processNo").toString()) ? "0" :
        // map.get("processNo").toString())
        // : 0;
        // 공정
        String processCd = map.containsKey("processCd") ? map.get("processCd").toString() : "";
        // 동력기계번호
        String pipeCd = map.containsKey("pipeCd") ? map.get("pipeCd").toString() : "";

        List<Pipe> pipes = pipeService.getPipes(plantCd, processCd, pipeCd);
        return ResponseEntity.ok().body(pipes);
    }

    /**
     * 배관 및 개스킷 상세 조회
     * 
     * @param pipeCd
     *            분류코드
     * @return 배관 및 개스킷
     * @throws Exception
     */
    @GetMapping("/pipe/{pipeNo}/{pipeCd}")
    public ResponseEntity<Pipe> getPipe(@PathVariable(name = "pipeNo") int pipeNo,
            @PathVariable(name = "pipeCd") String pipeCd) throws Exception {
        return ResponseEntity.ok().body(pipeService.getPipe(pipeNo, pipeCd));
    }

    /**
     * 배관 및 개스킷 등록/수정
     * 
     * @param Pipe
     *            배관 및 개스킷
     * @return 배관번호
     * @throws Exception
     */
    @PostMapping("/pipe")
    public ResponseEntity<Integer> savePipe(@RequestBody Pipe pipe) throws Exception {
        return ResponseEntity.ok().body(pipeService.savePipe(pipe) > 0 ? pipe.getPipeNo() : 0);
    }

    /**
     * 배관 및 개스킷 삭제
     * 
     * @param Pipe
     *            배관 및 개스킷
     * @return 변경 행 수
     * @throws Exception
     */
    @DeleteMapping("/pipe")
    public ResponseEntity<Integer> savePipe(@RequestBody List<Pipe> pipes) throws Exception {
        return ResponseEntity.ok().body(pipeService.deletePipe(pipes));
    }

}
