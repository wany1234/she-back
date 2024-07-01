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
package com.she.rsa.workRiskEval.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.common.model.DefaultParam;
import com.she.rsa.model.WorkRiskEvalPlanDeptList;
import com.she.rsa.model.WorkRiskEvalPlan;

@Mapper
@Repository("com.she.rsa.workRiskEval.mapper.WorkRiskEvalPlanMapper")
public interface WorkRiskEvalPlanMapper {

    /**
     * 작업위험성평가계획 관리 목록
     * 
     * @return 작업위험성평가 관리 목록
     * @throws Exception
     */
    public List<WorkRiskEvalPlan> getworkRiskEvalPlanLists(@Param("plantCd") String plantCd, @Param("evalNm") String evalNm, @Param("evalTypeCd") String evalTypeCd, @Param("startYear") String startYear, @Param("endYear") String endYear, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 작업위험성평가계획 관리 평가번호 채번
     * 
     * @return 작업위험성평가 관리 채번
     * @throws Exception
     */
    public String getCreateEvalNo(@Param("plantCd") String plantCd, @Param("evalYear") String evalYear) throws Exception;

    /**
     * 작업위험성평가계획 관리 등록
     * 
     * @param WorkRiskEvalPlan
     * 
     * @return WorkRiskEvalPlan
     * @throws Exception
     */
    public int createWorkRiskEvalPlan(WorkRiskEvalPlan workRiskEvalPlan) throws Exception;

    /**
     * 작업위험성평가계획 관리 수정
     * 
     * @param WorkRiskEvalPlan
     * 
     * @return WorkRiskEvalPlan
     * @throws Exception
     */
    public int updateWorkRiskEvalPlan(WorkRiskEvalPlan workRiskEvalPlan) throws Exception;

    /**
     * 작업위험성평가계획 관리 평가대상부서등록
     * 
     * @param PlanmgmtDeptList
     * 
     * @return PlanmgmtDeptList
     * @throws Exception
     */
    public int createWorkRiskEvalPlanDept(WorkRiskEvalPlanDeptList workRiskEvalPlanDeptList) throws Exception;

    /**
     * 작업위험성평가계획 관리 조회
     * 
     * @param evalNo
     *            평가번호
     * @return 작업위험성평가계획 관리 조회
     * @throws Exception
     */
    public WorkRiskEvalPlan getWorkRiskEvalPlanInfo(@Param("plantCd") String plantCd, @Param("evalYear") String evalYear, @Param("evalNo") String evalNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 작업위험성평가계획 관리 대상부서조회
     * 
     * @param evalNo
     *            평가번호
     * @return 작업위험성평가계획 관리 평가대상부서조회
     * @throws Exception
     */
    public List<WorkRiskEvalPlanDeptList> getWorkRiskEvalPlandetpLists(@Param("plantCd") String plantCd, @Param("evalYear") String evalYear, @Param("evalNo") String evalNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 작업위험성평가계획 관리 대상부서 삭제
     * 
     * @param evalNo,
     *            deptCd
     * 
     * @return
     * @throws Exception
     */
    public int deleteWorkRiskEvalPlandetpLists(@Param("plantCd") String plantCd, @Param("evalYear") String evalYear, @Param("evalNo") String evalNo) throws Exception;

    /**
     * 작업위험성평가계획 관리 삭제
     * 
     * @param evalNo
     * 
     * @return 작업위험성평가계획 관리 삭제
     * @throws Exception
     */
    public int deleteWorkRiskEvalPlan(@Param("plantCd") String plantCd, @Param("evalYear") String evalYear, @Param("evalNo") String evalNo) throws Exception;

    public int updateAppr(@Param("plantCd") String plantCd, @Param("evalYear") String evalYear, @Param("evalNo") String evalNo, @Param("apprRqstNo") int apprRqstNo, @Param("bizApprStepCd") String bizApprStepCd);

}
