package com.she.health.workMeasure.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.she.common.mapper.LogMapper;
import com.she.common.model.AttachFile;
import com.she.common.model.AttachFileGrp;
import com.she.common.model.DefaultParam;
import com.she.common.service.AttachFileService;
import com.she.config.GlobalSettings;
import com.she.config.model.ExceptionType;
import com.she.file.service.FileStorageService;
import com.she.health.model.Hazard;
import com.she.health.model.WorkMeasurePlan;
import com.she.health.model.WorkMeasureResult;
import com.she.health.workMeasure.mapper.WorkMeasurePlanMapper;
import com.she.health.workMeasure.mapper.WorkMeasureResultMapper;
import com.she.health.workingEnvManage.service.HazardService;
import com.she.manage.mapper.CodeMasterMapper;
import com.she.manage.mapper.DeptMapper;
import com.she.manage.model.CodeMaster;
import com.she.manage.model.Dept;
import com.she.manage.model.Process;
import com.she.manage.service.ProcessService;
import com.she.security.exception.RestAuthException;
import com.she.security.util.DateUtil;
import com.she.security.util.StringUtil;
import com.she.utils.ConstVal;
import com.she.utils.ExcelReader;

/**
 * 작업환경측정결과
 */
@Service
public class WorkMeasureResultService {
    private static final Logger logger = LoggerFactory.getLogger(WorkMeasureResultService.class);

    private static final String WORK_MEASURE_RESULT_TASK_CLASS = "WORK_MEASURE_RESULT_TEMPLETE";

    @Autowired
    private WorkMeasurePlanMapper workMeasurePlanMapper;

    @Autowired
    private WorkMeasureResultMapper workMeasureResultMapper;

    @Autowired
    private AttachFileService attachFileService;

    @Autowired
    private CodeMasterMapper codeMasterMapper;

    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private ProcessService processService;

    @Autowired
    private HazardService hazardService;

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private GlobalSettings globalSettings;

    @Autowired
    private LogMapper logMapper;

    /**
     * 작업환경측정결과 저장
     *
     * @param workMeasureResult
     *            작업환경측정결과
     * @return 저장 행 수
     * @throws Exception
     */
    @Transactional
    public int createWorkMeasureResult(WorkMeasureResult workMeasureResult) throws Exception {
        WorkMeasurePlan workMeasurePlan = workMeasurePlanMapper.getWorkMeasurePlan(workMeasureResult.getWorkMeasPlanNo());
        if (workMeasurePlan.getWorkMeasStateCd().equals("WMS20")) {
            // 계획접수 상태인 경우 결과입력중 단계로 변경
            workMeasurePlan.setWorkMeasStateCd("WMS30");
            workMeasurePlan.setUpdateUserId(workMeasureResult.getUpdateUserId());
            this.workMeasurePlanMapper.updateWorkMeasurePlanStep(workMeasurePlan);
        }
        workMeasureResultMapper.createWorkMeasureResult(workMeasureResult);
        return workMeasureResult.getWorkMeasRsltNo();
    }

    /**
     * 작업환경측정결과 조회
     *
     * @param workMeasPlanNo
     *            작업환경측정계획번호
     * @return 작업환경측정결과 목록
     * @throws Exception
     */
    public List<WorkMeasureResult> getWorkMeasureResults(int workMeasPlanNo, String workMeasStateCd) throws Exception {
        List<WorkMeasureResult> workMeasureResults = null;

        workMeasureResults = workMeasureResultMapper.getWorkMeasureResults(workMeasPlanNo);

        return workMeasureResults;
    }

    /**
     * 작업환경측정결과 상세 조회
     *
     * @param workMeasRsltNo
     *            작업환경측정결과번호
     * @return 작업환경측정결과
     * @throws Exception
     */
    public WorkMeasureResult getWorkMeasureResult(int workMeasRsltNo) throws Exception {
        return workMeasureResultMapper.getWorkMeasureResult(workMeasRsltNo);
    }

    /**
     * 작업환경측정결과 수정
     *
     * @param workMeasureResult
     *            작업환경측정리스트
     * @return 변경 행 수
     * @throws Exception
     */
    @Transactional
    public int updateWorkMeasureResult(WorkMeasureResult workMeasureResult) throws Exception {
        return workMeasureResultMapper.updateWorkMeasureResult(workMeasureResult);
    }

    /**
     * 작업환경측정결과 삭제
     *
     * @param workMeasureResults
     *            작업환경측정리스트
     * @return 삭제 행 수
     * @throws Exception
     */
    @Transactional
    public int deleteWorkMeasureResults(List<WorkMeasureResult> workMeasureResults) throws Exception {
        int resultNo = 0;
        for (WorkMeasureResult workMeasureResult : workMeasureResults) {
            resultNo += workMeasureResultMapper.deleteWorkMeasureResult(workMeasureResult.getWorkMeasRsltNo());
        }
        return resultNo;
    }

    /**
     * 작업환경측정결과 엑셀업로드 파일 조회 및 validation check
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
     * @return 작업환경측정결과 엑셀업로드 파일 데이터 목록
     * @throws Exception
     */
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public Map<String, Object> getUploadExcelDataWorkMeasureResults(int workMeasPlanNo, String plantCd, String year, String halfYearCd, String measAgency, String createUserId, MultipartFile[] files) throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
        String todate = formatter.format(new Date());
        AttachFileGrp attachFileGrp = new AttachFileGrp(WORK_MEASURE_RESULT_TASK_CLASS, todate, "", "", "", new ArrayList<>(Arrays.asList(files)));
        List<AttachFile> attachFiles = this.attachFileService.uploadFiles(attachFileGrp);

        // 저장된 엑셀 파일의 정보를 읽는다.
        List<AttachFile> excelFiles = attachFileService.getUploadFiles(WORK_MEASURE_RESULT_TASK_CLASS, todate, "");
        // 양식 파일경로
        CodeMaster path = this.codeMasterMapper.getCodeMaster(ConstVal.CODE_GROUP_FILE_PATH, ConstVal.CODE_FILE_PATH_FORM, "Y");

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("success", false);
        map.put("message", "");
        map.put("uploadList", null);
        map.put("totCount", null);
        map.put("completeCount", null);
        map.put("dontCount", null);

        int completeCount = 0;
        int dontCount = 0;
        int totCount = 0;

        try {

            File excel = new File(excelFiles.get(0).getFilePath());
            ExcelReader reader = new ExcelReader();
            List<String[][]> sheets = reader.read(excel);

            ClassPathResource classPathResource = new ClassPathResource("templates" + path.getCodeNm() + "작업환경측정결과_통합_양식_v1.0.xlsx");
            File templete = classPathResource.getFile();
            reader = new ExcelReader();
            List<String[][]> templeteSheets = reader.read(templete);

            if (sheets != null && templeteSheets != null) {
                String[][] sheet = sheets.get(0);
                String[][] templeteData = templeteSheets.get(0);

                // 헤더 양식확인
                if (sheet.length > 0 && !reader.excelHeaderCheck(sheet[0], templeteData[0])) {
                    map.put("message", "업로드양식이 오류: 시트의 헤더가 다릅니다.");
                } else {
                    if (sheet.length == 1) {
                        map.put("message", "업로드 오류: 데이터가 없습니다.");
                        map.put("uploadList", null);
                        map.put("totCount", 0);
                        map.put("completeCount", 0);
                        map.put("dontCount", 0);
                    } else {

                        // excel 업로드시 delete 후 insert
                        List<WorkMeasureResult> workMeasureResults = this.getWorkMeasureResults(workMeasPlanNo, null);
                        this.deleteWorkMeasureResults(workMeasureResults);

                        // 업로드양식 통과 후 데이터검사
                        // 부서 조회
                        List<Dept> depts = deptMapper.getDepts(plantCd, null, null, null, "Y", null, new DefaultParam("kr"));
                        // 공정 조회
                        List<Process> processes = processService.getProcesses("Y", null, null, null, plantCd, new DefaultParam("kr"));
                        // 유해인자 조회
                        List<Hazard> hazards = hazardService.getHazards(null, null, null, null, null, "Y");

                        List<LinkedHashMap<String, Object>> uploadList = new ArrayList<LinkedHashMap<String, Object>>();
                        StringBuilder rowCheck;

                        // 작업분류표
                        for (int row = 1; row < sheet.length; row++) {

                            rowCheck = new StringBuilder();
                            for (int i = 0; i < sheet[row].length; i++) {
                                if (sheet[row][i] == null) {
                                    break;
                                }
                                rowCheck.append(sheet[row][i].trim());
                            }

                            if (rowCheck.length() > 0) {
                                LinkedHashMap<String, Object> rowData = new LinkedHashMap<String, Object>();
                                // 1. 사업장
                                rowData.put("plantCdSheet", sheet[row][0].trim());
                                // 2. 측정년도
                                rowData.put("measureYear", sheet[row][1].trim());
                                // 3. 분기코드
                                rowData.put("measureHalfYear", sheet[row][2].trim());
                                // 4. 측정기관
                                rowData.put("measureCompanyNm", sheet[row][3].trim());
                                // 5. 구분코드
                                rowData.put("workAreaGradeCd", sheet[row][4].trim());
                                // 6. 부서코드
                                rowData.put("deptCd", sheet[row][5].trim());
                                // 7. 공정코드
                                rowData.put("processCd", sheet[row][6].trim());
                                // 8. 단위작업장소
                                rowData.put("workPlace", sheet[row][7].trim());
                                // 9. 유해인자코드
                                rowData.put("hazardCd", sheet[row][8].trim());
                                // 11. 근로자수
                                rowData.put("workerCnt", sheet[row][9].trim());
                                // 10. 작업내용
                                rowData.put("workContents", sheet[row][10].trim());
                                // 11. 근로형태 및 실근로시간
                                rowData.put("shiftType", sheet[row][11].trim());
                                // 12. 발생형태 및 발생시간(주기)
                                rowData.put("occurType", sheet[row][12].trim());
                                // 13. 유해인자발생시간(분)
                                rowData.put("occurTime", sheet[row][13].trim());
                                // 14. 측정위치(근로자명)
                                rowData.put("measPsnNm", sheet[row][14].trim());
                                // 15. 측정시간(시작)
                                rowData.put("measStartTime", sheet[row][15].trim());
                                // 16. 측정시간(종료)
                                rowData.put("measEndTime", sheet[row][16].trim());
                                // 17. 측정일자
                                rowData.put("measDt", sheet[row][17].trim());
                                // 18. 측정횟수
                                rowData.put("measCnt", sheet[row][18].trim());
                                // 19. 측정치
                                rowData.put("measValue", sheet[row][19].trim());
                                // 20. 시간가중평균치(전회)
                                rowData.put("twaPrev", sheet[row][20].trim());
                                // 21. 시간가중평균치(금회)
                                rowData.put("twaCurr", sheet[row][21].trim());
                                // 22. 노출기준
                                rowData.put("exposureStd", sheet[row][22].trim());
                                // 노출기준 초과여부
                                rowData.put("exposureExcessFlag", "");
                                // 23. 측정 및 분석방법
                                rowData.put("measMethod", sheet[row][23].trim());
                                // 24. 비고
                                rowData.put("remark", sheet[row][24].trim());

                                // 소음 체크
                                // boolean checkNoiseYn =
                                // rowData.get("noiseYn").equals("Y") ||
                                // rowData.get("noiseYn").equals("N");
                                // 사업장 체크
                                boolean checkPlant = rowData.get("plantCdSheet").equals(plantCd);
                                // 측정년도 체크
                                boolean checkYear = rowData.get("measureYear").equals(year);
                                // 분기코드 체크
                                boolean checkMeasureHalfYear = rowData.get("measureHalfYear").equals(halfYearCd);
                                // 측정기관 체크
                                boolean checkMeasAgency = rowData.get("measureCompanyNm").equals(measAgency);
                                // 부서 체크
                                boolean checkDept = depts.stream().filter(o -> o.getDeptCd().equals(rowData.get("deptCd"))).findFirst().isPresent();
                                // 공정 체크
                                boolean checkProcess = processes.stream().filter(o -> o.getProcessCd().equals(rowData.get("processCd"))).findFirst().isPresent();
                                // 유해인자 체크
                                boolean checkHazard = hazards.stream().filter(o -> o.getHazardCd().equals(rowData.get("hazardCd"))).findFirst().isPresent();
                                // 근로자수 체크
                                boolean checkWorkerCnt = StringUtil.isEmpty(String.valueOf(rowData.get("workerCnt"))) ? false : isStringInteger(String.valueOf(rowData.get("workerCnt")));
                                // 유해인자발생시간 체크 (빈값으로 들어오는 경우가 있음)
                                boolean checkOccurTime = StringUtil.isEmpty(String.valueOf(rowData.get("occurTime"))) ? true : isStringInteger(String.valueOf(rowData.get("occurTime")));
                                // 측정일자 체크
                                boolean checkMeasDt = DateUtil.validateDate(String.valueOf(rowData.get("measDt")));
                                // 측정횟수 체크 (측정횟수는 빈값으로 들어오는 경우가 있음)
                                boolean checkMeasCnt = StringUtil.isEmpty(String.valueOf(rowData.get("measCnt"))) ? true : isStringInteger(String.valueOf(rowData.get("measCnt")));
                                // 측정치 체크 (측정치는 빈값으로 들어오는 경우가 있음)
                                boolean checkMeasValue = StringUtil.isEmpty(String.valueOf(rowData.get("measValue"))) ? true : isStringDouble(String.valueOf(rowData.get("measValue")));
                                // 노출기준 체크 (노출기준은 빈값으로 들어오는 경우가 있음)
                                boolean checkExposureStd = StringUtil.isEmpty(String.valueOf(rowData.get("exposureStd"))) ? true : isStringDouble(String.valueOf(rowData.get("exposureStd")));

                                if (checkDept && checkProcess && checkHazard && checkWorkerCnt && checkOccurTime && checkMeasCnt && checkMeasDt && checkMeasValue && checkExposureStd) {
                                    rowData.put("workerCnt", Integer.parseInt(String.valueOf(rowData.get("workerCnt"))));
                                    rowData.put("occurTime", Integer.parseInt(String.valueOf(rowData.get("occurTime"))));
                                    rowData.put("measCnt", Integer.parseInt(String.valueOf(rowData.get("measCnt"))));
                                    // 노출기준초과여부
                                    if (!StringUtil.isEmpty(String.valueOf(rowData.get("measValue"))) && !StringUtil.isEmpty(String.valueOf(rowData.get("exposureStd")))) {
                                        if (Double.parseDouble(String.valueOf(rowData.get("measValue"))) > Double.parseDouble(String.valueOf(rowData.get("exposureStd")))) {
                                            rowData.put("exposureExcessFlag", "O");
                                        } else {
                                            rowData.put("exposureExcessFlag", "X");
                                        }
                                    }

                                    WorkMeasureResult workMeasureResult = new WorkMeasureResult();
                                    BeanUtils.populate(workMeasureResult, rowData);
                                    workMeasureResult.setPlantCd(plantCd);
                                    workMeasureResult.setWorkMeasPlanNo(workMeasPlanNo);
                                    workMeasureResult.setCreateUserId(createUserId);

                                    this.workMeasureResultMapper.createWorkMeasureResult(workMeasureResult);
                                    completeCount++;
                                } else {
                                    String failMessage = "";
                                    dontCount++;
                                    // if (!checkNoiseYn) {
                                    // failMessage =
                                    // this.appendString(failMessage, "소음여부는 'Y'
                                    // 또는 'N' 값이 들어가야합니다.");
                                    // }
                                    if (!checkPlant) {
                                        failMessage = this.appendString(failMessage, "사업장이 잘못되었습니다.");
                                    }
                                    if (!checkYear) {
                                        failMessage = this.appendString(failMessage, "측정년도가 잘못되었습니다.");
                                    }
                                    if (!checkMeasureHalfYear) {
                                        failMessage = this.appendString(failMessage, "분기코드가 잘못되었습니다.");
                                    }
                                    if (!checkMeasAgency) {
                                        failMessage = this.appendString(failMessage, "측정기관이 잘못되었습니다.");
                                    }
                                    if (!checkDept) {
                                        failMessage = this.appendString(failMessage, "부서코드가 잘못되었습니다.");
                                    }
                                    if (!checkProcess) {
                                        failMessage = this.appendString(failMessage, "공정코드가 잘못되었습니다.");
                                    }
                                    if (!checkHazard) {
                                        failMessage = this.appendString(failMessage, "유해인자코드가 잘못되었습니다.");
                                    }
                                    if (!checkWorkerCnt) {
                                        failMessage = this.appendString(failMessage, "[정수X]근로자수가 잘못되었습니다.");
                                    }
                                    if (!checkOccurTime) {
                                        failMessage = this.appendString(failMessage, "[정수X]유해인자발생시간이 잘못되었습니다.");
                                    }
                                    if (!checkMeasCnt) {
                                        failMessage = this.appendString(failMessage, "[정수X]측정횟수가 잘못되었습니다.");
                                    }
                                    if (!checkMeasDt) {
                                        failMessage = this.appendString(failMessage, "측정일자가 잘못되었습니다.");
                                    }
                                    if (!checkMeasValue) {
                                        failMessage = this.appendString(failMessage, "[숫자X]측정치가 잘못되었습니다.");
                                    }
                                    if (!checkExposureStd) {
                                        failMessage = this.appendString(failMessage, "[숫자X]노출기준이 잘못되었습니다.");
                                    }

                                    rowData.put("failMessage", failMessage);
                                }

                                uploadList.add(rowData);
                                totCount++;
                            } else {
                                break;
                            }
                        }

                        map.put("totCount", totCount);
                        map.put("completeCount", completeCount);
                        map.put("dontCount", dontCount);
                        map.put("success", true);
                        map.put("uploadList", uploadList);

                        WorkMeasurePlan workMeasurePlan = workMeasurePlanMapper.getWorkMeasurePlan(workMeasPlanNo);
                        if (workMeasurePlan.getWorkMeasStateCd().equals("WMS20")) {
                            // 계획접수 상태인 경우 결과입력중 단계로 변경
                            workMeasurePlan.setWorkMeasStateCd("WMS30");
                            workMeasurePlan.setUpdateUserId(createUserId);
                            this.workMeasurePlanMapper.updateWorkMeasurePlanStep(workMeasurePlan);
                        }
                    }
                }
            }
        } catch (Exception e) {
            map.put("success", false);
            map.put("message", "업로드 도중 에러발생");
        } finally {
            // 다 읽어드린 엑셀 파일은 지운다.
            this.fileStorageService.deleteFile(attachFiles.get(0).getFilePath());
            attachFileService.deleteFile(String.valueOf(attachFiles.get(0).getFileNo()));
        }
        return map;
    }

    public File setTempleteCodeSheet(File file, String fileName, int workMeasPlanNo) throws Exception {
        int dotIndex = fileName.lastIndexOf('.');
        String copyFileName = fileName.substring(0, dotIndex) + "_" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + fileName.substring(dotIndex);
        Path filePath = file.toPath();
        String fps = filePath.toString();
        File copyfile = new File(fps.substring(0, fps.lastIndexOf(File.separator) + 1).replace("form", "temp") + copyFileName);

        FileInputStream fis = null;
        XSSFWorkbook workbook = null;
        try {
            Files.copy(file.toPath(), copyfile.toPath());

            fis = new FileInputStream(copyfile);
            workbook = new XSSFWorkbook(fis);

            XSSFSheet sheet = workbook.createSheet("코드목록");

            CellStyle headerStyle = this.getCellStyle(workbook, 1);
            CellStyle dataStyle = this.getCellStyle(workbook, 2);
            // int maxRow = sheet.getPhysicalNumberOfRows();
            XSSFRow header = sheet.createRow(0);

            /** ## code header set */
            XSSFCell cell = header.createCell(0);
            cell.setCellValue("사업장코드");
            cell.setCellStyle(headerStyle);
            cell = header.createCell(1);
            cell.setCellValue("사업장명");
            cell.setCellStyle(headerStyle);

            cell = header.createCell(3);
            cell.setCellValue("유해인자코드");
            cell.setCellStyle(headerStyle);
            cell = header.createCell(4);
            cell.setCellValue("유해인자명");
            cell.setCellStyle(headerStyle);

            cell = header.createCell(6);
            cell.setCellValue("사업장");
            cell.setCellStyle(headerStyle);
            cell = header.createCell(7);
            cell.setCellValue("부서코드");
            cell.setCellStyle(headerStyle);
            cell = header.createCell(8);
            cell.setCellValue("부서명");
            cell.setCellStyle(headerStyle);

            cell = header.createCell(10);
            cell.setCellValue("사업장");
            cell.setCellStyle(headerStyle);
            cell = header.createCell(11);
            cell.setCellValue("공정코드");
            cell.setCellStyle(headerStyle);
            cell = header.createCell(12);
            cell.setCellValue("공정명");
            cell.setCellStyle(headerStyle);

            cell = header.createCell(14);
            cell.setCellValue("분기코드");
            cell.setCellStyle(headerStyle);
            cell = header.createCell(15);
            cell.setCellValue("분기명");
            cell.setCellStyle(headerStyle);

            cell = header.createCell(17);
            cell.setCellValue("구분코드");
            cell.setCellStyle(headerStyle);
            cell = header.createCell(18);
            cell.setCellValue("구분");
            cell.setCellStyle(headerStyle);

            /** ## 사업장 데이터 set */
            List<CodeMaster> plant = this.codeMasterMapper.getSelect("COM_PLANT_CD", "Y", new DefaultParam("kr"));
            for (int i = 0; i < plant.size(); i++) {
                XSSFRow data = sheet.createRow(i + 1);

                cell = data.createCell(0);
                cell.setCellValue(plant.get(i).getCode());
                cell.setCellStyle(dataStyle);
                cell = data.createCell(1);
                cell.setCellValue(plant.get(i).getCodeNm());
                cell.setCellStyle(dataStyle);
            }

            /** ## 유해인자 데이터 set */
            List<Hazard> hazards = this.hazardService.getHazards(null, null, null, null, null, "Y");
            for (int i = 0; i < hazards.size(); i++) {

                XSSFRow data = sheet.getRow(i + 1);
                if (data == null) {
                    data = sheet.createRow(i + 1);
                }

                cell = data.createCell(3);
                cell.setCellValue(hazards.get(i).getHazardCd());
                cell.setCellStyle(dataStyle);
                cell = data.createCell(4);
                cell.setCellValue(hazards.get(i).getHazardNmKo());
                cell.setCellStyle(dataStyle);
            }

            /** ## 부서 데이터 set */
            List<Dept> depts = this.workMeasureResultMapper.getDeptWorkTempleteInfos();
            for (int i = 0; i < depts.size(); i++) {

                XSSFRow data = sheet.getRow(i + 1);
                if (data == null) {
                    data = sheet.createRow(i + 1);
                }

                cell = data.createCell(6);
                cell.setCellValue(depts.get(i).getPlantNm());
                cell.setCellStyle(dataStyle);
                cell = data.createCell(7);
                cell.setCellValue(depts.get(i).getDeptCd());
                cell.setCellStyle(dataStyle);
                cell = data.createCell(8);
                cell.setCellValue(depts.get(i).getDeptNm());
                cell.setCellStyle(dataStyle);
            }

            /** ## 공정 데이터 set */
            List<Process> processes = this.processService.getProcesses("Y", null, null, null, null, new DefaultParam("kr"));
            for (int i = 0; i < processes.size(); i++) {

                XSSFRow data = sheet.getRow(i + 1);
                if (data == null) {
                    data = sheet.createRow(i + 1);
                }

                cell = data.createCell(10);
                cell.setCellValue(processes.get(i).getPlantNms());
                cell.setCellStyle(dataStyle);
                cell = data.createCell(11);
                cell.setCellValue(processes.get(i).getProcessCd());
                cell.setCellStyle(dataStyle);
                cell = data.createCell(12);
                cell.setCellValue(processes.get(i).getProcessNm());
                cell.setCellStyle(dataStyle);
            }

            /** ## 분기 데이터 set */
            List<CodeMaster> half = this.codeMasterMapper.getSelect("RSA_HALF_YEAR", "Y", new DefaultParam("kr"));
            for (int i = 0; i < half.size(); i++) {

                XSSFRow data = sheet.getRow(i + 1);
                if (data == null) {
                    data = sheet.createRow(i + 1);
                }

                cell = data.createCell(14);
                cell.setCellValue(half.get(i).getCode());
                cell.setCellStyle(dataStyle);
                cell = data.createCell(15);
                cell.setCellValue(half.get(i).getCodeNm());
                cell.setCellStyle(dataStyle);
            }

            /** ## 구분 데이터 set */
            XSSFRow data = sheet.getRow(1);
            if (data == null) {
                data = sheet.createRow(1);
            }
            cell = data.createCell(17);
            cell.setCellValue("C");
            cell.setCellStyle(dataStyle);
            cell = data.createCell(18);
            cell.setCellValue("화학적인자");
            cell.setCellStyle(dataStyle);

            data = sheet.getRow(2);
            if (data == null) {
                data = sheet.createRow(2);
            }
            cell = data.createCell(17);
            cell.setCellValue("P");
            cell.setCellStyle(dataStyle);
            cell = data.createCell(18);
            cell.setCellValue("물리적인자");
            cell.setCellStyle(dataStyle);

            for (int colNum = 0; colNum < header.getLastCellNum(); colNum++) {
                workbook.getSheetAt(2).autoSizeColumn(colNum);
            }

            // 계획 작성시 저장한 유해인자
            sheet = workbook.getSheetAt(0);

            WorkMeasurePlan workMeasurePlan = workMeasurePlanMapper.getWorkMeasurePlan(workMeasPlanNo);
            List<WorkMeasureResult> workMeasureResults = workMeasureResultMapper.getInitWorkMeasureResults(workMeasPlanNo);

            if (workMeasureResults != null && !workMeasureResults.isEmpty()) {
                for (int i = 0; i < workMeasureResults.size(); i++) {
                    XSSFRow rslt = sheet.createRow(i + 1);
                    // 사업장코드
                    cell = rslt.createCell(0);
                    cell.setCellValue(workMeasurePlan.getPlantCd());
                    cell.setCellStyle(dataStyle);

                    // 측정년도
                    cell = rslt.createCell(1);
                    cell.setCellValue(workMeasurePlan.getYear());
                    cell.setCellStyle(dataStyle);

                    // 분기코드
                    cell = rslt.createCell(2);
                    cell.setCellValue(workMeasurePlan.getHalfYearCd());
                    cell.setCellStyle(dataStyle);

                    // 측정기관
                    cell = rslt.createCell(3);
                    cell.setCellValue(workMeasurePlan.getMeasAgency());
                    cell.setCellStyle(dataStyle);

                    // 구분코드
                    cell = rslt.createCell(4);
                    cell.setCellValue(workMeasureResults.get(i).getWorkAreaGradeCd());
                    cell.setCellStyle(dataStyle);

                    // 부서코드
                    cell = rslt.createCell(5);
                    cell.setCellValue(workMeasureResults.get(i).getDeptCd());
                    cell.setCellStyle(dataStyle);

                    // 공정코드
                    cell = rslt.createCell(6);
                    cell.setCellValue(workMeasureResults.get(i).getProcessCd());
                    cell.setCellStyle(dataStyle);

                    cell = rslt.createCell(7);
                    cell.setCellValue("");
                    cell.setCellStyle(dataStyle);

                    // 유해인자코드
                    cell = rslt.createCell(8);
                    cell.setCellValue(workMeasureResults.get(i).getHazardCd());
                    cell.setCellStyle(dataStyle);

                    cell = rslt.createCell(9);
                    cell.setCellValue("");
                    cell.setCellStyle(dataStyle);

                    cell = rslt.createCell(10);
                    cell.setCellValue("");
                    cell.setCellStyle(dataStyle);

                    cell = rslt.createCell(11);
                    cell.setCellValue("");
                    cell.setCellStyle(dataStyle);

                    cell = rslt.createCell(12);
                    cell.setCellValue("");
                    cell.setCellStyle(dataStyle);

                    cell = rslt.createCell(13);
                    cell.setCellValue("");
                    cell.setCellStyle(dataStyle);

                    cell = rslt.createCell(14);
                    cell.setCellValue("");
                    cell.setCellStyle(dataStyle);

                    cell = rslt.createCell(15);
                    cell.setCellValue("");
                    cell.setCellStyle(dataStyle);

                    cell = rslt.createCell(16);
                    cell.setCellValue("");
                    cell.setCellStyle(dataStyle);

                    cell = rslt.createCell(17);
                    cell.setCellValue("");
                    cell.setCellStyle(dataStyle);

                    cell = rslt.createCell(18);
                    cell.setCellValue("");
                    cell.setCellStyle(dataStyle);

                    cell = rslt.createCell(19);
                    cell.setCellValue("");
                    cell.setCellStyle(dataStyle);

                    cell = rslt.createCell(20);
                    cell.setCellValue("");
                    cell.setCellStyle(dataStyle);

                    cell = rslt.createCell(21);
                    cell.setCellValue("");
                    cell.setCellStyle(dataStyle);

                    cell = rslt.createCell(22);
                    cell.setCellValue("");
                    cell.setCellStyle(dataStyle);

                    cell = rslt.createCell(23);
                    cell.setCellValue("");
                    cell.setCellStyle(dataStyle);

                    cell = rslt.createCell(24);
                    cell.setCellValue("");
                    cell.setCellStyle(dataStyle);
                }
            }

            FileOutputStream outputStream = new FileOutputStream(copyfile);
            workbook.write(outputStream);
        } catch (Exception e) {
            throw new RestAuthException(HttpStatus.BAD_REQUEST, e.getMessage(), ExceptionType.INTERNAL_SERVER_ERROR.name());
        } finally {
            try {
                if (workbook != null) {
                    workbook.close();
                }
                if (fis != null) {
                    fis.close();
                }
            } catch (Exception e) {
                throw new RestAuthException(HttpStatus.BAD_REQUEST, e.getMessage(), ExceptionType.INTERNAL_SERVER_ERROR.name());
            }
        }
        return copyfile;
    }

    public CellStyle getCellStyle(XSSFWorkbook workbook, int type) throws Exception {
        // 셀 스타일 및 폰트 설정
        CellStyle style = workbook.createCellStyle();
        XSSFDataFormat dataFormat = workbook.createDataFormat();

        if (type == 1) { // 헤더 style
            // 정렬
            style.setAlignment(CellStyle.ALIGN_CENTER); // 가운데 정렬
            style.setVerticalAlignment(CellStyle.VERTICAL_CENTER); // 높이 가운데 정렬
            // 배경색
            style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
            style.setFillPattern(CellStyle.SOLID_FOREGROUND);
            // 테두리 선 (우,좌,위,아래)
            style.setBorderRight(HSSFCellStyle.BORDER_THIN);
            style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            style.setBorderTop(HSSFCellStyle.BORDER_THIN);
            style.setBorderBottom(HSSFCellStyle.BORDER_THIN);

            // 폰트 설정
            Font font = workbook.createFont();
            font.setFontName("나눔고딕"); // 글씨체
            font.setFontHeight((short) (11 * 20)); // 사이즈
            // (굵게)
            style.setFont(font);
        } else if (type == 2) { // 일반 style
            // 정렬
            style.setAlignment(CellStyle.ALIGN_CENTER); // 가운데 정렬
            style.setVerticalAlignment(CellStyle.VERTICAL_CENTER); // 높이 가운데 정렬
            // 테두리 선 (우,좌,위,아래)
            style.setBorderRight(HSSFCellStyle.BORDER_THIN);
            style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            style.setBorderTop(HSSFCellStyle.BORDER_THIN);
            style.setBorderBottom(HSSFCellStyle.BORDER_THIN);

            // 폰트 설정
            Font font = workbook.createFont();
            font.setFontName("나눔고딕"); // 글씨체
            font.setFontHeight((short) (11 * 20)); // 사이즈
            // (굵게)
            style.setFont(font);
            // 셀서식: text 포맷
            style.setDataFormat(dataFormat.getFormat("@"));
        }
        return style;
    }

    /**
     * 문자열 숫자인지 문자인지 확인
     *
     * @param val
     * @return
     */
    public boolean isStringInteger(String val) {
        try {
            Integer.parseInt(val);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * 문자열 실수인지 문자인지 확인
     *
     * @param val
     * @return
     */
    public boolean isStringDouble(String val) {
        try {
            Double.parseDouble(val);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * 문자열더하기(\r\n추가 후)
     *
     * @param source
     *            원문자열
     * @param append
     *            추가문자열
     * @return 반환문자
     */
    private String appendString(String source, String append) {
        source += "■" + append;

        return source;
    }

    /**
     * 작업환경측정결과 통계
     *
     * @param plantCd
     *            공정코드
     * @param deptCd
     *            부서
     * @param processCd
     *            공정코드
     * @param hazardNm
     *            유해인자명
     * @param fromYear
     *
     * @param toYear
     *
     * @param yearPeriod
     *
     * @return 작업환경측정결과 통계 목록
     * @throws Exception
     *             예외
     */
    public List<WorkMeasureResult> getWorkMeasureResultStatus(String plantCd, String deptCd, String processCd, String hazardNm, String fromYear, String toYear, String[] yearPeriod, String deptSubYn) throws Exception {
        String yearPeriodStr = String.join(", ", yearPeriod);

        return workMeasureResultMapper.getWorkMeasureResultStatus(plantCd, deptCd, processCd, hazardNm, fromYear, toYear, yearPeriod, yearPeriodStr, deptSubYn);
    }

    /**
     * 작업환경측정결과 통계 차트
     *
     * @param plantCd
     *            공정코드
     * @param deptCd
     *            부서
     * @param processCd
     *            공정코드
     * @param hazardNm
     *            유해인자명
     * @param exposureExcessRate
     *            측정값 초과율
     * @param fromYear
     *
     * @param toYear
     *
     * @param monthPeriod
     *
     * @return 작업환경측정결과 통계 목록
     * @throws Exception
     *             예외
     */
    public Map<String, Object> getWorkMeasureResultChart(String plantCd, String deptCd, String processCd, String hazardNm, String exposureExcessRate, String fromYear, String toYear, String[] monthPeriod) throws Exception {
        String monthPeriodStr = String.join(", ", monthPeriod);

        ObjectMapper mapper = new ObjectMapper(); // jackson's objectmapper

        Map<String, Object> resultMap = new HashMap<>();
        double max = 0;

        List<Map<String, Object>> resultList = workMeasureResultMapper.getWorkMeasureResultChart(plantCd, deptCd, processCd, hazardNm, exposureExcessRate, fromYear, toYear, monthPeriod, monthPeriodStr);

        if (resultList.size() > 0) {

            for (Map<String, Object> rowMap : resultList) {
                for (String date : monthPeriod) {
                    date = date.replace("[", "").replace("]", "");

                    if (!rowMap.containsKey(date)) {
                        rowMap.put(date, 0);
                    } else {

                        double val = (double) rowMap.get(date);
                        if (max < val)
                            max = val;
                    }
                }
            }

            // Y축 최대값 설정
            if (max < 1) { // 소수점
                max = 1;
            } else {
                int intMax = (int) max;
                int maxLength = String.valueOf(intMax).length();

                if (maxLength == 1) {
                    max = Math.ceil(max / 10) * 10.0;
                } else {
                    // double tmp = 10 * maxLength;
                    // System.out.println(tmp);

                    double tmp = Math.pow(10, maxLength - 1);

                    max = Math.ceil(max / tmp) * tmp;
                }
            }
        }

        resultMap.put("data", resultList);
        resultMap.put("max", max);

        return resultMap;
    }

    /**
     * 계획 결재완료시 작업환경측정결과 조회
     *
     * @param workMeasPlanNo
     *            작업환경측정계획 번호
     *
     * @return 계획 결재완료시 작업환경측정결과 목록
     * @throws Exception
     *             예외
     */
    public List<WorkMeasureResult> getInitWorkMeasureResults(int workMeasPlanNo) throws Exception {
        return workMeasureResultMapper.getInitWorkMeasureResults(workMeasPlanNo);
    }

    /**
     * 작업환경측정결과 저장
     *
     * @param workMeasureResults
     *            작업환경측정결과 목록
     *
     * @return 결과
     * @throws Exception
     *             예외
     */
    @Transactional
    public int saveWorkMeasureResults(List<WorkMeasureResult> workMeasureResults) throws Exception {
        int resultNo = 0;

        if (CollectionUtils.isNotEmpty(workMeasureResults)) {
            for (WorkMeasureResult workMeasureResult : workMeasureResults) {
                if (workMeasureResult.getWorkMeasRsltNo() > 0) {
                    resultNo += this.updateWorkMeasureResult(workMeasureResult);
                } else {
                    resultNo += this.createWorkMeasureResult(workMeasureResult);
                }
            }
        }

        return resultNo;
    }
}
