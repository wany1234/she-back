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

import com.she.rsa.model.DeptAssessJob;
import com.she.rsa.model.DeptAssessStep;

@Mapper
@Repository("com.she.rsa.assess.mapper.DeptAssessJobMapper")
public interface DeptAssessJobMapper {
    /**
     * 평가대상직무 KRAS 조회
     * 
     * @param assessPlanNo
     *            평가계획 번호
     * @param deptCd
     *            부서 코드
     * @return 평가대상직무 KRAS 목록
     * @throws Exception
     */
    public List<DeptAssessJob> getDeptAssessJobKRASs(@Param("assessPlanNo") int assessPlanNo,
            @Param("processCd") String processCd, @Param("assessTypeNo") int assessTypeNo) throws Exception;

    /**
     * 평가대상직무 JSA 조회
     * 
     * @param assessPlanNo
     *            평가계획 번호
     * @param deptCd
     *            부서 코드
     * @return 평가대상직무 JSA 목록
     * @throws Exception
     */
    public List<DeptAssessJob> getDeptAssessJobJSAs(@Param("assessPlanNo") int assessPlanNo,
            @Param("deptCd") String deptCd, @Param("assessTypeNo") int assessTypeNo) throws Exception;

    /**
     * 평가대상직무 등록
     * 
     * @param DeptAssessJob
     *            평가대상직무
     * @return 변경 행 수
     * @throws Exception
     */
    public int createDeptAssessJob(DeptAssessJob deptAssessJob) throws Exception;

    /**
     * 평가대상직무 삭제
     * 
     * @param assessPlanNo
     *            평가계획 번호
     * @param deptCd
     *            부서코드
     * @param jobId
     *            직무ID
     * @return 변경 행 수
     * @throws Exception
     */
    public int deleteDeptAssessJob(@Param("assessPlanNo") int assessPlanNo, @Param("processCd") String processCd,
            @Param("jobId") int jobId) throws Exception;

    /**
     * 부서별 평가대상직무단계 등록
     * 
     * @param deptAssessStep
     *            부서별_평가대상직무단계
     * @return 변경 행 수
     * @throws Exception
     */
    public int createDeptAssessStep(DeptAssessStep deptAssessStep) throws Exception;

    /**
     * 부서별 평가대상직무단계 삭제
     * 
     * @param assessPlanNo
     *            평가계획 번호
     * @param deptCd
     *            부서코드
     * @param jobStepId
     *            작업절차ID
     * @return 변경 행 수
     * @throws Exception
     */
    public int deleteDeptAssessStep(@Param("assessPlanNo") int assessPlanNo, @Param("deptCd") String deptCd,
            @Param("jobStepId") int jobStepId) throws Exception;

}
