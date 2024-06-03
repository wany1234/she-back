package com.she.rsa.workRiskEval.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.common.model.DefaultParam;
import com.she.rsa.model.WorkRiskEval10DeptProcess;
import com.she.rsa.workRiskEval.mapper.WorkRiskEval10Mapper;

@Service
public class WorkRiskEval10Service {

    @Autowired
    private WorkRiskEval10Mapper workRiskEval10Mapper;

    /**
     * 작업위험성평가 작업위험성평가 공정별 부서 조회
     * 
     * @return 작업위험성평가 작업위험성평가 공정별 부서 목록
     * @throws Exception
     */
    public List<WorkRiskEval10DeptProcess> getworkRiskEval10Lists(String plantCd, String deptNm, DefaultParam defaultParam) throws Exception {
        return workRiskEval10Mapper.getworkRiskEval10Lists(plantCd, deptNm, defaultParam);
    }

    /**
     * 작업위험성평가 작업위험성평가 공정별 부서
     * 
     * @param parameter
     *            검색조건
     * @return 작업위험성평가 공정별 부서 상세조회
     * @throws Exception
     */
    public WorkRiskEval10DeptProcess getWorkRiskEval10Info(String deptCd, DefaultParam defaultParam) throws Exception {
        return workRiskEval10Mapper.getWorkRiskEval10Info(deptCd, defaultParam);
    }

}
