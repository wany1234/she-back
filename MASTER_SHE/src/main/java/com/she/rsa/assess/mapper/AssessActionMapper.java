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

import java.util.LinkedHashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.rsa.model.AssessAction;
import com.she.rsa.model.RiskAssess;
import com.she.rsa.model.RiskMatrix;

@Mapper
@Repository("com.she.rsa.assess.mapper.AssessActionMapper")
public interface AssessActionMapper {

    /**
     * 위험성평가 조회
     *
     * @param jobId
     *            직무번호
     * @param assessPlanNo
     *            평가계획번호
     * @return 위험성평가 목록
     * @throws Exception
     */
    public List<RiskAssess> getRiskAssesses(@Param("jobId") int jobId, @Param("assessPlanNo") String assessPlanNo) throws Exception;

    /**
     * 위험성평가 항목 생성 (KRAS)
     *
     * @param riskAssess
     *            위험성평가 항목
     * @return 변경 행 수
     * @throws Exception
     */
    public int createRiskKras(RiskAssess riskAssess) throws Exception;

    /**
     * 위험성평가 항목 수정 (KRAS)
     *
     * @param riskAssess
     *            위험성평가 항목
     * @return 변경 행 수
     * @throws Exception
     */
    public int updateRiskKras(RiskAssess riskAssess) throws Exception;

    public RiskAssess getRiskKras(@Param("assessPlanNo") int assessPlanNo, @Param("processCd") String processCd, @Param("jobId") String jobId, @Param("jobStepId") String jobStepId, @Param("riskHazardNo") String riskHazardNo) throws Exception;

    /**
     * 위험 Matrix 빈도 조회
     *
     * @param assessTypeNo
     *            평가기법 번호
     * @return 위험 Matrix 빈도 목록
     * @throws Exception
     */
    public List<RiskMatrix> getFrequencyRiskMatrixes(@Param("assessTypeNo") int assessTypeNo) throws Exception;

    /**
     * 위험 Matrix 강도 조회
     *
     * @param assessTypeNo
     *            평가기법 번호
     * @return 위험 Matrix 강도 목록
     * @throws Exception
     */
    public List<RiskMatrix> getStrongRiskMatrixes(@Param("assessTypeNo") int assessTypeNo) throws Exception;

    /**
     * 위험 Matrix 위험도 조회
     *
     * @param assessTypeNo
     *            평가기법 번호
     * @return 위험 Matrix 위험도 목록
     * @throws Exception
     */
    public List<RiskMatrix> getRiskMatrixes(@Param("assessTypeNo") int assessTypeNo) throws Exception;

    /**
     * 평가직무 job 조회 (JSA)
     *
     * @param assessPlanNo
     *            평가계획 번호
     * @param deptCd
     *            부서코드
     * @return 평가직무 목록
     * @throws Exception
     */
    public List<AssessAction> getAssessActionsJsaJob(@Param("assessPlanNo") String assessPlanNo, @Param("plantCd") String plantCd, @Param("deptCd") String deptCd, @Param("processCd") String processCd, @Param("jobNm") String jobNm) throws Exception;

    /**
     * 평가직무 조회 (JSA)
     *
     * @param assessPlanNo
     *            평가계획 번호
     * @param deptCd
     *            부서코드
     * @return 평가직무단계 목록
     * @throws Exception
     */
    public List<AssessAction> getAssessActionsJsa(@Param("assessPlanNo") int assessPlanNo, @Param("deptCd") String deptCd, @Param("jobId") int jobId, @Param("processCd") String processCd) throws Exception;

    /**
     * 위험성평가 조회 (JSA)
     *
     * @param jobId
     *            직무번호
     * @return 위험성평가 목록
     * @throws Exception
     */
    public List<RiskAssess> getRiskAssessesJsa(@Param("jobId") int jobId, @Param("jobStepNo") int jobStepNo, @Param("assessPlanNo") int assessPlanNo) throws Exception;

    /**
     * 위험성평가 항목 생성 (JSA)
     *
     * @param riskAssess
     *            위험성평가 항목
     * @return 변경 행 수
     * @throws Exception
     */
    public int createRiskJsa(RiskAssess riskAssess) throws Exception;

    /**
     * 위험성평가 항목 수정 (JSA)
     *
     * @param riskAssess
     *            위험성평가 항목
     * @return 변경 행 수
     * @throws Exception
     */
    public int updateRiskJsa(RiskAssess riskAssess) throws Exception;

    /**
     * 위험성평가 완료 처리
     *
     * @param assessAction
     *            위험성평가 항목
     * @return 위험성평가 번호
     * @throws Exception
     */
    public int updateAssessStep(AssessAction assessAction) throws Exception;

    public RiskMatrix getMaxRiskMatrix(@Param("assessTypeNo") String assessTypeNo) throws Exception;

    public List<LinkedHashMap<String, Object>> getAssessExcel(@Param("assessPlanNo") String assessPlanNo, @Param("processCd") String processCd, @Param("plantCd") String plantCd) throws Exception;

}
