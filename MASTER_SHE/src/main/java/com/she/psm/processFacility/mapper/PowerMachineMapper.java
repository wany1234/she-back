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

import com.she.psm.model.PowerMachine;

@Mapper
@Repository("com.she.psm.processFacility.mapper.PowerMachineMapper")
public interface PowerMachineMapper {
    /**
     * 동력기계 조회
     * 
     * @param plantCd
     *            사업장
     * @param processNo
     *            공정
     * @param safFacilityCd
     *            설비코드
     * @param facilityNm
     *            설비명
     * @return 동력기계 목록
     * @throws Exception
     */
    public List<PowerMachine> getPowerMachines(@Param("plantCd") String plantCd, @Param("processCd") String processCd,
            @Param("safFacilityCd") String safFacilityCd, @Param("facilityNm") String facilityNm) throws Exception;

    /**
     * 동력기계 상세 조회
     * 
     * @param safFacilityCd
     *            설비코드
     * @return 동력기계
     * @throws Exception
     */
    public PowerMachine getPowerMachine(@Param("safFacilityCd") String safFacilityCd) throws Exception;

    /**
     * 동력기계 등록
     * 
     * @param PowerMachine
     * @return 변경 행 수
     * @throws Exception
     */
    public int createPowerMachine(PowerMachine powerMachine) throws Exception;

    /**
     * 동력기계 수정
     * 
     * @param PowerMachine
     * @return 변경 행 수
     * @throws Exception
     */
    public int updatePowerMachine(PowerMachine powerMachine) throws Exception;

}
