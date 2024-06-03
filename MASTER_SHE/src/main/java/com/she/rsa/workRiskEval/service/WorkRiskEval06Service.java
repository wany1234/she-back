package com.she.rsa.workRiskEval.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.common.model.DefaultParam;
import com.she.rsa.model.WorkRiskEval06;
import com.she.rsa.workRiskEval.mapper.WorkRiskEval06Mapper;

/**
 * 작업위험성평가 팀 중요위험등록부 기능정의
 *
 */
@Service
public class WorkRiskEval06Service {

    @Autowired
    private WorkRiskEval06Mapper workRiskEval06Mapper;

    /**
     * 작업위험성평가 팀 중요위험등록부 목록
     * 
     * @param parameter
     *            검색조건
     * @return 작업위험성평가 팀 중요위험등록부 목록
     * @throws Exception
     */
    public List<WorkRiskEval06> getworkRiskEval06List(String plantCd, String deptCd, String evalTypeCd, String processNm, String unitWorkNm, String startYear, String endYear, DefaultParam defaultParam) throws Exception {
        return workRiskEval06Mapper.getworkRiskEval06List(plantCd, deptCd, evalTypeCd, processNm, unitWorkNm, startYear, endYear, defaultParam);
    }

}
