package com.she.env.gas.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.env.gas.model.FacCalByYear;

@Mapper
@Repository("com.she.env.gas.mapper.FacCalByYearMapper")
public interface FacCalByYearMapper {

    public List<FacCalByYear> getdisCalByYears(@Param("year") String year, @Param("plantCd") String plantCd, @Param("fcltNm") String fcltNm, @Param("ghgFcltCd") String ghgFcltCd, @Param("isSave") String isSave) throws Exception;

    public FacCalByYear getdisCalByYear(@Param("ghgFcltNo") int ghgFcltNo, @Param("year") String year, @Param("ghgOutActCd") String ghgOutActCd, @Param("ghgActDaCd") String ghgActDaCd) throws Exception;

    public int insertdisCalByYear(FacCalByYear dataList) throws Exception;

    public int updatedisCalByYear(FacCalByYear dataList) throws Exception;

    public int updatedisCalByYearActive(@Param("ghgFcltNo") int ghgFcltNo, @Param("year") String year, @Param("activeYn") String activeYn) throws Exception;

}
