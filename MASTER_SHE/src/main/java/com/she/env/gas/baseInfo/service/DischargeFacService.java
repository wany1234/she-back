package com.she.env.gas.baseInfo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.env.gas.baseInfo.mapper.DischargeFacMapper;
import com.she.env.gas.model.DischargeFac;

@Service
public class DischargeFacService {

    @Autowired
    private DischargeFacMapper dischargeFacMapper;

    /**
     * 배출시설코드 조회
     * 
     * @param disClsCd
     *            배츨시설구분
     * @param disFacNm
     *            배출시설코드명
     * @return 배출시설 목록
     * @throws Exception
     */
    public List<DischargeFac> getDischargeFacs(String disClsCd, String disFacNm, String useYn) throws Exception {
        return dischargeFacMapper.getDischargeFacs(disClsCd, disFacNm, useYn);
    }

    /**
     * 배출시설 체크
     * 
     * @param disFacCd
     *            배출시설코드
     * @return 배출시설 목록
     * @throws Exception
     */
    public int countDischargeFac(String disFacCd) {
        return dischargeFacMapper.countDischargeFac(disFacCd);
    }

    /**
     * 배출시설코드 신규등록
     * 
     * @param parameter
     *            검색조건
     * @return 배출시설코드
     * @throws Exception
     */
    @Transactional
    public String createDischargeFac(DischargeFac dischargeFac) throws Exception {
        this.dischargeFacMapper.createDischargeFac(dischargeFac);
        return dischargeFac.getDisFacCd();
    }

    /**
     * 배출시설코드 상세조회
     * 
     * @param disFacCd
     *            배출시설코드
     * @return 배출시설코드 상세내역
     * @throws Exception
     */
    public DischargeFac getDischargeFac(String disFacCd) {
        return this.dischargeFacMapper.getDischargeFac(disFacCd);
    }

    /**
     * 배출시설코드 수정
     * 
     * @param parameter
     *            검색조건
     * @return 배출시설코드
     * @throws Exception
     */
    public String updateDischargeFac(DischargeFac dischargeFac) {
        this.dischargeFacMapper.updateDischargeFac(dischargeFac);
        return dischargeFac.getDisFacCd();
    }

}
