package com.she.env.gas.baseInfo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.env.gas.baseInfo.mapper.GasTypeMapper;
import com.she.env.gas.model.GasType;

@Service
public class GasTypeService {

    @Autowired
    private GasTypeMapper gasTypeMapper;

    /**
     * 온실가스코드 조회
     * 
     * @param gasTypeNm
     *            온실가스명
     * @return 온실가스 목록
     * @throws Exception
     */
    public List<GasType> getGasTypes(String gasTypeNm, String useYn) throws Exception {
        return gasTypeMapper.getGasTypes(gasTypeNm, useYn);
    }

    /**
     * 온실가스 체크
     * 
     * @param gasTypeCd
     *            온실가스명
     * @return 온실가스 목록
     * @throws Exception
     */
    public int countGasType(String gasTypeCd) {
        return gasTypeMapper.countGasType(gasTypeCd);
    }

    /**
     * 온실가스코드 신규등록
     * 
     * @param parameter
     *            검색조건
     * @return 온실가스코드
     * @throws Exception
     */
    @Transactional
    public String createGasType(GasType gasType) throws Exception {
        this.gasTypeMapper.createGasType(gasType);
        return gasType.getGasTypeCd();
    }

    /**
     * 온실가스코드 상세조회
     * 
     * @param gasTypeCd
     *            온실가스코드
     * @return 온실가스코드 상세내역
     * @throws Exception
     */
    public GasType getGasType(String gasTypeCd) {
        return this.gasTypeMapper.getGasType(gasTypeCd);
    }

    /**
     * 온실가스코드 수정
     * 
     * @param parameter
     *            검색조건
     * @return 온실가스코드
     * @throws Exception
     */
    public String updateGasType(GasType gasType) {
        this.gasTypeMapper.updateGasType(gasType);
        return gasType.getGasTypeCd();
    }

}
