package com.she.safety.education.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Strings;
import com.she.common.model.DefaultParam;
import com.she.manage.mapper.CodeMasterMapper;
import com.she.manage.model.CodeMaster;
import com.she.manage.model.User;
import com.she.safety.education.service.EduGroupService;
import com.she.safety.education.service.EduMasterService;
import com.she.safety.education.service.EduProblemMgtService;
import com.she.safety.education.service.EduQuestionService;
import com.she.safety.model.EduCourseMat;
import com.she.safety.model.EduDetailPerson;
import com.she.safety.model.EduGroupUser;
import com.she.safety.model.EduMaster;
import com.she.safety.model.EduOutsideUser;
import com.she.safety.model.EduProblemMgt;
import com.she.safety.model.EduQuestionList;
import com.she.safety.model.EduVideo;
import com.she.utils.ConstVal;
import com.she.utils.RequestMapper;

@RestController
@RequestMapping("api/saf/education")
public class EduMasterController {
    @Autowired
    private EduMasterService eduMasterService;

    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private EduGroupService eduGroupService;

    @Autowired
    private CodeMasterMapper codeMasterMapper;

    private String excelDownloadFileName = "교육대상자_업로드_양식.xlsx";

    @Autowired
    private EduQuestionService eduQuestionService;

    @Autowired
    private EduProblemMgtService eduProblemMgtService;

    private static final Logger logger = LoggerFactory.getLogger(EduMasterController.class);

    /**
     * 교육마스터 상세조회
     *
     * @param safEduMstNo
     *            교육마스터번호
     * @return EduMaster 교육마스터
     * @throws Exception
     *             예외
     */
    @GetMapping("/edumaster/{safEduMstNo}")
    public ResponseEntity<EduMaster> getEduMaster(@PathVariable("safEduMstNo") int safEduMstNo, @ModelAttribute DefaultParam defaultParam) throws Exception {
        return ResponseEntity.ok().body(eduMasterService.getEduMaster(safEduMstNo, defaultParam));
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
    @DeleteMapping("/deleteedumaster/{safEduMstNo}/{safEduCourseNo}")
    public ResponseEntity<Integer> deleteEduMaster(@PathVariable("safEduMstNo") int safEduMstNo, @PathVariable("safEduCourseNo") int safEduCourseNo, @ModelAttribute DefaultParam defaultParam) throws Exception {
        return ResponseEntity.ok().body(eduMasterService.deleteEduMaster(safEduMstNo, safEduCourseNo, defaultParam));
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
    @PostMapping("/edumaster")
    public ResponseEntity<Integer> createEduMaster(@RequestBody EduMaster eduMaster, @ModelAttribute DefaultParam defaultParam) throws Exception {
        eduMasterService.createEduMaster(eduMaster, defaultParam);

        int safEduMstNo = eduMasterService.getSafEduMstNo(eduMaster.getSafEduCourseNo(), defaultParam);
        eduMasterService.insertSafEduCourseMat(safEduMstNo, eduMaster.getSafEduCourseNo(), defaultParam);

        return ResponseEntity.ok().body(eduMaster.getSafEduMstNo());
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
    @PutMapping("/edumaster")
    public ResponseEntity<Integer> updateEduMaster(@RequestBody EduMaster eduMaster, @ModelAttribute DefaultParam defaultParam) throws Exception {
        eduMasterService.updateEduMaster(eduMaster, defaultParam);
        return ResponseEntity.ok().body(eduMaster.getSafEduMstNo());
    }

    /**
     * 선택그룹 전체지정
     *
     *
     * @param EduGroupUser
     *            그룹 사용자
     * @return 그룹사용자
     * @throws Exception
     *             예외
     */

    @GetMapping("/edumastergroupuser/{eduGrpNo}")
    public ResponseEntity<List<EduGroupUser>> getEduMasterGroupUser(@PathVariable("eduGrpNo") int eduGrpNo) throws Exception {
        List<EduGroupUser> userList = eduGroupService.getEduGroupUsers(eduGrpNo);

        return ResponseEntity.ok().body(userList);
    }

    /**
     * 교육계획 유저 조회
     *
     * @param 교육계획번호
     * @return 교육계획 유저 List
     * @throws Exception
     */
    @GetMapping("/getedumasteruser/{safEduMstNo}")
    public ResponseEntity<List<User>> getEduMasterUser(@PathVariable("safEduMstNo") int safEduMstNo, @ModelAttribute DefaultParam defaultParam) throws Exception {
        List<User> userList = eduMasterService.getEduMasterUser(safEduMstNo, defaultParam);

        return ResponseEntity.ok().body(userList);
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
    @PutMapping("/eduresult")
    public ResponseEntity<Integer> updateEduResult(@RequestBody EduMaster eduMaster, @ModelAttribute DefaultParam defaultParam) throws Exception {
        eduMasterService.updateEduResult(eduMaster, defaultParam);
        return ResponseEntity.ok().body(eduMaster.getSafEduMstNo());
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
    @GetMapping("/edudetailpersons")
    public ResponseEntity<List<EduDetailPerson>> getEduDetailPersons(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        int safEduMstNo = map.containsKey("safEduMstNo") ? Integer.parseInt(map.get("safEduMstNo").toString()) : 0;
        String completYn = map.containsKey("completYn") ? map.get("completYn").toString() : "";
        String reEduNotYn = map.containsKey("reEduNotYn") ? map.get("reEduNotYn").toString() : "";

        List<EduDetailPerson> eduDetailPersonList = eduMasterService.getEduDetailPersons(safEduMstNo, completYn, reEduNotYn, defaultParam);

        return ResponseEntity.ok().body(eduDetailPersonList);
    }

    /**
     * 교육이수자 조회 (외부)
     *
     * @param safEduMstNo
     *            교육마스터번호
     * @return 교육이수자 목록
     * @throws Exception
     *             예외
     */
    @GetMapping("/edudetailoutsidepersons")
    public ResponseEntity<List<EduOutsideUser>> getEduOutSideUsers(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        int safEduMstNo = map.containsKey("safEduMstNo") ? Integer.parseInt(map.get("safEduMstNo").toString()) : 0;
        List<EduOutsideUser> eduDetailPersonList = eduMasterService.getEduOutSideUsers(safEduMstNo, defaultParam);

        return ResponseEntity.ok().body(eduDetailPersonList);
    }

    /**
     * 교육계획 문제 조회
     *
     * @param safEduCourseNo
     *            교육과정
     *
     * @return 질문 리스트
     * @throws Exception
     *             예외
     */

    @GetMapping("/eduplanquestions")
    public ResponseEntity<List<EduQuestionList>> getEduPlanQuestions(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {

        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        int safEduCourseNo = map.containsKey("safEduCourseNo") ? Integer.parseInt(map.get("safEduCourseNo").toString()) : 0;
        String eduAttCd = map.containsKey("eduAttCd") ? map.get("eduAttCd").toString() : "";
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        String useYn = map.containsKey("useYn") ? map.get("useYn").toString() : "";

        List<EduQuestionList> eduDetailPersonList = eduQuestionService.getEduPlanQuestions(safEduCourseNo, eduAttCd, plantCd, useYn, defaultParam);

        return ResponseEntity.ok().body(eduDetailPersonList);
    }

    /**
     * 교육계획 자료 조회
     *
     * @param safEduCourseNo
     *            교육과정
     *
     * @return 질문 리스트
     * @throws Exception
     *             예외
     */

    @GetMapping("/eduplanproblems/{safEduCourseNo}/{safEduMstNo}")
    public ResponseEntity<List<EduProblemMgt>> getEduPlanProblem(@PathVariable("safEduCourseNo") int safEduCourseNo, @PathVariable("safEduMstNo") int safEduMstNo, @ModelAttribute DefaultParam defaultParam) throws Exception {
        List<EduProblemMgt> eduProblems;

        if (safEduMstNo == 0) {
            eduProblems = eduProblemMgtService.getEduPlanProblems(safEduCourseNo, safEduMstNo, defaultParam);
        } else {
            eduProblems = eduProblemMgtService.getEduPlanProblemsNew(safEduCourseNo, safEduMstNo, defaultParam);
        }

        return ResponseEntity.ok().body(eduProblems);
    }

    /**
     * 기본 교육자료 삭제
     *
     * @param safEduMstNo
     * @param eduCourseMatList
     * @return
     * @throws Exception
     */
    @PostMapping("/eduplanmat/{safEduMstNo}")
    public ResponseEntity<List<EduCourseMat>> updateEduPlanProblem(@PathVariable("safEduMstNo") int safEduMstNo, @RequestBody List<EduCourseMat> eduCourseMatList) throws Exception {
        // 교육자료파일 복사 및 교육자료 파일 데이터 저장
        for (EduCourseMat eduCourseMat : eduCourseMatList) {
            eduProblemMgtService.updateEduPlanProblemMat("EDU_REFERENCE", eduCourseMat.getEduMatNo());
        }

        // 교육자료 DB 저장
        eduProblemMgtService.updateEduPlanProblems(safEduMstNo, eduCourseMatList);

        return null;
    }

    /**
     * 교육계획 자료 결과 조회
     *
     * @param safEduCourseNo
     *            교육과정
     *
     * @return 질문 리스트
     * @throws Exception
     *             예외
     */

    @GetMapping("/eduplanproblemresults/{safEduMstNo}")
    public ResponseEntity<List<EduProblemMgt>> getEduPlanProblemResult(@PathVariable("safEduMstNo") int safEduMstNo, @ModelAttribute DefaultParam defaultParam) throws Exception {

        List<EduProblemMgt> eduProblems = eduProblemMgtService.getEduPlanProblemResults(safEduMstNo, defaultParam);

        return ResponseEntity.ok().body(eduProblems);
    }

    /**
     * 교육계획 문제 조회
     *
     * @param safEduCourseNo
     *            교육과정
     *
     * @return 질문 리스트
     * @throws Exception
     *             예외
     */

    @PostMapping("/eduplanquestion")
    public ResponseEntity<List<EduQuestionList>> getEduPlanQuestions(@RequestBody List<EduQuestionList> eduQuestionList, @ModelAttribute DefaultParam defaultParam) throws Exception {

        List<EduQuestionList> eduDetailPersonList = eduQuestionService.getEduPlanQuestion(eduQuestionList, defaultParam);

        return ResponseEntity.ok().body(eduDetailPersonList);
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
    @PutMapping("/updatereedumaster")
    public ResponseEntity<Integer> updateReEduMaster(@RequestBody EduMaster eduMaster, @ModelAttribute DefaultParam defaultParam) throws Exception {
        return ResponseEntity.ok().body(eduMasterService.updateReEduMaster(eduMaster, defaultParam));
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
    @GetMapping("/updatereedumastercomplet/{safEduMstNo}")
    public ResponseEntity<Integer> updateReEduMasterComplet(@PathVariable("safEduMstNo") int safEduMstNo, @ModelAttribute DefaultParam defaultParam) throws Exception {
        return ResponseEntity.ok().body(eduMasterService.updateReEduMasterComplet(safEduMstNo, defaultParam));
    }

    /**
     * 불참자 재교육 완료 체크
     *
     * @param safEduMstNo
     *            교육마스터 번호
     * @return 성공여부
     * @throws Exception
     *             예외
     */
    @GetMapping("/updatereedumastercompletchk/{safEduMstNo}")
    public ResponseEntity<String> getReEduMasterCompletCheck(@PathVariable("safEduMstNo") int safEduMstNo, @ModelAttribute DefaultParam defaultParam) throws Exception {
        return ResponseEntity.ok().body(eduMasterService.getReEduMasterCompletCheck(safEduMstNo, defaultParam));
    }

    /**
     * 교육계획 문제 조회 [모바일]
     *
     * @param safEduCourseNo
     *            교육과정
     *
     * @return 질문 리스트
     * @throws Exception
     *             예외
     */

    @GetMapping("/mobile/eduplanquestion")
    public ResponseEntity<List<EduQuestionList>> getMobileEduPlanQuestions(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {

        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        int safEduMstNo = map.containsKey("safEduMstNo") ? Integer.parseInt(map.get("safEduMstNo").toString()) : 0;
        int safEduCourseNo = map.containsKey("safEduCourseNo") ? Integer.parseInt(map.get("safEduCourseNo").toString()) : 0;
        String userId = map.containsKey("userId") ? map.get("userId").toString() : "";

        List<EduQuestionList> eduDetailPersonList = null;
        if (safEduMstNo == 0) {
            eduDetailPersonList = eduQuestionService.getMobileEduPlanQuestionNew(safEduCourseNo, defaultParam);
        } else {
            eduDetailPersonList = eduQuestionService.getMobileEduPlanQuestion(safEduMstNo, userId, defaultParam);
        }
        // List<EduQuestionList> eduDetailPersonList =
        // eduQuestionService.getMobileEduPlanQuestion(safEduMstNo, userId,
        // defaultParam);

        return ResponseEntity.ok().body(eduDetailPersonList);
    }

    /**
     * 교육문제 사용자 제출 완료 [모바일]
     *
     * @param safEduCourseNo
     *            교육과정
     *
     * @return 질문 리스트
     * @throws Exception
     *             예외
     */

    @PostMapping("/mobile/createuseranswer")
    public ResponseEntity<Float> createEduUserAnswer(@RequestBody List<EduQuestionList> eduQuestionList, @ModelAttribute DefaultParam defaultParam) throws Exception {
        return ResponseEntity.ok().body(eduQuestionService.createEduUserAnswer(eduQuestionList, defaultParam));
    }

    /**
     * 교육문제 사용자 재 제출 완료 [모바일]
     *
     * @param safEduCourseNo
     *            교육과정
     *
     * @return 질문 리스트
     * @throws Exception
     *             예외
     */

    @PostMapping("/mobile/edituseranswer")
    public ResponseEntity<Float> editEduUserAnswer(@RequestBody List<EduQuestionList> eduQuestionList, @ModelAttribute DefaultParam defaultParam) throws Exception {
        return ResponseEntity.ok().body(eduQuestionService.editEduUserAnswer(eduQuestionList, defaultParam));
    }

    /**
     * 교육문제 다시풀기 상태 체크
     *
     * @param safEduCourseNo
     *            교육과정
     *
     * @return 질문 리스트
     * @throws Exception
     *             예외
     */

    @GetMapping("/mobile/answerchk")
    public ResponseEntity<String> selectAnswerChk(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        int safEduMstNo = map.containsKey("safEduMstNo") ? Integer.parseInt(map.get("safEduMstNo").toString()) : 0;
        String userId = map.containsKey("userId") ? map.get("userId").toString() : "";

        return ResponseEntity.ok().body(eduQuestionService.selectAnswerChk(safEduMstNo, userId, defaultParam));
    }

    /**
     * 교육문제 채점
     *
     * @param safEduMstNo
     *            교육 마스터 번호
     * @param userId
     *            사용자아이디
     *
     * @return 채점
     * @throws Exception
     *             예외
     */
    @GetMapping("/mobile/score")
    public ResponseEntity<String> selectScore(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        int safEduMstNo = map.containsKey("safEduMstNo") ? Integer.parseInt(map.get("safEduMstNo").toString()) : 0;
        String userId = map.containsKey("userId") ? map.get("userId").toString() : "";

        return ResponseEntity.ok().body(eduQuestionService.selectScore(safEduMstNo, userId, defaultParam));
    }

    /**
     * 교육문제 조회 전 교육기간 체크
     *
     * @param safEduMstNo
     *            교육 마스터 번호
     *
     * @return 교윤문제 목록
     * @throws Exception
     *             예외
     */
    @GetMapping("/mobile/questionymdchk/{safEduMstNo}")
    public ResponseEntity<HashMap<String, Object>> selectQuestionYmdChk(@PathVariable("safEduMstNo") int safEduMstNo, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> resultMap = eduQuestionService.selectQuestionYmdChk(safEduMstNo, defaultParam);
        return ResponseEntity.ok().body(resultMap);
    }

    /**
     * 교육과정 엑셀 다운로드
     *
     * @return 엑셀업로드 양식
     * @throws Exception
     */
    @GetMapping("/eduresultexceldown")
    public byte[] downloadExcelCheckupResult(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        String type = map.containsKey("type") ? map.get("type").toString() : "";
        int safEduMstNo = map.containsKey("safEduMstNo") ? Integer.parseInt(map.get("safEduMstNo").toString()) : 0;

        return eduMasterService.downloadExcelCheckupResult(type, safEduMstNo, defaultParam).getBytes("UTF-8");

    }

    /**
     * 교육대상자 업로드 양식 다운로드
     *
     * @return
     * @throws Exception
     */
    @GetMapping("/excelsample")
    public byte[] downloadSampleExcelForPlanPerson(@ModelAttribute DefaultParam defaultParam) throws Exception {
        String templetePath = "templates";
        CodeMaster filePath = this.codeMasterMapper.getCodeMaster(ConstVal.CODE_GROUP_FILE_PATH, ConstVal.CODE_FILE_PATH_FORM, "Y");

        ClassPathResource resource = new ClassPathResource(templetePath + filePath.getCodeNm() + "교육대상자_업로드_양식.xlsx");

        File file = resource.getFile();

        InputStream inputStream = null;
        try {
            inputStream = new BufferedInputStream(new FileInputStream(file));
            byte[] encoded = Base64.encodeBase64(IOUtils.toByteArray(inputStream));
            String encodedString = new String(encoded);
            return encodedString.getBytes("UTF-8");
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
     * 교육대상자 엑셀 업로드
     *
     * @param parameter
     * @return
     * @throws Exception
     */
    @GetMapping("/excelupload")
    public ResponseEntity<Map<String, Object>> getUploadExcelForPlanPerson(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        String taskClassNm = map.containsKey("taskClassNm") ? map.get("taskClassNm").toString() : "";
        String taskKey = map.containsKey("taskKey") ? map.get("taskKey").toString() : "";
        String uploadUserId = map.containsKey("createUserId") ? map.get("createUserId").toString() : "";

        Map<String, Object> resultMap = eduMasterService.uploadExcelForPlanPerson(taskClassNm, taskKey, uploadUserId, defaultParam);
        return ResponseEntity.ok().body(resultMap);
    }

    /**
     * 교육대상자 검색 목록 (연간교육계획으로 등록한 교육계획은 지정부서 대상자 조회)
     *
     * @param safEduMstNo
     *            교육마스터번호
     * @return 교육이수자 목록
     * @throws Exception
     *             예외
     */
    // @GetMapping("/yearlyplandeptusers/{safEduMstNo}")
    // public List<User> getYearlyPlanUsers(@PathVariable("safEduMstNo") int
    // safEduMstNo, @ModelAttribute DefaultParam defaultParam) throws Exception
    // {
    // return eduMasterService.getYearlyPlanUsers(safEduMstNo);
    // }

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
    @GetMapping("/educoursepsn")
    public ResponseEntity<List<EduDetailPerson>> getEduCoursePsn(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        int safEduCourseNo = map.containsKey("safEduCourseNo") ? Integer.parseInt(!Strings.isNullOrEmpty(map.get("safEduCourseNo").toString()) ? map.get("safEduCourseNo").toString() : "0") : 0;

        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";

        return ResponseEntity.ok().body(eduMasterService.getEduCoursePsn(safEduCourseNo, plantCd));
    }

    /**
     * 교육동영상 상세조회
     *
     * @param safEduMstNo
     *            교육마스터번호
     * @param safEduCourseNo
     *            교육과정번호
     * @return EduVideo 교육동영상
     * @throws Exception
     *             예외
     */
    @GetMapping("/eduvideo/{safEduMstNo}/{safEduCourseNo}")
    public ResponseEntity<EduVideo> getEduVideo(@PathVariable("safEduMstNo") int safEduMstNo, @PathVariable("safEduCourseNo") int safEduCourseNo) throws Exception {
        return ResponseEntity.ok().body(eduMasterService.getEduVideo(safEduMstNo, safEduCourseNo));
    }

    /**
     * 교육 미이수자 조회
     *
     * @param 교육마스터번호
     * @return 교육계획 유저 List
     * @throws Exception
     */
    @GetMapping("/getedunotcompletuser/{safEduMstNo}")
    public ResponseEntity<List<User>> getEduNotCompletUser(@PathVariable("safEduMstNo") int safEduMstNo, @ModelAttribute DefaultParam defaultParam) throws Exception {
        List<User> userList = eduMasterService.getEduNotCompletUser(safEduMstNo, defaultParam);

        return ResponseEntity.ok().body(userList);
    }

    /**
     * 교육이수자 교육동영상 시청 등록/수정
     * 
     * @param EduDetailPerson
     *            교육이수자
     *
     * @return 결과
     * @throws Exception
     *             예외
     */

    @PutMapping("/viewuservideo")
    public ResponseEntity<Integer> updateViewUserVideo(@RequestBody EduDetailPerson EduDetailPerson, @ModelAttribute DefaultParam defaultParam) throws Exception {
        return ResponseEntity.ok().body(eduQuestionService.updateViewUserVideo(EduDetailPerson, defaultParam));
    }

}
