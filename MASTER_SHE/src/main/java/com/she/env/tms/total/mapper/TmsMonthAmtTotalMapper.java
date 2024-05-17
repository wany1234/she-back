package com.she.env.tms.total.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository("com.she.env.tms.total.mapper.TmsMonthAmtTotalMapper")
public interface TmsMonthAmtTotalMapper {

    /**
     * TMS 집계 조회
     *
     * @param type
     *            구분
     * @param plantCd
     *            사업장
     * @param yearMonth
     *            년도월
     * @param stationCode
     *            TMS 측정소코드
     * @param itemCode
     *            TMS 측정항목코드
     * @return TMS 집계 목록
     * @throws Exception
     */
    public List<HashMap<String, Object>> getTmsAmtTotal(@Param("tmsType") String tmsType, @Param("plantCd") String plantCd, @Param("searchYears") List<HashMap<String, Object>> searchYears, @Param("stationCode") String stationCode, @Param("itemCode") String itemCode, @Param("searchYearStr") String searchYearStr) throws Exception;

}
