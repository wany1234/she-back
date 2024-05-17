package com.she.env.gas.service;

import java.util.HashMap;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.collections.CollectionUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.env.gas.mapper.EmissionsMapper;
import com.she.env.gas.model.Emissions;

@Service
public class EmissionsService {

    @Autowired
    private EmissionsMapper mapper;

    public List<Emissions> getDisByMonth(String year, String plant) throws Exception {
        return mapper.getDisByMonth(year, plant);
    }

    public List<Emissions> getDisByMonthEt(String year, String plant) throws Exception {
        return mapper.getDisByMonthEt(year, plant);
    }

    public Emissions getDisByMon(int ghgFcltNo, String year, String ghgActDaCd, String ghgOutActCd) throws Exception {
        Emissions emissions = mapper.getDisByMon(ghgFcltNo, year, ghgActDaCd, ghgOutActCd);

        if (emissions == null) {
            emissions = new Emissions();
        }
        return emissions;
    }

    @Transactional
    public int insertDisByMonth(Emissions fctByYear) throws Exception {
        return mapper.insertDisByMonth(fctByYear);
    }

    @Transactional
    public int updateDisByMonth(Emissions fctByYear) throws Exception {
        return mapper.updateDisByMonth(fctByYear);
    }

    @Transactional
    public int acceptDisByMonth(List<Emissions> emissionses) throws Exception {
        int result = 0;
        if (CollectionUtils.isNotEmpty(emissionses)) {
            for (Emissions emissions : emissionses) {
                emissions.setFcltCalcMtdNo(mapper.getGhgFcltCalMtdSeq());
                result += mapper.acceptDisByMonth(emissions);

                // 저장되어져 있는 시뮬레이션은 삭제처리
                mapper.deleteSimulation(emissions.getFcltCalcNo());
            }
        }
        return result;
    }

    /**
     * 배출량 현황 조회
     *
     * @param mCols
     * @param plantCd
     * @param fromYear
     * @param toYear
     * @return
     * @throws Exception
     */
    public List<HashMap<String, Object>> getActDataStatus(@Param("mCols") String[] mCols, @Param("plantCd") String plantCd, @Param("fromYear") String fromYear, @Param("toYear") String toYear) throws Exception {
        String mColStr = String.join(", ", mCols);

        return mapper.getActDataStatus(mCols, plantCd, fromYear, toYear, mColStr);
    }

    /**
     * 명세표 출력
     *
     * @param plantCd
     * @param year
     * @return
     * @throws Exception
     */
    public List<HashMap<String, Object>> getSpecSheet(@Param("plantCd") String plantCd, @Param("year") String year) throws Exception {
        return mapper.getSpecSheet(plantCd, year);

    }

}
