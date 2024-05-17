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
package com.she.rsa.assess.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.icu.text.SimpleDateFormat;
import com.she.common.mapper.AttachFileMapper;
import com.she.common.model.AttachFile;
import com.she.rsa.assess.mapper.AssessActionMapper;
import com.she.rsa.model.AssessAction;
import com.she.rsa.model.RiskAssess;
import com.she.rsa.model.RiskMatrix;
import com.she.utils.ExcelReader;
import com.she.utils.FileUtil;

/**
 * 평가계획 기능정의
 *
 */
@Service
public class AssessActionService {
    private static Logger logger = LoggerFactory.getLogger(AssessActionService.class);

    @Autowired
    private AssessActionMapper assessActionMapper;
    @Autowired
    private AttachFileMapper attachFileMapper;

    /**
     * 위험성평가 조회
     *
     * @param jobId
     *            직무번호
     * @param assessPlanNo
     *            평가계획번호
     * @return 위험성평가 목록
     * @throws Exception
     */
    public List<RiskAssess> getRiskAssesses(int jobId, String assessPlanNo) throws Exception {
        return assessActionMapper.getRiskAssesses(jobId, assessPlanNo);
    }

    /**
     * 위험성평가 항목 생성 (kras)
     *
     * @param RiskAssess
     *            위험성평가 항목
     * @return 위험성평가 번호
     * @throws Exception
     */
    public int createRiskKras(RiskAssess riskAssess) throws Exception {
        if (riskAssess.getKrasCnt() == 0) {
            // 해당 위험성평가 계획에서 처음 평가를 진행하는 경우에 평가의 진행단계를 진행중으로 변경 한다.
            AssessAction assessAction = new AssessAction();
            assessAction.setAssessPlanNo(riskAssess.getAssessPlanNo());
            assessAction.setUpdateUserId(assessAction.getUpdateUserId());
            assessActionMapper.updateAssessStep(assessAction);

            assessActionMapper.createRiskKras(riskAssess);
        } else {
            assessActionMapper.updateRiskKras(riskAssess);
        }

        return riskAssess.getAssessPlanNo();
    }

    /**
     * 위험성평가 항목 생성 (kras)
     *
     * @param RiskAssess
     *            위험성평가 항목
     * @return 위험성평가 번호
     * @throws Exception
     */
    public int createRiskKrasList(List<RiskAssess> riskAssessList) throws Exception {
        for (RiskAssess riskAssess : riskAssessList) {
            if (riskAssess.getKrasCnt() == 0) {
                // 해당 위험성평가 계획에서 처음 평가를 진행하는 경우에 평가의 진행단계를 진행중으로 변경 한다.
                RiskAssess assess = assessActionMapper.getRiskKras(riskAssess.getAssessPlanNo(), riskAssess.getProcessCd(), riskAssess.getJobId(), riskAssess.getJobStepId(), riskAssess.getRiskHazardNo());

                if (assess == null) {
                    AssessAction assessAction = new AssessAction();
                    assessAction.setAssessPlanNo(riskAssess.getAssessPlanNo());
                    assessAction.setUpdateUserId(assessAction.getUpdateUserId());
                    assessActionMapper.updateAssessStep(assessAction);

                    assessActionMapper.createRiskKras(riskAssess);
                } else {
                    assessActionMapper.updateRiskKras(riskAssess);
                }
            }
        }
        return 1;
    }

    /**
     * 위험 Matrix 빈도 조회
     *
     * @param assessTypeNo
     *            평가기법 번호
     * @return 위험 Matrix 빈도 목록
     * @throws Exception
     */
    public List<RiskMatrix> getFrequencyRiskMatrixes(int assessTypeNo) throws Exception {
        return assessActionMapper.getFrequencyRiskMatrixes(assessTypeNo);
    }

    /**
     * 위험 Matrix 강도 조회
     *
     * @param assessTypeNo
     *            평가기법 번호
     * @return 위험 Matrix 강도 목록
     * @throws Exception
     */
    public List<RiskMatrix> getStrongRiskMatrixes(int assessTypeNo) throws Exception {
        return assessActionMapper.getStrongRiskMatrixes(assessTypeNo);
    }

    /**
     * 위험 Matrix 위험도 조회
     *
     * @param assessTypeNo
     *            평가기법 번호
     * @return 위험 Matrix 위험도 목록
     * @throws Exception
     */
    public List<RiskMatrix> getRiskMatrixes(int assessTypeNo) throws Exception {
        return assessActionMapper.getRiskMatrixes(assessTypeNo);
    }

    /**
     * 평가직무 job 조회 (JSA)
     *
     * @param assessPlanNo
     *            평가계획 번호
     * @param deptCd
     *            부서코드
     * @return 평가직무 목록
     * @throws Exception
     */
    public List<AssessAction> getAssessActionsJsaJob(String assessPlanNo, String plantCd, String deptCd, String processCd, String jobNm) throws Exception {
        return assessActionMapper.getAssessActionsJsaJob(assessPlanNo, plantCd, deptCd, processCd, jobNm);
    }

    /**
     * 평가직무 조회 (JSA)
     *
     * @param assessPlanNo
     *            평가계획 번호
     * @param deptCd
     *            부서코드
     * @return 평가직무단계 목록
     * @throws Exception
     */
    public List<AssessAction> getAssessActionsJsa(int assessPlanNo, String deptCd, int jobId, String processCd) throws Exception {
        return assessActionMapper.getAssessActionsJsa(assessPlanNo, deptCd, jobId, processCd);
    }

    /**
     * 위험성평가 조회 (JSA)
     *
     * @param jobId
     *            직무번호
     * @return 위험성평가 목록
     * @throws Exception
     */
    public List<RiskAssess> getRiskAssessesJsa(int jobId, int jobStepNo, int assessPlanNo) throws Exception {
        return assessActionMapper.getRiskAssessesJsa(jobId, jobStepNo, assessPlanNo);
    }

    /**
     * 위험성평가 항목 생성 (JSA)
     *
     * @param RiskAssess
     *            위험성평가 항목
     * @return 위험성평가 번호
     * @throws Exception
     */
    public int createRiskJsa(RiskAssess riskAssess) throws Exception {
        if (riskAssess.getJsaCnt() == 0) {
            assessActionMapper.createRiskJsa(riskAssess);
        } else {
            assessActionMapper.updateRiskJsa(riskAssess);
        }

        return riskAssess.getAssessPlanNo();
    }

    /**
     * 위험성평가 엑셀 다운로드 (JSA)
     *
     * @param parameter
     *
     * @return 엑셀파일
     * @throws Exception
     */

    public File createExcelAssessSupply(String assessPlanNo, String processCd, String plantCd) throws Exception {

        XSSFWorkbook objWorkBook = new XSSFWorkbook();
        XSSFSheet objSheet = objWorkBook.createSheet("위험성평가 결과(JSA) 업로드 양식");

        XSSFRow objRow = null;
        XSSFCell objCell = null;

        int rowIndex1 = 0;

        // 헤더부분
        objRow = objSheet.createRow(rowIndex1++);

        CellStyle titleStyle = this.getCellStyle(objWorkBook, 1);

        this.createCellAndSetHeaderRow(objRow, "No/평가계획No/평가명/부서코드/부서명/공정코드/공정/매트릭스명/작업코드/작업명/작업단계코드/작업단계명/유해위험코드/유해위험요인 분류/유해위험요인/*개선전위험도 빈도/*개선전위험도 강도/*개선대책/*개선후위험도 빈도/*개선후위험도 강도/평가상세/*평가일자".split("/"));
        // 데이터부분
        List<LinkedHashMap<String, Object>> getAssess = this.assessActionMapper.getAssessExcel(assessPlanNo, processCd, plantCd);
        for (LinkedHashMap<String, Object> lists : getAssess) {
            List<Object> list = new ArrayList<>(lists.values());
            objRow = objSheet.createRow(rowIndex1++);
            this.createCellAndSetDataRow(objRow, list, 0, 1, 8, 10, 12);
        }
        // 셀 너비
        XSSFRow colAutoWidthRow = objWorkBook.getSheetAt(0).getRow(1);
        for (int i = colAutoWidthRow.getFirstCellNum(); i < colAutoWidthRow.getLastCellNum(); i++) {
            objSheet.autoSizeColumn(i);

            int widthSize = i == 13 || i == 15 || i == 16 || i == 18 || i == 19 || i == 9 ? objSheet.getColumnWidth(i) + 512 * 5 : objSheet.getColumnWidth(i) + 512 * 2;
            if (i == 2) {
                widthSize = objSheet.getColumnWidth(i) + 512 * 7;
            } else if (i == 20 || i == 17) {
                widthSize = objSheet.getColumnWidth(i) + 512 * 10;
            }
            objSheet.setColumnWidth(i, widthSize);

        }
        objRow = objSheet.createRow(rowIndex1++);
        objCell = objRow.createCell(0);
        objCell.setCellValue("*주의사항*");
        objCell.setCellStyle(titleStyle);
        objSheet.addMergedRegion(new CellRangeAddress(getAssess.size() + 1, getAssess.size() + 1, 0, 21)); // 제목

        objRow = objSheet.createRow(rowIndex1++);
        objCell = objRow.createCell(0);
        objCell.setCellValue("빈도/강도는 숫자로입력(0은안됨), 필수값 입력(*), 추가하지말기(모든 평가대상 작업->작업단계에 대한 유해위험요인들입니다. 자동으로 입력된 값들은 수정하지말기");
        objCell.setCellStyle(titleStyle);
        objSheet.addMergedRegion(new CellRangeAddress(getAssess.size() + 2, getAssess.size() + 3, 0, 21)); // 제목

        String path = FileUtil.getStoreFilePath() + File.separator + "assessactionexcel" + com.she.security.util.DateUtil.currentDateYYYYMMDD();// 폴더
        // 경로
        File folder = new File(path);
        folder.setExecutable(false, true);
        folder.setReadable(true);
        folder.setWritable(false, true);
        if (!folder.exists()) {
            folder.mkdir(); // 폴더 생성합니다.
        }

        String uuid = UUID.randomUUID().toString();
        FileOutputStream output = null;
        try {
            output = new FileOutputStream(path + File.separator + uuid + ".xlsx");
            objWorkBook.write(output);// 파일 생성

            // this.readOfXlsx(path + File.separator + uuid + ".xlsx");

            output.close();
            return new File(path + File.separator + uuid + ".xlsx");
        } catch (FileNotFoundException e) {
            logger.error(e.getMessage());
        } catch (IOException e) {
            logger.error(e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        } finally {
            output.close();
        }
        return null;
    }

    public List<String[][]> readOfXlsx(String filePath) throws Exception {
        List<String[][]> list = new ArrayList<String[][]>();

        FileInputStream fis = null;
        XSSFWorkbook workbook = null;
        try {
            fis = new FileInputStream(filePath);
            workbook = new XSSFWorkbook(fis);

            XSSFSheet curSheet;
            XSSFRow curRow;
            XSSFCell curCell;
            for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {
                curSheet = workbook.getSheetAt(sheetIndex);

                int maxRow = curSheet.getPhysicalNumberOfRows();
                int maxCell = (maxRow > 0) ? curSheet.getRow(0).getPhysicalNumberOfCells() : 0;
                String[][] sheetData = new String[maxRow][maxCell];

                for (int rowIndex = 0; rowIndex < curSheet.getPhysicalNumberOfRows(); rowIndex++) {
                    curRow = curSheet.getRow(rowIndex);
                    for (int cellIndex = 0; cellIndex < curRow.getPhysicalNumberOfCells(); cellIndex++) {
                        curCell = curRow.getCell(cellIndex);
                        String value = "";
                        switch (curCell.getCellType()) {
                        case XSSFCell.CELL_TYPE_FORMULA:
                            value = curCell.getCellFormula();
                            break;
                        case XSSFCell.CELL_TYPE_NUMERIC:
                            if (DateUtil.isCellDateFormatted(curCell)) {
                                SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
                                value = f.format(curCell.getDateCellValue()) + "";
                            } else {
                                value = curCell.getNumericCellValue() + "";
                            }
                            break;
                        case XSSFCell.CELL_TYPE_STRING:
                            if (curCell.equals("")) {
                                value = curCell.getNumericCellValue() + "";
                                break;
                            } else {
                                value = curCell.getStringCellValue() + "";
                                break;
                            }
                        case XSSFCell.CELL_TYPE_BOOLEAN:
                            value = curCell.getBooleanCellValue() + "";
                            break;
                        case XSSFCell.CELL_TYPE_ERROR:
                            value = curCell.getErrorCellValue() + "";
                            break;
                        default:
                            value = "";
                            break;
                        }

                        sheetData[rowIndex][cellIndex] = value;
                    }
                }

                list.add(sheetData);
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        } finally {
            try {
                if (workbook != null) {
                    workbook.close();
                }
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                logger.error(e.getMessage());
            } catch (Exception e) {
                logger.error(e.getMessage());
                throw e;
            }
        }

        return list;
    }

    /**
     * 위험성평가 엑셀 업로드(JSA)
     *
     * @param taskClassNm
     * @param taskKey
     * @param uploadUserId
     * @return
     * @throws Exception
     */
    public Map<String, Object> uploadExcelAssess(String taskClassNm, String taskKey, String uploadUserId, String assessTypeNo) throws Exception {
        // 위험성평가 엑셀 업로드
        List<AttachFile> uploadedPerson = attachFileMapper.getUploadFiles(taskClassNm, taskKey);

        RiskMatrix matrix = assessActionMapper.getMaxRiskMatrix(assessTypeNo);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("success", false);
        map.put("message", "");
        map.put("uploadResult", null);
        map.put("errorInfo", null);

        int totalCount = 0;
        int successCount = 0;
        int failCount = 0;

        String failMsg = "";
        File excel = new File(uploadedPerson.get(0).getFilePath());
        ExcelReader reader = new ExcelReader();
        List<String[][]> sheets = reader.read(excel);
        if (sheets != null) {
            String sheetName = "위험성평가 결과 업로드(JSA) 양식";
            String[][] sheet = sheets.get(0);
            String[] sheetHeader = new String[] { "No", "평가계획No", "평가명", "부서코드", "부서명", "공정코드", "공정", "매트릭스명", "작업코드", "작업명", "작업단계코드", "작업단계명", "유해위험코드", "유해위험요인 분류", "유해위험요인", "*개선전위험도 빈도", "*개선전위험도 강도", "*개선대책", "*개선후위험도 빈도", "*개선후위험도 강도", "평가상세", "*평가일자" };
            // 헤더 양식확인
            if (sheet.length > 0 && !reader.excelHeaderCheck(sheet[0], sheetHeader)) {
                map.put("message", "업로드양식이 오류: " + sheetName + " 시트의 헤더가 다릅니다.");
            } else {
                List<Map<String, Object>> uploadResults = new ArrayList<Map<String, Object>>();
                List<Map<String, Object>> errorInfos = new ArrayList<Map<String, Object>>();
                List<Map<String, String>> excelDataList = new ArrayList<Map<String, String>>();

                Map<String, Object> resultMap = new HashMap<String, Object>();
                StringBuilder rowCheck;

                for (int row = 1; row < sheet.length - 3; row++) {
                    rowCheck = new StringBuilder();

                    for (int i = 0; i < sheet[row].length; i++) {
                        rowCheck.append(sheet[row][i].trim());
                    }

                    if (rowCheck.length() > 0) {
                        String failMessage = "";
                        String assessPlanNo = sheet[row][1].trim();
                        String assessNm = sheet[row][2].trim();
                        // 부서코드
                        String deptCd = sheet[row][3].trim();
                        // 부서명
                        String deptNm = sheet[row][4].trim();
                        // 공정코드
                        String processCd = sheet[row][5].trim();
                        // 공정명
                        String processNm = sheet[row][6].trim();
                        // 작업코드
                        String jobId = sheet[row][8].trim();
                        // 작업명
                        String jobNm = sheet[row][9].trim();
                        // 작업단계코드
                        String jobStepId = sheet[row][10].trim();
                        // 작업단계명
                        String jobStepNm = sheet[row][11].trim();
                        // 유해위험요인 코드
                        String riskHazardNo = sheet[row][12].trim();
                        // 유해위험요인 분류
                        String pRiskHazardNm = sheet[row][13].trim();
                        // 유해위험요인
                        String riskHazardNm = sheet[row][14].trim();
                        // 개선전위험도 빈도
                        String beforeFrequency = sheet[row][15].trim();
                        // 개선전위험도 강도
                        String beforeStrong = sheet[row][16].trim();
                        // 개선대책
                        String improve = sheet[row][17].trim();
                        // 개선후위험도 빈도
                        String afterFrequency = sheet[row][18].trim();
                        // 개선후위험도 강도
                        String afterStrong = sheet[row][19].trim();
                        // 평가상세
                        String evalDesc = sheet[row][20].trim();
                        // 평가일자
                        String assessDate = sheet[row][21].trim();

                        // 사번 확인
                        if (StringUtils.isNotBlank(beforeFrequency) && StringUtils.isNotBlank(beforeStrong) && StringUtils.isNotBlank(improve) && StringUtils.isNotBlank(afterFrequency) && StringUtils.isNotBlank(afterStrong) && StringUtils.isNotBlank(assessDate)) {
                            try {
                                failMsg = "개선전위험도 빈도";
                                int cnt = Math.round(Float.parseFloat(beforeFrequency));
                                failMsg = "개선전위험도 강도";
                                int cnt2 = Math.round(Float.parseFloat(beforeStrong));
                                failMsg = "개선후위험도 빈도";
                                int cnt3 = Math.round(Float.parseFloat(afterFrequency));
                                failMsg = "개선후위험도 강도";
                                int cnt4 = Math.round(Float.parseFloat(afterStrong));

                                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                Date formattedDate = dateFormat.parse(assessDate);

                                if (cnt > matrix.getFrequencySize()) {
                                    failMsg = "개선전위험도 빈도는 기준정보 Matrix(빈도/강도)보다 높게 입력하였습니다.";
                                    throw new Exception(failMsg);
                                } else if (cnt2 > matrix.getStrongSize()) {
                                    failMsg = "개선전위험도  강도는 기준정보 Matrix(빈도/강도)보다 높게 입력하였습니다.";
                                    throw new Exception(failMsg);
                                } else if (cnt3 > matrix.getFrequencySize()) {
                                    failMsg = "개선후위험도 빈도는 기준정보 Matrix(빈도/강도)보다 높게 입력하였습니다.";
                                    throw new Exception(failMsg);
                                } else if (cnt4 > matrix.getStrongSize()) {
                                    failMsg = "개선후위험도 강도는 기준정보 Matrix(빈도/강도)보다 높게 입력하였습니다.";
                                    throw new Exception(failMsg);
                                }

                                if (cnt == 0 || cnt2 == 0 || cnt3 == 0 || cnt4 == 0) {
                                    failMsg = "빈도/강도는 0이 될 수 없습니다";
                                    throw new Exception(failMsg);
                                }
                                successCount++;
                                Map<String, String> userMap = new HashMap<String, String>();
                                userMap.put("assessPlanNo", String.valueOf(Math.round(Float.parseFloat(assessPlanNo))));
                                userMap.put("assessTypeNo", assessTypeNo);
                                userMap.put("deptCd", deptCd);
                                userMap.put("deptNm", deptNm);
                                userMap.put("processCd", processCd);
                                userMap.put("processNm", processNm);
                                userMap.put("jobId", String.valueOf(Math.round(Float.parseFloat(jobId))));
                                userMap.put("jobNm", jobNm);
                                userMap.put("jobStepId", String.valueOf(Math.round(Float.parseFloat(jobStepId))));
                                userMap.put("jobStepNm", jobStepNm);
                                userMap.put("riskHazardNo", String.valueOf(Math.round(Float.parseFloat(riskHazardNo))));
                                userMap.put("pRiskHazardNm", pRiskHazardNm);
                                userMap.put("riskHazardNm", riskHazardNm);
                                userMap.put("currentFrequencySize", String.valueOf(cnt));
                                userMap.put("currentStrongSize", String.valueOf(cnt2));
                                userMap.put("currentRiskSize", String.valueOf(cnt * cnt2));
                                userMap.put("improve", improve);
                                userMap.put("afterRiskSize", String.valueOf(cnt3 * cnt4));
                                userMap.put("afterFrequencySize", String.valueOf(cnt3));
                                userMap.put("afterStrongSize", String.valueOf(cnt4));
                                userMap.put("evalDesc", evalDesc);
                                userMap.put("assessDate", assessDate);
                                excelDataList.add(userMap);
                            } catch (NumberFormatException e) {
                                // 숫자 입력이 아닐시
                                failMessage += "■ " + failMsg + "는 숫자로 입력해야합니다.";
                                failCount++;
                                Map<String, Object> errorInfo = new HashMap<String, Object>();
                                errorInfo.put("classNm", sheetName);
                                errorInfo.put("failRow", row + 1);
                                errorInfo.put("failMessage", failMessage);
                                errorInfo.put("remark", "");
                                errorInfos.add(errorInfo);
                            } catch (ParseException ex) {
                                // date 타입이 아닐시
                                failMessage += "■ 평가일자를 다시 입력하세요.";
                                failCount++;
                                Map<String, Object> errorInfo = new HashMap<String, Object>();
                                errorInfo.put("classNm", sheetName);
                                errorInfo.put("failRow", row + 1);
                                errorInfo.put("failMessage", failMessage);
                                errorInfo.put("remark", "");
                                errorInfos.add(errorInfo);
                            } catch (Exception e) {
                                // 기준정보 matrix보다 빈도/강도가 높을때, 빈도/강도가 0일때
                                failMessage += "■ 기준정보 matrix보다 빈도/강도가 높거나, 빈도/강도가 0입니다.";
                                failCount++;
                                Map<String, Object> errorInfo = new HashMap<String, Object>();
                                errorInfo.put("classNm", sheetName);
                                errorInfo.put("failRow", row + 1);
                                errorInfo.put("failMessage", failMessage);
                                errorInfo.put("remark", "");
                                errorInfos.add(errorInfo);
                            }
                        } else {
                            // 입력이 비어있을때
                            failMsg = "평가일자";
                            if (beforeFrequency.equals("")) {
                                failMsg = "개선전위험도 빈도";
                            } else if (beforeStrong.equals("")) {
                                failMsg = "개선전위험도 강도";
                            } else if (afterFrequency.equals("")) {
                                failMsg = "개선후위험도 빈도";
                            } else if (afterStrong.equals("")) {
                                failMsg = "개선후위험도 강도";
                            } else if (improve.equals("")) {
                                failMsg = "개선대책";
                            }
                            failMessage += "■ 필수값을 입력하세요.";
                            failCount++;
                            Map<String, Object> errorInfo = new HashMap<String, Object>();
                            errorInfo.put("classNm", sheetName);
                            errorInfo.put("failRow", row + 1);
                            errorInfo.put("failMessage", failMessage);
                            errorInfo.put("remark", failMsg);
                            errorInfos.add(errorInfo);
                        }
                    } else {
                        continue;
                    }
                }
                resultMap.put("classNm", sheetName);
                resultMap.put("totalCount", totalCount);
                resultMap.put("successCount", successCount);
                resultMap.put("failCount", failCount);

                // 업로드결과
                uploadResults.add(resultMap);

                map.put("success", true);
                map.put("uploadResult", uploadResults);
                map.put("errorInfo", errorInfos);
                map.put("excelDataList", excelDataList);
            }
        } else {
            map.put("message", "업로드 오류: 파일을 읽을 수 없습니다.");
        }

        return map;
    }

    /**
     *
     * @param row
     *            셀을 넣어줄 row
     * @param cellValueList
     *            셀에 넣어줄 값(String) 배열
     */
    private void createCellAndSetHeaderRow(XSSFRow row, String... cellValueList) {
        for (int i = 0; i < cellValueList.length; i++) {
            row.createCell(i).setCellValue((String) cellValueList[i]);
        }
    }

    /**
     *
     * @param row
     *            셀을 넣어줄 row
     * @param cellValueList
     *            셀에 넣어줄 값(String) 배열
     */
    private void createCellAndSetDataRow(XSSFRow row, List<Object> cellValueList) {
        String[] stringCellValue = new String[cellValueList.size()];
        int i = 0;
        for (Object o : cellValueList) {
            stringCellValue[i++] = (String) o;
        }
        createCellAndSetHeaderRow(row, stringCellValue);
    }

    /**
     *
     * @param row
     *            셀을 넣어줄 row
     * @param cellValueList
     *            셀에 넣어줄 값(Object) 배열
     * @param num
     *            숫자 타입으로 바꿔줄 index 번호(복수개가능)
     */
    private void createCellAndSetDataRow(XSSFRow row, List<Object> cellValueList, int... num) {
        for (int i = 0; i < cellValueList.size(); i++) {
            if (isContainsNumber(i, num)) {
                row.createCell(i).setCellValue(Double.parseDouble(String.valueOf(cellValueList.get(i))));
            } else {
                row.createCell(i).setCellValue((String) cellValueList.get(i));
            }
        }
    }

    private boolean isContainsNumber(int value, int... compareValue) {
        for (int i = 0; i < compareValue.length; i++) {
            if (value == compareValue[i]) {
                return true;
            }
        }
        return false;
    }

    public CellStyle getCellStyle(XSSFWorkbook writebook, int type) throws Exception {

        // 셀 스타일 및 폰트 설정
        CellStyle style = writebook.createCellStyle();

        if (type == 1) { // 제목 style
            // 정렬
            style.setAlignment(CellStyle.ALIGN_CENTER); // 가운데 정렬
            style.setVerticalAlignment(CellStyle.VERTICAL_CENTER); // 높이 가운데 정렬

            // 폰트 설정
            Font font = writebook.createFont();
            font.setFontName("나눔고딕"); // 글씨체
            font.setFontHeight((short) (15 * 20)); // 사이즈
            font.setBoldweight(Font.BOLDWEIGHT_BOLD); // 볼드
            // (굵게)
            style.setFont(font);
        }
        if (type == 2) { // 헤더 style
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
            Font font = writebook.createFont();
            font.setFontName("나눔고딕"); // 글씨체
            font.setFontHeight((short) (11 * 20)); // 사이즈
            // (굵게)
            style.setFont(font);
        }
        return style;
    }

}
