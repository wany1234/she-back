package com.she.env.gas.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.env.gas.baseInfo.mapper.DischargeFacilityMapper;
import com.she.env.gas.mapper.FacCalByYearMapper;
import com.she.env.gas.mapper.FacMgtByYearMapper;
import com.she.env.gas.model.DischargeFacilityData;
import com.she.env.gas.model.FacMgtByYear;

@Service
public class FacMgtByYearService {

    @Autowired
    private FacMgtByYearMapper mapper;

    @Autowired
    private FacCalByYearMapper calMapper;

    @Autowired
    private DischargeFacilityMapper disMapper;

    public List<FacMgtByYear> getdisFacByYears(String year, String plantCd, String fcltNm) throws Exception {
        return mapper.getdisFacByYears(year, plantCd, fcltNm);

    }

    public FacMgtByYear getdisFacByYear(int ghgFcltNo, String year) throws Exception {
        FacMgtByYear years = mapper.getdisFacByYear(ghgFcltNo, year);
        if (years != null) {
            years.setDisActDataList(this.getdisFacYearDatas(ghgFcltNo, year));
        }
        return years;
    }

    public List<DischargeFacilityData> getdisFacYearDatas(int ghgFcltNo, String year) throws Exception {
        return mapper.getdisFacYearDatas(ghgFcltNo, year);
    }

    @Transactional
    public int insertdisFacByYear(List<FacMgtByYear> fctByYear) throws Exception {
        int result = 0;
        if (fctByYear != null && fctByYear.size() > 0) {
            for (FacMgtByYear list : fctByYear) {
                if (mapper.checkDisFacByYear(list.getGhgFcltNo(), list.getYear()) > 0) {
                    // to do..
                } else {
                    List<DischargeFacilityData> dataList = disMapper.getdisFacDatas(list.getGhgFcltNo());
                    if (dataList != null && dataList.size() > 0) {
                        for (DischargeFacilityData data : dataList) {
                            data.setYear(list.getYear());
                            mapper.insertdisFacByYearDatas(data);
                        }
                    }
                    calMapper.updatedisCalByYearActive(list.getGhgFcltNo(), list.getYear(), "Y");
                    result += mapper.insertdisFacByYear(list);
                }
            }
        }
        return result;
    }

    @Transactional
    public int deletedisFacByYearDatas(int ghgFcltNo, String year) throws Exception {
        int result = mapper.deletedisFacByYearDatas(ghgFcltNo, year);
        return result;
    }

    @Transactional
    public int deletedisFacByYear(int ghgFcltNo, String year) throws Exception {

        int result = 0;
        result += mapper.deletedisFacByYear(ghgFcltNo, year);
        calMapper.updatedisCalByYearActive(ghgFcltNo, year, "N");
        mapper.deletedisFacByYearDatas(ghgFcltNo, year);
        return result;
    }
}
