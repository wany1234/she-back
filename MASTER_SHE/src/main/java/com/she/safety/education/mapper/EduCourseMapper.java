package com.she.safety.education.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.common.model.DefaultParam;
import com.she.safety.model.EduCourse;
import com.she.safety.model.EduCourseGrp;

@Mapper
@Repository("com.she.safety.education.mapper.EduCourseMapper")
public interface EduCourseMapper {

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
    public List<EduCourse> getEduCourses(@Param("eduAttCd") String eduAttCd, @Param("eduTypeCd") String eduTypeCd, @Param("eduCourseNm") String eduCourseNm, @Param("useYn") String useYn, @Param("plantCd") String plantCd, @Param("isTypeCd") String isTypeCd, @Param("safEduCourseNo") String safEduCourseNo,
            @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 교육과정 상세조회
     * 
     * @param safEduCourseNo
     *            교육과정번호
     * @return EduCourse 교육과정
     * @throws Exception
     *             예외
     */
    public EduCourse getEduCourse(@Param("safEduCourseNo") int safEduCourseNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 교육과정 신규등록
     * 
     * @param EduCourse
     *            교육과정
     * @return safEduCourseNo 교육과정번호
     * @throws Exception
     *             예외
     */
    public int createEduCourse(EduCourse eduCourse) throws Exception;

    /**
     * 교육과정 수정
     * 
     * @param EduCourse
     *            교육과정
     * @return 교육과정
     * @throws Exception
     *             예외
     */
    public int updateEduCourse(EduCourse eduCourse) throws Exception;

    /**
     * 교육과정 교육대상자 수정
     * 
     * @param eduCourseGrp
     * @return
     * @throws Exception
     */
    public int insertEduCourseGrp(EduCourseGrp eduCourseGrp) throws Exception;

    public List<EduCourseGrp> getEduCourseGrp(@Param("safEduCourseNo") int safEduCourseNo) throws Exception;

    public int getEduCourseDepli(@Param("eduCourseNm") String eduCourseNm, @Param("eduAttCd") String eduAttCd, @Param("eduTypeCd") String eduTypeCd, @Param("plantCd") String plantCd) throws Exception;

    public void deleteEduCourseGrp(int safEduCourseNo) throws Exception;

    public List<EduCourse> getEduCourseList() throws Exception;
}
