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
package com.she.env.air.selfMeasurement.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.she.common.model.AttachFile;
import com.she.common.model.AttachFileGrp;
import com.she.common.model.DefaultParam;
import com.she.common.service.AttachFileService;
import com.she.env.air.model.OutletItem;
import com.she.env.air.model.SelfMeasurement;
import com.she.env.air.model.SelfMeasurementResult;
import com.she.env.air.selfMeasurement.mapper.SelfMeasurementMapper;
import com.she.file.service.FileStorageService;
import com.she.manage.mapper.CodeMasterMapper;
import com.she.manage.model.CodeMaster;
import com.she.security.util.StringUtil;
import com.she.utils.ConstVal;
import com.she.utils.ExcelReader;

/**
 * 대기 운영일지 기능정의
 *
 */
@Service("SelfMeasurementService")
public class SelfMeasurementService {
    private static final Logger logger = LoggerFactory.getLogger(SelfMeasurementService.class);

    private static final String SELF_MEASUREMENT_TASK_CLASS = "SELF_MEASUREMENT_TEMPLETE";

    @Autowired
    private SelfMeasurementMapper selfMeasurementMapper;

    @Autowired
    private AttachFileService attachFileService;

    @Autowired
    private CodeMasterMapper codeMasterMapper;

    @Autowired
    private FileStorageService fileStorageService;

    public List<SelfMeasurement> getSelfMeasurements(String fromYmd, String toYmd, String deptCd, String selfManageStatus, String searchEairOutletNm, String plantCd, String mgDeptCd, DefaultParam defaultParam) throws Exception {
        return this.selfMeasurementMapper.getSelfMeasurements(fromYmd, toYmd, deptCd, selfManageStatus, searchEairOutletNm, plantCd, mgDeptCd, defaultParam);
    }

    public SelfMeasurement getSelfMeasurement(int eairOpMeasNo, DefaultParam defaultParam) throws Exception {
        SelfMeasurement selfMeasurement = this.selfMeasurementMapper.getSelfMeasurement(eairOpMeasNo);
        if (selfMeasurement != null) {
            selfMeasurement.setSelfMeasurementResult(this.selfMeasurementMapper.getSelfMeasurementResult(eairOpMeasNo, selfMeasurement.getEairOutletNo(), defaultParam));
        }
        return selfMeasurement;
    }

    @Transactional
    public int createSelfMeasurement(SelfMeasurement selfMeasurement) throws Exception {
        int result = 0;
        result += this.selfMeasurementMapper.createSelfMeasurement(selfMeasurement);

        if (result > 0) {
            for (SelfMeasurementResult selfMeasurementResult : selfMeasurement.getSelfMeasurementResult()) {
                selfMeasurementResult.setEairOpMeasNo(selfMeasurement.getEairOpMeasNo());
                this.selfMeasurementMapper.createSelfMeasurementResult(selfMeasurementResult);
            }
        }

        return result > 0 ? selfMeasurement.getEairOpMeasNo() : 0;
    }

    @Transactional
    public int updateSelfMeasurement(SelfMeasurement selfMeasurement) throws Exception {
        int result = 0;
        result += this.selfMeasurementMapper.updateSelfMeasurement(selfMeasurement);

        if (result > 0) {
            this.selfMeasurementMapper.deleteSelfMeasurementResult(selfMeasurement.getEairOpMeasNo());
            for (SelfMeasurementResult selfMeasurementResult : selfMeasurement.getSelfMeasurementResult()) {
                selfMeasurementResult.setEairOpMeasNo(selfMeasurement.getEairOpMeasNo());
                this.selfMeasurementMapper.createSelfMeasurementResult(selfMeasurementResult);
            }
        }
        return result > 0 ? selfMeasurement.getEairOpMeasNo() : 0;
    }

    @Transactional
    public int deleteSelfMeasurement(int eairOpMeasNo) throws Exception {
        int result = 0;
        result += this.selfMeasurementMapper.deleteSelfMeasurementResult(eairOpMeasNo);
        result += this.selfMeasurementMapper.deleteSelfMeasurement(eairOpMeasNo);
        return result;
    }

    @Transactional
    public int updateAppr(int eairOpMeasNo, String apprStepCd, int apprRqstNo) throws Exception {

        int resultNo = 0;
        String stepCd = "";
        if (ConstVal.COM_BIZ_APPR_STEP_REJECT.equals(apprStepCd)) {
            // 반려

        } else if (ConstVal.COM_BIZ_APPR_STEP_COMPLETE.equals(apprStepCd)) {
            // 결재완료
            SelfMeasurement selfMeasurement = this.selfMeasurementMapper.getSelfMeasurement(eairOpMeasNo);

            if (selfMeasurement.getStepCd().equals(ConstVal.SAF_PROCESS_STEP_CREATE)) {
                stepCd = ConstVal.SAF_PROCESS_STEP_COMPLTE;
            }
        }
        resultNo += this.selfMeasurementMapper.updateAppr(eairOpMeasNo, stepCd, apprRqstNo);

        return resultNo;
    }

    /**
     * 자가측정 중복체크
     *
     * @param eairOpMeasNo
     *            자가측정번호
     * @param eairOutletNo
     *            배출구번호
     * @param plantCd
     *            사업장코드
     * @param measureYmd
     *            측정일자
     * @return 중복 행 수
     * @throws Exception
     */
    public int checkSelfMeasurement(int eairOpMeasNo, int eairOutletNo, String plantCd, String measureYmd) throws Exception {
        return this.selfMeasurementMapper.checkSelfMeasurement(eairOpMeasNo, eairOutletNo, plantCd, measureYmd);
    }

    /**
     * 자가측정결과 엑셀 업로드
     *
     * @return map(message, uploadList, totCount, completeCount, dontCount)
     * @throws Exception
     */
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public Map<String, Object> getUploadExcelDataSelfMeasurement(String plantCd, String createUserId, MultipartFile[] files) throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
        String taskKey = formatter.format(new Date());
        AttachFileGrp attachFileGrp = new AttachFileGrp(SELF_MEASUREMENT_TASK_CLASS, taskKey, "", "", "", new ArrayList<>(Arrays.asList(files)));
        List<AttachFile> attachFiles = this.attachFileService.uploadFiles(attachFileGrp);

        // 저장된 엑셀 파일의 정보를 읽는다.
        List<AttachFile> excelFiles = attachFileService.getUploadFiles(SELF_MEASUREMENT_TASK_CLASS, taskKey, "");
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
            List<String[][]> sheets = reader.read(excel, 0);

            ClassPathResource classPathResource = new ClassPathResource("templates" + path.getCodeNm() + "대기자가측정결과업로드양식.xlsx");
            File templete = classPathResource.getFile();
            reader = new ExcelReader();
            List<String[][]> templeteSheets = reader.read(templete, 0);

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
                        // 업로드양식 통과 후 데이터 검사

                        List<LinkedHashMap<String, Object>> uploadList = new ArrayList<LinkedHashMap<String, Object>>();
                        StringBuilder rowCheck;

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
                                LinkedHashMap<String, Object> subData = new LinkedHashMap<String, Object>();
                                // 1. 배출구일련번호
                                rowData.put("eairOutletNm", sheet[row][0].trim());
                                // 2. 허가증상배출구번호
                                rowData.put("eairOutletPermitNo", sheet[row][1].trim());
                                // 3. 배출구명
                                rowData.put("mainDischFacNm", sheet[row][2].trim());

                                // 배출구 번호
                                String eairOutletNm = String.valueOf(rowData.get("eairOutletNm"));
                                String eairOutletPermitNo = String.valueOf(rowData.get("eairOutletPermitNo"));
                                String mainDischFacNm = String.valueOf(rowData.get("mainDischFacNm"));
                                int eairOutletNo = selfMeasurementMapper.getOutletSelfMeasurement(eairOutletNm, eairOutletPermitNo, mainDischFacNm, plantCd);

                                // 4. 기상
                                rowData.put("weatherNm", sheet[row][3].trim());
                                String weatherNm = String.valueOf(rowData.get("weatherNm"));
                                String weatherCd = selfMeasurementMapper.checkCodeMaster(weatherNm, "SAF_WEATHER");
                                rowData.put("weatherCd", weatherCd.trim());
                                // 5. 온도℃
                                rowData.put("temp", sheet[row][4].trim());
                                // 6. 습도％
                                rowData.put("hum", sheet[row][5].trim());
                                // 7. 기압mmHg
                                rowData.put("airPress", sheet[row][6].trim());
                                // 8. 풍향
                                rowData.put("windDir", sheet[row][7].trim());
                                // 9. 풍속m／sec
                                rowData.put("windSpeed", sheet[row][8].trim());
                                // 10. 측정일자
                                rowData.put("measureYmd", sheet[row][9].trim());
                                // 11. 자가측정방법
                                rowData.put("methodNm", sheet[row][10].trim());

                                String methodNm = String.valueOf(rowData.get("methodNm"));
                                String methodCd = selfMeasurementMapper.checkCodeMaster(methodNm, "EAIR_SELF_TEST_MTD");

                                rowData.put("methodCd", methodCd.trim());
                                // 12. 가스속도m／s
                                rowData.put("gasSpeed", sheet[row][11].trim());
                                // 13. 가스온도℃
                                rowData.put("gasTemp", sheet[row][12].trim());
                                // 14. 수분함량％
                                rowData.put("wtrPer", sheet[row][13].trim());
                                // 15. 실측산소농도％
                                rowData.put("realO2Val", sheet[row][14].trim());
                                // 16. 표준산소농도％
                                rowData.put("stndO2Val", sheet[row][15].trim());
                                // 17. 배출가스유량S㎥／min
                                rowData.put("flowDay", sheet[row][16].trim());
                                // 18. 연료명및사용량
                                rowData.put("fuelNmResult", sheet[row][17].trim());
                                // 19. 원료명및사용량
                                rowData.put("ingrNmResult", sheet[row][18].trim());
                                // 20. 환경기술인
                                rowData.put("envEngrNm", sheet[row][19].trim());
                                // 21. 환경기술인의견
                                rowData.put("envEngrOpn", sheet[row][20].trim());
                                // 22. 오염물질
                                rowData.put("eairTestItemNm", sheet[row][21].trim());
                                subData.put("eairTestItemNm", sheet[row][21].trim());

                                String eairTestItemNm = String.valueOf(rowData.get("eairTestItemNm"));
                                String eairTestItemCd = selfMeasurementMapper.checkTestItem(plantCd, eairTestItemNm);

                                rowData.put("eairTestItemCd", eairTestItemCd.trim());
                                subData.put("eairTestItemCd", eairTestItemCd.trim());
                                // 23. 농도
                                rowData.put("numResult", sheet[row][22].trim());
                                subData.put("numResult", sheet[row][22].trim());
                                // 24. 일일배출량kg/day
                                rowData.put("dischAmtPerDay", sheet[row][23].trim());
                                subData.put("dischAmtPerDay", sheet[row][23].trim());
                                // 25. 검사기기명
                                rowData.put("eairInstNm", sheet[row][24].trim());
                                subData.put("eairInstNm", sheet[row][24].trim());

                                String eairInstNm = String.valueOf(rowData.get("eairInstNm"));
                                String eairInstCd = selfMeasurementMapper.checkCodeMaster(eairInstNm, "EAIR_INST");

                                rowData.put("eairInstCd", eairInstCd.trim());
                                subData.put("eairInstCd", eairInstCd.trim());
                                // 26. 검사방법
                                rowData.put("eairTestMtdNm", sheet[row][25].trim());
                                subData.put("eairTestMtdNm", sheet[row][25].trim());

                                String eairTestMtdNm = String.valueOf(rowData.get("eairTestMtdNm"));
                                String eairTestMtdCd = selfMeasurementMapper.checkCodeMaster(eairTestMtdNm, "EAIR_TEST_MTD");

                                rowData.put("eairTestMtdCd", eairTestMtdCd.trim());
                                subData.put("eairTestMtdCd", eairTestMtdCd.trim());

                                // 배출구일련번호, 허가증상배출구번호, 배출구명 유효성 검사
                                boolean checkOutlet = eairOutletNo > 0 ? true : false;

                                // 기상(날씨) 유효성 검사
                                boolean checkWeatherCd = StringUtil.isEmpty(String.valueOf(rowData.get("weatherCd"))) ? false : true;

                                // 온도℃ 유효성 검사
                                boolean checkTemp = StringUtil.isEmpty(String.valueOf(rowData.get("temp"))) ? false : isStringFloat(String.valueOf(rowData.get("temp")));

                                // 습도% 유효성 검사
                                boolean checkHum = StringUtil.isEmpty(String.valueOf(rowData.get("hum"))) ? false : isStringFloat(String.valueOf(rowData.get("hum")));

                                // 기압 유효성 검사
                                boolean checkAirPress = StringUtil.isEmpty(String.valueOf(rowData.get("airPress"))) ? false : isStringFloat(String.valueOf(rowData.get("airPress")));

                                // 풍속 유효성 검사
                                boolean checkWindSpeed = StringUtil.isEmpty(String.valueOf(rowData.get("windSpeed"))) ? false : isStringFloat(String.valueOf(rowData.get("windSpeed")));

                                // 가스속도 유효성 검사
                                boolean checkGasSpeed = StringUtil.isEmpty(String.valueOf(rowData.get("gasSpeed"))) ? false : isStringFloat(String.valueOf(rowData.get("gasSpeed")));

                                // 가스온도 유효성 검사
                                boolean checkGasTemp = StringUtil.isEmpty(String.valueOf(rowData.get("gasTemp"))) ? false : isStringFloat(String.valueOf(rowData.get("gasTemp")));

                                // 수분함량 유효성 검사
                                boolean checkWtrPer = StringUtil.isEmpty(String.valueOf(rowData.get("wtrPer"))) ? false : isStringFloat(String.valueOf(rowData.get("wtrPer")));

                                // 실측산소농도 유효성 검사
                                boolean checkRealO2Val = StringUtil.isEmpty(String.valueOf(rowData.get("realO2Val"))) ? false : isStringFloat(String.valueOf(rowData.get("realO2Val")));

                                // 표준산소농도 유효성 검사
                                boolean checkStndO2Val = StringUtil.isEmpty(String.valueOf(rowData.get("stndO2Val"))) ? false : isStringFloat(String.valueOf(rowData.get("stndO2Val")));

                                // 배출가스유량 유효성 검사
                                boolean checkFlowDay = StringUtil.isEmpty(String.valueOf(rowData.get("flowDay"))) ? false : isStringFloat(String.valueOf(rowData.get("flowDay")));

                                // 풍향 유효성 검사
                                boolean checkWindDir = StringUtil.isEmpty(String.valueOf(rowData.get("windDir"))) ? false : true;

                                // 측정일자 유효성 검사
                                boolean checkMeasureYmd = StringUtil.isEmpty(String.valueOf(rowData.get("measureYmd"))) ? false : isValidDate(String.valueOf(rowData.get("measureYmd")));

                                // 자가측정방법 유효성 검사
                                boolean checkMethodCd = StringUtil.isEmpty(String.valueOf(rowData.get("methodCd"))) ? false : true;

                                // 검사항목(오염물질) 유효성 검사
                                boolean checkEairTestItemCd = StringUtil.isEmpty(String.valueOf(rowData.get("eairTestItemCd"))) ? false : isValidEairTestItemCd(String.valueOf(rowData.get("eairTestItemCd")), eairOutletNo);

                                // 농도 유효성 검사
                                boolean checkNumResult = StringUtil.isEmpty(String.valueOf(rowData.get("numResult"))) ? false : isStringFloat(String.valueOf(rowData.get("numResult")));

                                // 일일배출량 유효성 검사
                                boolean checkDischAmtPerDay = StringUtil.isEmpty(String.valueOf(rowData.get("dischAmtPerDay"))) ? false : isStringFloat(String.valueOf(rowData.get("dischAmtPerDay")));

                                // 검사기기명 유효성 검사
                                boolean checkEairInstCd = StringUtil.isEmpty(String.valueOf(rowData.get("eairInstCd"))) ? false : true;

                                // 검사방법 유효성 검사
                                boolean checkEairTestMtdCd = StringUtil.isEmpty(String.valueOf(rowData.get("eairTestMtdCd"))) ? false : true;

                                if (checkOutlet && checkWeatherCd && checkTemp && checkHum && checkAirPress && checkWindSpeed && checkWindDir && checkMeasureYmd && checkMethodCd && checkEairTestItemCd && checkEairInstCd && checkEairTestMtdCd && checkGasSpeed && checkGasTemp && checkWtrPer && checkRealO2Val && checkStndO2Val && checkFlowDay
                                        && checkNumResult && checkDischAmtPerDay) {
                                    rowData.put("temp", Float.parseFloat(String.valueOf(rowData.get("temp"))));
                                    rowData.put("hum", Float.parseFloat(String.valueOf(rowData.get("hum"))));
                                    rowData.put("airPress", Float.parseFloat(String.valueOf(rowData.get("airPress"))));
                                    rowData.put("windSpeed", Float.parseFloat(String.valueOf(rowData.get("windSpeed"))));
                                    rowData.put("gasSpeed", Float.parseFloat(String.valueOf(rowData.get("gasSpeed"))));
                                    rowData.put("gasTemp", Float.parseFloat(String.valueOf(rowData.get("gasTemp"))));
                                    rowData.put("wtrPer", Float.parseFloat(String.valueOf(rowData.get("wtrPer"))));
                                    rowData.put("realO2Val", Float.parseFloat(String.valueOf(rowData.get("realO2Val"))));
                                    rowData.put("stndO2Val", Float.parseFloat(String.valueOf(rowData.get("stndO2Val"))));
                                    rowData.put("flowDay", Float.parseFloat(String.valueOf(rowData.get("flowDay"))));
                                    rowData.put("numResult", Float.parseFloat(String.valueOf(rowData.get("numResult"))));
                                    rowData.put("dischAmtPerDay", Float.parseFloat(String.valueOf(rowData.get("dischAmtPerDay"))));
                                    subData.put("numResult", Float.parseFloat(String.valueOf(rowData.get("numResult"))));
                                    subData.put("dischAmtPerDay", Float.parseFloat(String.valueOf(rowData.get("dischAmtPerDay"))));

                                    SelfMeasurement selfMeasurement = new SelfMeasurement();
                                    BeanUtils.populate(selfMeasurement, rowData);
                                    selfMeasurement.setPlantCd(plantCd);
                                    selfMeasurement.setCreateUserId(createUserId);
                                    selfMeasurement.setEairOutletNo(eairOutletNo);

                                    int eairOpMeasNo = this.selfMeasurementMapper.checkExcelSelfMeasurement(plantCd, eairOutletNo, String.valueOf(rowData.get("measureYmd")));
                                    if (eairOpMeasNo > 0) {
                                        SelfMeasurementResult selfMeasurementResult = new SelfMeasurementResult();
                                        BeanUtils.populate(selfMeasurementResult, subData);
                                        selfMeasurementResult.setEairOpMeasNo(eairOpMeasNo);
                                        this.selfMeasurementMapper.updateSelfMeasurementResult(selfMeasurementResult);
                                        completeCount++;
                                    } else {
                                        int count = 0;
                                        count += this.selfMeasurementMapper.createSelfMeasurement(selfMeasurement);
                                        if (count > 0) {
                                            // 신규등록 시 배출구에 등록된 검사항목들 조회 후 일괄 등록
                                            // 일괄 등록 시 첫번째 검사항목 값도 같이 저장
                                            List<OutletItem> outletItems = selfMeasurementMapper.getOutletTestItems(eairOutletNo);
                                            for (OutletItem outletItem : outletItems) {
                                                if (outletItem.getEairTestItemCd().equals(String.valueOf(subData.get("eairTestItemCd")))) {
                                                    SelfMeasurementResult selfMeasurementResult = new SelfMeasurementResult();
                                                    BeanUtils.populate(selfMeasurementResult, subData);
                                                    selfMeasurementResult.setEairOpMeasNo(selfMeasurement.getEairOpMeasNo());
                                                    this.selfMeasurementMapper.createSelfMeasurementResult(selfMeasurementResult);
                                                } else {
                                                    SelfMeasurementResult selfMeasurementResult = new SelfMeasurementResult();
                                                    selfMeasurementResult.setEairOpMeasNo(selfMeasurement.getEairOpMeasNo());
                                                    selfMeasurementResult.setEairTestItemCd(outletItem.getEairTestItemCd());
                                                    this.selfMeasurementMapper.createSelfMeasurementResult(selfMeasurementResult);
                                                }
                                            }
                                            completeCount++;
                                        }
                                    }

                                } else {
                                    String failMessage = "";
                                    dontCount++;
                                    if (!checkOutlet) {
                                        failMessage = this.appendString(failMessage, "배출구일련번호, 허가증상배출구번호, 배출구명이 잘못되었습니다.");
                                    }
                                    if (!checkWeatherCd) {
                                        failMessage = this.appendString(failMessage, "기상(날씨)코드가 잘못되었습니다.");
                                    }
                                    if (!checkTemp) {
                                        failMessage = this.appendString(failMessage, "[숫자X]온도℃가 잘못되었습니다.");
                                    }
                                    if (!checkHum) {
                                        failMessage = this.appendString(failMessage, "[숫자X]습도%가 잘못되었습니다.");
                                    }
                                    if (!checkAirPress) {
                                        failMessage = this.appendString(failMessage, "[숫자X]기압이 잘못되었습니다.");
                                    }
                                    if (!checkWindSpeed) {
                                        failMessage = this.appendString(failMessage, "[숫자X]풍속이 잘못되었습니다.");
                                    }
                                    if (!checkWindDir) {
                                        failMessage = this.appendString(failMessage, "풍향이 잘못되었습니다.");
                                    }
                                    if (!checkMeasureYmd) {
                                        failMessage = this.appendString(failMessage, "측정일자가 잘못되었습니다.(ex: 20210101)");
                                    }
                                    if (!checkMethodCd) {
                                        failMessage = this.appendString(failMessage, "자가측정방법이 잘못되었습니다.");
                                    }
                                    if (!checkEairTestItemCd) {
                                        failMessage = this.appendString(failMessage, "오염물질(검사항목)이 잘못되었습니다. 배출구에 등록된 검사항목인지 확인하십시오.");
                                    }
                                    if (!checkEairInstCd) {
                                        failMessage = this.appendString(failMessage, "검사기기명이 잘못되었습니다.");
                                    }
                                    if (!checkEairTestMtdCd) {
                                        failMessage = this.appendString(failMessage, "검사방법이 잘못되었습니다.");
                                    }
                                    if (!checkGasSpeed) {
                                        failMessage = this.appendString(failMessage, "[숫자X]가스속도가 잘못되었습니다.");
                                    }
                                    if (!checkGasTemp) {
                                        failMessage = this.appendString(failMessage, "[숫자X]가스온도가 잘못되었습니다.");
                                    }
                                    if (!checkWtrPer) {
                                        failMessage = this.appendString(failMessage, "[숫자X]수분함량이 잘못되었습니다.");
                                    }
                                    if (!checkRealO2Val) {
                                        failMessage = this.appendString(failMessage, "[숫자X]실측산소농도가 잘못되었습니다.");
                                    }
                                    if (!checkStndO2Val) {
                                        failMessage = this.appendString(failMessage, "[숫자X]표준산소농도가 잘못되었습니다.");
                                    }
                                    if (!checkFlowDay) {
                                        failMessage = this.appendString(failMessage, "[숫자X]배출가스유량이 잘못되었습니다.");
                                    }
                                    if (!checkNumResult) {
                                        failMessage = this.appendString(failMessage, "[숫자X]농도가 잘못되었습니다.");
                                    }
                                    if (!checkDischAmtPerDay) {
                                        failMessage = this.appendString(failMessage, "[숫자X]일일배출량이 잘못되었습니다.");
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
                    }
                }
            }
        } catch (FileNotFoundException fe) {
            logger.error(fe.getMessage());
            map.put("success", false);
            map.put("message", "업로드 도중 에러발생");
        } catch (IOException ie) {
            logger.error(ie.getMessage());
            map.put("success", false);
            map.put("message", "업로드 도중 에러발생");
        } catch (Exception exception) {
            logger.error(exception.getMessage());
            map.put("success", false);
            map.put("message", "업로드 도중 에러발생");
        } finally {
            // 다 읽어드린 엑셀 파일은 지운다.
            this.fileStorageService.deleteFile(attachFiles.get(0).getFilePath());
            attachFileService.deleteFile(String.valueOf(attachFiles.get(0).getFileNo()));
        }
        return map;
    }

    /**
     * 배출구에 등록된 오염물질(검사항목)인지 확인
     *
     * @param eairTestItemCd
     *            검사항목 코드
     * @param eairOutletNo
     *            배출구 번호
     * @return
     */
    public boolean isValidEairTestItemCd(String eairTestItemCd, int eairOutletNo) throws Exception {
        int count = 0;
        count += selfMeasurementMapper.checkOutletTestItem(eairOutletNo, eairTestItemCd);
        if (count > 0) {
            return true;
        } else {
            return false;
        }
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
     * 문자열 숫자인지 문자인지 확인
     *
     * @param val
     * @return
     */
    public boolean isStringFloat(String val) {
        try {
            Float.parseFloat(val);
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
     * 날짜형식 확인
     *
     * @param dateString
     *            확인문자
     * @return 날짜형식여부
     */
    private boolean isValidDate(String dateString) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
            format.setLenient(false);
            format.parse(dateString);

            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}
