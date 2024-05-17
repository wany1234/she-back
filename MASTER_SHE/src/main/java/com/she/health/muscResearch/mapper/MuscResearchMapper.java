package com.she.health.muscResearch.mapper;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.health.model.MuscHarmful;
import com.she.health.model.MuscHarmfulEval;
import com.she.health.model.MuscResearch;
import com.she.health.model.MuscResearchChklist;
import com.she.health.model.MuscResearchDept;
import com.she.health.model.MuscResearchRslt;
import com.she.health.model.MuscResearchUnit;
import com.she.health.model.MuscSurveyChklist;
import com.she.health.model.MuscSurveyRslt;

// SK E&S
@Mapper
@Repository("com.she.health.muscResearch.mapper.MuscResearchMapper")
public interface MuscResearchMapper {

    /**
     * 근골격계 조사목록 조회
     *
     * @param plantCd
     * @param muscResearchStateCd
     * @param researchNm
     * @param startDate
     * @param endDate
     * @param deptCd
     * @return
     * @throws Exception
     */
    public List<MuscResearch> getMuscResearchs(@Param("plantCd") String plantCd, @Param("muscResearchStateCd") String muscResearchStateCd, @Param("researchNm") String researchNm, @Param("startDate") String startDate, @Param("endDate") String endDate, @Param("deptCd") String deptCd, @Param("deptSubYn") String deptSubYn) throws Exception;

    /**
     * 근골격계 조사목록 조회
     *
     * @param muscResearchNo
     *            기본조사번호
     * @return 근골격계 기본조사 정보
     * @throws Exception
     */
    public MuscResearch getMuscResearch(@Param("muscResearchNo") int muscResearchNo) throws Exception;

    /**
     * 근골격계 사전조사 저장
     *
     * @param muscResearch
     *            근골격계 기본조사정보
     * @return 저장 행 수
     * @throws Exception
     */
    public int createMuscResearch(MuscResearch muscResearch) throws Exception;

    /**
     * 근골격계 기본정보 수정
     *
     * @param muscResearch
     * @return
     * @throws Exception
     */
    public Integer updateMuscResearch(MuscResearch muscResearch) throws Exception;

    /**
     * 근골격계 기본정보 완료
     *
     * @param muscResearch
     * @return
     * @throws Exception
     */
    public Integer completeMuscResearch(MuscResearch muscResearch) throws Exception;

    /**
     * 근골격계 기본정보 삭제
     *
     * @param muscResearchNo
     * @return
     * @throws Exception
     */
    public Integer deleteMuscResearch(@Param("muscResearchNo") int muscResearchNo) throws Exception;

    /**
     * 근골격계 결과등록 조사목록 조회
     *
     * @param plantCd
     * @param muscResearchStateCd
     * @param researchNm
     * @param startDate
     * @param endDate
     * @return
     * @throws Exception
     */
    public List<MuscResearch> getMuscResearchResults(@Param("plantCd") String plantCd, @Param("muscResearchStateCd") String muscResearchStateCd, @Param("researchNm") String researchNm, @Param("startDate") String startDate, @Param("endDate") String endDate, @Param("deptCd") String deptCd, @Param("deptSubYn") String deptSubYn) throws Exception;

    /**
     * 근골격계 단위작업 목록 조회
     *
     * @param muscResearchNo
     * @return
     * @throws Exception
     */
    public List<MuscResearchUnit> getunitWorks(@Param("muscResearchNo") int muscResearchNo, @Param("deptCd") String deptCd) throws Exception;

    public int getCheckUnitWorkCnt(@Param("muscResearchUnitNo") int muscResearchUnitNo, @Param("muscResearchNo") int muscResearchNo, @Param("deptCd") String deptCd, @Param("unitWorkNm") String unitWorkNm) throws Exception;

    /**
     * 근골격계 단위작업 순번
     *
     * @param muscResearchNo
     * @return
     * @throws Exception
     */
    public int getCountunitWork(@Param("muscResearchNo") int muscResearchNo) throws Exception;

    /**
     * 근골격계 단위작업 상세 조회
     *
     * @param muscResearchUnitNo
     * @return
     * @throws Exception
     */
    public MuscResearchUnit getunitWork(@Param("muscResearchUnitNo") int muscResearchUnitNo) throws Exception;

    /**
     * 근골격계 단위작업 등록
     *
     * @param muscResearchUnit
     * @return
     * @throws Exception
     */
    public int createUnitWork(MuscResearchUnit muscResearchUnit) throws Exception;

    /**
     * 근골격계 단위작업 수정
     *
     * @param muscResearchUnit
     * @return
     * @throws Exception
     */
    public Integer updateUnitWork(MuscResearchUnit muscResearchUnit) throws Exception;

    /**
     * 근골격계 단위작업 삭제
     *
     * @param muscResearchUnitNo
     * @return
     * @throws Exception
     */
    public Integer deleteUnitWork(@Param("muscResearchUnitNo") int muscResearchUnitNo) throws Exception;

    /**
     * 근골격계 부담작업no 리스트 조회
     *
     * @return
     * @throws Exception
     */
    public List<MuscResearchChklist> getResearchChklists() throws Exception;

    /**
     * 작업분류표 조회
     *
     * @return
     * @throws Exception
     */
    public List<Map<String, Object>> getRsltChklist(@Param("muscResearchNo") int muscResearchNo) throws Exception;

    /**
     * 근골격계 부담작업no 등록
     *
     * @param muscResearchRslt
     * @return
     * @throws Exception
     */
    public int createResearchUnitRslt(MuscResearchRslt muscResearchRslt) throws Exception;

    /**
     * 근골격계 부담작업no 수정
     *
     * @param muscResearchRslt
     * @return
     * @throws Exception
     */
    public Integer updateResearchChklist(MuscResearchRslt muscResearchRslt) throws Exception;

    /**
     * 근골격계 결과작성중
     *
     * @param muscResearchRsltNo
     * @return
     * @throws Exception
     */
    public Integer updateMuscResearchChklist(@Param("muscResearchRsltNo") int muscResearchRsltNo) throws Exception;

    /**
     * 근골격계 부담작업no 조회
     *
     * @param muscResearchUnitNo
     * @return
     * @throws Exception
     */
    public List<MuscResearchRslt> getResearchChklist(@Param("muscResearchUnitNo") int muscResearchUnitNo) throws Exception;

    /**
     * 근골격계 부담작업no 삭제
     *
     * @param muscResearchUnitNo
     * @return
     * @throws Exception
     */
    public int deleteResearchChklist(@Param("muscResearchUnitNo") int muscResearchUnitNo) throws Exception;

    /**
     * 근골격계 조사결과 목록 조회
     *
     * @return
     * @throws Exception
     */
    public List<MuscResearchRslt> getResearchRslt(@Param("muscResearchNo") int muscResearchNo, @Param("deptCd") String deptCd) throws Exception;

    /**
     * 유해인자 목록 조회
     *
     * @return
     * @throws Exception
     */
    public List<MuscHarmful> getHarmfuls(@Param("muscResearchNo") int muscResearchNo) throws Exception;

    /**
     * 유해인자 상세 조회
     *
     * @param muscHarmfulNo
     * @return
     * @throws Exception
     */
    public MuscHarmful getHarmful(@Param("muscHarmfulNo") int muscHarmfulNo) throws Exception;

    /**
     * 유해인자 등록
     *
     * @param muscHarmful
     * @return
     * @throws Exception
     */
    public int createHarmful(MuscHarmful muscHarmful) throws Exception;

    /**
     * 유해인자 수정
     *
     * @param muscHarmful
     * @return
     * @throws Exception
     */
    public Integer updateHarmful(MuscHarmful muscHarmful) throws Exception;

    /**
     * 유해인자 삭제
     *
     * @param muscHarmfulNo
     * @return
     * @throws Exception
     */
    public Integer deleteHarmful(@Param("muscHarmfulNo") int muscHarmfulNo) throws Exception;

    /**
     * 유해요인 조사평가 조회
     *
     * @return
     * @throws Exception
     */
    public List<MuscHarmfulEval> getHarmfulSurvey(@Param("muscResearchUnitNo") int muscResearchUnitNo) throws Exception;

    public MuscHarmfulEval getSingleHarmfulSurvey(int muscHarmfulNo, int muscResearchChklistNo) throws Exception;

    /**
     * 작업부하 및 작업빈도 목록 등록
     *
     * @param muscHarmfulEval
     * @return
     * @throws Exception
     */
    public int createHarmfulSurvey(MuscHarmfulEval muscHarmfulEval) throws Exception;

    public Integer deleteHarmfulSurvey(@Param("muscHarmfulEvalNo") int muscHarmfulEvalNo) throws Exception;

    /**
     * 작업부하 및 작업빈도 목록 수정
     *
     * @param muscHarmfulEval
     * @return
     * @throws Exception
     */
    public Integer updateHarmfulSurvey(MuscHarmfulEval muscHarmfulEval) throws Exception;

    /**
     * 근골격계 결재요청
     *
     * @param muscResearchNo
     * @param apprRqstNo
     * @param muscResearchStateCd
     * @return
     * @throws Exception
     */
    public int completeMuscImpr(@Param("muscResearchNo") int muscResearchNo, @Param("apprRqstNo") int apprRqstNo, @Param("muscResearchStateCd") String muscResearchStateCd) throws Exception;

    /******************************************************************************************************/

    /**
     * 작업분류표 삭제
     *
     * @param heaMuscSurveyNo
     *            근골격계 기본조사번호
     * @return 삭제 행 수
     * @throws Exception
     */
    public int deleteMuscSurveyChklist(@Param("heaMuscSurveyNo") int heaMuscSurveyNo);

    /**
     * 작업분류표 추가정보 저장
     *
     * @param muscSurveyChklist
     *            작업분류표 정보
     * @return 수정 행 수
     * @throws Exception
     */
    public int updateMuscSurveyChklist(MuscSurveyChklist muscSurveyChklist);

    /**
     * 작업별 조사결과 저장
     *
     * @param muscSurveyRslt
     *            작업별 조사결과
     * @return 저장 행 수
     * @throws Exception
     */
    public int createMuslSurveyRslt(MuscSurveyRslt muscSurveyRslt);

    /**
     * 작업별 조사결과 수정
     *
     * @param muscSurveyRslt
     *            작업별 조사결과
     * @return 수정 행 수
     * @throws Exception
     */
    public int updateMuslSurveyRslt(MuscSurveyRslt muscSurveyRslt);

    /**
     * 작업별 조사결과 삭제
     *
     * @param heaMuscSurveyChklistNo
     *            작업분류표번호
     * @return 삭제 행 수
     * @throws Exception
     */
    public int deleteMuslSurveyRslt(@Param("heaMuscSurveyChklistNo") int heaMuscSurveyChklistNo);

    /**
     * 근골격계 기본조사 결제완료시 진행상태 변경
     *
     * @param heaMuscSurveyNo
     *            근골격계 기본조사번호
     * @param pStepCd
     *            진행단계
     * @return 수정 행 수
     * @throws Exception
     */
    public int updatePStepCd(@Param("heaMuscSurveyNo") int heaMuscSurveyNo, @Param("pStepCd") String pStepCd);

    /**
     * 근골격계 기본조사 결재요청번호 수정
     *
     * @param heaMuscSurveyNo
     *            근골격계 기본조사번호
     * @param apprRqstNo
     *            걸재요청번호
     * @return 수정 행 수
     * @throws Exception
     */
    public int updateApprRqstNo(@Param("heaMuscSurveyNo") int heaMuscSurveyNo, @Param("apprRqstNo") int apprRqstNo);

    public List<MuscResearchDept> getMuscResearchDepts(@Param("muscResearchNo") int muscResearchNo) throws Exception;

    public int createMuscResearchDept(MuscResearchDept muscResearchDept) throws Exception;

    public int updateMuscResearchDept(MuscResearchDept muscResearchDept) throws Exception;

    public int deleteMuscResearchDept(@Param("muscResearchNo") int muscResearchNo) throws Exception;

    public Integer getHarmfulResearchNo(@Param("muscHarmfulNo") int muscHarmfulNo) throws Exception;

    int getUnitWorkNo(@Param("muscResearchNo") int muscResearchNo, @Param("deptCd") String deptCd, @Param("unitWorkNm") String unitWorkNm) throws Exception;

    List<LinkedHashMap<String, Object>> getHarmfulExcel(List<Integer> muscHarmfulNoList) throws Exception;

}
