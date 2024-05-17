package com.she.safety.education.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Font;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.she.common.mapper.AttachFileMapper;
import com.she.common.model.AttachFile;
import com.she.common.model.DefaultParam;
import com.she.impr.service.ImprService;
import com.she.manage.mapper.UserMapper;
import com.she.manage.model.User;
import com.she.safety.change.service.ChangeService;
import com.she.safety.education.mapper.EduMasterMapper;
import com.she.safety.model.EduDetailPerson;
import com.she.safety.model.EduMaster;
import com.she.safety.model.EduOutsideUser;
import com.she.utils.ConstVal;
import com.she.utils.ExcelReader;
import com.she.utils.POIUtil;
import com.she.utils.RequestMapper;

@Service
public class EduMasterService {
    @Autowired
    private EduMasterMapper eduMasterMapper;

    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private AttachFileMapper attachFileMapper;

    @Autowired
    private ImprService imprService;

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ChangeService changeService;

    /**
     * 교육마스터 상세조회
     *
     * @param safEduMstNo
     *            교육마스터번호
     * @return EduMaster 교육마스터
     * @throws Exception
     *             예외
     */
    public EduMaster getEduMaster(int safEduMstNo, DefaultParam defaultParam) throws Exception {
        return eduMasterMapper.getEduMaster(safEduMstNo, defaultParam);
    }

    public int getSafEduMstNo(int safEduCourseNo, DefaultParam defaultParam) throws Exception {
        return eduMasterMapper.getSafEduMstNo(safEduCourseNo, defaultParam);
    }

    /**
     * 교육계획등록시 기본교육자료 등록
     *
     * @param safEduMstNo
     * @param safEduCourseNo
     * @throws Exception
     */
    public void insertSafEduCourseMat(int safEduMstNo, int safEduCourseNo, DefaultParam defaultParam) throws Exception {
        eduMasterMapper.insertSafEduCourseMat(safEduCourseNo, safEduMstNo, defaultParam);
    }

    /**
     * 교육마스터 신규등록
     *
     * @param EduMaster
     *            교육마스터
     * @return safEduMstNo 교육마스터번호
     * @throws Exception
     *             예외
     */
    @Transactional
    public int createEduMaster(EduMaster eduMaster, DefaultParam defaultParam) throws Exception {
        this.eduMasterMapper.createEduMaster(eduMaster);
        eduMasterMapper.deleteEduMasterUser(eduMaster.getSafEduMstNo());
        for (int i = 0; i < eduMaster.getEduUserId().length; i++) {
            eduMasterMapper.createEduMasterUser(eduMaster.getSafEduMstNo(), eduMaster.getEduUserId()[i], eduMaster.getEduDeptCd()[i]);
        }

        for (int eduMatNo : Optional.ofNullable(eduMaster.getDataLsts()).orElse(new int[0])) {
            this.eduMasterMapper.createPlanData(String.valueOf(eduMaster.getSafEduMstNo()), String.valueOf(eduMatNo));
        }

        for (int eduQuestionLstNo : Optional.ofNullable(eduMaster.getQuestionLsts()).orElse(new int[0])) {
            this.eduMasterMapper.createPlanQuestion(String.valueOf(eduMaster.getSafEduMstNo()), String.valueOf(eduQuestionLstNo));
        }

        /**
         * 2022.02.21 LHJ MOC 번호 직접입력으로 변경
         */
        /*
         * // MOC 번호가 들어온 경우 ChangeRefWork changeRefWork = new ChangeRefWork();
         * changeRefWork.setSafChngNo(eduMaster.getSafChngNo());
         * changeRefWork.setRefTableId(String.valueOf(eduMaster.getSafEduMstNo()
         * )); changeRefWork.setRefTableNm("saf_edu_mst");
         * changeRefWork.setChngRefWorkCd(ConstVal.SAF_CHNG_REF_WORK_2);
         * changeRefWork.setCreateUserId(eduMaster.getCreateUserId());
         * changeService.taskChange(changeRefWork);
         */

        return eduMaster.getSafEduMstNo();
    }

    /**
     * 교육마스터 수정
     *
     * @param EduMaster
     *            교육마스터
     * @return 교육마스터
     * @throws Exception
     *             예외
     */
    @Transactional
    public int updateEduMaster(EduMaster eduMaster, DefaultParam defaultParam) throws Exception {
        if (eduMaster.getEduUserId() != null) {
            eduMasterMapper.deleteEduMasterUser(eduMaster.getSafEduMstNo());
            for (int i = 0; i < eduMaster.getEduUserId().length; i++) {
                eduMasterMapper.createEduMasterUser(eduMaster.getSafEduMstNo(), eduMaster.getEduUserId()[i], eduMaster.getEduDeptCd()[i]);
            }
        }

        if (eduMaster.getDataLsts() != null) {
            eduMasterMapper.deletePlanData(eduMaster.getSafEduMstNo());
            for (int eduMatNo : Optional.ofNullable(eduMaster.getDataLsts()).orElse(new int[0])) {
                this.eduMasterMapper.createPlanData(String.valueOf(eduMaster.getSafEduMstNo()), String.valueOf(eduMatNo));
            }
        }

        if (eduMaster.getQuestionLsts() != null) {
            eduMasterMapper.deletePlanQuestion(eduMaster.getSafEduMstNo());
            for (int eduQuestionLstNo : Optional.ofNullable(eduMaster.getQuestionLsts()).orElse(new int[0])) {
                this.eduMasterMapper.createPlanQuestion(String.valueOf(eduMaster.getSafEduMstNo()), String.valueOf(eduQuestionLstNo));
            }
        }

        /**
         * 2022.02.21 LHJ MOC 번호 직접입력으로 변경
         */
        /*
         * // MOC 번호가 들어온 경우 ChangeRefWork changeRefWork = new ChangeRefWork();
         * changeRefWork.setSafChngNo(eduMaster.getSafChngNo());
         * changeRefWork.setRefTableId(String.valueOf(eduMaster.getSafEduMstNo()
         * )); changeRefWork.setRefTableNm("saf_edu_mst");
         * changeRefWork.setChngRefWorkCd(ConstVal.SAF_CHNG_REF_WORK_2);
         * changeRefWork.setCreateUserId(eduMaster.getCreateUserId());
         * changeService.taskChange(changeRefWork);
         */

        return eduMasterMapper.updateEduMaster(eduMaster);
    }

    /**
     * 교육마스터 삭제
     *
     * @param safEduMstNo
     *            교육마스터번호
     * @return 교육마스터
     * @throws Exception
     *             예외
     */
    @Transactional
    public int deleteEduMaster(int safEduMstNo, int safEduCourseNo, DefaultParam defaultParam) throws Exception {
        return eduMasterMapper.deleteEduMaster(safEduMstNo, safEduCourseNo);
    }

    /**
     * 교육계획 유저 조회
     *
     * @param 교육계획번호
     * @return 교육계획 유저 List
     * @throws Exception
     */
    public List<User> getEduMasterUser(int safEduMstNo, DefaultParam defaultParam) throws Exception {
        return eduMasterMapper.getEduMasterUser(safEduMstNo, defaultParam);
    }

    /**
     * 교육결과 수정
     *
     * @param EduMaster
     *            교육마스터
     * @return 교육마스터
     * @throws Exception
     *             예외
     */
    @Transactional
    public int updateEduResult(EduMaster eduMaster, DefaultParam defaultParam) throws Exception {
        for (EduDetailPerson eduDetailPerson : Optional.ofNullable(eduMaster.getPersonList()).orElse(new ArrayList<EduDetailPerson>())) {
            this.eduMasterMapper.updateEduDetailPerson(eduDetailPerson);
        }

        if (Optional.ofNullable(eduMaster.getEduOutSideUsers()).orElse(new ArrayList<EduOutsideUser>()).size() > 0) {
            this.eduMasterMapper.deleteEduOutSideUser(eduMaster.getSafEduMstNo());
            for (EduOutsideUser eduOutsideUser : eduMaster.getEduOutSideUsers()) {
                eduOutsideUser.setSafEduMstNo(eduMaster.getSafEduMstNo());
                this.eduMasterMapper.createEduOutSideUser(eduOutsideUser);
            }
        }

        /**
         * 2022.02.21 LHJ MOC 번호 직접입력으로 변경
         */
        /*
         * // MOC 번호가 들어온 경우 ChangeRefWork changeRefWork = new ChangeRefWork();
         * changeRefWork.setSafChngNo(eduMaster.getSafChngNo());
         * changeRefWork.setRefTableId(String.valueOf(eduMaster.getSafEduMstNo()
         * )); changeRefWork.setRefTableNm("saf_edu_mst");
         * changeRefWork.setChngRefWorkCd(ConstVal.SAF_CHNG_REF_WORK_2);
         * changeRefWork.setCreateUserId(eduMaster.getCreateUserId());
         * changeService.taskChange(changeRefWork);
         */

        return eduMasterMapper.updateEduMaster(eduMaster);
    }

    /**
     * 교육이수자 조회
     *
     * @param safEduMstNo
     *            교육마스터번호
     * @return 교육이수자 목록
     * @throws Exception
     *             예외
     */
    public List<EduDetailPerson> getEduDetailPersons(int safEduMstNo, String completYn, String reEduNotYn, DefaultParam defaultParam) throws Exception {
        return eduMasterMapper.getEduDetailPersons(safEduMstNo, completYn, reEduNotYn, defaultParam);
    }

    /**
     * 불참자 재교육 등록/수정
     *
     * @param EduMaster
     *            교육마스터
     * @return 교육마스터
     * @throws Exception
     *             예외
     */
    @Transactional
    public int updateReEduMaster(EduMaster eduMaster, DefaultParam defaultParam) throws Exception {

        String[] searchPeriod = this.requestMapper.convertObjectListAsStringArray(eduMaster.getPeriod());
        if (searchPeriod != null && searchPeriod.length == 2) {
            eduMaster.setReEduSYmd(searchPeriod[0]);
            eduMaster.setReEduEYmd(searchPeriod[1]);
        }

        int updateCount = eduMasterMapper.updateReEduMaster(eduMaster);
        for (EduDetailPerson eduDetailPerson : eduMaster.getEduDetailPersons()) {
            eduDetailPerson.setSafEduMstNo(eduMaster.getSafEduMstNo());

            eduMasterMapper.updateReEduMasterUser(eduDetailPerson);
        }
        return updateCount;
    }

    /**
     * 불참자 재교육 완료
     *
     * @param safEduMstNo
     *            교육마스터 번호
     * @return 성공여부
     * @throws Exception
     *             예외
     */
    @Transactional
    public int updateReEduMasterComplet(int safEduMstNo, DefaultParam defaultParam) throws Exception {
        return eduMasterMapper.updateReEduMasterComplet(safEduMstNo);
    }

    /**
     * 불참자 재교육 완료 여부
     *
     * @param safEduMstNo
     *            교육마스터 번호
     * @return 성공여부
     * @throws Exception
     *             예외
     */
    public String getReEduMasterCompletCheck(int safEduMstNo, DefaultParam defaultParam) throws Exception {
        return eduMasterMapper.getReEduMasterCompletCheck(safEduMstNo, defaultParam);
    }

    /**
     * 교육이수자 조회 외부
     *
     * @param safEduMstNo
     *            교육마스터번호
     * @return 교육이수자 목록
     * @throws Exception
     *             예외
     */
    public List<EduOutsideUser> getEduOutSideUsers(int safEduMstNo, DefaultParam defaultParam) throws Exception {
        return eduMasterMapper.getEduOutSideUsers(safEduMstNo, defaultParam);
    }

    @Transactional
    public int updateAppr(int safEduMstNo, String apprStepCd, int apprRqstNo) {

        int resultNo = 0;

        if (ConstVal.COM_BIZ_APPR_STEP_REJECT.equals(apprStepCd)) {
            // 반려 - 결재 요청
            resultNo += eduMasterMapper.updateAppr(safEduMstNo, apprRqstNo, ConstVal.COM_BIZ_APPR_STEP_REJECT);
        } else if (ConstVal.COM_BIZ_APPR_STEP_ING.equals(apprStepCd)) {
            // 결재중
            resultNo += eduMasterMapper.updateAppr(safEduMstNo, apprRqstNo, ConstVal.COM_BIZ_APPR_STEP_ING);
        } else if (ConstVal.COM_BIZ_APPR_STEP_COMPLETE.equals(apprStepCd)) {
            // 결재 완료
            resultNo += eduMasterMapper.updateAppr(safEduMstNo, apprRqstNo, ConstVal.COM_BIZ_APPR_STEP_COMPLETE);
        }
        return resultNo;
    }

    public int updateApprResult(int safEduMstNo, String apprStepCd, int apprRqstNo) throws Exception {

        int resultNo = 0;
        if (ConstVal.COM_BIZ_APPR_STEP_REJECT.equals(apprStepCd)) {
            // 반려 - 결재 요청 PSREG(작성중) 으로 세팅
            resultNo += eduMasterMapper.updateApprResult(safEduMstNo, apprRqstNo, ConstVal.COM_BIZ_APPR_STEP_REJECT);
        } else if (ConstVal.COM_BIZ_APPR_STEP_ING.equals(apprStepCd)) {
            // 결재 중 - 결재 요청 PSREG(작성중) 으로 세팅
            resultNo += eduMasterMapper.updateApprResult(safEduMstNo, apprRqstNo, ConstVal.COM_BIZ_APPR_STEP_ING);
        } else if (ConstVal.COM_BIZ_APPR_STEP_COMPLETE.equals(apprStepCd)) {
            // 결재 완료 - 결재 요청 PSEND(완료) 으로 세팅
            resultNo += eduMasterMapper.updateApprResult(safEduMstNo, apprRqstNo, ConstVal.COM_BIZ_APPR_STEP_COMPLETE);
        }

        return resultNo;
    }

    public String downloadExcelCheckupResult(String type, int safEduMstNo, DefaultParam defaultParam) throws Exception {
        POIUtil excel = null;

        if ("EDU1".equals(type)) { // 교육 과정
            excel = new POIUtil("교육과정 엑셀 다운로드");
            excel.setOffset(0, 0);
            excel.addRow("C0BEC0", Font.BOLDWEIGHT_NORMAL, Arrays.asList("회사", "교육주관사업장", "교육기관", "과정운영부서", "과정명", "교육형태", "교육주제", "역량분류", "교육대상", "필수여부", "필수직급", "선수과정1", "선수과정2", "선수과정3", "교육기간", "신청방식", "고용보험대상", "교육일수", "해외교육여부", "총교육비", "교육시간", "승인자결재여부", "과정상태", "차수변경가능", "BL과정여부", "타사업장공개여부", "교육레벨", "성적기준", "", "수료기준", "", "교육만족도(설문)",
                    "현업적용도여부", "현업적용도여부", "현업적용도평가반영여부", "일정공개여부", "학습목표", "주요학습내용", "비고"));

            excel.addRow("C0BEC0", Font.BOLDWEIGHT_NORMAL, Arrays.asList(

                    "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "출석(%)", "과제물(%)", "출석(%)", "과제물(%)", "", "", "", "", "", "", "", ""));

            excel.merge(0, 0, 0, 1);
            excel.merge(1, 0, 1, 1);
            excel.merge(2, 0, 2, 1);
            excel.merge(3, 0, 3, 1);
            excel.merge(4, 0, 4, 1);
            excel.merge(5, 0, 5, 1);
            excel.merge(6, 0, 6, 1);
            excel.merge(7, 0, 7, 1);
            excel.merge(8, 0, 8, 1);
            excel.merge(9, 0, 9, 1);
            excel.merge(10, 0, 10, 1);
            excel.merge(11, 0, 11, 1);
            excel.merge(12, 0, 12, 1);
            excel.merge(13, 0, 13, 1);
            excel.merge(14, 0, 14, 1);
            excel.merge(15, 0, 15, 1);
            excel.merge(16, 0, 16, 1);
            excel.merge(17, 0, 17, 1);
            excel.merge(18, 0, 18, 1);
            excel.merge(19, 0, 19, 1);
            excel.merge(20, 0, 20, 1);
            excel.merge(21, 0, 21, 1);
            excel.merge(22, 0, 22, 1);
            excel.merge(23, 0, 23, 1);
            excel.merge(24, 0, 24, 1);
            excel.merge(25, 0, 25, 1);
            excel.merge(26, 0, 26, 1);
            excel.merge(27, 0, 28, 0);
            excel.merge(29, 0, 30, 0);
            excel.merge(31, 0, 31, 1);
            excel.merge(32, 0, 32, 1);
            excel.merge(33, 0, 33, 1);
            excel.merge(34, 0, 34, 1);
            excel.merge(35, 0, 35, 1);
            excel.merge(36, 0, 36, 1);
            excel.merge(37, 0, 37, 1);
            excel.merge(38, 0, 38, 1);

            List<LinkedHashMap<String, Object>> eduResultTargets = this.eduMasterMapper.getEduCourseExcel(safEduMstNo, defaultParam);

            for (LinkedHashMap<String, Object> data : eduResultTargets) {
                List<Object> list = new ArrayList<Object>(data.values());
                excel.addRow(list);
            }

        } else if ("EDU2".equals(type)) { // 교육 차수 다운로드
            excel = new POIUtil("교육차수 엑셀 다운로드");
            excel.addRow("C0BEC0", Font.BOLDWEIGHT_NORMAL, Arrays.asList(

                    "교육과정명", "년도", "교육장소", "교육일수", "교육시간", "정원", "정원외신청구분", "수강신청일", "수강신청종료일", "교육시작일", "교육종료일", "수강취소가능여부", "수강취소시작일", "수강취소종료일", "평가여부", "담임교수", "차수상태", "총교육비", "인당교육비", "담당자", "담당자연락처", "고용보험대상", "환급총금액", "인당환급비", "비고"));

            List<LinkedHashMap<String, Object>> eduResultTargets = this.eduMasterMapper.getEduDisparitysExcel(safEduMstNo, defaultParam);

            for (LinkedHashMap<String, Object> data : eduResultTargets) {
                List<Object> list = new ArrayList<Object>(data.values());
                excel.addRow(list);
            }

        } else if ("EDU3".equals(type)) { // 교육 대상자 다운로드
            excel = new POIUtil("교육대상자 엑셀 다운로드");
            EduMaster master = eduMasterMapper.getEduMaster(safEduMstNo, defaultParam);
            ;

            excel.addRow("FFFFFF", Font.BOLDWEIGHT_NORMAL, Arrays.asList(master.getEduNm(), "", "", "", "", ""));

            excel.addRow("FFFFFF", Font.BOLDWEIGHT_NORMAL, Arrays.asList(

                    "순번", "년도", "과정명", "차수", "회사코드", "*사번 "));
            excel.merge(0, 0, 5, 0);

            List<LinkedHashMap<String, Object>> eduResultTargets = this.eduMasterMapper.getEduResultTargetsExcel(safEduMstNo);

            for (LinkedHashMap<String, Object> data : eduResultTargets) {
                List<Object> list = new ArrayList<Object>(data.values());
                excel.addRow(list);
            }

        } else if ("EDU4".equals(type)) { // 교육 결과 다운로드
            excel = new POIUtil("교육결과 엑셀 다운로드");
            excel.addRow("FFFFFF", Font.BOLDWEIGHT_NORMAL, Arrays.asList("교육생 목록(수료처리용)", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""));

            excel.addRow("FFFFFF", Font.BOLDWEIGHT_NORMAL, Arrays.asList("순번", "년도", "교육과정명", "차수", "사용자명", "사번", "소속사", "소속사코드", "교육명", "시험", "출석", "과제", "기타", "총점", "수료여부Y(수료)N(미수료)", "수료시간"));
            excel.merge(0, 0, 15, 0);

            List<LinkedHashMap<String, Object>> eduResultTargets = this.eduMasterMapper.getEduResultsExcel(safEduMstNo);

            for (LinkedHashMap<String, Object> data : eduResultTargets) {
                List<Object> list = new ArrayList<Object>(data.values());
                excel.addRow(list);
            }

        } else if ("EDU5".equals(type)) { // 교육 이력 다운로드
            excel = new POIUtil("교육이력 엑셀 다운로드");
            excel.addRow("C0BEC0", Font.BOLDWEIGHT_NORMAL, Arrays.asList("* 사번", "* 교육시작일", "* 교육종료일", "* 회사", "* 교육주관사업장", "* 교육기관", "* 과정운영부서", "* 과정명", "* 교육형태", "* 교육주제", "* 역량분류", "* 교육대상", "* 필수여부", "* 교육기간", "* 신청방식", "* 고용보험대상", "* 교육일수", "* 해외교육여부", "* 총교육비", "* 교육시간", "* 승인자결재여부", "* 과정상태", "* 차수변경가능", "* BL과정여부", "* 타사업장공개여부",
                    "* 교육만족도(설문)", "* 현업적용도여부", "* 현업적용도여부", "* 일정공개여부", "* 학습목표", "* 주요학습내용", "* 교육장소", "* 정원", "* 정원외신청구분", "* 수강신청시작일", "* 수강신청종료일", "* 차수상태", "* 담당자", "* 담당자연락처", "* 수료여부", "* 총점", "* 고용보험대상", "* 평점", "* 이수시간"));

            excel.addRow("C0BEC0", Font.BOLDWEIGHT_NORMAL, Arrays.asList("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""));

            for (int i = 0; i < 44; i++) {
                excel.merge(i, 0, i, 1);
            }

            List<LinkedHashMap<String, Object>> eduResultTargets = this.eduMasterMapper.getEduResultHistorysExcel(safEduMstNo);

            for (LinkedHashMap<String, Object> data : eduResultTargets) {
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
     * 교육대상자 엑셀 업로드
     *
     * @param taskClassNm
     * @param taskKey
     * @param uploadUserId
     * @return
     * @throws Exception
     */
    public Map<String, Object> uploadExcelForPlanPerson(String taskClassNm, String taskKey, String uploadUserId, DefaultParam defaultParam) throws Exception {
        // 임시 저장된 교육대상자 엑셀 파일의 정보를 읽는다
        List<AttachFile> uploadedPerson = attachFileMapper.getUploadFiles(taskClassNm, taskKey);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("success", false);
        map.put("message", "");
        map.put("uploadResult", null);
        map.put("errorInfo", null);

        int totalCount = 0;
        int successCount = 0;
        int failCount = 0;

        File excel = new File(uploadedPerson.get(0).getFilePath());
        ExcelReader reader = new ExcelReader();
        List<String[][]> sheets = reader.read(excel);

        if (sheets != null) {
            String sheetName = "교육대상자";
            String[][] sheet = sheets.get(0);
            String[] sheetHeader = new String[] { "사번", "사원명", "부서명" };

            // 헤더 양식확인
            if (sheet.length > 0 && !reader.excelHeaderCheck(sheet[0], sheetHeader)) {
                map.put("message", "업로드양식이 오류: " + sheetName + " 시트의 헤더가 다릅니다.");
            } else {
                List<Map<String, Object>> uploadResults = new ArrayList<Map<String, Object>>();
                List<Map<String, Object>> errorInfos = new ArrayList<Map<String, Object>>();
                List<Map<String, String>> excelDataList = new ArrayList<Map<String, String>>();

                Map<String, Object> resultMap = new HashMap<String, Object>();
                StringBuilder rowCheck;

                for (int row = 1; row < sheet.length; row++) {
                    rowCheck = new StringBuilder();

                    for (int i = 0; i < sheet[row].length; i++) {
                        rowCheck.append(sheet[row][i].trim());
                    }

                    if (rowCheck.length() > 0) {
                        String failMessage = "";
                        // 사번
                        String userId = sheet[row][0].trim();
                        // 사원명
                        String userNm = sheet[row][1].trim();
                        // 부서명
                        String deptNm = sheet[row][2].trim();

                        // 사번 확인
                        if (StringUtils.isNotBlank(userId)) {
                            int cnt = eduMasterMapper.checkUserId(userId, defaultParam);
                            if (cnt <= 0) {
                                failMessage += "■ 사번이 잘못되었습니다.";
                                failCount++;
                                Map<String, Object> errorInfo = new HashMap<String, Object>();
                                errorInfo.put("classNm", sheetName);
                                errorInfo.put("failRow", row);
                                errorInfo.put("failMessage", failMessage);
                                errorInfo.put("remark", "");

                                errorInfos.add(errorInfo);
                            } else {
                                successCount++;
                                Map<String, String> userMap = new HashMap<String, String>();
                                userMap.put("userId", userId);
                                userMap.put("userNm", userNm);
                                userMap.put("deptNm", deptNm);
                                excelDataList.add(userMap);
                            }
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
     * 교육과정의 교육대상자에 해당하는 사용자 목록 조회
     *
     * @param safEduCourseNo
     *            교육과정번호
     * @param plantCd
     *            사업장코드
     * @return 교육이수자 목록
     * @throws Exception
     *             예외
     */
    public List<EduDetailPerson> getEduCoursePsn(int safEduCourseNo, String plantCd) throws Exception {
        return eduMasterMapper.getEduCoursePsn(safEduCourseNo, plantCd);
    }
}
