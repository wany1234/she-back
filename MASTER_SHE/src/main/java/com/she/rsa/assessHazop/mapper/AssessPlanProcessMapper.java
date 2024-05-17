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
package com.she.rsa.assessHazop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.common.model.DefaultParam;
import com.she.rsa.model.AssessPlanHazop;
import com.she.rsa.model.AssessPlanProcessFacility;
import com.she.rsa.model.AssessProcess;

@Mapper
@Repository("com.she.rsa.assessHazop.mapper.AssessPlanProcessMapper")
public interface AssessPlanProcessMapper {

    /**
     * 평가계획 조회
     *
     * @return 평가계획 목록
     * @throws Exception
     */
    public List<AssessPlanHazop> getAssessPlanHazops(@Param("plantCd") String plantCd, @Param("startYear") String startYear, @Param("endYear") String endYear, @Param("assessStepCd") String assessStepCd, @Param("userId") String userId, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 평가계획 진행상태 수정
     *
     * @param assessPlanProcess
     *            평가계획
     * @return 변경 행 수
     * @throws Exception
     */
    public int updateAssessPlanHazopStep(@Param("assessPlanNo") int assessPlanNo, @Param("assessStepCd") String assessStepCd) throws Exception;

    /**
     * 대상공정 조회
     *
     * @param assessPlanNo
     *            평가계획 번호
     * @return 대상공정 목록
     * @throws Exception
     */
    public List<AssessProcess> getAssessProcesses(@Param("assessPlanNo") int assessPlanNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 평가계획별 대상공정에 따른 설비 정보 조회
     *
     * @return 평가계획별 대상공정에 따른 설비 목록
     * @throws Exception
     */
    public List<AssessPlanProcessFacility> getAssessPlanProcessFacilitys(@Param("processCd") String processCd) throws Exception;

}
