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
package com.she.rsa.assessHazop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.common.model.DefaultParam;
import com.she.rsa.assessHazop.mapper.AssessPlanProcessMapper;
import com.she.rsa.model.AssessPlanHazop;
import com.she.safety.change.service.ChangeService;

/**
 * 평가계획 기능정의
 */
@Service
public class AssessPlanProcessService {
    @Autowired
    private AssessPlanProcessMapper assessPlanProcessMapper;

    @Autowired
    private ChangeService changeService;

    /**
     * 평가계획 조회
     *
     * @return 평가계획 목록
     * @throws Exception
     */
    public List<AssessPlanHazop> getAssessPlanHazops(String plantCd, String startYear, String endYear, String assessStepCd, String userId, DefaultParam defaultParam) throws Exception {
        return assessPlanProcessMapper.getAssessPlanHazops(plantCd, startYear, endYear, assessStepCd, userId, defaultParam);
    }

}
