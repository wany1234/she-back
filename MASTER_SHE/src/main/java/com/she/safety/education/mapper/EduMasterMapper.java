package com.she.safety.education.mapper;

import java.util.LinkedHashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.common.model.DefaultParam;
import com.she.manage.model.User;
import com.she.safety.model.EduDetailPerson;
import com.she.safety.model.EduMaster;
import com.she.safety.model.EduOutsideUser;
import com.she.safety.model.EduVideo;

@Mapper
@Repository("com.she.safety.education.mapper.EduMasterMapper")
public interface EduMasterMapper {

    /**
     * 교육마스터 상세조회
     * 
     * @param safEduMstNo
     *            교육마스터번호
     * @return EduMaster 교육마스터
     * @throws Exception
     *             예외
     */
    public EduMaster getEduMaster(@Param("safEduMstNo") int safEduMstNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    public int getSafEduMstNo(@Param("safEduCourseNo") int safEduCourseNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 교육계획 등록시 기본교육자료 등록
     * 
     * @param safEduCourseNo
     * @param safEduMstNo
     * @throws Exception
     */
    public void insertSafEduCourseMat(@Param("safEduCourseNo") int safEduCourseNo, @Param("safEduMstNo") int safEduMstNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 교육마스터 신규등록
     * 
     * @param EduMaster
     *            교육마스터
     * @return safEduMstNo 교육마스터번호
     * @throws Exception
     *             예외
     */
    public int createEduMaster(EduMaster eduMaster) throws Exception;

    /**
     * 교육마스터 수정
     * 
     * @param EduMaster
     *            교육마스터
     * @return 교육마스터
     * @throws Exception
     *             예외
     */
    public int updateEduMaster(EduMaster eduMaster) throws Exception;

    /**
     * 교육마스터 삭제
     * 
     * @param safEduMstNo
     *            교육마스터번호
     * @return 교육마스터
     * @throws Exception
     *             예외
     */
    public int deleteEduMaster(@Param("safEduMstNo") int safEduMstNo, @Param("safEduCourseNo") int safEduCourseNo) throws Exception;

    /**
     * 교육계획 유저 조회
     * 
     * @param 교육계획번호
     * @return 교육계획 유저 List
     * @throws Exception
     */
    public List<User> getEduMasterUser(@Param("safEduMstNo") int safEduMstNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 교육계획 유저(교육이수자) 신규
     * 
     * @param 유저Id
     *            ,부서번호, 교육번호
     * @return 변경행수
     * @throws Exception
     */
    public int createEduMasterUser(@Param("safEduMstNo") int safEduMstNo, @Param("userId") String userId, @Param("deptCd") String deptCd) throws Exception;

    /**
     * 교육계획 유저(교육이수자) 삭제
     * 
     * @param 유저Id
     * @return 변경행수
     * @throws Exception
     */
    public int deleteEduMasterUser(@Param("safEduMstNo") int safEduMstNo) throws Exception;

    /**
     * 교육이수자 조회
     * 
     * @param safEduMstNo
     *            교육마스터번호
     * @return 교육이수자 목록
     * @throws Exception
     *             예외
     */
    public List<EduDetailPerson> getEduDetailPersons(@Param("safEduMstNo") int safEduMstNo, @Param("completYn") String completYn, @Param("reEduNotYn") String reEduNotYn, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 교육이수자 조회
     * 
     * @param safEduMstNo
     *            교육마스터번호
     * @return 교육이수자 목록
     * @throws Exception
     *             예외
     */
    public List<EduOutsideUser> getEduOutSideUsers(@Param("safEduMstNo") int safEduMstNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 교육이수자 수정
     * 
     * @param EduDetailPerson
     *            교육이수자
     * @return 교육이수자
     * @throws Exception
     *             예외
     */
    public int updateEduDetailPerson(EduDetailPerson eduDetailPerson) throws Exception;

    /**
     * 교육이수자 생성 (외부)
     * 
     * @param EduDetailPerson
     *            교육이수자
     * @return 교육이수자
     * @throws Exception
     *             예외
     */
    public int createEduOutSideUser(EduOutsideUser eduOutsideUser) throws Exception;

    /**
     * 교육이수자 삭제 (외부)
     * 
     * @param EduDetailPerson
     *            교육이수자
     * @return 교육이수자
     * @throws Exception
     *             예외
     */
    public int deleteEduOutSideUser(@Param("safEduMstNo") int safEduMstNo) throws Exception;

    public String[] getPlanQuestion(@Param("safEduMstNo") String safEduMstNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 교육계획 문제 등록
     * 
     * @param safEduMstNo
     *            교육 마스터 번호
     * @Param eduQuestionLstNo 교육 문제 번호
     * @return 결과
     * @throws Exception
     *             예외
     */
    public int createPlanQuestion(@Param("safEduMstNo") String safEduMstNo, @Param("eduQuestionLstNo") String eduQuestionLstNo) throws Exception;

    /**
     * 교육계획 문제 삭제
     * 
     * @param safEduMstNo
     *            교육 마스터 번호
     * @return 결과
     * @throws Exception
     *             예외
     */
    public int deletePlanQuestion(@Param("safEduMstNo") int safEduMstNo) throws Exception;

    public String[] getPlanData(@Param("safEduMstNo") String safEduMstNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 교육계획 자료실 등록
     * 
     * @param safEduMstNo
     *            교육 마스터 번호
     * @Param eduMatNo 교육 자료실 번호
     * @return 결과
     * @throws Exception
     *             예외
     */
    public int createPlanData(@Param("safEduMstNo") String safEduMstNo, @Param("eduMatNo") String eduMatNo) throws Exception;

    /**
     * 교육계획 자료실 삭제
     * 
     * @param safEduMstNo
     *            교육 마스터 번호
     * @return 결과
     * @throws Exception
     *             예외
     */
    public int deletePlanData(@Param("safEduMstNo") int safEduMstNo) throws Exception;

    /**
     * 불참자 재교육 등록/수정
     * 
     * @param EduMaster
     *            교육마스터
     * @return 교육마스터
     * @throws Exception
     *             예외
     */
    public int updateReEduMaster(EduMaster eduMaster) throws Exception;

    /**
     * 불참자 재교육 등록/수정
     * 
     * @param EduDetailPerson
     *            재 교육 이수자
     * @return 교육마스터
     * @throws Exception
     *             예외
     */
    public int updateReEduMasterUser(EduDetailPerson eduDetailPerson) throws Exception;

    /**
     * 불참자 재교육 완료
     * 
     * @param safEduMstNo
     *            교육마스터 번호
     * @return 성공여부
     * @throws Exception
     *             예외
     */
    public int updateReEduMasterComplet(@Param("safEduMstNo") int safEduMstNo) throws Exception;

    /**
     * 불참자 재교육 완료 여부
     * 
     * @param safEduMstNo
     *            교육마스터 번호
     * @return 성공여부
     * @throws Exception
     *             예외
     */
    public String getReEduMasterCompletCheck(@Param("safEduMstNo") int safEduMstNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 교육계획 결제
     * 
     * @param safEduMstNo
     *            교육 마스터 번호
     * @param stepCd
     * 
     * @param apprRqstNo
     * 
     * 
     * 
     * @return 결과
     * @throws Exception
     *             예외
     */
    public int updateAppr(@Param("safEduMstNo") int safEduMstNo, @Param("apprRqstNo") int apprRqstNo, @Param("apprStepCd") String apprStepCd);

    /**
     * 교육결과 결제
     * 
     * @param safEduMstNo
     *            교육 마스터 번호
     * @param stepCd
     * 
     * @param apprRqstNo
     * 
     * 
     * 
     * @return 결과
     * @throws Exception
     *             예외
     */
    public int updateApprResult(@Param("safEduMstNo") int safEduMstNo, @Param("apprRqstNo") int apprRqstNo, @Param("apprStepCd") String apprStepCd) throws Exception;

    /**
     * 교육과정 엑셀 다운로드
     * 
     * @param safEduMstNo
     *            교육 마스터 번호
     * 
     * @return 결과
     * @throws Exception
     *             예외
     */
    public List<LinkedHashMap<String, Object>> getEduCourseExcel(@Param("safEduMstNo") int safEduMstNo, @Param("defaultParam") DefaultParam defaultParam);

    /**
     * 교육차수 엑셀 다운로드
     * 
     * 
     * @param safEduMstNo
     *            교육 마스터 번호
     * 
     * @return 결과
     * @throws Exception
     *             예외
     */
    public List<LinkedHashMap<String, Object>> getEduDisparitysExcel(@Param("safEduMstNo") int safEduMstNo, @Param("defaultParam") DefaultParam defaultParam);

    /**
     * 교육대상자 엑셀 다운로드
     * 
     * 
     * @param safEduMstNo
     *            교육 마스터 번호
     * 
     * @return 결과
     * @throws Exception
     *             예외
     */
    public List<LinkedHashMap<String, Object>> getEduResultTargetsExcel(@Param("safEduMstNo") int safEduMstNo);

    /**
     * 교육학습결과 엑셀 다운로드
     * 
     * @param safEduMstNo
     *            교육 마스터 번호
     * 
     * @return 결과
     * @throws Exception
     *             예외
     */
    public List<LinkedHashMap<String, Object>> getEduResultsExcel(@Param("safEduMstNo") int safEduMstNo);

    /**
     * 교육이력 엑셀 다운로드
     * 
     * @param safEduMstNo
     *            교육 마스터 번호
     * 
     * @return 결과
     * @throws Exception
     *             예외
     */
    public List<LinkedHashMap<String, Object>> getEduResultHistorysExcel(@Param("safEduMstNo") int safEduMstNo);

    /**
     * 사번 확인
     * 
     * @param userId
     * @return
     * @throws Exception
     */
    public int checkUserId(@Param("userId") String userId, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

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
    public List<EduDetailPerson> getEduCoursePsn(@Param("safEduCourseNo") int safEduCourseNo, @Param("plantCd") String plantCd) throws Exception;

    /**
     * 교육과정별동영상 신규등록
     * 
     * @param EduVideo
     *            교육과정별동영상
     * @return 결과
     * @throws Exception
     *             예외
     */
    public int createEduVideo(EduVideo eduVideo) throws Exception;

    /**
     * 교육과정별동영상 수정
     * 
     * @param EduVideo
     *            교육과정별동영상
     * @return 결과
     * @throws Exception
     *             예외
     */
    public int updateEduVideo(EduVideo eduVideo) throws Exception;

    /**
     * 교육과정별동영상 삭제
     * 
     * @param eduVideoNo
     *            교육과정별동영상번호
     * @return 결과
     * @throws Exception
     *             예외
     */
    public int deleteEduVideo(@Param("eduVideoNo") int eduVideoNo) throws Exception;

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
    public EduVideo getEduVideo(@Param("safEduMstNo") int safEduMstNo, @Param("safEduCourseNo") int safEduCourseNo) throws Exception;

    /**
     * 교육 미이수자 조회
     *
     * @param 교육마스터번호
     * @return 교육계획 유저 List
     * @throws Exception
     */
    public List<User> getEduNotCompletUser(@Param("safEduMstNo") int safEduMstNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

}
