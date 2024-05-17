package com.she.safety.education.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.she.common.model.DefaultParam;
import com.she.safety.education.service.EduQuestionService;
import com.she.safety.model.EduQuestion;
import com.she.safety.model.EduQuestionList;
import com.she.utils.RequestMapper;

@RestController
@RequestMapping("api/saf/eduquestion")
public class EduQuestionController {
    @Autowired
    private EduQuestionService eduQuestionService;

    @Autowired
    private RequestMapper requestMapper;

    /**
     * 교육문제목록 조회
     * 
     * @param safEduCourseNo
     *            교육과정번호
     * @param plantCd
     *            사업장코드
     * @param eduAttCd
     *            분류코드
     * @param eduNm
     *            제목
     * @param eduTypeCd
     *            교육분류
     * @return 교육마스터 목록
     * @throws Exception
     *             예외
     */
    @GetMapping("/eduquestions")
    public ResponseEntity<List<EduQuestion>> getEduQuestions(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam)
            throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        int safEduCourseNo = map.containsKey("safEduCourseNo") ? Integer.parseInt(map.get("safEduCourseNo").toString())
                : 0;
        String eduAttCd = map.containsKey("eduAttCd") ? map.get("eduAttCd").toString() : "";
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        String eduTypeCd = map.containsKey("eduTypeCd") ? map.get("eduTypeCd").toString() : "";

        List<EduQuestion> eduQuestions = this.eduQuestionService.getEduQuestions(safEduCourseNo, eduAttCd, plantCd,
                eduTypeCd, defaultParam);
        return ResponseEntity.ok().body(eduQuestions);
    }

    /**
     * 교육문제 단건 조회
     * 
     * @param eduQuestionNo
     *            교육 문제 번호
     * 
     * @return 교윤문제 목록
     * @throws Exception
     *             예외
     */
    @GetMapping("/eduquestion/{eduQuestionNo}")
    public ResponseEntity<EduQuestion> getEduQuestion(@PathVariable("eduQuestionNo") int eduQuestionNo, @ModelAttribute DefaultParam defaultParam)
            throws Exception {
        EduQuestion eduQuestion = eduQuestionService.getEduQuestion(eduQuestionNo, defaultParam);
        return ResponseEntity.ok().body(eduQuestion);
    }

    /**
     * 교육문제 리스트 조회
     * 
     * @param eduQuestionNo
     *            교육 문제 번호
     * 
     * @return 교윤문제 목록
     * @throws Exception
     *             예외
     */
    @GetMapping("/eduquestionlist/{eduQuestionNo}")
    public ResponseEntity<List<EduQuestionList>> getEduQuestionList(@PathVariable("eduQuestionNo") int eduQuestionNo)
            throws Exception {
        List<EduQuestionList> eduQuestionList = eduQuestionService.getEduQuestionList(eduQuestionNo);
        return ResponseEntity.ok().body(eduQuestionList);
    }

    /**
     * 교육문제 등록
     * 
     * @param EduCourse
     *            교육과정
     * @return 교육과정
     * @throws Exception
     *             예외
     */
    @PostMapping("/eduquestionlist")
    public ResponseEntity<Integer> createEduQuestion(@RequestBody EduQuestion eduQuestion) throws Exception {
        return ResponseEntity.ok().body(eduQuestionService.createEduQuestionList(eduQuestion));
    }

    /**
     * 교육문제 수정
     * 
     * @param EduCourse
     *            교육과정
     * @return 교육과정
     * @throws Exception
     *             예외
     */
    @PutMapping("/eduquestionlist")
    public ResponseEntity<Integer> updateEduQuestionList(@RequestBody EduQuestion eduQuestion) throws Exception {
        return ResponseEntity.ok().body(eduQuestionService.updateEduQuestionList(eduQuestion));
    }

    /**
     * 교육문제 삭제
     * 
     * @param eduQuestionNo
     *            교육 문제 번호
     * 
     * @return 교윤문제 목록
     * @throws Exception
     *             예외
     */
    @DeleteMapping("/eduquestionlist/{eduQuestionNo}")
    public ResponseEntity<Integer> deleteEduQuestionList(@PathVariable("eduQuestionNo") int eduQuestionNo)
            throws Exception {
        int deleteCount = eduQuestionService.deleteEduQuestion(eduQuestionNo);
        return ResponseEntity.ok().body(deleteCount);
    }

}
