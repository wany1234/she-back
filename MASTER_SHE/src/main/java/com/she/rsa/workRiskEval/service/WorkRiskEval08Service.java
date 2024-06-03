package com.she.rsa.workRiskEval.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.common.model.DefaultParam;
import com.she.rsa.model.WorkRiskEval08;
import com.she.rsa.workRiskEval.mapper.WorkRiskEval08Mapper;

/**
 * 작업위험성평가 중요위험 개선표 기능정의
 *
 */
@Service
public class WorkRiskEval08Service {

    @Autowired
    private WorkRiskEval08Mapper workRiskEval08Mapper;

    /**
     * 작업위험성평가 중요위험 개선표 목록
     * 
     * @param parameter
     *            검색조건
     * @return 작업위험성평가 중요위험 개선표 목록
     * @throws Exception
     */
    public List<WorkRiskEval08> getworkRiskEval08List(String plantCd, String deptCd, String evalTypeCd, String processNm, String unitWorkNm, String startYear, String endYear, DefaultParam defaultParam) throws Exception {
        return workRiskEval08Mapper.getworkRiskEval08List(plantCd, deptCd, evalTypeCd, processNm, unitWorkNm, startYear, endYear, defaultParam);
    }

}
