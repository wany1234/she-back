package com.she.health.workMeasure.controller;

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
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.base.Strings;
import com.she.health.model.WorkMeasureResult;
import com.she.health.workMeasure.service.WorkMeasureResultService;
import com.she.manage.mapper.CodeMasterMapper;
import com.she.manage.model.CodeMaster;
import com.she.utils.ConstVal;
import com.she.utils.RequestMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 작업환경측정결과
 */
@RestController
@RequestMapping("api/hea/workmeasure")
@Api(value = "/api/hea/workmeasure", description = "작업환경측정")
public class WorkMeasureResultController {

    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private WorkMeasureResultService workMeasureResultService;

    @Autowired
    private CodeMasterMapper codeMasterMapper;

    private static final Logger logger = LoggerFactory.getLogger(WorkMeasureResultController.class);

    /**
     * 작업환경측정결과 저장
     *
     * @param workMeasureResult
     *            작업환경측정결과
     * @return 작업환경측정결과번호
     * @throws Exception
     */
    @ApiOperation(value = "작업환경측정결과 등록", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "workMeasureResult", value = "작업환경측정결과", required = false, dataType = "WorkMeasure", paramType = "body") })
    @PostMapping("/workmeasureresult")
    public ResponseEntity<Integer> saveWorkMeasureResults(@RequestBody List<WorkMeasureResult> workMeasureResults) throws Exception {
        return ResponseEntity.ok().body(workMeasureResultService.saveWorkMeasureResults(workMeasureResults));
    }

    /**
     * 작업환경측정결과 목록 조회
     *
     * @param parameter
     *            검색조건
     * @return 작업환경측정결과 목록
     * @throws Exception
     */
    @ApiOperation(value = "작업환경측정결과 목록 조회", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "workMeasPlanNo", value = "작업환경측정계획번호", required = false, dataType = "int", paramType = "query"), @ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
    @GetMapping("/workmeasureresults")
    public ResponseEntity<List<WorkMeasureResult>> getWorkMeasureResults(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        // 작업환경측정계획번호
        int workMeasPlanNo = map.containsKey("workMeasPlanNo") ? Integer.parseInt("".equals(map.get("workMeasPlanNo").toString()) ? "0" : map.get("workMeasPlanNo").toString()) : 0;
        String workMeasStateCd = map.containsKey("workMeasStateCd") ? map.get("workMeasStateCd").toString() : "";

        return ResponseEntity.ok().body(workMeasureResultService.getWorkMeasureResults(workMeasPlanNo, workMeasStateCd));
    }

    /**
     * 작업환경측정결과 상세 조회
     *
     * @param workMeasRsltNo
     *            작업환경측정결과번호
     * @return 작업환경측정결과
     * @throws Exception
     */
    @ApiOperation(value = "작업환경측정결과 상세 조회", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "workMeasRsltNo", value = "작업환경측정결과번호", required = false, dataType = "int", paramType = "path") })
    @GetMapping("/workmeasureresult/{workMeasRsltNo}")
    public ResponseEntity<WorkMeasureResult> getWorkMeasureResult(@PathVariable(name = "workMeasRsltNo") int workMeasRsltNo) throws Exception {
        return ResponseEntity.ok().body(workMeasureResultService.getWorkMeasureResult(workMeasRsltNo));
    }

    /**
     * 작업환경측정결과 수정
     *
     * @param workMeasureResult
     *            작업환경측정리스트
     * @return 변경 행 수
     * @throws Exception
     */
    @ApiOperation(value = "작업환경측정결과 수정", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "workMeasureResult", value = "작업환경측정결과", required = false, dataType = "WorkMeasure", paramType = "body") })
    @PutMapping("/workmeasureresult")
    public ResponseEntity<Integer> updateWorkMeasureResult(@RequestBody WorkMeasureResult workMeasureResult) throws Exception {
        return ResponseEntity.ok().body(workMeasureResultService.updateWorkMeasureResult(workMeasureResult));
    }

    /**
     * 작업환경측정결과 삭제
     *
     * @param workMeasureResults
     *            작업환경측정리스트
     * @return 삭제 행 수
     * @throws Exception
     */
    @ApiOperation(value = "작업환경측정결과 삭제", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "workMeasureResults", value = "작업환경측정결과s", required = false, dataType = "List", paramType = "body") })
    @DeleteMapping("/workmeasureresult")
    public ResponseEntity<Integer> deleteWorkMeasureResults(@RequestBody List<WorkMeasureResult> workMeasureResults) throws Exception {
        return ResponseEntity.ok().body(workMeasureResultService.deleteWorkMeasureResults(workMeasureResults));
    }

    /**
     * 작업환경측정결과 양식 엑셀다운로드
     *
     * @return 엑셀업로드 양식
     * @throws Exception
     */
    @GetMapping("/excel/workmeasureresulttemplete")
    public @ResponseBody byte[] downloadExcelCheckupResultSpecial(@RequestParam HashMap<String, Object> parameter) throws Exception {
        String fileName = "작업환경측정결과_통합_양식_v1.0.xlsx";
        String templetePath = "templates";

        CodeMaster filePath = this.codeMasterMapper.getCodeMaster(ConstVal.CODE_GROUP_FILE_PATH, ConstVal.CODE_FILE_PATH_FORM, "Y");

        HashMap<String, Object> map = requestMapper.convertAsParameter(parameter);

        int workMeasPlanNo = map.containsKey("workMeasPlanNo") ? Integer.parseInt(Strings.isNullOrEmpty(map.get("workMeasPlanNo").toString()) ? "0" : map.get("workMeasPlanNo").toString()) : 0;

        // File file = new File("C:\\SHE_FILE\\FORM\\" + fileName);
        // File file = new File(templetePath + filePath.getCodeNm() +
        // File.separator + fileName);
        ClassPathResource classPathResource = new ClassPathResource(templetePath + filePath.getCodeNm() + fileName);
        File file = classPathResource.getFile();
        file = workMeasureResultService.setTempleteCodeSheet(file, fileName, workMeasPlanNo);
        InputStream inputStream = null;
        try {
            inputStream = new BufferedInputStream(new FileInputStream(file));
            byte[] encoded = Base64.encodeBase64(IOUtils.toByteArray(inputStream));
            String encodedString = new String(encoded);
            return encodedString.getBytes("UTF-8");
        } catch (IllegalArgumentException ie) {
            logger.error(ie.getMessage());
            throw ie;
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
     * 작업환경측정결과 엑셀업로드
     *
     * @param workMeasPlanNo
     *            작업환경측정계획번호
     * @param plantCd
     *            사업장
     * @param year
     *            측정년도
     * @param halfYearCd
     *            분기
     * @param measAgency
     *            측정기관
     * @param createUserId
     *            등록자
     * @param files
     *            업로드할파일
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "작업환경측정결과 엑셀업로드", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "workMeasPlanNo", value = "작업환경계획번호", required = true, dataType = "String", paramType = "form"), @ApiImplicitParam(name = "plantCd", value = "사업장", required = true, dataType = "String", paramType = "form"),
            @ApiImplicitParam(name = "year", value = "측정년도", required = true, dataType = "String", paramType = "form"), @ApiImplicitParam(name = "halfYearCd", value = "분기", required = true, dataType = "String", paramType = "form"), @ApiImplicitParam(name = "measAgency", value = "측정기관", required = true, dataType = "String", paramType = "form"),
            @ApiImplicitParam(name = "createUserId", value = "생성자id", required = true, dataType = "String", paramType = "form"), @ApiImplicitParam(name = "files", value = "업로드할파일", required = true, dataType = "__file", paramType = "form"), })
    @PostMapping(path = "/excel/workmeasureresults", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Map<String, Object>> getUploadExcelDataWorkMeasureResults(@RequestParam("workMeasPlanNo") int workMeasPlanNo, @RequestParam("plantCd") String plantCd, @RequestParam("year") String year, @RequestParam("halfYearCd") String halfYearCd, @RequestParam("measAgency") String measAgency,
            @RequestParam("createUserId") String createUserId, @RequestParam("files") MultipartFile[] files) throws Exception {
        return ResponseEntity.ok().body(workMeasureResultService.getUploadExcelDataWorkMeasureResults(workMeasPlanNo, plantCd, year, halfYearCd, measAgency, createUserId, files));
    }

    /**
     * 작업환경측정결과 통계 조회
     *
     * @param parameter
     *            검색조건
     * @return 작업환경측정결과 통계
     * @throws Exception
     */
    @GetMapping("/workmeasureresultstatus")
    public ResponseEntity<List<WorkMeasureResult>> getWorkMeasureResultStatus(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        // 사업장코드
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 기간
        String[] period = this.requestMapper.convertObjectListAsStringArray(map.get("period"));
        // 부서코드
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";
        // 공정코드
        String processCd = map.containsKey("processCd") ? map.get("processCd").toString() : "";
        // 유해인자
        String hazardNm = map.containsKey("hazardNm") ? map.get("hazardNm").toString() : "";
        // 유해인자대분류
        String[] workAreaGrades = this.requestMapper.convertObjectListAsStringArray(map.get("workAreaGrades"));
        // 하위부서 포함여부
        String deptSubYn = map.containsKey("deptSubYn") ? map.get("deptSubYn").toString() : "Y";

        String fromYear = "";
        String toYear = "";
        String[] yearPeriod = new String[0];

        if (period != null && period.length == 2) {
            if (period[0] == null || "".equals(period[0]) || period[1] == null || "".equals(period[1])) {
                return ResponseEntity.ok().body(null);
            }
            fromYear = period[0].substring(0, 4);
            toYear = period[1].substring(0, 4);

            int fromYearInt = Integer.parseInt(fromYear);
            int toYearInt = Integer.parseInt(toYear);
            yearPeriod = new String[(toYearInt - fromYearInt + 1) * 3 * 2]; // 년도*측정분기*유해인자대분류
            int index = 0;
            for (int i = fromYearInt; i <= toYearInt; i++) {
                for (int j = 0; workAreaGrades != null && j < workAreaGrades.length; j++) {
                    yearPeriod[index] = "[" + i + "_FHALF_" + workAreaGrades[j] + "]"; // 상반기
                    index++;
                    yearPeriod[index] = "[" + i + "_SHALF_" + workAreaGrades[j] + "]"; // 하반기
                    index++;
                    yearPeriod[index] = "[" + i + "_CHALF_" + workAreaGrades[j] + "]"; // 수시
                    index++;
                }
            }

            return ResponseEntity.ok().body(workMeasureResultService.getWorkMeasureResultStatus(plantCd, deptCd, processCd, hazardNm, fromYear, toYear, yearPeriod, deptSubYn));
        } else {
            return ResponseEntity.ok().body(null);
        }

    }

    /**
     * 작업환경측정결과 통계 조회
     *
     * @param parameter
     *            검색조건
     * @return 작업환경측정결과 통계
     * @throws Exception
     */
    @GetMapping("/workmeasureresultchart")
    public ResponseEntity<Map<String, Object>> getWorkMeasureResultChart(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        // 사업장코드
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 기간
        String[] period = this.requestMapper.convertObjectListAsStringArray(map.get("period"));
        // 부서코드
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";
        // 공정코드
        String processCd = map.containsKey("processCd") ? map.get("processCd").toString() : "";
        // 유해인자
        String hazardNm = map.containsKey("hazardNm") ? map.get("hazardNm").toString() : "";
        // 측정값 초과율
        String exposureExcessRate = map.containsKey("exposureExcessRate") ? map.get("exposureExcessRate").toString() : "";

        String fromYear = "";
        String toYear = "";
        String[] monthPeriod = new String[0];
        if (period != null && period.length == 2) {
            if (period[0] == null || "".equals(period[0]) || period[1] == null || "".equals(period[1])) {
                return ResponseEntity.ok().body(null);
            }

            int fromYearInt = Integer.parseInt(period[0]);
            int toYearInt = Integer.parseInt(period[1]);

            fromYear = period[0] + "-01-01";
            toYear = period[1] + "-12-31";

            monthPeriod = new String[(toYearInt - fromYearInt + 1) * 12];
            int index = 0;
            for (int i = fromYearInt; i <= toYearInt; i++) {
                for (int j = 1; j <= 12; j++) {

                    if (j == 10 || j == 11 || j == 12) {
                        monthPeriod[index] = "[" + i + "-" + j + "]"; // 10~12월
                        index++;
                    } else {
                        monthPeriod[index] = "[" + i + "-0" + j + "]"; // 1~9월
                        index++;
                    }

                }
            }

            return ResponseEntity.ok().body(workMeasureResultService.getWorkMeasureResultChart(plantCd, deptCd, processCd, hazardNm, exposureExcessRate, fromYear, toYear, monthPeriod));
        } else {
            return ResponseEntity.ok().body(null);
        }

    }

}
