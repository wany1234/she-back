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
package com.she.psm.processFacility.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.psm.model.SafetyValve;

@Mapper
@Repository("com.she.psm.processFacility.mapper.SafetyValveMapper")
public interface SafetyValveMapper {
    /**
     * 안전밸브 및 파열판 조회
     * 
     * @param plantCd
     *            사업장
     * @param processNo
     *            공정
     * @param safFacilityCd
     *            설비코드
     * @param facilityNm
     *            설비명
     * @return 안전밸브 및 파열판 목록
     * @throws Exception
     */
    public List<SafetyValve> getSafetyValves(@Param("plantCd") String plantCd, @Param("processCd") String processCd,
            @Param("safFacilityCd") String safFacilityCd, @Param("facilityNm") String facilityNm) throws Exception;

    /**
     * 안전밸브 및 파열판 상세 조회
     * 
     * @param safFacilityCd
     *            설비코드
     * @return 안전밸브 및 파열판
     * @throws Exception
     */
    public SafetyValve getSafetyValve(@Param("safFacilityCd") String safFacilityCd) throws Exception;

    /**
     * 안전밸브 및 파열판 등록
     * 
     * @param SafetyValve
     * @return 변경 행 수
     * @throws Exception
     */
    public int createSafetyValve(SafetyValve safetyValve) throws Exception;

    /**
     * 안전밸브 및 파열판 수정
     * 
     * @param SafetyValve
     * @return 변경 행 수
     * @throws Exception
     */
    public int updateSafetyValve(SafetyValve safetyValve) throws Exception;

}
