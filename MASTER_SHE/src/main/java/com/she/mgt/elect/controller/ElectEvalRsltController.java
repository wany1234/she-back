package com.she.mgt.elect.controller;

import com.google.common.base.Strings;
import com.she.common.model.DefaultParam;
import com.she.mgt.elect.service.ElectEvalRsltService;
import com.she.mgt.model.ElectEvalRslt;
import com.she.mgt.model.ElectEvalRsltStatus;
import com.she.utils.RequestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/mgt/electevalmgmt")
public class ElectEvalRsltController {
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private ElectEvalRsltService electEvalRsltService;

    /**
     * 법정선임자 본인평가결과 목록 조회
     * @param startYear
     *          시작년도
     * @param endYear
     *          종료년도
     * @param halfTypeCd
     *          구분코드
     * @param plantCd
     *          사업장
     * @param safElectTitlNo
     *          법정선임자 번호
     * @param evalNm
     *          평가명
     * @param deptCd
     *          주관부서 코드
     * @param deptSubYn
     *          하위부서 조회 여부
     * @param userId
     *          대상자
     * @param meUserId
     *          본인평가자
     * @param procStepCd
     *          단계코드
     * @param stateCd
     *          상태코드
     * @param evalIncompleteYn
     *          평가 미완료 여부
     * @return 법정선임자 본인평가 목록
     * @throws Exception
     */
    @GetMapping("/electevalmerslts")
    public ResponseEntity<List<ElectEvalRslt>> getElectEvalMeRslts(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
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

        // 사업장
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";

        // 법정선임자
        int safElectTitlNo = map.containsKey("safElectTitlNo") ? (Strings.isNullOrEmpty(map.get("safElectTitlNo").toString()) ? 0 : Integer.parseInt(map.get("safElectTitlNo").toString())) : 0;

        // 평가명
        String evalNm = map.containsKey("evalNm") ? map.get("evalNm").toString() : "";

        // 주관부서
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";

        // 하위부서 여부
        String deptSubYn = map.containsKey("deptSubYn") ? map.get("deptSubYn").toString() : "";

        // 대상자
        String userId = map.containsKey("userId") ? map.get("userId").toString() : "";

        // 본인평가자
        String meUserId = map.containsKey("meUserId") ? map.get("meUserId").toString() : "";

        // 단계
        String procStepCd = map.containsKey("procStepCd") ? map.get("procStepCd").toString() : "";

        // 상태
        String stateCd = map.containsKey("stateCd") ? map.get("stateCd").toString() : "";

        // 평가 미완료
        String evalIncompleteYn = map.containsKey("evalIncompleteYn") ? map.get("evalIncompleteYn").toString() : "";

        // 평가계획번호
        int evalPlanNo = map.containsKey("evalPlanNo") ? (Strings.isNullOrEmpty(map.get("evalPlanNo").toString()) ? 0 : Integer.parseInt(map.get("evalPlanNo").toString())) : 0;

        return ResponseEntity.ok().body(electEvalRsltService.getElectEvalMeRslts(startYear, endYear, halfTypeCd, plantCd, safElectTitlNo, evalNm, deptCd, deptSubYn, userId, meUserId, procStepCd, stateCd, evalIncompleteYn, evalPlanNo, defaultParam));
    }

    /**
     * 법정선임자 본인평가결과 상세 조회
     * @param evalUserNo
     *          평가대상자 번호
     * @return 법정선임자 본인평가결과 상세
     * @throws Exception
     */
    @GetMapping("/electevalmerslt/{evalUserNo}")
    public ResponseEntity<ElectEvalRslt> getElectEvalMeRslt(@PathVariable("evalUserNo") int evalUserNo, @ModelAttribute DefaultParam defaultParam) throws Exception {
        return ResponseEntity.ok().body(electEvalRsltService.getElectEvalMeRslt(evalUserNo, defaultParam));
    }

    /**
     * 법정선임자 본인평가결과 저장
     * @param electEvalRslt
     *          평가결과
     * @return 저장
     * @throws Exception
     */
    @PutMapping("/electevalmerslt")
    public ResponseEntity<ElectEvalRslt> saveElectEvalMeRslt(@RequestBody ElectEvalRslt electEvalRslt) throws Exception {
        return ResponseEntity.ok().body(electEvalRsltService.saveElectEvalMeRslt(electEvalRslt));
    }

    /**
     * 본인평가결과 확정취소
     * @param electEvalRslt
     *          평가결과
     * @return 결과
     * @throws Exception
     */
    @PutMapping("/electevalmerslt/cancel")
    public ResponseEntity<ElectEvalRslt> cancelElectEvalMeRslt(@RequestBody ElectEvalRslt electEvalRslt) throws Exception {
        return ResponseEntity.ok().body(electEvalRsltService.cancelElectEvalMeRslt(electEvalRslt));
    }

    /*********************************************************** 상위평가결과  ************************************************************/

    /**
     * 법정선임자 상위평가결과 목록 조회
     * @param startYear
     *          시작년도
     * @param endYear
     *          종료년도
     * @param halfTypeCd
     *          구분코드
     * @param plantCd
     *          사업장
     * @param safElectTitlNo
     *          법정선임자 번호
     * @param evalNm
     *          평가명
     * @param deptCd
     *          주관부서 코드
     * @param deptSubYn
     *          하위부서 조회 여부
     * @param userId
     *          대상자
     * @param meUserId
     *          본인평가자
     * @param procStepCd
     *          단계코드
     * @param stateCd
     *          상태코드
     * @param evalIncompleteYn
     *          평가 미완료 여부
     * @return 법정선임자 본인평가 목록
     * @throws Exception
     */
    @GetMapping("/electevaluprslts")
    public ResponseEntity<List<ElectEvalRslt>> getElectEvalUpRslts(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
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

        // 사업장
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";

        // 법정선임자
        int safElectTitlNo = map.containsKey("safElectTitlNo") ? (Strings.isNullOrEmpty(map.get("safElectTitlNo").toString()) ? 0 : Integer.parseInt(map.get("safElectTitlNo").toString())) : 0;

        // 평가명
        String evalNm = map.containsKey("evalNm") ? map.get("evalNm").toString() : "";

        // 주관부서
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";

        // 하위부서 여부
        String deptSubYn = map.containsKey("deptSubYn") ? map.get("deptSubYn").toString() : "";

        // 대상자
        String userId = map.containsKey("userId") ? map.get("userId").toString() : "";

        // 본인평가자
        String upUserId = map.containsKey("upUserId") ? map.get("upUserId").toString() : "";

        // 단계
        String procStepCd = map.containsKey("procStepCd") ? map.get("procStepCd").toString() : "";

        // 상태
        String stateCd = map.containsKey("stateCd") ? map.get("stateCd").toString() : "";

        // 평가 미완료
        String evalIncompleteYn = map.containsKey("evalIncompleteYn") ? map.get("evalIncompleteYn").toString() : "";

        // 평가계획번호
        int evalPlanNo = map.containsKey("evalPlanNo") ? (Strings.isNullOrEmpty(map.get("evalPlanNo").toString()) ? 0 : Integer.parseInt(map.get("evalPlanNo").toString())) : 0;

        return ResponseEntity.ok().body(electEvalRsltService.getElectEvalUpRslts(startYear, endYear, halfTypeCd, plantCd, safElectTitlNo, evalNm, deptCd, deptSubYn, userId, upUserId, procStepCd, stateCd, evalIncompleteYn, evalPlanNo, defaultParam));
    }

    /**
     * 법정선임자 상위평가결과 상세 조회
     * @param evalUserNo
     *          평가대상자 번호
     * @return 법정선임자 본인평가결과 상세
     * @throws Exception
     */
    @GetMapping("/electevaluprslt/{evalUserNo}")
    public ResponseEntity<ElectEvalRslt> getElectEvalUpRslt(@PathVariable("evalUserNo") int evalUserNo, @ModelAttribute DefaultParam defaultParam) throws Exception {
        return ResponseEntity.ok().body(electEvalRsltService.getElectEvalUpRslt(evalUserNo, defaultParam));
    }

    @PutMapping("/electevaluprslt")
    public ResponseEntity<ElectEvalRslt> saveElectEvalUpRslt(@RequestBody ElectEvalRslt electEvalRslt) throws Exception {
        return ResponseEntity.ok().body(electEvalRsltService.saveElectEvalUpRslt(electEvalRslt));
    }

    /**
     * 평가결과 현황 목록 조회
     * @param startYear
     *          시작년도
     * @param endYear
     *          종료년도
     * @param plantCd
     *          사업장
     * @param halfTypeCd
     *          구분
     * @param safElectTitlNo
     *          법정선임자 번호
     * @return 평가결과 현황 목록 조회
     * @throws Exception
     */
    @GetMapping("/electevalrsltstatus")
    public ResponseEntity<List<ElectEvalRsltStatus>> getElectEvalRsltStatus(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = requestMapper.convertAsParameter(parameter);

        // 대상년도
        String[] period = requestMapper.convertObjectListAsStringArray(map.get("period"));
        String startYear = "";
        String endYear = "";
        if (period != null && period.length == 2) {
            startYear = period[0];
            endYear = period[1];
        }

        // 사업장
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";

        // 구분
        String halfTypeCd = map.containsKey("halfTypeCd") ? map.get("halfTypeCd").toString() : "";

        // 법정선임자
        int safElectTitlNo = map.containsKey("safElectTitlNo") ? (!Strings.isNullOrEmpty(map.get("safElectTitlNo").toString()) ? Integer.parseInt(map.get("safElectTitlNo").toString()) : 0) : 0;

        return ResponseEntity.ok().body(electEvalRsltService.getElectEvalRsltStatus(startYear, endYear, plantCd, halfTypeCd, safElectTitlNo));
    }
}
