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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.she.safety.consol.model.ConsolInspDept;
import com.she.safety.consol.model.Consolidation;
import com.she.safety.consol.service.ConsolidationService;
import com.she.utils.RequestMapper;

/**
 * 연간합동점검계획.
 */
@RestController
@RequestMapping("/api/saf/master/consol")
public class ConsolidationController {
    /**
     * request mapper.
     */
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private ConsolidationService service;

    /**
     * 연간합동점검계획 목록 조회.
     * 
     * @param parameter
     *            검색조건
     * @return 연간합동점검계획 목록
     * @throws Exception
     */
    @GetMapping("/consols")
    public ResponseEntity<List<Consolidation>> getConsolMsts(@RequestParam HashMap<String, Object> parameter) throws Exception {
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
        String stepCd = map.containsKey("stepCd") ? map.get("stepCd").toString() : "";

        int safCheckKindNo = map.containsKey("safCheckKindNo") ? Integer.parseInt("".equals(map.get("safCheckKindNo").toString()) ? "0" : map.get("safCheckKindNo").toString()) : 0;

        List<Consolidation> resultList = service.getConsolMsts(startDate, endDate, deptCd, plantCd, stepCd, safCheckKindNo);
        return ResponseEntity.ok().body(resultList);
    }

    /**
     * 연간합동점검계획 상세.
     * 
     * @param safCongChkPlanNo
     * @return List<Consolidation>
     * @throws Exception
     */
    @GetMapping("/consol/{safCongChkPlanNo}")
    public ResponseEntity<List<Consolidation>> getConsolMst(@PathVariable("safCongChkPlanNo") int safCongChkPlanNo) throws Exception {
        return ResponseEntity.ok().body(service.getConsolMst(safCongChkPlanNo));
    }

    /**
     * 연간합동점검계획 부서/협력업체 조회.
     * 
     * @param safCongChkRsltNo
     * @return List<ConsolInspDept>
     * @throws Exception
     */
    @GetMapping("/inspdept/{safCongChkPlanNo}")
    public ResponseEntity<List<ConsolInspDept>> getConsolDept(@PathVariable("safCongChkPlanNo") int safCongChkPlanNo) throws Exception {
        return ResponseEntity.ok().body(service.getConsolDept(safCongChkPlanNo));
    }

    /**
     * 연간합동점검계획 등록.
     * 
     * @param Consolidation
     * @return safCongChkRsltNo
     * @throws Exception
     */
    @PostMapping("/consol")
    public ResponseEntity<Integer> createConsolMst(@RequestBody Consolidation consolMaster) throws Exception {
        return ResponseEntity.ok().body(service.createConsolMst(consolMaster));
    }

    /**
     * 연간합동점검계획 수정.
     * 
     * @param Consolidation
     * @return safCongChkRsltNo
     * @throws Exception
     */
    @PutMapping("/consol")
    public ResponseEntity<Integer> updateConsolMst(@RequestBody Consolidation consolMaster) throws Exception {
        return ResponseEntity.ok().body(service.updateConsolMst(consolMaster));
    }

    /**
     * 연간합동점검계획 삭제.
     * 
     * @param safCheckRsltNo,
     *            safCongChkPlanNo
     * @return 1, 0
     * @throws Exception
     */
    @DeleteMapping("/consol/{safCongChkRsltNo}/{safCongChkPlanNo}")
    public ResponseEntity<Integer> deleteconsolMst(@PathVariable("safCongChkRsltNo") int safCongChkRsltNo, @PathVariable("safCongChkPlanNo") int safCongChkPlanNo) throws Exception {
        return ResponseEntity.ok().body(service.deleteConsolMst(safCongChkRsltNo, safCongChkPlanNo));
    }
}
