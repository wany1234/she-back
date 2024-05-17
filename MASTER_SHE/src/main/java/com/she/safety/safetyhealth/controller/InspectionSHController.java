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
package com.she.safety.safetyhealth.controller;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.she.manage.mapper.CodeMasterMapper;
import com.she.rsa.planmgmt.controller.PlanmgmtController;
import com.she.safety.model.InspectionSH;
import com.she.safety.model.InspectionSHDept;
import com.she.safety.safetyhealth.service.InspectionSHService;
import com.she.utils.RequestMapper;

/**
 * 점검계획
 */
@RestController
@RequestMapping("api/saf/inspection")
public class InspectionSHController {
    @Autowired
    private InspectionSHService inspectionSHService;

    // queryString 변환을 위한 mapper 선언
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private CodeMasterMapper codeMasterMapper;

    private static final Logger logger = LoggerFactory.getLogger(PlanmgmtController.class);

    /**
     * 점검계획 관리 목록
     * 
     * @param parameter
     *            검색조건
     * @return 점검계획 관리 목록
     * @throws Exception
     */
    @GetMapping("/planlists")
    public ResponseEntity<List<InspectionSH>> getPlanLists(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 사업자명
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 대상년도
        String year = map.containsKey("year") ? map.get("year").toString() : "";
        // 구분
        String regRegdemCd = map.containsKey("regRegdemCd") ? map.get("regRegdemCd").toString() : "";
        // 점검명
        String chkNm = map.containsKey("chkNm") ? map.get("chkNm").toString() : "";
        // 단계
        String procStepCd = map.containsKey("procStepCd") ? map.get("procStepCd").toString() : "";
        // 상태
        String stateCd = map.containsKey("stateCd") ? map.get("stateCd").toString() : "";
        // 주관부서
        String mainDeptCd = map.containsKey("mainDeptCd") ? map.get("mainDeptCd").toString() : "";
        // 하위부서여부
        String deptSubYn = map.containsKey("deptSubYn") ? map.get("deptSubYn").toString() : "";
        // 점검기한초과
        String yearChk = map.containsKey("yearChk") ? map.get("yearChk").toString() : "";
        return ResponseEntity.ok().body(inspectionSHService.getPlanLists(plantCd, year, regRegdemCd, chkNm, procStepCd, stateCd, mainDeptCd, deptSubYn, yearChk, defaultParam));
    }

    /**
     * 점검계획 관리 조회
     * 
     * @param parameter
     *            검색조건
     * @return 점검계획 관리 조회
     * @throws Exception
     */
    @GetMapping("/planinfo/{implChkPlanNo}")
    public ResponseEntity<InspectionSH> getPlanInfo(@PathVariable("implChkPlanNo") int implChkPlanNo, @ModelAttribute DefaultParam defaultParam) throws Exception {

        return ResponseEntity.ok().body(inspectionSHService.getPlanInfo(implChkPlanNo, defaultParam));
    }

    /**
     * 점검계획 관리 대상부서조회
     * 
     * @param parameter
     *            검색조건
     * @return 점검계획 관리 대상부서조회
     * @throws Exception
     */
    @GetMapping("/plandeptlist")
    public ResponseEntity<List<InspectionSHDept>> getPlanDeptList(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        int implChkPlanNo = parameter.containsKey("implChkPlanNo") ? Integer.parseInt(parameter.get("implChkPlanNo").toString()) : 0;
        String apprFlag = map.containsKey("apprFlag") ? map.get("apprFlag").toString() : "";
        return ResponseEntity.ok().body(inspectionSHService.getPlanDeptList(implChkPlanNo, apprFlag));
    }

    /**
     * 점검계획 관리 등록
     * 
     * @param InspectionSH
     *            점검계획 관리 List
     * @return 점검계획 관리 Key값
     * @throws Exception
     */
    @PostMapping("/inspection")
    public ResponseEntity<Integer> createInspection(@RequestBody InspectionSH inspectionSH) throws Exception {
        return ResponseEntity.ok().body(inspectionSHService.createInspection(inspectionSH));
    }

    /**
     * 점검계획 관리 수정
     * 
     * @param InspectionSH
     *            점검계획 관리 List
     * @return 점검계획 관리 Key값
     * @throws Exception
     */
    @PutMapping("/inspection")
    public ResponseEntity<Integer> updateInspection(@RequestBody InspectionSH inspectionSH) throws Exception {
        return ResponseEntity.ok().body(inspectionSHService.updateInspection(inspectionSH));
    }

    /**
     * 점검계획/결과 관리 삭제
     * 
     * @param InspectionSH
     * 
     * @return 점검계획/결과 관리 삭제
     * @throws Exception
     */
    @DeleteMapping("/inspection/{implChkPlanNo}")
    public ResponseEntity<Integer> deleteInspection(@PathVariable("implChkPlanNo") int implChkPlanNo) throws Exception {
        return ResponseEntity.ok().body(inspectionSHService.deleteInspection(implChkPlanNo));
    }

    /**
     * 점검계획 관리 대상부서삭제
     * 
     * @param InspectionSH
     *            점검계획 관리 List
     * @return 점검계획 관리 Key값
     * @throws Exception
     */
    @DeleteMapping("/inspectionDept")
    public ResponseEntity<Integer> deleteInspectionDept(@RequestBody List<InspectionSHDept> inspectionSHDept) throws Exception {
        return ResponseEntity.ok().body(inspectionSHService.deleteInspectionDept(inspectionSHDept));
    }

    /**
     * 점검결과 관리 목록
     * 
     * @param parameter
     *            검색조건
     * @return 점검결과 관리 목록
     * @throws Exception
     */
    @GetMapping("/resultlists")
    public ResponseEntity<List<InspectionSHDept>> getResultLists(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        String year = map.containsKey("year") ? map.get("year").toString() : "";
        String chkNm = map.containsKey("chkNm") ? map.get("chkNm").toString() : "";
        String regRegdemCd = map.containsKey("regRegdemCd") ? map.get("regRegdemCd").toString() : "";
        String yearChk = map.containsKey("yearChk") ? map.get("yearChk").toString() : "";
        String imprChk = map.containsKey("imprChk") ? map.get("imprChk").toString() : "";
        String mainDeptCd = map.containsKey("mainDeptCd") ? map.get("mainDeptCd").toString() : "";
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";
        String mainDeptSubYn = map.containsKey("mainDeptSubYn") ? map.get("mainDeptSubYn").toString() : "";
        String deptSubYn = map.containsKey("deptSubYn") ? map.get("deptSubYn").toString() : "";
        int monFlag = parameter.containsKey("monFlag") ? Integer.parseInt(parameter.get("monFlag").toString()) : 0;
        String stateCd = map.containsKey("stateCd") ? map.get("stateCd").toString() : "";
        String gubun = map.containsKey("gubun") ? map.get("gubun").toString() : "";
        return ResponseEntity.ok().body(inspectionSHService.getResultLists(plantCd, year, chkNm, regRegdemCd, yearChk, imprChk, deptSubYn, deptCd, monFlag, gubun, mainDeptCd, mainDeptSubYn, stateCd, defaultParam));
    }

    /**
     * 점검결과 관리 조회
     * 
     * @param parameter
     *            검색조건
     * @return 점검결과 관리 목록
     * @throws Exception
     */
    @GetMapping("/resultinfo/{implChkDeptNo}")
    public ResponseEntity<InspectionSHDept> getResultInfo(@PathVariable("implChkDeptNo") int implChkDeptNo, @ModelAttribute DefaultParam defaultParam) throws Exception {
        return ResponseEntity.ok().body(inspectionSHService.getResultInfo(implChkDeptNo, defaultParam));
    }

    /**
     * 점검결과 관리 평가자(내부, 외부)등록 / 무계획 등록 포함
     * 
     * @param ResultEstimator
     * 
     * @return 점검결과 관리 평가자(내부, 외부) / 무계획 등록 포함
     * @throws Exception
     */
    @PostMapping("/inspectionresult")
    public ResponseEntity<Integer> createResult(@RequestBody InspectionSHDept inspectionSHDept) throws Exception {
        return ResponseEntity.ok().body(inspectionSHService.createResult(inspectionSHDept));
    }

    /**
     * 점검결과 현황 목록
     * 
     * @param parameter
     *            검색조건
     * @return 점검결과 현황 목록
     * @throws Exception
     */
    @GetMapping("/resultstatus")
    public ResponseEntity<List<HashMap<String, Object>>> getResultstatusList(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        String year = map.containsKey("year") ? map.get("year").toString() : "";
        String regRegdemCd = map.containsKey("regRegdemCd") ? map.get("regRegdemCd").toString() : "";
        String totalFlag = map.containsKey("totalFlag") ? map.get("totalFlag").toString() : "";
        return ResponseEntity.ok().body(inspectionSHService.getResultstatusList(plantCd, year, regRegdemCd, totalFlag, defaultParam));
    }

    /**
     * 점검결과 삭제 가능 확인
     * 
     * @param parameter
     *            검색조건
     * @return 점검결과 삭제 가능 확인
     * @throws Exception
     */
    @GetMapping("/imprstatus/{implChkDeptNo}")
    public ResponseEntity<Integer> getResultImprStatus(@PathVariable("implChkDeptNo") int implChkDeptNo, @ModelAttribute DefaultParam defaultParam) throws Exception {
        return ResponseEntity.ok().body(inspectionSHService.getResultImprStatus(implChkDeptNo));
    }

    /**
     * 점검결과 관리 삭제
     * 
     * @param InspectionSH
     * 
     * @return 점검계획/결과 관리 삭제
     * @throws Exception
     */
    @DeleteMapping("/inspectionresult/{implChkDeptNo}")
    public ResponseEntity<Integer> deleteResultDept(@PathVariable("implChkDeptNo") int implChkDeptNo) throws Exception {
        return ResponseEntity.ok().body(inspectionSHService.deleteResultDept(implChkDeptNo));
    }
}
