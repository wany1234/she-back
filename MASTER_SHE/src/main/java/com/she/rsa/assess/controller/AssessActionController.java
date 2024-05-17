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
package com.she.rsa.assess.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.she.manage.mapper.CodeMasterMapper;
import com.she.rsa.assess.service.AssessActionService;
import com.she.rsa.model.AssessAction;
import com.she.rsa.model.AssessType;
import com.she.rsa.model.RiskAssess;
import com.she.utils.RequestMapper;

/**
 * 평가계획
 */
@RestController
@RequestMapping("api/rsa/assess")
public class AssessActionController {
    @Autowired
    private AssessActionService assessActionService;

    // queryString 변환을 위한 mapper 선언
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private CodeMasterMapper codeMasterMapper;

    private static final Logger logger = LoggerFactory.getLogger(AssessActionController.class);

    /**
     * 위험성평가 조회
     *
     * @param parameter
     *            검색조건
     * @return 위험성평가 목록
     * @throws Exception
     */
    @GetMapping("/riskassesses")
    public ResponseEntity<List<RiskAssess>> getRiskAssesses(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 직무 ID
        int jobId = map.containsKey("jobId") ? Integer.parseInt("".equals(map.get("jobId").toString()) ? "0" : map.get("jobId").toString()) : 0;
        // 평가계획 ID
        String assessPlanNo = map.containsKey("assessPlanNo") ? map.get("assessPlanNo").toString() : "";

        return ResponseEntity.ok().body(assessActionService.getRiskAssesses(jobId, assessPlanNo));
    }

    /**
     * 위험성평가 항목 생성 (KRAS)
     *
     * @param riskAssess
     *            위험성평가 항목 List
     * @return 위험성평가 항목 Key값
     * @throws Exception
     */
    @PostMapping("/assessaction")
    public ResponseEntity<Integer> createRiskKras(@RequestBody RiskAssess riskAssess) throws Exception {
        return ResponseEntity.ok().body(assessActionService.createRiskKras(riskAssess));
    }

    /**
     * 위험성평가 항목 생성 (KRAS)
     *
     * @param riskAssess
     *            위험성평가 항목 List
     * @return 위험성평가 항목 Key값
     * @throws Exception
     */
    @PostMapping("/assessaction/list")
    public ResponseEntity<Integer> createRiskKrasList(@RequestBody List<RiskAssess> riskAssess) throws Exception {
        return ResponseEntity.ok().body(assessActionService.createRiskKrasList(riskAssess));
    }

    /**
     * 위험 Matrix 조회
     *
     * @param parameter
     *            검색조건
     * @return 위험 Matrix 목록
     * @throws Exception
     */
    @GetMapping("/riskmatrixes")
    public ResponseEntity<AssessType> getRiskMatrixes(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 평가기법
        int assessTypeNo = map.containsKey("assessTypeNo") ? Integer.parseInt("".equals(map.get("assessTypeNo").toString()) ? "0" : map.get("assessTypeNo").toString()) : 0;

        AssessType assessType = new AssessType();

        assessType.setFrequencyList(assessActionService.getFrequencyRiskMatrixes(assessTypeNo));
        assessType.setStrongList(assessActionService.getStrongRiskMatrixes(assessTypeNo));
        assessType.setRiskList(assessActionService.getRiskMatrixes(assessTypeNo));

        return ResponseEntity.ok().body(assessType);
    }

    /**
     * 평가계획 job 조회 (JSA)
     *
     * @param parameter
     *            검색조건
     * @return 평가직무 목록
     * @throws Exception
     */
    @GetMapping("/assessactionsjsajob")
    public ResponseEntity<List<AssessAction>> getAssessActionsJsaJob(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 평가계획 ID
        String assessPlanNo = map.containsKey("assessPlanNo") ? map.get("assessPlanNo").toString() : "";
        // 사업장코드
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 부서코드
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";
        // 공정코드
        String processCd = map.containsKey("processCd") ? map.get("processCd").toString() : "";
        // 작업명
        String jobNm = map.containsKey("jobNm") ? map.get("jobNm").toString() : "";

        return ResponseEntity.ok().body(assessActionService.getAssessActionsJsaJob(assessPlanNo, plantCd, deptCd, processCd, jobNm));
    }

    /**
     * 평가계획 조회 (JSA)
     *
     * @param parameter
     *            검색조건
     * @return 평가직무단계 목록
     * @throws Exception
     */
    @GetMapping("/assessactionsjsa")
    public ResponseEntity<List<AssessAction>> getAssessActionsJsa(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 평가계획 ID
        int assessPlanNo = map.containsKey("assessPlanNo") ? Integer.parseInt("".equals(map.get("assessPlanNo").toString()) ? "0" : map.get("assessPlanNo").toString()) : 0;
        // 부서코드
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";
        // 작업ID
        int jobId = map.containsKey("jobId") ? Integer.parseInt("".equals(map.get("jobId").toString()) ? "0" : map.get("jobId").toString()) : 0;
        // 공정코드
        String processCd = map.containsKey("processCd") ? map.get("processCd").toString() : "";

        return ResponseEntity.ok().body(assessActionService.getAssessActionsJsa(assessPlanNo, deptCd, jobId, processCd));
    }

    /**
     * 위험성평가 조회 (JSA)
     *
     * @param parameter
     *            검색조건
     * @return 위험성평가 목록
     * @throws Exception
     */
    @GetMapping("/riskassessesjsa")
    public ResponseEntity<List<RiskAssess>> getRiskAssessesJsa(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 직무 ID
        int jobId = map.containsKey("jobId") ? Integer.parseInt("".equals(map.get("jobId").toString()) ? "0" : map.get("jobId").toString()) : 0;
        int jobStepNo = map.containsKey("jobStepNo") ? Integer.parseInt("".equals(map.get("jobStepNo").toString()) ? "0" : map.get("jobStepNo").toString()) : 0;
        int assessPlanNo = map.containsKey("assessPlanNo") ? Integer.parseInt("".equals(map.get("assessPlanNo").toString()) ? "0" : map.get("assessPlanNo").toString()) : 0;

        return ResponseEntity.ok().body(assessActionService.getRiskAssessesJsa(jobId, jobStepNo, assessPlanNo));
    }

    /**
     * 위험성평가 항목 생성 (JSA)
     *
     * @param riskAssess
     *            위험성평가 항목 List
     * @return 위험성평가 항목 Key값
     * @throws Exception
     */
    @PostMapping("/assessactionjsa")
    public ResponseEntity<Integer> createJsaRiskJsa(@RequestBody RiskAssess riskAssess) throws Exception {
        return ResponseEntity.ok().body(assessActionService.createRiskJsa(riskAssess));
    }

    /**
     * 유해유험요인 양식 다운로드(JSA)
     *
     * @return
     * @throws Exception
     */
    @GetMapping("/excelsample")
    public ResponseEntity<byte[]> downloadSampleExcelForAssess(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = requestMapper.convertAsParameter(parameter);

        String assessPlanNo = map.containsKey("assessPlanNo") ? map.get("assessPlanNo").toString() : "";
        String processCd = map.containsKey("processCd") ? map.get("processCd").toString() : "";
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";

        File file = assessActionService.createExcelAssessSupply(assessPlanNo, processCd, plantCd);

        InputStream inputStream = null;
        try {
            inputStream = new BufferedInputStream(new FileInputStream(file));
            byte[] encoded = Base64.encodeBase64(IOUtils.toByteArray(inputStream));
            String encodedString = new String(encoded);
            return ResponseEntity.ok().body(encodedString.getBytes("UTF-8"));
        } catch (FileNotFoundException fe) {
            logger.error(fe.getMessage());
            throw fe;
        } catch (IOException ie) {
            logger.error(ie.getMessage());
            throw ie;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        } finally {
            inputStream.close();
        }
    }

    /**
     * 유해유험요인 업로드 (JSA)
     *
     * @param parameter
     * @return
     * @throws Exception
     */
    @GetMapping("/excelupload")
    public ResponseEntity<Map<String, Object>> uploadExcelAssess(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        String taskClassNm = map.containsKey("taskClassNm") ? map.get("taskClassNm").toString() : "";
        String taskKey = map.containsKey("taskKey") ? map.get("taskKey").toString() : "";
        String uploadUserId = map.containsKey("createUserId") ? map.get("createUserId").toString() : "";
        String assessTypeNo = map.containsKey("assessTypeNo") ? map.get("assessTypeNo").toString() : "";
        return ResponseEntity.ok().body(assessActionService.uploadExcelAssess(taskClassNm, taskKey, uploadUserId, assessTypeNo));
    }

}
