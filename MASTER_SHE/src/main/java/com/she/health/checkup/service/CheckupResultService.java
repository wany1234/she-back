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

package com.she.health.checkup.service;

import java.io.File;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import com.she.common.model.AttachFile;
import com.she.common.model.AttachFileGrp;
import com.she.common.model.DefaultParam;
import com.she.common.service.AttachFileService;
import com.she.file.service.FileStorageService;
import com.she.health.baseInfo.service.DiseaseService;
import com.she.health.baseInfo.service.TestItemService;
import com.she.health.checkup.mapper.CheckupResultMapper;
import com.she.health.checkup.mapper.CheckupResultUploadMapper;
import com.she.health.model.CheckupPlan;
import com.she.health.model.CheckupPlanOrg;
import com.she.health.model.CheckupResult;
import com.she.health.model.CheckupResultDiag;
import com.she.health.model.CheckupSituation;
import com.she.health.model.CheckupSituationDetail;
import com.she.health.model.CheckupUser;
import com.she.health.model.Disease;
import com.she.health.model.TestItem;
import com.she.health.model.TestItemResult;
import com.she.manage.mapper.CodeMasterMapper;
import com.she.manage.model.CodeMaster;
import com.she.manage.service.CodeMasterService;
import com.she.security.util.StringUtil;
import com.she.utils.ConstVal;
import com.she.utils.ExcelReader;

/**
 * 건강검진결과 조회 기능정의
 *
 */
@Service
public class CheckupResultService {
    @Autowired
    private CheckupResultMapper checkupResultMapper;

    @Autowired
    private CheckupResultUploadMapper checkupResultUploadMapper;

    @Autowired
    private AttachFileService attachFileService;

    @Autowired
    private CheckupPlanService checkupPlanService;

    @Autowired
    private DiseaseService diseaseService;

    @Autowired
    private CodeMasterService codeMasterService;

    @Autowired
    private CodeMasterMapper codeMasterMapper;

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private TestItemService testItemService;

    /**
     *
     * 건강검진결과 조회
     *
     * @param userId
     *            건강검진대상자
     * @param checkupYear
     *            검진연도
     * @param heaCheckupPlanNo
     *            검진계획
     * @param retirementYn
     *            재직/퇴직
     * @param heaDiagnoseCds
     *            판정(복수선택)
     * @param heaDiseaseClassCd
     *            질환종류
     * @param heaDiseaseCd
     *            질환
     * @param heaCheckedOrgNos
     *            검진병원(복수선택)
     * @param userNm
     *            사원명
     * @param plantCd
     *            사업장코드
     * @return 건강검진결과목록
     * @throws Exception
     */
    public List<CheckupResult> getCheckupResults(String userId, String checkupYear, int heaCheckupPlanNo, String retirementYn, String[] heaDiagnoseCds, String heaDiseaseClassCd, String heaDiseaseCd, int[] heaCheckedOrgNos, String userNm, String plantCd, String deptCd, String deptSubYn) throws Exception {
        return this.checkupResultMapper.getCheckupResults(userId, checkupYear, heaCheckupPlanNo, retirementYn, heaDiagnoseCds, heaDiseaseClassCd, heaDiseaseCd, heaCheckedOrgNos, userNm, plantCd, deptCd, deptSubYn);
    }

    /**
     * 건강검진결과 상세 조회
     *
     * @param heaCheckupPlanNo
     *            건강검진계획번호
     * @param userId
     *            사용자아이디
     * @return 검진정보
     * @throws Exception
     */
    public CheckupResult getCheckupResult(int heaCheckupPlanNo, String userId) throws Exception {
        return this.checkupResultMapper.getCheckupResult(heaCheckupPlanNo, userId);
    }

    /**
     * 건강검진진단결과 조회
     *
     * @param heaCheckupPlanNo
     *            건강검진계획번호
     * @param userId
     *            사용자아이디
     * @return 소견및판정정보
     * @throws Exception
     */
    public List<CheckupResultDiag> getCheckupResultDiags(int heaCheckupPlanNo, String userId) throws Exception {
        return this.checkupResultMapper.getCheckupResultDiags(heaCheckupPlanNo, userId);
    }

    /**
     * 건강검진항목별 결과 조회
     *
     * @param heaCheckupPlanNo
     *            건강검진계획번호
     * @param userId
     *            사용자아이디
     * @param optionalYn
     *            선택항목여부
     * @return 검진항목 목록
     * @throws Exception
     */
    public List<TestItemResult> getTestItemResults(int heaCheckupPlanNo, String userId, String optionalYn) throws Exception {
        return this.checkupResultMapper.getTestItemResults(heaCheckupPlanNo, userId, optionalYn);
    }

    /**
     * 건강검진결과 등록 전 유효성 체크
     *
     * @param heaCheckupPlanNo
     *            건강검진계획번호
     * @param userId
     *            검진대상자ID
     * @return 중복 행 수
     * @throws Exception
     */
    public int validCheckupResult(int heaCheckupPlanNo, String userId) throws Exception {
        return this.checkupResultMapper.validCheckupResult(heaCheckupPlanNo, userId);
    }

    /**
     * 건강검진결과 등록
     *
     * @param checkupResult
     *            건강검진결과
     * @return 건강검진계획번호
     * @throws Exception
     */
    public int createCheckupResult(CheckupResult checkupResult) throws Exception {
        return this.checkupResultMapper.createCheckupResult(checkupResult) > 0 ? checkupResult.getHeaCheckupPlanNo() : 0;
    }

    /**
     * 건강검진결과 수정
     *
     * @param checkupResult
     *            건강검진결과
     * @return 건강검진계획번호
     * @throws Exception
     */
    public int updateCheckupResult(CheckupResult checkupResult) throws Exception {
        return this.checkupResultMapper.updateCheckupResult(checkupResult) > 0 ? checkupResult.getHeaCheckupPlanNo() : 0;
    }

    /**
     * 건강검진진단결과 등록
     *
     * @param checkupResultDiag
     *            건강검진진단결과
     * @return 등록행수
     * @throws Exception
     */
    public int createCheckupResultDiag(CheckupResultDiag checkupResultDiag) throws Exception {
        return this.checkupResultMapper.createCheckupResultDiag(checkupResultDiag);
    }

    /**
     * 건강검진진단결과 삭제
     *
     * @param checkupResultDiags
     *            삭제목록
     * @return 삭제행수
     * @throws Exception
     */
    @Transactional
    public int deleteCheckupResultDiags(List<CheckupResultDiag> checkupResultDiags) throws Exception {
        int count = 0;
        for (CheckupResultDiag checkupResultDiag : checkupResultDiags) {
            count += this.checkupResultMapper.deleteCheckupResultDiag(checkupResultDiag.getHeaCheckupPlanNo(), checkupResultDiag.getUserId(), checkupResultDiag.getHeaDiseaseCd());
        }

        return count;
    }

    /**
     * 건강검진항목별 결과 등록
     *
     * @param testItemResult
     *            건강검진항목별 결과
     * @return 등록행수
     * @throws Exception
     */
    public int createTestItemResult(TestItemResult testItemResult) throws Exception {
        if (testItemResult.getHeaResultTypeCd().equals(ConstVal.CODE_MASTER_HEA_RESULT_TYPE_NUNBER)) {
            testItemResult.setNumResult(Float.parseFloat(testItemResult.getCharResult()));
        }

        return this.checkupResultMapper.createTestItemResult(testItemResult);
    }

    /**
     * 건강검진항목별 결과 삭제
     *
     * @param testItemResults
     *            삭제목록
     * @return 삭제행수
     * @throws Exception
     */
    @Transactional
    public int deleteTestItemResults(@RequestBody List<TestItemResult> testItemResults) throws Exception {
        int count = 0;
        for (TestItemResult testItemResult : testItemResults) {
            count += this.checkupResultMapper.deleteTestItemResult(testItemResult.getHeaCheckupPlanNo(), testItemResult.getUserId(), testItemResult.getHeaTestItemCd());
        }

        return count;
    }

    /**
     * 건강검진결과 엑셀업로드 파일 조회 및 validation check
     *
     * @param taskClassNm
     *            업무구분
     * @param taskKey
     *            업무키
     * @param heaCheckupPlanNo
     *            건강검진계획번호
     * @param heaCheckupOrgNo
     *            건강검진기관번호
     * @param createUserId
     *            등록자
     * @param files
     *            파일
     * @return 건강검진결과 엑셀업로드 파일 데이터 목록
     * @throws Exception
     */
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public Map<String, Object> uploadExcelCheckupResult(String taskClassNm, String taskKey, int heaCheckupPlanNo, int heaCheckupOrgNo, String createUserId, MultipartFile[] files) throws Exception {
        AttachFileGrp attachFileGrp = new AttachFileGrp(taskClassNm, taskKey, "", "", "", new ArrayList<>(Arrays.asList(files)));
        List<AttachFile> attachFiles = this.attachFileService.uploadFiles(attachFileGrp);

        // 건강검진계획 정보 조회
        CheckupPlan checkupPlan = this.checkupPlanService.getCheckupPlan(heaCheckupPlanNo);
        // 양식 파일 이름
        String templeteFileNm = "";
        if (checkupPlan.getHeaCheckupClassCd().equals(ConstVal.CODE_MASTER_HEA_CHECKUP_CLASS_SPECIAL)) {
            // 특수 검진인 경우
            templeteFileNm = File.separator + "특수검진결과_엑셀업로드양식.xlsx";
        } else {
            templeteFileNm = File.separator + "일반검진결과_엑셀업로드양식.xlsx";
        }

        // 양식 파일경로
        CodeMaster path = this.codeMasterMapper.getCodeMaster(ConstVal.CODE_GROUP_FILE_PATH, ConstVal.CODE_FILE_PATH_FORM, "Y");

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("success", false);
        map.put("message", "");
        map.put("uploadResult", null);
        map.put("errorInfo", null);

        int checkupResultTotalCount = 0;
        int checkupResultSuccessCount = 0;
        int checkupResultFailCount = 0;

        int checkupResultDiagTotalCount = 0;
        int checkupResultDiagSuccessCount = 0;
        int checkupResultDiagFailCount = 0;

        int testItemResultTotalCount = 0;
        int testItemResultSuccessCount = 0;
        int testItemResultFailCount = 0;

        try {
            File excel = new File(attachFiles.get(0).getFilePath());
            ExcelReader reader = new ExcelReader();
            List<String[][]> sheets = reader.read(excel);

            // File templete = new File(path.getCodeNm() + templeteFileNm);
            File templete = new ClassPathResource("templates" + path.getCodeNm() + templeteFileNm).getFile();
            reader = new ExcelReader();
            List<String[][]> templeteSheets = reader.read(templete);

            if (sheets != null && templeteSheets != null) {
                if (sheets.size() == 7) {
                    String checkupResultSheetName = "검진소견결과";
                    String checkupResultDiagSheetName = "검진질환및판정결과";
                    String testItemResultSheetName = "검진항목별결과";

                    String[][] checkupResultSheet = sheets.get(0);
                    String[][] checkupResultDiagSheet = sheets.get(1);
                    String[][] testItemResultSheet = sheets.get(2);

                    String[][] templateCheckupResultSheet = sheets.get(0);
                    String[][] templateCheckupResultDiagSheet = sheets.get(1);
                    String[][] templateTestItemResultSheet = sheets.get(2);

                    // String[] checkupResultSheetHeader;
                    // String[] checkupResultDiagSheetHeader;
                    // String[] testItemResultSheetHeader = new String[] {
                    // "사원번호", "검사항목코드", "검사항목", "결과값" };
                    //
                    // if
                    // (checkupPlan.getHeaCheckupClassCd().equals(ConstVal.CODE_MASTER_HEA_CHECKUP_CLASS_SPECIAL))
                    // {
                    // // 특수검진인 경우 관련유해인자 항목 설정
                    // checkupResultSheetHeader = new String[] { "검진일자", "사원번호",
                    // "검진기관코드", "검진기관", "업무수행적합여부코드", "업무수행적합여부", "사후관리코드",
                    // "사후관리", "종합의견", "관련유해인자" };
                    // checkupResultDiagSheetHeader = new String[] { "사원번호",
                    // "판정코드", "판정", "질환코드", "질환", "유해인자코드", "유해인자" };
                    // } else {
                    // // 특수검진이 아닌 경우 관련유해인자 항목 미설정
                    // checkupResultSheetHeader = new String[] { "검진일자", "사원번호",
                    // "검진기관코드", "검진기관", "업무수행적합여부코드", "업무수행적합여부", "사후관리코드",
                    // "사후관리", "종합의견" };
                    // checkupResultDiagSheetHeader = new String[] { "사원번호",
                    // "판정코드", "판정", "질환코드", "질환" };
                    // }

                    // 헤더 양식확인
                    if (checkupResultSheet.length > 0 && !reader.excelHeaderCheck(checkupResultSheet[0], templateCheckupResultSheet[0])) {
                        map.put("message", "업로드양식이 오류: " + checkupResultSheetName + " 시트의 헤더가 다릅니다.");
                    } else if (checkupResultDiagSheet.length > 0 && !reader.excelHeaderCheck(checkupResultDiagSheet[0], templateCheckupResultDiagSheet[0])) {
                        map.put("message", "업로드양식이 오류: " + checkupResultDiagSheetName + " 시트의 헤더가 다릅니다.");
                    } else if (testItemResultSheet.length > 0 && !reader.excelHeaderCheck(testItemResultSheet[0], templateTestItemResultSheet[0])) {
                        map.put("message", "업로드양식이 오류: " + testItemResultSheetName + " 시트의 헤더가 다릅니다.");
                    } else {
                        // 업로드양식 통과 후 데이터검사
                        // 검진대상자 체크를 위해 대상자 조회
                        List<CheckupUser> checkupUsers = this.checkupPlanService.getCheckupUsersNoPage(checkupPlan.getHeaCheckupPlanNo(), 0, null, null, null, null, null, null, null, null);
                        // 검진대상기관(검진기관) 조회
                        List<CheckupPlanOrg> checkupPlanOrgs = this.checkupPlanService.getCheckupPlanOrgs(heaCheckupPlanNo, 0, "");
                        // 질환분류별 질환 조회
                        List<Disease> diseases = this.diseaseService.getDiseases(null, null);
                        // 결과진단(판정) 조회
                        List<CodeMaster> diagnoses = this.codeMasterService.getSelect(ConstVal.CODE_GROUP_HEA_DIAGNOSE_CD, "Y", new DefaultParam("kr"));
                        // 업무수행적합여부코드 조회
                        List<CodeMaster> heaWorkables = this.codeMasterService.getSelect(ConstVal.CODE_GROUP_HEA_WORKABLE_CD, "Y", new DefaultParam("kr"));
                        // 사후관리코드 조회
                        List<CodeMaster> heaFollowUps = this.codeMasterService.getSelect(ConstVal.CODE_GROUP_HEA_FOLLOW_UP_CD, "Y", new DefaultParam("kr"));
                        // 검사항목코드 조회
                        List<TestItem> heaTestItems = this.testItemService.getTestItems(null, 0, null, null, "Y", null, "");

                        List<Map<String, Object>> uploadResults = new ArrayList<Map<String, Object>>();
                        List<Map<String, Object>> errorInfos = new ArrayList<Map<String, Object>>();

                        Map<String, Object> checkupResultMap = new HashMap<String, Object>();
                        Map<String, Object> checkupResultDiagMap = new HashMap<String, Object>();
                        Map<String, Object> testItemResultMap = new HashMap<String, Object>();

                        StringBuilder rowCheck;

                        // 검진결과 (sheet 1)
                        for (int row = 1; row < checkupResultSheet.length; row++) {
                            rowCheck = new StringBuilder();
                            for (int i = 0; i < checkupResultSheet[row].length; i++) {
                                rowCheck.append(checkupResultSheet[row][i].trim());
                            }

                            if (checkupResultSheet[row][1].trim().equals("")) {
                                continue;
                            }

                            if (rowCheck.length() > 0) {
                                // 1-0. 검진일자
                                String heaCheckedYmd = checkupResultSheet[row][0].trim();
                                // 1-1. 사원번호
                                String userIdTemp = checkupResultSheet[row][1].trim();
                                if (StringUtil.isNotEmpty(userIdTemp) && userIdTemp.indexOf(".") > -1) {
                                    userIdTemp = userIdTemp.substring(0, userIdTemp.indexOf("."));
                                }
                                String userId = userIdTemp;
                                // 1-2. 업무수행적합여부코드
                                // 1-3. 업무수행적합여부
                                String heaWorkableCd = checkupResultSheet[row][2].trim();
                                // 1-4. 사후관리코드
                                // 1-5. 사후관리
                                String heaFollowUpCd = checkupResultSheet[row][4].trim();
                                // 1-6. 종합의견
                                String overallOpinion = checkupResultSheet[row][6].trim();
                                // 1-7. 관련유해인자(특수검진)
                                String refHazardDesc = "";
                                // 특수검진인 경우 관련유해인자 항목 설정
                                if (checkupPlan.getHeaCheckupClassCd().equals(ConstVal.CODE_MASTER_HEA_CHECKUP_CLASS_SPECIAL)) {
                                    refHazardDesc = checkupResultSheet[row][7].trim();
                                }

                                int count = 0;
                                String failMessage = "";
                                // 사원번호 체크
                                boolean checkUser = checkupUsers.stream().filter(o -> o.getUserId().equals(userId)).findFirst().isPresent();
                                // 검진일자 체크
                                boolean checkDate = this.isValidDate(heaCheckedYmd);
                                // 업무수행적합여부코드 체크
                                boolean checkWorkable = heaWorkables.stream().filter(o -> o.getCode().equals(heaWorkableCd)).findFirst().isPresent();
                                // 사후관리코드 체크
                                boolean checkFollowUp = heaFollowUps.stream().filter(o -> o.getCode().equals(heaFollowUpCd)).findFirst().isPresent();

                                checkupResultTotalCount++;

                                if (checkUser && checkDate && checkWorkable && checkFollowUp) {
                                    CheckupResult checkupResult = new CheckupResult();
                                    checkupResult.setCreateUserId(createUserId);
                                    checkupResult.setHeaCheckupPlanNo(heaCheckupPlanNo);
                                    checkupResult.setHeaCheckupOrgNo(heaCheckupOrgNo);
                                    checkupResult.setUpdateUserId(createUserId);
                                    // 1-0. 검진일자
                                    checkupResult.setHeaCheckedYmd(heaCheckedYmd);
                                    // 1-1. 사원번호
                                    checkupResult.setUserId(userId);
                                    // 1-2. 업무수행적합여부코드
                                    // 1-3. 업무수행적합여부
                                    checkupResult.setHeaWorkableCd(heaWorkableCd);
                                    // 1-4. 사후관리코드
                                    // 1-5. 사후관리
                                    checkupResult.setHeaFollowUpCd(heaFollowUpCd);
                                    // 1-6. 종합의견
                                    checkupResult.setOverallOpinion(overallOpinion);
                                    // 1-7. 관련유해인자(특수검진)
                                    // 특수검진인 경우 관련유해인자 항목 설정
                                    if (checkupPlan.getHeaCheckupClassCd().equals(ConstVal.CODE_MASTER_HEA_CHECKUP_CLASS_SPECIAL)) {
                                        checkupResult.setRefHazardDesc(refHazardDesc);
                                    }

                                    // 검진소견결과 저장
                                    count = this.checkupResultUploadMapper.uploadExcelCheckupResult(checkupResult);
                                    if (count == 0) {
                                        failMessage = "업로드 중 오류가 발생했습니다. 관리자에게 문의하세요.";
                                    }
                                    checkupResultSuccessCount += count;
                                }

                                if (count == 0 && !userId.equals("") && !heaCheckedYmd.equals("") && !overallOpinion.equals("")) {
                                    if (!checkUser) {
                                        failMessage = this.appendString(failMessage, "건강검진 대상자로 지정되지 않은 임직원 입니다.");
                                    }
                                    if (!checkDate) {
                                        failMessage = this.appendString(failMessage, "건강검진일의 형식이 잘못 됐습니다.(ex: 20190101)");
                                    }
                                    if (!checkWorkable) {
                                        failMessage = this.appendString(failMessage, "업무수행적합 코드가 잘못되었습니다.(메세지 확인 필요)");
                                    }
                                    if (!checkFollowUp) {
                                        failMessage = this.appendString(failMessage, "사후관리 코드가 잘못되었습니다.(메세지 확인 필요)");
                                    }

                                    checkupResultFailCount++;
                                    Map<String, Object> errorInfo = new HashMap<String, Object>();
                                    errorInfo.put("classNm", checkupResultSheetName);
                                    errorInfo.put("failRow", row);
                                    errorInfo.put("failMessage", failMessage);
                                    errorInfo.put("remark", "");

                                    errorInfos.add(errorInfo);
                                }
                            } else {
                                continue;
                            }
                        }
                        checkupResultMap.put("classNm", checkupResultSheetName);
                        checkupResultMap.put("totalCount", checkupResultTotalCount);
                        checkupResultMap.put("successCount", checkupResultSuccessCount);
                        checkupResultMap.put("failCount", checkupResultFailCount);

                        // 검진질환 및 판정결과 (sheet 2)
                        for (int row = 1; row < checkupResultDiagSheet.length; row++) {
                            rowCheck = new StringBuilder();
                            for (int i = 0; i < checkupResultDiagSheet[row].length; i++) {
                                rowCheck.append(checkupResultDiagSheet[row][i].trim());
                            }

                            if (checkupResultDiagSheet[row][1].trim().equals("")) {
                                continue;
                            }

                            if (rowCheck.length() > 0) {

                                // 2-0. 사원번호
                                String userId = checkupResultDiagSheet[row][0].trim();
                                // 2-1. 판정코드
                                // 2-2. 판정
                                String heaDiagnoseCd = checkupResultDiagSheet[row][1].trim();
                                // 2-3. 질환코드
                                // 2-4. 질환
                                String heaDiseaseCd = checkupResultDiagSheet[row][3].trim();
                                // 2-5. 유해인자코드(특수검진)
                                // 2-6. 유해인자(특수검진)
                                String hazardCd = "";
                                // 특수검진인 경우 유해인자 항목 설정
                                if (checkupPlan.getHeaCheckupClassCd().equals(ConstVal.CODE_MASTER_HEA_CHECKUP_CLASS_SPECIAL)) {
                                    hazardCd = checkupResultDiagSheet[row][5].trim();
                                }

                                int count = 0;
                                String failMessage = "";
                                boolean checkUser = checkupUsers.stream().filter(o -> o.getUserId().equals(userId)).findFirst().isPresent();
                                boolean checkDiagCd = diagnoses.stream().filter(o -> o.getCodeNm().equals(heaDiagnoseCd)).findFirst().isPresent();
                                boolean checkDiseCd = diseases.stream().filter(o -> o.getHeaDiseaseCd().equals(heaDiseaseCd)).findFirst().isPresent();

                                checkupResultDiagTotalCount++;
                                if (checkUser && checkDiagCd && checkDiseCd) {
                                    CheckupResultDiag checkupResultDiag = new CheckupResultDiag();
                                    checkupResultDiag.setCreateUserId(createUserId);
                                    checkupResultDiag.setUpdateUserId(createUserId);
                                    checkupResultDiag.setHeaCheckupPlanNo(heaCheckupPlanNo);
                                    // 2-0. 사원번호
                                    checkupResultDiag.setUserId(userId);
                                    // 2-1. 판정코드
                                    // 2-2. 판정
                                    checkupResultDiag.setHeaDiseaseCd(heaDiseaseCd);
                                    // 2-3. 질환코드
                                    // 2-4. 질환
                                    checkupResultDiag.setHeaDiagnoseCd(heaDiagnoseCd);
                                    // 2-5. 유해인자코드(특수검진)
                                    // 2-6. 유해인자(특수검진)
                                    // 특수검진인 경우 유해인자 항목 설정
                                    if (checkupPlan.getHeaCheckupClassCd().equals(ConstVal.CODE_MASTER_HEA_CHECKUP_CLASS_SPECIAL)) {
                                        checkupResultDiag.setHazardCd(hazardCd);
                                    }

                                    count = this.checkupResultUploadMapper.uploadExcelCheckupResultDiag(checkupResultDiag);
                                    if (count == 0) {
                                        failMessage = "업로드 중 오류가 발생했습니다. 관리자에게 문의하세요.";
                                    }
                                    checkupResultDiagSuccessCount += count;
                                }

                                if (count == 0 && !userId.equals("") && !heaDiagnoseCd.equals("") && !heaDiseaseCd.equals("")) {
                                    if (!checkUser) {
                                        failMessage = this.appendString(failMessage, "건강검진 대상자로 지정되지 않은 임직원 입니다.");
                                    }
                                    if (!checkDiagCd) {
                                        failMessage = this.appendString(failMessage, "등록된 판정코드가 없습니다.");
                                    }
                                    if (!checkDiseCd) {
                                        failMessage = this.appendString(failMessage, "등록된 질환코드가 없습니다.");
                                    }

                                    checkupResultDiagFailCount++;
                                    Map<String, Object> errorInfo = new HashMap<String, Object>();
                                    errorInfo.put("classNm", checkupResultDiagSheetName);
                                    errorInfo.put("failRow", row);
                                    errorInfo.put("failMessage", failMessage);
                                    errorInfo.put("remark", "");

                                    errorInfos.add(errorInfo);
                                }
                            } else {
                                continue;
                            }
                        }
                        checkupResultDiagMap.put("classNm", checkupResultDiagSheetName);
                        checkupResultDiagMap.put("totalCount", checkupResultDiagTotalCount);
                        checkupResultDiagMap.put("successCount", checkupResultDiagSuccessCount);
                        checkupResultDiagMap.put("failCount", checkupResultDiagFailCount);

                        // 검진항목별 결과 (sheet 3)
                        for (int row = 1; row < testItemResultSheet.length; row++) {
                            rowCheck = new StringBuilder();
                            for (int i = 0; i < testItemResultSheet[row].length; i++) {
                                rowCheck.append(testItemResultSheet[row][i].trim());
                            }

                            if (testItemResultSheet[row][1].trim().equals("")) {
                                continue;
                            }

                            if (rowCheck.length() > 0) {
                                // 3-0. 사원번호
                                String userId = testItemResultSheet[row][0].trim();
                                // 3-1. 검사항목코드
                                // 3-2. 검사항목
                                String heaTestItemCd = testItemResultSheet[row][1].trim();
                                // 3-3. 결과값
                                String charResult = testItemResultSheet[row][3].trim();
                                float numResult = 0.0f;

                                int count = 0;
                                String failMessage = "";
                                boolean checkUser = checkupUsers.stream().filter(o -> o.getUserId().equals(userId)).findFirst().isPresent();
                                boolean checkItem = true;
                                boolean checkType = true;

                                if (checkUser) {
                                    checkItem = heaTestItems.stream().filter(o -> o.getHeaTestItemCd().equals(heaTestItemCd)).findFirst().isPresent();

                                    if (checkItem) {
                                        TestItem item = heaTestItems.stream().filter(o -> o.getHeaTestItemCd().equals(heaTestItemCd)).findFirst().get();

                                        if (item.getHeaResultTypeCd().equals(ConstVal.CODE_MASTER_HEA_RESULT_TYPE_NUNBER)) {
                                            checkType = this.isValidNumber(charResult);

                                            if (checkType) {
                                                numResult = Float.parseFloat(charResult);
                                            }
                                        }
                                    }
                                }

                                testItemResultTotalCount++;

                                if (checkUser && checkItem && checkType) {
                                    TestItemResult testItemResult = new TestItemResult();
                                    testItemResult.setCreateUserId(createUserId);
                                    testItemResult.setUpdateUserId(createUserId);
                                    testItemResult.setHeaCheckupPlanNo(heaCheckupPlanNo);
                                    // 3-0. 사원번호
                                    testItemResult.setUserId(userId);
                                    // 3-1. 검사항목코드
                                    // 3-2. 검사항목
                                    testItemResult.setHeaTestItemCd(heaTestItemCd);
                                    // 3-3. 결과값
                                    testItemResult.setCharResult(charResult);
                                    testItemResult.setNumResult(numResult);

                                    count = this.checkupResultUploadMapper.uploadExcelTestItemResult(testItemResult);
                                    if (count == 0) {
                                        failMessage = "업로드 중 오류가 발생했습니다. 관리자에게 문의하세요.";
                                    }
                                    testItemResultSuccessCount += count;
                                }
                                if (count == 0 && !userId.equals("") && !heaTestItemCd.equals("") && !charResult.equals("")) {
                                    if (!checkUser) {
                                        failMessage = this.appendString(failMessage, "건강검진 대상자로 지정되지 않은 임직원 입니다.");
                                    }
                                    if (!checkItem) {
                                        failMessage = this.appendString(failMessage, "해당년도 건강검진 수행기간별 검사항목이 등록되지 않았습니다.");
                                    }
                                    if (!checkType) {
                                        failMessage = this.appendString(failMessage, "검사항목별 결과타입이 다르거나 결과값이 없습니다.");
                                    }

                                    testItemResultFailCount++;

                                    Map<String, Object> errorInfo = new HashMap<String, Object>();
                                    errorInfo.put("classNm", testItemResultSheetName);
                                    errorInfo.put("failRow", row);
                                    errorInfo.put("failMessage", failMessage);
                                    errorInfo.put("remark", "");

                                    errorInfos.add(errorInfo);
                                }
                            } else {
                                continue;
                            }
                        }
                        testItemResultMap.put("classNm", testItemResultSheetName);
                        testItemResultMap.put("totalCount", testItemResultTotalCount);
                        testItemResultMap.put("successCount", testItemResultSuccessCount);
                        testItemResultMap.put("failCount", testItemResultFailCount);

                        // 업로드결과
                        uploadResults.add(checkupResultMap);
                        uploadResults.add(checkupResultDiagMap);
                        uploadResults.add(testItemResultMap);

                        map.put("success", true);
                        map.put("uploadResult", uploadResults);
                        map.put("errorInfo", errorInfos);
                    }
                } else {
                    map.put("message", "업로드양식이 오류: 업로드 시트 갯수가 다릅니다.");
                }
            } else {
                map.put("message", "업로드 오류: 파일을 읽을 수 없습니다.");
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

    /**
     * 수치형식 확인
     *
     * @param numberString
     *            확인문자
     * @return 수치형식여부
     */
    private boolean isValidNumber(String numberString) {
        try {
            float f = Float.parseFloat(numberString);
            return true;
        } catch (Exception e) {
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
        // if (source!=null && !source.equals(""))
        // {
        // source += "<br/>■" + append;
        // }
        // else
        // {
        // source = "■" + append;
        // }
        source += "■" + append;

        return source;
    }

    /**
     *
     * 과거판정데이터 > 검진 결과 이력 목록 조회
     *
     * @param userId
     *            건강검진대상자
     * @param heaCheckupPlanNo
     *            검진계획
     * @return 건강검진결과이력
     * @throws Exception
     */
    public List<CheckupResult> getCheckupPastResults(String userId, int heaCheckupPlanNo) throws Exception {
        return this.checkupResultMapper.getCheckupPastResults(userId, heaCheckupPlanNo);
    }
    
    /**
     * 검진대상자 엑셀 업로드
     * @param heaCheckupPlanNo
     * @param createUserId
     * @param files
     * @return
     * @throws Exception
     */
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public Map<String, Object> checkupUserUploadExcel(int heaCheckupPlanNo, String createUserId, MultipartFile[] files) throws Exception {
    	Map<String, Object> map = new HashMap<String, Object>();
        map.put("success", true);
        map.put("message", "");
        
        List<Map<String, Object>> failList = new ArrayList<Map<String, Object>>(); 
        int totalCount = 0;
        int successCount = 0;
        int failCount = 0;
        try {
        	 InputStream stream = files[0].getInputStream();
             File tempFile = File.createTempFile(String.valueOf(stream.hashCode()), ".xlsx");
             tempFile.deleteOnExit();
             FileUtils.copyInputStreamToFile(stream, tempFile);
             
             ExcelReader reader = new ExcelReader();
             List<String[][]> sheets = reader.read(tempFile);
             
             if (sheets != null && sheets.size() > 0) {
                 String[][] sheet = sheets.get(0);
                 
                 for (int row = 1; row < sheet.length; row++) {
                	 Map<String, Object> failmap = new HashMap<String, Object>();
                	 totalCount++;
                	 
                	 // 비어있는행 패스
                	 if(sheet[row][0] == null && sheet[row][1] == null && sheet[row][2] == null) {
                		 continue;
                	 }
                	 
                	 // 사번 없음
                	 if(sheet[row][0] == null) {
                		 failmap.put("failRowNo", row);
                		 failmap.put("message", "사원번호 없음");
                		 failCount++;
                		 failList.add(failmap);
                		 continue;
                	 } 
                	 // 외진여부 없음
                	 if(sheet[row][1] == null) {
                		 failmap.put("failRowNo", row);
                		 failmap.put("message", "외진여부 없음");
                		 failCount++;
                		 failList.add(failmap);
                		 continue;
                	 }
                	 
                	 CheckupUser checkupUser = new CheckupUser();
                	 
                	 int exists = checkupResultMapper.getExistsUser(sheet[row][0]);
                	 
                	 // 존재하지 않는 사원번호
                	 if(exists != 1) {
                		 failmap.put("failRowNo", row);
                		 failmap.put("message", "사원번호 존재하지않음");
                		 failCount++;
                		 failList.add(failmap);
                		 continue;
                	 }
                	 
                	 // 사원번호
                	 checkupUser.setUserId(sheet[row][0]);
                	 // 외진여부
                	 checkupUser.setOutCheckupYn(sheet[row][1]);
                	 // 비고
                	 checkupUser.setRemark(sheet[row][2]);
                	 // 등록자ID
                	 checkupUser.setCreateUserId(createUserId);
                	 // 검진계획 번호
                	 checkupUser.setHeaCheckupPlanNo(heaCheckupPlanNo);
                	 
                	 // 등록
                	 int result = checkupResultMapper.excelUploadCheckupUser(checkupUser);
                	 
                	 successCount++;
                 }
                 map.put("failResult", failList);// 실패정보
                 map.put("totalCount", totalCount);// 총 로우수
                 map.put("successCount", successCount);// 성공한 로우수
                 map.put("failCount", failCount); // 실패한 로우수 
             }
             
		} catch (Exception e) {
			map.put("success", false);
            map.put("message", "업로드 도중 에러발생");
		}
    	return map;
    }
    
    /**
     * 검진현황 목록
     * @param year
     * @param plantCd
     * @param deptCd
     * @param heaCheckupClassCd
     * @param defaultParam
     * @return
     * @throws Exception
     */
    public List<CheckupSituation> getCheckupSituation(String year, String plantCd, String deptCd, String heaCheckupClassCd, DefaultParam defaultParam) throws Exception {
    	return checkupResultMapper.getCheckupSituation(year, plantCd, deptCd, heaCheckupClassCd, defaultParam);
    }
    
    /**
     * 판정대상자 목록
     * @param year
     * @param plantCd
     * @param heaDiagnoseCd
     * @return
     * @throws Exception
     */
    public List<CheckupSituationDetail> getCheckupSituationDetail(String year, String plantCd, String heaDiagnoseCd, DefaultParam defaultParam) throws Exception {
    	return checkupResultMapper.getCheckupSituationDetail(year, plantCd, heaDiagnoseCd, defaultParam);
    }
}
