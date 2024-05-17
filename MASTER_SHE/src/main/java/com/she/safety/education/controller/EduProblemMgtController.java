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
import com.she.safety.education.service.EduProblemMgtService;
import com.she.safety.model.EduProblemMgt;
import com.she.utils.RequestMapper;

@RestController
@RequestMapping("api/saf/eduproblem")
public class EduProblemMgtController {
    @Autowired
    private EduProblemMgtService eduProblemMgtService;

    @Autowired
    private RequestMapper requestMapper;

    /**
     * 교육자료관리 조회
     * 
     * @param fromYmd
     *            교육분류
     * @param toYmd
     *            교육과정
     * @param toYmd
     *            제목
     * @param toYmd
     *            사업장코드
     * 
     * @return 교육마스터 목록
     * @throws Exception
     *             예외
     */
    @GetMapping("/eduproblems")
    public ResponseEntity<List<EduProblemMgt>> getEduMasters(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        int safEduCourseNo = map.containsKey("safEduCourseNo") ? Integer.parseInt(map.get("safEduCourseNo").toString()) : 0;
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        String eduAttCd = map.containsKey("eduAttCd") ? map.get("eduAttCd").toString() : "";
        String eduNm = map.containsKey("eduNm") ? map.get("eduNm").toString() : "";
        String eduTypeCd = map.containsKey("eduTypeCd") ? map.get("eduTypeCd").toString() : "";

        List<EduProblemMgt> eduProblemMgtList = eduProblemMgtService.getEduProblemMgts(safEduCourseNo, plantCd, eduAttCd, eduNm, eduTypeCd, defaultParam);

        return ResponseEntity.ok().body(eduProblemMgtList);
    }

    /**
     * 교육자료관리 개정 조회
     * 
     * @param eduMatNo
     *            교육자료관리 번호
     * 
     * @return 교육마스터 목록
     * @throws Exception
     *             예외
     */
    @GetMapping("/eduproblem/revisions/{eduMatNo}")
    public ResponseEntity<List<EduProblemMgt>> getEduProblemMgtRevisions(@PathVariable("eduMatNo") int eduMatNo, @ModelAttribute DefaultParam defaultParam) throws Exception {

        List<EduProblemMgt> eduProblemMgtList = eduProblemMgtService.getEduProblemMgtRevisions(eduMatNo, defaultParam);

        return ResponseEntity.ok().body(eduProblemMgtList);
    }

    /**
     * 교육자료관리 상세 조회
     * 
     * @param eduMatNo
     *            교육자료관리 번호
     * @return getEduProblemMgt 교육자료관리
     * @throws Exception
     *             예외
     */
    @GetMapping("/eduproblem/{eduMatNo}")
    public ResponseEntity<EduProblemMgt> getEduMaster(@PathVariable("eduMatNo") int eduMatNo, @ModelAttribute DefaultParam defaultParam) throws Exception {
        EduProblemMgt eduProblemMgt = eduProblemMgtService.getEduProblemMgt(eduMatNo, defaultParam);
        return ResponseEntity.ok().body(eduProblemMgt);
    }

    @GetMapping("/eduregproblem/{eduCourseMatNo}")
    public ResponseEntity<EduProblemMgt> getEduRegMaster(@PathVariable("eduCourseMatNo") int eduCourseMatNo, @ModelAttribute DefaultParam defaultParam) throws Exception {
        EduProblemMgt eduProblemMgt = eduProblemMgtService.getEduRegProblemMgt(eduCourseMatNo, defaultParam);
        return ResponseEntity.ok().body(eduProblemMgt);
    }

    /**
     * 교육자료관리 신규등록
     * 
     * @param getEduProblemMgt
     *            교육자료관리
     * @return eduMatNo 교육자료관리 번호
     * @throws Exception
     *             예외
     */
    @PostMapping("/eduproblem")
    public ResponseEntity<Integer> createEduProblemMgt(@RequestBody EduProblemMgt eduProblemMgt) throws Exception {
        return ResponseEntity.ok().body(eduProblemMgtService.createEduProblemMgt(eduProblemMgt));
    }

    /**
     * 교육자료관리 수정
     * 
     * @param EduMaster
     *            교육마스터
     * @return 교육마스터
     * @throws Exception
     *             예외
     */
    @PutMapping("/eduproblem")
    public ResponseEntity<Integer> updateEduProblemMgt(@RequestBody EduProblemMgt eduProblemMgt) throws Exception {
        return ResponseEntity.ok().body(eduProblemMgtService.updateEduProblemMgt(eduProblemMgt));
    }

    /**
     * 교육자료관리 삭제
     * 
     * @param safEduMstNo
     *            교육마스터번호
     * @return 교육마스터
     * @throws Exception
     *             예외
     */
    @DeleteMapping("/eduproblem/delete/{eduMatNo}")
    public ResponseEntity<Integer> deleteEduProblemMgt(@PathVariable("eduMatNo") int eduMatNo) throws Exception {
        return ResponseEntity.ok().body(eduProblemMgtService.deleteEduProblemMgt(eduMatNo));
    }

}
