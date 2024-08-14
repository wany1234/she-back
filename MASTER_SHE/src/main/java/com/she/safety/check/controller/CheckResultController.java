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
package com.she.safety.check.controller;

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
import com.she.safety.check.service.CheckResultService;
import com.she.safety.model.CheckInspector;
import com.she.safety.model.CheckItemResult;
import com.she.safety.model.CheckMaster;
import com.she.safety.model.CheckSchedule;
import com.she.utils.RequestMapper;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 안전점검결과.
 */
@RestController
@RequestMapping("/api/saf/check")
public class CheckResultController {
    /**
     * request mapper.
     */
    @Autowired
    private RequestMapper requestMapper;

    /**
     * 안전점검결과 service.
     */
    @Autowired
    private CheckResultService checkResultService;

    /**
     * 안전점검일정 목록 조회.
     *
     * @param parameter
     *            검색조건
     * @return 안전점검일정 목록
     * @throws Exception
     */
    @GetMapping("/checkschedulelist")
    public ResponseEntity<List<CheckMaster>> getCheckScheduleList(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        // From
        String startDate = map.containsKey("startDate") ? map.get("startDate").toString() : "";
        // To
        String endDate = map.containsKey("endDate") ? map.get("endDate").toString() : "";
        // 대상부서코드
        String tgtDeptCd = map.containsKey("tgtDeptCd") ? map.get("tgtDeptCd").toString() : "";
        // 하위부서 포함여부
        String tgtDeptSubYn = map.containsKey("tgtDeptSubYn") ? map.get("tgtDeptSubYn").toString() : "Y";
        // 주관부서코드
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";
        // 하위부서 포함여부
        String deptSubYn = map.containsKey("deptSubYn") ? map.get("deptSubYn").toString() : "Y";
        // 수행부서코드
        String pfmDeptCd = map.containsKey("pfmDeptCd") ? map.get("pfmDeptCd").toString() : "";
        // 하위수행부서 포함여부
        String pfmDeptSubYn = map.containsKey("pfmDeptSubYn") ? map.get("pfmDeptSubYn").toString() : "Y";
        // 점검종류번호
        int safCheckKindNo = map.containsKey("safCheckKindNo") ? Integer.parseInt("".equals(map.get("safCheckKindNo").toString()) ? "0" : map.get("safCheckKindNo").toString()) : 0;
        // 사업장 코드
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 점검상태
        String stepStatus = map.containsKey("stepStatus") ? map.get("stepStatus").toString() : "";
        // 점검(일정)명
        String keyword = map.containsKey("keyword") ? map.get("keyword").toString() : "";
        // 점검종류코드 포함여부
        String chngKind = map.containsKey("chngKind") ? map.get("chngKind").toString() : "Y";
        // 협력업체코드 포함여부
        String vendorCd = map.containsKey("vendorCd") ? map.get("vendorCd").toString() : "Y";

        List<CheckMaster> checkResults = checkResultService.getCheckScheduleList(startDate, endDate, tgtDeptCd, tgtDeptSubYn, deptCd, deptSubYn, pfmDeptCd, pfmDeptSubYn,safCheckKindNo, plantCd, stepStatus, keyword, defaultParam, chngKind, vendorCd);
        return ResponseEntity.ok().body(checkResults);
    }

    /**
     * 안전점검일정 등록.
     *
     * @param checkMaster
     * @return safCheckRsltNo
     * @throws Exception
     */
    @PostMapping("/checkschedule")
    public ResponseEntity<Integer> createCheckSchedule(@RequestBody CheckMaster checkMaster) throws Exception {
        return ResponseEntity.ok().body(checkResultService.createCheckSchedule(checkMaster));
    }

    /**
     * 안전점검일정 상세조회
     *
     * @param safCheckNo
     * @return
     * @throws Exception
     */
    @GetMapping("/checkschedule/{safCheckNo}")
    public ResponseEntity<CheckMaster> getCheckScheduleDetail(@PathVariable("safCheckNo") int safCheckNo, @ModelAttribute DefaultParam defaultParam) throws Exception {
        return ResponseEntity.ok().body(checkResultService.getCheckScheduleDetail(safCheckNo, defaultParam));
    }

    /**
     * 안전점검일정 수정
     *
     * @param checkMaster
     * @return
     * @throws Exception
     */
    @PutMapping("/checkschedule")
    public ResponseEntity<Integer> updateCheckSchedule(@RequestBody CheckMaster checkMaster) throws Exception {
        return ResponseEntity.ok().body(checkResultService.updateCheckSchedule(checkMaster));
    }

    /**
     * 안전점검일정 완료
     *
     * @param checkMaster
     * @return
     * @throws Exception
     */
    @PutMapping("/completecheckschedule")
    public ResponseEntity<Integer> completeCheckSchedule(@RequestBody CheckMaster checkMaster) throws Exception {
        return ResponseEntity.ok().body(checkResultService.completeCheckSchedule(checkMaster));
    }

    /**
     * 안전점검일정 삭제
     *
     * @param safCheckNo
     * @return
     * @throws Exception
     */
    @DeleteMapping("/checkschedule/{safCheckNo}")
    public ResponseEntity<Integer> deleteCheckSchedule(@PathVariable("safCheckNo") int safCheckNo) throws Exception {
        return ResponseEntity.ok().body(checkResultService.deleteCheckSchedule(safCheckNo));
    }

    /**
     * 안전점검계획 목록 조회.
     *
     * @param parameter
     *            검색조건
     * @return 안전점검일정 목록
     * @throws Exception
     */
    @GetMapping("/checkplanlist")
    public ResponseEntity<List<CheckSchedule>> getCheckPlanList(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        // From
        String startDate = map.containsKey("startDate") ? map.get("startDate").toString() : "";
        // To
        String endDate = map.containsKey("endDate") ? map.get("endDate").toString() : "";
        // 대상부서코드
        String tgtDeptCd = map.containsKey("tgtDeptCd") ? map.get("tgtDeptCd").toString() : "";
        // 하위부서 포함여부
        String tgtDeptSubYn = map.containsKey("tgtDeptSubYn") ? map.get("tgtDeptSubYn").toString() : "Y";

        // 수행부서코드
        String pfmDeptCd = map.containsKey("pfmDeptCd") ? map.get("pfmDeptCd").toString() : "";
        // 하위부서 포함여부
        String pfmDeptSubYn = map.containsKey("pfmDeptSubYn") ? map.get("pfmDeptSubYn").toString() : "Y";

        // 주관부서코드
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";
        // 하위부서 포함여부
        String deptSubYn = map.containsKey("deptSubYn") ? map.get("deptSubYn").toString() : "Y";

        // 점검종류번호
        int safCheckKindNo = map.containsKey("safCheckKindNo") ? Integer.parseInt("".equals(map.get("safCheckKindNo").toString()) ? "0" : map.get("safCheckKindNo").toString()) : 0;
        // 사업장 코드
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 점검진행상태
        String checkStepCd = map.containsKey("checkStepCd") ? map.get("checkStepCd").toString() : "";
        // 검색어
        String keyword = map.containsKey("keyword") ? map.get("keyword").toString() : "";

        return ResponseEntity.ok().body(checkResultService.getCheckPlanList(startDate, endDate, tgtDeptCd, tgtDeptSubYn, pfmDeptCd, pfmDeptSubYn, deptCd, deptSubYn, safCheckKindNo, plantCd, checkStepCd, keyword, defaultParam));
    }

    /**
     * 안전점검 계획, 결과 상세 조회
     *
     * @param safCheckScheduleNo
     * @return
     * @throws Exception
     */
    @GetMapping("/checkplan/{safCheckScheduleNo}")
    public ResponseEntity<CheckSchedule> getCheckScheduleInfo(@PathVariable("safCheckScheduleNo") int safCheckScheduleNo, @ModelAttribute DefaultParam defaultParam) throws Exception {
        return ResponseEntity.ok().body(checkResultService.getCheckScheduleInfo(safCheckScheduleNo, defaultParam));
    }

    /**
     * 안전점검 결과 목록 조회.
     *
     * @param parameter
     *            검색조건
     * @return 안전점검결과 목록
     * @throws Exception
     */
    @GetMapping("/checkresults")
    public ResponseEntity<List<CheckSchedule>> getCheckResults(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        // From
        String startDate = map.containsKey("startDate") ? map.get("startDate").toString() : "";
        // To
        String endDate = map.containsKey("endDate") ? map.get("endDate").toString() : "";
        // 대상부서코드
        String tgtDeptCd = map.containsKey("tgtDeptCd") ? map.get("tgtDeptCd").toString() : "";
        // 하위부서 포함여부
        String tgtDeptSubYn = map.containsKey("tgtDeptSubYn") ? map.get("tgtDeptSubYn").toString() : "N";

        // 주관부서코드
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";
        // 하위부서 포함여부
        String deptSubYn = map.containsKey("deptSubYn") ? map.get("deptSubYn").toString() : "N";

        // 수행부서코드
        String pfmDeptCd = map.containsKey("pfmDeptCd") ? map.get("pfmDeptCd").toString() : "";
        // 하위부서 포함여부
        String pfmDeptSubYn = map.containsKey("pfmDeptSubYn") ? map.get("pfmDeptSubYn").toString() : "N";

        // 점검종류번호
        int safCheckKindNo = map.containsKey("safCheckKindNo") ? Integer.parseInt("".equals(map.get("safCheckKindNo").toString()) ? "0" : map.get("safCheckKindNo").toString()) : 0;
        // 사업장 코드
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 점검진행상태
        String checkStepCd = map.containsKey("checkStepCd") ? map.get("checkStepCd").toString() : "";
        // 점검결과
        String checkResultCd = map.containsKey("checkResultCd") ? map.get("checkResultCd").toString() : "";
        // 검색어
        String keyword = map.containsKey("keyword") ? map.get("keyword").toString() : "";

        return ResponseEntity.ok().body(checkResultService.getCheckResultList(startDate, endDate, checkResultCd, tgtDeptCd, tgtDeptSubYn, pfmDeptCd, pfmDeptSubYn, deptCd, deptSubYn, safCheckKindNo, plantCd, checkStepCd, keyword, defaultParam));
    }

    /**
     * 안전점검 점검자 목록 조회
     *
     * @param parameter
     *            검색조건
     * @return 안전점검 점검자 목록
     */
    @GetMapping("/checkinspectors")
    public ResponseEntity<List<CheckInspector>> getCheckInspectors(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        // 안전점검일정 일련번호
        int safCheckScheduleNo = map.containsKey("safCheckScheduleNo") ? Integer.parseInt(map.get("safCheckScheduleNo").toString()) : 0;
        // 점검자구분코드
        String inspectorClassCd = map.containsKey("inspectorClassCd") ? map.get("inspectorClassCd").toString() : "";

        List<CheckInspector> checkInspectors = checkResultService.getCheckInspectors(safCheckScheduleNo, inspectorClassCd, defaultParam);
        return ResponseEntity.ok().body(checkInspectors);
    }

    /**
     * 점검예정일별 점검계획 등록.
     *
     * @param checkSchedule
     * @return
     * @throws Exception
     */
    @PostMapping("/checkplan")
    public ResponseEntity<Integer> createCheckPlan(@RequestBody CheckSchedule checkSchedule) throws Exception {
        return ResponseEntity.ok().body(checkResultService.createCheckPlan(checkSchedule));
    }

    /**
     * 점검예정일별 점검계획 수정
     *
     * @param checkSchedule
     * @return
     * @throws Exception
     */
    @PutMapping("/checkplan")
    public ResponseEntity<Integer> updateCheckPlan(@RequestBody CheckSchedule checkSchedule) throws Exception {
        return ResponseEntity.ok().body(checkResultService.updateCheckPlan(checkSchedule));
    }

    /**
     * 점검예정일별 점검계획 완료
     *
     * @param checkSchedule
     * @return
     * @throws Exception
     */
    @PutMapping("/completecheckplan")
    public ResponseEntity<Integer> completeCheckPlan(@RequestBody CheckSchedule checkSchedule) throws Exception {
        return ResponseEntity.ok().body(checkResultService.completeCheckPlan(checkSchedule));
    }

    /**
     * 점검예정일별 점검계획 삭제
     *
     * @param checkResult
     *            안전점검결과
     * @return 변경 행 수
     * @throws Exception
     */
    @DeleteMapping("/checkplan/{safCheckNo}/{safCheckScheduleNo}")
    public ResponseEntity<Integer> deleteCheckPlan(@PathVariable("safCheckNo") int safCheckNo, @PathVariable("safCheckScheduleNo") int safCheckScheduleNo) throws Exception {
        return ResponseEntity.ok().body(checkResultService.deleteCheckPlan(safCheckNo, safCheckScheduleNo));
    }

    /**
     * 안전점검 항목별점검결과 조회
     *
     * @param parameter
     *            검색조건
     * @return 안전점검 항목별점검결과 목록
     */
    @GetMapping("/checkitemresults")
    public ResponseEntity<List<CheckItemResult>> getCheckItemResults(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        // 안전점검일정 일련번호
        int safCheckScheduleNo = map.containsKey("safCheckScheduleNo") ? Integer.parseInt("".equals(map.get("safCheckScheduleNo").toString()) ? "0" : map.get("safCheckScheduleNo").toString()) : 0;
        // 안전점검 마스터 일련번호
        int safCheckNo = map.containsKey("safCheckNo") ? Integer.parseInt("".equals(map.get("safCheckNo").toString()) ? "0" : map.get("safCheckNo").toString()) : 0;
        // 안전설비유형코드
        int safCheckKindNo = map.containsKey("safCheckKindNo") ? Integer.parseInt("".equals(map.get("safCheckKindNo").toString()) ? "0" : map.get("safCheckKindNo").toString()) : 0;

        List<CheckItemResult> checkItemResults = checkResultService.getCheckItemResults(safCheckScheduleNo, safCheckNo, safCheckKindNo);
        return ResponseEntity.ok().body(checkItemResults);
    }

    /**
     * 안전점검결과 수정
     *
     * @param checkResult
     * @return
     * @throws Exception
     */
    @PutMapping("/checkresult")
    public ResponseEntity<CheckSchedule> updateCheckResult(@RequestBody CheckSchedule checkSchedule) throws Exception {
        return ResponseEntity.ok().body(checkResultService.updateCheckResult(checkSchedule));
    }

    /**
     * 안전점검결과 완료
     *
     * @param checkSchedule
     * @return
     * @throws Exception
     */
    @PutMapping("/completecheckresult")
    public ResponseEntity<Integer> completeCheckResult(@RequestBody CheckSchedule checkSchedule) throws Exception {
        return ResponseEntity.ok().body(checkResultService.completeSafetyCheckResult(checkSchedule));
    }

    /**
     * 안전점검 무계획결과 등록
     *
     * @param checkSchedule
     * @return
     * @throws Exception
     */
    @PostMapping("/checkresult")
    public ResponseEntity<CheckSchedule> createCheckResult(@RequestBody CheckSchedule checkSchedule) throws Exception {
        return ResponseEntity.ok().body(checkResultService.createCheckResult(checkSchedule));
    }

    /**
     * 안전점검결과 삭제
     *
     * @param safCheckNo
     *            안전점검 마스터 일련번호
     * @param safCheckScheduleNo
     *            안전점검일정일련번호
     * @return 변경 행 수
     * @throws Exception
     */
    @DeleteMapping("/checkresult/{safCheckNo}/{safCheckScheduleNo}")
    public ResponseEntity<Integer> deleteCheckResult(@PathVariable("safCheckNo") int safCheckNo, @PathVariable("safCheckScheduleNo") int safCheckScheduleNo) throws Exception {
        return ResponseEntity.ok().body(checkResultService.deleteCheckResult(safCheckNo, safCheckScheduleNo));
    }

    /**
     * 안전점검 사업장별 실적집계
     *
     * @param parameter
     *            검색조건
     * @return 안전점검 사업장별 실적집계 목록
     */
    @ApiOperation(value = "안전점검 사업장별 실적집계[SAF90020]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "plantCd", value = "사업장", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "year", value = "점검년도", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
    @GetMapping("checkresultstatus")
    public ResponseEntity<List<HashMap<String, Object>>> getCheckResultStatus(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        // 사업장명
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 점검년도
        String year = map.containsKey("year") ? map.get("year").toString() : "";

        return ResponseEntity.ok().body(checkResultService.getCheckResultStatus(plantCd, year));
    }

    /**
     * 안전점검 사업장별 실적 세부집계
     *
     * @param parameter
     *            검색조건
     * @return 안전점검 사업장별 실적 세부집계 목록
     */
    @ApiOperation(value = "안전점검 사업장별 실적 세부집계[SAF90021]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "plantCd", value = "사업장", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "year", value = "점검년도", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "safCheckKindNo", value = "점검종류", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "deptCd", value = "주관부서", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "tgtDeptCd", value = "대상부서", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
    @GetMapping("checkresultstatussub")
    public ResponseEntity<List<HashMap<String, Object>>> getCheckResultStatusSub(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        // 사업장명
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 점검년도
        String year = map.containsKey("year") ? map.get("year").toString() : "";
        // 점검종류
        int safCheckKindNo = map.containsKey("safCheckKindNo") ? Integer.parseInt("".equals(map.get("safCheckKindNo").toString()) ? "0" : map.get("safCheckKindNo").toString()) : 0;
        // 점검부서
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";
        // 하위부서 포함여부
        String deptSubYn = map.containsKey("deptSubYn") ? map.get("deptSubYn").toString() : "Y";

        // 대상부서
        String tgtDeptCd = map.containsKey("tgtDeptCd") ? map.get("tgtDeptCd").toString() : "";
        // 하위부서 포함여부
        String tgtDeptSubYn = map.containsKey("tgtDeptSubYn") ? map.get("tgtDeptSubYn").toString() : "Y";

        return ResponseEntity.ok().body(checkResultService.getCheckResultStatusSub(plantCd, year, safCheckKindNo, deptCd, deptSubYn, tgtDeptCd, tgtDeptSubYn));
    }

}
