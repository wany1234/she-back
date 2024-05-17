package com.she.env.gas.baseInfo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.env.gas.baseInfo.mapper.GasUnitMapper;
import com.she.env.gas.model.GasUnit;

@Service
public class GasUnitService {

    @Autowired
    private GasUnitMapper gasUnitMapper;

    /**
     * 산정기준단위 조회
     * 
     * @param unitNm
     * 
     * @param GasUnitNm
     *            산정기준단위명
     * @return 산정기준단위 목록
     * @throws Exception
     */
    public List<GasUnit> getGasUnits(String unitClsCd, String unitNm, String useYn) throws Exception {
        return gasUnitMapper.getGasUnits(unitClsCd, unitNm, useYn);
    }

    /**
     * 산정기준단위 체크
     * 
     * @param unitCd
     *            산정기준단위명
     * @return 산정기준단위 목록
     * @throws Exception
     */
    public int countGasUnit(String unitCd) {
        return gasUnitMapper.countGasUnit(unitCd);
    }

    /**
     * 산정기준단위코드 신규등록
     * 
     * @param parameter
     *            검색조건
     * @return 산정기준단위코드
     * @throws Exception
     */
    @Transactional
    public String createGasUnit(GasUnit gasUnit) throws Exception {
        this.gasUnitMapper.createGasUnit(gasUnit);
        return gasUnit.getUnitCd();
    }

    /**
     * 산정기준단위코드 상세조회
     * 
     * @param unitCd
     *            산정기준단위코드
     * @return 산정기준단위코드 상세내역
     * @throws Exception
     */
    public GasUnit getGasUnit(String unitCd) {
        return this.gasUnitMapper.getGasUnit(unitCd);
    }

    /**
     * 산정기준단위코드 수정
     * 
     * @param parameter
     *            검색조건
     * @return 산정기준단위코드
     * @throws Exception
     */
    public String updateGasUnit(GasUnit gasUnit) {
        this.gasUnitMapper.updateGasUnit(gasUnit);
        return gasUnit.getUnitCd();
    }

    /**
     * 산정기준단위 조회
     * 
     * @param unitCd
     *            산정기준단위코드 unitNm 산정기준단위
     * @return 산정기준단위 목록
     * @throws Exception
     */
    public List<GasUnit> getGasUnitCdNms() throws Exception {
        return this.gasUnitMapper.getGasUnitCdNms();
    }

}
