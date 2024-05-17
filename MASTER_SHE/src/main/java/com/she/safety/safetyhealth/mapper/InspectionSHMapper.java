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
package com.she.safety.safetyhealth.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.common.model.DefaultParam;
import com.she.rsa.model.ResultEstimator;
import com.she.safety.model.InspectionSH;
import com.she.safety.model.InspectionSHDept;

@Mapper
@Repository("com.she.safety.safetyhealth.mapper.InspectionSHMapper")
public interface InspectionSHMapper {

    /**
     * 점검계획 관리 목록
     * 
     * @return 점검계획 관리 목록
     * @throws Exception
     */
    public List<InspectionSH> getPlanLists(@Param("plantCd") String plantCd, @Param("year") String year, @Param("regRegdemCd") String regRegdemCd, @Param("chkNm") String chkNm, @Param("procStepCd") String procStepCd, @Param("stateCd") String stateCd, @Param("mainDeptCd") String mainDeptCd, @Param("deptSubYn") String deptSubYn,
            @Param("yearChk") String yearChk, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 점검계획 관리 조회
     * 
     * @param parameter
     *            검색조건
     * @return 점검계획 관리 조회
     * @throws Exception
     */
    public InspectionSH getPlanInfo(@Param("implChkPlanNo") int implChkPlanNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 점검계획 관리 대상부서 조회
     * 
     * @param parameter
     *            검색조건
     * @return 점검계획 관리 대상부서 조회
     * @throws Exception
     */
    public List<InspectionSHDept> getPlanDeptList(@Param("implChkPlanNo") int implChkPlanNo, @Param("apprFlag") String apprFlag) throws Exception;

    /**
     * 점검계획 관리 등록
     * 
     * @param InspectionSH
     * 
     * @return InspectionSH
     * @throws Exception
     */
    public int createInspection(InspectionSH inspectionSH) throws Exception;

    /**
     * 점검계획 관리 수정
     * 
     * @param InspectionSH
     * 
     * @return InspectionSH
     * @throws Exception
     */
    public int updateInspection(InspectionSH inspectionSH) throws Exception;

    /**
     * 점검계획 관리 대상부서 삭제
     * 
     * @param assessPlanNo
     * 
     * @return
     * @throws Exception
     */
    public int deleteInspectionDept(@Param("implChkPlanNo") int implChkPlanNo, @Param("implChkDeptNo") int implChkDeptNo) throws Exception;

    /**
     * 점검계획 관리 대상부서등록
     * 
     * @param InspectionSHDept
     * 
     * @return InspectionSHDept
     * @throws Exception
     */
    public int createPlanDept(InspectionSHDept inspectionSHDept) throws Exception;

    /**
     * 점검계획 관리 대상부서수정
     * 
     * @param InspectionSHDept
     * 
     * @return InspectionSHDept
     * @throws Exception
     */
    public int updatePlanDept(InspectionSHDept inspectionSHDept) throws Exception;

    /**
     * 점검계획/결과 관리 삭제
     * 
     * @param InspectionSH
     * 
     * @return 점검계획/결과 관리 삭제
     * @throws Exception
     */
    public int deleteInspection(@Param("implChkPlanNo") int implChkPlanNo) throws Exception;

    /**
     * 점검계획 관리 결재상태수정
     * 
     * @param implChkPlanNo등등..
     * 
     * @return int
     * @throws Exception
     */
    public int updateAppr(@Param("implChkPlanNo") int implChkPlanNo, @Param("apprRqstNo") int apprRqstNo, @Param("apprStepCd") String apprStepCd);

    /**
     * 점검결과 관리 목록
     * 
     * @return 점검계획 결과 목록
     * @throws Exception
     */
    public List<InspectionSHDept> getResultLists(@Param("plantCd") String plantCd, @Param("year") String year, @Param("chkNm") String chkNm, @Param("regRegdemCd") String regRegdemCd, @Param("yearChk") String yearChk, @Param("imprChk") String imprChk, @Param("deptSubYn") String deptSubYn, @Param("deptCd") String deptCd,
            @Param("monFlag") int monFlag, @Param("gubun") String gubun, @Param("mainDeptCd") String mainDeptCd, @Param("mainDeptSubYn") String mainDeptSubYn, @Param("stateCd") String stateCd, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 점검결과 관리 조회
     * 
     * @param implChkDeptNo
     * 
     * @return 점검결과 관리 조회
     * @throws Exception
     */
    public InspectionSHDept getResultInfo(@Param("implChkDeptNo") int implChkDeptNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 점검결과 관리 평가자(내부)
     * 
     * @param implChkDeptNo
     * 
     * @return 점검결과 관리 평가자(내부)
     * @throws Exception
     */
    public List<ResultEstimator> getEstimatorIn(@Param("implChkDeptNo") int implChkDeptNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 점검결과 관리 평가자(외부)
     * 
     * @param implChkDeptNo
     * 
     * @return 점검결과 관리 평가자(외부)
     * @throws Exception
     */
    public List<ResultEstimator> getEstimatorOut(@Param("implChkDeptNo") int implChkDeptNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 점검결과 관리 수정
     * 
     * @param InspectionSHDept
     * 
     * @return 점검결과 관리 수정
     * @throws Exception
     */
    public int updateResultRslt(InspectionSHDept inspectionSHDept) throws Exception;

    /**
     * 점검결과 관리 등록
     * 
     * @param InspectionSHDept
     * 
     * @return 점검결과 관리 등록
     * @throws Exception
     */
    public int createResultRslt(InspectionSHDept inspectionSHDept) throws Exception;

    /**
     * 점검결과 관리 평가자 삭제(내부/외부)
     * 
     * @param
     * 
     * @return 점검결과 관리 평가자 삭제(내부/외부)
     * @throws Exception
     */
    public int deleteEstimator(@Param("implChkDeptNo") int implChkDeptNo) throws Exception;

    /**
     * 점검결과 관리 평가자 등록(내부)
     * 
     * @param ResultEstimator
     * 
     * @return 점검결과 관리 평가자 등록(내부)
     * @throws Exception
     */
    public int createInEstimator(ResultEstimator resultEstimator) throws Exception;

    /**
     * 점검가결과 관리 평가자 등록(외부)
     * 
     * @param ResultEstimator
     * 
     * @return 점검결과 관리 평가자 등록(외부)
     * @throws Exception
     */
    public int createOutEstimator(ResultEstimator resultEstimator) throws Exception;

    /**
     * 점검결과 관리 부서삭제
     * 
     * @param PlanmgmtDeptList
     *            점검결과 관리 부서삭제
     * @return int
     * @throws Exception
     */
    public int deleteResultDept(@Param("assessDeptNo") int assessDeptNo) throws Exception;

    /**
     * 점검결과 관리 결재상태수정
     * 
     * @param
     * 
     * @return int
     * @throws Exception
     */
    public int resultUpdateAppr(@Param("implChkDeptNo") int implChkDeptNo, @Param("apprRqstNo") int apprRqstNo, @Param("apprStepCd") String apprStepCd);

    /**
     * 점검결과 현황 목록
     * 
     * @param parameter
     *            검색조건
     * @return 점검결과 현황 목록
     * @throws Exception
     */
    public List<HashMap<String, Object>> getResultstatusList(@Param("plantCd") String plantCd, @Param("year") String year, @Param("regRegdemCd") String regRegdemCd, @Param("totalFlag") String totalFlag, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    public List<HashMap<String, Object>> getResultImprList(@Param("plantCd") String plantCd, @Param("assessYear") String assessYear, @Param("regRegdemCd") String regRegdemCd, @Param("riskType") String riskType, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    public List<HashMap<String, Object>> getResultPercentList(@Param("plantCd") String plantCd, @Param("assessYear") String assessYear, @Param("regRegdemCd") String regRegdemCd, @Param("riskType") String riskType, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 점검결과 삭제 가능 확인
     * 
     * @param parameter
     *            검색조건
     * @return 점검결과 삭제 가능 확인
     * @throws Exception
     */
    public int getResultImprStatus(@Param("implChkDeptNo") int implChkDeptNo) throws Exception;

    /**
     * 점검결과 관리 삭제
     * 
     * @param InspectionSH
     * 
     * @return 점검계획/결과 관리 삭제
     * @throws Exception
     */
    public int deletePlanmgmtDept(@Param("implChkDeptNo") int implChkDeptNo) throws Exception;

    /**
     * 점검결과 관리 삭제 update
     * 
     * @param InspectionSH
     * 
     * @return 점검계획/결과 관리 삭제 update
     * @throws Exception
     */
    public int deleteUpdateResultDept(@Param("implChkDeptNo") int implChkDeptNo) throws Exception;
}
