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
package com.she.safety.facilityInspection.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.she.safety.facilityInspection.service.FacilityInspectionService;
import com.she.safety.model.FacilityInspectionInspector;
import com.she.safety.model.FacilityInspectionItemResult;
import com.she.safety.model.FacilityInspectionMaster;
import com.she.safety.model.FacilityInspectionSchedule;
import com.she.utils.RequestMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 설비점검결과
 */
@RestController
@RequestMapping("/api/saf/facilityinspection/")
@Api(value = "/api/saf/facilityinspection/", description = "시설점검 서비스")
public class FacilityInspectionController {
    // TODO : queryString 변환을 위한 mapper 선언
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private FacilityInspectionService facilityInspectionService;

    /**
     * 시설점검일정 조회
     * 
     * @param parameter
     *            검색조건
     * @return 시설점검일정 목록
     */
    @ApiOperation(value = "시설점검일정 조회", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "period", value = "기간", required = false, dataType = "array", paramType = "query"), @ApiImplicitParam(name = "plantCd", value = "사업장", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "deptCd", value = "점검부서", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "comFacilityCheckCd", value = "점검종류", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "comFacilityTypeCd", value = "시설유형", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "facilityNm", value = "시설명", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "stepStatus", value = "진행단계", required = false, dataType = "string", paramType = "query"), })
    // @ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false,
    // dataType = "java.util.HashMap", paramType = "query"), })
    @GetMapping("facilityinspectionschedules")
    public ResponseEntity<List<FacilityInspectionMaster>> getFacilityInspectionSchedules(@RequestParam HashMap<String, Object> parameter) throws Exception {
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

        // 점검대상부서
        String tgtDeptCd = map.containsKey("tgtDeptCd") ? map.get("tgtDeptCd").toString() : "";

        // 점검수행부서
        String pfmDeptCd = map.containsKey("pfmDeptCd") ? map.get("pfmDeptCd").toString() : "";

        // 하위부서 포함여부
        String deptSubYn = map.containsKey("deptSubYn") ? map.get("deptSubYn").toString() : "Y";

        // 하위대상부서 포함여부
        String tgtDeptSubYn = map.containsKey("tgtDeptSubYn") ? map.get("tgtDeptSubYn").toString() : "Y";

        // 하위수행부서 포함여부
        String pfmDeptSubYn = map.containsKey("pfmDeptSubYn") ? map.get("pfmDeptSubYn").toString() : "Y";

        // 점검종류
        String comFacilityCheckCd = map.containsKey("comFacilityCheckCd") ? map.get("comFacilityCheckCd").toString() : "";
        // 시설유형
        String comFacilityTypeCd = map.containsKey("comFacilityTypeCd") ? map.get("comFacilityTypeCd").toString() : "";
        // 점검상태
        String facilityNm = map.containsKey("facilityNm") ? map.get("facilityNm").toString() : "";
        // 점검상태
        String stepStatus = map.containsKey("stepStatus") ? map.get("stepStatus").toString() : "";

        return ResponseEntity.ok().body(facilityInspectionService.getFacilityInspectionSchedules(startYmd, endYmd, plantCd, deptCd, tgtDeptCd, pfmDeptCd, deptSubYn, tgtDeptSubYn, pfmDeptSubYn, comFacilityCheckCd, comFacilityTypeCd, facilityNm, stepStatus));
    }

    /**
     * 시설 유형 셀렉박스 리스트 조회
     * 
     * @return 시설 유형 리스트
     * @throws Exception
     */
    @ApiOperation(value = "시설 유형 셀렉박스 리스트 조회", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @GetMapping("comfacilitytypes")
    public ResponseEntity<List<Map<String, String>>> getComFacilityTypes(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        // 시설유형코드
        String facilityCd = map.containsKey("facilityCd") ? map.get("facilityCd").toString() : "";
        // 시설유형명
        String facilityNm = map.containsKey("facilityNm") ? map.get("facilityNm").toString() : "";
        // 사용여부
        String useYn = map.containsKey("useYn") ? map.get("useYn").toString() : "";
        return ResponseEntity.ok().body(facilityInspectionService.getComFacilityTypes(facilityCd, facilityNm, useYn));
    }

    /**
     * 시설점검마스터 일정 상세 조회
     * 
     * @param safFacilityCheckNo
     *            시설점검일련번호
     * @return 시설점검일정
     */
    @ApiOperation(value = "시설점검 일정 상세조회", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "comFacilityCheckNo", value = "시설점검일련번호", required = false, dataType = "int", paramType = "path") })
    @GetMapping("facilityinspectionschedule/{comFacilityCheckNo}")
    public ResponseEntity<FacilityInspectionMaster> getFacilityCheckSchedule(@PathVariable("comFacilityCheckNo") int comFacilityCheckNo) throws Exception {
        return ResponseEntity.ok().body(facilityInspectionService.getFacilityInspectionSchedule(comFacilityCheckNo));
    }

    /**
     * 시설점검일정 등록
     * 
     * @param facilityInspectionMaster
     *            시설점검일정마스터
     * @return 키
     */
    @ApiOperation(value = "시설점검일정 등록", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "facilityInspectionMaster", value = "시설점검일정마스터", required = false, dataType = "FacilityInspectionMaster", paramType = "body") })
    @PostMapping("facilityinspectionschedule")
    public ResponseEntity<Integer> createFacilityCheckSchedule(@RequestBody FacilityInspectionMaster facilityInspectionMaster) throws Exception {
        return ResponseEntity.ok().body(facilityInspectionService.createFacilityInspectionSchedule(facilityInspectionMaster) > 0 ? facilityInspectionMaster.getComFacilityCheckNo() : 0);
    }

    /**
     * 시설점검일정 수정
     * 
     * @param facilityInspectionMaster
     *            시설점검일정마스터
     * @return 수정 행
     */

    @ApiOperation(value = "시설점검일정 수정", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "facilityInspectionMaster", value = "시설점검일정마스터", required = false, dataType = "FacilityInspectionMaster", paramType = "body") })
    @PutMapping("facilityinspectionschedule")
    public ResponseEntity<Integer> updateFacilityCheckSchedule(@RequestBody FacilityInspectionMaster facilityInspectionMaster) throws Exception {
        return ResponseEntity.ok().body(facilityInspectionService.updateFacilityInspectionSchedule(facilityInspectionMaster));
    }

    /**
     * 시설점검마스터 삭제
     * 
     * @param comFacilityCheckNo
     *            시설점검일련번호
     * @return 삭제 행 수
     * @throws Exception
     */
    @ApiOperation(value = "시설점검마스터 삭제", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "comFacilityCheckNo", value = "시설점검일련번호", required = false, dataType = "int", paramType = "path") })
    @DeleteMapping("facilityinspectionschedule/{comFacilityCheckNo}")
    public ResponseEntity<Integer> deleteFacilityCheckSchedule(@PathVariable("comFacilityCheckNo") int comFacilityCheckNo) throws Exception {
        return ResponseEntity.ok().body(facilityInspectionService.deleteFacilityCheckSchedule(comFacilityCheckNo));
    }

    /**
     * 시설점검계획 조회
     * 
     * @param parameter
     *            검색조건
     * @return 시설점검계획 목록
     */
    @ApiOperation(value = "시설점검계획 조회", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "plantCd", value = "사업장", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "period", value = "기간", required = false, dataType = "array", paramType = "query"),
            @ApiImplicitParam(name = "comFacilityCheckCd", value = "시설점검종류", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "comFacilityTypeCd", value = "시설유형", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "facilityNm", value = "시설명", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "checkStepCd", value = "진행단계", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "deptCd", value = "점검부서", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "comFacilityCheckNo", value = "시설점검마스터번호", required = false, dataType = "int", paramType = "query"), })
    @GetMapping("facilityinspectionplans")
    public ResponseEntity<List<FacilityInspectionSchedule>> getFacilityInspectionPlans(@RequestParam HashMap<String, Object> parameter) throws Exception {
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
        // 시설점검종류
        String comFacilityCheckCd = map.containsKey("comFacilityCheckCd") ? map.get("comFacilityCheckCd").toString() : "";
        // 시설유형
        String comFacilityTypeCd = map.containsKey("comFacilityTypeCd") ? map.get("comFacilityTypeCd").toString() : "";
        // 명
        String facilityNm = map.containsKey("facilityNm") ? map.get("facilityNm").toString() : "";
        // 진행단계
        String checkStepCd = map.containsKey("checkStepCd") ? map.get("checkStepCd").toString() : "";
        // 점검주관부서
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";
        // 점검대상부서
        String tgtDeptCd = map.containsKey("tgtDeptCd") ? map.get("tgtDeptCd").toString() : "";
        // 점검수헹부서
        String pfmDeptCd = map.containsKey("pfmDeptCd") ? map.get("pfmDeptCd").toString() : "";

        // 하위부서 포함여부
        String deptSubYn = map.containsKey("deptSubYn") ? map.get("deptSubYn").toString() : "Y";
        // 하위부서 포함여부
        String tgtDeptSubYn = map.containsKey("tgtDeptSubYn") ? map.get("tgtDeptSubYn").toString() : "Y";
        // 하위부서 포함여부
        String pfmDeptSubYn = map.containsKey("pfmDeptSubYn") ? map.get("pfmDeptSubYn").toString() : "Y";

        // 시설점검마스터번호
        int comFacilityCheckNo = map.containsKey("comFacilityCheckNo") ? Integer.parseInt("".equals(map.get("comFacilityCheckNo").toString()) ? "0" : map.get("comFacilityCheckNo").toString()) : 0;

        return ResponseEntity.ok().body(facilityInspectionService.getFacilityInspectionPlans(plantCd, startYmd, endYmd, comFacilityCheckCd, comFacilityTypeCd, facilityNm, checkStepCd, deptCd, tgtDeptCd, pfmDeptCd, deptSubYn, tgtDeptSubYn, pfmDeptSubYn, comFacilityCheckNo));

    }

    /**
     * 시설점검결과 조회
     * 
     * @param parameter
     *            검색조건
     * @return 시설점검결과 목록
     */
    @ApiOperation(value = "시설점검결과 조회", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "plantCd", value = "사업장", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "period", value = "기간", required = false, dataType = "array", paramType = "query"),
            @ApiImplicitParam(name = "comFacilityCheckCd", value = "시설점검종류", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "comFacilityTypeCd", value = "시설유형", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "facilityNm", value = "시설명", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "checkStepCd", value = "진행단계", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "deptCd", value = "점검부서", required = false, dataType = "string", paramType = "query"), })
    @GetMapping("facilityinspectionresults")
    public ResponseEntity<List<FacilityInspectionSchedule>> getFacilityInspectionResults(@RequestParam HashMap<String, Object> parameter) throws Exception {
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
        // 시설점검종류
        String comFacilityCheckCd = map.containsKey("comFacilityCheckCd") ? map.get("comFacilityCheckCd").toString() : "";
        // 시설유형
        String comFacilityTypeCd = map.containsKey("comFacilityTypeCd") ? map.get("comFacilityTypeCd").toString() : "";
        // 명
        String facilityNm = map.containsKey("facilityNm") ? map.get("facilityNm").toString() : "";
        // 진행단계
        String checkStepCd = map.containsKey("checkStepCd") ? map.get("checkStepCd").toString() : "";
        // 점검주관부서
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";

        // 점검대상부서
        String tgtDeptCd = map.containsKey("tgtDeptCd") ? map.get("tgtDeptCd").toString() : "";
        // 점검수행부서
        String pfmDeptCd = map.containsKey("pfmDeptCd") ? map.get("pfmDeptCd").toString() : "";
        // 점검결과코드
        String checkResultCd = map.containsKey("checkResultCd") ? map.get("checkResultCd").toString() : "";
        // 하위부서 포함여부
        String deptSubYn = map.containsKey("deptSubYn") ? map.get("deptSubYn").toString() : "Y";
        // 하위부서 포함여부
        String tgtDeptSubYn = map.containsKey("tgtDeptSubYn") ? map.get("tgtDeptSubYn").toString() : "Y";
        // 하위부서 포함여부
        String pfmDeptSubYn = map.containsKey("pfmDeptSubYn") ? map.get("pfmDeptSubYn").toString() : "Y";

        // 시설점검마스터번호
        int comFacilityCheckNo = map.containsKey("comFacilityCheckNo") ? Integer.parseInt("".equals(map.get("comFacilityCheckNo").toString()) ? "0" : map.get("comFacilityCheckNo").toString()) : 0;

        return ResponseEntity.ok().body(facilityInspectionService.getFacilityInspectionResults(plantCd, startYmd, endYmd, comFacilityCheckCd, comFacilityTypeCd, facilityNm, checkResultCd, checkStepCd, deptCd, tgtDeptCd, pfmDeptCd, deptSubYn, tgtDeptSubYn, pfmDeptSubYn, comFacilityCheckNo));
    }

    /**
     * 시설점검계획 상세조회
     * 
     * @param comFacilityCheckScheduleNo
     *            시설점검일정일련번호
     * @return 시설점검계획
     * @throws Exception
     */
    @ApiOperation(value = "시설점검계획 상세조회", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "comFacilityCheckScheduleNo", value = "시설점검일정일련번호", required = true, dataType = "int", paramType = "path") })
    @GetMapping("facilityinspectionplan/{comFacilityCheckScheduleNo}")
    public ResponseEntity<FacilityInspectionSchedule> getFacilityInspectionPlan(@PathVariable("comFacilityCheckScheduleNo") int comFacilityCheckScheduleNo) throws Exception {
        return ResponseEntity.ok().body(facilityInspectionService.getFacilityInspectionPlan(comFacilityCheckScheduleNo));
    }

    /**
     * 시설점검계획 수정
     * 
     * @param facilityInspectionSchedule
     *            시설점검일정(계획)
     * @return 변경 행 수
     * @throws Exception
     */
    @ApiOperation(value = "시설점검계획 수정", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "facilityInspectionSchedule", value = "시설점검일정마스터", required = false, dataType = "FacilityInspectionSchedule", paramType = "body") })
    @PutMapping("facilityinspectionplanresult")
    public ResponseEntity<Integer> updateFacilityInspectionPlan(@RequestBody FacilityInspectionSchedule facilityInspectionSchedule) throws Exception {
        return ResponseEntity.ok().body(facilityInspectionService.updateFacilityInspectionPlan(facilityInspectionSchedule));
    }

    /**
     * 시설점검계획 무점검계획등록
     * 
     * @param facilityInspectionSchedule
     *            시설점검일정(계획)
     * @return 변경 행 수
     * @throws Exception
     */
    @PostMapping("facilityinspectionplanresult")
    public ResponseEntity<Integer> insertFacilityInspectionPlan(@RequestBody FacilityInspectionSchedule facilityInspectionSchedule) throws Exception {
        return ResponseEntity.ok().body(facilityInspectionService.insertFacilityInspectionPlan(facilityInspectionSchedule));
    }

    /**
     * 시설점검일정(계획) 삭제
     * 
     * @param comFacilityCheckScheduleNo
     *            시설점검일정일련번호
     * @return 변경 행 수
     * @throws Exception
     */
    @ApiOperation(value = "시설점검일정 삭제", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "comFacilityCheckScheduleNo", value = "시설점검일정일련번호", required = false, dataType = "int", paramType = "path") })
    @DeleteMapping("facilityinspectionplanresult/{comFacilityCheckScheduleNo}")
    public ResponseEntity<Integer> deletesFacilityCheckResult(@PathVariable("comFacilityCheckScheduleNo") int comFacilityCheckScheduleNo) throws Exception {
        return ResponseEntity.ok().body(facilityInspectionService.deleteFacilityInspectionPlan(comFacilityCheckScheduleNo));
    }

    /**
     * 시설점검 대상자 조회
     * 
     * @param parameter
     *            검색조건
     * @return 시설점검 대상자 목록
     */
    @ApiOperation(value = "설비점검 대상자 조회", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "comFacilityCheckScheduleNo", value = "시설점검일정일련번호", required = false, dataType = "int", paramType = "query"), @ApiImplicitParam(name = "inspectorClassCd", value = "점검자구분코드", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
    @GetMapping("facilityinsinspectors")
    public ResponseEntity<List<FacilityInspectionInspector>> getFacilityCheckInspectors(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        // 시설점검결과번호
        int comFacilityCheckScheduleNo = map.containsKey("comFacilityCheckScheduleNo") ? Integer.parseInt("".equals(map.get("comFacilityCheckScheduleNo").toString()) ? "0" : map.get("comFacilityCheckScheduleNo").toString()) : 0;
        // 점검자구분코드
        String inspectorClassCd = map.containsKey("inspectorClassCd") ? map.get("inspectorClassCd").toString() : "";

        List<FacilityInspectionInspector> facilityInspectionInspector = facilityInspectionService.getFacilityInsInspectors(comFacilityCheckScheduleNo, inspectorClassCd);
        return ResponseEntity.ok().body(facilityInspectionInspector);
    }

    /**
     * 시설점검 항목별점검결과 조회
     * 
     * @param parameter
     *            검색조건
     * @return 시설점검 항목별점검결과 목록
     */
    @ApiOperation(value = "시설점검 항목별점검결과 조회", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "comFacilityCheckScheduleNo", value = "시설점검일정일련번호", required = false, dataType = "int", paramType = "query"), @ApiImplicitParam(name = "comFacilityCheckCd", value = "점검종류코드", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "comFacilityTypeCd", value = "시설유형코드", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "plantCd", value = "사업장코드", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
    @GetMapping("facilityinspectionitemresults")
    public ResponseEntity<List<FacilityInspectionItemResult>> getFacilityCheckItemResults(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        // 시설점검결과번호
        int comFacilityCheckScheduleNo = map.containsKey("comFacilityCheckScheduleNo") ? Integer.parseInt(map.get("comFacilityCheckScheduleNo").toString()) : 0;
        // 점검종류코드
        String comFacilityCheckCd = map.containsKey("comFacilityCheckCd") ? map.get("comFacilityCheckCd").toString() : "";
        // 시설유형코드
        String comFacilityTypeCd = map.containsKey("comFacilityTypeCd") ? map.get("comFacilityTypeCd").toString() : "";
        // 사업장
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        return ResponseEntity.ok().body(facilityInspectionService.getFacilityInsItemResults(comFacilityCheckScheduleNo, comFacilityCheckCd, comFacilityTypeCd, plantCd));
    }

    /**
     * 시설점검 사업장별 실적집계
     * 
     * @param parameter
     *            검색조건
     * @return 시설점검 사업장별 실적집계 목록
     */
    @ApiOperation(value = "시설점검 사업장별 실적집계", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "plantCd", value = "사업장", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "year", value = "점검년도", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
    @GetMapping("facilityinspectionresultstatus")
    public ResponseEntity<List<HashMap<String, Object>>> getFacilityInspectionResultStatus(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        // 사업장명
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 점검년도
        String year = map.containsKey("year") ? map.get("year").toString() : "";

        return ResponseEntity.ok().body(facilityInspectionService.getFacilityInspectionResultStatus(plantCd, year));
    }

    /**
     * 시설점검 사업장별 실적 세부집계
     * 
     * @param parameter
     *            검색조건
     * @return 시설점검 사업장별 실적 세부집계 목록
     */
    @ApiOperation(value = "시설점검 사업장별 실적 세부집계", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "plantCd", value = "사업장", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "year", value = "점검년도", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "comFacilityCheckCd", value = "점검년도", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "comFacilityTypeCd", value = "점검년도", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "deptCd", value = "점검년도", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
    @GetMapping("facilityinspectionresultstatussub")
    public ResponseEntity<List<HashMap<String, Object>>> getFacilityInspectionResultStatusSub(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        // 사업장명
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 점검년도
        String year = map.containsKey("year") ? map.get("year").toString() : "";
        // 점검년도
        String comFacilityCheckCd = map.containsKey("comFacilityCheckCd") ? map.get("comFacilityCheckCd").toString() : "";
        // 점검년도
        String comFacilityTypeCd = map.containsKey("comFacilityTypeCd") ? map.get("comFacilityTypeCd").toString() : "";
        // 점검주관부서
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";
        // 하위부서 포함여부
        String deptSubYn = map.containsKey("deptSubYn") ? map.get("deptSubYn").toString() : "Y";

        return ResponseEntity.ok().body(facilityInspectionService.getFacilityInspectionResultStatusSub(plantCd, year, comFacilityCheckCd, comFacilityTypeCd, deptCd, deptSubYn));
    }

}
