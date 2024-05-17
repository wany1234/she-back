package com.she.env.gas.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.env.gas.model.DischargeFacilityData;
import com.she.env.gas.model.FacMgtByYear;

@Mapper
@Repository("com.she.env.gas.mapper.FacMgtByYearMapper")
public interface FacMgtByYearMapper {

    public List<FacMgtByYear> getdisFacByYears(@Param("year") String year, @Param("plantCd") String plantCd, @Param("fcltNm") String fcltNm) throws Exception;

    public FacMgtByYear getdisFacByYear(@Param("ghgFcltNo") int ghgFcltNo, @Param("year") String year) throws Exception;

    public int insertdisFacByYear(FacMgtByYear fctByYear) throws Exception;

    public int insertdisFacByYearDatas(DischargeFacilityData datas) throws Exception;

    public List<DischargeFacilityData> getdisFacYearDatas(@Param("ghgFcltNo") int ghgFcltNo, @Param("year") String year) throws Exception;

    public int deletedisFacByYearDatas(@Param("ghgFcltNo") int ghgFcltNo, @Param("year") String year) throws Exception;

    public int deletedisFacByYear(@Param("ghgFcltNo") int ghgFcltNo, @Param("year") String year) throws Exception;

    public int checkDisFacByYear(@Param("ghgFcltNo") int ghgFcltNo, @Param("year") String year) throws Exception;

}
