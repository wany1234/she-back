package com.she.safety.education.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.common.model.DefaultParam;
import com.she.safety.model.EduCourseGrpSchedule;
import com.she.safety.model.EduMaster;

@Mapper
@Repository("com.she.safety.education.mapper.EduStatusMapper")
public interface EduStatusMapper {

    /**
     * 교육계획 현황 조회
     * 
     * @param fromYmd
     *            교육기간
     * @param toYmd
     *            교육기간
     * @param deptCd
     *            부서코드
     * @Param tgtDeptCd 대상부서코드
     * @param eduAttCd
     *            교육분류코드
     * @param safEduCourseNo
     *            교육과정번호
     * @param eduTypeCd
     *            교육구분코드
     * @param eduNm
     *            교육명
     * @return 교육마스터 목록
     * @throws Exception
     *             예외
     */
    public List<EduMaster> getEduStatuss(@Param("year") String year, @Param("pStateCd") String pStateCd, @Param("isTypeCd") String isTypeCd, @Param("mainDeptCd") String mainDeptCd, @Param("targetDeptCd") String targetDeptCd, @Param("execDeptCd") String execDeptCd, @Param("fromYmd") String fromYmd, @Param("toYmd") String toYmd,
            @Param("deptCd") String deptCd, @Param("deptSubYn") String deptSubYn, @Param("tgtDeptCd") String tgtDeptCd, @Param("eduAttCd") String eduAttCd, @Param("safEduCourseNo") int safEduCourseNo, @Param("eduTypeCd") String eduTypeCd, @Param("eduNm") String eduNm, @Param("eduClassCd") String eduClassCd, @Param("plantCd") String plantCd,
            @Param("pProcStepNm") String pProcStepNm, @Param("pYear") String pYear, @Param("pMonth") String pMonth, @Param("mainDeptSubYn") String mainDeptSubYn, @Param("targetDeptSubYn") String targetDeptSubYn, @Param("execDeptSubYn") String execDeptSubYn, @Param("reEduYn") String reEduYn, @Param("eduMethodCd") String eduMethodCd,
            @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 교육결과 현황 조회
     */

    public List<EduMaster> getEduResults(@Param("year") String year, @Param("rStateCd") String rStateCd, @Param("isTypeCd") String isTypeCd, @Param("mainDeptCd") String mainDeptCd, @Param("targetDeptCd") String targetDeptCd, @Param("execDeptCd") String execDeptCd, @Param("fromYmd") String fromYmd, @Param("toYmd") String toYmd,
            @Param("deptCd") String deptCd, @Param("deptSubYn") String deptSubYn, @Param("tgtDeptCd") String tgtDeptCd, @Param("eduAttCd") String eduAttCd, @Param("safEduCourseNo") int safEduCourseNo, @Param("eduTypeCd") String eduTypeCd, @Param("eduNm") String eduNm, @Param("eduClassCd") String eduClassCd, @Param("plantCd") String plantCd,
            @Param("rProcStepNm") String rProcStepNm, @Param("pYear") String pYear, @Param("pMonth") String pMonth, @Param("mainDeptSubYn") String mainDeptSubYn, @Param("targetDeptSubYn") String targetDeptSubYn, @Param("execDeptSubYn") String execDeptSubYn, @Param("reEduYn") String reEduYn, @Param("eduMethodCd") String eduMethodCd,
            @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 교육 문제 조회 (모바일 )
     * 
     * @param userId
     *            사용자아이디
     * 
     * @return 교육마스터 목록
     * @throws Exception
     *             예외
     */
    public List<EduMaster> getMobileEduStatuss(@Param("userId") String userId, @Param("plantCd") String plantCd, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 교육 계획 및 실적 조회
     * 
     * @param year
     *            교육연도
     * @param safEduCourseNo
     *            교육과정번호
     * @Param tgtDeptCd 대상부서코드
     * @param eduAttCd
     *            교육분류코드
     * @param eduNm
     *            교육명
     * @return 교육마스터 목록
     * @throws Exception
     *             예외
     */
    public List<EduMaster> getEduDeptStatuss(@Param("year") String year, @Param("eduAttCd") String eduAttCd, @Param("safEduCourseNo") int safEduCourseNo, @Param("userId") String userId, @Param("deptCd") String deptCd, @Param("deptSubYn") String deptSubYn, @Param("eduClassCd") String eduClassCd, @Param("completYn") String completYn,
            @Param("plantCd") String plantCd, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 개인별 교육 현황 조회
     * 
     * @param year
     *            교육연도
     * @param safEduCourseNo
     *            교육과정번호
     * @param eduAttCd
     *            교육분류코드
     * @param userId
     *            이수자
     * @return 교육마스터 목록
     * @throws Exception
     *             예외
     */
    public List<EduMaster> getEduPersonStatuss(@Param("year") String year, @Param("safEduCourseNo") int safEduCourseNo, @Param("eduAttCd") String eduAttCd, @Param("userId") String userId, @Param("deptCd") String deptCd, @Param("eduClassCd") String eduClassCd, @Param("eduNm") String eduNm, @Param("plantCd") String plantCd,
            @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 교육 대상자 여부 조회
     * 
     * @param userId
     * @param safEduMstNo
     * @return
     * @throws Exception
     */
    public int getEduDtlPsnCnt(@Param("userId") String userId, @Param("safEduMstNo") int safEduMstNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 개인별 교육 도래일 조회
     * 
     * @param safEduCourseNo
     *            과정번호
     * @param eduAttCd
     *            교육대분류코드
     * @param userId
     *            사용자아이디
     * @param deptCd
     *            부서코드
     * @param eduClassCd
     *            교육 분류
     * @param plantCd
     *            사업장
     * @return
     * @throws Exception
     */
    public List<EduCourseGrpSchedule> getUserEduSchedules(@Param("safEduCourseNo") int safEduCourseNo, @Param("eduAttCd") String eduAttCd, @Param("userId") String userId, @Param("deptCd") String deptCd, @Param("deptSubYn") String deptSubYn, @Param("eduClassCd") String eduClassCd, @Param("plantCd") String plantCd,
            @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    public List<EduMaster> getEduProblemss(@Param("fromYmd") String fromYmd, @Param("toYmd") String toYmd, @Param("eduAttCd") String eduAttCd, @Param("safEduCourseNo") int safEduCourseNo, @Param("eduTypeCd") String eduTypeCd, @Param("eduNm") String eduNm, @Param("eduClassCd") String eduClassCd, @Param("plantCd") String plantCd,
            @Param("processStepNm") String processStepNm, @Param("pYear") String pYear, @Param("pMonth") String pMonth, @Param("userId") String userId, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 교육 내부인원
     * 
     * @param safEduMstNo
     *            교육마스터 번호
     * @param completYn
     *            이수여부
     */
    public List<EduMaster> getEduUserList(@Param("safEduMstNo") int safEduMstNo, @Param("completYn") String completYn, @Param("reEduNotYn") String reEduNotYn, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 교육 외부인원
     * 
     * @param safEduMstNo
     *            교육마스터 번호
     */
    public List<EduMaster> getEduOutUserList(@Param("safEduMstNo") int safEduMstNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 교육 이수여부 Y 상세조회
     * 
     * @param safEduMstNo
     *            교육마스터 번호
     * @param completYn
     *            이수여부
     */
    public List<EduMaster> getEduComYList(@Param("safEduMstNo") int safEduMstNo, @Param("completYn") String completYn, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 교육 결과 현황 목록
     * 
     * @param safEduMstNo
     *            교육마스터 번호
     * @param completYn
     *            이수여부
     */
    public List<HashMap<String, Object>> getEduResultStatusList(@Param("plantCd") String plantCd, @Param("year") String year, @Param("isTypeCd") String isTypeCd, @Param("totalFlag") String totalFlag, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 교육결과 현황 목록 상세 조회
     * 
     * @param parameter
     *            검색조건
     * @return 평가결과 관리 목록
     * @throws Exception
     */
    public List<EduMaster> getEduResultLists(@Param("plantCd") String plantCd, @Param("completYn") String completYn, @Param("year") String year, @Param("isTypeCd") String isTypeCd, @Param("monFlag") int monFlag, @Param("gubun") String gubun, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

}
