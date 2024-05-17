package com.she.env.tms.baseInfo.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.she.env.tms.baseInfo.mapper.TmsStationItemStdMapper;
import com.she.env.tms.model.TmsStationItemStd;

@Service
public class TmsStationItemStdService {
    @Autowired
    private TmsStationItemStdMapper tmsStationItemStdMapper;

    /**
     * TMS 측정소항목별 허용기준 조회
     *
     * @param plantCd
     *            사업장코드
     * @param type
     *            측정소항목별 허용기준 구분
     * @return TMS 측정소항목별 허용기준 목록
     * @throws Exception
     */
    public List<HashMap<String, Object>> getTmsStationItemStds(String plantCd, String tmsType, String startYear, String endYear, String stationCode, String itemCode, String[] yearsLaw, String[] yearsWrn, String[] yearsOper) throws Exception {
        String yearsLawStr = String.join(", ", yearsLaw);
        String yearsWrnStr = String.join(", ", yearsWrn);
        String yearsOperStr = String.join(", ", yearsOper);

        return tmsStationItemStdMapper.getTmsStationItemStds(plantCd, tmsType, startYear, endYear, stationCode, itemCode, yearsLaw, yearsWrn, yearsOper, yearsLawStr, yearsWrnStr, yearsOperStr);
    }

    /**
     * TMS 측정소항목별 허용기준 상세 조회
     *
     * @param stationItemCode
     *            TMS 측정소항목별 허용기준코드
     * @return TMS 측정소항목별 허용기준
     * @throws Exception
     */
    public List<TmsStationItemStd> getTmsStationItemStd(String stationItemCode, String dataYear) throws Exception {
        return this.tmsStationItemStdMapper.getTmsStationItemStd(stationItemCode, dataYear);
    }

    /**
     * TMS 측정소항목별 허용기준 신규등록
     *
     * @param TmsStationItemStd
     *            TMS 측정소항목별 허용기준
     * @return TMS 측정소항목별 허용기준코드
     * @throws Exception
     */
    @Transactional
    public int saveTmsStationItemStd(TmsStationItemStd tmsStationItemStd) throws Exception {
        return this.tmsStationItemStdMapper.saveTmsStationItemStd(tmsStationItemStd);
    }

}
