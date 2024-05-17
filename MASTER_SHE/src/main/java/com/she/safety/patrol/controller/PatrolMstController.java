package com.she.safety.patrol.controller;

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

import com.she.safety.model.PatrolMaster;
import com.she.safety.patrol.service.PatrolMstService;
import com.she.utils.RequestMapper;

/**
 * 연간순회점검계획.
 */
@RestController
@RequestMapping("/api/saf/master/patrol")
public class PatrolMstController {
    /**
     * request mapper.
     */
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private PatrolMstService service;

    /**
     * 연간순회점검계획 목록 조회.
     * 
     * @param parameter
     *            검색조건
     * @return 안전점검일정 목록
     * @throws Exception
     */
    @GetMapping("/patrols")
    public ResponseEntity<List<PatrolMaster>> getPatrolMsts(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        // From
        String startDate = map.containsKey("startDate") ? map.get("startDate").toString() : "";
        // To
        String endDate = map.containsKey("endDate") ? map.get("endDate").toString() : "";
        // 주관부서코드
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";
        // 하위부서 포함여부
        String deptSubYn = map.containsKey("deptSubYn") ? map.get("deptSubYn").toString() : "Y";
        // 순회대상부서
        String tgtDeptCd = map.containsKey("tgtDeptCd") ? map.get("tgtDeptCd").toString() : "";
     // 하위부서 포함여부
        String tgtDeptSubYn = map.containsKey("tgtDeptSubYn") ? map.get("tgtDeptSubYn").toString() : "Y";
        // 순회대상업체
        String tgtVendorCd = map.containsKey("tgtVendorCd") ? map.get("tgtVendorCd").toString() : "";
        // 하위수행부서 포함여부
        String pgpDeptSubYn = map.containsKey("pgpDeptSubYn") ? map.get("pgpDeptSubYn").toString() : "Y";
        // 순회수행업체
        String pgpDeptCd = map.containsKey("pgpDeptCd") ? map.get("pgpDeptCd").toString() : "";
        // 사업장 코드
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 점검상태
        String stepCd = map.containsKey("stepCd") ? map.get("stepCd").toString() : "";

        int safCheckKindNo = map.containsKey("safCheckKindNo") ? Integer.parseInt("".equals(map.get("safCheckKindNo").toString()) ? "0" : map.get("safCheckKindNo").toString()): 0;
        // 점검명
        String keyword = map.containsKey("keyword") ? map.get("keyword").toString() : "";

        return ResponseEntity.ok().body(service.getPatrolMsts(startDate, endDate, deptCd, deptSubYn, tgtDeptCd, tgtDeptSubYn, pgpDeptCd, pgpDeptSubYn,tgtVendorCd, plantCd, stepCd, safCheckKindNo, keyword));
    }

    /**
     * 연간순회점검계획 상세.
     * 
     * @param checkMaster
     * @return safCheckRsltNo
     * @throws Exception
     */
    @GetMapping("/patrol/{patrolMstNo}")
    public ResponseEntity<PatrolMaster> getPatrolMst(@PathVariable("patrolMstNo") int patrolMstNo) throws Exception {
        return ResponseEntity.ok().body(service.getPatrolMst(patrolMstNo));
    }

    /**
     * 연간순회점검계획 등록.
     * 
     * @param checkMaster
     * @return safCheckRsltNo
     * @throws Exception
     */
    @PostMapping("/patrol")
    public ResponseEntity<Integer> createPatrolMst(@RequestBody PatrolMaster patrolMaster) throws Exception {
        return ResponseEntity.ok().body(service.createPatrolMst(patrolMaster));
    }

    /**
     * 연간순회점검계획 수정.
     * 
     * @param checkMaster
     * @return safCheckRsltNo
     * @throws Exception
     */
    @PutMapping("/patrol")
    public ResponseEntity<Integer> updatePatrolMst(@RequestBody PatrolMaster patrolMaster) throws Exception {
        return ResponseEntity.ok().body(service.updatePatrolMst(patrolMaster));
    }

    /**
     * 연간순회점검계획 수정.
     * 
     * @param checkMaster
     * @return safCheckRsltNo
     * @throws Exception
     */
    @PutMapping("/complete/{patrolMstNo}")
    public ResponseEntity<Integer> completePatrolMst(@PathVariable("patrolMstNo") int patrolMstNo) throws Exception {
        return ResponseEntity.ok().body(service.completePatrolMst(patrolMstNo));
    }

    /**
     * 연간순회점검계획 수정.
     * 
     * @param checkMaster
     * @return safCheckRsltNo
     * @throws Exception
     */
    @DeleteMapping("/patrol/{patrolMstNo}")
    public ResponseEntity<Integer> deletePatrolMst(@PathVariable("patrolMstNo") int patrolMstNo) throws Exception {
        return ResponseEntity.ok().body(service.deletePatrolMst(patrolMstNo));
    }
}
