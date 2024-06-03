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
package com.she.rsa.workRiskEval.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.common.model.DefaultParam;
import com.she.rsa.model.WorkRiskEval09Line;
import com.she.rsa.model.WorkRiskEval09Process;
import com.she.rsa.workRiskEval.mapper.WorkRiskEval09Mapper;

/**
 * 작업위험성평가 > 기준정보 > 단위작업
 */
@Service
public class WorkRiskEval09Service {

    @Autowired
    private WorkRiskEval09Mapper workRiskEval09Mapper;

    /**
     * 작업위험성평가 공정 목록
     * 
     * @param parameter
     *            검색조건
     * @return 작업위험성평가 공정 목록
     * @throws Exception
     */
    public List<WorkRiskEval09Process> getworkRiskEval09Lists(String plantCd, String prcsLvlCd, String processNm, DefaultParam defaultParam) throws Exception {
        return workRiskEval09Mapper.getworkRiskEval09Lists(plantCd, prcsLvlCd, processNm, defaultParam);
    }

    /**
     * 작업위험성평가 공정 등록
     * 
     * @param WorkRiskEval09Process
     * @return 작업위험성평가 공정 Key값
     * @throws Exception
     */
    public String createWorkRiskEval09(WorkRiskEval09Process workRiskEval09Process) throws Exception {
        workRiskEval09Mapper.createWorkRiskEval09(workRiskEval09Process);

        return workRiskEval09Process.getProcessCd();
    }

    /**
     * 작업위험성평가 공정 수정
     * 
     * @param WorkRiskEval09Process
     * @return 작업위험성평가 공정 Key값
     * @throws Exception
     */
    public String updateWorkRiskEval09(WorkRiskEval09Process workRiskEval09Process) throws Exception {
        workRiskEval09Mapper.updateWorkRiskEval09(workRiskEval09Process);

        return workRiskEval09Process.getProcessCd();
    }

    /**
     * 작업위험성평가 공정 상세
     * 
     * @param plantCd,
     *            processCd 공정코드
     * @return 작업위험성평가 공정 상세조회
     * @throws Exception
     */
    public WorkRiskEval09Process getWorkRiskEval09Info(String plantCd, String processCd, DefaultParam defaultParam) throws Exception {
        return workRiskEval09Mapper.getWorkRiskEval09Info(plantCd, processCd, defaultParam);
    }

    /**
     * 작업위험성평가 공정 라인 조회
     * 
     * @return 작업위험성평가 공정 라인 목록
     * @throws Exception
     */
    public List<WorkRiskEval09Line> getworkRiskEval09LineLists(String plantCd, String processClsCd, String processNm, DefaultParam defaultParam) throws Exception {
        return workRiskEval09Mapper.getworkRiskEval09LineLists(plantCd, processClsCd, processNm, defaultParam);
    }

    /**
     * 작업위험성평가 상위공정 코드조회
     * 
     * @param plantCd,
     *            prcsLvlCd 레벨
     * @return 작업위험성평가 상위공정 코드조회
     * @throws Exception
     */
    public List<WorkRiskEval09Process> getWorkRiskEval09UpProcess(String plantCd, String prcsLvlCd, DefaultParam defaultParam) throws Exception {

        List<WorkRiskEval09Process> getWorkRiskEval09UpProcessList = null;

        if ("2".equals(prcsLvlCd)) {
            getWorkRiskEval09UpProcessList = workRiskEval09Mapper.getWorkRiskEval09UpProcessLvl2(plantCd, prcsLvlCd, defaultParam);
        } else if ("3".equals(prcsLvlCd)) {
            getWorkRiskEval09UpProcessList = workRiskEval09Mapper.getWorkRiskEval09UpProcessLvl3(plantCd, prcsLvlCd, defaultParam);
        }

        return getWorkRiskEval09UpProcessList;
    }

}
