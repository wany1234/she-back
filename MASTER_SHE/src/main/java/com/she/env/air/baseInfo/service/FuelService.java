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
package com.she.env.air.baseInfo.service;

import java.util.List;

import com.she.common.model.DefaultParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.env.air.baseInfo.mapper.FuelMapper;
import com.she.env.air.model.Fuel;

/**
 * 대기 연료 기능정의
 *
 */
@Service("EairFuelService")
public class FuelService {
    @Autowired
    private FuelMapper fuelMapper;

    /**
     * 연료 전체 조회
     *
     * @param useYn
     *            사용여부
     * @return 연료목록
     * @throws Exception
     */
    public List<Fuel> getFuels(String useYn, String plantCd, String eairFuelNm, DefaultParam defaultParam) throws Exception {
        return this.fuelMapper.getFuels(useYn, plantCd, eairFuelNm, defaultParam);
    }

    /**
     * 연료 상세 조회
     *
     * @param eairFuelCd
     *            연료코드
     * @return 연료
     * @throws Exception
     */
    public Fuel getFuel(String eairFuelCd) throws Exception {

        return this.fuelMapper.getFuel(eairFuelCd);
    }

    /**
     * 연료 신규등록
     *
     * @param fuel
     *            연료
     * @return 연료코드
     * @throws Exception
     */
    public String createFuel(Fuel fuel) throws Exception {
        int result = 0;
        result += this.fuelMapper.createFuel(fuel);
        return result > 0 ? fuel.getEairFuelCd() : "";
    }

    /**
     * 연료 수정
     *
     * @param fuel
     *            연료
     * @return 수정행수
     * @throws Exception
     */
    public String updateFuel(Fuel fuel) throws Exception {
        int result = 0;
        result += this.fuelMapper.updateFuel(fuel);
        return result > 0 ? fuel.getEairFuelCd() : "";
    }

    /**
     * 연료명 중복체크
     *
     * @param eairFuelCd
     *            대기연료 코드
     * @param eairFuelNm
     *            대기연료 명칭
     * @param plantCd
     *            사업장코드
     * @return 중복 행 수
     * @throws Exception
     */
    public int checkFuel(String eairFuelCd, String eairFuelNm, String plantCd) throws Exception {
        return this.fuelMapper.checkFuel(eairFuelCd, eairFuelNm, plantCd);
    }
}
