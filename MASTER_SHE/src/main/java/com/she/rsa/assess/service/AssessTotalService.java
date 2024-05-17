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
import com.she.impr.model.ImprAct;
import com.she.rsa.assess.mapper.AssessTotalMapper;
import com.she.rsa.model.AssessTotal;

/**
 * 평가계획 기능정의
 *
 */
@Service
public class AssessTotalService {
    @Autowired
    private AssessTotalMapper assessTotalMapper;

    /**
     * 평가대상 목록 조회
     * 
     * @param assessNm
     *            평가명
     * @param startYear
     *            평가년도 시작일
     * @param endYear
     *            평가년도 종료일
     * @return 평가대상 목록
     * @throws Exception
     */
    public List<AssessTotal> getAssessTotals(String plantCd, String userId, String assessNm, String startYear, String endYear, String totalDivision, String flag, DefaultParam defaultParam) throws Exception {
        if ("KRAS".equals(flag)) {
            if ("PROCESS".equals(totalDivision)) {
                return assessTotalMapper.getAssessTotalKRASProcess(assessNm, startYear, endYear, defaultParam);
            } else {
                return assessTotalMapper.getAssessTotalKRASDept(assessNm, startYear, endYear, defaultParam);
            }
        } else if ("JSA".equals(flag)) {
            if ("PROCESS".equals(totalDivision)) {
                return assessTotalMapper.getAssessTotalJSAProcess(plantCd,userId, assessNm, startYear, endYear, defaultParam);
            } else {
                return assessTotalMapper.getAssessTotalJSADept(plantCd,userId, assessNm, startYear, endYear, defaultParam);
            }
        } else if ("CHARM".equals(flag)) {
            if ("PROCESS".equals(totalDivision)) {
                return assessTotalMapper.getAssessTotalCHARMProcess(plantCd, assessNm, startYear, endYear, defaultParam);
            } else {
                return assessTotalMapper.getAssessTotalCHARMDept(plantCd, assessNm, startYear, endYear, defaultParam);
            }
        } else {
            return assessTotalMapper.getAssessTotalHAZOPs(plantCd, assessNm, startYear, endYear, defaultParam);
        }
    }

    /**
     * 개선진행현황 조회
     * 
     * @param assessPlanNo
     *            평가계획 번호
     * @param deptCd
     *            부서코드
     * @return 개선진행현황 목록
     * @throws Exception
     */
    public List<ImprAct> getImprActs(String assessPlanNo, String deptCd, String processCd, String assessTypeNm, DefaultParam defaultParam) throws Exception {
        return assessTotalMapper.getImprActs(assessPlanNo, deptCd, processCd, assessTypeNm, defaultParam);
    }

    /**
     * 평가대상 직무 조회 (JSA)
     * 
     * @param assessPlanNo
     *            평가계획 번호
     * @param deptCd
     *            부서코드
     * @param processNo
     *            공정번호
     * @return 개선진행현황 목록
     * @throws Exception
     */
    public List<AssessTotal> getAssessJob(String assessPlanNo, String deptCd, String processCd, DefaultParam defaultParam) throws Exception {
        return assessTotalMapper.getAssessJob(assessPlanNo, deptCd, processCd, defaultParam);
    }

}
