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
import com.she.rsa.model.WorkRiskEval12RefInd;

@Mapper
@Repository("com.she.rsa.workRiskEval.mapper.WorkRiskEval12Mapper")
public interface WorkRiskEval12Mapper {

    /**
     * 작업위험성평가 관련지표 목록
     * 
     * @return 작업위험성평가 단위작업 목록
     * @throws Exception
     */
    public List<WorkRiskEval12RefInd> getworkRiskEval12Lists(@Param("plantCd") String plantCd, @Param("indTypeCd") String indTypeCd, @Param("setNm") String setNm, @Param("useYn") String useYn, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 작업위험성평가 관련지표 등록
     * 
     * @param WorkRiskEval12RefInd
     * @return 작업위험성평가 단위작업 Key값
     * @throws Exception
     */
    public int createWorkRiskEval12(WorkRiskEval12RefInd workRiskEval12RefInd) throws Exception;

    /**
     * 작업위험성평가 관련지표 수정
     * 
     * @param WorkRiskEval12RefInd
     * @return 작업위험성평가 단위작업 Key값
     * @throws Exception
     */
    public int updateWorkRiskEval12(WorkRiskEval12RefInd workRiskEval12RefInd) throws Exception;

    /**
     * 작업위험성평가 관련지표 상세
     * 
     * @param plantCd,
     *            unitWorkCd 평가번호
     * @return 작업위험성평가 관련지표 상세조회
     * @throws Exception
     */
    public WorkRiskEval12RefInd getWorkRiskEval12Info(@Param("plantCd") String plantCd, @Param("indTypeCd") String indTypeCd, @Param("setVal") String setVal, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

}
