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
package com.she.rsa.planmgmt.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.common.model.DefaultParam;
import com.she.rsa.model.Planmgmt;
import com.she.rsa.model.PlanmgmtDeptList;
import com.she.rsa.model.ResultEstimator;

@Mapper
@Repository("com.she.rsa.planmgmt.mapper.PlanmgmtMapper")
public interface PlanmgmtMapper {

    /**
     * 평가계획 관리 목록
     * 
     * @return 평가계획 관리 목록
     * @throws Exception
     */
    public List<Planmgmt> getPlanmgmtLists(@Param("plantCd") String plantCd, @Param("assessYear") String assessYear, @Param("assessTypeCd") String assessTypeCd, @Param("regRegdemCd") String regRegdemCd, @Param("yearChk") String yearChk, @Param("assessNm") String assessNm, @Param("riskType") String riskType, @Param("stateCd") String stateCd,
            @Param("mainDeptCd") String mainDeptCd, @Param("deptSubYn") String deptSubYn, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 평가계획 관리 조회
     * 
     * @param assessPlanNo
     *            평가계획번호
     * @return 평가계획 관리 조회
     * @throws Exception
     */
    public Planmgmt getPlanmgmtInfo(@Param("assessPlanNo") int assessPlanNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 평가계획 관리 대상부서조회
     * 
     * @param assessPlanNo
     *            평가계획번호
     * @return 평가계획 관리 대상부서조회
     * @throws Exception
     */
    public List<PlanmgmtDeptList> getPlanmgmtDeptList(@Param("assessPlanNo") int assessPlanNo, @Param("apprFlag") String apprFlag) throws Exception;

    /**
     * 평가계획 관리 등록
     * 
     * @param Planmgmt
     * 
     * @return Planmgmt
     * @throws Exception
     */
    public int createPlanmgmt(Planmgmt planmgmt) throws Exception;

    /**
     * 평가계획 관리 수정
     * 
     * @param Planmgmt
     * 
     * @return Planmgmt
     * @throws Exception
     */
    public int updatePlanmgmt(Planmgmt planmgmt) throws Exception;

    /**
     * 평가계획 관리 대상부서 삭제
     * 
     * @param assessPlanNo
     * 
     * @return
     * @throws Exception
     */
    public int deletePlanmgmtDept(@Param("assessPlanNo") int assessPlanNo, @Param("assessDeptNo") int assessDeptNo) throws Exception;

    /**
     * 평가계획 관리 대상부서등록
     * 
     * @param PlanmgmtDeptList
     * 
     * @return PlanmgmtDeptList
     * @throws Exception
     */
    public int createPlanmgmtDept(PlanmgmtDeptList planmgmt) throws Exception;

    /**
     * 평가계획 관리 대상부서수정
     * 
     * @param PlanmgmtDeptList
     * 
     * @return PlanmgmtDeptList
     * @throws Exception
     */
    public int updatePlanmgmtDept(PlanmgmtDeptList planmgmt) throws Exception;

    /**
     * 평가계획/결과 관리 삭제
     * 
     * @param Planmgmt
     * 
     * @return 평가계획/결과 관리 삭제
     * @throws Exception
     */
    public int deletePlanmgmt(@Param("assessPlanNo") int assessPlanNo) throws Exception;

    /**
     * 평가계획 관리 결재상태수정
     * 
     * @param assessPlanNo등등..
     * 
     * @return int
     * @throws Exception
     */
    public int updateAppr(@Param("assessPlanNo") int assessPlanNo, @Param("apprRqstNo") int apprRqstNo, @Param("apprStepCd") String apprStepCd);

    /**
     * 평가결과 관리 목록
     * 
     * @return 평가계획 결과 목록
     * @throws Exception
     */
    public List<PlanmgmtDeptList> getResultmgmtLists(@Param("plantCd") String plantCd, @Param("assessYear") String assessYear, @Param("assessTypeCd") String assessTypeCd, @Param("regRegdemCd") String regRegdemCd, @Param("yearChk") String yearChk, @Param("assessNm") String assessNm, @Param("imprChk") String imprChk,
            @Param("deptSubYn") String deptSubYn, @Param("deptCd") String deptCd, @Param("riskType") String riskType, @Param("monFlag") int monFlag, @Param("gubun") String gubun, @Param("mainDeptCd") String mainDeptCd, @Param("mainDeptSubYn") String mainDeptSubYn, @Param("stateCd") String stateCd, @Param("defaultParam") DefaultParam defaultParam)
            throws Exception;

    /**
     * 평가결과 관리 조회
     * 
     * @param assessPlanNo,
     *            assessDeptNo 평가계획번호
     * @return 평가결과 관리 조회
     * @throws Exception
     */
    public PlanmgmtDeptList getResultmgmtInfo(@Param("assessDeptNo") int assessDeptNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 평가결과 관리 평가자(내부)
     * 
     * @param assessPlanNo,
     *            assessDeptNo 평가계획번호
     * @return 평가결과 관리 평가자(내부)
     * @throws Exception
     */
    public List<ResultEstimator> getEstimatorIn(@Param("assessDeptNo") int assessDeptNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 평가결과 관리 평가자(외부)
     * 
     * @param assessPlanNo,
     *            assessDeptNo 평가계획번호
     * @return 평가결과 관리 평가자(외부)
     * @throws Exception
     */
    public List<ResultEstimator> getEstimatorOut(@Param("assessDeptNo") int assessDeptNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 평가결과 관리 수정
     * 
     * @param PlanmgmtDeptList
     * 
     * @return 평가결과 관리 수정
     * @throws Exception
     */
    public int updateAssessRslt(PlanmgmtDeptList planmgmtDeptInfo) throws Exception;

    /**
     * 평가결과 관리 등록
     * 
     * @param PlanmgmtDeptList
     * 
     * @return 평가결과 관리 등록
     * @throws Exception
     */
    public int createAssessRslt(PlanmgmtDeptList planmgmtDeptInfo) throws Exception;

    /**
     * 평가결과 관리 평가자 삭제(내부/외부)
     * 
     * @param assessDeptNo
     * 
     * @return 평가결과 관리 평가자 삭제(내부/외부)
     * @throws Exception
     */
    public int deleteEstimator(@Param("assessDeptNo") int assessDeptNo) throws Exception;

    /**
     * 평가결과 관리 평가자 등록(내부)
     * 
     * @param ResultEstimator
     * 
     * @return 평가결과 관리 평가자 등록(내부)
     * @throws Exception
     */
    public int createInEstimator(ResultEstimator resultEstimator) throws Exception;

    /**
     * 평가결과 관리 평가자 등록(외부)
     * 
     * @param ResultEstimator
     * 
     * @return 평가결과 관리 평가자 등록(외부)
     * @throws Exception
     */
    public int createOutEstimator(ResultEstimator resultEstimator) throws Exception;

    /**
     * 평가결과 관리 부서삭제
     * 
     * @param PlanmgmtDeptList
     *            평가결과 관리 부서삭제
     * @return int
     * @throws Exception
     */
    public int deleteResultDept(@Param("assessDeptNo") int assessDeptNo) throws Exception;

    /**
     * 평가결과 관리 결재상태수정
     * 
     * @param assessDeptNo등등..
     * 
     * @return int
     * @throws Exception
     */
    public int resultUpdateAppr(@Param("assessDeptNo") int assessDeptNo, @Param("apprRqstNo") int apprRqstNo, @Param("apprStepCd") String apprStepCd);

    /**
     * 평가결과 현황 목록
     * 
     * @param parameter
     *            검색조건
     * @return 평가결과 현황 목록
     * @throws Exception
     */
    public List<HashMap<String, Object>> getResultstatusList(@Param("plantCd") String plantCd, @Param("assessYear") String assessYear, @Param("regRegdemCd") String regRegdemCd, @Param("riskType") String riskType, @Param("totalFlag") String totalFlag, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    public List<HashMap<String, Object>> getResultImprList(@Param("plantCd") String plantCd, @Param("assessYear") String assessYear, @Param("regRegdemCd") String regRegdemCd, @Param("riskType") String riskType, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    public List<HashMap<String, Object>> getResultPercentList(@Param("plantCd") String plantCd, @Param("assessYear") String assessYear, @Param("regRegdemCd") String regRegdemCd, @Param("riskType") String riskType, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 평가계획 삭제 가능 확인
     * 
     * @param parameter
     *            검색조건
     * @return 평가계획 삭제 가능 확인
     * @throws Exception
     */
    public int getPlanmgmtStatus(@Param("assessPlanNo") int assessPlanNo) throws Exception;

    /**
     * 평가결과 삭제 가능 확인
     * 
     * @param parameter
     *            검색조건
     * @return 평가결과 삭제 가능 확인
     * @throws Exception
     */
    public int getPlanmgmtImprStatus(@Param("assessDeptNo") int assessDeptNo) throws Exception;

    /**
     * 평가결과 관리 삭제
     * 
     * @param Planmgmt
     * 
     * @return 평가계획/결과 관리 삭제
     * @throws Exception
     */
    public int deletePlanmgmtDept(@Param("assessDeptNo") int assessDeptNo) throws Exception;

    /**
     * 평가결과 관리 삭제 update
     * 
     * @param Planmgmt
     * 
     * @return 평가계획/결과 관리 삭제 update
     * @throws Exception
     */
    public int deleteUpdatePlanmgmtDept(@Param("assessDeptNo") int assessDeptNo) throws Exception;
}
