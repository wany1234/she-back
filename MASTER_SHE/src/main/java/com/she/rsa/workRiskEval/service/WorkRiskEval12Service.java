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
import com.she.rsa.model.WorkRiskEval12RefInd;
import com.she.rsa.workRiskEval.mapper.WorkRiskEval12Mapper;

/**
 * 작업위험성평가 > 기준정보 > 관련지표
 */
@Service
public class WorkRiskEval12Service {

    @Autowired
    private WorkRiskEval12Mapper workRiskEval12Mapper;

    /**
     * 작업위험성평가 관련지표 목록
     * 
     * @param parameter
     *            검색조건
     * @return 작업위험성평가 관련지표 목록
     * @throws Exception
     */
    public List<WorkRiskEval12RefInd> getworkRiskEval12Lists(String plantCd, String indTypeCd, String setNm, String useYn, DefaultParam defaultParam) throws Exception {
        return workRiskEval12Mapper.getworkRiskEval12Lists(plantCd, indTypeCd, setNm, useYn, defaultParam);
    }

    /**
     * 작업위험성평가 관련지표 등록
     * 
     * @param WorkRiskEval12RefInd
     * @return 작업위험성평가 관련지표 Key값
     * @throws Exception
     */
    public String createWorkRiskEval12(WorkRiskEval12RefInd workRiskEval12RefInd) throws Exception {
        workRiskEval12Mapper.createWorkRiskEval12(workRiskEval12RefInd);

        return workRiskEval12RefInd.getSetVal();
    }

    /**
     * 작업위험성평가 관련지표 수정
     * 
     * @param WorkRiskEval12RefInd
     * @return 작업위험성평가 관련지표 Key값
     * @throws Exception
     */
    public String updateWorkRiskEval12(WorkRiskEval12RefInd workRiskEval12RefInd) throws Exception {
        workRiskEval12Mapper.updateWorkRiskEval12(workRiskEval12RefInd);

        return workRiskEval12RefInd.getSetVal();
    }

    /**
     * 작업위험성평가 관련지표 상세
     * 
     * @param plantCd,
     *            indTypeCd 평가번호, setVal
     * @return 작업위험성평가 관련지표 상세조회
     * @throws Exception
     */
    public WorkRiskEval12RefInd getWorkRiskEval12Info(String plantCd, String indTypeCd, String setVal, DefaultParam defaultParam) throws Exception {
        return workRiskEval12Mapper.getWorkRiskEval12Info(plantCd, indTypeCd, setVal, defaultParam);
    }

}
