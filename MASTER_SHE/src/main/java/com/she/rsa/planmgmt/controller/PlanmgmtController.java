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
package com.she.rsa.planmgmt.controller;

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
import com.she.rsa.model.Planmgmt;
import com.she.rsa.model.PlanmgmtDeptList;
import com.she.rsa.planmgmt.service.PlanmgmtService;
import com.she.utils.RequestMapper;

/**
 * 평가계획
 */
@RestController
@RequestMapping("api/rsa/planmgmt")
public class PlanmgmtController {
    @Autowired
    private PlanmgmtService planmgmtActionService;

    // queryString 변환을 위한 mapper 선언
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private CodeMasterMapper codeMasterMapper;

    private static final Logger logger = LoggerFactory.getLogger(PlanmgmtController.class);

    /**
     * 평가계획 관리 목록
     * 
     * @param parameter
     *            검색조건
     * @return 평가계획 관리 목록
     * @throws Exception
     */
    @GetMapping("/planmgmtlists")
    public ResponseEntity<List<Planmgmt>> getPlanmgmtLists(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 평가계획 ID
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        String assessYear = map.containsKey("assessYear") ? map.get("assessYear").toString() : "";
        String assessNm = map.containsKey("assessNm") ? map.get("assessNm").toString() : "";
        String assessTypeCd = map.containsKey("assessTypeCd") ? map.get("assessTypeCd").toString() : "";
        String regRegdemCd = map.containsKey("regRegdemCd") ? map.get("regRegdemCd").toString() : "";
        String yearChk = map.containsKey("yearChk") ? map.get("yearChk").toString() : "";
        String riskType = map.containsKey("riskType") ? map.get("riskType").toString() : "";
        String stateCd = map.containsKey("stateCd") ? map.get("stateCd").toString() : "";
        String mainDeptCd = map.containsKey("mainDeptCd") ? map.get("mainDeptCd").toString() : "";
        String deptSubYn = map.containsKey("deptSubYn") ? map.get("deptSubYn").toString() : "";
        return ResponseEntity.ok().body(planmgmtActionService.getPlanmgmtLists(plantCd, assessYear, assessTypeCd, regRegdemCd, yearChk, assessNm, riskType, stateCd, mainDeptCd, deptSubYn, defaultParam));
    }

    /**
     * 평가계획 관리 조회
     * 
     * @param parameter
     *            검색조건
     * @return 평가계획 관리 목록
     * @throws Exception
     */
    @GetMapping("/planmgmtinfo/{assessPlanNo}")
    public ResponseEntity<Planmgmt> getPlanmgmtInfo(@PathVariable("assessPlanNo") int assessPlanNo, @ModelAttribute DefaultParam defaultParam) throws Exception {

        return ResponseEntity.ok().body(planmgmtActionService.getPlanmgmtInfo(assessPlanNo, defaultParam));
    }

    /**
     * 평가계획 관리 대상부서조회
     * 
     * @param parameter
     *            검색조건
     * @return 평가계획 관리 대상부서목록
     * @throws Exception
     */
    @GetMapping("/planmgmtdetplist")
    public ResponseEntity<List<PlanmgmtDeptList>> getPlanmgmtDeptList(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        int assessPlanNo = parameter.containsKey("assessPlanNo") ? Integer.parseInt(parameter.get("assessPlanNo").toString()) : 0;
        String apprFlag = map.containsKey("apprFlag") ? map.get("apprFlag").toString() : "";
        return ResponseEntity.ok().body(planmgmtActionService.getPlanmgmtDeptList(assessPlanNo, apprFlag));
    }

    /**
     * 평가계획 관리 등록
     * 
     * @param Planmgmt
     *            평가계획 관리 List
     * @return 평가계획 관리 Key값
     * @throws Exception
     */
    @PostMapping("/planmgmt")
    public ResponseEntity<Integer> createPlanmgmt(@RequestBody Planmgmt planmgmt) throws Exception {
        return ResponseEntity.ok().body(planmgmtActionService.createPlanmgmt(planmgmt));
    }

    /**
     * 평가계획 관리 수정
     * 
     * @param Planmgmt
     *            평가계획 관리 List
     * @return 평가계획 관리 Key값
     * @throws Exception
     */
    @PutMapping("/planmgmt")
    public ResponseEntity<Integer> updatePlanmgmt(@RequestBody Planmgmt planmgmt) throws Exception {
        return ResponseEntity.ok().body(planmgmtActionService.updatePlanmgmt(planmgmt));
    }

    /**
     * 평가계획/결과 관리 삭제
     * 
     * @param Planmgmt
     * 
     * @return 평가계획/결과 관리 삭제
     * @throws Exception
     */
    @DeleteMapping("/planmgmt/{assessPlanNo}")
    public ResponseEntity<Integer> deletePlanmgmt(@PathVariable("assessPlanNo") int assessPlanNo) throws Exception {
        return ResponseEntity.ok().body(planmgmtActionService.deletePlanmgmt(assessPlanNo));
    }

    /**
     * 평가계획 관리 대상부서삭제
     * 
     * @param Planmgmt
     *            평가계획 관리 List
     * @return 평가계획 관리 Key값
     * @throws Exception
     */
    @DeleteMapping("/planmgmtdeptlist")
    public ResponseEntity<Integer> deletePlanmgmtDeptList(@RequestBody List<PlanmgmtDeptList> planmgmtDeptList) throws Exception {
        return ResponseEntity.ok().body(planmgmtActionService.deletePlanmgmtDeptList(planmgmtDeptList));
    }

    /**
     * 평가결과 관리 목록
     * 
     * @param parameter
     *            검색조건
     * @return 평가결과 관리 목록
     * @throws Exception
     */
    @GetMapping("/resultmgmtlists")
    public ResponseEntity<List<PlanmgmtDeptList>> getResultmgmtLists(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 평가계획 ID
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        String assessYear = map.containsKey("assessYear") ? map.get("assessYear").toString() : "";
        String assessNm = map.containsKey("assessNm") ? map.get("assessNm").toString() : "";
        String assessTypeCd = map.containsKey("assessTypeCd") ? map.get("assessTypeCd").toString() : "";
        String regRegdemCd = map.containsKey("regRegdemCd") ? map.get("regRegdemCd").toString() : "";
        String yearChk = map.containsKey("yearChk") ? map.get("yearChk").toString() : "";
        String imprChk = map.containsKey("imprChk") ? map.get("imprChk").toString() : "";
        String mainDeptCd = map.containsKey("mainDeptCd") ? map.get("mainDeptCd").toString() : "";
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";
        String mainDeptSubYn = map.containsKey("mainDeptSubYn") ? map.get("mainDeptSubYn").toString() : "";
        String deptSubYn = map.containsKey("deptSubYn") ? map.get("deptSubYn").toString() : "";
        String riskType = map.containsKey("riskType") ? map.get("riskType").toString() : "";
        int monFlag = parameter.containsKey("monFlag") ? Integer.parseInt(parameter.get("monFlag").toString()) : 0;
        String stateCd = map.containsKey("stateCd") ? map.get("stateCd").toString() : "";
        String gubun = map.containsKey("gubun") ? map.get("gubun").toString() : "";
        return ResponseEntity.ok().body(planmgmtActionService.getResultmgmtLists(plantCd, assessYear, assessTypeCd, regRegdemCd, yearChk, assessNm, imprChk, deptSubYn, deptCd, riskType, monFlag, gubun, mainDeptCd, mainDeptSubYn, stateCd, defaultParam));
    }

    /**
     * 평가결과 관리 조회
     * 
     * @param parameter
     *            검색조건
     * @return 평가결과 관리 목록
     * @throws Exception
     */
    @GetMapping("/resultmgmtinfo/{assessDeptNo}")
    public ResponseEntity<PlanmgmtDeptList> getResultmgmtInfo(@PathVariable("assessDeptNo") int assessDeptNo, @ModelAttribute DefaultParam defaultParam) throws Exception {
        return ResponseEntity.ok().body(planmgmtActionService.getResultmgmtInfo(assessDeptNo, defaultParam));
    }

    /**
     * 평가결과 관리 평가자(내부, 외부)등록 / 무계획 등록 포함
     * 
     * @param ResultEstimator
     * 
     * @return 평가결과 관리 평가자(내부, 외부) / 무계획 등록 포함
     * @throws Exception
     */
    @PostMapping("/resultmgmt")
    public ResponseEntity<Integer> createResultmgmt(@RequestBody PlanmgmtDeptList planmgmtDeptInfo) throws Exception {
        return ResponseEntity.ok().body(planmgmtActionService.createResultmgmt(planmgmtDeptInfo));
    }

    /**
     * 평가결과 현황 목록
     * 
     * @param parameter
     *            검색조건
     * @return 평가결과 현황 목록
     * @throws Exception
     */
    @GetMapping("/resultstatus")
    public ResponseEntity<List<HashMap<String, Object>>> getResultstatusList(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 평가계획 ID
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        String assessYear = map.containsKey("assessYear") ? map.get("assessYear").toString() : "";
        String regRegdemCd = map.containsKey("regRegdemCd") ? map.get("regRegdemCd").toString() : "";
        String riskType = map.containsKey("riskType") ? map.get("riskType").toString() : "";
        String totalFlag = map.containsKey("totalFlag") ? map.get("totalFlag").toString() : "";
        return ResponseEntity.ok().body(planmgmtActionService.getResultstatusList(plantCd, assessYear, regRegdemCd, riskType, totalFlag, defaultParam));
    }

    /**
     * 평가계획 삭제 가능 확인
     * 
     * @param parameter
     *            검색조건
     * @return 평가계획 삭제 가능 확인
     * @throws Exception
     */
    @GetMapping("/planmgmtstatus/{assessPlanNo}")
    public ResponseEntity<Integer> getPlanmgmtStatus(@PathVariable("assessPlanNo") int assessPlanNo, @ModelAttribute DefaultParam defaultParam) throws Exception {
        return ResponseEntity.ok().body(planmgmtActionService.getPlanmgmtStatus(assessPlanNo));
    }

    /**
     * 평가결과 삭제 가능 확인
     * 
     * @param parameter
     *            검색조건
     * @return 평가결과 삭제 가능 확인
     * @throws Exception
     */
    @GetMapping("/planmgmtimprstatus/{assessDeptNo}")
    public ResponseEntity<Integer> getPlanmgmtImprStatus(@PathVariable("assessDeptNo") int assessDeptNo, @ModelAttribute DefaultParam defaultParam) throws Exception {
        return ResponseEntity.ok().body(planmgmtActionService.getPlanmgmtImprStatus(assessDeptNo));
    }

    /**
     * 평가결과 관리 삭제
     * 
     * @param Planmgmt
     * 
     * @return 평가계획/결과 관리 삭제
     * @throws Exception
     */
    @DeleteMapping("/resultmgmt/{assessDeptNo}")
    public ResponseEntity<Integer> deletePlanmgmtDept(@PathVariable("assessDeptNo") int assessDeptNo) throws Exception {
        return ResponseEntity.ok().body(planmgmtActionService.deletePlanmgmtDept(assessDeptNo));
    }
}
