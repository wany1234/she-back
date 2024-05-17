package com.she.env.gas.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.env.gas.model.Emissions;

@Mapper
@Repository("com.she.env.gas.mapper.EmissionsMapper")
public interface EmissionsMapper {

    public List<Emissions> getDisByMonth(@Param("year") String year, @Param("plantCd") String plant) throws Exception;

    public List<Emissions> getDisByMonthEt(@Param("year") String year, @Param("plantCd") String plant) throws Exception;

    public Emissions getDisByMon(@Param("ghgFcltNo") int ghgFcltNo, @Param("year") String year, @Param("ghgOutActCd") String ghgOutActCd, @Param("ghgActDaCd") String ghgActDaCd) throws Exception;

    public int insertDisByMonth(Emissions dataList) throws Exception;

    public int updateDisByMonth(Emissions dataList) throws Exception;

    public int getGhgFcltCalMtdSeq() throws Exception;

    public int acceptDisByMonth(Emissions emissions) throws Exception;

    public int deleteSimulation(@Param("fcltCalcNo") int fcltCalcNo) throws Exception;

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
    public List<HashMap<String, Object>> getActDataStatus(@Param("mCols") String[] mCols, @Param("plantCd") String plantCd, @Param("fromYear") String fromYear, @Param("toYear") String toYear, @Param("mColStr") String mColStr) throws Exception;

    /**
     * 명세표 출력
     *
     * @param plantCd
     * @param year
     * @return
     * @throws Exception
     */
    public List<HashMap<String, Object>> getSpecSheet(@Param("plantCd") String plantCd, @Param("year") String year) throws Exception;
}
