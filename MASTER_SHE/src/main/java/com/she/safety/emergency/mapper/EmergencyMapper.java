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
package com.she.safety.emergency.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.common.model.DefaultParam;
import com.she.impr.model.ImprAct;
import com.she.safety.model.Emergency;
import com.she.safety.model.EmergencyDept;
import com.she.safety.model.EmergencyScenario;

@Mapper
@Repository("com.she.safety.emergency.mapper.EmergencyMapper")
public interface EmergencyMapper {

    /**
     * 훈련계획 관리 목록 조회
     *
     * @param parameter
     * @return 훈련계획 관리 목록 조회
     * @throws Exception
     */
    public List<Emergency> getEmergencyLists(@Param("plantCd") String plantCd, @Param("startDt") String startDt, @Param("endDt") String endDt, @Param("trainTypeCd") String trainTypeCd, @Param("trainNm") String trainNm, @Param("trainPlace") String trainPlace, @Param("deptCd") String deptCd, @Param("deptSubYn") String deptSubYn,
            @Param("procStepCd") String procStepCd, @Param("stateCd") String stateCd, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 훈련계획 관리 계획 조회
     *
     * @param parameter
     * @return 훈련계획 관리 계획 조회
     * @throws Exception
     */
    public Emergency getEmergencyInfo(@Param("safTrainPlanNo") int safTrainPlanNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 훈련계획 관리 계획 부서조회
     *
     * @param parameter
     * @return 훈련계획 관리 계획 부서조회
     * @throws Exception
     */
    public List<EmergencyDept> getEmergencyDeptLists(@Param("safTrainPlanNo") int safTrainPlanNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 훈련계획 대상부서 목록
     *
     * @param parameter
     * @return 훈련계획 대상부서 목록
     * @throws Exception
     */
    public List<EmergencyDept> getEmergencyDeptList(@Param("safTrainPlanNo") int safTrainPlanNo, @Param("apprFlag") String apprFlag, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 훈련계획 관리 계획 부서조회
     *
     * @param parameter
     * @return 훈련계획 관리 계획 부서조회
     * @throws Exception
     */
    public List<EmergencyScenario> getEmergencyScenarioLists(@Param("safTrainPlanNo") int safTrainPlanNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 훈련계획 관리 계획 등록
     *
     * @param parameter
     * @return 훈련계획 관리 계획 등록
     * @throws Exception
     */
    public int createEmergency(Emergency emergency) throws Exception;

    /**
     * 훈련계획 관리 계획 수정
     *
     * @param parameter
     * @return 훈련계획 관리 계획 수정
     * @throws Exception
     */
    public int updateEmergency(Emergency emergency) throws Exception;

    /**
     * 훈련계획 관리 계획 대상부서 등록
     *
     * @param parameter
     * @return 훈련계획 관리 계획 대상부서 등록
     * @throws Exception
     */
    public int createEmergencyDept(EmergencyDept emergencyDept) throws Exception;

    /**
     * 훈련계획 관리 계획 시나리오 등록
     *
     * @param parameter
     * @return 훈련계획 관리 계획 시나리오 등록
     * @throws Exception
     */
    public int createEmergencyScenario(EmergencyScenario emergencyDept) throws Exception;

    /**
     * 훈련계획 관리 계획 삭제
     *
     * @param parameter
     * @return 훈련계획 관리 계획 삭제
     * @throws Exception
     */
    public int deleteEmergency(@Param("safTrainPlanNo") int safTrainPlanNo) throws Exception;

    /**
     * 훈련계획 관리 계획 대상부서 삭제
     *
     * @param parameter
     * @return 훈련계획 관리 계획 대상부서 삭제
     * @throws Exception
     */
    public int deleteEmergencyDept(@Param("safTrainPlanNo") int safTrainPlanNo) throws Exception;

    /**
     * 훈련계획 관리 계획 시나리오 삭제
     *
     * @param parameter
     * @return 훈련계획 관리 계획 시나리오 삭제
     * @throws Exception
     */
    public int deleteEmergencyScenario(@Param("safTrainPlanNo") int safTrainPlanNo) throws Exception;

    /**
     * 훈련계획 관리 대상부서 삭제
     *
     * @param parameter
     * @return 훈련계획 관리 대상부서 삭제
     * @throws Exception
     */
    public int deleteEachEmergencyDept(@Param("safTrainDeptRsltNo") int safTrainDeptRsltNo) throws Exception;

    /**
     * 훈련계획 관리 계획 완료처리
     *
     * @param parameter
     * @return 훈련계획 관리 계획 완료처리
     * @throws Exception
     */
    public int updateEmergencyComplete(@Param("safTrainPlanNo") int safTrainPlanNo) throws Exception;

    /**
     * 훈련결과 관리 목록 조회
     *
     * @param parameter
     * @return 훈련결과 관리 목록 조회
     * @throws Exception
     */
    public List<EmergencyDept> getEmergencyResultLists(@Param("plantCd") String plantCd, @Param("startDt") String startDt, @Param("endDt") String endDt, @Param("trainTypeCd") String trainTypeCd, @Param("trainNm") String trainNm, @Param("trainPlace") String trainPlace, @Param("deptCd") String deptCd, @Param("deptSubYn") String deptSubYn,
            @Param("procStepCd") String procStepCd, @Param("stateCd") String stateCd, @Param("monFlag") int monFlag, @Param("year") String year, @Param("gubun") String gubun, @Param("imprChk") String imprChk, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 훈련결과 관리 계획 조회
     *
     * @param parameter
     * @return 훈련결과 관리 계획 조회
     * @throws Exception
     */
    public EmergencyDept getEmergencyResultInfo(@Param("safTrainDeptRsltNo") int safTrainDeptRsltNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 훈련결과 수정
     *
     * @param parameter
     * @return 훈련결과 수정
     * @throws Exception
     */
    public int updateEmergencyResult(EmergencyDept emergencyDept) throws Exception;

    /**
     * 훈련결과 관리 완료처리
     *
     * @param parameter
     * @return 훈련결과 관리 완료처리
     * @throws Exception
     */
    public int updateEmergencyResultComplete(@Param("safTrainDeptRsltNo") int safTrainDeptRsltNo) throws Exception;

    /**
     * 훈련결과 현황 목록
     * 
     * @param parameter
     *            검색조건
     * @return 훈련결과 현황 목록현황 목록
     * @throws Exception
     */
    public List<HashMap<String, Object>> getResultstatusList(@Param("plantCd") String plantCd, @Param("year") String year, @Param("trainTypeCd") String trainTypeCd, @Param("totalFlag") String totalFlag) throws Exception;

    public List<ImprAct> getEmergencyImprList(@Param("plantCd") String plantCd, @Param("monFlag") int monFlag, @Param("trainTypeCd") String trainTypeCd, @Param("apprFlag") String apprFlag, @Param("defaultParam") DefaultParam defaultParam) throws Exception;
}
