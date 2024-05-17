package com.she.mgt.elect.controller;

import com.google.common.base.Strings;
import com.she.common.model.DefaultParam;
import com.she.mgt.elect.service.ElectEvalPlanService;
import com.she.mgt.model.ElectEvalItem;
import com.she.mgt.model.ElectEvalPlan;
import com.she.utils.RequestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/mgt/electevalmgmt")
public class ElectEvalPlanController {
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private ElectEvalPlanService electEvalPlanService;

    /**
     * 평가대상 법정선임자 목록 조회
     *
     * @param safElectTitlNo
     *            법정선임자 번호
     * @return 평가대상 법정선임자 목록
     * @throws Exception
     */
    @GetMapping("/electhisevaltarget")
    public ResponseEntity<List<HashMap<String, Object>>> getSafElectTitlEvalTarget(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = requestMapper.convertAsParameter(parameter);

        int safElectTitlNo = map.containsKey("safElectTitlNo") ? (map.get("safElectTitlNo") != null ? Integer.parseInt(map.get("safElectTitlNo").toString()) : 0) : 0;
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : null;

        return ResponseEntity.ok().body(electEvalPlanService.getSafElectTitlEvalTarget(safElectTitlNo, plantCd, defaultParam));
    }

    /**
     * 법정선임자 평가계획 목록 조회
     *
     * @param startYear
     *            시작년도
     * @param endYear
     *            종료년도
     * @param halfTypeCd
     *            구분코드
     * @param deptCd
     *            주관부서코드
     * @param deptSubYn
     *            하위부서 조회여부
     * @param safElectTitlNo
     *            법정선임자번호
     * @param evalNm
     *            평가명
     * @param procStepCd
     *            단계코드
     * @param stateCd
     *            상태코드
     * @param evalIncompleteYn
     *            평가미완료여부
     * @param evalStepCd
     *            평가미완료시 조회할 상태코드
     * @param defaultParam
     * @return 법정선임자 평가계획 목록
     * @throws Exception
     */
    @GetMapping("/electevalplans")
    public ResponseEntity<List<ElectEvalPlan>> getElectEvalPlans(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = requestMapper.convertAsParameter(parameter);

        // 대상년도
        String[] period = requestMapper.convertObjectListAsStringArray(map.get("period"));
        String startYear = "";
        String endYear = "";
        if (period != null && period.length == 2) {
            startYear = period[0];
            endYear = period[1];
        }

        // 구분
        String halfTypeCd = map.containsKey("halfTypeCd") ? map.get("halfTypeCd").toString() : "";

        // 주관부서
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";

        // 하위부서여부
        String deptSubYn = map.containsKey("deptSubYn") ? map.get("deptSubYn").toString() : "";

        // 법정선임자
        int safElectTitlNo = map.containsKey("safElectTitlNo") ? (Strings.isNullOrEmpty(map.get("safElectTitlNo").toString()) ? 0 : Integer.parseInt(map.get("safElectTitlNo").toString())) : 0;

        // 평가명
        String evalNm = map.containsKey("evalNm") ? map.get("evalNm").toString() : "";

        // 단계
        String procStepCd = map.containsKey("procStepCd") ? map.get("procStepCd").toString() : "";

        // 상태
        String stateCd = map.containsKey("stateCd") ? map.get("stateCd").toString() : "";

        // 평가미완료
        String evalIncompleteYn = map.containsKey("evalIncompleteYn") ? map.get("evalIncompleteYn").toString() : "";

        // 평가미완료 선택값
        String evalStepCd = map.containsKey("evalStepCd") ? map.get("evalStepCd").toString() : "";

        return ResponseEntity.ok().body(electEvalPlanService.getElectEvalPlans(startYear, endYear, halfTypeCd, deptCd, deptSubYn, safElectTitlNo, evalNm, procStepCd, stateCd, evalIncompleteYn, evalStepCd, defaultParam));
    }

    /**
     * 법정선임자 평가계획 상세 조회
     *
     * @param evalPlanNo
     *            법정선임자 평가계획 번호
     * @return 법정선임자 평가계획 상세
     * @throws Exception
     */
    @GetMapping("/electevalplan/{evalPlanNo}")
    public ResponseEntity<ElectEvalPlan> getElectEvalPlan(@PathVariable("evalPlanNo") int evalPlanNo, @ModelAttribute DefaultParam defaultParam) throws Exception {
        return ResponseEntity.ok().body(electEvalPlanService.getElectEvalPlan(evalPlanNo, defaultParam));
    }

    /**
     * 법정선임자 평가계획 신규 등록
     *
     * @param electEvalPlan
     *            법정선임자 평가계획
     * @return 법정선임자 평가계획
     * @throws Exception
     */
    @PostMapping("/electevalplan")
    public ResponseEntity<ElectEvalPlan> createElectEvalPlan(@RequestBody ElectEvalPlan electEvalPlan) throws Exception {
        return ResponseEntity.ok().body(electEvalPlanService.createElectEvalPlan(electEvalPlan));
    }

    /**
     * 법정선임자 평가계획 수정
     *
     * @param electEvalPlan
     *            법정선임자 평가계획
     * @return 법정선임자 평가계획
     * @throws Exception
     */
    @PutMapping("/electevalplan")
    public ResponseEntity<ElectEvalPlan> updateElectEvalPlan(@RequestBody ElectEvalPlan electEvalPlan) throws Exception {
        return ResponseEntity.ok().body(electEvalPlanService.updateElectEvalPlan(electEvalPlan));
    }

    /**
     * 법정선임자 평가계획 삭제
     *
     * @param evalPlanNo
     *            법정선임자 평가계획 번호
     * @return 결과
     * @throws Exception
     */
    @DeleteMapping("/electevalplan/{evalPlanNo}")
    public ResponseEntity<Integer> deleteElectEvalPlan(@PathVariable("evalPlanNo") int evalPlanNo) throws Exception {
        return ResponseEntity.ok().body(electEvalPlanService.deleteElectEvalPlan(evalPlanNo));
    }

    @DeleteMapping("/electevalplan/delevalusers/{evalPlanNo}")
    public ResponseEntity<Integer> deleteElectEvalUser(@PathVariable("evalPlanNo") int evalPlanNo) throws Exception {

        return ResponseEntity.ok().body(electEvalPlanService.deleteElectEvalUser(evalPlanNo));
    }

    /**
     * 법정선임자 평가계획 평가항목 목록 조회
     *
     * @param evalPlanNo
     *            법정선임자 평가계획 번호
     * @param safElectTitlNo
     *            법정선임자 번호
     * @return 법정선임자 평가계획 평가항목 목록
     * @throws Exception
     */
    @GetMapping("/electevalplan/evalitem/{safElectTitlNo}")
    public ResponseEntity<List<ElectEvalItem>> getElectEvalItems(@PathVariable("safElectTitlNo") int safElectTitlNo) throws Exception {
        return ResponseEntity.ok().body(electEvalPlanService.getElectEvalItems(0, safElectTitlNo));
    }
}
