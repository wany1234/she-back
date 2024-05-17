package com.she.env.envEffectEval.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
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
import org.springframework.core.io.ClassPathResource;
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
import org.springframework.web.multipart.MultipartFile;

import com.she.common.model.DefaultParam;
import com.she.env.envEffectEval.model.EnvEffectEvalPlan;
import com.she.env.envEffectEval.model.EnvEffectEvalRslt;
import com.she.env.envEffectEval.service.EnvEffectEvalPlanService;
import com.she.manage.mapper.CodeMasterMapper;
import com.she.manage.model.CodeMaster;
import com.she.utils.ConstVal;
import com.she.utils.RequestMapper;

@RestController
@RequestMapping("/api/env/enveffectevalplan")
public class EnvEffectEvalPlanController {
    private final Logger logger = LoggerFactory.getLogger(EnvEffectEvalPlanController.class);

    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private EnvEffectEvalPlanService envEffectEvalPlanService;

    @Autowired
    private CodeMasterMapper codeMasterMapper;

    /**
     * 환경영향평가 계획 조회
     *
     * @param parameter
     * @param defaultParam
     * @return
     * @throws Exception
     */
    @GetMapping("/plans")
    public ResponseEntity<List<EnvEffectEvalPlan>> getEnvEffectEvalPlans(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = requestMapper.convertAsParameter(parameter);

        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";
        String tgtDeptCd = map.containsKey("tgtDeptCd") ? map.get("tgtDeptCd").toString() : "";
        String envEffEvalPlanStepCd = map.containsKey("envEffEvalPlanStepCd") ? map.get("envEffEvalPlanStepCd").toString() : "";
        String envEffEvalDivCd = map.containsKey("envEffEvalDivCd") ? map.get("envEffEvalDivCd").toString() : "";
        String evalTitle = map.containsKey("evalTitle") ? map.get("evalTitle").toString() : "";
        String evalYearFrom = map.containsKey("evalYearFrom") ? map.get("evalYearFrom").toString() : "";
        String evalYearTo = map.containsKey("evalYearTo") ? map.get("evalYearTo").toString() : "";
        String apprStepCd = map.containsKey("apprStepCd") ? map.get("apprStepCd").toString() : "";

        return ResponseEntity.ok().body(envEffectEvalPlanService.getEnvEffectEvalPlans(plantCd, deptCd, tgtDeptCd, envEffEvalPlanStepCd, envEffEvalDivCd, evalTitle, evalYearFrom, evalYearTo, apprStepCd, defaultParam));
    }

    /**
     * 환경영향평가 계획 상세
     *
     * @param evalPlanNo
     * @param defaultParam
     * @return
     * @throws Exception
     */
    @GetMapping("/plan/{evalPlanNo}")
    public ResponseEntity<EnvEffectEvalPlan> getEnvEffectEvalPlan(@PathVariable(name = "evalPlanNo") int evalPlanNo, @ModelAttribute DefaultParam defaultParam) throws Exception {
        return ResponseEntity.ok().body(envEffectEvalPlanService.getEnvEffectEvalPlan(evalPlanNo, defaultParam));
    }

    /**
     * 환경영향평가 계획 등록
     *
     * @param envEffectEvalPlan
     * @return
     * @throws Exception
     */
    @PostMapping("/plan")
    public ResponseEntity<Integer> createEnvEffectEvalPlan(@RequestBody EnvEffectEvalPlan envEffectEvalPlan) throws Exception {
        return ResponseEntity.ok().body(envEffectEvalPlanService.createEnvEffectEvalPlan(envEffectEvalPlan));
    }

    /**
     * 환경영향평가 계획 수정
     *
     * @param envEffectEvalPlan
     * @return
     * @throws Exception
     */
    @PutMapping("/plan")
    public ResponseEntity<Integer> updateEnvEffectEvalPlan(@RequestBody EnvEffectEvalPlan envEffectEvalPlan) throws Exception {
        return ResponseEntity.ok().body(envEffectEvalPlanService.updateEnvEffectEvalPlan(envEffectEvalPlan));
    }

    /**
     * 환경영향평가 계획 삭제
     *
     * @param evalPlanNo
     * @return
     * @throws Exception
     */
    @DeleteMapping("/plan/{evalPlanNo}")
    public ResponseEntity<Integer> deleteEnvEffectEvalPlan(@PathVariable(name = "evalPlanNo") int evalPlanNo) throws Exception {
        return ResponseEntity.ok().body(envEffectEvalPlanService.deleteEnvEffectEvalPlan(evalPlanNo));
    }

    /**
     * 환경영향평가 결과 목록
     *
     * @param evalPlanNo
     * @param defaultParam
     * @return
     * @throws Exception
     */
    @GetMapping("/plan/{evalPlanNo}/results")
    public ResponseEntity<List<EnvEffectEvalRslt>> getEnvEffectEvalResults(@PathVariable(name = "evalPlanNo") int evalPlanNo, @ModelAttribute DefaultParam defaultParam) throws Exception {
        return ResponseEntity.ok().body(envEffectEvalPlanService.getEnvEffectEvalResults(evalPlanNo, defaultParam));
    }

    /**
     * 환경영향평가 결과 상세
     *
     * @param evalRsltNo
     * @param defaultParam
     * @return
     * @throws Exception
     */
    @GetMapping("/result/{evalRsltNo}")
    public ResponseEntity<EnvEffectEvalRslt> getEnvEffectEvalResult(@PathVariable(name = "evalRsltNo") int evalRsltNo, @ModelAttribute DefaultParam defaultParam) throws Exception {
        return ResponseEntity.ok().body(envEffectEvalPlanService.getEnvEffectEvalResult(evalRsltNo, defaultParam));
    }

    /**
     * 환경영향평가 결과 등록
     *
     * @param envEffectEvalRslt
     * @param lang
     * @return
     * @throws Exception
     */
    @PostMapping("/result/{lang}")
    public ResponseEntity<EnvEffectEvalRslt> createEnvEffectEvalRslt(@RequestBody EnvEffectEvalRslt envEffectEvalRslt, @PathVariable(name = "lang") String lang) throws Exception {
        DefaultParam defaultParam = new DefaultParam(lang);
        return ResponseEntity.ok().body(envEffectEvalPlanService.createEnvEffectEvalRslt(envEffectEvalRslt, defaultParam));
    }

    /**
     * 환경영향평가 결과 등록
     *
     * @param envEffectEvalRslts
     * @return
     * @throws Exception
     */
    @PostMapping("/result")
    public ResponseEntity<Integer> createEnvEffectEvalRslts(@RequestBody EnvEffectEvalPlan envEffectEvalPlan) throws Exception {
        return ResponseEntity.ok().body(envEffectEvalPlanService.createEnvEffectEvalRslts(envEffectEvalPlan));
    }

    /**
     * 환경영향평가 결과 수정
     *
     * @param envEffectEvalPlan
     * @return
     * @throws Exception
     */
    @PutMapping("/result")
    public ResponseEntity<Integer> updateEnvEffectEvalRslts(@RequestBody EnvEffectEvalPlan envEffectEvalPlan) throws Exception {
        return ResponseEntity.ok().body(envEffectEvalPlanService.updateEnvEffectEvalRslts(envEffectEvalPlan));
    }

    /**
     * 환경영향평가 결과 삭제
     *
     * @param evalRsltNo
     * @return
     * @throws Exception
     */
    @PutMapping("/result/delete")
    public ResponseEntity<Integer> deleteEnvEffectEvalRslt(@RequestBody List<EnvEffectEvalRslt> envEffectEvalRslts) throws Exception {
        return ResponseEntity.ok().body(envEffectEvalPlanService.deleteEnvEffectEvalRslt(envEffectEvalRslts));
    }

    /**
     * 환경영향평가 결과 수정
     *
     * @param envEffectEvalPlan
     * @return
     * @throws Exception
     */
    @PutMapping("/result/managercomment")
    public ResponseEntity<Integer> updateEnvEffectEvalRsltManagerComment(@RequestBody EnvEffectEvalPlan envEffectEvalPlan) throws Exception {
        return ResponseEntity.ok().body(envEffectEvalPlanService.updateEnvEffectEvalRsltManagerComment(envEffectEvalPlan));
    }

    /**
     * 환경영형평가 등록부 목록조회
     *
     * @param parameter
     * @param defaultParam
     * @return
     * @throws Exception
     */
    @GetMapping("/impo/results")
    public ResponseEntity<List<EnvEffectEvalRslt>> getEnvEffectEvalImpoResults(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = requestMapper.convertAsParameter(parameter);

        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";
        String tgtDeptCd = map.containsKey("tgtDeptCd") ? map.get("tgtDeptCd").toString() : "";
        String tgtProcessCd = map.containsKey("tgtProcessCd") ? map.get("tgtProcessCd").toString() : "";
        String evalTitle = map.containsKey("evalTitle") ? map.get("evalTitle").toString() : "";
        String evalYearFrom = map.containsKey("evalYearFrom") ? map.get("evalYearFrom").toString() : "";
        String evalYearTo = map.containsKey("evalYearTo") ? map.get("evalYearTo").toString() : "";
        String safFacilityCd = map.containsKey("safFacilityCd") ? map.get("safFacilityCd").toString() : "";
        String impoRate = map.containsKey("impoRate") ? map.get("impoRate").toString() : "";

        return ResponseEntity.ok().body(envEffectEvalPlanService.getEnvEffectEvalImpoResults(plantCd, deptCd, tgtDeptCd, tgtProcessCd, evalTitle, evalYearFrom, evalYearTo, safFacilityCd, impoRate, defaultParam));
    }

    /**
     * 환경영향평가 도움말다운로드
     *
     * @return
     * @throws Exception
     */
    @GetMapping("/result/helpdownload")
    public @ResponseBody byte[] downloadHelpExcel() throws Exception {
        String templetePath = "templates";
        String excelFileName = "도움말_환경측면 파악 영향 평가서(KZB-0802-02).xlsx";

        CodeMaster filePath = this.codeMasterMapper.getCodeMaster(ConstVal.CODE_GROUP_FILE_PATH, ConstVal.CODE_FILE_PATH_FORM, "Y");

        ClassPathResource classPathResource = new ClassPathResource(templetePath + filePath.getCodeNm() + excelFileName);
        File file = classPathResource.getFile();

        InputStream inputStream = null;
        try {
            inputStream = new BufferedInputStream(new FileInputStream(file));
            byte[] encoded = Base64.encodeBase64(IOUtils.toByteArray(inputStream));
            String encodedString = new String(encoded);
            return encodedString.getBytes("UTF-8");
        } catch (IOException e) {
            logger.error(e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        } finally {
            inputStream.close();
        }
        return null;
    }

    /**
     * 환경영향평가 결과양식다운로드
     *
     * @return
     * @throws Exception
     */
    @GetMapping("/result/exceldownload")
    public @ResponseBody byte[] downloadExcel() throws Exception {
        String templetePath = "templates";
        String excelFileName = "환경영향평가_결과_업로드양식.xlsx";

        CodeMaster filePath = this.codeMasterMapper.getCodeMaster(ConstVal.CODE_GROUP_FILE_PATH, ConstVal.CODE_FILE_PATH_FORM, "Y");

        ClassPathResource classPathResource = new ClassPathResource(templetePath + filePath.getCodeNm() + excelFileName);
        File file = classPathResource.getFile();

        InputStream inputStream = null;
        try {
            inputStream = new BufferedInputStream(new FileInputStream(file));
            byte[] encoded = Base64.encodeBase64(IOUtils.toByteArray(inputStream));
            String encodedString = new String(encoded);
            return encodedString.getBytes("UTF-8");
        } catch (IOException e) {
            logger.error(e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        } finally {
            inputStream.close();
        }
        return null;
    }

    /**
     * 환경영향평가 결과 엑셀업로드
     *
     * @param files
     * @param lang
     * @param evalPlanNo
     * @return
     * @throws Exception
     */
    @PostMapping("/result/excelupload")
    public ResponseEntity<Map<String, Object>> uploadExcel(@RequestParam("files") MultipartFile[] files, @RequestParam(name = "lang") String lang, @RequestParam("evalPlanNo") int evalPlanNo) throws Exception {
        return ResponseEntity.ok().body(envEffectEvalPlanService.uploadExcel(files, lang, evalPlanNo));
    }
}
