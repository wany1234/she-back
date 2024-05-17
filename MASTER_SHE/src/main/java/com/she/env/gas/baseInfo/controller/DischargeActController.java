package com.she.env.gas.baseInfo.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.she.env.gas.baseInfo.service.DischargeActService;
import com.she.env.gas.model.DischargeAct;
import com.she.utils.RequestMapper;

@RestController
public class DischargeActController {

    // queryString 변환을 위한 mapper 선언
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private DischargeActService dischargeActService;

    /**
     * 배출활동코드 조회
     * 
     * @param parameter
     *            검색조건
     * @return 배출활동코드 목록
     * @throws Exception
     */
    @GetMapping("api/env/gas/baseinfo/disacts")
    public ResponseEntity<List<DischargeAct>> getDischargeActs(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 배출활동구분
        String disClsCd = map.containsKey("disClsCd") ? map.get("disClsCd").toString() : "";
        // 배출활동명
        String disActNm = map.containsKey("disActNm") ? map.get("disActNm").toString() : "";
        // 사용여부
        String useYn = map.containsKey("useYn") ? map.get("useYn").toString() : "";

        return ResponseEntity.ok().body(dischargeActService.getDischargeActs(disClsCd, disActNm, useYn));
    }

    /**
     * 배출활동코드 상세조회
     * 
     * @param disActCd
     *            배출활동코드
     * @return 배출활동코드 상세내역
     * @throws Exception
     */
    @GetMapping("api/env/gas/baseinfo/disact/{disActCd}")
    public ResponseEntity<DischargeAct> getDischargeAct(@PathVariable("disActCd") String dischargeAct) throws Exception {
        return ResponseEntity.ok().body(this.dischargeActService.getDischargeAct(dischargeAct));
    }

    /**
     * 배출활동코드 체크
     * 
     * @param disActCd
     *            배출활동코드
     * @return 배출활동 중복코드 갯수
     * @throws Exception
     */
    @GetMapping("/api/env/gas/baseinfo/count/disact/{disActCd}")
    public ResponseEntity<Integer> countDischargeAct(@PathVariable("disActCd") String disActCd) throws Exception {
        return ResponseEntity.ok(dischargeActService.countDischargeAct(disActCd));
    }

    /**
     * 배출활동코드 신규등록
     * 
     * @param parameter
     *            검색조건
     * @return 배출활동코드
     * @throws Exception
     */
    @PostMapping("api/env/gas/baseinfo/disact")
    public ResponseEntity<String> createDischargeAct(@RequestBody DischargeAct dischargeAct) throws Exception {
        return ResponseEntity.ok().body(this.dischargeActService.createDischargeAct(dischargeAct));
    }

    /**
     * 배출활동코드 수정
     * 
     * @param parameter
     *            검색조건
     * @return 배출활동코드
     * @throws Exception
     */
    @PutMapping("api/env/gas/baseinfo/disact")
    public ResponseEntity<String> updateDischargeAct(@RequestBody DischargeAct dischargeAct) throws Exception {
        return ResponseEntity.ok().body(this.dischargeActService.updateDischargeAct(dischargeAct));
    }
}
