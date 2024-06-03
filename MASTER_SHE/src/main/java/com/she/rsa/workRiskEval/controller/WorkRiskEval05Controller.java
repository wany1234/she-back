package com.she.rsa.workRiskEval.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.she.common.model.DefaultParam;
import com.she.rsa.model.WorkRiskEval04Appr;
import com.she.rsa.model.WorkRiskEval05Exam;
import com.she.rsa.workRiskEval.service.WorkRiskEval05Service;
import com.she.utils.RequestMapper;

/**
 * 작업위험성평가 검토
 */
@RestController
@RequestMapping("api/rsa/workRiskEval05")
public class WorkRiskEval05Controller {

    // queryString 변환을 위한 mapper 선언
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private WorkRiskEval05Service workRiskEval05Service;

    /**
     * 작업위험성평가 검토 조회
     * 
     * @return 작업위험성평가 검토 목록
     * @throws Exception
     */
    @GetMapping("/workRiskEval05List")
    public ResponseEntity<List<WorkRiskEval04Appr>> getworkRiskEval05List(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 사업장
        String plantCd = map.containsKey("schPlantCd") ? map.get("schPlantCd").toString() : "";

        // 부서코드
        String deptCd = map.containsKey("schDeptCd") ? map.get("schDeptCd").toString() : "";

        // 퍙가구분 (정시/수기)
        String evalTypeCd = map.containsKey("schEvalType") ? map.get("schEvalType").toString() : "";

        // 평가년도
        String[] evalYear = this.requestMapper.convertObjectListAsStringArray(map.get("schEvalYear"));

        String startYear = "";
        String endYear = "";

        if (evalYear != null && evalYear.length == 2) {
            startYear = evalYear[0];
            endYear = evalYear[1];
        }

        return ResponseEntity.ok().body(workRiskEval05Service.getworkRiskEval05List(plantCd, deptCd, evalTypeCd, startYear, endYear, defaultParam));
    }

    /**
     * 작업위험성평가 공정설정 평가대상공정 확정
     * 
     * @param WorkRiskEval05Exam
     *            작업위험성평가 검토결과
     * @return 작업위험성평가 검토결과 관리 Key값
     * @throws Exception
     */
    @PutMapping("/workRiskEval05")
    public ResponseEntity<String> updateWorkRiskEval05(@RequestBody WorkRiskEval05Exam workRiskEval05Exam) throws Exception {
        return ResponseEntity.ok().body(workRiskEval05Service.updateWorkRiskEval05CheckRslt(workRiskEval05Exam));
    }

    /**
     * 작업위험성평가 공정설정 평가대상공정 확정
     * 
     * @param WorkRiskEval05Exam
     *            작업위험성평가 검토결과
     * @return 작업위험성평가 검토결과 관리 Key값
     * @throws Exception
     */
    @PutMapping("/workRiskEval05Chk")
    public ResponseEntity<String> updateWorkRiskEval05Chk(@RequestBody WorkRiskEval05Exam workRiskEval05Exam) throws Exception {
        return ResponseEntity.ok().body(workRiskEval05Service.updateWorkRiskEval05CheckRsltPrcs(workRiskEval05Exam));
    }

    /**
     * 작업위험성평가 공정설정 평가대상공정 확정
     * 
     * @param WorkRiskEval05Exam
     *            작업위험성평가 검토결과
     * @return 작업위험성평가 검토결과 관리 Key값
     * @throws Exception
     */
    @PutMapping("/workRiskEval05ChkAll")
    public ResponseEntity<String> updateWorkRiskEval05ChkAll(@RequestBody WorkRiskEval05Exam workRiskEval05Exam) throws Exception {
        return ResponseEntity.ok().body(workRiskEval05Service.updateWorkRiskEval05CheckRsltPrcsAll(workRiskEval05Exam));
    }

    /**
     * 작업위험성평가 결과검토 내용 조회 plantCd(사업장코드) evalYear(평가년도) evalNo(평가번호)
     * deptCd(부서코드) apprBizCd(결재문서코드)
     * 
     * @return 결재요청 내용 HTML
     * @throws Exception
     */
    @GetMapping("/createApprContents")
    public ResponseEntity<Map<String, String>> createApprContents(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = requestMapper.convertAsParameter(parameter);

        // 사업장코드
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";

        // 평가년도
        String evalYear = map.containsKey("evalYear") ? map.get("evalYear").toString() : "";

        // 평가번호
        String evalNo = map.containsKey("evalNo") ? map.get("evalNo").toString() : "";

        // 부서코드
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";

        // Html Title
        String apprRqstNm = map.containsKey("apprRqstNm") ? map.get("apprRqstNm").toString() : "";

        // 결재양식 url
        String apprBizCd = map.containsKey("apprBizCd") ? map.get("apprBizCd").toString() : "";

        // 결재양식 line url
        String linkUrl = map.containsKey("linkUrl") ? map.get("linkUrl").toString() : "";

        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(workRiskEval05Service.createApprContents(plantCd, evalYear, evalNo, deptCd, apprRqstNm, apprBizCd, linkUrl, defaultParam));
    }

}
