package com.she.env.tms.baseInfo.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.env.tms.model.TmsStationItemStd;

@Mapper
@Repository("com.she.env.tms.baseInfo.mapper.TmsStationItemStdMapper")
public interface TmsStationItemStdMapper {

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
    public List<HashMap<String, Object>> getTmsStationItemStds(@Param("plantCd") String plantCd, @Param("tmsType") String tmsType, @Param("startYear") String startYear, @Param("endYear") String endYear, @Param("stationCode") String stationCode, @Param("itemCode") String itemCode, @Param("yearsLaw") String[] yearsLaw,
            @Param("yearsWrn") String[] yearsWrn, @Param("yearsOper") String[] yearsOper, @Param("yearsLawStr") String yearsLawStr, @Param("yearsWrnStr") String yearsWrnStr, @Param("yearsOperStr") String yearsOperStr) throws Exception;

    /**
     * TMS 측정소항목별 허용기준 상세 조회
     *
     * @param stationItemCode
     *            TMS 측정소항목별 허용기준코드
     * @return TMS 측정소항목별 허용기준
     * @throws Exception
     */
    public List<TmsStationItemStd> getTmsStationItemStd(@Param("stationItemCode") String stationItemCode, @Param("dataYear") String dataYear) throws Exception;

    /**
     * TMS 측정소항목별 허용기준 신규등록
     *
     * @param TmsStationItemStd
     *            TMS 측정소항목별 허용기준
     * @return 등록 행 수
     * @throws Exception
     */
    public int saveTmsStationItemStd(TmsStationItemStd tmsStationItemStd) throws Exception;

}
