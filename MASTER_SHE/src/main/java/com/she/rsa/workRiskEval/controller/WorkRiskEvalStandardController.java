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
import com.she.rsa.model.WorkRiskEval09Line;
import com.she.rsa.model.WorkRiskEval09Process;
import com.she.rsa.model.WorkRiskEval11UnitWork;
import com.she.rsa.model.WorkRiskEval12RefInd;
import com.she.rsa.model.WorkRiskEval13DtlUnitWork;
import com.she.rsa.model.WorkRiskEval13UnitWork;
import com.she.rsa.workRiskEval.service.WorkRiskEvalStandardService;
import com.she.utils.RequestMapper;

/**
 * 작업위험성평가 > 기준정보 
 */
@RestController
@RequestMapping("api/rsa/workRiskEvalStandard")
public class WorkRiskEvalStandardController {

    // queryString 변환을 위한 mapper 선언
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private WorkRiskEvalStandardService workRiskEvalStandardService;

    // 09 공정
    /**
     * 작업위험성평가 공정 조회
     * 
     * @return 작업위험성평가 공정 목록
     * @throws Exception
     */
    @GetMapping("/workRiskEvalPrcsList")
    public ResponseEntity<List<WorkRiskEval09Process>> getworkRiskEval09Lists(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 사업장
        String plantCd = map.containsKey("schPlantCd") ? map.get("schPlantCd").toString() : "";

        // 공정레벨
        String prcsLvlCd = map.containsKey("schPrcsLvlCd") ? map.get("schPrcsLvlCd").toString() : "";

        // 공정명
        String processNm = map.containsKey("schProcessNm") ? map.get("schProcessNm").toString() : "";

        return ResponseEntity.ok().body(workRiskEvalStandardService.getworkRiskEval09Lists(plantCd, prcsLvlCd, processNm, defaultParam));
    }

    /**
     * 작업위험성평가 공정 등록
     * 
     * @param WorkRiskEval09UnitWork
     * @return 작업위험성평가 단위작업 Key값
     * @throws Exception
     */
    @PostMapping("/workRiskEvalPrcs")
    public ResponseEntity<String> createWorkRiskEval09(@RequestBody WorkRiskEval09Process workRiskEval09Process) throws Exception {
        return ResponseEntity.ok().body(workRiskEvalStandardService.createWorkRiskEval09(workRiskEval09Process));
    }

    /**
     * 작업위험성평가 공정 수정
     * 
     * @param WorkRiskEval09UnitWork
     * @return 작업위험성평가 단위작업 Key값
     * @throws Exception
     */
    @PutMapping("/workRiskEvalPrcs")
    public ResponseEntity<String> updateWorkRiskEval09(@RequestBody WorkRiskEval09Process workRiskEval09Process) throws Exception {
        return ResponseEntity.ok().body(workRiskEvalStandardService.updateWorkRiskEval09(workRiskEval09Process));
    }

    /**
     * 작업위험성평가 공정 조회
     * 
     * @param parameter
     *            검색조건
     * @return 작업위험성평가 공정 상세조회
     * @throws Exception
     */
    @GetMapping("/workRiskEvalPrcsInfo/{plantCd}/{processCd}")
    public ResponseEntity<WorkRiskEval09Process> getWorkRiskEval09info(@PathVariable("plantCd") String plantCd, @PathVariable("processCd") String processCd, @ModelAttribute DefaultParam defaultParam) throws Exception {
        return ResponseEntity.ok().body(workRiskEvalStandardService.getWorkRiskEval09Info(plantCd, processCd, defaultParam));
    }

    /**
     * 작업위험성평가 공정 라인 조회
     * 
     * @return 작업위험성평가 공정 라인 목록
     * @throws Exception
     */
    @GetMapping("/workRiskEvalPrcsLineList")
    public ResponseEntity<List<WorkRiskEval09Line>> getworkRiskEval09LineLists(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 사업장
        String plantCd = map.containsKey("schPlantCd") ? map.get("schPlantCd").toString() : "";

        // 부서코드
        String processClsCd = map.containsKey("schProcessClsCd") ? map.get("schProcessClsCd").toString() : "";

        // 공정명
        String processNm = map.containsKey("schProcessNm") ? map.get("schProcessNm").toString() : "";

        return ResponseEntity.ok().body(workRiskEvalStandardService.getworkRiskEval09LineLists(plantCd, processClsCd, processNm, defaultParam));
    }

    /**
     * 작업위험성평가 상위공정 코드조회
     * 
     * @param plantCd,
     *            prcsLvlCd 레벨
     * @return 작업위험성평가 상위공정 코드조회
     * @throws Exception
     */
    @GetMapping("/workRiskEvalPrcsUpProcess/{plantCd}/{prcsLvlCd}")
    public ResponseEntity<List<WorkRiskEval09Process>> getWorkRiskEval09UpProcess(@PathVariable("plantCd") String plantCd, @PathVariable("prcsLvlCd") String prcsLvlCd, @ModelAttribute DefaultParam defaultParam) throws Exception {
        return ResponseEntity.ok().body(workRiskEvalStandardService.getWorkRiskEval09UpProcess(plantCd, prcsLvlCd, defaultParam));
    }
    
    // 11 단위작업
    /**
     * 작업위험성평가 계획 조회
     * 
     * @return 작업위험성평가 계획 목록
     * @throws Exception
     */
    @GetMapping("/workRiskEvalUnitWorkList")
    public ResponseEntity<List<WorkRiskEval11UnitWork>> getworkRiskEval11Lists(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 사업장
        String plantCd = map.containsKey("schPlantCd") ? map.get("schPlantCd").toString() : "";

        // 작업명
        String unitWorknm = map.containsKey("schUnitWorkNm") ? map.get("schUnitWorkNm").toString() : "";

        // 사용여부
        String useYn = map.containsKey("schUseYn") ? map.get("schUseYn").toString() : "";

        return ResponseEntity.ok().body(workRiskEvalStandardService.getworkRiskEval11Lists(plantCd, unitWorknm, useYn, defaultParam));
    }

    /**
     * 작업위험성평가 단위작업 등록
     * 
     * @param WorkRiskEval11UnitWork
     * @return 작업위험성평가 단위작업 Key값
     * @throws Exception
     */
    @PostMapping("/workRiskEvalUnitWork")
    public ResponseEntity<String> createWorkRiskEval11(@RequestBody WorkRiskEval11UnitWork workRiskEval11UnitWork) throws Exception {
        return ResponseEntity.ok().body(workRiskEvalStandardService.createWorkRiskEval11(workRiskEval11UnitWork));
    }

    /**
     * 작업위험성평가 단위작업 수정
     * 
     * @param WorkRiskEval11UnitWork
     * @return 작업위험성평가 단위작업 Key값
     * @throws Exception
     */
    @PutMapping("workRiskEvalUnitWork")
    public ResponseEntity<String> updateWorkRiskEval11(@RequestBody WorkRiskEval11UnitWork workRiskEval11UnitWork) throws Exception {
        return ResponseEntity.ok().body(workRiskEvalStandardService.updateWorkRiskEval11(workRiskEval11UnitWork));
    }

    /**
     * 작업위험성평가 단위작업 조회
     * 
     * @param parameter
     *            검색조건
     * @return 작업위험성평가 단위작업 상세조회
     * @throws Exception
     */
    @GetMapping("/workRiskEvalUnitWorkInfo/{plantCd}/{unitWorkCd}")
    public ResponseEntity<WorkRiskEval11UnitWork> getWorkRiskEval11info(@PathVariable("plantCd") String plantCd, @PathVariable("unitWorkCd") String unitWorkCd, @ModelAttribute DefaultParam defaultParam) throws Exception {
        return ResponseEntity.ok().body(workRiskEvalStandardService.getWorkRiskEval11Info(plantCd, unitWorkCd, defaultParam));
    }
    
    // 13 세부작업
    /**
     * 작업위험성평가 계획 조회
     * 
     * @return 작업위험성평가 계획 목록
     * @throws Exception
     */
    @GetMapping("/workRiskEvalDtlWorkList")
    public ResponseEntity<List<WorkRiskEval13UnitWork>> getworkRiskEval13Lists(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 사업장
        String plantCd = map.containsKey("schPlantCd") ? map.get("schPlantCd").toString() : "";

        // 부서
        String deptCd = map.containsKey("schDeptCd") ? map.get("schDeptCd").toString() : "";

        // 공정코드
        String processCd = map.containsKey("schProcessCd") ? map.get("schProcessCd").toString() : "";

        // 작업명
        String unitWorkNm = map.containsKey("schUnitWorkNm") ? map.get("schUnitWorkNm").toString() : "";

        // 사용여부
        String useYn = map.containsKey("schUseYn") ? map.get("schUseYn").toString() : "";

        return ResponseEntity.ok().body(workRiskEvalStandardService.getworkRiskEval13Lists(plantCd, deptCd, processCd, unitWorkNm, useYn, defaultParam));
    }

    /**
     * 작업위험성평가 공정검색팝업 공정 조회
     * 
     * @return 작업위험성평가 공정검색팝업 공정 목록
     * @throws Exception
     */
    @GetMapping("/workRiskEvalDtlWorkProcessList")
    public ResponseEntity<List<WorkRiskEval13UnitWork>> getworkRiskEval13ProcessLists(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 사업장
        String plantCd = map.containsKey("schPlantCd") ? map.get("schPlantCd").toString() : "";

        // 공정레벨
        String prcsLvlCd = map.containsKey("schPrcsLvlCd") ? map.get("schPrcsLvlCd").toString() : "";

        // 작업공정명
        String processNm = map.containsKey("schProcessNm") ? map.get("schProcessNm").toString() : "";

        return ResponseEntity.ok().body(workRiskEvalStandardService.getworkRiskEval13ProcessLists(plantCd, prcsLvlCd, processNm, defaultParam));
    }

    /**
     * 작업위험성평가 단위작업검색팝업 단위작업 조회
     * 
     * @return 작업위험성평가 단위작업검색팝업 단위작업 목록
     * @throws Exception
     */
    @GetMapping("/workRiskEvalDtlWorkUnitList")
    public ResponseEntity<List<WorkRiskEval13UnitWork>> getworkRiskEval13UnitLists(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 사업장
        String plantCd = map.containsKey("schPlantCd") ? map.get("schPlantCd").toString() : "";

        // 단위작업명
        String unitWorkNm = map.containsKey("schUnitWorkNm") ? map.get("schUnitWorkNm").toString() : "";

        return ResponseEntity.ok().body(workRiskEvalStandardService.getworkRiskEval13UnitLists(plantCd, unitWorkNm, defaultParam));
    }

    /**
     * 작업위험성평가 부서공정별 조회
     * 
     * @param parameter
     *            검색조건
     * @return 작업위험성평가 부서공정별 상세조회
     * @throws Exception
     */
    @GetMapping("/workRiskEvalDtlWorkInfo/{plantCd}/{deptCd}/{processCd}/{unitWorkCd}")
    public ResponseEntity<WorkRiskEval13UnitWork> getWorkRiskEval13info(@PathVariable("plantCd") String plantCd, @PathVariable("deptCd") String deptCd, @PathVariable("processCd") String processCd, @PathVariable("unitWorkCd") String unitWorkCd, @ModelAttribute DefaultParam defaultParam) throws Exception {
        return ResponseEntity.ok().body(workRiskEvalStandardService.getWorkRiskEval13Info(plantCd, deptCd, processCd, unitWorkCd, defaultParam));
    }

    /**
     * 작업위험성평가 부서공정 등록
     * 
     * @param WorkRiskEval13UnitWork
     * @return 작업위험성평가 부서공정 Key값
     * @throws Exception
     */
    @PostMapping("/workRiskEvalDtlWork")
    public ResponseEntity<String> createWorkRiskEval13(@RequestBody WorkRiskEval13UnitWork workRiskEval13UnitWork) throws Exception {
        return ResponseEntity.ok().body(workRiskEvalStandardService.createWorkRiskEval13(workRiskEval13UnitWork));
    }

    /**
     * 작업위험성평가 부서공정 수정
     * 
     * @param WorkRiskEval13UnitWork
     * @return 작업위험성평가 부서공정 Key값
     * @throws Exception
     */
    @PutMapping("/workRiskEvalDtlWork")
    public ResponseEntity<String> updateWorkRiskEval13(@RequestBody WorkRiskEval13UnitWork workRiskEval13UnitWork) throws Exception {
        return ResponseEntity.ok().body(workRiskEvalStandardService.updateWorkRiskEval13(workRiskEval13UnitWork));
    }

    /**
     * 작업위험성평가 부서공정별 세부작업 조회
     * 
     * @param parameter
     *            검색조건
     * @return 작업위험성평가 부서공정별 세부작업 목록 조회
     * @throws Exception
     */
    @GetMapping("/workRiskEvalDtlWorkDtlUnitWorkList/{plantCd}/{deptCd}/{processCd}/{unitWorkCd}")
    public ResponseEntity<List<WorkRiskEval13DtlUnitWork>> getWorkRiskEval13DtlUnitWorkList(@PathVariable("plantCd") String plantCd, @PathVariable("deptCd") String deptCd, @PathVariable("processCd") String processCd, @PathVariable("unitWorkCd") String unitWorkCd, @ModelAttribute DefaultParam defaultParam) throws Exception {
        return ResponseEntity.ok().body(workRiskEvalStandardService.getWorkRiskEval13DtlUnitWorkList(plantCd, deptCd, processCd, unitWorkCd, defaultParam));
    }
    
    // 12 평가지표
    /**
     * 작업위험성평가 평가지표 조회
     * 
     * @return 작업위험성평가 평가지표 목록
     * @throws Exception
     */
    @GetMapping("/workRiskEvalIndiList")
    public ResponseEntity<List<WorkRiskEval12RefInd>> getworkRiskEval12Lists(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 사업장
        String plantCd = map.containsKey("schPlantCd") ? map.get("schPlantCd").toString() : "";

        // 구분
        String indTypeCd = map.containsKey("schIndTypeCd") ? map.get("schIndTypeCd").toString() : "";

        // 설정명
        String setNm = map.containsKey("schSetNm") ? map.get("schSetNm").toString() : "";

        // 사용여부
        String useYn = map.containsKey("schUseYn") ? map.get("schUseYn").toString() : "";

        return ResponseEntity.ok().body(workRiskEvalStandardService.getworkRiskEval12Lists(plantCd, indTypeCd, setNm, useYn, defaultParam));
    }

    /**
     * 작업위험성평가 평가지표 등록
     * 
     * @param WorkRiskEval12RefInd
     * @return 작업위험성평가 평가지표 Key값
     * @throws Exception
     */
    @PostMapping("/workRiskEvalIndi")
    public ResponseEntity<String> createWorkRiskEval12(@RequestBody WorkRiskEval12RefInd workRiskEval12RefInd) throws Exception {
        return ResponseEntity.ok().body(workRiskEvalStandardService.createWorkRiskEval12(workRiskEval12RefInd));
    }

    /**
     * 작업위험성평가 평가지표 수정
     * 
     * @param WorkRiskEval12RefInd
     * @return 작업위험성평가 평가지표 Key값
     * @throws Exception
     */
    @PutMapping("/workRiskEvalIndi")
    public ResponseEntity<String> updateWorkRiskEval12(@RequestBody WorkRiskEval12RefInd workRiskEval12RefInd) throws Exception {
        return ResponseEntity.ok().body(workRiskEvalStandardService.updateWorkRiskEval12(workRiskEval12RefInd));
    }

    /**
     * 작업위험성평가 평가지표 조회
     * 
     * @param parameter
     *            검색조건
     * @return 작업위험성평가 평가지표 상세조회
     * @throws Exception
     */
    @GetMapping("/workRiskEvalIndiInfo/{plantCd}/{indTypeCd}/{setVal}")
    public ResponseEntity<WorkRiskEval12RefInd> getWorkRiskEval12info(@PathVariable("plantCd") String plantCd, @PathVariable("indTypeCd") String indTypeCd, @PathVariable("setVal") String setVal, @ModelAttribute DefaultParam defaultParam) throws Exception {
        return ResponseEntity.ok().body(workRiskEvalStandardService.getWorkRiskEval12Info(plantCd, indTypeCd, setVal, defaultParam));
    }
}
