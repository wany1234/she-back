package com.she.safety.consol.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.she.safety.consol.model.ConsolInspDept;
import com.she.safety.consol.model.Consolidation;
import com.she.safety.consol.service.ConsolidationPlanService;
import com.she.safety.model.PatrolItemResult;
import com.she.utils.RequestMapper;

/**
 * 합동점검계획.
 */
@RestController
public class ConsolidationPlanController {
    /**
     * request mapper.
     */
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private ConsolidationPlanService service;

    /**
     * 합동점검계획 목록 조회.
     * 
     * @param parameter
     *            검색조건
     * @return 합동점검일정 목록
     * @throws Exception
     */
    @GetMapping("/api/saf/plan/consol/consols")
    public ResponseEntity<List<Consolidation>> getConsolPlans(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        // From
        String startDate = map.containsKey("startDate") ? map.get("startDate").toString() : "";
        // To
        String endDate = map.containsKey("endDate") ? map.get("endDate").toString() : "";
        // 주관부서코드
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";
        // 사업장 코드
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 점검상태
        String checkStepCd = map.containsKey("checkStepCd") ? map.get("checkStepCd").toString() : "";

        int safCheckKindNo = map.containsKey("safCheckKindNo") ? Integer.parseInt("".equals(map.get("safCheckKindNo").toString()) ? "0" : map.get("safCheckKindNo").toString()) : 0;

        List<Consolidation> resultList = service.getConsolPlans(startDate, endDate, deptCd, plantCd, checkStepCd, safCheckKindNo);
        return ResponseEntity.ok().body(resultList);
    }

    /**
     * 합동점검(모바일) 목록 조회.
     * 
     * @param parameter
     *            검색조건
     * @return 합동점검(모바일) 목록
     * @throws Exception
     */
    @GetMapping("/api/saf/plan/consol/signatures")
    public ResponseEntity<List<Consolidation>> getConsolSigs(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        // From
        String startDate = map.containsKey("startDate") ? map.get("startDate").toString() : "";
        // To
        String endDate = map.containsKey("endDate") ? map.get("endDate").toString() : "";
        // 사업장 코드
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 부서 코드
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";
        // 참석자 코드
        String userId = map.containsKey("userId") ? map.get("userId").toString() : "";
        List<Consolidation> resultList = service.getConsolSigs(startDate, endDate, plantCd, deptCd, userId);
        return ResponseEntity.ok().body(resultList);
    }

    /**
     * 합동점검(모바일) 상세.
     * 
     * @param safCongChkRsltNo,safChkInspPsnNo,safChkInspDeptNo
     * @return Consolidation
     * @throws Exception
     */
    @GetMapping("/api/saf/plan/consol/signature/{safCongChkRsltNo}/{safChkInspPsnNo}/{safChkInspDeptNo}")
    public ResponseEntity<Consolidation> getConsolSig(@PathVariable("safCongChkRsltNo") int safCongChkRsltNo, @PathVariable("safChkInspPsnNo") int safChkInspPsnNo, @PathVariable("safChkInspDeptNo") int safChkInspDeptNo) throws Exception {
        return ResponseEntity.ok().body(service.getConsolSig(safCongChkRsltNo, safChkInspPsnNo, safChkInspDeptNo));
    }

    /**
     * 합동점검(모바일) 사인완료.
     * 
     * @param safChkInspPsnNo
     * @return
     * @throws Exception
     */
    @PutMapping("/api/saf/plan/consol/signature/complete/{safChkInspPsnNo}")
    public ResponseEntity<Integer> completeSign(@PathVariable("safChkInspPsnNo") int safChkInspPsnNo) throws Exception {
        return ResponseEntity.ok().body(service.completeSign(safChkInspPsnNo));
    }

    /**
     * 참석자 서명 등록
     *
     * @param parameter
     *            참석자 일련번호, 사용자 서명
     * @return
     * @throws Exception
     */
    @PutMapping("/api/saf/plan/consol/signature")
    public ResponseEntity<Integer> updateUserSignature(@RequestBody HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        int safChkInspPsnNo = map.containsKey("safChkInspPsnNo") ? Integer.parseInt("".equals(map.get("safChkInspPsnNo").toString()) ? "0" : map.get("safChkInspPsnNo").toString()) : 0;
        String signImg = map.containsKey("signImg") ? map.get("signImg").toString() : "";

        return ResponseEntity.ok().body(this.service.updateUserSignature(safChkInspPsnNo, signImg));
    }

    /**
     * 합동점검계획 상세.
     * 
     * @param safCongChkRsltNo
     * @return 합동점검계획
     * @throws Exception
     */
    @GetMapping("/api/saf/plan/consol/consol/{safCongChkRsltNo}")
    public ResponseEntity<Consolidation> getConsolPlan(@PathVariable("safCongChkRsltNo") int safCongChkRsltNo) throws Exception {
        return ResponseEntity.ok().body(service.getConsolPlan(safCongChkRsltNo));
    }

    /**
     * 합동점검계획 협력업체/부서.
     * 
     * @param checkMaster
     * @return List<ConsolInspDept>
     * @throws Exception
     */
    @GetMapping("/api/saf/plan/consol/inspector/{safCongChkRsltNo}")
    public ResponseEntity<List<ConsolInspDept>> getConsolInspectors(@PathVariable("safCongChkRsltNo") int safCongChkRsltNo) throws Exception {
        return ResponseEntity.ok().body(service.getConsolInspectors(safCongChkRsltNo));
    }

    /**
     * 연간합동점검계획 등록.
     * 
     * @param consolMaster
     * @return safCongChkRsltNo
     * @throws Exception
     */
    @PostMapping("/api/saf/plan/consol/consol")
    public ResponseEntity<Integer> createConsolPlan(@RequestBody Consolidation consolMaster) throws Exception {
        return ResponseEntity.ok().body(service.createConsolPlan(consolMaster));
    }

    /**
     * 연간합동점검계획 수정.
     * 
     * @param consolMaster
     * @return safCongChkRsltNo
     * @throws Exception
     */
    @PutMapping("/api/saf/plan/consol/consol")
    public ResponseEntity<Integer> updateConsolPlan(@RequestBody Consolidation consolMaster) throws Exception {
        return ResponseEntity.ok().body(service.updateConsolPlan(consolMaster));
    }

    /**
     * 합동결과 검사항목 조회.
     * 
     * @param safCongChkRsltNo,
     *            safCheckKindNo
     * @return 검사항목
     * @throws Exception
     */
    @GetMapping("/api/saf/plan/consol/resultItem")
    public ResponseEntity<List<PatrolItemResult>> getConsolResultItems(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        // 합동번호
        int safCongChkRsltNo = map.containsKey("safCongChkRsltNo") ? Integer.parseInt("".equals(map.get("safCongChkRsltNo").toString()) ? "0" : map.get("safCongChkRsltNo").toString()) : 0;
        // 합동종류
        int safCheckKindNo = map.containsKey("safCheckKindNo") ? Integer.parseInt("".equals(map.get("safCheckKindNo").toString()) ? "0" : map.get("safCheckKindNo").toString()) : 0;

        return ResponseEntity.ok().body(service.getConsolResultItems(safCongChkRsltNo, safCheckKindNo));
    }

    /**
     * 연간합동점검계획 완료.
     * 
     * @param safCongChkRsltNo
     * @return
     * @throws Exception
     */
    @PutMapping("/api/saf/plan/consol/complete/{safCongChkRsltNo}")
    public ResponseEntity<Integer> completeConsolPlan(@PathVariable("safCongChkRsltNo") int safCongChkRsltNo) throws Exception {
        return ResponseEntity.ok().body(service.completeConsolPlan(safCongChkRsltNo));
    }

    /**
     * 연간합동점검계획 삭제.
     * 
     * @param safCongChkRsltNo
     * @return
     * @throws Exception
     */
    @DeleteMapping("/consol/{safCongChkRsltNo}")
    public ResponseEntity<Integer> deleteConsolPlan(@PathVariable("safCongChkRsltNo") int safCongChkRsltNo) throws Exception {
        return ResponseEntity.ok().body(service.deleteConsolPlan(safCongChkRsltNo));
    }
}
