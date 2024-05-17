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
package com.she.env.waste.operationLog.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.common.model.DefaultParam;

/**
 * 폐기물 관리대장 맵퍼
 *
 */
@Mapper
@Repository("com.she.env.waste.operationLog.mapper.OperationMapper")
public interface OperationMapper {

    /**
     * 월별 폐기물 발생 및 처리량 조회
     * 
     * @param fromDate
     *            검색시작일
     * @param toDate
     *            검색종료일
     * @return 폐기물 발생 및 처리량
     * @throws Exception
     */
    public List<HashMap<String, Object>> getMonthlyDisposalRequestAndResult(@Param("fromDate") String fromDate, @Param("toDate") String toDate) throws Exception;

    /**
     * 월별 폐기물 발생 및 처리량 조회
     * 
     * @param fromDate
     *            검색시작일
     * @param toDate
     *            검색종료일
     * @return 폐기물 발생 및 처리량
     * @throws Exception
     */

    public List<HashMap<String, Object>> getOperationStatus(@Param("ewstWasteNo") String ewstWasteNo, @Param("ewstClassCd") String ewstClassCd, @Param("measureYear") String measureYear, @Param("plantCd") String plantCd, @Param("totalTypeCd") String totalTypeCd, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    public List<HashMap<String, Object>> getWaterUnitStatus(@Param("measureYear") String measureYear, @Param("plantCd") String plantCd, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

}
