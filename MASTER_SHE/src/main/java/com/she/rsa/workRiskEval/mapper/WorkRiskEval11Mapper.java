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
package com.she.rsa.workRiskEval.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.common.model.DefaultParam;
import com.she.rsa.model.WorkRiskEval11UnitWork;

@Mapper
@Repository("com.she.rsa.workRiskEval.mapper.WorkRiskEval11Mapper")
public interface WorkRiskEval11Mapper {

    /**
     * 작업위험성평가 단위작업 목록
     * 
     * @return 작업위험성평가 단위작업 목록
     * @throws Exception
     */
    public List<WorkRiskEval11UnitWork> getworkRiskEval11Lists(@Param("plantCd") String plantCd, @Param("unitWorkNm") String unitWorkNm, @Param("useYn") String useYn, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 작업위험성평가 단위작업 등록
     * 
     * @param WorkRiskEval11UnitWork
     * @return 작업위험성평가 단위작업 Key값
     * @throws Exception
     */
    public int createWorkRiskEval11(WorkRiskEval11UnitWork workRiskEval11UnitWork) throws Exception;

    /**
     * 작업위험성평가 단위작업 수정
     * 
     * @param WorkRiskEval11UnitWork
     * @return 작업위험성평가 단위작업 Key값
     * @throws Exception
     */
    public int updateWorkRiskEval11(WorkRiskEval11UnitWork workRiskEval11UnitWork) throws Exception;

    /**
     * 작업위험성평가 단위작업 상세
     * 
     * @param plantCd,
     *            unitWorkCd 평가번호
     * @return 작업위험성평가 단위작업 상세조회
     * @throws Exception
     */
    public WorkRiskEval11UnitWork getWorkRiskEval11Info(@Param("plantCd") String plantCd, @Param("unitWorkCd") String unitWorkCd, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

}
