package com.she.health.muscResearch.controller;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
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
import org.springframework.web.multipart.MultipartFile;

import com.she.common.model.DefaultParam;
import com.she.config.GlobalSettings;
import com.she.health.model.MuscHarmful;
import com.she.health.model.MuscHarmfulEval;
import com.she.health.model.MuscResearch;
import com.she.health.model.MuscResearchChklist;
import com.she.health.model.MuscResearchDept;
import com.she.health.model.MuscResearchRslt;
import com.she.health.model.MuscResearchUnit;
import com.she.health.model.MuscSurveyChklist;
import com.she.health.muscResearch.service.MuscResearchService;
import com.she.manage.mapper.CodeMasterMapper;
import com.she.manage.model.CodeMaster;
import com.she.utils.ConstVal;
import com.she.utils.ExcelUtil;
import com.she.utils.RequestMapper;
import com.she.utils.model.TableHeader;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

// SK E&S
/**
 * 근골격계메뉴
 *
 */
@RestController
@RequestMapping("/api/hea/muscresearch")
public class MuscResearchController {
    @Autowired
    private MuscResearchService muscResearchService;

    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private CodeMasterMapper codeMasterMapper;

    @Autowired
    private GlobalSettings globalSettings;

    private String excelDownloadFileName = "근골격계작업분류표.xlsx";

    private static final Logger logger = LoggerFactory.getLogger(MuscResearchController.class);

    /**
     * 근골격계 조사목록 조회
     *
     * @param parameter
     * @return
     * @throws Exception
     */
    @GetMapping("/muscresearchs")
    public ResponseEntity<List<MuscResearch>> getMuscResearchs(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> convertedParameter = requestMapper.convertAsParameter(parameter);

        // 사업장코드
        String plantCd = convertedParameter.containsKey("plantCd") ? convertedParameter.get("plantCd").toString() : "";

        // 진행상태
        String muscResearchStateCd = convertedParameter.containsKey("muscResearchStateCd") ? convertedParameter.get("muscResearchStateCd").toString() : "";

        // 조사명
        String researchNm = convertedParameter.containsKey("researchNm") ? convertedParameter.get("researchNm").toString() : "";

        // 조사부서 추가
        String deptCd = convertedParameter.containsKey("deptCd") ? convertedParameter.get("deptCd").toString() : "";
        // 하위부서 포함여부
        String deptSubYn = convertedParameter.containsKey("deptSubYn") ? convertedParameter.get("deptSubYn").toString() : "Y";

        // 조사기간
        String[] heaCheckupPlanPeriod = this.requestMapper.convertObjectListAsStringArray(convertedParameter.get("heaCheckupPlanPeriod"));
        String startDate = "";
        String endDate = "";

        if (heaCheckupPlanPeriod != null && heaCheckupPlanPeriod.length == 2) {
            startDate = StringUtils.isNotBlank(heaCheckupPlanPeriod[0]) ? heaCheckupPlanPeriod[0].substring(0, 4) : "";
            endDate = StringUtils.isNotBlank(heaCheckupPlanPeriod[1]) ? heaCheckupPlanPeriod[1].substring(0, 4) : "";
        }

        return ResponseEntity.ok().body(muscResearchService.getMuscResearchs(plantCd, muscResearchStateCd, researchNm, startDate, endDate, deptCd, deptSubYn));
    }

    /**
     * 근골격계 기본조사 정보 조회
     *
     * @param muscResearchNo
     *            근골격계 기본조사번호
     * @return 근골격계 기본조사 정보
     * @throws Exception
     *             예외
     */
    @GetMapping("/muscresearch/{muscResearchNo}")
    public ResponseEntity<MuscResearch> getMuscResearch(@PathVariable(name = "muscResearchNo") int muscResearchNo) throws Exception {
        return ResponseEntity.ok().body(muscResearchService.getMuscResearch(muscResearchNo));
    }

    /**
     * 근골격계 기본정보 저장
     *
     * @param muscResearch
     *            근골격계 기본정보
     * @return 근골격계 기본정보 저장
     * @throws Exception
     *             예외
     */
    @PostMapping("/muscresearch")
    public ResponseEntity<Integer> createMuscResearch(@RequestBody MuscResearch muscResearch) throws Exception {
        return ResponseEntity.ok().body(this.muscResearchService.createMuscResearch(muscResearch));
    }

    /**
     * 근골격계 기본정보 수정
     *
     * @param muscResearch
     * @return
     * @throws Exception
     */
    @PutMapping("/muscresearch")
    public ResponseEntity<Integer> updateMuscResearch(@RequestBody MuscResearch muscResearch) throws Exception {
        return ResponseEntity.ok().body(muscResearchService.updateMuscResearch(muscResearch));
    }

    @PutMapping("/muscresearchcmpt")
    public ResponseEntity<Integer> completeMuscResearch(@RequestBody MuscResearch muscResearch) throws Exception {
        return ResponseEntity.ok().body(muscResearchService.completeMuscResearch(muscResearch));
    }

    /**
     * 근골격계 기본정보 삭제
     *
     * @param muscResearchNo
     * @return
     * @throws Exception
     */
    @DeleteMapping("/muscresearch/{muscResearchNo}")
    public ResponseEntity<Integer> deleteMuscResearch(@PathVariable(name = "muscResearchNo") int muscResearchNo) throws Exception {
        return ResponseEntity.ok().body(muscResearchService.deleteMuscResearch(muscResearchNo));
    }

    /**
     * 근골격계 결과등록 조사목록 조회
     *
     * @param parameter
     * @return
     * @throws Exception
     */
    @GetMapping("/muscresults")
    public ResponseEntity<List<MuscResearch>> getMuscResearchResults(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> convertedParameter = requestMapper.convertAsParameter(parameter);
        String plantCd = convertedParameter.containsKey("plantCd") ? convertedParameter.get("plantCd").toString() : "";
        String muscResearchStateCd = convertedParameter.containsKey("muscResearchStateCd") ? convertedParameter.get("muscResearchStateCd").toString() : "";
        String researchNm = convertedParameter.containsKey("researchNm") ? convertedParameter.get("researchNm").toString() : "";

        String[] heaCheckupPlanPeriod = this.requestMapper.convertObjectListAsStringArray(convertedParameter.get("heaCheckupPlanPeriod"));
        String startDate = "";
        String endDate = "";

        if (heaCheckupPlanPeriod != null && heaCheckupPlanPeriod.length == 2) {
            startDate = StringUtils.isNotBlank(heaCheckupPlanPeriod[0]) ? heaCheckupPlanPeriod[0].substring(0, 4) : "";
            endDate = StringUtils.isNotBlank(heaCheckupPlanPeriod[1]) ? heaCheckupPlanPeriod[1].substring(0, 4) : "";
        }
        String deptCd = convertedParameter.containsKey("deptCd") ? convertedParameter.get("deptCd").toString() : "";

        // 하위부서 포함여부
        String deptSubYn = convertedParameter.containsKey("deptSubYn") ? convertedParameter.get("deptSubYn").toString() : "Y";

        return ResponseEntity.ok().body(this.muscResearchService.getMuscResearchResults(plantCd, muscResearchStateCd, researchNm, startDate, endDate, deptCd, deptSubYn));
    }

    /**
     * 단위작업 목록 조회
     *
     * @param parameter
     * @return
     * @throws Exception
     */
    @GetMapping("/muscunitworks")
    public ResponseEntity<List<MuscResearchUnit>> getunitWorks(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> convertedParameter = requestMapper.convertAsParameter(parameter);
        int muscResearchNo = convertedParameter.containsKey("muscResearchNo") ? Integer.parseInt(convertedParameter.get("muscResearchNo").toString()) : 0;
        String deptCd = convertedParameter.containsKey("deptCd") ? convertedParameter.get("deptCd").toString() : "";

        return ResponseEntity.ok().body(this.muscResearchService.getunitWorks(muscResearchNo, deptCd));
    }

    /**
     * 근골격계 단위작업 상세조회
     *
     * @param muscResearchUnitNo
     * @return
     * @throws Exception
     */
    @GetMapping("/muscunitwork/{muscResearchUnitNo}")
    public ResponseEntity<MuscResearchUnit> getunitWork(@PathVariable(name = "muscResearchUnitNo") int muscResearchUnitNo) throws Exception {
        return ResponseEntity.ok().body(this.muscResearchService.getunitWork(muscResearchUnitNo));
    }

    @GetMapping("/checkmuscunitwork")
    public ResponseEntity<Integer> getCheckUnitWorks(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> convertedParameter = requestMapper.convertAsParameter(parameter);
        int muscResearchUnitNo = convertedParameter.containsKey("muscResearchUnitNo") ? Integer.parseInt(convertedParameter.get("muscResearchUnitNo").toString()) : 0;
        int muscResearchNo = convertedParameter.containsKey("muscResearchNo") ? Integer.parseInt(convertedParameter.get("muscResearchNo").toString()) : 0;
        String deptCd = convertedParameter.containsKey("deptCd") ? convertedParameter.get("deptCd").toString() : "";
        String unitWorkNm = convertedParameter.containsKey("unitWorkNm") ? convertedParameter.get("unitWorkNm").toString() : "";

        return ResponseEntity.ok().body(this.muscResearchService.getCheckUnitWorks(muscResearchUnitNo, muscResearchNo, deptCd, unitWorkNm));
    }

    /**
     * 근골격계 단위작업 등록
     *
     * @param muscResearchUnit
     * @return
     * @throws Exception
     */
    @PostMapping("/muscunitwork")
    public ResponseEntity<Integer> createUnitWork(@RequestBody MuscResearchUnit muscResearchUnit) throws Exception {
        return ResponseEntity.ok().body(this.muscResearchService.createUnitWork(muscResearchUnit));
    }

    /**
     * 근골격계 단위작업 수정
     *
     * @param muscResearchUnit
     * @return
     * @throws Exception
     */
    @PutMapping("/muscunitwork")
    public ResponseEntity<Integer> updateUnitWork(@RequestBody MuscResearchUnit muscResearchUnit) throws Exception {
        return ResponseEntity.ok().body(muscResearchService.updateUnitWork(muscResearchUnit));
    }

    /**
     * 근골격계 단위작업 삭제
     *
     * @param muscResearchUnits
     * @return
     * @throws Exception
     */
    @DeleteMapping("/muscunitwork")
    public ResponseEntity<Integer> deleteUnitWork(@RequestBody List<MuscResearchUnit> muscResearchUnits) throws Exception {
        return ResponseEntity.ok().body(muscResearchService.deleteUnitWork(muscResearchUnits));
    }

    /**
     * 근골격계 부담작업no 리스트 조회
     *
     * @return
     * @throws Exception
     */
    @GetMapping("/muscchklists")
    public ResponseEntity<List<MuscResearchChklist>> getResearchChklists() throws Exception {
        return ResponseEntity.ok().body(muscResearchService.getResearchChklists());
    }

    /**
     * 근골격계 부담작업no 등록
     *
     * @param list
     * @param taskClassNm
     * @param tempIds
     * @param files
     * @return
     * @throws Exception
     */
    @PutMapping("/muscchklist")
    public ResponseEntity<Integer> updateResearchChklist(@RequestParam("list") String list, @RequestParam("taskClassNm") String taskClassNm, @RequestParam("tempId") List<String> tempIds, @RequestParam("files") MultipartFile[] files) throws Exception {
        List<MuscResearchRslt> objMuscResearchRslts = RequestMapper.convertJSONStringToList(MuscResearchRslt.class, list);
        return ResponseEntity.ok().body(muscResearchService.updateResearchChklist(objMuscResearchRslts, taskClassNm, tempIds, files));
    }

    /**
     * [근골격계 진행상태 변경], 유해인자 등록, 조사결과 저장
     *
     * @param muscResearch
     * @return
     * @throws Exception
     */
    @PutMapping("/muscresearchresultcmpt")
    public ResponseEntity<Integer> completeResearchChklist(@RequestBody MuscResearch muscResearch) throws Exception {
        return ResponseEntity.ok().body(muscResearchService.completeResearchChklist(muscResearch));
    }

    /**
     * [근골격계 진행상태 변경(보건담당자, 시스템관리자)]
     *
     * @param muscResearch
     * @return
     * @throws Exception
     */
    @PutMapping("/muscresearchresultcmpt/all")
    public ResponseEntity<Integer> completeResearchAllChklist(@RequestBody MuscResearch muscResearch) throws Exception {
        return ResponseEntity.ok().body(muscResearchService.completeResearchAllChklist(muscResearch));
    }

    /**
     * 근골격계 조사결과 목록 조회
     *
     * @return
     * @throws Exception
     */
    @GetMapping("/muscrslts")
    public ResponseEntity<List<MuscResearchRslt>> getResearchRslt(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        int muscResearchNo = map.containsKey("muscResearchNo") ? Integer.parseInt("".equals(map.get("muscResearchNo").toString()) ? "0" : map.get("muscResearchNo").toString()) : 0;
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";

        return ResponseEntity.ok().body(this.muscResearchService.getResearchRslt(muscResearchNo, deptCd));
    }

    @ApiOperation(value = "근골격계 조사결과 엑셀출력", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "muscResearchNo", value = "근골격계조사번호", required = false, dataType = "int", paramType = "path"), @ApiImplicitParam(name = "deptCd", value = "부서코드", required = false, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
    @GetMapping("/excel/muscrslts")
    public @ResponseBody byte[] getExcelResearchRslt(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        int muscResearchNo = map.containsKey("muscResearchNo") ? Integer.parseInt("".equals(map.get("muscResearchNo").toString()) ? "0" : map.get("muscResearchNo").toString()) : 0;
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";

        List<MuscResearchRslt> total = this.muscResearchService.getResearchRslt(muscResearchNo, deptCd);
        List<TableHeader> tableHeaders = new ArrayList<TableHeader>();
        TableHeader tableHeader = new TableHeader();
        tableHeader.setText("최종평가");
        tableHeader.setName("finalValue");
        tableHeaders.add(tableHeader);
        tableHeader = new TableHeader();
        tableHeader.setText("조사부서명");
        tableHeader.setName("deptNm");
        tableHeaders.add(tableHeader);
        tableHeader = new TableHeader();
        tableHeader.setText("단위작업명");
        tableHeader.setName("unitWorkNm");
        tableHeaders.add(tableHeader);
        tableHeader = new TableHeader();
        tableHeader.setText("작업명");
        tableHeader.setName("workNm");
        tableHeaders.add(tableHeader);
        tableHeader = new TableHeader();
        tableHeader.setText("부담작업No");
        tableHeader.setName("muscResearchChklistNo");
        tableHeaders.add(tableHeader);
        tableHeader = new TableHeader();
        tableHeader.setText("작업시간");
        tableHeader.setName("workTime");
        tableHeaders.add(tableHeader);
        tableHeader = new TableHeader();
        tableHeader.setText("작업횟수");
        tableHeader.setName("workCnt");
        tableHeaders.add(tableHeader);
        tableHeader = new TableHeader();
        tableHeader.setText("총노출시간");
        tableHeader.setName("totExposureTime");
        tableHeaders.add(tableHeader);
        tableHeader = new TableHeader();
        tableHeader.setText("사진");
        tableHeader.setName("files");
        tableHeader.setType("1");
        tableHeaders.add(tableHeader);

        File file = ExcelUtil.createExcelFile(tableHeaders, total, "근골격계 조사결과", globalSettings.getFileStorePath());

        if (file.exists() && file.isFile()) {
            InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
            byte[] encoded = Base64.encodeBase64(IOUtils.toByteArray(inputStream));
            String encodedString = new String(encoded);
            return encodedString.getBytes("UTF-8");

        } else {
            return null;
        }
    }

    /**
     * 작업분류표 조회
     *
     * @return
     * @throws Exception
     */
    @GetMapping("/muscrsltschklist")
    public ResponseEntity<List<Map<String, Object>>> getRsltChklist(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        int muscResearchNo = map.containsKey("muscResearchNo") ? Integer.parseInt("".equals(map.get("muscResearchNo").toString()) ? "0" : map.get("muscResearchNo").toString()) : 0;

        return ResponseEntity.ok().body(this.muscResearchService.getRsltChklist(muscResearchNo));
    }

    @ApiOperation(value = "작업분류표 엑셀출력", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "muscResearchNo", value = "근골격계조사번호", required = false, dataType = "int", paramType = "path"), @ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
    @GetMapping("/excel/muscrsltschklist")
    public @ResponseBody byte[] getExcelRsltChklist(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        int muscResearchNo = map.containsKey("muscResearchNo") ? Integer.parseInt("".equals(map.get("muscResearchNo").toString()) ? "0" : map.get("muscResearchNo").toString()) : 0;

        List<Map<String, Object>> total = this.muscResearchService.getRsltChklist(muscResearchNo);

        File file = muscResearchService.createExcelFile(total, "작업분류표", globalSettings.getFileStorePath());

        if (file.exists() && file.isFile()) {
            InputStream inputStream = null;

            try {
                inputStream = new BufferedInputStream(new FileInputStream(file));
                byte[] encoded = Base64.encodeBase64(IOUtils.toByteArray(inputStream));
                String encodedString = new String(encoded);
                return encodedString.getBytes("UTF-8");
            } catch (IOException ie) {
                logger.error(ie.getMessage());
                throw ie;
            } catch (Exception e) {
                logger.error(e.getMessage());
                throw e;
            } finally {
                if (inputStream != null)
                    inputStream.close();
            }

        } else {
            return null;
        }
    }

    /**
     * 유해인자 목록 조회
     *
     * @param parameter
     * @return
     * @throws Exception
     */
    @GetMapping("/muscharmfuls")
    public ResponseEntity<List<MuscHarmful>> getHarmfuls(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> convertedParameter = requestMapper.convertAsParameter(parameter);
        int muscResearchNo = convertedParameter.containsKey("muscResearchNo") ? Integer.parseInt(convertedParameter.get("muscResearchNo").toString()) : 0;

        return ResponseEntity.ok().body(this.muscResearchService.getHarmfuls(muscResearchNo));
    }

    /**
     * 유해인자 상세 조회
     *
     * @param muscHarmfulNo
     * @return
     * @throws Exception
     */
    @GetMapping("/muscharmful/{muscHarmfulNo}")
    public ResponseEntity<MuscHarmful> getHarmful(@PathVariable(name = "muscHarmfulNo") int muscHarmfulNo) throws Exception {
        return ResponseEntity.ok().body(this.muscResearchService.getHarmful(muscHarmfulNo));
    }

    /**
     * 유해인자 수정
     *
     * @param harmful
     * @param taskClassNm
     * @param tempIds
     * @param files
     * @return
     * @throws Exception
     */
    @PutMapping("/muscharmful")
    public ResponseEntity<Integer> updateHarmful(@RequestParam("harmful") String harmful, @RequestParam("taskClassNm") String taskClassNm, @RequestParam("tempId") List<String> tempIds, @RequestParam("files") MultipartFile[] files) throws Exception {
        MuscHarmful objMuscHarmfuls = RequestMapper.convertJSONStringToClass(MuscHarmful.class, harmful);
        return ResponseEntity.ok().body(this.muscResearchService.updateHarmful(objMuscHarmfuls, taskClassNm, tempIds, files));
    }

    /**
     * 유해인자 삭제
     *
     * @param muscHarmfuls
     * @return
     * @throws Exception
     */
    @DeleteMapping("/muscharmful")
    public ResponseEntity<Integer> deleteHarmful(@RequestBody List<MuscHarmful> muscHarmfuls) throws Exception {
        return ResponseEntity.ok().body(muscResearchService.deleteHarmful(muscHarmfuls));
    }

    /**
    @GetMapping("/muscresearchdepts")
    public ResponseEntity<List<MuscResearchDept>> getMuslSurveyDepts(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        int muscResearchNo = map.containsKey("muscResearchNo") ? Integer.parseInt(map.get("muscResearchNo").toString()) : 0;

        return ResponseEntity.ok().body(muscResearchService.getMuscResearchDepts(muscResearchNo));
    }

    /**
     * 근골격계 작업분류표 수정
     *
     * @param muscSurveyChklist
     * @return 근골격계 작업분류표 목록
     * @throws Exception
     *             예외
     */
    @PutMapping("/muscsurveychklist")
    public ResponseEntity<Integer> updateMuscSurveyChklist(@RequestBody MuscSurveyChklist muscSurveyChklist) throws Exception {
        return ResponseEntity.ok().body(muscResearchService.updateMuscSurveyChklist(muscSurveyChklist));
    }

    /**
     * 근골격계 작업분류표 수정
     *
     * @param heaMuscSurveyNo
     * @param apprRqstNo
     * @return 근골격계 작업분류표 목록
     * @throws Exception
     *             예외
     */
    @PutMapping("/muscsurveychklist/{heaMuscSurveyNo}/{apprRqstNo}")
    public ResponseEntity<Integer> collectAppr(@PathVariable(name = "heaMuscSurveyNo") int heaMuscSurveyNo, @PathVariable(name = "apprRqstNo") int apprRqstNo) throws Exception {
        return ResponseEntity.ok().body(muscResearchService.collectAppr(heaMuscSurveyNo, apprRqstNo));
    }

    @GetMapping("/harmful-exceldown")
    public ResponseEntity<byte[]> downloadSampleExcelForAssess(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = requestMapper.convertAsParameter(parameter);

        List<Integer> muscHarmfulNoList = map.containsKey("harmfulNoArr") ? (List<Integer>) map.get("harmfulNoArr") : null;

        File file = muscResearchService.createExcelAssessSupply(muscHarmfulNoList);

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

    @GetMapping("/exceldown")
    public ResponseEntity<String> muscResearchExcelDown(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        CodeMaster path = this.codeMasterMapper.getCodeMaster(ConstVal.CODE_GROUP_FILE_PATH, ConstVal.CODE_FILE_PATH_FORM, "Y");

        ClassPathResource classPathResource = new ClassPathResource("templates" + path.getCodeNm() + "SHE_MUSC_UNIT_SAMPLE.xlsx");

        File templete = classPathResource.getFile();

        int read = 0;
        byte[] buff = new byte[1024];

        ByteArrayInputStream in = null;
        FileInputStream fi = null;

        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            fi = new FileInputStream(templete);
            while ((read = fi.read(buff)) > 0) {
                out.write(buff, 0, read);
            }

            in = new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw e;
        } finally {
            try {
                if (in != null)
                    in.close();
                fi.close();
            } catch (IOException e) {
                throw e;
            }
        }

        byte[] encoded = Base64.encodeBase64(IOUtils.toByteArray(in));
        String encodedString = new String(encoded);
        return ResponseEntity.ok().body(encodedString);
    }

    @GetMapping("/excel-upload")
    public ResponseEntity<Map<String, Object>> uploadExcelAssess(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        String taskClassNm = map.containsKey("taskClassNm") ? map.get("taskClassNm").toString() : "";
        String taskKey = map.containsKey("taskKey") ? map.get("taskKey").toString() : "";
        String uploadUserId = map.containsKey("createUserId") ? map.get("createUserId").toString() : "";
        String muscResearchNo = map.containsKey("muscResearchNo") ? map.get("muscResearchNo").toString() : "";
        return ResponseEntity.ok().body(muscResearchService.uploadExcelAssess(taskClassNm, taskKey, uploadUserId, muscResearchNo));
    }

    @GetMapping("/harmful-excel-upload")
    public ResponseEntity<Map<String, Object>> uploadExcelHarmful(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        String taskClassNm = map.containsKey("taskClassNm") ? map.get("taskClassNm").toString() : "";
        String taskKey = map.containsKey("taskKey") ? map.get("taskKey").toString() : "";
        String uploadUserId = map.containsKey("createUserId") ? map.get("createUserId").toString() : "";
        String muscResearchNo = map.containsKey("muscResearchNo") ? map.get("muscResearchNo").toString() : "";
        return ResponseEntity.ok().body(muscResearchService.uploadExcelHarmful(taskClassNm, taskKey, uploadUserId, muscResearchNo, defaultParam));
    }

}
