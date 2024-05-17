package com.she.safety.education.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.common.model.DefaultParam;
import com.she.safety.education.mapper.EduCourseMapper;
import com.she.safety.model.EduCourse;
import com.she.safety.model.EduCourseGrp;

@Service
public class EduCourseService {
    @Autowired
    private EduCourseMapper eduCourseMapper;

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
    public List<EduCourse> getEduCourses(String eduAttCd, String eduTypeCd, String eduCourseNm, String useYn, String plantCd, String isTypeCd, String safEduCourseNo, DefaultParam defaultParam) throws Exception {
        return eduCourseMapper.getEduCourses(eduAttCd, eduTypeCd, eduCourseNm, useYn, plantCd, isTypeCd, safEduCourseNo, defaultParam);
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
    public EduCourse getEduCourse(int safEduCourseNo, DefaultParam defaultParam) throws Exception {
        return eduCourseMapper.getEduCourse(safEduCourseNo, defaultParam);
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
    public int createEduCourse(EduCourse eduCourse) throws Exception {
        this.eduCourseMapper.createEduCourse(eduCourse);
        return eduCourse.getSafEduCourseNo();
    }

    /**
     * 교육과정 수정
     * 
     * @param EduCourse
     *            교육과정
     * @return 교육과정
     * @throws Exception
     *             예외
     */
    public int updateEduCourse(EduCourse eduCourse) throws Exception {
        return eduCourseMapper.updateEduCourse(eduCourse);
    }

    /**
     * 교육과정 교육대상자그룹 수정
     * 
     * @param eduCourseGrp
     * @return
     * @throws Exception
     */
    public int insertEduCourseGrp(EduCourseGrp eduCourseGrp) throws Exception {
        return eduCourseMapper.insertEduCourseGrp(eduCourseGrp);
    }

    public List<EduCourseGrp> getEduCourseGrp(int safEduCourseNo) throws Exception {
        return eduCourseMapper.getEduCourseGrp(safEduCourseNo);
    }

    public int getEduCourseDepli(String eduCourseNm, String eduAttCd, String eduTypeCd, String plantCd) throws Exception {
        return eduCourseMapper.getEduCourseDepli(eduCourseNm, eduAttCd, eduTypeCd, plantCd);
    }

    public void deleteEduCourseGrp(int safEduCourseNo) throws Exception {
        eduCourseMapper.deleteEduCourseGrp(safEduCourseNo);
    }

    public List<EduCourse> getEduCourseList() throws Exception {
        return eduCourseMapper.getEduCourseList();
    }
}
