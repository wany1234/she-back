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
import com.she.rsa.model.WorkRiskEval11UnitWork;
import com.she.rsa.workRiskEval.mapper.WorkRiskEval11Mapper;

/**
 * 작업위험성평가 > 기준정보 > 단위작업
 */
@Service
public class WorkRiskEval11Service {

    @Autowired
    private WorkRiskEval11Mapper workRiskEval11Mapper;

    /**
     * 작업위험성평가 단위작업 목록
     * 
     * @param parameter
     *            검색조건
     * @return 작업위험성평가 단위작업 목록
     * @throws Exception
     */
    public List<WorkRiskEval11UnitWork> getworkRiskEval11Lists(String plantCd, String unitWorkNm, String useYn, DefaultParam defaultParam) throws Exception {
        return workRiskEval11Mapper.getworkRiskEval11Lists(plantCd, unitWorkNm, useYn, defaultParam);
    }

    /**
     * 작업위험성평가 단위작업 등록
     * 
     * @param WorkRiskEval11UnitWork
     * @return 작업위험성평가 단위작업 Key값
     * @throws Exception
     */
    public String createWorkRiskEval11(WorkRiskEval11UnitWork workRiskEval11UnitWork) throws Exception {
        workRiskEval11Mapper.createWorkRiskEval11(workRiskEval11UnitWork);

        return workRiskEval11UnitWork.getUnitWorkCd();
    }

    /**
     * 작업위험성평가 단위작업 수정
     * 
     * @param WorkRiskEval11UnitWork
     * @return 작업위험성평가 단위작업 Key값
     * @throws Exception
     */
    public String updateWorkRiskEval11(WorkRiskEval11UnitWork workRiskEval11UnitWork) throws Exception {
        workRiskEval11Mapper.updateWorkRiskEval11(workRiskEval11UnitWork);

        return workRiskEval11UnitWork.getUnitWorkCd();
    }

    /**
     * 작업위험성평가 단위작업 상세
     * 
     * @param plantCd,
     *            unitWorkCd 평가번호
     * @return 작업위험성평가 단위작업 상세조회
     * @throws Exception
     */
    public WorkRiskEval11UnitWork getWorkRiskEval11Info(String plantCd, String unitWorkCd, DefaultParam defaultParam) throws Exception {
        return workRiskEval11Mapper.getWorkRiskEval11Info(plantCd, unitWorkCd, defaultParam);
    }

}
