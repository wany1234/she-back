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
import java.util.Optional;
import java.util.stream.Collectors;

import com.google.common.base.Strings;
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
import org.springframework.web.bind.annotation.DeleteMapping;

import com.she.env.air.facility.service.PreventFacService;
import com.she.env.air.model.PreventFac;
import com.she.env.air.model.PreventFacActCarbHist;
import com.she.env.air.model.PreventFacChgHist;

import com.she.env.air.model.PreventFacMaintHist;
import com.she.utils.RequestMapper;

/**
 * 대기 방지시설
 *
 */
@RestController("EairPreventFacNController")
@RequestMapping("api/env/air/facility/facility")
public class PreventFacController {

    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private PreventFacService preventFacService;

    /**
     * 방지시설 전체 조회
     *
     * @param parameter
     *            검색조건
     * @return 방지시설 목록
     * @throws Exception
     */
    @GetMapping("/preventionfacilities")
    public ResponseEntity<List<PreventFac>> getPreventionFacilities(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = requestMapper.convertAsParameter(parameter);

        // 사업장코드
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 관리부서코드
        String mgDeptCd = map.containsKey("mgDeptCd") ? map.get("mgDeptCd").toString() : "";
        // (C)대기오염 방지시설 분류 코드
        String eairPreventFacClassCd = map.containsKey("eairPreventFacClassCd") ? map.get("eairPreventFacClassCd").toString() : "";
        // 방지시설명
        String eairPreventFacNm = map.containsKey("eairPreventFacNm") ? map.get("eairPreventFacNm").toString() : "";
        // 사용여부
        String useYn = map.containsKey("useYn") ? map.get("useYn").toString() : "";

        return ResponseEntity.ok().body(this.preventFacService.getPreventionFacilities(useYn, eairPreventFacClassCd, eairPreventFacNm, plantCd, mgDeptCd, defaultParam));
    }

    /**
     * 방지시설 상세 조회
     *
     * @param eairPreventFacNo
     *            방지시설번호
     * @return 방지시설
     * @throws Exception
     */
    @GetMapping("/preventionfacility/{eairPreventFacNo}")
    public ResponseEntity<PreventFac> getPreventionFacility(@PathVariable(name = "eairPreventFacNo") int eairPreventFacNo) throws Exception {
        return ResponseEntity.ok().body(this.preventFacService.getPreventionFacility(eairPreventFacNo));
    }

    /**
     * 방지시설 신규등록
     *
     * @param preventFac
     *            방지시설
     * @return 방지시설번호
     * @throws Exception
     */
    @PostMapping("/preventionfacility")
    public ResponseEntity<Integer> createPreventionFacility(@RequestBody PreventFac preventFac) throws Exception {
        return ResponseEntity.ok().body(this.preventFacService.createPreventionFacility(preventFac));
    }

    /**
     * 선택된 방지지설 수정
     *
     * @param preventFac
     *            방지시설
     * @return 수정행수
     * @throws Exception
     */
    @PutMapping("/preventionfacility")
    public ResponseEntity<Integer> updatePreventionFacility(@RequestBody PreventFac preventFac) throws Exception {
        return ResponseEntity.ok().body(this.preventFacService.updatePreventionFacility(preventFac));
    }

    // 방지시설 변경이력
    /**
     * 방지시설 변경이력 전체 조회
     *
     * @param parameter
     * @return 방지시설 변경이력 목록
     * @throws Exception
     */
    @GetMapping("/preventionfacilitychangehistories")
    public ResponseEntity<List<PreventFacChgHist>> getPreventionFacilityChangeHistories(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = requestMapper.convertAsParameter(parameter);

        int eairPreventFacNo = map.containsKey("eairPreventFacNo") ? Integer.parseInt(map.get("eairPreventFacNo").toString()) : 0;

        List<PreventFacChgHist> preventFacChgHists = this.preventFacService.getPreventionFacilityChangeHistories(eairPreventFacNo, defaultParam);

        for (int i = 0; i < preventFacChgHists.size(); i++) {
            PreventFacChgHist preventFacChgHist = preventFacChgHists.get(i);

            preventFacChgHist.setEairChemNm(this.preventFacService.selectPreventionFacilityChangeHistoryChem(preventFacChgHist.getEairPreventFacChgHistNo()).stream().map(p -> {
                return Optional.ofNullable(p.getName()).orElse("");
            }).collect(Collectors.joining(" ")));

            preventFacChgHist.setEairPolluNm(this.preventFacService.selectPreventionFacilityChangeHistoryPollu(preventFacChgHist.getEairPreventFacChgHistNo(), defaultParam).stream().map(p -> {
                return Optional.ofNullable(p.getName()).orElse("");
            }).collect(Collectors.joining(" ")));
        }

        return ResponseEntity.ok().body(preventFacChgHists);
    }

    /**
     * 선택된 방지시설 변경이력 상세 조회
     *
     * @param eairPreventFacChgHistNo
     *            방지시설 변경 이력 번호
     * @param eairPreventFacNo
     *            방지시설 번호
     * @return 방지시설 변경이력
     * @throws Exception
     */
    @GetMapping("/preventionfacilitychangehistory/{eairPreventFacChgHistNo}/{eairPreventFacNo}")
    public ResponseEntity<PreventFacChgHist> getPreventionFacilityChangeHistory(@PathVariable(name = "eairPreventFacChgHistNo") int eairPreventFacChgHistNo, @PathVariable(name = "eairPreventFacNo") int eairPreventFacNo, @ModelAttribute DefaultParam defaultParam) throws Exception {
        PreventFacChgHist preventFacChgHist;
        if (eairPreventFacChgHistNo == 0) {
            // 이력번호가 0 이면 방지시설의 마지막 이력을 조회함
            preventFacChgHist = this.preventFacService.getPreventionFacilityLastChangeHistory(eairPreventFacNo, defaultParam);
            if (preventFacChgHist != null) {
                preventFacChgHist.setEairChemCds(this.preventFacService.selectPreventionFacilityChangeHistoryChem(preventFacChgHist.getEairPreventFacChgHistNo()));
                preventFacChgHist.setEairPolluCds(this.preventFacService.selectPreventionFacilityChangeHistoryPollu(preventFacChgHist.getEairPreventFacChgHistNo(), defaultParam));
            }
        } else {
            preventFacChgHist = this.preventFacService.getPreventionFacilityChangeHistory(eairPreventFacChgHistNo, defaultParam);
            if (preventFacChgHist != null) {
                preventFacChgHist.setEairChemCds(this.preventFacService.selectPreventionFacilityChangeHistoryChem(eairPreventFacChgHistNo));
                preventFacChgHist.setEairPolluCds(this.preventFacService.selectPreventionFacilityChangeHistoryPollu(eairPreventFacChgHistNo, defaultParam));
            }
        }

        return ResponseEntity.ok().body(preventFacChgHist);
    }

    /**
     * 방지시설 변경이력 신규등록
     *
     * @param preventFacChgHist
     *            방지시설 변경이력
     * @return 등록행수
     * @throws Exception
     */
    @PostMapping("/preventionfacilitychangehistory")
    public ResponseEntity<Integer> createPreventionFacilityChangeHistory(@RequestBody PreventFacChgHist preventFacChgHist) throws Exception {
        return ResponseEntity.ok().body(this.preventFacService.createPreventionFacilityChangeHistory(preventFacChgHist));
    }

    /**
     * 선택된 방지시설 변경이력 수정
     *
     * @param preventFacChgHist
     *            방지시설 변경이력
     * @return 수정행수
     * @throws Exception
     */
    @PutMapping("/preventionfacilitychangehistory")
    public ResponseEntity<Integer> updatePreventionFacilityChangeHistory(@RequestBody PreventFacChgHist preventFacChgHist) throws Exception {

        //
        // if (count > 0)
        // {
        // this.outletMapper.deleteOutletTestItem(outlet.getEairOutletNo());
        //
        // for (TestItem eairTestItemCd : outlet.getEairTestItemCds())
        // {
        // eairTestItemCd.setEairOutletNo(outlet.getEairOutletNo());
        //
        // count += this.outletMapper.createOutletTestItem(eairTestItemCd);
        // }
        // }
        //
        return ResponseEntity.ok().body(this.preventFacService.updatePreventionFacilityChangeHistory(preventFacChgHist));
    }

    /**
     * 선택된 방지시설 변경이력 삭제
     *
     * @param eairPreventFacChgHistNo
     *            방지시설 변경 이력 번호
     * @return 삭제행수
     * @throws Exception
     */
    @DeleteMapping("/preventionfacilitychangehistory")
    public ResponseEntity<Integer> deletePreventionFacilityChangeHistory(@RequestBody List<PreventFacChgHist> preventFacChgHists) throws Exception {
        Integer count = this.preventFacService.deletePreventionFacilityChangeHistory(preventFacChgHists);
        return ResponseEntity.ok().body(count);
    }

    // 방지시설 보수이력
    /**
     * 방지시설 보수이력 전체 조회
     *
     * @param eairPreventFacNo
     *            방지시설번호
     * @return 방지시설 보수이력 목록
     * @throws Exception
     */
    @GetMapping("/preventionfacilitymaintenancehistories")
    public ResponseEntity<List<PreventFacMaintHist>> getPreventionFacilityMaintenanceHistories(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = requestMapper.convertAsParameter(parameter);

        int eairPreventFacNo = map.containsKey("eairPreventFacNo") ? Integer.parseInt(map.get("eairPreventFacNo").toString()) : 0;

        List<PreventFacMaintHist> preventFacMaintHists = this.preventFacService.getPreventionFacilityMaintenanceHistories(eairPreventFacNo, defaultParam);

        return ResponseEntity.ok().body(preventFacMaintHists);
    }

    /**
     * 선택된 방지시설 보수이력 상세 조회
     *
     * @param eairPreventFacMaintHistNo
     *            방지시설 보수이력번호
     * @return 방지시설 보수이력
     * @throws Exception
     */
    @GetMapping("/preventionfacilitymaintenancehistory/{eairPreventFacMaintHistNo}")
    public ResponseEntity<PreventFacMaintHist> getPreventionFacilityMaintenanceHistory(@PathVariable(name = "eairPreventFacMaintHistNo") int eairPreventFacMaintHistNo, @ModelAttribute DefaultParam defaultParam) throws Exception {
        PreventFacMaintHist preventFacMaintHist = this.preventFacService.getPreventionFacilityMaintenanceHistory(eairPreventFacMaintHistNo, defaultParam);
        return ResponseEntity.ok().body(preventFacMaintHist);
    }

    /**
     * 방지시설 보수이력 신규등록
     *
     * @param preventFacMaintHist
     *            방지시설 보수이력
     * @return 등록행수
     * @throws Exception
     */
    @PostMapping("/preventionfacilitymaintenancehistory")
    public ResponseEntity<Integer> createPreventionFacilityMaintenanceHistory(@RequestBody PreventFacMaintHist preventFacMaintHist) throws Exception {
        return ResponseEntity.ok().body(this.preventFacService.createPreventionFacilityMaintenanceHistory(preventFacMaintHist));
    }

    /**
     * 활성탄 교체내역 등록
     *
     * @param PreventFacActCarbHist
     *            활성탄 교체 내역
     * @return 활성탄 교체
     * @throws Exception
     */

    @PostMapping("/preventionfacactcarbhist")
    public ResponseEntity<Integer> createPreventionFacilityMaintenanceHistory(@RequestBody PreventFacActCarbHist preventFacActCarbHist) throws Exception {
        return ResponseEntity.ok().body(this.preventFacService.createPreventionFacActCarbHist(preventFacActCarbHist));
    }

    /**
     * 선택된 방지시설 보수이력 수정
     *
     * @param preventFacMaintHist
     *            방지시설 보수이력
     * @return 수정행수
     * @throws Exception
     */
    @PutMapping("/preventionfacilitymaintenancehistory")
    public ResponseEntity<Integer> updatePreventionFacilityMaintenanceHistory(@RequestBody PreventFacMaintHist preventFacMaintHist) throws Exception {
        return ResponseEntity.ok().body(this.preventFacService.updatePreventionFacilityMaintenanceHistory(preventFacMaintHist));
    }

    /**
     * 선택된 방지시설 보수이력 삭제
     *
     * @param eairPreventFacChgHistNo
     *            방지시설 변경 이력 번호
     * @return 삭제행수
     * @throws Exception
     */
    @DeleteMapping("/preventionfacilitymaintenancehistory")
    public ResponseEntity<Integer> deletePreventionFacilityMaintenanceHistory(@RequestBody List<PreventFacMaintHist> preventFacMaintHist) throws Exception {
        Integer count = this.preventFacService.deletePreventionFacilityMaintenanceHistory(preventFacMaintHist);
        return ResponseEntity.ok().body(count);
    }

    /**
     * 활성탄 교체내역 수정
     *
     * @param PreventFacActCarbHist
     *            활성탄 교체내역
     * @return 활성탄 교체
     * @throws Exception
     */
    @PutMapping("/preventionfacactcarbhist")
    public ResponseEntity<Integer> updatePreventionFacilityMaintenanceHistory(@RequestBody PreventFacActCarbHist preventFacActCarbHist) throws Exception {
        return ResponseEntity.ok().body(this.preventFacService.updatePreventionFacActCarbHist(preventFacActCarbHist));
    }

    /**
     * 활성탄 교체 내역 조회
     *
     * @param PreventFacActCarbHist
     *            활성탄 교체 내역
     * @return 활성탄 교체 내역
     * @throws Exception
     */
    @GetMapping("/preventionfacactcarbhist")
    public ResponseEntity<List<PreventFacActCarbHist>> getPreventionFacActCarbHists(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = requestMapper.convertAsParameter(parameter);

        int eairPreventFacNo = map.containsKey("eairPreventFacNo") ? Integer.parseInt(map.get("eairPreventFacNo").toString()) : 0;

        List<PreventFacActCarbHist> preventFacChgHists = this.preventFacService.getPreventionFacActCarbHists(eairPreventFacNo, defaultParam);
        return ResponseEntity.ok().body(preventFacChgHists);
    }

    /**
     * 활성탄 교체 내역단건 조회
     *
     * @param PreventFacActCarbHist
     *            활성탄 교체 내역
     * @return 활성탄 교체
     * @throws Exception
     */
    @GetMapping("/preventionfacactcarbhist/{eairPreventFacActCarbHistNo}")
    public ResponseEntity<PreventFacActCarbHist> getPreventionFacActCarbHist(@PathVariable(name = "eairPreventFacActCarbHistNo") int eairPreventFacActCarbHistNo) throws Exception {

        return ResponseEntity.ok().body(this.preventFacService.getPreventionFacActCarbHist(eairPreventFacActCarbHistNo));
    }

    /**
     * 활성탄 교체내역 삭제
     *
     * @param PreventFacActCarbHist
     *            활성탄 교체내역 삭제
     * @return 활성탄 교체
     * @throws Exception
     */

    @DeleteMapping("/preventionfacactcarbhist")
    public ResponseEntity<Integer> deletePreventionFacActCarbHist(@RequestBody List<PreventFacActCarbHist> preventFacActCarbHist) throws Exception {
        return ResponseEntity.ok().body(this.preventFacService.deletePreventionFacActCarbHist(preventFacActCarbHist));
    }

    /**
     * 방지시설명 중복체크
     *
     * @param parameter
     *            중복체크조건
     * @return 중복 행 수
     * @throws Exception
     */
    @GetMapping("/check/preventionfacility")
    public ResponseEntity<Integer> checkPreventionFacility(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 원료코드
        int eairPreventFacNo = map.containsKey("eairPreventFacNo") ? Integer.parseInt(map.get("eairPreventFacNo").toString()) : 0;
        // 원료명
        String eairPreventFacNm = map.containsKey("eairPreventFacNm") ? map.get("eairPreventFacNm").toString() : "";
        // 사업장코드
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";

        return ResponseEntity.ok().body(this.preventFacService.checkPreventionFacility(eairPreventFacNo, eairPreventFacNm, plantCd));
    }

    /**
     * 고유방지시설번호 중복체크
     *
     * @param parameter
     *            중복체크조건
     * @return 중복 행 수
     * @throws Exception
     */
    @GetMapping("/check/preventioninhnum")
    public ResponseEntity<Integer> checkPreventionInhNum(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 고유방지시설번호
        String eairPreventFacInhNum = map.containsKey("eairPreventFacInhNum") ? map.get("eairPreventFacInhNum").toString() : "0";

        int eairPreventFacNo = map.containsKey("eairPreventFacNo") ? Integer.parseInt(Strings.isNullOrEmpty(map.get("eairPreventFacNo").toString()) ? "0" :map.get("eairPreventFacNo").toString()) : 0;

        int result = this.preventFacService.checkPreventionInhNum(eairPreventFacInhNum, eairPreventFacNo);

        return ResponseEntity.ok().body(result);
    }

}
