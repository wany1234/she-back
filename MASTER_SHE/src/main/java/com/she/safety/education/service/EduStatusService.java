package com.she.safety.education.service;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.common.model.DefaultParam;
import com.she.safety.education.mapper.EduStatusMapper;
import com.she.safety.model.EduCourseGrpSchedule;
import com.she.safety.model.EduMaster;

@Service
public class EduStatusService {
    @Autowired
    private EduStatusMapper eduStatusMapper;

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
    public List<EduMaster> getEduStatuss(String year, String pStateCd, String isTypeCd, String mainDeptCd, String targetDeptCd, String execDeptCd, String fromYmd, String toYmd, String deptCd, String deptSubYn, String tgtDeptCd, String eduAttCd, int safEduCourseNo, String eduTypeCd, String eduNm, String eduClassCd, String plantCd,
            String pProcStepNm, String pYear, String pMonth, String mainDeptSubYn, String targetDeptSubYn, String execDeptSubYn, DefaultParam defaultParam) throws Exception {
        return eduStatusMapper.getEduStatuss(year, pStateCd, isTypeCd, mainDeptCd, targetDeptCd, execDeptCd, fromYmd, toYmd, deptCd, deptSubYn, tgtDeptCd, eduAttCd, safEduCourseNo, eduTypeCd, eduNm, eduClassCd, plantCd, pProcStepNm, pYear, pMonth, mainDeptSubYn, targetDeptSubYn, execDeptSubYn, defaultParam);
    }

    /**
     * 교육결과 현황 조회
     */
    public List<EduMaster> getEduResults(String year, String rStateCd, String isTypeCd, String mainDeptCd, String targetDeptCd, String execDeptCd, String fromYmd, String toYmd, String deptCd, String deptSubYn, String tgtDeptCd, String eduAttCd, int safEduCourseNo, String eduTypeCd, String eduNm, String eduClassCd, String plantCd,
            String rProcStepNm, String pYear, String pMonth, String mainDeptSubYn, String targetDeptSubYn, String execDeptSubYn, DefaultParam defaultParam) throws Exception {
        return eduStatusMapper.getEduResults(year, rStateCd, isTypeCd, mainDeptCd, targetDeptCd, execDeptCd, fromYmd, toYmd, deptCd, deptSubYn, tgtDeptCd, eduAttCd, safEduCourseNo, eduTypeCd, eduNm, eduClassCd, plantCd, rProcStepNm, pYear, pMonth, mainDeptSubYn, targetDeptSubYn, execDeptSubYn, defaultParam);
    }

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
    public List<EduMaster> getMobileEduStatuss(@Param("userId") String userId, @Param("plantCd") String plantCd, DefaultParam defaultParam) throws Exception {
        return eduStatusMapper.getMobileEduStatuss(userId, plantCd, defaultParam);
    }

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
    public List<EduMaster> getEduDeptStatuss(String year, String eduAttCd, int safEduCourseNo, String userId, String deptCd, String deptSubYn, String eduClassCd, String completYn, String plantCd, DefaultParam defaultParam) throws Exception {
        return eduStatusMapper.getEduDeptStatuss(year, eduAttCd, safEduCourseNo, userId, deptCd, deptSubYn, eduClassCd, completYn, plantCd, defaultParam);
    }

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
    public List<EduMaster> getEduPersonStatuss(String year, int safEduCourseNo, String eduAttCd, String userId, String deptCd, String eduClassCd, String eduNm, String plantCd, DefaultParam defaultParam) throws Exception {
        return eduStatusMapper.getEduPersonStatuss(year, safEduCourseNo, eduAttCd, userId, deptCd, eduClassCd, eduNm, plantCd, defaultParam);
    }

    /**
     * 교육 대상자 여부 조회
     * 
     * @param userId
     * @param safEduMstNo
     * @return
     * @throws Exception
     */
    public int getEduDtlPsnCnt(String userId, int safEduMstNo, DefaultParam defaultParam) throws Exception {
        if (StringUtils.isBlank(userId) || safEduMstNo <= 0) {
            return 0;
        } else {
            return eduStatusMapper.getEduDtlPsnCnt(userId, safEduMstNo, defaultParam);
        }
    }

    /**
     * 개인별 교육 도래일 조회
     * 
     * @param safEduCourseNo
     * @param eduAttCd
     * @param userId
     * @param deptCd
     * @param eduClassCd
     * @param plantCd
     * @return
     * @throws Exception
     */
    public List<EduCourseGrpSchedule> getUserEduSchedules(int safEduCourseNo, String eduAttCd, String userId, String deptCd, String deptSubYn, String eduClassCd, String plantCd, DefaultParam defaultParam) throws Exception {
        return eduStatusMapper.getUserEduSchedules(safEduCourseNo, eduAttCd, userId, deptCd, deptSubYn, eduClassCd, plantCd, defaultParam);
    }

    public List<EduMaster> getEduProblemss(String fromYmd, String toYmd, String eduAttCd, int safEduCourseNo, String eduTypeCd, String eduNm, String eduClassCd, String plantCd, String processStepNm, String pYear, String pMonth, String userId, DefaultParam defaultParam) throws Exception {
        return eduStatusMapper.getEduProblemss(fromYmd, toYmd, eduAttCd, safEduCourseNo, eduTypeCd, eduNm, eduClassCd, plantCd, processStepNm, pYear, pMonth, userId, defaultParam);
    }

    /**
     * 교육 내부인원
     * 
     * @param safEduMstNo
     *            교육마스터 번호
     * @param completYn
     *            이수여부
     */
    public List<EduMaster> getEduUserList(int safEduMstNo, String completYn, String reEduNotYn, DefaultParam defaultParam) throws Exception {
        List<EduMaster> eduMaster = eduStatusMapper.getEduUserList(safEduMstNo, completYn, reEduNotYn, defaultParam);
        return eduMaster;
    }

    /**
     * 교육 외부인원
     * 
     * @param safEduMstNo
     *            교육마스터 번호
     */
    public List<EduMaster> getEduOutUserList(int safEduMstNo, DefaultParam defaultParam) throws Exception {
        List<EduMaster> eduMaster = eduStatusMapper.getEduOutUserList(safEduMstNo, defaultParam);
        return eduMaster;
    }

    /**
     * 교육 내부인원
     * 
     * @param safEduMstNo
     *            교육마스터 번호
     * @param completYn
     *            이수여부
     */
    public List<EduMaster> getEduComYList(int safEduMstNo, String completYn, DefaultParam defaultParam) throws Exception {
        List<EduMaster> eduMaster = eduStatusMapper.getEduComYList(safEduMstNo, completYn, defaultParam);
        return eduMaster;
    }

    /**
     * 교육결과 현황 목록 조회
     * 
     * @param parameter
     *            검색조건
     * @return 평가결과 현황 목록
     * @throws Exception
     */
    public List<HashMap<String, Object>> getEduResultStatusList(String plantCd, String assessYear, String isTypeCd, String totalFlag, DefaultParam defaultParam) throws Exception {
        List<HashMap<String, Object>> resultEduStatusLists = eduStatusMapper.getEduResultStatusList(plantCd, assessYear, isTypeCd, totalFlag, defaultParam);
        return resultEduStatusLists;
    }

    /**
     * 교육결과 현황 목록 상세 조회
     * 
     * @param parameter
     *            검색조건
     * @return 평가결과 관리 목록
     * @throws Exception
     */
    public List<EduMaster> getEduResultLists(String plantCd, String completYn, String year, String isTypeCd, int monFlag, String gubun, DefaultParam defaultParam) throws Exception {
        return eduStatusMapper.getEduResultLists(plantCd, completYn, year, isTypeCd, monFlag, gubun, defaultParam);
    }

}
