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
package com.she.vendor.assmnSfhlc.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import com.she.utils.RequestMapper;
import com.she.vendor.assmnSfhlc.service.AssmnSfhlcService;
import com.she.vendor.model.AssmnSfhlc;
import com.she.vendor.model.AssmnSfhlcItemList;
import com.she.vendor.model.AssmnSfhlcVendorList;

@RestController
@RequestMapping("api/vendor/assmnSfhlc")
public class AssmnSfhlcController {
    // TODO : queryString 변환을 위한 mapper 선언
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private AssmnSfhlcService assmnSfhlcService;

    /**
     * 평가 및 안전보건 계획 목록 조회
     *
     * @param parameter
     * @return 평가 및 안전보건 계획 목록 조회
     * @throws Exception
     */
    @GetMapping("/planmgmtlists")
    public ResponseEntity<List<AssmnSfhlc>> getPlanmgmtLists(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 사업장
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 대상년도
        String year = map.containsKey("year") ? map.get("year").toString() : "";
        // 평가구분
        String evalClsCd = map.containsKey("evalClsCd") ? map.get("evalClsCd").toString() : "";
        // 평가명
        String evalTitle = map.containsKey("evalTitle") ? map.get("evalTitle").toString() : "";
        // 주관부서
        String mainDeptCd = map.containsKey("mainDeptCd") ? map.get("mainDeptCd").toString() : "";
        // 하위부서 여부
        String deptSubYn = map.containsKey("deptSubYn") ? map.get("deptSubYn").toString() : "";
        // 단계
        String stateCd = map.containsKey("stateCd") ? map.get("stateCd").toString() : "";
        // 진행상태
        String procStepCd = map.containsKey("procStepCd") ? map.get("procStepCd").toString() : "";
        // 평가기한초과
        String yearChk = map.containsKey("yearChk") ? map.get("yearChk").toString() : "";
        // 수행부서
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";
        // 하위부서 사용여부
        String deptSubYn2 = map.containsKey("deptSubYn2") ? map.get("deptSubYn2").toString() : "";
        return ResponseEntity.ok().body(assmnSfhlcService.getPlanmgmtLists(plantCd, year, evalClsCd, evalTitle, mainDeptCd, deptSubYn, stateCd, procStepCd, yearChk, deptCd, deptSubYn2, defaultParam));
    }

    /**
     * 평가 및 안전보건 계획 조회
     *
     * @param parameter
     * @return 평가 및 안전보건 계획 조회
     * @throws Exception
     */
    @GetMapping("/planmgmtinfo/{vendorEvalPlanNo}")
    public ResponseEntity<AssmnSfhlc> getPlanmgmtInfo(@PathVariable("vendorEvalPlanNo") int vendorEvalPlanNo, @ModelAttribute DefaultParam defaultParam) throws Exception {

        return ResponseEntity.ok().body(assmnSfhlcService.getPlanmgmtInfo(vendorEvalPlanNo, defaultParam));
    }

    /**
     * 평가 및 안전보건 계획 > 평가항목(기본조회)
     *
     * @param parameter
     * @return 평가 및 안전보건 계획 > 평가항목(기본조회)
     * @throws Exception
     */
    @GetMapping("/searchAssmnSfhlcItem/{vendorEvalPlanNo}/{plantCd}/{evalClsCd}")
    public ResponseEntity<List<AssmnSfhlcItemList>> searchAssmnSfhlcItemList(@PathVariable("vendorEvalPlanNo") int vendorEvalPlanNo, @PathVariable("plantCd") String plantCd, @PathVariable("evalClsCd") String evalClsCd, @ModelAttribute DefaultParam defaultParam) throws Exception {

        return ResponseEntity.ok().body(assmnSfhlcService.searchAssmnSfhlcItemList(vendorEvalPlanNo, plantCd, evalClsCd));
    }

    /**
     * 평가 및 안전보건 계획 등록
     *
     * @param parameter
     * @return 평가 및 안전보건 계획 등록
     * @throws Exception
     */
    @PostMapping("/assmnSfhlcplan")
    public ResponseEntity<Integer> createAssmnSfhlc(@RequestBody AssmnSfhlc assmnSfhlc) throws Exception {
        return ResponseEntity.ok().body(assmnSfhlcService.createAssmnSfhlc(assmnSfhlc));
    }

    /**
     * 평가 및 안전보건 계획 수정
     *
     * @param parameter
     * @return 평가 및 안전보건 계획 수정
     * @throws Exception
     */
    @PutMapping("/assmnSfhlcplan")
    public ResponseEntity<Integer> updateAssmnSfhlc(@RequestBody AssmnSfhlc assmnSfhlc) throws Exception {
        return ResponseEntity.ok().body(assmnSfhlcService.updateAssmnSfhlc(assmnSfhlc));
    }

    /**
     * 평가 및 안전보건 계획 삭제
     *
     * @param parameter
     * @return 평가 및 안전보건 계획 삭제
     * @throws Exception
     */
    @DeleteMapping("/assmnSfhlcplan/{vendorEvalPlanNo}")
    public ResponseEntity<Integer> deleteAssmnSfhlc(@PathVariable("vendorEvalPlanNo") int vendorEvalPlanNo) throws Exception {
        return ResponseEntity.ok().body(assmnSfhlcService.deleteAssmnSfhlc(vendorEvalPlanNo));
    }

    /**
     * 평가 및 안전보건 계획 삭제 가능 확인
     * 
     * @param parameter
     *            검색조건
     * @return 평가 및 안전보건 계획 삭제 가능 확인
     * @throws Exception
     */
    @GetMapping("/planmgmtstatus/{vendorEvalPlanNo}")
    public ResponseEntity<Integer> getPlanmgmtStatus(@PathVariable("vendorEvalPlanNo") int vendorEvalPlanNo, @ModelAttribute DefaultParam defaultParam) throws Exception {
        return ResponseEntity.ok().body(assmnSfhlcService.getPlanmgmtStatus(vendorEvalPlanNo));
    }

    /**
     * 평가 및 안전보건 계획 대상협력업체 삭제
     *
     * @param parameter
     * @return 평가 및 안전보건 계획 대상협력업체 삭제
     * @throws Exception
     */
    @DeleteMapping("/deleteAssmnSfhlcVendor")
    public ResponseEntity<Integer> deleteAssmnSfhlcVendorEach(@RequestBody List<AssmnSfhlcVendorList> assmnSfhlcVendorList) throws Exception {
        return ResponseEntity.ok().body(assmnSfhlcService.deleteAssmnSfhlcVendorEach(assmnSfhlcVendorList));
    }

    /**
     * 평가 및 안전보건 계획 대상협력업체 팝업
     *
     * @param parameter
     * @return 평가 및 안전보건 계획 대상협력업체 팝업
     * @throws Exception
     */
    @GetMapping("/planmgmtVendorList")
    public ResponseEntity<List<AssmnSfhlcVendorList>> getPlanmgmtVendorList(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 사업장
        int vendorEvalPlanNo = parameter.containsKey("vendorEvalPlanNo") ? Integer.parseInt(parameter.get("vendorEvalPlanNo").toString()) : 0;
        // 대상년도
        String apprFlag = map.containsKey("apprFlag") ? map.get("apprFlag").toString() : "";
        // 평가구분
        String procStepCd = map.containsKey("procStepCd") ? map.get("procStepCd").toString() : "";
        return ResponseEntity.ok().body(assmnSfhlcService.getPlanmgmtVendorList(vendorEvalPlanNo, apprFlag, procStepCd));
    }

    /**
     * 평가 및 안전보건 결과 목록
     *
     * @param parameter
     * @return 평가 및 안전보건 결과 목록
     * @throws Exception
     */
    @GetMapping("/resultlists")
    public ResponseEntity<List<AssmnSfhlcVendorList>> getResultList(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 사업장
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 평가구분
        String evalClsCd = map.containsKey("evalClsCd") ? map.get("evalClsCd").toString() : "";
        // 평가기간
        String[] assessDt = this.requestMapper.convertObjectListAsStringArray(map.get("assessDt"));
        String startDt = "";
        String endDt = "";

        if (assessDt != null && assessDt.length == 2) {
            startDt = assessDt[0];
            endDt = assessDt[1];
        }
        // 평가명
        String evalTitle = map.containsKey("evalTitle") ? map.get("evalTitle").toString() : "";
        // 업체명
        String vendorNm = map.containsKey("vendorNm") ? map.get("vendorNm").toString() : "";
        // 단계
        String procStepCd = map.containsKey("procStepCd") ? map.get("procStepCd").toString() : "";
        // 상태
        String stateCd = map.containsKey("stateCd") ? map.get("stateCd").toString() : "";
        // 산업안전보건비 반영여부
        String costRefYn = map.containsKey("costRefYn") ? map.get("costRefYn").toString() : "";
        // 하위부서사용여부
        String deptSubYn = map.containsKey("deptSubYn") ? map.get("deptSubYn").toString() : "";
        // 수행부서
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";

        return ResponseEntity.ok().body(assmnSfhlcService.getResultList(plantCd, evalClsCd, startDt, endDt, evalTitle, vendorNm, procStepCd, stateCd, costRefYn, deptSubYn, deptCd, defaultParam));
    }

    /**
     * 평가 및 안전보건 결과 조회
     *
     * @param parameter
     * @return 평가 및 안전보건 결과 조회
     * @throws Exception
     */
    @GetMapping("/resultinfo/{evalPlanVendorNo}")
    public ResponseEntity<AssmnSfhlcVendorList> getResultmgmtInfo(@PathVariable("evalPlanVendorNo") int evalPlanVendorNo, @ModelAttribute DefaultParam defaultParam) throws Exception {

        return ResponseEntity.ok().body(assmnSfhlcService.getResultmgmtInfo(evalPlanVendorNo, defaultParam));
    }

    /**
     * 평가 및 안전보건 결과 등록
     *
     * @param parameter
     * @return 평가 및 안전보건 결과 등록
     * @throws Exception
     */
    @PostMapping("/assmnSfhlcresult")
    public ResponseEntity<Integer> createAssmnSfhlcResult(@RequestBody AssmnSfhlcVendorList assmnSfhlc) throws Exception {
        return ResponseEntity.ok().body(assmnSfhlcService.createAssmnSfhlcResult(assmnSfhlc));
    }

    /**
     * 평가 및 안전보건 결과 수정
     *
     * @param parameter
     * @return 평가 및 안전보건 결과 수정
     * @throws Exception
     */
    @PutMapping("/assmnSfhlcresult")
    public ResponseEntity<Integer> updateAssmnSfhlcResult(@RequestBody AssmnSfhlcVendorList assmnSfhlc) throws Exception {
        return ResponseEntity.ok().body(assmnSfhlcService.updateAssmnSfhlcResult(assmnSfhlc));
    }

    /**
     * 평가 및 안전보건 결과 삭제
     *
     * @param parameter
     * @return 평가 및 안전보건 결과 삭제
     * @throws Exception
     */
    @DeleteMapping("/assmnSfhlcresult/{evalPlanVendorNo}")
    public ResponseEntity<Integer> deleteAssmnSfhlcResult(@PathVariable("evalPlanVendorNo") int evalPlanVendorNo) throws Exception {
        return ResponseEntity.ok().body(assmnSfhlcService.deleteAssmnSfhlcResult(evalPlanVendorNo));
    }

    /**
     * 평가 및 안전보건 비용 현황 목록
     *
     * @param parameter
     * @return 평가 및 안전보건 비용 현황 목록
     * @throws Exception
     */
    @GetMapping("/status")
    public ResponseEntity<List<HashMap<String, Object>>> getAssmnSfhlcStatus(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        // 사업장
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 대상년도
        String year = map.containsKey("year") ? map.get("year").toString() : "";
        String allSearchYn = map.containsKey("allSearchYn") ? map.get("allSearchYn").toString() : "";
        return ResponseEntity.ok().body(assmnSfhlcService.getAssmnSfhlcStatus(plantCd, year, allSearchYn));
    }

    /**
     * 평가 및 안전보건 비용 현황 팝업
     *
     * @param parameter
     * @return 평가 및 안전보건 비용 현황 팝업
     * @throws Exception
     */
    @GetMapping("/statusPopup")
    public ResponseEntity<List<HashMap<String, Object>>> getAssmnSfhlcStatusPopupList(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        // 사업장
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 대상년도
        String year = map.containsKey("year") ? map.get("year").toString() : "";
        // 조회 종류
        String searchFlag = map.containsKey("searchFlag") ? map.get("searchFlag").toString() : "";
        // 조회 월 구분
        String monFlag = map.containsKey("monFlag") ? map.get("monFlag").toString() : "";
        return ResponseEntity.ok().body(assmnSfhlcService.getAssmnSfhlcStatusPopupList(plantCd, year, searchFlag, monFlag));
    }
}
