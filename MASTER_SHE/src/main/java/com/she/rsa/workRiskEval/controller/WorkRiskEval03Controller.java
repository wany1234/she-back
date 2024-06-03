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
package com.she.rsa.workRiskEval.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import com.she.rsa.model.WorkRiskEval03Rslt;
import com.she.rsa.model.WorkRiskEval03RsltDetailUnitWork;
import com.she.rsa.model.WorkRiskEval03RsltDetailWork;
import com.she.rsa.model.WorkRiskEval03RsltEvalParty;
import com.she.rsa.model.WorkRiskEval03RsltRefInd;
import com.she.rsa.workRiskEval.service.WorkRiskEval03Service;
import com.she.utils.RequestMapper;

/**
 * 작업위험성평가 결과
 */
@RestController
@RequestMapping("api/rsa/workRiskEval03")
public class WorkRiskEval03Controller {

    // queryString 변환을 위한 mapper 선언
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private WorkRiskEval03Service workRiskEval03Service;

    /**
     * 작업위험성평가 결과 조회
     * 
     * @return 작업위험성평가 결과 목록
     * @throws Exception
     */
    @GetMapping("/workRiskEval03List")
    public ResponseEntity<List<WorkRiskEval03Rslt>> getworkRiskEval03List(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 사업장
        String plantCd = map.containsKey("schPlantCd") ? map.get("schPlantCd").toString() : "";

        // 부서코드
        String deptCd = map.containsKey("schDeptCd") ? map.get("schDeptCd").toString() : "";

        // 퍙가구분 (정시/수기)
        String evalTypeCd = map.containsKey("schEvalType") ? map.get("schEvalType").toString() : "";

        // 공정레벨
        String prcsLvlCd = map.containsKey("schPrcsLvlCd") ? map.get("schPrcsLvlCd").toString() : "";

        // 공정명
        String processNm = map.containsKey("schProcessNm") ? map.get("schProcessNm").toString() : "";

        // 공정명
        String confirmYn = map.containsKey("schConfirmYn") ? map.get("schConfirmYn").toString() : "";

        // 평가년도
        String[] evalYear = this.requestMapper.convertObjectListAsStringArray(map.get("schEvalYear"));

        String startYear = "";
        String endYear = "";

        if (evalYear != null && evalYear.length == 2) {
            startYear = evalYear[0];
            endYear = evalYear[1];
        }

        String prcsNm01 = "";
        String prcsNm02 = "";
        String prcsNm03 = "";

        if ("1".equals(prcsLvlCd)) {
            prcsNm01 = processNm;
        } else if ("2".equals(prcsLvlCd)) {
            prcsNm02 = processNm;
        } else if ("3".equals(prcsLvlCd)) {
            prcsNm03 = processNm;
        }

        return ResponseEntity.ok().body(workRiskEval03Service.getworkRiskEval03List(plantCd, deptCd, evalTypeCd, startYear, endYear, prcsNm01, prcsNm02, prcsNm03, confirmYn, defaultParam));
    }

    /**
     * 작업위험성평가 결과 상세조회
     * 
     * @param parameter
     *            검색조건
     * @return 작업위험성평가 결과 상세조회
     * @throws Exception
     */
    @GetMapping("/workRiskEval03Info/{plantCd}/{evalYear}/{evalNo}/{deptCd}/{processCd}/{unitWorkCd}")
    public ResponseEntity<WorkRiskEval03Rslt> getWorkRiskEval03Info(@PathVariable("plantCd") String plantCd, @PathVariable("evalYear") String evalYear, @PathVariable("evalNo") String evalNo, @PathVariable("deptCd") String deptCd, @PathVariable("processCd") String processCd, @PathVariable("unitWorkCd") String unitWorkCd,
            @ModelAttribute DefaultParam defaultParam) throws Exception {
        return ResponseEntity.ok().body(workRiskEval03Service.getWorkRiskEval03Info(plantCd, evalYear, evalNo, deptCd, processCd, unitWorkCd, defaultParam));
    }

    /**
     * 작업위험성평가 결과 세부작업 조회
     * 
     * @return 작업위험성평가 결과 세부작업 목록
     * @throws Exception
     */
    @GetMapping("/detailWorkList")
    public ResponseEntity<List<WorkRiskEval03RsltDetailWork>> getDetailWorkList(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 사업장코드
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";

        // 평가년도
        String evalYear = map.containsKey("evalYear") ? map.get("evalYear").toString() : "";

        // 평가번호
        String evalNo = map.containsKey("evalNo") ? map.get("evalNo").toString() : "";

        // 부서코드
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";

        // 주요설비코드
        String processCd = map.containsKey("processCd") ? map.get("processCd").toString() : "";

        // 작업코드
        String unitWorkCd = map.containsKey("unitWorkCd") ? map.get("unitWorkCd").toString() : "";

        return ResponseEntity.ok().body(workRiskEval03Service.getDetailWorkList(plantCd, deptCd, evalYear, evalNo, processCd, unitWorkCd, defaultParam));
    }

    /**
     * 작업위험성평가 결과 평가참여자 조회
     * 
     * @return 작업위험성평가 결과 평가참여자 목록
     * @throws Exception
     */
    @GetMapping("/evalPartyList")
    public ResponseEntity<List<WorkRiskEval03RsltEvalParty>> getEvalPartyList(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 사업장코드
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";

        // 평가년도
        String evalYear = map.containsKey("evalYear") ? map.get("evalYear").toString() : "";

        // 평가번호
        String evalNo = map.containsKey("evalNo") ? map.get("evalNo").toString() : "";

        // 부서코드
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";

        // 주요설비코드
        String processCd = map.containsKey("processCd") ? map.get("processCd").toString() : "";

        // 작업코드
        String unitWorkCd = map.containsKey("unitWorkCd") ? map.get("unitWorkCd").toString() : "";

        return ResponseEntity.ok().body(workRiskEval03Service.getEvalPartyList(plantCd, deptCd, evalYear, evalNo, processCd, unitWorkCd, defaultParam));
    }

    /**
     * 작업위험성평가 결과 등록
     * 
     * @param WorkRiskEval03Rslt
     *            작업위험성평가 공정설정 평가대상공정 List
     * @return 작업위험성평가 공정설정 평가대상공정 관리 Key값
     * @throws Exception
     */
    @PostMapping("/workRiskEval03")
    public ResponseEntity<String> createWorkRiskEval03(@RequestBody WorkRiskEval03Rslt workRiskEval03Rslt) throws Exception {
        workRiskEval03Rslt.setRsltCfmYn("N");
        return ResponseEntity.ok().body(workRiskEval03Service.createWorkRiskEval03(workRiskEval03Rslt));
    }

    /**
     * 작업위험성평가 공정설정 평가대상공정 확정
     * 
     * @param WorkRiskEval03Rslt
     *            작업위험성평가 공정설정 평가대상공정 List
     * @return 작업위험성평가 공정설정 평가대상공정 관리 Key값
     * @throws Exception
     */
    @PutMapping("/workRiskEval03")
    public ResponseEntity<String> confirmWorkRiskEval03(@RequestBody WorkRiskEval03Rslt workRiskEval03Rslt) throws Exception {
        workRiskEval03Rslt.setRsltCfmYn("Y");
        return ResponseEntity.ok().body(workRiskEval03Service.createWorkRiskEval03(workRiskEval03Rslt));
    }

    /**
     * 작업위험성평가 결과 세부작업 팝업
     * 
     * @param parameter
     *            검색조건
     * @return 작업위험성평가 결과 세부작업 팝업 상세조회
     * @throws Exception
     */
    @GetMapping("/workRiskEval03DtlWorkInfo")
    public ResponseEntity<WorkRiskEval03RsltDetailUnitWork> getWorkRiskEval03DtlWorkInfo(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 사업장코드
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";

        // 평가년도
        String evalYear = map.containsKey("evalYear") ? map.get("evalYear").toString() : "";

        // 평가번호
        String evalNo = map.containsKey("evalNo") ? map.get("evalNo").toString() : "";

        // 부서코드
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";

        // 주요설비코드
        String processCd = map.containsKey("processCd") ? map.get("processCd").toString() : "";

        // 작업코드
        String unitWorkCd = map.containsKey("unitWorkCd") ? map.get("unitWorkCd").toString() : "";

        // 세부작업순번
        String dtlWkSeq = map.containsKey("dtlWkSeq") ? map.get("dtlWkSeq").toString() : "";

        return ResponseEntity.ok().body(workRiskEval03Service.getWorkRiskEval03DtlWorkInfo(plantCd, evalYear, evalNo, deptCd, processCd, unitWorkCd, dtlWkSeq, defaultParam));
    }

    /**
     * 작업위험성평가 결과 관련지표 콤보
     * 
     * @param parameter
     *            검색조건
     * @return 작업위험성평가 관련지표 콤보 조회
     * @throws Exception
     */
    @GetMapping("/workRiskEval03RefIndItemList/{plantCd}/{indTypeCd}")
    public ResponseEntity<List<WorkRiskEval03RsltRefInd>> getWorkRiskEval03RefIndItemList(@PathVariable("plantCd") String plantCd, @PathVariable("indTypeCd") String indTypeCd, @ModelAttribute DefaultParam defaultParam) throws Exception {
        return ResponseEntity.ok().body(workRiskEval03Service.getWorkRiskEval03RefIndItemList(plantCd, indTypeCd, defaultParam));
    }

    /**
     * 작업위험성평가 결과 세부작업 등록
     * 
     * @param workRiskEval03RsltDetailUnitWork
     * @return 작업위험성평가 결과 세부작업 관리 Key값
     * @throws Exception
     */
    @PostMapping("/workRiskEval03WorkPop")
    public ResponseEntity<String> createWorkRiskEval03WorkPop(@RequestBody WorkRiskEval03RsltDetailUnitWork workRiskEval03RsltDetailUnitWork) throws Exception {
        return ResponseEntity.ok().body(workRiskEval03Service.createWorkRiskEval03WorkPop(workRiskEval03RsltDetailUnitWork));
    }

    /**
     * 작업위험성평가 결과 세부작업 수정
     * 
     * @param workRiskEval03RsltDetailUnitWork
     * @return 작업위험성평가 결과 세부작업 관리 Key값
     * @throws Exception
     */
    @PutMapping("/workRiskEval03WorkPop")
    public ResponseEntity<String> updateWorkRiskEval03WorkPop(@RequestBody WorkRiskEval03RsltDetailUnitWork workRiskEval03RsltDetailUnitWork) throws Exception {
        return ResponseEntity.ok().body(workRiskEval03Service.updateWorkRiskEval03WorkPop(workRiskEval03RsltDetailUnitWork));
    }

    /**
     * 작업위험성평가 결과 세부작업 조회
     * 
     * @return 작업위험성평가 결과 세부작업 목록
     * @throws Exception
     */
    @GetMapping("/workRiskEval03RefIndList")
    public ResponseEntity<List<WorkRiskEval03RsltDetailUnitWork>> getWorkRiskEval03RefIndList(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 사업장코드
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";

        // 평가년도
        String evalYear = map.containsKey("evalYear") ? map.get("evalYear").toString() : "";

        // 평가번호
        String evalNo = map.containsKey("evalNo") ? map.get("evalNo").toString() : "";

        // 부서코드
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";

        // 주요시설코드
        String processCd = map.containsKey("processCd") ? map.get("processCd").toString() : "";

        // 작업코드
        String unitWorkCd = map.containsKey("unitWorkCd") ? map.get("unitWorkCd").toString() : "";

        // 세부작업명
        String dtlWkNm = map.containsKey("dtlWkNm") ? map.get("dtlWkNm").toString() : "";

        return ResponseEntity.ok().body(workRiskEval03Service.getWorkRiskEval03RefIndList(plantCd, evalYear, evalNo, deptCd, processCd, unitWorkCd, dtlWkNm, defaultParam));
    }

    /**
     * 작업위험성평가 공정설정 세부작업 정렬 수정
     * 
     * @param WorkRiskEval03RsltDetailUnitWork
     *            세부작업 List
     * @return 작업위험성평가 세부작업 관리 Key값
     * @throws Exception
     */
    @PutMapping("/detailWorkSortOrderEdit")
    public ResponseEntity<String> updateDetailWorkSortOrder(@RequestBody WorkRiskEval03Rslt workRiskEval03Rslt) throws Exception {
        return ResponseEntity.ok().body(workRiskEval03Service.updateDetailWorkSortOrder(workRiskEval03Rslt));
    }
}
