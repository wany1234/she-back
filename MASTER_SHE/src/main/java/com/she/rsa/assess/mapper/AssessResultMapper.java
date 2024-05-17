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
package com.she.rsa.assess.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.common.model.DefaultParam;
import com.she.rsa.model.AssessResult;

@Mapper
@Repository("com.she.rsa.assess.mapper.AssessResultMapper")
public interface AssessResultMapper {

    /**
     * 작업허가서 항목 생성
     * 
     * @param assessResult
     *            평가결과 목록
     * @return 변경 행 수
     * @throws Exception
     */
    public int createJobRiskBook(AssessResult assessResult) throws Exception;

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
    public List<AssessResult> getAssessResultsJsa(@Param("assessPlanNo") String assessPlanNo, @Param("deptCd") String deptCd, @Param("processCd") String processCd, @Param("riskBookCheck") String riskBookCheck, @Param("searchFlag") String searchFla, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

}
