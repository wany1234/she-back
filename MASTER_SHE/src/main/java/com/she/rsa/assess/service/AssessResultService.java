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
package com.she.rsa.assess.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.common.model.DefaultParam;
import com.she.rsa.assess.mapper.AssessResultMapper;
import com.she.rsa.model.AssessResult;

/**
 * 평가계획 기능정의
 *
 */
@Service
public class AssessResultService {
    @Autowired
    private AssessResultMapper assessResultMapper;

    /**
     * 평가대상 Kras 목록 조회
     * 
     * @param assessPlanNo
     *            평가계획 번호
     * @param deptCd
     *            부서코드
     * @param processNo
     *            공정번호
     * @return 평가직무 목록
     * @throws Exception
     */
    public int createJobRiskBook(List<AssessResult> assessResults) throws Exception {
        int riskBookNo = 0;

        for (AssessResult assessResult : assessResults) {
            if ("N".equals(assessResult.getJobRiskBookYn())) {
                assessResultMapper.createJobRiskBook(assessResult);
                riskBookNo = assessResult.getRiskBookNo();
            }
        }

        return riskBookNo;
    }

    /**
     * 평가대상 Jsa 목록 조회
     * 
     * @param assessPlanNo
     *            평가계획 번호
     * @param deptCd
     *            부서코드
     * @param processNo
     *            공정번호
     * @return 평가직무 목록
     * @throws Exception
     */
    public List<AssessResult> getAssessResultsJsa(String assessPlanNo, String deptCd, String processCd, String riskBookCheck, String searchFlag, DefaultParam defaultParam) throws Exception {
        return assessResultMapper.getAssessResultsJsa(assessPlanNo, deptCd, processCd, riskBookCheck, searchFlag, defaultParam);
    }

}
