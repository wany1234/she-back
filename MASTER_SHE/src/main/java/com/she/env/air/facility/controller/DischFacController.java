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
package com.she.env.air.facility.controller;

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

import com.she.env.air.facility.service.DischFacService;
import com.she.env.air.model.DischFac;
import com.she.utils.RequestMapper;

/**
 * 대기 배출시설
 *
 */
@RestController("DischFacNController")
@RequestMapping("api/env/air/facility/facility")
public class DischFacController {

    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private DischFacService dischFacNService;

    /**
     * 배출시설 전체 조회
     *
     * @param parameter
     *            검색조건
     * @return 배출시설 목록
     * @throws Exception
     */
    @GetMapping("/dischargefacilities")
    public ResponseEntity<List<DischFac>> getDischargeFacilities(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = requestMapper.convertAsParameter(parameter);

        // 사용여부
        String useYn = map.containsKey("useYn") ? map.get("useYn").toString() : "";
        // 대기 배출시설명
        String eairDischFacNm = map.containsKey("eairDischFacNm") ? map.get("eairDischFacNm").toString() : "";
        // 사업장 코드
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 관리부서 코드
        String mgDeptCd = map.containsKey("mgDeptCd") ? map.get("mgDeptCd").toString() : "";

        return ResponseEntity.ok().body(this.dischFacNService.getDischargeFacilities(useYn, eairDischFacNm, plantCd, mgDeptCd, defaultParam));
    }

    /**
     * 선택된 배출시설 상세 조회
     *
     * @param eairDischFacNo
     *            배출시설번호
     * @return 배출시설
     * @throws Exception
     */
    @GetMapping("/dischargefacility/{eairDischFacNo}")
    public ResponseEntity<DischFac> getDischargeFacility(@PathVariable(name = "eairDischFacNo") int eairDischFacNo) throws Exception {
        DischFac dischFac = this.dischFacNService.getDischargeFacility(eairDischFacNo);
        return ResponseEntity.ok().body(dischFac);
    }

    /**
     * 배출시설 신규등록
     *
     * @param dischFac
     *            배출시설
     * @return 배출시설번호
     * @throws Exception
     */
    @PostMapping("/dischargefacility")
    public ResponseEntity<Integer> createDischargeFacility(@RequestBody DischFac dischFac) throws Exception {
        return ResponseEntity.ok().body(this.dischFacNService.createDischargeFacility(dischFac));
    }

    /**
     * 선택된 배출시설 수정
     *
     * @param dischFac
     *            배출시설
     * @return 수정행수
     * @throws Exception
     */
    @PutMapping("/dischargefacility")
    public ResponseEntity<Integer> updateDischargeFacility(@RequestBody DischFac dischFac) throws Exception {
        return ResponseEntity.ok().body(this.dischFacNService.updateDischargeFacility(dischFac));
    }

    /**
     * 배출시설명 중복체크
     *
     * @param parameter
     *            중복조건
     * @return 중복 행 수
     * @throws Exception
     */
    @GetMapping("/check/dischargefacility")
    public ResponseEntity<Integer> checkDischargeFacility(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = requestMapper.convertAsParameter(parameter);

        // 대기 배출시설 명
        String eairDischFacNm = map.containsKey("eairDischFacNm") ? map.get("eairDischFacNm").toString() : "";
        // 대기 배출시설 번호
        int eairDischFacNo = map.containsKey("eairDischFacNo") ? Integer.parseInt(map.get("eairDischFacNo").toString()) : 0;
        // 사업장 코드
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";

        return ResponseEntity.ok().body(this.dischFacNService.checkDischargeFacility(eairDischFacNm, eairDischFacNo, plantCd));
    }
}
