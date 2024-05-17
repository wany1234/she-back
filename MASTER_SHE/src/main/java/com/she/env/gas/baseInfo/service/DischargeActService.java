package com.she.env.gas.baseInfo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.env.gas.baseInfo.mapper.DischargeActMapper;
import com.she.env.gas.model.DischargeAct;

@Service
public class DischargeActService {

    @Autowired
    private DischargeActMapper dischargeActMapper;

    /**
     * 배출활동코드 조회
     * 
     * @param disClsCd
     *            배츨활동구분
     * @param disActNm
     *            배출활동명
     * @return 배출활동 목록
     * @throws Exception
     */
    public List<DischargeAct> getDischargeActs(String disClsCd, String disActNm, String useYn) throws Exception {
        return dischargeActMapper.getDischargeActs(disClsCd, disActNm, useYn);
    }

    /**
     * 배출활동코드 상세조회
     * 
     * @param disActCd
     *            배출활동코드
     * @return 배출활동코드 상세내역
     * @throws Exception
     */
    public DischargeAct getDischargeAct(String dischargeAct) {
        return this.dischargeActMapper.getDischargeAct(dischargeAct);
    }

    /**
     * 배출활동 체크
     * 
     * @param disActCd
     *            배출활동명
     * @return 배출활동 목록
     * @throws Exception
     */
    public int countDischargeAct(String disActCd) {
        return dischargeActMapper.countDischargeAct(disActCd);
    }

    /**
     * 배출활동코드 신규등록
     * 
     * @param parameter
     *            검색조건
     * @return 배출활동코드
     * @throws Exception
     */
    @Transactional
    public String createDischargeAct(DischargeAct dischargeAct) throws Exception {
        this.dischargeActMapper.createDischargeAct(dischargeAct);
        return dischargeAct.getDisActCd();
    }

    /**
     * 배출활동코드 수정
     * 
     * @param parameter
     *            검색조건
     * @return 배출활동코드
     * @throws Exception
     */
    public String updateDischargeAct(DischargeAct dischargeAct) {
        this.dischargeActMapper.updateDischargeAct(dischargeAct);
        return dischargeAct.getDisActCd();
    }

}
