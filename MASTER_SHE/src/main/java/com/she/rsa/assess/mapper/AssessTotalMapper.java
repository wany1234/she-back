/**
 * Copyright (C) 2019, 2019 All Right Reserved, http://www.yullin.com/
 *
 * SHE Business can not be copied and/or distributed without the express
 * permission of Yullin Technologies
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 */
package com.she.rsa.assess.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.common.model.DefaultParam;
import com.she.impr.model.ImprAct;
import com.she.rsa.model.AssessTotal;

@Mapper
@Repository("com.she.rsa.assess.mapper.AssessTotalMapper")
public interface AssessTotalMapper {
    /**
     * 평가대상 Kras 목록 조회 (공정)
     * 
     * @param assessNm
     *            평가명
     * @param startYear
     *            평가년도 시작일
     * @param endYear
     *            평가년도 종료일
     * @return 평가대상 목록
     * @throws Exception
     */
    public List<AssessTotal> getAssessTotalKRASProcess(@Param("assessNm") String assessNm, @Param("startYear") String startYear, @Param("endYear") String endYear, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 평가대상 Kras 목록 조회 (부서)
     * 
     * @param assessNm
     *            평가명
     * @param startYear
     *            평가년도 시작일
     * @param endYear
     *            평가년도 종료일
     * @return 평가대상 목록
     * @throws Exception
     */
    public List<AssessTotal> getAssessTotalKRASDept(@Param("assessNm") String assessNm, @Param("startYear") String startYear, @Param("endYear") String endYear, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 개선진행현황 조회
     * 
     * @param assessPlanNo
     *            평가계획 번호
     * @param deptCd
     *            부서코드
     * @return 개선진행현황 목록
     * @throws Exception
     */
    public List<ImprAct> getImprActs(@Param("assessPlanNo") String assessPlanNo, @Param("deptCd") String deptCd, @Param("processCd") String processCd, @Param("assessTypeNm") String assessTypeNm, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 평가대상 Jsa 목록 조회 (공정)
     * 
     * @param assessNm
     *            평가명
     * @param startYear
     *            평가년도 시작일
     * @param endYear
     *            평가년도 종료일
     * @return 평가대상 목록
     * @throws Exception
     */
    public List<AssessTotal> getAssessTotalJSAProcess(@Param("plantCd") String plantCd, @Param("userId") String userId, @Param("assessNm") String assessNm, @Param("startYear") String startYear, @Param("endYear") String endYear, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 평가대상 Jsa 목록 조회 (부서)
     * 
     * @param assessNm
     *            평가명
     * @param startYear
     *            평가년도 시작일
     * @param endYear
     *            평가년도 종료일
     * @return 평가대상 목록
     * @throws Exception
     */
    public List<AssessTotal> getAssessTotalJSADept(@Param("plantCd") String plantCd, @Param("userId") String userId, @Param("assessNm") String assessNm, @Param("startYear") String startYear, @Param("endYear") String endYear, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 평가대상 CHARM 목록 조회 (공정)
     * 
     * @param assessNm
     *            평가명
     * @param startYear
     *            평가년도 시작일
     * @param endYear
     *            평가년도 종료일
     * @return 평가대상 목록
     * @throws Exception
     */
    public List<AssessTotal> getAssessTotalCHARMProcess(@Param("plantCd") String plantCd, @Param("assessNm") String assessNm, @Param("startYear") String startYear, @Param("endYear") String endYear, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 평가대상 CHARM 목록 조회 (부서)
     * 
     * @param assessNm
     *            평가명
     * @param startYear
     *            평가년도 시작일
     * @param endYear
     *            평가년도 종료일
     * @return 평가대상 목록
     * @throws Exception
     */
    public List<AssessTotal> getAssessTotalCHARMDept(@Param("plantCd") String plantCd, @Param("assessNm") String assessNm, @Param("startYear") String startYear, @Param("endYear") String endYear, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 평가대상 HAZOP 목록 조회 (부서)
     * 
     * @param assessNm
     *            평가명
     * @param startYear
     *            평가년도 시작일
     * @param endYear
     *            평가년도 종료일
     * @return 평가대상 목록
     * @throws Exception
     */
    public List<AssessTotal> getAssessTotalHAZOPs(@Param("plantCd") String plantCd, @Param("assessNm") String assessNm, @Param("startYear") String startYear, @Param("endYear") String endYear, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 개선진행현황 조회
     * 
     * @param assessPlanNo
     *            평가계획 번호
     * @param deptCd
     *            부서코드
     * @param processNo
     *            공정번호
     * @return 개선진행현황 목록
     * @throws Exception
     */
    public List<AssessTotal> getAssessJob(@Param("assessPlanNo") String assessPlanNo, @Param("deptCd") String deptCd, @Param("processCd") String processCd, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

}
