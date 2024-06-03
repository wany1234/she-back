package com.she.rsa.workRiskEval.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.common.model.DefaultParam;
import com.she.rsa.model.WorkRiskEval07;
import com.she.rsa.workRiskEval.mapper.WorkRiskEval07Mapper;

/**
 * 작업위험성평가 사업장 중요위험등록부 기능정의
 *
 */
@Service
public class WorkRiskEval07Service {

    @Autowired
    private WorkRiskEval07Mapper workRiskEval07Mapper;

    /**
     * 작업위험성평가 사업장 중요위험등록부 목록
     * 
     * @param parameter
     *            검색조건
     * @return 작업위험성평가 사업장 중요위험등록부 목록
     * @throws Exception
     */
    public List<WorkRiskEval07> getworkRiskEval07List(String plantCd, String deptCd, String evalTypeCd, String processNm, String unitWorkNm, String startYear, String endYear, DefaultParam defaultParam) throws Exception {
        return workRiskEval07Mapper.getworkRiskEval07List(plantCd, deptCd, evalTypeCd, processNm, unitWorkNm, startYear, endYear, defaultParam);
    }
}
