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
package com.she.env.air.baseInfo.controller;

import java.util.HashMap;
import java.util.List;

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

import com.she.common.model.DefaultParam;
import com.she.env.air.baseInfo.service.OutletService;
import com.she.env.air.model.Outlet;
import com.she.utils.RequestMapper;

/**
 * 대기 배출구 컨트롤러
 *
 */
@RestController
@RequestMapping("api/env/air/baseinfo/outlet")
public class OutletController {
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private OutletService outletService;

    /**
     * 배출구 전체 조회
     * 
     * @param parameter
     *            검색조건
     * @return 배출구목록
     * @throws Exception
     */
    @GetMapping("/outlets")
    public ResponseEntity<List<Outlet>> getOutlets(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = requestMapper.convertAsParameter(parameter);

        // 사용여부
        String useYn = map.containsKey("useYn") ? map.get("useYn").toString() : "";
        // 배출구일련번호
        String eairOutletNm = map.containsKey("eairOutletNm") ? map.get("eairOutletNm").toString() : "";
        // 배출구명
        String mainDischFacNm = map.containsKey("mainDischFacNm") ? map.get("mainDischFacNm").toString() : "";
        // 사업장코드
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 관리부서코드
        String mgDeptCd = map.containsKey("mgDeptCd") ? map.get("mgDeptCd").toString() : "";

        return ResponseEntity.ok().body(this.outletService.getOutlets(useYn, eairOutletNm, mainDischFacNm, plantCd, mgDeptCd, defaultParam));
    }

    /**
     * 배출구 상세 조회
     * 
     * @param eairOutletNo
     *            배출구번호
     * @return 배출구
     * @throws Exception
     */
    @GetMapping("/outlet/{eairOutletNo}")
    public ResponseEntity<Outlet> getOutlet(@PathVariable(name = "eairOutletNo") int eairOutletNo, @ModelAttribute DefaultParam defaultParam) throws Exception {
        return ResponseEntity.ok().body(this.outletService.getOutlet(eairOutletNo, defaultParam));
    }

    /**
     * 배출구 신규등록
     * 
     * @param outlet
     *            배출구
     * @return 배출구번호
     * @throws Exception
     */
    @PostMapping("/outlet")
    public ResponseEntity<Integer> createOutlet(@RequestBody Outlet outlet) throws Exception {
        return ResponseEntity.ok().body(this.outletService.createOutlet(outlet));
    }

    /**
     * 배출구 수정
     * 
     * @param outlet
     *            배출구
     * @return 수정행수
     * @throws Exception
     */
    @PutMapping("/outlet")
    public ResponseEntity<Integer> updateOutlet(@RequestBody Outlet outlet) throws Exception {
        return ResponseEntity.ok().body(this.outletService.updateOutlet(outlet));
    }

    /**
     * 배출구명 중복체크
     *
     * @param parameter
     *            중복조건
     * @return 중복 행 수
     * @throws Exception
     */
    @GetMapping("/check/outlet")
    public ResponseEntity<Integer> checkOutlet(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = requestMapper.convertAsParameter(parameter);

        // 배출구번호
        int eairOutletNo = map.containsKey("eairOutletNo") ? Integer.parseInt(map.get("eairOutletNo").toString()) : 0;
        // 배출구명
        String mainDischFacNm = map.containsKey("mainDischFacNm") ? map.get("mainDischFacNm").toString() : "";
        // 사업장코드
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";

        return ResponseEntity.ok().body(this.outletService.checkOutlet(eairOutletNo, mainDischFacNm, plantCd));
    }
}
