package com.she.safety.psm.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.she.common.model.DefaultParam;
import com.she.safety.model.SafPsmAuditRslt;
import com.she.safety.psm.service.AuditAnalysisService;
import com.she.utils.RequestMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * PSM 분석
 */
@RestController
@RequestMapping("/api/saf/psm/auditanalysis/")
@Api(value = "/api/saf/psm/auditanalysis/", description = "PSM 분석 서비스")
public class AuditAnalysisController {

    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private AuditAnalysisService auditAnalysisService;

    /**
     * PSM 분석 분석현황 부서 헤더 조회
     * 
     * @param parameter
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "PSM 분석 분석현황 부서 헤더 조회", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @GetMapping("/gridsetting")
    public ResponseEntity<List<HashMap<String, Object>>> getAnalysisstatusDept(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        String year = map.containsKey("year") ? map.get("year").toString() : "";
        String auditRsltNo = map.containsKey("auditRsltNo") ? map.get("auditRsltNo").toString() : "";

        return ResponseEntity.ok().body(auditAnalysisService.getAnalysisstatusDept(plantCd, year, auditRsltNo));
    }

    /**
     * PSM 분석 분석현황 조회
     * 
     * @param parameter
     *            검색조건
     * @return PSM 분석 목록
     */
    @ApiOperation(value = "PSM 분석 조회[]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "plantCd", value = "사업장", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "year", value = "감사년도", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "auditRsltNo", value = "auditRsltNo", required = false, dataType = "string", paramType = "query") })
    @GetMapping("analysisstatus")
    public ResponseEntity<List<HashMap<String, Object>>> getAnalysisstatus(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        String year = map.containsKey("year") ? map.get("year").toString() : "";
        String auditRsltNo = map.containsKey("auditRsltNo") ? map.get("auditRsltNo").toString() : "";
        String[] deptCds = this.requestMapper.convertObjectListAsStringArray(map.getOrDefault("deptCds", new ArrayList<String>()));

        return ResponseEntity.ok().body(auditAnalysisService.getAnalysisstatus(plantCd, year, auditRsltNo, deptCds));
    }

    /**
     * 선택박스에서 사용되는 자체검사 조회
     * 
     * @return 감사결과
     * @throws Exception
     */
    @GetMapping("auditResults")
    public ResponseEntity<List<SafPsmAuditRslt>> getAuditResults(@Param("defaultParam") DefaultParam defaultParam) throws Exception {
        return ResponseEntity.ok().body(this.auditAnalysisService.getAuditResults(defaultParam));
    }

}
