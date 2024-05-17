package com.she.safety.education.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.common.model.AttachFile;
import com.she.common.model.DefaultParam;
import com.she.safety.model.EduCourseMat;
import com.she.safety.model.EduProblemMgt;

@Mapper
@Repository("com.she.safety.education.mapper.EduProblemMgtMapper")
public interface EduProblemMgtMapper {

    /**
     * 교육자료관리 조회
     * 
     * @param safEduCourseNo
     *            교육과정번호
     * @param plantCd
     *            사업장코드
     * @param eduAttCd
     *            분류코드
     * @param eduNm
     *            제목
     * 
     * @return 교육마스터 목록
     * @throws Exception
     *             예외
     */
    public List<EduProblemMgt> getEduProblemMgts(@Param("safEduCourseNo") int safEduCourseNo, @Param("plantCd") String plantCd, @Param("eduAttCd") String eduAttCd, @Param("eduNm") String eduNm, @Param("eduTypeCd") String eduTypeCd, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 
     * 
     * @param safEduCourseNo
     * @param safEduMstNo
     * @param plantCd
     * @param eduAttCd
     * @param eduNm
     * @param eduTypeCd
     * @return
     * @throws Exception
     */
    public List<EduProblemMgt> getEduProblemMgtsNew(@Param("safEduCourseNo") int safEduCourseNo, @Param("safEduMstNo") int safEduMstNo, @Param("plantCd") String plantCd, @Param("eduAttCd") String eduAttCd, @Param("eduNm") String eduNm, @Param("eduTypeCd") String eduTypeCd, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 기본 교육자료 삭제
     * 
     * @param safEduMstNo
     * @throws Exception
     */
    public void deleteEduPlanProblems(@Param("safEduMstNo") int safEduMstNo) throws Exception;

    /**
     * 교육계획 등록-교육자료 등록
     * 
     * @param taskKey
     * @throws Exception
     */
    public void insertEduPlanProblemMat(@Param("taskKey") int taskKey) throws Exception;

    public List<AttachFile> getMatFileData(@Param("taskKey") int taskKey) throws Exception;

    /**
     * 교육계획등록-교육자료 삭제
     * 
     * @param taskKey
     * @throws Exception
     */
    public void deleteEduPlanProblemMat(@Param("taskKey") int taskKey) throws Exception;

    /**
     * 기본 교육자료 등록
     * 
     * @param eduCourseMat
     * @throws Exception
     */
    public void insertEduPlanProblems(EduCourseMat eduCourseMat) throws Exception;

    /**
     * 교육자료결과 조회
     * 
     * @param safEduCourseNo
     *            교육과정번호
     * 
     * @return 교육마스터 목록
     * @throws Exception
     *             예외
     */
    public List<EduProblemMgt> getEduProblemMgtResults(@Param("safEduMstNo") int safEduMstNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

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
    public List<EduProblemMgt> getEduProblemMgtRevisions(@Param("eduMatNo") int eduMatNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 교육자료관리 상세 조회
     * 
     * @param eduMatNo
     *            교육자료관리 번호
     * @return getEduProblemMgt 교육자료관리
     * @throws Exception
     *             예외
     */
    public EduProblemMgt getEduProblemMgt(@Param("eduMatNo") int eduMatNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    public EduProblemMgt getEduRegProblemMgt(@Param("eduCourseMatNo") int eduCourseMatNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 교육마스터 신규등록
     * 
     * @param getEduProblemMgt
     *            교육자료관리
     * @return eduMatNo 교육자료관리 번호
     * @throws Exception
     *             예외
     */
    public int createEduProblemMgt(EduProblemMgt eduProblemMgt) throws Exception;

    /**
     * 교육마스터 수정
     * 
     * @param EduProblemMgt
     *            교육자료관리
     * @return 교육자료관리
     * @throws Exception
     *             예외
     */
    public void updateEduProblemMgt(EduProblemMgt eduProblemMgt) throws Exception;

    /**
     * 교육마스터 삭제
     * 
     * @param eduMatNo
     *            교육자료관리 번호
     * @return 교육마스터
     * @throws Exception
     *             예외
     */
    public int deleteEduProblemMgt(@Param("eduMatNo") int eduMatNo) throws Exception;

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
    public List<EduProblemMgt> getEduPlanProblems(@Param("safEduCourseNo") int safEduCourseNo) throws Exception;

}
