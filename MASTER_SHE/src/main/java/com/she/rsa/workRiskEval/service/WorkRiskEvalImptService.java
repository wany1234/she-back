package com.she.rsa.workRiskEval.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.common.model.DefaultParam;
import com.she.rsa.model.WorkRiskEvalImprove;
import com.she.rsa.model.WorkRiskEvalImpt;
import com.she.rsa.workRiskEval.mapper.WorkRiskEvalImptMapper;

/**
 * 작업위험성평가 팀 중요위험등록부 기능정의
 *
 */
@Service
public class WorkRiskEvalImptService {

    @Autowired
    private WorkRiskEvalImptMapper workRiskEvalImptMapper;

    /**
     * 작업위험성평가 팀/사업장 중요위험등록부 목록
     * 
     * @param parameter
     *            검색조건
     * @return 작업위험성평가 팀/사업장 중요위험등록부 목록
     * @throws Exception
     */
    public List<WorkRiskEvalImpt> getworkRiskEvalImptList(String plantCd, String deptCd, String evalTypeCd, String processNm, String unitWorkNm, String startYear, String endYear, DefaultParam defaultParam) throws Exception {
        return workRiskEvalImptMapper.getworkRiskEvalImptList(plantCd, deptCd, evalTypeCd, processNm, unitWorkNm, startYear, endYear, defaultParam);
    }
    
    /**
     * 작업위험성평가 중요위험 개선표 목록
     * 
     * @param parameter
     *            검색조건
     * @return 작업위험성평가 중요위험 개선표 목록
     * @throws Exception
     */
    public List<WorkRiskEvalImprove> getworkRiskEvalImproveList(String plantCd, String deptCd, String evalTypeCd, String processNm, String unitWorkNm, String startYear, String endYear, DefaultParam defaultParam) throws Exception {
        return workRiskEvalImptMapper.getworkRiskEvalImproveList(plantCd, deptCd, evalTypeCd, processNm, unitWorkNm, startYear, endYear, defaultParam);
    }

}
