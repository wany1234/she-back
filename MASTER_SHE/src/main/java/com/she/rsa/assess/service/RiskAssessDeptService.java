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
import org.springframework.transaction.annotation.Transactional;

import com.she.rsa.assess.mapper.RiskAssessDeptMapper;
import com.she.rsa.model.RiskAssessDept;

/**
 * 대상부서 기능정의
 *
 */
@Service
public class RiskAssessDeptService {
    @Autowired
    private RiskAssessDeptMapper riskAssessDeptMapper;

    /**
     * 대상부서 조회
     * 
     * @param assessPlanNo
     *            평가계획 번호
     * @return 대상부서 목록
     * @throws Exception
     */
    public List<RiskAssessDept> getRiskAssessDepts(int assessPlanNo) throws Exception {
        return riskAssessDeptMapper.getRiskAssessDepts(assessPlanNo);
    }

    /**
     * 대상부서 상세 조회
     * 
     * @param assessPlanNo
     *            평가계획 번호
     * @param deptCd
     *            부서 코드
     * @param userId
     *            부서담당자ID
     * @return 대상부서
     * @throws Exception
     */
    public RiskAssessDept getRiskAssessDept(int assessPlanNo, String processCd, String userId) throws Exception {
        return riskAssessDeptMapper.getRiskAssessDept(assessPlanNo, processCd, userId);
    }
    // public RiskAssessDept getRiskAssessDept(int assessPlanNo, String
    // processCd, String userId) throws Exception {
    // return riskAssessDeptMapper.getRiskAssessDept(assessPlanNo, processCd,
    // userId);
    // }

    /**
     * 대상부서 신규등록
     * 
     * @param AssessPlan
     *            대상부서
     * @return 변경 행 수
     * @throws Exception
     */
    public int createRiskAssessDept(RiskAssessDept riskAssessDept) throws Exception {
        return riskAssessDeptMapper.createRiskAssessDept(riskAssessDept);
    }

    /**
     * 대상부서 신규등록
     * 
     * @param AssessPlan
     *            대상부서
     * @return 변경 행 수
     * @throws Exception
     */
    @Transactional
    public int createRiskAssessDeptMulti(List<RiskAssessDept> riskAssessDepts) throws Exception {
        int resultNo = 0;
        if (riskAssessDepts != null && riskAssessDepts.size() > 0) {
            for (RiskAssessDept riskAssessDept : riskAssessDepts) {
                resultNo += riskAssessDeptMapper.createRiskAssessDept(riskAssessDept);
            }
        }
        return resultNo;
    }

    /**
     * 대상부서 수정
     * 
     * @param AssessPlan
     *            대상부서
     * @return 변경 행 수
     * @throws Exception
     */
    @Transactional
    public int updateRiskAssessDept(RiskAssessDept riskAssessDept) throws Exception {
        int resultNo = 0;
        if (!riskAssessDept.getProcessCd().equals(riskAssessDept.getSelectedProcessCd())) {
            riskAssessDeptMapper.deleteRiskAssessDept(riskAssessDept.getAssessPlanNo(),
                    riskAssessDept.getSelectedProcessCd());
            resultNo += riskAssessDeptMapper.createRiskAssessDept(riskAssessDept);
        } else {
            resultNo += riskAssessDeptMapper.updateRiskAssessDept(riskAssessDept);
        }
        return resultNo;
    }

    /**
     * 대상부서 삭제
     * 
     * @param assessPlanNo
     *            평가계획 번호
     * @param deptCd
     *            부서 코드
     * @return 변경 행 수
     * @throws Exception
     */
    public int deleteRiskAssessDept(int assessPlanNo, String processCd) throws Exception {
        return riskAssessDeptMapper.deleteRiskAssessDept(assessPlanNo, processCd);
    }

    /**
     * 대상부서 삭제
     * 
     * @param assessPlanNo
     *            평가계획 번호
     * @param deptCd
     *            부서 코드
     * @return 변경 행 수
     * @throws Exception
     */
    public int deleteRiskAssessDeptMulti(List<RiskAssessDept> riskAssessDepts) throws Exception {
        int reusltNo = 0;
        if (riskAssessDepts != null && riskAssessDepts.size() > 0) {
            for (RiskAssessDept riskAssessDept : riskAssessDepts) {
                reusltNo += riskAssessDeptMapper.deleteRiskAssessDept(riskAssessDept.getAssessPlanNo(),
                        riskAssessDept.getProcessCd());
            }
        }
        return reusltNo;
    }

}
