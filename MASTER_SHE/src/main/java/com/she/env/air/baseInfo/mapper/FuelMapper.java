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
package com.she.env.air.baseInfo.mapper;

import java.util.List;

import com.she.common.model.DefaultParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.env.air.model.Fuel;

/**
 * 연료 맵퍼
 *
 */
@Mapper
@Repository("com.she.env.air.baseInfo.mapper.FuelMapper")
public interface FuelMapper {

    /**
     * 연료 전체 조회
     *
     * @param useYn
     *            사용여부
     * @return 연료목록
     * @throws Exception
     */
    public List<Fuel> getFuels(@Param("useYn") String useYn, @Param("plantCd") String plantCd, @Param("eairFuelNm") String eairFuelNm, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 선택된 연료 상세 조회
     *
     * @param eairFuelCd
     *            연료코드
     * @return 연료
     * @throws Exception
     */
    public Fuel getFuel(@Param("eairFuelCd") String eairFuelCd) throws Exception;

    /**
     * 연료 신규등록
     *
     * @param fuel
     *            연료
     * @return 등록행수
     * @throws Exception
     */
    public int createFuel(Fuel fuel) throws Exception;

    /**
     * 연료 수정
     *
     * @param fuel
     *            연료
     * @return 수정행수
     * @throws Exception
     */
    public int updateFuel(Fuel fuel) throws Exception;

    /**
     * 연료명 중복체크
     *
     * @param eairFuelCd
     *            대기연료 코드
     * @param eairFuelNm
     *            대기연료 명칭
     * @return 중복 행 수
     * @throws Exception
     */
    public int checkFuel(@Param("eairFuelCd") String eairFuelCd, @Param("eairFuelNm") String eairFuelNm, @Param("plantCd") String plantCd) throws Exception;
}
