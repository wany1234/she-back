package com.she.safety.education.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import com.she.safety.education.service.EduCourseService;
import com.she.safety.education.service.EduGroupService;
import com.she.safety.model.EduCourse;
import com.she.safety.model.EduCourseGrp;
import com.she.safety.model.EduGroup;
import com.she.utils.RequestMapper;

@RestController
@RequestMapping("api/saf/education")
public class EduCourseController {
    @Autowired
    private EduCourseService eduCourseService;

    @Autowired
    private EduGroupService eduGroupService;

    @Autowired
    private RequestMapper requestMapper;

    /**
     * 교육과정 조회
     * 
     * @param eduAttCd
     *            교육분류코드
     * @param eduCourseNm
     *            교육과정명
     * @param useYn
     *            사용여부
     * @return 교육과정 목록
     * @throws Exception
     *             예외
     */
    @GetMapping("/educourses")
    public ResponseEntity<List<EduCourse>> getEduCourses(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        String eduAttCd = map.containsKey("eduAttCd") ? map.get("eduAttCd").toString() : "";
        String eduTypeCd = map.containsKey("eduTypeCd") ? map.get("eduTypeCd").toString() : "";
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";

        String eduCourseNm = map.containsKey("eduCourseNm") ? map.get("eduCourseNm").toString() : "";
        String useYn = map.containsKey("useYn") ? map.get("useYn").toString() : "";
        String isTypeCd = map.containsKey("isTypeCd") ? map.get("isTypeCd").toString() : "";

        String safEduCourseNo = map.containsKey("safEduCourseNo") ? map.get("safEduCourseNo").toString() : "0";

        List<EduCourse> eduCourseList = eduCourseService.getEduCourses(eduAttCd, eduTypeCd, eduCourseNm, useYn, plantCd, isTypeCd, safEduCourseNo, defaultParam);

        return ResponseEntity.ok().body(eduCourseList);
    }

    /**
     * 교육대상자그룹 리스트
     * 
     * @return
     * @throws Exception
     */
    @GetMapping("/edugrp/{plantCd}")
    public ResponseEntity<List<EduGroup>> getEduGrp(@PathVariable("plantCd") String plantCd, @ModelAttribute DefaultParam defaultParam) throws Exception {
        List<EduGroup> eduGroups = eduGroupService.getEduGroups("", plantCd, "Y", defaultParam);
        return ResponseEntity.ok().body(eduGroups);
    }

    @GetMapping("/preedu")
    public ResponseEntity<List<EduCourse>> getPreEdu() throws Exception {
        List<EduCourse> eduCourse = eduCourseService.getEduCourseList();
        return ResponseEntity.ok().body(eduCourse);
    }

    /**
     * 교육과정 상세조회
     * 
     * @param safEduCourseNo
     *            교육과정번호
     * @return EduCourse 교육과정
     * @throws Exception
     *             예외
     */
    @GetMapping("/educourse/{safEduCourseNo}")
    public ResponseEntity<EduCourse> getEduCourse(@PathVariable("safEduCourseNo") int safEduCourseNo, @ModelAttribute DefaultParam defaultParam) throws Exception {
        EduCourse eduCourse = eduCourseService.getEduCourse(safEduCourseNo, defaultParam);
        return ResponseEntity.ok().body(eduCourse);
    }

    /**
     * 교육과정 신규등록
     * 
     * @param EduCourse
     *            교육과정
     * @return safEduCourseNo 교육과정번호
     * @throws Exception
     *             예외
     */
    @PostMapping("/educourse")
    public ResponseEntity<Integer> createEduCourse(@RequestBody EduCourse eduCourse) throws Exception {
        return ResponseEntity.ok().body(eduCourseService.createEduCourse(eduCourse));
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
    @PutMapping("/educourse")
    public ResponseEntity<Integer> updateEduCourse(@RequestBody EduCourse eduCourse) throws Exception {
        return ResponseEntity.ok().body(eduCourseService.updateEduCourse(eduCourse));
    }

    /**
     * 교육과정 교육대상자 수정
     * 
     * @param safEduCourseNo
     * @param eduCourseGrp
     * @return
     * @throws Exception
     */
    @PostMapping("/educoursegrp/{safEduCourseNo}")
    public int insertEduCourseGrp(@PathVariable("safEduCourseNo") int safEduCourseNo, @RequestBody List<EduCourseGrp> eduCourseGrps) throws Exception {
        int resultCnt = 0;

        eduCourseService.deleteEduCourseGrp(safEduCourseNo);

        for (EduCourseGrp eduCourseGrp : eduCourseGrps) {
            eduCourseGrp.setSafEduCourseNo(safEduCourseNo);
            resultCnt += eduCourseService.insertEduCourseGrp(eduCourseGrp);
            resultCnt++;
        }

        return resultCnt;
    }

    @GetMapping("/educoursegrp/{safEduCourseNo}")
    public ResponseEntity<List<EduCourseGrp>> getEduCourseGrp(@PathVariable("safEduCourseNo") int safEduCourseNo) throws Exception {
        return ResponseEntity.ok().body(eduCourseService.getEduCourseGrp(safEduCourseNo));
    }

    @GetMapping("/educoursecheck/{eduCourseNm}/{eduAttCd}/{eduTypeCd}/{plantCd}")
    public int getEduCourseDupli(@PathVariable("eduCourseNm") String eduCourseNm, @PathVariable("eduAttCd") String eduAttCd, @PathVariable("eduTypeCd") String eduTypeCd, @PathVariable("plantCd") String plantCd) throws Exception {
        return eduCourseService.getEduCourseDepli(eduCourseNm, eduAttCd, eduTypeCd, plantCd);
    }
}
