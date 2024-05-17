package com.she.env.gas.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.env.gas.mapper.FacCalByYearMapper;
import com.she.env.gas.model.FacCalByYear;

@Service
public class FacCalByYearService {

    @Autowired
    private FacCalByYearMapper mapper;

    public List<FacCalByYear> getdisCalByYears(String year, String plantCd, String fcltNm, String ghgFcltCd, String isSave) throws Exception {
        return mapper.getdisCalByYears(year, plantCd, fcltNm, ghgFcltCd, isSave);
    }

    public FacCalByYear getdisCalByYear(int ghgFcltNo, String year, String ghgActDaCd, String ghgOutActCd) throws Exception {
        return mapper.getdisCalByYear(ghgFcltNo, year, ghgActDaCd, ghgOutActCd);
    }

    @Transactional
    public int insertdisCalByYear(FacCalByYear fctByYear) throws Exception {

        return mapper.insertdisCalByYear(fctByYear);
    }

    @Transactional
    public int updatedisCalByYear(FacCalByYear fctByYear) throws Exception {
        return mapper.updatedisCalByYear(fctByYear);
    }

    @Transactional
    public int saveDisCalByYear(List<FacCalByYear> list) throws Exception {

        int cnt = 0;

        for (int i = 0; i < list.size(); i++) {

            FacCalByYear row = list.get(i);

            if (row.getFcltCalcMtdNo() == 0) {
                cnt += mapper.insertdisCalByYear(row);
            } else {
                cnt += mapper.updatedisCalByYear(row);
            }
        }

        return cnt;
    }

    @Transactional
    public int updatedisCalByYear(int ghgFcltNo, String year, String activeYn) throws Exception {
        int result = 0;
        mapper.updatedisCalByYearActive(ghgFcltNo, year, activeYn);
        return result;
    }

}
