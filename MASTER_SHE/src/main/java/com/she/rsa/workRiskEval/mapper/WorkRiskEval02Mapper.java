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
import com.she.rsa.model.WorkRiskEval02DeptPrcs;
import com.she.rsa.model.WorkRiskEval02Prcs;

@Mapper
@Repository("com.she.rsa.workRiskEval.mapper.WorkRiskEval02Mapper")
public interface WorkRiskEval02Mapper {

    /**
     * 작업위험성평가 대상 공정 설정 목록
     * 
     * @return 작업위험성평가 대상 공정설정 목록
     * @throws Exception
     */
    public List<WorkRiskEval02Prcs> getworkRiskEval02Lists(@Param("plantCd") String plantCd, @Param("deptCd") String deptCd, @Param("evalTypeCd") String evalTypeCd, @Param("startYear") String startYear, @Param("endYear") String endYear, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 작업위험성평가 대상 공정설정 평가대상공정 목록
     * 
     * @return 작업위험성평가 대상 공정 설정 평가대상공정 목록
     * @throws Exception
     */
    public List<WorkRiskEval02DeptPrcs> getWorkRiskEval02detpPrcsLists(@Param("plantCd") String plantCd, @Param("deptCd") String deptCd, @Param("evalYear") String evalYear, @Param("evalNo") String evalNo, DefaultParam defaultParam) throws Exception;

    /**
     * 작업위험성평가 대상 공정설정 부서별 공정/단위작업 목록
     * 
     * @return 작업위험성평가 대상 공정 설정 부서별 공정/단위작업 목록
     * @throws Exception
     */
    public List<WorkRiskEval02DeptPrcs> getdeptPrcsBaseInfoLists(@Param("plantCd") String plantCd, @Param("deptCd") String deptCd, @Param("processNm") String processNm, @Param("unitWorkNm") String unitWorkNm, DefaultParam defaultParam) throws Exception;

    /**
     * 작업위험성평가 대상 공정설정 기존 부서별 공정/단위작업 목록
     * 
     * @return 작업위험성평가 대상 공정 설정 기존 부서별 공정/단위작업 목록
     * @throws Exception
     */
    public List<WorkRiskEval02DeptPrcs> getdeptPrcsBaseExistInfoLists(@Param("plantCd") String plantCd, @Param("evalNo") String evalNo, @Param("deptCd") String deptCd, @Param("processNm") String processNm, @Param("unitWorkNm") String unitWorkNm, @Param("evalTypeCd") String evalTypeCd, @Param("startYear") String startYear,
            @Param("endYear") String endYear, DefaultParam defaultParam) throws Exception;

    /**
     * 작업위험성평가계획 공정설정 평가대상공정 조회
     * 
     * @param evalNo
     *            평가번호
     * @return 작업위험성평가계획 공정설정 평가대상공정 상세 조회
     * @throws Exception
     */
    public WorkRiskEval02Prcs getWorkRiskEval02Info(@Param("plantCd") String plantCd, @Param("evalYear") String evalYear, @Param("evalNo") String evalNo, @Param("deptCd") String deptCd, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 작업위험성평가계획 공정설정 평가대상공정 수정
     * 
     * @param WorkRiskEval02Prcs
     * @return WorkRiskEval02Prcs
     * @throws Exception
     */
    public int updateDeptEvalUserId(WorkRiskEval02Prcs workRiskEval02Prcs) throws Exception;

    /**
     * 작업위험성평가계획 공정설정 평가대상공정 목록 등록
     * 
     * @param WorkRiskEval02Prcs.getWorkRiskEval02DeptPrcs
     * @return WorkRiskEval02Prcs.getWorkRiskEval02DeptPrcs
     * @throws Exception
     */
    public int createWorkRiskEval02DeptPrcs(WorkRiskEval02DeptPrcs workRiskEval02DeptPrcs) throws Exception;

    /**
     * 작업위험성평가계획 공정설정 평가대상공정 목록 등록(부서공정별 단위작업 : 안전사고이력)
     * 
     * @param WorkRiskEval02Prcs.getWorkRiskEval02DeptPrcs
     * @return WorkRiskEval02Prcs.getWorkRiskEval02DeptPrcs
     * @throws Exception
     */
    public int updateUnitWorkCnt(WorkRiskEval02DeptPrcs workRiskEval02DeptPrcs) throws Exception;

    /**
     * 작업위험성평가 공정설정 평가대상공정 삭제
     * 
     * @param WorkRiskEval02DeptPrcs
     *            작업위험성평가 공정설정 대상평가 List
     * @return 작업위험성평가 공정설정 Key값
     * @throws Exception
     */
    public int deleteWorkRiskEval02DeptPrcs(@Param("plantCd") String plantCd, @Param("evalYear") String evalYear, @Param("evalNo") String evalNo, @Param("deptCd") String deptCd) throws Exception;

    public int deleteWorkRiskEval02EvalRslt(@Param("plantCd") String plantCd, @Param("evalYear") String evalYear, @Param("evalNo") String evalNo, @Param("deptCd") String deptCd) throws Exception;

}
