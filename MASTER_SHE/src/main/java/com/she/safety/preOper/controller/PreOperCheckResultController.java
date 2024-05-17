package com.she.safety.preOper.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.tomcat.util.codec.binary.Base64;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.she.common.model.DefaultParam;
import com.she.safety.model.FacilChkItemResult;
import com.she.safety.model.FacilChkMaster;
import com.she.safety.model.FacilChkResult;
import com.she.safety.model.ForEachType;
import com.she.safety.model.SafPreOperChkPsn;
import com.she.safety.preOper.service.PreOperCheckResultService;
import com.she.utils.RequestMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 설비점검결과
 */
@RestController
@RequestMapping("/api/saf/facility/")
@Api(value = "/api/saf/facility/", description = "설비점검 서비스")
public class PreOperCheckResultController {
    // TODO : queryString 변환을 위한 mapper 선언
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private PreOperCheckResultService preOperCheckResultService;

    private static final String PADDING_FOUR = "&nbsp;&nbsp;&nbsp;&nbsp;";
    private static final String STR_ENTER = " \n ";

    /**
     * 설비점검 사업장별 실적집계
     *
     * @param parameter
     *            검색조건
     * @return 설비점검 사업장별 실적집계 목록
     */
    @ApiOperation(value = "설비점검 사업장별 실적집계[SAF03020]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "plantCd", value = "사업장", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "year", value = "점검년도", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
    @GetMapping("preopercheckresultstatus")
    public ResponseEntity<List<HashMap<String, Object>>> getFacilityCheckResultStatus(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        // 사업장명
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 점검년도
        String year = map.containsKey("year") ? map.get("year").toString() : "";

        return ResponseEntity.ok().body(preOperCheckResultService.getFacilityCheckResultStatus(plantCd, year));
    }

    /**
     * 설비점검 사업장별 실적 세부집계
     *
     * @param parameter
     *            검색조건
     * @return 설비점검 사업장별 실적 세부집계 목록
     */
    @ApiOperation(value = "설비점검 사업장별 실적 세부집계[SAF03021]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "plantCd", value = "사업장", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "year", value = "점검년도", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "safCheckTypeCd", value = "점검종류", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "safFacilityTypeCd", value = "설비유형", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "deptCd", value = "주관부서", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
    @GetMapping("preopercheckresultstatussub")
    public ResponseEntity<List<HashMap<String, Object>>> getFacilityCheckResultStatusSub(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        // 사업장명
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 점검년도
        String year = map.containsKey("year") ? map.get("year").toString() : "";
        // 점검종류
        String safCheckTypeCd = map.containsKey("safCheckTypeCd") ? map.get("safCheckTypeCd").toString() : "";
        // 설비유형
        String safFacilityTypeCd = map.containsKey("safFacilityTypeCd") ? map.get("safFacilityTypeCd").toString() : "";
        // 주관부서
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";

        return ResponseEntity.ok().body(preOperCheckResultService.getFacilityCheckResultStatusSub(plantCd, year, safCheckTypeCd, safFacilityTypeCd, deptCd, defaultParam));
    }

    // ============================================================================================================
    // -----------------설비점검계획 start-------------------------------
    /**
     * 설비점검계획 목록 조회
     *
     * @param parameter
     *            검색조건
     * @return 설비점검 계획 목록
     */
    @GetMapping("/preoperchkplans")
    public ResponseEntity<List<FacilChkMaster>> getFacilChkPlans(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        // 사업장
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 점검상태
        String checkStepCd = map.containsKey("checkStepCd") ? map.get("checkStepCd").toString() : "";
        // 점검주관부서
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";
        // 하위부서 포함여부
        String deptSubYn = map.containsKey("deptSubYn") ? map.get("deptSubYn").toString() : "Y";
        // 점검대상부서
        String tDeptCd = map.containsKey("tDeptCd") ? map.get("tDeptCd").toString() : "";
        // 하위부서 포함여부
        String tDeptSubYn = map.containsKey("tDeptSubYn") ? map.get("tDeptSubYn").toString() : "Y";
        // 점검수행부서
        String pDeptCd = map.containsKey("pDeptCd") ? map.get("pDeptCd").toString() : "";
        // 하위부서 포함여부
        String pDeptSubYn = map.containsKey("pDeptSubYn") ? map.get("pDeptSubYn").toString() : "Y";
        // 기간(년도)
        String year = map.containsKey("year") ? map.get("year").toString() : "";
        // 점검유형
        String safCheckTypeCd = map.containsKey("safCheckTypeCd") ? map.get("safCheckTypeCd").toString() : "";
        // 기간
        String[] period = this.requestMapper.convertObjectListAsStringArray(map.get("period"));
        String startYmd = "";
        String endYmd = "";
        if (period != null && period.length == 2) {
            startYmd = period[0];
            endYmd = period[1];
        }
        // 완료여부
        String chkEndYn = map.containsKey("chkEndYn") ? map.get("chkEndYn").toString() : "";
        // 점검명
        String keyword = map.containsKey("keyword") ? map.get("keyword").toString() : "";

        return ResponseEntity.ok().body(preOperCheckResultService.getFacilChkPlans(plantCd, checkStepCd, deptCd, deptSubYn, tDeptCd, tDeptSubYn, pDeptCd, pDeptSubYn, chkEndYn, startYmd, endYmd, year, safCheckTypeCd, keyword, defaultParam));
    }

    /**
     * 설비점검계획 상세 조회
     *
     * @param safFacilChkNo
     *            검색조건
     * @return 설비점검 계획 상세
     */
    @GetMapping("/preoperchkplan/{safFacilChkNo}")
    public ResponseEntity<FacilChkMaster> getFacilChkPlan(@PathVariable("safFacilChkNo") int safFacilChkNo, @ModelAttribute DefaultParam defaultParam) throws Exception {
        return ResponseEntity.ok().body(preOperCheckResultService.getFacilChkPlan(safFacilChkNo, defaultParam));
    }

    /**
     * 설비점검 계획 등록
     *
     * @param facilChkMaster
     *            설비점검 계획
     * @return 설비점검번호
     * @throws Exception
     */
    @PostMapping("/preoperchkplan")
    public ResponseEntity<Integer> createFacilChkPlan(@RequestBody FacilChkMaster facilChkMaster) throws Exception {
        return ResponseEntity.ok().body(preOperCheckResultService.createFacilChkPlan(facilChkMaster) > 0 ? facilChkMaster.getSafFacilChkNo() : 0);
    }

    /**
     * 설비점검 계획 등록
     *
     * @param facilChkItemResult
     *            설비점검 계획
     * @return 설비점검번호
     * @throws Exception
     */
    @PostMapping("/sub/preoperchkplan")
    public ResponseEntity<Integer> createFacilChkSubPlan(@RequestBody FacilChkItemResult facilChkItemResult) throws Exception {
        return ResponseEntity.ok().body(preOperCheckResultService.createFacilChkSubPlan(facilChkItemResult));
    }

    /**
     * 설비점검 계획 수정
     *
     * @param facilChkMaster
     *            설비점검 계획
     * @return 설비점검번호
     * @throws Exception
     */
    @PutMapping("/preoperchkplan")
    public ResponseEntity<Integer> updateFacilChkPlan(@RequestBody FacilChkMaster facilChkMaster) throws Exception {
        return ResponseEntity.ok().body(preOperCheckResultService.updateFacilChkPlan(facilChkMaster) > 0 ? facilChkMaster.getSafFacilChkNo() : 0);
    }

    /**
     * 설비점검 계획 삭제
     *
     * @param safFacilChkNo
     *            설비점검 계획
     * @return 삭제 행 수
     * @throws Exception
     */
    @DeleteMapping("/preoperchkplan/typeitem/{safCheckTypeCd}/{safFacilChkNo}/{safFacilityCd}")
    public ResponseEntity<Integer> deleteFacilChkItem(@PathVariable("safCheckTypeCd") String safCheckTypeCd, @PathVariable("safFacilChkNo") int safFacilChkNo, @PathVariable("safFacilityCd") String safFacilityCd) throws Exception {
        return ResponseEntity.ok().body(preOperCheckResultService.deleteFacilChkItem(safCheckTypeCd, safFacilChkNo, safFacilityCd));
    }

    /**
     * 설비점검 계획 삭제
     *
     * @param safFacilChkNo
     *            설비점검 계획
     * @return 삭제 행 수
     * @throws Exception
     */
    @DeleteMapping("/preoperchkplan/{safFacilChkNo}")
    public ResponseEntity<Integer> deleteFacilChkPlan(@PathVariable("safFacilChkNo") int safFacilChkNo) throws Exception {
        return ResponseEntity.ok().body(preOperCheckResultService.deleteFacilChkPlan(safFacilChkNo));
    }

    // -----------------설비점검계획 end-------------------------------
    // -----------------설비점검결과 start-------------------------------

    /**
     * 설비점검계획 설비별 점검항목 조회
     *
     * @param parameter
     *            param
     * @return 설비점검결과 상세 정보
     * @throws Exception
     */
    @GetMapping("/preoperresultitems")
    public ResponseEntity<List<FacilChkItemResult>> getFacilChkResultItems(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        int safFacilChkNo = map.containsKey("safFacilChkNo") ? Integer.parseInt(map.get("safFacilChkNo").toString()) : 0;
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        String safCheckTypeCd = map.containsKey("safCheckTypeCd") ? map.get("safCheckTypeCd").toString() : "";
        String noPlanYn = map.containsKey("noPlanYn") ? map.get("noPlanYn").toString() : "";

        return ResponseEntity.ok().body(preOperCheckResultService.getFacilChkResultItems(safFacilChkNo, plantCd, safCheckTypeCd, noPlanYn, defaultParam));
    }

    /**
     * 설비점검계획 설비별 점검항목 (결과에서) 조회
     *
     * @param parameter
     *            param
     * @return 설비점검결과 상세 정보
     * @throws Exception
     */
    @GetMapping("/result/preoperresultitems")
    public ResponseEntity<List<FacilChkItemResult>> setFacilChkItems(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        int safFacilChkNo = map.containsKey("safFacilChkNo") ? Integer.parseInt(map.get("safFacilChkNo").toString()) : 0;
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        String safCheckTypeCd = map.containsKey("safCheckTypeCd") ? map.get("safCheckTypeCd").toString() : "";

        return ResponseEntity.ok().body(preOperCheckResultService.setFacilChkItems(safFacilChkNo, plantCd, safCheckTypeCd, defaultParam));
    }

    /**
     * 설비점검계획 설비별 점검유형 조회
     *
     * @param parameter
     *            param
     * @return 설비점검결과 상세 정보
     * @throws Exception
     */
    @GetMapping("/foreachtype/preopertypes")
    public ResponseEntity<List<ForEachType>> getForEachTypes(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        int safFacilChkNo = map.containsKey("safFacilChkNo") ? Integer.parseInt(map.get("safFacilChkNo").toString()) : 0;
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        String safCheckTypeCd = map.containsKey("safCheckTypeCd") ? map.get("safCheckTypeCd").toString() : "";

        return ResponseEntity.ok().body(preOperCheckResultService.getForEachTypes(safFacilChkNo, plantCd, safCheckTypeCd, defaultParam));
    }

    /**
     * 설비별 점검결과 전체완료
     *
     * @param facilChkMaster
     *            설비점검계획
     * @return 수정 행 수
     * @throws Exception
     */
    @PutMapping("/preoperchkplan/complete")
    public ResponseEntity<Integer> updateFacilChkResults(@RequestBody FacilChkMaster facilChkMaster) throws Exception {
        return ResponseEntity.ok().body(preOperCheckResultService.updateFacilChkResults(facilChkMaster));
    }

    /**
     * 설비점검 사업장별 실적집계
     *
     * @param parameter
     *            검색조건
     * @return 설비점검 사업장별 실적집계 목록
     */
    @GetMapping("/preoperchkresultstatus")
    public ResponseEntity<List<HashMap<String, Object>>> getFacilChkResultStatus(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        // 사업장명
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 점검년도
        String year = map.containsKey("year") ? map.get("year").toString() : "";

        List<HashMap<String, Object>> data = preOperCheckResultService.getFacilChkResultStatus(plantCd, year);
        return ResponseEntity.ok().body(data);
    }

    /**
     * 설비점검 사업장별 실적 세부집계
     *
     * @param parameter
     *            검색조건
     * @return 설비점검 사업장별 실적 세부집계 목록
     */
    @GetMapping("/preoperchkresultstatussub")
    public ResponseEntity<List<HashMap<String, Object>>> getFacilChkResultStatusSub(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        // 사업장명
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 점검년도
        String year = map.containsKey("year") ? map.get("year").toString() : "";
        // 점검종류
        String safCheckTypeCd = map.containsKey("safCheckTypeCd") ? map.get("safCheckTypeCd").toString() : "";
        // 설비유형
        String safFacilityTypeCd = map.containsKey("safFacilityTypeCd") ? map.get("safFacilityTypeCd").toString() : "";
        // 주관부서
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";
        // 하위부서 포함여부
        String deptSubYn = map.containsKey("deptSubYn") ? map.get("deptSubYn").toString() : "Y";

        return ResponseEntity.ok().body(preOperCheckResultService.getFacilChkResultStatusSub(plantCd, year, safCheckTypeCd, safFacilityTypeCd, deptCd, deptSubYn, defaultParam));
    }

    /**
     * 설비점검결과 목록 조회
     *
     * @return 설비점검 결과 목록
     * @throws Exception
     */
    @GetMapping("/preoperchkresulttable")
    public ResponseEntity<List<FacilChkResult>> getFacilChkResultTable(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        // 사업장명
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 검색 시작일
        String startDate = map.containsKey("startDate") ? map.get("startDate").toString() : "";
        // 검색 종료일
        String endDate = map.containsKey("endDate") ? map.get("endDate").toString() : "";
        // 종료여부
        String chkEndYn = map.containsKey("chkEndYn") ? map.get("chkEndYn").toString() : "";
        // 부서
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";
        // 설비유형별
        String safCheckTypeCd = map.containsKey("safCheckTypeCd") ? map.get("safCheckTypeCd").toString() : "";
        // 설비별
        String safFacilityTypeCd = map.containsKey("safFacilityTypeCd") ? map.get("safFacilityTypeCd").toString() : "";
        // 완료여부
        String completeYn = map.containsKey("completeYn") ? map.get("completeYn").toString() : "";
        // 완료여부
        String dtlYn = map.containsKey("dtlYn") ? map.get("dtlYn").toString() : "";

        return ResponseEntity.ok().body(preOperCheckResultService.getFacilChkResultTable(plantCd, startDate, endDate, chkEndYn, deptCd, safCheckTypeCd, safFacilityTypeCd, completeYn, dtlYn));
    }
    // -----------------설비점검결과 end-------------------------------

    /**
     * 가동전점검 출력
     *
     * @param parameter
     * @return
     * @throws Exception
     */
    @GetMapping("result/print/prestart")
    public @ResponseBody byte[] getPreStartPrint(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        String safCheckTypeCd = map.containsKey("safCheckTypeCd") ? map.get("safCheckTypeCd").toString() : "";
        int safFacilChkNo = map.containsKey("safFacilChkNo") ? Integer.parseInt(map.get("safFacilChkNo").toString()) : 0;
        String chkUserNm = map.containsKey("chkUserNm") ? map.get("chkUserNm").toString() : "";
        String reportName = map.containsKey("reportName") ? map.get("reportName").toString() : "";

        File file = preOperCheckResultService.getPreStartPrint(plantCd, safCheckTypeCd, safFacilChkNo, chkUserNm, reportName, defaultParam);

        if (file.exists() && file.isFile() && file != null) {
            InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
            byte[] encoded = Base64.encodeBase64(IOUtils.toByteArray(inputStream));
            String encodedString = new String(encoded);
            return encodedString.getBytes(StandardCharsets.UTF_8);
        } else {
            return null;
        }
    }

    /**
     * 가동전점검(excel) 출력
     *
     * @param parameter
     * @return
     * @throws Exception
     */
    @GetMapping("result/print/prestart/excel")
    public @ResponseBody byte[] getPreStartPrintExcel(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        String safCheckTypeCd = map.containsKey("safCheckTypeCd") ? map.get("safCheckTypeCd").toString() : "";
        int safFacilChkNo = map.containsKey("safFacilChkNo") ? Integer.parseInt(map.get("safFacilChkNo").toString()) : 0;
        String chkUserNm = map.containsKey("chkUserNm") ? map.get("chkUserNm").toString() : "";
        String reportName = map.containsKey("reportName") ? map.get("reportName").toString() : "";

        File file = preOperCheckResultService.getPreStartPrintExcel(plantCd, safCheckTypeCd, safFacilChkNo, chkUserNm, reportName, defaultParam);
        InputStream inputStream = null;
        try {
            inputStream = new BufferedInputStream(new FileInputStream(file));
            byte[] encoded = org.apache.commons.codec.binary.Base64.encodeBase64(IOUtils.toByteArray(inputStream));
            String encodedString = new String(encoded);
            return encodedString.getBytes("UTF-8");
        } catch (FileNotFoundException e) {
            return null;
        } finally {
            inputStream.close();
        }
    }

    /**
     * 가동전점검보고서 출력
     *
     * @param parameter
     * @return
     * @throws Exception
     */
    @GetMapping("result/print/prestartreport")
    public @ResponseBody byte[] getPreStartReportPrint(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        String safCheckTypeCd = map.containsKey("safCheckTypeCd") ? map.get("safCheckTypeCd").toString() : "";
        int safFacilChkNo = map.containsKey("safFacilChkNo") ? Integer.parseInt(map.get("safFacilChkNo").toString()) : 0;
        String chkUserNm = map.containsKey("chkUserNm") ? map.get("chkUserNm").toString() : "";
        String chkSchYmd = map.containsKey("chkSchYmd") ? map.get("chkSchYmd").toString() : "";
        String facilityNames = map.containsKey("facilityNames") ? map.get("facilityNames").toString() : "";

        File file = preOperCheckResultService.getPreStartReportPrint(plantCd, safCheckTypeCd, safFacilChkNo, chkUserNm, chkSchYmd, facilityNames, defaultParam);

        if (file.exists() && file.isFile() && file != null) {
            InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
            byte[] encoded = Base64.encodeBase64(IOUtils.toByteArray(inputStream));
            String encodedString = new String(encoded);
            return encodedString.getBytes(StandardCharsets.UTF_8);
        } else {
            return null;
        }
    }

    /**
     * 가동전점검보고서(excel) 출력
     *
     * @param parameter
     * @return
     * @throws Exception
     */
    @GetMapping("result/print/prestartreport/excel")
    public @ResponseBody byte[] getPreStartReportPrintExcel(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        String safCheckTypeCd = map.containsKey("safCheckTypeCd") ? map.get("safCheckTypeCd").toString() : "";
        int safFacilChkNo = map.containsKey("safFacilChkNo") ? Integer.parseInt(map.get("safFacilChkNo").toString()) : 0;
        String chkUserNm = map.containsKey("chkUserNm") ? map.get("chkUserNm").toString() : "";
        String chkSchYmd = map.containsKey("chkSchYmd") ? map.get("chkSchYmd").toString() : "";
        String facilityNames = map.containsKey("facilityNames") ? map.get("facilityNames").toString() : "";

        File file = preOperCheckResultService.getPreStartReportPrintExcel(plantCd, safCheckTypeCd, safFacilChkNo, chkUserNm, chkSchYmd, facilityNames, defaultParam);
        InputStream inputStream = null;
        try {
            inputStream = new BufferedInputStream(new FileInputStream(file));
            byte[] encoded = org.apache.commons.codec.binary.Base64.encodeBase64(IOUtils.toByteArray(inputStream));
            String encodedString = new String(encoded);
            return encodedString.getBytes("UTF-8");
        } catch (FileNotFoundException e) {
            return null;
        } finally {
            inputStream.close();
        }
    }

    /**
     * 가동전점검 점검자 조회
     *
     * @param safFacilChkNo
     * @param inspectorClassCd
     * @return
     * @throws Exception
     */
    @GetMapping("/preoperchkplan/inspector")
    public ResponseEntity<List<SafPreOperChkPsn>> getPreOperChkPsns(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = requestMapper.convertAsParameter(parameter);

        // 가동전점검 마스터 번호
        int safFacilChkNo = map.containsKey("safFacilChkNo") ? (map.get("safFacilChkNo") != null ? Integer.parseInt(map.get("safFacilChkNo").toString()) : Integer.parseInt("0")) : 0;

        // 점검자 구분 코드
        String inspectorClassCd = map.containsKey("inspectorClassCd") ? map.get("inspectorClassCd").toString() : "";

        return ResponseEntity.ok().body(preOperCheckResultService.getPreOperChkPsns(safFacilChkNo, inspectorClassCd));
    }
}