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
package com.she.safety.facilityCheck.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
import com.she.safety.facilityCheck.service.FacilityCheckResultService;
import com.she.safety.model.FacilChkMaster;
import com.she.safety.model.FacilChkResult;
import com.she.safety.model.FacilityCheckSchedule;
import com.she.utils.RequestMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 설비점검결과
 */
@RestController
@RequestMapping("/api/saf/facility/")
@Api(value = "/api/saf/facility/", description = "설비점검 서비스")
public class FacilityCheckResultController {
    // TODO : queryString 변환을 위한 mapper 선언
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private FacilityCheckResultService facilityCheckResultService;

    /**
     * 설비점검계획 조회
     *
     * @param parameter
     *            검색조건
     * @return 설비점검계획 목록
     */
    @ApiOperation(value = "설비점검계획 조회[SAF03005]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "period", value = "기간", required = false, dataType = "array", paramType = "query"), @ApiImplicitParam(name = "plantCd", value = "사업장", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "deptCd", value = "점검주관부서", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "safCheckTypeCd", value = "점검종류", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "safFacilityTypeCd", value = "설비유형", required = false, dataType = "array", paramType = "query"), @ApiImplicitParam(name = "facilityNm", value = "설비명", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "checkStepCd", value = "진행단계", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "safFacilityCheckNo", value = "설비점검마스터번호", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
    @GetMapping("facilitycheckresults")
    public ResponseEntity<List<FacilityCheckSchedule>> getFacilityCheckResults(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        // 기간
        String[] period = this.requestMapper.convertObjectListAsStringArray(map.get("period"));
        String startYmd = "";
        String endYmd = "";
        if (period != null && period.length == 2) {
            startYmd = period[0];
            endYmd = period[1];
        }
        // 사업장
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 점검주관부서
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";
        // 점검종류
        String safCheckTypeCd = map.containsKey("safCheckTypeCd") ? map.get("safCheckTypeCd").toString() : "";
        // 설비유형
        String safFacilityTypeCd = map.containsKey("safFacilityTypeCd") ? map.get("safFacilityTypeCd").toString() : "";
        // 설비명
        String facilityNm = map.containsKey("facilityNm") ? map.get("facilityNm").toString() : "";
        // 진행단계
        String checkStepCd = map.containsKey("checkStepCd") ? map.get("checkStepCd").toString() : "";
        // 설비점검마스터번호
        int safFacilityCheckNo = map.containsKey("safFacilityCheckNo") ? Integer.parseInt("".equals(map.get("safFacilityCheckNo").toString()) ? "0" : map.get("safFacilityCheckNo").toString()) : 0;

        return ResponseEntity.ok().body(facilityCheckResultService.getFacilityCheckResults(startYmd, endYmd, plantCd, deptCd, safCheckTypeCd, safFacilityTypeCd, facilityNm, checkStepCd, safFacilityCheckNo, defaultParam));
    }

    /**
     * 설비점검결과 조회
     *
     * @param parameter
     *            검색조건
     * @return 설비점검결과 목록
     */
    @ApiOperation(value = "설비점검결과 조회[SAF03005]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "period", value = "기간", required = false, dataType = "array", paramType = "query"), @ApiImplicitParam(name = "plantCd", value = "사업장", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "deptCd", value = "점검주관부서", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "safCheckTypeCd", value = "점검종류", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "safFacilityTypeCd", value = "설비유형", required = false, dataType = "array", paramType = "query"), @ApiImplicitParam(name = "facilityNm", value = "설비명", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "checkStepCd", value = "진행단계", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "safFacilityCheckNo", value = "설비점검마스터번호", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
    @GetMapping("rfacilitycheckresults")
    public ResponseEntity<List<FacilityCheckSchedule>> getrFacilityCheckResults(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        // 기간
        String[] period = this.requestMapper.convertObjectListAsStringArray(map.get("period"));
        String startYmd = "";
        String endYmd = "";
        if (period != null && period.length == 2) {
            startYmd = period[0];
            endYmd = period[1];
        } else {
            startYmd = map.containsKey("startYmd") ? map.get("startYmd").toString() : "";
            endYmd = map.containsKey("endYmd") ? map.get("endYmd").toString() : "";
        }
        // 사업장
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 점검주관부서
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";
        // 점검종류
        String safCheckTypeCd = map.containsKey("safCheckTypeCd") ? map.get("safCheckTypeCd").toString() : "";
        // 설비유형
        String safFacilityTypeCd = map.containsKey("safFacilityTypeCd") ? map.get("safFacilityTypeCd").toString() : "";
        // 설비명
        String facilityNm = map.containsKey("facilityNm") ? map.get("facilityNm").toString() : "";
        // 진행단계
        String checkStepCd = map.containsKey("checkStepCd") ? map.get("checkStepCd").toString() : "";
        // 설비점검마스터번호
        int safFacilityCheckNo = map.containsKey("safFacilityCheckNo") ? Integer.parseInt("".equals(map.get("safFacilityCheckNo").toString()) ? "0" : map.get("safFacilityCheckNo").toString()) : 0;

        return ResponseEntity.ok().body(facilityCheckResultService.getrFacilityCheckResults(startYmd, endYmd, plantCd, deptCd, safCheckTypeCd, safFacilityTypeCd, facilityNm, checkStepCd, safFacilityCheckNo, defaultParam));
    }

    /**
     * 설비점검계획 수정
     *
     * @param facilityCheckSchedule
     *            설비점검계획
     * @return 변경 행 수
     * @throws Exception
     */
    @PutMapping("facilitycheckresult")
    public ResponseEntity<Integer> updateFacilityCheckResult(@RequestBody FacilityCheckSchedule facilityCheckSchedule, @ModelAttribute DefaultParam defaultParam) throws Exception {
        return ResponseEntity.ok().body(facilityCheckResultService.updateFacilityCheckResult(facilityCheckSchedule, defaultParam));
    }

    // ============================================================================================================
    // -----------------설비점검계획 start-------------------------------
    /**
     * 설비점검계획 목록 조회
     *
     * @param parameter
     *            검색조건
     * @return 설비점검 계획 목록
     */
    @GetMapping("/facilchkplans")
    public ResponseEntity<List<FacilChkMaster>> getFacilChkPlans(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        // 사업장
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 설비점검종류
        String safCheckTypeCd = map.containsKey("safCheckTypeCd") ? map.get("safCheckTypeCd").toString() : "";
        // 점검상태
        String checkStepCd = map.containsKey("checkStepCd") ? map.get("checkStepCd").toString() : "";
        // 점검주관부서
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";
        // 하위부서 포함여부
        String deptSubYn = map.containsKey("deptSubYn") ? map.get("deptSubYn").toString() : "Y";

        // 점검대상부서
        String tDeptCd = map.containsKey("tDeptCd") ? map.get("tDeptCd").toString() : "";
        // 하위부서 포함여부
        String tDeptSubYn = map.containsKey("tDeptSubYn") ? map.get("tDeptSubYn").toString() : "Y";

        // 점검수행부서
        String pDeptCd = map.containsKey("pDeptCd") ? map.get("pDeptCd").toString() : "";
        // 하위부서 포함여부
        String pDeptSubYn = map.containsKey("pDeptSubYn") ? map.get("pDeptSubYn").toString() : "Y";

        // 설비명
        String facilityNm = map.containsKey("facilityNm") ? map.get("facilityNm").toString() : "";

        // 완료여부
        String chkEndYn = map.containsKey("chkEndYn") ? map.get("chkEndYn").toString() : "";

        // 기간
        String[] period = this.requestMapper.convertObjectListAsStringArray(map.get("period"));
        String startYmd = "";
        String endYmd = "";
        if (period != null && period.length == 2) {
            startYmd = period[0];
            endYmd = period[1];
        }
        // 점검명
        String keyword = map.containsKey("keyword") ? map.get("keyword").toString() : "";

        return ResponseEntity.ok().body(facilityCheckResultService.getFacilChkPlans(plantCd, safCheckTypeCd, checkStepCd, deptCd, deptSubYn, startYmd, endYmd, keyword, tDeptCd, tDeptSubYn, pDeptCd, pDeptSubYn, chkEndYn, facilityNm, defaultParam));
    }

    /**
     * 설비점검계획 상세 조회
     *
     * @param parameter
     *            검색조건
     * @return 설비점검 계획 상세
     */
    @GetMapping("/facilchkplan/{safFacilChkNo}")
    public ResponseEntity<FacilChkMaster> getFacilChkPlan(@PathVariable("safFacilChkNo") int safFacilChkNo, @RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 점검대상부서
        String tDeptCd = map.containsKey("tDeptCd") ? map.get("tDeptCd").toString() : "";

        // 점검수행부서
        String pDeptCd = map.containsKey("pDeptCd") ? map.get("pDeptCd").toString() : "";

        return ResponseEntity.ok().body(facilityCheckResultService.getFacilChkPlan(safFacilChkNo, tDeptCd, pDeptCd, defaultParam));
    }

    /**
     * 설비점검 계획 등록
     *
     * @param facilChkMaster
     *            설비점검 계획
     * @return 설비점검번호
     * @throws Exception
     */
    @PostMapping("/facilchkplan")
    public ResponseEntity<Integer> createFacilChkPlan(@RequestBody FacilChkMaster facilChkMaster) throws Exception {
        return ResponseEntity.ok().body(facilityCheckResultService.createFacilChkPlan(facilChkMaster) > 0 ? facilChkMaster.getSafFacilChkNo() : 0);
    }

    /**
     * 설비점검 계획 수정
     *
     * @param facilChkMaster
     *            설비점검 계획
     * @return 설비점검번호
     * @throws Exception
     */
    @PutMapping("/facilchkplan")
    public ResponseEntity<Integer> updateFacilChkPlan(@RequestBody FacilChkMaster facilChkMaster) throws Exception {
        return ResponseEntity.ok().body(facilityCheckResultService.updateFacilChkPlan(facilChkMaster) > 0 ? facilChkMaster.getSafFacilChkNo() : 0);
    }

    /**
     * 설비점검 계획 삭제
     *
     * @param facilChkMaster
     *            설비점검 계획
     * @return 삭제 행 수
     * @throws Exception
     */
    @DeleteMapping("/facilchkplan/{safFacilChkNo}")
    public ResponseEntity<Integer> deleteFacilChkPlan(@PathVariable("safFacilChkNo") int safFacilChkNo) throws Exception {
        return ResponseEntity.ok().body(facilityCheckResultService.deleteFacilChkPlan(safFacilChkNo));
    }

    // -----------------설비점검계획 end-------------------------------
    // -----------------설비점검결과 start-------------------------------
    /**
     * 설비점검결과 목록 조회
     *
     * @param parameter
     *            검색조건
     * @return 설비점검 계획 목록
     */
    @GetMapping("/rfacilchkplans")
    public ResponseEntity<List<FacilChkMaster>> getRFacilChkPlans(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        // 사업장
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 설비점검종류
        String safCheckTypeCd = map.containsKey("safCheckTypeCd") ? map.get("safCheckTypeCd").toString() : "";
        // 점검상태
        String checkStepCd = map.containsKey("checkStepCd") ? map.get("checkStepCd").toString() : "";
        // 점검주관부서
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";
        // 하위부서 포함여부
        String deptSubYn = map.containsKey("deptSubYn") ? map.get("deptSubYn").toString() : "Y";

        // 점검대상부서
        String tDeptCd = map.containsKey("tDeptCd") ? map.get("tDeptCd").toString() : "";
        // 하위부서 포함여부
        String tDeptSubYn = map.containsKey("tDeptSubYn") ? map.get("tDeptSubYn").toString() : "Y";

        // 점검수행부서
        String pDeptCd = map.containsKey("pDeptCd") ? map.get("pDeptCd").toString() : "";
        // 하위부서 포함여부
        String pDeptSubYn = map.containsKey("pDeptSubYn") ? map.get("pDeptSubYn").toString() : "Y";

        // 설비명
        String facilityNm = map.containsKey("facilityNm") ? map.get("facilityNm").toString() : "";

        // 완료여부
        String chkEndYn = map.containsKey("chkEndYn") ? map.get("chkEndYn").toString() : "";

        // 기간
        String[] period = this.requestMapper.convertObjectListAsStringArray(map.get("period"));
        String startYmd = "";
        String endYmd = "";
        if (period != null && period.length == 2) {
            startYmd = period[0];
            endYmd = period[1];
        } else {
            startYmd = map.containsKey("startYmd") ? map.get("startYmd").toString() : "";
            endYmd = map.containsKey("endYmd") ? map.get("endYmd").toString() : "";
        }
        // 점검명
        String keyword = map.containsKey("keyword") ? map.get("keyword").toString() : "";

        return ResponseEntity.ok().body(facilityCheckResultService.getRFacilChkPlans(plantCd, safCheckTypeCd, checkStepCd, deptCd, deptSubYn, startYmd, endYmd, keyword, tDeptCd, tDeptSubYn, pDeptCd, pDeptSubYn, chkEndYn, facilityNm, defaultParam));
    }

    /**
     * 설비점검결과 목록 조회
     *
     * @param safFacilChkNo
     *            설비점검번호
     * @return 설비점검 결과 목록
     * @throws Exception
     */
    @GetMapping("/facilchkresults")
    public ResponseEntity<List<FacilChkResult>> getFacilChkResults(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        int safFacilChkNo = map.containsKey("safFacilChkNo") ? Integer.parseInt(map.get("safFacilChkNo").toString()) : 0;
        // 점검대상부서
        String tDeptCd = map.containsKey("tDeptCd") ? map.get("tDeptCd").toString() : "";
        // 점검수행부서
        String pDeptCd = map.containsKey("pDeptCd") ? map.get("pDeptCd").toString() : "";
        // 점검 설비코드
        String safFacilityCd = map.containsKey("safFacilityCd") ? map.get("safFacilityCd").toString() : "";
        return ResponseEntity.ok().body(facilityCheckResultService.getFacilChkResults(safFacilChkNo, tDeptCd, pDeptCd, safFacilityCd));
    }

    /**
     * 설비점검결과 상세 조회
     *
     * @param safFacilChkNo
     *            설비점검번호
     * @param safFacilityCd
     *            설비코드
     * @return 설비점검결과 상세 정보
     * @throws Exception
     */
    @GetMapping("/facilchkresult")
    public ResponseEntity<FacilChkResult> getFacilChkResult(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        int safFacilChkNo = map.containsKey("safFacilChkNo") ? Integer.parseInt(map.get("safFacilChkNo").toString()) : 0;
        String safFacilityCd = map.containsKey("safFacilityCd") ? map.get("safFacilityCd").toString() : "";
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        String safCheckTypeCd = map.containsKey("safCheckTypeCd") ? map.get("safCheckTypeCd").toString() : "";

        return ResponseEntity.ok().body(facilityCheckResultService.getFacilChkResult(safFacilChkNo, safFacilityCd, plantCd, safCheckTypeCd, defaultParam));
    }

    /**
     * 설비별 점검결과 수정
     *
     * @param safFacilChkResult
     *            설비점검결과
     * @return 수정 행 수
     * @throws Exception
     */
    @PutMapping("/facilchkresult")
    public ResponseEntity<Integer> updateFacilChkResult(@RequestBody FacilChkResult facilChkResult) throws Exception {
        return ResponseEntity.ok().body(facilityCheckResultService.updateFacilChkResult(facilChkResult));
    }

    /**
     * 설비별 점검결과 전체완료
     *
     * @param facilChkMaster
     *            설비점검계획
     * @return 수정 행 수
     * @throws Exception
     */
    @PutMapping("/facilchkplan/complete")
    public ResponseEntity<Integer> updateFacilChkResults(@RequestBody FacilChkMaster facilChkMaster) throws Exception {
        return ResponseEntity.ok().body(facilityCheckResultService.updateFacilChkResults(facilChkMaster));
    }

    /**
     * 설비점검 사업장별 실적집계
     *
     * @param parameter
     *            검색조건
     * @return 설비점검 사업장별 실적집계 목록
     */
    @GetMapping("/facilchkresultstatus")
    public ResponseEntity<List<HashMap<String, Object>>> getFacilChkResultStatus(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        // 사업장명
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 점검년도
        String year = map.containsKey("year") ? map.get("year").toString() : "";

        List<HashMap<String, Object>> data = facilityCheckResultService.getFacilChkResultStatus(plantCd, year);
        return ResponseEntity.ok().body(data);
    }

    /**
     * 설비점검 사업장별 실적 세부집계
     *
     * @param parameter
     *            검색조건
     * @return 설비점검 사업장별 실적 세부집계 목록
     */
    @GetMapping("/facilchkresultstatussub")
    public ResponseEntity<List<HashMap<String, Object>>> getFacilChkResultStatusSub(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        // 사업장명
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 점검년도
        String year = map.containsKey("year") ? map.get("year").toString() : "";
        // 점검종류
        String safCheckTypeCd = map.containsKey("safCheckTypeCd") ? map.get("safCheckTypeCd").toString() : "";
        // 설비유형
        String safFacilityTypeCd = map.containsKey("safFacilityTypeCd") ? map.get("safFacilityTypeCd").toString() : "";
        // 주관부서
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";
        // 하위부서 포함여부
        String deptSubYn = map.containsKey("deptSubYn") ? map.get("deptSubYn").toString() : "Y";

        return ResponseEntity.ok().body(facilityCheckResultService.getFacilChkResultStatusSub(plantCd, year, safCheckTypeCd, safFacilityTypeCd, deptCd, deptSubYn, defaultParam));
    }

    /**
     * 설비점검결과 목록 조회
     *
     * @return 설비점검 결과 목록
     * @throws Exception
     */
    @GetMapping("/facilchkresulttable")
    public ResponseEntity<List<FacilChkResult>> getFacilChkResultTable(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        // 사업장명
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 검색 시작일
        String startDate = map.containsKey("startDate") ? map.get("startDate").toString() : "";
        // 검색 종료일
        String endDate = map.containsKey("endDate") ? map.get("endDate").toString() : "";
        // 완료여부
        String chkEndYn = map.containsKey("chkEndYn") ? map.get("chkEndYn").toString() : "";
        // 완료여부
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";
        // 완료여부
        String safCheckTypeCd = map.containsKey("safCheckTypeCd") ? map.get("safCheckTypeCd").toString() : "";
        // 완료여부
        String safFacilityTypeCd = map.containsKey("safFacilityTypeCd") ? map.get("safFacilityTypeCd").toString() : "";
        // 점검진행상태
        String chkStepCd = map.containsKey("chkStepCd") ? map.get("chkStepCd").toString() : "";

        return ResponseEntity.ok().body(facilityCheckResultService.getFacilChkResultTable(plantCd, startDate, endDate, chkEndYn, deptCd, safCheckTypeCd, safFacilityTypeCd, chkStepCd));
    }
    // -----------------설비점검결과 end-------------------------------
}
