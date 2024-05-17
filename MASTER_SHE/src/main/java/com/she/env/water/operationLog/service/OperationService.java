package com.she.env.water.operationLog.service;

import java.io.File;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.she.common.model.DefaultParam;
import com.she.config.GlobalSettings;
import com.she.env.water.baseInfo.mapper.MonPosMapper;
import com.she.env.water.facility.mapper.PreventFacBreakHistMapper;
import com.she.env.water.model.ChemChkResult;
import com.she.env.water.model.DischChkResult;
import com.she.env.water.model.IngrChkResult;
import com.she.env.water.model.OpLogResult;
import com.she.env.water.model.PreventFacBreakHist;
import com.she.env.water.model.PreventFacRunTm;
import com.she.env.water.model.PwrcChkResult;
import com.she.env.water.model.SuplChkResult;
import com.she.env.water.model.TestItemResult;
import com.she.env.water.model.TestItemResultPrint;
import com.she.env.water.model.WasteDischFacRunTm;
import com.she.env.water.operationLog.mapper.OperationMapper;
import com.she.utils.ConstVal;
import com.she.utils.FileUtil;
import com.she.utils.Methods;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.data.JRXmlDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

@Service
public class OperationService {

    @Autowired
    private OperationMapper operationMapper;

    @Autowired
    private MonPosMapper monPosMapper;

    @Autowired
    private PreventFacBreakHistMapper preventFacBreakHistMapper;

    @Autowired
    private GlobalSettings globalSettings;

    /**
     * 운영일지 조회
     *
     * @param yearMonth
     * @return 운영일지 목록
     * @throws Exception
     *             예외
     */
    public List<OpLogResult> getDailyReports(String fromDate, String toDate, String deptCd, String mgDeptCd, String plantCd, String ewtrCleanFacNo, String envOpLogStCd, DefaultParam defaultParam, String deptSubYn) throws Exception {
        List<OpLogResult> opLogResults = operationMapper.getDailyReports(fromDate, toDate, deptCd, mgDeptCd, plantCd, ewtrCleanFacNo, envOpLogStCd, defaultParam, deptSubYn);
        for (OpLogResult opLogResult : opLogResults) {
            if (opLogResult.getEnvOpLogStCd() == null || opLogResult.getEnvOpLogStCd().equals(ConstVal.SAF_PROCESS_STEP_CREATE)) {
                opLogResult.setMeasureEditable(true);
            }
        }
        return opLogResults;
    }

    public int createOperationLogChk(String measureYmd, String ewtrCleanFacNo, String plantCd, String deptCd) throws Exception {
        return operationMapper.createOperationLogChk(measureYmd, ewtrCleanFacNo, plantCd, deptCd);
    }

    /**
     * 방지시설 고장유무 및 특기사항
     *
     * @param measureYmd
     * @return 방지시설 고장유무 및 특기사항 목록
     * @throws Exception
     *             예외
     */
    public List<PreventFacBreakHist> getPreventBreakHists(String measureYmd, int ewtrCleanFacNo, String plantCd) throws Exception {
        return operationMapper.getPreventBreakHists(measureYmd, ewtrCleanFacNo, plantCd);
    }

    /**
     * 운영일지 삭제
     *
     * @param measureYmd
     * @param ewtrCleanFacNo
     * @return 운영일지 목록
     * @throws Exception
     *             예외
     */

    public int deleteOperationLog(String measureYmd, String ewtrCleanFacNo) throws Exception {

        return operationMapper.deleteOperationLog(measureYmd, ewtrCleanFacNo);
    }

    /**
     * 운영일지 상세조회
     *
     * @param measureYmd
     *            운영일지측정일
     * @return OpLogResult 운영일지
     * @throws Exception
     *             예외
     */
    public OpLogResult getDailyReport(String measureYmd, int ewtrCleanFacNo, String plantCd, String deptCd) throws Exception {
        OpLogResult opLogResult = operationMapper.getDailyReport(measureYmd, ewtrCleanFacNo, plantCd, deptCd);
        if (opLogResult == null) {
            opLogResult = new OpLogResult();
            opLogResult.setMeasureYmd(measureYmd);
            opLogResult.setEwtrCleanFacNo(ewtrCleanFacNo);
            opLogResult.setPlantCd(plantCd);
            opLogResult.setDeptCd(deptCd);
            opLogResult.setUpdateFlag(false);
        } else {
            opLogResult.setUpdateFlag(true);
        }
        return opLogResult;
    }

    /**
     * 수질 운영일지 신규등록
     *
     * @param opLogResult
     *            운영일지
     * @return 등록행수
     * @throws Exception
     */
    @Transactional
    @ExceptionHandler
    public int createOperationLog(OpLogResult opLogResult) throws Exception {
        opLogResult.setEnvOpLogStCd(ConstVal.SAF_PROCESS_STEP_CREATE);

        // 요일 구하기
        String date = opLogResult.getMeasureYmd();
        String day = "";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date nDate = dateFormat.parse(date);
        Calendar cal = Calendar.getInstance();
        cal.setTime(nDate);
        int dayNum = cal.get(Calendar.DAY_OF_WEEK);
        switch (dayNum) {
        case 1:
            day = "일요일";
            break;
        case 2:
            day = "월요일";
            break;
        case 3:
            day = "화요일";
            break;
        case 4:
            day = "수요일";
            break;
        case 5:
            day = "목요일";
            break;
        case 6:
            day = "금요일";
            break;
        case 7:
            day = "토요일";
            break;
        }
        opLogResult.setDay(day);

        int resultNum = this.operationMapper.createOperationLog(opLogResult);

        // 운영일지 일자의 오염방지시설 고장이력을 조회하여 해당되는 시간에 업데이트 함.
        if (resultNum > 0) {
            operationMapper.updateDownTimeYn(opLogResult.getMeasureYmd());

            operationMapper.createDischRunTms(opLogResult); // 배출시설 가동시간 초기데이터
                                                            // 등록

            for (OpLogResult list : operationMapper.getPreventTms(opLogResult.getPlantCd(), opLogResult.getEwtrCleanFacNo())) {
                list.setCreateUserId(opLogResult.getCreateUserId());
                list.setMeasureYmd(opLogResult.getMeasureYmd());
                operationMapper.createPreventRunTms(list); // 방지시설 가동시간 초기데이터 등록
            }

            operationMapper.createChems(opLogResult); // 약품 사용량 초기데이터 등록

            operationMapper.createTestItems(opLogResult); // 자가측정 결과 목록 초기데이터 등록

        }

        return resultNum;
    }

    /**
     * 운영일지 신규 및 수정
     *
     * @param OpLogResult
     *            운영일지
     * @return 수정행수
     * @throws Exception
     *             예외
     */
    @Transactional
    public int updateDailyReport(OpLogResult opLogResult) throws Exception {
        int result = operationMapper.updateDailyReport(opLogResult);

        return result;
    }

    /**
     * 운영일지 슬러지 수정
     *
     * @param OpLogResult
     *            운영일지 슬러지
     * @return 수정행수
     * @throws Exception
     *             예외
     */
    public int updateSludge(OpLogResult opLogResult) throws Exception {
        return operationMapper.updateSludge(opLogResult);
    }

    /**
     * 공급수 사용량 조회
     *
     * @param measureYmd
     * @return 공급수 사용량 목록
     * @throws Exception
     *             예외
     */
    public List<SuplChkResult> getSuplChkResults(String measureYmd, int ewtrCleanFacNo, String plantCd, String deptCd, DefaultParam defaultParam) throws Exception {
        return operationMapper.getSuplChkResults(measureYmd, ewtrCleanFacNo, plantCd, deptCd, defaultParam);
    }

    /**
     * 공급수 사용량 수정
     *
     * @param List<SuplChkResult>
     * @return 성공여부
     * @throws Exception
     *             예외
     */
    @Transactional
    public int updateSuplChkResult(List<SuplChkResult> suplChkResults) throws Exception {
        int returnNum = 0;

        for (SuplChkResult suplChkResult : suplChkResults) {
            // 공급수 등록
            returnNum += operationMapper.updateSuplChkResult(suplChkResult);
        }

        return returnNum;
    }

    /**
     * 배출수 사용량 조회
     *
     * @param measureYmd
     * @return 배출수 사용량 목록
     * @throws Exception
     *             예외
     */
    public List<DischChkResult> getDischChkResults(String measureYmd, int ewtrCleanFacNo, String deptCd, DefaultParam defaultParam) throws Exception {
        return operationMapper.getDischChkResults(measureYmd, ewtrCleanFacNo, deptCd, defaultParam);
    }

    /**
     * 배출수 사용량 수정
     *
     * @param DischChkResult
     *            운영일지
     * @return 수정행수
     * @throws Exception
     *             예외
     */
    @Transactional
    public int updateDischChkResult(List<DischChkResult> dischChkResults) throws Exception {
        int returnNum = 0;

        for (DischChkResult dischChkResult : dischChkResults) {
            // 배출수 등록
            returnNum += operationMapper.updateDischChkResult(dischChkResult);
        }

        return returnNum;
    }

    /**
     * 약품 사용량 조회
     *
     * @param measureYmd
     * @return 약품 사용량 목록
     * @throws Exception
     *             예외
     */
    public List<ChemChkResult> getChemChkResults(String measureYmd, int ewtrCleanFacNo, String deptCd, DefaultParam defaultParam) throws Exception {
        return operationMapper.getChemChkResults(measureYmd, ewtrCleanFacNo, deptCd, defaultParam);
    }

    /**
     * 약품 사용량 수정
     *
     * @param ChemChkResult
     *            운영일지
     * @return 수정행수
     * @throws Exception
     *             예외
     */
    @Transactional
    public int updateChemChkResult(List<ChemChkResult> chemChkResults) throws Exception {
        int returnNum = 0;

        for (ChemChkResult chemChkResult : chemChkResults) {
            // 약품 현재재고량 플러스
            // operationMapper.plusAmountCurr(chemChkResult);
            // 약품 현재재고량 마이너스
            // operationMapper.minusAmountCurr(chemChkResult);
            // 약품 등록
            returnNum += operationMapper.updateChemChkResult(chemChkResult);
        }

        return returnNum;
    }

    /**
     * 전력 사용량 조회
     *
     * @param measureYmd
     * @return 전력 사용량 목록
     * @throws Exception
     *             예외
     */
    public List<PwrcChkResult> getPwrcChkResults(String measureYmd, int ewtrCleanFacNo, String deptCd) throws Exception {
        return operationMapper.getPwrcChkResults(measureYmd, ewtrCleanFacNo, deptCd);
    }

    /**
     * 전력 사용량 수정
     *
     * @param List<PwrcChkResult>
     * @return 수정행수
     * @throws Exception
     *             예외
     */
    @Transactional
    public int updatePwrcChkResult(List<PwrcChkResult> pwrcChkResults) throws Exception {
        int returnNum = 0;

        for (PwrcChkResult pwrcChkResult : pwrcChkResults) {
            // 전력사용량 등록
            returnNum += operationMapper.updatePwrcChkResult(pwrcChkResult);

            // operationMapper.updatePwrcPerDay(pwrcChkResult.getMeasureYmd(),
            // pwrcChkResult.getEwtrPwrMeterNo());
        }

        return returnNum;
    }

    /**
     * 약품/첨가제 사용량 조회
     *
     * @param measureYmd
     * @return 약품/첨가제 사용량 목록
     * @throws Exception
     *             예외
     */
    public List<IngrChkResult> getIngrChkResults(@Param("measureYmd") String measureYmd, @Param("ewtrCleanFacNo") int ewtrCleanFacNo, @Param("deptCd") String deptCd) throws Exception {
        return operationMapper.getIngrChkResults(measureYmd, ewtrCleanFacNo, deptCd);
    }

    /**
     * 약품/첨가제 사용량 수정
     *
     * @param List<IngrChkResult>
     * @return 수정행수
     * @throws Exception
     *             예외
     */
    @Transactional
    public int updateIngrChkResult(List<IngrChkResult> ingrChkResults) throws Exception {
        int returnNum = 0;

        for (IngrChkResult ingrChkResult : ingrChkResults) {
            // 약품/첨가제 등록
            returnNum += operationMapper.updateIngrChkResult(ingrChkResult);
        }

        return returnNum;
    }

    /**
     * 자가측정 검사결과 신규 항목 조회
     *
     * @param measureYmd
     * @return 자가측정 검사결과 목록
     * @throws Exception
     *             예외
     */
    public List<HashMap<String, Object>> getTestItemResultsNew(String measureYmd, int ewtrCleanFacNo, String plantCd, List<TestItemResult> testItemResult) throws Exception {
        String testItemResultStr = String.join(", ", testItemResult.stream().map(item -> item.getInputTypeCd() + "_" + item.getEwtrTestItemCd()).collect(Collectors.toList()));

        return operationMapper.getTestItemResultsNew(measureYmd, ewtrCleanFacNo, plantCd, testItemResult, testItemResultStr);
    }

    /**
     * 자가측정 검사결과 신규 항목 조회
     *
     * @param measureYmd
     * @return 자가측정 검사결과 목록
     * @throws Exception
     *             예외
     */
    public List<TestItemResult> getTestItemResultsNewItems(String measureYmd, int ewtrCleanFacNo, String plantCd) throws Exception {
        return operationMapper.getTestItemResultsNewItems(measureYmd, ewtrCleanFacNo, plantCd);
    }

    /**
     * 자가측정 검사결과 조회
     *
     * @param measureYmd
     * @return 자가측정 검사결과 목록
     * @throws Exception
     *             예외
     */
    public List<HashMap<String, Object>> getTestItemResults(String measureYmd, int ewtrCleanFacNo, String deptCd, String plantCd) throws Exception {
        List<TestItemResult> testItemResults = operationMapper.getTestItemResultsNewItems(measureYmd, ewtrCleanFacNo, plantCd);

        String testItemResultStr = String.join(", ", testItemResults.stream().map(item -> item.getInputTypeCd() + "_" + item.getEwtrTestItemCd()).collect(Collectors.toList()));

        return operationMapper.getTestItemResults(measureYmd, ewtrCleanFacNo, deptCd, testItemResults, testItemResultStr);
    }

    /**
     * 자가측정 검사결과 수정
     *
     * @param List<TestItemResult>
     * @return 수정행수
     * @throws Exception
     *             예외
     */
    @Transactional
    public int updateTestItemResult(List<HashMap<String, Object>> testItemResults) throws Exception {

        int returnNum = 0;
        String regExp = "(NUM01|TXT01)([0-9].+)";
        for (HashMap<String, Object> itemMap : testItemResults) {
            for (String key : itemMap.keySet()) {
                if (Pattern.compile(regExp).matcher(key).matches()) {
                    String itemCd = key.replaceAll(regExp, "$2");
                    String typeCd = key.replaceAll(regExp, "$1");
                    if (itemCd != null) {
                        TestItemResult testItemResult = new TestItemResult();
                        BeanUtils.populate(testItemResult, itemMap);
                        testItemResult.setItemCd(itemCd);

                        if (typeCd.equals("TXT01")) {
                            testItemResult.setNumResult(0.0f);
                            testItemResult.setCharResult(String.valueOf(itemMap.get(key)));
                        } else {
                            testItemResult.setNumResult(Float.parseFloat(String.valueOf(itemMap.get(key))));
                            testItemResult.setCharResult("");
                        }

                        returnNum += operationMapper.updateTestItemResult(testItemResult);

                    }
                }
            }
        }
        return returnNum;
    }

    /**
     * 배출시설 가동시간 조회
     *
     * @param measureYmd
     * @return 배출시설 가동시간 목록
     * @throws Exception
     *             예외
     */
    public List<WasteDischFacRunTm> getDischRunTms(String measureYmd, int ewtrCleanFacNo, String deptCd, DefaultParam defaultParam) throws Exception {
        return operationMapper.getDischRunTms(measureYmd, ewtrCleanFacNo, deptCd, defaultParam);
    }

    /**
     * 배출시설 가동시간 수정
     *
     * @param List<WasteDischFacRunTm>
     * @return 수정행수
     * @throws Exception
     *             예외
     */
    @Transactional
    public int updateDischRunTm(List<WasteDischFacRunTm> wasteDischFacRunTms) throws Exception {
        int returnNum = 0;

        for (WasteDischFacRunTm wasteDischFacRunTm : wasteDischFacRunTms) {
            // 배출시설 가동시간 등록
            returnNum += operationMapper.updateDischRunTm(wasteDischFacRunTm);
        }

        return returnNum;
    }

    /**
     * 방지시설 가동시간 조회
     *
     * @param measureYmd
     * @return 방지시설 가동시간 목록
     * @throws Exception
     *             예외
     */
    public List<PreventFacRunTm> getPreventRunTms(String measureYmd, int ewtrCleanFacNo, String deptCd) throws Exception {
        return operationMapper.getPreventRunTms(measureYmd, ewtrCleanFacNo, deptCd);
    }

    /**
     * 방지시설 가동시간 수정
     *
     * @param List<PreventFacRunTm>
     * @return 수정행수
     * @throws Exception
     *             예외
     */
    @Transactional
    public int updatePreventRunTm(List<PreventFacRunTm> preventFacRunTms) throws Exception {
        int returnNum = 0;

        for (PreventFacRunTm preventFacRunTm : preventFacRunTms) {
            // 방지시설 가동시간 등록
            returnNum += operationMapper.updatePreventRunTm(preventFacRunTm);
        }

        return returnNum;
    }

    public List<TestItemResult> getTestItemResultStatusNewItems(String plantCd) throws Exception {
        return this.operationMapper.getTestItemResultStatusNewItems(plantCd);
    }

    /**
     * 수질 운영현황 조회
     *
     * @param parameter
     *            검색조건
     * @return 수질 운영현황
     * @throws Exception
     */
    public List<HashMap<String, Object>> getOperationStatus(String searchPeriod, String plantCd, String totalTypeCd, String ewtrCleanFacNo, String search, String deptCd, DefaultParam defaultParam) throws Exception {
        List<String> ymCols = new ArrayList<String>();

        String fromYmd = searchPeriod + "-01-01";
        String toYmd = searchPeriod + "-12-31";

        Calendar fromDate = Calendar.getInstance();
        Calendar toDate = Calendar.getInstance();

        // 2020-01 ~ 2020-04로 떠서 오류
        fromDate.setTime(Methods.convertStringToDate(fromYmd));
        toDate.setTime(Methods.convertStringToDate(toYmd));
        for (; fromDate.compareTo(toDate) < 1; fromDate.add(Calendar.MONTH, 1)) {
            String year = fromDate.get(Calendar.YEAR) + "";
            String month = Methods.leftPad(String.valueOf(fromDate.get(Calendar.MONTH) + 1), 2, '0');
            ymCols.add(year + month);
        }
        // toYmd 해당월의 마지막일로 설정
        toYmd = toYmd.substring(0, 8) + Methods.leftPad(String.valueOf(toDate.getActualMaximum(Calendar.DAY_OF_MONTH)), 2, '0');

        List<TestItemResult> testItemResults = this.operationMapper.getTestItemResultStatusNewItems(plantCd);

        String ymColStr = String.join(", ", ymCols);
        String testItemStr = String.join(", ", testItemResults.stream().map(item -> item.getEwtrTestItemCd()).collect(Collectors.toList()));

        return this.operationMapper.getOperationStatus(fromYmd, toYmd, ymCols, plantCd, totalTypeCd, ewtrCleanFacNo, search, this.operationMapper.getTestItemResultStatusNewItems(plantCd), deptCd, ymColStr, testItemStr, defaultParam);

    }

    public int updateAppr(String measureYmd, int ewtrCleanFacNo, String apprStepCd, int apprRqstNo, String deptCd) throws Exception {
        int resultNo = 0;
        String stepCd = "";
        if (ConstVal.COM_BIZ_APPR_STEP_REJECT.equals(apprStepCd)) {
            // 반려

        } else if (ConstVal.COM_BIZ_APPR_STEP_COMPLETE.equals(apprStepCd)) {
            // 결재완료
            OpLogResult oplogResult = this.operationMapper.getDailyReport(measureYmd, ewtrCleanFacNo, null, deptCd);

            if (oplogResult.getEnvOpLogStCd().equals(ConstVal.SAF_PROCESS_STEP_CREATE)) {
                stepCd = ConstVal.SAF_PROCESS_STEP_COMPLTE;
            }
        }
        resultNo += this.operationMapper.updateAppr(measureYmd, ewtrCleanFacNo, stepCd, apprRqstNo, deptCd);

        return resultNo;
    }

    /**
     * 수질운영일지 출력
     *
     * @param ewtrCleanFacNo
     *            클린센터번호
     * @param measureYmd
     *            대기운영일자
     * @return 수질운영일지 파일
     * @throws Exception
     */
    public File getPrintOplog(int ewtrCleanFacNo, String measureYmd, String plantCd, String deptCd, DefaultParam defaultParam) throws Exception {
        // 수질운영일지 기본정보
        OpLogResult opLogResult = this.getDailyReport(measureYmd, ewtrCleanFacNo, plantCd, deptCd);
        String[] ymdArr = measureYmd.split("-");
        String year = ymdArr[0];
        String month = ymdArr[1];
        String date = ymdArr[2];

        // 슬러지처리시설,지도점검사항 -- 기본정보에 있음

        // 배출시설 가동(조업) 시간 (데이터없을경우 공데이터 1개 기입)
        List<WasteDischFacRunTm> wasteDischFacRunTms = operationMapper.getDischRunTms(measureYmd, ewtrCleanFacNo, deptCd, defaultParam);
        if (wasteDischFacRunTms.size() < 5) {
            int addCount = 5 - wasteDischFacRunTms.size();
            for (int i = 0; i < addCount; i++) {
                WasteDischFacRunTm wasteDischFacRunTm = new WasteDischFacRunTm();
                wasteDischFacRunTm.setEwtrWasteDischFacNm(" ");
                wasteDischFacRunTm.setH00Yn(" ");
                wasteDischFacRunTm.setH01Yn(" ");
                wasteDischFacRunTm.setH02Yn(" ");
                wasteDischFacRunTm.setH03Yn(" ");
                wasteDischFacRunTm.setH04Yn(" ");
                wasteDischFacRunTm.setH05Yn(" ");
                wasteDischFacRunTm.setH06Yn(" ");
                wasteDischFacRunTm.setH07Yn(" ");
                wasteDischFacRunTm.setH08Yn(" ");
                wasteDischFacRunTm.setH09Yn(" ");
                wasteDischFacRunTm.setH10Yn(" ");
                wasteDischFacRunTm.setH11Yn(" ");
                wasteDischFacRunTm.setH12Yn(" ");
                wasteDischFacRunTm.setH13Yn(" ");
                wasteDischFacRunTm.setH14Yn(" ");
                wasteDischFacRunTm.setH15Yn(" ");
                wasteDischFacRunTm.setH16Yn(" ");
                wasteDischFacRunTm.setH17Yn(" ");
                wasteDischFacRunTm.setH18Yn(" ");
                wasteDischFacRunTm.setH19Yn(" ");
                wasteDischFacRunTm.setH20Yn(" ");
                wasteDischFacRunTm.setH21Yn(" ");
                wasteDischFacRunTm.setH22Yn(" ");
                wasteDischFacRunTm.setH23Yn(" ");
                wasteDischFacRunTms.add(wasteDischFacRunTm);
            }
        }
        // 방지시설 가동(조업) 시간 (데이터없을경우 공데이터 1개 기입)
        List<PreventFacRunTm> preventFacRunTms = operationMapper.getPreventRunTms(measureYmd, ewtrCleanFacNo, deptCd);
        if (preventFacRunTms.size() < 5) {
            int addCount = 5 - preventFacRunTms.size();
            for (int i = 0; i < addCount; i++) {
                PreventFacRunTm preventFacRunTm = new PreventFacRunTm();
                preventFacRunTm.setEwtrPreventFacNm(" ");
                preventFacRunTm.setH00Yn(" ");
                preventFacRunTm.setH01Yn(" ");
                preventFacRunTm.setH02Yn(" ");
                preventFacRunTm.setH03Yn(" ");
                preventFacRunTm.setH04Yn(" ");
                preventFacRunTm.setH05Yn(" ");
                preventFacRunTm.setH06Yn(" ");
                preventFacRunTm.setH07Yn(" ");
                preventFacRunTm.setH08Yn(" ");
                preventFacRunTm.setH09Yn(" ");
                preventFacRunTm.setH10Yn(" ");
                preventFacRunTm.setH11Yn(" ");
                preventFacRunTm.setH12Yn(" ");
                preventFacRunTm.setH13Yn(" ");
                preventFacRunTm.setH14Yn(" ");
                preventFacRunTm.setH15Yn(" ");
                preventFacRunTm.setH16Yn(" ");
                preventFacRunTm.setH17Yn(" ");
                preventFacRunTm.setH18Yn(" ");
                preventFacRunTm.setH19Yn(" ");
                preventFacRunTm.setH20Yn(" ");
                preventFacRunTm.setH21Yn(" ");
                preventFacRunTm.setH22Yn(" ");
                preventFacRunTm.setH23Yn(" ");
                preventFacRunTms.add(preventFacRunTm);
            }
        }
        // 용수 공급원별 사용량
        List<SuplChkResult> suplChkResults = operationMapper.getSuplChkResults(measureYmd, ewtrCleanFacNo, plantCd, deptCd, defaultParam);
        // 용수 공급원별 폐수배출량
        List<DischChkResult> dischChkResults = operationMapper.getDischChkResults(measureYmd, ewtrCleanFacNo, deptCd, defaultParam);

        // 공급원별사용량,폐수배출량 각각 10개 이하일때 공데이터 기입 처리
        // 10개이상이면 갯수 맞춰서 공데이터 기입 처리
        int suplCount = suplChkResults.size();
        int dischCount = dischChkResults.size();
        if (suplCount < 14 && dischCount < 14) {
            int suplAdd = 14 - suplCount;
            int dischAdd = 14 - dischCount;
            // 사용량 공데이터기입
            for (int i = 0; i < suplAdd; i++) {
                SuplChkResult suplChkResult = new SuplChkResult();
                suplChkResult.setEwtrSuplClassNm("-");
                suplChkResult.setEwtrSuplNm("-");
                suplChkResult.setMeterCntY(0.0f);
                suplChkResult.setMeterCntT(0.0f);
                suplChkResult.setConsumAmt(0.0f);
                suplChkResult.setChkTime("0");
                suplChkResults.add(suplChkResult);
            }
            // 폐수배출량 공데이터기입
            for (int i = 0; i < dischAdd; i++) {
                DischChkResult dischChkResult = new DischChkResult();
                dischChkResult.setEwtrClassNm("");
                dischChkResult.setEwtrDischNm("");
                dischChkResult.setMeterCntY(null);
                dischChkResult.setMeterCntT(null);
                dischChkResult.setConsumAmt(null);
                dischChkResults.add(dischChkResult);
            }
        } else if (suplCount < dischCount) {
            int suplAdd = dischCount - suplCount;
            // 사용량 공데이터기입
            for (int i = 0; i < suplAdd; i++) {
                SuplChkResult suplChkResult = new SuplChkResult();
                suplChkResult.setEwtrSuplClassNm("-");
                suplChkResult.setEwtrSuplNm("-");
                suplChkResult.setMeterCntY(0.0f);
                suplChkResult.setMeterCntT(0.0f);
                suplChkResult.setConsumAmt(0.0f);
                suplChkResult.setChkTime("0");
                suplChkResults.add(suplChkResult);
            }
        } else if (suplCount > dischCount) {
            int dischAdd = suplCount - dischCount;
            // 폐수배출량 공데이터기입
            for (int i = 0; i < dischAdd; i++) {
                DischChkResult dischChkResult = new DischChkResult();
                dischChkResult.setEwtrClassNm("");
                dischChkResult.setEwtrDischNm("");
                dischChkResult.setMeterCntY(null);
                dischChkResult.setMeterCntT(null);
                dischChkResult.setConsumAmt(null);
                dischChkResults.add(dischChkResult);
            }
        }
        // 원료,첨가제등 약품량
        List<IngrChkResult> ingrChkResults = operationMapper.getIngrChkResults(measureYmd, ewtrCleanFacNo, deptCd);
        if (ingrChkResults.size() < 5) {
            int abs = 5 - ingrChkResults.size();
            for (int i = 0; i < abs; i++) {
                IngrChkResult emptIngrChkResult = new IngrChkResult();
                emptIngrChkResult.setEwtrIngrNm("");
                emptIngrChkResult.setConsumAmt(null);
                ingrChkResults.add(emptIngrChkResult);
            }
        } else {
            List<IngrChkResult> newIngrChkResults = new ArrayList<IngrChkResult>();
            // 5개만 기입
            for (int i = 0; i < 5; i++) {
                IngrChkResult chkResult = ingrChkResults.get(i);
                newIngrChkResults.add(chkResult);
            }
            ingrChkResults = newIngrChkResults;
        }
        // 전력사용량 조회
        List<PwrcChkResult> pwrcChkResults = operationMapper.getPwrcChkResults(measureYmd, ewtrCleanFacNo, deptCd);
        if (pwrcChkResults.size() == 0) {
            PwrcChkResult pwrcChkResult = new PwrcChkResult();
            pwrcChkResult.setEwtrPwrMeterNm(" ");
            pwrcChkResult.setRunTm(0.0f);
            pwrcChkResult.setPwrConsumAmt(0.0f);
            pwrcChkResult.setPwrcPerDay(0.0f);
            pwrcChkResult.setChkTime(" ");
            pwrcChkResult.setPwrMeterCntT(0.0f);
            pwrcChkResult.setRemark(" ");
            pwrcChkResults.add(pwrcChkResult);
        }
        // 약품사용량 조회
        List<ChemChkResult> chemChkResults = operationMapper.getChemChkResults(measureYmd, ewtrCleanFacNo, deptCd, defaultParam);
        if (chemChkResults.size() == 0) {
            ChemChkResult chemChkResult = new ChemChkResult();
            chemChkResult.setEwtrDischGrpNm(" ");
            chemChkResult.setEwtrChemNm(" ");
            chemChkResult.setBuyAmt(0.0f);
            chemChkResult.setConsumAmt(0.0f);
            chemChkResult.setAmountCurr(0.0f);
            chemChkResult.setRemarks(" ");
            chemChkResults.add(chemChkResult);
        }
        // 방지시설 고장유무 및 특이사항
        List<PreventFacBreakHist> preventFacBreakHists = operationMapper.getPreventBreakHists(measureYmd, ewtrCleanFacNo, opLogResult.getPlantCd());
        if (preventFacBreakHists.size() == 0) {
            PreventFacBreakHist preventFacBreakHist = new PreventFacBreakHist();
            preventFacBreakHist.setEwtrPreventFacNm(" ");
            preventFacBreakHist.setMeasureYmd(" ");
            preventFacBreakHist.setDownCond(" ");
            preventFacBreakHist.setAction(" ");
            preventFacBreakHist.setRemark(" ");
            preventFacBreakHists.add(preventFacBreakHist);
        }
        // 오염물질 측정 내용
        List<TestItemResult> results = this.getTestItemResultsNewItems(measureYmd, ewtrCleanFacNo, opLogResult.getPlantCd());
        if (results.size() < 30) {
            int ads = 30 - results.size();
            for (int i = 0; i < ads; i++) {
                TestItemResult emptyResult = new TestItemResult();
                emptyResult.setItemCd("");
                emptyResult.setEwtrTestItemCd("");
                results.add(emptyResult);
            }
        }

        String testItemResultStr = String.join(",", results.stream().map(item -> item.getInputTypeCd() + "_" + item.getEwtrTestItemCd()).collect(Collectors.toList()));

        List<HashMap<String, Object>> testItemResults = operationMapper.getTestItemResults(measureYmd, ewtrCleanFacNo, deptCd, results, testItemResultStr);
        List<TestItemResultPrint> testItemResultValues = new ArrayList<>();
        if (testItemResults.size() == 0) {
            TestItemResultPrint testItemResultPrint = new TestItemResultPrint();
            testItemResultPrint.setEwtrMonPosNm(" ");
            testItemResultPrint.setNumResult1(" ");
            testItemResultPrint.setNumResult2(" ");
            testItemResultPrint.setNumResult3(" ");
            testItemResultPrint.setNumResult4(" ");
            testItemResultPrint.setNumResult5(" ");
            testItemResultPrint.setNumResult6(" ");
            testItemResultPrint.setNumResult7(" ");
            testItemResultPrint.setNumResult8(" ");
            testItemResultPrint.setNumResult9(" ");
            testItemResultPrint.setNumResult10(" ");
            testItemResultPrint.setNumResult11(" ");
            testItemResultPrint.setNumResult12(" ");
            testItemResultPrint.setNumResult13(" ");
            testItemResultPrint.setNumResult14(" ");
            testItemResultPrint.setNumResult15(" ");
            testItemResultPrint.setNumResult16(" ");
            testItemResultPrint.setNumResult17(" ");
            testItemResultPrint.setNumResult18(" ");
            testItemResultPrint.setNumResult19(" ");
            testItemResultPrint.setNumResult20(" ");
            testItemResultPrint.setNumResult21(" ");
            testItemResultPrint.setNumResult22(" ");
            testItemResultPrint.setNumResult23(" ");
            testItemResultPrint.setNumResult24(" ");
            testItemResultPrint.setNumResult25(" ");
            testItemResultPrint.setNumResult26(" ");
            testItemResultPrint.setNumResult27(" ");
            testItemResultPrint.setNumResult28(" ");
            testItemResultPrint.setNumResult29(" ");

            testItemResultValues.add(testItemResultPrint);
        }
        for (int i = 0; i < testItemResults.size(); i++) {
            Map<String, Object> testItemResultItem = new HashMap<>();
            testItemResultItem = testItemResults.get(i);
            TestItemResultPrint resultPrint = new TestItemResultPrint();
            TestItemResult result1 = results.get(0);
            TestItemResult result2 = results.get(1);
            TestItemResult result3 = results.get(2);
            TestItemResult result4 = results.get(3);
            TestItemResult result5 = results.get(4);
            TestItemResult result6 = results.get(5);
            TestItemResult result7 = results.get(6);
            TestItemResult result8 = results.get(7);
            TestItemResult result9 = results.get(8);
            TestItemResult result10 = results.get(9);
            TestItemResult result11 = results.get(10);
            TestItemResult result12 = results.get(11);
            TestItemResult result13 = results.get(12);
            TestItemResult result14 = results.get(13);
            TestItemResult result15 = results.get(14);
            TestItemResult result16 = results.get(15);
            TestItemResult result17 = results.get(16);
            TestItemResult result18 = results.get(17);
            TestItemResult result19 = results.get(18);
            TestItemResult result20 = results.get(19);
            TestItemResult result21 = results.get(20);
            TestItemResult result22 = results.get(21);
            TestItemResult result23 = results.get(22);
            TestItemResult result24 = results.get(23);

            TestItemResult result25 = results.get(24);
            TestItemResult result26 = results.get(25);
            TestItemResult result27 = results.get(26);
            TestItemResult result28 = results.get(27);
            TestItemResult result29 = results.get(28);
            TestItemResult result30 = results.get(29);

            String key1 = result1.getInputTypeCd() + result1.getEwtrTestItemCd();
            String key2 = result2.getInputTypeCd() + result2.getEwtrTestItemCd();
            String key3 = result3.getInputTypeCd() + result3.getEwtrTestItemCd();
            String key4 = result4.getInputTypeCd() + result4.getEwtrTestItemCd();
            String key5 = result5.getInputTypeCd() + result5.getEwtrTestItemCd();
            String key6 = result6.getInputTypeCd() + result6.getEwtrTestItemCd();
            String key7 = result7.getInputTypeCd() + result7.getEwtrTestItemCd();
            String key8 = result8.getInputTypeCd() + result8.getEwtrTestItemCd();
            String key9 = result9.getInputTypeCd() + result9.getEwtrTestItemCd();
            String key10 = result10.getInputTypeCd() + result10.getEwtrTestItemCd();
            String key11 = result11.getInputTypeCd() + result11.getEwtrTestItemCd();
            String key12 = result12.getInputTypeCd() + result12.getEwtrTestItemCd();
            String key13 = result13.getInputTypeCd() + result13.getEwtrTestItemCd();
            String key14 = result14.getInputTypeCd() + result14.getEwtrTestItemCd();
            String key15 = result15.getInputTypeCd() + result15.getEwtrTestItemCd();
            String key16 = result16.getInputTypeCd() + result16.getEwtrTestItemCd();
            String key17 = result17.getInputTypeCd() + result17.getEwtrTestItemCd();
            String key18 = result18.getInputTypeCd() + result18.getEwtrTestItemCd();
            String key19 = result19.getInputTypeCd() + result19.getEwtrTestItemCd();
            String key20 = result20.getInputTypeCd() + result20.getEwtrTestItemCd();
            String key21 = result21.getInputTypeCd() + result21.getEwtrTestItemCd();
            String key22 = result22.getInputTypeCd() + result22.getEwtrTestItemCd();
            String key23 = result23.getInputTypeCd() + result23.getEwtrTestItemCd();
            String key24 = result24.getInputTypeCd() + result24.getEwtrTestItemCd();

            String key25 = result25.getInputTypeCd() + result25.getEwtrTestItemCd();
            String key26 = result26.getInputTypeCd() + result26.getEwtrTestItemCd();
            String key27 = result27.getInputTypeCd() + result27.getEwtrTestItemCd();
            String key28 = result28.getInputTypeCd() + result28.getEwtrTestItemCd();
            String key29 = result28.getInputTypeCd() + result29.getEwtrTestItemCd();
            String key30 = result28.getInputTypeCd() + result30.getEwtrTestItemCd();

            String valueNm = testItemResultItem.get("ewtrMonPosNm") != null ? testItemResultItem.get("ewtrMonPosNm").toString() : "";
            String value1 = key1.equals("null") ? null : (testItemResultItem.get(key1).toString());
            String value2 = key2.equals("null") ? null : (testItemResultItem.get(key2).toString());
            String value3 = key3.equals("null") ? null : (testItemResultItem.get(key3).toString());
            String value4 = key4.equals("null") ? null : (testItemResultItem.get(key4).toString());
            String value5 = key5.equals("null") ? null : (testItemResultItem.get(key5).toString());
            String value6 = key6.equals("null") ? null : (testItemResultItem.get(key6).toString());
            String value7 = key7.equals("null") ? null : (testItemResultItem.get(key7).toString());
            String value8 = key8.equals("null") ? null : (testItemResultItem.get(key8).toString());
            String value9 = key9.equals("null") ? null : (testItemResultItem.get(key9).toString());
            String value10 = key10.equals("null") ? null : (testItemResultItem.get(key10).toString());
            String value11 = key11.equals("null") ? null : (testItemResultItem.get(key11).toString());
            String value12 = key12.equals("null") ? null : (testItemResultItem.get(key12).toString());
            String value13 = key13.equals("null") ? null : (testItemResultItem.get(key13).toString());
            String value14 = key14.equals("null") ? null : (testItemResultItem.get(key14).toString());
            String value15 = key15.equals("null") ? null : (testItemResultItem.get(key15).toString());
            String value16 = key16.equals("null") ? null : (testItemResultItem.get(key16).toString());
            String value17 = key17.equals("null") ? null : (testItemResultItem.get(key17).toString());
            String value18 = key18.equals("null") ? null : (testItemResultItem.get(key18).toString());
            String value19 = key19.equals("null") ? null : (testItemResultItem.get(key19).toString());
            String value20 = key20.equals("null") ? null : (testItemResultItem.get(key20).toString());
            String value21 = key21.equals("null") ? null : (testItemResultItem.get(key21).toString());
            String value22 = key22.equals("null") ? null : (testItemResultItem.get(key22).toString());
            String value23 = key23.equals("null") ? null : (testItemResultItem.get(key23).toString());
            String value24 = key24.equals("null") ? null : (testItemResultItem.get(key24).toString());

            String value25 = key25.equals("null") ? null : (testItemResultItem.get(key25).toString());
            String value26 = key26.equals("null") ? null : (testItemResultItem.get(key26).toString());
            String value27 = key27.equals("null") ? null : (testItemResultItem.get(key27).toString());
            String value28 = key28.equals("null") ? null : (testItemResultItem.get(key28).toString());
            String value29 = key29.equals("null") ? null : (testItemResultItem.get(key29).toString());
            String value30 = key30.equals("null") ? null : (testItemResultItem.get(key30).toString());

            resultPrint.setEwtrMonPosNm(valueNm);
            resultPrint.setNumResult1(value1 != null ? value1 : "");
            resultPrint.setNumResult2(value2 != null ? value2 : "");
            resultPrint.setNumResult3(value3 != null ? value3 : "");
            resultPrint.setNumResult4(value4 != null ? value4 : "");
            resultPrint.setNumResult5(value5 != null ? value5 : "");
            resultPrint.setNumResult6(value6 != null ? value6 : "");
            resultPrint.setNumResult7(value7 != null ? value7 : "");
            resultPrint.setNumResult8(value8 != null ? value8 : "");
            resultPrint.setNumResult9(value9 != null ? value9 : "");
            resultPrint.setNumResult10(value10 != null ? value10 : "");
            resultPrint.setNumResult11(value11 != null ? value11 : "");
            resultPrint.setNumResult12(value12 != null ? value12 : "");
            resultPrint.setNumResult13(value13 != null ? value13 : "");
            resultPrint.setNumResult14(value14 != null ? value14 : "");
            resultPrint.setNumResult15(value15 != null ? value15 : "");
            resultPrint.setNumResult16(value16 != null ? value16 : "");
            resultPrint.setNumResult17(value17 != null ? value17 : "");
            resultPrint.setNumResult18(value18 != null ? value18 : "");
            resultPrint.setNumResult19(value19 != null ? value19 : "");
            resultPrint.setNumResult20(value20 != null ? value20 : "");
            resultPrint.setNumResult21(value21 != null ? value21 : "");
            resultPrint.setNumResult22(value22 != null ? value22 : "");
            resultPrint.setNumResult23(value23 != null ? value23 : "");
            resultPrint.setNumResult24(value24 != null ? value24 : "");

            resultPrint.setNumResult25(value25 != null ? value25 : "");
            resultPrint.setNumResult26(value26 != null ? value26 : "");
            resultPrint.setNumResult27(value27 != null ? value27 : "");
            resultPrint.setNumResult28(value28 != null ? value28 : "");
            resultPrint.setNumResult29(value29 != null ? value29 : "");
            resultPrint.setNumResult30(value30 != null ? value30 : "");

            testItemResultValues.add(resultPrint);
        }
        // 용수 공급원별 사용량,폐수배출량 data to xml
        String suplDataPath = FileUtil.getStoreFilePath() + File.separator + "suplData_" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".xml";
        String dischDataPath = FileUtil.getStoreFilePath() + File.separator + "dischData_" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".xml";
        String chemChkDataPath = FileUtil.getStoreFilePath() + File.separator + "chemChkData_" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".xml";

        // 중복값 제거(중복 Class명 제거)
        List<String> ewtrSuplClassNms = new ArrayList<>();
        for (int i = 0; i < suplChkResults.size(); i++) {
            ewtrSuplClassNms.add(suplChkResults.get(i).getEwtrSuplClassNm());
        }

        List<String> ewtrSuplClassNmsUnique = ewtrSuplClassNms.parallelStream().distinct().collect(Collectors.toList());

        List<String> ewtrDischClassNms = new ArrayList<>();
        for (int i = 0; i < dischChkResults.size(); i++) {
            ewtrDischClassNms.add(dischChkResults.get(i).getEwtrClassNm());
        }

        List<String> ewtrDischClassNmsUnique = ewtrDischClassNms.parallelStream().distinct().collect(Collectors.toList());

        String getComsumAmt = dischChkResults.get(0).getConsumAmtt() != null ? dischChkResults.get(0).getConsumAmtt() : "0";
        ewtrDischClassNmsUnique.remove(0);

        List<String> ewtrChemGrpClassNms = new ArrayList<>();
        for (int i = 0; i < chemChkResults.size(); i++) {
            ewtrChemGrpClassNms.add(chemChkResults.get(i).getEwtrDischGrpNm());
        }

        List<String> ewtrChemGrpClassNmsUnique = ewtrChemGrpClassNms.parallelStream().distinct().collect(Collectors.toList());

        Float meterCntYSumSupl = 0.0f;
        Float meterCntTSumSupl = 0.0f;
        Float consumAmtSupl = 0.0f;
        Float meterCntYSumDisch = 0.0f;
        Float meterCntTSumDisch = 0.0f;
        Float consumAmtDisch = 0.0f;

        // data setting
        DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
        Document docSupl = documentBuilder.newDocument();
        Document docDisch = documentBuilder.newDocument();
        Document docChem = documentBuilder.newDocument();
        // root element
        Element rootSupl = docSupl.createElement("report");
        Element rootDisch = docDisch.createElement("report");
        Element rootChem = docChem.createElement("report");
        docSupl.appendChild(rootSupl);
        docDisch.appendChild(rootDisch);
        docChem.appendChild(rootChem);
        // 공급수,배출수 표 높이 맞추기 위함
        for (int i = 0; i < ewtrSuplClassNmsUnique.size(); i++) {
            String ewtrClassNm = ewtrSuplClassNmsUnique.get(i);
            Element suplResult = docSupl.createElement("ewtr3");

            Element ewtrSuplClassNm = docSupl.createElement("ewtrSuplClassNm");
            Element ewtrSuplData = docSupl.createElement("ewtrData");
            ewtrSuplClassNm.appendChild(docSupl.createTextNode(ewtrClassNm != null ? ewtrClassNm : ""));
            for (int j = 0; j < suplChkResults.size(); j++) {

                SuplChkResult suplChkResult = suplChkResults.get(j);
                if (ewtrClassNm.equals(suplChkResult.getEwtrSuplClassNm())) {
                    Element ewtrSuplDetail = docSupl.createElement("ewtrDetail");
                    Element ewtrSuplNm = docSupl.createElement("ewtrSuplNm");
                    Element meterCntY = docSupl.createElement("meterCntY");
                    Element meterCntT = docSupl.createElement("meterCntT");
                    Element consumAmt = docSupl.createElement("consumAmt");
                    Element chkTime = docSupl.createElement("chkTime");
                    ewtrSuplNm.appendChild(docSupl.createTextNode(suplChkResult.getEwtrSuplNm() != null ? suplChkResult.getEwtrSuplNm() : " "));
                    meterCntY.appendChild(docSupl.createTextNode(suplChkResult.getMeterCntY() != null ? String.valueOf(NumberFormat.getInstance().format(suplChkResult.getMeterCntY())) : "0"));
                    meterCntT.appendChild(docSupl.createTextNode(suplChkResult.getMeterCntT() != null ? String.valueOf(NumberFormat.getInstance().format(suplChkResult.getMeterCntT())) : "0"));
                    consumAmt.appendChild(docSupl.createTextNode(suplChkResult.getConsumAmt() != null ? String.valueOf(NumberFormat.getInstance().format(suplChkResult.getConsumAmt())) : "0"));
                    chkTime.appendChild(docSupl.createTextNode(suplChkResult.getChkTime() != null ? suplChkResult.getChkTime() : " "));

                    ewtrSuplDetail.appendChild(ewtrSuplNm);
                    ewtrSuplDetail.appendChild(meterCntY);
                    ewtrSuplDetail.appendChild(meterCntT);
                    ewtrSuplDetail.appendChild(consumAmt);
                    ewtrSuplDetail.appendChild(chkTime);
                    ewtrSuplData.appendChild(ewtrSuplDetail);
                }
                suplResult.appendChild(ewtrSuplClassNm);
                suplResult.appendChild(ewtrSuplData);
                meterCntYSumSupl += (suplChkResult.getMeterCntY() != null ? suplChkResult.getMeterCntY() : 0.0f);
                meterCntTSumSupl += (suplChkResult.getMeterCntT() != null ? suplChkResult.getMeterCntT() : 0.0f);
                consumAmtSupl += (suplChkResult.getConsumAmt() != null ? suplChkResult.getConsumAmt() : 0.0f);
            }
            rootSupl.appendChild(suplResult);
        }

        for (int i = 0; i < ewtrDischClassNmsUnique.size(); i++) {
            String ewtrClassNm = ewtrDischClassNmsUnique.get(i);
            Element dischResult = docDisch.createElement("ewtr4");

            Element ewtrDischClassNm = docDisch.createElement("ewtrDischClassNm");
            Element ewtrDischData = docDisch.createElement("ewtrData");
            ewtrDischClassNm.appendChild(docDisch.createTextNode(ewtrClassNm != null ? ewtrClassNm : ""));
            for (int j = 0; j < dischChkResults.size(); j++) {
                DischChkResult dischChkResult = dischChkResults.get(j);
                if (ewtrClassNm.equals(dischChkResult.getEwtrClassNm())) {
                    Element ewtrDischDetail = docDisch.createElement("ewtrDetail");
                    Element ewtrDischNm = docDisch.createElement("ewtrDischNm");
                    Element meterCntY = docDisch.createElement("meterCntY");
                    Element meterCntT = docDisch.createElement("meterCntT");
                    Element consumAmt = docDisch.createElement("consumAmt");
                    ewtrDischNm.appendChild(docDisch.createTextNode(dischChkResult.getEwtrDischNm() != null ? dischChkResult.getEwtrDischNm() : " "));
                    meterCntY.appendChild(docDisch.createTextNode(dischChkResult.getMeterCntY() != null ? String.valueOf(NumberFormat.getInstance().format(dischChkResult.getMeterCntY())) : "0"));
                    meterCntT.appendChild(docDisch.createTextNode(dischChkResult.getMeterCntT() != null ? String.valueOf(NumberFormat.getInstance().format(dischChkResult.getMeterCntT())) : "0"));
                    consumAmt.appendChild(docDisch.createTextNode(dischChkResult.getConsumAmt() != null ? String.valueOf(NumberFormat.getInstance().format(dischChkResult.getConsumAmt())) : "0"));
                    ewtrDischDetail.appendChild(ewtrDischNm);
                    ewtrDischDetail.appendChild(meterCntY);
                    ewtrDischDetail.appendChild(meterCntT);
                    ewtrDischDetail.appendChild(consumAmt);
                    ewtrDischData.appendChild(ewtrDischDetail);
                }
                dischResult.appendChild(ewtrDischClassNm);
                dischResult.appendChild(ewtrDischData);
                meterCntYSumDisch += (dischChkResult.getMeterCntY() != null ? dischChkResult.getMeterCntY() : 0.0f);
                meterCntTSumDisch += (dischChkResult.getMeterCntT() != null ? dischChkResult.getMeterCntT() : 0.0f);
                consumAmtDisch += (dischChkResult.getConsumAmt() != null ? dischChkResult.getConsumAmt() : 0.0f);
            }
            rootDisch.appendChild(dischResult);
        }

        for (int i = 0; i < ewtrChemGrpClassNmsUnique.size(); i++) {
            String ewtrChemGrpClassNm = ewtrChemGrpClassNmsUnique.get(i);
            Element ewtrChemChkResult = docChem.createElement("chemChkResult");

            Element ewtrDischGrpNm = docChem.createElement("ewtrDischGrpNm");
            Element ewtrData = docChem.createElement("ewtrData");
            ewtrDischGrpNm.appendChild(docChem.createTextNode(ewtrChemGrpClassNm != null ? ewtrChemGrpClassNm : ""));
            for (int j = 0; j < chemChkResults.size(); j++) {
                ChemChkResult chemChkResult = chemChkResults.get(j);
                if (ewtrChemGrpClassNm.equals(chemChkResult.getEwtrDischGrpNm())) {
                    Element ewtrDetail = docChem.createElement("ewtrDetail");
                    Element ewtrChemNm = docChem.createElement("ewtrChemNm");
                    Element buyAmt = docChem.createElement("buyAmt");
                    Element consumAmt = docChem.createElement("consumAmt");
                    Element amountCurr = docChem.createElement("amountCurr");
                    Element remarks = docChem.createElement("remarks");
                    ewtrChemNm.appendChild(docChem.createTextNode(chemChkResult.getEwtrChemNm() != null ? chemChkResult.getEwtrChemNm() : " "));
                    buyAmt.appendChild(docChem.createTextNode(chemChkResult.getInAmt() != null ? String.valueOf(NumberFormat.getInstance().format(chemChkResult.getInAmt())) : " "));
                    consumAmt.appendChild(docChem.createTextNode(chemChkResult.getConsumAmt() != null ? String.valueOf(NumberFormat.getInstance().format(chemChkResult.getConsumAmt())) : " "));
                    amountCurr.appendChild(docChem.createTextNode(chemChkResult.getAmountCurr() != null ? String.valueOf(NumberFormat.getInstance().format(chemChkResult.getAmountCurr())) : " "));
                    remarks.appendChild(docChem.createTextNode(chemChkResult.getRemarks() != null ? chemChkResult.getRemarks() : " "));
                    ewtrDetail.appendChild(ewtrChemNm);
                    ewtrDetail.appendChild(buyAmt);
                    ewtrDetail.appendChild(consumAmt);
                    ewtrDetail.appendChild(amountCurr);
                    ewtrDetail.appendChild(remarks);
                    ewtrData.appendChild(ewtrDetail);
                }
                ewtrChemChkResult.appendChild(ewtrDischGrpNm);
                ewtrChemChkResult.appendChild(ewtrData);
            }
            rootChem.appendChild(ewtrChemChkResult);
        }

        // create the xml file
        // transform the DOM Object to an XML File
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        DOMSource domSourceSupl = new DOMSource(docSupl);
        DOMSource domSourceDisch = new DOMSource(docDisch);
        DOMSource domSourceChem = new DOMSource(docChem);
        StreamResult streamResultSupl = new StreamResult(new File(suplDataPath));
        StreamResult streamResultDisch = new StreamResult(new File(dischDataPath));
        StreamResult streamResultChem = new StreamResult(new File(chemChkDataPath));
        // 출력 시 문자코드: UTF-8
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        // 들여 쓰기 있음
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(domSourceSupl, streamResultSupl);
        transformer.transform(domSourceDisch, streamResultDisch);
        transformer.transform(domSourceChem, streamResultChem);

        JRDataSource wasteDischFacRunTmList = new JRBeanCollectionDataSource(wasteDischFacRunTms);
        JRDataSource preventFacRunTmList = new JRBeanCollectionDataSource(preventFacRunTms);
        JRDataSource ingrChkResultList = new JRBeanCollectionDataSource(ingrChkResults);
        JRDataSource pwrcChkResultList = new JRBeanCollectionDataSource(pwrcChkResults);
        JRDataSource preventFacBreakHistList = new JRBeanCollectionDataSource(preventFacBreakHists);
        JRDataSource testItemResultNmList = new JRBeanCollectionDataSource(results);
        JRDataSource testItemResultValueList = new JRBeanCollectionDataSource(testItemResultValues);
        File xmlDataSupl = new File(suplDataPath);
        JRXmlDataSource suplDataSource = new JRXmlDataSource(xmlDataSupl, "/report/ewtr3");
        JRDataSource suplChkResultList = suplDataSource;
        File xmlDataDisch = new File(dischDataPath);
        JRXmlDataSource dischDataSource = new JRXmlDataSource(xmlDataDisch, "/report/ewtr4");
        JRDataSource dischChkResultList = dischDataSource;
        File xmlDataChem = new File(chemChkDataPath);
        JRXmlDataSource chemDataSource = new JRXmlDataSource(xmlDataChem, "/report/chemChkResult");
        JRDataSource chemChkResultList = chemDataSource;

        // 파일경로 setting
        String reportPath = globalSettings.getReportEnvEwtrOplog();
        // String reportPath =
        // globalSettings.getReportEnvEwtrOplogDir()+"/test.jrxml";
        String subReportDir = globalSettings.getReportEnvEwtrOplogDir();
        String fileName = FilenameUtils.getBaseName(reportPath);
        String fileExt = "." + FilenameUtils.getExtension(reportPath);

        File file = FileUtil.getFileFixedBase64(reportPath, fileName, fileExt);
        String reportFileName = file.getAbsolutePath();
        String outputFileName = FileUtil.getStoreFilePath() + File.separator + fileName + "_" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".xlsx";
        // String outputFileNamepdf = FileUtil.getStoreFilePath() +
        // File.separator + fileName + "_" + new
        // SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".pdf";

        // Compile the Jasper report from .jrxml to .japser
        JasperReport jasperReport = JasperCompileManager.compileReport(reportFileName);

        // 필수데이터 List to Datasource
        List<OpLogResult> opLogResults = new ArrayList<OpLogResult>();
        opLogResults.add(opLogResult);
        JRDataSource dataSource = new JRBeanCollectionDataSource(opLogResults);
        // 파라미터값
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("report_dir", subReportDir);
        parameters.put("ewtrCleanFacNm", opLogResult.getEwtrCleanFacNm());
        parameters.put("year", year);
        parameters.put("month", month.lastIndexOf("0") == 0 ? month.substring(1, month.length()) : month.toString());
        parameters.put("date", date.lastIndexOf("0") == 0 ? date.substring(1, date.length()) : date.toString());

        // 일지는 근무자시간과 성명을 합쳐서 적용한다.
        parameters.put("shiftWorker1", opLogResult.getShiftConcat1());
        parameters.put("shiftWorker2", opLogResult.getShiftConcat2());
        parameters.put("shiftWorker3", opLogResult.getShiftConcat3());
        parameters.put("shiftWorker4", opLogResult.getShiftConcat4());
        parameters.put("shiftWorker5", opLogResult.getShiftConcat5());
        parameters.put("shiftWorker6", opLogResult.getShiftConcat6());

        parameters.put("meterCntYSumSupl", " ");
        parameters.put("meterCntTSumSupl", " ");
        parameters.put("consumAmtSumSupl", String.valueOf(NumberFormat.getInstance().format(consumAmtSupl)));
        parameters.put("meterCntYSumDisch", " ");
        parameters.put("meterCntTSumDisch", " ");
        parameters.put("consumAmtSumDisch", getComsumAmt);

        parameters.put("wasteDischFacRunTmList", wasteDischFacRunTmList);
        parameters.put("preventFacRunTmList", preventFacRunTmList);
        parameters.put("suplChkResultList", suplChkResultList);
        parameters.put("dischChkResultList", dischChkResultList);
        parameters.put("ingrChkResultList", ingrChkResultList);
        parameters.put("pwrcChkResultList", pwrcChkResultList);
        parameters.put("chemChkResultList", chemChkResultList);
        parameters.put("preventFacBreakHistList", preventFacBreakHistList);
        parameters.put("testItemResultNmList", testItemResultNmList);
        parameters.put("testItemResultValueList", testItemResultValueList);

        // Fill the report
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        // Export the report to a PDF
        // JasperExportManager.exportReportToPdfFile(jasperPrint,
        // outputFileNamepdf);
        // Export the report to a excel
        JRXlsxExporter exporter = new JRXlsxExporter();
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        File outputFile = new File(outputFileName);
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputFile));
        SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
        configuration.setAutoFitPageHeight(true);
        configuration.setCollapseRowSpan(false);

        exporter.setConfiguration(configuration);
        exporter.exportReport();

        return new File(outputFileName);
    }

    /**
     * 슬러지 지도사항 리스트 목록
     *
     * @param measureYmd
     * @param ewtrCleanFacNo
     * @param plantCd
     * @param deptCd
     * @return
     * @throws Exception
     */
    public List<OpLogResult> DailyReportList(String measureYmd, int ewtrCleanFacNo, String plantCd, String deptCd) throws Exception {
        List<OpLogResult> opLogResults = operationMapper.DailyReportList(measureYmd, ewtrCleanFacNo, plantCd, deptCd);
        for (OpLogResult opLogResult : opLogResults) {
            if (opLogResult == null) {
                opLogResult = new OpLogResult();
                opLogResult.setMeasureYmd(measureYmd);
                opLogResult.setEwtrCleanFacNo(ewtrCleanFacNo);
                opLogResult.setPlantCd(plantCd);
                opLogResult.setDeptCd(deptCd);
                opLogResult.setUpdateFlag(false);
            } else {
                opLogResult.setUpdateFlag(true);
            }
        }

        return opLogResults;
    }
}
