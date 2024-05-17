package com.she.rsa.baseInfo.service;

import javax.transaction.Transactional;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.rsa.baseInfo.mapper.RiskMatrixMapper;
import com.she.rsa.model.AssessType;
import com.she.rsa.model.RiskMatrix;

@Service
public class RiskMatrixService {

    @Autowired
    private RiskMatrixMapper riskMatrixMapper;

    /**
     * 위험 Matrix 신규등록
     * 
     * @param riskMatrix
     *            위험 Matrix
     * @return 위험도 번호
     * @throws Exception
     */
    @Transactional
    public int createRiskMatrixs(AssessType assessType) throws Exception {
        this.riskMatrixMapper.deleteRiskMatrix(assessType.getAssessTypeNo());

        if (CollectionUtils.isNotEmpty(assessType.getRiskList())) {
            for (RiskMatrix risk : assessType.getRiskList()) {
                risk.setAssessTypeNo(assessType.getAssessTypeNo());

                this.riskMatrixMapper.createRiskMatrix(risk);
            }
        }

        return 0;
    }

}
