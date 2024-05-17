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
package com.she.baseInfo.facility.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.she.baseInfo.facility.service.FacilityService;
import com.she.baseInfo.model.FacilityMst;
import com.she.baseInfo.model.FacilityTypeItem;
import com.she.common.model.DefaultParam;
import com.she.utils.RequestMapper;

/**
 * 안전설비마스터
 */
@RequestMapping("/api/saf/facility/")
@RestController
public class FacilityController {

    // TODO : queryString 변환을 위한 mapper 선언
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private FacilityService facilityService;

    /**
     * 안전설비마스터 조회
     * 
     * @param parameter
     *            검색조건
     * @return 안전설비마스터 목록
     */
    @GetMapping("facilitymsts")
    public ResponseEntity<List<FacilityMst>> getFacilityMsts(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam)
            throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 설비유형
        String safFacilityTypeCd = map.containsKey("safFacilityTypeCd") ? map.get("safFacilityTypeCd").toString() : "";
        // 공정
        String processCd = map.containsKey("processCd") ? map.get("processCd").toString() : "";
        // 설비명
        String facilityNm = map.containsKey("facilityNm") ? map.get("facilityNm").toString() : "";
        // 설비관리부서
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";
        // 설비코드
        String safFacilityCd = map.containsKey("safFacilityCd") ? map.get("safFacilityCd").toString() : "";
        // 설치위치
        String installLocate = map.containsKey("installLocate") ? map.get("installLocate").toString() : "";
        // 사업장
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 폐기여부
        String disuseYn = map.containsKey("disuseYn") ? map.get("disuseYn").toString() : "";
        // 점검종류
        String safCheckTypeCd = map.containsKey("safCheckTypeCd") ? map.get("safCheckTypeCd").toString() : "";
        // 점검종류
        String safLsnYn = map.containsKey("safLsnYn") ? map.get("safLsnYn").toString() : "";
        // 점검종류
        String safChkYn = map.containsKey("safChkYn") ? map.get("safChkYn").toString() : "";

        return ResponseEntity.ok().body(facilityService.getFacilityMsts(safFacilityTypeCd, facilityNm, deptCd,
                processCd, safFacilityCd, installLocate, plantCd, disuseYn, safCheckTypeCd, safLsnYn, safChkYn, defaultParam));
    }

    /**
     * 안전설비마스터 상세조회
     * 
     * @param safFacilityCd
     *            안전설비코드
     * @return 안전설비마스터
     * @throws Exception
     */
    @GetMapping("facilitymst")
    public ResponseEntity<FacilityMst> getFacilityMst(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam)
            throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 설비유형
        String safFacilityCd = map.containsKey("safFacilityCd") ? map.get("safFacilityCd").toString() : "";
        return ResponseEntity.ok().body(this.facilityService.getFacilityMst(safFacilityCd, defaultParam));
    }

    /**
     * 안전설비유형 관리항목 조회
     * 
     * @param parameter
     * @return 안전설비유형 관리항목 목록
     * @throws Exception
     */
    @GetMapping("/facilitytypeitems")
    public ResponseEntity<List<FacilityTypeItem>> getFacilityTypeItems(@RequestParam HashMap<String, Object> parameter)
            throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        String safFacilityCd = map.containsKey("safFacilityCd") ? map.get("safFacilityCd").toString() : "";
        String safFacilityTypeCd = map.containsKey("safFacilityTypeCd") ? map.get("safFacilityTypeCd").toString() : "";

        List<FacilityTypeItem> facilityTypeItems = facilityService.getFacilityTypeItems(safFacilityCd,
                safFacilityTypeCd);
        return ResponseEntity.ok().body(facilityTypeItems);
    }

    /**
     * 권한그룹 중복검사
     * 
     * @param safFacilityCd
     * @return 중복 행 수
     * @throws Exception
     */
    @GetMapping("/countfacilitymst")
    public ResponseEntity<Integer> countFacilityMst(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 설비코드
        String safFacilityCd = map.containsKey("safFacilityCd") ? map.get("safFacilityCd").toString() : "";

        return ResponseEntity.ok().body(this.facilityService.countFacilityMst(safFacilityCd));
    }

    /**
     * 안전설비마스터 신규등록
     * 
     * @param facilityMst
     * @return 등록 행 수
     * @throws Exception
     */
    @PostMapping("facilitymst")
    public ResponseEntity<String> createFacilityMst(@RequestBody FacilityMst facilityMst, @ModelAttribute DefaultParam defaultParam) throws Exception {
        return ResponseEntity.ok()
                .body(this.facilityService.createFacilityMst(facilityMst, defaultParam) > 0 ? facilityMst.getSafFacilityCd() : "");
    }

    /**
     * 안전설비마스터 수정
     * 
     * @param facilityMst
     * @return 수정 행 수
     * @throws Exception
     */
    @PutMapping("facilitymst")
    public ResponseEntity<String> updateFacilityMst(@RequestBody FacilityMst facilityMst, @ModelAttribute DefaultParam defaultParam) throws Exception {
        return ResponseEntity.ok().body(this.facilityService.updateFacilityMst(facilityMst, defaultParam));
    }

    /**
     * 부서공정별 설비자재현황 조회
     * 
     * @param parameter
     *            검색조건
     * @return 부서공정별 설비자재현황
     * @throws Exception
     */
    @GetMapping("facilitymststatus")
    public ResponseEntity<List<FacilityMst>> getFacilityMstStatus(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam)
            throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 설비관리부서
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 설비관리부서
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";
        // 공정
        String processCd = map.containsKey("processCd") ? map.get("processCd").toString() : "";
        // 공정
        String specHealthString = map.containsKey("specHealth") ? map.get("specHealth").toString() : "false";
        boolean specHealth = Boolean.parseBoolean(specHealthString);

        return ResponseEntity.ok().body(facilityService.getFacilityMstStatus(plantCd, deptCd, processCd, specHealth, defaultParam));
    }

}
