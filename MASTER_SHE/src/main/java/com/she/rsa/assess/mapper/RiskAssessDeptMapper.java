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

import com.she.rsa.model.RiskAssessDept;

@Mapper
@Repository("com.she.rsa.assess.mapper.RiskAssessDeptMapper")
public interface RiskAssessDeptMapper {
    /**
     * 대상부서 조회
     * 
     * @param assessPlanNo
     *            평가계획 번호
     * @return 대상부서 목록
     * @throws Exception
     */
    public List<RiskAssessDept> getRiskAssessDepts(@Param("assessPlanNo") int assessPlanNo) throws Exception;

    /**
     * 대상부서(CHARM) 조회
     * 
     * @param assessPlanNo
     *            평가계획 번호
     * @return 대상부서 목록
     * @throws Exception
     */
    public List<RiskAssessDept> getRiskAssessDeptCHARMs(@Param("assessPlanNo") int assessPlanNo) throws Exception;

    /**
     * 대상부서 상세 조회
     * 
     * @param assessPlanNo
     *            평가계획 번호
     * @param deptCd
     *            부서 코드
     * @param userId
     *            부서담당자ID
     * @return 대상부서
     * @throws Exception
     */
    public RiskAssessDept getRiskAssessDept(@Param("assessPlanNo") int assessPlanNo,
            @Param("processCd") String processCd, @Param("userId") String userId) throws Exception;
    // public RiskAssessDept getRiskAssessDept(@Param("assessPlanNo") int
    // assessPlanNo, @Param("deptCd") String deptCd,
    // @Param("userId") String userId) throws Exception;

    /**
     * 대상부서 신규등록
     * 
     * @param AssessPlan
     *            대상부서
     * @return 변경 행 수
     * @throws Exception
     */
    public int createRiskAssessDept(RiskAssessDept riskAssessDept) throws Exception;

    /**
     * 대상부서 수정
     * 
     * @param AssessPlan
     *            대상부서
     * @return 변경 행 수
     * @throws Exception
     */
    public int updateRiskAssessDept(RiskAssessDept riskAssessDept) throws Exception;

    /**
     * 대상부서 삭제
     * 
     * @param assessPlanNo
     *            평가계획 번호
     * @param deptCd
     *            부서 코드
     * @return 변경 행 수
     * @throws Exception
     */
    public int deleteRiskAssessDept(@Param("assessPlanNo") int assessPlanNo, @Param("processCd") String processCd)
            throws Exception;

}
