package com.she.safety.psm.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
import org.springframework.web.multipart.MultipartFile;

import com.she.common.model.DefaultParam;
import com.she.safety.model.SafPsmAuditRslt;
import com.she.safety.psm.service.AuditPlanService;
import com.she.utils.ConstVal;
import com.she.utils.RequestMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * PSM 계획 등록
 */
@RestController
@RequestMapping("/api/saf/psm/auditplan/")
@Api(value = "/api/saf/psm/auditplan/", description = "PSM 계획 등록 서비스")
public class AuditPlanController {

    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private AuditPlanService auditPlanService;

    /**
     * PSM 계획 조회
     * 
     * @param parameter
     *            검색조건
     * @return PSM 계획 목록
     */
    @ApiOperation(value = "PSM 계획 조회[]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ 
    		@ApiImplicitParam(name = "plantCd", value = "사업장", required = false, dataType = "string", paramType = "query"),
    		@ApiImplicitParam(name = "startYear", value = "시작년도", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "endYear", value = "끝년도", required = false, dataType = "string", paramType = "query"), 
            @ApiImplicitParam(name = "auditType", value = "(C)감사종류", required = false, dataType = "string", paramType = "query"),
    		@ApiImplicitParam(name = "psmProgState", value ="(C)진행상태 ", required = false, dataType = "string", paramType = "query")})
    @GetMapping("auditplans")
    public ResponseEntity<List<SafPsmAuditRslt>> getAuditPlans(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        return ResponseEntity.ok().body(auditPlanService.getAuditPlans(map, defaultParam));
    }

    /**
     * PSM 계획 등록
     * 
     * @param safPsmAuditRslt
     *            PSM 계획
     * @return 카운트
     */
    @ApiOperation(value = "PSM 계획 등록[]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "safPsmAuditRslt", value = "PSM 계획", required = false, dataType = "SafPsmAuditRslt", paramType = "body") })
    @PostMapping("auditplan")
    public ResponseEntity<Integer> createAuditPlan(@RequestParam("safPsmAuditRslt") String safPsmAuditRslt, @RequestParam("taskClassNm") String taskClassNm, @RequestParam("tempId") List<String> tempIds, @RequestParam("files") MultipartFile[] files) throws Exception {
        SafPsmAuditRslt objSafPsmAuditRslt = RequestMapper.convertJSONStringToClass(SafPsmAuditRslt.class, safPsmAuditRslt);
        return ResponseEntity.ok().body(auditPlanService.createAuditPlan(objSafPsmAuditRslt, taskClassNm, tempIds, files));
    }

    /**
     * PSM 계획 상세 조회
     * 
     * @param auditRsltNo
     *            PSM 계획 번호
     * @return PSM 계획 상세
     */
    @ApiOperation(value = "PSM 계획 상세조회[]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "auditRsltNo", value = "PSM 계획 번호", required = false, dataType = "int", paramType = "path") })
    @GetMapping("auditplan/{auditRsltNo}")
    public ResponseEntity<SafPsmAuditRslt> getAuditPlan(@PathVariable("auditRsltNo") int auditRsltNo, @ModelAttribute DefaultParam defaultParam) throws Exception {
        return ResponseEntity.ok().body(auditPlanService.getAuditPlan(auditRsltNo, defaultParam));
    }

    /**
     * PSM 계획 수정
     * 
     * @param safPsmAuditRslt
     *            PSM 계획
     * @return 키
     */
    @ApiOperation(value = "PSM 계획 수정[]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "safPsmAuditRslt", value = "PSM 계획", required = false, dataType = "SafPsmAuditRslt", paramType = "body") })
    @PutMapping("auditplan")
    public ResponseEntity<Integer> updateAuditPlan(@RequestParam("safPsmAuditRslt") String safPsmAuditRslt, @RequestParam("taskClassNm") String taskClassNm, @RequestParam("tempId") List<String> tempIds, @RequestParam("files") MultipartFile[] files) throws Exception {
        SafPsmAuditRslt objSafPsmAuditRslt = RequestMapper.convertJSONStringToClass(SafPsmAuditRslt.class, safPsmAuditRslt);
        return ResponseEntity.ok().body(auditPlanService.updateAuditPlan(objSafPsmAuditRslt, taskClassNm, tempIds, files));
    }

    @PutMapping("auditplanNew")
    public ResponseEntity<Integer> updateAuditPlanNew(@RequestBody SafPsmAuditRslt safPsmAuditRslt) throws Exception {
        return ResponseEntity.ok().body(auditPlanService.updateAuditPlan(safPsmAuditRslt, null, null, null));
    }

    /**
     * PSM 계획 삭제
     * 
     * @param safPsmAuditRslts
     *            PSM 계획
     * @return 키
     */
    @ApiOperation(value = "PSM 계획 삭제[]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "safPsmAuditRslts", value = "PSM 계획 번호", required = false, dataType = "List", paramType = "path") })
    @DeleteMapping("auditplan")
    public ResponseEntity<Integer> deleteAuditPlan(@RequestBody List<SafPsmAuditRslt> safPsmAuditRslts) throws Exception {
        return ResponseEntity.ok().body(auditPlanService.deleteAuditPlan(safPsmAuditRslts));
    }

    /**
     * 결제 요청
     * 
     * @param safPsmAuditRslt
     *            PSM 계획
     * @return 키
     */
    @ApiOperation(value = "PSM 계획 결제 요청[]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "safPsmAuditRslt", value = "PSM 계획", required = false, dataType = "SafPsmAuditRslt", paramType = "body") })
    @PutMapping("apprrequestauditplan")
    public ResponseEntity<Integer> apprRequestAuditPlan(@RequestParam("safPsmAuditRslt") String safPsmAuditRslt, @RequestParam("taskClassNm") String taskClassNm, @RequestParam("tempId") List<String> tempIds, @RequestParam("files") MultipartFile[] files) throws Exception {

        SafPsmAuditRslt objSafPsmAuditRslt = RequestMapper.convertJSONStringToClass(SafPsmAuditRslt.class, safPsmAuditRslt);
        objSafPsmAuditRslt.setPsmProgState(ConstVal.CODE_MASTER_PSM_PROG_STATE_APPR_REQUEST);

        return ResponseEntity.ok().body(auditPlanService.updateAuditPlan(objSafPsmAuditRslt, taskClassNm, tempIds, files));
    }

    /**
     * PSM 계획 중복 조회
     * 
     * @param plantCd
     *            사업장코드
     * @param auditYear
     *            감사년도
     * @return 키
     */
    @ApiOperation(value = "PSM 계획 중복 조회", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "plantCd", value = "사업장코드", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "auditYear", value = "감사년도", required = false, dataType = "string", paramType = "query") })
    @GetMapping("auditplanCheck")
    public ResponseEntity<Integer> auditplanCheck(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        String auditYear = map.containsKey("auditYear") ? map.get("auditYear").toString() : "";

        int returnNum = auditPlanService.auditplanCheck(plantCd, auditYear, defaultParam);
        return ResponseEntity.ok().body(returnNum);
    }

}
