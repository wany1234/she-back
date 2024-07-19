package com.she.safety.education.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.common.model.DefaultParam;
import com.she.safety.model.EduDetailPerson;
import com.she.safety.model.EduQuestion;
import com.she.safety.model.EduQuestionAnswer;
import com.she.safety.model.EduQuestionList;
import com.she.safety.model.UserEduAnswer;

@Mapper
@Repository("com.she.safety.education.mapper.EduQuestionMapper")
public interface EduQuestionMapper {

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
    public List<EduQuestion> getEduQuestions(@Param("safEduCourseNo") int safEduCourseNo, @Param("eduAttCd") String eduAttCd, @Param("plantCd") String plantCd, @Param("eduTypeCd") String eduTypeCd, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

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
    public EduQuestion getEduQuestion(@Param("eduQuestionNo") int eduQuestionNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 교육문제 단건 등록
     * 
     * @param eduQuestionNo
     *            교육 문제 번호
     * 
     * @return 교윤문제 목록
     * @throws Exception
     *             예외
     */
    public int createEduQuestion(EduQuestion eduQuestion) throws Exception;

    /**
     * 교육문제 단건 수정
     * 
     * @param eduQuestionNo
     *            교육 문제 번호
     * 
     * @return 교윤문제 목록
     * @throws Exception
     *             예외
     */
    public int updateEduQuestion(EduQuestion eduQuestion) throws Exception;

    /**
     * 교육문제 단건 삭제
     * 
     * @param eduQuestionNo
     *            교육 문제 번호
     * 
     * @return 교윤문제 목록
     * @throws Exception
     *             예외
     */
    public int deleteEduQuestion(@Param("eduQuestionNo") int eduQuestionNo) throws Exception;

    /**
     * 교육문제답변목록 삭제
     * 
     * @param eduQuestionAnsNo
     *            교육문제답변목록 번호
     * 
     * @return 삭제 개수
     * @throws Exception
     *             예외
     */
    public int deleteEduQuestionAnswer(@Param("eduQuestionNo") int eduQuestionNo) throws Exception;

    /**
     * 교육문제목록 조회
     * 
     * @param eduQuestionNo
     * 
     * @return 질문 리스트
     * @throws Exception
     *             예외
     */
    public List<EduQuestionList> getEduQuestionList(@Param("eduQuestionNo") int eduQuestionNo) throws Exception;

    /**
     * 교육문제목록 등록
     * 
     * @param eduQuestionNo
     * 
     * @return 생성 개수
     * @throws Exception
     *             예외
     */
    public int createEduQuestionList(EduQuestionList eduQuestionList) throws Exception;

    /**
     * 교육문제목록 삭제
     * 
     * @param eduQuestionNo
     * 
     * @return 삭제 개수
     * @throws Exception
     *             예외
     */
    public int deleteEduQuestionList(@Param("eduQuestionNo") int eduQuestionNo) throws Exception;

    /**
     * 교육문제답변목록 조회
     * 
     * @param eduQuestionLstNo
     * 
     * @return 교육문제답변목록 리스트
     * @throws Exception
     *             예외
     */
    public List<EduQuestionAnswer> getEduQuestionAnswers(@Param("eduQuestionLstNo") int eduQuestionLstNo) throws Exception;

    /**
     * 교육문제답변목록 등록
     * 
     * @param EduQuestionAnswer
     *            교육문제답변목록
     * 
     * @return 생성 개수
     * @throws Exception
     *             예외
     */
    public int createEduQuestionAnswers(EduQuestionAnswer eduQuestionAnswer) throws Exception;

    /**
     * 교육문제답변목록 삭제
     * 
     * @param eduQuestionAnsNo
     *            교육문제답변목록 번호
     * 
     * @return 삭제 개수
     * @throws Exception
     *             예외
     */
    public int deleteEduQuestionAnswers(@Param("eduQuestionLstNo") int eduQuestionLstNo) throws Exception;

    /**
     * 교육문제답변목록 단건 삭제
     * 
     * @param eduQuestionAnsNo
     *            교육문제답변목록 번호
     * 
     * @return 삭제 개수
     * @throws Exception
     *             예외
     */
    public int deleteEduAnswer(@Param("eduQuestionAnsNo") int eduQuestionAnsNo) throws Exception;

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
    public List<EduQuestionList> getEduPlanQuestions(@Param("safEduCourseNo") int safEduCourseNo, @Param("eduAttCd") String eduAttCd, @Param("plantCd") String plantCd, @Param("useYn") String useYn, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 교육계획 문제 체크데이터 조회
     * 
     * @param safEduCourseNo
     *            교육과정
     * 
     * @return 질문 리스트
     * @throws Exception
     *             예외
     */
    public String[] getEduPlanQuestionsCheckData(@Param("eduQuestionLstNo") int eduQuestionLstNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 교육문제 조회 ( 모바일)
     * 
     * @param safEduMstNo
     *            교육마스터 번호
     * 
     * @return 질문 리스트
     * @throws Exception
     *             예외
     */
    public List<EduQuestionList> getMobileEduPlanQuestions(@Param("safEduMstNo") int safEduMstNo, @Param("userId") String userId, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 교육문제 조회 ( 모바일) 교육계획 신규등록시
     *
     * @param safEduMstNo
     *            교육마스터 번호
     *
     * @return 질문 리스트
     * @throws Exception
     *             예외
     */
    public List<EduQuestionList> getMobileEduPlanQuestionsNew(@Param("safEduCourseNo") int safEduCourseNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 교육문제 사용자 제출 완료 (모바일)
     * 
     * @param safEduMstNo
     *            교육마스터 번호
     * 
     * @return 질문 리스트
     * @throws Exception
     *             예외
     */
    public int createEduUserAnswer(UserEduAnswer userEduAnswer) throws Exception;

    /**
     * 교육문제 사용자 제출 삭제 (모바일)
     * 
     * @param safEduMstNo
     *            교육마스터 번호
     * 
     * @return 질문 리스트
     * @throws Exception
     *             예외
     */
    public int deleteEduUserAnswer(@Param("safEduMstNo") int safEduMstNo, @Param("userId") String userId) throws Exception;

    /**
     * 교육문제 다시 풀기 [모바일]
     * 
     * @param safEduCourseNo
     *            교육과정
     * 
     * @return 다시풀기 체크
     * @throws Exception
     *             예외
     */

    public String selectAnswerChk(@Param("safEduMstNo") int safEduMstNo, @Param("userId") String userId) throws Exception;

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

    public String selectScore(@Param("safEduMstNo") int safEduMstNo, @Param("userId") String userId, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

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
    public HashMap<String, Object> selectQuestionYmdChk(@Param("safEduMstNo") int safEduMstNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    public int updateAnswerChk(@Param("secQuestionYn") String secQuestionYn, @Param("completYn") String completYn, @Param("eduEvalPnt") String eduEvalPnt, @Param("eduEvalPntSec") String eduEvalPntSec, @Param("safEduMstNo") int safEduMstNo, @Param("userId") String userId) throws Exception;

    /**
     * 교육이수자 교육동영상 시청 등록/수정
     * 
     * @param eduDetailPerson
     *            교육이수자
     * 
     * @return 결과
     * @throws Exception
     *             예외
     */
    public int updateViewUserVideo(EduDetailPerson eduDetailPerson) throws Exception;

}
