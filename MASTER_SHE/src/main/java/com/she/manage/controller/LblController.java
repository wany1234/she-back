package com.she.manage.controller;

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

import com.she.manage.model.LblDtl;
import com.she.manage.model.LblMst;
import com.she.manage.service.LblService;
import com.she.utils.RequestMapper;

@RestController
@RequestMapping("/api/manage/lbl/")
public class LblController {
    // TODO : queryString 변환을 위한 mapper 선언
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private LblService lblService;

    /**
     * 메시지타입 중복 확인
     *
     * @param parameter
     *            메시지타입
     * @return 중복 행 수
     * @throws Exception
     */
    @GetMapping("check/lblMst")
    public int checkLblMst(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = requestMapper.convertAsParameter(parameter);

        // 메시지타입
        String mstCd = map.containsKey("mstCd") ? map.get("mstCd").toString() : "";

        return lblService.checkLblMst(mstCd);
    }

    /**
     * 메시지타입 등록
     *
     * @param lblMst
     *            메시지타입
     * @return 등록 행 수
     * @throws Exception
     */
    @PostMapping("lblMst")
    public ResponseEntity<Integer> createLblMst(@RequestBody LblMst lblMst) throws Exception {
        return ResponseEntity.ok().body(lblService.createLblMst(lblMst));
    }

    /**
     * 메시지타입 목록 조회
     *
     * @return 메시지타입 목록
     * @throws Exception
     */
    @GetMapping("lblMsts")
    public ResponseEntity<List<LblMst>> getLblMsts() throws Exception {
        return ResponseEntity.ok().body(lblService.getLblMsts());
    }

    /**
     * 메시지타입 상세 조회
     *
     * @param mstCd
     *            메시지타입
     * @return 메시지타입
     * @throws Exception
     */
    @GetMapping("lblMst/{mstCd}")
    public ResponseEntity<LblMst> getLblMst(@PathVariable("mstCd") String mstCd) throws Exception {
        return ResponseEntity.ok().body(lblService.getLblMst(mstCd));
    }

    /**
     * 메시지타입 수정
     *
     * @param lblMst
     *            메시지타입
     * @return 수정 행 수
     * @throws Exception
     */
    @PutMapping("lblMst")
    public ResponseEntity<Integer> updateLblMst(@RequestBody LblMst lblMst) throws Exception {
        return ResponseEntity.ok().body(lblService.updateLblMst(lblMst));
    }

    /**
     * 메시지 중복 확인
     *
     * @param parameter
     *            메시지타입, 한국어
     * @return 중복 행 수
     * @throws Exception
     */
    @GetMapping("check/lblDtl")
    public int checkLblDtl(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = requestMapper.convertAsParameter(parameter);

        // 메시지타입
        String mstCd = map.containsKey("mstCd") ? map.get("mstCd").toString() : "";

        // 한국어
        String lblVal = map.containsKey("lblVal") ? map.get("lblVal").toString() : "";

        return lblService.checkLblDtl(mstCd, lblVal.trim());
    }

    /**
     * 메시지코드 등록
     *
     * @param lblDtl
     *            메시지코드
     * @return 등록 행 수
     * @throws Exception
     */
    @PostMapping("lblDtl")
    public ResponseEntity<String> createLblDtl(@RequestBody LblDtl lblDtl) throws Exception {
        return ResponseEntity.ok().body(lblService.createLblDtl(lblDtl));
    }

    /**
     * 메시지코드 목록 조회
     *
     * @param mstCd
     *            메시지타입
     * @return 메시지코드 목록
     * @throws Exception
     */
    @GetMapping("lblDtls/{mstCd}")
    public ResponseEntity<List<LblDtl>> getLblDtls(@PathVariable("mstCd") String mstCd) throws Exception {
        return ResponseEntity.ok().body(lblService.getLblDtls(mstCd));
    }

    /**
     * 메시지코드 상세 조회
     *
     * @param lblCd
     *            메시지코드
     * @return 메시지코드
     * @throws Exception
     */
    @GetMapping("lblDtl/{lblCd}")
    public ResponseEntity<LblDtl> getLblDtl(@PathVariable("lblCd") String lblCd) throws Exception {
        return ResponseEntity.ok().body(lblService.getLblDtl(lblCd));
    }

    /**
     * 메시지코드 수정
     *
     * @param lblDtl
     *            메시지코드
     * @return 수정 행 수
     * @throws Exception
     */
    @PutMapping("lblDtl")
    public ResponseEntity<String> updateLblDtl(@RequestBody LblDtl lblDtl) throws Exception {
        return ResponseEntity.ok().body(lblService.updateLblDtl(lblDtl));
    }
}
