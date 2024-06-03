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
import com.she.rsa.model.WorkRiskEval09Line;
import com.she.rsa.model.WorkRiskEval09Process;

@Mapper
@Repository("com.she.rsa.workRiskEval.mapper.WorkRiskEval09Mapper")
public interface WorkRiskEval09Mapper {

    /**
     * 작업위험성평가 공정 목록
     * 
     * @return 작업위험성평가 공정 목록
     * @throws Exception
     */
    public List<WorkRiskEval09Process> getworkRiskEval09Lists(@Param("plantCd") String plantCd, @Param("prcsLvlCd") String prcsLvlCd, @Param("processNm") String processNm, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 작업위험성평가 공정 등록
     * 
     * @param WorkRiskEval09Process
     * @return 작업위험성평가 공정 Key값
     * @throws Exception
     */
    public int createWorkRiskEval09(WorkRiskEval09Process workRiskEval09Process) throws Exception;

    /**
     * 작업위험성평가 공정 수정
     * 
     * @param WorkRiskEval09Process
     * @return 작업위험성평가 공정 Key값
     * @throws Exception
     */
    public int updateWorkRiskEval09(WorkRiskEval09Process workRiskEval09Process) throws Exception;

    /**
     * 작업위험성평가 공정 상세
     * 
     * @param plantCd,
     *            processCd 공정코드
     * @return 작업위험성평가 공정 상세조회
     * @throws Exception
     */
    public WorkRiskEval09Process getWorkRiskEval09Info(@Param("plantCd") String plantCd, @Param("processCd") String processCd, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 작업위험성평가 공정 라인 조회
     * 
     * @return 작업위험성평가 공정 라인 목록
     * @throws Exception
     */
    public List<WorkRiskEval09Line> getworkRiskEval09LineLists(@Param("plantCd") String plantCd, @Param("processClsCd") String processClsCd, @Param("processNm") String processNm, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 작업위험성평가 상위공정 코드조회
     * 
     * @param plantCd,
     *            prcsLvlCd 레벨
     * @return 작업위험성평가 상위공정 코드조회
     * @throws Exception
     */
    public List<WorkRiskEval09Process> getWorkRiskEval09UpProcessLvl2(@Param("plantCd") String plantCd, @Param("prcsLvlCd") String prcsLvlCd, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 작업위험성평가 상위공정 코드조회
     * 
     * @param plantCd,
     *            prcsLvlCd 레벨
     * @return 작업위험성평가 상위공정 코드조회
     * @throws Exception
     */
    public List<WorkRiskEval09Process> getWorkRiskEval09UpProcessLvl3(@Param("plantCd") String plantCd, @Param("prcsLvlCd") String prcsLvlCd, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

}
