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
import com.she.rsa.model.AssessPlan;

@Mapper
@Repository("com.she.rsa.assess.mapper.AssessPlanMapper")
public interface AssessPlanMapper {

    /**
     * 평가계획 조회
     * 
     * @param chkKRASYn
     *            KRAS yn
     * @param chkJSAYn
     *            JSA yn
     * @param chkCHARMYn
     *            CHARM yn
     * @param assessNm
     *            평가명
     * @param regRegdem
     *            정시/수기
     * @param assessDesc
     *            상세내용
     * @param assessStatus
     *            진행상태
     * @param startYear
     *            평가년도 시작일
     * @param endYear
     *            평가년도 종료일
     * @return 평가계획 목록
     * @throws Exception
     */
    public List<AssessPlan> getAssessPlans(@Param("plantCd") String plantCd, @Param("deptCd") String deptCd, @Param("deptSubYn") String deptSubYn, @Param("userId") String userId, @Param("assessStatus") String assessStatus, @Param("startDate") String startDate, @Param("endDate") String endDate, @Param("assessGroupCd") String assessGroupCd,
            @Param("assessTypeCd") String assessTypeCd, @Param("userNm") String userNm, @Param("assessNm") String assessNm, @Param("regRegdem") String regRegdem, @Param("startYear") String startYear, @Param("endYear") String endYear, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 평가계획 조회
     * 
     * @param chkKRASYn
     *            KRAS yn
     * @param chkJSAYn
     *            JSA yn
     * @param chkCHARMYn
     *            CHARM yn
     * @param assessNm
     *            평가명
     * @param regRegdem
     *            정시/수기
     * @param assessDesc
     *            상세내용
     * @param assessStatus
     *            진행상태
     * @param startYear
     *            평가년도 시작일
     * @param endYear
     *            평가년도 종료일
     * @return 평가계획 목록
     * @throws Exception
     */
    public List<AssessPlan> getAssessPlanResults(@Param("plantCd") String plantCd, @Param("assessStatus") String assessStatus, @Param("userId") String userId, @Param("deptCd") String deptCd, @Param("deptSubYn") String deptSubYn, @Param("startDate") String startDate, @Param("endDate") String endDate, @Param("assessGroupCd") String assessGroupCd,
            @Param("assessTypeCd") String assessTypeCd, @Param("userNm") String userNm, @Param("assessNm") String assessNm, @Param("regRegdem") String regRegdem, @Param("startYear") String startYear, @Param("endYear") String endYear, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 평가계획 상세 조회
     * 
     * @param assessPlanNo
     *            평가계획 번호
     * @return 평가계획
     * @throws Exception
     */
    public AssessPlan getAssessPlan(@Param("assessPlanNo") int assessPlanNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 평가계획 신규등록
     * 
     * @param AssessPlan
     *            평가계획
     * @return 변경 행 수
     * @throws Exception
     */
    public int createAssessPlan(AssessPlan assessPlan) throws Exception;

    /**
     * 평가계획 수정
     * 
     * @param AssessPlan
     *            평가계획
     * @return 변경 행 수
     * @throws Exception
     */
    public int updateAssessPlan(AssessPlan assessPlan) throws Exception;

    /**
     * 결재 단계 수정
     * 
     * @param AssessPlan
     *            평가계획
     * @return 변경 행 수
     * @throws Exception
     */
    public int updateAssessPlanApprStep(AssessPlan assessPlan) throws Exception;

}
