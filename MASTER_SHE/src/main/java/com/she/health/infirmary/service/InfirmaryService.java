package com.she.health.infirmary.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.usermodel.Font;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.she.common.mapper.AttachFileMapper;
import com.she.common.model.AttachFile;
import com.she.common.model.DefaultParam;
import com.she.health.infirmary.mapper.InfirmaryMapper;
import com.she.health.model.CheckupResult;
import com.she.health.model.Consult;
import com.she.health.model.DrugSuply;
import com.she.health.model.HearingMgr;
import com.she.health.model.InfirmaryUsage;
import com.she.health.model.Suspect;
import com.she.health.model.SuspectHist;
import com.she.manage.model.CodeMaster;
import com.she.manage.service.CodeMasterService;
import com.she.utils.ConstVal;
import com.she.utils.ExcelReader;
import com.she.utils.POIUtil;

/**
 * 건강관리실 기능 정의
 *
 */
@Service
public class InfirmaryService {
    @Autowired
    private InfirmaryMapper infirmaryMapper;

    @Autowired
    private AttachFileMapper attachFileMapper;

    @Autowired
    private CodeMasterService codeMasterService;

    /**
     * 건강관리실 방문이력 조회
     *
     * @param plantCd
     *            사업장코드
     * @param counselTypeCd
     *            상담구분코드
     * @param userNm
     *            사용자명
     * @param deptCd
     *            부서코드
     * @return 건강관리실 방문이력 목록
     * @throws Exception
     */
    public List<InfirmaryUsage> getInfirmaryUsageHistorys(String plantCd, String counselTypeCd, String userNm, String deptCd, String startDt, String endDt) throws Exception {
        return infirmaryMapper.getInfirmaryUsageHistorys(plantCd, counselTypeCd, userNm, deptCd, startDt, endDt);
    }

    /**
     * 건강검진이력 조회
     *
     * @param userNm
     *            사용자명
     * @param deptCd
     *            부서코드
     * @return 건강검진이력목록
     * @throws Exception
     */
    public List<CheckupResult> getCheckupHistorys(String userNm, String deptCd, String startYmd, String endYmd, String heaCheckupClassCd, String plantCd) throws Exception {
        return infirmaryMapper.getCheckupHistorys(userNm, deptCd, startYmd, endYmd, heaCheckupClassCd, plantCd);
    }

    /**
     * 관리대상 유소견자 조회
     *
     * @param plantCd
     *            사업장코드
     * @param deptCd
     *            부서코드
     * @param userId
     *            사용자ID
     * @param suspectYn
     *            유소견자 지정여부
     * @param startYear
     *            검색년도(시작)
     * @param endYear
     *            검색년도(종료)
     * @return 관리대상 유소견자 목록
     * @throws Exception
     */
    public List<Suspect> getSuspectUsers(String plantCd, String deptCd, String userId, String suspectYn, String startYear, String endYear, String deptSubYn) throws Exception {
        return infirmaryMapper.getSuspectUsers(plantCd, deptCd, userId, suspectYn, startYear, endYear, deptSubYn);
    }

    /**
     * 관리대상 유소견자 지정팝업 조회(검진결과 목록)
     *
     * @param heaCheckupPlanNo
     *            검진계획번호
     * @param deptCd
     *            부서코드
     * @param userNm
     *            사용자명
     * @param suspectYn
     *            유소견자여부
     * @param heaCheckedYear
     *            검진년도
     * @param plantCd
     *            사업장
     * @return 관리대상 유소견자 지정팝업 목록(검진결과 목록)
     * @throws Exception
     */
    public List<Suspect> getSuspectAppointUsers(int heaCheckupPlanNo, String deptCd, String userNm, String suspectYn, String heaCheckedYear, String plantCd) throws Exception {
        return infirmaryMapper.getSuspectAppointUsers(heaCheckupPlanNo, deptCd, userNm, suspectYn, heaCheckedYear, plantCd);
    }

    /**
     * 관리대상 유소견자 해제팝업 조회
     *
     * @param heaCheckupPlanNo
     *            검진계획번호
     * @param deptCd
     *            부서코드
     * @param userNm
     *            사용자명
     * @param heaCheckedYear
     *            검진년도
     * @param plantCd
     *            사업장코드
     * @return 관리대상 유소견자 해제팝업 목록
     * @throws Exception
     */
    public List<Suspect> getSuspectReleaseUsers(int heaCheckupPlanNo, String deptCd, String userNm, String heaCheckedYear, String plantCd) throws Exception {
        return infirmaryMapper.getSuspectReleaseUsers(heaCheckupPlanNo, deptCd, userNm, heaCheckedYear, plantCd);
    }

    /**
     * 관리대상 유소견자팝업 조회(검진결과 목록)
     *
     * @param plantCd
     *            사업장코드
     * @param userNm
     *            등록자명
     * @param startDt
     *            기간(시작)
     * @param endDt
     *            기간(종료)
     * @return 관리대상 유소견자팝업 목록(결재건별 지정, 해제 인원)
     * @throws Exception
     */
    public List<Suspect> getSuspectUsersOfAll(String plantCd, String userNm, String startDt, String endDt) throws Exception {
        return infirmaryMapper.getSuspectUsersOfAll(plantCd, userNm, startDt, endDt);
    }

    /**
     * 관리대상 유소견자 지정/해제 목록
     *
     * @param suspectReqNo
     *            요청번호
     * @return 유소견자 지정/해제 목록
     * @throws Exception
     */
    public Suspect getSuspectUsersOfTarget(int suspectReqNo) throws Exception {
        // return infirmaryMapper.getSuspectUsers(suspectReqNo);
        Suspect results = infirmaryMapper.getSuspectRequest(suspectReqNo);

        results.setActUserItems(infirmaryMapper.getSuspectUsersOfActTarget(suspectReqNo));
        results.setRelUserItems(infirmaryMapper.getSuspectUsersOfRelTarget(suspectReqNo));

        return results;
    }

    /**
     * 관리대상 유소견자 지정
     *
     * @param suspect
     * @return Integer(수정행수)
     * @throws Exception
     *             예외
     */
    @Transactional
    public int requestSuspectUsers(Suspect suspect) throws Exception {

        // 유소견자 지정 및 해제 요청 생성
        infirmaryMapper.createSuspectRequest(suspect);

        List<Suspect> actUserItems = suspect.getActUserItems();
        List<Suspect> relUserItems = suspect.getRelUserItems();

        // 유소견자 지정 목록 저장
        for (int i = 0; i < actUserItems.size(); i++) {
            actUserItems.get(i).setSuspectReqNo(suspect.getSuspectReqNo());
            infirmaryMapper.createSuspectActUser(actUserItems.get(i));
        }
        // 유소견자 해제 목록 저장
        for (int i = 0; i < relUserItems.size(); i++) {
            relUserItems.get(i).setSuspectReqNo(suspect.getSuspectReqNo());
            infirmaryMapper.createSuspectRelUser(relUserItems.get(i));
        }

        int suspectReqNo = suspect.getSuspectReqNo();

        return suspectReqNo;
    }

    /**
     * 관리대상 유소견자 수정
     *
     * @param List<CheckupResult>
     * @return Integer(수정행수)
     * @throws Exception
     *             예외
     */
    @Transactional
    public int updateSuspectUsers(Suspect suspect) throws Exception {
        int results = 0;

        int suspectReqNo = suspect.getSuspectReqNo();

        results += infirmaryMapper.updateSuspectRequest(suspect);

        results += infirmaryMapper.deleteSuspectActUser(suspectReqNo);
        results += infirmaryMapper.deleteSuspectRelUser(suspectReqNo);

        List<Suspect> actUserItems = suspect.getActUserItems();
        List<Suspect> relUserItems = suspect.getRelUserItems();

        // 유소견자 지정 목록 저장
        for (int i = 0; i < actUserItems.size(); i++) {
            actUserItems.get(i).setSuspectReqNo(suspect.getSuspectReqNo());
            results += infirmaryMapper.createSuspectActUser(actUserItems.get(i));
        }

        /// 해제자는 아직 처리가 안되어있음. 지정자는 건강검진 계획번호를 목록에서 가지고 있지만 해제자는 유소견자이력번호를
        /// 가지고있지않음.
        /// 조회에서 먼저 유소견자이력번호를 가지고 있도록 선처리 필요함.
        // 유소견자 해제 목록 저장
        for (int i = 0; i < relUserItems.size(); i++) {
            relUserItems.get(i).setSuspectReqNo(suspect.getSuspectReqNo());
            results += infirmaryMapper.createSuspectRelUser(relUserItems.get(i));
        }

        return results;
    }

    /**
     * 관리대상 유소견자 지정
     *
     * @param List<CheckupResult>
     * @return Integer(수정행수)
     * @throws Exception
     *             예외
     */
    @Transactional
    public Integer createSuspectUser(List<CheckupResult> checkupResults) throws Exception {
        int resultNo = 0;

        for (CheckupResult checkupResult : checkupResults) {
            SuspectHist suspectHist = new SuspectHist();
            suspectHist.setHeaCheckupPlanNo(checkupResult.getHeaCheckupPlanNo());
            suspectHist.setUserId(checkupResult.getUserId());
            suspectHist.setDeptCd(checkupResult.getDeptCd());
            suspectHist.setSuspectYn("Y");
            suspectHist.setCreateUserId(checkupResult.getCreateUserId());

            String userId = suspectHist.getUserId();

            Suspect suspect = new Suspect();
            suspect.setUserId(userId);
            suspect.setSuspectYn("Y");

            // 유소견자 이력에 create
            resultNo += infirmaryMapper.createSuspectUserHist(suspectHist);
            // 유소견자 상세조회
            Suspect suspectTemp = infirmaryMapper.getSuspectUser(userId);
            // suspect가 없으면
            if (suspectTemp == null) {
                suspect.setCreateUserId(suspectHist.getCreateUserId());
                infirmaryMapper.createSuspectUser(suspect);
            } else if (suspectTemp.getSuspectYn().equals("N")) {
                suspect.setUpdateUserId(suspectHist.getCreateUserId());
                infirmaryMapper.updateSuspectUser(suspect);
            }

        }

        return resultNo;
    }

    /**
     * 유소견자 건강상담이력 조회
     *
     * @param userId
     *            사번
     * @param startYmd
     *            기간
     * @param endYmd
     *            기간
     * @param plantCd
     *            사업장코드
     * @param counselTypeCd
     *            상담구분코드
     * @param userNm
     *            사원명
     * @param deptCd
     *            부서명
     *
     * @return 건강상담이력 목록
     * @throws Exception
     *             예외
     */
    public List<Consult> getConsults(String userId, String startYmd, String endYmd, String plantCd, String counselTypeCd, String userNm, String deptCd) throws Exception {
        return infirmaryMapper.getConsults(userId, startYmd, endYmd, plantCd, counselTypeCd, userNm, deptCd);
    }

    /**
     * 유소견자 건강상담이력 상세 조회
     *
     * @param userId
     *            사번
     * @return 유소견자 건강상담이력 상세 조회
     * @throws Exception
     *             예외
     */
    public Consult getConsult(int heaConsultNo) throws Exception {
        Consult consult = infirmaryMapper.getConsult(heaConsultNo);
        if (consult != null) {
            consult.setDrugSuplies(infirmaryMapper.getDrugSuplies(heaConsultNo));
        }
        return consult;
    }

    /**
     * 유소견자 건강상담이력 신규 등록
     *
     * @param consult
     *            (유소견자)건강 상담
     * @return heaConsultNo 건강상담번호
     * @throws Exception
     *             예외
     */
    @Transactional
    public int createConsult(Consult consult) throws Exception {
        this.infirmaryMapper.createConsult(consult);

        if (CollectionUtils.isNotEmpty(consult.getDrugSuplies())) {
            for (DrugSuply drugSuply : consult.getDrugSuplies()) {
                drugSuply.setHeaConsultNo(consult.getHeaConsultNo());
                drugSuply.setCreateUserId(consult.getCreateUserId());
                this.infirmaryMapper.createDrugSuply(drugSuply);
            }
        }
        return consult.getHeaConsultNo();
    }

    /**
     * 유소견자 건강상담이력 수정
     *
     * @param consult
     *            (유소견자)건강 상담
     * @return 수정행수
     * @throws Exception
     *             예외
     */
    public int updateConsult(Consult consult) throws Exception {
        infirmaryMapper.updateConsult(consult);

        if (CollectionUtils.isNotEmpty(consult.getDrugSuplies())) {
            for (DrugSuply drugSuply : consult.getDrugSuplies()) {
                if (drugSuply.getHeaConsultNo() > 0) {
                    drugSuply.setUpdateUserId(consult.getUpdateUserId());
                    this.infirmaryMapper.updateDrugSuply(drugSuply);
                } else {
                    drugSuply.setHeaConsultNo(consult.getHeaConsultNo());
                    drugSuply.setCreateUserId(consult.getCreateUserId());
                    this.infirmaryMapper.createDrugSuply(drugSuply);
                }
            }
        }

        if (CollectionUtils.isNotEmpty(consult.getDeleteDrugSuplies())) {
            for (DrugSuply drugSuply : consult.getDeleteDrugSuplies()) {
                this.infirmaryMapper.deleteDrugSuply(consult.getHeaConsultNo(), drugSuply.getHeaDrugNo());
            }
        }
        return consult.getHeaConsultNo();
    }

    /**
     * 유소견자 건강상담 이력 삭제
     *
     * @param List<Consult>
     * @return 삭제행수
     * @throws Exception
     *             예외
     */
    @Transactional
    public int deleteConsult(List<Consult> consults) throws Exception {
        int resultNo = 0;
        for (Consult consult : consults) {
            resultNo += this.infirmaryMapper.deleteDrugSuply(consult.getHeaConsultNo(), 0);
            resultNo += infirmaryMapper.deleteConsult(consult);
        }
        return resultNo;
    }

    /**
     * 청력관리대상자 조회
     *
     * @param plantCd
     *            사업장코드
     * @param userNm
     *            성명
     * @param deptCd
     *            부서코드
     * @param startDt
     *            검사 시작일
     * @param endDt
     *            검사 종료일
     *
     * @return 청력관리대상자 목록
     * @throws Exception
     *             예외
     */
    public List<HearingMgr> getHearingMgrs(String plantCd, String userNm, String deptCd, String startDt, String endDt, String deptSubYn) {
        return infirmaryMapper.getHearingMgrs(plantCd, userNm, deptCd, startDt, endDt, deptSubYn);
    }

    /**
     * 청력관리대상자 엑셀 다운로드
     *
     * @param plantCd
     * @param userNm
     * @param deptCd
     * @param startDt
     * @param endDt
     * @return
     */
    public String getHearingMgrsExcel(String plantCd, String userNm, String deptCd, String startDt, String endDt) throws Exception {
        POIUtil excel = new POIUtil("청력관리대상자 엑셀 다운로드 ");
        excel.setOffset(0, 0);

        List<LinkedHashMap<String, Object>> excelDataList = infirmaryMapper.getHearingMgrsForExcel(plantCd, userNm, deptCd, startDt, endDt);

        excel.addRow("C0BEC0", Font.BOLDWEIGHT_NORMAL, Arrays.asList("사업장", "검사일자", "부서", "성명", "사번", "좌 500", "좌 1000", "좌 2000", "좌 4000", "좌청력 3분법", "좌청력 6분법", "우 500", "우 1000", "우 2000", "우 4000", "우청력 3분법", "우청력 6분법", "판정", "관리등급", "고위험군"));

        if (CollectionUtils.isNotEmpty(excelDataList)) {
            for (LinkedHashMap<String, Object> data : excelDataList) {
                List<Object> list = new ArrayList<Object>(data.values());
                excel.addRow(list);
            }
        }

        ByteArrayInputStream in = null;
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            excel.write(out);
            in = new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw e;
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                throw e;
            }
        }

        byte[] encoded = Base64.encodeBase64(IOUtils.toByteArray(in));
        String encodedString = new String(encoded);
        return encodedString;
    }

    /**
     * 건강검진결과 엑셀업로드
     *
     * @param heaCheckupPlanNo
     *            건강검진계획번호
     * @param file
     *            업로드엑셀파일
     * @return 업로드결과
     * @throws Exception
     */
    public Map<String, Object> uploadExcelHearingMgrs(String taskClassNm, String taskKey, String uploadUserId) throws Exception {
        // 임시로 저장된 건강검진결과 엑셀 파일의 정보를 읽는다.
        List<AttachFile> uploadedHearingMgrs = attachFileMapper.getUploadFiles(taskClassNm, taskKey);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("success", false);
        map.put("message", "");
        map.put("uploadResult", null);
        map.put("errorInfo", null);

        int hearingMgrTotalCount = 0;
        int hearingMgrSuccessCount = 0;
        int hearingMgrFailCount = 0;

        File excel = new File(uploadedHearingMgrs.get(0).getFilePath());
        ExcelReader reader = new ExcelReader();
        List<String[][]> sheets = reader.read(excel);

        if (sheets != null) {
            String hearingMgrSheetName = "검진소견결과";

            String[][] hearingMgrSheet = sheets.get(0);

            // String[] hearingMgrSheetHeader = new String[] { "검사일자", "부서코드",
            // "사번", "좌500", "좌1000", "좌2000", "좌4000",
            // "좌3분법", "좌6분법", "우500", "우1000", "우2000", "우4000", "우3분법",
            // "우6분법", "판정", "관리등급", "고위험군" };
            String[] hearingMgrSheetHeader = new String[] { "검사일자", "사번", "좌500", "좌1000", "좌2000", "좌4000", "좌3분법", "좌6분법", "우500", "우1000", "우2000", "우4000", "우3분법", "우6분법", "판정", "관리등급", "고위험군" };

            // 헤더 양식확인
            if (hearingMgrSheet.length > 0 && !reader.excelHeaderCheck(hearingMgrSheet[0], hearingMgrSheetHeader)) {
                map.put("message", "업로드양식이 오류: " + hearingMgrSheetName + " 시트의 헤더가 다릅니다.");
            } else {
                // 업로드양식 통과 후 데이터검사

                // 부서 조회
                // List<Dept> depts = this.deptService.getDepts(null, null,
                // null, null, "Y");
                // 검사결과 진단 판정코드 조회
                List<CodeMaster> heaDiagnoses = this.codeMasterService.getSelect(ConstVal.CODE_GROUP_HEA_DIAGNOSE_CD, "Y", new DefaultParam("kr"));

                List<Map<String, Object>> uploadResults = new ArrayList<Map<String, Object>>();
                List<Map<String, Object>> errorInfos = new ArrayList<Map<String, Object>>();

                Map<String, Object> hearingMgrMap = new HashMap<String, Object>();

                StringBuilder rowCheck;

                // 작업분류표
                for (int row = 1; row < hearingMgrSheet.length; row++) {

                    rowCheck = new StringBuilder();
                    for (int i = 0; i < hearingMgrSheet[row].length; i++) {
                        rowCheck.append(hearingMgrSheet[row][i].trim());
                    }

                    if (rowCheck.length() > 0) {
                        // 1-1. 검진일자
                        String chkDt = hearingMgrSheet[row][0].trim();
                        // 1-2. 부서코드
                        // String deptCd = hearingMgrSheet[row][1].trim();
                        // 1-3. 사번
                        String userId = hearingMgrSheet[row][1].trim();

                        // 좌 수치
                        float left500 = 0.0f;
                        float left1000 = 0.0f;
                        float left2000 = 0.0f;
                        float left4000 = 0.0f;
                        float left3Div = 0.0f;
                        float left6Div = 0.0f;
                        // 우 수치
                        float right500 = 0.0f;
                        float right1000 = 0.0f;
                        float right2000 = 0.0f;
                        float right4000 = 0.0f;
                        float right3Div = 0.0f;
                        float right6Div = 0.0f;

                        float[] numberArr = { left500, left1000, left2000, left4000, left3Div, left6Div, right500, right1000, right2000, right4000, right3Div, right6Div };

                        boolean checkValidNumber = false;
                        String inputVal = "";
                        // 숫자형 수치 체크
                        // for (int i = 3; i < 15; i++) {
                        for (int i = 2; i < 14; i++) {
                            inputVal = hearingMgrSheet[row][i].trim();

                            if (inputVal.equals("") || this.isValidNumber(inputVal)) {
                                // 숫자 또는 공백이 입력되어있는 경우
                                checkValidNumber = true;
                                // numberArr[i - 2] = inputVal != "" ? (int)
                                // Float.parseFloat(inputVal) : 0;
                                numberArr[i - 2] = inputVal != "" ? Float.parseFloat(inputVal) : 0.0f;

                            } else {
                                // 문자가 입력되어있는 경우
                                checkValidNumber = false;
                                break;
                            }
                        }

                        // 1-16. 판정
                        // String heaDiagnoseCd =
                        // hearingMgrSheet[row][15].trim();
                        String heaDiagnoseCd = hearingMgrSheet[row][14].trim();
                        // 1-17. 관리등급
                        // String mgrLvl = hearingMgrSheet[row][16].trim();
                        String mgrLvl = hearingMgrSheet[row][15].trim();
                        // 1-18. 고위헙군
                        // String highDgrGrpNm =
                        // hearingMgrSheet[row][17].trim();
                        String highDgrGrpNm = hearingMgrSheet[row][16].trim();

                        int count = 0;
                        String failMessage = "";
                        // 판정코드 체크
                        boolean checkHeaDiagnoseCd = heaDiagnoses.stream().filter(o -> o.getCode().equals(heaDiagnoseCd)).findFirst().isPresent();
                        // 부서코드 체크
                        // boolean checkDept = depts.stream().filter(o ->
                        // o.getDeptCd().equals(deptCd)).findFirst()
                        // .isPresent();
                        // 검사일자 체크
                        boolean checkChkDt = this.isValidDate(chkDt);

                        hearingMgrTotalCount++;
                        // if (checkHeaDiagnoseCd && checkDept &&
                        // checkValidNumber && checkChkDt) {
                        if (checkHeaDiagnoseCd && checkValidNumber && checkChkDt) {

                            HearingMgr hearingMgr = new HearingMgr();
                            // 1-1. 검사일자
                            hearingMgr.setChkDt(chkDt);
                            // 1-2. 부서코드
                            // hearingMgr.setDeptCd(deptCd);
                            // 1-3. 사번
                            hearingMgr.setUserId(userId);
                            // 1-4. 좌500
                            hearingMgr.setLeft500(numberArr[0]);
                            // 1-5. 좌1000
                            hearingMgr.setLeft1000(numberArr[1]);
                            // 1-6. 좌2000
                            hearingMgr.setLeft2000(numberArr[2]);
                            // 1-7. 좌4000
                            hearingMgr.setLeft4000(numberArr[3]);
                            // 1-8. 좌3분법
                            hearingMgr.setLeft3Div(numberArr[4]);
                            // 1-9. 좌6분법
                            hearingMgr.setLeft6Div(numberArr[5]);
                            // 1-10. 우500
                            hearingMgr.setRight500(numberArr[6]);
                            // 1-11. 우1000
                            hearingMgr.setRight1000(numberArr[7]);
                            // 1-12. 우2000
                            hearingMgr.setRight2000(numberArr[8]);
                            // 1-13. 우4000
                            hearingMgr.setRight4000(numberArr[9]);
                            // 1-14. 우3분법
                            hearingMgr.setRight3Div(numberArr[10]);
                            // 1-15. 우6분법
                            hearingMgr.setRight6Div(numberArr[11]);
                            // 1-16. 판정
                            hearingMgr.setHeaDiagnoseCd(heaDiagnoseCd);
                            // 1-17. 관리등급
                            hearingMgr.setMgrLvl(mgrLvl);
                            // 1-18. 고위험군
                            hearingMgr.setHighDgrGrpNm(highDgrGrpNm);
                            // 1-19. 등록자
                            hearingMgr.setCreateUserId(uploadUserId);
                            // 1-20. 수정자
                            hearingMgr.setUpdateUserId(uploadUserId);

                            count = this.infirmaryMapper.uploadExcelHearingMgr(hearingMgr);
                            if (count == 0) {
                                failMessage = "업로드 중 오류가 발생했습니다. 관리자에게 문의하세요.";
                            }
                            hearingMgrSuccessCount += count;
                        }

                        if (count == 0 && !userId.equals("") && !chkDt.equals("")) {
                            if (!checkHeaDiagnoseCd) {
                                failMessage = this.appendString(failMessage, "판정코드가 잘못되었습니다.");
                            }
                            if (!checkValidNumber) {
                                failMessage = this.appendString(failMessage, "청력측정치가 잘못되었습니다.");
                            }
                            // if (!checkDept) {
                            // failMessage = this.appendString(failMessage,
                            // "시스템에 등록된 부서가 없습니다.");
                            // }
                            if (!checkChkDt) {
                                failMessage = this.appendString(failMessage, "검사일자의 형식이 잘못되었습니다.(ex: 20190101)");
                            }

                            hearingMgrFailCount++;
                            Map<String, Object> errorInfo = new HashMap<String, Object>();
                            errorInfo.put("classNm", hearingMgrSheetName);
                            errorInfo.put("failRow", row);
                            errorInfo.put("failMessage", failMessage);
                            errorInfo.put("remark", "");

                            errorInfos.add(errorInfo);
                        }
                    } else {
                        continue;
                    }
                }
                hearingMgrMap.put("classNm", hearingMgrSheetName);
                hearingMgrMap.put("totalCount", hearingMgrTotalCount);
                hearingMgrMap.put("successCount", hearingMgrSuccessCount);
                hearingMgrMap.put("failCount", hearingMgrFailCount);

                // 업로드결과
                uploadResults.add(hearingMgrMap);

                map.put("success", true);
                map.put("uploadResult", uploadResults);
                map.put("errorInfo", errorInfos);
            }

        } else {
            map.put("message", "업로드 오류: 파일을 읽을 수 없습니다.");
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
            // int i = Integer.parseInt(numberString);
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
     * 유소견자 지정 및 해제 진행단계 변경
     *
     * @param suspectReqNo
     *            요청번호
     * @param bizApprStepCd
     *            결재진행단계
     * @return 변경 행 수
     * @throws Exception
     */
    public int apprSuspectRequest(int suspectReqNo, String bizApprStepCd, int apprRqstNo) throws Exception {
        int resultNo = 0;

        if (ConstVal.COM_BIZ_APPR_STEP_REJECT.equals(bizApprStepCd)) {
            // 반려

        } else if (ConstVal.COM_BIZ_APPR_STEP_COMPLETE.equals(bizApprStepCd)) {
            // 결재완료
            Suspect suspect = this.getSuspectUsersOfTarget(suspectReqNo);

            if (suspect != null) {
                // 진행단계가 작성중인 경우
                if (suspect.getSuspStepCd().equals(ConstVal.SAF_PROCESS_STEP_CREATE)) {
                    // 결재가 완료되면 유소견자 지정및해제요청 테이블의 진행단계를 완료로 변경한다.
                    resultNo = infirmaryMapper.updateSuspStepCd(suspectReqNo, ConstVal.SAF_PROCESS_STEP_COMPLTE);

                    List<Suspect> actUserItems = suspect.getActUserItems();
                    List<Suspect> relUserItems = suspect.getRelUserItems();

                    // 유소견자 지정
                    for (int i = 0; i < actUserItems.size(); i++) {
                        infirmaryMapper.createSuspectUser(actUserItems.get(i));
                    }
                    // 유소견자 해제
                    for (int i = 0; i < relUserItems.size(); i++) {
                        infirmaryMapper.releaseSuspectUser(relUserItems.get(i));
                    }
                }
            }
        } else if (ConstVal.COM_BIZ_APPR_STEP_ING.equals(bizApprStepCd)) {
            resultNo = infirmaryMapper.updateApprRqstNo(suspectReqNo, apprRqstNo);
        }
        return resultNo;
    }

    /**
     * 청력관리대상자 저장
     *
     * @param hearingMgr
     *            청력관리대상자
     * @return 변경 행 수
     * @throws Exception
     */
    public int saveHearingMgr(HearingMgr hearingMgr) throws Exception {
        infirmaryMapper.updateHearingMgr(hearingMgr);
        return hearingMgr.getHeaHearingMgrListNo();
    }

    /**
     * 청력관리대상자 조회
     *
     * @param heaHearingMgrListNo
     *            청력관리대상자번호
     *
     * @return 청력관리대상자 목록
     * @throws Exception
     *             예외
     */
    public HearingMgr getHearingMgr(int heaHearingMgrListNo) throws Exception {
        return infirmaryMapper.getHearingMgr(heaHearingMgrListNo);
    }

    /**
     * 청력관리대상자 조회
     *
     * @param heaHearingMgrListNo
     *            청력관리대상자번호
     *
     * @return 청력관리대상자 목록
     * @throws Exception
     *             예외
     */
    public int deleteHearingMgr(int heaHearingMgrListNo) throws Exception {
        return infirmaryMapper.deleteHearingMgr(heaHearingMgrListNo);
    }
}
